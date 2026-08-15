/**
 * 
 */
package com.pearl.o2o.pojo;

import java.util.List;

/**
 * @author lifengyang
 * 
 */
public class GrowthMissionVo implements Comparable<GrowthMissionVo>{
	private int id;
	private String icon;
	private String title;
	private String desc;
	private int target;
	private int number;
	private byte status;
	private byte award;
	private byte type;
	private byte needaward;
	private byte isnew;
	private byte upgrade;
	private int money;
	private int exp;
	private int vipmoney;
	private int vipexp;
	private List<AwardItemVo> awardItemVos;
	private int isMain;

	public GrowthMissionVo(int id, String icon,String title, String desc, int target, int number, byte status, byte award, byte isnew, int money, int exp,List<AwardItemVo> awardItemVos) {
		this.id = id;
		this.icon = icon;
		this.title = title;
		this.desc = desc;
		this.target = target;
		this.number = number;
		this.status = status;
		this.award = award;
		this.isnew = isnew;
		this.money = money;
		this.exp = exp;
		this.awardItemVos =awardItemVos;
	}

	public GrowthMissionVo(int money, int exp, int vipmoney, int vipexp, byte upgrade,List<AwardItemVo> awardItemVos) {
		this.money = money;
		this.exp = exp;
		this.vipmoney = vipmoney;
		this.vipexp = vipexp;
		this.upgrade = upgrade;
		this.awardItemVos =awardItemVos;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public int getTarget() {
		return target;
	}

	public void setTarget(int target) {
		this.target = target;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public byte getStatus() {
		return status;
	}

	public void setStatus(byte status) {
		this.status = status;
	}

	public byte getIsnew() {
		return isnew;
	}

	public void setIsnew(byte isnew) {
		this.isnew = isnew;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public int getExp() {
		return exp;
	}

	public void setExp(int exp) {
		this.exp = exp;
	}

	

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public byte getAward() {
		return award;
	}

	public void setAward(byte award) {
		this.award = award;
	}

	public byte getType() {
		return type;
	}

	public void setType(byte type) {
		this.type = type;
	}

	public byte getNeedaward() {
		return needaward;
	}

	public void setNeedaward(byte needaward) {
		this.needaward = needaward;
	}

	public int getVipmoney() {
		return vipmoney;
	}

	public void setVipmoney(int vipmoney) {
		this.vipmoney = vipmoney;
	}

	public int getVipexp() {
		return vipexp;
	}

	public void setVipexp(int vipexp) {
		this.vipexp = vipexp;
	}

	public byte getUpgrade() {
		return upgrade;
	}

	public void setUpgrade(byte upgrade) {
		this.upgrade = upgrade;
	}

	public List<AwardItemVo> getAwardItemVos() {
		return awardItemVos;
	}

	public void setAwardItemVos(List<AwardItemVo> awardItemVos) {
		this.awardItemVos = awardItemVos;
	}

	public int getIsMain() {
		return isMain;
	}

	public void setIsMain(int isMain) {
		this.isMain = isMain;
	}

	@Override
	public int compareTo(GrowthMissionVo o) {
		return  o.getStatus() - this.getStatus();
	}
	
}
