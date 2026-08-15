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
public class s_character_offline extends CM_RequestBinaryRPC {

	private int userId;
	private int playerId;

	@Override
	protected void fillParam(ChannelBuffer message) {
		message.writeInt(userId);
		message.writeInt(playerId);
	}

	@Override
	protected void mergeParam(ChannelBuffer r) {
		userId = r.readInt();
		playerId = r.readInt();
	}

	@Override
	public String getMessageStringFace() {
		return null;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getPlayerId() {
		return playerId;
	}

	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}

}
