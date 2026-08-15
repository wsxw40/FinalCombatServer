package com.pearl.o2o.nosql.object.playerevent;

import com.pearl.o2o.pojo.SysItem;
import com.pearl.o2o.utils.CommonUtil;

public class PurchaseEvent extends BasePlayerEvent {
	private String itemStr;
	
	public PurchaseEvent(String time, int playerId, String playerName, SysItem sysItem) {
//		super( "购买了" + sysItem.getDisplayName(),time, playerId, playerName);
		super( CommonUtil.messageFormatI18N(EventConstants.I18NEventConstantsCode.BUY_MSG.getCode(),sysItem.getDisplayName()),time, playerId, playerName);
		//TODO REFACTOR
		this.itemStr = "{" +sysItem.getType()+ ",\""+ sysItem.getDisplayName() + "\",\""
				+ sysItem.getName() + "\"," + sysItem.getDamange()+ "," + sysItem.getShootSpeed()+ ","
				+ sysItem.getWAmmoOneClip()+ "," + sysItem.getWAmmoCount() + ","+sysItem.getGunProperty().getColor()+ ","+sysItem.getSeq()+",},";
	}
	
	public PurchaseEvent(String time, String content,int playerId, String playerName,String itemStr) {
		super(playerId, playerName,time, content);
		this.itemStr = itemStr;
	}
	
	public String getItemStr() {
		return itemStr;
	}

	@Override
	public int getType() {
		return BasePlayerEvent.EventType.PURCHASE.ordinal();
	}
}
