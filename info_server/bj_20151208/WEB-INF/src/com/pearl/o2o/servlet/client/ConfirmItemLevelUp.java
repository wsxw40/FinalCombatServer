package com.pearl.o2o.servlet.client;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.o2o.pojo.Player;
import com.pearl.o2o.pojo.SysItem;
import com.pearl.o2o.pojo.Team;
import com.pearl.o2o.pojo.TeamItem;
import com.pearl.o2o.pojo.TeamLevelInfo;
import com.pearl.o2o.pojo.TeamLevelModeInfo;
import com.pearl.o2o.pojo.TeamTechnology;
import com.pearl.o2o.pojo.TeamLevelModeInfo.ConfigPoint;
import com.pearl.o2o.utils.CommonMsg;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.StringUtil;
import com.pearl.o2o.utils.ItemIntensifyUtil;

public class ConfirmItemLevelUp extends BaseClientServlet{
	/**
	 * 战队物品升级
	 */
	private static final long serialVersionUID = 4991386241340707375L;
	private static final String [] paramNames={"playerId","teamItemId","teamId"};
	private Logger log = LoggerFactory.getLogger(ConfirmItemLevelUp.class);
	@Override
	protected String innerService(String... strings) {
		if(!strings[0].matches("^\\d+$")){		
			return Converter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
		}
		if(!strings[1].matches("^\\d+$")){		
			return Converter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
		}
		if(!strings[2].matches("^\\d+$")){		
			return Converter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
		}
		int playerId = StringUtil.toInt(strings[0]);
		int teamItemId = StringUtil.toInt(strings[1]);
		int teamId = StringUtil.toInt(strings[2]);
		//检测是否输过二级密码
		
		try{
//			if(!checkEnterSPW(playerId)){
//				return Converter.error(CommonMsg.INPUT_SECOND_PASSWORD);
//			}
			Player player = getService.getPlayerByIdWithTeam(playerId);
			TeamItem  ti = getService.getTeamItemById(teamItemId);
			Team team = getService.getTeamById(teamId);
			if(player.getId()!=team.getHeaderId()){
				return Converter.error(ExceptionMessage.IS_NOT_LEADER);
			}
			if(ti.isUpgrade(ti.getSysItem().getSubType())){
				
				if(ti.getLevel()>=ItemIntensifyUtil.ITEM_MAX_LEVEL){
					return Converter.error(ExceptionMessage.IS_MAX_LEVEL);
				}
				if(ti.getSysItem().getSubType()==ItemIntensifyUtil.TEAM_STORAGE_TYPE.OIL_TANK.getValue()){
					return Converter.error(ExceptionMessage.IS_MAX_LEVEL);
				}
				
				TeamTechnology tt = getService.getTeamTechnologyByCurNode(ti.getSysItem().getId());
				
				
				updateService.updateTeamItem(team.getId(), ti);
				TeamItem newTeamItem=null;
				try{
					
					SysItem sysItem = getService.getSysItemByItemId(tt.getNextNode());
					newTeamItem = createService.createTeamItem(team.getId(), sysItem, ti.getQuantity(), ti.getUnitType(), ti.getIsGift(), ti.getIsBind(), ti.getIsDefault(),ti.getUsedCount());
					newTeamItem.setLastBuildTime(ti.getLastBuildTime());
					newTeamItem.setUsedCount(ti.getUsedCount());
					updateService.updateTeamItem(newTeamItem.getTeamId(), newTeamItem);
				}catch (Exception e) {
					log.info(ExceptionMessage.IS_MAX_LEVEL);
				}
				//更新地图里的物品
				TeamLevelInfo teamLevel = getService.getTeamLevelInfo(team.getId(), Constants.DEFAULT_TEAM_LEVEL_RES_ID);
				TeamLevelModeInfo teamLevelMode = new TeamLevelModeInfo(teamLevel);
				Set<ConfigPoint> configs = teamLevelMode.getConfigPoints();
				StringBuffer stringBuffer = new StringBuffer();
				for(ConfigPoint config:configs){
					if(config.getItemId()==ti.getItemId()){
						config.setItemId(newTeamItem.getItemId());
					}
					stringBuffer.append(config.toPlainString()+";");
				}
				updateService.updateTeamLevelInfo(teamLevelMode.getTeamLevelInfo(stringBuffer.toString()));
				
				
				deleteService.deleteTeamItem(team.getId(), ti);
				
				if(newTeamItem.getLevel()==2){
					if(tt.getOpenTower()!=0){
						SysItem nextItem = getService.getSysItemByItemId(tt.getOpenTower());
						createService.createTeamItem(team.getId(), nextItem, 0, newTeamItem.getUnitType(), newTeamItem.getIsGift(), newTeamItem.getIsBind(), newTeamItem.getIsDefault(),0);
					}
				}else if(newTeamItem.getLevel()==3){
					if(tt.getOpenTower()!=0){
						SysItem nextItem = getService.getSysItemByItemId(tt.getOpenTower());
						createService.createTeamItem(team.getId(), nextItem,0, newTeamItem.getUnitType(), newTeamItem.getIsGift(), newTeamItem.getIsBind(), newTeamItem.getIsDefault(),0);
					}
				}
				return Converter.getIntensifyInfo(0, 0, 0, 0, 1, newTeamItem);
			}else{
				return Converter.error(ExceptionMessage.DISSATISFY_LEVEL_UP_CONDITION);
			}
		}catch (Exception e) {
			log.error("ConfirmItemLevelUp:"+e);
			return Converter.error("error");
		}
	}
	@Override
	protected String[] paramNames() {
		return paramNames;
	}
}
