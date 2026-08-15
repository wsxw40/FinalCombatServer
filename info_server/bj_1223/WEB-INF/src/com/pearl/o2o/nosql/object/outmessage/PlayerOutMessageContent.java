package com.pearl.o2o.nosql.object.outmessage;

import com.pearl.o2o.nosql.object.PlayerNosqlRecord;

public class PlayerOutMessageContent extends PlayerNosqlRecord {
	public static final NoSqlDataType DATATYPE = NoSqlDataType.STRING;
	public static final String 	RECORDTYPE = "PlayerOutMessage";
	
	private String content;
	
	public PlayerOutMessageContent(int playerId, String playerName, String content) {
		super(playerId, playerName);
		this.content = content;
	}
	
	@Override
	protected NoSqlDataType getDataTypeKey() {
		return DATATYPE;
	}

	@Override
	protected String getRecordType() {
		return RECORDTYPE;
	}
	
	@Override
	public String generateNosqlPlainStr() {
		return content;
	}
	
	//see generateNosqlPlainStr
	public static PlayerOutMessageContent getFromNosqlPlainStr(int playerId, String playerName, String content) {
		return new PlayerOutMessageContent( playerId, playerName, content);
	}

	public String getContent() {
		return content;
	}
	
	
	
	
}
