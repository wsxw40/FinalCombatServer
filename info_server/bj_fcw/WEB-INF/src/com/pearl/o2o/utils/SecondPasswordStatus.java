package com.pearl.o2o.utils;

public class SecondPasswordStatus {
	public final static int UNSET=0;   //未设置密码
	public final static int SET=1;		//已设置密码
	public final static int EMPTY=2;	//申请清空密码操作
	
	public final static String SUCCESS="status=1";
	public final static String FAIL="status=0";

	public final static String HAD_APPLY_EMPTY="time=";
//	public final static String WRONG_PASSWORD="wrong";
	
	
	public final static int THREE_DAY=3*24*3600;

	public final static int SET_PASSWORD=0;
	public final static int CHECK_PASSWORD=1;
}
