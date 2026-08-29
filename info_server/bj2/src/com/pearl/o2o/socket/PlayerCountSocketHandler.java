package com.pearl.o2o.socket;

import java.util.Map;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.o2o.pojo.CharacterOnlineInfo;
import com.pearl.o2o.utils.BinaryUtil;
import com.pearl.o2o.utils.ServiceLocator;

/** one handler each connection */
public class PlayerCountSocketHandler extends SimpleChannelUpstreamHandler {
	private static final Logger log = LoggerFactory.getLogger(PlayerCountSocketHandler.class.getName());

	private Channel channel;

	@Override
	public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) {

	}

	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) {
		ChannelBuffer input = (ChannelBuffer) e.getMessage();
		channel = e.getChannel();

		while (input.readable()) {
			byte receive = input.readByte();
			if (receive == 'b') {
				CharacterOnlineInfo cOnlineInfo;
				try {
					cOnlineInfo = ServiceLocator.soClient.getCharacterOnlineInfo();
				} catch (Exception ee) {
					ee.printStackTrace();
					cOnlineInfo = null;
				}
				StringBuilder sb = new StringBuilder("b|");
				sb.append(null == cOnlineInfo ? null : cOnlineInfo.getOnlineCharacter() + "|");
				if (cOnlineInfo != null) {
					for (Map.Entry<Integer, Map<Integer, Integer>> serverEntry : cOnlineInfo.getChannelClientCount().entrySet()) {
						for (Map.Entry<Integer, Integer> channelEntry : serverEntry.getValue().entrySet()) {
							sb.append(serverEntry.getKey()).append("/").append(channelEntry.getKey()).append("/").append(channelEntry.getValue()).append(";");
						}
					}
				} else {
					sb.append("/0/-1");
				}

				push(sb.toString().getBytes());
				channel.disconnect();
			}
		}
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) {
		// Close the connection when an exception is raised.
		log.warn("Unexpected exception from downstream.", e.getCause());
		channel.close();
	}

	public void push(byte[] output) {
		ChannelBuffer buff = ChannelBuffers.buffer(output.length + 2);
		buff.writeBytes((BinaryUtil.toByta((char) (output.length + 2))));
		buff.writeBytes(output);
		channel.write(buff);
	}

	@Override
	public void channelDisconnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {

	}

	@Override
	public void channelClosed(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
	}

	public Channel getChannel() {
		return channel;
	}
}