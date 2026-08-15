package com.pearl.manager.pojo;

public class SysModeConfig  {
	
	private static final long serialVersionUID = -4170089746411542200L;
	private int id;
	private Integer type;
	private String config;
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getConfig() {
		return config;
	}
	public void setConfig(String config) {
		this.config = config;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}
