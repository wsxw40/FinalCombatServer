package com.pearl.o2o.servlet.client;


import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.o2o.exception.BaseException;
import com.pearl.o2o.pojo.Character;
import com.pearl.o2o.pojo.PlayerItem;
import com.pearl.o2o.pojo.SysCharacter;
import com.pearl.o2o.utils.CacheUtil;
import com.pearl.o2o.utils.CommonMsg;
import com.pearl.o2o.utils.CommonUtil;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.StringUtil;

public class GetCharacter extends BaseClientServlet {
	private static final long serialVersionUID = -7782733693520784343L;
	private static Logger log = LoggerFactory.getLogger(GetCharacter.class.getName());
	private static final String[] paramNames = {"pid","cid"};
	
	protected String innerService(String... args) {
		String result;
		try{
			int playerId=StringUtil.toInt(args[0]);
			int characterId=StringUtil.toInt(args[1]);
			Character ch=getService.getCharacterByCharacterId(playerId,characterId);
			if(ch.getCostumeList().size()<3||ch.getWeaponList().size()<4){
				soClient.puchCMDtoClient(getService.getSimplePlayerById(playerId).getName(), CommonUtil.messageFormat(CommonMsg.WARN_MSG, CommonMsg.WARN_MSG_STR));
				log.warn("error happened in get character,and reset auto. "+playerId+" CostumePackList="+ch.getCostumeList().size()+" weaponlist="+ch.getWeaponList().size());
				for(SysCharacter sc : getService.getDefaultSysCharacterList()){
					mcc.delete(CacheUtil.oStorage1(playerId,Constants.DEFAULT_WEAPON_TYPE, sc.getId(), 0));
					mcc.delete(CacheUtil.oStorage1(playerId,Constants.DEFAULT_WEAPON_TYPE, sc.getId(), 1));
					mcc.delete(CacheUtil.oStorage1(playerId,Constants.DEFAULT_WEAPON_TYPE, sc.getId(), 2));
					mcc.delete(CacheUtil.oStorage1(playerId,Constants.DEFAULT_WEAPON_TYPE, sc.getId(), 3));
					mcc.delete(CacheUtil.oStorage1(playerId,Constants.DEFAULT_WEAPON_TYPE, sc.getId(), 4));
					mcc.delete(CacheUtil.oStorage1(playerId,Constants.DEFAULT_COSTUME_TYPE, sc.getId(), 0));
					mcc.delete(CacheUtil.oStorage1(playerId,Constants.DEFAULT_PART_TYPE,sc.getId(), 0));
					mcc.delete(CacheUtil.oStorage1(playerId,Constants.DEFAULT_PART_TYPE,sc.getId(), 1));
					mcc.delete(CacheUtil.oStorage1(playerId,Constants.DEFAULT_PART_TYPE,sc.getId(), 2));
				}
				mcc.delete(CacheUtil.oCharacterList(playerId));
				for (int i=1;i<=Constants.MAX_CHARACTER_SIZE;i++){
					mcc.delete(CacheUtil.oWeaponPack(playerId, 1,i));
					mcc.delete(CacheUtil.oCostumePack(playerId, 1,i));
				}
				updateService.resetPlayerWeapon(playerId,characterId);
				ch=getService.getCharacterByCharacterId(playerId,characterId);
			}
			if(ch.getCostumeList().size()<3||ch.getWeaponList().size()<4){
				throw new BaseException(ExceptionMessage.ERROR_MESSAGE_RETRY);
			}else{

//				List<PlayerItem> list=ch.getCostumeList();
//				list.addAll(ch.getWeaponList());
//				for(Iterator<PlayerItem> it=list.iterator();it.hasNext();){
//					PlayerItem pi= it.next();
//				    SysItem sysItem=getService.getSysItemByItemId(pi.getItemId());
//					int lowPropertyNum = 0;
//					int middlePropertyNum = 0;
//					int highPropertyNum = 0;
//				    int sysItemFightNum=0;//基础战斗力
//				    int strengthenItemFightNum=0;//强化带来的战斗力
//				    double b=0;
//				    double a=0;
//					if(sysItem.getType() == Constants.DEFAULT_WEAPON_TYPE){
//						if(sysItem.getSubType() == Constants.NUM_ONE){//主武器
//							a= Math.pow(Constants.FIGHT_PARAM[0][1],pi.getLevel())-1;
//							b = (lowPropertyNum/Constants.FIGHT_PARAM[0][4] + middlePropertyNum/Constants.FIGHT_PARAM[0][5] + highPropertyNum/Constants.FIGHT_PARAM[0][6]);
//							sysItemFightNum = (int)(Constants.FIGHT_PARAM[0][0] * Math.pow(Constants.FIGHT_PARAM[0][1],((sysItem.getRareLevel() - 1)/Constants.FIGHT_PARAM[0][2])));
//						} else if(sysItem.getSubType() == Constants.NUM_TWO){//副武器
//							a= Math.pow(Constants.FIGHT_PARAM[5][1],(pi.getLevel()))-1;
//							b = (lowPropertyNum/Constants.FIGHT_PARAM[5][4] + middlePropertyNum/Constants.FIGHT_PARAM[5][5] + highPropertyNum/Constants.FIGHT_PARAM[5][6]);
//							sysItemFightNum = (int)(Constants.FIGHT_PARAM[5][0] * Math.pow(Constants.FIGHT_PARAM[5][1],((sysItem.getRareLevel() - 1)/Constants.FIGHT_PARAM[5][2])));
//						} else if(sysItem.getSubType() == Constants.NUM_THREE){//近身器
//							a= Math.pow(Constants.FIGHT_PARAM[1][1],(pi.getLevel()))-1;
//							b = (lowPropertyNum/Constants.FIGHT_PARAM[1][4] + middlePropertyNum/Constants.FIGHT_PARAM[1][5] + highPropertyNum/Constants.FIGHT_PARAM[1][6]);
//							sysItemFightNum = (int)(Constants.FIGHT_PARAM[1][0] * Math.pow(Constants.FIGHT_PARAM[1][1],((sysItem.getRareLevel() - 1)/Constants.FIGHT_PARAM[1][2])));
//						}
//					} else if(sysItem.getType() == Constants.DEFAULT_COSTUME_TYPE){//服装
//						a=Math.pow(Constants.FIGHT_PARAM[2][1],pi.getLevel())-1;
//							b = (lowPropertyNum/Constants.FIGHT_PARAM[2][4] + middlePropertyNum/Constants.FIGHT_PARAM[2][5] + highPropertyNum/Constants.FIGHT_PARAM[2][6]);
//						    sysItemFightNum = (int)(Constants.FIGHT_PARAM[2][0] * ((Math.pow(Constants.FIGHT_PARAM[2][1], ((sysItem.getRareLevel() - 1)/Constants.FIGHT_PARAM[2][2]))) - 1));
//					} else if(sysItem.getType() == Constants.DEFAULT_PART_TYPE){
//						if(sysItem.getSubType() == Constants.NUM_ONE){//帽子
//							a=Math.pow(Constants.FIGHT_PARAM[3][1],pi.getLevel())-1;
//							b = (lowPropertyNum/Constants.FIGHT_PARAM[3][4] + middlePropertyNum/Constants.FIGHT_PARAM[3][5] + highPropertyNum/Constants.FIGHT_PARAM[3][6]);
//							sysItemFightNum = (int)(Constants.FIGHT_PARAM[3][0] * ((Math.pow(Constants.FIGHT_PARAM[3][1],((sysItem.getRareLevel() - 1)/Constants.FIGHT_PARAM[3][2]) )) - 1));
//						} else if(sysItem.getSubType() == Constants.NUM_TWO){//配饰
//							a=Math.pow(Constants.FIGHT_PARAM[4][1],pi.getLevel())-1;
//							b = (lowPropertyNum/Constants.FIGHT_PARAM[4][4] + middlePropertyNum/Constants.FIGHT_PARAM[4][5] + highPropertyNum/Constants.FIGHT_PARAM[4][6]);
//							sysItemFightNum = (int)(Constants.FIGHT_PARAM[4][0] * (Math.pow(Constants.FIGHT_PARAM[4][1],((sysItem.getRareLevel() - 1)/Constants.FIGHT_PARAM[4][2])) - 1));
//						}
//					}
//					if((a+b)!=0){
//						strengthenItemFightNum=(int)((a/(a+b))*(pi.getFightNum()-sysItemFightNum));//强化增加的战斗力
//					} 
//					pi.setSysItemFightNum(sysItemFightNum);
//					pi.setStrengthenItemFightNum(strengthenItemFightNum);
					
//				}
			}
//			Player player=getService.getPlayerById(playerId);
//			int maxCFightNum=0;
//			for(PlayerItem pi:ch.getCostumeList()){
//				if(pi!=null){
//					SysItem si=getService.getSysItemByItemId(pi.getItemId());
//					pi.setSysItem(si);
//					if(ch.getFightNum()>maxCFightNum){
//						maxCFightNum=ch.getFightNum();
//					}
//					pi.initWithoutPart(si);
//				}
//			}
//			for(PlayerItem pi:ch.getWeaponList()){
//				if(pi!=null){
//					SysItem si=getService.getSysItemByItemId(pi.getItemId());
//					pi.setSysItem(si);
//					if(ch.getFightNum()>maxCFightNum){
//						maxCFightNum=ch.getFightNum();
//					}
//					pi.initWithoutPart(si);
//				}
//			}
//			if(maxCFightNum!=player.getMaxFightNum()){
//				player.setMaxFightNum(maxCFightNum);
//				ServiceLocator.updateService.updatePlayerInfoOnly(player);
//			}
			//检测套装情况
			ch.checkHasSuitNow();
			//zlm2015-5-7-限时装备-开始------------在身上的显示续费时间-------------------------
			//weaponList; 武器
			for (PlayerItem playerItem : ch.getWeaponList()) {
				//先缩小范围
				if(playerItem.getProvisional_item_flag()){
                    playerItem.setProvisional_item_day(CommonUtil.provisional_time(nosqlService.getNosql(), playerItem, playerId));
				}
			}
			//costumeList; 衣服
			for (PlayerItem playerItem : ch.getCostumeList()) {
				//先缩小范围
				if(playerItem.getProvisional_item_flag()){
				    playerItem.setProvisional_item_day(CommonUtil.provisional_time(nosqlService.getNosql(), playerItem, playerId));
				}
			}
			//zlm2015-5-7-限时装备-结束-------------------------------------
			Integer mateRank=getService.getQW_MateRank(playerId);
			result=Converter.playerPack(ch,getService.getSimplePlayerById(playerId).getName(),mateRank);
			return result;
		}catch (BaseException e) {
			log.debug("ch getCharacterByCharacterId size error:"+e.getMessage(),e);
			return Converter.error(e.getMessage());
		}
		catch (Exception e) {
			log.warn("Exception happen in GetCharacter",e);
			return Converter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
		}
	}

	@Override
	protected String[] paramNames() {
		return paramNames;
	}
	@Override
	protected String getLockKey(String[] paramNames) {
		return CommonUtil.getLockKey(StringUtil.toInt(paramNames[1]));
	}
}
