package com.pearl.fcw.info.lobby.utils;

public class Constants {
    /* 公用 */
    public static int ONE_DAY_SEC = 60 * 60 * 24;
    public static int ONE_DAY = 1;
    public static int ONE_WEEK_DAYS = 7;
    public static int CURRENCY_GRADE = 1;
    public static byte BOOLEAN_TRUE_BYTE = 1;
    public static byte BOOLEAN_FALSE_BYTE = 0;
    public static String NEW_LINE = System.getProperty("line.separator");// 换行符
    public static String ENCODING = "UTF8";
    public static String ILLEGAL_CHARACTER = "[\'\" ~!@#$%^&*()+`{}|\\\\,\\./<>?;:]";
    public static String ILLEGAL_CHARACTER_NO_SPACE = "[\'\"~!@#$%^&*()+`{}|\\\\,\\./<>?;:]";// 只比上面少一个空格
    public static String EMPTY_STRING = "";
    public static final String COMMA = ",";
    public static final String BACK_SLASH = "\\";
    public static final String SLASH = "/";
    public static final char SPLIT_CHAR = '|';
    public static final String EQ = "=";
    public static final String SEMICOLON = ";";
    public static final String COLON = ":";
    public static int REFIT_DIGIT = 4;    // 小数位数最大个数,超过的会过滤掉

    /* DB */
    public static int DB_INT_NULL = 0;
    public static int DB_NOW_SYNCHRONIZE_SPACE = 1000 * 60 * 60; // 当前时间跟数据库同步的时间间隔
    public static int DB_DESC = 1; // 降序
    public static int DB_ASC = -1; // 升序

    /* 缓存 */
    public static String OBJECT_TYPE = "O";
    public static String STRING_TYPE = "S";
    public static String DELIMITER = "|";
    public static String SYNCHRONIZE_PREFIX = "SYN";
    public static int CACHE_ITEM_TIMEOUT = 60 * 60 * 12;// unit: second，12小时
    public static int SYNCHRONIZE_TIMEOUT = 1000 * 10;// 同步获得锁超时(毫秒)
    public static int CACHE_ATHLETIC_TIMEOUT = 60 * 5;// 5分钟
}
