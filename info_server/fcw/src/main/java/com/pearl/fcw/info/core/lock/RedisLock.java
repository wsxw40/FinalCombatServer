package com.pearl.fcw.info.core.lock;

import com.pearl.fcw.info.core.persistence.cache.RedisClient;

import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Protocol.Keyword;
import redis.clients.jedis.Response;
import redis.clients.util.SafeEncoder;

public abstract class RedisLock {

    protected static final long DEFAULT_RETRY_INTERVAL = 10L; // 自旋的间隔,每次争锁失败会睡眠此间隔指定时间, 毫秒
    protected static final int DEFAULT_EXPIRE_MILLIS = 60 * 10; // 锁最大存活时间，秒

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
     * 在给定“等待时间”内，至少尝试一次获取锁。
     * @param waitMillis 等待时间。在该时间段内,方法会尽量获取锁, 单位：毫秒
     * @return 是否成功获取锁
     */
    public abstract boolean tryLock(String key, int waitMillis);

    public abstract void unlock(String key);
}
