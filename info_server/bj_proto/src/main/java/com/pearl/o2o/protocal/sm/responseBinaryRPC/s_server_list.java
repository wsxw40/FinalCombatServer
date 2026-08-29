/**
 * 
 */
package com.pearl.o2o.protocal.sm.responseBinaryRPC;

import java.util.Arrays;

import org.jboss.netty.buffer.ChannelBuffer;

import com.pearl.o2o.protocal.pojo.Channel;
import com.pearl.o2o.protocal.pojo.Server;
import com.pearl.o2o.protocal.sm.SM_ResponseBinaryRPC;

/**
 * @author lifengyang
 * 
 */
public class s_server_list extends SM_ResponseBinaryRPC {
	Server[] serverList;

	public s_server_list(int userDataLength) {
		super(userDataLength);
	}

	@Override
	protected final void fillResponseData(ChannelBuffer message) {
	}

	@Override
	protected final void mergeResponse(ChannelBuffer buffer) {
		if (buffer.readable()) {
			int serverListSize = buffer.readInt();
			serverList = new Server[serverListSize];
			for (int i = 0; i < serverListSize; i++) {
				Server server = new Server();
				server.setId(buffer.readInt());
				server.setName(readStringByLengthField(buffer));
				server.setMin(buffer.readInt());
				server.setMax(buffer.readInt());
				server.setMaxOnline(buffer.readInt());
				server.setIsNew(buffer.readByte());
				int channelListSize = buffer.readInt();
				Channel[] channelList = new Channel[channelListSize];
				for (int j = 0; j < channelListSize; j++) {
					Channel channel = new Channel();
					channel.setId(buffer.readInt());
					channel.setName(readStringByLengthField(buffer));
					channel.setMin(buffer.readInt());
					channel.setMax(buffer.readInt());
					channel.setMaxOnline(buffer.readInt());
					channel.setIsTcp((int) buffer.readByte());
					channelList[0] = channel;
				}
				server.setChannelList(channelList);
				serverList[i] = server;
			}
		}
	}

	@Override
	public final String getMessageStringFace() {
		return toString();
	}

	@Override
	public String toString() {
		return "s_server_list [serverList=" + Arrays.toString(serverList) + ", queueId=" + queueId + ", queueCurrentSize=" + queueCurrentSize + ", rpcId=" + rpcId
				+ ", statusCode=" + statusCode + ", userData=" + Arrays.toString(userData) + ", userDataLength=" + userDataLength + ", type=" + type + ", datalength=" + datalength + "]";
	}

}
