package com.pearl.fcw.core.cache.redis;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Protocol.Keyword;
import redis.clients.jedis.Response;
import redis.clients.util.SafeEncoder;

public class MultiKeyNonReentrantLock extends RedisLock {

    private static final Logger logger = LoggerFactory.getLogger(MultiKeyNonReentrantLock.class);

    private final long retryInterval;
    private final List<byte[]> bikeys = new ArrayList<>();

    public MultiKeyNonReentrantLock(RedisClient redisClient, Collection<String> keys) {
        this(redisClient, keys, DEFAULT_RETRY_INTERVAL);
    }

    public MultiKeyNonReentrantLock(RedisClient redisClient, Collection<String> keys, long retryInterval) {
        super(redisClient);
        this.retryInterval = retryInterval;
        keys.stream().distinct().forEach(k -> this.bikeys.add(SafeEncoder.encode(k)));
    }

    @Override
    public boolean tryLock(int waitMillis, int leaseMillis) {
        return tryLockInner(waitMillis, leaseMillis).isEmpty();
    }

    /**
     * 在“等待时间”超时后，强制获取该锁，该方法保证最终会获得锁。
     *
     * 只应在必须获得锁的时候调用，如果存在强制获得锁的情况，为异常现象，应当在业务层记录详细日志已备调查。
     *
     * @param waitMillis 等待时间
     * @param leaseMillis 锁最大存活时间
     * @return 强制获取的锁的名称
     */
    public List<String> forceLock(int waitMillis, int leaseMillis) {
        List<byte[]> l = tryLockInner(waitMillis, leaseMillis);
        if (l.isEmpty()) {
            return Collections.emptyList();
        }

        List<String> res = new ArrayList<>();
        redisClient.consume(jedis -> {
            Pipeline p = jedis.pipelined();
            for (byte[] bikey : l) {
                res.add(SafeEncoder.encode(bikey));
                jedis.psetex(bikey, (long) leaseMillis, BIVALUE);
            }
            p.sync();
        });
        logger.warn("Forced lock keys {}", res);

        return res;
    }

    private List<byte[]> tryLockInner(int waitMillis, int leaseMillis) {
        if (waitMillis <= 0 || leaseMillis <= 0) {
            throw new IllegalArgumentException();
        }

        long failureTime = System.currentTimeMillis() + waitMillis;

        List<byte[]> l = new ArrayList<>(bikeys);
        while (true) {
            List<Response<String>> rl = new ArrayList<>();

            redisClient.consume(jedis -> {
                Pipeline p = jedis.pipelined();
                for (byte[] ba : l) {
                    Response<String> r = setNxpx(p, ba, BIVALUE, leaseMillis);
                    rl.add(r);
                }
                p.sync();
            });

            Iterator<byte[]> itr = l.iterator();
            for (Response<String> r : rl) {
                itr.next();
                if (Keyword.OK.name().equals(r.get())) {
                    itr.remove();
                }
            }

            if (l.isEmpty() || System.currentTimeMillis() > failureTime) {
                break;
            }

            try {
                Thread.sleep(retryInterval);
            } catch (InterruptedException e) {
                logger.warn("Error has occurred", e);
            }
        }

        return l;
    }

    @Override
    public void unlock() {
        redisClient.consume(jedis -> {
            Pipeline p = jedis.pipelined();
            bikeys.forEach(jedis::del);
            p.sync();
        });
    }

}
