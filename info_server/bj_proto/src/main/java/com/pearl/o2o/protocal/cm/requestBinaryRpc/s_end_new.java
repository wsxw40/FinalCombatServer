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
public class s_end_new extends CM_RequestBinaryRPC {
	protected int playerId;
	protected int entertTime;
	protected int quitTime;
	protected int characterId;
	protected int isClear;

	@Override
	protected final void fillParam(ChannelBuffer message) {
		message.writeInt(playerId);
		message.writeInt(entertTime);
		message.writeInt(quitTime);
		message.writeInt(characterId);
		message.writeInt(isClear);
	}

	@Override
	protected final void mergeParam(ChannelBuffer buffer) {
		playerId = buffer.readInt();
		entertTime = buffer.readInt();
		quitTime = buffer.readInt();
		characterId = buffer.readInt();
		isClear = buffer.readInt();
	}

	@Override
	public String getMessageStringFace() {
		// TODO Auto-generated method stub
		return null;
	}

	public int getPlayerId() {
		return playerId;
	}

	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}

	public int getEntertTime() {
		return entertTime;
	}

	public void setEntertTime(int entertTime) {
		this.entertTime = entertTime;
	}

	public int getQuitTime() {
		return quitTime;
	}

	public void setQuitTime(int quitTime) {
		this.quitTime = quitTime;
	}

	public int getCharacterId() {
		return characterId;
	}

	public void setCharacterId(int characterId) {
		this.characterId = characterId;
	}

	public int getIsClear() {
		return isClear;
	}

	public void setIsClear(int isClear) {
		this.isClear = isClear;
	}

}
