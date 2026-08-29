/**
 * 
 */
package com.pearl.o2o.protocal.sm.responseBinaryRPC;

import org.jboss.netty.buffer.ChannelBuffer;

import com.pearl.o2o.protocal.sm.SM_ResponseBinaryRPC;

/**
 * @author lifengyang 未完成
 */
public class s_get_level_info extends SM_ResponseBinaryRPC {
	int levelAndModeId;
	byte levelAndModeType;
	String levelAndModeName;
	byte levelAndModeIsSelf;
	float levelAndModeLevelHorizon;
	float levelAndModeTargetSpeed;
	int VehicleInfogetAddBlood;
	float vectorX;
	float vectorY;
	float vectorZ;

	public s_get_level_info(int userDataLength) {
		super(userDataLength);
	}

	@Override
	protected final void fillResponseData(ChannelBuffer message) {
	}

	@Override
	protected final void mergeResponse(ChannelBuffer buffer) {
		levelAndModeId = buffer.readInt();
		if (levelAndModeId != 0) {

		}
	}

	@Override
	public final String getMessageStringFace() {
		return toString();
	}

	@Override
	public String toString() {
		return "s_get_level_info [queueId=" + queueId + ", queueCurrentSize=" + queueCurrentSize + ", rpcId=" + rpcId + ", statusCode=" + statusCode + ", type=" + type
				+ ", datalength=" + datalength + "]";
	}

}
