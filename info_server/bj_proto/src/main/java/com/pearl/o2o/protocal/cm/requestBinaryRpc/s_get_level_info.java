/**
 * 
 */
package com.pearl.o2o.protocal.cm.requestBinaryRpc;

import org.jboss.netty.buffer.ChannelBuffer;

import com.pearl.o2o.protocal.cm.CM_RequestBinaryRPC;

/**
 * @author lifengyang
 * 
 */
public class s_get_level_info extends CM_RequestBinaryRPC {

	protected int lid;
	protected int roomId;
	protected int playerId;
	protected int serverId;
	protected int channelId;
	protected int[] team0;
	protected int[] team1;
	protected int roomCreate;
	protected int gameStart;
	protected int m_type;

	@Override
	protected void fillParam(ChannelBuffer message) {
		message.writeInt(lid);
		message.writeInt(roomId);
		message.writeInt(playerId);
		message.writeInt(serverId);
		message.writeInt(channelId);
		message.writeInt(team0.length);
		for (int id : team0) {
			message.writeInt(id);
		}
		message.writeInt(team1.length);
		for (int id : team1) {
			message.writeInt(id);
		}
		message.writeInt(roomCreate);
		message.writeInt(gameStart);
		message.writeInt(gameStart);
	}

	@Override
	protected void mergeParam(ChannelBuffer in) {
		lid = in.readInt();
		roomId = in.readInt();
		playerId = in.readInt();
		serverId = in.readInt();
		channelId = in.readInt();
		team0 = new int[in.readInt()];
		for (int i = 0; i < team0.length; i++) {
			team0[i] = in.readInt();
		}
		team1 = new int[in.readInt()];
		for (int i = 0; i < team1.length; i++) {
			team1[i] = in.readInt();
		}
		roomCreate = in.readInt();
		gameStart = in.readInt();
		m_type = in.readByte();
	}

	@Override
	public String getMessageStringFace() {
		return null;
	}

	public int getLid() {
		return lid;
	}

	public void setLid(int lid) {
		this.lid = lid;
	}

	public int getRoomId() {
		return roomId;
	}

	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}

	public int getPlayerId() {
		return playerId;
	}

	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}

	public int getServerId() {
		return serverId;
	}

	public void setServerId(int serverId) {
		this.serverId = serverId;
	}

	public int getChannelId() {
		return channelId;
	}

	public void setChannelId(int channelId) {
		this.channelId = channelId;
	}

	public int[] getTeam0() {
		return team0;
	}

	public void setTeam0(int[] team0) {
		this.team0 = team0;
	}

	public int[] getTeam1() {
		return team1;
	}

	public void setTeam1(int[] team1) {
		this.team1 = team1;
	}

	public int getRoomCreate() {
		return roomCreate;
	}

	public void setRoomCreate(int roomCreate) {
		this.roomCreate = roomCreate;
	}

	public int getGameStart() {
		return gameStart;
	}

	public void setGameStart(int gameStart) {
		this.gameStart = gameStart;
	}

	public int getM_type() {
		return m_type;
	}

	public void setM_type(int m_type) {
		this.m_type = m_type;
	}

	

}
