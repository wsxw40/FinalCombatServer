/**
 * 
 */
package com.pearl.o2o.test;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;

import com.pearl.o2o.protocal.sm.responseBinaryRPC.s_player_online;

/**
 * @author lifengyang
 * 
 */
public class SMClientHandleTest extends SimpleChannelHandler {



	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
		final ChannelBuffer message = (ChannelBuffer)e.getMessage();
		final s_player_online s_player_online = new s_player_online(10);
		s_player_online.mergeFrom(message);
		System.out.println(s_player_online.getMessageStringFace());
		System.exit(0);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) throws Exception {
		e.getCause().printStackTrace();
	}
	
}
