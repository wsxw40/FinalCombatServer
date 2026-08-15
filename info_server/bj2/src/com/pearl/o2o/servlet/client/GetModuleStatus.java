package com.pearl.o2o.servlet.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.o2o.utils.CommonUtil;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.StringUtil;

public class GetModuleStatus extends BaseClientServlet {
	private static final long serialVersionUID = 2412223517067028215L;
	private static Logger log = LoggerFactory.getLogger(GetModuleStatus.class.getName());
	private static final String[] paramNames = { "pid" };

	protected String innerService(String... args) {
		try {
			int playerId = StringUtil.toInt(args[0]);
			char[] tutorial = getService.getPlayerTutorial(playerId);
			return Converter.getModuleStatus(tutorial);
		} catch (Exception e) {
			log.warn("Error in AcceptFriend: ", e);
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
