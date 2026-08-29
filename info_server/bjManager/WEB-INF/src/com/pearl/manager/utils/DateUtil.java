package com.pearl.manager.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	 private static final DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	 
	 private static final DateFormat df2 = new SimpleDateFormat("yyyy/MM/dd");
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
}
