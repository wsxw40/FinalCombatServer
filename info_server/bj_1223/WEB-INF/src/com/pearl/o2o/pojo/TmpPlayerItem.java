package com.pearl.o2o.pojo;

public class TmpPlayerItem {
	private Player player;
	private SysItem sysItem;
	private int quantity;
	
	
	public TmpPlayerItem() {
		super();
	}
	public TmpPlayerItem(Player player, SysItem sysItem,int quantity) {
		super();
		this.player = player;
		this.sysItem = sysItem;
		this.quantity = quantity;
	}
	public Player getPlayer() {
		return player;
	}
	public void setPlayer(Player player) {
		this.player = player;
	}
	public SysItem getSysItem() {
		return sysItem;
	}
	public void setSysItem(SysItem sysItem) {
		this.sysItem = sysItem;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
}
