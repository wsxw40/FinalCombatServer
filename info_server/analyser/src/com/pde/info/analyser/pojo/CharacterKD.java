package com.pde.info.analyser.pojo;

import java.util.Date;

public class CharacterKD {
	
	private int id;
	private int characterId;
	private int characterKill;
	private int characterDead;
	private int levelId;
	private String statisticDate;
	private Date createdate;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCharacterId() {
		return characterId;
	}
	public void setCharacterId(int characterId) {
		this.characterId = characterId;
	}
	public int getCharacterKill() {
		return characterKill;
	}
	public void setCharacterKill(int characterKill) {
		this.characterKill = characterKill;
	}
	public int getCharacterDead() {
		return characterDead;
	}
	public void setCharacterDead(int characterDead) {
		this.characterDead = characterDead;
	}
	public String getStatisticDate() {
		return statisticDate;
	}
	public void setStatisticDate(String statisticDate) {
		this.statisticDate = statisticDate;
	}
	public Date getCreatedate() {
		return createdate;
	}
	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}
	public int getLevelId() {
		return levelId;
	}
	public void setLevelId(int levelId) {
		this.levelId = levelId;
	}
}
