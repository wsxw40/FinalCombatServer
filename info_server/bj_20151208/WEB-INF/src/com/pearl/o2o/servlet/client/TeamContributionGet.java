
package com.pearl.o2o.servlet.client;

import java.util.Date;

import org.lilystudio.smarty4j.Context;
import org.slf4j.Logger;import org.slf4j.LoggerFactory;

import com.google.common.base.Preconditions;
import com.pearl.o2o.pojo.Player;
import com.pearl.o2o.pojo.PlayerTeam;
import com.pearl.o2o.pojo.SysTeamBuff;
import com.pearl.o2o.pojo.Team;
import com.pearl.o2o.pojo.TeamBuff;
import com.pearl.o2o.utils.CommonUtil;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.StringUtil;
import com.pearl.o2o.utils.TeamConstants;

/**
 * @author bobby
 */
public class TeamContributionGet extends BaseClientServlet {

	private static final long serialVersionUID = 7794098604530646005L;
	static Logger logger=LoggerFactory.getLogger(TeamContributionGet.class.getName());
	private static final String[] paramNames = {"ptid","tid","pid"};
	/**
	 * 获取战队详细信息
	 */
	protected String innerService(String... args) {
		try{
			int playerTeamId = Integer.parseInt(args[0]);
			int teamId = Integer.parseInt(args[1]);
			int pid=Integer.parseInt(args[2]);
			Team team = getService.getTeam(teamId);
			PlayerTeam playerTeam = getService.getPlayerTeamDao().getPlayerTeam(teamId, playerTeamId);
			Preconditions.checkNotNull(team);
			Preconditions.checkNotNull(playerTeam);
//			int medolNum=getService.getMedolNumByPlayerId(playerTeam.getPlayerId());
			int flag=nosqlService.isTeamSalary(pid);
			
			Context ctx	= new Context();
			ctx.set("team", team);
			ctx.set("playerTeam", playerTeam);
			ctx.set("burnt", 0);
			ctx.set("flag", flag);
//			ctx.set("medolNum", medolNum);
			return Converter.smartTemplate("TeamContributionGet.st",ctx);
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
