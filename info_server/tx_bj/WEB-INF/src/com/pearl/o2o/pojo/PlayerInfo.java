package com.pearl.o2o.pojo;


public class PlayerInfo extends BaseMappingBean<PlayerInfo> {
	private static final long serialVersionUID = 2173121343890561106L;
	private int playerId;
	private int xunleiPoint=0;
	public int getPlayerId() {
		return playerId;
	}
	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}
	public int getXunleiPoint() {
		return xunleiPoint;
	}
	public void setXunleiPoint(int xunleiPoint) {
		this.xunleiPoint = xunleiPoint;
	}
}