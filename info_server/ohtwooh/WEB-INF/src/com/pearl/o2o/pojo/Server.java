package com.pearl.o2o.pojo;

import java.io.Serializable;
import java.util.Map;

public class Server extends BasePojo implements Serializable{
	private static final long serialVersionUID = -3194044116691123511L;
	
	private String name;
	private Integer online = 0;
	private Integer max = 1000;
	private Map<Integer,Channel> channelMap;

	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getOnline() {
		return online;
	}
	public void setOnline(Integer online) {
		this.online = online;
	}
	public Integer getMax() {
		return max;
	}
	public void setMax(Integer max) {
		this.max = max;
	}
	public Map<Integer, Channel> getChannelMap() {
		return channelMap;
	}
	public void setChannelMap(Map<Integer, Channel> channelMap) {
		this.channelMap = channelMap;
	}
}
