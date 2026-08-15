package com.pearl.fcw.info.core.persistence.config;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.GenerationType;

public class ClassMetadata {

    private Class<?> javaClass;

    private String tableName;
    private String schema;
    private int countPerGroup = 1;
    private String partitionHashColumn = "";
    private int partitions = 0;
    private String[] uniqueFieldNames = null;

    private CacheStrategy cacheStrategy = null;
    private int cacheTimeoutSecs;

    private Map<String, FieldMetadata> columns = new LinkedHashMap<>();
    private FieldMetadata primaryKey;
    private Map<String, TableIndex> indexes = new LinkedHashMap<String, TableIndex>();
    private Map<String, FieldMetadata> foreignKeys = new LinkedHashMap<String, FieldMetadata>();

    public ClassMetadata(Class<?> clazz) {
        this.javaClass = clazz;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getSchema() {
        return schema;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }

    public int getCountPerGroup() {
        return countPerGroup;
    }

    public void setCountPerGroup(int countPerGroup) {
        this.countPerGroup = countPerGroup;
    }

    public String getPartitionHashColumn() {
        return partitionHashColumn;
    }

    public void setPartitionHashColumn(String partitionHashColumn) {
        this.partitionHashColumn = partitionHashColumn;
    }

    public int getPartitions() {
        return partitions;
    }

    public void setPartitions(int partitions) {
        this.partitions = partitions;
    }

    public String[] getUniqueFieldNames() {
        return uniqueFieldNames;
    }

    public void setUniqueFieldNames(String[] uniqueFieldNames) {
        this.uniqueFieldNames = uniqueFieldNames;
    }

    public CacheStrategy getCacheStrategy() {
        return cacheStrategy;
    }

    public void setCacheStrategy(CacheStrategy cacheStrategy) {
        this.cacheStrategy = cacheStrategy;
    }

    public int getCacheTimeoutSecs() {
        return cacheTimeoutSecs;
    }

    public void setCacheTimeoutSecs(int cacheTimeoutSecs) {
        this.cacheTimeoutSecs = cacheTimeoutSecs;
    }

    public FieldMetadata getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(FieldMetadata primaryKey) {
        this.primaryKey = primaryKey;
    }

    public boolean isPartitioning() {
        return partitions > 0;
    }

    public Class<?> getJavaClass() {
        return javaClass;
    }

    public Map<String, FieldMetadata> getColumns() {
        return columns;
    }

    public Map<String, TableIndex> getIndexes() {
        return indexes;
    }

    public Map<String, FieldMetadata> getForeignKeys() {
        return foreignKeys;
    }

    public TableIndex getOrCreateIndex(String name) {
        TableIndex index = indexes.get(name);
        if (index == null) {
            index = new TableIndex(name);
            indexes.put(name, index);
        }
        return index;
    }

    public List<FieldMetadata> getUpdatableFieldMetadata() {
        List<FieldMetadata> result = new ArrayList<FieldMetadata>();
        for (FieldMetadata fm : columns.values()) {
            if (fm.isUpdatable()) {
                result.add(fm);
            }
        }
        return result;
    }

    public class FieldMetadata {

        private ClassMetadata classMetadata;
        private Field javaField;

        private String columnName;
        private boolean nullable = true;
        private boolean unique = false;
        private boolean insertable = true;
        private boolean updatable = true;

        private boolean isDeleteFlag = false;
        private boolean isPrimaryKey = false;
        private GenerationType generationType = null;

        private boolean isReferenced = false;
        private FieldMetadata referenced = null;
        private ClassMetadata referencedClass = null;
        private String referencedJavaFieldName = null;
        private boolean noIndex = false;

        private String columnDefinition = null;
        private int columnLength = 255;

        public FieldMetadata(Field field) {
            this.classMetadata = ClassMetadata.this;
            this.javaField = field;
        }

        public Field getJavaField() {
            return javaField;
        }

        public void setJavaField(Field javaField) {
            this.javaField = javaField;
        }

        public String getColumnName() {
            return columnName;
        }

        public void setColumnName(String columnName) {
            this.columnName = columnName;
        }

        public boolean isNullable() {
            return nullable;
        }

        public void setNullable(boolean nullable) {
            this.nullable = nullable;
        }

        public boolean isUnique() {
            return unique;
        }

        public void setUnique(boolean unique) {
            this.unique = unique;
        }

        public boolean isInsertable() {
            return insertable;
        }

        public void setInsertable(boolean insertable) {
            this.insertable = insertable;
        }

        public boolean isUpdatable() {
            return updatable;
        }

        public void setUpdatable(boolean updatable) {
            this.updatable = updatable;
        }

        public boolean isDeleteFlag() {
            return isDeleteFlag;
        }

        public void setDeleteFlag(boolean isDeleteFlag) {
            this.isDeleteFlag = isDeleteFlag;
        }

        public boolean isPrimaryKey() {
            return isPrimaryKey;
        }

        public void setPrimaryKey(boolean isPrimaryKey) {
            this.isPrimaryKey = isPrimaryKey;
        }

        public GenerationType getGenerationType() {
            return generationType;
        }

        public void setGenerationType(GenerationType generationType) {
            this.generationType = generationType;
        }

        public boolean isReferenced() {
            return isReferenced;
        }

        public void setReferenced(boolean isReferenced) {
            this.isReferenced = isReferenced;
        }

        public FieldMetadata getReferenced() {
            return referenced;
        }

        public void setReferenced(FieldMetadata referenced) {
            this.referenced = referenced;
            if (referenced != null) {
                this.referencedClass = referenced.getClassMetadata();
            } else {
                this.referencedClass = null;
            }
        }

        public boolean isNoIndex() {
            return noIndex;
        }

        public void setNoIndex(boolean noIndex) {
            this.noIndex = noIndex;
        }

        public String getColumnDefinition() {
            return columnDefinition;
        }

        public void setColumnDefinition(String columnDefinition) {
            this.columnDefinition = columnDefinition;
        }

        public int getColumnLength() {
            return columnLength;
        }

        public void setColumnLength(int columnLength) {
            this.columnLength = columnLength;
        }

        public ClassMetadata getClassMetadata() {
            return classMetadata;
        }

        public ClassMetadata getReferencedClass() {
            return referencedClass;
        }

        public String getJavaFieldName() {
            return javaField.getName();
        }

        public String getReferencedJavaFieldName() {
            return referencedJavaFieldName;
        }

        public void setReferencedJavaFieldName(String referencedJavaFieldName) {
            this.referencedJavaFieldName = referencedJavaFieldName;
        }

        public boolean isAutoIncreaseId() {
            return isPrimaryKey && generationType != null && GenerationType.AUTO.equals(generationType);
        }

    }

}
