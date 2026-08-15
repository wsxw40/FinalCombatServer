package com.pearl.fcw;

import java.lang.invoke.MethodHandles;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.fcw.info.core.network.Header;
import com.pearl.fcw.info.core.network.Packet;
import com.pearl.fcw.info.core.network.PacketDecoder;
import com.pearl.fcw.info.core.network.PacketEncoder;
import com.pearl.fcw.info.core.network.PacketType;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.protostuff.LinkedBuffer;
import io.protostuff.Message;
import io.protostuff.ProtobufIOUtil;

public class BaseTest {
    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    protected static Bootstrap client = null;
    protected static String host = "127.0.0.1";
    protected static int port = 9000;
    protected static EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
    protected static Channel infoChannel;
    protected static CountDownLatch latch = new CountDownLatch(1);

    @AfterClass
    public static void classTearDown() {
        stop();
    }

    protected <T extends Message<T>> void send(long playerId, String methodName, T request, Message<?> response) {
        Packet packet = new Packet();
        Header header = new Header();
        header.setType(PacketType.REQUEST);
        header.setMethodName(methodName);
        header.setUid(0);
        header.setPid(playerId);
        header.setRpcId((int) Thread.currentThread().getId());
        packet.setHeader(header);

        if (request != null) {
            packet.setBody(Unpooled.wrappedBuffer(ProtobufIOUtil.toByteArray(request, request.cachedSchema(), LinkedBuffer.allocate())));
        } else {
            packet.setBody(Unpooled.wrappedBuffer("".getBytes()));
        }

        start(response);
        send(packet);
        
        logger.info("Send rpc {}",packet.getHeader().getMethodName());
    }

    protected static void send(Packet packet) {
        infoChannel.writeAndFlush(packet);
        try {
            if (!latch.await(60, TimeUnit.SECONDS)) {
                throw new RuntimeException("Timeout");
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    protected static void start(Message<?> response) {
        client = new Bootstrap();
        client.group(eventLoopGroup).channel(NioSocketChannel.class);
        client.option(ChannelOption.TCP_NODELAY, false).option(ChannelOption.SO_KEEPALIVE, true);
        client.handler(new ChannelInitializer<SocketChannel>() {
            @SuppressWarnings({ "unchecked", "rawtypes" })
			@Override
            protected void initChannel(SocketChannel ch) throws Exception {
                ch.pipeline().addLast(new PacketDecoder());
                ch.pipeline().addLast(new PacketEncoder());
                ch.pipeline().addLast(new BaseClientHandler(latch, response));
            }
        });
        ChannelFuture future = client.connect(host, port).awaitUninterruptibly();
        infoChannel = future.channel();
    }

    protected static void stop() {
        infoChannel.closeFuture().awaitUninterruptibly(500);
        eventLoopGroup.shutdownGracefully();
    }

}
