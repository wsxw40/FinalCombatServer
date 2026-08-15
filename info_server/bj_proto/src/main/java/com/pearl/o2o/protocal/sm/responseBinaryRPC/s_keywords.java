/**
 * 
 */
package com.pearl.o2o.protocal.sm.responseBinaryRPC;

import java.util.Arrays;

import org.jboss.netty.buffer.ChannelBuffer;

import com.pearl.o2o.protocal.sm.SM_ResponseBinaryRPC;

/**
 * @author lifengyang 完成
 */
public class s_keywords extends SM_ResponseBinaryRPC {
	private String[] keywords;

	public s_keywords(int userDataLength) {
		super(userDataLength);
	}

	@Override
	protected final void fillResponseData(ChannelBuffer message) {
	}

	@Override
	protected final void mergeResponse(ChannelBuffer buffer) {
		int keywordsSize = buffer.readInt();
		keywords = new String[keywordsSize];
		for (int i = 0; i < keywordsSize; i++) {
			keywords[i] = readStringByLengthField(buffer);
		}
	}

	@Override
	public final String getMessageStringFace() {
		return toString();
	}

	@Override
	public String toString() {
		return "s_keywords [keywords=" + Arrays.toString(keywords) + ", queueId=" + queueId + ", queueCurrentSize=" + queueCurrentSize + ", rpcId=" + rpcId + ", statusCode="
				+ statusCode + ", type=" + type + ", datalength=" + datalength + "]";
	}

}
