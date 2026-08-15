package com.pde.info.analyser.pojo;

import java.util.Date;

public class ReChargeInfo {
	
	private int playerId;
	private String playerUserName;
	private String playerName;
	private int rmb;
	private int playerRank;
	private int isNewer;   //1 is, 0 isn't
	private Date createDate;
	public int getPlayerId() {
		return playerId;
	}
	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}
	public String getPlayerUserName() {
		return playerUserName;
	}
	public void setPlayerUserName(String playerUserName) {
		this.playerUserName = playerUserName;
	}
	public String getPlayerName() {
		return playerName;
	}
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
	public int getRmb() {
		return rmb;
	}
	public void setRmb(int rmb) {
		this.rmb = rmb;
	}
	public int getPlayerRank() {
		return playerRank;
	}
	public void setPlayerRank(int playerRank) {
		this.playerRank = playerRank;
	}
	public int getIsNewer() {
		return isNewer;
	}
	public void setIsNewer(int isNewer) {
		this.isNewer = isNewer;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	

}
