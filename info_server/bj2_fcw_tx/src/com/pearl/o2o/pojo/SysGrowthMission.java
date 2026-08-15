package com.pearl.o2o.pojo;

import java.util.List;
import java.util.Map;

/**
 * @author lifengyang
 * 
 */
public class SysGrowthMission extends BaseMappingBean<SysGrowthMission> {

	private static final long serialVersionUID = -4716759307287244599L;
	private String icon;
	private String title;
	private String description;
	private String descriptionCN;
	private int number;
	private int rank;
	private int parent;
	private int experience;
	private int money;
	private int strengthen;
	private int honor;
	private int addSucess;
	private byte defaultopen;
	private int openmodule;
	private String gift;
	private int amount;
	private int unit;
	private int unitType;
	private byte status;
	private List<AwardItemVo> awardItemVos;
	
	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public SysGrowthMission() {
	}

	public String getDescription() {
		return "<GD" + this.getId()+"^0>";
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public int getParent() {
		return parent;
	}

	public void setParent(int parent) {
		this.parent = parent;
	}

	public String getGift() {
		return gift;
	}

	public void setGift(String gift) {
		this.gift = gift;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int getUnit() {
		return unit;
	}

	public void setUnit(int unit) {
		this.unit = unit;
	}

	public int getUnitType() {
		return unitType;
	}

	public void setUnitType(int unitType) {
		this.unitType = unitType;
	}

	public byte getStatus() {
		return status;
	}

	public void setStatus(byte status) {
		this.status = status;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public int getStrengthen() {
		return strengthen;
	}

	public void setStrengthen(int strengthen) {
		this.strengthen = strengthen;
	}

	public int getHonor() {
		return honor;
	}

	public void setHonor(int honor) {
		this.honor = honor;
	}

	public byte getDefaultopen() {
		return defaultopen;
	}

	public void setDefaultopen(byte defaultopen) {
		this.defaultopen = defaultopen;
	}

	public int getOpenmodule() {
		return openmodule;
	}

	public void setOpenmodule(int openmodule) {
		this.openmodule = openmodule;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getExperience() {
		return experience;
	}

	public void setExperience(int experience) {
		this.experience = experience;
	}

	public int getAddSucess() {
		return addSucess;
	}

	public void setAddSucess(int addSucess) {
		this.addSucess = addSucess;
	}

	public List<AwardItemVo> getAwardItemVos() {
		return awardItemVos;
	}

	public void setAwardItemVos(List<AwardItemVo> awardItemVos) {
		this.awardItemVos = awardItemVos;
	}

	public String getTitle() {
		return "<GT" + this.getId()+"^0>";
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescriptionCN() {
		return descriptionCN;
	}

	public void setDescriptionCN(String descriptionCN) {
		this.descriptionCN = descriptionCN;
	}
	
}
