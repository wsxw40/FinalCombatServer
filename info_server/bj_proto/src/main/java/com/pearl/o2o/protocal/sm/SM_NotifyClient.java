/**
 * 
 */
package com.pearl.o2o.protocal.sm;

import org.jboss.netty.buffer.ChannelBuffer;

/**
 * @author lifengyang
 *
 */
public class SM_NotifyClient extends SM {
	private String receiver;
	private String msg;
	
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
