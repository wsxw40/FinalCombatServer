package com.pearl.o2o.pojo;


public class PlayerAchievement implements java.io.Serializable{

	private static final long serialVersionUID = -4071149724920506802L;

	private String id;
	private Integer	playerId;
	private Integer achievementId;
	private Integer status;
	private Integer number;
	private int	visible;
	
	private SysAchievement achievement;
	
	public SysAchievement getAchievement() {
		return achievement;
	}
	public void setAchievement(SysAchievement achievement) {
		this.achievement = achievement;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}

	/**
	 * @return the playerId
	 */
	public Integer getPlayerId() {
		return playerId;
	}
	/**
	 * @param playerId the playerId to set
	 */
	public void setPlayerId(Integer playerId) {
		this.playerId = playerId;
	}
	public Integer getAchievementId() {
		return achievementId;
	}
	public void setAchievementId(Integer achievementId) {
		this.achievementId = achievementId;
	}
	public int getVisible() {
		return visible;
	}
	public void setVisible(int visible) {
		this.visible = visible;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
}
