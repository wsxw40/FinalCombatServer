package com.pearl.o2o.servlet.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.o2o.pojo.PlayerTeam;
import com.pearl.o2o.pojo.Team;
import com.pearl.o2o.pojo.TeamLevelInfo;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.StringUtil;

public class EditTeamMap extends BaseClientServlet {

	private static final long serialVersionUID = -69155512930083756L;
	private static Logger log = LoggerFactory.getLogger(EditTeamMap.class.getName());
	private String[] paramNames  = { "pid"};
	
	@Override
	protected String innerService(String... args) {
		
		if(!args[0].matches("^\\d+$")){		
			return Converter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
		}
		
		int playerId = StringUtil.toInt(args[0]) ;
		
		try {
			PlayerTeam playerTeam=getService.getPlayerTeamByPlayerId(playerId);
			if(playerTeam!=null){
				Team team=getService.getTeamById(playerTeam.getTeamId());
				TeamLevelInfo teamLevelInfo=getService.getTeamLevelInfo(playerTeam.getTeamId(), Constants.DEFAULT_TEAM_LEVEL_RES_ID);
				
				if(team==null){
					return Converter.error(ExceptionMessage.NOT_FIND_TEAM);
				}
				if(team.getHeaderId()!=playerId){
					return Converter.error(ExceptionMessage.IS_NOT_LEADER);
				}
				if(teamLevelInfo==null){
					teamLevelInfo=createService.createTeamLevel(team.getId());
				}
				if(teamLevelInfo!=null){
					return Converter.editTeamMap(getService.getFormatConfigOption(teamLevelInfo.getConfigPoints()),team.getTeamSpaceLevel(),team.getName());	
				}
				return "";
			}
			
			
		} catch (Exception e) {
			log.error("Error happened when get the team map "+e);
			//return Converter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
		}
		return Converter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
	}
	
	@Override
	protected String[] paramNames() {
		return paramNames;
	}
}
