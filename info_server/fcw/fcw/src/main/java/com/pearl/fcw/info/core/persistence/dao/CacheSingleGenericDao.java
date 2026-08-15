package com.pearl.fcw.info.core.persistence.dao;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import com.pearl.fcw.info.core.lock.InfoServerLock;
import com.pearl.fcw.info.core.persistence.BaseEntity;
import com.pearl.fcw.info.core.persistence.cache.RedisClient;
import com.pearl.fcw.info.core.persistence.route.cache.CacheSourceRouter;
import com.pearl.fcw.info.lobby.utils.ConfigurationUtil;

public class CacheSingleGenericDao<T extends BaseEntity> extends SingleGenericDao<T> {

    @Resource
    private RedisClient cacheClient = null;
    @Resource
    private InfoServerLock infoServerLock = null;

    public CacheSingleGenericDao(Class<T> clazz) {
        super(clazz);
    }

    @Override
    @PostConstruct
    public void init() {
        super.init();
        super.singleSourceRouter = CacheSourceRouter.getCacheSourceRouter(super.singleSourceRouter, cacheClient, infoServerLock, ConfigurationUtil.ORMCLIENT_INTERVAL_SECS,
                ConfigurationUtil.ORMCLIENT_SCAN_INTERVAL_SECS, ConfigurationUtil.ORMCLIENT_MAXDIRTY);
    }
}
