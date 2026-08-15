package com.pearl.o2o.pojo;

import java.io.Serializable;

public class PlayerAchievement implements Serializable {

	private static final long serialVersionUID = -4071149724920506802L;
	
	private Integer id;
	private Integer	playerId;
	private Integer sysAchievementId;
	
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
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
	/**
	 * @return the sysAchievementId
	 */
	public Integer getSysAchievementId() {
		return sysAchievementId;
	}
	/**
	 * @param sysAchievementId the sysAchievementId to set
	 */
	public void setSysAchievementId(Integer sysAchievementId) {
		this.sysAchievementId = sysAchievementId;
	}
}
