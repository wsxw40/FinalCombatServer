package com.pearl.fcw.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * 日期和时间处理工具
 */
public class DateUtil {
    public final static ThreadLocal<SimpleDateFormat> FORMATTER = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH));
    public final static ThreadLocal<SimpleDateFormat> FORMATTER2 = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH));
    public final static ThreadLocal<SimpleDateFormat> FORMATTER3 = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.ENGLISH));

    public static Date now() {
        return new Date();
    }

    public static int toUnixSecond() {
        return toUnixSecond(now());
    }

    public static int toUnixSecond(Date date) {
        return (int) (date.getTime() / 1000);
    }

    public static Date fromUnixSecond(int offset) {
        return new Date(offset * 1000L);
    }

    /**
     * 日期转字符串
     * @param source yyyy-MM-dd HH:mm:ss 或 yyyy-MM-dd 或 yyyy/MM/dd HH:mm:ss
     * @return Date
     * @throws IllegalArgumentException
     */
    public static Date parseString(String source) {
        try {
            return FORMATTER.get().parse(source);
        } catch (ParseException e) {
            try {
                return FORMATTER2.get().parse(source);
            } catch (ParseException e1) {
                try {
                    return FORMATTER3.get().parse(source);
                } catch (ParseException e2) {
                    throw new IllegalArgumentException("不支持的日期格式");
                }
            }
        }
    }

    /**
     * 字符串转日期
     * @param d
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static String format(Date d) {
        if (d == null) {
            return null;
        }
        return FORMATTER.get().format(d);
    }

	/**
	 * @param flag
	 * @return
	 * */
	public static String formatToFlag(Date date,String flag){
		return new SimpleDateFormat(flag).format(date);
	}

    /**
     * 判断两个日期是否同一天
     * @param date1
     * @param date2
     * @return
     */
    public static boolean isSameDay(Date date1, Date date2) {
        try {
            Calendar cal1 = Calendar.getInstance();
            cal1.setTime(date1);
            Calendar cal2 = Calendar.getInstance();
            cal2.setTime(date2);
            return cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) && cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH) && cal1.get(Calendar.DATE) == cal2.get(Calendar.DATE);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 判断两个时间是否在同一周内。周日为第一天，周六第七天
     * @param date1
     * @param date2
     * @return
     */
    public static boolean isSameWeek(Date date1, Date date2) {
        try {
            Calendar cal = Calendar.getInstance();
            cal.setTime(date1);
            int n = cal.get(Calendar.DAY_OF_WEEK) - 1;
            cal.add(Calendar.DATE, -n);
            Date week1 = cal.getTime();
            cal.add(Calendar.DATE, 7);
            Date week2 = cal.getTime();
            return date2.after(week1) && date2.before(week2);
        } catch (Exception e) {
        }
        return false;
    }

    /**
     * 判断两个时间是否在同一个月份
     * @param date1
     * @param date2
     */
    public static boolean isSameMonth(Date date1, Date date2) {
        try {
            Calendar cal = Calendar.getInstance();
            cal.setTime(date1);
            cal.set(Calendar.DAY_OF_MONTH, 1);
            cal.set(Calendar.HOUR_OF_DAY, 1);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
            cal.add(Calendar.HOUR_OF_DAY, -1);
            Date month1 = cal.getTime();
            cal.add(Calendar.MONTH, 1);
            Date month2 = cal.getTime();
            return date2.after(month1) && date2.before(month2);
        } catch (Exception e) {
        }
        return false;
    }

    /**
     * 取得指定日期的零点，精确到毫秒
     * @param date
     * @return
     */
    public static Date getZero(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /**
     * 比较date1是否比date2超前指定的时间间隔
     * @param date1
     * @param date2
     * @param intervalType 时间间隔类型
     *            输入Calendar.YEAR,Calendar.MONTH,Calendar.MONTH,Calendar.DATE,Calendar.HOUR,Calendar.HOUR,Calendar.
     *            MINUTE,Calendar.SECOND,Calendar.MILLISECOND
     * @param interval 时间间隔
     * @return
     */
    public static boolean before(Date date1, Date date2, int intervalType, int interval) {
        try {
            Calendar cal1 = Calendar.getInstance();
            cal1.setTime(date1);
            cal1.set(intervalType, interval);
            Calendar cal2 = Calendar.getInstance();
            cal2.setTime(date2);
            return cal1.before(cal2);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 比较date1是否比date2落后指定的时间间隔
     * @param date1
     * @param date2
     * @param intervalType 时间间隔类型
     *            输入Calendar.YEAR,Calendar.MONTH,Calendar.MONTH,Calendar.DATE,Calendar.HOUR,Calendar.HOUR,Calendar.
     *            MINUTE,Calendar.SECOND,Calendar.MILLISECOND
     * @param interval 时间间隔
     * @return
     */
    public static boolean after(Date date1, Date date2, int intervalType, int interval) {
        return before(date2, date1, intervalType, interval);
    }

    /**
     * 取得指定时间一定间隔前或者后的新时间
     * @param date
     * @param intervalType intervalType 时间间隔类型
     *            输入Calendar.YEAR,Calendar.MONTH,Calendar.MONTH,Calendar.DATE,Calendar.HOUR,Calendar.HOUR,Calendar.
     *            MINUTE,Calendar.SECOND,Calendar.MILLISECOND
     * @param interval 时间间隔
     * @return
     */
    public static Date add(Date date, int intervalType, int interval) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(intervalType, interval);
        return cal.getTime();
    }

    /** 当月中,存在该天数 */
    public static boolean isExistDay(int day){
    	Calendar calendar = Calendar.getInstance();
    	calendar.add(Calendar.MONTH, 1);
    	calendar.add(Calendar.DAY_OF_MONTH, -calendar.get(Calendar.DAY_OF_MONTH));
    	return day>0&&calendar.get(Calendar.DAY_OF_MONTH)>=day;
    }

    /** 获得上一个月的时间类型 */
    public static Date upMonth(){
    	Calendar calendar = Calendar.getInstance();
    	calendar.add(Calendar.MONTH, -1);
    	return calendar.getTime();
    }

    /**
     * 获得DAY_OF_WEEK
     * @return
     */
    public static int getDayOfWeek(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.DAY_OF_WEEK);
    }

    /**
     * 获得DAY_OF_MONTH
     * @return
     */
    public static int getDayOfMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.DAY_OF_MONTH);
    }

    public static void main(String[] args) {
        System.out.println(getDayOfMonth(new Date()));
    }
}
