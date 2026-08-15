package com.pearl.fcw.core.cache.redis;

import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Protocol.Keyword;
import redis.clients.jedis.Response;
import redis.clients.util.SafeEncoder;

public abstract class RedisLock {

    protected static final long DEFAULT_RETRY_INTERVAL = 20L;

    protected static final byte[] BINX = SafeEncoder.encode("NX");
    protected static final byte[] BIPX = SafeEncoder.encode("PX");
    protected static final byte[] BIVALUE = new byte[] { 0 };

    protected final RedisClient redisClient;

    public RedisLock(RedisClient redisClient) {
        this.redisClient = redisClient;
    }

    protected boolean setNxpx(byte[] name, byte[] value, int expireMillis) {
        String res = redisClient.execute(jedis -> jedis.set(name, value, BINX, BIPX, expireMillis));
        return Keyword.OK.name().equals(res);
    }

    protected Response<String> setNxpx(Pipeline pipeline, byte[] name, byte[] value, int expireMillis) {
        return pipeline.set(name, value, BINX, BIPX, expireMillis);
    }

    /**
     * 在给定“超时时间”内，至少尝试一次获取锁。
     *
     * @param timeoutMillis 超时时间。在该时间段内,方法会尽量获取锁
     * @param leaseMillis 锁最大存活时间
     * @return 是否成功获取锁
     */
    public abstract boolean tryLock(int timeoutMillis, int leaseMillis);

    public abstract void unlock();

}
