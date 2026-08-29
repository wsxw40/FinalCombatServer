/**
 * 
 */
package com.pearl.o2o.protocal.pojo;

/**
 * @author lifengyang
 *
 */
public class Channel {
	private int id;
	private Integer serverId;
	private String name;
	private Integer online=0;
	private Integer maxOnline=0;
	private Integer max=1000;
	private Integer min=1;
	private Integer channelId;
	private Integer isTcp;
	private int isChange;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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

	@Override
	public String toString() {
		return "Channel [id=" + id + ", serverId=" + serverId + ", name=" + name + ", online=" + online + ", maxOnline=" + maxOnline + ", max=" + max + ", min=" + min
				+ ", channelId=" + channelId + ", isTcp=" + isTcp + ", isChange=" + isChange + "]";
	}

}
