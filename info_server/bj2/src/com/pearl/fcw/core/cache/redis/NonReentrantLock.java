package com.pearl.fcw.core.cache.redis;

import redis.clients.util.SafeEncoder;

public class NonReentrantLock extends RedisLock {

    private final long retryInterval;
    private final byte[] bikey;

    public NonReentrantLock(RedisClient redisClient, String key) {
        this(redisClient, key, DEFAULT_RETRY_INTERVAL);
    }

    public NonReentrantLock(RedisClient redisClient, String key, long retryInterval) {
        super(redisClient);
        this.retryInterval = retryInterval;
        this.bikey = SafeEncoder.encode(key);
    }

    @Override
    public boolean tryLock(int waitMillis, int leaseMillis) {
        if (waitMillis <= 0 || leaseMillis <= 0) {
            throw new IllegalArgumentException();
        }

        long failureTime = System.currentTimeMillis() + waitMillis;

        while (true) {
            boolean res = setNxpx(bikey, BIVALUE, leaseMillis);
            if (res) {
                return true;
            }

            if (System.currentTimeMillis() > failureTime) {
                break;
            }

            try {
                Thread.sleep(retryInterval);
            } catch (InterruptedException e) {
                return false;
            }
        }

        return false;
    }

    @Override
    public void unlock() {
        redisClient.del(bikey);
    }

}
