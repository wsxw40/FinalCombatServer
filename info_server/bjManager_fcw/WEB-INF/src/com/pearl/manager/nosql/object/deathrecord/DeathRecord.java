package com.pearl.manager.nosql.object.deathrecord;

import java.util.Date;

public class DeathRecord {
	private float x,y,z;
	private int mapId;
	private String mapName;
	private Date killTime;
	
	private int deadId;
	private int deadCharacterID;
	private String deadCharacterName;

	private int killId;
	private int killCharacterID;
	private String killCharacterName;
	private int killWeaponId;
	private String killWeaponIdName;
//	private static char c = ':';
//	public String getSuffixKey(){
//		//X:Y:Z:TEAM:TYPE
//		return new StringBuilder().append(x).append(c)
//				.append(y).append(c).append(z).append(c).append(team).append(c)
//				.append(type).toString();
//	}
	public float getX() {
		return x;
	}
	public void setX(float x) {
		this.x = x;
	}
	public float getY() {
		return y;
	}
	public void setY(float y) {
		this.y = y;
	}
	public float getZ() {
		return z;
	}
	public void setZ(float z) {
		this.z = z;
	}
	public int getMapId() {
		return mapId;
	}
	public void setMapId(int mapId) {
		this.mapId = mapId;
	}
	public String getMapName() {
		return mapName;
	}
	public void setMapName(String mapName) {
		this.mapName = mapName;
	}
	public Date getKillTime() {
		return killTime;
	}
	public void setKillTime(Date killTime) {
		this.killTime = killTime;
	}
	public int getDeadId() {
		return deadId;
	}
	public void setDeadId(int deadId) {
		this.deadId = deadId;
	}
	public int getDeadCharacterID() {
		return deadCharacterID;
	}
	public void setDeadCharacterID(int deadCharacterID) {
		this.deadCharacterID = deadCharacterID;
	}
	public String getDeadCharacterName() {
		return deadCharacterName;
	}
	public void setDeadCharacterName(String deadCharacterName) {
		this.deadCharacterName = deadCharacterName;
	}
	public int getKillId() {
		return killId;
	}
	public void setKillId(int killId) {
		this.killId = killId;
	}
	public int getKillCharacterID() {
		return killCharacterID;
	}
	public void setKillCharacterID(int killCharacterID) {
		this.killCharacterID = killCharacterID;
	}
	public String getKillCharacterName() {
		return killCharacterName;
	}
	public void setKillCharacterName(String killCharacterName) {
		this.killCharacterName = killCharacterName;
	}
	public int getKillWeaponId() {
		return killWeaponId;
	}
	public void setKillWeaponId(int killWeaponId) {
		this.killWeaponId = killWeaponId;
	}
	public String getKillWeaponIdName() {
		return killWeaponIdName;
	}
	public void setKillWeaponIdName(String killWeaponIdName) {
		this.killWeaponIdName = killWeaponIdName;
	}
	
	
}
