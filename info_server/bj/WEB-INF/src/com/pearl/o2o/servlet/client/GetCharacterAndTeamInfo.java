package com.pearl.o2o.servlet.client;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.o2o.exception.BaseException;
import com.pearl.o2o.pojo.Character;
import com.pearl.o2o.pojo.Player;
import com.pearl.o2o.pojo.PlayerTeam;
import com.pearl.o2o.pojo.Team;
import com.pearl.o2o.utils.CommonUtil;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.StringUtil;

public class GetCharacterAndTeamInfo extends BaseClientServlet {
	private static final long serialVersionUID = -7782733693520784343L;
	private static Logger log = LoggerFactory.getLogger(GetCharacterAndTeamInfo.class.getName());
	private static final String[] paramNames = {"pid","cid","t"};
	
	protected String innerService(String... args) {
		String result;
		try{
			int playerId=StringUtil.toInt(args[0]);
			int characterId=StringUtil.toInt(args[1]);
			String type = args[2]; 
			Player player = getService.getPlayerById(playerId);
			CommonUtil.checkNull(player, ExceptionMessage.NOT_FIND_PLAYER);
			int  rankNumInTop = 0;
			if("kFightNum".equals(type)){
				rankNumInTop = getService.getPlayerFightNumRankInTop(playerId, String.valueOf(characterId));
			}else{
				rankNumInTop = getService.getCommonRankByType(playerId, type);
			}
			Character ch = null;
			if(characterId==0){
				ch = getService.getMaxFightnumCharacterById(playerId);
			}else{
				ch = getService.getCharacterByCharacterId(playerId,characterId);
			}
			/*if(ch.getCostumeList().size()<3||ch.getWeaponList().size()<4){
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
			}*/
			if(ch.getCostumeList().size()<3||ch.getWeaponList().size()<4){
				log.warn("error happened in get character. "+playerId+" CostumePackList="+ch.getCostumeList().size()+" weaponlist="+ch.getWeaponList().size());
//				throw new BaseException(ExceptionMessage.ERROR_MESSAGE_RETRY);
			}
			
			Team team = null;
			PlayerTeam pteam = getService.getPlayerTeamByPlayerIdSimple(playerId);
			if(pteam!=null){
				team = getService.getTeam(pteam.getTeamId());
			}
			
			//检测套装情况
			ch.checkHasSuitNow();
			result=Converter.characterTeamInfo(player,rankNumInTop,ch,team);
			return result;
		}catch (BaseException e) {
			log.debug("ch getCharacterByCharacterId size error:"+e.getMessage());
			return Converter.error(e.getMessage());
		}
		catch (Exception e) {
			log.warn("GetCharacterAndTeamInfo/Error:\t",e);
			return Converter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
		}
	}

	@Override
	protected String[] paramNames() {
		return paramNames;
	}
	/*@Override
	protected String getLockKey(String[] paramNames) {
		return CommonUtil.getLockKey(StringUtil.toInt(paramNames[0]));
	}*/
}
