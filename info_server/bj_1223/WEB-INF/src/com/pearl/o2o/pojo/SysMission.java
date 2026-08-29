package com.pearl.o2o.pojo;

import java.util.List;

public class SysMission extends BaseMappingBean<SysMission>{
	
	private static final long serialVersionUID = -6084718780609211563L;
	
	private String name;
	private String description;
	private String descriptionCN;
	private String title;
	private String missionTitle;
	private int type;//0:everyday  1:everyweek
	private int simpleTarget;
	private int normalTarget;
	private int hardTarget;
	private int color;
	private int characterId;
	private int action;
	private int statType;
	private String normalItems;
	private String vipItems;
	private List<SysItem> normalItemList;
	private List<SysItem> vipItemList;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return "<MD"+this.getId()+"^1^{0}>";
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getTitle() {
		return "<MT"+ this.getId()+"^0>";
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getSimpleTarget() {
		return simpleTarget;
	}
	public void setSimpleTarget(int simpleTarget) {
		this.simpleTarget = simpleTarget;
	}
	public int getNormalTarget() {
		return normalTarget;
	}
	public void setNormalTarget(int normalTarget) {
		this.normalTarget = normalTarget;
	}
	public int getColor() {
		return color;
	}
	public void setColor(int color) {
		this.color = color;
	}
	public int getCharacterId() {
		return characterId;
	}
	public void setCharacterId(int characterId) {
		this.characterId = characterId;
	}
	public int getAction() {
		return action;
	}
	public void setAction(int action) {
		this.action = action;
	}
	public int getStatType() {
		return statType;
	}
	public void setStatType(int statType) {
		this.statType = statType;
	}
	public String getNormalItems() {
		return normalItems;
	}
	public void setNormalItems(String normalItems) {
		this.normalItems = normalItems;
	}
	public String getVipItems() {
		return vipItems;
	}
	public void setVipItems(String vipItems) {
		this.vipItems = vipItems;
	}
	public List<SysItem> getNormalItemList() {
		return normalItemList;
	}
	public void setNormalItemList(List<SysItem> normalItemList) {
		this.normalItemList = normalItemList;
	}
	public List<SysItem> getVipItemList() {
		return vipItemList;
	}
	public void setVipItemList(List<SysItem> vipItemList) {
		this.vipItemList = vipItemList;
	}
	public String getMissionTitle() {
		return "<MT"+ this.getId()+"^0>";
	}
	public void setMissionTitle(String missionTitle) {
		this.missionTitle = missionTitle;
	}
	public int getHardTarget() {
		return hardTarget;
	}
	public void setHardTarget(int hardTarget) {
		this.hardTarget = hardTarget;
	}
	public String getDescriptionCN() {
		return descriptionCN;
	}
	public void setDescriptionCN(String descriptionCN) {
		this.descriptionCN = descriptionCN;
	}
	
}
