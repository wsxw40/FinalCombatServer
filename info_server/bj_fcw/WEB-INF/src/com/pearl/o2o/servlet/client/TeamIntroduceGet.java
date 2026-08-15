
package com.pearl.o2o.servlet.client;

import org.slf4j.Logger;import org.slf4j.LoggerFactory;

import com.pearl.o2o.pojo.Team;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.StringUtil;

/**
 * @author bobby
 */
public class TeamIntroduceGet extends BaseClientServlet {

	private static final long serialVersionUID = 7794098604530646005L;
	static Logger logger=LoggerFactory.getLogger(TeamIntroduceGet.class.getName());
	private static final String[] paramNames = {"tid"};
	/**
	 * 获取战队详细信息
	 */
	protected String innerService(String... args) {
		try{
			int teamId = StringUtil.toInt(args[0]);
			Team team=getService.getTeamById(teamId);
			int currentNum = getService.getPlayerTeamByTeamIdSimple(teamId).size();
			return Converter.team(team, currentNum);
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
