package com.snda.services.oa.client.callback;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import com.snda.services.oa.client.impl.ISDOMessage;

public final class SDOItemAccountLockNotify {
	private final int result;
	private final int payType;
	private final int balance;
	private final int uidType;
	private final String userId;
	private final String sessionId;
	private final String contextId;
	//data pack id
	private final String orderId;
	private final String appendix;
	private final String award_num;
	
	public SDOItemAccountLockNotify(ISDOMessage idoMsg) throws NumberFormatException{
		result = Integer.valueOf(idoMsg.getValue("result"));
		payType = Integer.valueOf(idoMsg.getValue("payType"));
		balance = Integer.valueOf(idoMsg.getValue("balance"));
		uidType = Integer.valueOf(idoMsg.getValue("uidType"));;
		userId = idoMsg.getValue("userId");
		sessionId = idoMsg.getValue("sessionId");
		contextId = idoMsg.getValue("contextId");
		orderId = idoMsg.getValue("orderId");
		appendix = idoMsg.getValue("appendix");
		award_num = idoMsg.getValue("award_num");
	}

	
	
	public int getResult() {
		return result;
	}


	public int getPayType() {
		return payType;
	}

	public int getBalance() {
		return balance;
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

	public String getContextId() {
		return contextId;
	}

	public String getOrderId() {
		return orderId;
	}


	public String getAppendix() {
		return appendix;
	}

	public String getAward_num() {
		return award_num;
	}

	@Override
	public String toString() {
		 return ReflectionToStringBuilder.toString(this);
	}

	
}
