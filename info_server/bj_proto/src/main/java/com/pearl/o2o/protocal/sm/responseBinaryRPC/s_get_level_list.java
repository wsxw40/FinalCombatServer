/**
 * 
 */
package com.pearl.o2o.protocal.sm.responseBinaryRPC;

import java.util.Arrays;

import org.jboss.netty.buffer.ChannelBuffer;

import com.pearl.o2o.protocal.pojo.LevelInfo;
import com.pearl.o2o.protocal.sm.SM_ResponseBinaryRPC;

/**
 * @author lifengyang
 * 完成
 */
public class s_get_level_list extends SM_ResponseBinaryRPC {
	LevelInfo[] LevelInfos;
	public s_get_level_list(int userDataLength) {
		super(userDataLength);
	}

	@Override
	protected final void fillResponseData(ChannelBuffer message) {
	}

	@Override
	protected final void mergeResponse(ChannelBuffer buffer) {
		int LevelInfosSize = buffer.readInt();
		LevelInfos = new LevelInfo[LevelInfosSize];
		for (int i = 0; i < LevelInfosSize; i++) {
			LevelInfo levelInfo = new LevelInfo();
			levelInfo.setId(buffer.readInt());
			levelInfo.setName(readStringByLengthField(buffer));
			levelInfo.setType(buffer.readByte());
			levelInfo.setDisplayName(readStringByLengthField(buffer));
			levelInfo.setDescription(readStringByLengthField(buffer));
			levelInfo.setIsVip(buffer.readByte());
			LevelInfos[i] = levelInfo;
		}
	}

	@Override
	public final String getMessageStringFace() {
		return toString();
	}

	@Override
	public String toString() {
		return "s_get_level_list [LevelInfos=" + Arrays.toString(LevelInfos) + ", queueId=" + queueId + ", queueCurrentSize=" + queueCurrentSize + ", rpcId=" + rpcId
				+ ", statusCode=" + statusCode + ", userData=" + Arrays.toString(userData) + ", userDataLength=" + userDataLength + ", type=" + type + ", datalength=" + datalength + "]";
	}

}
