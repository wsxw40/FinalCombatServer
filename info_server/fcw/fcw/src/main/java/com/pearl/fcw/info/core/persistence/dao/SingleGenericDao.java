package com.pearl.fcw.info.core.persistence.dao;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import com.pearl.fcw.info.core.persistence.BaseEntity;
import com.pearl.fcw.info.core.persistence.route.SingleSourceRouter;
import com.pearl.fcw.info.core.persistence.utils.EntityUtils;

public class SingleGenericDao<T extends BaseEntity> extends AbstractGenericDao<T> {

    protected SingleSourceRouter singleSourceRouter = null;

    public SingleGenericDao(Class<T> clazz) {
        super(clazz);
    }

    @PostConstruct
    public void init() {
        singleSourceRouter = super.getRouter();
    }

    public T get(int id) {
        return singleSourceRouter.queryById(id, clazz);
    }

    public Map<Integer, T> getAll() {
        List<T> list = singleSourceRouter.queryAll(clazz);
        return EntityUtils.toPKMap(list);
    }

    public List<T> getAllList() {
        return singleSourceRouter.queryAll(clazz);
    }

    public Map<Integer, T> getByIds(Collection<Integer> ids) {
        return singleSourceRouter.queryByIdsForMap(ids, clazz);
    }

    public List<T> getByIdsList(Collection<Integer> ids) {
        return singleSourceRouter.queryByIds(ids, clazz);
    }

    public Map<Integer, T> getByFK(int fk, Class<?> foreignClass) {
        List<T> list = singleSourceRouter.queryByFK(fk, clazz, foreignClass);
        return EntityUtils.toPKMap(list);
    }

    public List<T> getByFKList(int fk, Class<?> foreignClass) {
        return singleSourceRouter.queryByFK(fk, clazz, foreignClass);
    }

    public Map<Integer, T> getByFK(int fk, Class<?> foreignClass, String fieldName) {
        List<T> list = singleSourceRouter.queryByFK(fk, clazz, fieldName);
        return EntityUtils.toPKMap(list);
    }

    public List<T> getByFKList(int fk, Class<?> foreignClass, String fieldName) {
        return singleSourceRouter.queryByFK(fk, clazz, fieldName);
    }

    public T getSingleByFK(int fk, Class<?> foreignClass) {
        List<T> list = singleSourceRouter.queryByFK(fk, clazz, foreignClass);
        return list.isEmpty() ? null : list.get(0);
    }

    public T save(T entity) {
//        entity.setCreateTime(DateUtil.now().getTime());
        singleSourceRouter.insert(entity);
        return entity;
    }

    public T saveNoIncrement(T entity) {
//        entity.setCreateTime(DateUtil.now().getTime());
        singleSourceRouter.insertNoIncrement(entity);
        return entity;
    }

    public T merge(T entity) {
//        entity.setUpdateTime(DateUtil.now().getTime());
        singleSourceRouter.update(entity);
        return entity;
    }

    public void remove(T entity) {
//        entity.setDeleteTime(DateUtil.now().getTime());
        singleSourceRouter.delete(entity);
    }

    public int hardDelete(T entity) {
        return singleSourceRouter.hardDelete(entity);
    }

    public int getMaxID() {
        return singleSourceRouter.getMaxID(clazz);
    }

    public void batchInsert(final List<T> entities) {
        singleSourceRouter.batchInsert(entities);
    }

    public void batchInsertNoIncrement(final List<T> entities) {
        singleSourceRouter.batchInsertNoIncrement(entities);
    }

    protected SingleSourceRouter getSingleSourceRouter() {
        return this.singleSourceRouter;
    }

}
