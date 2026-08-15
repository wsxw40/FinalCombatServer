package com.pearl.o2o.pojo;

import java.io.Serializable;

public class StrengthenAppend implements Serializable{

	private static final long serialVersionUID = -3330048645907227665L;
	private int id;
	private int level;
	private int type;//1 武器，2 衣服，3 帽子，4 配饰
	private double property1;//属性1加成，
	private double property2;//属性2加成，
	private int streNum;//所需强化部件个数，
	private int streGR;//强化所需C币，
	private double winRate;//成功概率，	
	private double falseKeepRate;//失败不掉级概率，
	private double falseFallRate;//失败掉级概率，
	private double falseRuinRate;//失败爆装概率，
	private double holeWinRate;//开孔的成功率，
	private double switchFallRate;//转换掉1级概率
	//TODO zlm_7_15装备属性start================================================================================
	private double property1_db=0;//属性1加成的UI显示
	private double property2_db=0;//属性2加成的UI显示
	public double getProperty1_db() {
		return property1_db;
	}
	public void setProperty1_db(double property1_db) {
		this.property1_db = property1_db;
	}
	public double getProperty2_db() {
		return property2_db;
	}
	public void setProperty2_db(double property2_db) {
		this.property2_db = property2_db;
	}
	//TODO zlm_7_15装备属性end==========
	private int isChange;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public void setSwitchFallRate(float switchFallRate) {
		this.switchFallRate = switchFallRate;
	}
	public int getIsChange() {
		return isChange;
	}
	public void setIsChange(int isChange) {
		this.isChange = isChange;
	}
	public double getProperty1() {
		return property1;
	}
	public void setProperty1(double property1) {
		this.property1 = property1;
	}
	public double getProperty2() {
		return property2;
	}
	public void setProperty2(double property2) {
		this.property2 = property2;
	}
	public int getStreNum() {
		return streNum;
	}
	public void setStreNum(int streNum) {
		this.streNum = streNum;
	}
	public int getStreGR() {
		return streGR;
	}
	public void setStreGR(int streGR) {
		this.streGR = streGR;
	}
	public double getWinRate() {
		return winRate;
	}
	public void setWinRate(double winRate) {
		this.winRate = winRate;
	}
	public double getFalseKeepRate() {
		return falseKeepRate;
	}
	public void setFalseKeepRate(double falseKeepRate) {
		this.falseKeepRate = falseKeepRate;
	}
	public double getFalseFallRate() {
		return falseFallRate;
	}
	public void setFalseFallRate(double falseFallRate) {
		this.falseFallRate = falseFallRate;
	}
	public double getFalseRuinRate() {
		return falseRuinRate;
	}
	public void setFalseRuinRate(double falseRuinRate) {
		this.falseRuinRate = falseRuinRate;
	}
	public double getHoleWinRate() {
		return holeWinRate;
	}
	public void setHoleWinRate(double holeWinRate) {
		this.holeWinRate = holeWinRate;
	}
	public double getSwitchFallRate() {
		return switchFallRate;
	}
	public void setSwitchFallRate(double switchFallRate) {
		this.switchFallRate = switchFallRate;
	}
}
