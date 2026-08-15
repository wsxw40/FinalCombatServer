/**
 * 
 */
package com.pearl.o2o.protocal.pojo;

import java.util.Arrays;
import java.util.List;

/**
 * @author lifengyang
 *
 */
public class Server {
	private int id ;
	private String name;
	private Integer online = 0;
	private Integer maxOnline=0;
	private Integer max = 1000;
	private Integer min = 1;
	private int isNew;
	private int isChange;
	private Channel[] channelList;
	
	
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
	public Integer getMaxOnline() {
		return maxOnline;
	}
	public void setMaxOnline(Integer maxOnline) {
		this.maxOnline = maxOnline;
	}

	public Integer getIsNew() {
		return isNew;
	}
	public void setIsNew(int isNew) {
		this.isNew = isNew;
	}
	public Channel[] getChannelList() {
		return channelList;
	}
	public void setChannelList(Channel[] channelList) {
		this.channelList = channelList;
	}
	@Override
	public String toString() {
		return "Server [id=" + id + ", name=" + name + ", online=" + online + ", maxOnline=" + maxOnline + ", max=" + max + ", min=" + min + ", isNew=" + isNew + ", isChange="
				+ isChange + ", channelList=" + Arrays.toString(channelList) + "]";
	}
	
	
	
}
