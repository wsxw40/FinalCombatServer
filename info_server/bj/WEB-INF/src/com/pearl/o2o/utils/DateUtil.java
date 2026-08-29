package com.pearl.o2o.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	private static final DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	private static final DateFormat df2 = new SimpleDateFormat("yyyy/MM/dd");
	private static final DateFormat df3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public static String getCurrentTimeStr() {
		return ((DateFormat) df.clone()).format(new Date());
	}

	public static DateFormat getDf() {
		return (DateFormat) df.clone();
	}

	public static DateFormat getDf2() {
		return (DateFormat) df2.clone();
	}

	public static DateFormat getDf3() {
		return (DateFormat) df3.clone();
	}

	/**
	 * 获取传递的时间的下一天和当前时间相差的毫秒数 '00:00:00'
	 * @return
	 */
	public static long getTimeMillis(String time) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_YEAR, 0);

		String dateStr = df3.format(cal.getTime());
		Date executeTime;
		try {
			executeTime = df3.parse(dateStr.replace(dateStr.substring(11), time));
			return executeTime.getTime() - System.currentTimeMillis();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}

	/**
	 * 通过时间获得两个时间之间相差的天数，注：如10天23小时59分会默认为10天
	 * @param one
	 * @param two
	 * @return Float
	 * @author OuYangGuang
	 * @date 2014-5-27
	 */
	public static int getTimeDifference(Date one, Date two) {
		Long timeL = one.getTime() - two.getTime();
		Long time = ((timeL / 60 / 60 / 24) / 1000);
		Long result = Math.abs(time);
		return Integer.parseInt(String.valueOf(result));
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
			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			cal.set(Calendar.MILLISECOND, 0);
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

	public static void main(String[] args) throws Exception {
		System.out.println(getTimeMillis("00:00:00"));
	}
}
