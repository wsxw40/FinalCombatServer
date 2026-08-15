/**
 * 
 */
package com.pearl.o2o.protocal.sm;

import org.jboss.netty.buffer.ChannelBuffer;


/**
 * @author lifengyang
 *
 */
public class SM_UpdateCharacterInfo extends SM {
	
	private String playerName;
	private int playerId; 
	private int exp; 
	private byte isVip; 
	
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
