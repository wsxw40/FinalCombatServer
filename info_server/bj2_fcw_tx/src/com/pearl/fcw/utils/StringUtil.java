package com.pearl.fcw.utils;

import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {
    private static final Pattern SQL_VALUE_REPLACE_PATTERN = Pattern.compile("([_%'\"\\\\])");
    private static final Pattern HUMP_PATTERN = Pattern.compile("(_)([0-9a-zA-Z]{1})");
    private static ResourceBundle i18nResourceBundle = null;
    static {
        i18nResourceBundle = ResourceBundle.getBundle("i18n/messages");
    }
    private static ResourceBundle application = null;
    static {
        application = ResourceBundle.getBundle("config/application");
    }

    /**
     * 过滤SQL语句的敏感字符
     * @param value
     * @return
     */
    public static String escapeSQLValue(String value) {
        if (value == null) {
            return null;
        }
        Matcher m = SQL_VALUE_REPLACE_PATTERN.matcher(value);
        return m.replaceAll("\\\\$1");
    }

    /**
     * 将字符串的部分内容转为小写
     * @param str
     * @param startIndex 转小写的起始索引
     * @param length 转小写的长度
     * @return
     */
    public static String toLowerCase(String str, int startIndex, int length) {
        String str1 = str.substring(0, startIndex);
        String str2 = str.substring(startIndex, startIndex + length).toLowerCase();
        String str3 = str.substring(startIndex + length);
        return str1 + str2 + str3;
    }

    /**
     * 将字符串的部分内容转为大写
     * @param str
     * @param startIndex 转大写的起始索引
     * @param length 转大写的长度
     * @return
     */
    public static String toUpperCase(String str, int startIndex, int length) {
        String str1 = str.substring(0, startIndex);
        String str2 = str.substring(startIndex, startIndex + length).toUpperCase();
        String str3 = str.substring(startIndex + length);
        return str1 + str2 + str3;
    }

    /**
     * 将字符串转为符合驼峰命名法的规范
     * @param str
     * @return
     */
    public static String toHump(String str) {
        Matcher m = HUMP_PATTERN.matcher(str);
        while (m.find()) {
            str = str.replaceFirst(m.group(), m.group(2).toUpperCase());
        }
        return str.substring(0, 1).toLowerCase() + str.substring(1);
    }

    /**
     * 将字符串转为符合帕斯卡命名法的规范
     * @param str
     * @return
     */
    public static String toPascal(String str) {
        Matcher m = HUMP_PATTERN.matcher(str);
        while (m.find()) {
            str = str.replaceFirst(m.group(), m.group(2).toUpperCase());
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    /**
     * 获取配置在i18n/messages.properties文件中的词语
     * @param key
     * @return
     */
    public static String getI18nWord(String key) {
        return i18nResourceBundle.getString(key);
    }

    /**
     * 获取配置在config/application.properties文件中的词语
     * @param key
     * @return
     */
    public static String getApplicationWord(String key) {
        return application.getString(key);
    }

    /**
     * 检查字符串是否Null或者空白
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        return null == str ? true : "".equals(str.trim()) ? true : false;
    }

    public static void main(String[] args) {
        System.out.println(isEmpty("    "));

    }
}
