package com.pearl.fcw.utils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.poi.ss.util.NumberComparer;

import com.pearl.fcw.core.pojo.BaseEntity;
import com.pearl.fcw.core.pojo.DmModel;
import com.pearl.fcw.gm.pojo.enums.ItemSubType;

public class CompareUtil {
    private static final Pattern PATTERN_1 = Pattern.compile("^[a-z]{1}[A-Z]{1}");
    public static final Comparator<Object> COMPARATOR = (p1, p2) -> (null == p1 && null == p2) ? 0 : (null == p1 && null != p2) ? -1 : (null != p1 && null == p2) ? 1
            : (p1 instanceof Number && p2 instanceof Number) ? NumberComparer.compare(Double.parseDouble(p1.toString()), Double.parseDouble(p2.toString())) : p1.toString().compareTo(p2.toString());

    public static void main(String[] args) throws Exception {
        //        for (int i = 0; i < com.pearl.fcw.gm.pojo.enums.ItemType.values().length; i++) {
        //
        //        }
        //        for (com.pearl.fcw.gm.pojo.enums.ItemType p : com.pearl.fcw.gm.pojo.enums.ItemType.values()) {
        //            System.out.println(p.getValue());
        //            System.out.println(com.pearl.fcw.utils.StringUtil.getI18nWord("game.sysItem.type." + p.getValue()));
        //        }
        for (Class<?> p : ItemSubType.class.getDeclaredClasses()) {
            if (p.isEnum()) {

                System.out.println(p.getMethod("values"));
            }
        }
    }

    /**
     * 比较两个DmModel对象的数据库字段的差异，用于GM日志。
     * @param oldM 修改前
     * @param newM 修改后
     * @return 全部参数为null或者无差异，将返回长度为0的List
     * @throws Exception
     */
    public static Map<String, List<Object>> compareDmModel(BaseEntity oldM, BaseEntity newM) throws Exception {
        Map<String, List<Object>> diffMap = new HashMap<>();
        if (null == oldM && null == newM) {
            return diffMap;
        }

        if (null != oldM && null != newM && !oldM.getClass().equals(newM.getClass())) {
            throw new IllegalArgumentException("Arg's type must be equal!");
        }
        //获取所有的数据库字段
        Map<String, Method> fieldMap = new HashMap<>();
        Class<?> clazz = null == oldM ? newM.getClass() : oldM.getClass();
        while (null != clazz && !clazz.equals(DmModel.class)) {
            for (Field f : clazz.getDeclaredFields()) {
                if (f.getModifiers() != Modifier.PRIVATE) {
                    continue;
                }
                try {
                    PropertyDescriptor pd = null;
                    if (PATTERN_1.matcher(f.getName()).find()) {
                        pd = new PropertyDescriptor(f.getName(), clazz, "get" + f.getName(), "set" + f.getName());
                    } else {
                        pd = new PropertyDescriptor(f.getName(), clazz);
                    }
                    fieldMap.put(f.getName(), pd.getReadMethod());
                } catch (Exception e) {
                    continue;
                }
            }
            clazz = clazz.getSuperclass();
        }
        //找出新旧数据的差异
        for (String f : fieldMap.keySet()) {
            Object o1 =null==oldM?null: fieldMap.get(f).invoke(oldM);
            Object o2 = null == newM ? null : fieldMap.get(f).invoke(newM);
            if (COMPARATOR.compare(o1, o2) != 0) {
                diffMap.put(f, Arrays.asList(o1, o2));
            }
        }

        return diffMap;
    }
}
