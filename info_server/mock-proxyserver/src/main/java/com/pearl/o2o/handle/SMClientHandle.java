/**
 * 
 */
package com.pearl.o2o.handle;

import java.net.InetSocketAddress;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;
import org.jboss.netty.util.Timeout;
import org.jboss.netty.util.Timer;
import org.jboss.netty.util.TimerTask;

import com.pearl.o2o.server.MessageTransfer;
import com.pearl.o2o.server.SMClient;

/**
 * @author lifengyang
 * 
 */
public class SMClientHandle extends SimpleChannelHandler {
	private static final Logger log = Logger.getLogger(MessageTransfer.class);
	private final MessageTransfer messageTransfer;
	private final Timer timer;
	private final ClientBootstrap bootstrap;

	public SMClientHandle(MessageTransfer messageTransfer,Timer timer,ClientBootstrap bootstrap) {
		this.messageTransfer = messageTransfer;
		this.timer = timer;
		this.bootstrap = bootstrap;
	}

	@Override
	public void channelBound(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
		messageTransfer.registerSMChannel(e.getChannel());
	}

	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
		messageTransfer.transferSM((ChannelBuffer) e.getMessage());
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) throws Exception {
		e.getCause().printStackTrace();
		ctx.getChannel().close();
	}

	@Override
	public void channelClosed(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
		log.warn("Sleeping for: " + SMClient.RECONNECT_DELAY + "s");
		timer.newTimeout(new TimerTask() {
			public void run(Timeout timeout) throws Exception {
				log.warn("Reconnecting to: " + getRemoteAddress());
				bootstrap.connect();
			}
		}, SMClient.RECONNECT_DELAY, TimeUnit.SECONDS);
	}
	InetSocketAddress getRemoteAddress() {
		return (InetSocketAddress) bootstrap.getOption("remoteAddress");
	}
}
