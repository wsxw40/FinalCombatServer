package com.pearl.o2o.nosql.object.playerevent;

import com.pearl.o2o.pojo.Player;
import com.pearl.o2o.utils.CommonUtil;
import com.pearl.o2o.utils.DateUtil;

public class AddFriendEvent extends BasePlayerEvent {

	public AddFriendEvent(Player p, int playerId,String playerName) {
		//TODO
//		super(CommonUtil.messageFormat(EventConstants.MAKE_FRIEDS_WHIT_MSG, p.getName()),DateUtil.getCurrentTimeStr(), playerId, playerName);
		super(CommonUtil.messageFormatI18N(EventConstants.I18NEventConstantsCode.MAKE_FRIEDS_WHIT_MSG.getCode(), p.getName()),DateUtil.getCurrentTimeStr(), playerId, playerName);
	}
	
	@Override
	public int getType() {
		return BasePlayerEvent.EventType.ADDFRIEND.ordinal();
	}
}
