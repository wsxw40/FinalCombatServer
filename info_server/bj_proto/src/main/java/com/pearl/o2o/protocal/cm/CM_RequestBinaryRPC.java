/**
 * 
 */
package com.pearl.o2o.protocal.cm;

import org.jboss.netty.buffer.ChannelBuffer;

import com.pearl.o2o.enumuration.ClientMessage;
import com.pearl.o2o.enumuration.ClientMessage.CM_RequestBinaryRPCURL;

/**
 * @author lifengyang
 * 
 */
public abstract class CM_RequestBinaryRPC extends AbstractCM_RequestRPC {
	public static final ClientMessage TYPE = ClientMessage.CM_RequestBinaryRPC;
	public static final int QUEUEID = ClientMessage.CM_RequestBinaryRPC.ordinal();

	public static CM_RequestBinaryRPC buildCM_RequestBinaryRPC(ChannelBuffer message) throws Exception {
		int length = message.getInt(9);
		String url = message.toString(17, length, DEFAULT_CHARSET);
		return buildCM_RequestBinaryRPC(message, url);
	}
	public static CM_RequestBinaryRPC buildCM_RequestBinaryRPC(ChannelBuffer message,String url) throws Exception {
		 CM_RequestBinaryRPC cm_RequestBinaryRPC = CM_RequestBinaryRPCURL.getCM_RequestBinaryRPCClass(url).newInstance();
		 cm_RequestBinaryRPC.mergeFrom(message);
		 return cm_RequestBinaryRPC;
	}
	
	@Override
	protected final void fillType(ChannelBuffer message) {
		message.writeByte(TYPE.ordinal());
	}

	@Override
	protected final void fillQueueId(ChannelBuffer message) {
		message.writeInt(QUEUEID);
	}
}
