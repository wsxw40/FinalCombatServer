/**
 * 
 */
package com.pearl.o2o.protocal.sm.responseBinaryRPC;

import org.jboss.netty.buffer.ChannelBuffer;

import com.pearl.o2o.protocal.sm.SM_ResponseBinaryRPC;

/**
 * @author lifengyang
 * 无返回数据
 */
public class s_character_offline extends SM_ResponseBinaryRPC {

	public s_character_offline(int userDataLength) {
		super(userDataLength);
	}

	@Override
	protected final void fillResponseData(ChannelBuffer message) {
	}

	@Override
	protected final void mergeResponse(ChannelBuffer buffer) {
	}

	@Override
	public final String getMessageStringFace() {
		return toString();
	}

	@Override
	public String toString() {
		return "s_character_offline [queueId=" + queueId + ", queueCurrentSize=" + queueCurrentSize + ", rpcId=" + rpcId + ", statusCode=" + statusCode + ", type=" + type + ", datalength=" + datalength + "]";
	}

}
