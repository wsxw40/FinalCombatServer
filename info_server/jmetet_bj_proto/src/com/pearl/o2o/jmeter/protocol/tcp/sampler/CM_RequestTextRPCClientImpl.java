package com.pearl.o2o.jmeter.protocol.tcp.sampler;

import org.jboss.netty.buffer.ChannelBuffer;

import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.pearl.o2o.enumuration.ServerMessage;
import com.pearl.o2o.protocal.cm.CM;
import com.pearl.o2o.protocal.cm.CM_RequestTextRPC;
import com.pearl.o2o.protocal.sm.SM;
import com.pearl.o2o.protocal.sm.SM_ResponseTextRPC;

/**
 * 
 */

/**
 * @author lifengyang
 * 
 */
public class CM_RequestTextRPCClientImpl extends PJRPCMessageClientImpl {
	private static final Gson gson = new Gson();
	private static final byte[] userdata = new byte[10];

	@Override
	protected CM converToCM(String str) {
		CM_RequestTextRPC message = gson.fromJson(str, CM_RequestTextRPC.class);
		message.setRpcId(generateRpcId());
		message.setUserData(userdata);
		return message;
	}

	@Override
	protected SM buildSM(ChannelBuffer channelBuffer) {
		SM_ResponseTextRPC sm_ResponseTextRPC = new SM_ResponseTextRPC(userdata.length);
		sm_ResponseTextRPC.mergeFrom(channelBuffer);
		return sm_ResponseTextRPC;
	}


	@Override
	protected String getStringFace(SM sm) {
		return sm.getMessageStringFace();
	}
	
	public static void main(String[] args) {
		String string = "{ \"url\":\"c_get_growth_list\", \"paramMap\":{ \"pid\":\"gfhg\" } }";
		String asString = new JsonParser().parse(string).getAsJsonObject().get("url").getAsString().trim();
		System.out.println(asString.trim());
	}

	@Override
	protected int getType() {
		return ServerMessage.SM_ResponseTextRPC.ordinal();
	}
}
