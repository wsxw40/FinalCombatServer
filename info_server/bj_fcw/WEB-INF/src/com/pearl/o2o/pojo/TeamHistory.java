package com.pearl.o2o.pojo;

public class TeamHistory  {
	private int id;
	private Integer teamId;
	private String oppoNent;
	private String attendees;
	private Integer result;
	private Integer createTime;
	public Integer getTeamId() {
		return teamId;
	}
	public void setTeamId(Integer teamId) {
		this.teamId = teamId;
	}
	public String getOppoNent() {
		return oppoNent;
	}
	public void setOppoNent(String oppoNent) {
		this.oppoNent = oppoNent;
	}
	public String getAttendees() {
		return attendees;
	}
	public void setAttendees(String attendees) {
		this.attendees = attendees;
	}
	public Integer getResult() {
		return result;
	}
	public void setResult(Integer result) {
		this.result = result;
	}
	public Integer getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Integer createTime) {
		this.createTime = createTime;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}
