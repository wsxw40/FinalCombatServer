/**
 * 
 */
package com.pearl.o2o.handle;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;

import com.pearl.o2o.server.MessageTransfer;

/**
 * @author lifengyang
 * 
 */
public class CMServerHandle extends SimpleChannelHandler {
	private final MessageTransfer messageTransfer;

	public CMServerHandle(MessageTransfer messageTransfer) {
		this.messageTransfer = messageTransfer;
	}

	@Override
	public void channelBound(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
		messageTransfer.registerCMChannel(e.getChannel());
	}

	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
		messageTransfer.transferCM((ChannelBuffer) e.getMessage(), e.getChannel().getId());
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) throws Exception {
		e.getCause().printStackTrace();
		ctx.getChannel().close();
	}

}
