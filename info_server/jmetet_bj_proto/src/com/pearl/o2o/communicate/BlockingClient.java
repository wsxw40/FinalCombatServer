/**
 * 
 */
package com.pearl.o2o.communicate;

import java.net.InetSocketAddress;
import java.nio.ByteOrder;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.buffer.HeapChannelBufferFactory;
import org.jboss.netty.channel.AdaptiveReceiveBufferSizePredictor;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelFactory;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;
import org.jboss.netty.handler.codec.frame.LengthFieldBasedFrameDecoder;

import com.pearl.o2o.enumuration.ClientMessage;
import com.pearl.o2o.enumuration.ClientMessage.CM_RequestBinaryRPCURL;
import com.pearl.o2o.enumuration.ServerMessage;
import com.pearl.o2o.protocal.cm.AbstractCM_RequestRPC;
import com.pearl.o2o.protocal.sm.AbstractSM_ResponseRPC;

/**
 * @author lifengyang
 * 
 */
public class BlockingClient extends SimpleChannelHandler {

	private boolean state = false;

	private ChannelFactory channelFactory;
	private Channel channel;
	private ClientBootstrap clientBootstrap;

	public synchronized void initClient(String host, int prot) {
		destoryClient();

		channelFactory = new NioClientSocketChannelFactory(Executors.newCachedThreadPool(), Executors.newCachedThreadPool());
		clientBootstrap = new ClientBootstrap(channelFactory);
		clientBootstrap.setPipelineFactory(new ChannelPipelineFactory() {

			public ChannelPipeline getPipeline() {
				final LengthFieldBasedFrameDecoder lengthFieldBasedFrameDecoder = Constants.getNewLengthFieldBasedFrameDecoder();
				final SMDecoder smDecoder = new SMDecoder();
				// CMEncoder cmEncoder = new CMEncoder();
				// return Channels.pipeline(lengthFieldBasedFrameDecoder,
				// smDecoder, cmEncoder, smHandle);
				return Channels.pipeline(lengthFieldBasedFrameDecoder, smDecoder, BlockingClient.this);
			}
		});
		clientBootstrap.setOption("tcpNoDelay", true);
		clientBootstrap.setOption("keepAlive", true);
		clientBootstrap.setOption("reuseAddress", true);
		clientBootstrap.setOption("receiveBufferSizePredictor", new AdaptiveReceiveBufferSizePredictor(16 * 1024, 16 * 1024, 64 * 1024));
		clientBootstrap.setOption("bufferFactory", new HeapChannelBufferFactory(ByteOrder.LITTLE_ENDIAN));

		try {
			final ChannelFuture future = clientBootstrap.connect(new InetSocketAddress(host, prot));
			future.awaitUninterruptibly();
			if (future.isSuccess()) {
				channel = future.getChannel();
				System.err.printf("BlockingClient; connect success; infoServer:%s, port:%s\n", host, prot);

			} else {
				System.err.printf("BlockingClient; connect fail infoServer:%s, port:%s\n", host, prot);
				future.getCause().printStackTrace();
			}
			state = future.isSuccess();
		} catch (Exception e) {
			System.err.printf("BlockingClient; connect fail infoServer:%s, port:%s\n", host, prot);
			e.printStackTrace();
			state = false;
		}
	}

	public synchronized void destoryClient() {
		if (null != channel) {
			channel.getCloseFuture().awaitUninterruptibly();
		}
		if (null != channelFactory) {
			channelFactory.releaseExternalResources();
		}
		state = false;
		event.clear();
	}

	private Map<Integer, ServerMessage> event = new ConcurrentHashMap<Integer, ServerMessage>();

	public void registerEvent(AbstractCM_RequestRPC request) {
		switch (request.getType()) {
		case CM_RequestTextRPC:
			event.put(request.getRpcId(), ServerMessage.SM_ResponseTextRPC);
			break;
		case CM_RequestBinaryRPC:
			event.put(request.getRpcId(), ServerMessage.SM_ResponseBinaryRPC);
			break;
		}

	}

	public AbstractSM_ResponseRPC requestCM(AbstractCM_RequestRPC request) {
		return null;
	}

	@Override
	public void channelBound(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
	}

	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) throws Exception {
		e.getCause().printStackTrace();
		ctx.getChannel().close();
	}

}
