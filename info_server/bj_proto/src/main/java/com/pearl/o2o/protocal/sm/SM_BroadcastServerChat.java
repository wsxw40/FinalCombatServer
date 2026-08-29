/**
 * 
 */
package com.pearl.o2o.protocal.sm;

import org.jboss.netty.buffer.ChannelBuffer;

/**
 * @author lifengyang
 *
 */
public class SM_BroadcastServerChat extends SM {
	private int serverId;
	private String to;
	private String name;
	private String msg;
	
	@Override
	protected void fillDataBody(ChannelBuffer message) {
	}
	@Override
	protected void mergeDatabody(ChannelBuffer message) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public String getMessageStringFace() {
		// TODO Auto-generated method stub
		return null;
	}
}
