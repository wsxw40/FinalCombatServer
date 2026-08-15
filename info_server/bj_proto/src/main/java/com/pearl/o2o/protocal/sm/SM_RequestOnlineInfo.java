/**
 * 
 */
package com.pearl.o2o.protocal.sm;

import org.jboss.netty.buffer.ChannelBuffer;

/**
 * @author lifengyang
 *
 */
public class SM_RequestOnlineInfo extends SM {
	private int queueId;
	private int rpcId;
	private String url;
	private byte[] userData;	
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
