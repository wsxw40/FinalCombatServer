
package com.pearl.o2o.servlet.client;

import org.slf4j.Logger;import org.slf4j.LoggerFactory;

import com.pearl.o2o.pojo.Team;
import com.pearl.o2o.utils.CommonUtil;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.StringUtil;
import com.pearl.o2o.utils.TeamUtils;

/**
 * @author bobby
 */
public class GetTeamList extends BaseClientServlet {

	private static final long serialVersionUID = 7794098604530646005L;
	static Logger logger=LoggerFactory.getLogger(GetTeamList.class.getName());
	private static final String[] paramNames = {"tida","tidb"};
	/**
	 * 获取战队详细信息
	 */
	protected String innerService(String... args) {
		try{
			int teamId = StringUtil.toInt(args[0]);
			int teamId2 = StringUtil.toInt(args[1]);
			Team team=getService.getTeam(teamId);
			Team team2=getService.getTeam(teamId2);
			CommonUtil.checkNull(team, ExceptionMessage.NOT_FIND_TEAM);
			CommonUtil.checkNull(team2, ExceptionMessage.NOT_FIND_TEAM);
			return Converter.teamList(team, team2);
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
