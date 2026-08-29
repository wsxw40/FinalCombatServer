/**
 * 
 */
package com.pearl.o2o.communicate;

import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelDownstreamHandler;

/**
 * @author lifengyang
 *
 */
public class SMEncoder extends SimpleChannelDownstreamHandler {

	@Override
	public void writeRequested(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
		// TODO Auto-generated method stub
		super.writeRequested(ctx, e);
	}

}
