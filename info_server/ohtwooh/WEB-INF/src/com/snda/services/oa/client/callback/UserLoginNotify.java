package com.snda.services.oa.client.callback;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import com.snda.services.oa.client.impl.ISDOMessage;

public class UserLoginNotify {
	private final String uniqueId;
	private final String sndaID;
	private final String gameAccount;
	private final String idCard;
	private final String appId;
	private final String areaId;
	private final String clientSignature;
	private final String forbiddenAccount;
	private final String goldenAccount;
	private final String adult;
	private final String applingAdult;
	private final String spreadedUser;
	private final String spreader;
	private final String onlineSMSNotice;
	private final String ekeyType;
	private final String eKeyBindFlag;
	// used for syn verify
	private final String result;
	
	public UserLoginNotify(ISDOMessage idoMsg){
		this.uniqueId = idoMsg.getValue("UniqueId");
		this.result = idoMsg.getValue("result");
		this.sndaID = idoMsg.getValue("sndaID");
		this.gameAccount = idoMsg.getValue("gameAccount");
		this.idCard = idoMsg.getValue("idCard");
		this.appId = idoMsg.getValue("appId");
		this.areaId = idoMsg.getValue("areaId");
		this.clientSignature = idoMsg.getValue("clientSignature");
		this.forbiddenAccount = idoMsg.getValue("forbiddenAccount");
		
		this.goldenAccount = idoMsg.getValue("goldenAccount");
		this.adult = idoMsg.getValue("adult");
		this.applingAdult = idoMsg.getValue("applingAdult");
		this.spreadedUser = idoMsg.getValue("spreadedUser");
		this.spreader = idoMsg.getValue("spreader");
		
		this.onlineSMSNotice = idoMsg.getValue("onlineSMSNotice");
		this.ekeyType = idoMsg.getValue("ekeyType");
		this.eKeyBindFlag = idoMsg.getValue("eKeyBindFlag");
	}
	
	
	public String getResult() {
		return result;
	}

	public String getSndaID() {
		return sndaID;
	}
	public String getGameAccount() {
		return gameAccount;
	}
	public String getIdCard() {
		return idCard;
	}
	public String getAppId() {
		return appId;
	}
	public String getAreaId() {
		return areaId;
	}
	public String getClientSignature() {
		return clientSignature;
	}
	public String getForbiddenAccount() {
		return forbiddenAccount;
	}
	public String getGoldenAccount() {
		return goldenAccount;
	}
	public String getAdult() {
		return adult;
	}
	public String getApplingAdult() {
		return applingAdult;
	}
	public String getSpreadedUser() {
		return spreadedUser;
	}
	public String getSpreader() {
		return spreader;
	}
	public String getOnlineSMSNotice() {
		return onlineSMSNotice;
	}
	public String getEkeyType() {
		return ekeyType;
	}
	public String getEKeyBindFlag() {
		return eKeyBindFlag;
	}

	public String getUniqueId() {
		return uniqueId;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
	
}
