package com.pearl.o2o.servlet.client;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.o2o.pojo.Player;
import com.pearl.o2o.pojo.Team;
import com.pearl.o2o.pojo.TeamLevelInfo;
import com.pearl.o2o.utils.CommonMsg;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.StringUtil;
import com.pearl.o2o.utils.ItemIntensifyUtil;
import com.pearl.o2o.utils.ItemIntensifyUtil.ConditionForPlaceUp;

public class GetOurTeamLevel extends BaseClientServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2054570729515577516L;
	private static final String[] paramNames = {"teamId"};
	private Logger log = LoggerFactory.getLogger(GetOurTeamLevel.class);
	@Override
	protected String innerService(String... strings) {
		int teamId=0;
		try{
			if(!strings[0].matches("^\\d+$")){		
				return Converter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
			}
		
			teamId = StringUtil.toInt(strings[0]);
			Team team = getService.getTeamById(teamId);
			TeamLevelInfo teamLevelInfo=getService.getTeamLevelInfo(teamId, Constants.DEFAULT_TEAM_LEVEL_RES_ID);
			
			if(team==null){
				return Converter.error(ExceptionMessage.TEAM_NOT_EXIST_ZYZDZ);
			}
			if(teamLevelInfo!=null){
				return Converter.editTeamMap(getService.getFormatConfigOption(teamLevelInfo.getConfigPoints()),team.getTeamSpaceLevel(),team.getName());	
			}else{
				return Converter.error(ExceptionMessage.TEAM_LEVEL_NOT_EXIST_ZYZDZ);
			}
		
		}catch (Exception e) {
			log.error(e+" happened at get the team's level with id "+teamId);
		}
		return Converter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
	}
	@Override
	protected String[] paramNames() {
		return paramNames;
	}
}
