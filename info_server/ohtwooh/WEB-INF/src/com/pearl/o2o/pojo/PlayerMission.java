package com.pearl.o2o.pojo;

import java.io.Serializable;

public class PlayerMission implements Serializable {

	private static final long serialVersionUID = 2044601984128880825L;
	
	private Integer id;
	private Integer	playerId;
	private Integer sysMissionId;
	
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
	 * @return the sysMissionId
	 */
	public Integer getSysMissionId() {
		return sysMissionId;
	}
	/**
	 * @param sysMissionId the sysMissionId to set
	 */
	public void setSysMissionId(Integer sysMissionId) {
		this.sysMissionId = sysMissionId;
	}
}
