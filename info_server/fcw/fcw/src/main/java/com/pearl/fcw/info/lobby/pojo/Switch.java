package com.pearl.fcw.info.lobby.pojo;

import java.util.ArrayList;
import java.util.List;

public  class Switch {
	private volatile boolean isOn = false; 
	private final String name;
	
	private static List<Switch> switchList = new ArrayList<Switch>();
	
	public Switch(boolean isOn, String name) {
		this.isOn = isOn;
		this.name = name;
		switchList.add(this);
	}

	public void open(){
		isOn = true;
	}
	
	public void close(){
		isOn = false;
	}
	
	public boolean getIsOn(){
		return isOn;
	}

	public String getName() {
		return name;
	}

	public static List<Switch> getSwitchList() {
		return switchList;
	}	
	
}
