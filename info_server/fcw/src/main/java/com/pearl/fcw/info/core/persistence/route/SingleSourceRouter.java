package com.pearl.fcw.info.core.persistence.route;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.pearl.fcw.info.core.persistence.BaseEntity;

public interface SingleSourceRouter {

    String getSchema();

    <T extends BaseEntity> T queryById(long id, Class<T> clazz);

    <T extends BaseEntity> List<T> queryByIds(Collection<Long> ids, Class<T> clazz);

    <T extends BaseEntity> Map<Long, T> queryByIdsForMap(Collection<Long> ids, Class<T> clazz);

    <T extends BaseEntity> List<T> queryByFK(long fk, Class<T> clazz, String fieldName);

    <T extends BaseEntity> List<T> queryByFK(long fk, Class<T> clazz, Class<?> foreignClass);

    <T extends BaseEntity> List<T> queryAll(Class<T> clazz);

    <T extends BaseEntity> List<T> query(Class<T> clazz, String sql, Object... parameters);

    <T extends BaseEntity> List<T> query(Class<T> clazz, String namedSql, Map<String, Object> namedParameters);

    <T extends BaseEntity> List<T> query(Class<T> clazz, String sql, T entity);

    List<Map<String, Object>> query(String sql, Object... parameters);

    <T extends BaseEntity> long getMaxID(Class<T> clazz);

    <T extends BaseEntity> List<Long> queryIdsByFK(long fk, Class<T> clazz, String fieldName);

    <T extends BaseEntity> List<Long> queryAllIds(Class<T> clazz);

    <T extends BaseEntity> int insert(T entity);

    <T extends BaseEntity> int insertNoIncrement(T entity);

    <T extends BaseEntity> int update(T entity);

    <T extends BaseEntity> int delete(T entity);

    <T extends BaseEntity> int hardDelete(T entity);

    int queryForInt(String sql, Object... parameters);

    int update(String sql, Object... parameters);

    List<Long> queryForLongList(String sql, Object... parameters);

    <Y> Y queryForObject(String sql, Class<Y> requiredType, Object... parameters);

    void batchUpdate(Collection<BaseEntity> entity);

    <T extends BaseEntity> void batchInsert(List<T> entities);

    <T extends BaseEntity> void batchInsertNoIncrement(List<T> entities);

    List<String> getTableColumns(String tableName);

    void execute(String sql);

    Map<String, Object> queryForMap(String sql);

    NamedParameterJdbcTemplate namedParameterJdbcTemplate();

}
