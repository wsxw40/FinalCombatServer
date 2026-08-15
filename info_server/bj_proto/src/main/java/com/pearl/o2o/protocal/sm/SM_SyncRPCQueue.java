/**
 * 
 */
package com.pearl.o2o.protocal.sm;

import org.jboss.netty.buffer.ChannelBuffer;

/**
 * @author lifengyang
 *
 */
public class SM_SyncRPCQueue extends SM {
	private int queueId;
	private int incrSize;
	private int availSize;
	@Override
	protected void fillDataBody(ChannelBuffer message) {
	}
	@Override
	protected void mergeDatabody(ChannelBuffer message) {
	}
	@Override
	public String getMessageStringFace() {
		return null;
	}
	
}
