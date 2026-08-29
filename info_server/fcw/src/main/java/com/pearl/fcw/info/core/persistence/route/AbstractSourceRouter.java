package com.pearl.fcw.info.core.persistence.route;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.pearl.fcw.info.core.persistence.BaseEntity;
import com.pearl.fcw.info.core.persistence.utils.EntityUtils;

public abstract class AbstractSourceRouter {

    protected static final Log log = LogFactory.getLog(AbstractSourceRouter.class);

    protected static final RowMapper<Long> longRowMapper = new RowMapper<Long>() {
        @Override
        public Long mapRow(ResultSet rs, int rowNum) throws SQLException {
            return rs.getLong(1);
        }
    };

    protected <T extends BaseEntity> int insert(NamedParameterJdbcTemplate namedParameterJdbcTemplate, PreparedStatementCreator creator, boolean autoIncreaseId, T entity) {
        int result = 0;
        if (autoIncreaseId) {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            result = namedParameterJdbcTemplate.getJdbcOperations().update(creator, keyHolder);
            entity.setId(keyHolder.getKey().longValue());
        } else {
            result = namedParameterJdbcTemplate.getJdbcOperations().update(creator);
        }
        return result;
    }

    protected <T extends BaseEntity> int insertNoIncrement(NamedParameterJdbcTemplate namedParameterJdbcTemplate, PreparedStatementCreator creator) {
        return namedParameterJdbcTemplate.getJdbcOperations().update(creator);
    }

    protected <T extends BaseEntity> int update(NamedParameterJdbcTemplate namedParameterJdbcTemplate, String namedSql, T entity) {
        return namedParameterJdbcTemplate.update(namedSql, new BeanPropertySqlParameterSource(entity));
    }

    protected <T extends BaseEntity> int update(NamedParameterJdbcTemplate namedParameterJdbcTemplate, String sql, Object... parameters) {
        return namedParameterJdbcTemplate.getJdbcOperations().update(sql, parameters);
    }

    protected <T extends BaseEntity> int update(NamedParameterJdbcTemplate namedParameterJdbcTemplate, String namedSql, Map<String, Object> namedParameters) {
        return namedParameterJdbcTemplate.update(namedSql, namedParameters);
    }

    protected <T extends BaseEntity> T queryForObject(NamedParameterJdbcTemplate namedParameterJdbcTemplate, RowMapper<T> parameterizedRowMapper, String sql, Object... parameters) {
        try {
            return namedParameterJdbcTemplate.getJdbcOperations().queryForObject(sql, parameterizedRowMapper, parameters);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    protected <T extends BaseEntity> List<T> query(NamedParameterJdbcTemplate namedParameterJdbcTemplate, RowMapper<T> parameterizedRowMapper, String sql, Object... parameters) {
        return namedParameterJdbcTemplate.getJdbcOperations().query(sql, parameterizedRowMapper, parameters);
    }

    protected <T extends BaseEntity> List<T> query(NamedParameterJdbcTemplate namedParameterJdbcTemplate, RowMapper<T> parameterizedRowMapper, String sql, T entity) {
        return namedParameterJdbcTemplate.getJdbcOperations().query(sql, parameterizedRowMapper, new BeanPropertySqlParameterSource(entity));
    }

    protected <T extends BaseEntity> List<T> query(NamedParameterJdbcTemplate namedParameterJdbcTemplate, RowMapper<T> parameterizedRowMapper, String namedSql,
            Map<String, Object> namedParameters) {
        return namedParameterJdbcTemplate.query(namedSql, namedParameters, parameterizedRowMapper);
    }

    protected List<Long> queryForLongList(NamedParameterJdbcTemplate namedParameterJdbcTemplate, String sql, Object... parameters) {
        return namedParameterJdbcTemplate.getJdbcOperations().query(sql, longRowMapper, parameters);
    }

    protected List<Long> queryForLongList(NamedParameterJdbcTemplate namedParameterJdbcTemplate, String namedSql, Map<String, Object> namedParameters) {
        return namedParameterJdbcTemplate.query(namedSql, namedParameters, longRowMapper);
    }

    protected int queryForInt(NamedParameterJdbcTemplate namedParameterJdbcTemplate, String sql, Object... parameters) {
        Number number = namedParameterJdbcTemplate.getJdbcOperations().queryForObject(sql, parameters, Integer.class);
        return (number != null ? number.intValue() : 0);
    }

    protected int queryForInt(NamedParameterJdbcTemplate namedParameterJdbcTemplate, String namedSql, Map<String, Object> namedParameters) {
        Number number = namedParameterJdbcTemplate.queryForObject(namedSql, namedParameters, Integer.class);
        return (number != null ? number.intValue() : 0);
    }

    protected long queryForLong(NamedParameterJdbcTemplate namedParameterJdbcTemplate, String sql, Object... parameters) {
        Number number = namedParameterJdbcTemplate.getJdbcOperations().queryForObject(sql, parameters, Long.class);
        return (number != null ? number.longValue() : 0);
    }

    protected long queryForLong(NamedParameterJdbcTemplate namedParameterJdbcTemplate, String namedSql, Map<String, Object> namedParameters) {
        Number number = namedParameterJdbcTemplate.queryForObject(namedSql, namedParameters, Long.class);
        return (number != null ? number.longValue() : 0);
    }

    protected <Y> Y queryForObject(NamedParameterJdbcTemplate namedParameterJdbcTemplate, String sql, Class<Y> requiredType, Object... parameters) {
        return namedParameterJdbcTemplate.getJdbcOperations().queryForObject(sql, requiredType, parameters);
    }

    protected ResultSet getTableMetaData(DataSource dataSource, String tableName) {
        ResultSet resultSet = null;
        try {
            DatabaseMetaData databaseMetaData = dataSource.getConnection().getMetaData();
            resultSet = databaseMetaData.getColumns(null, "%", tableName, "%");
        } catch (SQLException e) {
        }
        return resultSet;
    }

    protected static <T extends BaseEntity> Map<Long, T> toPKMap(Collection<Long> ids, Collection<T> entities, Class<T> clazz) {

        Map<Long, T> result = EntityUtils.toPKMap(entities);

        // 存在指定ID没有数据库数据对应的情况（该情况几率较小）
        if (result.size() != ids.size()) {
            for (Long id : ids) {
                if (!result.containsKey(id)) {
                    result.put(id, null);
                    log.warn("Missing ID, " + clazz + " " + id);
                }
            }
        }

        return result;
    }

}
