package com.pearl.fcw;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.concurrent.CountDownLatch;
import java.util.function.BiFunction;

public class BaseClientHandler extends SimpleChannelInboundHandler<Object> {

    private CountDownLatch latch;
	private BiFunction<ChannelHandlerContext, Object, Boolean> response;

    public BaseClientHandler(CountDownLatch latch, BiFunction<ChannelHandlerContext, Object, Boolean> response) {
        this.latch = latch;
        this.response = response;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Object msg) throws Exception {
        response.apply(channelHandlerContext, msg);
        latch.countDown();
    }

}
