/**
 * 
 */
package com.pearl.o2o.protocal.cm;

import org.jboss.netty.buffer.ChannelBuffer;

/**
 * @author lifengyang
 *
 */
public class CM_SyncRPCQueue extends CM {
	private int queueId;
	private int queueSize;
	
	@Override
	protected void fillDataBody(ChannelBuffer message) {
		message.writeInt(this.queueId);
		message.writeInt(this.queueSize);
	}
	@Override
	protected void mergeDatabody(ChannelBuffer message) {
		this.queueId = message.readInt();
		this.queueSize = message.readInt();
	}
	public int getQueueId() {
		return queueId;
	}
	public void setQueueId(int queueId) {
		this.queueId = queueId;
	}
	public int getQueueSize() {
		return queueSize;
	}
	public void setQueueSize(int queueSize) {
		this.queueSize = queueSize;
	}
	@Override
	public String getMessageStringFace() {
		return null;
	}
	
}
