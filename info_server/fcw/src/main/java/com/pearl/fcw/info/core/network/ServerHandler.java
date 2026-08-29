package com.pearl.fcw.info.core.network;

import java.util.concurrent.ExecutorService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.fcw.info.core.persistence.utils.ThreadPoolSelector;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;

public class ServerHandler extends SimpleChannelInboundHandler<Packet> {

    private static final Logger logger = LoggerFactory.getLogger(ServerHandler.class);

    private ExecutableSelector executableSelector;
    private final ThreadPoolSelector threadPoolSelector;
    private ChannelGroup allChannels;

    public ServerHandler(ExecutableSelector executableSelector, ThreadPoolSelector threadPoolSelector, ChannelGroup allChannels) {
        this.executableSelector = executableSelector;
        this.threadPoolSelector = threadPoolSelector;
        this.allChannels = allChannels;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Packet msg) throws Exception {

        try {
            ExecutableWrap executableWrap = new ExecutableWrap(ctx, msg);
            ExecutorService threadPool = threadPoolSelector.chooseThreadPool(msg.getHeader().getType().getNumber());
            threadPool.execute(executableWrap);
        } catch (Throwable t) {
            logger.warn("Error has occurred: ", t);
            ctx.writeAndFlush(Response.error());
        }

    }

    private class ExecutableWrap implements Runnable {

        private final ChannelHandlerContext ctx;
        private final Packet msg;

        public ExecutableWrap(ChannelHandlerContext ctx, Packet msg) {
            this.ctx = ctx;
            this.msg = msg;
        }

        @Override
        public void run() {

            if (msg == null || msg.getHeader() == null) {
                ctx.writeAndFlush(Response.error());
            }

            String requestMethod = msg.getHeader().getMethodName();

            Packet packet = new Packet();

            Header header = new Header();
            header.setType(PacketType.RESPONSE);
            header.setMethodName(msg.getHeader().getMethodName());
            header.setRpcId(msg.getHeader().getRpcId());
            header.setCode(ResponseCode.SUCCESS.getNumber());
            header.setPid(msg.getHeader().getPid());
            header.setUid(msg.getHeader().getUid());
            header.setPacketId(msg.getHeader().getPacketId());

            try {
                Executable executable = executableSelector.select(requestMethod);

                if (executable == null) {
                    logger.error("unknown rpc url :" + header.toString());
                    throw new MessageException(ResponseCode.SERVER_ERROR);
                }

                byte[] bodyArray = msg.getBody().array();

                byte[] ba = executable.execute(msg.getHeader().getPid(), bodyArray);
                ByteBuf result = ba != null ? Unpooled.wrappedBuffer(ba) : null;
                packet.setBody(result);

            } catch (MessageException e) {
                String messageCode = e.getMessage();
                header.setCode(Integer.valueOf(messageCode));
                packet.setBody(Unpooled.wrappedBuffer(messageCode.getBytes()));
                logger.error("An MessageException has occurred in request: " + header.toString());

            } catch (Throwable t) {
                header.setCode(ResponseCode.SERVER_ERROR.getNumber());
                packet.setBody(Unpooled.wrappedBuffer((ResponseCode.SERVER_ERROR.getNumber() + "").getBytes()));
                logger.error("An Exception has occurred in servlet:" + this.getClass().getName() + ",  header: " + header.toString(), t);

            } finally {
                packet.setHeader(header);

                try {
                    LogMessage.log(header.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }

                ctx.writeAndFlush(packet);
            }
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        allChannels.add(ctx.channel());
        super.channelActive(ctx);
    }
}
