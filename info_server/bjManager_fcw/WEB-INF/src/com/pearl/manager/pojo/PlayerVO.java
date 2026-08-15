package com.pearl.manager.pojo;

import java.io.Serializable;
import java.util.Date;

public class PlayerVO implements Serializable {
	private static final long serialVersionUID = -323371513342174379L;
	private Integer 	id;
	private String 		userName;
	private	Integer		sysCharacterId;
	private String		gender;
	private String		name;
	private	Integer		exp 			= 0;
	private	Integer		rank 			= 1;
	private Integer		wPackSize 		= 2;
	private Integer		cPackSize		= 1;
	private	Integer		gPoint 			= 0;
	private	Integer		gScore ;
	private	Integer		credit 			= 0;
	private	Integer		voucher 		= 0;
	private Integer 	isChange;
	private String 		profile;
	private String 		deleted;
	private String 		blackWhite;
	private String		characters;
	private String 		tutorial;
	private String 		lastLogin;
	private int 		isOnline;
	private String 		lastLogout;
	private String 		createTime;
	private String character1W;
	private String character2W;
	private String character3W;
	private String character4W;
	private String character5W;
	private String character6W;
	
	public String getDeleted() {
		return deleted;
	}
	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}
	public String getBlackWhite() {
		return blackWhite;
	}
	public void setBlackWhite(String blackWhite) {
		this.blackWhite = blackWhite;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Integer getSysCharacterId() {
		return sysCharacterId;
	}
	public void setSysCharacterId(Integer sysCharacterId) {
		this.sysCharacterId = sysCharacterId;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getExp() {
		return exp;
	}
	public void setExp(Integer exp) {
		this.exp = exp;
	}
	public Integer getRank() {
		return rank;
	}
	public void setRank(Integer rank) {
		this.rank = rank;
	}
	public Integer getWPackSize() {
		return wPackSize;
	}
	public void setWPackSize(Integer packSize) {
		wPackSize = packSize;
	}
	public Integer getCPackSize() {
		return cPackSize;
	}
	public void setCPackSize(Integer packSize) {
		cPackSize = packSize;
	}
	public Integer getGPoint() {
		return gPoint;
	}
	public void setGPoint(Integer point) {
		gPoint = point;
	}
	public Integer getCredit() {
		return credit;
	}
	public void setCredit(Integer credit) {
		this.credit = credit;
	}
	
	public Integer getIsChange() {
		return isChange;
	}
	public void setIsChange(Integer isChange) {
		this.isChange = isChange;
	}
	public String getProfile() {
		return profile;
	}
	public void setProfile(String profile) {
		this.profile = profile;
	}
	public Integer getVoucher() {
		return voucher;
	}
	public void setVoucher(Integer voucher) {
		this.voucher = voucher;
	}
	public Integer getGScore() {
		return gScore;
	}
	public void setGScore(Integer score) {
		gScore = score;
	}
	public String getCharacters() {
		return characters;
	}
	public void setCharacters(String characters) {
		this.characters = characters;
	}
	public String getCharacter1W() {
		return character1W;
	}
	public void setCharacter1W(String character1W) {
		this.character1W = character1W;
	}
	public String getCharacter2W() {
		return character2W;
	}
	public void setCharacter2W(String character2W) {
		this.character2W = character2W;
	}
	public String getCharacter3W() {
		return character3W;
	}
	public void setCharacter3W(String character3W) {
		this.character3W = character3W;
	}
	public String getCharacter4W() {
		return character4W;
	}
	public void setCharacter4W(String character4W) {
		this.character4W = character4W;
	}
	public String getCharacter5W() {
		return character5W;
	}
	public void setCharacter5W(String character5W) {
		this.character5W = character5W;
	}
	public String getCharacter6W() {
		return character6W;
	}
	public void setCharacter6W(String character6W) {
		this.character6W = character6W;
	}
	public String getTutorial() {
		return tutorial;
	}
	public void setTutorial(String tutorial) {
		this.tutorial = tutorial;
	}
	public String getLastLogin() {
		return lastLogin;
	}
	public void setLastLogin(String lastLogin) {
		this.lastLogin = lastLogin;
	}
	public int getIsOnline() {
		return isOnline;
	}
	public void setIsOnline(int isOnline) {
		this.isOnline = isOnline;
	}
	public String getLastLogout() {
		return lastLogout;
	}
	public void setLastLogout(String lastLogout) {
		this.lastLogout = lastLogout;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
}
