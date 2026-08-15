package com.pearl.o2o.nosql.object.playerevent;

import com.pearl.o2o.utils.CommonUtil;

public class RoomInEvent extends BasePlayerLogEvent {

	public RoomInEvent(int roomId,long time,  int playerId,
			String playerName) {
//		super("进入房间"+roomId,time,  playerId, playerName,BasePlayerLogEvent.EventType.ROOMIN.ordinal());
		super(CommonUtil.messageFormatI18N(EventConstants.I18NEventConstantsCode.IN_ROME_MSG.getCode(), roomId),time,  playerId, playerName,BasePlayerLogEvent.EventType.ROOMIN.ordinal());
	}
	@Override
	public int getType() {
		return BasePlayerLogEvent.EventType.ROOMIN.ordinal();
	}
}
