
package com.pearl.o2o.servlet.client;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.lilystudio.smarty4j.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.o2o.exception.BaseException;
import com.pearl.o2o.pojo.Player;
import com.pearl.o2o.pojo.PlayerItem;
import com.pearl.o2o.pojo.PlayerTeam;
import com.pearl.o2o.pojo.SysItem;
import com.pearl.o2o.pojo.Team;
import com.pearl.o2o.utils.CommonUtil;
import com.pearl.o2o.utils.ConfigurationUtil;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.GrowthMissionConstants.GrowthMissionType;
import com.pearl.o2o.utils.ServiceLocator;
import com.pearl.o2o.utils.StringUtil;
import com.pearl.o2o.utils.TeamConstants;

/**
 * @author lfyang
 */
public class TeamBurntUse extends BaseClientServlet {
	private static final long serialVersionUID = 7794098604530646005L;
	private static Logger logger=LoggerFactory.getLogger(TeamBurntUse.class.getName());
	private static final String[] paramNames = {"tid","ptid","pid","sid"};
	// type :1 小香 2 中香 3 大香
	protected String innerService(String... args) {
		try{
			int teamId = Integer.valueOf(args[0]);
			int playerTeamId = Integer.valueOf(args[1]);
			int playerId = Integer.valueOf(args[2]);
			int sysItemId = Integer.valueOf(args[3]);
			
			PlayerTeam playerTeam = getService.getPlayerTeamDao().getPlayerTeam(teamId, playerTeamId);
			CommonUtil.checkNull(playerTeam, ExceptionMessage.NOT_FIND_PLAYER_TEAM);
			if(TeamConstants.Burnt_Id_Set.contains(sysItemId)){
				Map<Integer,PlayerItem> map=getService.getPlayerItemMapByType1(playerId, Constants.DEFAULT_ITEM_TYPE, 0, 0);
				for(Map.Entry<Integer, PlayerItem> entry: map.entrySet()) {  
					PlayerItem value = entry.getValue();
					value.setSysItem(getService.getSysItemByItemId(value.getItemId()));
					if(value.getItemId() == sysItemId&&value.getQuantity()>0&&value.getIsDeleted().equals(Constants.BOOLEAN_NO)){
						//每天限用一次升级能量块
						if(!getService.isBurnt(playerId)){
							return Converter.error(TeamConstants.ONE_TIME_DAYLY);
						}
						Team team = getService.getTeam(teamId);

//						if(team.getLevel() == TeamConstants.Team_Level_Limit&&team.getExp() == team.getTotalExp()){
//							return Converter.error(TeamConstants.TEAM_EXP_FULL);
//						}
						
						value.setQuantity(value.getQuantity() - 1);
						ServiceLocator.updateService.updatePlayerItem(value);
//						if(value.getQuantity() == 0){
//							ServiceLocator.deleteService.deletePlayerItem(value.getPlayerId(), value.getSysItem().getType(), value.getId());
//						}
						SysItem si=getService.getSysItemByItemId(sysItemId);
						int parseInt = Integer.parseInt(si.getIValue());
						//vip 获得额外贡献
						Player player=getService.getPlayerById(playerId);
						CommonUtil.checkNull(player, ExceptionMessage.NO_HAVE_THE_CHARACTER);
						if(player.getIsVip()>=1 && player.getIsVip()<=Constants.VIP_BURN_EX_EARN_PER.length){
							parseInt=(int)(parseInt*Constants.VIP_BURN_EX_EARN_PER[player.getIsVip()-1]);
						}
						
						int level = team.getLevel();
						team.addExp(parseInt,team);
						if(team.getLevel()>level){//更新战队等级排名
							updateService.updateTeamTop(team, 2);
							List<PlayerTeam> ptList = getService.getPlayerTeamByTeamIdSimple(team.getId());
							for (PlayerTeam pt : ptList) {
								Player p=getService.getPlayerById(pt.getPlayerId());
								soClient.updateCharacterInfo(p, team.getName(), p.getRank());
							}
						}
//						TeamUtils.updateTeamExp(teamId, parseInt);
						updateService.updateTeamInfo(team);
						
//						playerTeam.setLastBurnt(System.currentTimeMillis());
						playerTeam.setContribution(playerTeam.getContribution()+parseInt);
						getService.getPlayerTeamDao().updatePlayerTeam(playerTeam);
						
						updateService.updatePlayerGrowthMission(playerId, GrowthMissionType.TEAM_BRUNT);
						if (ConfigurationUtil.SWITCH_XUNLEI_LOG.getIsOn()) {
					
							nosqlService.addXunleiLog("12.1"
									+ Constants.XUNLEI_LOG_DELIMITER + player.getUserName()
									+ Constants.XUNLEI_LOG_DELIMITER + player.getName()
									+ Constants.XUNLEI_LOG_DELIMITER + team.getId()
									+ Constants.XUNLEI_LOG_DELIMITER + team.getName()
									+ Constants.XUNLEI_LOG_DELIMITER + team.getLevel()
									+ Constants.XUNLEI_LOG_DELIMITER+ si.getId()
									+ Constants.XUNLEI_LOG_DELIMITER+ si.getDisplayNameCN()
									+ Constants.XUNLEI_LOG_DELIMITER+ si.getIValue()
									+ Constants.XUNLEI_LOG_DELIMITER+ CommonUtil.simpleDateFormat.format(new Date())
									);
						}
						
						Context ctx	= new Context();
						ctx.set("team", team);
						ctx.set("playerTeam", playerTeam);
						ctx.set("burnt", parseInt);
						return Converter.smartTemplate("TeamContributionGet.st",ctx);
					}
				}
				return Converter.error(TeamConstants.NOT_ENOUGH_ITEMS);
			}else{
				return Converter.error(TeamConstants.Commit_Error_ITMES);
			}
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
	@Override
	protected String getLockKey(String[] paramNames) {
		return CommonUtil.getTeamLockKey(StringUtil.toInt(paramNames[0]));
	}
}