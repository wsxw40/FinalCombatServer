package com.snda.services.oa.client.callback;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import com.snda.services.oa.client.impl.ISDOMessage;

public final class UserInfoAuthenNotify {
	public static final int PAYINFO_LENGTH = 5;
	private final int result;
	private final PayInfo[] payinfos = new PayInfo[PAYINFO_LENGTH];
	private final int uidType;
	private final String userId;
	private final String sessionId;
	private final String orderId;
	private final String beginTime;
	private final String endTime;
	private final String endpointIp;
	private final String appendix;
	
	public UserInfoAuthenNotify(ISDOMessage idoMsg) throws NumberFormatException{
		this.result = Integer.valueOf(idoMsg.getValue("result"));
		//payinfo
		for (int i=0;i<PAYINFO_LENGTH;i++){
			int payType = Integer.valueOf(idoMsg.getValue("payInfo[" + i +"].payType"));
			int balance = Integer.valueOf(idoMsg.getValue("payInfo[" + i +"].balance"));
			payinfos[i] = new PayInfo(payType,balance);
		}
		
		this.uidType = Integer.valueOf(idoMsg.getValue("uidType"));
		this.userId = idoMsg.getValue("userId");
		this.sessionId = idoMsg.getValue("sessionId");
		this.orderId = idoMsg.getValue("orderId");
		this.beginTime = idoMsg.getValue("beginTime");
		this.endTime = idoMsg.getValue("endTime");
		this.endpointIp = idoMsg.getValue("endpointIp");
		this.appendix = idoMsg.getValue("appendix");
	}
	
	public int getResult() {
		return result;
	}
	public int getUidType() {
		return uidType;
	}
	public String getUserId() {
		return userId;
	}
	public String getSessionId() {
		return sessionId;
	}
	public String getOrderId() {
		return orderId;
	}
	public String getBeginTime() {
		return beginTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public String getEndpointIp() {
		return endpointIp;
	}
	public String getAppendix() {
		return appendix;
	}
	
	public int getBalance(int payType){
		for(PayInfo payInfo : payinfos){
			if(payInfo.getPayType() == payType){
				return payInfo.balance;
			}
		}
		return -1;
	}
	
	class PayInfo{
		private final int payType;
		private final int balance;
		
		public PayInfo(int payType, int balance) {
			super();
			this.payType = payType;
			this.balance = balance;
		}
		public int getPayType() {
			return payType;
		}
		public int getBalance() {
			return balance;
		}
	}
	
	public PayInfo[] getPayinfos() {
		return payinfos;
	}


	@Override
	public String toString() {
		 return ReflectionToStringBuilder.toString(this);
	}
}
