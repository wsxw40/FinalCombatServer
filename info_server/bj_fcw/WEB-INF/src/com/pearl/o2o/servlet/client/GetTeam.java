
package com.pearl.o2o.servlet.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.o2o.exception.BaseException;
import com.pearl.o2o.pojo.Team;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.StringUtil;
import com.pearl.o2o.utils.TeamUtils;

/**
 * @author bobby
 */
public class GetTeam extends BaseClientServlet {

	private static final long serialVersionUID = 7794098604530646005L;
	static Logger logger=LoggerFactory.getLogger(GetTeam.class.getName());
	private static final String[] paramNames = {"tid"};
	/**
	 * 获取战队详细信息
	 */
	protected String innerService(String... args) {
		try{
			int teamId = StringUtil.toInt(args[0]);
//			System.out.println(teamId);
//			Team team=getService.getTeamById(teamId);
//			CommonUtil.isNull(team, ExceptionMessage.NOT_FIND_TEAM);
//			if(team.getFight()==0){
//				getService.setTeamMember(team);
//				team.setFight(getService.getTeamFight(team));
//			}
//			int currentRank = (int)nosqlService.getNosql().revRankInSortedSet(Constants.TEAMTOP_KEY_PREFIX+Constants.TEAM_TOP_TYPE.BATTLERESULT.getValue(), String.valueOf(teamId))+1;
//			team.setRecoreRankingCurr(currentRank);
			Team team = TeamUtils.updateTeamInfo(teamId);
//			int currentNum = getService.getPlayerTeamByTeamIdSimple(teamId).size();
			int currentNum = team.getNumber();
			return Converter.team(team, currentNum);
		}catch (BaseException e) {

			logger.warn("GetTeam/Null",e);
			return Converter.commonFeedback(e.getMessage());
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
