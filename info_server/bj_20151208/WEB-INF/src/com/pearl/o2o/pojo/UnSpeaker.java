package com.pearl.o2o.pojo;

import java.io.Serializable;
import java.util.Date;


public class UnSpeaker implements Serializable {

	private static final long serialVersionUID = -5344911783412618935L;
	
	private Integer id;
	private int		playerId;
	private Date    createTime;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public int getPlayerId() {
		return playerId;
	}
	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}
