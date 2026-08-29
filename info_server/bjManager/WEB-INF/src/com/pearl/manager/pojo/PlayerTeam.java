package com.pearl.manager.pojo;


public class PlayerTeam extends BaseMappingBean<PlayerTeam>{

	private static final long serialVersionUID = -3237212642385023223L;
	private Integer playerId;
	private Integer teamId;
	private String nickName;
	private Integer rank;
	private Integer isVip;
	private int internetCafe=0;
	private Integer exp;
	private Integer job;
	private Integer killNum;
	private Integer deadNum;
	private Integer createTime;
	private String approved;
	private Integer card;
	
	private Integer online=0;
	private Integer score;
	private Integer assist;
	
	private int contribution;//贡献度
	private int times;//战队战次数
	private int point;//战队战分数
	
	
	public Integer getAssist() {
		return assist;
	}
	public void setAssist(Integer assist) {
		this.assist = assist;
	}
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	public Integer getOnline() {
		return online;
	}
	public void setOnline(Integer online) {
		this.online = online;
	}
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
	public Integer getExp() {
		return exp;
	}
	public void setExp(Integer exp) {
		this.exp = exp;
	}
	public Integer getIsVip() {
		return isVip;
	}
	public void setIsVip(Integer isVip) {
		this.isVip = isVip;
	}
	public Integer getCard() {
		return card;
	}
	public void setCard(Integer card) {
		this.card = card;
	}
	public int getInternetCafe() {
		return internetCafe;
	}
	public void setInternetCafe(int internetCafe) {
		this.internetCafe = internetCafe;
	}
	
}
