/**
 * 
 */
package com.pearl.o2o.protocal.sm;

import org.jboss.netty.buffer.ChannelBuffer;

import com.pearl.o2o.enumuration.ServerMessage;
import com.pearl.o2o.protocal.PJMessage;

/**
 * @author lifengyang
 * 
 */
public abstract class SM extends PJMessage {
	protected ServerMessage type;

	public static SM buildSM(ChannelBuffer message, int userDataLength, String url) throws Exception {
		byte type = message.getByte(0);
		return buildSM(message, type, userDataLength, url);
	}

	public static SM buildSM(ChannelBuffer message, int type, int userDataLength, String url) throws Exception {
		SM sm = null;
		ServerMessage clientMessage = ServerMessage.getClientMessage(type);
		switch (clientMessage) {
		case SM_ResponseTextRPC:
			sm = new SM_ResponseTextRPC(userDataLength);
			sm.mergeDatabody(message);
			break;
		case SM_ResponseBinaryRPC:
			sm = SM_ResponseBinaryRPC.buildSM_ResponseBinaryRPC(message, userDataLength, url);
			break;
		default:
			sm = clientMessage.getClzz().newInstance();
			sm.mergeDatabody(message);
			break;
		}
		return sm;
	}

}
