package com.pearl.o2o.servlet.client;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.o2o.pojo.Player;
import com.pearl.o2o.pojo.Team;
import com.pearl.o2o.pojo.TeamItem;
import com.pearl.o2o.utils.CommonUtil;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.StringUtil;
import com.pearl.o2o.utils.ItemIntensifyUtil;
import com.pearl.o2o.utils.ItemIntensifyUtil.ConditionForPlaceUp;

public class TeamItemManage extends BaseClientServlet{
	private static final long serialVersionUID = -5794230877787369619L;
	private Logger log = LoggerFactory.getLogger(TeamItemManage.class);
	private static final String [] paramNames ={"playerId","teamId","teamItemId","playerItemId","manageType"};
	@Override
	protected String innerService(String... strings){
		try{
			if(!strings[0].matches("^\\d+$")){		
				return Converter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
			}
			int playerId = StringUtil.toInt(strings[0]);
			String teamIdStr = strings[1];
			String teamItemIdStr = strings[2];
			String playerItemIdStr = strings[3];
			if(!strings[4].matches("^\\d+$")){		
				return Converter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
			}
			int manageType = StringUtil.toInt(strings[4]);
			
			Player player = getService.getPlayerById(playerId);
			
			int teamId =0;
			TeamItem ti=null;
			Team team = null;
			
			if(teamIdStr!=null&&!"-1".equals(teamIdStr)){
				teamId = StringUtil.toInt(teamIdStr);
				team = getService.getTeamById(teamId);
			}else if(teamItemIdStr!=null&&!"-1".equals(teamItemIdStr)){
				ti = getService.getTeamItemById(StringUtil.toInt(teamItemIdStr));
				teamId = ti.getTeamId();
				team = getService.getTeamById(teamId);
				
			}
			if(teamIdStr!=null&&!"-1".equals(teamIdStr)){//全部修理或全部补给
			
			}
			if(teamItemIdStr!=null&&!"-1".equals(teamItemIdStr)){//战队空间配置
				if(manageType==ItemIntensifyUtil.TEAM_ITEM_MANAGE_TYPE.TEAM_PALCE_UP.getValue()){//升级
					int level = team.getTeamSpaceLevel();
					if(level>=ItemIntensifyUtil.MAX_TEAM_PLACE_LEVEL){
						return Converter.getTeamPlaceUp(level, 0, 0,0);//最高等级了
					}
					if(playerId!=team.getHeaderId()){
						return Converter.getTeamPlaceUp(level, 0, 0,0);
					}
					int isCanLevelUp = 0;
					int towerNum = 0;
					int wallNum = 0;
					int money = 0;
					long time=0;
					
					TeamItem guard = null;
					TeamItem snipe = null;
					TeamItem defense = null;
					ConditionForPlaceUp con = ConditionForPlaceUp.getConditionForPlaceUp(level);

					List<TeamItem> teamItemList = getService.getTeamItemList(teamId);
					for(TeamItem teamItem :teamItemList){
						if(teamItem.getSysItem().getSubType()==ItemIntensifyUtil.TEAM_STORAGE_TYPE.DEFENSE_TOWER.getValue()){
							towerNum = towerNum+teamItem.getShowQuantity();//记塔数
							if(teamItem.getSysItem().getWId()==Constants.WEAPON_TEAM_ELEMENTARY_TOWER){//机枪塔
								guard = teamItem;
							}else if(teamItem.getSysItem().getWId()==Constants.WEAPON_TEAM_MIDDLE_TOWER){//狙击塔
								snipe = teamItem;
							}else if(teamItem.getSysItem().getWId()==Constants.WEAPON_TEAM_ADVANCED_TOWER){//防御塔
								defense = teamItem;
							}
						}else if(teamItem.getSysItem().getSubType()==ItemIntensifyUtil.TEAM_STORAGE_TYPE.WALL.getValue()){
							wallNum = wallNum+teamItem.getShowQuantity();//记墙数
						}
					}
						long lastTime = team.getLastTeamPlaceLevelUpTime();
						long nowTime = System.currentTimeMillis()/1000;
						ConditionForPlaceUp curCon = new ConditionForPlaceUp(level,towerNum,wallNum,guard,snipe,defense);
						
					
						if(lastTime==0){
							time = 0;
						}else{
							if(nowTime-lastTime>con.getTime()){
								time = 0;
							}else{
								time = con.getTime() - (nowTime-lastTime);
							}
						}
						if(con.aaequals(curCon)&&time==0){
							isCanLevelUp=1;
						}
						money = con.getMoney();
					
					return Converter.getTeamPlaceUp(level, isCanLevelUp, time*1000,money);
				}
				else if(manageType==ItemIntensifyUtil.TEAM_ITEM_MANAGE_TYPE.REPAIR_TEAM_ITEM.getValue()){//修理
					int price =ti.getRepairPrice();
					return "price="+price;
				}
				else if(manageType==ItemIntensifyUtil.TEAM_ITEM_MANAGE_TYPE.SUPPLY.getValue()){//补给
					int price = ti.getSupplyPrice();
					return "price="+price;
				}
			}
			if(playerItemIdStr!=null&&!"-1".equals(playerItemIdStr)){//个人空间配置
				
			}
		}catch (Exception e) {
			log.error(""+e);
		}
		return "";
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
