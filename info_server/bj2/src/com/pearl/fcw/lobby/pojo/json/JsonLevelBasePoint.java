package com.pearl.fcw.lobby.pojo.json;

/**
 * 地图出生点，旗帜点，金币掉落点
 */
public class JsonLevelBasePoint {
	private int teamId;//战局内战队的标示，只有0和1
	private float x;
	private float y;
	private float z;
	private float rotate;

	public int getTeamId() {
		return teamId;
	}

	public void setTeamId(int teamId) {
		this.teamId = teamId;
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

	public float getRotate() {
		return rotate;
	}

	public void setRotate(float rotate) {
		this.rotate = rotate;
	}
}
