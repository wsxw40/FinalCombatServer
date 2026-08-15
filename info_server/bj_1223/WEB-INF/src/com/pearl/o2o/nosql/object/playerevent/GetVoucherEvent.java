package com.pearl.o2o.nosql.object.playerevent;

import com.pearl.o2o.utils.CommonUtil;

public class GetVoucherEvent extends BasePlayerLogEvent {

	public GetVoucherEvent(int value,long time, int playerId,
			String playerName) {
//		super("消耗了抵用券"+value,time,  playerId, playerName,BasePlayerLogEvent.EventType.GETVOUCHER.ordinal());
		super(CommonUtil.messageFormatI18N(EventConstants.I18NEventConstantsCode.COST_TICKET_MSG.getCode(), value),time,  playerId, playerName,BasePlayerLogEvent.EventType.GETVOUCHER.ordinal());
	}
	@Override
	public int getType() {
		return BasePlayerLogEvent.EventType.GETVOUCHER.ordinal();
	}
}
