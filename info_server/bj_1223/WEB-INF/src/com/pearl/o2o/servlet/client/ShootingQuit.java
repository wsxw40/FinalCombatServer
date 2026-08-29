package com.pearl.o2o.servlet.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.o2o.utils.CommonUtil;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.StringUtil;

public class ShootingQuit extends BaseClientServlet {
	
	private static final long serialVersionUID = -7556870074674515144L;
	private Logger logger = LoggerFactory.getLogger("shooting");
	private static final String[] paramNames = {"pid"};
	@Override
	protected String innerService(String... strings) {
		try {
			int playerId = StringUtil.toInt(strings[0]);
			String shootMccKey = Constants.SHOOTING_MCC_KEY_PREFX + playerId;
			mcc.delete(shootMccKey);
			String rdmGiftsKey = Constants.SHOOT_RDM_GIFTS_ID_MCC_KEY_PRFX + playerId;
			mcc.delete(rdmGiftsKey);
			return "";
		} catch (Exception e) {
			logger.error("ShootingJoin/Error:\t" , e);
			return Converter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
		}
		
	}
	@Override
	protected String[] paramNames() {
		return paramNames;
	}
	@Override
	protected String getLockKey(String[] paramNames) {
		return CommonUtil.getLockKey(StringUtil.toInt(paramNames[0]));
	}
	
}
