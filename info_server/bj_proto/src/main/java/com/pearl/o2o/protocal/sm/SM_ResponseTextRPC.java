/**
 * 
 */
package com.pearl.o2o.protocal.sm;

import org.jboss.netty.buffer.ChannelBuffer;

import com.pearl.o2o.enumuration.ServerMessage;

/**
 * @author lifengyang
 * 
 */
public class SM_ResponseTextRPC extends AbstractSM_ResponseRPC {
	public SM_ResponseTextRPC(int userDataLength) {
		super(userDataLength);
	}

	public static final ServerMessage TYPE = ServerMessage.SM_ResponseTextRPC;
	public static final int QUEUEID = ServerMessage.SM_ResponseTextRPC.ordinal();

	private String response;

	@Override
	protected void fillType(ChannelBuffer message) {
		message.writeByte(TYPE.ordinal());
	}

	@Override
	protected void fillQueueId(ChannelBuffer message) {
		message.writeInt(QUEUEID);
	}

	@Override
	protected void fillResponseData(ChannelBuffer message) {
		message.writeBytes(DEFAULT_CHARSET.encode(response));
	}

	@Override
	protected void mergeResponse(ChannelBuffer buffer) {
		this.response = readStringToEnd(buffer);
	}

	@Override
	public String getMessageStringFace() {
		String typeEnum = type.name();
		String statuscodeEnum = statusCode.name();
		return "\ndatalength=" + datalength + "\ntype=" + typeEnum + "\nqueueId=" + queueId + "\nqueueCurrentSize=" + queueCurrentSize + "\nrpcId=" + rpcId + "\nstatuscode="
				+ statuscodeEnum + "\nresponse:\n=======================================================\n" + response;
	}

}
