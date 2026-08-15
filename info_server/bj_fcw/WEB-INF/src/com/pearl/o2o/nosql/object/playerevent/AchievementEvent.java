package com.pearl.o2o.nosql.object.playerevent;

import com.pearl.o2o.pojo.PlayerAchievement;
import com.pearl.o2o.utils.CommonUtil;

public class AchievementEvent extends BasePlayerEvent {
	private String itemStr;
	
	public AchievementEvent(String time, int playerId, String playerName, PlayerAchievement pa) {
//		super( "解锁并获得了\'" + pa.getAchievement().getDescription()+"\'"+"成就",time, playerId, playerName);
//		super( CommonUtil.messageFormat(EventConstants.UNLOCK_AND_ACHIEVEMENT, pa.getAchievement().getDescription()),time, playerId, playerName);
		super( CommonUtil.messageFormatI18N(EventConstants.I18NEventConstantsCode.UNLOCK_AND_ACHIEVEMENT.getCode(), pa.getAchievement().getDescription()),time, playerId, playerName);
		this.itemStr = "{" +pa.getAchievement().getId()+ ",\""+ pa.getAchievement().getName() + "\",\""
				+ pa.getAchievement().getDescription() + "\""  + ",},";
	}
	
	public AchievementEvent(String time, String content,int playerId, String playerName,String itemStr) {
		super(playerId, playerName,time, content);
		this.itemStr = itemStr;
	}
	
	public String getItemStr() {
		return itemStr;
	}

	@Override
	public int getType() {
		return BasePlayerEvent.EventType.ACHIEVEMENT.ordinal();
	}
}
