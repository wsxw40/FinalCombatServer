/**
 * 
 */
package com.pearl.o2o.codec;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.oneone.OneToOneDecoder;

import com.pearl.o2o.protocal.PJMessage;

/**
 * @author lifengyang
 * 
 */
public class SMDecoder extends OneToOneDecoder {

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
//				s_character_online s_character_online = new s_character_online();
//				s_character_online.mergeFrom(message);
//				System.out.println(s_character_online.getMessageStringFace());
				return msg;
			} else {
				System.err.println("SMDecoder Message HEADTIP Error ! headtip:" + headtip);
				channel.close();
				return null;
			}
		} else {
			System.err.println("SMDecoder Message Type Error :" + msg);
			return msg;
		}
	}
}
