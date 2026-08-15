package com.pearl.o2o.servlet.client;


import java.util.List;

import org.slf4j.Logger;import org.slf4j.LoggerFactory;

import com.pearl.o2o.pojo.SysActivity;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.StringUtil;


public class GetActivity extends BaseClientServlet {

	
	private static final long serialVersionUID = 143683649586609039L;
	private static Logger log = LoggerFactory.getLogger(GetActivity.class.getName());
	private static final String[] paramNames = {"pid"};
	
	protected String innerService(String... args) {
		try{
			int playerId = StringUtil.toInt(args[0]);
			List<SysActivity> activityList = getService.getAvailableActivities();
			return Converter.activityList(activityList);
		}
		catch(Exception e){
			log.warn("Error in getMission: " , e);
			return Converter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
		}	
	}

	@Override
	protected String[] paramNames() {
		return paramNames;
	}
	
}
