/**
 * 
 */
package com.pearl.o2o.protocal.pojo;

/**
 * @author lifengyang
 * 
 */
public class LevelInfo {
	int id;
	private String name;
	private int type;
	private String displayName;
	private String description;
	private int isVip;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getIsVip() {
		return isVip;
	}
	public void setIsVip(int isVip) {
		this.isVip = isVip;
	}
	@Override
	public String toString() {
		return "LevelInfo [id=" + id + ", name=" + name + ", type=" + type + ", displayName=" + displayName + ", description=" + description + ", isVip=" + isVip + "]";
	}

}
