/**
 * 
 */
package com.pearl.o2o.protocal.sm.responseBinaryRPC;

import java.util.Arrays;

import org.jboss.netty.buffer.ChannelBuffer;

import com.pearl.o2o.protocal.sm.SM_ResponseBinaryRPC;

/**
 * @author lifengyang
 * 完成
 */
public class s_get_unspeaklist extends SM_ResponseBinaryRPC {
	private int[] playerIds;

	public s_get_unspeaklist(int userDataLength) {
		super(userDataLength);
	}

	@Override
	protected final void fillResponseData(ChannelBuffer message) {
	}

	@Override
	protected final void mergeResponse(ChannelBuffer buffer) {
		playerIds = new int[buffer.readInt()];
		for (int i = 0; i < playerIds.length; i++) {
			playerIds[i] = buffer.readInt();
		}
	}

	@Override
	public final String getMessageStringFace() {
		return toString();
	}

	@Override
	public String toString() {
		return "s_get_unspeaklist [playerIds=" + Arrays.toString(playerIds) + ", queueId=" + queueId + ", queueCurrentSize=" + queueCurrentSize + ", rpcId=" + rpcId
				+ ", statusCode=" + statusCode + ", userData=" + Arrays.toString(userData) + ", userDataLength=" + userDataLength + ", type=" + type + ", datalength=" + datalength + "]";
	}

}
