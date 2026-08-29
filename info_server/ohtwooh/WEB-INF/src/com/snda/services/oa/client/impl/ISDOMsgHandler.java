package com.snda.services.oa.client.impl;

public class ISDOMsgHandler {
	public native long Initialize();
	public native int UnInitialize(long lAddress);

	public native String GetValue(long lAddress,String strKey);
	public native int SetValue(long lAddress,String strKey,String strValue);
	ISDOMsgHandler() {}
	
	static {
		System.loadLibrary("SDOA4BillingJni");
	}
}
