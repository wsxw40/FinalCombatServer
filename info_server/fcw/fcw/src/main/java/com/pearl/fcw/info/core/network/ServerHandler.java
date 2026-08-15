package com.pearl.fcw.info.core.network;

import java.util.concurrent.ExecutorService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.fcw.info.core.persistence.utils.BinaryChannelBuffer;
import com.pearl.fcw.info.core.persistence.utils.ThreadPoolSelector;

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
            ExecutorService threadPool = threadPoolSelector.chooseThreadPool(msg.getRequest().getType());
            threadPool.execute(executableWrap);
        } catch (Throwable t) {
            logger.warn("Error has occurred: ", t);
//            ctx.writeAndFlush(Response.error());
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

            if (msg == null || msg.getRequest() == null) {
//                ctx.writeAndFlush(Response.error());
            	return;
            }
            
            String requestMethod = msg.getRequest().getUrl();

            Packet packet = new Packet();
            Response response = null;
            
            try {
                Executable executable = executableSelector.select(requestMethod);
                
                if(msg.getRequest().getType()==ClientMessage.CM_SyncRPCQueue.ordinal()|| 
                		msg.getRequest().getType() == ClientMessage.CM_ResponseStatus.ordinal()){	//特殊请求
                	
                	packet.setBody(msg.getBody());
                	
                }else{
                	
                	 if (executable == null) {
                         logger.error("unknown rpc url :" + requestMethod);
                         throw new MessageException("unknown rpc url :" + requestMethod);	//TODO 
                     }
                     if(msg.getRequest().getType() == ClientMessage.CM_RequestTextRPC.ordinal()){
                     	response = Response.text(new String(executable.execute(msg.getRequest().getParamMap())));
                     }else if(msg.getRequest().getType() == ClientMessage.CM_RequestBinaryRPC.ordinal()){
                     	response = Response.binary(executable.execute(new BinaryChannelBuffer(Unpooled.copiedBuffer(msg.getRequest().getParamArray()))));
                     }
                     
                    packet.setRequest(msg.getRequest());
                    packet.setResponse(response);
                    packet.setBody(msg.getBody());
                    
                }
            } catch (MessageException e) {
                String messageCode = e.getMessage();
                logger.error("An MessageException has occurred in request: {}" ,messageCode);

            } catch (Throwable t) {
                logger.error("An Exception has occurred in servlet: {} , {}", this.getClass().getName(), t);

            } finally {
                try {
                    LogMessage.log(msg.getRequest().toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }

                ctx.writeAndFlush(packet).addListener(packet::operationComplete);
            }
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        allChannels.add(ctx.channel());
        super.channelActive(ctx);
    }
}
