/**
 * 
 */
package com.pearl.o2o.protocal.sm.responseBinaryRPC;

import java.util.Arrays;

import org.jboss.netty.buffer.ChannelBuffer;

import com.pearl.o2o.protocal.pojo.SysNotice;
import com.pearl.o2o.protocal.sm.SM_ResponseBinaryRPC;

/**
 * @author lifengyang
 */
public class s_notice_list extends SM_ResponseBinaryRPC {
	public s_notice_list(int userDataLength) {
		super(userDataLength);
	}

	private SysNotice[] sysNoticeList;

	@Override
	protected final void fillResponseData(ChannelBuffer message) {
	}

	@Override
	protected final void mergeResponse(ChannelBuffer buffer) {
		int sysNoticeListSize = buffer.readInt();
		sysNoticeList = new SysNotice[sysNoticeListSize];
		for (int i = 0; i < sysNoticeListSize; i++) {
			SysNotice sysNotice = new SysNotice();
			sysNotice.setId(buffer.readInt());
			sysNotice.setType((int) buffer.readByte());
			sysNotice.setContent(readStringByLengthField(buffer));
			if (sysNotice.getType() == 2) {
				sysNotice.setStartTime(buffer.readInt());
				sysNotice.setEndTime(buffer.readInt());
				sysNotice.setNoticeTime(buffer.readInt());
			} else if (sysNotice.getType() == 1) {
				sysNotice.setStartTime(buffer.readInt());
			}
			sysNoticeList[i] = sysNotice;
		}
	}

	@Override
	public final String getMessageStringFace() {
		return toString();
	}

	@Override
	public String toString() {
		return "s_notice_list [sysNoticeList=" + Arrays.toString(sysNoticeList) + ", queueId=" + queueId + ", queueCurrentSize=" + queueCurrentSize + ", rpcId=" + rpcId
				+ ", statusCode=" + statusCode + ", type=" + type + ", datalength=" + datalength + "]";
	}

}
