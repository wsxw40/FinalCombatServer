/**
 * 
 */
package com.pearl.o2o.protocal.cm;

import org.jboss.netty.buffer.ChannelBuffer;

import com.pearl.o2o.enumuration.ClientMessage;

/**
 * @author lifengyang
 * 
 */
public abstract class AbstractCM_RequestRPC extends CM {
	protected int queueId;
	protected int rpcId;
	protected String url;
	protected byte[] userData;

	protected abstract void fillType(ChannelBuffer message);

	protected abstract void fillQueueId(ChannelBuffer message);

	private ChannelBuffer fillUserData(ChannelBuffer message) {
		message.writeInt(userData.length);
		message.writeBytes(userData);
		return message;
	}

	protected abstract void fillParam(ChannelBuffer message);

	@Override
	protected final void fillDataBody(ChannelBuffer message) {
		fillType(message);
		fillQueueId(message);
		message.writeInt(rpcId);
		fillStringByLengthField(message, url);
		fillUserData(message);
		fillParam(message);
	}

	private void mergeUserData(ChannelBuffer buffer) {
		this.userData = new byte[buffer.readInt()];
		buffer.readBytes(userData);
	}

	@Override
	protected final void mergeDatabody(ChannelBuffer buffer) {
		this.type = ClientMessage.values()[buffer.readUnsignedByte()];
		this.queueId = buffer.readInt();
		this.rpcId = buffer.readInt();
		this.url = readStringByLengthField(buffer);
		this.mergeUserData(buffer);
		mergeParam(buffer);
	}

	protected abstract void mergeParam(ChannelBuffer buffer);

	public int getQueueId() {
		return queueId;
	}

	public void setQueueId(int queueId) {
		this.queueId = queueId;
	}

	public int getRpcId() {
		return rpcId;
	}

	public void setRpcId(int rpcId) {
		this.rpcId = rpcId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public byte[] getUserData() {
		return userData;
	}

	public void setUserData(byte[] userData) {
		this.userData = userData;
	}

}
