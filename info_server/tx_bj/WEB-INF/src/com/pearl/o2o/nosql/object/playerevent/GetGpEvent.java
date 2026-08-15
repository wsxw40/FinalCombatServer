package com.pearl.o2o.nosql.object.playerevent;

import com.pearl.o2o.utils.CommonUtil;

public class GetGpEvent extends BasePlayerLogEvent {

	public GetGpEvent(int value,long time,  int playerId,
			String playerName) {
//		super("获得了C币"+value,time, playerId, playerName,BasePlayerLogEvent.EventType.GETGP.ordinal());
		super(CommonUtil.messageFormatI18N(EventConstants.I18NEventConstantsCode.GAIN_CB_MSG.getCode(), value),time, playerId, playerName,BasePlayerLogEvent.EventType.GETGP.ordinal());
		// TODO Auto-generated constructor stub
	}
	@Override
	public int getType() {
		return BasePlayerLogEvent.EventType.GETGP.ordinal();
	}
}
