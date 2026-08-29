package com.pearl.fcw.info.core.persistence.route.cache;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import com.pearl.fcw.info.core.lock.InfoServerLock;
import com.pearl.fcw.info.core.persistence.BaseShardingEntity;
import com.pearl.fcw.info.core.persistence.cache.RedisClient;
import com.pearl.fcw.info.core.persistence.config.ClassMetadataConfig;
import com.pearl.fcw.info.core.persistence.config.ClassMetadataWrapper;
import com.pearl.fcw.info.core.persistence.route.MultiSourceRouter;
import com.pearl.fcw.info.core.persistence.route.RouteRule;
import com.pearl.fcw.info.core.persistence.utils.EntityUtils;

public class CacheMultiSourceRouter extends CacheSourceRouter implements MultiSourceRouter {

    private MultiSourceRouter multiSourceRouter = null;

    public CacheMultiSourceRouter(MultiSourceRouter multiSourceRouter, RedisClient cacheClient, InfoServerLock infoServerLock) {
        super(cacheClient, infoServerLock);
        this.multiSourceRouter = multiSourceRouter;
    }

    @Override
    public String getSchema() {
        return multiSourceRouter.getSchema();
    }

    @Override
    public RouteRule getRouteRule() {
        return multiSourceRouter.getRouteRule();
    }

    @Override
    public <T extends BaseShardingEntity> T queryById(long shardingKey, long id, Class<T> clazz) {
        final String key = CacheKeyUtil.getCacheKeyById(id, clazz);
        T entity;
        try {
            entity = cacheClient.get(key);
        } catch (Exception e) {
            log.warn("exception appera when try to get pojoInCache in " + getSchema() + " cacheClient.get(key), key = " + key);
            return multiSourceRouter.queryById(shardingKey, id, clazz);
        }

        if (entity == null) {
            entity = multiSourceRouter.queryById(shardingKey, id, clazz);
            if (entity != null) {
                try {
                    createInCache(entity);
                } catch (Exception e) {
                    log.error("error happend during createORMPojoInCache, exception ", e);
                }
            }
        }

        return entity;
    }

    @Override
    public <T extends BaseShardingEntity> List<T> queryByIds(long shardingKey, Collection<Long> ids, Class<T> clazz) {

        final List<T> result = new ArrayList<>(ids.size());

        final Collection<Long> idsNeedQueryFromDB = new HashSet<>();

        /* 从缓存批量获取POJO */
        final Collection<String> cacheKeys = new ArrayList<>(ids.size());
        for (long id : ids) {
            final String key = CacheKeyUtil.getCacheKeyById(id, clazz);
            cacheKeys.add(key);
        }

        final Map<String, Object> pojosInCache;
        try {
            pojosInCache = cacheClient.get(cacheKeys);
        } catch (Exception e) {
            log.warn("exception appera when try to get pojoInCaches in " + getSchema() + ", key = " + cacheKeys);
            return multiSourceRouter.queryByIds(shardingKey, ids, clazz);
        }

        for (Map.Entry<String, Object> entry : pojosInCache.entrySet()) {
            @SuppressWarnings("unchecked")
            T entity = (T) entry.getValue();
            if (entity != null) {
                result.add(entity);
            } else {
                Long dbId = CacheKeyUtil.getIdByCacheKey(entry.getKey());
                idsNeedQueryFromDB.add(dbId);
            }
        }

        /* 从数据库查找在cache中不存在的对象，并更新进cache */
        List<T> list = multiSourceRouter.queryByIds(shardingKey, idsNeedQueryFromDB, clazz);
        Map<Long, T> resultFromDB = EntityUtils.toPKMap(list);
        for (Map.Entry<Long, T> entry : resultFromDB.entrySet()) {
            if (entry.getValue() != null) {
                result.add(entry.getValue());   // 会出现Null的情况，表示指定ID的数据不存在，list中不保存
                try {
                    createInCache(entry.getValue());
                } catch (Exception e) {
                    log.error("error happened during createORMPojoInCache, exception ", e);
                }
            }
        }

        return result;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends BaseShardingEntity> Map<Long, T> queryByIdsForMap(long shardingKey, Collection<Long> ids, Class<T> clazz) {

        final Map<Long, T> result = new HashMap<>(ids.size());

        final Collection<Long> idsNeedQueryFromDB = new HashSet<>();

        /* 从缓存批量获取POJO */
        final Collection<String> cacheKeys = new ArrayList<>(ids.size());
        for (long id : ids) {
            final String key = CacheKeyUtil.getCacheKeyById(id, clazz);
            cacheKeys.add(key);
        }

        final Map<String, Object> pojosInCache;
        try {
            pojosInCache = cacheClient.get(cacheKeys);
        } catch (Exception e) {
            log.warn("exception appear when try to get pojoInCaches in " + getSchema() + " cacheClient.get(cacheKeys), key = " + cacheKeys);
            return multiSourceRouter.queryByIdsForMap(shardingKey, ids, clazz);
        }

        for (Map.Entry<String, Object> entry : pojosInCache.entrySet()) {
            T pojo = (T) entry.getValue();
            if (pojo != null) {
                result.put(pojo.getId(), pojo);
            } else {
                Long dbId = CacheKeyUtil.getIdByCacheKey(entry.getKey());
                idsNeedQueryFromDB.add(dbId);
            }
        }

        /* 从数据库查找在cache中不存在的对象，并更新进cache */
        Map<Long, T> resultFromDB = multiSourceRouter.queryByIdsForMap(shardingKey, idsNeedQueryFromDB, clazz);
        for (Map.Entry<Long, T> entry : resultFromDB.entrySet()) {
            result.put(entry.getKey(), entry.getValue());
            if (entry.getValue() != null) {     // 不可能的吧……
                try {
                    createInCache(entry.getValue());
                } catch (Exception e) {
                    log.error("error happend during createORMPojoInCache, exception ", e);
                }
            }
        }

        /* 处理null */
        if (result.size() != ids.size()) {
            ids.stream().filter(id -> !result.containsKey(id)).forEach(id -> {
                result.put(id, null);
            });
        }

        return result;
    }

    @Override
    public <T extends BaseShardingEntity> List<T> queryByFK(long shardingKey, long fk, Class<T> clazz, String fieldName) {
        List<Long> ids = queryIdsByFK(shardingKey, fk, clazz, fieldName);
        return queryByIds(shardingKey, ids, clazz);
    }

    @Override
    public <T extends BaseShardingEntity> List<T> queryByFK(long shardingKey, long fk, Class<T> clazz, Class<?> foreignClass) {
        ClassMetadataWrapper<T> wrapper = ClassMetadataConfig.getClassMetadataWrapper(clazz);
        String fieldName = wrapper.getSingleReferencedJavaFieldName(foreignClass);
        return queryByFK(shardingKey, fk, clazz, fieldName);
    }

    @Override
    public <T extends BaseShardingEntity> List<T> queryAll(Class<T> clazz) {
        // 对于非系统表不支持
        throw new UnsupportedOperationException();  // TODO
    }

    @Override
    public <T extends BaseShardingEntity> List<T> query(Class<T> clazz, String sql, Object... parameters) {
        return multiSourceRouter.query(clazz, sql, parameters);
    }

    @Override
    public <T extends BaseShardingEntity> List<T> query(Class<T> clazz, String namedSql, Map<String, Object> namedParameters) {
        return multiSourceRouter.query(clazz, namedSql, namedParameters);
    }

    @Override
    public <T extends BaseShardingEntity> List<T> query(Class<T> clazz, String namedSql, Map<String, Object> namedParameters, MultiQueryFilter filter) {
        return multiSourceRouter.query(clazz, namedSql, namedParameters, filter);
    }

    @Override
    public <T extends BaseShardingEntity> List<T> query(Class<T> clazz, String sql, T entity) {
        return multiSourceRouter.query(clazz, sql, entity);
    }

    @Override
    public <T extends BaseShardingEntity> List<T> query(long shardingKey, Class<T> clazz, String namedSql, Map<String, Object> namedParameters) {
        return multiSourceRouter.query(shardingKey, clazz, namedSql, namedParameters);
    }

    @Override
    public <T extends BaseShardingEntity> long getMaxID(Class<T> clazz) {
        return multiSourceRouter.getMaxID(clazz);
    }

    @Override
    public <T extends BaseShardingEntity> List<Long> queryIdsByFK(long shardingKey, long fk, Class<T> clazz, String fieldName) {
        String key = CacheKeyUtil.getCacheKeyByForeignId(fk, clazz, fieldName);
        List<Long> ids = cacheClient.safeGet(key);

        if (ids == null) {
            ids = multiSourceRouter.queryIdsByFK(shardingKey, fk, clazz, fieldName);
            cacheClient.set(key, ids);
        }

        return ids;
    }

    @Override
    public <T extends BaseShardingEntity> List<Long> queryAllIds(Class<T> clazz) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T extends BaseShardingEntity> int insert(T entity) {
        multiSourceRouter.insert(entity);
        super.createInCache(entity);
        return 1;
    }

    @Override
    public <T extends BaseShardingEntity> int update(T entity) {
        boolean success = super.updateInCache(entity);
        if (!success) {
            // 未成功放入缓存，则立即更新数据库
            return multiSourceRouter.update(entity);
        }
        return 1;
    }

    public <T extends BaseShardingEntity> int updateImmediately(T entity) {
        int result = multiSourceRouter.update(entity);
        if (result > 0) {
            super.updateInCacheOnly(entity);
        }
        return result;
    }

    @Override
    public <T extends BaseShardingEntity> int delete(T entity) {
        int result = multiSourceRouter.delete(entity);
        if (result > 0) {
            super.deleteInCache(entity);
        }
        return result;
    }

    @Override
    public <T extends BaseShardingEntity> int hardDelete(T entity) {
        int result = multiSourceRouter.hardDelete(entity);
        if (result > 0) {
            super.deleteInCache(entity);
        }
        return result;
    }

    @Override
    public void batchUpdate(Collection<BaseShardingEntity> entities) {
        multiSourceRouter.batchUpdate(entities);
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void batchUpdateCache(Collection<?> entities) {
        multiSourceRouter.batchUpdate((Collection<BaseShardingEntity>) entities);
    }

    @Override
    public List<String> getTableColumns(String tableName) {
        return multiSourceRouter.getTableColumns(tableName);
    }

    @Override
    public void execute(SqlCreationCallback callback) {
        multiSourceRouter.execute(callback);
    }

    @Override
    public void execute(SqlExecutionCallback callback) {
        multiSourceRouter.execute(callback);
    }

}
