package com.pearl.fcw.core.pojo;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public abstract class DmModel implements BaseEntity {

    private static final long serialVersionUID = 4086584063866427495L;
    public static final Pattern PATTERN_ONLY_FIRST_ONE_LOWER = Pattern.compile("^[a-z]{1}[A-Z]{1}");

    private transient Long dbVersion;

    @Override
    public Long getDbVersion() {
        return dbVersion;
    }

    @Override
    public void setDbVersion(Long dbVersion) {
        this.dbVersion = dbVersion;
    }


        /**
     * 创建实体<br/>
     * 为防止向数据库新增数据时，数据中已存在的非空却没有默认值的字段报错，采用反射方式填写默认值<br/>
     * 因效率低下，正常情况下请勿使用此方法
     * @return
     * @throws Exception
     */
    public static <M extends DmModel> M createInstance(Class<M> clazz) throws Exception {
        M m = clazz.newInstance();
        Map<Field, Method> map = new HashMap<>();
        Class<?> clazz1 = clazz;
        while (null != clazz1 && !clazz1.equals(DmModel.class)) {
            for (Field f : m.getClass().getDeclaredFields()) {
                try {
                    if (f.getModifiers() != Modifier.PRIVATE) {
                        continue;
                    }
                    PropertyDescriptor pd = null;
                    if (PATTERN_ONLY_FIRST_ONE_LOWER.matcher(f.getName()).find()) {
                        pd = new PropertyDescriptor(f.getName(), clazz1, "get" + f.getName(), "set" + f.getName());
                    } else {
                        pd = new PropertyDescriptor(f.getName(), clazz1);
                    }
                    map.put(f, pd.getWriteMethod());
                } catch (Exception e) {
                    continue;
                }
            }
            clazz1 = clazz1.getSuperclass();
        }
        for (Field f : map.keySet()) {
            if (f.getType().equals(Byte.class)) {
                map.get(f).invoke(m, (byte) 0);
            } else if (f.getType().equals(Short.class)) {
                map.get(f).invoke(m, (short) 0);
            } else if (f.getType().equals(Integer.class)) {
                map.get(f).invoke(m, 0);
            } else if (f.getType().equals(Long.class)) {
                map.get(f).invoke(m, 0L);
            } else if (f.getType().equals(Float.class)) {
                map.get(f).invoke(m, 0.0F);
            } else if (f.getType().equals(Double.class)) {
                map.get(f).invoke(m, 0.0);
            } else if (f.getType().equals(String.class)) {
                map.get(f).invoke(m, "");
            }
        }
        return m;
    }
}
