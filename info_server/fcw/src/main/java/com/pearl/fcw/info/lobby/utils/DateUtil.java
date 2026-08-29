package com.pearl.fcw.info.lobby.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.lang3.time.DateUtils;

public class DateUtil {

    public static long dateGap = 0L;

    public final static ThreadLocal<SimpleDateFormat> formatter = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH));
    public final static ThreadLocal<SimpleDateFormat> formatter2 = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH));
    public final static ThreadLocal<SimpleDateFormat> formatter3 = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.ENGLISH));

    public static String getCurrentTimeStr() {
        return formatter.get().format(now());
    }

    public final static long MINUTE_OF_MILLISECONDS = 60000; // 每分钟的毫秒数, 60 * 1000
    public final static long HOUR_OF_MILLISECONDS = 3600000; // 每小时的毫秒数, 60 * 60 * 1000
    public final static long DAY_OF_MILLISECONDS = 86400000; // 每天的毫秒数, 24 * 60 * 60 * 1000

    public static String format(Date d) {
        if (d == null) {
            return null;
        }
        return formatter.get().format(d);
    }

    /**
     * @param source yyyy-MM-dd HH:mm:ss 或 yyyy-MM-dd 或 yyyy/MM/dd HH:mm:ss
     * @return Date
     * @throws IllegalArgumentException
     */
    public static Date parseString(String source) {
        try {
            return formatter.get().parse(source);
        } catch (ParseException e) {
            try {
                return formatter2.get().parse(source);
            } catch (ParseException e1) {
                try {
                    return formatter3.get().parse(source);
                } catch (ParseException e2) {
                    throw new IllegalArgumentException("不支持的日期格式");
                }
            }
        }
    }

    public static Date now() {
        return new Date(dateGap + System.currentTimeMillis());
    }

    /**
     * 获取当天零点
     * @return
     */
    public static Date dailyTime() {
        Calendar c = Calendar.getInstance();
        c.setTime(now());
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTime();
    }

    public static Date customTime(int hour, int minute, int second) {
        Calendar c = Calendar.getInstance();
        c.setTime(now());
        c.set(Calendar.HOUR_OF_DAY, hour);
        c.set(Calendar.MINUTE, minute);
        c.set(Calendar.SECOND, second);
        return c.getTime();
    }

    /**
     * 取得未来离基准时间最近的，且与给定时、分、秒相同的时间。
     * @param baseTime 基准时间
     * @param hour 小时
     * @param minute 分钟
     * @param second 秒
     * @return 未来离基准时间最近的，且与给定时、分、秒相同的时间
     */
    public static Date future(Date baseTime, int hour, int minute, int second) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(baseTime);
        cal.set(Calendar.MILLISECOND, 0);
        cal.set(Calendar.SECOND, second);
        cal.set(Calendar.MINUTE, minute);
        cal.set(Calendar.HOUR_OF_DAY, hour);
        if (baseTime.after(cal.getTime())) {
            cal.add(Calendar.DATE, 1);
        }
        return cal.getTime();
    }

    /**
     * 取得指定日期后一天的零点时间。
     * @param date 指定日期
     * @return 指定日期的后一天
     */
    public static Date tomorrow(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, 1);
        cal.set(Calendar.MILLISECOND, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        return cal.getTime();
    }

    /**
     * 取得指定日期前一天的零点时间。
     * @param date 指定日期
     * @return 指定日期的前一天
     */
    public static Date yesterday(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.MILLISECOND, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.add(Calendar.DATE, -1);
        return cal.getTime();
    }

    /**
     * 取得指定日期之后，第一个和给定“星期中的某天”一致的零点时间。
     * @param date 指定日期
     * @param dayOfWeek
     * @return 日期
     * @see Calendar#DAY_OF_WEEK
     */
    public static Date nextDayOfWeek(Date date, int dayOfWeek) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int offset = dayOfWeek - cal.get(Calendar.DAY_OF_WEEK);
        if (offset <= 0) {
            offset += Calendar.DAY_OF_WEEK;
        }
        cal.add(Calendar.DAY_OF_WEEK, offset);
        cal.set(Calendar.MILLISECOND, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        return cal.getTime();
    }

    /**
     * 验证两个日期的间隔是否大于指定的天数,secondDate-firstDate>=gap
     * @param firstDate
     * @param secondDate
     * @param gap 间隔天数
     * @return
     */
    public static boolean isGapDay(Date firstDate, Date secondDate, int gap) {

        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(firstDate);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(secondDate);

        if (cal2.get(Calendar.YEAR) == cal1.get(Calendar.YEAR)) {
            if (cal2.get(Calendar.DAY_OF_YEAR) - cal1.get(Calendar.DAY_OF_YEAR) >= gap) {
                return true;
            }
        }
        return false;
    }

    /**
     * 验证两个日期的间隔是否大于指定的小时,secondDate-firstDate>=gap
     * @param firstDate
     * @param secondDate
     * @param gap 间隔小时
     * @return
     */
    public static boolean isGapHour(Date firstDate, Date secondDate, int gap) {

        if (firstDate == null || secondDate == null) {
            return false;
        }

        return (secondDate.getTime() - firstDate.getTime()) >= gap * HOUR_OF_MILLISECONDS;
    }

    /**
     * 验证两个日期的间隔是否大于指定的分钟,secondDate-firstDate>=gap
     * @param firstDate
     * @param secondDate
     * @param gap 间隔分钟
     * @return
     */
    public static boolean isGapMinute(Date firstDate, Date secondDate, int gap) {

        if (firstDate == null || secondDate == null) {
            return false;
        }

        return (secondDate.getTime() - firstDate.getTime()) >= gap * MINUTE_OF_MILLISECONDS;
    }

    /**
     * 取得指定日期后一个月的第一天零点时间。
     * @param date 指定日期
     * @return 日期
     */
    public static Date nextDayOfMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, 1);
        cal.set(Calendar.MILLISECOND, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        return cal.getTime();
    }

    /**
     * 取得第二天的0时的Date对象。
     * @param date
     * @return
     */
    public static Date nextDayZero(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.MILLISECOND, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.DAY_OF_YEAR, Calendar.DAY_OF_YEAR + 1);
        return cal.getTime();
    }

    /**
     * 取得第二天的0时的Date对象。
     * @param date
     * @return
     */
    public static Date thisDayZero(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.MILLISECOND, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.DAY_OF_YEAR, Calendar.DAY_OF_YEAR);
        return cal.getTime();
    }

    /**
     * 获得当前时间跟凌晨的偏移分钟数
     * @param h
     * @param m
     */
    public static int getDailyGap(int h, int m) {
        return 60 * h + m;
    }

    public static boolean isSameDay(Date date1, Date date2) {
        if (date1 == null || date2 == null) {
            return false;
        }
        return DateUtils.isSameDay(date1, date2);
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

    public static int toUnixSeconds(Date date) {
        return date != null ? (int) (date.getTime() / 1000) : 0;
    }

    /**
     * 判断指定日期是否为今天
     * @param c
     * @return true:今天
     */
    public static boolean isToday(Calendar c) {
        Calendar now = Calendar.getInstance();
        now.setTime(DateUtil.now());

        if (now.get(Calendar.YEAR) == c.get(Calendar.YEAR) && now.get(Calendar.MONTH) == c.get(Calendar.MONTH) && now.get(Calendar.DAY_OF_MONTH) == c.get(Calendar.DAY_OF_MONTH)) {
            return true;
        }

        return false;
    }

    /**
     * 判断指定日期是否为今天
     * @param now 系统当前时间
     * @param today 日期
     * @return true:今天
     */
    public static boolean isToday(Date now, Date today) {
        Calendar n = Calendar.getInstance();
        n.setTime(now);

        Calendar t = Calendar.getInstance();
        t.setTime(today);

        if (n.get(Calendar.YEAR) == t.get(Calendar.YEAR) && n.get(Calendar.DAY_OF_YEAR) == t.get(Calendar.DAY_OF_YEAR)) {
            return true;
        }

        return false;
    }

    /**
     * 判断指定日期是否为昨天
     * @param now
     * @param yesterday
     * @return true:是昨天
     */
    public static boolean isYesterday(Date now, Date yesterday) {
        Calendar y = Calendar.getInstance();
        y.setTime(yesterday);

        Calendar n = Calendar.getInstance();
        n.setTime(now);
        if (n.get(Calendar.YEAR) == y.get(Calendar.YEAR) && n.get(Calendar.DAY_OF_YEAR) - 1 == y.get(Calendar.DAY_OF_YEAR)) {
            return true;
        }
        return false;
    }

    /**
     * 判断指定日期是否为今天；或者日期集合中是否有当天
     * @param timeStr yyyy-MM-dd HH:mm:ss（或者 yyyy-MM-dd HH:mm:ss;yyyy-MM-dd HH:mm:ss）
     * @return true:今天
     * @throws Exception
     */
    public static boolean isToday(String timeStr) {
        try {
            String[] days = timeStr.split(";");
            Calendar cal = Calendar.getInstance();
            for (String day : days) {
                cal.setTime(parseString(day));
                if (isToday(cal)) {
                    return true;
                }
            }
        } catch (Exception e) {
        }
        return false;
    }

    /**
     * 获得当前月的最大天数
     * @return 天数
     */
    public static int getMaximumDayOfMonth() {
        return Calendar.getInstance().getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获得DAY_OF_MONTH
     * @return
     */
    public static int getTodayOfMonth() {
        return Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获得DAY_OF_WEEK
     * @return
     */
    public static int getTodayOfWeek() {
        return Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
    }

    /**
     * 获取上周的当前时间
     * @return
     */
    public static Date getLastWeekTime() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, -7);
        return c.getTime();
    }

    /**
     * 获取上周的零点的时间
     * @return
     */
    public static Date getLastWeekZeroTime() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, -7);
        c.set(Calendar.HOUR_OF_DAY, 1);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.add(Calendar.HOUR_OF_DAY, -1);
        return c.getTime();
    }

    /**
     * 当前是否为周日
     * @return
     */
    public static boolean isSunday() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(DateUtil.now());
        return calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY;
    }

    public static void main(String[] args) {
//        Calendar cal = Calendar.getInstance();
//        cal.setTime(DateUtil.now());
//        System.out.println(cal.get(Calendar.DAY_OF_WEEK));
//        System.out.println(cal.getActualMaximum(Calendar.DAY_OF_MONTH));
//        System.out.println(cal.get(Calendar.HOUR_OF_DAY));

        // long a = 1438098993071l;
        // Date d = new Date(a);
        // System.out.println("1438098993071: " + DateUtil.format(d));

        // Calendar c = Calendar.getInstance();
        // c.set(Calendar.HOUR, 0);
        // c.set(Calendar.MINUTE, 0);
        // c.set(Calendar.SECOND, 0);
        // System.out.println(c.get(Calendar.MILLISECOND));

        Date now = DateUtil.now();
        Date yes = DateUtil.yesterday(now);
        System.out.println(yes);

        System.out.println(isGapHour(yes, now, 43));

//        System.out.println(nextDayZero(now()));
    }
}
