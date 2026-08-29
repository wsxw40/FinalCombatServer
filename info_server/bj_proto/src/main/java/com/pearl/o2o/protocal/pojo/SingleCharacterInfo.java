/**
 * 
 */
package com.pearl.o2o.protocal.pojo;

/**
 * @author lifengyang
 *
 */
public class SingleCharacterInfo {
	int characterId;
	int kill;
	int dead;
	int controlNum;
	int revengeNum;
	int assistNum;
	int grenadeKill;
	int knifeKill;
	int usedCount;
	int headshot;
	int maxDamage;
	int boostKill;
	int sustainKill;
	int healthNum;
	
	
	
	public SingleCharacterInfo() {
	}
	public SingleCharacterInfo(int characterId,int kill,int dead,int controlNum,int revengeNum,int assistNum,int knifeKill,
			int used,int grenadeKill,int headshot,int maxDamage,int boostKill,int sustainKill,int healthNum) {
		this.characterId=characterId;
		this.kill=kill;
		this.dead=dead;
		this.controlNum=controlNum;
		this.revengeNum=revengeNum;
		this.assistNum=assistNum;
		this.knifeKill=knifeKill;
		this.usedCount=used;
		this.grenadeKill=grenadeKill;
		this.headshot=headshot;
		this.maxDamage=maxDamage;
		this.boostKill=boostKill;
		this.sustainKill=sustainKill;
		this.healthNum=healthNum;
	}
	public int getCharacterId() {
		return characterId;
	}
	public void setCharacterId(int characterId) {
		this.characterId = characterId;
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
	public int getUsedCount() {
		return usedCount;
	}
	public void setUsedCount(int used) {
		this.usedCount = used;
	}
	public int getGrenadeKill() {
		return grenadeKill;
	}
	public void setGrenadeKill(int grenadeKill) {
		this.grenadeKill = grenadeKill;
	}
	public int getHeadshot() {
		return headshot;
	}
	public void setHeadshot(int headshot) {
		this.headshot = headshot;
	}
	public int getMaxDamage() {
		return maxDamage;
	}
	public void setMaxDamage(int maxDamage) {
		this.maxDamage = maxDamage;
	}
	public int getBoostKill() {
		return boostKill;
	}
	public void setBoostKill(int boostKill) {
		this.boostKill = boostKill;
	}
	public int getSustainKill() {
		return sustainKill;
	}
	public void setSustainKill(int sustainKill) {
		this.sustainKill = sustainKill;
	}
	public int getHealthNum() {
		return healthNum;
	}
	public void setHealthNum(int healthNum) {
		this.healthNum = healthNum;
	}
	
	@Override
	public String toString() {
		return "SingleCharacterInfo [characterId=" + characterId + ", kill=" + kill + ", dead=" + dead + ", controlNum=" + controlNum + ", revengeNum=" + revengeNum
				+ ", assistNum=" + assistNum + ", grenadeKill=" + grenadeKill + ", knifeKill=" + knifeKill + ", usedCount=" + usedCount + ", headshot=" + headshot + ", maxDamage="
				+ maxDamage + ", boostKill=" + boostKill + ", sustainKill=" + sustainKill + ", healthNum=" + healthNum + "]";
	}
	
}
