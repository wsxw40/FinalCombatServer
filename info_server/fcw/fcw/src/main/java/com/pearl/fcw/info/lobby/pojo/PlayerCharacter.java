package com.pearl.fcw.info.lobby.pojo;

import java.util.HashSet;
import java.util.List;

public class PlayerCharacter {
	private int playerId;
	private SysCharacter sysCharacter;
	private List<PlayerItem> weaponList;
	private List<PlayerItem> costumeList;
	private String headResource;
	private String description = "完成“赢得500场游戏”成就可解锁职业";
	private int isOpen = 0;
	private int fightNum = 0;

	// just for bioCharacter: to show the character state in characterList
	// 0:locked 1:unlock 2:using
	private int stateInCharList;
	// biobuff left time
	private int leftSeconds;

	private transient boolean hasSuitNow = false;
	private int suitNum = 0; // 1表示4 件套 ; 3表示 6件套
	private int suitId = 0;

	public PlayerCharacter() {
	}

	public PlayerCharacter(int playerId, SysCharacter sysCharacter,
			List<PlayerItem> weaponList, List<PlayerItem> costumeList,
			String headResource) {
		this.playerId = playerId;
		this.sysCharacter = sysCharacter;
		this.weaponList = weaponList;
		this.costumeList = costumeList;
		this.headResource = headResource;
	}

	public String getHeadResource() {
		return headResource;
	}

	public void setHeadResource(String headResource) {
		this.headResource = headResource;
	}

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

	public int getIsOpen() {
		return isOpen;
	}

	public void setIsOpen(int isOpen) {
		this.isOpen = isOpen;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getFightNum() throws Exception {
		return this.fightNum;
	}

	public void setFightNum(int fightNum) {

		this.fightNum = fightNum;
	}

	public int getStateInCharList() {
		return stateInCharList;
	}

	public void setStateInCharList(int stateInCharList) {
		this.stateInCharList = stateInCharList;
	}

	public int getLeftSeconds() {
		return leftSeconds;
	}

	public void setLeftSeconds(int leftSeconds) {
		this.leftSeconds = leftSeconds;
	}

	public boolean isHasSuitNow() {
		return hasSuitNow;
	}

	public void setHasSuitNow(boolean hasSuitNow) {
		this.hasSuitNow = hasSuitNow;
	}

	final public int getSuitNum() {
		return suitNum;
	}

	public final int getSuitId() {
		return suitId;
	}

	private boolean checkWeaponForSuit(HashSet<Integer> weaponListStauts) {

		if (weaponListStauts.size() >= 1) {
			if (weaponListStauts.contains(1)) {
				suitNum = weaponListStauts.size();
				return true;
			}
			return false;

		} else {
			return false;
		}
	}
}
