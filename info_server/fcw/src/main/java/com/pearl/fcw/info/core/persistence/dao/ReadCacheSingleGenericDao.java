package com.pearl.fcw.info.core.persistence.dao;

import com.pearl.fcw.info.core.persistence.BaseEntity;
import com.pearl.fcw.info.core.persistence.route.cache.CacheSingleSourceRouter;
import com.pearl.fcw.info.lobby.utils.DateUtil;

public class ReadCacheSingleGenericDao<T extends BaseEntity> extends CacheSingleGenericDao<T> {

    public ReadCacheSingleGenericDao(Class<T> clazz) {
        super(clazz);
    }


    @Override
    public T merge(T entity) {
        entity.setUpdateTime(DateUtil.now());
        ((CacheSingleSourceRouter) singleSourceRouter).updateImmediately(entity);
        return entity;
    }

}
