package com.pearl.manager.pojo;

import java.io.Serializable;

public class GmPrivilege implements Serializable {

	private static final long serialVersionUID = -796113871867138360L;

	private int id;
	private String name;
	private String description;
	private String parent;
	
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getParent() {
		return parent;
	}
	public void setParent(String parent) {
		this.parent = parent;
	}

}
