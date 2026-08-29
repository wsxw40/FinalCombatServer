package com.pearl.fcw.info.core.network;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PacketEncoder extends MessageToByteEncoder<Packet> {

	private static final Logger logger = LoggerFactory
			.getLogger(PacketEncoder.class);

	@Override
	protected void encode(ChannelHandlerContext ctx, Packet msg, ByteBuf out)
			throws Exception {
		if (msg.getResponse() != null) {
			out.writeBytes(msg.response());
		} else if (msg.getBody() != null) {
			out.writeBytes(msg.getBody().array());
		}

		logger.info("Packet encode.request: {}", msg.getRequest());
	}

}
