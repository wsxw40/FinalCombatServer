package com.pearl.fcw.lobby.pojo.json;

/**
 * 地图内推车详细信息
 */
public class JsonLevelVehicleLine {
	private int lineId;//路线ID，1和2
	private int index;//索引
	private float x;
	private float y;
	private float z;
	private float x1;
	private float y1;
	private float z1;
	private int isSlope;//是否倾斜
	public int getLineId() {
		return lineId;
	}
	public void setLineId(int lineId) {
		this.lineId = lineId;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
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
	public float getX1() {
		return x1;
	}
	public void setX1(float x1) {
		this.x1 = x1;
	}
	public float getY1() {
		return y1;
	}
	public void setY1(float y1) {
		this.y1 = y1;
	}
	public float getZ1() {
		return z1;
	}
	public void setZ1(float z1) {
		this.z1 = z1;
	}
	public int getIsSlope() {
		return isSlope;
	}
	public void setIsSlope(int isSlope) {
		this.isSlope = isSlope;
	}

}
