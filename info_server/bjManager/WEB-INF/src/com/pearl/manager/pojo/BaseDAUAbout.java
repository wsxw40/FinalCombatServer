package com.pearl.manager.pojo;

import java.io.Serializable;

public class BaseDAUAbout implements Serializable {
	
	private static final long serialVersionUID = -6439780818257643674L;
	private int playerId;
	private int totalRmb;
	public int getPlayerId() {
		return playerId;
	}
	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}
	public int getTotalRmb() {
		return totalRmb;
	}
	public void setTotalRmb(int totalRmb) {
		this.totalRmb = totalRmb;
	}
	
	
}
