package com.pearl.o2o.servlet.client;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.o2o.pojo.Player;
import com.pearl.o2o.pojo.PlayerTeam;
import com.pearl.o2o.pojo.Team;
import com.pearl.o2o.utils.CommonUtil;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.ServiceLocator;
import com.pearl.o2o.utils.StringUtil;
import com.pearl.o2o.utils.TeamConstants;
import com.pearl.o2o.utils.TeamUtils;

public class GetMyTeamInfo extends BaseClientServlet {

	private static final long serialVersionUID = 964459518992817674L;
	static Logger logger = LoggerFactory.getLogger(GetTeam.class.getName());
	private String[] paramNames = { "cid" };

	/**
	 * 获取我的战队信息
	 */
	@Override
	protected String innerService(String... args) {
		try {
			int playerId = StringUtil.toInt(args[0]);
			Player player = getService.getPlayerById(playerId);
			CommonUtil.checkNull(player, ExceptionMessage.NO_HAVE_CHARACTER);
//			PlayerTeam pteam = getService.getPlayerTeamByPlayerIdSimple(playerId);
			PlayerTeam pteam = null;
			if(player.getTeamId()!=0){
				pteam=getService.getPlayerTeam(player.getTeamId(), playerId);
			}
			String teamName = "";
			if (pteam == null) {
				pteam = getService.getPlayerTeamByPlayerIdSimple(playerId);
				if(pteam==null){
					ServiceLocator.teamCheckLog.error("can't find playerTeam by pid:\t"+playerId);
//					return Converter.commonFeedback(ExceptionMessage.NOT_FIND_PLAYER_TEAM);
				}else{
					player.setTeamId(pteam.getTeamId());
					updateService.updatePlayerInfoOnly(player);
				}
			}
			//FIXME OPTIMISM
			Team team = null;
			if(pteam!=null){
				team = TeamUtils.updateTeamInfo(pteam.getTeamId());
			}
			int currentNum = 0;
			String leaderName = "";
			if (null != team) {
//				currentNum = getService.getPlayerTeamByTeamIdSimple(team.getId()).size();
				currentNum = team.getNumber();
				Player header = getService.getPlayerById(team.getHeaderId());
				if (null != header) {
					leaderName = header.getName();
				} else {
					Map<Integer, PlayerTeam> playerTeams = getService.getPlayerTeamDao().getPlayerTeams(team.getId());
					for (PlayerTeam pt : playerTeams.values()) {
						if (pt.getJob() == TeamConstants.TEAMJOB.TEAM_CAPTAIN.getValue()) {
							Player p = getService.getPlayerById(team.getHeaderId());
							if (null != p) {
								leaderName = p.getName();
							}
						}
					}
				}
			}

			int num = 0;
			if (null != team) {
				num = getService.getUnapprovedMemberSimple(team.getId()).size();
			}

			return Converter.myTeam(team, pteam, teamName, currentNum, leaderName, num);
		} catch (Exception e) {
			logger.warn("GetMyTeamInfo============"+e.getMessage(), e);
			return Converter.commonFeedback(ExceptionMessage.ERROR_MESSAGE_ALL);
		}
	}

	

	@Override
	protected String[] paramNames() {
		return paramNames;
	}

	@Override
	protected String getLockKey(String[] paramNames) {
		return CommonUtil.getLockKey(StringUtil.toInt(paramNames[0]));
	}
}