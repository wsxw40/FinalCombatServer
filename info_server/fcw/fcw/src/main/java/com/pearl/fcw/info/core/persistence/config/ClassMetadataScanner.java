package com.pearl.fcw.info.core.persistence.config;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import com.pearl.fcw.info.core.persistence.config.ClassMetadata.FieldMetadata;
import com.pearl.fcw.info.core.persistence.config.annotation.Cache;
import com.pearl.fcw.info.core.persistence.config.annotation.CacheDuration;
import com.pearl.fcw.info.core.persistence.config.annotation.CountPerGroup;
import com.pearl.fcw.info.core.persistence.config.annotation.HashPartitioning;
import com.pearl.fcw.info.core.persistence.config.annotation.Index;
import com.pearl.fcw.info.core.persistence.config.annotation.Indexes;
import com.pearl.fcw.info.core.persistence.config.annotation.NoIndex;
import com.pearl.fcw.info.core.persistence.config.annotation.Schema;
import com.pearl.fcw.info.core.persistence.config.annotation.Unique;
import com.pearl.fcw.info.core.persistence.utils.ClassUtils;
import com.pearl.fcw.info.lobby.utils.ConfigurationUtil;

public class ClassMetadataScanner extends ClassMetadataConfig {

    private static final String DELETE_FLAG_FIELD_NAME = "isDeleted";

    private CacheStrategy defaultCacheStrategy = CacheStrategy.MEMCACHED;
    private int defaultCacheTimeOutSecs = ConfigurationUtil.DEFAULT_EXPR_SECS;
    private NamingStrategy namingStrategy = new NamingStrategy();

    private List<String> pojoPackageNames = new ArrayList<String>();

    public ClassMetadataScanner() {
    }

    public ClassMetadataScanner(List<Class<?>> classes) {
        parse(classes);
    }

    public ClassMetadataScanner(int defaultCacheTimeOutSecs) {
        this.defaultCacheTimeOutSecs = defaultCacheTimeOutSecs;
    }

    public ClassMetadataScanner(NamingStrategy namingStrategy) {
        this.namingStrategy = namingStrategy;
    }

    public ClassMetadataScanner(int defaultCacheTimeOutSecs, NamingStrategy namingStrategy) {
        this.defaultCacheTimeOutSecs = defaultCacheTimeOutSecs;
        this.namingStrategy = namingStrategy;
    }

    public void setDefaultCacheTimeOutSecs(int defaultCacheTimeOutSecs) {
        this.defaultCacheTimeOutSecs = defaultCacheTimeOutSecs;
    }

    public void setNamingStrategy(NamingStrategy namingStrategy) {
        this.namingStrategy = namingStrategy;
    }

    public List<String> getPojoPackageNames() {
        return pojoPackageNames;
    }

    public void setPojoPackageNames(List<String> pojoPackageNames) {
        this.pojoPackageNames = pojoPackageNames;
    }

    public void addPojoPackageNames(String[] pojoPackages) {
        Collections.addAll(this.pojoPackageNames, pojoPackages);
    }

    public void addPojoPackageName(String pojoPackage) {
        this.pojoPackageNames.add(pojoPackage);
    }

    /**
     * 解析指定包下全部符合条件的类，返回其ClassMetadata。
     *
     * @return ClassMetadata列表
     */
    public Map<Class<?>, ClassMetadata> parse() {
        List<Class<?>> allClasses = getClasses();
        return parse(allClasses);
    }

    /**
     * 解析指定包下全部符合条件的类，返回其ClassMetadata。
     *
     * @return ClassMetadata列表
     */
    public Map<Class<?>, ClassMetadata> parse(List<Class<?>> allClasses) {

        classMetadataMap.clear();

        for (Class<?> c : allClasses) {
            if (isValid(c)) {
                ClassMetadata cm = parse(c);
                classMetadataMap.put(c, cm);
            }
        }

        List<ClassMetadata> l = new ArrayList<ClassMetadata>(classMetadataMap.values());
        Collections.sort(l, new Comparator<ClassMetadata>() {
            @Override
            public int compare(ClassMetadata o1, ClassMetadata o2) {
                return o1.getTableName().compareTo(o2.getTableName());
            }
        });

        // 不用TreeMap
        classMetadataMap.clear();
        for (ClassMetadata cm : l) {
            classMetadataMap.put(cm.getJavaClass(), cm);
            classMetadataWrapperMap.put(cm.getJavaClass(), ClassMetadataWrapper.getInstance(cm));
        }

        return classMetadataMap;

    }

    private ClassMetadata parse(Class<?> clazz) {
        ClassMetadata cm = new ClassMetadata(clazz);

        // schema
        if (clazz.isAnnotationPresent(Schema.class)) {
            Schema schema = clazz.getAnnotation(Schema.class);
            cm.setSchema(schema.value());
        } else {
            throw new RuntimeException(clazz + "未定义Schema");
        }

        // // sharding
        // if (BaseShardingEntity.class.isAssignableFrom(clazz)) {
        // cm.setSharding(true);
        // } else {
        // cm.setSharding(false);
        // }

        // CountPerGroup
        if (clazz.isAnnotationPresent(CountPerGroup.class)) {
            CountPerGroup countPerGroup = clazz.getAnnotation(CountPerGroup.class);
            cm.setCountPerGroup(countPerGroup.value());
        } else {
            cm.setCountPerGroup(1);
        }

        // cache strategy
        if (clazz.isAnnotationPresent(Cache.class)) {
            Cache cache = clazz.getAnnotation(Cache.class);
            cm.setCacheStrategy(cache.strategy());
        } else {
            cm.setCacheStrategy(defaultCacheStrategy);
        }

        // cache timeout
        if (clazz.isAnnotationPresent(CacheDuration.class)) {
            CacheDuration cacheDuration = clazz.getAnnotation(CacheDuration.class);
            cm.setCacheTimeoutSecs(cacheDuration.value());
        } else {
            cm.setCacheTimeoutSecs(defaultCacheTimeOutSecs);
        }

        // partition
        if (clazz.isAnnotationPresent(HashPartitioning.class)) {
            HashPartitioning hashPartitioning = clazz.getAnnotation(HashPartitioning.class);
            if (hashPartitioning == null || hashPartitioning.field().length() == 0) {
                throw new RuntimeException(clazz + "分表策略未定义分割字段");
            }
            cm.setPartitionHashColumn(namingStrategy.propertyToColumnName(hashPartitioning.field()));
            cm.setPartitions(hashPartitioning.partitions());
        }

        // table name
        cm.setTableName(namingStrategy.classToTableName(clazz.getSimpleName()));

        // unique
        if (clazz.isAnnotationPresent(Unique.class)) {
            Unique unique = clazz.getAnnotation(Unique.class);
            cm.setUniqueFieldNames(unique.fieldNames());
        }

        // field
        parseFieldMetadata(cm, clazz);

        // 表级索引
        if (clazz.isAnnotationPresent(Indexes.class)) {
            Indexes indexes = clazz.getAnnotation(Indexes.class);
            for (Index index : indexes.value()) {
                TableIndex tableIndex = cm.getOrCreateIndex(index.name());
                for (String fieldName : index.fieldNames()) {
                    FieldMetadata fm = cm.getColumns().get(fieldName);
                    if (fm == null) {
                        throw new IllegalArgumentException(cm.getJavaClass() + "中不包含字段" + fieldName);
                    }
                    tableIndex.add(fm);
                }
            }
        }

        return cm;
    }

    private void parseFieldMetadata(ClassMetadata cm, Class<?> clazz) {
    	
        Class<?> superclass = clazz.getSuperclass();
        if (isPresistentClass(superclass)) {
            parseFieldMetadata(cm, superclass);
        }

        for (Field field : clazz.getDeclaredFields()) {
            if (!isVaild(field)) {
                continue;
            }
            parseFieldMetadata(cm, field);
        }

    }

    private void parseFieldMetadata(ClassMetadata cm, Field field) {

        FieldMetadata fm = cm.new FieldMetadata(field);

        if (field.isAnnotationPresent(OneToOne.class) || field.isAnnotationPresent(ManyToOne.class)) {

            fm.setReferenced(true);

            // reference
            ClassMetadata referenceClassMetadata = classMetadataMap.get(field.getType());
            if (referenceClassMetadata == null) {
                referenceClassMetadata = parse(field.getType());
            }
            FieldMetadata referenceFieldMetadata = referenceClassMetadata.getPrimaryKey();  // 只应用主键
            if (referenceFieldMetadata == null) {
                throw new RuntimeException();
            }
            fm.setReferenced(referenceFieldMetadata);
            fm.setReferencedJavaFieldName(namingStrategy.foreignKeyFieldName(field.getName(), referenceFieldMetadata.getJavaFieldName()));
            fm.setColumnName(namingStrategy.foreignKeyColumnName(fm.getJavaFieldName(), referenceFieldMetadata.getColumnName()));
            // cm.getForeignKeys().put(fm.getJavaFieldName(), referenceFieldMetadata);

            // join column
            if (field.isAnnotationPresent(JoinColumn.class)) {
                JoinColumn joinColumn = field.getAnnotation(JoinColumn.class);
                if (!"".equals(joinColumn.name())) {
                    fm.setColumnName(joinColumn.name());
                }
                fm.setNullable(joinColumn.nullable());
                fm.setInsertable(joinColumn.insertable());
                fm.setUpdatable(joinColumn.updatable());
            }

            cm.getForeignKeys().put(fm.getReferencedJavaFieldName(), fm);

        } else {

            // pk
            if (field.isAnnotationPresent(Id.class)) {
                fm.setPrimaryKey(true);
                if (field.isAnnotationPresent(GeneratedValue.class)) {
                    fm.setGenerationType(field.getAnnotation(GeneratedValue.class).strategy());
                }
                if (cm.getPrimaryKey() != null) {
                    throw new RuntimeException("重复定义主键 \""+cm.getJavaClass().getName()+":"+field.getName()+"\"");
                }
                fm.setUpdatable(false);
                cm.setPrimaryKey(fm);
            }

            // column
            fm.setColumnName(namingStrategy.propertyToColumnName(field.getName()));
            if (field.isAnnotationPresent(Column.class)) {
                Column column = field.getAnnotation(Column.class);
                if (!"".equals(column.name())) {
                    fm.setColumnName(column.name());
                }
                if (!"".equals(column.columnDefinition())) {
                    fm.setColumnDefinition(column.columnDefinition());
                }

                fm.setColumnLength(column.length());

                fm.setNullable(column.nullable());
                fm.setInsertable(column.insertable());
                fm.setUpdatable(column.updatable());
            }

            // index
            if (field.isAnnotationPresent(Index.class)) {
                Index index = field.getAnnotation(Index.class);
                TableIndex tableIndex = cm.getOrCreateIndex(index.name());
                if (index.length() != 0) {
                    tableIndex.setLength(index.length());
                }
                tableIndex.add(fm);
            }

            cm.getColumns().put(field.getName(), fm);

        }

        if (field.isAnnotationPresent(NoIndex.class)) {
            fm.setNoIndex(true);
        } else {
            fm.setNoIndex(false);
        }

        if (DELETE_FLAG_FIELD_NAME.equals(field.getName())) {
            fm.setDeleteFlag(true);
        }

        if (byte.class.equals(field.getType()) || short.class.equals(field.getType()) || boolean.class.equals(field.getType()) || char.class.equals(field.getType()) || double.class
                .equals(field.getType()) || float.class.equals(field.getType()) || int.class.equals(field.getType()) || long.class.equals(field.getType())) {
            fm.setNullable(false);
        }

    }

    private boolean isValid(Class<?> clazz) {
        return clazz.isAnnotationPresent(Entity.class);
    }

    private boolean isPresistentClass(Class<?> clazz) {
        return clazz != null && (isValid(clazz) || clazz.isAnnotationPresent(MappedSuperclass.class));
    }

    public boolean isVaild(Field field) {
        return !Modifier.isStatic(field.getModifiers()) && !field.isAnnotationPresent(Transient.class) && !field.toGenericString().matches(".* transient .*");
    }

    private List<Class<?>> getClasses() {
        List<Class<?>> list = new ArrayList<Class<?>>();
        for (String pkg : pojoPackageNames) {
            List<Class<?>> l = ClassUtils.getClasses(pkg);
            list.addAll(l);
        }
        return list;
    }

}
