
package com.pearl.o2o.servlet.client;

import java.util.Collections;
import java.util.List;

import org.lilystudio.smarty4j.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Preconditions;
import com.pearl.o2o.exception.BaseException;
import com.pearl.o2o.pojo.Player;
import com.pearl.o2o.pojo.PlayerTeam;
import com.pearl.o2o.pojo.SysTeamBuff;
import com.pearl.o2o.pojo.Team;
import com.pearl.o2o.pojo.TeamBuff;
import com.pearl.o2o.pojo.TeamBuffView;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;

/**
 * @author bobby
 */
public class TeamBuffList extends BaseClientServlet {

	private static final long serialVersionUID = 7794098604530646005L;
	static Logger logger=LoggerFactory.getLogger(TeamBuffList.class.getName());
	private static final String[] paramNames = {"ptid","tid","rank"};
	//action 1: 解锁	2：激活
	/**
	 * 获取战队详细信息
	 */
	protected String innerService(String... args) {
		try{
			int playerTeamId = Integer.parseInt(args[0]);
			int teamId = Integer.parseInt(args[1]);
			int rank = Integer.parseInt(args[2]);
			
			Team team = getService.getTeamDao().getTeamById(teamId);
			PlayerTeam playerTeam = getService.getPlayerTeamDao().getPlayerTeam(teamId, playerTeamId);
			Preconditions.checkNotNull(team);
			Preconditions.checkNotNull(playerTeam);
			Player player = getService.getPlayerById(playerTeam.getPlayerId());
			Preconditions.checkNotNull(player);
			
			List<TeamBuffView> teamBuffList = getService.getTeamBuffList(playerTeam, team, rank);
			
//			Collections.sort(teamBuffList);
			
			Context ctx	= new Context();
//			int medolNum=getService.getMedolNumByPlayerId(playerTeam.getPlayerId());

//			ctx.set("medalNum", medolNum);
			ctx.set("num", teamBuffList.size());
			ctx.set("contribution", playerTeam.getContribution());
			ctx.set("contributionLimit", Team.getContributionLimitByLevel(rank));
			ctx.set("list", teamBuffList);
			return Converter.smartTemplate("TeamBuffList.st",ctx);
		}catch (BaseException e) {
			return Converter.error(e.getMessage());
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
