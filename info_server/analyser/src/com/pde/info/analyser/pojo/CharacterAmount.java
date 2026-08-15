package com.pde.info.analyser.pojo;

import java.util.Date;

public class CharacterAmount {
	
	int id;
	int characterId;
	int usingNumber;
	int levelId;
	Date createdate;
	
	
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
	public int getUsingNumber() {
		return usingNumber;
	}
	public void setUsingNumber(int usingNumber) {
		this.usingNumber = usingNumber;
	}
	public int getLevelId() {
		return levelId;
	}
	public void setLevelId(int levelId) {
		this.levelId = levelId;
	}
	public Date getCreatedate() {
		return createdate;
	}
	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

}
