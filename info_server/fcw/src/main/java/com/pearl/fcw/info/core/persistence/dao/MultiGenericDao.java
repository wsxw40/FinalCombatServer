package com.pearl.fcw.info.core.persistence.dao;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import com.pearl.fcw.info.core.persistence.BaseShardingEntity;
import com.pearl.fcw.info.core.persistence.route.MultiSourceRouter;
import com.pearl.fcw.info.core.persistence.utils.EntityUtils;
import com.pearl.fcw.info.lobby.utils.DateUtil;

public class MultiGenericDao<T extends BaseShardingEntity> extends AbstractGenericDao<T> {

    protected MultiSourceRouter multiSourceRouter = null;

    public MultiGenericDao(Class<T> clazz) {
        super(clazz);
    }

    @PostConstruct
    public void init() {
        multiSourceRouter = super.getRouter();
    }

    public T get(long shardingKey, long id) {
        return multiSourceRouter.queryById(shardingKey, id, clazz);
    }

    public Map<Long, T> getAll() {
        List<T> list = multiSourceRouter.queryAll(clazz);
        return EntityUtils.toPKMap(list);
    }

    public List<T> getAllList() {
        return multiSourceRouter.queryAll(clazz);
    }

    public Map<Long, T> getByIds(long shardingKey, Collection<Long> ids) {
        return multiSourceRouter.queryByIdsForMap(shardingKey, ids, clazz);
    }

    public List<T> getByIdsList(long shardingKey, Collection<Long> ids) {
        return multiSourceRouter.queryByIds(shardingKey, ids, clazz);
    }

    public Map<Long, T> getByFK(long shardingKey, long fk, Class<?> foreignClass) {
        List<T> list = multiSourceRouter.queryByFK(shardingKey, fk, clazz, foreignClass);
        return EntityUtils.toPKMap(list);
    }

    public List<T> getByFKList(long shardingKey, long fk, Class<?> foreignClass) {
        return multiSourceRouter.queryByFK(shardingKey, fk, clazz, foreignClass);
    }

    public Map<Long, T> getByFK(long shardingKey, long fk, Class<?> foreignClass, String fieldName) {
        List<T> list = multiSourceRouter.queryByFK(shardingKey, fk, clazz, fieldName);
        return EntityUtils.toPKMap(list);
    }

    public List<T> getByFKList(long shardingKey, long fk, Class<?> foreignClass, String fieldName) {
        return multiSourceRouter.queryByFK(shardingKey, fk, clazz, fieldName);
    }

    public T getSingleByFK(long shardingKey, long fk, Class<?> foreignClass) {
        List<T> list = multiSourceRouter.queryByFK(shardingKey, fk, clazz, foreignClass);
        return list.isEmpty() ? null : list.get(0);
    }

    public T save(T entity) {
        entity.setCreateTime(DateUtil.now());
        multiSourceRouter.insert(entity);
        return entity;
    }

    public T merge(T entity) {
        entity.setUpdateTime(DateUtil.now());
        multiSourceRouter.update(entity);
        return entity;
    }

    public void remove(T entity) {
        entity.setDeleteTime(DateUtil.now());
        multiSourceRouter.delete(entity);
    }

    public void hardDelete(T entity) {
        multiSourceRouter.hardDelete(entity);
    }

    public long getMaxID() {
        return multiSourceRouter.getMaxID(clazz);
    }

    protected MultiSourceRouter getMultiSourceRouter() {
        return this.multiSourceRouter;
    }

}
