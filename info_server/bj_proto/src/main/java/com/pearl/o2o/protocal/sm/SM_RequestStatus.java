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
public class SM_RequestStatus extends SM {
	private int queueId;
	private int queueCurrentSize;
	private int rpcId;
	private String url;
	private byte[] userData;	
	@Override
	protected void fillDataBody(ChannelBuffer message) {
		message.writeInt(queueId);
		message.writeInt(queueCurrentSize);
		message.writeInt(rpcId);
		fillStringByLengthField(message, url);
		fillByteArrayByLengthField(message, userData);
	}
	@Override
	protected void mergeDatabody(ChannelBuffer buffer) {
		this.type = ServerMessage.values()[buffer.readByte()];
		this.queueId = buffer.readInt();
		this.queueCurrentSize = buffer.readInt();
		this.rpcId = buffer.readInt();
		this.url = readStringByLengthField(buffer);
		userData = readByteArrayByLengthField(buffer);
	}
	@Override
	public String getMessageStringFace() {
		// TODO Auto-generated method stub
		return null;
	}

}
