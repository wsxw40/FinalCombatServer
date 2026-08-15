package com.pearl.o2o.nosql.object.team;

import com.pearl.o2o.nosql.object.TeamNosqlRecord;

public class TeamBattleHistory extends  TeamNosqlRecord{
	public static final NoSqlDataType DATATYPE = NoSqlDataType.LIST;
	public static final String 	RECORDTYPE = "TeamHistory";
	private static final String DELEMETER = "|";
	public static final String NAMEDELEMETER = ",";
	
	private String opponentName;
	private String attendees;
	private boolean isWin;
	private String battleTime;
	
	public TeamBattleHistory(String opponentName, String attendees, boolean isWin, String battleTime, int teamId) {
		this.opponentName = opponentName;
		this.attendees = attendees;
		this.isWin = isWin;
		this.battleTime = battleTime;
		this.teamId = teamId;
	}
	//see generateNosqlPlainStr
	//opponentName|attende1,attende2..|isWin|battleTime
	public TeamBattleHistory(String str, int teamId) {
		String[] fields = str.split("\\" + DELEMETER);
		boolean isWin = Integer.valueOf(fields[2]) == 0? false:true;
		
		this.opponentName = fields[0];
		this.attendees = fields[1];
		this.isWin = isWin;
		this.battleTime = fields[3];
		this.teamId = teamId;
	}
	
	@Override
	protected NoSqlDataType getDataTypeKey() {
		return DATATYPE;
	}

	@Override
	protected String getRecordType() {
		return RECORDTYPE;
	}
	
	//opponentName|attende1,attende2..|isWin|battleTime
	@Override
	public String generateNosqlPlainStr() {
		StringBuilder sb = new StringBuilder();
		sb.append(opponentName).append(DELEMETER);
		sb.append(attendees).append(DELEMETER);
		if (isWin){
			sb.append(1);
		}else{
			sb.append(0);
		}
		sb.append(DELEMETER);
		sb.append(battleTime);

		return sb.toString();
	}

	public String getOpponentName() {
		return opponentName;
	}

	public String getAttendees() {
		return attendees;
	}

	public String getBattleTime() {
		return battleTime;
	}

	public int getResult(){
		return isWin?1:0;
	}
}
