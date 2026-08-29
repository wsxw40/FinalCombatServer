package com.pearl.o2o.servlet.client;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.o2o.exception.BaseException;
import com.pearl.o2o.pojo.Team;
import com.pearl.o2o.pojo.TeamLevelInfo;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.StringUtil;
import com.pearl.o2o.utils.TOPTeam;

/**
 * 挑战主界面
 * 
 * @author leo.zhang
 */
public class GetChallengeInfo extends BaseClientServlet {
	private static final long serialVersionUID = 3809672433515940145L;
	static Logger logger = LoggerFactory.getLogger(GetChallengeInfo.class
			.getName());
	/** TeamID */
	private String[] paramNames = { "tid" };

	@Override
	protected String innerService(String... args) {
		String result = "";
		try {
			int teamID = StringUtil.toInt(args[0]);
			Team attackTeam =  getService.getTeamById(teamID);
			if (attackTeam != null) {
				List<Team> preZYZDZRank = getService.getTeamTopForPreRes();
				boolean isChallengeZYZDZOn=checkZYZDZChallengeOpen();
				//顶级队伍
				TOPTeam topTeam = createService.createTopTeam(attackTeam, preZYZDZRank,isChallengeZYZDZOn,attackTeam);
				
				//队伍地图详细信息
				TeamLevelInfo teamLevelInfo = getService.getTeamLevelInfo(teamID,Constants.DEFAULT_TEAM_LEVEL_RES_ID);
				teamLevelInfo.setFormatedConfig(getService.getFormatConfigOption(teamLevelInfo.getConfigPoints()));				
				
				
				result=Converter.GetChallengeInfo(topTeam,teamLevelInfo);
			}else{
				throw new BaseException(ExceptionMessage.ERROR_MESSAGE_NOTEAM);
			}
			return result;

		}catch (BaseException e) {
			logger.warn(e.getMessage(), e);
			return Converter.commonFeedback(e.getMessage());
		}
		catch (Exception e) {
			logger.warn(e.getMessage(), e);
			return Converter.commonFeedback(ExceptionMessage.ERROR_MESSAGE_ALL);
		}

	}

	@Override
	protected String[] paramNames() {
		return paramNames;
	}
}
