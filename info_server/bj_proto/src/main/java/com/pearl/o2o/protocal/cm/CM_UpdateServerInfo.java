/**
 * 
 */
package com.pearl.o2o.protocal.cm;

import org.jboss.netty.buffer.ChannelBuffer;

/**
 * @author lifengyang
 *
 */
public class CM_UpdateServerInfo extends CM {
	private int queueId;
	private int rpcId;
	private String url;
	private byte[] userData;
	private String useLessStr;
	private short port;
	
	@Override
	protected void fillDataBody(ChannelBuffer message) {
	}
	@Override
	protected void mergeDatabody(ChannelBuffer message) {
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
	public String getUseLessStr() {
		return useLessStr;
	}
	public void setUseLessStr(String useLessStr) {
		this.useLessStr = useLessStr;
	}
	public short getPort() {
		return port;
	}
	public void setPort(short port) {
		this.port = port;
	}
	@Override
	public String getMessageStringFace() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
