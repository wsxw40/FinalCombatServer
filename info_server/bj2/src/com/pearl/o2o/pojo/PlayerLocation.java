package com.pearl.o2o.pojo;

import java.io.Serializable;
import java.util.Date;

public class PlayerLocation implements Serializable {
	private static final long serialVersionUID = -7745805719966098350L;
	
	private int id;
	private int playerId;
	private int channel=0;
	private int server=0;
	private int room;
	private String serverName;
	private String roomName;
	private Date loginTime;
	public PlayerLocation(int playerId,int channel,int room,String serverName,String roomName) {

		this.playerId = playerId;
		this.channel = channel;
		this.room = room;
		this.loginTime = new Date();
	}
	public PlayerLocation(int playerId,int channel,int room) {

		this.playerId = playerId;
		this.channel = channel;
		this.room = room;
		this.loginTime = new Date();
	}
	public int getPlayerId() {
		return playerId;
	}
	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}
	public int getChannel() {
		return channel;
	}
	public void setChannel(int channel) {
		this.channel = channel;
	}
	public int getRoom() {
		return room;
	}
	public void setRoom(int room) {
		this.room = room;
	}
	public String getServerName() {
		return serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	public int getServer() {
		return server;
	}
	public void setServer(int server) {
		this.server = server;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

	
}
