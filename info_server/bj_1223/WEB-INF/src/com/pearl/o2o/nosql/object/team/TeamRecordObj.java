package com.pearl.o2o.nosql.object.team;

import java.util.Date;

public class TeamRecordObj {
	private int team;
	private int type;
	private int playerid;
	private Date createTime;
	private static char c = ':';
	
	public String getStringValue(){
		//team:player:time:type-->0,apply 1,join 2,quit 
		return new StringBuilder().append(team).append(c).append(playerid).append(c).append(createTime.getTime()).append(c).append(type).toString();
	}

	public static TeamRecordObj toObject(String stringValue){
		TeamRecordObj result = new TeamRecordObj();
		String[] values = stringValue.split(":");
		result.setTeam(Integer.parseInt(values[0]));
		result.setPlayerid(Integer.parseInt(values[1]));
		result.setCreateTime(new Date(Long.parseLong(values[2])));
		result.setType(Integer.parseInt(values[3]));
		return result;
	}
	
	public int getTeam() {
		return team;
	}

	public void setTeam(int team) {
		this.team = team;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getPlayerid() {
		return playerid;
	}

	public void setPlayerid(int playerid) {
		this.playerid = playerid;
	}

	public int getType() {
		return type;
	}
}
