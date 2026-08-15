package com.pearl.o2o.utils;

public class SecondPasswordStatus {
    /**未设置密码*/
	public final static int UNSET=0;   //未设置密码
	 /**已设置密码*/
	public final static int SET=1;		//已设置密码
	/**申请清空密码操作*/
	public final static int EMPTY=2;	//申请清空密码操作
	/**status=1*/
	public final static String SUCCESS="status=1";
	/**status=0*/
	public final static String FAIL="status=0";

	public final static String HAD_APPLY_EMPTY="time=";
//	public final static String WRONG_PASSWORD="wrong";
	
	
	public final static int THREE_DAY=3*24*3600;
    /**设置密码*/
	public final static int SET_PASSWORD=0;//设置密码
    /**验证密码*/
	public final static int CHECK_PASSWORD=1;//验证密码
}
