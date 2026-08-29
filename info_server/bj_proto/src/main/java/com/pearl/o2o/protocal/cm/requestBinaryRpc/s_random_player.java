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
public class s_random_player extends CM_RequestBinaryRPC {

	private int[] playerIds;

	@Override
	protected void fillParam(ChannelBuffer message) {
		message.writeInt(playerIds.length);
		for (int playerId : playerIds) {
			message.writeInt(playerId);
		}
	}

	@Override
	protected void mergeParam(ChannelBuffer r) {
		int size = r.readInt();
		playerIds = new int[size];
		for (int i = 0; i < size; i++) {
			playerIds[i] = r.readInt();
		}
	}

	@Override
	public String getMessageStringFace() {
		return null;
	}

	public int[] getPlayerIds() {
		return playerIds;
	}

	public void setPlayerIds(int[] playerIds) {
		this.playerIds = playerIds;
	}

	

}
