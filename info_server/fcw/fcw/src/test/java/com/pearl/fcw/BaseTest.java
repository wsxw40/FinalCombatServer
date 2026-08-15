package com.pearl.fcw;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.bytes.ByteArrayDecoder;
import io.netty.handler.codec.bytes.ByteArrayEncoder;

import java.lang.invoke.MethodHandles;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.function.BiFunction;

import org.junit.AfterClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.fcw.info.core.network.ClientMessage;
import com.pearl.fcw.info.core.persistence.utils.BinaryChannelBuffer;
import com.pearl.fcw.info.core.persistence.utils.BinaryUtil;

public class BaseTest {
	private static final Logger logger = LoggerFactory.getLogger(MethodHandles
			.lookup().lookupClass());
	protected static Bootstrap client = null;
    protected static String host = "127.0.0.1";
    protected static int port = 8082;
	protected static EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
	protected static Channel infoChannel;
	protected static CountDownLatch latch = new CountDownLatch(1);

	@AfterClass
	public static void classTearDown() {
		stop();
	}

	protected Boolean messageHandler(ChannelHandlerContext ctx, Object msg) {
		return false;
	}

	protected void sendBinaryRPC(String methodName, Map<String, Object> params, ClientMessage type) {
		BinaryChannelBuffer channel = new BinaryChannelBuffer();
        //		channel.writeByte((byte) 1);// CM_RequestBinaryRPC
        channel.writeByte((byte) type.ordinal());
        channel.writeInt(3);// queueId
        channel.writeInt(8);// rpcId
        channel.writeString(methodName);// url
        channel.writeString("Client_Data"); // user data
        params.entrySet().forEach(entry -> {
            if (entry.getValue() instanceof String) {
                channel.writeString(entry.getValue().toString());
            } else if (entry.getValue() instanceof Integer) {
                channel.writeInt(Integer.parseInt(entry.getValue().toString())); // param
            } else if (entry.getValue() instanceof Short) {
                channel.writeBytes(BinaryUtil.toByta(new Short(entry.getValue().toString()))); // param
            }
        });

		start(this::messageHandler);
		send(packet(channel.array()),methodName);
	}

	private Object packet(byte[] output) {
		ByteBuf buff = Unpooled.buffer(output.length + 4);
		buff.writeBytes(BinaryUtil.toByta((char) (21314)));
		buff.writeBytes(BinaryUtil.toByta((char) (output.length + 4)));
		buff.writeBytes(output);
		logger.info("Writ :{}", buff.array());
		return buff;
	}

	static void send(Object msg,String methodName) {
		infoChannel.writeAndFlush(msg).addListener(new ChannelFutureListener() {
			@Override
			public void operationComplete(ChannelFuture future)
					throws Exception {
				if (future.isSuccess()) {
					logger.info("{} Send Success.",methodName);
				}
			}
		});;
		try {
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	protected static void send(Object msg, int timeOut) {
		infoChannel.writeAndFlush(msg);
		try {
			if (!latch.await(timeOut, TimeUnit.SECONDS)) {
				throw new RuntimeException("Timeout");
			}
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	protected static void start(BiFunction<ChannelHandlerContext, Object, Boolean> messageHandler) {
		client = new Bootstrap();
		client.group(eventLoopGroup).channel(NioSocketChannel.class);
		client.option(ChannelOption.TCP_NODELAY, false).option(
				ChannelOption.SO_KEEPALIVE, true);
		client.handler(new ChannelInitializer<SocketChannel>() {
			@Override
			protected void initChannel(SocketChannel ch) throws Exception {
				 ch.pipeline().addLast(new ByteArrayDecoder());
				 ch.pipeline().addLast(new ByteArrayEncoder());
				ch.pipeline().addLast(new BaseClientHandler(latch, messageHandler));
			}
		});
		ChannelFuture future = client.connect(host, port)
				.awaitUninterruptibly();
		infoChannel = future.channel();
	}

	protected static void stop() {
		infoChannel.closeFuture().awaitUninterruptibly(500);
		eventLoopGroup.shutdownGracefully();
	}

}
