/**
 * 
 */
package com.pearl.o2o.test;

import java.net.InetSocketAddress;
import java.nio.ByteOrder;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.buffer.HeapChannelBufferFactory;
import org.jboss.netty.channel.AdaptiveReceiveBufferSizePredictor;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;
import org.jboss.netty.handler.codec.frame.LengthFieldBasedFrameDecoder;

import com.pearl.o2o.codec.SMDecoder;
import com.pearl.o2o.protocal.cm.requestBinaryRpc.s_player_online;

/**
 * @author lifengyang
 * 
 */
public class Test {
	private static final int maxFrameLength = 65535;
	private static final int lengthFieldOffset = 2;
	private static final int lengthFieldLength = 2;
	private static final int lengthAdjustment = -4;
	private static final int initialBytesToStrip = 0;

	private static LengthFieldBasedFrameDecoder getNewLengthFieldBasedFrameDecoder() {
		return new LengthFieldBasedFrameDecoder(maxFrameLength, lengthFieldOffset, lengthFieldLength, lengthAdjustment, initialBytesToStrip);
	}

	public static void main(String[] args) {
		String infoServer = "192.168.1.112";
		int port = 8082;
		
		NioClientSocketChannelFactory sMClientChannelFactory = new NioClientSocketChannelFactory(Executors.newCachedThreadPool(), Executors.newCachedThreadPool());
		ClientBootstrap sMClientBootstrap = new ClientBootstrap(sMClientChannelFactory);
		sMClientBootstrap.setPipelineFactory(new ChannelPipelineFactory() {
			public ChannelPipeline getPipeline() {
				LengthFieldBasedFrameDecoder lengthFieldBasedFrameDecoder = getNewLengthFieldBasedFrameDecoder();
				SMDecoder smDecoder = new SMDecoder();
				// CMEncoder cmEncoder = new CMEncoder();
				SMClientHandleTest smHandle = new SMClientHandleTest();
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
		ChannelFuture future = sMClientBootstrap.connect(new InetSocketAddress(infoServer, port));
		future.awaitUninterruptibly();
		if (future.isSuccess()) {
			Channel sMClientChannel = future.getChannel();
			System.err.printf("MockPorxyServer SMClient; connect success; infoServer:%s, port:%s\n", infoServer, port);
			s_player_online s_player_online = new s_player_online();
			s_player_online.setUid(81074);
			s_player_online.setUserName("faf"+(int)(Math.random()+1000));
			s_player_online.setName("faf"+(int)(Math.random()+1000));
			s_player_online.setUrl("s_player_online");
			s_player_online.setIp("0.71.11.216");
			s_player_online.setUserData(new byte[10]);
			s_player_online.setInternetCafe("0");
			s_player_online.setIsXunleiVip("1");
			sMClientChannel.write(s_player_online.toChannelBuffer());
		} else {
			future.getCause().printStackTrace();
			System.err.printf("MockPorxyServer SMClient; connect infoServer:%s, port:%s\n", infoServer, port);

		}
	}
}
