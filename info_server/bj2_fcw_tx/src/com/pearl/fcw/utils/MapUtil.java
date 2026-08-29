package com.pearl.fcw.utils;

import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MapUtil {
    private static Logger logger = LoggerFactory.getLogger(MapUtil.class);

    /**
     * 继承Serializable接口的实体转Map。指定名称的字段不存入Map
     * @param obj
     * @param fields 不存入Map的字段
     * @return
     */
    public static Map<String, Object> toMapExcept(Serializable obj, String... fields) {
        Map<String, Object> map = new HashMap<>();
        if (null == obj) {
            return map;
        }
        List<String> fieldList = Arrays.asList(fields);
        Class<?> clazz = obj.getClass();
        do {
            for (Field f : clazz.getDeclaredFields()) {
                if (fieldList.contains(f.getName())) {
                    continue;
                }
                try {
                    PropertyDescriptor pd = new PropertyDescriptor(f.getName(), obj.getClass());
                    map.put(f.getName(), pd.getReadMethod().invoke(obj));
                } catch (Exception e) {
                    logger.info("toMapExcept() : " + obj.getClass() + "has not contains " + f.getName());
                }
            }
            clazz = clazz.getSuperclass();
        } while (clazz != null && !clazz.equals(Object.class));
        return map;
    }

    /**
     * 继承Serializable接口的实体转Map。只有指定名称的字段才存入Map
     * @param obj
     * @param fields 只存入Map的字段
     * @return
     */
    public static Map<String, Object> toMapContains(Serializable obj, String... fields) {
        Map<String, Object> map = new HashMap<>();
        if (null == obj) {
            return map;
        }
        List<String> fieldList = Arrays.asList(fields);
        Class<?> clazz = obj.getClass();
        do {
            for (Field f : clazz.getDeclaredFields()) {
                if (!fieldList.contains(f.getName())) {
                    continue;
                }
                try {
                    PropertyDescriptor pd = new PropertyDescriptor(f.getName(), obj.getClass());
                    map.put(f.getName(), pd.getReadMethod().invoke(obj));
                } catch (Exception e) {
                    logger.info("toMapContains() : " + obj.getClass() + "has not contains " + f.getName());
                }
            }
            clazz = clazz.getSuperclass();
        } while (clazz != null && !clazz.equals(Object.class));
        return map;
    }

    /**
     * 继承Serializable接口的实体list转包含Map的list，避免toMapExcept方法中重复反射，提升速度。指定名称的字段不存入Map
     * @param objList
     * @param fields 不存入Map的字段
     * @return
     */
    public static List<Map<String, Object>> toListMapExcept(List<?> objList, String... fields) {
        List<Map<String, Object>> list = new ArrayList<>();
        if (null == objList || objList.isEmpty()) {
            return list;
        }
        List<String> fieldList = Arrays.asList(fields);
        Class<?> clazz = objList.get(0).getClass();
        Map<String, Method> readMethodMap = new HashMap<>();
        do {
            for (Field f : clazz.getDeclaredFields()) {
                if (fieldList.contains(f.getName())) {
                    continue;
                }
                try {
                    PropertyDescriptor pd = new PropertyDescriptor(f.getName(), objList.get(0).getClass());
                    readMethodMap.put(f.getName(), pd.getReadMethod());
                } catch (Exception e) {
                    logger.info("toListMapExcept() : " + objList.get(0).getClass().getClass() + "has not contains " + f.getName());
                }
            }
            clazz = clazz.getSuperclass();
        } while (clazz != null && !clazz.equals(Object.class));
        for (Object obj : objList) {
            Map<String, Object> map = new HashMap<>();
            readMethodMap.forEach((k, v) -> {
                try {
                    map.put(k, v.invoke(obj));
                } catch (Exception e) {
                }
            });
            list.add(map);
        }
        return list;
    }

    /**
     * 继承Serializable接口的实体list转包含Map的list，避免toMapContains方法中重复反射，提升速度。只有指定名称的字段才存入Map
     * @param objList
     * @param fields 只存入Map的字段
     * @return
     */
    public static List<Map<String, Object>> toListMapContains(List<?> objList, String... fields) {
        List<Map<String, Object>> list = new ArrayList<>();
        if (null == objList || objList.isEmpty()) {
            return list;
        }
        List<String> fieldList = Arrays.asList(fields);
        Class<?> clazz = objList.get(0).getClass();
        Map<String, Method> readMethodMap = new HashMap<>();
        do {
            for (Field f : clazz.getDeclaredFields()) {
                if (!fieldList.contains(f.getName())) {
                    continue;
                }
                try {
                    PropertyDescriptor pd = new PropertyDescriptor(f.getName(), objList.get(0).getClass());
                    readMethodMap.put(f.getName(), pd.getReadMethod());
                } catch (Exception e) {
                    logger.info("toListMapContains() : " + objList.get(0).getClass().getClass() + "has not contains " + f.getName());
                }
            }
            clazz = clazz.getSuperclass();
        } while (clazz != null && !clazz.equals(Object.class));
        for (Object obj : objList) {
            Map<String, Object> map = new HashMap<>();
            readMethodMap.forEach((k, v) -> {
                try {
                    map.put(k, v.invoke(obj));
                } catch (Exception e) {
                }
            });
            list.add(map);
        }
        return list;
    }
}
