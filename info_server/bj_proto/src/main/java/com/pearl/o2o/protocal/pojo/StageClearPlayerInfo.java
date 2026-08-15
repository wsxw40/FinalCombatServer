/**
 * 
 */
package com.pearl.o2o.protocal.pojo;

import java.util.Arrays;


/**
 * @author lifengyang
 *
 */
public class StageClearPlayerInfo {
	private int id;
	private String name;
	private int side;
	private int onlineMinutes;
	private int offlineMinutes;
	private byte decrNum;
	private int isWiner;
	private int isVip;
	private int internetCafe=0;
	private String headGif;
	private int currExp;
	private int currRank;
	private int currGp;
	
	private int rank;//currented rank
	private int exp;  // exp increment
	private int gp;// gp increment
	
	private int score;
	private int kill;			
	private int dead;	
	private int health;
	private int controlNum;
	private int revengeNum;
	private int assistNum;
	private int knifeKill;	
	private int maxHeadshotNum;
	private int maxHeadshotCharacter;
	private int maxKillNum;
	private int maxKillNumCharacter;
	private int maxHealthNum;
	private int maxHealthNumCharacter;
	private int maxDamageNum;
	private int maxDamageNumCharacter;
	private int maxLiveTime;
	private int maxLiveTimeCharacter;
	private int bottleHpNum;
	private int bulletNum;
	private int useTotal=0;
	
	private int start;
	private int end;
	private int isFirstKill;
	private int isFirstDead;
	private int[] killCharacters;
	private SingleCharacterInfo[] characterInfos;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getSide() {
		return side;
	}
	public void setSide(int side) {
		this.side = side;
	}
	public int getOnlineMinutes() {
		return onlineMinutes;
	}
	public void setOnlineMinutes(int onlineMinutes) {
		this.onlineMinutes = onlineMinutes;
	}
	public int getOfflineMinutes() {
		return offlineMinutes;
	}
	public void setOfflineMinutes(int offlineMinutes) {
		this.offlineMinutes = offlineMinutes;
	}
	public int getIsWiner() {
		return isWiner;
	}
	public void setIsWiner(int isWiner) {
		this.isWiner = isWiner;
	}
	public int getIsVip() {
		return isVip;
	}
	public void setIsVip(int isVip) {
		this.isVip = isVip;
	}
	public int getInternetCafe() {
		return internetCafe;
	}
	public void setInternetCafe(int internetCafe) {
		this.internetCafe = internetCafe;
	}
	public String getHeadGif() {
		return headGif;
	}
	public void setHeadGif(String headGif) {
		this.headGif = headGif;
	}
	public int getCurrExp() {
		return currExp;
	}
	public void setCurrExp(int currExp) {
		this.currExp = currExp;
	}
	public int getCurrRank() {
		return currRank;
	}
	public void setCurrRank(int currRank) {
		this.currRank = currRank;
	}
	public int getCurrGp() {
		return currGp;
	}
	public void setCurrGp(int currGp) {
		this.currGp = currGp;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	public int getExp() {
		return exp;
	}
	public void setExp(int exp) {
		this.exp = exp;
	}
	public int getGp() {
		return gp;
	}
	public void setGp(int gp) {
		this.gp = gp;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public int getKill() {
		return kill;
	}
	public void setKill(int kill) {
		this.kill = kill;
	}
	public int getDead() {
		return dead;
	}
	public void setDead(int dead) {
		this.dead = dead;
	}
	public int getHealth() {
		return health;
	}
	public void setHealth(int health) {
		this.health = health;
	}
	public int getControlNum() {
		return controlNum;
	}
	public void setControlNum(int controlNum) {
		this.controlNum = controlNum;
	}
	public int getRevengeNum() {
		return revengeNum;
	}
	public void setRevengeNum(int revengeNum) {
		this.revengeNum = revengeNum;
	}
	public int getAssistNum() {
		return assistNum;
	}
	public void setAssistNum(int assistNum) {
		this.assistNum = assistNum;
	}
	public int getKnifeKill() {
		return knifeKill;
	}
	public void setKnifeKill(int knifeKill) {
		this.knifeKill = knifeKill;
	}
	public int getMaxHeadshotNum() {
		return maxHeadshotNum;
	}
	public void setMaxHeadshotNum(int maxHeadshotNum) {
		this.maxHeadshotNum = maxHeadshotNum;
	}
	public int getMaxHeadshotCharacter() {
		return maxHeadshotCharacter;
	}
	public void setMaxHeadshotCharacter(int maxHeadshotCharacter) {
		this.maxHeadshotCharacter = maxHeadshotCharacter;
	}
	public int getMaxKillNum() {
		return maxKillNum;
	}
	public void setMaxKillNum(int maxKillNum) {
		this.maxKillNum = maxKillNum;
	}
	public int getMaxKillNumCharacter() {
		return maxKillNumCharacter;
	}
	public void setMaxKillNumCharacter(int maxKillNumCharacter) {
		this.maxKillNumCharacter = maxKillNumCharacter;
	}
	public int getMaxHealthNum() {
		return maxHealthNum;
	}
	public void setMaxHealthNum(int maxHealthNum) {
		this.maxHealthNum = maxHealthNum;
	}
	public int getMaxHealthNumCharacter() {
		return maxHealthNumCharacter;
	}
	public void setMaxHealthNumCharacter(int maxHealthNumCharacter) {
		this.maxHealthNumCharacter = maxHealthNumCharacter;
	}
	public int getMaxDamageNum() {
		return maxDamageNum;
	}
	public void setMaxDamageNum(int maxDamageNum) {
		this.maxDamageNum = maxDamageNum;
	}
	public int getMaxDamageNumCharacter() {
		return maxDamageNumCharacter;
	}
	public void setMaxDamageNumCharacter(int maxDamageNumCharacter) {
		this.maxDamageNumCharacter = maxDamageNumCharacter;
	}
	public int getMaxLiveTime() {
		return maxLiveTime;
	}
	public void setMaxLiveTime(int maxLiveTime) {
		this.maxLiveTime = maxLiveTime;
	}
	public int getMaxLiveTimeCharacter() {
		return maxLiveTimeCharacter;
	}
	public void setMaxLiveTimeCharacter(int maxLiveTimeCharacter) {
		this.maxLiveTimeCharacter = maxLiveTimeCharacter;
	}
	public int getBottleHpNum() {
		return bottleHpNum;
	}
	public void setBottleHpNum(int bottleHpNum) {
		this.bottleHpNum = bottleHpNum;
	}
	public int getBulletNum() {
		return bulletNum;
	}
	public void setBulletNum(int bulletNum) {
		this.bulletNum = bulletNum;
	}
	public int getUseTotal() {
		return useTotal;
	}
	public void setUseTotal(int useTotal) {
		this.useTotal = useTotal;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getEnd() {
		return end;
	}
	public void setEnd(int end) {
		this.end = end;
	}
	public int getIsFirstKill() {
		return isFirstKill;
	}
	public void setIsFirstKill(int isFirstKill) {
		this.isFirstKill = isFirstKill;
	}
	public int getIsFirstDead() {
		return isFirstDead;
	}
	public void setIsFirstDead(int isFirstDead) {
		this.isFirstDead = isFirstDead;
	}
	public int[] getKillCharacters() {
		return killCharacters;
	}
	public void setKillCharacters(int[] killCharacters) {
		this.killCharacters = killCharacters;
	}
	public SingleCharacterInfo[] getCharacterInfos() {
		return characterInfos;
	}
	public void setCharacterInfos(SingleCharacterInfo[] characterInfos) {
		this.characterInfos = characterInfos;
	}
	
	public byte getDecrNum() {
		return decrNum;
	}
	public void setDecrNum(byte decrNum) {
		this.decrNum = decrNum;
	}
	@Override
	public String toString() {
		return "StageClearPlayerInfo [id=" + id + ", name=" + name + ", side=" + side + ", onlineMinutes=" + onlineMinutes + ", offlineMinutes=" + offlineMinutes + ", isWiner="
				+ isWiner + ", isVip=" + isVip + ", internetCafe=" + internetCafe + ", headGif=" + headGif + ", currExp=" + currExp + ", currRank=" + currRank + ", currGp="
				+ currGp + ", rank=" + rank + ", exp=" + exp + ", gp=" + gp + ", score=" + score + ", kill=" + kill + ", dead=" + dead + ", health=" + health + ", controlNum="
				+ controlNum + ", revengeNum=" + revengeNum + ", assistNum=" + assistNum + ", knifeKill=" + knifeKill + ", maxHeadshotNum=" + maxHeadshotNum
				+ ", maxHeadshotCharacter=" + maxHeadshotCharacter + ", maxKillNum=" + maxKillNum + ", maxKillNumCharacter=" + maxKillNumCharacter + ", maxHealthNum="
				+ maxHealthNum + ", maxHealthNumCharacter=" + maxHealthNumCharacter + ", maxDamageNum=" + maxDamageNum + ", maxDamageNumCharacter=" + maxDamageNumCharacter
				+ ", maxLiveTime=" + maxLiveTime + ", maxLiveTimeCharacter=" + maxLiveTimeCharacter + ", bottleHpNum=" + bottleHpNum + ", bulletNum=" + bulletNum + ", useTotal="
				+ useTotal + ", start=" + start + ", end=" + end + ", isFirstKill=" + isFirstKill + ", isFirstDead=" + isFirstDead + ", killCharacters="
				+ Arrays.toString(killCharacters) + ", characterInfos=" + Arrays.toString(characterInfos) + "]";
	}
	
}
