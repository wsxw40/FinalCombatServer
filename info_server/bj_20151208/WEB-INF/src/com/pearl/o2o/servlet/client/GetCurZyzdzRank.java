package com.pearl.o2o.servlet.client;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.o2o.pojo.Player;
import com.pearl.o2o.pojo.Team;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.StringUtil;

/**
 * 显示资源争夺战主界面
 * 
 * @author leo.zhang
 */
public class GetCurZyzdzRank extends BaseClientServlet {
	private static final long serialVersionUID = 3809672433515940145L;
	static Logger logger = LoggerFactory.getLogger(GetCurZyzdzRank.class
			.getName());
	private String[] paramNames = { "pid" };

	@Override
	protected String innerService(String... args) {
		String result = "";
		try {
			int playerId = StringUtil.toInt(args[0]);
			Player player = getService.getSimplePlayerById(playerId);
			Team team = null;
			if (player.getTeamId() != 0) {
				team = getService.getTeamById(player.getTeamId());
			}
			if (team != null) {
				// 本周排名
				List<Team> curZYZDZRank = getService.getTeamTopForRes();
				if (curZYZDZRank.size() > 20) {
					curZYZDZRank = curZYZDZRank.subList(0, 20);
				}
				result= Converter.GetZYZDZCurRank(curZYZDZRank);
			}
			
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
