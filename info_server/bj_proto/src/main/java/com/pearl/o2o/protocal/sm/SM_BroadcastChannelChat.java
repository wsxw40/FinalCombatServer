/**
 * 
 */
package com.pearl.o2o.protocal.sm;

import org.jboss.netty.buffer.ChannelBuffer;

/**
 * @author lifengyang
 *
 */
public class SM_BroadcastChannelChat extends SM {
	private int serverId;
	private int channelId;
	private String to;
	private String name;
	private String msg;
	@Override
	protected void fillDataBody(ChannelBuffer message) {
		message.writeInt(serverId);
		message.writeInt(channelId);
		fillStringByLengthField(message, to);
		fillStringByLengthField(message, name);
		fillStringByLengthField(message, msg);
	}
	@Override
	protected void mergeDatabody(ChannelBuffer message) {
		this.serverId = message.readInt();
		this.channelId = message.readInt();
		this.to = readStringByLengthField(message);
		this.name = readStringByLengthField(message);
		this.msg = readStringByLengthField(message);
	}
	@Override
	public String getMessageStringFace() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
