package com.pearl.fcw.info.lobby.pojo;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.pearl.fcw.info.core.persistence.config.annotation.Schema;

@Entity
@Schema(GoSchema.MAIN)
public class Player extends BasePojo {
	private static final long serialVersionUID = -3609150921466192707L;

	@Id
	@GeneratedValue
	private int id;
	private String userName;
	private String name;
	private int isNew;
	private int exp;
	private Integer isVip;
	private String icon = "01_r";
	private int maxFightNum;
	private int gWin;
	private int gLose;

	@Override
	public int getId() {
		return id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIsNew() {
		return isNew;
	}

	public void setIsNew(int isNew) {
		this.isNew = isNew;
	}

	public int getExp() {
		return exp;
	}

	public void setExp(int exp) {
		this.exp = exp;
	}

	public Integer getIsVip() {
		return isVip;
	}

	public void setIsVip(int isVip) {
		this.isVip = isVip;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public int getMaxFightNum() {
		return maxFightNum;
	}

	public void setMaxFightNum(int maxFightNum) {
		this.maxFightNum = maxFightNum;
	}

	public void setIsVip(Integer isVip) {
		this.isVip = isVip;
	}

	public List getBuffList() { // FIXME
		return null;
	}

	public int getGWin() {
		return gWin;
	}

	public void setGWin(int gWin) {
		this.gWin = gWin;
	}

	public int getGLose() {
		return gLose;
	}

	public void setGLose(int gLose) {
		this.gLose = gLose;
	}

}
