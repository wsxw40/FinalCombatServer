package com.pearl.fcw.info.core.network;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class PacketEncoder extends MessageToByteEncoder<Packet> {

    private static final Logger logger = LoggerFactory.getLogger(PacketEncoder.class);

    @Override
    protected void encode(ChannelHandlerContext ctx, Packet msg, ByteBuf out) throws Exception {
        byte[] headerData = ProtostuffUtil.toByteArray(msg.getHeader());
        int headLength = headerData.length;
        if (headLength > Packet.HEADER_MAX_LENGTH) {
            logger.warn("Max header length is {}, current is {}", Packet.HEADER_MAX_LENGTH, headLength);
            throw new IllegalArgumentException();
        }

        int packetLength = headLength;
        if (msg.getBody() != null) {
            packetLength += msg.getBody().readableBytes();
        }

        out.writeInt(packetLength);
        out.writeInt(headLength);
        out.writeBytes(headerData);
        if (msg.getBody() != null) {
            out.writeBytes(msg.getBody());
        }
    }

}
