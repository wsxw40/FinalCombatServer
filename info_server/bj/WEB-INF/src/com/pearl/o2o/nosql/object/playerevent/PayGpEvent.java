package com.pearl.o2o.nosql.object.playerevent;

import com.pearl.o2o.utils.CommonUtil;

public class PayGpEvent extends BasePlayerLogEvent {

	public PayGpEvent(int cost ,long time, int playerId,
			String playerName) {
//		super("消耗了C币"+cost,time,  playerId, playerName,BasePlayerLogEvent.EventType.PAYGP.ordinal());
		super(CommonUtil.messageFormatI18N(EventConstants.I18NEventConstantsCode.COST_CB_MSG.getCode(), cost),time,  playerId, playerName,BasePlayerLogEvent.EventType.PAYGP.ordinal());
		// TODO Auto-generated constructor stub
	}
	@Override
	public int getType() {
		return BasePlayerLogEvent.EventType.PAYGP.ordinal();
	}
}
