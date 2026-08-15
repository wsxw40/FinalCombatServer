/**
 * 
 */
package com.pearl.o2o.communicate;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.oneone.OneToOneDecoder;

import com.pearl.o2o.protocal.PJMessage;

/**
 * @author lifengyang 作用：包头校验
 */
public class HeadTipCheckDecoder extends OneToOneDecoder {

	@Override
	protected Object decode(ChannelHandlerContext ctx, Channel channel, Object msg) throws Exception {
		if (msg instanceof ChannelBuffer) {
			// ChannelBuffer channelBuffer = (ChannelBuffer) e.getMessage();
			// byte type = channelBuffer.getByte(4);
			// CM message =
			// ClientMessage.getClientMessage(type).buildMessage(channelBuffer);

			final ChannelBuffer message = (ChannelBuffer) msg;
			final int headtip = message.getUnsignedShort(0);
			if (headtip == PJMessage.HEADTIP) {
				return msg;
			} else {
				System.err.println("Message HEADTIP Error ! headtip:" + headtip);
				channel.close().awaitUninterruptibly();
				return null;
			}
		} else {
			System.err.println("Message Type Error :" + msg);
			return msg;
		}
	}

}
