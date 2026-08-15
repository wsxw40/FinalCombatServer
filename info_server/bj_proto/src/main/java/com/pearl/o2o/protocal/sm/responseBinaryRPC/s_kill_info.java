/**
 * 
 */
package com.pearl.o2o.protocal.sm.responseBinaryRPC;

import java.util.Arrays;

import org.jboss.netty.buffer.ChannelBuffer;

import com.pearl.o2o.protocal.sm.SM_ResponseBinaryRPC;

/**
 * @author lifengyang
 * 无返回数据
 */
public class s_kill_info extends SM_ResponseBinaryRPC {

	public s_kill_info(int userDataLength) {
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
		return "s_kill_info [queueId=" + queueId + ", queueCurrentSize=" + queueCurrentSize + ", rpcId=" + rpcId + ", statusCode=" + statusCode + ", userData="
				+ Arrays.toString(userData) + ", userDataLength=" + userDataLength + ", type=" + type + ", datalength=" + datalength
				+ ", getMessageStringFace()=" + getMessageStringFace() + "]";
	}

}
