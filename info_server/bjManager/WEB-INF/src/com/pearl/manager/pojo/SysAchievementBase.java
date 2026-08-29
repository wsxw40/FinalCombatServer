package com.pearl.manager.pojo;

public class SysAchievementBase extends BaseMappingBean<SysAchievementBase>{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7082137714542945513L;
	private String ids;
	private String title;
	private int type;
	private String numbers;
	private int totalLevel;
	private int characterId;
	private int group;
	public String getTitle() {
		return title;
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
	public String getNumbers() {
		return numbers;
	}
	public void setNumbers(String numbers) {
		this.numbers = numbers;
	}
	public int getTotalLevel() {
		return totalLevel;
	}
	public void setTotalLevel(int totalLevel) {
		this.totalLevel = totalLevel;
	}
	public int getCharacterId() {
		return characterId;
	}
	public void setCharacterId(int characterId) {
		this.characterId = characterId;
	}
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
	public int getGroup() {
		return group;
	}
	public void setGroup(int group) {
		this.group = group;
	}
}
