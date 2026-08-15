package com.pearl.o2o.servlet.client;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.o2o.pojo.Player;
import com.pearl.o2o.pojo.PlayerInfo;
import com.pearl.o2o.pojo.SysItem;
import com.pearl.o2o.pojo.Team;
import com.pearl.o2o.pojo.TeamItem;
import com.pearl.o2o.pojo.TeamTechnology;
import com.pearl.o2o.utils.CommonUtil;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ItemIntensifyUtil;
import com.pearl.o2o.utils.ItemIntensifyUtil.ConditionForPlaceUp;
import com.pearl.o2o.utils.StringUtil;

import edu.emory.mathcs.backport.java.util.Collections;

public class GetTeamResourceWarInfo extends BaseClientServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8987301741651923706L;
	private Logger log = LoggerFactory.getLogger(GetTeamResourceWarInfo.class);
	private static final String[] paramNames = { "playerId", "teamId", "storageType","curPage"};
	@SuppressWarnings("unused")
	@Override
	protected String innerService(String... strings) {
		try{
			
			//int userId = StringUtil.toInt(strings[0]);
			int playerId = Integer.valueOf(strings[0]);
			int teamId = Integer.valueOf(strings[1]);
			int storageType = Integer.valueOf(strings[2]);
			int curPage = Integer.valueOf(strings[3]);
			
			Team team = getService.getTeamById(teamId);
			
			HashMap<String, Integer> teamResHashMap=team.getLatestTeamRes();
			
			Player player = getService.getPlayerById(playerId);
			//PlayerTeam pt = getService.getPlayerTeamByPlayerIdSimple(playerId);
			int placeLevel = team.getTeamSpaceLevel();
			
			int isLeader = team.getHeaderId()==player.getId()?1:0;
			int resoucesNum = teamResHashMap.get(Team.RES);

			List<TeamItem> itemList=getService.getTeamItemList(teamId,storageType);
			
			PlayerInfo playerInfo = getService.getPlayerInfoById(playerId);
			if((itemList==null||itemList.size()==0)){
				if(storageType==ItemIntensifyUtil.TEAM_STORAGE_TYPE.DEFENSE_TOWER.getValue()||storageType==ItemIntensifyUtil.TEAM_STORAGE_TYPE.WALL.getValue()){
					List<TeamTechnology> ttList = getService.getTeamTechnologyByType(storageType);
					for(TeamTechnology tt:ttList){
						if(tt.getPlace()==1){
							SysItem sysItem = getService.getSysItemByItemId(tt.getCurNode());
							TeamItem ti;
							if(storageType==ItemIntensifyUtil.TEAM_STORAGE_TYPE.DEFENSE_TOWER.getValue()){//默认给几个塔
								ti = createService.createTeamItem(teamId, sysItem, ItemIntensifyUtil.DEFAULT_TEAM_TOWER_NUM, 1, Constants.BOOLEAN_NO, Constants.BOOLEAN_NO, Constants.BOOLEAN_NO,0);
							}else{
								ti = createService.createTeamItem(teamId, sysItem, 0, 1, Constants.BOOLEAN_NO, Constants.BOOLEAN_NO, Constants.BOOLEAN_NO,0);
							}
							getService.setTeamItemCurrentPayMentAndMaxQuantityByItemAndPLevel(ti, placeLevel);
							ti.setSysItem(sysItem);
							itemList.add(ti);
						}
					}
				}else if(storageType == ItemIntensifyUtil.TEAM_STORAGE_TYPE.OIL_TANK.getValue()){
				    List<SysItem> sysItemList = getService.getSysItemByType(Constants.DEFAULT_ZYZDZ_TYPE, ItemIntensifyUtil.TEAM_STORAGE_TYPE.OIL_TANK.getValue());
				    for(SysItem si:sysItemList){
				    	if(si.getWId()==Constants.WEAPON_ZYZDZ_OIL_POT&&si.getLevel()==1){
				    		TeamItem ti = createService.createTeamItem(teamId, si, ItemIntensifyUtil.DEFAULT_TEAM_OIL_CAN, 1, Constants.BOOLEAN_NO, Constants.BOOLEAN_NO, Constants.BOOLEAN_NO,0);
							ti.setSysItem(si);
							itemList.add(ti);
				    	}
				    }
				}
				
			}
			int pages = CommonUtil.getListPages(itemList, Constants.TEAM_PAGE_SIZE);
			if(pages==0){
				pages=1;
			}
			if (curPage > pages){
				curPage = pages;
			}else if(curPage <= 0) {
				curPage = 1;
			}
			int fromIndex = (curPage - 1) * Constants.TEAM_PAGE_SIZE;
			int toIndex = (curPage) * Constants.TEAM_PAGE_SIZE;
			itemList = itemList.subList(fromIndex, toIndex > itemList.size() ? itemList.size() : toIndex);
			getService.setTeamItemCurrentPayMentAndMaxQuantityByItemListAndPLevel(itemList, placeLevel);
			long time =0;
			if(team.getTeamSpaceLevel()>=ItemIntensifyUtil.MAX_TEAM_PLACE_LEVEL){
				time = 0;
			}else{
				ConditionForPlaceUp con = ConditionForPlaceUp.getConditionForPlaceUp(team.getTeamSpaceLevel());
				long lastTime = team.getLastTeamPlaceLevelUpTime();
				long nowTime = System.currentTimeMillis()/1000;
				if(lastTime==0){
					time = 0;
				}else{
					if(nowTime-lastTime>con.getTime()){
						time = 0;
						team.setLastTeamPlaceLevelUpTime(0);
						team.setTeamSpaceLevel(team.getTeamSpaceLevel()+1);
						updateService.updateTeamInfo(team);
					}else{
						time = con.getTime() - (nowTime-lastTime);
					}
				}
			}
			Collections.sort(itemList,new Comparator<TeamItem>() {

				@Override
				public int compare(TeamItem o1, TeamItem o2) {
					return o1.getSysItem().getWId()-o2.getSysItem().getWId();
				}
				
			});
			String result= Converter.getTeamResWarInfo(time*1000,storageType,placeLevel,isLeader,curPage,pages,resoucesNum,itemList,playerInfo.getXunleiPoint(),player.getUsableResource());
			return result;
			
		}catch(Exception e){
			
			log.error("error happen GetTeamResourceWarInfo ",e);
		}
		return super.innerService(strings);
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
