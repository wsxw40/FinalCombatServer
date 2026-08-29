package com.pearl.o2o.servlet.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.o2o.pojo.Team;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.StringUtil;

/**
 * 激活资源争夺战</p>
 * 
 * <h1>2013-11-07激活功能放入到了GetFightResInfo类中</h1>
 * 
 * @author zhang.li
 * 
 */
@Deprecated
public class ActiveTeamspace extends BaseClientServlet {
	private static final long serialVersionUID = 3364118008916644588L;

	static Logger logger = LoggerFactory.getLogger(ActiveTeamspace.class
			.getName());

	private String[] paramNames = { "teamid" };

	@Override
	protected String innerService(String... args) {

		String result = "";
		try {
			int teamId = StringUtil.toInt(args[0]);
			Team team = getService.getTeamById(teamId);
			team
					.setTeamSpaceActive(Constants.TeamSpaceConstants.TEAM_SAPCE_ACTIVE);
			updateService.updateTeamInfo(team);
			return result;
		} catch (Exception e) {
			logger.warn(e.getMessage(), e);
			return Converter.commonFeedback(ExceptionMessage.ERROR_MESSAGE_ALL);
		}

	}

	@Override
	protected String[] paramNames() {
		return paramNames;
	}
}
