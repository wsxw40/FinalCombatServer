package com.pearl.o2o.servlet.client;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.o2o.enumuration.CostFunction;
import com.pearl.o2o.enumuration.CostType;
import com.pearl.o2o.pojo.Player;
import com.pearl.o2o.pojo.Team;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.ItemIntensifyUtil;
import com.pearl.o2o.utils.StringUtil;
import com.pearl.o2o.utils.ItemIntensifyUtil.ConditionForPlaceUp;

public class ConfirmLevelUp extends BaseClientServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2054570729515577516L;
	private static final String[] paramNames = { "playerId", "teamId"};
	private Logger log = LoggerFactory.getLogger(ConfirmLevelUp.class);
	@Override
	protected String innerService(String... strings) {
		try{
			if(!strings[0].matches("^\\d+$")){		
				return Converter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
			}
			if(!strings[1].matches("^\\d+$")){		
				return Converter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
			}
			int playerId = StringUtil.toInt(strings[0]);
			int teamId = StringUtil.toInt(strings[1]);
			Player player = getService.getPlayerById(playerId);
			Team team = getService.getTeamById(teamId);
			//检测是否输过二级密码
//			if(!checkEnterSPW(playerId)){
//				return Converter.error(CommonMsg.INPUT_SECOND_PASSWORD);
//			}
			if(team==null){
				return Converter.error(ExceptionMessage.TEAM_NOT_EXIST_ZYZDZ);
			}
			HashMap<String, Integer> teamResHashMap=team.getLatestTeamRes();
			if(player.getId()==team.getHeaderId()){
				int placeLevel = team.getTeamSpaceLevel();
				if(placeLevel>=ItemIntensifyUtil.MAX_TEAM_PLACE_LEVEL){
					return Converter.error(ExceptionMessage.IS_MAX_LEVEL);
				}
				ConditionForPlaceUp cf = ConditionForPlaceUp.getConditionForPlaceUp(placeLevel);
				int price = cf.getMoney();
				if(teamResHashMap.get(Team.RES)<price){
					return Converter.error(ExceptionMessage.NOT_ENOUGH_USABLE_RESOURCE);
				}
				team.setLastTeamPlaceLevelUpTime((int)(System.currentTimeMillis()/1000));
				team.setUsableResource(teamResHashMap.get(Team.RES)-price);
				updateService.updateTeamInfo(team);
				createService.createPlayerBuyFunctionLog(playerId,CostType.RES_PAY_TEAM,price,CostFunction.ZYZDZ_START_CREATE_TeamSpace,team.getId()+"| "+team.getName());
				return "";//return "placeLevel="+(placeLevel);
			}else{
				return Converter.error(ExceptionMessage.NOT_LEADER_POWER);
			}
		}catch (Exception e) {
			log.error(e.getMessage());
		}
		
		return super.innerService(strings);
	}
	@Override
	protected String[] paramNames() {
		return paramNames;
	}
}
