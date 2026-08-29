package com.pearl.fcw.info.core.persistence.route;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.PostConstruct;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;

import com.pearl.fcw.info.core.persistence.BaseEntity;
import com.pearl.fcw.info.core.persistence.BaseShardingEntity;
import com.pearl.fcw.info.core.persistence.config.ClassMetadataConfig;
import com.pearl.fcw.info.core.persistence.config.ClassMetadataWrapper;

public class MultiSourceRouterImpl extends AbstractSourceRouter implements MultiSourceRouter {

    private String schema = null;
    private RouteRule routeRule = null;
    private Map<Integer, NamedParameterJdbcTemplate> namedParameterJdbcTemplates = new HashMap<>();

    private MultiSourceFactory factory;

    public MultiSourceRouterImpl() {

    }

    public MultiSourceRouterImpl(String schema, RouteRule routeRule, MultiSourceFactory factory) {
        this.schema = schema;
        this.routeRule = routeRule;
        this.factory = factory;
    }

    @PostConstruct
    public void init() {
        List<DataSourceInfo> infos = factory.getDataSourceInfos();
        for (DataSourceInfo info : infos) {
            NamedParameterJdbcTemplate sjt = new NamedParameterJdbcTemplate(info.getDataSource());
            for (Integer shardId : info.getShardIdSet()) {
                namedParameterJdbcTemplates.put(shardId, sjt);
            }
        }
    }

    @Override
    public String getSchema() {
        return schema;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }

    @Override
    public RouteRule getRouteRule() {
        return routeRule;
    }

    public void setRouteRule(RouteRule routeRule) {
        this.routeRule = routeRule;
    }

    public void setFactory(MultiSourceFactory factory) {
        this.factory = factory;
    }

    protected NamedParameterJdbcTemplate determineJdbcTemplate(int key) {
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = this.namedParameterJdbcTemplates.get(key);
        if (namedParameterJdbcTemplate == null) {
            throw new RuntimeException("Could not find NamedParameterJdbcTemplate with key: " + key);
        }
        return namedParameterJdbcTemplate;
    }

    @Override
    public <T extends BaseShardingEntity> T queryById(long shardingKey, long id, Class<T> clazz) {
        int key = routeRule.determineLookupKey(shardingKey);
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = determineJdbcTemplate(key);
        ClassMetadataWrapper<T> wrapper = ClassMetadataConfig.getClassMetadataWrapper(clazz);
        String tableSuffix = routeRule.getTableSuffix(key, shardingKey, wrapper.getClassMetadata().getCountPerGroup());
        return super.queryForObject(namedParameterJdbcTemplate, wrapper.getParameterizedRowMapper(), wrapper.getQueryByIdSql(tableSuffix), id);
    }

    @Override
    public <T extends BaseShardingEntity> List<T> queryByIds(long shardingKey, Collection<Long> ids, Class<T> clazz) {
        if (ids == null || ids.isEmpty()) {
            return Collections.emptyList();
        }
        int key = routeRule.determineLookupKey(shardingKey);
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = determineJdbcTemplate(key);
        ClassMetadataWrapper<T> wrapper = ClassMetadataConfig.getClassMetadataWrapper(clazz);
        String tableSuffix = routeRule.getTableSuffix(key, shardingKey, wrapper.getClassMetadata().getCountPerGroup());
        String sql = wrapper.getQueryByIds(tableSuffix, ids);
        return super.query(namedParameterJdbcTemplate, wrapper.getParameterizedRowMapper(), sql);
    }

    @Override
    public <T extends BaseShardingEntity> Map<Long, T> queryByIdsForMap(long shardingKey, Collection<Long> ids, Class<T> clazz) {
        if (ids == null || ids.isEmpty()) {
            return Collections.emptyMap();
        }
        List<T> list = queryByIds(shardingKey, ids, clazz);
        return toPKMap(ids, list, clazz);
    }

    @Override
    public <T extends BaseShardingEntity> List<T> queryByFK(long shardingKey, long fk, Class<T> clazz, String fieldName) {
        int key = routeRule.determineLookupKey(shardingKey);
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = determineJdbcTemplate(key);
        ClassMetadataWrapper<T> wrapper = ClassMetadataConfig.getClassMetadataWrapper(clazz);
        String tableSuffix = routeRule.getTableSuffix(key, shardingKey, wrapper.getClassMetadata().getCountPerGroup());
        return super.query(namedParameterJdbcTemplate, wrapper.getParameterizedRowMapper(), wrapper.getQueryByFK(tableSuffix, fieldName), fk);
    }

    @Override
    public <T extends BaseShardingEntity> List<T> queryByFK(long shardingKey, long fk, Class<T> clazz, Class<?> foreignClass) {
        int key = routeRule.determineLookupKey(shardingKey);
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = determineJdbcTemplate(key);
        ClassMetadataWrapper<T> wrapper = ClassMetadataConfig.getClassMetadataWrapper(clazz);
        String fieldName = wrapper.getSingleReferencedJavaFieldName(foreignClass);
        String tableSuffix = routeRule.getTableSuffix(key, shardingKey, wrapper.getClassMetadata().getCountPerGroup());
        return super.query(namedParameterJdbcTemplate, wrapper.getParameterizedRowMapper(), wrapper.getQueryByFK(tableSuffix, fieldName), fk);
    }

    @Override
    public <T extends BaseShardingEntity> List<T> queryAll(Class<T> clazz) {
        List<T> result = new ArrayList<>();
        ClassMetadataWrapper<T> wrapper = ClassMetadataConfig.getClassMetadataWrapper(clazz);
        for (Entry<Integer, NamedParameterJdbcTemplate> e : namedParameterJdbcTemplates.entrySet()) {
            for (int i = 0; i < wrapper.getClassMetadata().getCountPerGroup(); i++) {
                String tableSuffix = routeRule.getTableSuffix(e.getKey(), i);
                List<T> l = super.query(e.getValue(), wrapper.getParameterizedRowMapper(), wrapper.getQueryAllSql(tableSuffix));
                result.addAll(l);
            }
        }
        return result;
    }

    @Override
    public <T extends BaseShardingEntity> List<T> query(Class<T> clazz, String sql, Object... parameters) {
        List<T> result = new ArrayList<>();
        ClassMetadataWrapper<T> wrapper = ClassMetadataConfig.getClassMetadataWrapper(clazz);
        for (Entry<Integer, NamedParameterJdbcTemplate> e : namedParameterJdbcTemplates.entrySet()) {
            for (int i = 0; i < wrapper.getClassMetadata().getCountPerGroup(); i++) {
                String tableSuffix = routeRule.getTableSuffix(e.getKey(), i);
                List<T> l = super.query(e.getValue(), wrapper.getParameterizedRowMapper(), wrapper.replaceTable(sql, tableSuffix), parameters);
                result.addAll(l);
            }
        }
        return result;
    }

    @Override
    public <T extends BaseShardingEntity> List<T> query(Class<T> clazz, String namedSql, Map<String, Object> namedParameters) {
        List<T> result = new ArrayList<>();
        ClassMetadataWrapper<T> wrapper = ClassMetadataConfig.getClassMetadataWrapper(clazz);
        for (Entry<Integer, NamedParameterJdbcTemplate> e : namedParameterJdbcTemplates.entrySet()) {
            for (int i = 0; i < wrapper.getClassMetadata().getCountPerGroup(); i++) {
                String tableSuffix = routeRule.getTableSuffix(e.getKey(), i);
                List<T> l = super.query(e.getValue(), wrapper.getParameterizedRowMapper(), wrapper.replaceTable(namedSql, tableSuffix), namedParameters);
                result.addAll(l);
            }
        }
        return result;
    }

    @Override
    public <T extends BaseShardingEntity> List<T> query(Class<T> clazz, String namedSql, Map<String, Object> namedParameters, MultiQueryFilter filter) {
        List<T> result = new ArrayList<>();
        ClassMetadataWrapper<T> wrapper = ClassMetadataConfig.getClassMetadataWrapper(clazz);
        for (Entry<Integer, NamedParameterJdbcTemplate> e : namedParameterJdbcTemplates.entrySet()) {
            for (int i = 0; i < wrapper.getClassMetadata().getCountPerGroup(); i++) {
                String tableSuffix = routeRule.getTableSuffix(e.getKey(), i);
                List<T> l = super.query(e.getValue(), wrapper.getParameterizedRowMapper(), wrapper.replaceTableAll(namedSql, tableSuffix), namedParameters);
                result = filter.doFilter(result, l);
            }
        }
        return result;
    }

    @Override
    public <T extends BaseShardingEntity> List<T> query(Class<T> clazz, String sql, T entity) {
        List<T> result = new ArrayList<>();
        ClassMetadataWrapper<T> wrapper = ClassMetadataConfig.getClassMetadataWrapper(clazz);
        for (Entry<Integer, NamedParameterJdbcTemplate> e : namedParameterJdbcTemplates.entrySet()) {
            for (int i = 0; i < wrapper.getClassMetadata().getCountPerGroup(); i++) {
                String tableSuffix = routeRule.getTableSuffix(e.getKey(), i);
                List<T> l = super.query(e.getValue(), wrapper.getParameterizedRowMapper(), wrapper.replaceTable(sql, tableSuffix), entity);
                result.addAll(l);
            }
        }
        return result;
    }

    @Override
    public <T extends BaseShardingEntity> List<T> query(long shardingKey, Class<T> clazz, String namedSql, Map<String, Object> namedParameters) {
        int key = routeRule.determineLookupKey(shardingKey);
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = determineJdbcTemplate(key);
        ClassMetadataWrapper<T> wrapper = ClassMetadataConfig.getClassMetadataWrapper(clazz);
        String tableSuffix = routeRule.getTableSuffix(key, shardingKey, wrapper.getClassMetadata().getCountPerGroup());
        return super.query(namedParameterJdbcTemplate, wrapper.getParameterizedRowMapper(), wrapper.replaceTableAll(namedSql, tableSuffix), namedParameters);
    }

    @Override
    public <T extends BaseShardingEntity> long getMaxID(Class<T> clazz) {
        long result = 0;
        ClassMetadataWrapper<T> wrapper = ClassMetadataConfig.getClassMetadataWrapper(clazz);
        for (Entry<Integer, NamedParameterJdbcTemplate> e : namedParameterJdbcTemplates.entrySet()) {
            for (int i = 0; i < wrapper.getClassMetadata().getCountPerGroup(); i++) {
                String tableSuffix = routeRule.getTableSuffix(e.getKey(), i);
                long temp = super.queryForLong(e.getValue(), wrapper.getQueryMaxId(tableSuffix));
                if (temp > result) {
                    result = temp;
                }
            }
        }
        return result;
    }

    @Override
    public <T extends BaseShardingEntity> List<Long> queryIdsByFK(long shardingKey, long fk, Class<T> clazz, String fieldName) {
        int key = routeRule.determineLookupKey(shardingKey);
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = determineJdbcTemplate(key);
        ClassMetadataWrapper<T> wrapper = ClassMetadataConfig.getClassMetadataWrapper(clazz);
        String tableSuffix = routeRule.getTableSuffix(key, shardingKey, wrapper.getClassMetadata().getCountPerGroup());
        return super.queryForLongList(namedParameterJdbcTemplate, wrapper.getQueryByFK(tableSuffix, fieldName), fk);
    }

    @Override
    public <T extends BaseShardingEntity> List<Long> queryAllIds(Class<T> clazz) {
        List<Long> result = new ArrayList<>();
        ClassMetadataWrapper<T> wrapper = ClassMetadataConfig.getClassMetadataWrapper(clazz);
        for (Entry<Integer, NamedParameterJdbcTemplate> e : namedParameterJdbcTemplates.entrySet()) {
            for (int i = 0; i < wrapper.getClassMetadata().getCountPerGroup(); i++) {
                String tableSuffix = routeRule.getTableSuffix(e.getKey(), i);
                List<Long> l = super.queryForLongList(e.getValue(), wrapper.getQueryAllIdsSql(tableSuffix));
                result.addAll(l);
            }
        }
        return result;
    }

    @Override
    public <T extends BaseShardingEntity> int insert(T entity) {
        int key = routeRule.determineLookupKey(entity.getShardingKey());
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = determineJdbcTemplate(key);
        ClassMetadataWrapper<T> wrapper = ClassMetadataConfig.getClassMetadataWrapper(entity.getClass());
        String tableSuffix = routeRule.getTableSuffix(key, entity.getShardingKey(), wrapper.getClassMetadata().getCountPerGroup());
        return super.insert(namedParameterJdbcTemplate, wrapper.getInsertPreparedStatementCreator(tableSuffix, entity), wrapper.getClassMetadata().getPrimaryKey().isAutoIncreaseId(), entity);
    }

    @Override
    public <T extends BaseShardingEntity> int update(T entity) {
        int key = routeRule.determineLookupKey(entity.getShardingKey());
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = determineJdbcTemplate(key);
        ClassMetadataWrapper<T> wrapper = ClassMetadataConfig.getClassMetadataWrapper(entity.getClass());
        String tableSuffix = routeRule.getTableSuffix(key, entity.getShardingKey(), wrapper.getClassMetadata().getCountPerGroup());
        return super.update(namedParameterJdbcTemplate, wrapper.getNamedParameterUpdateSql(tableSuffix), entity);
    }

    @Override
    public <T extends BaseShardingEntity> int delete(T entity) {
        int key = routeRule.determineLookupKey(entity.getShardingKey());
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = determineJdbcTemplate(key);
        ClassMetadataWrapper<T> wrapper = ClassMetadataConfig.getClassMetadataWrapper(entity.getClass());
        String tableSuffix = routeRule.getTableSuffix(key, entity.getShardingKey(), wrapper.getClassMetadata().getCountPerGroup());
        return super.update(namedParameterJdbcTemplate, wrapper.getNamedParameterDeleteSql(tableSuffix), entity);
    }

    @Override
    public <T extends BaseShardingEntity> int hardDelete(T entity) {
        int key = routeRule.determineLookupKey(entity.getShardingKey());
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = determineJdbcTemplate(key);
        ClassMetadataWrapper<T> wrapper = ClassMetadataConfig.getClassMetadataWrapper(entity.getClass());
        String tableSuffix = routeRule.getTableSuffix(key, entity.getShardingKey(), wrapper.getClassMetadata().getCountPerGroup());
        return super.update(namedParameterJdbcTemplate, wrapper.getHardDeleteSql(tableSuffix), entity.getId());
    }

    @Override
    public void batchUpdate(Collection<BaseShardingEntity> entities) {

        Map<Integer, Map<Class<?>, TableData>> map = new HashMap<>();
        for (BaseShardingEntity e : entities) {

            if (e == null) {
                continue;
            }

            int shardId = routeRule.determineLookupKey(e.getShardingKey());

            Map<Class<?>, TableData> m = map.get(shardId);
            if (m == null) {
                m = new HashMap<>();
                map.put(shardId, m);
            }

            Class<?> clazz = e.getClass();
            TableData td = m.get(clazz);
            if (td == null) {
                td = new TableData();
                m.put(clazz, td);
            }

            ClassMetadataWrapper<BaseEntity> wrapper = ClassMetadataConfig.getClassMetadataWrapper(clazz);
            if (wrapper != null) {
                String tableSuffix = routeRule.getTableSuffix(shardId, e.getShardingKey(), wrapper.getClassMetadata().getCountPerGroup());
                td.add(tableSuffix, e);
            }
        }

        for (Map.Entry<Integer, Map<Class<?>, TableData>> entry : map.entrySet()) {
            NamedParameterJdbcTemplate sjt = determineJdbcTemplate(entry.getKey());
            for (Map.Entry<Class<?>, TableData> en : entry.getValue().entrySet()) {
                try {
                    ClassMetadataWrapper<BaseEntity> wrapper = ClassMetadataConfig.getClassMetadataWrapper(en.getKey());
                    for (Map.Entry<String, List<BaseEntity>> e : en.getValue().getData().entrySet()) {
                        try {
                            SqlParameterSource[] batch = SqlParameterSourceUtils.createBatch(e.getValue().toArray());
                            sjt.batchUpdate(wrapper.getNamedParameterUpdateSql(e.getKey()), batch);
                        } catch (Exception e1) {
                            log.warn("error occurred during batchUpdate, ", e1);
                        }
                    }
                } catch (Exception e) {
                    log.warn("error occurred during batchUpdate, ", e);
                }
            }
        }

    }

    private static class TableData {

        private final Map<String, List<BaseEntity>> map = new HashMap<>();

        public void add(String tableSuffix, BaseEntity e) {
            List<BaseEntity> l = map.get(tableSuffix);
            if (l == null) {
                l = new ArrayList<>();
                map.put(tableSuffix, l);
            }
            l.add(e);
        }

        public Map<String, List<BaseEntity>> getData() {
            return map;
        }

    }

    @Override
    public List<String> getTableColumns(String tableName) {
        //        if (dataSources.isEmpty()) {
        //            return null;
        //        }
        //        return super.getTableMetaData(dataSources.values().iterator().next(), tableName);
        throw new UnsupportedOperationException();
    }

    @Override
    public void execute(SqlCreationCallback callback) {
        for (Entry<Integer, NamedParameterJdbcTemplate> entry : namedParameterJdbcTemplates.entrySet()) {
            String sql = callback.create(entry.getKey());
            entry.getValue().getJdbcOperations().execute(sql);
        }
    }

    @Override
    public void execute(SqlExecutionCallback callback) {
        for (Entry<Integer, NamedParameterJdbcTemplate> entry : namedParameterJdbcTemplates.entrySet()) {
            callback.create(entry.getKey(), entry.getValue());
        }
    }

}
