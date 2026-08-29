package com.pearl.o2o.nosql.object;


public abstract class TeamNosqlRecord extends NosqlRecord {
	protected int teamId;
	public static final String PREFIX = "t:";
	

	public String getKey(){
		return new StringBuilder().append(PREFIX).append(teamId).append(":")
				.append(getDataTypeKey())
				.append(":").append(getRecordType()).toString();
	}
	
	public static String getKey(int teamId,  NoSqlDataType nosqlDataType, String recordType){
		return new StringBuilder().append(PREFIX).append(teamId).append(":")
				.append(nosqlDataType.toString()).append(":")
				.append(recordType).toString();
	}

	public int getTeamId() {
		return teamId;
	}

	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}

}
