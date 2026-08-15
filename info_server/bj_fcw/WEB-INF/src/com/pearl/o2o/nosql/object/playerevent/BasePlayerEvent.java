package com.pearl.o2o.nosql.object.playerevent;

import com.pearl.o2o.nosql.object.PlayerNosqlRecord;
import com.pearl.o2o.utils.Converter;

public class BasePlayerEvent extends PlayerNosqlRecord {
	protected final String content;
	protected final String time;//seconds
	
	private String nosqlPlainStr;
	
	public static final NoSqlDataType DATATYPE = NoSqlDataType.LIST;
	public static final String 	RECORDTYPE = "PlayerEvent";
	
	public enum EventType{
		LEVELUP,PURCHASE,ADDFRIEND,PRESENT,ACHIEVEMENT,TITLE,TEAMBUFFACTIVE,VIPLEVELUP
	}
	
	public BasePlayerEvent(String content, String time , int playerId,String playerName) {
		super(playerId, playerName);
		this.time = time;
		this.content = content;
	}
	
	public BasePlayerEvent( int playerId,String playerName,String time, String nosqlPlainStr) {
		super(playerId, playerName);
		this.time = time;
		this.content = "";
		this.nosqlPlainStr = nosqlPlainStr;
	}
	
	@Override
	public  NoSqlDataType getDataTypeKey(){
		return DATATYPE;
	}
	
	public String getTime() {
		return time;
	}
	
	@Override
	protected String getRecordType() {
		return RECORDTYPE;
	}
	
	//time|template
	@Override
	public String generateNosqlPlainStr() {
		return ""+time.toString()+DELEMETER+Converter.friendNewsEntry(this);
	}
	
	public static BasePlayerEvent getFromNosqlPlainStr(String str,int playerId, String playerName){
		String[] fields = str.split("\\" + DELEMETER);
		return new BasePlayerEvent(playerId, playerName,fields[0] ,fields[1]);
	}
	
	public String getContent() {
		return content;
	}
	
	/**
	 * need be overided
	 */
	public int getType(){
		return -1;
	}

	public String getNosqlPlainStr() {
		return nosqlPlainStr;
	}
}
	
