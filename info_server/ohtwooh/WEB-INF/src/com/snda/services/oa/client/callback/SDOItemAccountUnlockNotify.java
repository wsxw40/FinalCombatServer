package com.snda.services.oa.client.callback;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import com.snda.services.oa.client.impl.ISDOMessage;

public final class SDOItemAccountUnlockNotify {
	private final int result;
	private final int payType;
	private final int balance;
	private final int balanceLocked;
	private final int uidType;
	private final String userId;
	private final String sessionId;
	private final String contextId;
	private final String orderId;
	private final String appendix;
	
	public SDOItemAccountUnlockNotify(ISDOMessage idoMsg) throws NumberFormatException{
		result = Integer.valueOf(idoMsg.getValue("result"));
		payType = Integer.valueOf(idoMsg.getValue("payType"));
		balance = Integer.valueOf(idoMsg.getValue("balance"));
		balanceLocked = Integer.valueOf(idoMsg.getValue("balanceLocked"));
		uidType = Integer.valueOf(idoMsg.getValue("uidType"));
		userId = idoMsg.getValue("userId");
		sessionId = idoMsg.getValue("sessionId");
		contextId = idoMsg.getValue("contextId");
		orderId = idoMsg.getValue("orderId");
		appendix = idoMsg.getValue("appendix");
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

	public int getBalanceLocked() {
		return balanceLocked;
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
	
	@Override
	public String toString() {
		 return ReflectionToStringBuilder.toString(this);
	}
}
