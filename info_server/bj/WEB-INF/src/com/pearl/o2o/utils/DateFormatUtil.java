package com.pearl.o2o.utils;

import java.text.SimpleDateFormat;

public class DateFormatUtil {
	private static SimpleDateFormat ymdhmsSF=new SimpleDateFormat("yyyy-MM-dd HH:mm:SS");
	
	private static SimpleDateFormat ymdhmSF=new SimpleDateFormat("yyyy-MM-dd HH:mm");
	
	private static SimpleDateFormat ymd=new SimpleDateFormat("yyyy-MM-dd");
	
	
	public static SimpleDateFormat getYMDHMSSf(){
		return (SimpleDateFormat) ymdhmsSF.clone();
	}

	public static SimpleDateFormat getYMDHMSf(){
		return (SimpleDateFormat) ymdhmSF.clone();
	}
	
	public static SimpleDateFormat getYMDSf(){
		return (SimpleDateFormat) ymd.clone();
	}	
}
