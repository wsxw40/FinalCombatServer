package com.pearl.o2o.servlet.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.StringUtil;

public class GetLuckyPackageList extends BaseClientServlet {

	private static final long serialVersionUID = 191393562801279437L;
	private static Logger log = LoggerFactory.getLogger(GetLuckyPackageList.class.getName());
	private static final String[] paramNames = {"level","pageNo"};
	protected String innerService(String... args) {
		try{
			int level	 = StringUtil.toInt(args[0]);
			int pageNo = StringUtil.toInt(args[1]);
			return getService.getLuckyPackagePage(level, pageNo);
		} catch (Exception e) {
			log.warn("Error in GetLuckyPackageList: " , e);
			return Converter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
		}	
	}
	
	@Override
	protected String[] paramNames() {
		return paramNames;
	}

}
