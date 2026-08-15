package com.pearl.o2o.pojo;


public class PlayerTrack extends BaseMappingBean<PlayerTrack>{
	private static final long serialVersionUID = -3237212642385023211L;
	private int playerId;
	private String giftTime;
	private int medalCount;
	private int goldCount;
	private double firstCost;
	private String firstCostTime;
	private String newRenewItem;
	
	public int getPlayerId() {
		return playerId;
	}
	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}
	public String getGiftTime() {
		return giftTime;
	}
	public void setGiftTime(String giftTime) {
		this.giftTime = giftTime;
	}
	public int getMedalCount() {
		return medalCount;
	}
	public void setMedalCount(int medalCount) {
		this.medalCount = medalCount;
	}
	public int getGoldCount() {
		return goldCount;
	}
	public void setGoldCount(int goldCount) {
		this.goldCount = goldCount;
	}
	
	public double getFirstCost() {
		return firstCost;
	}
	public void setFirstCost(double firstCost) {
		this.firstCost = firstCost;
	}
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
	public String getFirstCostTime() {
		return firstCostTime;
	}
	public void setFirstCostTime(String firstCostTime) {
		this.firstCostTime = firstCostTime;
	}
	public String getNewRenewItem() {
		return newRenewItem;
	}
	public void setNewRenewItem(String newRenewItem) {
		this.newRenewItem = newRenewItem;
	}
}
