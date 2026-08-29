package com.pearl.o2o.nosql.object.playerevent;

import com.pearl.o2o.utils.CommonUtil;

public class KillEvent extends BasePlayerLogEvent {

	public KillEvent(int value,long time, int playerId,
			String playerName) {
//		super("击杀了"+value+"人。",time,  playerId, playerName,BasePlayerLogEvent.EventType.KILL.ordinal());
		super(CommonUtil.messageFormatI18N(EventConstants.I18NEventConstantsCode.KILL_COUNT_MSG.getCode(), value),time,  playerId, playerName,BasePlayerLogEvent.EventType.KILL.ordinal());
		// TODO Auto-generated constructor stub
	}
	@Override
	public int getType() {
		return BasePlayerLogEvent.EventType.KILL.ordinal();
	}
}
