package com.pearl.fcw.info.core.persistence.dao;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import com.pearl.fcw.info.core.lock.InfoServerLock;
import com.pearl.fcw.info.core.persistence.BaseShardingEntity;
import com.pearl.fcw.info.core.persistence.cache.RedisClient;
import com.pearl.fcw.info.core.persistence.route.cache.CacheSourceRouter;
import com.pearl.fcw.info.lobby.utils.ConfigurationUtil;

public class CacheMultiGenericDao<T extends BaseShardingEntity> extends MultiGenericDao<T> {

    @Resource
    private RedisClient cacheClient = null;
    @Resource
    private InfoServerLock infoServerLock = null;

    public CacheMultiGenericDao(Class<T> clazz) {
        super(clazz);
    }

    @Override
    @PostConstruct
    public void init() {
        super.init();
        super.multiSourceRouter = CacheSourceRouter.getCacheSourceRouter(super.multiSourceRouter, cacheClient, infoServerLock, ConfigurationUtil.ORMCLIENT_INTERVAL_SECS, ConfigurationUtil.ORMCLIENT_SCAN_INTERVAL_SECS, ConfigurationUtil.ORMCLIENT_MAXDIRTY);
    }

    public void setCacheClient(RedisClient cacheClient) {
        this.cacheClient = cacheClient;
    }

    public void setServerLock(InfoServerLock serverLock) {
        this.infoServerLock = serverLock;
    }



}
