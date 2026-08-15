package com.pearl.fcw.info.core.lock;

import com.pearl.fcw.info.core.persistence.cache.RedisClient;

import redis.clients.util.SafeEncoder;

public class InfoServerLock extends RedisLock {

    public InfoServerLock(RedisClient redisClient) {
        super(redisClient);
    }

    @Override
    public boolean tryLock(String key, int waitMillis) {

        if (waitMillis <= 0) {
            throw new IllegalArgumentException();
        }
        byte[] biKey = SafeEncoder.encode(key);
        long failureTime = System.currentTimeMillis() + waitMillis;

        while (true) {
            boolean res = setNxpx(biKey, BIVALUE, DEFAULT_EXPIRE_MILLIS);
            if (res) {
                return true;
            }

            if (System.currentTimeMillis() > failureTime) {
                break;
            }

            try {
                Thread.sleep(DEFAULT_RETRY_INTERVAL);
            } catch (InterruptedException e) {
                return false;
            }
        }

        return false;
    }

    @Override
    public void unlock(String key) {
        redisClient.delete(SafeEncoder.encode(key));
    }
}
