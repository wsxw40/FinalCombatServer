/**
 * 
 */
package com.pde.log.server;

import java.net.InetSocketAddress;
import java.nio.ByteOrder;
import java.util.Arrays;
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
import org.jboss.netty.handler.codec.protobuf.ProtobufDecoder;
import org.jboss.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;

import com.pde.log.common.LogMessage.LogMsg;
import com.pde.log.server.handle.LogServerHandle;
import com.pde.log.server.util.Constants;

/**
 * @author lifengyang
 * 
 */
public class LogServer {
	public final static int BufferSizeDefault = 10000 * 300;// 2*100B*10*1000*100*3==>6K*1000*100==>600M
	public final static int DumpDelayDefault = 10;// ms

	private final int listen;
	private final LogServerHandle logHandle;

	private Channel logServerchannel;
	private ChannelFactory logServerChannelFactory;
	private ServerBootstrap logServerBootstrap;

	public LogServer(int listen) {
		this(listen, BufferSizeDefault, DumpDelayDefault);
	}

	public LogServer(int listen, int bufferSize, final long delay) {
		this.listen = listen;
		this.logHandle = new LogServerHandle(bufferSize, delay);
		buildLogServer();
	}

	private void buildLogServer() {
		logServerChannelFactory = new NioServerSocketChannelFactory(Executors.newCachedThreadPool(), Executors.newCachedThreadPool());
		logServerBootstrap = new ServerBootstrap(logServerChannelFactory);
		logServerBootstrap.setPipelineFactory(new ChannelPipelineFactory() {
			public ChannelPipeline getPipeline() {
				return Channels.pipeline(new ProtobufVarint32FrameDecoder(), new ProtobufDecoder(LogMsg.getDefaultInstance()), logHandle);
			}
		});

		logServerBootstrap.setOption("child.tcpNoDelay", true);
		logServerBootstrap.setOption("child.keepAlive", true);
		logServerBootstrap.setOption("child.reuseAddress", true);
		logServerBootstrap.setOption("child.receiveBufferSizePredictor", new AdaptiveReceiveBufferSizePredictor(16 * 1024, 16 * 1024, 64 * 1024));
		logServerBootstrap.setOption("child.bufferFactory", new HeapChannelBufferFactory(ByteOrder.LITTLE_ENDIAN));
	}

	@PostConstruct
	public boolean startLogServer() {
		try {
			System.out.println("LogServer LogServer; start success; listen:" + listen);
			logServerchannel = logServerBootstrap.bind(new InetSocketAddress(listen));
			print("LogServer LogServer; start success; listen:" + listen);
			return true;
		} catch (Exception e) {
			errorPrint("LogServer LogServer; start fail; listen:" + listen, e);
			return false;
		}
	}

	@PreDestroy
	public void destoryLogServer() {
		logServerchannel.close().awaitUninterruptibly();
		logServerChannelFactory.releaseExternalResources();
		logHandle.shutdown();
	}

	private static void print(String str) {
		System.out.println(str);
		Constants.logServerLogger.info(str);
	}

	private static void errorPrint(String str, Throwable e) {
		System.err.println(str);
		e.printStackTrace();
		Constants.logServerLogger.error(str, e);
	}

	public static void main(String[] args) {
		if (args.length != 1 && args.length != 3) {
			System.err.println("args error:" + Arrays.toString(args));
			System.exit(1);
		}
		LogServer logServer = null;
		if (args.length == 1) {
			int listen = Integer.parseInt(args[0]);
			new LogServer(listen);
		} else if (args.length == 3) {
			int listen = Integer.parseInt(args[0]);
			int bufferSize = Integer.parseInt(args[1]);
			long delay = Long.parseLong(args[1]);
			logServer = new LogServer(listen, bufferSize, delay);
		}
		if (logServer.startLogServer()) {
			final LogServer tmpLogServer = logServer;
			Runtime.getRuntime().addShutdownHook(new Thread() {
				@Override
				public void run() {
					tmpLogServer.destoryLogServer();
				}
			});
		}else{
			System.err.println("startLogServer error:" + Arrays.toString(args));
			System.exit(1);
		}
	}
}
