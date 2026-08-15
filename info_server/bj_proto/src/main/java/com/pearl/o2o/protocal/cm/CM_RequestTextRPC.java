/**
 * 
 */
package com.pearl.o2o.protocal.cm;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.jboss.netty.buffer.ChannelBuffer;

import com.pearl.o2o.enumuration.ClientMessage;

/**
 * @author lifengyang
 * 
 */
public class CM_RequestTextRPC extends AbstractCM_RequestRPC {
	public static final ClientMessage TYPE = ClientMessage.CM_RequestTextRPC;
	private static final int QUEUEID = ClientMessage.CM_RequestTextRPC.ordinal();
	private Map<String, String> paramMap;

	@Override
	protected void fillType(ChannelBuffer message) {
		message.writeByte(TYPE.ordinal());
	}

	@Override
	protected void fillQueueId(ChannelBuffer message) {
		message.writeInt(QUEUEID);
	}

	@Override
	protected void fillParam(ChannelBuffer message) {
		for (Entry<String, String> entry : paramMap.entrySet()) {
			fillStringByLengthField(message, entry.getKey());
			fillStringByLengthField(message, entry.getValue());
		}
	}

	@Override
	protected void mergeParam(ChannelBuffer buffer) {
		paramMap = new HashMap<String, String>();
		while (buffer.readable()) {
			paramMap.put(readStringByLengthField(buffer), readStringByLengthField(buffer));
		}
	}

	@Override
	public String getMessageStringFace() {
		return null;
	}

	public int getRpcId() {
		return rpcId;
	}

	public void setRpcId(int rpcId) {
		this.rpcId = rpcId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Map<String, String> getParamMap() {
		return paramMap;
	}

	public void setParamMap(Map<String, String> paramMap) {
		this.paramMap = paramMap;
	}

	public int getQueueId() {
		return queueId;
	}

	public byte[] getUserData() {
		return userData;
	}

	public void setUserData(byte[] userData) {
		this.userData = userData;
	}
}