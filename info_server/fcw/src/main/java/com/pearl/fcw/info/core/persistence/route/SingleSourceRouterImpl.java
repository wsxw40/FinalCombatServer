package com.pearl.fcw.info.core.persistence.route;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;

import com.pearl.fcw.info.core.persistence.BaseEntity;
import com.pearl.fcw.info.core.persistence.config.ClassMetadataConfig;
import com.pearl.fcw.info.core.persistence.config.ClassMetadataWrapper;
import com.pearl.fcw.info.lobby.utils.DateUtil;

public class SingleSourceRouterImpl extends AbstractSourceRouter implements SingleSourceRouter {

    private String schema = null;
    private DataSource dataSource = null;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate = null;

    public SingleSourceRouterImpl() {

    }

    @Override
    public String getSchema() {
        return schema;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public <T extends BaseEntity> T queryById(long id, Class<T> clazz) {
        ClassMetadataWrapper<T> wrapper = ClassMetadataConfig.getClassMetadataWrapper(clazz);
        return super.queryForObject(namedParameterJdbcTemplate, wrapper.getParameterizedRowMapper(), wrapper.getQueryByIdSql(), id);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends BaseEntity> List<T> queryByIds(Collection<Long> ids, Class<T> clazz) {
        if (ids == null || ids.isEmpty()) {
            return Collections.EMPTY_LIST;
        }
        ClassMetadataWrapper<T> wrapper = ClassMetadataConfig.getClassMetadataWrapper(clazz);
        String sql = wrapper.getQueryByIds(ids);
        return super.query(namedParameterJdbcTemplate, wrapper.getParameterizedRowMapper(), sql);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends BaseEntity> Map<Long, T> queryByIdsForMap(Collection<Long> ids, Class<T> clazz) {
        if (ids == null || ids.isEmpty()) {
            return Collections.EMPTY_MAP;
        }
        List<T> list = queryByIds(ids, clazz);
        return toPKMap(ids, list, clazz);
    }

    @Override
    public <T extends BaseEntity> List<T> queryByFK(long fk, Class<T> clazz, String fieldName) {
        ClassMetadataWrapper<T> wrapper = ClassMetadataConfig.getClassMetadataWrapper(clazz);
        return super.query(namedParameterJdbcTemplate, wrapper.getParameterizedRowMapper(), wrapper.getQueryByFK(fieldName), fk);
    }

    @Override
    public <T extends BaseEntity> List<T> queryByFK(long fk, Class<T> clazz, Class<?> foreignClass) {
        ClassMetadataWrapper<T> wrapper = ClassMetadataConfig.getClassMetadataWrapper(clazz);
        String fieldName = wrapper.getSingleReferencedJavaFieldName(foreignClass);
        return super.query(namedParameterJdbcTemplate, wrapper.getParameterizedRowMapper(), wrapper.getQueryByFK(fieldName), fk);
    }

    @Override
    public <T extends BaseEntity> List<T> queryAll(Class<T> clazz) {
        ClassMetadataWrapper<T> wrapper = ClassMetadataConfig.getClassMetadataWrapper(clazz);
        return super.query(namedParameterJdbcTemplate, wrapper.getParameterizedRowMapper(), wrapper.getQueryAllSql());
    }

    @Override
    public <T extends BaseEntity> List<T> query(Class<T> clazz, String sql, Object... parameters) {
        ClassMetadataWrapper<T> wrapper = ClassMetadataConfig.getClassMetadataWrapper(clazz);
        return super.query(namedParameterJdbcTemplate, wrapper.getParameterizedRowMapper(), sql, parameters);
    }

    @Override
    public <T extends BaseEntity> List<T> query(Class<T> clazz, String namedSql, Map<String, Object> namedParameters) {
        ClassMetadataWrapper<T> wrapper = ClassMetadataConfig.getClassMetadataWrapper(clazz);
        return super.query(namedParameterJdbcTemplate, wrapper.getParameterizedRowMapper(), namedSql, namedParameters);
    }

    @Override
    public <T extends BaseEntity> List<T> query(Class<T> clazz, String sql, T entity) {
        ClassMetadataWrapper<T> wrapper = ClassMetadataConfig.getClassMetadataWrapper(clazz);
        return super.query(namedParameterJdbcTemplate, wrapper.getParameterizedRowMapper(), sql, entity);
    }

    @Override
    public List<Map<String, Object>> query(String sql, Object... parameters) {
        return namedParameterJdbcTemplate.getJdbcOperations().queryForList(sql, parameters);
    }

    @Override
    public <T extends BaseEntity> long getMaxID(Class<T> clazz) {
        ClassMetadataWrapper<T> wrapper = ClassMetadataConfig.getClassMetadataWrapper(clazz);
        return super.queryForLong(namedParameterJdbcTemplate, wrapper.getQueryMaxId());
    }

    @Override
    public <T extends BaseEntity> List<Long> queryIdsByFK(long fk, Class<T> clazz, String fieldName) {
        ClassMetadataWrapper<T> wrapper = ClassMetadataConfig.getClassMetadataWrapper(clazz);
        return super.queryForLongList(namedParameterJdbcTemplate, wrapper.getQueryByFK(fieldName), fk);
    }

    @Override
    public <T extends BaseEntity> List<Long> queryAllIds(Class<T> clazz) {
        ClassMetadataWrapper<T> wrapper = ClassMetadataConfig.getClassMetadataWrapper(clazz);
        return super.queryForLongList(namedParameterJdbcTemplate, wrapper.getQueryAllIdsSql());
    }

    @Override
    public <T extends BaseEntity> int insert(T entity) {
        ClassMetadataWrapper<T> wrapper = ClassMetadataConfig.getClassMetadataWrapper(entity.getClass());
        return super.insert(namedParameterJdbcTemplate, wrapper.getInsertPreparedStatementCreator(entity), wrapper.getClassMetadata().getPrimaryKey().isAutoIncreaseId(), entity);
    }

    @Override
    public <T extends BaseEntity> int insertNoIncrement(T entity) {
        ClassMetadataWrapper<T> wrapper = ClassMetadataConfig.getClassMetadataWrapper(entity.getClass());
        return super.insertNoIncrement(namedParameterJdbcTemplate, wrapper.getInsertNoIncrementPreparedStatementCreator(entity));
    }

    @Override
    public <T extends BaseEntity> int update(T entity) {
        ClassMetadataWrapper<T> wrapper = ClassMetadataConfig.getClassMetadataWrapper(entity.getClass());
        return super.update(namedParameterJdbcTemplate, wrapper.getNamedParameterUpdateSql(), entity);
    }

    @Override
    public <T extends BaseEntity> int delete(T entity) {
        ClassMetadataWrapper<T> wrapper = ClassMetadataConfig.getClassMetadataWrapper(entity.getClass());
        return super.update(namedParameterJdbcTemplate, wrapper.getNamedParameterDeleteSql(), entity);
    }

    @Override
    public <T extends BaseEntity> int hardDelete(T entity) {
        ClassMetadataWrapper<T> wrapper = ClassMetadataConfig.getClassMetadataWrapper(entity.getClass());
        return super.update(namedParameterJdbcTemplate, wrapper.getHardDeleteSql(), entity.getId());
    }

    @Override
    public int queryForInt(String sql, Object... parameters) {
        return super.queryForInt(namedParameterJdbcTemplate, sql, parameters);
    }

    @Override
    public List<Long> queryForLongList(String sql, Object... parameters) {
        return super.queryForLongList(namedParameterJdbcTemplate, sql, parameters);
    }

    @Override
    public int update(String sql, Object... parameters) {
        return super.update(namedParameterJdbcTemplate, sql, parameters);
    }

    @Override
    public <Y> Y queryForObject(String sql, Class<Y> requiredType, Object... parameters) {
        return super.queryForObject(namedParameterJdbcTemplate, sql, requiredType, parameters);
    }

    @Override
    public void batchUpdate(Collection<BaseEntity> entities) {

        Map<Class<?>, List<BaseEntity>> map = new HashMap<>();
        for (BaseEntity e : entities) {

            if (e == null) {
                continue;
            }

            Class<?> clazz = e.getClass();
            List<BaseEntity> l = map.get(clazz);
            if (l == null) {
                l = new ArrayList<>();
                map.put(clazz, l);
            }
            l.add(e);

        }

        for (Map.Entry<Class<?>, List<BaseEntity>> entry : map.entrySet()) {
            try {

                ClassMetadataWrapper<BaseEntity> wrapper = ClassMetadataConfig.getClassMetadataWrapper(entry.getKey());
                SqlParameterSource[] batch = SqlParameterSourceUtils.createBatch(entry.getValue().toArray());
                namedParameterJdbcTemplate.batchUpdate(wrapper.getNamedParameterUpdateSql(), batch);
            } catch (Exception e) {
                log.warn("error occurred during batchUpdate, ", e);
            }
        }
    }

    @Override
    public <T extends BaseEntity> void batchInsert(final List<T> entities) {
        if (entities == null || entities.isEmpty()) {
            return;
        }

        final Date now = DateUtil.now();

        @SuppressWarnings("unchecked")
        Class<T> clazz = (Class<T>) entities.iterator().next().getClass();
        final ClassMetadataWrapper<BaseEntity> wrapper = ClassMetadataConfig.getClassMetadataWrapper(clazz);
        BatchPreparedStatementSetter setter = new BatchPreparedStatementSetter() {

            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                BaseEntity e = entities.get(i);
                e.setCreateTime(now);
                wrapper.setInsertValues(ps, e);
            }

            @Override
            public int getBatchSize() {
                return entities.size();
            }

        };
        namedParameterJdbcTemplate.getJdbcOperations().batchUpdate(wrapper.getInsertSql(), setter);
    }

    @Override
    public <T extends BaseEntity> void batchInsertNoIncrement(final List<T> entities) {
        if (entities == null || entities.isEmpty()) {
            return;
        }

        final Date now = DateUtil.now();

        @SuppressWarnings("unchecked")
        Class<T> clazz = (Class<T>) entities.iterator().next().getClass();
        final ClassMetadataWrapper<BaseEntity> wrapper = ClassMetadataConfig.getClassMetadataWrapper(clazz);
        BatchPreparedStatementSetter setter = new BatchPreparedStatementSetter() {

            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                BaseEntity e = entities.get(i);
                e.setCreateTime(now);
                wrapper.setInsertNoIncrementValues(ps, e);
            }

            @Override
            public int getBatchSize() {
                return entities.size();
            }

        };
        namedParameterJdbcTemplate.getJdbcOperations().batchUpdate(wrapper.getInsertSqlNoIncrement(), setter);
    }

    @Override
    public List<String> getTableColumns(String tableName) {
        List<String> result = new ArrayList<>();
        Connection conn = null;
        ResultSet rs = null;
        try {
            conn = dataSource.getConnection();
            DatabaseMetaData databaseMetaData = conn.getMetaData();
            rs = databaseMetaData.getColumns(null, "%", tableName, "%");
            while (rs.next()) {
                result.add(rs.getString(4));
            }
        } catch (SQLException e) {
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                }
            }
        }
        return result;
    }

    @Override
    public void execute(String sql) {
        namedParameterJdbcTemplate.getJdbcOperations().execute(sql);
    }

    @Override
    public Map<String, Object> queryForMap(String sql) {
        return namedParameterJdbcTemplate.getJdbcOperations().queryForMap(sql);
    }

    @Override
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate() {
        return namedParameterJdbcTemplate;
    }

}
