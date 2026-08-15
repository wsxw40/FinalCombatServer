/**
 * 
 */
package com.pearl.o2o.socket.codec;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.frame.FrameDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author lifengyang
 * 
 */
public class BJRPCDecoder extends FrameDecoder {
    private static final Logger log = LoggerFactory.getLogger(BJRPCDecoder.class.getName());

	public static final int HEADTIP = 21314;

	@Override
	protected Object decode(ChannelHandlerContext ctx, Channel channel, ChannelBuffer buffer) throws Exception {
		buffer.markReaderIndex();
		if (buffer.readableBytes() > 4) {
			int headtip = getLEChar(buffer.readByte(), buffer.readByte());
			if (HEADTIP == headtip) {
				int databodylength = getLEChar(buffer.readByte(), buffer.readByte()) - 4;
				if (buffer.readableBytes() >= databodylength) {
					byte[] databody = new byte[databodylength];
					buffer.readBytes(databody);
					return databody;
				}
			}else{
				log.warn("wrong package head");
				channel.close();
			}
		}
		buffer.resetReaderIndex();
		return null;
	}

	private int getLEChar(byte a, byte b) {
		return ((b & 0xff) << 8) | (a & 0xff);
	}

}
