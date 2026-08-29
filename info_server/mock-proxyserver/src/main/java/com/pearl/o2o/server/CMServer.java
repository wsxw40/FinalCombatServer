/**
 * 
 */
package com.pearl.o2o.server;

import java.net.InetSocketAddress;
import java.nio.ByteOrder;
import java.util.concurrent.Executors;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.buffer.HeapChannelBufferFactory;
import org.jboss.netty.channel.AdaptiveReceiveBufferSizePredictor;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelFactory;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.jboss.netty.handler.codec.frame.LengthFieldBasedFrameDecoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.pearl.o2o.codec.CMDecoder;
import com.pearl.o2o.handle.CMServerHandle;
import com.pearl.o2o.util.Constants;

/**
 * @author lifengyang
 * 
 */
@Component("cMServer")
public class CMServer {
	private final int listen;
	private final MessageTransfer messageTransfer;

	private Channel channel;
	private ChannelFactory cMServerChannelFactory;
	private ServerBootstrap cMServerBootstrap;

	@Autowired
	public CMServer(@Value("${cmserver.listen}") int listen, @Qualifier("messageTransfer") MessageTransfer messageTransfer) {
		this.listen = listen;
		this.messageTransfer = messageTransfer;
		buildCMServer();
	}

	private void buildCMServer() {
		cMServerChannelFactory = new NioServerSocketChannelFactory(Executors.newCachedThreadPool(), Executors.newCachedThreadPool());
		cMServerBootstrap = new ServerBootstrap(cMServerChannelFactory);
		cMServerBootstrap.setPipelineFactory(new ChannelPipelineFactory() {
			CMServerHandle cmHandle = new CMServerHandle(messageTransfer);

			public ChannelPipeline getPipeline() {
				final LengthFieldBasedFrameDecoder lengthFieldBasedFrameDecoder = Constants.getNewLengthFieldBasedFrameDecoder();
				final CMDecoder cmDecoder = new CMDecoder();
				// SMEncoder smEncoder = new SMEncoder();
				// return Channels.pipeline(lengthFieldBasedFrameDecoder,
				// cmDecoder, smEncoder, cmHandle);
				return Channels.pipeline(lengthFieldBasedFrameDecoder, cmDecoder, cmHandle);
			}
		});
		cMServerBootstrap.setOption("child.tcpNoDelay", true);
		cMServerBootstrap.setOption("child.keepAlive", true);
		cMServerBootstrap.setOption("child.reuseAddress", true);
		cMServerBootstrap.setOption("child.receiveBufferSizePredictor", new AdaptiveReceiveBufferSizePredictor(16 * 1024, 16 * 1024, 64 * 1024));
		cMServerBootstrap.setOption("child.bufferFactory", new HeapChannelBufferFactory(ByteOrder.LITTLE_ENDIAN));
	}

	@PostConstruct
	private boolean startCMServer() {
		try {
			channel = cMServerBootstrap.bind(new InetSocketAddress(listen));
			System.err.printf("MockPorxyServer CMServer; start success; listen:%s\n", listen);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			System.err.printf("MockPorxyServer CMServer; start fail; listen:%s\n", listen);
			return false;
		}
	}

	@PreDestroy
	public void destoryCMServer() {
		channel.close().awaitUninterruptibly();
		cMServerChannelFactory.releaseExternalResources();
	}
}
