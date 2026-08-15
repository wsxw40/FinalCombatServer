package com.pearl.o2o.nosql.object.playerevent;

import com.pearl.o2o.utils.CommonUtil;

public class DeadEvent extends BasePlayerLogEvent {

	public DeadEvent(int value,long time, int playerId,
			String playerName) {
//		super("被杀死"+value+"次",time,  playerId, playerName,BasePlayerLogEvent.EventType.DEAD.ordinal());
		super(CommonUtil.messageFormatI18N(EventConstants.I18NEventConstantsCode.KILLED_BY_MSG.getCode(), value),time,  playerId, playerName,BasePlayerLogEvent.EventType.DEAD.ordinal());
		
		// TODO Auto-generated constructor stub
	}
	@Override
	public int getType() {
		return BasePlayerLogEvent.EventType.DEAD.ordinal();
	}
}
