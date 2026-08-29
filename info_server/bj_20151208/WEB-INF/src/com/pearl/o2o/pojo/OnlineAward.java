package com.pearl.o2o.pojo;

public class OnlineAward extends BaseMappingBean<OnlineAward> {

	private static final long serialVersionUID = 5216955331704824144L;


	private int itemId;
	private String itemName;
	private int level;//级别 1,2,3...
	private int type;//类型 1：在线时长奖励，2：签到累计奖励，3：神秘锦囊.....
	private int unit;
	private int unitType;
	private int weight;//权重
	private int music;//音效 0：没有 1,2,3...
	private SysItem sysItem;
	private int index;
	private int rareLevel;
	public SysItem getSysItem() {
		return sysItem;
	}
	public void setSysItem(SysItem sysItem) {
		this.sysItem = sysItem;
	}
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
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
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public int getMusic() {
		return music;
	}
	public void setMusic(int music) {
		this.music = music;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public int getRareLevel() {
		if(sysItem==null)
			return 0;
		rareLevel = type==1?unit*sysItem.getRareLevel():sysItem.getRareLevel();
		return rareLevel;
	}
	public void setRareLevel(int rareLevel) {
		this.rareLevel = rareLevel;
	}
	
	
}
