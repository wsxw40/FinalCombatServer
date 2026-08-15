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
public class s_login_activity extends SM_ResponseBinaryRPC {

	public s_login_activity(int userDataLength) {
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
		return "s_login_activity [queueId=" + queueId + ", queueCurrentSize=" + queueCurrentSize + ", rpcId=" + rpcId + ", statusCode=" + statusCode + ", type=" + type + ",datalength=" + datalength + "]";
	}

}
