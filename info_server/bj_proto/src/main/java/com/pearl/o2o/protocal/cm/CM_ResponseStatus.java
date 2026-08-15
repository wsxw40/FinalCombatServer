/**
 * 
 */
package com.pearl.o2o.protocal.cm;

import org.jboss.netty.buffer.ChannelBuffer;

/**
 * @author lifengyang
 *
 */
public class CM_ResponseStatus extends CM {
	private int queueId;
	private int rpcId;
	private String url;
	private byte[] userData;
	
	@Override
	protected void fillDataBody(ChannelBuffer message) {
		message.writeInt(queueId);
		message.writeInt(rpcId);
		fillStringByLengthField(message, url);
		fillByteArrayByLengthField(message, userData);
	}
	@Override
	protected void mergeDatabody(ChannelBuffer message) {
		this.queueId = message.readInt();
		this.rpcId = message.readInt();
		this.url = readStringByLengthField(message);
		this.userData = readByteArrayByLengthField(message);
	}
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
	@Override
	public String getMessageStringFace() {
		return null;
	}
	
}
