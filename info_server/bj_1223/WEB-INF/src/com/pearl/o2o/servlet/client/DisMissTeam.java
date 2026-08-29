package com.pearl.o2o.servlet.client;

import org.slf4j.Logger;import org.slf4j.LoggerFactory;

import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.StringUtil;

/**
 * 战队解散
 * @author lifengyang
 */
public class DisMissTeam extends BaseClientServlet {


	private static final long serialVersionUID = -5235644177360535709L;
	static Logger logger=LoggerFactory.getLogger(DisMissTeam.class.getName());
	private String[] paramNames={"uid","pid","tid"};
	@Override
	protected String innerService(String... args) {
		try{
			int userId = StringUtil.toInt(args[0]);
			int playerId=StringUtil.toInt(args[1]);
			int teamId=StringUtil.toInt(args[2]);
			String error = deleteService.deleteTeam(userId, playerId, teamId);
			if(null != error){
				return Converter.error(error);
			}
			return Converter.commonFeedback(null);
		}catch(Exception e){
			logger.warn("Error in DisMissTeam: ", e);
			return Converter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
		}
	}
	@Override
	protected String[] paramNames() {
		return paramNames;
	}
}
