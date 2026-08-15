package com.pearl.o2o.jmeter.protocol.tcp.sampler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.jmeter.protocol.tcp.sampler.ReadException;
import org.jboss.netty.buffer.ChannelBuffer;

import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.pearl.o2o.enumuration.ClientMessage.CM_RequestBinaryRPCURL;
import com.pearl.o2o.enumuration.ServerMessage;
import com.pearl.o2o.protocal.cm.CM;
import com.pearl.o2o.protocal.cm.CM_RequestBinaryRPC;
import com.pearl.o2o.protocal.sm.SM;
import com.pearl.o2o.protocal.sm.SM_ResponseBinaryRPC;

/**
 * 
 */

/**
 * @author lifengyang
 * 
 */
public class CM_RequestBinaryRPCClientImpl extends PJRPCMessageClientImpl {
	private static final Gson gson = new Gson();
	private static final byte[] userdata = new byte[10];
	private String url;

	@Override
	protected CM converToCM(String str) {
		url = new JsonParser().parse(str).getAsJsonObject().get("url").getAsString().trim();
		Class<? extends CM_RequestBinaryRPC> clzz = CM_RequestBinaryRPCURL.getCM_RequestBinaryRPCClass(url);
		CM_RequestBinaryRPC message = gson.fromJson(str, clzz);
		message.setRpcId(generateRpcId());
		message.setUserData(userdata);
		return message;
	}

	@Override
	protected SM buildSM(ChannelBuffer channelBuffer) throws ReadException {
			try {
				return SM_ResponseBinaryRPC.buildSM_ResponseBinaryRPC(channelBuffer , userdata.length, url);
			} catch (Exception e) {
				throw new ReadException("", e, "CM_RequestBinaryRPCClientImpl.buildSM error!当前不上报数据解析错误，如有需要使用测试类进行调试！李峰阳！");
			}
	}

	@Override
	protected String getStringFace(SM sm) {
		return sm.getMessageStringFace();
	}

	@Override
	protected int getType() {
		return ServerMessage.SM_ResponseBinaryRPC.ordinal();
	}
}
