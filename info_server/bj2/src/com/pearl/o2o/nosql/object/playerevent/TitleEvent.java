package com.pearl.o2o.nosql.object.playerevent;

import com.pearl.o2o.pojo.PlayerAchievement;
import com.pearl.o2o.utils.CommonUtil;

public class TitleEvent extends BasePlayerEvent {
	private String itemStr;
	
	public TitleEvent(String time, int playerId, String playerName, PlayerAchievement pa) {
//		super( "解锁并获得了\'" + pa.getAchievement().getTitle()+"\'"+"称号",time, playerId, playerName);
		super( CommonUtil.messageFormatI18N(EventConstants.I18NEventConstantsCode.UNLOCK_AND_TITLE.getCode(),pa.getAchievement().getTitleCode() ),time, playerId, playerName);
		this.itemStr = "{" +pa.getAchievement().getId()+ ",\""+ pa.getAchievement().getTitleCode() + "\",\""
				+ pa.getAchievement().getDescription() + "\""  + ",},";
	}
	
	public TitleEvent(String time, String content,int playerId, String playerName,String itemStr) {
		super(playerId, playerName,time, content);
		this.itemStr = itemStr;
	}
	
	public String getItemStr() {
		return itemStr;
	}

	@Override
	public int getType() {
		return BasePlayerEvent.EventType.TITLE.ordinal();
	}
}
