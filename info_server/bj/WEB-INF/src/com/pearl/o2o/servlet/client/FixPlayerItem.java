package com.pearl.o2o.servlet.client;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.o2o.exception.BaseException;
import com.pearl.o2o.utils.CommonUtil;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.StringUtil;

public class FixPlayerItem extends BaseClientServlet {
	private static final long serialVersionUID = 348899082580431429L;
	static Logger log = LoggerFactory.getLogger(FixPlayerItem.class.getName());
	private String[] paramNames={"uid","pid","t","piid"};
	@Override
	protected String innerService(String... args) {
		try{
			int userId = StringUtil.toInt(args[0]);
			int playerId=StringUtil.toInt(args[1]);
			int type=StringUtil.toInt(args[2]);
			int playerItemId=StringUtil.toInt(args[3]);
			updateService.fixPlayerItem(userId, playerId, playerItemId, type);
			return Converter.commonFeedback(null);
		}
		catch (BaseException be) {
			log.debug("Exception in FixPlayerItem"+be.getMessage());	
			return Converter.commonFeedback(be.getMessage());	
		}
		catch(Exception e){
			log.warn("Error in FixPlayerItem: " , e);
			return Converter.commonFeedback(e.getMessage());	
		}
	}
	@Override
	protected String[] paramNames() {
		return paramNames;
	}
	@Override
	protected String getLockKey(String[] paramNames) {
		return CommonUtil.getLockKey(StringUtil.toInt(paramNames[1]));
	}
}
