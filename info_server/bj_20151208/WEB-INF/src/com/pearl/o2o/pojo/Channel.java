package com.pearl.o2o.pojo;

import java.io.Serializable;



public class Channel  extends BaseMappingBean<Channel>{

	private static final long serialVersionUID = -4478187185757807200L;

	private Integer serverId;
	private String name;
	private Integer online=0;
	private Integer maxOnline=0;
	private Integer max=1000;
	private Integer min=1;
	private Integer maxTeam=10;
	private Integer minTeam=1;
	private Integer channelId;
	private Integer isTcp;
	private int isChange;

	public int getIsChange() {
		return isChange;
	}

	public void setIsChange(int isChange) {
		this.isChange = isChange;
	}

	public Integer getMaxOnline() {
		return maxOnline;
	}

	public void setMaxOnline(Integer maxOnline) {
		this.maxOnline = maxOnline;
	}

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

	public Integer getMin() {
		return min;
	}

	public void setMin(Integer min) {
		this.min = min;
	}

	public Integer getChannelId() {
		return channelId;
	}

	public void setChannelId(Integer channelId) {
		this.channelId = channelId;
	}

	public Integer getIsTcp() {
		return isTcp;
	}

	public void setIsTcp(Integer isTcp) {
		this.isTcp = isTcp;
	}

	public Integer getMaxTeam() {
		return maxTeam;
	}

	public void setMaxTeam(Integer maxTeam) {
		this.maxTeam = maxTeam;
	}

	public Integer getMinTeam() {
		return minTeam;
	}

	public void setMinTeam(Integer minTeam) {
		this.minTeam = minTeam;
	}

}
