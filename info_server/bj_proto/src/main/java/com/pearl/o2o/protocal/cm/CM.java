/**
 * 
 */
package com.pearl.o2o.protocal.cm;

import org.jboss.netty.buffer.ChannelBuffer;

import com.pearl.o2o.enumuration.ClientMessage;
import com.pearl.o2o.protocal.PJMessage;

/**
 * @author lifengyang
 * 
 */
public abstract class CM extends PJMessage {
	protected ClientMessage type;

	public static CM buildCM(ChannelBuffer message) throws Exception {
		byte type = message.getByte(0);
		return buildCM(message, type);
	}
	public static CM buildCM(ChannelBuffer message,int type) throws Exception {
		CM cm = null;
		ClientMessage clientMessage = ClientMessage.getClientMessage(type);
		switch (clientMessage) {
		case CM_RequestBinaryRPC:
			CM_RequestBinaryRPC.buildCM_RequestBinaryRPC(message);
			break;
		case CM_RequestTextRPC:
		case CM_ResponseStatus:
		case CM_ResponseOnlineInfo:
		case CM_UpdateServerInfo:
		case CM_CancelRPC:
		case CM_SyncRPCQueue:
			cm = clientMessage.getClzz().newInstance();
			cm.mergeFrom(message);
			break;
		}
		return cm;
	}
	public ClientMessage getType() {
		return type;
	}
}
