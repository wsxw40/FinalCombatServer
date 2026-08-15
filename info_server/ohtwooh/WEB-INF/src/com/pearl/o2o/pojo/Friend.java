package com.pearl.o2o.pojo;


import java.io.Serializable;


public class Friend implements Serializable {
	private static final long serialVersionUID = -3518175517759453431L;

	private Integer 	userId;
	private Integer 	playerId;
	private Integer 	friendId;
	private String  	isBlackList;
	
	
	//Joined Fields
	private String 		name;
	private Integer 	rank;
	private String 		gender;
	
	private  String team;
	
	//Location
	private int room=0;
	private String channel;
	private String server;
	
	public String getTeam() {
		return team;
	}
	public void setTeam(String team) {
		this.team = team;
	}
	/**
	 * @return the userId
	 */
	public Integer getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
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
	 * @return the friendId
	 */
	public Integer getFriendId() {
		return friendId;
	}
	/**
	 * @param friendId the friendId to set
	 */
	public void setFriendId(Integer friendId) {
		this.friendId = friendId;
	}
	/**
	 * @return the isBlackList
	 */
	public String getIsBlackList() {
		return isBlackList;
	}
	/**
	 * @param isBlackList the isBlackList to set
	 */
	public void setIsBlackList(String isBlackList) {
		this.isBlackList = isBlackList;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the rank
	 */
	public Integer getRank() {
		return rank;
	}
	/**
	 * @param rank the rank to set
	 */
	public void setRank(Integer rank) {
		this.rank = rank;
	}
	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}
	/**
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getRoom() {
		return room;
	}
	public void setRoom(int room) {
		this.room = room;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public String getServer() {
		return server;
	}
	public void setServer(String server) {
		this.server = server;
	}
}
