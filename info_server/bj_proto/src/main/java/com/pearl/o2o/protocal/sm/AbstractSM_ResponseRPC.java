/**
 * 
 */
package com.pearl.o2o.protocal.sm;

import org.jboss.netty.buffer.ChannelBuffer;

import com.pearl.o2o.enumuration.ServerMessage;
import com.pearl.o2o.enumuration.StatusCode;

/**
 * @author lifengyang
 * 
 */
public abstract class AbstractSM_ResponseRPC extends SM {
	protected int queueId;
	protected int queueCurrentSize;
	protected int rpcId;
	protected StatusCode statusCode;
	protected byte[] userData;
	protected int userDataLength;

	public AbstractSM_ResponseRPC(int userDataLength) {
		this.userDataLength = userDataLength;
	}

	protected abstract void fillType(ChannelBuffer message);

	protected abstract void fillQueueId(ChannelBuffer message);

	protected abstract void fillResponseData(ChannelBuffer message);

	private void fillUserData(ChannelBuffer message) {
		message.writeBytes(userData);
	}

	protected final void fillDataBody(ChannelBuffer message) {
		fillType(message);
		fillQueueId(message);
		message.writeInt(queueCurrentSize);
		message.writeInt(rpcId);
		message.writeInt(statusCode.ordinal());
		fillUserData(message);
		fillResponseData(message);
	}

	protected abstract void mergeResponse(ChannelBuffer buffer);

	private void mergeUserData(ChannelBuffer buffer) {
		this.userData = new byte[userDataLength];
		buffer.readBytes(userData);
	}

	@Override
	protected final void mergeDatabody(ChannelBuffer buffer) {
		this.type = ServerMessage.values()[buffer.readByte()];
		this.queueId = buffer.readInt();
		this.queueCurrentSize = buffer.readInt();
		this.rpcId = buffer.readInt();
		this.statusCode = StatusCode.values()[buffer.readInt()];
		mergeUserData(buffer);
		mergeResponse(buffer);
	}
}
