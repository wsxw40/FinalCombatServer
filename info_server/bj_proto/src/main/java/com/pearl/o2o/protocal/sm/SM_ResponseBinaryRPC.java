/**
 * 
 */
package com.pearl.o2o.protocal.sm;

import java.lang.reflect.InvocationTargetException;

import org.jboss.netty.buffer.ChannelBuffer;

import com.pearl.o2o.enumuration.ServerMessage;
import com.pearl.o2o.enumuration.ServerMessage.SM_ResponseBinaryRPCURL;

/**
 * @author lifengyang
 * 
 */
public abstract class SM_ResponseBinaryRPC extends AbstractSM_ResponseRPC {
	public SM_ResponseBinaryRPC(int userDataLength) {
		super(userDataLength);
	}

	public static final ServerMessage TYPE = ServerMessage.SM_ResponseBinaryRPC;
	public static final int QUEUEID = ServerMessage.SM_ResponseBinaryRPC.ordinal();

	public static SM_ResponseBinaryRPC buildSM_ResponseBinaryRPC(ChannelBuffer message, int userDataLength, String url) throws IllegalArgumentException, SecurityException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		SM_ResponseBinaryRPC sm_ResponseBinaryRPC = SM_ResponseBinaryRPCURL.getSM_ResponseBinaryRPCClass(url).getConstructor(int.class).newInstance(userDataLength);
		sm_ResponseBinaryRPC.mergeFrom(message);
		return sm_ResponseBinaryRPC;
	}
	@Override
	protected final void fillType(ChannelBuffer message) {
		message.writeByte(TYPE.ordinal());
	}

	@Override
	protected final void fillQueueId(ChannelBuffer message) {
		message.writeInt(QUEUEID);
	}

	@Override
	protected abstract void fillResponseData(ChannelBuffer message);

	@Override
	protected abstract void mergeResponse(ChannelBuffer buffer);
}
