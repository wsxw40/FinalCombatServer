/**
 * 
 */
package com.pearl.o2o.protocal.cm;

import org.jboss.netty.buffer.ChannelBuffer;

/**
 * @author lifengyang
 *
 */
public class CM_CancelRPC extends CM {
	private int queueId;
	private int rpcId;
	
	@Override
	protected void fillDataBody(ChannelBuffer message) {
		message.writeInt(this.queueId);
		message.writeInt(rpcId);
	}
	@Override
	protected void mergeDatabody(ChannelBuffer message) {
		this.queueId = message.readInt();
		this.rpcId = message.readInt();
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
	@Override
	public String getMessageStringFace() {
		return toString();
	}
	@Override
	public String toString() {
		return "CM_CancelRPC [queueId=" + queueId + ", rpcId=" + rpcId + ", type=" + type + ", datalength=" + datalength + "]";
	}
	
}
