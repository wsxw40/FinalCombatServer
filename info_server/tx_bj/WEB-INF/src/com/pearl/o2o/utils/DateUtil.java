package com.pearl.o2o.utils;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.beanutils.converters.BigDecimalConverter;

public class DateUtil {
	 private static final DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	 private static final DateFormat df2 = new SimpleDateFormat("yyyy/MM/dd");
	 private static final DateFormat df3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	 public static String getCurrentTimeStr(){
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
	 *  通过时间获得两个时间之间相差的天数，注：如10天23小时59分会默认为10天
	 *  @param one 
	 *  @param two
	 *  @return Float
	 *  @author OuYangGuang
	 *  @date 2014-5-27
	 * */
	public static int getTimeDifference(Date one, Date two){
		Long timeL = one.getTime() - two.getTime();
		Long time = ((timeL / 60 / 60 / 24) / 1000);
		Long result = Math.abs(time);
		return Integer.parseInt(String.valueOf(result));
	}
	
	public static void main(String[] args) throws Exception {
		long tmp = 1000*60*60*24L;
		long time8 = 1000*60*60*8L;
		Date parse = df.parse("2012/8/31 0:0:1");
		boolean today = CacheUtil.isToday(parse.getTime());
		System.out.println(today);
		
		Date d1 = df.parse("2014/6/27 23:00:00");
		Date d2 = df.parse("2014/6/27 23:00:00");
		
		System.out.println(getTimeDifference(d2, d1));
	}
}
