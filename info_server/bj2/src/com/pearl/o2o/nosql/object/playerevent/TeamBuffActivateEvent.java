package com.pearl.o2o.nosql.object.playerevent;

import com.pearl.o2o.pojo.SysItem;
import com.pearl.o2o.utils.CommonUtil;
import com.pearl.o2o.utils.DateUtil;

public class TeamBuffActivateEvent extends BasePlayerEvent {
	private String itemStr;

	public TeamBuffActivateEvent(int playerId,String playerName,SysItem sysItem) {
//		super("激活了战队Buff:" + sysItem.getDisplayName(),DateUtil.getCurrentTimeStr(), playerId, playerName);
		super(CommonUtil.messageFormatI18N(EventConstants.I18NEventConstantsCode.ACTIVE_TEAM_BUF_MSG.getCode(), sysItem.getDisplayName()),DateUtil.getCurrentTimeStr(), playerId, playerName);
		this.itemStr = "{" +sysItem.getType()+ ",\""+ sysItem.getDisplayName() + "\",\""
		+ sysItem.getName() + "\"," + sysItem.getDamange()+ "," + sysItem.getShootSpeed()+ ","
		+ sysItem.getWAmmoOneClip()+ "," + sysItem.getWAmmoCount() + ","+sysItem.getGunProperty().getColor()+ ","+sysItem.getSeq()+",},";
	}
	
	
	public TeamBuffActivateEvent(String content, int playerId,String playerName, String itemStr) {
		super(playerId, playerName,DateUtil.getCurrentTimeStr(), content);
		this.itemStr = itemStr;
	}
	
	public String getItemStr() {
		return itemStr;
	}
	
	@Override
	public int getType() {
		return BasePlayerEvent.EventType.TEAMBUFFACTIVE.ordinal();
	}
}
