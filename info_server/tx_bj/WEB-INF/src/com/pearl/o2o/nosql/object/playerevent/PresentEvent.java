package com.pearl.o2o.nosql.object.playerevent;


import com.pearl.o2o.pojo.Player;
import com.pearl.o2o.pojo.SysItem;
import com.pearl.o2o.utils.CommonUtil;

public class PresentEvent extends BasePlayerEvent {
	private String itemStr;
	public PresentEvent(String time,Player player,String receiverName, SysItem sysItem) {
//		super("赠送给好友：" + receiverName + "一个物品："+sysItem.getDisplayName(),time, player.getId(), player.getName());
		super(CommonUtil.messageFormatI18N(EventConstants.I18NEventConstantsCode.PRESENT_FRIEND_SYSITEM_MSG.getCode(), receiverName,sysItem.getDisplayName()),time, player.getId(), player.getName());
		this.itemStr = "{" +sysItem.getType()+ ",\""+ sysItem.getDisplayName() + "\",\""
		+ sysItem.getName() + "\"," + sysItem.getDamange()+ "," + sysItem.getShootSpeed()+ ","
		+ sysItem.getWAmmoOneClip()+ "," + sysItem.getWAmmoCount() + ","+sysItem.getGunProperty().getColor()+ ","+sysItem.getSeq()+ ",},";
	}
	public PresentEvent(String time, String content,int playerId, String playerName,String itemStr) {
		super(playerId, playerName,time, content);
		this.itemStr = itemStr;
	}
	@Override
	public int getType() {
		return BasePlayerEvent.EventType.PRESENT.ordinal();
	}
	public String getItemStr() {
		return itemStr;
	}
}
