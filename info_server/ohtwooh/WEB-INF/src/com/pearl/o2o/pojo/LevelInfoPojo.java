package com.pearl.o2o.pojo;

import java.io.Serializable;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

public class LevelInfoPojo implements Serializable{

	private static final long serialVersionUID = -3700162506515280178L;
	private Integer id;
	private int type;
	private String name;	
	private String startPoints;
	private String blastPoints;
	private String flagPoints;
	private String weaponPoints;
	private String supplyPoints;
	private String supplies;
	//not in database
	private int isChange;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public String getStartPoints() {
		return startPoints;
	}
	public void setStartPoints(String startPoints) {
		this.startPoints = startPoints;
	}
	public String getBlastPoints() {
		return blastPoints;
	}
	public void setBlastPoints(String blastPoints) {
		this.blastPoints = blastPoints;
	}
	public String getFlagPoints() {
		return flagPoints;
	}
	public void setFlagPoints(String flagPoints) {
		this.flagPoints = flagPoints;
	}
	public String getWeaponPoints() {
		return weaponPoints;
	}
	public void setWeaponPoints(String weaponPoints) {
		this.weaponPoints = weaponPoints;
	}
	public String getSupplyPoints() {
		return supplyPoints;
	}
	public void setSupplyPoints(String supplyPoints) {
		this.supplyPoints = supplyPoints;
	}
	public String getSupplies() {
		return supplies;
	}
	public void setSupplies(String supplies) {
		this.supplies = supplies;
	}
	
	@Override
	public String toString() {
	    return ReflectionToStringBuilder.toString(this);
	}
	public int getIsChange() {
		return isChange;
	}
	public void setIsChange(int isChange) {
		this.isChange = isChange;
	}
	
}
