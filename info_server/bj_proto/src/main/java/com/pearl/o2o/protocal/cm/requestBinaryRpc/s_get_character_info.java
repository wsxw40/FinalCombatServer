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
public class s_get_character_info extends CM_RequestBinaryRPC {

	protected int uId;
	protected int playerId;
	private int levelId;

	@Override
	protected void fillParam(ChannelBuffer message) {
		message.writeInt(uId);
		message.writeInt(playerId);
		message.writeInt(levelId);
	}

	@Override
	protected void mergeParam(ChannelBuffer r) {
		uId = r.readInt();
		playerId = r.readInt();
		levelId = r.readInt();
	}

	@Override
	public String getMessageStringFace() {
		return null;
	}

	public int getuId() {
		return uId;
	}

	public void setuId(int uId) {
		this.uId = uId;
	}

	public int getPlayerId() {
		return playerId;
	}

	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}

	public int getLevelId() {
		return levelId;
	}

	public void setLevelId(int levelId) {
		this.levelId = levelId;
	}

}
