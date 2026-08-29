package com.pearl.o2o.pojo;

public class Rank extends BaseItemPojo<Rank>{
	private static final long serialVersionUID = -1041923125840623738L;
	private String title;
	private Integer exp;
	
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getExp() {
		return exp;
	}
	public void setExp(Integer exp) {
		this.exp = exp;
	}
}
