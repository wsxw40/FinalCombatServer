package com.pearl.o2o.pojo;

import java.io.Serializable;
import java.util.Date;

public class CharacterLog implements Serializable{

	private static final long serialVersionUID = 8790378202316416739L;
	private int logVersion;
	private Character character;
	private Integer id;
	private Date logTime;
	public int getLogVersion() {
		return logVersion;
	}
	public void setLogVersion(int logVersion) {
		this.logVersion = logVersion;
	}
	public Character getCharacter() {
		return character;
	}
	public void setCharacter(Character character) {
		this.character = character;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getLogTime() {
		return logTime;
	}
	public void setLogTime(Date logTime) {
		this.logTime = logTime;
	}

}
