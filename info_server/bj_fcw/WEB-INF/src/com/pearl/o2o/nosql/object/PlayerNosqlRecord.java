package com.pearl.o2o.nosql.object;

public abstract class PlayerNosqlRecord extends NosqlRecord{
	public   int playerId;
	public   String playerName;
	public static final String PREFIX = "p:";
	
	public PlayerNosqlRecord( int playerId,
			String playerName) {
		this.playerId = playerId;
		this.playerName = playerName;
	}
	//p:id:name:DATATYPE:RECORDTYPE
	public String getKey(){
		return new StringBuilder().append(PREFIX).append(playerId).append(":")
				.append(playerName).append(":").append(getDataTypeKey())
				.append(":").append(getRecordType()).toString();
	}
	
	public static String getKey(int playerId, String playerName, NoSqlDataType nosqlDataType, String recordType){
		return new StringBuilder().append(PREFIX).append(playerId).append(":")
				.append(playerName).append(":")
				.append(nosqlDataType.toString()).append(":")
				.append(recordType).toString();
	}

	public int getPlayerId() {
		return playerId;
	}

	public String getPlayerName() {
		return playerName;
	}
}