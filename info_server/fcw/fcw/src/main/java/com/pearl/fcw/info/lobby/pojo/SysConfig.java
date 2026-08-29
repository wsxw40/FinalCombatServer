package com.pearl.fcw.info.lobby.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.pearl.fcw.info.core.persistence.config.annotation.Schema;

@Entity
@Schema(GoSchema.SYS)
public class SysConfig extends BasePojo {
	private static final long serialVersionUID = 7401552271457834190L;
	@Id
	private int id;
	private String name;
	private String value;
	private String confName;

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

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getConfName() {
		return confName;
	}

	public void setConfName(String confName) {
		this.confName = confName;
	}

}
