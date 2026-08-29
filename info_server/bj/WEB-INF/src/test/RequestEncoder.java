package test;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.oneone.OneToOneEncoder;

import com.pearl.o2o.utils.BinaryUtil;


public class RequestEncoder extends OneToOneEncoder {

    private static final int LENGTH_BYTE_COUNT = 2;

    @Override
    protected Object encode(ChannelHandlerContext ctx, Channel channel, Object msg) throws Exception {
        byte[] ba = ((Request) msg).encode();
        int pkgLen = LENGTH_BYTE_COUNT + ba.length+2;
        ChannelBuffer channelBuffer = ChannelBuffers.buffer(pkgLen);
        channelBuffer.writeBytes(BinaryUtil.toByta((short) 21314));
        channelBuffer.writeBytes(BinaryUtil.toByta((short) pkgLen));
        channelBuffer.writeBytes(ba);
        return channelBuffer;
    }

}
