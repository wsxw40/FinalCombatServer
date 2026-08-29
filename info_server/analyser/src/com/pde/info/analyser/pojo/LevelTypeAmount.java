package com.pde.info.analyser.pojo;

import java.util.Date;

public class LevelTypeAmount {

	private int id;
	private int levelId; // 地图id
	private int type;  // 模式
	private String statisticDate;
	private Date createDate;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getLevelId() {
		return levelId;
	}
	public void setLevelId(int levelId) {
		this.levelId = levelId;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getStatisticDate() {
		return statisticDate;
	}
	public void setStatisticDate(String statisticDate) {
		this.statisticDate = statisticDate;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
}
