package com.pearl.o2o.pojo;

import java.io.Serializable;

public class Channel extends BasePojo implements Serializable {

	private static final long serialVersionUID = -4478187185757807200L;
	private Integer serverId;
	private String name;
	private Integer online=0;
	private Integer max=1000;

	public Integer getServerId() {
		return serverId;
	}

	public void setServerId(Integer serverId) {
		this.serverId = serverId;
	}

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
}
