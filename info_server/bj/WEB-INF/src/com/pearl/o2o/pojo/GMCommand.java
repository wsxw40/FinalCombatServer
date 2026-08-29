package com.pearl.o2o.pojo;

public class GMCommand extends BaseMappingBean<GMCommand> {
	private static final long serialVersionUID = 5358478280915759269L;
	
	private String commandName;
	private int gmLevel;
	
	public String getCommandName() {
		return commandName;
	}
	public void setCommandName(String commandName) {
		this.commandName = commandName;
	}
	public int getGmLevel() {
		return gmLevel;
	}
	public void setGmLevel(int gmLevel) {
		this.gmLevel = gmLevel;
	}
}
