package com.pearl.o2o.nosql.object.playerrate;

import com.pearl.o2o.nosql.object.PlayerNosqlRecord;
import com.pearl.o2o.utils.Constants.GAMETYPE;

public class PlayerRate extends PlayerNosqlRecord {
	private GAMETYPE gameType;
	private int winRate;
	private int time;
	public static final NoSqlDataType DATATYPE = NoSqlDataType.LIST;
	public static final String RECORDTYPE_PREFIX = "PlayerRate";
	
	public PlayerRate(int winRate, int time, int playerId, String playerName, GAMETYPE gameType) {
		super(playerId, playerName);
		this.winRate = winRate;
		this.time = time;
		this.gameType = gameType;	
	}
	
	//see generateNosqlPlainStr
	//time|rate
	public PlayerRate(String nosqlStr, int playerId, String playerName, GAMETYPE gameType){
		super(playerId, playerName);
		String[] fields = nosqlStr.split("\\" + DELEMETER);
		this.time = Integer.valueOf(fields[0]);
		this.winRate = Integer.valueOf(fields[1]);
		this.gameType = gameType;
	}
	
	@Override
	public NoSqlDataType getDataTypeKey() {
		return DATATYPE;
	}

	@Override
	public String getRecordType() {
		return RECORDTYPE_PREFIX + gameType;
	}
	
	@Override
	//time|rate
	public String generateNosqlPlainStr() {
		return new StringBuilder().append(time).append(DELEMETER).append(winRate).toString();
	}

	public int getWinRate() {
		return winRate;
	}

	public int getTime() {
		return time;
	}

	public GAMETYPE getGameType() {
		return gameType;
	}
}
