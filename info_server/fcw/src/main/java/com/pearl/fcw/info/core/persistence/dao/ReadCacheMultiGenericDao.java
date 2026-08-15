package com.pearl.fcw.info.core.persistence.dao;

import com.pearl.fcw.info.core.persistence.BaseShardingEntity;
import com.pearl.fcw.info.core.persistence.route.cache.CacheMultiSourceRouter;

public class ReadCacheMultiGenericDao<T extends BaseShardingEntity> extends CacheMultiGenericDao<T> {

    public ReadCacheMultiGenericDao(Class<T> clazz) {
        super(clazz);
    }

    @Override
    public T merge(T entity) {
        ((CacheMultiSourceRouter) multiSourceRouter).updateImmediately(entity);
        return entity;
    }

}
