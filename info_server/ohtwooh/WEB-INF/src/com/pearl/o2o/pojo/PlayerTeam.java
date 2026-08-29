package com.pearl.o2o.pojo;


public class PlayerTeam {

	private static final long serialVersionUID = -3237212642385023223L;
	private Integer playerId;
	private Integer teamId;
	private String nickName;
	private Integer rank;
	private Integer job;
	private Integer killNum;
	private Integer deadNum;
	private Integer createTime;
	private String approved;
	
	public Integer getPlayerId() {
		return playerId;
	}
	public void setPlayerId(Integer playerId) {
		this.playerId = playerId;
	}
	public Integer getTeamId() {
		return teamId;
	}
	public void setTeamId(Integer teamId) {
		this.teamId = teamId;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public Integer getRank() {
		return rank;
	}
	public void setRank(Integer rank) {
		this.rank = rank;
	}
	public Integer getJob() {
		return job;
	}
	public void setJob(Integer job) {
		this.job = job;
	}
	public Integer getKillNum() {
		return killNum;
	}
	public void setKillNum(Integer killNum) {
		this.killNum = killNum;
	}
	public Integer getDeadNum() {
		return deadNum;
	}
	public void setDeadNum(Integer deadNum) {
		this.deadNum = deadNum;
	}
	
	public Integer getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Integer createTime) {
		this.createTime = createTime;
	}
	public String getApproved() {
		return approved;
	}
	public void setApproved(String approved) {
		this.approved = approved;
	}
	
}
