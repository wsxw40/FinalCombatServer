/**
 * 
 */
package com.pearl.o2o.protocal.sm;

import org.jboss.netty.buffer.ChannelBuffer;

/**
 * @author lifengyang
 *
 */
public class SM_SendChat extends SM {
	private String receiver;
	private String to;
	private String name;
	private String msg;
	
	@Override
	protected void fillDataBody(ChannelBuffer message) {
		fillStringByLengthField(message, receiver);
		fillStringByLengthField(message, to);
		fillStringByLengthField(message, name);
		fillStringByLengthField(message, msg);
	}
	@Override
	protected void mergeDatabody(ChannelBuffer message) {
		this.receiver = readStringByLengthField(message);
		this.to = readStringByLengthField(message);
		this.name = readStringByLengthField(message);
		this.msg = readStringByLengthField(message);
	}
	@Override
	public String getMessageStringFace() {
		return null;
	}
}
