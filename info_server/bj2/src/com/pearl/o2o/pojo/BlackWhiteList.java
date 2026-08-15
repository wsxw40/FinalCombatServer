package com.pearl.o2o.pojo;

import java.io.Serializable;

public class BlackWhiteList implements Serializable{
	
	private static final long serialVersionUID = 1l;
	
	public BlackWhiteList(){
		super();
	}
	
	private int id;
	
	private String userId;
	
	private int playerId;
	
	private String playerName;
	
	private String isBlack;
	
	//not in database,flag is changed
	private int isChange;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getPlayerId() {
		return playerId;
	}

	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public String getIsBlack() {
		return isBlack;
	}

	public void setIsBlack(String isBlack) {
		this.isBlack = isBlack;
	}

	public int getIsChange() {
		return isChange;
	}

	public void setIsChange(int isChange) {
		this.isChange = isChange;
	}
	
	
}
