package com.pearl.o2o.nosql.object.playerevent;

import com.pearl.o2o.utils.CommonUtil;

public class PayVoucherEvent extends BasePlayerLogEvent {

	public PayVoucherEvent(int cost,long time,int playerId,
			String playerName) {
//		super("消耗了抵用券"+cost,time,playerId, playerName,BasePlayerLogEvent.EventType.PAYVOUCHER.ordinal());
		super(CommonUtil.messageFormatI18N(EventConstants.I18NEventConstantsCode.COST_TICKET_MSG.getCode(), cost),time,playerId, playerName,BasePlayerLogEvent.EventType.PAYVOUCHER.ordinal());
		// TODO Auto-generated constructor stub
	}
	@Override
	public int getType() {
		return BasePlayerLogEvent.EventType.PAYVOUCHER.ordinal();
	}
}
