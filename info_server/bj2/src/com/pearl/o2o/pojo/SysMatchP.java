package com.pearl.o2o.pojo;
//zlm2015-10-9-匹配
public class SysMatchP extends BaseItemPojo<SysMatchP>{
	private static final long serialVersionUID = -1041923125840623738L;
	private int id;
	private Integer score;//战斗力数值
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
}