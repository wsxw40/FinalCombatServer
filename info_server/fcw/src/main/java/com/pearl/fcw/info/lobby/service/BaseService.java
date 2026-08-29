package com.pearl.fcw.info.lobby.service;

import com.pearl.fcw.info.core.persistence.cache.RedisClient;

public class BaseService {

    protected RedisClient redisClient;

    public RedisClient getRedisClient() {
        return redisClient;
    }

    public void setRedisClient(RedisClient redisClient) {
        this.redisClient = redisClient;
    }

}
