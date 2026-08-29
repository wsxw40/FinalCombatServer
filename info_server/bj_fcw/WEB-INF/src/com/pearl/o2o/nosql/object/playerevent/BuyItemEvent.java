package com.pearl.o2o.nosql.object.playerevent;

import com.pearl.o2o.pojo.SysItem;
import com.pearl.o2o.utils.CommonUtil;

public class BuyItemEvent extends BasePlayerLogEvent {

	public BuyItemEvent(long time, int playerId,
			String playerName,SysItem si) {
//		super("购买了"+si.getDisplayName(),time,  playerId, playerName,BasePlayerLogEvent.EventType.BUYITEM.ordinal());
		super(CommonUtil.messageFormatI18N(EventConstants.I18NEventConstantsCode.BUY_MSG.getCode(), si.getDisplayName()),time,  playerId, playerName,BasePlayerLogEvent.EventType.BUYITEM.ordinal());
		
		// TODO Auto-generated constructor stub
	}
	@Override
	public int getType() {
		return BasePlayerLogEvent.EventType.BUYITEM.ordinal();
	}
}
