package com.pearl.fcw.info.core.persistence.route;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.pearl.fcw.info.core.persistence.BaseShardingEntity;

public interface MultiSourceRouter {

    String getSchema();

    RouteRule getRouteRule();

    <T extends BaseShardingEntity> T queryById(long shardingKey, long id, Class<T> clazz);

    <T extends BaseShardingEntity> List<T> queryByIds(long shardingKey, Collection<Long> ids, Class<T> clazz);

    <T extends BaseShardingEntity> Map<Long, T> queryByIdsForMap(long shardingKey, Collection<Long> ids, Class<T> clazz);

    <T extends BaseShardingEntity> List<T> queryByFK(long shardingKey, long fk, Class<T> clazz, String fieldName);

    <T extends BaseShardingEntity> List<T> queryByFK(long shardingKey, long fk, Class<T> clazz, Class<?> foreignClass);

    <T extends BaseShardingEntity> List<T> queryAll(Class<T> clazz);

    <T extends BaseShardingEntity> List<T> query(Class<T> clazz, String sql, Object... parameters);

    <T extends BaseShardingEntity> List<T> query(Class<T> clazz, String sql, Map<String, Object> namedParameters);

    <T extends BaseShardingEntity> List<T> query(Class<T> clazz, String namedSql, Map<String, Object> namedParameters, MultiQueryFilter filter);

    <T extends BaseShardingEntity> List<T> query(Class<T> clazz, String sql, T entity);

    <T extends BaseShardingEntity> List<T> query(long shardingKey, Class<T> clazz, String namedSql, Map<String, Object> namedParameters);

    <T extends BaseShardingEntity> long getMaxID(Class<T> clazz);

    <T extends BaseShardingEntity> List<Long> queryIdsByFK(long shardingKey, long fk, Class<T> clazz, String fieldName);

    <T extends BaseShardingEntity> List<Long> queryAllIds(Class<T> clazz);

    <T extends BaseShardingEntity> int insert(T entity);

    <T extends BaseShardingEntity> int update(T entity);

    <T extends BaseShardingEntity> int delete(T entity);

    <T extends BaseShardingEntity> int hardDelete(T entity);

    void batchUpdate(Collection<BaseShardingEntity> entity);

    List<String> getTableColumns(String tableName);

    void execute(SqlCreationCallback callback);

    void execute(SqlExecutionCallback callback);

    public interface SqlCreationCallback {

        String create(int shardId);

    }

    public interface SqlExecutionCallback {
        void create(int shardId, NamedParameterJdbcTemplate namedParameterJdbcTemplate);
    }

    public interface MultiQueryFilter {
        <T extends BaseShardingEntity> List<T> doFilter(List<T> result, List<T> current);
    }

}
