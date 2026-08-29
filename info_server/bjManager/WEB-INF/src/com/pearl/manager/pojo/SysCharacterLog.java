package com.pearl.manager.pojo;

import java.io.Serializable;
import java.util.Date;

public class SysCharacterLog implements Serializable{

	private static final long serialVersionUID = 8790378202316416739L;
	private int logVersion;
	private SysCharacter character;
	private Integer id;
	private Date logTime;
	public int getLogVersion() {
		return logVersion;
	}
	public void setLogVersion(int logVersion) {
		this.logVersion = logVersion;
	}
	public SysCharacter getCharacter() {
		return character;
	}
	public void setSysCharacter(SysCharacter character) {
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
