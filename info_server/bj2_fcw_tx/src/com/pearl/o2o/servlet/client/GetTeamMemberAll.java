
package com.pearl.o2o.servlet.client;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.ComparisonChain;
import com.pearl.o2o.pojo.Player;
import com.pearl.o2o.pojo.PlayerTeam;
import com.pearl.o2o.pojo.Team;
import com.pearl.o2o.utils.CommonUtil;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.ServiceLocator;
import com.pearl.o2o.utils.StringUtil;

/**
 * @author bobby
 */
public class GetTeamMemberAll extends BaseClientServlet {

	private static final long serialVersionUID = 7794098604530646005L;
	private static Logger logger=LoggerFactory.getLogger(GetTeamMemberAll.class.getName());
	private static final String[] paramNames = {"pid", "tid"};
	
	private static Comparator<PlayerTeam> playerTeamComparator = new Comparator<PlayerTeam>() {
		@Override
		public int compare(PlayerTeam o1, PlayerTeam o2) {
			return ComparisonChain.start()
					.compare(o2.getOnline(), o1.getOnline())
					.compare(o2.getIsVip(), o1.getIsVip())
					.compare(o2.getRank(), o1.getRank())
					.result();
		}
	};
	
	protected String innerService(String... args) {
		try{
			int playerId = StringUtil.toInt(args[0]);
			int teamId = 0;
			if(args.length > 1){
				teamId = StringUtil.toInt(args[1]);
			}
			Player player = getService.getSimplePlayerById(playerId);
			Team team = null;
			if(teamId > 0){
				team = getService.getTeam(teamId);
			} else {
				if(player.getTeamId()!=0){
					team = getService.getTeam(player.getTeamId());
				}
				if(team==null){//如果没有，则去数据库查
					team = getService.getTeamByPlayerId(playerId);

					if(team!=null){
						player.setTeamId(team.getId());
						updateService.updatePlayerInfoOnly(player);
					}else{
						ServiceLocator.teamCheckLog.error("can't find playerTeam by pid:\t"+playerId);
						return Converter.commonFeedback(ExceptionMessage.NOT_FIND_PLAYER_TEAM);
					}
				}
			}
			if(team == null){
				return "";
			}
//			List<PlayerTeam> list=getService.getPlayerTeamByTeamIdSimple(team.getId());
			List<PlayerTeam> list=getService.getPlayerTeamByTeamId(team.getId());
			Collections.sort(list,playerTeamComparator);
			team.setMemberList(list);
		
			return Converter.teamMemberAll(team);
			
			
		}catch (Exception e) {
			logger.warn(e.getMessage(),e);
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