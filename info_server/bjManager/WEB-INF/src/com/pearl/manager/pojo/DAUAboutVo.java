package com.pearl.manager.pojo;

import java.io.Serializable;

public class DAUAboutVo implements Serializable {
	
	private static final long serialVersionUID = -7459076771552933230L;
	private int level;
	private int num;
	private String desc;
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public DAUAboutVo(int level,int num,String desc) {
		this.level=level;
		this.num=num;
		this.desc=desc;
	}
}
