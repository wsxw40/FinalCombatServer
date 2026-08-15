package com.snda.services.oa.client.impl;

public class ISDOUserAuthenHandler {
	
	static {
		System.loadLibrary("SDOA4BillingJni");
	}
	
	protected SDOComponentImpl sdoComponent;
	
	public void OnSndaUserAuthenCallback(long lResMsg){
		sdoComponent.OnSndaUserAuthenCallback(lResMsg);
	}
	public native int Initialize(String strConfigFile, ISDOUserAuthenHandler handler);
	public native int UnInitialize();
	public native int SyncGetUserInfo(String strSessionId, String strEndpointIp, long lUniqueId, long lResMsg);
	public native int ASyncGetUserInfo(String strSessionId, String strEndpointIp, long lUniqueId);
	ISDOUserAuthenHandler() {}
	
}
