package com.pearl.o2o.pojo;

public class SysIcon extends BaseItemPojo<SysIcon>{
	private static final long serialVersionUID = -1041923125840623738L;
	private String icon;
	private int level=0;
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	

}
