package com.pearl.o2o.nosql.object.playerevent;

import com.pearl.o2o.utils.CommonUtil;

public class GetExpEvent extends BasePlayerLogEvent {

	public GetExpEvent(int value,long time,  int playerId,
			String playerName) {
//		super("获得了经验"+value,time, playerId, playerName,BasePlayerLogEvent.EventType.GETEXP.ordinal());
		super(CommonUtil.messageFormatI18N(EventConstants.I18NEventConstantsCode.GAIN_EXP_MSG.getCode(), value),time, playerId, playerName,BasePlayerLogEvent.EventType.GETEXP.ordinal());
		// TODO Auto-generated constructor stub
	}
	@Override
	public int getType() {
		return BasePlayerLogEvent.EventType.GETEXP.ordinal();
	}
}
