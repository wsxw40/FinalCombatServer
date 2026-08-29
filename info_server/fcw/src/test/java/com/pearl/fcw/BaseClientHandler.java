package com.pearl.fcw;

import java.util.concurrent.CountDownLatch;

import org.junit.Assert;

import com.pearl.fcw.info.core.network.Header;
import com.pearl.fcw.info.core.network.Packet;
import com.pearl.fcw.info.core.network.ResponseCode;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.protostuff.Message;
import io.protostuff.ProtobufIOUtil;

public class BaseClientHandler<T extends Message<T>> extends SimpleChannelInboundHandler<Packet> {

    private CountDownLatch latch;
    private T response;

    public BaseClientHandler(CountDownLatch latch, T message) {
        this.latch = latch;
        this.response = message;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Packet packet) throws Exception {
        latch.countDown();
        Header header = packet.getHeader();
        System.out.println(header);
        Assert.assertEquals(header.getCode(), ResponseCode.SUCCESS.getNumber());
        ProtobufIOUtil.mergeFrom(packet.getBody().array(), response, response.cachedSchema());
        System.out.println(response.toString());
    }

}
