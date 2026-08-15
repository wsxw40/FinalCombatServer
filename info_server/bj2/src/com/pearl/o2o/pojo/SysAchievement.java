package com.pearl.o2o.pojo;

public class SysAchievement extends BaseMappingBean<SysAchievement> {

	private static final long serialVersionUID = -6084718780609211563L;

	private String name;
	private String description;
	private String descriptionCN;
	private String title;
	private int type;
	private int number;
	private int color;
	private int characterId;
	private int action;
	private int statType;//是否累计
	private String gift;
	private String parent;
	//	private List<SysItem> sysItemList;
	//	private SysItem sysItem;
	private int group;
	private String isDeleted;

	public int getGroup() {
		return group;
	}

	public void setGroup(int group) {
		this.group = group;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
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

	public String getName() {
		return "<AN" + this.getId() + "^0>";
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return "<AD" + this.getId() + "^0>";
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTitle() {
		return this.title;
	}

	public String getTitleCode() {
		return "<AN" + this.getId() + "^0>";
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

	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

	public String getGift() {
		return gift;
	}

	public void setGift(String gift) {
		this.gift = gift;
	}

	public String getDescriptionCN() {
		return descriptionCN;
	}

	public void setDescriptionCN(String descriptionCN) {
		this.descriptionCN = descriptionCN;
	}

	public String getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}

	//	public List<SysItem> getSysItemList() {
	//		return sysItemList;
	//	}
	//
	//	public void setSysItemList(List<SysItem> sysItemList) {
	//		this.sysItemList = sysItemList;
	//	}
	//
	//	public SysItem getSysItem() {
	//		return sysItem;
	//	}
	//
	//	public void setSysItem(SysItem sysItem) {
	//		this.sysItem = sysItem;
	//	}

}
