package com.pearl.o2o.socket;

import java.util.ArrayList;
import java.util.List;

public class ChannelManager {
	private List<DefaultSocketHandler>  channels = new ArrayList<DefaultSocketHandler>();
	
	public void registChannel(DefaultSocketHandler handler){
		channels.add(handler);
	}
	
	
	public void removeChannel(DefaultSocketHandler handler){
		channels.remove(handler);
	}


	public List<DefaultSocketHandler> getChannels() {
		return channels;
	}
	
	public DefaultSocketHandler getChannelById(int id){
		for (DefaultSocketHandler handler : channels) {
			if (handler.getChannel().getId().equals(id)) {
				return handler;
			}
		}
		
		return null;
	}
}
