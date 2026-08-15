package com.pearl.fcw.lobby.pojo.json;

/**
 * 地图补给点
 */
public class JsonLevelSupplyPoint {
	private float x;
	private float y;
	private float z;
	private int type;//对应谁的类型？
	private String name;//补给点名称
	private int value;
	private float random;//出现百分比
	private float skillTime;//消失时间？

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

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public float getRandom() {
		return random;
	}

	public void setRandom(float random) {
		this.random = random;
	}

	public float getSkillTime() {
		return skillTime;
	}

	public void setSkillTime(float skillTime) {
		this.skillTime = skillTime;
	}
}
