package com.pearl.fcw.info.core.persistence.config;

import java.lang.reflect.Constructor;
import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;

import com.pearl.fcw.info.core.persistence.BaseEntity;
import com.pearl.fcw.info.core.persistence.config.ClassMetadata.FieldMetadata;
import com.pearl.fcw.info.lobby.pojo.GoSchema;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtNewMethod;

public abstract class ClassMetadataWrapper<T extends BaseEntity> {

    protected ClassMetadata classMetadata;

    protected String insertSql;
    protected String insertSqlNoIncrement;
    protected PreparedStatementCreator preparedStatementCreator;
    protected String namedParameterUpdateSql;
    protected String namedParameterDeleteSql;
    protected String hardDeleteSql;
    protected String queryByIdSql;
    protected String queryAllSql;
    protected String queryAllIdsSql;
    protected String queryMaxId;
    protected RowMapper<T> parameterizedRowMapper;

    public ClassMetadataWrapper(ClassMetadata cm) {

        this.classMetadata = cm;

        this.insertSql = createInsertSql(cm);
        this.insertSqlNoIncrement = createInsertSqlNoIncrement(cm);
        this.namedParameterUpdateSql = createNamedParameterUpdateSql(cm);
        this.namedParameterDeleteSql = createNamedParameterDeleteSql(cm);
        this.hardDeleteSql = createHardDeleteSql(cm);
        this.queryByIdSql = createQueryByIdSql(cm);
        this.queryAllSql = createQueryAllSql(cm);
        this.queryAllIdsSql = createQueryAllIdsSql(cm);
        this.queryMaxId = createQueryMaxId(cm);

        this.parameterizedRowMapper = new RowMapper<T>() {
            @Override
            public T mapRow(ResultSet rs, int rowNum) throws SQLException {
                return ClassMetadataWrapper.this.mapRow(rs, rowNum);
            }
        };

    }

    public ClassMetadata getClassMetadata() {
        return classMetadata;
    }

    public String getInsertSql() {
        return insertSql;
    }

    public String getInsertSqlNoIncrement() {
        return insertSqlNoIncrement;
    }

    public String getNamedParameterUpdateSql() {
        return namedParameterUpdateSql;
    }

    public String getNamedParameterDeleteSql() {
        return namedParameterDeleteSql;
    }

    public String getHardDeleteSql() {
        return hardDeleteSql;
    }

    public String getQueryByIdSql() {
        return queryByIdSql;
    }

    public String getQueryAllSql() {
        return queryAllSql;
    }

    public String getQueryAllIdsSql() {
        return queryAllIdsSql;
    }

    public String getQueryMaxId() {
        return queryMaxId;
    }

    public String getInsertSql(String tableSuffix) {
        return replaceTable(insertSql, tableSuffix);
    }

    public String getNamedParameterUpdateSql(String tableSuffix) {
        return replaceTable(namedParameterUpdateSql, tableSuffix);
    }

    public String getNamedParameterDeleteSql(String tableSuffix) {
        return replaceTable(namedParameterDeleteSql, tableSuffix);
    }

    public String getHardDeleteSql(String tableSuffix) {
        return replaceTable(hardDeleteSql, tableSuffix);
    }

    public String getQueryByIdSql(String tableSuffix) {
        return replaceTable(queryByIdSql, tableSuffix);
    }

    public String getQueryAllSql(String tableSuffix) {
        return replaceTable(queryAllSql, tableSuffix);
    }

    public String getQueryAllIdsSql(String tableSuffix) {
        return replaceTable(queryAllIdsSql, tableSuffix);
    }

    public String getQueryMaxId(String tableSuffix) {
        return replaceTable(queryMaxId, tableSuffix);
    }

    public String replaceTable(String sql, String tableSuffix) {
        String tableName = classMetadata.getTableName();
        String newTableName = tableName + tableSuffix;
        return sql.replaceFirst(tableName, newTableName);
    }

    public String replaceTableAll(String sql, String tableSuffix) {
        String tableName = classMetadata.getTableName();
        String newTableName = tableName + tableSuffix;
        return sql.replaceAll(tableName, newTableName);
    }

    public String getQueryByIds(Collection<Long> ids) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM ").append(classMetadata.getTableName()).append(" WHERE IS_DELETED = 0 AND (");
        String pkName = classMetadata.getPrimaryKey().getColumnName();
        for (Long l : ids) {
            sb.append(pkName).append(" = ").append(l).append(" OR ");
        }
        sb.delete(sb.length() - 4, sb.length());
        sb.append(")");
        return sb.toString();
    }

    public String getQueryByIds(String tableSuffix, Collection<Long> ids) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM ").append(classMetadata.getTableName()).append(tableSuffix).append(" WHERE IS_DELETED = 0 AND (");
        String pkName = classMetadata.getPrimaryKey().getColumnName();
        for (Long l : ids) {
            sb.append(pkName).append(" = ").append(l).append(" OR ");
        }
        sb.delete(sb.length() - 4, sb.length());
        sb.append(")");
        return sb.toString();
    }

    public String getQueryByFK(String fieldName) {
        FieldMetadata fm = classMetadata.getForeignKeys().get(fieldName);
        if (fm != null) {
            return "SELECT * FROM " + fm.getClassMetadata().getTableName() + " WHERE " + fm.getColumnName() + " = ? AND IS_DELETED = 0";
        }
        return null;
    }

    public String getQueryByFK(String tableSuffix, String fieldName) {
        FieldMetadata fm = classMetadata.getForeignKeys().get(fieldName);
        if (fm != null) {
            return "SELECT * FROM " + fm.getClassMetadata().getTableName() + tableSuffix + " WHERE " + fm.getColumnName() + " = ? AND IS_DELETED = 0";
        }
        return null;
    }

    public RowMapper<T> getParameterizedRowMapper() {
        return parameterizedRowMapper;
    }

    public PreparedStatementCreator getInsertPreparedStatementCreator(final BaseEntity entity) {
        return new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement ps = con.prepareStatement(insertSql, new String[] { classMetadata.getPrimaryKey().getColumnName() });
                setInsertValues(ps, entity);
                return ps;
            }
        };
    }

    public PreparedStatementCreator getInsertNoIncrementPreparedStatementCreator(final BaseEntity entity) {
        return new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement ps = con.prepareStatement(insertSqlNoIncrement, new String[] { classMetadata.getPrimaryKey().getColumnName() });
                setInsertNoIncrementValues(ps, entity);
                return ps;
            }
        };
    }

    public PreparedStatementCreator getInsertPreparedStatementCreator(final String tableSuffix, final BaseEntity entity) {
        return new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement ps = con.prepareStatement(replaceTable(insertSql, tableSuffix), new String[] { classMetadata.getPrimaryKey().getColumnName() });
                setInsertValues(ps, entity);
                return ps;
            }
        };
    }

    @SuppressWarnings("unchecked")
    protected static final <T extends BaseEntity> ClassMetadataWrapper<T> getInstance(ClassMetadata cm) {

        ClassPool pool = ClassPool.getDefault();
        CtClass icc;
        try {
            icc = pool.getCtClass(ClassMetadataWrapper.class.getName());
            CtClass cc = pool.makeClass(ClassMetadataWrapper.class.getPackage() + "." + "Wrapper_" + cm.getJavaClass().getSimpleName(), icc);

            createGetFKValue(cc, cm);
            createGetSingleReferencedJavaFieldName(cc, cm);
            createSetValues(cc, cm);
            createSetNoIncrementValues(cc, cm);
            createMapRow(cc, cm);

            Class<?> c = cc.toClass();
            Constructor<? extends BaseEntity> constructor = (Constructor<? extends BaseEntity>) c.getConstructors()[0];
            return (ClassMetadataWrapper<T>) constructor.newInstance(cm);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public abstract long getFKValue(BaseEntity entity, String fkName);

    public abstract String getSingleReferencedJavaFieldName(Class<?> clazz);

    public abstract void setInsertValues(PreparedStatement ps, BaseEntity entity) throws SQLException;

    public abstract void setInsertNoIncrementValues(PreparedStatement ps, BaseEntity entity) throws SQLException;

    public abstract T mapRow(ResultSet rs, int rowNum) throws SQLException;

    private String createInsertSql(ClassMetadata cm) {
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO ").append(cm.getTableName()).append(" (");
        int columnCount = 0;
        for (FieldMetadata fm : cm.getColumns().values()) {
            if (fm.isPrimaryKey() && fm.isAutoIncreaseId()) {
                continue;
            }
            columnCount++;
            if (columnCount > 1) {
                sb.append(", ");
            }
            sb.append(fm.getColumnName());
        }
        sb.append(") VALUES (");
        for (int i = 0; i < columnCount; i++) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append("?");
        }
        sb.append(")");

        return sb.toString();
    }

    private String createInsertSqlNoIncrement(ClassMetadata cm) {
        // 只处理系统表，并且ID为自增
        if (!cm.getSchema().equals(GoSchema.SYS) || !cm.getPrimaryKey().isAutoIncreaseId()) {
            return null;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO ").append(cm.getTableName()).append(" (");
        int columnCount = 0;
        for (FieldMetadata fm : cm.getColumns().values()) {
            columnCount++;
            if (columnCount > 1) {
                sb.append(", ");
            }
            sb.append(fm.getColumnName());
        }
        sb.append(") VALUES (");
        for (int i = 0; i < columnCount; i++) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append("?");
        }
        sb.append(")");
        return sb.toString();
    }

    private String createNamedParameterUpdateSql(ClassMetadata cm) {
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE ").append(cm.getTableName()).append(" SET");
        Iterator<FieldMetadata> iterator = cm.getUpdatableFieldMetadata().iterator();
        while (iterator.hasNext()) {
            FieldMetadata fm = iterator.next();
            if (fm.isUpdatable() && !fm.isDeleteFlag()) {
                sb.append(" ").append(fm.getColumnName()).append(" = ").append(":").append(fm.getJavaFieldName()).append(",");
            }
        }
        sb.delete(sb.length() - 1, sb.length());
        sb.append(" WHERE ").append(cm.getPrimaryKey().getColumnName()).append(" = :").append(cm.getPrimaryKey().getJavaFieldName());
        return sb.toString();
    }

    private String createNamedParameterDeleteSql(ClassMetadata cm) {
        return "UPDATE " + cm.getTableName() + " SET IS_DELETED = 1, DELETE_TIME = :deleteTime WHERE " + cm.getPrimaryKey().getColumnName() + " = :" + cm.getPrimaryKey().getJavaFieldName();
    }

    private String createHardDeleteSql(ClassMetadata cm) {
        return "DELETE FROM " + cm.getTableName() + " WHERE " + cm.getPrimaryKey().getColumnName() + " = ?";
    }

    private String createQueryByIdSql(ClassMetadata cm) {
        return "SELECT * FROM " + cm.getTableName() + " WHERE ID = ? AND IS_DELETED = 0";
    }

    private String createQueryAllSql(ClassMetadata cm) {
        return "SELECT * FROM " + cm.getTableName() + " WHERE IS_DELETED = 0";
    }

    private String createQueryAllIdsSql(ClassMetadata cm) {
        return "SELECT " + cm.getPrimaryKey().getColumnName() + " FROM " + cm.getTableName() + " WHERE IS_DELETED = 0";
    }

    private String createQueryMaxId(ClassMetadata cm) {
        return "SELECT MAX(" + cm.getPrimaryKey().getColumnName() + ") FROM " + cm.getTableName();
    }

    protected static final void createGetSingleReferencedJavaFieldName(CtClass cc, ClassMetadata cm) throws CannotCompileException {

        StringBuilder sb = new StringBuilder();

        sb.append("public String getSingleReferencedJavaFieldName(Class clazz) {");

        List<FieldMetadata> list = new ArrayList<FieldMetadata>(cm.getForeignKeys().values());
        for (int i = 0; i < list.size(); i++) {

            FieldMetadata one = list.get(i);
            boolean isSingle = true;
            for (int j = i + 1; j < list.size(); j++) {
                FieldMetadata another = list.get(j);
                if (one.getReferencedClass().equals(another.getReferencedClass())) {
                    isSingle = false;
                    break;
                }
            }

            sb.append("if (").append(one.getReferencedClass().getJavaClass().getName()).append(".class.equals(clazz)) {");
            if (isSingle) {
                sb.append("return \"").append(one.getReferencedJavaFieldName()).append("\";");
            } else {
                sb.append("throw new ").append(RuntimeException.class.getName()).append("();");
            }
            sb.append("}");

        }

        sb.append("throw new ").append(RuntimeException.class.getName()).append("();");
        sb.append("}");

        cc.addMethod(CtNewMethod.make(sb.toString(), cc));

    }

    protected static final void createGetFKValue(CtClass cc, ClassMetadata cm) throws CannotCompileException {

        StringBuilder sb = new StringBuilder();

        sb.append(" public long getFKValue(").append(BaseEntity.class.getName()).append(" entity, String fkName) {");
        sb.append(cm.getJavaClass().getName()).append(" o = (").append(cm.getJavaClass().getName()).append(") entity;");
        for (FieldMetadata fm : cm.getForeignKeys().values()) {
            String getMethodName = "get" + StringUtils.capitalize(fm.getReferencedJavaFieldName()) + "()";
            sb.append("if (\"").append(fm.getReferencedJavaFieldName()).append("\".equals(fkName)) {");
            sb.append("return o.").append(getMethodName).append(";");
            sb.append("}");
        }
        sb.append("throw new ").append(RuntimeException.class.getName()).append("();");
        sb.append("}");

        cc.addMethod(CtNewMethod.make(sb.toString(), cc));

    }

    /**
     * @see org.springframework.jdbc.support.JdbcUtils#getResultSetValue(ResultSet,
     *      int, Class)
     */
    protected static final void createSetValues(CtClass cc, ClassMetadata cm) throws CannotCompileException {

        StringBuilder sb = new StringBuilder();

        sb.append("public void setInsertValues(").append(PreparedStatement.class.getName()).append(" ps, ").append(BaseEntity.class.getName()).append(" entity").append(") throws ")
                .append(SQLException.class.getName()).append(" {");
        sb.append(cm.getJavaClass().getName()).append(" o = (").append(cm.getJavaClass().getName()).append(") entity;");

        String pattern = "ps.set{0}({1}, o.{2});";
        int i = 0;
        for (FieldMetadata fm : cm.getColumns().values()) {

            if (fm.isPrimaryKey() && fm.isAutoIncreaseId()) {
                continue;
            }

            Class<?> type = fm.getJavaField().getType();
            String getMethodName = "get" + StringUtils.capitalize(fm.getJavaFieldName()) + "()";
            i++;
            appendGetMethod(sb, pattern, i, type, getMethodName);

        }
        sb.append("}");

        cc.addMethod(CtNewMethod.make(sb.toString(), cc));
    }

    /**
     * @see org.springframework.jdbc.support.JdbcUtils#getResultSetValue(ResultSet,
     *      int, Class)
     */
    protected static final void createSetNoIncrementValues(CtClass cc, ClassMetadata cm) throws CannotCompileException {

        StringBuilder sb = new StringBuilder();

        sb.append("public void setInsertNoIncrementValues(").append(PreparedStatement.class.getName()).append(" ps, ").append(BaseEntity.class.getName()).append(" entity").append(") throws ")
                .append(SQLException.class.getName()).append(" {");
        sb.append(cm.getJavaClass().getName()).append(" o = (").append(cm.getJavaClass().getName()).append(") entity;");

        String pattern = "ps.set{0}({1}, o.{2});";
        int i = 0;
        for (FieldMetadata fm : cm.getColumns().values()) {

            Class<?> type = fm.getJavaField().getType();
            String getMethodName = "get" + StringUtils.capitalize(fm.getJavaFieldName()) + "()";
            i++;
            appendGetMethod(sb, pattern, i, type, getMethodName);

        }
        sb.append("}");

        cc.addMethod(CtNewMethod.make(sb.toString(), cc));
    }

    private static void appendGetMethod(StringBuilder sb, String pattern, int i, Class<?> type, String getMethodName) {
        if (String.class.equals(type)) {
            sb.append(MessageFormat.format(pattern, "String", i, getMethodName));

        } else if (boolean.class.equals(type)) {
            sb.append(MessageFormat.format(pattern, "Boolean", i, getMethodName));

        } else if (byte.class.equals(type)) {
            sb.append(MessageFormat.format(pattern, "Byte", i, getMethodName));

        } else if (short.class.equals(type)) {
            sb.append(MessageFormat.format(pattern, "Short", i, getMethodName));

        } else if (int.class.equals(type)) {
            sb.append(MessageFormat.format(pattern, "Int", i, getMethodName));

        } else if (long.class.equals(type)) {
            sb.append(MessageFormat.format(pattern, "Long", i, getMethodName));

        } else if (float.class.equals(type)) {
            sb.append(MessageFormat.format(pattern, "Float", i, getMethodName));

        } else if (double.class.equals(type)) {
            sb.append(MessageFormat.format(pattern, "Double", i, getMethodName));

        } else if (char.class.equals(type)) {
            sb.append(MessageFormat.format(pattern, "String", i, getMethodName));

        } else if (byte[].class.equals(type)) {
            sb.append(MessageFormat.format(pattern, "Bytes", i, getMethodName));

        } else if (java.sql.Time.class.equals(type)) {
            sb.append(MessageFormat.format(pattern, "Time", i, getMethodName));

        } else if (Timestamp.class.equals(type)) {
            sb.append(MessageFormat.format(pattern, "Timestamp", i, getMethodName));

        } else if (java.util.Date.class.equals(type)) {
            sb.append(MessageFormat.format(pattern, "Object", i, getMethodName));

        } else if (BigDecimal.class.equals(type)) {
            sb.append(MessageFormat.format(pattern, "BigDecimal", i, getMethodName));

        } else if (Blob.class.equals(type)) {
            sb.append(MessageFormat.format(pattern, "Blob", i, getMethodName));

        } else if (Clob.class.equals(type)) {
            sb.append(MessageFormat.format(pattern, "Clob", i, getMethodName));

        } else {
            sb.append(MessageFormat.format(pattern, "Object", i, getMethodName));

        }
    }

    /**
     * @see org.springframework.jdbc.support.JdbcUtils#getResultSetValue(ResultSet,
     *      int, Class)
     */
    protected static final void createMapRow(CtClass cc, ClassMetadata cm) throws CannotCompileException {

        StringBuilder sb = new StringBuilder();

        sb.append("public ").append(BaseEntity.class.getName()).append(" mapRow(").append(ResultSet.class.getName()).append(" rs, int rowNum").append(") throws ").append(SQLException.class.getName())
                .append(" {");
        sb.append(cm.getJavaClass().getName()).append(" o = new ").append(cm.getJavaClass().getName()).append("();");

        String pattern = "o.{0}(rs.get{1}(\"{2}\"));";
        for (FieldMetadata fm : cm.getColumns().values()) {

            Class<?> type = fm.getJavaField().getType();
            String setMethodName = "set" + StringUtils.capitalize(fm.getJavaFieldName());
            if (String.class.equals(type)) {
                sb.append(MessageFormat.format(pattern, setMethodName, "String", fm.getColumnName()));

            } else if (boolean.class.equals(type)) {
                sb.append(MessageFormat.format(pattern, setMethodName, "Boolean", fm.getColumnName()));

            } else if (byte.class.equals(type)) {
                sb.append(MessageFormat.format(pattern, setMethodName, "Byte", fm.getColumnName()));

            } else if (short.class.equals(type)) {
                sb.append(MessageFormat.format(pattern, setMethodName, "Short", fm.getColumnName()));

            } else if (int.class.equals(type)) {
                sb.append(MessageFormat.format(pattern, setMethodName, "Int", fm.getColumnName()));
            } else if (long.class.equals(type)) {
                sb.append(MessageFormat.format(pattern, setMethodName, "Long", fm.getColumnName()));

            } else if (float.class.equals(type)) {
                sb.append(MessageFormat.format(pattern, setMethodName, "Float", fm.getColumnName()));

            } else if (double.class.equals(type)) {
                sb.append(MessageFormat.format(pattern, setMethodName, "Double", fm.getColumnName()));

            } else if (char.class.equals(type)) {
                sb.append(MessageFormat.format(pattern, setMethodName, "String", fm.getColumnName()));

            } else if (Integer.class.equals(type) || Boolean.class.equals(type) || Byte.class.equals(type) || Short.class.equals(type) || Long.class.equals(type) || Float.class
                    .equals(type) || Double.class.equals(type) || Character.class.equals(type)) {
                String p = "o.{0}(({3}) rs.get{1}(\"{2}\"));";
                sb.append(MessageFormat.format(p, setMethodName, "Object", fm.getColumnName(), type.getName()));

            } else if (byte[].class.equals(type)) {
                sb.append(MessageFormat.format(pattern, setMethodName, "Bytes", fm.getColumnName()));

            } else if (java.sql.Time.class.equals(type)) {
                sb.append(MessageFormat.format(pattern, setMethodName, "Time", fm.getColumnName()));

            } else if (Timestamp.class.equals(type)) {
                sb.append(MessageFormat.format(pattern, setMethodName, "Timestamp", fm.getColumnName()));

            } else if (java.util.Date.class.equals(type)) {
                sb.append(MessageFormat.format(pattern, setMethodName, "Timestamp", fm.getColumnName()));

            } else if (BigDecimal.class.equals(type)) {
                sb.append(MessageFormat.format(pattern, setMethodName, "BigDecimal", fm.getColumnName()));

            } else if (Blob.class.equals(type)) {
                sb.append(MessageFormat.format(pattern, setMethodName, "Blob", fm.getColumnName()));

            } else if (Clob.class.equals(type)) {
                sb.append(MessageFormat.format(pattern, setMethodName, "Clob", fm.getColumnName()));

            } else {
                throw new UnsupportedOperationException(fm.getJavaFieldName() + " " + type);

            }

        }

        sb.append("return o;");
        sb.append("}");

        cc.addMethod(CtNewMethod.make(sb.toString(), cc));
    }

}
