/**
 * 
 */
package com.pearl.o2o.protocal.sm.responseBinaryRPC;

import java.util.Arrays;

import org.jboss.netty.buffer.ChannelBuffer;

import com.pearl.o2o.protocal.pojo.SysActivity;
import com.pearl.o2o.protocal.sm.SM_ResponseBinaryRPC;

/**
 * @author lifengyang
 * 
 */
public class s_sys_config extends SM_ResponseBinaryRPC {
	private byte wallowButton;
	private byte activityTheSwitch;
	private byte activityTargetNum;
	private byte activityValue;
	private SysActivity[] sysActivities;

	public s_sys_config(int userDataLength) {
		super(userDataLength);
	}

	@Override
	protected final void fillResponseData(ChannelBuffer message) {
	}

	@Override
	protected final void mergeResponse(ChannelBuffer buffer) {
		this.wallowButton = buffer.readByte();
		this.activityTheSwitch = buffer.readByte();
		this.activityTargetNum = buffer.readByte();
		this.activityValue = buffer.readByte();
		sysActivities = new SysActivity[buffer.readInt()];
		for (int i = 0; i < sysActivities.length; i++) {
			SysActivity sysActivity = new SysActivity();
			sysActivity.setId(buffer.readInt());
			sysActivity.setStartTime(buffer.readInt());
			sysActivities[i] = sysActivity;
		}
	}

	@Override
	public final String getMessageStringFace() {
		return toString();
	}

	@Override
	public String toString() {
		return "s_sys_config [wallowButton=" + wallowButton + ", activityTheSwitch=" + activityTheSwitch + ", activityTargetNum=" + activityTargetNum + ", activityValue="
				+ activityValue + ", sysActivities=" + Arrays.toString(sysActivities) + ", queueId=" + queueId + ", queueCurrentSize=" + queueCurrentSize + ", rpcId=" + rpcId
				+ ", statusCode=" + statusCode + ", userData=" + Arrays.toString(userData) + ", userDataLength=" + userDataLength + ", type=" + type + ", datalength=" + datalength + "]";
	}

}
