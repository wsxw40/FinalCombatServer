package com.pearl.o2o.nosql.object.playerevent;

import com.pearl.o2o.utils.CommonUtil;

public class LoginChannelEvent extends BasePlayerLogEvent {

	public LoginChannelEvent(int value,long time, int playerId,
			String playerName) {
//		super("登陆了频道"+value,time,  playerId, playerName,BasePlayerLogEvent.EventType.LOGINCHANNEL.ordinal());
		super(CommonUtil.messageFormatI18N(EventConstants.I18NEventConstantsCode.LOGIN_CHANEL_MSG.getCode(), value),time,  playerId, playerName,BasePlayerLogEvent.EventType.LOGINCHANNEL.ordinal());
		// TODO Auto-generated constructor stub
	}
	@Override
	public int getType() {
		return BasePlayerLogEvent.EventType.LOGINCHANNEL.ordinal();
	}
}
