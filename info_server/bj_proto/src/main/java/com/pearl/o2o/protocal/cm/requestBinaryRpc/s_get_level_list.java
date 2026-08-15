/**
 * 
 */
package com.pearl.o2o.protocal.cm.requestBinaryRpc;

import org.jboss.netty.buffer.ChannelBuffer;

import com.pearl.o2o.protocal.cm.CM_RequestBinaryRPC;

/**
 * @author lifengyang
 *
 */
public class s_get_level_list extends CM_RequestBinaryRPC {

	@Override
	protected void fillParam(ChannelBuffer message) {
	}

	@Override
	protected void mergeParam(ChannelBuffer buffer) {
	}

	@Override
	public String getMessageStringFace() {
		// TODO Auto-generated method stub
		return null;
	}

}
