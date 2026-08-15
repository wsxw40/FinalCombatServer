package com.pearl.o2o.nosql.object.playerevent;

import com.pearl.o2o.utils.CommonUtil;

public class PayCrEvent extends BasePlayerLogEvent {

	public PayCrEvent(int cost,long time, int playerId,
			String playerName) {
//		super("消耗了雷点"+cost,time, playerId, playerName,BasePlayerLogEvent.EventType.PAYCR.ordinal());
		super(CommonUtil.messageFormatI18N(EventConstants.I18NEventConstantsCode.COST_LEIPOINT_MSG.getCode(), cost),time, playerId, playerName,BasePlayerLogEvent.EventType.PAYCR.ordinal());
		// TODO Auto-generated constructor stub
	}
	@Override
	public int getType() {
		return BasePlayerLogEvent.EventType.PAYCR.ordinal();
	}
}
