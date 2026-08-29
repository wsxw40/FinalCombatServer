package com.pearl.o2o.nosql.object.recentplayer;

import com.pearl.o2o.nosql.object.PlayerNosqlRecord;
import com.pearl.o2o.utils.Constants.GAMETYPE;

public class RecentPlayer extends PlayerNosqlRecord {
	private GAMETYPE gameType;
	private int recentPlayerId;
	public static final NoSqlDataType DATATYPE = NoSqlDataType.LIST;
	public static final String RECORDTYPE = "RecentPlayer";
	
	public RecentPlayer(int recentPlayerId, int playerId, String playerName, GAMETYPE gameType) {
		super(playerId, playerName);
		this.recentPlayerId = recentPlayerId;
		this.gameType = gameType;	
	}
	
	//see generateNosqlPlainStr
	//recentPlayerId
	public RecentPlayer(String nosqlStr, int playerId, String playerName, GAMETYPE gameType){
		super(playerId, playerName);
		this.recentPlayerId = Integer.valueOf(nosqlStr);
		this.gameType = gameType;
	}
	
	@Override
	public NoSqlDataType getDataTypeKey() {
		return DATATYPE;
	}

	@Override
	public String getRecordType() {
		return RECORDTYPE;
	}
	
	@Override
	//playerId
	public String generateNosqlPlainStr() {
		return new StringBuilder().append(recentPlayerId).toString();
	}

	public GAMETYPE getGameType() {
		return gameType;
	}
}
