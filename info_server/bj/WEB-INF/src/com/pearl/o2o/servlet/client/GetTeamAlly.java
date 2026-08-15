
package com.pearl.o2o.servlet.client;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.o2o.pojo.Team;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.StringUtil;

/**
 * @author bobby
 */
public class GetTeamAlly extends BaseClientServlet {
	private static final long serialVersionUID = 7794098604530646005L;
	private static Logger logger=LoggerFactory.getLogger(GetTeamAlly.class.getName());
	private static final String[] paramNames = {"tid"};
	
	protected String innerService(String... args) {
		int teamId = StringUtil.toInt(args[0]);
		try{
			Team team=getService.getTeam(teamId);
			if (team == null){
				return Converter.commonFeedback(ExceptionMessage.TEAM_NOT_EXIST);
			}
			if(team.getTeamList() == null){
				team.setTeamList(new ArrayList<Team>());
			}
			
			return Converter.teamAlly(team);
		}catch (Exception e) {
			//e.printStackTrace();
			logger.warn("team id " + teamId  + "exception is " + e.getMessage(),e);
			return Converter.commonFeedback(ExceptionMessage.ERROR_MESSAGE_ALL);
		}
	}

	@Override
	protected String[] paramNames() {
		return paramNames;
	}
}
