package com.pearl.fcw.info.lobby.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.pearl.fcw.info.lobby.utils.CommonUtil;

public class GunProperty implements Serializable {
	private static final long serialVersionUID = -1426315616375769217L;
	public final static int RED 	= 1;
	public final static int PURPLE 	= 2;
	public final static int BLUE 	= 3;
	public final static int GREEND 	= 4;
	public final static int ORANGE 	= 5;
	public final static int COLORFUL = 6;
	public final static int GOLD 	= 7;
	
	public final static int UNKNOW = 99;
	
	private int color;//1 red 2 purple 3 blue 4 green 5 orange
	private List<Property> propertyList=new ArrayList<Property>();
	private List<StrProperty> strPropertyList = new ArrayList<StrProperty>();
	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}

	public List<Property> getPropertyList() {
		return propertyList;
	}

	public void setPropertyList(List<Property> propertyList) {
		this.propertyList = propertyList;
	}
	public static class StrProperty implements Serializable{

		private static final long serialVersionUID = 6032395140985208671L;
		private int index = 0;
		private int open = 0;
		private int state = 0;
		private int level = 0;
		private String desc = "";
		public int getIndex() {
			return index;
		}
		public void setIndex(int index) {
			this.index = index;
		}
		public int getState() {
			return state;
		}
		public void setState(int state) {
			this.state = state;
		}
		public int getLevel() {
			return level;
		}
		public void setLevel(int level) {
			this.level = level;
		}
		public String getDesc() {
			return desc;
		}
		public void setDesc(String desc) {
			this.desc = desc;
		}
		public int getOpen() {
			return open;
		}
		public void setOpen(int open) {
			this.open = open;
		}
		
	}
	public static class Property implements Serializable{
		private static final long serialVersionUID = 6543779662662796145L;
		private int index;//the property type
		private int value;
		private int value2;
		private int time;
		private int color;
		public Property(int index,int value,int value2,int time,int color) {
			this.index=index;
			this.value=value;
			this.value2=value2;
			this.time=time;
			this.color = color;
		}
		public int getIndex() {
			return index;
		}
		public void setIndex(int index) {
			this.index = index;
		}
		public int getValue() {
			return value;
		}
		public void setValue(int value) {
			this.value = value;
		}
		public int getValue2() {
			return value2;
		}
		public void setValue2(int value2) {
			this.value2 = value2;
		}
		public int getTime() {
			return time;
		}
		public void setTime(int time) {
			this.time = time;
		}
		public String getBasePropertyStr() {
			return CommonUtil.getPropertyStr(this.index,this.value,this.value2,this.time);
		}
		public void setBasePropertyStr(String basePropertyStr) {
		}
		public int getColor() {
			return color;
		}
		public void setColor(int color) {
			this.color = color;
		}
	}
	public List<StrProperty> getStrPropertyList() {
		return strPropertyList;
	}

	public void setStrPropertyList(List<StrProperty> strPropertyList) {
		this.strPropertyList = strPropertyList;
	}
}
