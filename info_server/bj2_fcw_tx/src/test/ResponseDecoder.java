package test;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.replay.ReplayingDecoder;
import org.jboss.netty.handler.codec.replay.VoidEnum;

import com.pearl.o2o.utils.BinaryUtil;


public class ResponseDecoder extends ReplayingDecoder<VoidEnum> {

    private static final int LENGTH_BYTE_COUNT = 2;

    @Override
    protected Response decode(ChannelHandlerContext ctx, Channel channel, ChannelBuffer buffer, VoidEnum state) throws Exception {
        byte[] pkgLen = new byte[LENGTH_BYTE_COUNT];
        buffer.readBytes(pkgLen);
        buffer.readBytes(pkgLen);
        byte[] pkgContent = new byte[BinaryUtil.toShort(pkgLen) - LENGTH_BYTE_COUNT*2];
        buffer.readBytes(pkgContent);
        return new Response(pkgContent);
    }

}
