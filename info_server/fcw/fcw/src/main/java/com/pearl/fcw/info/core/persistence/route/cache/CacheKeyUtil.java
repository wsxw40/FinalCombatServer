package com.pearl.fcw.info.core.persistence.route.cache;

import java.util.HashSet;
import java.util.Set;

import com.pearl.fcw.info.core.persistence.BaseEntity;
import com.pearl.fcw.info.core.persistence.config.ClassMetadata.FieldMetadata;
import com.pearl.fcw.info.core.persistence.config.ClassMetadataWrapper;

public class CacheKeyUtil {

    private static final char SPLIT_CHAR = '|';

    /**
     * 数据库中每行一条
     */
    public static String getCacheKeyById(long id, Class<?> clazz) {
        return clazz.getSimpleName() + SPLIT_CHAR + id;
    }

    /**
     * 根据缓存中的KEY， 逆向获得ORM POJO ID
     */
    public static Integer getIdByCacheKey(String key) {
        return Integer.parseInt(key.substring(key.indexOf(SPLIT_CHAR) + 1));
    }

    /**
     * 存放ID列表，数据库中指定表的所有ID
     */
    public static String getCacheKeyForAll(Class<?> clazz) {
        return clazz.getSimpleName() + SPLIT_CHAR + "ALL";
    }

    /**
     * 存放ID列表，外键关联的所有ID
     */
    public static <T extends BaseEntity> String getCacheKeyByForeignId(long foreignId, Class<T> clazz, String fieldName) {
        return clazz.getSimpleName() + SPLIT_CHAR + fieldName + SPLIT_CHAR + foreignId;
    }

    public static <T extends BaseEntity> Set<String> getAllForeignKeysByPojo(T pojo, ClassMetadataWrapper<T> cmw) {
        Set<String> result = new HashSet<String>(8);
        @SuppressWarnings("unchecked")
        Class<T> clazz = (Class<T>) pojo.getClass();
        for (FieldMetadata fm : cmw.getClassMetadata().getForeignKeys().values()) {
            String fkFieldName = fm.getReferencedJavaFieldName();
            long fk = cmw.getFKValue(pojo, fkFieldName);
            String cacheKey = getCacheKeyByForeignId(fk, clazz, fkFieldName);
            result.add(cacheKey);
        }
        return result;
    }




    public static void main(String[] args) {

    }

}
