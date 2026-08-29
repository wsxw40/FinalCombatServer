package com.pearl.fcw.info.core.persistence.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.pearl.fcw.info.core.persistence.BaseEntity;

public class ClassMetadataConfig {

    protected static final Map<Class<?>, ClassMetadata> classMetadataMap = new ConcurrentHashMap<>();
    protected static final Map<Class<?>, ClassMetadataWrapper<? extends BaseEntity>> classMetadataWrapperMap = new ConcurrentHashMap<>();

    public static final ClassMetadata getClassMetadata(Class<?> clazz) {
        return classMetadataMap.get(clazz);
    }

    @SuppressWarnings("unchecked")
    public static final <T extends BaseEntity> ClassMetadataWrapper<T> getClassMetadataWrapper(Class<?> clazz) {
        return (ClassMetadataWrapper<T>) classMetadataWrapperMap.get(clazz);
    }

    public static final List<ClassMetadata> getBySchema(String schema) {
        List<ClassMetadata> result = new ArrayList<>();
        if (schema == null) {
            return result;
        }
        for (ClassMetadata cm : classMetadataMap.values()) {
            if (schema.equals(cm.getSchema())) {
                result.add(cm);
            }
        }
        return result;
    }

}
