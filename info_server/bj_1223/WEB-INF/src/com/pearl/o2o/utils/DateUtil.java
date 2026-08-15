package com.pearl.o2o.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	 public static final DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	 public static final DateFormat df2 = new SimpleDateFormat("yyyy/MM/dd");
	 public static final DateFormat df3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	 public static String getCurrentTimeStr(){
		 return df.format(new Date());
	 }

	public static DateFormat getDf2() {
		return df2;
	}
	public static DateFormat getDf() {
		return df;
	}
	public static void main(String[] args) throws Exception {
		long tmp = 1000*60*60*24L;
		long time8 = 1000*60*60*8L;
		Date parse = df.parse("2012/8/31 0:0:1");
		boolean today = CacheUtil.isToday(parse.getTime());
		System.out.println(today);
	}
}
