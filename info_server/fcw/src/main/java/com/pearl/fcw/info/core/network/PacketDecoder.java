package com.pearl.fcw.info.core.network;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

public class PacketDecoder extends ByteToMessageDecoder {

    private static final Logger logger = LoggerFactory.getLogger(PacketDecoder.class);

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        if (in.readableBytes() < Packet.MIN_PACK_SIZE) {
            return;
        }

        in.markReaderIndex();

        int packetLength = in.readInt();
        if (in.readableBytes() < packetLength) {
            in.resetReaderIndex();
            return;
        } else if (packetLength > Packet.MAX_PACK_SIZE) {
            logger.warn("Received packet length is greater than {}", Packet.MAX_PACK_SIZE);
            return;
        }

        int headerLength = in.readInt();
        if (headerLength > packetLength) {
            logger.warn("Received header length is greater than packet length");
            return;
        }

        if (in.readableBytes() < headerLength) {
            in.resetReaderIndex();
            return;
        }

        byte[] headerData = new byte[headerLength];
        in.readBytes(headerData);
        Header header = new Header();
        ProtostuffUtil.mergeFrom(headerData, header);

        int bodyLength = packetLength - headerLength;

        if (in.readableBytes() < bodyLength) {
            in.resetReaderIndex();
            return;
        }

        ByteBuf bodyData = in.readBytes(bodyLength).retain();

        out.add(new Packet(header, bodyData));
    }

}
