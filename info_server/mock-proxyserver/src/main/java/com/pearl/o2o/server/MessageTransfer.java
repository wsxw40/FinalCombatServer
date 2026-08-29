/**
 * 
 */
package com.pearl.o2o.server;

import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.group.ChannelGroup;
import org.jboss.netty.channel.group.DefaultChannelGroup;
import org.springframework.stereotype.Component;

/**
 * @author lifengyang
 * 
 */
@Component("messageTransfer")
public class MessageTransfer {
	// private final AtomicInteger COUNT = new AtomicInteger();
	private static final Logger log = Logger.getLogger(MessageTransfer.class);
	private final ChannelGroup allCMClientChannels = new DefaultChannelGroup("MessageTransfer_allCMClientChannels");
	private final ChannelGroup allSMServerChannels = new DefaultChannelGroup("MessageTransfer_allSMServerChannels");

	@PostConstruct
	public void logCMClientChannels(){
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				log.info("allCMClientChannels size:"+allCMClientChannels.size());
			}
		}, 500, 2000);
	}
	
	public void transferCM(ChannelBuffer cm, int cMChannelId) {
		cm.setInt(9, cMChannelId);
		// int rpcId = cm.getInt(9);
		// System.err.println("receiveCMMessage rpcID:" + rpcId);
		final Iterator<Channel> iterator = allSMServerChannels.iterator();
		if (!allSMServerChannels.isEmpty()) {
			final Channel next = allSMServerChannels.iterator().next();
			if (next.isWritable()) {
				next.write(cm);
			}
		}
	}

	public void transferSM(ChannelBuffer sm) {
		final int rpcId = sm.getInt(13);
		// System.err.println("receiveSMMessage rpcID:" + rpcId);
		final Channel smClient = allCMClientChannels.find(rpcId);
		if (null != smClient && smClient.isWritable()) {
			smClient.write(sm);
		}
	}

	public void registerCMChannel(Channel cMChannel) {
		allCMClientChannels.add(cMChannel);
	}

	public void registerSMChannel(Channel sMChannel) {
		allSMServerChannels.add(sMChannel);
	}
}
