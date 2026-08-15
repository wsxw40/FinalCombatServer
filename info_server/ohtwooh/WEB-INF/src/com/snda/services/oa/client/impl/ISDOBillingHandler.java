package com.snda.services.oa.client.impl;

public class ISDOBillingHandler {
	static {
		System.loadLibrary("SDOA4BillingJni");
	}
	
	protected SDOComponentImpl sdoComponent;
	
	public void OnSndaBillingCallback(int nMsgType, long lResMsg)
	{
		sdoComponent.onBillingCallBack(nMsgType, lResMsg);
	}

	public native int Initialize(String strConfigFile, ISDOBillingHandler handler);
    public native int UnInitialize();
	public native int SendRequest(int nMsgType, long lReqMsg);
	public native String GetUniqueId();
	public native String GetUniqueIdByParam(int nAreaId,int nGroupId);
	ISDOBillingHandler() {}

}