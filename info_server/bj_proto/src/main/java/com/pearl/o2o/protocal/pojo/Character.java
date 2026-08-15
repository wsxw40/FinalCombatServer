/**
 * 
 */
package com.pearl.o2o.protocal.pojo;

import java.util.List;

/**
 * @author lifengyang
 * 
 */
public class Character {
	private int playerId;
	private SysCharacter sysCharacter;
	private List<PlayerItem> weaponList;
	private List<PlayerItem> costumeList;
	private String headResource;
	private int isOpen = 0;
	private int fightNum = 0;

	public int getPlayerId() {
		return playerId;
	}

	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}

	public SysCharacter getSysCharacter() {
		return sysCharacter;
	}

	public void setSysCharacter(SysCharacter sysCharacter) {
		this.sysCharacter = sysCharacter;
	}

	public List<PlayerItem> getWeaponList() {
		return weaponList;
	}

	public void setWeaponList(List<PlayerItem> weaponList) {
		this.weaponList = weaponList;
	}

	public List<PlayerItem> getCostumeList() {
		return costumeList;
	}

	public void setCostumeList(List<PlayerItem> costumeList) {
		this.costumeList = costumeList;
	}

	public String getHeadResource() {
		return headResource;
	}

	public void setHeadResource(String headResource) {
		this.headResource = headResource;
	}

	public int getIsOpen() {
		return isOpen;
	}

	public void setIsOpen(int isOpen) {
		this.isOpen = isOpen;
	}

	public int getFightNum() {
		return fightNum;
	}

	public void setFightNum(int fightNum) {
		this.fightNum = fightNum;
	}

}
