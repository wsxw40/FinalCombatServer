package com.pearl.o2o.servlet.client;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.o2o.nosql.object.team.TeamBattleHistory;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.StringUtil;

public class GetTeamHistoryList extends BaseClientServlet {
	private static final long serialVersionUID = 385922639423472952L;
	static Logger logger=LoggerFactory.getLogger(GetTeamHistoryList.class.getName());
	private static final String[] paramNames = {"tid"};
	
	protected String innerService(String... args) {
		try{
			int teamId = StringUtil.toInt(args[0]);
			List<TeamBattleHistory> list = nosqlService.getTeamHistory(teamId);
			
			return Converter.teamHistory(list);
		}catch (Exception e) {
			logger.warn(e.getMessage(),e);
			return Converter.commonFeedback(ExceptionMessage.ERROR_MESSAGE_ALL);
		}
	}

	@Override
	protected String[] paramNames() {
		return paramNames;
	}
}
