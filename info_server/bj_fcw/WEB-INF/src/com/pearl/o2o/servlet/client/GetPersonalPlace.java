package com.pearl.o2o.servlet.client;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.o2o.pojo.Player;
import com.pearl.o2o.pojo.PlayerItem;
import com.pearl.o2o.pojo.SysItem;
import com.pearl.o2o.pojo.Team;
import com.pearl.o2o.pojo.TeamTechnology;
import com.pearl.o2o.utils.CommonUtil;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ItemIntensifyUtil;
import com.pearl.o2o.utils.ItemIntensifyUtil.TEAM_STORAGE_TYPE;
import com.pearl.o2o.utils.StringUtil;

public class GetPersonalPlace extends BaseClientServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5417553897076163355L;
	private Logger log = LoggerFactory.getLogger(GetPersonalPlace.class);
	private static final String[] paramNames = { "playerId","storageType","curPage","teamId"};
	@Override
	protected String innerService(String... strings) {
		try{
		int playerId = Integer.valueOf(strings[0]);
		int storageType = Integer.valueOf(strings[1]);
		int curPage = Integer.valueOf(strings[2]);
		int teamId = Integer.valueOf(strings[3]);
		Player player = getService.getPlayerById(playerId);
		Team team = getService.getTeamById(teamId);
		int fc = getService.getPlayerInfoById(playerId).getXunleiPoint();
		int placeLevel = team.getTeamSpaceLevel();
		HashMap<String, Integer> playerRes=player.getLatestPlayerRes(placeLevel);
		int resoucesNum = playerRes.get(Player.RES);
		
		List<PlayerItem> items=new ArrayList<PlayerItem>();
		if(storageType==ItemIntensifyUtil.TEAM_STORAGE_TYPE.PERSONAL_TANK.getValue()){
		
			items.addAll(getService.getPlayerItemByItemIid(playerId, Constants.DEFAULT_ZYZDZ_TYPE, Constants.SPECIAL_ITEM_IIDS.TANK.getValue()));
		
			if(items==null||items.size()<=0){
				List<TeamTechnology> ttList = getService.getTeamTechnologyByType(storageType);
				for(TeamTechnology tt:ttList){
					if(tt.getPlace()==1){
						SysItem sysItem = getService.getSysItemByItemId(tt.getCurNode());
						sysItem.setGunProperty3("0,0,1");// hp 
						sysItem.setGunProperty2("0,0,1");// 射速
						sysItem.setGunProperty1("0,0,1");// 威力
						sysItem.setGunProperty6("0,0,1");// 移动
						int playerItemId = createService.createPlayerItem(playerId, sysItem, 1, 1, Constants.BOOLEAN_NO,  Constants.BOOLEAN_NO,  Constants.BOOLEAN_NO,new Date(System.currentTimeMillis()-24*60*60*1000l));
						items.add(getService.getPlayerItemById(playerId, playerItemId));
					}
				}
			}
		}else if(storageType==ItemIntensifyUtil.TEAM_STORAGE_TYPE.PERSONAL_SKILL.getValue()){
			for(int iid=Constants.SPECIAL_ITEM_IIDS.BLOOD_BOTTLE.getValue();iid<=Constants.SPECIAL_ITEM_IIDS.ZYZDZ_BUFF_ATT_SP.getValue();iid++){
				if(iid!=Constants.SPECIAL_ITEM_IIDS.TANK.getValue()){
					List<PlayerItem> playerItems=getService.getPlayerItemByItemIid(playerId, Constants.DEFAULT_ZYZDZ_TYPE, iid);
					
					if(playerItems==null||playerItems.size()==0){
						List<SysItem> siList=getService.getSysItemByIID(iid, Constants.DEFAULT_ZYZDZ_TYPE);
						if(siList!=null && siList.size()>0){
							SysItem siItem=siList.get(0);
							if(siItem!=null){
								int playerItemId = createService.createPlayerItem(playerId, siItem, 1, 1, Constants.BOOLEAN_NO,  Constants.BOOLEAN_NO,  Constants.BOOLEAN_NO,new Date(System.currentTimeMillis()-24*60*60*1000l));
								items.add(getService.getPlayerItemById(playerId, playerItemId));
							}
						}
						
					}else{
						items.addAll(playerItems);
					}
				}
			}
			setItemsRealPrice(TEAM_STORAGE_TYPE.PERSONAL_SKILL, items, playerId);
		}
		
		
		int pages = CommonUtil.getListPages(items, Constants.TEAM_PAGE_SIZE);
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
		items = items.subList(fromIndex, toIndex > items.size() ? items.size() : toIndex);
		
		String result= Converter.getPersonalPlace(fc,storageType,curPage,pages,resoucesNum,items);
		return result;
		
		
		}catch (Exception e) {
			log.warn(""+e);
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
	
	
	private void setItemsRealPrice(TEAM_STORAGE_TYPE type,List<PlayerItem> items,int playerId) throws Exception{
		if(TEAM_STORAGE_TYPE.PERSONAL_SKILL==type && items!=null){
			for (PlayerItem playerItem : items) {
				SysItem clonedItem=getService.getClonedZyzdzBuff(playerItem.getSysItem(), playerId);
				playerItem.setSysItem(clonedItem);
			}
		}
	}	
}
