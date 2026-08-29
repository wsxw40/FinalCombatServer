package com.pearl.o2o.socket;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;


public class SocketClientHandler extends SimpleChannelUpstreamHandler {
    private static final Logger log = Logger.getLogger(SocketClientHandler.class.getName());
    
    private SocketClient client;
    
    public SocketClientHandler(SocketClient client) {
    	this.client = client;
    }


    @Override
    public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) {
/*    	try{	    	 		    		
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			out.write(BinaryUtil.toByta((int)0x01000000));
			ChannelBuffer buff = ChannelBuffers.buffer(out.size()+2);
			buff.writeBytes(BinaryUtil.toByta((short)(out.size()+2)));
			buff.writeBytes(out.toByteArray());
	        e.getChannel().write(buff);		       
		}
		catch(IOException ie){
			log.error("Exception in creating socket connection: ", ie);
		}*/	
    }

    @Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) {
        System.out.print(((ChannelBuffer) e.getMessage()).toString());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) {
        // Close the connection when an exception is raised.
        log.log(Level.WARN, "Unexpected exception from downstream.", e.getCause());
        e.getChannel().close();
    }


	@Override
	public void channelClosed(ChannelHandlerContext ctx, ChannelStateEvent e)
			throws Exception {
		log.warn("channel closed !");
		client.restart();
	}
    
}
