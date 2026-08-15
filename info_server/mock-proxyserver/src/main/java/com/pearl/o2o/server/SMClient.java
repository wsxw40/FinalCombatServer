/**
 * 
 */
package com.pearl.o2o.server;

import java.net.InetSocketAddress;
import java.nio.ByteOrder;
import java.util.concurrent.Executors;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.buffer.HeapChannelBufferFactory;
import org.jboss.netty.channel.AdaptiveReceiveBufferSizePredictor;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelFactory;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;
import org.jboss.netty.handler.codec.frame.LengthFieldBasedFrameDecoder;
import org.jboss.netty.util.HashedWheelTimer;
import org.jboss.netty.util.Timer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.pearl.o2o.codec.SMDecoder;
import com.pearl.o2o.handle.SMClientHandle;
import com.pearl.o2o.util.Constants;

/**
 * @author lifengyang
 * 
 */
@Component("sMClient")
public class SMClient {
	public static final int RECONNECT_DELAY = 1;
	private final String infoServer;
	private final int port;
	private final MessageTransfer messageTransfer;
	private final Timer timer = new HashedWheelTimer();

	private ChannelFactory sMClientChannelFactory;
	private Channel sMClientChannel;
	private ClientBootstrap sMClientBootstrap;

	@Autowired
	public SMClient(@Value("${smclient.host}") String infoServer, @Value("${smclient.port}") int port, @Qualifier("messageTransfer") MessageTransfer messageTransfer) {
		this.infoServer = infoServer;
		this.port = port;
		this.messageTransfer = messageTransfer;
		buildSMClient();
	}

	public void buildSMClient() {
		sMClientChannelFactory = new NioClientSocketChannelFactory(Executors.newCachedThreadPool(), Executors.newCachedThreadPool());
		sMClientBootstrap = new ClientBootstrap(sMClientChannelFactory);
		sMClientBootstrap.setPipelineFactory(new ChannelPipelineFactory() {
			SMClientHandle smHandle = new SMClientHandle(messageTransfer,timer,sMClientBootstrap);

			public ChannelPipeline getPipeline() {
				final LengthFieldBasedFrameDecoder lengthFieldBasedFrameDecoder = Constants.getNewLengthFieldBasedFrameDecoder();
				final SMDecoder smDecoder = new SMDecoder();
				// CMEncoder cmEncoder = new CMEncoder();
				// return Channels.pipeline(lengthFieldBasedFrameDecoder,
				// smDecoder, cmEncoder, smHandle);
				return Channels.pipeline(lengthFieldBasedFrameDecoder, smDecoder, smHandle);
			}
		});
		sMClientBootstrap.setOption("tcpNoDelay", true);
		sMClientBootstrap.setOption("keepAlive", true);
		sMClientBootstrap.setOption("reuseAddress", true);
		sMClientBootstrap.setOption("receiveBufferSizePredictor", new AdaptiveReceiveBufferSizePredictor(16 * 1024, 16 * 1024, 64 * 1024));
		sMClientBootstrap.setOption("bufferFactory", new HeapChannelBufferFactory(ByteOrder.LITTLE_ENDIAN));
		sMClientBootstrap.setOption("remoteAddress", new InetSocketAddress(infoServer, port));
	}

	@PostConstruct
	private boolean startSMClient() {
		try {
			final ChannelFuture future = sMClientBootstrap.connect();
			future.awaitUninterruptibly();
			if (future.isSuccess()) {
				sMClientChannel = future.getChannel();
				System.err.printf("MockPorxyServer SMClient; connect success; infoServer:%s, port:%s\n", infoServer, port);

			} else {
				System.err.printf("MockPorxyServer SMClient; connect fail infoServer:%s, port:%s\n", infoServer, port);
				future.getCause().printStackTrace();
			}
			return future.isSuccess();
		} catch (Exception e) {
			System.err.printf("MockPorxyServer SMClient; connect fail infoServer:%s, port:%s\n", infoServer, port);
			e.printStackTrace();
			return false;
		}
	}

	@PreDestroy
	public void destorySMClient() {
		sMClientChannel.getCloseFuture().awaitUninterruptibly();
		sMClientChannelFactory.releaseExternalResources();
	}

}
