package com.pearl.fcw.utils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.poi.ss.util.NumberComparer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.o2o.pojo.BaseMappingBean;
import com.pearl.o2o.pojo.SysItem;

/**
 * 用于原始o2o部分的逻辑操作整合
 */
public class O2oUtil {
    private static Logger logger = LoggerFactory.getLogger(O2oUtil.class);

    private static final Pattern PATTERN1 = Pattern.compile("([a-zA-Z0-9_]*)\\[([0-9]+)\\]");

    private static final Comparator<Object> COMPARATOR = (p1, p2) -> (null == p1 && null == p2) ? 0 : (null == p1 && null != p2) ? -1 : (null != p1 && null == p2) ? 1
            : (p1 instanceof Number && p2 instanceof Number) ? NumberComparer.compare(
            Double.parseDouble(p1.toString()), Double.parseDouble(p2.toString())) : p1.toString().compareTo(p2.toString());

    /**
     * 直接从缓存获取分页的原始o2o数据
     * @param clazz 原始o2o的pojo类
     * @param param 客户端请求的分页条件
     * @param dataList 用于分页操作的原始o2o数据
     * @param readMethods 用于字段左匹配过滤的原始o2o的pojo类的无参get方法，方法体内自带getId方法判定，该参数不必传入getId方法
     * @return 返回分页的原始o2o数据
     * @throws Exception
     */
    public static <T extends BaseMappingBean<T>> DataTablesPage<T> getPage(Class<T> clazz, DataTablesParameter param, List<T> dataList, String... readMethods) throws Exception {
        Method[] methods = new Method[readMethods.length];
        for (int i = 0; i < readMethods.length; i++) {
            methods[i] = clazz.getMethod(readMethods[i]);
        }
        return getPage(clazz, param, dataList, methods);
    }

    /**
     * 直接从缓存获取分页的原始o2o数据
     * @param clazz 原始o2o的pojo类
     * @param param 客户端请求的分页条件
     * @param dataList 用于分页操作的原始o2o数据
     * @param readMethods 用于字段左匹配过滤的原始o2o的pojo类的无参get方法，方法体内自带getId方法判定，该参数不必传入getId方法
     * @return 返回分页的原始o2o数据
     */
    private static <T extends BaseMappingBean<T>> DataTablesPage<T> getPage(Class<T> clazz, DataTablesParameter param, List<T> dataList, Method... readMethods) {
        DataTablesPage<T> page = new DataTablesPage<>();
        List<T> list = dataList.stream().filter(p -> {//左匹配过滤
                    try {
                        if (clazz.getMethod("getIsDeleted").invoke(p).toString().equalsIgnoreCase("Y")) {//必须是没有被逻辑删除的数据
                            return false;
                        }
                    } catch (Exception e) {
                        logger.warn(e.toString());
                    }
                    if (StringUtil.isEmpty(param.getSearchValue())) {//无搜索过滤
                        return true;
                    }
                    //任意一个字段左匹配，即获取该条数据
                    if ((p.getId() + "").startsWith(param.getSearchValue())) {//ID必定比较
                        return true;
                    }
                    if (null == readMethods) {//无指定的比较字段
                        return true;
                    }
                    for (Method readMead : readMethods) {
                        try {
                            Object o = readMead.invoke(p);
                            if (o == null) {
                                continue;
                            }
                            if (o.toString().startsWith(param.getSearchValue())) {//符合左匹配
                                return true;
                            }
                        } catch (Exception e) {
                            logger.warn(e.toString());
                            return true;
                        }
                    }
                    return false;
                }).sorted((p1, p2) -> {//字段排序
                    try {
                        if (!StringUtil.isEmpty(param.getOrderColumn())) {
                            //                            PropertyDescriptor pd = new PropertyDescriptor(param.getOrderColumn(), clazz);
                            //                            int result = COMPARATOR.compare(pd.getReadMethod().invoke(p1), pd.getReadMethod().invoke(p2));
                            int result = COMPARATOR.compare(getProperty(p1, param.getOrderColumn()), getProperty(p2, param.getOrderColumn()));
                            if ("desc".equalsIgnoreCase(param.getOrderDir())) {
                                return -result;
                            }
                            return result;
                        }
                    } catch (Exception e) {
                        logger.warn(e.toString());
                    }
                    return p1.getId() - p2.getId();
                }).collect(Collectors.toList());
        //判断有效的起始和终止索引
        int startIndex = param.getStart();
        startIndex = startIndex > list.size() - 1 ? list.size() - 1 : startIndex;
        startIndex = startIndex < 0 ? 0 : startIndex;
        int endIndex = param.getLength() + startIndex;
        endIndex = (endIndex > list.size()) ? list.size() : endIndex;
        endIndex = startIndex > endIndex ? startIndex : endIndex;
        //数据填充
        List<T> list1 = new ArrayList<>();
        for (int i = startIndex; i < endIndex; i++) {
            list1.add(list.get(i));
        }
        //页面数据赋值
        page.setData(list1);
        page.setDraw(param.getDraw());
        page.setRecordsFiltered(list.size());
        page.setRecordsTotal(dataList.size());
        return page;
    }

    /**
     * 直接从缓存获取分页的原始o2o数据
     * @param param 客户端请求的分页条件
     * @param dataList 用于分页操作的原始o2o数据
     * @param keys 参与过滤的key组合
     * @return 返回分页的原始o2o数据
     */
    public static DataTablesPage<Map<String, Object>> getPageMap(DataTablesParameter param, List<Map<String, Object>> dataList, String... keys) throws Exception {
        DataTablesPage<Map<String, Object>> page = new DataTablesPage<>();
        List<Map<String, Object>> list = dataList.stream().filter(p -> {//左匹配过滤
                    if (StringUtil.isEmpty(param.getSearchValue())) {//无搜索过滤
                        return true;
                    }
                    //任意一个字段左匹配，即获取该条数据
                    if (null == keys) {//无指定的比较字段
                        return true;
                    }
                    for (String key : keys) {
                        Object value = p.getOrDefault(key, null);
                        if (null != value && value.toString().startsWith(param.getSearchValue())) {
                            return true;
                        }
                    }
                    return false;
                }).sorted((p1, p2) -> {//字段排序
                    try {
                        if (!StringUtil.isEmpty(param.getOrderColumn())) {
                            int result = COMPARATOR.compare(p1.getOrDefault(param.getOrderColumn(), null), p2.getOrDefault(param.getOrderColumn(), null));
                            if ("desc".equalsIgnoreCase(param.getOrderDir())) {
                                return -result;
                            }
                            return result;
                        }
                    } catch (Exception e) {
                        logger.warn(e.toString());
                    }
                    return -1;
                }).collect(Collectors.toList());
        //判断有效的起始和终止索引
        int startIndex = param.getStart();
        startIndex = startIndex > list.size() - 1 ? list.size() - 1 : startIndex;
        startIndex = startIndex < 0 ? 0 : startIndex;
        int endIndex = param.getLength() + startIndex;
        endIndex = (endIndex > list.size()) ? list.size() : endIndex;
        endIndex = startIndex > endIndex ? startIndex : endIndex;
        //数据填充
        List<Map<String, Object>> list1 = new ArrayList<>();
        for (int i = startIndex; i < endIndex; i++) {
            list1.add(list.get(i));
        }
        //页面数据赋值
        page.setData(list1);
        page.setDraw(param.getDraw());
        page.setRecordsFiltered(list.size());
        page.setRecordsTotal(dataList.size());
        return page;
    }

    /**
     * 对原始o2o的pojo类值为null进行默认赋值处理，防止写入数据库发生空指针异常
     * @param t
     * @return
     * @throws Exception
     */
    public static <T extends BaseMappingBean<T>> T dealNullPointField(final T t) throws Exception {
        List<Field> fieldList = new ArrayList<>(Arrays.asList(t.getClass().getDeclaredFields()));
        fieldList.addAll(Arrays.asList(t.getClass().getSuperclass().getDeclaredFields()));
        for (Field f : fieldList) {
            try {
                if (f.getType().equals(Long.class) || f.getType().equals(Integer.class) || f.getType().equals(Short.class) || f.getType().equals(Byte.class)) {
                    PropertyDescriptor pd = new PropertyDescriptor(f.getName(), SysItem.class);
                    if (pd.getReadMethod().invoke(t) == null) {
                        pd.getWriteMethod().invoke(t, 0);
                    }
                } else if (f.getType().equals(Float.class)) {
                    PropertyDescriptor pd = new PropertyDescriptor(f.getName(), SysItem.class);
                    if (pd.getReadMethod().invoke(t) == null) {
                        pd.getWriteMethod().invoke(t, 0f);
                    }
                } else if (f.getType().equals(Double.class)) {
                    PropertyDescriptor pd = new PropertyDescriptor(f.getName(), SysItem.class);
                    if (pd.getReadMethod().invoke(t) == null) {
                        pd.getWriteMethod().invoke(t, 0d);
                    }
                } else if (f.getType().equals(Boolean.class)) {
                    PropertyDescriptor pd = new PropertyDescriptor(f.getName(), SysItem.class);
                    if (pd.getReadMethod().invoke(t) == null) {
                        pd.getWriteMethod().invoke(t, false);
                    }
                } else if (f.getType().equals(String.class)) {
                    PropertyDescriptor pd = new PropertyDescriptor(f.getName(), SysItem.class);
                    if (pd.getReadMethod().invoke(t) == null) {
                        pd.getWriteMethod().invoke(t, "");
                    }
                }
            } catch (Exception e) {
                logger.warn(t.getClass().getName() + "." + f.getName() + ":" + e.toString());
            }
        }
        return t;
    }

    /**
     * 获取一个对象的指定属性对应的无参get方法返回值。其属性可能是嵌套内容，例如 a.b.c 对应 getA().getB().getC()
     * 或者获取一个Map结构的属性值。a.b.c对应get(a).get(b).get(c)
     * @param obj
     * @param propertyName
     * @return
     * @throws Exception
     */
    @SuppressWarnings("rawtypes")
    public static Object getProperty(final Object obj, String propertyName) throws Exception {
        Object result = null;
        if (null == obj || StringUtil.isEmpty(propertyName)) {
            return result;
        }
        //不含嵌套属性
        if (!propertyName.contains(".")) {
            Matcher m = PATTERN1.matcher(propertyName);
            //该属性为List或者数组
            if (m.find()) {
                propertyName = m.group(1);
                int index = Integer.parseInt(m.group(2));
                if (propertyName.equals("")) {
                    result = obj;
                } else {
                    if (obj instanceof Map) {
                        result = ((Map) obj).get(propertyName);
                    } else {
                        PropertyDescriptor pd = new PropertyDescriptor(propertyName, obj.getClass());
                        result = pd.getReadMethod().invoke(obj);
                    }
                }
                if (result instanceof List) {
                    return ((List) result).get(index);
                } else {
                    if (result.getClass().equals(int[].class)) {
                        return ((int[]) result)[index];
                    } else if (result.getClass().equals(long[].class)) {
                        return ((long[]) result)[index];
                    } else if (result.getClass().equals(float[].class)) {
                        return ((float[]) result)[index];
                    } else if (result.getClass().equals(double[].class)) {
                        return ((double[]) result)[index];
                    } else if (result.getClass().equals(byte[].class)) {
                        return ((byte[]) result)[index];
                    } else if (result.getClass().equals(String[].class)) {
                        return ((String[]) result)[index];
                    }
                }
            }
            //该属性为Map
            if (obj instanceof Map) {
                return ((Map) obj).get(propertyName);
            }
            //该属性为类实例
            try {
                PropertyDescriptor pd = new PropertyDescriptor(propertyName, obj.getClass());
                return pd.getReadMethod().invoke(obj);
            } catch (Exception e) {
                obj.getClass().getMethod("get" + StringUtil.toPascal(propertyName)).invoke(obj);
            }
        }

        //含嵌套属性
        Class<?> clazz = obj.getClass();
        result = obj;
        for (String str : propertyName.split("\\.")) {
            Matcher m = PATTERN1.matcher(str);
            //该层属性为List或者数组
            if (m.find()) {
                String var = m.group(1);
                int index = Integer.parseInt(m.group(2));
                //上一层属性为Map
                if (result instanceof Map) {
                    result = ((Map) result).get(var);
                    if (null != result) {
                        clazz = result.getClass();
                    }
                } else {//上一层属性为类实例
                    try {
                        PropertyDescriptor pd = new PropertyDescriptor(var, clazz);
                        result = pd.getReadMethod().invoke(result);
                    } catch (Exception e) {
                        result = result.getClass().getMethod("get" + StringUtil.toPascal(var)).invoke(result);
                    }
                    if (null != result) {
                        clazz = result.getClass();
                    }
                }
                if (result instanceof List) {
                    result = ((List) result).get(index);
                } else {
                    if (result.getClass().equals(int[].class)) {
                        result = ((int[]) result)[index];
                    } else if (result.getClass().equals(long[].class)) {
                        result = ((long[]) result)[index];
                    } else if (result.getClass().equals(String[].class)) {
                        result = ((String[]) result)[index];
                    } else if (result.getClass().equals(byte[].class)) {
                        result = ((byte[]) result)[index];
                    } else if (result.getClass().equals(Object[].class)) {
                        result = ((Object[]) result)[index];
                    }
                }
                continue;
            }
            //该层属性为Map
            if (result instanceof Map) {
                result = ((Map) result).get(str);
                if (null != result) {
                    clazz = result.getClass();
                }
                continue;
            }
            //该层属性为类实例
            try {
                PropertyDescriptor pd = new PropertyDescriptor(str, clazz);
                result = pd.getReadMethod().invoke(result);
            } catch (Exception e) {
                result = result.getClass().getMethod("get" + StringUtil.toPascal(str)).invoke(result);
            }
            if (null != result) {
                clazz = result.getClass();
            }
        }
        return result;
    }

    /**
     * Smarty4j专用，异常不抛出，出现异常结果返回null
     * 获取一个对象的指定属性对应的无参get方法返回值。其属性可能是嵌套内容，例如 a.b.c 对应 getA().getB().getC()
     * 或者获取一个Map结构的属性值。a.b.c对应get(a).get(b).get(c)
     * @param obj
     * @param propertyName
     * @return
     */
    public static Object getSmarty4jProperty(final Object obj, String propertyName) {
        try {
            return getProperty(obj, propertyName);
        } catch (Exception e) {
            logger.warn("O2oUtil.getProperty() has error, obj = " + obj + " , propertyName = " + propertyName);
            logger.warn(e.toString());
            return null;
        }
    }

    public static Object getSmarty4jPropertyNil(final Object obj, String propertyName) {
        Object o = getSmarty4jProperty(obj, propertyName);
        if (null == o) {
            return "nil";
        }
        return o;
    }

    /**
     * 比较两个值的大小，返回布尔值
     * @param a
     * @param compareStr 比较符号
     * @param b
     * @return
     */
    public static boolean compare(Object a, String compareStr, Object b) {
        int result = COMPARATOR.compare(a, b);
        switch (compareStr.trim()) {
        case ">":
            return result > 0;
        case "<":
            return result < 0;
        case "==":
            return result == 0;
        case ">=":
            return result >= 0;
        case "<=":
            return result <= 0;
        case "!=":
            return result != 0;
        default:
            return true;
        }
    }

    /**
     * 仅供Smarty4j生成代码使用
     * @param o
     * @return
     */
    public static float parseFloat(Object o) {
        try {
            return Float.parseFloat(o.toString());
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public static void main(String[] args) throws Exception {
        String[] a = { "a", "b" };
        Integer[] b = { 1, 2 };
        Object o = a;

        System.out.println(o instanceof String[]);

        List list = Stream.of(o instanceof String[] ? (String[]) o : o).collect(Collectors.toList());
        if (o instanceof String[]) {
            String[] tmp = (String[]) o;
            list = Stream.of((String[]) o).collect(Collectors.toList());
        }

        list.forEach(p -> System.out.println(p));
        System.out.println(o instanceof Number[]);
        Stream.of(o).forEach(p -> {
            System.out.println(p);
        });
        Arrays.asList(o).forEach(p -> {
            System.out.println(p);
        });

    }
}
