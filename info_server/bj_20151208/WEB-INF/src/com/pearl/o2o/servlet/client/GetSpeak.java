package com.pearl.o2o.servlet.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.o2o.pojo.SysItem;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.StringUtil;

public class GetSpeak extends BaseClientServlet {
	private static Logger log = LoggerFactory.getLogger(GetSpeak.class.getName());
	private static final long serialVersionUID = -6836692174398117141L;
	private static final String[] paramNames = {"type"};

	protected String innerService(String... args) {
		try {
			int iId = StringUtil.toInt(args[0]);
			SysItem theItem = getService.getSysItemByIID(iId, Constants.DEFAULT_ITEM_TYPE).get(0);
			return Converter.sysItemEntity(theItem);
		} catch (Exception e) {
			log.warn("exception in UsePlayerItem servlet", e);
			return Converter.warn(ExceptionMessage.ERROR_MESSAGE_ALL);
		}
	}

	@Override
	protected String[] paramNames() {
		return paramNames;
	}

	@Override
	protected String getLockKey(String[] paramNames) {
		return null;
	}
}
