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
public class s_login_activity extends CM_RequestBinaryRPC {

	private int activityId;
	private int[] playerIds;

	@Override
	protected void fillParam(ChannelBuffer message) {
		message.writeInt(activityId);
		message.writeInt(playerIds.length);
		for (int playerId : playerIds) {
			message.writeInt(playerId);
		}
	}

	@Override
	protected void mergeParam(ChannelBuffer r) {
		activityId = r.readInt();
		playerIds = new int[r.readInt()];
		for(int i=0;i<playerIds.length;i++){
			playerIds[i] = r.readInt();
		}
	}

	@Override
	public String getMessageStringFace() {
		return null;
	}

	public int getActivityId() {
		return activityId;
	}

	public void setActivityId(int activityId) {
		this.activityId = activityId;
	}

	public int[] getPlayerIds() {
		return playerIds;
	}

	public void setPlayerIds(int[] playerIds) {
		this.playerIds = playerIds;
	}

	

}
