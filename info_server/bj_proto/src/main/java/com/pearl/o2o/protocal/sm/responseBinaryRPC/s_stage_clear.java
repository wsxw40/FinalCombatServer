/**
 * 
 */
package com.pearl.o2o.protocal.sm.responseBinaryRPC;

import org.jboss.netty.buffer.ChannelBuffer;

import com.pearl.o2o.protocal.sm.SM_ResponseBinaryRPC;

/**
 * @author lifengyang
 * 无返回信息
 */
public class s_stage_clear extends SM_ResponseBinaryRPC {

	public s_stage_clear(int userDataLength) {
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
		return "s_stage_clear [queueId=" + queueId + ", queueCurrentSize=" + queueCurrentSize + ", rpcId=" + rpcId + ", statusCode=" + statusCode + ", type=" + type + ", datalength=" + datalength + "]";
	}

}
