package com.pearl.o2o.servlet.client;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.o2o.exception.BaseException;
import com.pearl.o2o.pojo.Player;
import com.pearl.o2o.pojo.PlayerItem;
import com.pearl.o2o.pojo.SysCharacter;
import com.pearl.o2o.utils.BiochemicalConstants;
import com.pearl.o2o.utils.CacheUtil;
import com.pearl.o2o.utils.CommonUtil;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.GrowthMissionConstants.GrowthMissionType;
import com.pearl.o2o.utils.StringUtil;

public class SetCharacter extends BaseClientServlet {
	private static final long serialVersionUID = 4905285569651840521L;
	private static Logger log = LoggerFactory.getLogger(SetCharacter.class.getName());
	private static final String[] paramNames = {"pid","cids","bid"};
	
	protected String innerService(String... args) {
		try{
			int playerId=StringUtil.toInt(args[0]);
			String characterIds=args[1];
			String bioId=args[2];
			
			String[] newIds=characterIds.split(Constants.COMMA);
			Player player=getService.getPlayerById(playerId);
			String selectIds="";
			List<SysCharacter> characterList=getService.getDefaultSysCharacterList();
			for(String id:player.getCharactersIds()){
				for(String newId:newIds){
					if(id.equals(newId)){
						for(SysCharacter sc:characterList){
							if(StringUtil.toInt(id)==sc.getId()){
								selectIds+=id+Constants.COMMA;
								break;
							}
						}
					}
				}
			}
			if("".equals(selectIds)){
				throw new BaseException(ExceptionMessage.NOT_MIN_CHARACTER_SIZE);
			}
			selectIds=CommonUtil.cutLastWord(selectIds);
			player.setSelectedCharacters(selectIds);
			//生化人参战名单
			//找到选择的生化人item，并使用之
			if(bioId.matches("^\\d+$")){
				int bioItemID=Integer.parseInt(bioId);
				if(bioItemID>0){
					List<PlayerItem> selectedBioItemList=getService.getPlayerItemListByItemId(playerId, Integer.parseInt(bioId));
					//selectedBioItemList 中存在leftTime=0的值，因此必须过滤掉
					List<PlayerItem> filterBioItemList=new ArrayList<PlayerItem>();
					for(PlayerItem pItem : selectedBioItemList){
						if(pItem.getTimeLeft()>0){
							filterBioItemList.add(pItem);
						}
					}
					//降序排列
					Collections.sort(filterBioItemList, new Comparator<PlayerItem>(){
	
						@Override
						public int compare(PlayerItem o1, PlayerItem o2) {
							// TODO Auto-generated method stub
							return o2.getLeftSeconds()-o1.getLeftSeconds();
						}
						
					});
					createService.useItemById(player, Constants.DEFAULT_ITEM_TYPE, filterBioItemList.get(0).getId(), "", 0,0);
				}else if(bioItemID==0){
					List<PlayerItem> allPlayerBuffList=getService.getPlayerBuffListById(playerId);
				
					for(PlayerItem pi: allPlayerBuffList){
						if(pi.getSysItem().getIBuffId()==BiochemicalConstants.ordinaryBuffId){
							PlayerItem deleteItem=pi;
							if(deleteItem.getId()>0){
								deleteService.deletePlayerBuff(deleteItem);
							}else{
								log.error("Exception happen in delete bio buff with error data!"); 
							}
							break;
						}
					}	
				}
			}
			
			updateService.updatePlayerInfo(player);
			mcc.delete(CacheUtil.oPlayer(player.getId()));
			mcc.delete(CacheUtil.sPlayer(player.getId()));
			
			//成长任务7：更改参战名单
			updateService.updatePlayerGrowthMission(player, GrowthMissionType.CHANGE_WAR_LIST);

			String 	result=Converter.commonFeedback(null);
			return result;
		}catch (BaseException e) {
			log.debug("Exception happen in GetPackList:"+e.getMessage());
			return Converter.error(e.getMessage());
		}
		catch (Exception e) {
			log.warn("Exception happen in GetPackList",e);
			return Converter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
		}
	}

	@Override
	protected String[] paramNames() {
		return paramNames;
	}

}
