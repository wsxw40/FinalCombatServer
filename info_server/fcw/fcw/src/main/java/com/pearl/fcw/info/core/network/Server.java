package com.pearl.fcw.info.core.network;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.fcw.info.core.persistence.utils.ThreadPoolSelector;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.concurrent.GenericFutureListener;
import io.netty.util.concurrent.GlobalEventExecutor;

public class Server {

    private static final Logger logger = LoggerFactory.getLogger(Server.class);

    private EventLoopGroup group = new NioEventLoopGroup();
    private ChannelGroup allChannels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    private ExecutableSelector executableSelector;
    private final ThreadPoolSelector threadPoolSelector;
    private final int proxyPort;

    public Server(ExecutableSelector executableSelector, ThreadPoolSelector threadPoolSelector, int proxyPort) {
        this.executableSelector = executableSelector;
        this.threadPoolSelector = threadPoolSelector;
        this.proxyPort = proxyPort;
    }

    public void start() throws InterruptedException {

        ServerBootstrap b = new ServerBootstrap();

        b.group(group).channel(NioServerSocketChannel.class);
        b.option(ChannelOption.TCP_NODELAY, true).option(ChannelOption.SO_KEEPALIVE, true);
        b.childHandler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel ch) throws Exception {

                ch.closeFuture().addListener(new GenericFutureListener<ChannelFuture>() {
                    @Override
                    public void operationComplete(ChannelFuture future) throws Exception {
                        logger.info("Proxy server disconnected: " + future.channel().remoteAddress());
                    }
                });

                ch.pipeline().addLast(new PacketDecoder());
                ch.pipeline().addLast(new PacketEncoder());
                ch.pipeline().addLast(new ServerHandler(executableSelector, threadPoolSelector, allChannels));

                logger.info("Proxy server connected: " + ch.remoteAddress());
            }
        });

        try {
            ChannelFuture f = b.bind(proxyPort).sync();
            logger.info("Info listener running at {}", f.channel().localAddress());

        } catch (Exception e) {
            logger.error("Info listener bind failed: ", e);
        }
    }

    public ChannelGroup getAllChannels() {
        return allChannels;
    }

    public void stop() throws Exception {
        allChannels.close();
        group.shutdownGracefully().sync();
        threadPoolSelector.stop();
    }
    
    public void broadcast(Object msg) {
    	allChannels.writeAndFlush(msg).addListener(f -> {
            if (msg instanceof Packet) {
                ((Packet) msg).operationComplete(f);
            }
        });
    }
}
