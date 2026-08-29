package com.pearl.fcw.lobby.pojo.json;

/**
 * 地图内道具点
 */
public class JsonLevelItemPoint {
	private int sysItemId;//系统道具ID
	private float x;
	private float y;
	private float z;
	private int gameType;//地图类型

	public int getSysItemId() {
		return sysItemId;
	}

	public void setSysItemId(int sysItemId) {
		this.sysItemId = sysItemId;
	}

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

	public int getGameType() {
		return gameType;
	}

	public void setGameType(int gameType) {
		this.gameType = gameType;
	}
}
