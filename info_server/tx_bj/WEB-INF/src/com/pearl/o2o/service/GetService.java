package com.pearl.o2o.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeoutException;

import net.rubyeye.xmemcached.GetsResponse;
import net.rubyeye.xmemcached.exception.MemcachedException;

import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import redis.clients.jedis.Tuple;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.base.Strings;
import com.google.common.collect.Collections2;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.Iterables;
import com.google.common.collect.Multiset;
import com.google.common.collect.Ordering;
import com.google.common.primitives.Ints;
import com.mysql.jdbc.StringUtils;
import com.pearl.o2o.exception.BaseException;
import com.pearl.o2o.nosql.NoSql;
import com.pearl.o2o.nosql.NosqlKeyUtil;
import com.pearl.o2o.nosql.object.playerevent.BasePlayerEvent;
import com.pearl.o2o.nosql.object.playerrate.PlayerRate;
import com.pearl.o2o.pojo.Ally;
import com.pearl.o2o.pojo.AwardItemVo;
import com.pearl.o2o.pojo.BannedWord;
import com.pearl.o2o.pojo.BattleFieldRobDaily;
import com.pearl.o2o.pojo.BiochemicalCharacter;
import com.pearl.o2o.pojo.BlackIP;
import com.pearl.o2o.pojo.BossPojo;
import com.pearl.o2o.pojo.BuyItemRecord;
import com.pearl.o2o.pojo.Channel;
import com.pearl.o2o.pojo.Character;
import com.pearl.o2o.pojo.CharacterData;
import com.pearl.o2o.pojo.CombineProperty;
import com.pearl.o2o.pojo.Friend;
import com.pearl.o2o.pojo.GrowthMissionVo;
import com.pearl.o2o.pojo.LevelInfo;
import com.pearl.o2o.pojo.LevelModeInfo;
import com.pearl.o2o.pojo.LevelWeapon;
import com.pearl.o2o.pojo.MeltingAward;
import com.pearl.o2o.pojo.MeltingReslut;
import com.pearl.o2o.pojo.Message;
import com.pearl.o2o.pojo.OnlineAward;
import com.pearl.o2o.pojo.OnlineAwardReturnValue;
import com.pearl.o2o.pojo.PayLog;
import com.pearl.o2o.pojo.Payment;
import com.pearl.o2o.pojo.Player;
import com.pearl.o2o.pojo.PlayerAchievement;
import com.pearl.o2o.pojo.PlayerActivity;
import com.pearl.o2o.pojo.PlayerBuff;
import com.pearl.o2o.pojo.PlayerGPointTotal;
import com.pearl.o2o.pojo.PlayerGrowthMission;
import com.pearl.o2o.pojo.PlayerInfo;
import com.pearl.o2o.pojo.PlayerItem;
import com.pearl.o2o.pojo.PlayerLocation;
import com.pearl.o2o.pojo.PlayerMelting;
import com.pearl.o2o.pojo.PlayerMission;
import com.pearl.o2o.pojo.PlayerPack;
import com.pearl.o2o.pojo.PlayerTeam;
import com.pearl.o2o.pojo.PlayerVO;
import com.pearl.o2o.pojo.QwPlayerDay;
import com.pearl.o2o.pojo.QwPlayerSum;
import com.pearl.o2o.pojo.Rank;
import com.pearl.o2o.pojo.Server;
import com.pearl.o2o.pojo.StrengthenAppend;
import com.pearl.o2o.pojo.SysAchievement;
import com.pearl.o2o.pojo.SysAchievementBase;
import com.pearl.o2o.pojo.SysActivity;
import com.pearl.o2o.pojo.SysBioCharacter;
import com.pearl.o2o.pojo.SysCharacter;
import com.pearl.o2o.pojo.SysChest;
import com.pearl.o2o.pojo.SysConfiguration;
import com.pearl.o2o.pojo.SysGrowthMission;
import com.pearl.o2o.pojo.SysIcon;
import com.pearl.o2o.pojo.SysItem;
import com.pearl.o2o.pojo.SysMatchP;
import com.pearl.o2o.pojo.SysMission;
import com.pearl.o2o.pojo.SysModeConfig;
import com.pearl.o2o.pojo.SysNotice;
import com.pearl.o2o.pojo.SysSuit;
import com.pearl.o2o.pojo.SysTeamBuff;
import com.pearl.o2o.pojo.Team;
import com.pearl.o2o.pojo.TeamBuff;
import com.pearl.o2o.pojo.TeamBuffView;
import com.pearl.o2o.pojo.TeamHistory;
import com.pearl.o2o.pojo.TeamItem;
import com.pearl.o2o.pojo.TeamLevelInfo;
import com.pearl.o2o.pojo.TeamLevelModeInfo;
import com.pearl.o2o.pojo.TeamProclamation;
import com.pearl.o2o.pojo.TeamRecord;
import com.pearl.o2o.pojo.TeamTechnology;
import com.pearl.o2o.pojo.TmpPlayerItem;
import com.pearl.o2o.pojo.UnSpeaker;
import com.pearl.o2o.pojo.User;
import com.pearl.o2o.pojo.XunleiOrderLog;
import com.pearl.o2o.sort.LuckyPackageComparator;
import com.pearl.o2o.utils.BattleFieldStatus;
import com.pearl.o2o.utils.BiochemicalConstants;
import com.pearl.o2o.utils.CacheUtil;
import com.pearl.o2o.utils.ChallengeHillStatus;
import com.pearl.o2o.utils.CommonMsg;
import com.pearl.o2o.utils.CommonUtil;
import com.pearl.o2o.utils.ComparatorUtil;
import com.pearl.o2o.utils.ConfigurationUtil;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.DateFormatUtil;
import com.pearl.o2o.utils.Constants.GAMETYPE;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.GrowthMissionConstants;
import com.pearl.o2o.utils.GrowthMissionConstants.BooleanBytevalue;
import com.pearl.o2o.utils.GrowthMissionConstants.GrowthMissionType;
import com.pearl.o2o.utils.GrowthMissionConstants.ModuleStatus;
import com.pearl.o2o.utils.GrowthMissionConstants.ModuleStatusIndex;
import com.pearl.o2o.utils.ItemIntensifyUtil;
import com.pearl.o2o.utils.ItemIntensifyUtil.ConditionForPlaceUp;
import com.pearl.o2o.utils.JoinDataUtil;
import com.pearl.o2o.utils.LogUtils;
import com.pearl.o2o.utils.MeltingConstants;
import com.pearl.o2o.utils.QuietBounds;
import com.pearl.o2o.utils.ServiceLocator;
import com.pearl.o2o.utils.StringUtil;
import com.pearl.o2o.utils.TeamConstants;

/**
 * @author bobby
 *
 */
public class GetService extends BaseService{

	static Logger log = LoggerFactory.getLogger(GetService.class.getName());
	private MessageService messageService;
	private Random random = new Random(47);
	 
	//===============================================================================	
	//								  Check Services
	//===============================================================================
	public void checkNewPackage(Player player,List<PlayerItem> list) throws Exception{
		List<PlayerItem> newPackageList=new ArrayList<PlayerItem>();
		List<PlayerItem> newPackageList2=new ArrayList<PlayerItem>();
		List<PlayerItem> newPackageList3=new ArrayList<PlayerItem>();
		
		boolean isQuantity=false;
		for(PlayerItem pi:list){
			if(pi.getSysItem().getIId()==23&&pi.getQuantity()>0){
				if(pi.getQuantity()>1){
					isQuantity=true;
				}
				newPackageList.add(pi);
			}else if((pi.getSysItem().getIId()==59)&&pi.getQuantity()>0){
				if(pi.getQuantity()>1){
					isQuantity=true;
				}
				newPackageList2.add(pi);
			}else if((pi.getSysItem().getIId()==60)&&pi.getQuantity()>0){
				if(pi.getQuantity()>1){
					isQuantity=true;
				}
				newPackageList3.add(pi);
			}
		}
		if(newPackageList.size()>1||isQuantity){
			Collections.sort(newPackageList, new Comparator<PlayerItem>(){
				@Override
				public int compare(PlayerItem o1, PlayerItem o2) {
					Integer o11=StringUtil.toInt(o1.getSysItem().getIValue());
					Integer o22=StringUtil.toInt(o2.getSysItem().getIValue());
					return o22.compareTo(o11);
				}
			});
		
			for(int i=0;i<newPackageList.size();i++){
				PlayerItem pi=newPackageList.get(i);
				if(i==0){
					if(pi.getQuantity()>1){
						pi.setQuantity(1);
						ServiceLocator.updateService.updatePlayerItem(pi);
						log.error("new package update num playerid="+player.getId()+" pi="+pi.getId());
					}
				}else{
					pi.setIsDeleted("Y");
					ServiceLocator.deleteService.deletePlayerItem(pi);
					log.error("new package delete playerid="+player.getId()+" pi="+pi.getId());
				}
			}
		}
		
		if(newPackageList2.size()>1||isQuantity){
			Collections.sort(newPackageList2, new Comparator<PlayerItem>(){
				@Override
				public int compare(PlayerItem o1, PlayerItem o2) {
					Integer o11=StringUtil.toInt(o1.getSysItem().getIValue());
					Integer o22=StringUtil.toInt(o2.getSysItem().getIValue());
					return o22.compareTo(o11);
				}
			});
		
			for(int i=0;i<newPackageList2.size();i++){
				PlayerItem pi=newPackageList2.get(i);
				if(i==0){
					if(pi.getQuantity()>1){
						pi.setQuantity(1);
						ServiceLocator.updateService.updatePlayerItem(pi);
						log.error("new package update num playerid="+player.getId()+" pi="+pi.getId());
					}
				}else{
					pi.setIsDeleted("Y");
					ServiceLocator.deleteService.deletePlayerItem(pi);
					log.error("new package delete playerid="+player.getId()+" pi="+pi.getId());
				}
			}
		}
		
		if(newPackageList3.size()>1||isQuantity){
			Collections.sort(newPackageList3, new Comparator<PlayerItem>(){
				@Override
				public int compare(PlayerItem o1, PlayerItem o2) {
					Integer o11=StringUtil.toInt(o1.getSysItem().getIValue());
					Integer o22=StringUtil.toInt(o2.getSysItem().getIValue());
					return o22.compareTo(o11);
				}
			});
		
			for(int i=0;i<newPackageList3.size();i++){
				PlayerItem pi=newPackageList3.get(i);
				if(i==0){
					if(pi.getQuantity()>1){
						pi.setQuantity(1);
						ServiceLocator.updateService.updatePlayerItem(pi);
						log.error("new package update num playerid="+player.getId()+" pi="+pi.getId());
					}
				}else{
					pi.setIsDeleted("Y");
					ServiceLocator.deleteService.deletePlayerItem(pi);
					log.error("new package delete playerid="+player.getId()+" pi="+pi.getId());
				}
			}
		}
	}
	protected String checkPlayerItem(Player player) throws Exception{
		StringBuilder sb=new StringBuilder();
		List<PlayerItem> itemlist=getPlayerItemList1(player.getId(), Constants.DEFAULT_ITEM_TYPE, 0, 0);
		for(PlayerItem pi:itemlist){
			if(pi.getPlayerItemUnitType()==1&pi.getQuantity()>=150){
				String str="checking player item...item="+pi.getItemId()+" quantity="+pi.getQuantity()+" playerId="+player.getId()+" id="+pi.getId();
				sb.append(str).append("\n");
				log.warn(str);
			}
		}
		List<PlayerItem> materiallist=getPlayerItemList1(player.getId(), Constants.DEFAULT_MATERIAL_TYPE, 0, 0);
		for(PlayerItem pi:materiallist){
			if(pi.getPlayerItemUnitType()==1&pi.getQuantity()>=150){
				String str="checking player item...item="+pi.getItemId()+" quantity="+pi.getQuantity()+" playerId="+player.getId()+" id="+pi.getId();
				sb.append(str).append("\n");
				log.warn(str);
			}
		}
		return sb.toString();
	}
	protected String checkNewPackage(Player player) throws Exception{
		StringBuilder sb=new StringBuilder();
		List<PlayerItem> itemArray = getService.getPlayerItemList1(player.getId(), Constants.DEFAULT_OPEN_TYPE, 0, 0);
		List<PlayerItem> newPackageList=new ArrayList<PlayerItem>();
		for(PlayerItem pi:itemArray){
			if(pi.getSysItem().getIId()==23&&pi.getQuantity()>0){
				newPackageList.add(pi);
			}
		}
		if(newPackageList.size()>1){
			Collections.sort(newPackageList, new Comparator<PlayerItem>(){
				@Override
				public int compare(PlayerItem o1, PlayerItem o2) {
					Integer o11=StringUtil.toInt(o1.getSysItem().getIValue());
					Integer o22=StringUtil.toInt(o2.getSysItem().getIValue());
					return o22.compareTo(o11);
				}
			});
			for(int i=0;i<newPackageList.size();i++){
				PlayerItem pi=newPackageList.get(i);
				if(i==0){
					if(pi.getQuantity()>1){
						pi.setQuantity(1);
						ServiceLocator.updateService.updatePlayerItem(pi);
						sb.append("new package update num playerid="+player.getId()+" pi="+pi.getId()).append("\n");
						log.error("new package update num playerid="+player.getId()+" pi="+pi.getId());
					}
				}else{
					pi.setIsDeleted("Y");
					ServiceLocator.deleteService.deletePlayerItem(player.getId(),Constants.DEFAULT_OPEN_TYPE, pi);
					sb.append("new package delete playerid="+player.getId()+" pi="+pi.getId()).append("\n");
					log.error("new package delete playerid="+player.getId()+" pi="+pi.getId());
				}
			}
		}
		return sb.toString();
	}
	protected String checkPlayerPack(Player player) throws Exception{
		StringBuilder sb=new StringBuilder();
		boolean isChange=false;
		for(int i=1;i<=Constants.MAX_CHARACTER_SIZE;i++){
			List<PlayerPack> weaponPackList = playerPackDao.getWeaponPackByPackId(player.getId(), i);
			List<PlayerPack> costumePackList = playerPackDao.getCostumePackByPackId(player.getId(), i);
			if(weaponPackList==null||weaponPackList.size()==0){
				isChange=true;
				PlayerPack pack = new PlayerPack();
				for (int k = 1; k <= Constants.DEFAULT_SEQ_SIZE; k++) {
					if (Constants.weaponPack[i - 1][k - 1] == 0) {
						pack.setPackId(1);
						pack.setExpireTime(null);
						pack.setPlayerId(player.getId());
						pack.setPlayerItemId(null);
						pack.setSeq(k);
						pack.setSysCharacterId(i);
						pack.setType("W");
						ServiceLocator.createService.createPlayerPack(pack);
					} else {
						SysItem sysItem = getService.getSysItemByItemId(Constants.weaponPack[i - 1][k - 1]);
						int playerItemId =getService.getPlayerItemDao().createPlayItem(player.getId(), sysItem, 1, 0, Constants.BOOLEAN_NO, Constants.BOOLEAN_YES, Constants.BOOLEAN_YES);
						pack.setPackId(1);
						pack.setExpireTime(null);
						pack.setPlayerId(player.getId());
						pack.setPlayerItemId(playerItemId);
						pack.setSeq(k);
						pack.setSysCharacterId(i);
						pack.setType("W");
						ServiceLocator.createService.createPlayerPack(pack);
					}
				}
				sb.append("add player weapon pack character="+i).append("\n");
				mcc.delete(CacheUtil.oWeaponPack(player.getId(), 1, i));
			}else if(weaponPackList.size()!=4){
				log.error("weaponPackList have some wrong size="+weaponPackList.size()+" playerId="+player.getId()+" characterId="+i);
			}
			if(costumePackList==null||costumePackList.size()==0){
				isChange=true;
				PlayerPack pack = new PlayerPack();
				for (int k = 1; k <= Constants.DEFAULT_COSTUME_SEQ_SIZE; k++) {
					SysItem sysItem = getService.getSysItemByItemId(Constants.costumePack[i - 1][k - 1]);
					int playerItemId =getService.getPlayerItemDao().createPlayItem(player.getId(), sysItem, 1, 0, Constants.BOOLEAN_NO, Constants.BOOLEAN_YES, Constants.BOOLEAN_YES);
					pack.setPackId(1);
					pack.setExpireTime(null);
					pack.setPlayerId(player.getId());
					pack.setPlayerItemId(playerItemId);
					pack.setSeq(k);
					pack.setSysCharacterId(i);
					pack.setType("C");
					ServiceLocator.createService.createPlayerPack(pack);
				}
				sb.append("add player weapon costume character="+i).append("\n");
				mcc.delete(CacheUtil.oCostumePack(player.getId(), 1, i));
			}else if(costumePackList.size()!=3){
				log.error("costumePackList have some wrong size="+costumePackList.size()+" playerId="+player.getId()+" characterId="+i);
			}
		}
		if(isChange){
			mcc.delete(CacheUtil.oCharacterList(player.getId()));
			log.warn("add playerPack sucessfully playerId="+player.getId());
		}
		return sb.toString();
	}
	protected String checkCharacterData(Player player) throws Exception{
		StringBuilder sb=new StringBuilder();
		for(int i=1;i<=Constants.MAX_CHARACTER_SIZE;i++){
			CharacterData cd=getService.getCharacterData(player.getId(), i);
			if(cd==null){
				CharacterData characterData = new CharacterData();
				characterData.setCharacterId(i);
				characterData.setPlayerId(player.getId());
				ServiceLocator.createService.getCharacterDataDao().create(characterData);
				sb.append("add characterdate playerId="+player.getId()+" characterId="+i);
				log.warn("add characterData sucessfully playerId="+player.getId()+" characterId="+i);
			}
		}
		return sb.toString();
	}
	public void checkDataWhileLogin(Player player){
		try {
			checkNewPackage(player);
			checkPlayerPack(player);
			checkCharacterData(player);
			//check fourth weapon
			checkDefaultFourthWeapon(player);
			checkPlayerItem(player);
			checkMyTeamInfo(player);
			checkSelectCharacterIds(player);
		} catch (Exception e) {
			log.warn("some exception happen in checkDataWhileLogin",e);
		}
		
	}
	/**
	 * 检测玩家所选参战名单
	 * @param player
	 * @throws Exception
	 */
	public void checkSelectCharacterIds(Player player) throws Exception {
		String[] selectIds = player.getSelectedCharacters().split(Constants.COMMA);
		List<String> temList = new ArrayList<String>();
		for(String id :selectIds){
			if(selectIds.length<1||selectIds.length>Constants.MAX_SELECT_CHARACTER_SIZE||StringUtil.toInt(id)>Constants.MAX_CHARACTER_SIZE||StringUtil.toInt(id)<1|| temList.contains(id)){
				log.warn("player:"+player.getId()+"   "+player.getName()+" selectCharacterid wrong:"+player.getSelectedCharacters());
				player.setSelectedCharacters(Constants.DEFAULT_CHARACTERS_SELECT);
				player.setCharacterSize(Constants.DEFAULT_CHARACTERS_SELECT.split(Constants.COMMA).length);
				ServiceLocator.updateService.updatePlayerInfoOnly(player);
				break;
			}
			temList.add(id);
		}
	}
	private void checkMyTeamInfo(Player player) throws Exception {
		int teamId = player.getTeamId();
		boolean isTeamRight = false;
		if(teamId!=0){
			List<PlayerTeam> playerTeams = getPlayerTeamByTeamIdSimple(teamId);
			for(PlayerTeam pt : playerTeams){
				if(pt.getId()==player.getId()){
					isTeamRight = true;
				}
			}
		}
		if(!isTeamRight){
			Team team = getTeamByPlayerId(player.getId());
			if(team!=null){
				player.setTeamId(team.getId());
				ServiceLocator.updateService.updatePlayerInfoOnly(player);
			}
		}
	}
	public void checkPlayerDataWhenEnterGame(Player player,List<Character> characterList,boolean isKnife) throws Exception{
		if(characterList.size()>6){
			//playerId size
			ServiceLocator.checkCharacterData.debug(String.format("characterList\t%s\t%s",player.getId(),characterList.size()));
		}
		for (Character character : characterList) {
			if((character.getWeaponList().size()<4||character.getCostumeList().size()<3)&&!isKnife){
				//playerId characterId weaponListSize costumeListSize
				ServiceLocator.checkCharacterData.debug(String.format("character\t%s\t%s\t%s\t%s",player.getId(),character.getSysCharacter().getId(),character.getWeaponList().size(),character.getCostumeList().size()));
			}
		}
	}
	
	protected String  checkDefaultFourthWeapon(Player player) throws Exception{
		StringBuilder sb=new StringBuilder();
		for(int characterId=1;characterId<=Constants.MAX_CHARACTER_SIZE;characterId++){
			if(characterId!=4){
				List<PlayerItem> weapons=getService.getDefaultPackList(player.getId(), Constants.PACK_TYPE_W, characterId);
				if(weapons.size()!=4){
					boolean haveFourth=false;
					for(PlayerItem pi:weapons){
						int seq=CommonUtil.getWeaponSeq(pi.getSysItem().getWId());
						if(seq==4){
							haveFourth=true;
							break;
						}
					}
					if(!haveFourth){
						SysItem sysItem = getService.getSysItemByItemId(Constants.weaponPack[characterId - 1][3]);
						int playerItemId =getService.getPlayerItemDao().createPlayItem(player.getId(), sysItem, 1, 0, Constants.BOOLEAN_NO, Constants.BOOLEAN_YES, Constants.BOOLEAN_YES);
						ServiceLocator.deleteService.deletePlayerItemInMemcached(player.getId(), sysItem);
						ServiceLocator.updateService.updateWeaponPackEquipment(player.getId(), Constants.DEFAULT_WEAPON_TYPE, playerItemId, characterId);
						mcc.delete(CacheUtil.oWeaponPack(player.getId(), 1,characterId));
						mcc.delete(CacheUtil.oCharacterList(player.getId()));
						sb.append("add FourthWeapon sucessfully playerId="+player.getId()+" characterId="+characterId+" sysitemId="+sysItem.getId()
								+" itemName="+sysItem.getDisplayNameCN()).append("\n");
						log.warn("add FourthWeapon sucessfully playerId="+player.getId()+" characterId="+characterId+" sysitemId="+sysItem.getId()
								+" itemName="+sysItem.getDisplayNameCN());
					}
				}
			}
		}
		return sb.toString();
	}
	//===============================================================================	
	//								  Common Services
	//===============================================================================
	
	public int getMedolNumByPlayerId(int playerId)throws Exception{
		int returnNum=0;
		List<SysItem> sysItemByIID = getSysItemByIID(Constants.DEFAULT_MEDAL_IID,Constants.DEFAULT_ITEM_TYPE);
		if(null != sysItemByIID && !sysItemByIID.isEmpty()){
			SysItem medol = sysItemByIID.get(0);
			Map<Integer,PlayerItem> map=getPlayerItemMapByType1(playerId, Constants.DEFAULT_ITEM_TYPE, 0, 0);
			for(Map.Entry<Integer, PlayerItem> entry: map.entrySet()) {  
				PlayerItem pi=map.get(entry.getKey());
				if(pi!=null && pi.getItemId()==medol.getId()){
					returnNum=returnNum+pi.getQuantity();
				}
			}
		}
		return returnNum;
	}
	
	public int getReliveCoinNumByPlayerId(int playerId)throws Exception{
		int returnNum=0;
		List<SysItem> sysItemByIID = getSysItemByIID(Constants.SPECIAL_ITEM_IIDS.RELIVE_COIN.getValue(),Constants.DEFAULT_ITEM_TYPE);
		if(null != sysItemByIID && !sysItemByIID.isEmpty()){
			SysItem reliveCoin = sysItemByIID.get(0);
			Map<Integer,PlayerItem> map=getPlayerItemMapByType1(playerId, Constants.DEFAULT_ITEM_TYPE, 0, 0);
			for(Map.Entry<Integer, PlayerItem> entry: map.entrySet()) {  
				PlayerItem pi=map.get(entry.getKey());
				if(pi!=null && pi.getItemId()==reliveCoin.getId() && pi.getIsDeleted().equals(Constants.BOOLEAN_NO)){
					returnNum=returnNum+pi.getQuantity();
				}
			}
		}
		return returnNum;
	}
	/**
	 * 获得幸运彩盒“青铜卡“、”白银卡“、”黄金卡“的数量
	 * @param playerId
	 * @param iValue
	 * @return
	 * @throws Exception
	 */
	public int getLuckypackageCardNum(int playerId,int iValue)throws Exception{
		int returnNum=0;
		List<SysItem> itemList = getSysItemByIID(Constants.SPECIAL_ITEM_IIDS.LUCKYPACKAGE_CARD.getValue(),Constants.DEFAULT_MATERIAL_TYPE);
		SysItem tmpSi = null;
		for(SysItem si : itemList){
			if(String.valueOf(iValue).equals(si.getIValue())){
				tmpSi = si;
				break;
			}
		}
		Map<Integer,PlayerItem> map=getPlayerItemMapByType1(playerId, Constants.DEFAULT_MATERIAL_TYPE, 0, 0);
		for(Map.Entry<Integer, PlayerItem> entry: map.entrySet()) {  
			PlayerItem pi=map.get(entry.getKey());
			if(pi!=null && pi.getItemId()==tmpSi.getId()){
				returnNum=returnNum+pi.getQuantity();
			}
		}
		return returnNum;
	}
	
	/**
	 * 根据玩家id，物品类型，iId和iValue获得玩家物品
	 * @param playerId 玩家id
	 * @param type 物品类型
	 * @param iId  物品iId
	 * @param iValue 物品iValue
	 * @return List<PlayerItem>
	 * @throws Exception
	 */
	public List<PlayerItem> getPlayerItemsByIIdAndIValue(int playerId,int type ,int iId ,int iValue) throws Exception{
		List<PlayerItem> piList = getPlayerItemByItemIid(playerId, type, iId);
		List<PlayerItem> retList = new ArrayList<PlayerItem>();
		for(PlayerItem pi : piList){
			SysItem si = pi.getSysItem();
			if(String.valueOf(iValue).equals(si.getIValue())){
				retList.add(pi);
			}
		}
		return retList;
		
	}
	
	public List<SysItem> getSysItemByIID(int iid ,int type)throws Exception{
		List<SysItem> returnList=new ArrayList<SysItem>();
		List<SysItem> list=sysItemDao.getAllSystemItem();
		for(SysItem s:list){
			if(s.getIId()==iid && s.getType()==type){
				returnList.add(s);
			}
		}
		return returnList;
	}
	public List<SysItem> getSysItemByType(int type,int subType)throws Exception{
		List<SysItem> returnList=new ArrayList<SysItem>();
		List<SysItem> list =sysItemDao.getAllSystemItem();
		for(SysItem item : list){
			if(item.getType()==type&&!"Y".equals(item.getIsDeleted())){
				if(item.getSubType()==subType){
					returnList.add(item);
				}
			}
		}
		return returnList;
	}
	public String getLuckyPackageIsPay(int playerId,SysChest sc,int payType ,int level ,int num)throws Exception{
		if(payType==1){
//			PlayerInfo pi = getPlayerInfoById(playerId);
			int leftNum = getLuckypackageCardNum(playerId, level);
			if(leftNum<num){
				String retStr = ExceptionMessage.NOT_ENOUGH_LC;
				if(level==1)
					retStr = retStr.replaceAll("@", CommonMsg.BRONZE_CARD);
				else if(level==2){
					retStr = retStr.replaceAll("@", CommonMsg.SILVER_CARD);	
				}else{
					retStr = retStr.replaceAll("@", CommonMsg.GOLD_CARD);
				}
				return retStr;
			}
		}else{
			int mn = getMedolNumByPlayerId(playerId);
			if(mn<num){
				return ExceptionMessage.NOT_ENOUGH_CHIP;
			}
		}
		return null;
	}
	
	//exclude ibuffid 6,32,51
	public List<PlayerItem> filterBuffListFont(Player player){
		List<PlayerItem> buffList=new ArrayList<PlayerItem>();
		if(player.getBuffList()!=null&&player.getBuffList().size()!=0){
			for(PlayerItem pi:player.getBuffList()){
				SysItem si=sysItemDao.getSystemItemById(pi.getItemId());
				if (Constants.DEFAULT_ITEM_TYPE == si.getType() && si.getIId() == 1&&si.getIBuffId()!=6&&si.getIBuffId()!=32
						&&si.getIBuffId()!=BiochemicalConstants.ordinaryBuffId) {
					buffList.add(pi);
				}
			}
		}
		return buffList;
	}
	//mini
	public List<PlayerItem> filterBuffList(Player player){
		List<PlayerItem> buffList=new ArrayList<PlayerItem>();
		if(player.getBuffList()!=null&&player.getBuffList().size()!=0){
			for(PlayerItem pi:player.getBuffList()){
				SysItem si=sysItemDao.getSystemItemById(pi.getItemId());
				if (Constants.DEFAULT_ITEM_TYPE == si.getType() && si.getIId() == 1 && (si.getIBuffId() == 1 || si.getIBuffId() == 2 || si.getIBuffId() == 3 || si.getIBuffId() == 4 
						|| si.getIBuffId() == 5 || si.getIBuffId() == 7 || si.getIBuffId() == 8 || si.getIBuffId() == 9 || si.getIBuffId() == 13|| si.getIBuffId() == 33|| si.getIBuffId() == 34||(si.getIBuffId()>=61&&si.getIBuffId()<=74))) {
					buffList.add(pi);
				}
			}
		}
		List<PlayerItem> displayList=new ArrayList<PlayerItem>();
		if(buffList.size()>8){
			displayList=buffList.subList(buffList.size()-8, buffList.size());
		}else{
			displayList=buffList;
		}
		return displayList;
	}
	/**
	 * @param playerId 当前用户的ID
	 * @param type 0：本日   1：本周 （可以扩展每月...）		2:本月
	 * @return true 是第一次   false 不是第一次
	 * @throws Exception 
	 */
	
	public boolean isFirstLogin(int playerId, int type) throws Exception{
		Calendar now = Calendar.getInstance();
		if(0 == type){
			Date lastDailySendTime = getDailyMissionDate(playerId);
			if(null != lastDailySendTime){
				Calendar last = Calendar.getInstance();
				last.setTime(lastDailySendTime);
				if(now.get(Calendar.DAY_OF_YEAR) == last.get(Calendar.DAY_OF_YEAR)&&now.get(Calendar.YEAR) == last.get(Calendar.YEAR)){
					return false;
				}
			}
			nosqlService.updateMissionDate(playerId, now.getTime(), 0);
		} else if(1 == type) {
			Date lastWeekSendTime = getWeekMissionDate(playerId);
			if(null != lastWeekSendTime){
				Calendar last = Calendar.getInstance();
				last.setTime(lastWeekSendTime);
				if(now.get(Calendar.YEAR)==last.get(Calendar.YEAR)){
					if(now.get(Calendar.WEEK_OF_YEAR) == last.get(Calendar.WEEK_OF_YEAR)){
						return false;
					}
				}else if(now.get(Calendar.YEAR)==last.get(Calendar.YEAR)+1){//跨年判断
					if(last.get(Calendar.MONTH)==Calendar.DECEMBER&&now.get(Calendar.WEEK_OF_YEAR)==1&&last.get(Calendar.DAY_OF_WEEK)==1){ 
						return false;
					}
				}
//				if(now.get(Calendar.WEEK_OF_YEAR) == last.get(Calendar.WEEK_OF_YEAR)&&now.get(Calendar.YEAR) == last.get(Calendar.YEAR)){
//					return false;
//				}
			}
			nosqlService.updateMissionDate(playerId, now.getTime(), 1);
		}else if(2 == type) {
			Date lastMonthSendTime = getMonthMissionDate(playerId);
			if(null != lastMonthSendTime){
				Calendar last = Calendar.getInstance();
				last.setTime(lastMonthSendTime);
				if(now.get(Calendar.MONTH) == last.get(Calendar.MONTH)&&now.get(Calendar.YEAR) == last.get(Calendar.YEAR)){
					return false;
				}
			}
			nosqlService.updateMissionDate(playerId, now.getTime(), 2);
		}
		return true;
	}
	
	@SuppressWarnings("unchecked")
	public Date getGameDate(final int playerId) throws Exception{
		String key = CacheUtil.oGameDate(playerId);
		Date date = (Date) loadValue(key,new CacheMissHandler() 
			{
				@Override
				public Date loadFromDB(GetService service) throws Exception {
					return nosqlService.getGameDate(playerId);
				}
			});
		return date;
	}
	
	public boolean isFirstGameToday(int playerId) throws Exception{
		Calendar now = Calendar.getInstance();
		Date lastGameTime = getGameDate(playerId);
		if(null != lastGameTime){
			Calendar last = Calendar.getInstance();
			last.setTime(lastGameTime);
			if(now.get(Calendar.DAY_OF_YEAR) == last.get(Calendar.DAY_OF_YEAR)){
				return false;
			}
		}
		String key = CacheUtil.oGameDate(playerId);
		mcc.delete(key);
		nosqlService.updateGameDate(playerId, now.getTime());
		return true;
	}
	//===============================================================================	
	//								  Activity Services
	//===============================================================================
	
	public List<SysActivity> getActivityList() throws Exception{
		return sysActivityDao.getSysActivityList();
	}
	@SuppressWarnings("unchecked")
	public Map<Integer, SysActivity> getAvailableActivitiesMap() throws Exception{
		String key=CacheUtil.oCurrentActivityMap();
		Map<Integer, SysActivity> returnMap=(Map<Integer, SysActivity>) loadValue(key, new CacheMissHandler() {
			@Override
			public Object loadFromDB(GetService service) throws Exception {
				List<SysActivity> activityList = sysActivityDao.getSysActivityList();
				Map<Integer, SysActivity> returnList=new HashMap<Integer, SysActivity>();
				for(SysActivity as:activityList){
					if(as.getTheSwitch().equals(Constants.BOOLEAN_YES)&&Constants.BOOLEAN_NO.equals(as.getIsDeleted())&& (as.getAction() != Constants.ACTION_ACTIVITY.TOP_PLAYER_ACTIVITY.getValue()) &&as.getEndTime().after(new Date())){
						as.initActivity();
						as.initNeedAward();
						as.initTimeStr();
						if(as.getWithAward() == 1){
							SysItem sysItem=getSysItemByItemId(StringUtil.toInt(as.getItems()));
							as.setSysItem(sysItem);
						}
						returnList.put(as.getId(), as);
					}
				}
				return returnList;
			}
		},Constants.CACHE_TIMEOUT_HALF_DAY);
		return returnMap;
	}
	public int getOnlineActivityOffset() throws Exception{
		int returnValue=1;
		Map<Integer, SysActivity> map= getAvailableActivitiesMap();
		for(Map.Entry<Integer, SysActivity> entry:map.entrySet()){
			SysActivity sa=entry.getValue();
			if(sa.getAction()==Constants.ACTION_ACTIVITY.ONLINE_ACTIVITY.getValue()&&sa.getStartTime().before(new Date())
					&&sa.getEndTime().after(new Date())){
				int num=entry.getValue().getValue();
				returnValue=num>1?num:1;
			}
		}
		return returnValue;
	}
	@SuppressWarnings("unchecked")
	public List<SysActivity> getAllActivitiesList() throws Exception{
		//String key=CacheUtil.oCurrentActivityMap();
		List<SysActivity> tempList = sysActivityDao.getSysActivityList();
		List<SysActivity> result = new ArrayList<SysActivity>();
		for(SysActivity sa : tempList){
			if(sa.getIsDeleted().equals(Constants.BOOLEAN_NO)){
				result.add(sa);
				sa.initActivity();
				if(sa.getWithAward() == 1){
					SysItem sysItem=getSysItemByItemId(StringUtil.toInt(sa.getItems()));
					sa.setSysItem(sysItem);
				}
			}
		}
		return result;
	}
	
	public List<SysActivity> getAvailableActivities() throws Exception{
		List<SysActivity> returnList = new ArrayList<SysActivity>(getAvailableActivitiesMap().values());
		if(returnList.size()>0){
			Collections.sort(returnList, new Comparator<SysActivity>(){
				@Override
				public int compare(SysActivity o1, SysActivity o2) {
					return ((Integer)o1.getAction()).compareTo(((Integer)o2.getAction()));
				}
				
			});
		}
		return returnList;
	}
	
	
	/**
	 * <li>|--读取所有系统活动</li>
	 * <li>|--获得玩家可用的活动</li>
	 * <li>|--玩家是否拥有该活动</li>
	 * <li>| |--有，根据时间清零</li>
	 * <li>| |--没有，新增</li>
	 * @param playerId
	 * @throws Exception
	 */
	public void checkAvailablePlayerActivities(int playerId) throws Exception{
		List<PlayerActivity> newlist=new ArrayList<PlayerActivity>();
		//可用的系统Activity
		List<SysActivity> availableList=getAvailableActivities();
		List<PlayerActivity> playerList = playerActivityDao.getPlayerActivityList(playerId);
		
		//Key:PlayerActivity代号  Value:PlayerActivity
		Map<Integer, PlayerActivity> allPlayerActivityMap = new HashMap<Integer, PlayerActivity>();
		for(PlayerActivity pa:playerList){
			if(null != pa){
				allPlayerActivityMap.put(pa.getSysActivityId(), pa);
			}
		}
		
		for(SysActivity sa:availableList){
			boolean isNew=false;
			/*for(PlayerActivity pa:playerList){
				if(pa.getSysActivityId()==sa.getId()){
					isNew=false;
					//重新初始化每日登录和时段登录任务
					if(sa.getAction() == Constants.ACTION_ACTIVITY.LOGIN_ACTIVITY.getValue() || sa.getAction() == Constants.ACTION_ACTIVITY.HOUR2HOUR_ACTIVITY.getValue()){
						pa.setStatus(0);
						pa.setAward(0);
						ServiceLocator.updateService.updatePlayerActivity(pa);
					}
				}
			}*/
			PlayerActivity tempPa = allPlayerActivityMap.get(sa.getId());
			if(null == tempPa){
				isNew = true;
			} else {
				//Status 状态0未完成 1完成
				//Award 奖品是否发送 0未完成 1完成
				//Number 数目 比如按杀敌数目计算 数字多少表示杀敌多少
				//重新初始化每日登录和时段登录任务
				if(sa.getAction() == Constants.ACTION_ACTIVITY.HOUR2HOUR_ACTIVITY.getValue()
						||sa.getAction() == Constants.ACTION_ACTIVITY.LEVEL_FIRST_LOGIN.getValue()){
					tempPa.setStatus(0);
					tempPa.setAward(0);
					ServiceLocator.updateService.updatePlayerActivity(tempPa);
				}else if(sa.getAction() == Constants.ACTION_ACTIVITY.LOGIN_ACTIVITY.getValue() 
						|| sa.getAction() == Constants.ACTION_ACTIVITY.KILL_ACTIVITY.getValue()){//杀敌
					if(sa.getBackup().equals("Y")){ //开启每天清空
						tempPa.setStatus(0);
						tempPa.setAward(0);
						tempPa.setNumber(0);
						ServiceLocator.updateService.updatePlayerActivity(tempPa);
					}
				}else if( sa.getAction() == Constants.ACTION_ACTIVITY.CHARGE_FC.getValue()){
					if(sa.getBackup().equals("Y")){ //开启每天清空
						tempPa.setStatus(0);
						tempPa.setAward(0);
						tempPa.setNumber(0);
						mcc.delete( CacheUtil.oPlayerActivityList(playerId));
						ServiceLocator.updateService.updatePlayerActivity(tempPa);
					}
				}else if(sa.getAction()==Constants.ACTION_ACTIVITY.TEAM_COMBAT_FINISH.getValue()){
					tempPa.setStatus(0);
					tempPa.setAward(0);
					tempPa.setNumber(0);
					mcc.delete( CacheUtil.oPlayerActivityList(playerId));
					ServiceLocator.updateService.updatePlayerActivity(tempPa);
				}
			}
			if(isNew){
//				if(sa.getAction()==Constants.ACTION_ACTIVITY.ACHIEVEMENT_ACTIVITY.getValue()){
//					int achievementId=sa.getValue();
//					Map<Integer, SysAchievement> map = sysAchievementDao.getAllSysAchievementMap();
//					SysAchievement sysAchievement=map.get(achievementId);
//					sa.setName(sysAchievement.getDescription());
//				}
				PlayerActivity playerActivity=new PlayerActivity(sa,playerId);
				newlist.add(playerActivity);
			}
			sa.initNeedAward();
		}
		if(newlist.size()>0){
			playerActivityDao.createPlayerActivity(newlist);
		}
		mcc.delete(CacheUtil.oPlayerActivityList(playerId));
	}
	//===============================================================================	
	//							Player	  Activity Services
	//===============================================================================
	public List<PlayerActivity> getPlayerActivityList(final int playerId, int action) throws Exception{
		List<PlayerActivity> list=getPlayerActivityOneDayList(playerId);
		List<PlayerActivity> returnList=new ArrayList<PlayerActivity>();
		Map<Integer, SysActivity> map=getAvailableActivitiesMap();
		Date now = new Date();
		for(PlayerActivity pa:list){
			SysActivity sa = map.get(pa.getSysActivityId());
			if(sa!=null&&Constants.BOOLEAN_NO.equals(sa.getIsDeleted())&&sa.getTheSwitch().equals(Constants.BOOLEAN_YES)
					&&(action==0||sa.getAction()==action)&&sa.getEndTime().after(now)&& sa.getStartTime().before(now)){
					pa.setSysActivity(sa);
					returnList.add(pa);
			}
		}
		return returnList;
	}
	@SuppressWarnings("unchecked")
	public List<PlayerActivity> getPlayerActivityOneDayList(final int playerId) throws Exception{
		String key = CacheUtil.oPlayerActivityList(playerId);
		List<PlayerActivity> playerActivityList = (List<PlayerActivity>) loadValue(key, new CacheMissHandler() {
			@Override
			public Object loadFromDB(GetService service) throws Exception {
				List<PlayerActivity> returnList=new ArrayList<PlayerActivity>();
				List<PlayerActivity> list = playerActivityDao.getPlayerActivityList(playerId);
				if(list.size()!=0){
					Map<Integer, SysActivity> map=getAvailableActivitiesMap();
					Date now = new Date();
					for(PlayerActivity pa:list){
						SysActivity sa = map.get(pa.getSysActivityId());
						if(sa!=null&&Constants.BOOLEAN_NO.equals(sa.getIsDeleted())&&sa.getTheSwitch().equals(Constants.BOOLEAN_YES)){
							if(sa.getEndTime().after(now)&&(sa.getStartTime().before(now)||sa.getStartTime().getDate()==now.getDate())){//已经开始并且还没结束或者今天开始的活动
//								sa.initNeedAward();
//								SysItem sysItem=getSysItemByItemId(StringUtil.toInt(sa.getItems()));
//								pa.setSysItem(sysItem);
								pa.setSysActivity(sa);
								returnList.add(pa);
							}
						}
					}
				}
				return returnList;
			}
		});
		for(PlayerActivity pa : playerActivityList){
			if(pa.getSysActivity().getAction()==5){
//				Object[] o2={CommonUtil.dateFormat.format(pa.getSysActivity().getStartTime()),CommonUtil.dateFormat.format(pa.getSysActivity().getEndTime())};
//				pa.setName("<AD"+pa.getSysActivity().getName()+",2,\""
//						+CommonUtil.dateFormat.format(pa.getSysActivity().getStartTime())+"\",\""
//						+CommonUtil.dateFormat.format(pa.getSysActivity().getEndTime())+"\">");
			}else{
				pa.setName(pa.getSysActivity().getName());
			}
		}
		return playerActivityList;
	}
	//===============================================================================	
	//								  User Services
	//===============================================================================
	public User getUser(String userName) {
		List<User> result = userDao.getUser(userName);
		if (result.size() > 0) {
			return  result.get(0);
		} else {
			return null;
		}
	}
	
	public User getUserById(int id){
		return userDao.getUserById(id);
	}
	public SysModeConfig getSysModeConfig(Integer type) {
		SysModeConfig modeConfig=new SysModeConfig();
		modeConfig=sysModeConfigDao.getSysModeConfig(type);
		return modeConfig;
	}
	public String getConfig()throws Exception{
		SysModeConfig config=getSysModeConfig(3);
		return config.getConfig();
	}
	
	
	public int getCR(String userId) throws Exception {
//		final User user = getUserById(userId);
//		String ip = "";
//		/*for (Player p : getPlayerByUserId(user.getId())) {
//			if( mcc.get(CacheUtil.oPlayerLocation(p.getId())) != null){
//				ip = p.getLastLoginIp();
//				break;
//			}
//		}*/
//		if (StringUtil.isEmptyString(ip)) {
//			ip = "127.0.0.1";
//		}
//		
//		final  String finalIp = ip;
//		
//		int cr= 0;
		try{
//			cr = (Integer) loadValue(CacheUtil.sUserCR(userId), new CacheMissHandler(){
//				@Override
//				public Object loadFromDB(GetService service) throws Exception {
//					String sdoUid =  ConfigurationUtil.SWITCH_SDO_BUY_TEST_ACCOUNT.getIsOn()?"1055423031":user.getUserName();
//						int balance = ServiceLocator.getSDOComponent().getBalance(
//								new PlayerInfo(sdoUid, user.getId(), 0,
//										finalIp, SDOConstants.AREAID,
//										SDOConstants.GROUPID),
//								SDOItemOrder.PayType.TICKET);
//						return balance;
//				}
//			},Constants.CACHE_SDO_CR_TIMEOUT);
			return 10;
		}catch(Exception e){
			log.error("Error happend during get CR, uid :" + userId + " exception is " + e);
			//return a number less than 0, indicate error happened during get CR
			return -1;
		}
	}
	
	//===============================================================================	
	//								  sysCharater Services
	//===============================================================================
	
	public interface CacheMissHandler{
		Object loadFromDB(GetService service) throws Exception;
	}
	
	/**
	 * first, try to load value from cache, if it can not be found in cache, use handler to load it ,and put it into cache
	 * @param key
	 * @param handler
	 * @return
	 * @throws Exception
	 */
	public Object loadValue(String key, CacheMissHandler handler) throws Exception{
		return loadValue(key,handler,Constants.CACHE_TIMEOUT_DAY);
	}
	public Object loadValue(String key, CacheMissHandler handler, int expr) throws Exception{
		Object result = null;
		try{
			 result = mcc.get(key,Constants.CACHE_TIMEOUT);
		}catch(Exception e){	
			log.warn("error happend during get key from memcache " + e);
		}
		if (result == null){ 
			result = handler.loadFromDB(this);
			if (result == null) {
				return null;
			}
			try{
				mcc.set(key, expr, result,Constants.CACHE_TIMEOUT);
			}catch(Exception e){
				log.warn("Fail to put object into cache with key: " + key );
				e.printStackTrace();
			}
		}
		return result;
	}
	
	
	/**
	 * method to get character for create player
	 * @param isDefault
	 * @return List<Character>
	 * @throws Exception
	 */
	public List<SysCharacter> getSysCharacterListAll()throws Exception{
		List<SysCharacter> sysCharaterList = new ArrayList<SysCharacter>();
		sysCharaterList = mcc.get(CacheUtil.oSysCharacterList(),Constants.CACHE_TIMEOUT);
		if (sysCharaterList==null) {
			sysCharaterList=sysCharacterDao.getSysCharacterList();
			mcc.set(CacheUtil.oSysCharacterList(), Constants.CACHE_ITEM_TIMEOUT, sysCharaterList,Constants.CACHE_TIMEOUT);
			
		}
		return sysCharaterList;
	}
	/**
	 * method to get character for create player
	 * @param isDefault
	 * @return List<Character>
	 * @throws Exception
	 */
	public List<SysCharacter> getDefaultSysCharacterList()throws Exception{
		List<SysCharacter> sysCharaterList = getSysCharacterListAll();
		List<SysCharacter> filter = new ArrayList<SysCharacter>(
				Collections2.filter(sysCharaterList, new Predicate<SysCharacter>() {

					@Override
					public boolean apply(SysCharacter character) {
						if (Constants.BOOLEAN_YES.equals(character
								.getIsDefault())) {
							return true;
						}
						return false;
					}

				})
		);
		return filter;
	}
	
	public boolean isPlayerOnline(Player p){
		try {
			return mcc.get(CacheUtil.oPlayerLocation(p.getId()),Constants.CACHE_TIMEOUT)!=null;
		} catch (Exception e) {
			log.warn("Error happend during get playerLocation with cid :" + p.getId());
			return false;
		} 
	}
	
	
	/**
	 * method to get one syscharacter
	 * @param id
	 * @param isDefault
	 * @return Character
	 * @throws Exception
	 */
	public SysCharacter getSysCharacter(Integer id)throws Exception{
		SysCharacter returnValue=sysCharacterDao.getSysCharacterById(id);
		return returnValue;
	}
	public int getLogVersionFromCharacterLog(Integer characterId)throws Exception{
		return sysCharacterLogDao.getSysCharacterLogVersion(characterId);
	}
	public List<SysCharacter> getCharacterLog(Integer characterId)throws Exception{
		return	 sysCharacterLogDao.getSysCharacterLogList(characterId);
	}
	
	//===============================================================================	
	//								  Team Services
	//===============================================================================
	@SuppressWarnings("unchecked")
	public List<Team> getTeamList() throws Exception{
		String key = CacheUtil.oTeamList();
		List<Team> teamList = (List<Team>) loadValue(key, new CacheMissHandler() {
			@Override
			public Object loadFromDB(GetService service) throws Exception {
				List<Team> teamList = teamDao.getTeamList();
				for (Team t : teamList) {
					setTeamMember(t);
				}
				return teamList;
			}
		});
		return teamList;
	}
	public Team getTeamByPlayerId(final int playerId) throws Exception{
		PlayerTeam playerTeam = playerTeamDao.getByPlayerId(playerId);
		if(playerTeam == null){
			return null;
		} else {
			return getTeamById(playerTeam.getTeamId());
		}
	}
	public void setTeamMember(Team team)throws Exception{
		List<PlayerTeam> players = playerTeamDao.getPlayerTeam(team.getId());
		for(PlayerTeam pt:players){
			joinPlayerAndPlayerTeamWithoutCard(pt);
		}
		team.setNumber(players.size());
		team.setMemberList(players);
	}
	
	/**
	 * 获得战队玩家
	 * @param teamId
	 * @param playerId
	 * @return
	 */
	public PlayerTeam getPlayerTeam(int teamId,int playerId){
		List<PlayerTeam> players = playerTeamDao.getPlayerTeam(teamId);
		for(PlayerTeam pt : players){
			if(pt.getPlayerId()==playerId){
				return pt;
			}
		}
		return null;
	}
	/*	public Team getTeamByPlayerId(Integer playerId)throws Exception{
		Player p = getPlayerById(playerId);
		return getTeam(p.getTeamId());
	}	*/
	
	public Team getTeam(final Integer teamId)throws Exception{
//		Team result = getTeamById(teamId);
		return teamDao.getTeamById(teamId);
	}
	/**
	 * 简单获取team信息，不填充、关联任何信息
	 * @param teamId
	 * @return
	 * @throws Exception
	 */
	public Team getSimpleTeamById(final Integer teamId)throws Exception{
		String key = CacheUtil.oSimpleTeam(teamId);
		Team result = (Team) loadValue(key, new CacheMissHandler() {
			@Override
			public Object loadFromDB(GetService service) throws Exception {
				return teamDao.getTeamById(teamId);
			}
		});
		
		return result;
	}
	
	public int getCanRoboRes(final Team team) throws Exception{
		if(team.getTeamSpaceActive()==Constants.TeamSpaceConstants.TEAM_SAPCE_DISACTIVE){
			return Constants.TeamSpaceConstants.DEAD_FISH_RES_FOR_ROB;
		}	
		HashMap<String, Integer> teamRes=team.getLatestTeamRes();
		return teamRes.get(Team.ORG_RES)/Constants.TeamSpaceConstants.robDivide;
	}
	
	public Team getTeamById(final Integer teamId)throws Exception {
		String key = CacheUtil.oTeam(teamId);
		Team result = (Team) loadValue(key, new CacheMissHandler() {
			@Override
			public Object loadFromDB(GetService service) throws Exception {
				Team team=teamDao.getTeamById(teamId);
				if (team != null){
					int headerid = teamDao.getTeamHeader(teamId);
					team.setHeaderId(headerid);
					setTeamMember(team);
					team.setFight(getTeamFight(team));
					if(team.getNumber() == 0){
//						log.warn("team:"+team.getName()+" getNumber()==0");
						team.setNumber(team.getMemberList().size());
						teamDao.updateTeam(team);
					}
				}
				return team;
			}
		});
		if(null!=result&&(result.getNumber() == 0||result.getFight()==0)){
			log.debug("team:"+result.getName()+" null!=result&&getNumber()==0");
			if(result.getNumber()==0){
				result.setNumber(result.getMemberList().size());
			}
			if(result.getFight()==0){
			result.setFight(getTeamFight(result));
			}
			teamDao.updateTeam(result);
			mcc.delete(key);
		}
		return result;
	}
	
	public List<Ally> getAlly(Integer teamId){
		return allyDao.getAllyList(teamId);
	}
	
	public List<Team> getAllyTeam(Integer teamId) throws Exception{
		List<Ally> allys = getAlly(teamId);
		List<Team> result = new ArrayList<Team>();
		for(Ally ally:allys){
			result.add(getTeam(ally.getAllyId()));
		}
		return result;
	}
	public int getNewTeamRequest(int userId,int playerId)throws Exception{
		Player p=getPlayerById(playerId);
		if(p!=null){
			if(p.getJob()!=TeamConstants.TEAMJOB.TEAM_CAPTAIN.getValue()){
				return 0;
			}else {
				return getUnapprovedMember(p.getTeamId()).size();
			}
		}
		return 0;
	}
	
	public int fuzzyCountTeam(String name)throws Exception{
		return teamDao.fuzzyCountTeam(name);
	}
	
	
	
	

	public List<Team> getTeamsByName(String name)throws Exception{
		return (List<Team>)teamDao.getTeamByName(name);
	}
	public List<Team> getTeamsByNameProvinceCity(String name, String province, String city)throws Exception{
		List<Team> teamList = (List<Team>)teamDao.getTeamByNameProvinceCity(name, province, city);
		return teamList;
	}
	public List<Team> getSortedTeamsByProvinceCityType(String province, String city,String name,int type ,Integer start ,Integer end)throws Exception{
		String sql = "";
		switch (type) {
		case 1:
			sql = "Team.selectTeamTopByGwin";
			break;
		case 2:
			sql = "Team.selectTeamTopByLevel";
			break;
		case 3:
			sql = "Team.selectTeamTopByNumber";
			break;
		case 4:
			sql = "Team.selectTeamTopByCreateTime";
			break;
		case 5:
			sql="Team.selectTeamTopByResource";//TODO 资源争夺
			break;
		case 6:sql = "Team.selectPreDayTeamTop";
			break;
		default:
			break;
		}
		List<Team> teamList = (List<Team>)teamDao.getTeamByProvinceCity(province, city,name,sql,start,end);
		return teamList;
	}
	public int getTeamTotalNumByNameProvinceCity(String name, String province, String city) throws Exception{
		return teamDao.getTeamTotalNumByNameProvinceCity(name, province, city);
	}
	public Team getTeamBySpecificName(String name)throws Exception{
		return  teamDao.getTeamBySpecificName(name);
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Team> getDefaultRecommendTeam()throws Exception{
		final String cacheKey = CacheUtil.oDefaultTeamList();
		List<Team> allDefaultTeamList = (List<Team>) loadValue(cacheKey, new CacheMissHandler(){
			@Override
			public Object loadFromDB(GetService service) throws Exception {
				List<Team> list = teamDao.getDefaultRecommendTeamList();
				if (list != null && list.size() != 0) {
					for (Team team : list) {
						setTeamMember(team);
					}
				}
				return list;
			}
			
		});
		return allDefaultTeamList;
	}
	
	public int getCountRecommendTeam(){
		return teamDao.countRecommendTeam();
	}
	
	
	public List<TeamHistory> getTeamHistoryById(Integer id)throws Exception{
		return teamHistoryDao.getTeamHistoryById(id);
	}
	
	public List<PlayerTeam> getUnapprovedMember(int teamId) throws Exception{
		List<PlayerTeam> players = playerTeamDao.getUnapprovedMember(teamId);
		for(PlayerTeam pt:players){
			joinPlayerAndPlayerTeamWithoutCard(pt);
		}
		return players;
	}
	
	public List<PlayerTeam> getUnapprovedMemberOverMaxNumber(int teamId) throws Exception{
		List<PlayerTeam> players = playerTeamDao.getUnapprovedMember(teamId);
		if(players.size() > 99){
			players = players.subList(0, 99);
		}
		for(PlayerTeam pt:players){
			joinPlayerAndPlayerTeamWithoutCard(pt);
		}
		return players;
	}
	
	public List<PlayerTeam> getUnapprovedMemberSimple(int teamId) throws Exception{
		List<PlayerTeam> players = playerTeamDao.getUnapprovedMember(teamId);
		return players;
	}
	
	public PlayerTeam getPlayerTeamByPlayerId(int playerId) throws Exception{
		PlayerTeam pt = playerTeamDao.getByPlayerId(playerId);
		if(pt != null){
			joinPlayerAndPlayerTeam(pt);
		}
		return pt;
	}
	public PlayerTeam getPlayerTeamByPlayerIdSimple(int playerId) throws Exception{
		PlayerTeam pt = playerTeamDao.getByPlayerId(playerId);
		return pt;
	}
	
	public List<PlayerTeam> getPlayerTeamByTeamId(final int teamId) throws Exception{
		//String	objKey=CacheUtil.oPlayerTeam(teamId);
		//List<PlayerTeam> list = (List<PlayerTeam>) loadValue(objKey, new CacheMissHandler() {
			//@Override
			//public Object loadFromDB(GetService service) throws Exception {
				List<PlayerTeam> players = playerTeamDao.getPlayerTeam(teamId);
				List<PlayerTeam> onlines=new ArrayList<PlayerTeam>();
				List<PlayerTeam> others=new ArrayList<PlayerTeam>();
				for(PlayerTeam pt:players){
					joinPlayerAndPlayerTeam(pt);
					if(pt.getOnline()==1){
						onlines.add(pt);
					}else{
						others.add(pt);
					}
				}
				Collections.sort(onlines,new Comparator<PlayerTeam>(){
					@Override
					public int compare(PlayerTeam o1, PlayerTeam o2) {
						return o2.getIsVip().compareTo(o1.getIsVip());
					}
				});
				onlines.addAll(others);
				return onlines;
			//}
		//},5*1000);
		//return list;
	}
	public List<PlayerTeam> getPlayerTeamByTeamIdSimple(int teamId) throws Exception{
		List<PlayerTeam> players = playerTeamDao.getPlayerTeam(teamId);
		return players;
	}
	public void joinPlayerAndPlayerTeam(PlayerTeam pt) throws Exception{
		Player player= getPlayerById(pt.getPlayerId());
		pt.setNickName(player.getName());
		pt.setRank(player.getRank());
		pt.setExp(player.getExp());
		pt.setIsVip(player.getIsVip());
		int card = 0;
		for(PlayerItem pi:player.getBuffList()){
			SysItem si=sysItemDao.getSystemItemById(pi.getItemId());
			if(Constants.DEFAULT_ITEM_TYPE == si.getType() && si.getIId() == 1 && si.getIBuffId() == 32){
				card=StringUtil.toInt(si.getIValue());
				break;
			}
		}
		PlayerLocation location=mcc.get(CacheUtil.oPlayerLocation(pt.getPlayerId()),Constants.CACHE_TIMEOUT);
		if (location != null) {
			pt.setOnline(1);
		}
		pt.setCard(card);
	}
	public void joinPlayerAndPlayerTeamWithoutCard(PlayerTeam pt) throws Exception{
		Player player= getSimplePlayerById(pt.getPlayerId());
		pt.setNickName(player.getName());
		pt.setRank(player.getRank());
		pt.setExp(player.getExp());
		pt.setIsVip(player.getIsVip());
		pt.setCard(0);
		pt.setFight(player.getMaxFightNum());
		pt.setPlayer(player);
	}
	//===============================================================================	
	//								  Character Services
	//===============================================================================
	//no update action
	public Player getSimplePlayerById(final Integer playerId) throws Exception  {
		return playerDao.getPlayerById(playerId);
	}
	public PlayerInfo getPlayerInfoById(final Integer playerId) throws Exception  {
		String objKey=CacheUtil.oPlayerInfo(playerId);
		PlayerInfo playerInfo = (PlayerInfo) loadValue(objKey, new CacheMissHandler() {
			@Override
			public Object loadFromDB(GetService service) throws Exception {
				PlayerInfo playerInfo=playerInfoDao.getPlayerInfoByPid(playerId);
				if (playerInfo == null) {
					return null;
				}
				return playerInfo;
			}
		});
		if(playerInfo==null){
			log.error("Not find PLAYERINFO playerId="+playerId);
			throw new IllegalArgumentException("Not find PLAYERINFO playerId="+playerId);
		}
		return playerInfo;
	}
	
//	public PlayerInfo getPlayerInfoByIdNotCreate(final Integer playerId) throws Exception  {
//		PlayerInfo playerInfo=playerInfoDao.getPlayerInfoByPid(playerId);
//		if(playerInfo==null){
//			log.error("Not find PLAYERINFO playerId="+playerId);
//		}
//		return playerInfo;
//	}
	
	public Player getPlayerById(final Integer playerId) throws Exception  {
		String objKey = CacheUtil.oPlayer(playerId);
		Player result = (Player) loadValue(objKey, new CacheMissHandler() {
			@Override
			public Object loadFromDB(GetService service) throws Exception {
				Player player=playerDao.getPlayerById(playerId);
				if (player == null) {
					return null;
				}
				return player;
			}
		});
		if(result != null){
			List<PlayerItem> buffList=getPlayerBuffListById(playerId);
			result.setBuffList(buffList);
//			boolean isBuff=false;//grid available 
//			for(PlayerItem pi:buffList){
//				SysItem si=sysItemDao.getSystemItemById(pi.getItemId());
//				if(si.getType()==Constants.DEFAULT_ITEM_TYPE&&si.getIId()==1&&si.getIBuffId()==Constants.DEFAULT_RELEASE_SIZE_ID){
//					isBuff=true;
//				}
//			}
			//TODO CharacterSize
//			if(isBuff){
//				result.setCharacterSize(5);
//			}else{
//				result.setSelectedCharacters();
//				result.setCharacterSize(3);
//			}
		}
		return result;
	}
	public Player getPlayerByIdWithTeam(final Integer playerId) throws Exception  {
		Player result=getPlayerById(playerId);
		PlayerTeam pt=playerTeamDao.getByPlayerId(playerId);
		if(pt!=null&&Constants.BOOLEAN_YES.equals(pt.getApproved())){
			Team team=teamDao.getTeamById(pt.getTeamId());
			result.setTeam(team.getName());
			result.setTeamId(team.getId());
		}else{
			result.setTeam("");
		}
		return result;
	}
	public PlayerItem getPlayerBuff(Player player,int buffId) throws Exception{
		List<PlayerItem> buffs=player.getBuffList();
		for(PlayerItem pi:buffs){
			if(pi.getSysItem().getIBuffId() == buffId){
				return pi;
			}
		}
		return null;
	}
	public PlayerItem getPlayerBuff(int playerId,int buffId) throws Exception{
		List<PlayerBuff> buffList=playerBuffDao.getPlayerBuffListByPlayerId(playerId);
		Map<Integer, PlayerItem>  playerItemMap=getPlayerItemMapByType1(playerId, Constants.DEFAULT_ITEM_TYPE, 0, 0);
		for(PlayerBuff pb:buffList){
			Integer playerItemId=pb.getPlayerItemId();
			if(playerItemId!=null&&playerItemId!=0){
				PlayerItem pi=playerItemMap.get(playerItemId);
				if(null == pi){
					pb.setPlayerItemId(null);
					playerBuffDao.updateMappingBeanInCache(pb, pb.getPlayerId());
				}else if(null != pi && sysItemDao.getSystemItemById(pi.getItemId()).getIBuffId() == buffId){
					if(pi.getPlayerItemUnitType()==2){
						if(pi.getExpireSecondsLeft()>0){
							pi.calculateTimeLeft();
							if(pi.getExpireSecondsLeft()>0){
								pi.setSysItem(sysItemDao.getSystemItemById(pi.getItemId()));
								return pi;
							}
						} else{
							pb.setPlayerItemId(null);
							playerBuffDao.updateMappingBeanInCache(pb, pb.getPlayerId());
						} 
					}else if(pi.getPlayerItemUnitType()==0){
						pi.setSysItem(sysItemDao.getSystemItemById(pi.getItemId()));
						return pi;
					}
					return null;
				}
			}
		}
		return null;
	}
	
	public List<PlayerItem> getPlayerBuffListById(Integer playerId) throws Exception{
		List<PlayerBuff> buffList=playerBuffDao.getPlayerBuffListByPlayerId(playerId);
//		Collections.sort(buffList, new Comparator<PlayerBuff>(){
//
//			@Override
//			public int compare(PlayerBuff o1, PlayerBuff o2) {
//				return ((Integer)o1.getBuffId()).compareTo((Integer)o2.getBuffId());
//			}  
//		});
		List<PlayerItem> returnlist=new ArrayList<PlayerItem>();
		Map<Integer, PlayerItem>  playerItemMap=getPlayerItemMapByType1(playerId, Constants.DEFAULT_ITEM_TYPE, 0, 0);
		for(PlayerBuff pb:buffList){
			Integer playerItemId=pb.getPlayerItemId();
			if(playerItemId!=null&&playerItemId!=0){
				PlayerItem pi=playerItemMap.get(playerItemId);
				if(null == pi){
					pb.setPlayerItemId(null);
					playerBuffDao.updateMappingBeanInCache(pb, pb.getPlayerId());
				}else{
					if(pi.getPlayerItemUnitType()==2){
						if(pi.getExpireSecondsLeft()>0){
							pi.calculateTimeLeft();
							if(pi.getExpireSecondsLeft()>0){
								pi.setSysItem(sysItemDao.getSystemItemById(pi.getItemId()));
								returnlist.add(pi);
							}
						} else{
							pb.setPlayerItemId(null);
							playerBuffDao.updateMappingBeanInCache(pb, pb.getPlayerId());
						}
					}else if(pi.getPlayerItemUnitType()==0){
						pi.setSysItem(sysItemDao.getSystemItemById(pi.getItemId()));
						returnlist.add(pi);
					}
				}
			}
		}
		Collections.sort(returnlist, new Comparator<PlayerItem>(){
		
					@Override
					public int compare(PlayerItem o1, PlayerItem o2) {
						return ((Integer)o1.getId()).compareTo((Integer)o2.getId());
					}  
				});
//		log.fatal("playerBuff ="+returnlist.size()+" playerId="+playerId);
		return returnlist;
	}
	public Player getPlayerWithAvatarAndWeapon(String name){
		try {
			Player p = getPlayerByName(name);
			
			if (p == null) {
				return null;
			}
			
//			List<PlayerItem> cosT = getService.getCostumePackList(p.getId(), 1, 0);
//			List<PlayerItem> cosP = getService.getCostumePackList(p.getId(), 1, 1);
//			List<PlayerItem> pack = getService.getWeaponPackList(p.getId(), 1);
			
//			p.putOnCostume(0, cosT);
//			p.putOnCostume(1, cosP);
			
			//get first weapon in pack
//			PlayerItem weapon = null;
//			for(PlayerItem pi : pack){
//				if(pi!=null && pi.getId()!=0){
//					weapon = pi;
//					break;
//				}
//			}
//			if (weapon == null) {
//				throw new Exception("null weapon found with cid : " + p.getId());
//			}
//			p.setPi(weapon);
			return p;
		} catch (Exception e) {
			log.error("Error happend during getPlayerWithAvatarAndWeapon, e:" + e);
			return null;
		}
	}
	
	
	
//	public Player getPlayerFullRankInfoById(final int playerId) throws Exception{
//		Player player =  playerDao.getPlayerRankById(playerId);
//		if (player == null) {
//			return null;
//		}
//		
//		List<PlayerItem> buffList=playerBuffDao.getBuffList(playerId);
//		player.setBuffList(buffList);
//		return player;
//	}
	
//	public Player getPlayerFullRankInfoByName(String playerName) throws Exception{
//		Player player = getPlayerByName(playerName);
//		if(player==null){
//			throw new BaseException(ExceptionMessage.NOT_FIND_PLAYER);
//		}
//		player = getPlayerFullRankInfoById(player.getId());
//		if (player == null){
//			return null;
//		}
//		List<PlayerItem> buffList=playerBuffDao.getBuffList(player.getId());
//		player.setBuffList(buffList);
//		
//		return player;
//	}
	

	
	public Integer getPlayerIdByUserId(String userId)throws Exception{
		Integer playerId = playerDao.getPlayerIdByUserId(userId);
		return playerId; 
	}
	public Player getPlayerByName(final String name)throws Exception{
		Player player = playerDao.getPlayerByName(name);
		return player; 
	}
	public Player getPlayerByName(String userName,final String name)throws Exception{
		Player player = playerDao.getPlayerByName(userName,name);
		return player; 
	}
	
	public List<Player> getPlayerAll()throws Exception{
		return playerDao.getAllPlayer(); 
	}
	
//	public Character getCharacterById(Integer playerId)throws Exception {
//		String objKey=CacheUtil.oCharacterList(playerId);
//		Character character=new Character();
//		List<Character> characterList=new ArrayList<Character>();
//		characterList=mcc.get(objKey,Constants.CACHE_TIMEOUT);
//		if(characterList==null){
//			characterList=new ArrayList<Character>();
//			List<Integer> characterIds=new ArrayList<Integer>();
//			characterIds=playerPackDao.getCharacterIdFromPlayerPack(playerId);
//			if(characterIds.size()!=0){
//				int id=characterIds.get(0);
//				List<PlayerItem> costumeList=getCostumePackList(playerId, Constants.NUM_ONE, id);
//				List<PlayerItem> weaponList=getWeaponPackList(playerId, Constants.NUM_ONE, id);
//				SysCharacter sysCharacter=sysCharacterDao.getSysCharacterById(id);
//				character.setPlayerId(playerId);
//				character.setSysCharacter(sysCharacter);
//				character.setWeaponList(weaponList);
//				character.setCostumeList(costumeList);
//				character.putOnCostume();
//			}
//		}else{
//			character=characterList.get(0);
//		}
//		return character;
//	}
	public List<Integer> getCharacterIdFromPlayerPack(int playerId){
		return playerPackDao.getCharacterIdFromPlayerPack(playerId);
	}
	public SysCharacter getSysCharacterById(int id){
		return sysCharacterDao.getSysCharacterById(id);
	}
	public Character getCharacterByCharacterId(Integer playerId,Integer characterId)throws Exception {
			NosqlService nosqlService = ServiceLocator.nosqlService;
			NoSql nosql = nosqlService.getNosql();
			Character character=null;
			List<Character> characterList=getCharacterListById(playerId);
			List<Integer> fightNumList = new ArrayList<Integer>();
			boolean isNeedUpdate = false;
			for(Character ch:characterList){
				int fightNum = ch.getFightNum();
				int lastFightNum = ch.initFightNum();
				if(fightNum!=lastFightNum){
					isNeedUpdate = true;
				}
				fightNumList.add(fightNum);
				if(ch.getSysCharacter().getId()==characterId){
					character=ch;
				}
			}
			
			if(character==null){
				throw new BaseException(ExceptionMessage.NO_HAVE_THE_CHARACTER);
			}
			Collections.sort(fightNumList, new Comparator<Integer>(){
				@Override
				public int compare(Integer i1, Integer i2) {
					return i2.compareTo(i1);
				}
			});
			double maxCFightNum = 0;
			if(isNeedUpdate){
			//update charcter in cache
				String objKey=CacheUtil.oCharacterList(playerId);
				mcc.set(objKey, Constants.CACHE_ITEM_TIMEOUT, characterList,Constants.CACHE_TIMEOUT);
			}
			for(int i=0;i<fightNumList.size();i++){
				maxCFightNum += fightNumList.get(i)*Math.pow(Constants.FUNDUS_NUM, i);
			}
			Player player=getService.getPlayerById(playerId);
			String totalFightNumKey = NosqlKeyUtil.fightNumInRealTopByType("0");
			if((int)maxCFightNum!=player.getMaxFightNum()){
					soClient.puchCMDtoClient(player.getName(), CommonUtil.messageFormat(CommonMsg.REFRESH_FIGHT_NUM,""+maxCFightNum));
					player.setMaxFightNum((int)maxCFightNum);
					ServiceLocator.updateService.updatePlayerInfo(player);
				}
			int totalFightNumScore =  (int)Math.abs(nosql.zScore(totalFightNumKey, String.valueOf(playerId)));
			if((int)maxCFightNum!=totalFightNumScore){
				nosql.zAdd(totalFightNumKey, -maxCFightNum, String.valueOf(playerId));//为了保证按照战斗力由大到小排列，将真实战斗力符号取反
			}
			String fightnumKey = NosqlKeyUtil.fightNumInRealTopByType(String.valueOf(characterId));
			int fightNumScore = (int)Math.abs(nosql.zScore(fightnumKey, String.valueOf(playerId)));
			if(character.getFightNum()!=fightNumScore){
				nosql.zAdd(fightnumKey, -character.getFightNum(), String.valueOf(playerId));
			}
			return character;
	}
	/**
	 * 获得玩家最大战斗力职业
	 * @param playerId
	 * @return
	 * @throws Exception
	 */
	public Character getMaxFightnumCharacterById(int playerId) throws Exception{
		Character character=new Character();
		List<Character> characterList=new ArrayList<Character>();
		characterList=getCharacterListById(playerId);
		int maxFightNum = 0;
		for(Character ch:characterList){
			int fightNum = ch.getFightNum();
			if(fightNum > maxFightNum){
				character=ch;
				maxFightNum = fightNum;
			}
		}
		
		return character;
	}
	
	@SuppressWarnings("unchecked")
	public List<Character> getCharacterListForGame(Integer playerId,int characterId,boolean isKnife,boolean isZyzdz) throws Exception {
		
		List<Character> characterList=getCharacterListById(playerId);
		Set<Character> characterSet=new HashSet<Character>();
		Player player=getSimplePlayerById(playerId);
		if(characterList.size()!=0){
			if(characterId==0){
				for(String characterIds:player.getSelectedCharacterIds()){
					for(Character ch:characterList){
						int selectCh=StringUtil.toInt(characterIds);
						if(isZyzdz&&selectCh==7){//资源争夺战无工程兵
							if(player.getSelectedCharacterIds().length==1){
								selectCh=1;
							}else{
								continue;
							}
						}
						if(ch.getSysCharacter().getId()==selectCh){
							List<PlayerItem> costumeList=getCostumePackForGame(playerId, Constants.NUM_ONE, ch.getSysCharacter().getId());
							List<PlayerItem> weaponList=getWeaponPackForGame(playerId, Constants.NUM_ONE, ch.getSysCharacter().getId(),isKnife);
							SysCharacter sysCharacter=sysCharacterDao.getSysCharacterById(ch.getSysCharacter().getId());
							Character character=new Character();
							character.setPlayerId(playerId);
							character.setSysCharacter(sysCharacter);
							character.setWeaponList(weaponList);
							character.setCostumeList(costumeList);
							character.putOnCostume();
							characterSet.add(character);
							break;
						}
					}	
				}
			}else{
				for(Character ch:characterList){
					if(ch.getSysCharacter().getId()==characterId){
						List<PlayerItem> costumeList=getCostumePackForGame(playerId, Constants.NUM_ONE, ch.getSysCharacter().getId());
						List<PlayerItem> weaponList=getWeaponPackForGame(playerId, Constants.NUM_ONE, ch.getSysCharacter().getId(),isKnife);
						SysCharacter sysCharacter=sysCharacterDao.getSysCharacterById(ch.getSysCharacter().getId());
						Character character=new Character();
						character.setPlayerId(playerId);
						character.setSysCharacter(sysCharacter);
						character.setWeaponList(weaponList);
						character.setCostumeList(costumeList);
						character.putOnCostume();
						characterSet.add(character);
						break;
					}
				}	
			}
		}
		if(characterSet.size()==0){
			throw new BaseException(ExceptionMessage.NO_HAVE_CHARACTER);
		}
		return new ArrayList<Character>(characterSet);
	}	
	public List<Integer> getCharacterPackNum(Integer playerId){
		List<Integer> characterIds=new ArrayList<Integer>();
		characterIds=playerPackDao.getCharacterIdFromPlayerPack(playerId);
		return characterIds;
	}
	public  List<Character> getCharacterListById(Integer playerId) throws Exception {
		String objKey=CacheUtil.oCharacterList(playerId);
		List<Character> characterList=new ArrayList<Character>();
		characterList=mcc.get(objKey,Constants.CACHE_TIMEOUT);
		if(characterList==null){
			characterList=new ArrayList<Character>();
			List<Integer> characterIds=new ArrayList<Integer>();
			characterIds=playerPackDao.getCharacterIdFromPlayerPack(playerId);
			if(characterIds.size()!=0){
				for(Integer id:characterIds){
					List<PlayerItem> costumeList=getCostumePackList(playerId, Constants.NUM_ONE, id);
					List<PlayerItem> weaponList=getWeaponPackList(playerId, Constants.NUM_ONE, id);
					SysCharacter sysCharacter=sysCharacterDao.getSysCharacterById(id);
					Character character=new Character();
					character.setPlayerId(playerId);
					character.setSysCharacter(sysCharacter);
					character.setWeaponList(weaponList);
					character.setCostumeList(costumeList);
					character.putOnCostume();
					character.initFightNum();
					characterList.add(character);
				}
			}
			if(characterList.size()!=0){
				mcc.set(objKey, Constants.CACHE_ITEM_TIMEOUT, characterList,Constants.CACHE_TIMEOUT);
			}
		}
		return characterList;
	}	
	
	/**
	 * method to check duplication player name
	 * @param name
	 * @return Integer 
	 * @throws Exception
	 */
	public Integer fuzzyCountPlayer(String name) throws Exception {
		return playerDao.fuzzyCountPlayer(name);
	}	
	
	/**
	 * method to fuzzy search player
	 * @param name
	 * @param page
	 * @return List<Player>
	 * @throws Exception
	 */
	public List<Player> fuzzyGetPlayerByName(String name, Integer page) throws Exception {
		Map<Integer, Player> playerMap = playerDao.fuzzyGetPlayerMap(name, Constants.FRIEND_PAGE_SIZE * (page - 1), Constants.FRIEND_PAGE_SIZE);
		List<PlayerTeam> playerTeamList = playerTeamDao.getTeamByPlayerIds(new ArrayList<Integer>(playerMap.keySet()));
		Map<Integer, Team> teamMap = teamDao.getAllTeamMap();		
		return new ArrayList<Player>(JoinDataUtil.joinPlayerTeam(playerMap, playerTeamList, teamMap).values());
	}
	
	/**
	 * Get the recent win rate of a player
	 * @param playerId
	 * @param playerName
	 * @param gameType
	 * @return  win rate history, the first rate is the latest rate, if the date not exist, fill defaultRate
	 */
	public List<Integer> getRecentPlayerRate(int playerId, String playerName, int gameType){
		final int defaultRate = 0;
		//rates were stored in list order by weeks desc
		List<PlayerRate> rates = nosqlService.getPlayerRatesByType(playerId, playerName, GAMETYPE.getTypeByValue(gameType));
		if (rates.isEmpty()){
			List<Integer> emptyList = new  ArrayList<Integer>();
			for (int i=0;i<Constants.PLAYER_RATE_HISTORY_SIZE;i++) {
				emptyList.add(0);
			}
			return emptyList;
		}
		
		//first record must be the record of current week, we shouldn't include it
//		rates.remove(0);
		
		Calendar c = Calendar.getInstance();
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.setMinimalDaysInFirstWeek(1);
		int weeksInAYear = 365/7;//=52
		//get the last week as last date
		int currentWeeks = c.get(Calendar.WEEK_OF_YEAR) - 1;
		
		//fist, iterator the rates, these rates were sorted by weeks, so the first rate must be the latest rate
		//put the current rate into suitable slot by calculating the time offset according to now
		int [] result = new int[Constants.PLAYER_RATE_HISTORY_SIZE];
		int leftIndex = result.length;
		int leftRate =  rates.isEmpty()? defaultRate:rates.get(0).getWinRate();
		
		for (int i=0;i<Constants.PLAYER_RATE_HISTORY_SIZE && i < rates.size();i++) {
			PlayerRate pr = rates.get(i);
			leftRate = pr.getWinRate();
			//time offset means the number of slot 
			int timeOffset = currentWeeks - pr.getTime();
			
			if (timeOffset < 0) {// cross a year
				timeOffset = currentWeeks +  weeksInAYear - pr.getTime(); 
			}
			if (timeOffset > leftIndex) {// if current rate was prior to current period, no need to iterator further more
				//means this record was recored many time ago, and no new record during these time, fill it from start to next period
				Arrays.fill(result, 0, leftIndex, leftRate);//fill last rate that not in range
				break;		
			}
			//from right to left
			int slotIndex = result.length -1 - timeOffset;
			//put rate into suitable slot
			Arrays.fill(result, slotIndex, leftIndex, leftRate);
			leftIndex = slotIndex;
		}
		
		List<Integer> list = new ArrayList<Integer>(result.length);
		
		for (int i : result){
			list.add(i);
		}
		return list;
	}
	
	//===============================================================================	
	//								  Friend Services
	//===============================================================================
	@SuppressWarnings("unchecked")
	public List<Friend> getFriendList(final Integer playerId,final Integer type) throws Exception {
		String objKey="";
		if(type.equals(Constants.BLACKLIST)){
			objKey=CacheUtil.oBlackList(playerId);
		}else if(type.equals(Constants.FRIEND)){
			objKey=CacheUtil.oFriendList(playerId);
		}else{
			objKey=CacheUtil.oGroupList(playerId);
		}
		List<Friend> friendList = (List<Friend>) loadValue(objKey, new CacheMissHandler() {
			@Override
			public Object loadFromDB(GetService service) throws Exception {
				List<Friend> friends = friendDao.getFriend(playerId, type);
				List<Friend> result = new ArrayList<Friend>();
				for(Friend friend:friends){
						//取出好友的信息加到结果中
						Player player =getPlayerById(friend.getFriendId());
						friend.setName(player.getName());
						friend.setRank(player.getRank());
						friend.setInternetCafe(player.getInternetCafe());
						friend.setIsVip(player.getIsVip());
						friend.setIcon(player.getIcon());
						if(player.getBuffList()!=null&&player.getBuffList().size()!=0){
							boolean isCard=false;
							for(PlayerItem pi:player.getBuffList()){
								SysItem si=sysItemDao.getSystemItemById(pi.getItemId());
								if(Constants.DEFAULT_ITEM_TYPE == si.getType() && si.getIId()==1&&si.getIBuffId()==32){
									isCard=true;
									friend.setCard(StringUtil.toInt(si.getIValue()));
									break;
								}
							}
							if(!isCard){
								friend.setCard(0);
							}
						}else{
							friend.setCard(0);
						}
						if(friend.getIsVip().equals(1)){
							result.add(0, friend);
						}else if(friend.getFriendId().equals(friend.getPlayerId())){
							result.add(0, friend);
						}else{
							result.add(friend);
						}
				}
				return result;
			}
		},5*1000);
		return friendList;
	}
	
	@SuppressWarnings("unchecked")
	public List<Friend> getRequestFriendList(final Integer playerId,final Integer type) throws Exception {
		String objKey="";
		if(type.equals(Constants.BLACKLIST)){
			objKey=CacheUtil.oReqBlackList(playerId);
		}else if(type.equals(Constants.FRIEND)){
			objKey=CacheUtil.oReqFriendList(playerId);
		}else{
			objKey=CacheUtil.oReqGroupList(playerId);
		}
		List<Friend> friendList = (List<Friend>) loadValue(objKey, new CacheMissHandler() {
			@Override
			public Object loadFromDB(GetService service) throws Exception {
				List<Friend> friends = friendDao.getRequestList(playerId, type);
				List<Friend> result = new ArrayList<Friend>();
				for(Friend friend:friends){
						//取出好友的信息加到结果中
						Player player =getPlayerById(friend.getFriendId());
						friend.setName(player.getName());
						friend.setRank(player.getRank());
						friend.setIsVip(player.getIsVip());
						result.add(friend);
				}
				return result;
			}
		});
		return friendList;
	}
	//if exist friend request
	public Friend getFriendIndexById(int playerId,int friendId,int type) throws Exception{
		return friendDao.getFriendIndex(playerId, friendId, type);
	}
	public Friend getFriendById(int playerId,int friendId,int type) throws Exception{
		List<Friend> blackList = getFriendList(playerId,type);
		
		if(blackList!=null&&blackList.size()!=0){
			for(Friend fri:blackList){
				if(friendId==fri.getFriendId()){
					return fri;
				}
			}
		}else{
			return null;
		}
		return null;
	}
	public Friend getBlackById(int playerId,int friendId) throws Exception{
		/*String objKey=CacheUtil.oBlackList(playerId);
		List<Friend> blackList=mcc.get(objKey);*/
		List<Friend> blackList = getFriendList(playerId,Constants.BLACKLIST);
		
		if(blackList!=null&&blackList.size()!=0){
			for(Friend fri:blackList){
				if(friendId==fri.getFriendId()){
					return fri;
				}
			}
		}else{
			return null;
		}
		return null;
	}
	public Friend getFriendById(int playerId,int friendId) throws Exception{
		List<Friend> friendList = getFriendList(playerId,Constants.FRIEND);
		
		if(friendList!=null&&friendList.size()!=0){
			for(Friend fri:friendList){
				if(friendId==fri.getFriendId()){
					return fri;
				}
			}
		}else{
			return null;
		}
		return null;
	}
	@SuppressWarnings("unchecked")
	public List<Friend> getFriendListForMsg(Integer playerId) {
		return friendDao.getFriend(playerId, Constants.FRIEND);
	}
	
	public List<Integer> getGroupOwers(Integer friendId) {
		return friendDao.getGroupOwners(friendId);
	}
	
	
	public int fuzzyCountGroup(String name)throws Exception{
		return friendDao.fuzzyCountGroup(name);
	}
	
	//TODO optimize
//	public List<Friend> getFriendRankList(Integer playerId) {
//		return friendDao.getFriendRankList(playerId);
//	}
	
	public List<BasePlayerEvent> getFriendsNews(int pid) throws Exception{
		List<Friend> friends = getFriendList(pid, Constants.FRIEND);
		return nosqlService.getLatestEventsFromFriends(friends);
	}
	
	
	public boolean hasNewFriendNewsSinceLastLogin(Player p){
//		try {
//			List<BasePlayerEvent> recentEvents = getFriendsNews(p.getId());
//			for (BasePlayerEvent be : recentEvents) {
//				if(be.getTime() > p.getLastLogoutTime()){
//					return true;
//				}
//			}
//			return false;
//		} catch (Exception e) {
//			e.printStackTrace();
			return false;
//		}
	}
	
	//===============================================================================	
	//								  Server Services
	//===============================================================================	
	@SuppressWarnings("unchecked")
	public List<Server> getServersList() throws Exception {
		String key = CacheUtil.oServerList();
		List<Server> result = (List<Server>) loadValue(key, new CacheMissHandler() {
			@Override
			public Object loadFromDB(GetService service) {
				List<Server> serverList= serverDao.getServerList();
				return serverList;
			}
		}	
		);
		return result;
	}

	@SuppressWarnings("unchecked")
	public Server getServer(int serverId) throws Exception {
		List<Server> server = getServersList();
		for(Server s : server){
			if(s.getId() == serverId){
				return s;
			}
		}
		return 	 null;
	}
	@SuppressWarnings("unchecked")
	public Channel getChannel(int channelId) throws Exception {
		List<Server> server = getServersList();
		for (Server s : server) {
			List<Channel> channels = getChannelsList(s.getId());
			for(Channel c : channels){
				if(c.getId()== channelId){
					return c;
				}
			}
		}
		return null;
	}
	//===============================================================================	
	//								  Rank Services
	//===============================================================================	
	@SuppressWarnings("unchecked")
	public String getRankList() throws Exception {
		List<Rank> rankList= rankDao.getRankList();
		return 	 Converter.rankList(rankList);
	}
	
	@SuppressWarnings("unchecked")
	public List<Rank> getRanks() throws Exception{
		String key = CacheUtil.oRankList();
		List<Rank> result = (List<Rank>) loadValue(key, new CacheMissHandler() {
			@Override
			public Object loadFromDB(GetService service) {
				List<Rank> rankList= rankDao.getRankList();
				Collections.sort(rankList, new Comparator<Rank>(){
					@Override
					public int compare(Rank o1, Rank o2) {
						return o1.getExp().compareTo(o2.getExp());
					}
				});
				return rankList;
			}
		});
		
		return result;
	}
	
	public Rank getRankByExp(int exp) throws Exception{
		List<Rank> list = getRanks();
		for(int i=1;i<list.size();i++){
			if (exp < list.get(i).getExp()) {
				return list.get(i-1);
			}
		}
		//run here, return the biggest rank 
		return list.get(list.size()-1);
	}
	
	public Rank getRankByLevel(int level) throws Exception{
		List<Rank> list = getRanks();
		return list.get(level-1);
	}
	
	//===============================================================================	
	//								  Key Word Services
	//===============================================================================	
	@SuppressWarnings("unchecked")
	public String getKeyWords() throws Exception {
		return keyWordsDao.getKeyWords();
	}
	//===============================================================================	
	//								  Channel Services
	//===============================================================================	
	@SuppressWarnings("unchecked")
	public List<Channel> getChannelsList(final int serverId) throws Exception {
		String key = CacheUtil.oChannelList(serverId);
		List<Channel> result = (List<Channel>) loadValue(key, new CacheMissHandler() {
			@Override
			public Object loadFromDB(GetService service) {
				List<Channel> channelList= serverDao.getChannelrList(serverId);
				return channelList;
			}
		}	
		);
		return result;
	}
	//===============================================================================	
	//								 Payment Services
	//===============================================================================
	@SuppressWarnings("unchecked")
	public List<Payment> getPaymemtListById(final int itemId) throws Exception{
		String key = CacheUtil.oPaymentList(itemId);
		List<Payment> paymentList=(List<Payment>) loadValue(key, new CacheMissHandler() {
			@Override
			public Object loadFromDB(GetService service) throws Exception {
				List<Payment> list=paymentDao.getPaymentListById(itemId);
//				if(list.size()!=0){
//					for(Payment pay:list){
//						pay.init();
//					}
//				}
				return list;
			}
		}	
		);
		return paymentList;
	}
	public Payment getPaymentById(int itemId ,int paymentId)throws Exception{
		Payment returnPayment=null;
		List<Payment> list=getPaymemtListById(itemId);
		if(list.size()==0){
			throw new BaseException(ExceptionMessage.NOT_HAVE_PRICE);
		}else{
			for(Payment pay:list){
				if(pay.getId()==paymentId){
					returnPayment= pay;
					break;
				}
			}
		}
		return returnPayment;
	}
	//===============================================================================	
	//								  SysItem Services
	//===============================================================================
	public SysItem joinPayment(SysItem si) throws Exception{
		List<Payment> paymentList=mcc.get(CacheUtil.oPaymentList(si.getId()),Constants.CACHE_TIMEOUT);
		if(paymentList==null){
			paymentList=getPaymemtListById(si.getId());
		}
		List<Payment> gpPricesList=new ArrayList<Payment>();
		List<Payment> crPricesList=new ArrayList<Payment>();
		List<Payment> voucherPricesList=new ArrayList<Payment>();
		List<Payment> medalPricesList=new ArrayList<Payment>();
		List<Payment> resPricesList=new ArrayList<Payment>();
		List<Payment> resDisPricesList=new ArrayList<Payment>();
		List<Payment> resTeamPricesList=new ArrayList<Payment>();
		List<Payment> resDisTeamPricesList=new ArrayList<Payment>();
		
		List<Payment> allGpPricesList=new ArrayList<Payment>();
		List<Payment> allCrPricesList=new ArrayList<Payment>();
		List<Payment> allVoucherPricesList=new ArrayList<Payment>();
		List<Payment> allResPricesList=new ArrayList<Payment>();
		List<Payment> allResDisPricesList=new ArrayList<Payment>();
		List<Payment> allResTeamPricesList=new ArrayList<Payment>();
		List<Payment> allResDisTeamPricesList=new ArrayList<Payment>();
		List<Payment> reviveCoinList=new ArrayList<Payment>();	
		//20160401zlm start  
		List<Payment> achipPricesList = new ArrayList<Payment>();
		List<Payment> bchipPricesList = new ArrayList<Payment>();
		List<Payment> cchipPricesList = new ArrayList<Payment>();
		List<Payment> allAchipPricesList = new ArrayList<Payment>();
		List<Payment> allBchipPricesList = new ArrayList<Payment>();
		List<Payment> allCchipPricesList = new ArrayList<Payment>();
		//20160401zlm end
		if(paymentList!=null&&paymentList.size()!=0){
			for(Payment pay:paymentList){
				switch (pay.getPayType()) {
				case 1:
					if(pay.getIsShow()==1){
					gpPricesList.add(pay);
					}
					allGpPricesList.add(pay);
					break;
				case 2:
					if(pay.getIsShow()==1){
					crPricesList.add(pay);
					}
					allCrPricesList.add(pay);
					break;
				case 3:
					if(pay.getIsShow()==1){
					voucherPricesList.add(pay);
					}
					allVoucherPricesList.add(pay);
					break;
				case 4:
					if(pay.getIsShow()==1){
						medalPricesList.add(pay);
					}
					break;
				case 5:
					if(pay.getIsShow()==1){
						resPricesList.add(pay);
					}
					allResPricesList.add(pay);
					break;	
				case 6:
					if(pay.getIsShow()==1){
						resTeamPricesList.add(pay);
					}
					allResTeamPricesList.add(pay);
					break;			
				case 7:
					if(pay.getIsShow()==1){
						resDisPricesList.add(pay);
					}
					allResDisPricesList.add(pay);
					break;		
				case 8:
					if(pay.getIsShow()==1){
						resDisTeamPricesList.add(pay);
					}
					allResDisTeamPricesList.add(pay);
					break;
				case 9:
					if(pay.getIsShow()==1){
						reviveCoinList.add(pay);
					}
					break;
				//20160401zlm start
				case Constants.A_CHIP_PAY:
					if(pay.getIsShow()==1){
						achipPricesList.add(pay);
					}
					allAchipPricesList.add(pay);
					break;
				case Constants.B_CHIP_PAY:
					if(pay.getIsShow()==1){
						bchipPricesList.add(pay);
					}
					allBchipPricesList.add(pay);
					break;
				case Constants.C_CHIP_PAY:
					if(pay.getIsShow()==1){
						cchipPricesList.add(pay);
					}
					allCchipPricesList.add(pay);
					break;
				//20160401zlm end
				default:
					break;
				}
			}
			//20160401zlm start  
			si.setAchipPricesList(achipPricesList);
			si.setBchipPricesList(bchipPricesList);
			si.setCchipPricesList(cchipPricesList);
			si.setAllAchipPricesList(allAchipPricesList);
			si.setAllBchipPricesList(allBchipPricesList);
			si.setAllCchipPricesList(allCchipPricesList);
			//20160401zlm end
			si.setGpPricesList(gpPricesList);
			si.setCrPricesList(crPricesList);
			si.setVoucherPricesList(voucherPricesList);
			si.setMedalPricesList(medalPricesList);
			si.setResPricesList(resPricesList);
			si.setResDisPricesList(resDisPricesList);
			si.setResTeamPricesList(resTeamPricesList);
			si.setResTeamPricesList(resTeamPricesList);
			si.setAllGpPricesList(allGpPricesList);
			si.setAllCrPricesList(allCrPricesList);
			si.setAllVoucherPricesList(allVoucherPricesList);
			si.setAllResPricesList(allResPricesList);
			si.setAllResDisPricesList(allResDisPricesList);
			si.setAllResTeamPricesList(allResTeamPricesList);
			si.setAllResDisTeamPricesList(allResDisTeamPricesList);
			si.setReviveCoinList(reviveCoinList);
		}else{
			log.error(ExceptionMessage.SYS_ITEM_EMPTY_PRICE + si.getDisplayNameCN());
			throw new Exception(ExceptionMessage.SYS_ITEM_EMPTY_PRICE + "id=" + si.getId());
		}
		return si;
	}
	private void joinSysItemItems(SysItem si) throws Exception{
		String[] items = si.getItems().split(",");
		if(items.length > 0){
			List<SysItem> itemList = new ArrayList<SysItem>();
			for(String item : items){
				if(null != item && !"".equals(item)){
					itemList.add(getSysItemByItemId(StringUtil.toInt(item)));
				}
			}
			si.setPackages(itemList);
		}
	}
	/**
	 * method to construction SysItem datas
	 * @param itemList
	 * @param type
	 * @param subType
	 * @return ArrayList<SysItem>[]
	 */
	/*private ArrayList<SysItem>[] constructionData(List<SysItem> itemList) {
		ArrayList<SysItem> result[] = null;
		ArrayList<SysItem> tmpList = new ArrayList<SysItem>();
		for (SysItem si : itemList) {
			if(Constants.BOOLEAN_NO.equals(si.getIsDeleted())){
				si.init();// init
				if (result == null) {
					result = new ArrayList[1];
				}
				if (tmpList.size() != Constants.PAGE_SIZE[si.getType()-1]) {
					tmpList.add(si);
				} else {
					int pagePointer = result.length;
					ArrayList<SysItem> tmpArray[] = new ArrayList[pagePointer+1];
					System.arraycopy(result, 0, tmpArray, 0, pagePointer);
					tmpArray[pagePointer-1] = tmpList;
					result = tmpArray;
					tmpList = new ArrayList<SysItem>(Constants.PAGE_SIZE[si.getType()-1]);
					tmpList.add(si);
				}
			
			}
		}
		if (result != null && result.length != 0 && result[0] == null) {
			result[0] = tmpList;
		}else if(result != null && result.length != 0 && result[result.length-1] == null){
			result[result.length-1]=tmpList;
		}
		return result;
	}*/
	
	/**
	 * method to get SysItem by type,subtype
	 * @param userId
	 * @param playerId
	 * @param type
	 * @param subType
	 * @param gender
	 * @param filter
	 * @return
	 * @throws Exception
	 */
	/*@SuppressWarnings("unchecked")
	public ArrayList<SysItem>[] getSysItem(int type,int characterId) throws Exception {
		
		List<SysItem> itemList=new ArrayList<SysItem>();
		String objKey=CacheUtil.oShop(type,characterId);
		ArrayList<SysItem> result[]= mcc.get(objKey,Constants.CACHE_TIMEOUT);
		if (result == null) {
			itemList = sysItemDao.getSystemItemListByCharacter(type, characterId);
			for(SysItem si:itemList){
				//1 join payment
				si=joinPayment(si);
				
			}
//			Collections.sort(itemList, new ComparatorUtil.SysItemWeaponSeqComparatorClass());
			if(itemList!=null&&itemList.size()!=0){
				//2 construct data
				result=constructionData(itemList);
				if(result!=null){
				mcc.set(objKey, Constants.CACHE_ITEM_TIMEOUT, result,Constants.CACHE_TIMEOUT);
				}
			}
		}
	
		return result;
	}*/
	
	/**
	 * method to get sysitem List by type
	 * @param type
	 * @return
	 * @throws Exception
	 */
	/*@SuppressWarnings("unchecked")
	public ArrayList<SysItem>[] getSysItem(int type) throws Exception {
		
		List<SysItem> itemList=new ArrayList<SysItem>();
		String objKey=CacheUtil.oShop(type);
		ArrayList<SysItem> result[]= mcc.get(objKey,Constants.CACHE_TIMEOUT);
		if (result == null) {
			itemList = sysItemDao.getSystemItemList(type);
			for(SysItem si:itemList){
				si=joinPayment(si);
			}
			if(itemList!=null&&itemList.size()!=0){
				result=constructionData(itemList);
				if(result!=null){
				mcc.set(objKey, Constants.CACHE_ITEM_TIMEOUT, result,Constants.CACHE_TIMEOUT);
				}
			}
		}
	
		return result;
	}*/
	
	public SysItem getSysItemByItemId(int itemId)throws Exception{
		SysItem sysItem=sysItemDao.getSystemItemById(itemId);
//		sysItem=joinPayment(sysItem);
		if(sysItem!=null){
//			sysItem.init();
			if(sysItem.getType() == Constants.DEFAULT_PACKAGE_TYPE||sysItem.getType()==Constants.DEFAULT_ZYZDZ_TYPE){
				joinSysItemItems(sysItem);
			}
			
			if(sysItem.getNeedTeamPlaceLevel()>99){
				sysItem.setBelongSuit(ConfigurationUtil.SUITMAP.get(sysItem.getNeedTeamPlaceLevel()));
			}
		}else{
			log.warn("not find the sysitem itemId="+itemId);
		}
		return sysItem;
	}
	
	public Map<Integer, SysItem> getAllSysItemMap() {
		return sysItemDao.getAllSysItemMap();
	}	
	
	
//	public int getTicketBalance(PlayerInfo playerInfo) throws Exception{
//		int SUCCESS = 0;
//		UserInfoAuthenNotify resp = ServiceLocator.getSDOComponent().sendUserInfoAuthenRequest(playerInfo);
//		if (resp.getResult() == SUCCESS) {
//			return resp.getBalance(SDOItemOrder.PayType.TICKET.getCode());
//		}else {
//			throw new Exception("fail to get user authen ");
//		}
//		return 0;
//	}
	
	
	

	
	//===============================================================================	
	//								  SysItem Services
	//===============================================================================
	public int getLogVersionFromSysItemLog(int itemId)throws Exception{
		return sysItemLogDao.getSysItemLogVersion(itemId);
	}
	public List<SysItem> getSysItemLog(int itemId)throws Exception{
		return	 sysItemLogDao.getSysItemLog(itemId);
	}

	//===============================================================================	
	//								  Player Item Services
	//===============================================================================
	/**
	 * method to get parts for weapon
	 * @param playerId
	 * @param list
	 * @return
	 */
	/*public  List<PlayerItem> getPartsForPlayerItem(Integer playerId,List<PlayerItem> list) throws Exception{
		if(list!=null||list.size()!=0){
			//get parts
			List<PlayerItem> parts=getPlayerItemListByType(playerId, Constants.DEFAULT_PART_TYPE, 0, 0);
			
			Map<Integer, List<PlayerItem>> tempMap=new HashMap<Integer, List<PlayerItem>>();

			for(PlayerItem pi : parts){
				if(tempMap.get(pi.getParentItemId())==null){
					tempMap.put(pi.getParentItemId(),new ArrayList<PlayerItem>());
				}
				(tempMap.get(pi.getParentItemId())).add(pi);
			}
			//init weapon with part
			for(PlayerItem pi : list){
				SysItem si=sysItemDao.getSystemItemById(pi.getItemId());
				if(si.getSubType()>6){//exclude knife,grenade and flash
					pi.setParts(tempMap.get(pi.getId()));
					if(pi.getParts()!=null&&pi.getParts().size()!=0){
						pi.putOnPart();
					}else if(pi.getId()!=0){
						pi.initWithoutPart(si);
					}
				}else{
					pi.initWithoutPart(si);
				}
			}
			//verify description string
			for(PlayerItem pi : list){
				String decStr=pi.getModifiedDesc();
				if(decStr!=null&&decStr.indexOf("2")!=-1){
					String sysDescStr=sysItemDao.getSystemItemById(pi.getItemId()).getDescription();
					List<PlayerItem> partList=pi.getParts();
					if(partList!=null&&partList.size()!=0){
						for(PlayerItem part:partList){
							SysItem si=sysItemDao.getSystemItemById(part.getItemId());
							sysDescStr=StringUtil.updateModifiedDesc(sysDescStr, si.getSubType(), Constants.NUM_TWO+"");
						}
					}
					if(!decStr.equals(sysDescStr)){
						pi.setModifiedDesc(sysDescStr);
						playerItemDao.updatePlayerItem(pi);
					}
				}
			}
		}
	   return list;
	}*/
	
	
	/**
	 * method to get player item by type
	 * @param userId
	 * @param playerId
	 * @param type
	 * @return List<PlayerItem>[][]
	 * @throws Exception
	 */
	//NO use
	@SuppressWarnings("unchecked")
	public List<PlayerItem>[][] getPlayerItems_O(Integer playerId, int type)throws Exception {
//		String objKey=CacheUtil.oStorage(playerId,type,0);
		List<PlayerItem> itemArray[][] = null;
//		if (type < Constants.NUM_ONE || type > Constants.TYPE_NUM ) {
//			log.warn("type or subtype not correct in getPlayerItems.");
//		} else {
//			// 1. Get Player Item from cache
//			itemArray = mcc.get(objKey,Constants.CACHE_TIMEOUT);
//			// 2. If cache miss, get data from database and construct into
//			if (itemArray == null) {
//				//construct data depend type
//				List<PlayerItem> playerItemList = getPlayerItemListByType(playerId, type);//PLAYERITEM 已有SYSITEM
//				Map<Integer, PlayerItem> playerItemMap=getPlayerItemMapByType(playerId, type);
//				
//				if(type==Constants.DEFAULT_COSTUME_TYPE||type==Constants.DEFAULT_WEAPON_TYPE){
//					List<PlayerPack> playerPacks = playerPackDao.getPlayerPackList(playerId);
//					playerItemList=JoinDataUtil.joinPlayerItemPack(playerItemMap, playerPacks);//加上背包号，和位置序号
//				}else if(type==Constants.DEFAULT_ITEM_TYPE){
//					List<PlayerBuff> playerBuffs=playerBuffDao.getPlayerBuffListByPlayerId(playerId);
//					playerItemList=JoinDataUtil.joinPlayerItemBuff(playerItemMap, playerBuffs);
//				}else if(type==Constants.DEFAULT_PART_TYPE){
//					List<ItemMod> itemMods=itemModDao.getItemModListById(playerId);
//					playerItemList=JoinDataUtil.joinPartItemMod(playerItemMap, itemMods);
//				}
//				//TODO verify modify desc put on part 
//				//init parts while is weapon
//				if(type==Constants.DEFAULT_WEAPON_TYPE){
//					playerItemList=getPartsForPlayerItem(playerId, playerItemList);
//				}
//				//knife 
//				int packSize=playerPackDao.getPlayerPackSize(playerId);
//				
//				if(type==Constants.DEFAULT_WEAPON_TYPE){
//					//knife in pack
//					ArrayList<PlayerItem> knifePackList = new ArrayList<PlayerItem>();
//					//knife not in pack
//					ArrayList<PlayerItem> knifeNoPackList = new ArrayList<PlayerItem>();
//					//playerItem list with the pack size knife
//					ArrayList<PlayerItem> needList = new ArrayList<PlayerItem>();
//					int index=0;
//					int index2=0;
//					for (PlayerItem pi : playerItemList) {
//						if(pi.getSysItem().getSubType()==Constants.DEFAULT_KNIFE_SUBTYPE && Constants.BOOLEAN_YES.equals(pi.getIsDefault())){
//							if(pi.getPack()!=0){
//								knifePackList.add(pi);
//								index++;
//							}else{
//								knifeNoPackList.add(pi);
//								index2++;
//							}
//						}
//						else{
//							needList.add(pi);
//						}
//					}
//					needList.addAll(knifePackList);
//					if(packSize>index){
//						List<PlayerItem> l=knifeNoPackList.subList(0, packSize-index);
//						needList.addAll(l);
//					}
//					playerItemList=needList;
//				}
//				
//				//对仓库里面的物品进行排序
//				Collections.sort(playerItemList,new Comparator<PlayerItem>(){
//					@Override
//					public int compare(PlayerItem o1, PlayerItem o2) {
//						return (o1.getId()>=o2.getId()?1:-1);
//					}
//				});
//				itemArray = new ArrayList[Constants.TYPE_NUM][];
//				//将playerItemList 进行分页
//				ArrayList<PlayerItem> tmpList;
//				for (PlayerItem pi : playerItemList){
//					if (pi.getPack() == null) {
//						pi.setPack(0);// set pi not in any pack
//					}
//					if(type!=Constants.DEFAULT_WEAPON_TYPE){//初始化除武器外的所有的resources
//						pi.initWithoutPart();
//					}
//					//一页的PLAYEITEM
//					tmpList = new ArrayList<PlayerItem>(Constants.DEFAULT_PAGE_SIZE);
//					int t = pi.getSysItem().getType() - 1;//类型，原值从1开始
//					// init the array on first time
//					if (itemArray[t] == null) {
//						itemArray[t] = new ArrayList[1];
//						itemArray[t][0] = tmpList;
//					}
//					int pg = itemArray[t].length - 1;//当前的最后页
//					// 如果当前页还未满，则直接添加
//					if (itemArray[t][pg].size() < Constants.DEFAULT_PAGE_SIZE) {
//						itemArray[t][pg].add(pi);
//					}
//					// 8 item
//					else {
//						int pagePointer = itemArray[t].length;
//						ArrayList tmpArray[] = new ArrayList[pagePointer + 1];
//						System.arraycopy(itemArray[t], 0, tmpArray, 0,
//								pagePointer);
//
//						tmpList = new ArrayList<PlayerItem>(Constants.DEFAULT_PAGE_SIZE);
//						tmpList.add(pi);
//						tmpArray[pagePointer] = tmpList;
//						itemArray[t] = tmpArray;
//					}
//				}
//				
//				if(itemArray!=null){
//					// 3. Set 2-dimension array into cache
//					mcc.set(objKey, Constants.CACHE_ITEM_TIMEOUT,itemArray,Constants.CACHE_TIMEOUT);
//				}
//			}
//		}
		return itemArray;
	}
	/*private List<PlayerItem> getPlayerItem_s(Integer playerId, int type)throws Exception {
		String objKey=CacheUtil.oStorage(playerId,type,0);
		List<PlayerItem> playerItemList = null;
		if (type < Constants.NUM_ONE || type > Constants.TYPE_NUM ) {
			log.warn("type or subtype not correct in getPlayerItems.");
		} else {
			// 1. Get Player Item from cache
			playerItemList = mcc.get(objKey,Constants.CACHE_TIMEOUT);
			// 2. If cache miss, get data from database and construct into
			if (playerItemList == null) {
				//construct data depend type
			
				Map<Integer, PlayerItem> playerItemMap=getPlayerItemMapByType(playerId, type);
				playerItemList =new ArrayList<PlayerItem>(playerItemMap.values());//PLAYERITEM 已有SYSITEM
				if(type==Constants.DEFAULT_COSTUME_TYPE||type==Constants.DEFAULT_WEAPON_TYPE||type==Constants.DEFAULT_PART_TYPE){
					List<PlayerPack> playerPacks = playerPackDao.getPlayerPackList(playerId);
					playerItemList=JoinDataUtil.joinPlayerItemPack(playerItemMap, playerPacks);//加上背包号，和位置序号
				}else if(type==Constants.DEFAULT_ITEM_TYPE){
					List<PlayerBuff> playerBuffs=playerBuffDao.getPlayerBuffListByPlayerId(playerId);
					playerItemList=JoinDataUtil.joinPlayerItemBuff(playerItemMap, playerBuffs);
				}
				
				//对仓库里面的物品进行排序
				Collections.sort(playerItemList,new Comparator<PlayerItem>(){
					@Override
					public int compare(PlayerItem o1, PlayerItem o2) {
						return (o1.getId()>=o2.getId()?1:-1);
					}
				});

				for (PlayerItem pi : playerItemList){
					if (pi.getPack() == null) {
						pi.setPack(0);// set pi not in any pack
					}
//					SysItem si=sysItemDao.getSystemItemById(pi.getItemId());
					
//					joinPayment(si);
//					si.init();
//					pi.setSysItem(si);
//					pi.initWithoutPart(si);
				}
				if(playerItemList!=null){
					// 3. Set 2-dimension array into cache
					mcc.set(objKey, Constants.CACHE_ITEM_TIMEOUT,playerItemList,Constants.CACHE_TIMEOUT);
				}
			}
		}
		return playerItemList;
	}*/
	
	public List<PlayerItem> getPlayerItem_s1(Integer playerId, int type, int characterId, int subType)throws Exception {
		String objKey=CacheUtil.oStorage1(playerId, type, characterId, subType);
		List<PlayerItem> playerItemList = null;
		if (type < Constants.NUM_ONE || type > Constants.TYPE_NUM ) {
			log.warn("type or subtype not correct in getPlayerItem_s1.");
		} else {
			// 1. Get Player Item from cache
			playerItemList = mcc.get(objKey, Constants.CACHE_TIMEOUT);
			// 2. If cache miss, get data from database and construct into
			if (playerItemList == null||playerItemList.size()<=0) {
				//construct data depend type
				Map<Integer, PlayerItem> playerItemMap = getPlayerItemMapByType1(playerId, type, characterId, subType);
				playerItemList =new ArrayList<PlayerItem>(playerItemMap.values());//PLAYERITEM 已有SYSITEM
				if(type==Constants.DEFAULT_COSTUME_TYPE||type==Constants.DEFAULT_WEAPON_TYPE||type==Constants.DEFAULT_PART_TYPE){
					List<PlayerPack> playerPacks = playerPackDao.getPlayerPackList(playerId);
					playerItemList=JoinDataUtil.joinPlayerItemPack(playerItemMap, playerPacks);//加上背包号，和位置序号
				}else if(type==Constants.DEFAULT_ITEM_TYPE){
					List<PlayerBuff> playerBuffs=playerBuffDao.getPlayerBuffListByPlayerId(playerId);
					playerItemList=JoinDataUtil.joinPlayerItemBuff(playerItemMap, playerBuffs);
				}
				
				//对仓库里面的物品进行排序
				Collections.sort(playerItemList,new Comparator<PlayerItem>(){
					@Override
					public int compare(PlayerItem o1, PlayerItem o2) {
						return (o1.getId()>=o2.getId()?1:-1);
					}
				});

				for (PlayerItem pi : playerItemList){
					if (pi.getPack() == null) {
						pi.setPack(0);// set pi not in any pack
					}
//					SysItem si=sysItemDao.getSystemItemById(pi.getItemId());
					
//					joinPayment(si);
//					si.init();
//					pi.setSysItem(si);
//					pi.initWithoutPart(si);
				}
				if(playerItemList!=null){
					// 3. Set 2-dimension array into cache
					mcc.set(objKey, Constants.CACHE_ITEM_TIMEOUT,playerItemList,Constants.CACHE_TIMEOUT);
				}
			}
		}
		return playerItemList;
	}
	
	public List<PlayerItem> getPlayerItems(Integer playerId, int type, int characterId, int subType)throws Exception {
//		List<PlayerItem> list=getPlayerItem_s(playerId, type);
		List<PlayerItem> list = getPlayerItem_s1(playerId, type, characterId, subType);
		for(PlayerItem pi:list){
			SysItem si=sysItemDao.getSystemItemById(pi.getItemId());
			int isSuit=si.getNeedTeamPlaceLevel();
			if(isSuit>99){
				SysSuit ss=ConfigurationUtil.SUITMAP.get(isSuit);
				//do not need check NULL
				si.setBelongSuit(ss);
			}
//			joinPayment(si);
//			si.init();
			pi.setSysItem(si);
		}
		return list;
	}
	
	/*@SuppressWarnings("unchecked")
	public List<PlayerItem> getPlayerItemList(Integer playerId, Integer type, Integer characterId) throws Exception {
		List<PlayerItem> result = null;
		List<PlayerItem> itemArray = getPlayerItems(playerId, type, characterId, 0);
		
		result = new ArrayList<PlayerItem>();
		for(PlayerItem pi:itemArray){
			SysItem si=getSysItemByItemId(pi.getItemId());
//			si.init();
			if(pi.getPack().intValue()==0 && type.equals(si.getType()) &&Constants.BOOLEAN_NO.equals(pi.getIsGift())){
				
				if(pi.getPlayerItemUnitType()==2){
					pi.calculateTimeLeft();
					for(String s:si.getCharacterIds()){
						if(characterId.toString().equals(s)){
							if(pi.getTimeLeft()<=0&&si.getType()>3){
								break;
							}else{
								result.add(pi);
								break;
							}
						}
					}
				}else if(pi.getPlayerItemUnitType()==1&&pi.getQuantity()>0){
					for(String s:si.getCharacterIds()){
						if(characterId.toString().equals(s)){
							result.add(pi);
							break;
						}
					}
				}else if(pi.getPlayerItemUnitType()==0){
					for(String s:si.getCharacterIds()){
						if(characterId.toString().equals(s)){
							result.add(pi);
							break;
						}
					}
				}
				
			}
		}
		Collections.sort(result,new ComparatorUtil.PlayerItemIndexComparatorClass());
		return result;
	}*/
	@SuppressWarnings("unchecked")
	public List<PlayerItem> getPlayerItemList1(Integer playerId, Integer type, Integer characterId, int subType) throws Exception {
		List<PlayerItem> result = null;
		List<PlayerItem> itemArray = getPlayerItems(playerId, type, characterId, subType);

		result = new ArrayList<PlayerItem>();
		for (PlayerItem pi : itemArray) {
			SysItem si = pi.getSysItem();
			boolean isShow=false;
			if(si.getType()==1&&"Y".equals(pi.getIsDefault())){
				if(4==CommonUtil.getWeaponSeq(si.getWId())&&characterId!=4){
				}else{
					isShow=true;
				}
			}else if((si.getType()==2||si.getType()==3)&&"Y".equals(pi.getIsDefault())){
				if(2==CommonUtil.getCotumeSeq(si.getCId())||3==CommonUtil.getCotumeSeq(si.getCId())){
				}else{
					isShow=true;
				}
			}else{
				isShow=true;
			}
			if (pi.getPack().intValue() == 0 && type.equals(si.getType()) && Constants.BOOLEAN_NO.equals(pi.getIsGift())&&isShow) {

				if (pi.getPlayerItemUnitType() == 2 && pi.getQuantity() > 0) {
					// pi.calculateTimeLeft();
					//FIXME
					if (pi.getTimeLeft() <= 0 && si.getType() > 3) {
					} else {
						result.add(pi);
					}
				} else if (pi.getPlayerItemUnitType() == 1 && pi.getQuantity() > 0) {
					result.add(pi);
				} else if (pi.getPlayerItemUnitType() == 0 && pi.getQuantity() > 0) {
					result.add(pi);
				}

			}
		}
		Collections.sort(result, new ComparatorUtil.PlayerItemIndexComparatorClass());
		return result;
	}
	
	//get playeritem with sysitem info
	/*@SuppressWarnings("unchecked")
	private List<PlayerItem> getPlayerItemListByType(Integer playerId, int type, int characterId, int subType)throws Exception{
		Map<Integer,PlayerItem> playerItemMap = getPlayerItemMapByType(playerId, type);
		return new ArrayList(playerItemMap.values());
	}
	private Map<Integer, PlayerItem> getPlayerItemMapByType(Integer playerId, int type)throws Exception{
		Map<Integer, SysItem> sysItemMap = sysItemDao.getAllSysItemMap();				
		Map<Integer,PlayerItem> playerItemMap = playerItemDao.getPlayerItemMap(playerId);
		Map<Integer,PlayerItem> returnMap =new HashMap<Integer, PlayerItem>();
		for(Map.Entry<Integer, PlayerItem> entry: playerItemMap.entrySet()) {  
			PlayerItem pi=playerItemMap.get(entry.getKey());
			if(pi!=null){
				SysItem si = sysItemMap.get(pi.getItemId());
				if(si.getType()==type){
					if(si.getCharacterList().size()==0){
//						si.init();
					}
//					entry.getValue().setSysItem(si);
					pi.initWithoutPart(si);
					returnMap.put(pi.getId(), pi);
				}
			}
		}
		return returnMap;
	}*/
	
	public Map<Integer, PlayerItem> getPlayerItemMapByType1(Integer playerId, int type, int characterId, int subType) throws Exception {
		Map<Integer, SysItem> sysItemMap = sysItemDao.getAllSysItemMap();
		Map<Integer, PlayerItem> playerItemMap = playerItemDao.getPlayerItemMap(playerId);
		Map<Integer, PlayerItem> returnMap = new HashMap<Integer, PlayerItem>();
		for (Map.Entry<Integer, PlayerItem> entry : playerItemMap.entrySet()) {
			PlayerItem pi = entry.getValue();
			if (pi != null&&Constants.BOOLEAN_NO.equals(pi.getIsGift())) {
				
				SysItem si = sysItemMap.get(pi.getItemId());
				if(si==null){
					continue;
				}
				if (si.getType() == type) {
					if (si.getCharacterList().size() == 0) {
						si.init();
//						si.initFightNumFront();
					}
					if (characterId == 0 || si.getCharacterList().contains(characterId)) {
						switch (type) {
						case 1:
							if (subType == 0) {
								pi.initWithoutPart(si);
								returnMap.put(pi.getId(), pi);
							} else if (subType == 1) {
								if (si.getType() == 1 && si.getSubType() == 1) {
									pi.initWithoutPart(si);
									returnMap.put(pi.getId(), pi);
								}
							} else if (subType == 2) {
								if (si.getType() == 1 && si.getSubType() == 2) {
									pi.initWithoutPart(si);
									returnMap.put(pi.getId(), pi);
								}
							} else if (subType == 3) {
								if (si.getType() == 1 && si.getSubType() == 3) {
									pi.initWithoutPart(si);
									returnMap.put(pi.getId(), pi);
								}
							} else if (subType == 4) {
								if (si.getType() == 1 && si.getSubType() == 4) {
									pi.initWithoutPart(si);
									returnMap.put(pi.getId(), pi);
								}
							}
							break;
						case 2:
							pi.initWithoutPart(si);
							returnMap.put(pi.getId(), pi);
							break;
						case 3:
							if (subType == 0) {
								pi.initWithoutPart(si);
								returnMap.put(pi.getId(), pi);
							} else if (subType == 1) {
								if (si.getType() == 3 && si.getSubType() == 1) {
									pi.initWithoutPart(si);
									returnMap.put(pi.getId(), pi);
								}
							} else if (subType == 2) {
								if (si.getType() == 3 && si.getSubType() == 2) {
									pi.initWithoutPart(si);
									returnMap.put(pi.getId(), pi);
								}
							}
							break;
						case 4:
							if (subType == 0) {
								pi.initWithoutPart(si);
								returnMap.put(pi.getId(), pi);
							} else if (subType == 1) {
								if (si.getType() == 4 && si.getSubType() == 1) {
									pi.initWithoutPart(si);
									returnMap.put(pi.getId(), pi);
								}
							} else if (subType == 2) {
								if (si.getType() == 4 && si.getSubType() == 2) {
									pi.initWithoutPart(si);
									returnMap.put(pi.getId(), pi);
								}
							} else if (subType == 3) {
								if (si.getType() == 4 && si.getSubType() == 3) {
									pi.initWithoutPart(si);
									returnMap.put(pi.getId(), pi);
								}
							} else if (subType == 4) {
								if (si.getType() == 4 && si.getSubType() == 4 || (si.getType()==4 && si.getSubType()==5)) {//20150114 功能类内整合非卖类
									pi.initWithoutPart(si);
									returnMap.put(pi.getId(), pi);
								}
							} else if (subType == Constants.DEFAULT_ITEM_SUBTYPE.COST.getValue()) {
							if ((si.getType() == 4 && si.getSubType() == 7)) {
								pi.initWithoutPart(si);
								returnMap.put(pi.getId(), pi);
							}
						}
							break;
						case 5:
							if(subType==0){	//素材类比较特殊，0包含所有
								pi.initWithoutPart(si);
								returnMap.put(pi.getId(), pi);
							}else if(si.getSubType()==subType){
								pi.initWithoutPart(si);
								returnMap.put(pi.getId(), pi);
							}
							break;
						case 6:
							pi.initWithoutPart(si);
							returnMap.put(pi.getId(), pi);
							break;
						case 7:
							pi.initWithoutPart(si);
							returnMap.put(pi.getId(), pi);
							break;
						case 8:
							pi.initWithoutPart(si);
							returnMap.put(pi.getId(), pi);
							break;
						default :
							pi.initWithoutPart(si);
							returnMap.put(pi.getId(), pi);
							break;
						}
					}
					// entry.getValue().setSysItem(si);
				}
			}
		}
		return returnMap;
	}
	
	public Set<SysItem> getSysItemByItemIds(Set<String> itemIds) throws Exception{
		List<SysItem> sysItems = sysItemDao.getAllSystemItem();//getSysItem();
		Set<SysItem> result = new HashSet<SysItem>();
		
		for (SysItem item : sysItems) {
			if (itemIds.contains(String.valueOf(item.getId()))) {
				result.add(item);
			}
		}
				
		return result;
	}
	/**
	 * 根据iid,i_buff_id获得 playeritem List(固定获得type为4的道具)
	 * @param playerId
	 * @param iBuffId
	 * @param iid
	 * @return
	 * @throws Exception
	 */
	public List<PlayerItem> getPlayerItemByItemIidAndIBuffId(Integer playerId,int iid,int iBuffId) throws Exception{
		List<PlayerItem> retList = new ArrayList<PlayerItem>();
		List<PlayerItem> itemList =  getPlayerItems(playerId, Constants.DEFAULT_ITEM_TYPE, 0, 0);
		for(PlayerItem item:itemList){
			SysItem si = item.getSysItem();
			if(si.getIId()==iid && si.getIBuffId()==iBuffId){
				retList.add(item);
			}
		}
		return retList;
	}
	public List<PlayerItem> getPlayerItemsWillBeExpired(int userId, int playerId) throws Exception{

		List<PlayerItem> expiringItemList = new ArrayList<PlayerItem>();
		List<PlayerItem> playerItemList = new ArrayList<PlayerItem>();
		
		for(SysCharacter sc : getService.getDefaultSysCharacterList()){
			playerItemList.addAll(getPlayerItems(playerId, 1, sc.getId(), 0));
		}
		
		Calendar cal = Calendar.getInstance();
//		cal.add(Calendar.HOUR_OF_DAY, Constants.EXPIRED_ITEM_NOTIFY_HOUR);
		
		for(PlayerItem pi : playerItemList){
			if(Constants.ITEM_UNIT_TYPE_TIMEBASE == (int)pi.getPlayerItemUnitType() && cal.getTimeInMillis()>=(pi.getExpireDate().getTime()) && pi.getPack()!=0 && pi.getIsBind().equals(Constants.BOOLEAN_YES)){
//				pi.setSysItem(sysItemDao.getSystemItemById(pi.getItemId()));
				expiringItemList.add(pi);
			}
		}
		
		return expiringItemList;
	}
	
	public List<PlayerItem> getPlayerItemsNotDurable(int userId, int playerId) throws Exception{
		List<PlayerItem> durableItemList = new ArrayList<PlayerItem>();
		List<PlayerItem> playerItemList = new ArrayList<PlayerItem>();
		
		for(SysCharacter sc : getService.getDefaultSysCharacterList()){
			playerItemList.addAll(getPlayerItems(playerId, 1, sc.getId(), 0));
		}
		
		for(PlayerItem pi : playerItemList){
			if(Constants.ITEM_UNIT_TYPE_TIMEBASE == (int)pi.getPlayerItemUnitType() && pi.getDurable()<=0 && pi.getPack()!=0){
//				pi.setSysItem(sysItemDao.getSystemItemById(pi.getItemId()));
				durableItemList.add(pi);
			}
		}
		
		return durableItemList;
	}
	
	public int getExpiredPlayerItemSinceLastLogin(int pid, Date lastLoginTime,Player player) throws Exception{
		Date now = new Date();
		int flag=0;//0未过期 1即将2已过期
		boolean isExpired=false;
		List<PlayerItem> result = new ArrayList<PlayerItem>();
		List<PlayerItem> expireList = new ArrayList<PlayerItem>();
		List<PlayerItem> playerItemList = new ArrayList<PlayerItem>();
		for(SysCharacter sc : getService.getDefaultSysCharacterList()){
			playerItemList.addAll(getPlayerItems(pid, 1, sc.getId(), 0));
		}
		for (PlayerItem pi : playerItemList) {
			SysItem si=pi.getSysItem();
			Date exDate = new Date(pi.getExpireDate().getTime() - Constants.EXPIRED_ITEM_MAIL_HOUR*3600*1000);
			if(Constants.ITEM_UNIT_TYPE_TIMEBASE == (int)pi.getPlayerItemUnitType() &&pi.getExpireDate().before(new Date())&&isExpired==false && pi.getIsBind().equals(Constants.BOOLEAN_YES)){
				isExpired=true;
				if(pi.getExpireDate().after(lastLoginTime)){
					expireList.add(pi);
				}
			}
			//所有基于时间的，并且即将过期的，并且在上一次登录时未过期的
			if (Constants.ITEM_UNIT_TYPE_TIMEBASE == (int)pi.getPlayerItemUnitType() && now.after(exDate) && exDate.after(lastLoginTime)
					&&si.getType()<=3 && pi.getIsBind().equals(Constants.BOOLEAN_YES)){//道具过期不发过期邮件
				pi.initWithoutPart(si);
				result.add(pi);
				flag=1;
			}
		}
		if (result != null && !result.isEmpty()) {
			StringBuilder ids = new StringBuilder("");
			for(PlayerItem pi : result){
				ids.append(pi.getItemId()).append(",");
			}
			messageService.sendExpiredItemNotifyMail(player, result, ids.toString());
		}
		if(expireList!=null&&!expireList.isEmpty()){
			StringBuilder ids = new StringBuilder("");
			for(PlayerItem pi : expireList){
				ids.append(pi.getItemId()).append(",");
			}
			messageService.sendExpiredPackMail(player, expireList, ids.toString());
		}
		
		if(isExpired){
			flag=2;
		}
		return flag;
	}
	
	public int getDurableNotFull(int pid, Date lastLoginTime,Player player) throws Exception{
		Date now = new Date();
		int flag=0;//0 >40 1<40 2<0
		boolean isUndurable=false;
		List<PlayerItem> result = new ArrayList<PlayerItem>();
		List<PlayerItem> expireList = new ArrayList<PlayerItem>();
		List<PlayerItem> playerItemList = new ArrayList<PlayerItem>();
		for(SysCharacter sc : getService.getDefaultSysCharacterList()){
			playerItemList.addAll(getPlayerItems(pid, 1, sc.getId(), 0));
		}
		for (PlayerItem pi : playerItemList) {
			SysItem si=sysItemDao.getSystemItemById(pi.getItemId());
			pi.setSysItem(si);
			if(pi.getDurableInt()<=0){
				isUndurable=true;
				expireList.add(pi);
			}
			now = new Date(now.getTime() - Constants.EXPIRED_ITEM_MAIL_HOUR*3600*1000);
			//耐久度不满20的武器
			if (Constants.ITEM_UNIT_TYPE_TIMEBASE != (int)pi.getPlayerItemUnitType()  && pi.getDurable()<=Constants.DURABLE_NOTIFY&&pi.getDurableInt()!=0){
				flag=1;
				result.add(pi);
			}
		}
		if (result != null && !result.isEmpty()) {
			StringBuilder ids = new StringBuilder("");
			for(PlayerItem pi : result){
				ids.append(pi.getItemId()).append(",");
			}
			messageService.sendDurableNotifyMail(player, result, ids.toString());
		}
		if(expireList!=null&&!expireList.isEmpty()){
			StringBuilder ids = new StringBuilder("");
			for(PlayerItem pi : expireList){
				ids.append(pi.getItemId()).append(",");
			}
			messageService.sendExpiredPackMail(player, expireList, ids.toString());
		}
		if(isUndurable){
			flag=2;
		}
		return flag;
	}
	
	//get PlayerItem by ID
	/**
	 * method to get one player item by id
	 * @param userId
	 * @param playerId
	 * @param type
	 * @param itemId
	 * @return
	 * @throws Exception
	 */
	public PlayerItem getPlayerItemByItemId(Integer playerId, int type,int itemId)throws Exception{
		List<PlayerItem> itemList = new ArrayList<PlayerItem>();
		if (type != Constants.DEFAULT_WEAPON_TYPE && type != Constants.DEFAULT_COSTUME_TYPE && type != Constants.DEFAULT_PART_TYPE) {
			itemList = getPlayerItems(playerId, type, 0, 0);
		} else {
			for(SysCharacter sc : getService.getDefaultSysCharacterList()){
				itemList.addAll(getPlayerItems(playerId, type, sc.getId(), 0));
			}
		}
		PlayerItem playerItem=null;
		//get playerItem
			for(PlayerItem item:itemList){
				if(item.getId()==itemId){
					playerItem=item;
					break;
				}
			}
		return playerItem;
	}
	/**
	 * 
	 * @param playerId
	 * @param type
	 * @param itemId ---> sysItemId
	 * @return
	 * @throws Exception
	 */
	public List<PlayerItem> getPlayerItemListBySysItemId(Integer playerId, int type,int itemId)throws Exception{
		List<PlayerItem> itemList = new ArrayList<PlayerItem>();
		if (type != Constants.DEFAULT_WEAPON_TYPE && type != Constants.DEFAULT_COSTUME_TYPE && type != Constants.DEFAULT_PART_TYPE) {
			itemList = getPlayerItems(playerId, type, 0, 0);
		} else {
			for(SysCharacter sc : getService.getDefaultSysCharacterList()){
				itemList.addAll(getPlayerItems(playerId, type, sc.getId(), 0));
			}
		}
		
		List<PlayerItem> allItems=new ArrayList<PlayerItem>();
		for(PlayerItem item:itemList){
			if(item.getItemId()==itemId){
				allItems.add(item);
			}
		}
		return allItems;
	}
	/**
	 * 根据iid获得playeritem List
	 * @param playerId
	 * @param type
	 * @param iid
	 * @return
	 * @throws Exception
	 */
	public List<PlayerItem> getPlayerItemByItemIid(Integer playerId, int type,int iid) throws Exception{
		List<PlayerItem> itemList = new ArrayList<PlayerItem>();
		List<PlayerItem> retList = new ArrayList<PlayerItem>();
		if (type != Constants.DEFAULT_WEAPON_TYPE && type != Constants.DEFAULT_COSTUME_TYPE && type != Constants.DEFAULT_PART_TYPE) {
			itemList = getPlayerItems(playerId, type, 0, 0);
		} else {
			for(SysCharacter sc : getService.getDefaultSysCharacterList()){
				itemList.addAll(getPlayerItems(playerId, type, sc.getId(), 0));
			}
		}
		for(PlayerItem item:itemList){
			SysItem si = item.getSysItem();
			if(si.getIId()==iid){
				retList.add(item);
			}
		}
		return retList;
	}
	
	/**
	 * 根据物品iid获取玩家物品数量
	 * @param playerId
	 * @param type
	 * @param iid
	 * @return
	 * @throws Exception
	 */
	public int getPlayerItemsTotalQuantityByIid(Integer playerId, int type,int iid) throws Exception{
		List<PlayerItem> itemList = getPlayerItemByItemIid(playerId,type,iid);
		int totalQuantity = 0;
		for(PlayerItem pi : itemList){
			totalQuantity += pi.getQuantity();
		}
		return totalQuantity;
	}
	
	/**
	 * method to get playeritem list by player id
	 * @param userId
	 * @param playerId
	 * @param type
	 * @param itemId
	 * @return
	 * @throws Exception
	 */
	public List<PlayerItem> getPlayerItemListById(Integer playerId, int type)throws Exception{
//		List<PlayerItem>[][] itemArray=new ArrayList[Constants.TYPE_NUM][];
//		//1.get itemArray
//		itemArray=getPlayerItems_O(userId, playerId, type);
//		//2.get pages
//		List<PlayerItem> pageArray[] = itemArray[type - 1];
//		int pages = 0;
		List<PlayerItem> playerItemList=new ArrayList<PlayerItem>();
//		if (pageArray != null) {
//			pages = pageArray.length;
//		}
//		//3.get playerItem
//		for(int i=0;i<pages;i++){
//			List<PlayerItem> itemList=pageArray[i];
//			for(PlayerItem item:itemList){
//				playerItemList.add(item);
//			}
//		}
		return playerItemList;
	}
	private Set<String> getWeaponNameList(Integer userId,Integer playerId,int characterId)throws Exception{
		Set<String> playerWeaponNameList=new HashSet<String>();
		//适用于第一个背包的武器
		List<PlayerItem> pack=getWeaponPackList(playerId, Constants.NUM_ONE,characterId);
		for(PlayerItem pi:pack){
			playerWeaponNameList.add(pi.getItemName());
		}
		return playerWeaponNameList;
	}
	/*public List<PlayerItem> getReequipWeaponList(Integer playerId,Integer type, int itemId) throws Exception{
		List<PlayerItem> returnList=new ArrayList<PlayerItem>();
		List<PlayerItem>[][] itemArray=new ArrayList[Constants.TYPE_NUM][];
		//1.get itemArray
		itemArray=getPlayerItems_O(playerId, type);
		List<PlayerItem> pageArray[] = itemArray[type - 1];
		PlayerItem playerItem=null;
		int pages = 0;
		if (pageArray != null) {
			pages = pageArray.length;
		}
		List<PlayerItem> tmpList=new ArrayList<PlayerItem>();
		//3.get playerItem
		for(int i=0;i<pages;i++){
			List<PlayerItem> itemList=pageArray[i];
			for(PlayerItem item:itemList){
				SysItem si=sysItemDao.getSystemItemById(item.getItemId());
				if(type==Constants.DEFAULT_WEAPON_TYPE){
					if(item.getId()==itemId){
						playerItem=item;
					}else if(CommonUtil.getWeaponSeq(si.getWId())==1||CommonUtil.getWeaponSeq(si.getWId())==2){
						//添加主武器和副武器
						tmpList.add(item);
					}
				}
			}
		}
		if(playerItem!=null){
			returnList.add(playerItem);
		}
		returnList.addAll(tmpList);
		return returnList;
	}*/
	//not use
	/**
	 * method to get item List via pack seq  
	 * @param userId
	 * @param playerId
	 * @param type:pack type
	 * @param packId
	 * @param seq
	 * @return List<PlayerItem> and the packed item first
	 * @throws Exception
	 */
	/*public List<PlayerItem> getItemPackList(Integer playerId,Integer type,int packId,int seq)throws Exception{
		List<PlayerItem> returnList=new ArrayList<PlayerItem>();
		List<PlayerItem>[][] itemArray=new ArrayList[Constants.TYPE_NUM][];
		List<PlayerItem>[][] setArray=new ArrayList[Constants.TYPE_NUM][];
		//1.get itemArray
		itemArray=getPlayerItems_O(playerId, type);
		Player player=mcc.get(CacheUtil.oPlayer(playerId),Constants.CACHE_TIMEOUT);
		if(player==null){
			player=getSimplePlayerById(playerId);
		}
		List<PlayerItem> pageArray[] = itemArray[type - 1];
		PlayerItem playerItem=null;
		int pages = 0;
		if (pageArray != null) {
			pages = pageArray.length;
		}
		List<PlayerItem> tmpList=new ArrayList<PlayerItem>();
		//3.get playerItem
		for(int i=0;i<pages;i++){
			List<PlayerItem> itemList=pageArray[i];
			for(PlayerItem item:itemList){
				SysItem si=sysItemDao.getSystemItemById(item.getItemId());
				if(type==Constants.DEFAULT_WEAPON_TYPE){
					if(CommonUtil.getWeaponSeq(si.getWId())==seq&&item.getPack()==packId){
						playerItem=item;
					}
					if(CommonUtil.getWeaponSeq(si.getWId())==seq&&item.getPack()==Constants.NUM_ZERO&&si.getLevel()<=player.getRank()){
						tmpList.add(item);
					}
				}else {
//					if(CommonUtil.getCotumeSeq(item.getSysItem().getSubType())==seq&&item.getSysItem().getCSide()==packId&&item.getPack()==Constants.NUM_ONE){
//						playerItem=item;
//					}
//					else if(item.getSeq()==seq&&item.getSysItem().getSubType()==Constants.DEFAULT_COSTUME_SET_SUBTYPE&&item.getSysItem().getCSide()==packId&&item.getPack()==Constants.NUM_ONE){
//						playerItem=item;
//					}
//					if(CommonUtil.getCotumeSeq(item.getSysItem().getSubType())==seq&&item.getSysItem().getCSide()==packId&&item.getPack()==Constants.NUM_ZERO&&item.getSysItem().getLevel()<=player.getRank()){
//						tmpList.add(item);
//					}else if(item.getSysItem().getSubType()==Constants.DEFAULT_COSTUME_SET_SUBTYPE&&item.getSysItem().getCSide()==packId&&item.getPack()==Constants.NUM_ZERO&&item.getSysItem().getLevel()<=player.getRank()){
//						tmpList.add(item);
//					}
				}
				
			}
		}
		if(playerItem!=null){
			returnList.add(playerItem);
		}
//		if(returnList.size()==0){
//			returnList.addAll(tmpList.subList(0, Constants.DEFAULT_PAGE_SIZE));
//		}else{
//			returnList.addAll(tmpList.subList(0, Constants.DEFAULT_PAGE_SIZE-1));
//		}
		returnList.addAll(tmpList);
		return returnList;
	}*/
	//get data from database
	public PlayerItem getPlayerItemById(Integer playerId, int itemId)throws Exception{
		Map<Integer, SysItem> sysItemMap = sysItemDao.getAllSysItemMap();				
		List<PlayerItem> playerItems = playerItemDao.getPlayerItemList(playerId);
		for(PlayerItem pi : playerItems){
			if(itemId == pi.getId()){
				SysItem si=sysItemMap.get(pi.getItemId());
				pi.setSysItem(si);
				pi.initWithoutPart(si);
				return pi;
			}
		}
		
		return null;		
	}
	/**
	 * 根据playerId和sysItemId获得playItem的list
	 * @param playerId
	 * @param sysItemId
	 * @return
	 * @throws Exception
	 */
	public List<PlayerItem> getPlayerItemsBySysId(Integer playerId, int sysItemId)throws Exception{
		Map<Integer, SysItem> sysItemMap = sysItemDao.getAllSysItemMap();				
		List<PlayerItem> playerItems = playerItemDao.getPlayerItemList(playerId);
		List<PlayerItem> retList = new ArrayList<PlayerItem>();
		for(PlayerItem pi : playerItems){
			if(sysItemId == pi.getItemId()){
				SysItem si=sysItemMap.get(pi.getItemId());
				pi.setSysItem(si);
				pi.initWithoutPart(si);
				retList.add(pi);
			}
		}
		
		return retList;		
	}
	/**
	 * 从cache 中获取playerItem
	 * @param playerId
	 * @param sysItemId
	 * @return
	 * @throws Exception
	 */
	public List<PlayerItem> getPlayerItemsBySysId(int playerId, int sysItemId)throws Exception{
		int type = getSysItemByItemId(sysItemId).getType();
		List<PlayerItem> itemList = getPlayerItemList1(playerId, type, 0, 0);
		
		List<PlayerItem> retList = new ArrayList<PlayerItem>();
		for(PlayerItem pi : itemList){
			if(sysItemId == pi.getItemId()){
				retList.add(pi);
			}
		}
		return retList;		
	}
	/**
	 * 从cache 中获取playerItem
	 * @param playerId
	 * @param type
	 * @param sysItemId
	 * @return
	 * @throws Exception
	 */
	public List<PlayerItem> getPlayerItemsByTypeAndSysId(int playerId,int type ,int sysItemId)throws Exception{
		List<PlayerItem> itemList = getPlayerItemList1(playerId, type, 0, 0);
		
		List<PlayerItem> retList = new ArrayList<PlayerItem>();
		for(PlayerItem pi : itemList){
			if(sysItemId == pi.getItemId()){
				retList.add(pi);
			}
		}
		return retList;		
	}
	/**
	 * 根据玩家id和物品的id获得该玩家最大数量的playerItem
	 * @param playerId
	 * @param sysItemId
	 * @return PlayerItem
	 * @throws Exception
	 */
	public PlayerItem getMaxQuantityPlayerItemById(Integer playerId, int sysItemId) throws Exception{
		List<PlayerItem> piList = getPlayerItemsBySysId(playerId,sysItemId);
		PlayerItem retPi = piList.size()>0?piList.get(0):null;
		int maxQuantity = retPi==null?0:retPi.getQuantity();
		for(int i =1;i<piList.size(); i++){
			PlayerItem pi = piList.get(i);
			if(pi.getQuantity()>maxQuantity ){
				maxQuantity = pi.getQuantity();
				retPi= pi;
			}
		}
		return retPi;
	}
	/**
	 * 获得玩家的vip宝箱
	 * @param playerId
	 * @param sysItemId
	 * @return
	 */
	public PlayerItem getPlayerVipSafeCabinetById(Integer playerId, int sysItemId){
		Map<Integer, SysItem> sysItemMap = sysItemDao.getAllSysItemMap();
		List<PlayerItem> playerItems = playerItemDao.getPlayerItemList(playerId);
		for(PlayerItem pi : playerItems){
			if(sysItemId == pi.getItemId()){
				SysItem si=sysItemMap.get(pi.getItemId());
				pi.setSysItem(si);
				pi.initWithoutPart(si);
				return pi;
			}
		}
		return null;
	}
//	public PlayerItem getPlayerItemById(int playerItemId)throws Exception{
//		return playerItemDao.getPlayerItemById(playerItemId);
//		
//	}
	//===============================================================================	
	//								  Player Pack Services
	//===============================================================================
	/**
	 * get all equipt weapons 
	 * 
	 * @return key:packNo value:weapons in that pack. if this pack is empty, then the list is empty
	 */
	
//	public Map<Integer,List<PlayerItem>> getWeaponsInAllPacks(final int playerId) throws Exception{ 
//		String key = "keys";
//		long start=System.currentTimeMillis();
//		@SuppressWarnings("unchecked")
//		Map<Integer,List<PlayerItem>> result = (Map<Integer,List<PlayerItem>>) loadValue(key, new CacheMissHandler() {
//			@Override
//			public Object loadFromDB(GetService service) throws  InterruptedException, Exception {
//				Map<Integer,List<PlayerItem>> weaponsInAllPacks =  playerPackDao.getAllWeaponPacks(playerId);
//				long start=System.currentTimeMillis();
//				for (Map.Entry<Integer, List<PlayerItem>> entry : weaponsInAllPacks.entrySet()) {
//					getPartsForPlayerItem(playerId, entry.getValue());
//				}
//				ServiceLocator.interfaceLogger.debug("getWeaponsInAllPacks==》getPartsForPlayerItem共用时:"+(System.currentTimeMillis()-start)+"ms");
//				mcc.deleteWithNoReply(CacheUtil.oStorage(playerId, Constants.DEFAULT_WEAPON_TYPE));
//				mcc.deleteWithNoReply(CacheUtil.sStorage(playerId, Constants.DEFAULT_WEAPON_TYPE));
//				for (int i=1;i<=Constants.MAX_WEAPON_PACK_SIZE;i++){
//					mcc.deleteWithNoReply(CacheUtil.sWeaponPack(playerId, i));
//				}
//				return weaponsInAllPacks;
//			}
//		});
//		ServiceLocator.interfaceLogger.debug("getWeaponsInAllPacks共用时:"+(System.currentTimeMillis()-start)+"ms");
//		return result;
//	}
	protected boolean isFitCharacter(SysItem si,int characterId) {
		boolean isFit=false;
		for(String str:si.getCharacterIds()){
			if(StringUtil.toInt(str)==characterId){
				isFit=true;
				break;
			}
		}
		return isFit;
	}
	/**
	 * 根据背包号，获取该背包下所有武器
	 * @param playerId
	 * @param packId
	 * @return  包含PLAYER ITEM的LIST
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PlayerItem> getWeaponPackList(final int playerId, final int packId,final int characterId) throws Exception {
		String objKey=CacheUtil.oWeaponPack(playerId, packId,characterId);
		List<PlayerItem> packList = (List<PlayerItem>) loadValue(objKey, new CacheMissHandler() {
			@Override
			public Object loadFromDB(GetService service) throws Exception {

				try{
					//所有未删除的武器道具
					List<PlayerItem> playerItemList = getPlayerItem_s1(playerId, Constants.DEFAULT_WEAPON_TYPE, characterId, 0);
					//所有有效的背包条
					List<PlayerPack> playerPackList = playerPackDao.getWeaponPackByPackId(playerId, characterId);
					List<PlayerItem> packList = new ArrayList<PlayerItem>();
					for (PlayerPack pp : playerPackList) {
						if (pp.getPlayerItemId() != null) {
							PlayerItem item=null;
							for(PlayerItem pi:playerItemList){
								if(null != pi && ((Integer)pi.getId()).equals(pp.getPlayerItemId())&&pi.getPack()==1){
									if(pi.getPlayerItemUnitType()==2 && pi.getExpireSecondsLeft()>0){
										pi.calculateTimeLeft();
									}
									item=pi;
									packList.add(pi);
									break;
								}
							}
							if (item == null) {
								boolean normal=false;
								for (PlayerItem pi : playerItemList) {
									SysItem si = sysItemDao.getSystemItemById(pi.getItemId());
									if (pi.getIsDefault().equals("Y") && CommonUtil.getWeaponSeq(si.getWId()) == pp.getSeq()&&isFitCharacter(si, characterId)) {
										log.warn("find the root cause of weaponlist,seq ="+pp.getSeq()+" itemId="+pi.getId()+"playerId="+pp.getPlayerId());
										packList.add(pi);
										pp.setPlayerItemId(pi.getId());
										playerPackDao.updateMappingBeanInCache(pp, pp.getPlayerId());
										mcc.delete(CacheUtil.oWeaponPack(playerId, Constants.NUM_ONE,characterId));
										ServiceLocator.deleteService.deletePlayerItemInMemcached(playerId, si);
										mcc.delete(CacheUtil.oCharacterList(playerId));
										normal=true;
										break;
									}
								}
								if(!normal&&pp.getSeq()==4){
									packList.add(null);
									normal=true;
								}
								if(!normal){
									log.error("can not find default weapon playerId="+playerId+" characterId="+characterId+" seq="+pp.getSeq());
								}
							}
						}else{
							packList.add(null);
						}
					}
					return packList;
				}catch(Exception e){
					log.warn("exception in getWeaponPackList, playerId=" + playerId, e);
					throw e;
				}
			}
		});
		if(packList.size()!=4&&characterId<10&&characterId!=0){
			log.warn("WeaponPack size="+packList.size()+" playerId="+playerId+" characterId="+characterId+".start to reset the character pack");
			packList=new ArrayList<PlayerItem>();
			mcc.delete(CacheUtil.oWeaponPack(playerId, packId, characterId));
			mcc.delete(CacheUtil.oCharacterList(playerId));
			List<PlayerItem> playerItemList = getPlayerItemList1(playerId, Constants.DEFAULT_WEAPON_TYPE, characterId, 0);
			List<PlayerPack> playerPackList = playerPackDao.getWeaponPackByPackId(playerId, characterId);
			for(PlayerPack pp : playerPackList){
				if(pp.getPlayerItemId() != null){//have item
					PlayerItem item=null;
					for(PlayerItem pi:playerItemList){
						SysItem si=sysItemDao.getSystemItemById(pi.getItemId());
						if(pi.getIsDefault().equals("Y")&&CommonUtil.getWeaponSeq(si.getWId())==pp.getSeq()){
							packList.add(pi);
							break;
						}
					}
					if(item==null){//equip item miss
						log.warn("find the root cause of weaponlist size,seq ="+pp.getSeq()+" itemId="+pp.getPlayerItemId()+"playerId="+pp.getPlayerId());
						for(PlayerItem pi:playerItemList){
							SysItem si=sysItemDao.getSystemItemById(pi.getItemId());
							if(pi.getIsDefault().equals("Y")&&CommonUtil.getWeaponSeq(si.getWId())==pp.getSeq()&&isFitCharacter(si, characterId)){
								packList.add(pi);
								ServiceLocator.updateService.updateWeaponPackEquipment(playerId, Constants.DEFAULT_WEAPON_TYPE, pi.getId(), characterId);
								break;
							}
						}
					}
				}else if(pp.getSeq()==4&&characterId!=4){//other character fourth item
					packList.add(null);
				}else{//leader fourth item and other character's item
					for(PlayerItem pi:playerItemList){
						SysItem si=sysItemDao.getSystemItemById(pi.getItemId());
						if(pi.getIsDefault().equals("Y")&&CommonUtil.getWeaponSeq(si.getWId())==pp.getSeq()&&isFitCharacter(si, characterId)){
							packList.add(pi);
							ServiceLocator.updateService.updateWeaponPackEquipment(playerId, Constants.DEFAULT_WEAPON_TYPE, pi.getId(), characterId);
							break;
						}
					}
				}
			}
			
		}
		return packList;
	}
	@SuppressWarnings("unchecked")
	public List<PlayerItem> getDefaultPackList(final int playerId,String packType, int characterId) throws Exception {
		
		//所有有效的背包条
		List<PlayerItem> packList = new ArrayList<PlayerItem>();
		if(packType.equals(Constants.PACK_TYPE_W)){
			//所有未删除的武器道具
			List<PlayerItem> playerItemList = getPlayerItems(playerId, Constants.DEFAULT_WEAPON_TYPE, characterId, 0);
			int i=0;
			for(PlayerItem pi:playerItemList){
				SysItem si=sysItemDao.getSystemItemById(pi.getItemId());
				if(i<4&&"Y".equals(pi.getIsDefault())&&CommonUtil.getWeaponSeq(si.getWId())==(i+1)&&isFitCharacter(si, characterId)){
					packList.add(pi);
					i++;
				}
				if(i>3){
					i=0;
					break;
				}
			}
		}else {
			List<PlayerItem> playerItemList = getPlayerItems(playerId, Constants.DEFAULT_COSTUME_TYPE, characterId, 0);
			List<PlayerItem> playerItemList2 = getPlayerItems(playerId, Constants.DEFAULT_PART_TYPE, characterId, 0);
			playerItemList.addAll(playerItemList2);
			int i=0;
			for(PlayerItem pi:playerItemList){
				SysItem si=sysItemDao.getSystemItemById(pi.getItemId());
				if(i<3&&"Y".equals(pi.getIsDefault())&&CommonUtil.getCotumeSeq(si.getCId())==(i+1)&&isFitCharacter(si, characterId)){
					packList.add(pi);
					i++;
				}
				if(i>2){
					i=0;
					break;
				}
			}
		}
		return packList;
	}
	public List<PlayerItem> getCostumePackForGame(final int playerId, final int packId, final int characterId) throws Exception {
		List<PlayerItem> returnList = new ArrayList<PlayerItem>();
		List<PlayerItem> expireList = new ArrayList<PlayerItem>();
		List<PlayerItem> packList = getCostumePackList(playerId, packId, characterId);
		final Player player = getService.getSimplePlayerById(playerId);
		for (PlayerItem pi : packList) {
			if (pi == null) {
				returnList.add(pi);
			} else if ((pi.getPlayerItemUnitType() == 2 && pi.getTimeLeft() <= 0) || pi.getDurable() <= 0) {
				List<PlayerItem> playerItemList = getDefaultPackList(playerId, "C", characterId);
				int seq = pi.getSeq();
				for (PlayerItem p : playerItemList) {
					SysItem si=sysItemDao.getSystemItemById(p.getItemId());
//					si.init();
					p.setSysItem(si);
					if (CommonUtil.getCotumeSeq(si.getCId()) == seq) {
						returnList.add(p);
						if (pi.getPlayerItemUnitType() == 2&& (si.getType() == 2 || si.getType() == 3)
								&&new Date().getTime()>pi.getExpireDate().getTime() && pi.getIsBind().equals(Constants.BOOLEAN_YES)) {
							Date date = pi.getExpireDate();
							Calendar c = Calendar.getInstance();
							c.setTimeInMillis(date.getTime());
							c.add(Calendar.DAY_OF_YEAR, Constants.AUTO_UNEQUIP);
							if (c.getTimeInMillis() <=new Date().getTime()) {
								ServiceLocator.fileLog.debug("player costume pack ,playerID="+ pi.getPlayerId() + " itemId="+ pi.getItemId() + " "+ pi.getItemName()+ "expired more than 3 days");
								ServiceLocator.updateService.updateCostumePackEquipment(playerId, si.getType(), p.getId(), characterId);
							}
						}
						break;
					}
				}
				
				expireList.add(pi);
			} else {
				returnList.add(pi);
			}
		}
		if (expireList.size() > 0) {
			final List<PlayerItem> finalExpireList = expireList;
			Runnable task = new Runnable() {
				@Override
				public void run() {
					try {
						soClient.pushExpiredPackList(player.getName(), finalExpireList);
					} catch (Exception e) {
						log.warn("sendExpiredPackMail in getCostumePackForGame", e);
					}

				}
			};
			ServiceLocator.asynTakService.submit(task);
		}
		return returnList;
	}
	public List<PlayerItem> getWeaponPackForGame(final int playerId, final int packId,final int characterId,boolean isKnife) throws Exception {
		List<PlayerItem> returnList=new ArrayList<PlayerItem>();
		List<PlayerItem> expireList=new ArrayList<PlayerItem>();
		List<PlayerItem> packList=getWeaponPackList(playerId, packId, characterId);
		for(PlayerItem pi:packList){
			SysItem si=null;
			if(pi!=null){
			 si=sysItemDao.getSystemItemById(pi.getItemId());
//			 si.init();
			 pi.setSysItem(si);
			}
			if(pi!=null&&pi.getPlayerItemUnitType()==2&&pi.getTimeLeft()>0){
				pi.calculateTimeLeft();
			}
			if(pi==null){
				returnList.add(pi);
//			}else if((pi.getPlayerItemUnitType()==2&&pi.getTimeLeft()<=0)&&CommonUtil.getWeaponSeq(si.getWId())==4&&characterId!=4){
//				ServiceLocator.deleteService.deletePackEquip(playerId, characterId, Constants.DEFAULT_WEAPON_TYPE, 4);
//				expireList.add(pi);
			}else 
				if((pi.getPlayerItemUnitType()==2&&pi.getTimeLeft()<=0)||pi.getDurable()<=0){
				List<PlayerItem> playerItemList=getDefaultPackList(playerId, "W", characterId);
				int seq=pi.getSeq();
				for(PlayerItem p:playerItemList){
					SysItem sysItem=sysItemDao.getSystemItemById(p.getItemId());
					if(CommonUtil.getWeaponSeq(sysItem.getWId())==seq){
						if(!isKnife){
							returnList.add(p);
						}else if(isKnife&&seq==3){
							returnList.add(p);
						}
						if (pi.getPlayerItemUnitType() == 2&& (si.getType() == 1)&&new Date().getTime()>pi.getExpireDate().getTime()) {
							Date date = pi.getExpireDate();
							Calendar c = Calendar.getInstance();
							c.setTimeInMillis(date.getTime());
							c.add(Calendar.DAY_OF_YEAR, Constants.AUTO_UNEQUIP);
							if (c.getTimeInMillis() <= new Date().getTime()) {
								ServiceLocator.fileLog.debug("player weapon pack ,playerID="+ pi.getPlayerId() + " itemId="+ pi.getItemId() + " "+ pi.getItemName()+ "expired more than 3 days");
								ServiceLocator.updateService.updateWeaponPackEquipment(playerId, Constants.DEFAULT_WEAPON_TYPE, p.getId(), characterId);
							}
						}
						break;
					}
				}
				expireList.add(pi);
			}else {
				if(!isKnife){
					returnList.add(pi);
				}else if(isKnife&&pi.getSeq()==3){
					returnList.add(pi);
				}
			}
		}
		if(expireList.size()>0){
			final Player player=getService.getSimplePlayerById(playerId);
			final List<PlayerItem> finalExpireList=expireList;
			Runnable task = new Runnable(){

				@Override
				public void run() {
					try {
						soClient.pushExpiredPackList(player.getName(),finalExpireList);
					} catch (Exception e) {
						log.warn("sendExpiredPackMail in getCostumePackForGame",e);
					}
				}};
				ServiceLocator.asynTakService.submit(task);
		}
		return returnList;
	}
	/**
	 * method to get costume pack
	 * @param playerId
	 * @param packId
	 * @param side
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PlayerItem> getCostumePackList(final int playerId, final int packId,final int characterId) throws Exception {
		String objKey=CacheUtil.oCostumePack(playerId, packId, characterId);
		List<PlayerItem> packList = (List<PlayerItem>) loadValue(objKey, new CacheMissHandler() {
			@Override
			public Object loadFromDB(GetService service) throws Exception {
				List<PlayerItem> playerItemList = getPlayerItem_s1(playerId, Constants.DEFAULT_COSTUME_TYPE, characterId, 0);
				List<PlayerItem> playerItemList2 = getPlayerItem_s1(playerId, Constants.DEFAULT_PART_TYPE, characterId, 0);
				List<PlayerPack> playerPackList = playerPackDao.getCostumePackByPackId(playerId, characterId);
				List<PlayerItem> packList = new ArrayList<PlayerItem>();
				for(PlayerPack pp : playerPackList){
					if(pp.getPlayerItemId() != null){
						PlayerItem item=null;
						for(PlayerItem pi:playerItemList){
							if(null != pi &&((Integer)pi.getId()).equals(pp.getPlayerItemId())&&pi.getPack()==1){
								if(null != pi && pi.getPlayerItemUnitType()==2 && pi.getExpireSecondsLeft()>0){
									pi.calculateTimeLeft();
								}
								item=pi;
								packList.add(pi);
								break;
							}
						}
						for(PlayerItem pi:playerItemList2){
							if(null != pi &&((Integer)pi.getId()).equals(pp.getPlayerItemId())&&pi.getPack()==1){
								if(null != pi && pi.getPlayerItemUnitType()==2 && pi.getExpireSecondsLeft()>0){
									pi.calculateTimeLeft();
								}
								item=pi;
								packList.add(pi);
								break;
							}
						}
						
						if (item == null) {
							boolean normal=false;
							for (PlayerItem pi : playerItemList) {
								SysItem si = sysItemDao.getSystemItemById(pi.getItemId());
								if (pi.getIsDefault().equals("Y") && CommonUtil.getCotumeSeq(si.getCId()) == pp.getSeq()&&isFitCharacter(si, characterId)) {
									log.warn("find the root cause of costumelist,seq ="+pp.getSeq()+" itemId="+pi.getId()+"playerId="+pp.getPlayerId());
									packList.add(pi);
									pp.setPlayerItemId(pi.getId());
									playerPackDao.updateMappingBeanInCache(pp, pp.getPlayerId());
									mcc.delete(CacheUtil.oWeaponPack(playerId, Constants.NUM_ONE,characterId));
									ServiceLocator.deleteService.deletePlayerItemInMemcached(playerId, si);
									mcc.delete(CacheUtil.oCharacterList(playerId));
									log.debug("run updateMappingBeanInCache playerItemId=" + pp.getPlayerItemId());
									normal=true;
									break;
								}
							}
							if(!normal){
								for (PlayerItem pi : playerItemList2) {
									SysItem si = sysItemDao.getSystemItemById(pi.getItemId());
									if (pi.getIsDefault().equals("Y") && CommonUtil.getCotumeSeq(si.getCId()) == pp.getSeq()&&isFitCharacter(si, characterId)) {
										packList.add(pi);
										pp.setPlayerItemId(pi.getId());
										playerPackDao.updateMappingBeanInCache(pp, pp.getPlayerId());
										mcc.delete(CacheUtil.oWeaponPack(playerId, Constants.NUM_ONE,characterId));
										ServiceLocator.deleteService.deletePlayerItemInMemcached(playerId, si);
										mcc.delete(CacheUtil.oCharacterList(playerId));
										log.debug("run updateMappingBeanInCache playerItemId=" + pp.getPlayerItemId());
										normal=true;
										break;
									}
								}
							}
							if(!normal){
								log.error("can not find default weapon playerId="+playerId+" characterId="+characterId+" seq="+pp.getSeq());
							}
						}
					}else{
						packList.add(null);
					}
				}
				return packList;
			}
		});
		if(packList.size()!=3&&characterId<10){
			log.warn("CostumePack size="+packList.size()+" playerId="+playerId+".start to reset the character pack");
			packList=new ArrayList<PlayerItem>();
			soClient.puchCMDtoClient(getSimplePlayerById(playerId).getName(), CommonUtil.messageFormat(CommonMsg.WARN_MSG, CommonMsg.WARN_MSG_STR));
			mcc.delete(CacheUtil.oCostumePack(playerId, packId, characterId));
			mcc.delete(CacheUtil.oCharacterList(playerId));
			List<PlayerItem> playerItemList = getPlayerItemList1(playerId, Constants.DEFAULT_COSTUME_TYPE, characterId, 0);
			List<PlayerItem> playerItemList2 = getPlayerItemList1(playerId, Constants.DEFAULT_PART_TYPE, characterId, 0);
			List<PlayerPack> playerPackList = playerPackDao.getCostumePackByPackId(playerId, characterId);
			for(PlayerPack pp : playerPackList){
				if(pp.getPlayerItemId() != null){
					PlayerItem item=null;
					for(PlayerItem pi:playerItemList){
						if(null != pi &&((Integer)pi.getId()).equals(pp.getPlayerItemId())&&pi.getPack()==1){
							if(null != pi && pi.getPlayerItemUnitType()==2 && pi.getExpireSecondsLeft()>0){
								pi.calculateTimeLeft();
							}
							item=pi;
							packList.add(pi);
							break;
						}
					}
					for(PlayerItem pi:playerItemList2){
						if(null != pi &&((Integer)pi.getId()).equals(pp.getPlayerItemId())&&pi.getPack()==1){
							if(null != pi && pi.getPlayerItemUnitType()==2 && pi.getExpireSecondsLeft()>0){
								pi.calculateTimeLeft();
							}
							item=pi;
							packList.add(pi);
							break;
						}
					}
					
					if (item == null) {
						boolean normal=false;
						for (PlayerItem pi : playerItemList) {
							SysItem si = sysItemDao.getSystemItemById(pi.getItemId());
							if (pi.getIsDefault().equals("Y") && CommonUtil.getCotumeSeq(si.getCId()) == pp.getSeq()&&isFitCharacter(si, characterId)) {
								packList.add(pi);
								ServiceLocator.updateService.updateCostumePackEquipment(playerId, Constants.DEFAULT_COSTUME_TYPE, pi.getId(), characterId);
								normal=true;
								break;
							}
						}
						if(!normal){
							for (PlayerItem pi : playerItemList2) {
								SysItem si = sysItemDao.getSystemItemById(pi.getItemId());
								if (pi.getIsDefault().equals("Y") && CommonUtil.getCotumeSeq(si.getCId()) == pp.getSeq()&&isFitCharacter(si, characterId)) {
									packList.add(pi);
									ServiceLocator.updateService.updateCostumePackEquipment(playerId, Constants.DEFAULT_COSTUME_TYPE, pi.getId(), characterId);
									normal=true;
									break;
								}
							}
						}
						if(!normal){
							log.error("can not find default weapon playerId="+playerId+" characterId="+characterId+" seq="+pp.getSeq());
						}
					}
				}
				else{
					for(PlayerItem pi:playerItemList){
						if(pi.getIsDefault().equals("Y")&&CommonUtil.getCotumeSeq(pi.getSysItem().getCId())==pp.getSeq()){
							packList.add(pi);
							ServiceLocator.updateService.updateCostumePackEquipment(playerId, Constants.DEFAULT_COSTUME_TYPE, pi.getId(), characterId);
							break;
						}
					}
					for(PlayerItem pi:playerItemList2){
						if(pi.getIsDefault().equals("Y")&&CommonUtil.getCotumeSeq(pi.getSysItem().getCId())==pp.getSeq()){
							packList.add(pi);
							ServiceLocator.updateService.updateCostumePackEquipment(playerId, Constants.DEFAULT_COSTUME_TYPE, pi.getId(), characterId);
							break;
						}
					}
				}
			}
		}
		return packList;
	}
	//===============================================================================	
	//								  Message Service
	//===============================================================================
	/**
	 * method to get email list by player id
	 * @param userId
	 * @param playerId
	 * @return List<Message>
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Message> getMessageListFromInbox(final Integer userId,final int playerId)throws Exception{
		String objKey=CacheUtil.oPlayerMessage(playerId);
		List<Message> list=(List<Message>) loadValue(objKey, new CacheMissHandler() {
			@Override
			public Object loadFromDB(GetService service) {
				List<Message> list = messageDao.getMessageListFromInBox(userId,playerId);
				List<Message> messageList=new ArrayList<Message>();
				int i=1;
				for(Message message:list){
					message.init();
					messageList.add(message);
					i++;
					if(i>50){
						break;
					}
				}
				return messageList;
			}
		});
		Collections.sort(list, new Comparator<Message>(){

			@Override
			public int compare(Message o1, Message o2) {
				// TODO Auto-generated method stub
				Integer o11="Y".equals(o1.getOpen())?1:0;
				Integer o22="Y".equals(o2.getOpen())?1:0;
				return o11.compareTo(o22);
			}
			
		});
		return list;
	}

	public int getNewMessageNum(final int playerId)throws Exception{
		int returnValue=0;
		List<Message> list=getMessageListFromInbox(playerId, playerId);
		for(Message message:list){
			if(Constants.BOOLEAN_NO.equals(message.getOpen())){
				returnValue+=1;
			}
		}
		return returnValue;
	}
	
	public Message getMessageFromInbox( int playerId,int messageId)throws Exception{
		List<Message> list=getMessageListFromInbox(playerId, playerId);
		Message message = new Message();
		for(Message m:list){
			if(m.getId()==messageId){
				message=m;
				break;
			}
		}
		if ("Y".equals(message.getHaveAttached())) {
			PlayerItem playerItem =null;
			if(message.getSenderId()!=0){
//			Player p=getSimplePlayerById(message.getSenderId());
				playerItem = getPlayerItemById(message.getSenderId(), message.getPlayerItemId());
			}else{
				playerItem = getPlayerItemById(playerId, message.getPlayerItemId());
			}
			if (playerItem != null) {
				message.setPlayerItem(playerItem);
			}
		}
		if ("N".equals(message.getOpen())) {
			messageDao.updateMessageOpen(messageId);
		}
		mcc.delete(CacheUtil.oPlayerMessage(playerId));
		mcc.delete(CacheUtil.sPlayerMessage(playerId));
		return message;
	}
	public Message getMessageFromOutBox(Integer userId, int playerId,int messageId) throws Exception{
		Message message = messageDao.getMessageFromOutBoxById(userId, playerId, messageId);
		if (message != null){
			message.init();
		}
		return message;
	}
	
	public List<Message> getMessageFromOutBoxBySenderName(String senderName, int id)throws Exception{
		return messageDao.getMessageFromOutBoxBySenderName(senderName,id);
	}
	//===============================================================================	
	//								  Level Info
	//===============================================================================	
	
	@SuppressWarnings("unchecked")
	public List<LevelInfo> getLevelListUseCache() throws Exception{
		String key = CacheUtil.oLevelList();
		List<LevelInfo> list=(List<LevelInfo>) loadValue(key, new CacheMissHandler() {
			@Override
			public Object loadFromDB(GetService service) {
				return sysLevelDao.getAllLevelsList();
			}
		});
		return list;
	}
	
	public LevelModeInfo getLevelModeInfo(int levelId) throws Exception{
		LevelInfo pojo = getLevelInfo(levelId);	
		if (pojo == null) {
			return null;
		}
		return new LevelModeInfo(pojo);
	}
	
	//仅用于加载地图，传入的id可能是teamlevel ID的情况
	public LevelModeInfo getLevelModeInfoIncludeTeamLevel(int levelId)throws Exception{
		LevelInfo pojo = getLevelInfo(levelId);	
		if (pojo == null) {
			// chek it's teamlevel or not
			TeamLevelInfo teamLevelInfo=getTeamLevelInfo(levelId);
			if(teamLevelInfo!=null){
				pojo=getLevelInfo(teamLevelInfo.getRefLevelId());
				if(pojo!=null){
					pojo.setId(levelId);
					pojo.setType(Constants.GAMETYPE.TEAMZYZDZ.getValue());
					return new LevelModeInfo(pojo);
				}
			}
			return null;
		}
		return new LevelModeInfo(pojo);
	}
	
	public LevelInfo getLevelInfo(int levelId)throws Exception{
		LevelInfo pojo = null;
		List<LevelInfo> list=getLevelListUseCache();
		for(LevelInfo p:list){
			if(p.getId()==levelId){
				pojo=p;
				break;
			}
		}
		return pojo;
	}
	public List<LevelWeapon> getLevelWeaponsByLevelId(int levelId){
		return sysLevelDao.getLevelWeaponByLevelId(levelId);
	}
	//use By GM mxml
	public List<LevelWeapon> getLevelWeapons(int levelId){
		return sysLevelDao.getLevelWeaponByLevelId(levelId);
	}
	
	//===============================================================================	
	//		TeamLevel Info
	//===============================================================================

	
	public TeamLevelInfo getTeamLevelInfo(int teamLevelId)throws Exception{
		TeamLevelInfo teamLevelInfo=teamLevelDao.getTeamLevelById(teamLevelId);
		return teamLevelInfo;
	}
	
	public TeamLevelInfo getTeamLevelInfo(final int teamId,final int levelId) throws Exception{
		String key = CacheUtil.oTeamLevelByTL(teamId,levelId);
		TeamLevelInfo teamLevelInfo=null;
		
		@SuppressWarnings("unchecked")
		TeamLevelInfo list=(TeamLevelInfo)loadValue(key, new CacheMissHandler() {
			@Override
			public Object loadFromDB(final GetService service) {
				ArrayList<TeamLevelInfo> result=new ArrayList<TeamLevelInfo>(teamLevelDao.getTeamLevelByTL(teamId, levelId).values());
				if(result.isEmpty()){
					return null;
				}else{
					return result.get(0);
				}
			}
			
		});
		
		teamLevelInfo=list;
//		List<TeamLevelInfo> teamLevelInfos= new ArrayList<TeamLevelInfo>(teamLevelDao.getTeamLevelByTL(teamId, levelId).values());
//		if(teamLevelInfos.size()>0){
//			teamLevelInfo=teamLevelInfos.get(0);
//		}
		return teamLevelInfo;
	}

	public TeamLevelModeInfo getTeamLevelModeInfo(int teamLevelId)throws Exception{
		TeamLevelInfo pojo = getTeamLevelInfo(teamLevelId);	
		if (pojo == null) {
			return null;
		}
		return new TeamLevelModeInfo(pojo);
	}
	
	//===============================================================================	
	//								 Nosql Service
	//===============================================================================
	
	
	/**
	 * used for player top in nosql.  player top in nosql are updated per week,
	 * so keep its length in memcache
	 */
	public long getTopLength(final String nosqlListKey) throws Exception{
//		long result =  (Long) loadValue(nosqlListKey, new CacheMissHandler() {
//			@Override
//			public Object loadFromDB(GetService service) {
//				Long result = 0L;
//				try {
//					result = 
//				} catch (Exception e) {
//					log.error("Error happend during get length of list in nosql, key is " + nosqlListKey);
//				}
//				return result;
//			}
//		});
		return nosqlService.getNosql().llen(nosqlListKey);
	}
	
	
	@SuppressWarnings("unchecked")
	public Map<String,String> getSysConfig(){
		String key = CacheUtil.oSysConfigMap();
		Map<String, String> result = new HashMap<String, String>();
		try{
			result = (Map<String, String>) loadValue(key, new CacheMissHandler() {
				@Override
				public Object loadFromDB(GetService service) {
					Map<String, String> list=(Map<String, String>)systemDao.getSysConfig();
					return list;
				}
			}
			);
		} catch(Exception e){
			log.error("happen in getSysConfig",e);
		}
		return result;
	}
	
	//for mxml
	@SuppressWarnings("unchecked")
	public List<SysConfiguration> getSysConfigList() throws Exception{
		List<SysConfiguration> list=(List<SysConfiguration>)systemDao.getSysConfigList();
		return list;
	}
	public String[] getClientVersion(){
		String allVersionStr = getSysConfig().get(Constants.CFG_CLIENT_VERSION).trim();
		//TODO
		return allVersionStr.split(";"); 
	}
	
	
	public MessageService getMessageService() {
		return messageService;
	}
	public void setMessageService(MessageService messageService) {
		this.messageService = messageService;
	}
	public CharacterData getCharacterData(int playerId,int characterId) throws Exception {
		return characterDataDao.getCharacterDataByPlayerId(playerId, characterId);
	}
	public List<CharacterData> getCharacterDataService(int playerId) throws Exception {
		List<CharacterData> result = characterDataDao.getCharacterDataByPlayerId(playerId);
		List<CharacterData> returnList = new ArrayList<CharacterData>();
		
		for(CharacterData data : result){
			if(data.getPlayerId() == playerId){
				returnList.add(data);
			}
		}
		Collections.sort(returnList, new Comparator<CharacterData>() {
			@Override
			public int compare(CharacterData o1, CharacterData o2) {
				return (o1.getCharacterId() - o2.getCharacterId());
			}
		});
		return returnList;
	}
	//根据使用次数排序后的 characterData
	public List<CharacterData> getCharacterDataSortByUsedNums(final int playerId) throws Exception{
		List<CharacterData> result = characterDataDao.getCharacterDataByPlayerId(playerId);
		List<CharacterData> returnList = new ArrayList<CharacterData>();
		
		for(CharacterData data : result){
			if(data.getPlayerId() == playerId && data.getCharacterId()<8){
				returnList.add(data);
			}
		}
		//降序排列
		Collections.sort(returnList, new Comparator<CharacterData>() {
			@Override
			public int compare(CharacterData o1, CharacterData o2) {
				final int result= -o1.getUsedCount() - (-o2.getUsedCount());
				if(result==0){
					//升序排列
					return o1.getCharacterId()-o2.getCharacterId();
				}
				
				return result;
			}
		});
		
		return returnList;
		
	}
	
	public List<SysIcon> getSysIcon() throws Exception {
		//按vip等级 降序排列
		List<SysIcon> allIcons=sysIconDao.getSysIconList();
		Collections.sort(allIcons, new Comparator<SysIcon>() {
			@Override
			public int compare(SysIcon o1, SysIcon o2) {
				return ((Integer)o2.getLevel()).compareTo((Integer)o1.getLevel());
			}  
		});
		return allIcons;
	}
	
	//===============================================================================	
	//								 BlackIP Service
	//===============================================================================
	@SuppressWarnings("unchecked")
	public List<BlackIP> getBlackIPList() throws Exception{
		String key = CacheUtil.oBlackIpList();
		List<BlackIP> list=(List<BlackIP>) loadValue(key, new CacheMissHandler() {
			@Override
			public Object loadFromDB(GetService service) {
				return blackIPDao.selectBlackIP();
			}
		});
		return list;
	}
	public List<BlackIP> getByIP(final String ip) throws Exception{
		List<BlackIP> retList = blackIPDao.selectByIP(ip);
		for(BlackIP tempIP : retList){
			long tempLong = tempIP.getCreateTime()*1000L;
			Calendar c = Calendar.getInstance();
			long nowLong = c.getTimeInMillis();
			tempLong = (long)tempIP.getBannedTime()*60000L + tempLong;
			if(tempLong < nowLong){
				tempIP.setIsBanned("N");
				blackIPDao.updateBlackIP(tempIP);
				mcc.delete(CacheUtil.oBlackIpList());
			}
		}
		return retList;
	}
	//===============================================================================	
	//								 Achievement Service
	//===============================================================================
	@SuppressWarnings("unchecked")
	public List<SysAchievement> getSysAchievementList() throws Exception {
		return new ArrayList(sysAchievementDao.getAllSysAchievementMap().values());
	}
	
	@SuppressWarnings("unchecked")
	public SysAchievement getSysAchievementById(int saId) throws Exception {
		return sysAchievementDao.getAllSysAchievementMap().get(saId);
	}
	
	@SuppressWarnings("unchecked")
	public Map<Integer, SysAchievement> getSysAchievementMap() throws Exception {
		return sysAchievementDao.getAllSysAchievementMap();
	}
	//zlm2015-10-9-匹配-开始 
	@SuppressWarnings("unchecked")
	public List<SysMatchP> getSysMatchPList() throws Exception {
		return new ArrayList(sysMatchPDao.getAllSysMatchPMap().values());
	}
	
	@SuppressWarnings("unchecked")
	public SysMatchP getSysMatchPById(int saId) throws Exception {
		return sysMatchPDao.getAllSysMatchPMap().get(saId);
	}
	
	@SuppressWarnings("unchecked")
	public Map<Integer, SysMatchP> getSysMatchPMap() throws Exception {
		return sysMatchPDao.getAllSysMatchPMap();
	}
	//zlm2015-10-9-匹配-结束
	
	@SuppressWarnings("unchecked")
	public List<PlayerAchievement> getPlayerAchievementList(final int playerId) throws Exception {
		String key = CacheUtil.oPlayerAchievementList(playerId);
		List<PlayerAchievement> paList = (List<PlayerAchievement>) loadValue(key, new CacheMissHandler() {
			@Override
			public Object loadFromDB(GetService service) throws Exception {
				return playerAchievementDao.getPlayerAchievementList(playerId);
			}
		});
		
		//在此join SysAchievement，避免将SysAchievement存入memcached
		Iterator<PlayerAchievement> paIter = paList.iterator();
		while(paIter.hasNext()){
			PlayerAchievement pa = paIter.next();
			SysAchievement sysAchievement = getSysAchievementById(pa.getAchievementId());
			if(null != sysAchievement){
				pa.setAchievement(sysAchievement);
				if(pa.getStatus() == Constants.NUM_ONE){
					pa.setNumber(sysAchievement.getNumber());
				}
			} else {
				paIter.remove();
			}
		}
		
		Collections.sort(paList,new Comparator<PlayerAchievement>(){
			@Override
			public int compare(PlayerAchievement o1, PlayerAchievement o2) {
				if(o1.getStatus() == o2.getStatus()){
					if(o1.getAchievement().getAction() == 0){
						return 1;
					} else {
						return ((Integer)o1.getAchievement().getId()).compareTo((Integer)o2.getAchievement().getId());
					}
				} else {
					return o2.getStatus().compareTo(o1.getStatus());
				}
			}
		});
		return paList;
	}
	
	public int getVisibleSysAchievementSize() throws Exception{
		Map<Integer, List<SysAchievementBase>> map = getSysAchievementBaseMap();
		int size = 0;
		for (List<SysAchievementBase> list : map.values()) {
			size += list.size();
		}
		return size;
	}
	
	public List<PlayerAchievement> getPlayerAchievementVisible(int playerId) throws Exception {
		List<PlayerAchievement> resultList = new ArrayList<PlayerAchievement>();
		for(PlayerAchievement pa : getPlayerAchievementList(playerId)){
			if(pa.getVisible() == Constants.NUM_ONE){
				resultList.add(pa);
			}
		}
		return resultList;
	}
	
	public List<PlayerAchievement> getAllPlayerAchievementList(int playerId) throws Exception {

		checkAndCreatePlayerAchievement(playerId);
		
		return getPlayerAchievementList(playerId);
	}
	
	public boolean checkAndCreatePlayerAchievement(int playerId) throws Exception{
		Map<Integer, List<SysAchievementBase>> sysAchievementBaseMap = getSysAchievementBaseMap();
		List<Integer> keyList = new ArrayList<Integer>(sysAchievementBaseMap.keySet());
		Collections.sort(keyList);
		//Map<Integer, PlayerAchievement> playerAchievementMap = new HashMap<Integer, PlayerAchievement>();
		Set<Integer> groupSet = new HashSet<Integer>();
		for (PlayerAchievement pa : getPlayerAchievementList(playerId)) {
			//playerAchievementMap.put(pa.getAchievement().getGroup(), pa);
			groupSet.add(pa.getAchievement().getGroup());
		}
		boolean isChanged = false;
		for(Integer key : keyList){
			List<SysAchievementBase> valueList = sysAchievementBaseMap.get(key);
			for(SysAchievementBase sysAchievementBase : valueList){
				if(!groupSet.contains(sysAchievementBase.getGroup())){//如果之前没有，则在数据库中插入
					playerAchievementDao.createPlayerAchievement(playerId, sysAchievementBase);
					isChanged = true;
				}
			}
		}
		if(isChanged){
			mcc.delete(CacheUtil.oPlayerAchievementList(playerId));
		}
		return isChanged;
	}
	public List<PlayerAchievement> getPlayerAchievementByType(int playerId,int type) throws Exception {
		List<PlayerAchievement> result = new ArrayList<PlayerAchievement>();
		List<PlayerAchievement> paList = getPlayerAchievementVisible(playerId);
		
		int paNum = 0, gold = 0, silver = 0, bronze = 0;
		for (PlayerAchievement pa : paList) {
			if (pa.getVisible() == Constants.NUM_ONE) {
				paNum++;
				if (pa.getStatus() == Constants.NUM_ONE) {
					if (pa.getAchievement().getColor() == Constants.NUM_ONE) {
						bronze++;
					} else if (pa.getAchievement().getColor() == Constants.NUM_TWO) {
						silver++;
					} else if (pa.getAchievement().getColor() == Constants.NUM_THREE) {
						gold++;
					}
				}
				if (pa.getAchievement().getType() == type) {
					result.add(pa);
				}
			}
		}		
		Player player = getPlayerById(playerId);
		player.setTotal(paNum);
		player.setBronze(bronze);
		player.setSilver(silver);
		player.setGold(gold);
		ServiceLocator.updateService.updatePlayerInfo(player);
		return result;
	}
	//===============================================================================	
	//								 sys Notice Service
	//===============================================================================
	@SuppressWarnings("unchecked")
	public List<SysNotice> getSysNotice() throws Exception {
		String key = CacheUtil.oSysNoticeList();
		List<SysNotice> result = (List<SysNotice>) loadValue(key, new CacheMissHandler() {
			@Override
			public Object loadFromDB(GetService service) {
				List<SysNotice> sysNoticelList= sysNoticeDao.getSysNoticeList();
				return sysNoticelList;
			}
		}	
		);
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public List<PlayerVO> getPlayerBlackList() throws Exception {
		return (List<PlayerVO>) loadValue(CacheUtil.oBlackPlayerList(),
				new CacheMissHandler() {
					@Override
					public Object loadFromDB(GetService service) {
						return playerDao.selectBlackWhiteList("1");
					}
				});
	}

	@SuppressWarnings("unchecked")
	public List<PlayerVO> getPlayerWhiteList() throws Exception {
		return (List<PlayerVO>) loadValue(CacheUtil.oWhitePlayerList(),
				new CacheMissHandler() {
					@Override
					public Object loadFromDB(GetService service) {
						return playerDao.selectBlackWhiteList("2");
					}
				});
	}

	// ===============================================================================
	// 								Key Word Services
	// ===============================================================================
	@SuppressWarnings("unchecked")
	public List<String> getBannedWordsStrList() throws Exception {
		List<String> result = new ArrayList<String>();
		String key = CacheUtil.oBannedWords();
		List<BannedWord> list = (List<BannedWord>)loadValue(key, new CacheMissHandler(){
			@Override
			public Object loadFromDB(GetService service) throws Exception {
				return bannedWordDao.getAllBannedWrodListIncludeDeleted();
			}
			
		});
		Collections.sort(list,new BannedWord());
		for (BannedWord bw : list) {
			if(bw.getIsDeleted().equals("N")){
				result.add(bw.getBannedWord());
			}
		}
		return result;
	}
	// ===============================================================================
	// 										PayLog
	// ===============================================================================
	public List<PayLog> getPayLogList(Integer id){
		if(id==null)
			return null;
		return payLogDao.getpayloListByPlayerId(id);
	}
	//===============================================================================	
	//								  xunlei order Service
	//===============================================================================
	public XunleiOrderLog getXunleiOrderById(String orderId,String userId) throws Exception{
		return xunleiOrderLogDao.getXunleiOrderLog(orderId,userId);
	}
	
	public List<XunleiOrderLog> getAddXunleiOrderById(int playerId) throws Exception{
		return xunleiOrderLogDao.getAddXunleiOrderLogList(playerId);
	}
	
	public List<XunleiOrderLog> getReduceXunleiOrderById(int playerId) throws Exception{
		return xunleiOrderLogDao.getReduceXunleiOrderLogList(playerId);
	}
	
	public int duplicateXunleiOrder(String orderId,String userId){
		return xunleiOrderLogDao.duplicateXunleiOrder(orderId,userId);
	}
	//===============================================================================	
	//								  sysMission Service
	//===============================================================================
	@SuppressWarnings("unchecked")
	public SysMission getSysMissionById(int sysMissionId) throws Exception{
		Map<Integer, SysMission> map = sysMissionDao.getAllSysMissionMap();
		return map.get(sysMissionId);
	}
	
	@SuppressWarnings("unchecked")
	public List<PlayerMission> getPlayerMissionList(final int playerId) throws Exception{
		String key = CacheUtil.oPlayerMissionList(playerId);
		List<PlayerMission> list = (List<PlayerMission>) loadValue(key,new CacheMissHandler() {
			@Override
			public List<PlayerMission> loadFromDB(GetService service) throws Exception {
				List<PlayerMission> list = playerMissionDao.getPlayerMissionList(playerId);
				return list;
			}
		});
		for(PlayerMission pm : list){
			SysMission sysMission =  getSysMissionById(pm.getSysMissionId());
			pm.setSysMission(sysMission);
		}
		return list;
	}
	
	public List<SysMission> getRandomDailyMission()throws Exception{
		return getRandomMission(0, 4);
//		Map<Integer, SysMission> missions = getSysMissionMap();
//		List<SysMission> result = new ArrayList<SysMission>();
//		for(SysMission mission : missions.values()){
//			if(mission.getType() == 0){
//				result.add(mission);
//			}
//		}
//		return result;
	}
	
	public List<SysMission> getRandomWeekMission()throws Exception{
		List<SysMission> randomMission = getRandomMission(1, 3);//随即部分
		//固定部分 
		Map<Integer, SysMission> map = sysMissionDao.getAllSysMissionMap();
		for(Entry<Integer,SysMission> entry : map.entrySet()){
			SysMission sn =  entry.getValue();
			if(sn.getId()==Constants.ACTION_M.MAP42_MODEL0.getValue()){
				randomMission.add(sn);
			}
		}
		return randomMission;
//		Map<Integer, SysMission> missions = getSysMissionMap();
//		List<SysMission> result = new ArrayList<SysMission>();
//		for(SysMission mission : missions.values()){
//			if(mission.getType() == 1){
//				result.add(mission);
//			}
//		}
//		return result;
	}
	public static final int DAILY_MISSION_SIMPLE_DEFAULT = 11;
	public List<SysMission> getDailyMission()throws Exception{//size:how many sysmission you want to get
		Map<Integer, SysMission> map = sysMissionDao.getAllSysMissionMap();
		List<SysMission> indexList = new ArrayList<SysMission>();
		List<SysMission> indexLista = new ArrayList<SysMission>();	//击杀类任务
		List<SysMission> indexListb = new ArrayList<SysMission>();	//地图类任务
		List<SysMission> indexListc = new ArrayList<SysMission>();	//固定任务
		for(Entry<Integer,SysMission> entry : map.entrySet()){
			SysMission sn =  entry.getValue();
			if(sn.getType()==0){
				if (sn.getId()==Constants.ACTION_M.MAP40_MODEL0.getValue()||sn.getId()==Constants.ACTION_M.MAP41_MODEL0.getValue()) {
					indexListc.add(sn);
				} else 	if(sn.getAction()>=12&&sn.getAction()<=18){//游戏击杀类任务
					indexLista.add(sn);
				}else{
					indexListb.add(sn);//地图类任务
				}
				//VVVVVVV有鸡毛用。。。。
			}else if(sn.getType()==9){
				indexListc.add(sn);
			}
		}	
		Collections.shuffle(indexLista);
		Collections.shuffle(indexListb);
		Collections.shuffle(indexListc);
		indexList.addAll(indexLista.subList(0, 2));
		indexListb.remove(map.get(DAILY_MISSION_SIMPLE_DEFAULT));
		indexList.add(indexListb.get(0));
		Collections.shuffle(indexList);
		indexList.add(0, map.get(DAILY_MISSION_SIMPLE_DEFAULT));
		indexList.addAll(indexListc);
		return indexList;
	}
	public List<SysMission> getRandomMission(int type, int size)throws Exception{//size:how many sysmission you want to get
		Map<Integer, SysMission> map = sysMissionDao.getAllSysMissionMap();
		List<SysMission> indexList = new ArrayList<SysMission>();
		for(Entry<Integer,SysMission> entry : map.entrySet()){
			SysMission sn =  entry.getValue();
			if(sn.getType()==type&&sn.getId()!=Constants.ACTION_M.MAP42_MODEL0.getValue()){
				indexList.add(sn);
			}
		}
		Collections.shuffle(indexList);
		int count = size;
		if(type==0){//daily mission 第一个 指定任务  为 完成1局游戏，奖励同简单任务 ID 11
			indexList.remove(map.get(DAILY_MISSION_SIMPLE_DEFAULT));
			indexList.add(0, map.get(DAILY_MISSION_SIMPLE_DEFAULT));
		}
		return indexList.subList(0, count);
	}
	@SuppressWarnings("unchecked")
	public Date getDailyMissionDate(final int playerId) throws Exception{
		String key = CacheUtil.oDailyMissionDate(playerId);
		Date date = (Date) loadValue(key,new CacheMissHandler() 
			{
				@Override
				public Date loadFromDB(GetService service) throws Exception {
					return nosqlService.getDailyMissionDate(playerId);
				}
			});
		return date;
	}
	
	@SuppressWarnings("unchecked")
	public Date getWeekMissionDate(final int playerId) throws Exception{
		String key = CacheUtil.oWeekMissionDate(playerId);
		Date date = (Date) loadValue(key,new CacheMissHandler() 
			{
				@Override
				public Date loadFromDB(GetService service) throws Exception {
					return nosqlService.getWeekMissionDate(playerId);
				}
			});
		return date;
	}
	
	@SuppressWarnings("unchecked")
	public Date getMonthMissionDate(final int playerId) throws Exception{
		return nosqlService.getMonthMissionDate(playerId);
	}
	
	@SuppressWarnings("unchecked")
	public Map<Integer, List<SysAchievementBase>> getSysAchievementBaseMap() throws Exception{
		String key = CacheUtil.oSysAchievementBaseMap();
		return (Map<Integer, List<SysAchievementBase>>) loadValue(key, new CacheMissHandler(){
			@Override
			public Object loadFromDB(GetService service) throws Exception {
				//归类
				Map<String, SysAchievementBase> map = new HashMap<String, SysAchievementBase>();
				for(Entry<Integer, SysAchievement> entry : sysAchievementDao.getAllSysAchievementMap().entrySet()){
					SysAchievement sa = entry.getValue();
					StringBuilder tempKey = new StringBuilder();
					tempKey.append(sa.getType()).append(":").append(sa.getCharacterId()).append(":").append(sa.getAction()).append(":").append(sa.getStatType());
					if(null == map.get(tempKey.toString())){
						SysAchievementBase base = new SysAchievementBase();
						base.setIds((sa.getId() + ","));
						base.setCharacterId(sa.getCharacterId());
						base.setNumbers((sa.getNumber() + ","));
						base.setType(sa.getType());
						base.setGroup(sa.getGroup());
						base.setTotalLevel(1);
						if(null != sa.getTitle() && !sa.getTitle().equals("")){
							base.setTitle(sa.getTitle());
						}
						map.put(tempKey.toString(), base);
					} else {
						SysAchievementBase base = map.get(tempKey.toString());
						base.setIds((base.getIds() + sa.getId() + ","));
						base.setNumbers((base.getNumbers() + sa.getNumber() + ","));
						base.setTotalLevel((base.getTotalLevel() + 1));
						if(null != sa.getTitle() && !sa.getTitle().equals("")){
							base.setTitle(sa.getTitle());
						}
					}
				}
				
				Map<Integer, List<SysAchievementBase>> resultMap = new HashMap<Integer, List<SysAchievementBase>>();
				for(SysAchievementBase base : map.values()){
					int type = base.getType();
					List<SysAchievementBase> tempList = null;
					if(type == 1){
						tempList = resultMap.get(0);
					} else {
						tempList = resultMap.get(base.getCharacterId());
					}
					if(null == tempList){
						tempList = new ArrayList<SysAchievementBase>();
						if(type == 1){
							resultMap.put(0, tempList);
						} else {
							resultMap.put(base.getCharacterId(), tempList);
						}
					}
					String[] idArray = base.getIds().split(",");
					String[] numberArray = base.getNumbers().split(",");
					for(int i=0; i < numberArray.length; i++){
						for(int j=0; j<numberArray.length-i-1; j++){
							int temp1 = Integer.parseInt(numberArray[j]);
							int temp2 = Integer.parseInt(numberArray[j + 1]);
							if(temp1 > temp2){
								String tempId = idArray[j];
								numberArray[j] = temp2 + "";
								numberArray[j + 1] = temp1 + "";
								idArray[j] = idArray[j + 1];
								idArray[j + 1] = tempId;
							}
						}
					}
					StringBuilder ids = new StringBuilder();
					StringBuilder numbers = new StringBuilder();
					for(int i = 0; i<idArray.length; i++){
						ids.append(idArray[i]).append(",");
						numbers.append(numberArray[i]).append(",");
					}
					base.setIds(ids.toString());
					base.setNumbers(numbers.toString());
					tempList.add(base);
				}
				return resultMap;
			}
		});
	}

	public Map<String, List<SysItem>> getClassifySysItemMap() throws Exception{
		return sysItemDao.getClassifySysItemMap();
	}
	
	@SuppressWarnings("unchecked")
	public List<UnSpeaker> getAllUnSpeakerList() throws Exception{
		String key = CacheUtil.oUnSpeakerList();
		return (List<UnSpeaker>)loadValue(key, new CacheMissHandler(){
			@Override
			public List<UnSpeaker> loadFromDB(GetService service) throws Exception {
				List<UnSpeaker> list = unSpeakerDao.getAllUnSpeaker();
				return list;
			}
			
		});
	}
	
	public List<PlayerItem> getPlayerItemListByItemId(int playerId, int itemId, int type) throws Exception{
		List<PlayerItem> piList = new ArrayList<PlayerItem>();
		
		List<PlayerItem> materialList = getPlayerItem_s1(playerId, type, 0, 0);
		for(PlayerItem each : materialList){
			if(each.getItemId() == itemId){
				piList.add(each);
			}
		}
		
		return piList;
	}
	/**
	 * 通过物品id获得玩家仓库所有相应装备
	 * @param playerId
	 * @param itemId
	 * @return
	 * @throws Exception
	 */
	public List<PlayerItem> getPlayerItemListByItemId(int playerId, int itemId) throws Exception{
		List<PlayerItem> piList = new ArrayList<PlayerItem>();
		int type = getSysItemByItemId(itemId).getType();
		List<PlayerItem> playerItemList = getPlayerItem_s1(playerId, type, 0, 0);
		for(PlayerItem each : playerItemList){
			if(each.getItemId() == itemId){
				piList.add(each);
			}
		}
		return piList;
	}
	
	public Map<Integer,SysChest> getAllSysChestMap()throws Exception{
		return sysChestDao.getSysChestMap();
	}
	
	/**
	 * @param type 1：fixed 2:random
	 * @param level 1,2,3,4, if level = 0 return all the type sysChest
	 * @return
	 * @throws Exception
	 */
	public List<SysChest> getChestList(int type, int level) throws Exception{
		List<SysChest> resultList = new ArrayList<SysChest>();
		Map<Integer, SysChest> map = getAllSysChestMap();
		Map<Integer, SysItem> sysItemMap = sysItemDao.getAllSysItemMap();
		
		for(SysChest sc : map.values()){
			if(sc.getType() == type && (level == Constants.NUM_ZERO || sc.getLevel() == level)){
				sc.setSysItemName(sysItemMap.get(sc.getSysItemId()).getDisplayName());
//				sc.setSysItem(sysItemMap.get(sc.getSysItemId()));
				resultList.add(sc);
			}
		}
		
		Collections.sort(resultList);
		
		return resultList;
	}
	
	public List<SysChest> getChestListNoDeleted(int type ,int level)throws Exception{
		List<SysChest> resultList = new ArrayList<SysChest>();
		Map<Integer, SysChest> map = getAllSysChestMap();
		Map<Integer, SysItem> sysItemMap = sysItemDao.getAllSysItemMap();
		
		for(SysChest sc : map.values()){
			if(sc.getType() == type && (level == Constants.NUM_ZERO || sc.getLevel() == level) && sc.getIsDeleted()==Constants.IS_DELETED_NO){
				SysItem si = sysItemMap.get(sc.getSysItemId());
				sc.setSysItemName(si.getDisplayName());
				sc.setSysItem(si);
				resultList.add(sc);
			}
		}
		return resultList;
	}
	
	public List<SysChest> getChestPriceList(int type)throws Exception{
		List<SysChest> resultList = new ArrayList<SysChest>(Constants.PACKAGE_LEVEL_NUM);
		Map<Integer, SysChest> map = getAllSysChestMap();
		for(SysChest sc : map.values()){
			if(resultList.size()!=Constants.PACKAGE_LEVEL_NUM){
				if(sc.getType() == type  && sc.getIsDeleted()==Constants.IS_DELETED_NO){
					insertToPriceList(resultList,sc);
				}
			}else break;
		}
		return resultList;
	}
	
	public void insertToPriceList(List<SysChest> list,SysChest sc ){
		if(list.size()==0)list.add(sc);
		else{
			int listSize=list.size();
			for(int i=0;i<listSize;i++){
				int level = sc.getLevel();
				if(list.get(i).getLevel()>level){
					if(i>0){
						if(list.get(i-1).getLevel()<level){
							list.add(i,sc);
						}
					}else list.add(0, sc);
				}
				else if(list.get(i).getLevel()<level){
					if(i<listSize-1){
						if(list.get(i+1).getLevel()>level){
							list.add(i+1,sc);
						}
					}else{
						list.add(sc);
					}
				}
			}
		}
	}
	/**
	 * 获得所有OnlineAward
	 */
	public List<OnlineAward> getOnlineAwardList()throws Exception{
		return onlineAwardDao.getOnlineAwardList();
	}
	
	public List<OnlineAward> getOnlineAwardSysItemList()throws Exception{
		List<OnlineAward> list=getOnlineAwardList();
		for(OnlineAward o:list){
			SysItem s=getSysItemByItemId(o.getItemId());
			if(s!=null){
				o.setItemName(s.getDisplayNameCN());
				o.setSysItem(s);
			}else{
				log.error("Error happend during get sysItem in get OnlineAwardList, id="+ o.getItemId());
			}
		}
		return list;
	}
	
	public List<OnlineAward> getAllOnlineAward()throws Exception{
		String objKey=CacheUtil.oOnlineAwardList();
		// 1. Get OnlineAward from cache
		List<OnlineAward>  onlineAwardList = mcc.get(objKey, Constants.CACHE_TIMEOUT);
		if(onlineAwardList==null){
		// 2. Load OnlineAward from DB
			onlineAwardList = onlineAwardDao.getOnlineAwardList();
			if(onlineAwardList!=null)
			mcc.set(objKey, Constants.CACHE_TIMEOUT_DAY,onlineAwardList,Constants.CACHE_TIMEOUT);
		}
		return onlineAwardList;
	}
	/**
	 * 根据OnlineAward type获得OnlineAward List
	 * @param awardType
	 * @return
	 * @throws Exception
	 */
	public List<OnlineAward> getOnlineAwardByType(int awardType) throws Exception {
		String objKey=CacheUtil.oOnlineAwardList(awardType);
		List<OnlineAward>  onlineAwardList = mcc.get(objKey, Constants.CACHE_TIMEOUT);
		if(onlineAwardList==null){
			onlineAwardList = onlineAwardDao.getOnlineAwardByType(awardType);
			if(onlineAwardList!=null)
				mcc.set(objKey, Constants.CACHE_TIMEOUT_DAY,onlineAwardList,Constants.CACHE_TIMEOUT);
		}
		
		return onlineAwardList;
	}
	/**
	 * 根据type和level获得OnlineAward
	 * @param type
	 * @param level
	 * @return
	 * @throws Exception
	 */
	public List<OnlineAward> getOnlineAwardByTypeAndLevel(int awardType, int level)throws Exception{
		List<OnlineAward> typeAllAwards = getOnlineAwardByType(awardType);
		if(typeAllAwards==null){
			return null;
		}
		List<OnlineAward> retList = new ArrayList<OnlineAward>();
		for(OnlineAward oa :typeAllAwards){
			if(oa!=null&&oa.getLevel()==level){
				retList.add(oa);
				}
		}
		return retList;
	}
	/**
	 * 根据指定的比较器，获得排序好的OnlineAward
	 * @param awardType
	 * @param ct
	 * @return
	 * @throws Exception
	 */
	public List<OnlineAward> getSortedOnlineAwardByType(int awardType , Comparator<OnlineAward> ct) throws Exception {
		List<OnlineAward> oaList = getOnlineAwardByType(awardType);
		for(OnlineAward oa : oaList){
			if(oa!=null&&getSysItemByItemId(oa.getItemId())!=null){
				SysItem si = getSysItemByItemId(oa.getItemId());
				oa.setSysItem(si);
				
				}
		}
		Collections.sort(oaList, ct);
		return oaList;
	}
	public List<OnlineAward> getSortedOnlineAwardByType(int awardType , Comparator<OnlineAward> ct,int level) throws Exception {
		List<OnlineAward> oaList = getOnlineAwardByTypeAndLevel(awardType, level);
		for(OnlineAward oa : oaList){
			if(oa!=null&&getSysItemByItemId(oa.getItemId())!=null){
				SysItem si = getSysItemByItemId(oa.getItemId());
				oa.setSysItem(si);
				}
		}
		Collections.sort(oaList, ct);
		return oaList;
	}
	
	
	/**
	 * @return left seconds before the next reward
	 * @throws Exception
	 */
	public OnlineAwardReturnValue getOnlineAward(int playerId) throws Exception {
		String[] awardState = nosqlService.getOnlineAwardState(playerId).split(";");
		int level = Integer.parseInt(awardState[0]);
		String flag = awardState[1];
		long time = Long.parseLong(awardState[2]);
		OnlineAwardReturnValue rv = new OnlineAwardReturnValue();
		Calendar levelTime = Calendar.getInstance();
		boolean isVip = this.getVipLevel(playerId)>0;
		int vipLeftSeconds = 0;

		// 今天的在线时长奖励已经领取结束
		if (level > Constants.LEVEL_MINS.length) {
			rv.setLeftSeconds(Constants.NUM_NEG_TWO);
			rv.setNextTime(Constants.LEVEL_IS_OUT_LINE);
		}
		// 上次未领取
		else if (flag.equals(Constants.FLAG_NOT_GET_AWARD)) {
			rv.setLeftSeconds(Constants.NUM_ZERO);
			rv.setNextTime(getHHMM(levelTime));
		}
		// 登陆进入大厅
		else if (time == Constants.NUM_ZERO) {
			if (isVip) {
				vipLeftSeconds = Constants.LEVEL_MINS_VIP[level - 1] * 60;
				levelTime = getTimeAfterMinutesByVip(level);
			} else {
				vipLeftSeconds = Constants.LEVEL_MINS[level - 1] * 60;
				levelTime = getTimeAfterMinutes(level);
			}
			rv.setLeftSeconds(vipLeftSeconds);
			rv.setNextTime(getHHMM(levelTime));
		}
		// 返回大厅时未到领取时间
		else if (time > levelTime.getTimeInMillis()) {
//			vipLeftSeconds = new Long((time - levelTime.getTimeInMillis()) / 1000).intValue();
//			if (isVip) {
//				vipLeftSeconds /= 2;
//				time /= 2;
//			}
			rv.setLeftSeconds(new Long((time - levelTime.getTimeInMillis()) / 1000).intValue());
			levelTime.setTimeInMillis(time);
			rv.setNextTime(getHHMM(levelTime));
		} else {
			rv.setLeftSeconds(Constants.NUM_ZERO);
			rv.setNextTime(getHHMM(levelTime));
		}
		List<OnlineAward> gifts = getOnlineAwardGiftsByLevel(level);
		if (gifts == null)
			gifts = new ArrayList<OnlineAward>();
		for (OnlineAward oa : gifts) {
			if (oa != null && oa.getSysItem() == null) {
				oa.setSysItem(getSysItemByItemId(oa.getItemId()));
			}
		}
		rv.setGifts(gifts);
		nosqlService.updateOnlineAwardState(playerId, level, flag, levelTime.getTimeInMillis());
		
		if(level <= Constants.EXPRANK.length)
			rv.setCExp(Constants.EXPRANK[level-1]);
		else
			rv.setCExp(0);
		return rv;
	}

	/**
	 * @param playerId
	 * @return
	 * @throws Exception
	 */
	public OnlineAwardReturnValue getOnlineAwardGift(int playerId) throws Exception {

		String[] awardState = nosqlService.getOnlineAwardState(playerId).split(";");
		int level = Integer.parseInt(awardState[0]);
		String flag = awardState[1];
		long time = Long.parseLong(awardState[2]);
		OnlineAwardReturnValue rv = new OnlineAwardReturnValue();
		Calendar levelTime = Calendar.getInstance();
		Player player = getPlayerById(playerId);
		CommonUtil.checkNull(player, ExceptionMessage.NO_HAVE_THE_CHARACTER);
		
		if (flag.equals(Constants.FLAG_NOT_GET_AWARD)
			|| (time != Constants.NUM_ZERO && time < levelTime.getTimeInMillis())) {
			List<OnlineAward> gifts = new ArrayList<OnlineAward>();
			if (level <= Constants.LEVEL_MINS.length) {
				gifts = getOnlineAwardGiftsByLevel(level);// 获取当前级别奖励
				for (OnlineAward oa : gifts) {
					if (oa != null && oa.getSysItem() == null) {
						oa.setSysItem(getSysItemByItemId(oa.getItemId()));
					}
				}
				for (OnlineAward item : gifts) {
					// //防沉迷
					// int num=item.getUnit();
					// int
					// fcm_time=ServiceLocator.nosqlService.getFCMTime(playerId);
					// if(fcm_time>300){
					// num=num/300;
					// }else if(fcm_time>180){
					// num=num/2;
					// }
					ServiceLocator.createService.awardToPlayer(player, item.getSysItem(), new Payment(item.getUnit(), item.getUnitType()), null, Constants.BOOLEAN_YES);
					if (ConfigurationUtil.SWITCH_XUNLEI_LOG.getIsOn()
							&& item.getItemId() == GrowthMissionConstants.MEDAL_ID) {
						int chipNum=getService.getMedolNumByPlayerId(playerId);
						nosqlService.addXunleiLog("7.4"
								+ Constants.XUNLEI_LOG_DELIMITER
								+ player.getUserName()
								+ Constants.XUNLEI_LOG_DELIMITER
								+ player.getName()
								+ Constants.XUNLEI_LOG_DELIMITER
								+ 1
								+ Constants.XUNLEI_LOG_DELIMITER
								+ item.getUnit()
								+ Constants.XUNLEI_LOG_DELIMITER
								+ chipNum
								+ Constants.XUNLEI_LOG_DELIMITER
								+ 2
								+ Constants.XUNLEI_LOG_DELIMITER
								+ CommonUtil.simpleDateFormat.format(new Date()));
					}
					
					if (ConfigurationUtil.SWITCH_XUNLEI_LOG.getIsOn()
							&& item.getItemId() == GrowthMissionConstants.RELIVE_COIN) {
						int chipNum=getService.getReliveCoinNumByPlayerId(playerId);
						nosqlService.addXunleiLog("18.1"
								+ Constants.XUNLEI_LOG_DELIMITER
								+ player.getUserName()
								+ Constants.XUNLEI_LOG_DELIMITER
								+ player.getName()
								+ Constants.XUNLEI_LOG_DELIMITER
								+ 1
								+ Constants.XUNLEI_LOG_DELIMITER
								+ item.getUnit()
								+ Constants.XUNLEI_LOG_DELIMITER
								+ chipNum
								+ Constants.XUNLEI_LOG_DELIMITER
								+ 2
								+ Constants.XUNLEI_LOG_DELIMITER
								+ CommonUtil.simpleDateFormat.format(new Date()));
					}
					ServiceLocator.deleteService.deletePlayerItemInMemcached(playerId, item.getSysItem());
				}

				//vip 获得升级经验
				if(player.getIsVip()>0){
					ServiceLocator.updateService.updateVipUpExp(Constants.VIP_EARN_EXP_METHODS.ONLINETIME.getValue(), playerId);
					player = getPlayerById(playerId);
				}
				
				/* -----------应对“高级小号”增加在线时长经验奖励 20140719 OuYangGuang----------------*/
				
				int upExp = player.getExp();
				int tmExp = Constants.EXPRANK[level-1];
				int pRank = player.getRank();
				ServiceLocator.createService.awardExpToPlayer(player,tmExp);
				soClient.puchCMDtoClient(player.getName(), CommonUtil
						.messageFormat(CommonMsg.REFRESH_EXP,
								player.getExp(), String.valueOf(player.getRank())));		//推送一个命令给客户端，当前表示 刷新经验和等级
				//System.out.println("PLAYER EXP ADD "+upExp+" "+pRank+" "+tmExp+" "+player.getRank()+" "+player.getExp());
				
				/*-------------------------------------------------------------------------------*/
			}
			if (ConfigurationUtil.SWITCH_XUNLEI_LOG.getIsOn()) {
				nosqlService.addXunleiLog("9.1"
						+ Constants.XUNLEI_LOG_DELIMITER
						+ player.getUserName()
						+ Constants.XUNLEI_LOG_DELIMITER
						+ player.getName()
						+ Constants.XUNLEI_LOG_DELIMITER
						+ player.getRank()
						+ Constants.XUNLEI_LOG_DELIMITER
						+ level
						// + Constants.XUNLEI_LOG_DELIMITER + (level==3?1:0)
						+ Constants.XUNLEI_LOG_DELIMITER
						+ (gifts.size() >= 1 ? gifts.get(0).getUnit() : 0)
						+ Constants.XUNLEI_LOG_DELIMITER
						+ (gifts.size() >= 2 ? gifts.get(1).getUnit() : 0)
						+ Constants.XUNLEI_LOG_DELIMITER
						+ CommonUtil.simpleDateFormat.format(new Date()));
			}
			// 获取下一级奖励
			gifts = getOnlineAwardGiftsByLevel(++level > Constants.LEVEL_MINS.length ? Constants.LEVEL_MINS.length : level);
			for (OnlineAward oa : gifts) {
				if (oa != null && oa.getSysItem() == null) {
					oa.setSysItem(getSysItemByItemId(oa.getItemId()));
				}
			}
			boolean isVip = this.getVipLevel(playerId)>0;
			if (level > Constants.LEVEL_MINS.length) {
				rv.setLeftSeconds(Constants.NUM_NEG_TWO);
				rv.setNextTime(Constants.LEVEL_IS_OUT_LINE);
				time = Constants.NUM_ZERO;
			} else {
				if (isVip) {
					levelTime = getTimeAfterMinutesByVip(level);
					rv.setLeftSeconds(Constants.LEVEL_MINS_VIP[level - 1] * 60);
					if(level <= Constants.EXPRANK.length)
						rv.setCExp(Constants.EXPRANK[level-1]);
					else
						rv.setCExp(0);
				} else {
					levelTime = getTimeAfterMinutes(level);
					rv.setLeftSeconds(Constants.LEVEL_MINS[level - 1] * 60);
					if(level <= Constants.EXPRANK.length)
						rv.setCExp(Constants.EXPRANK[level-1]);
					else
						rv.setCExp(0);
				}
				rv.setNextTime(getHHMM(levelTime));
				time = levelTime.getTimeInMillis();
			}

			rv.setGifts(gifts);
			flag = Constants.FLAGE_OTHER;
			nosqlService.updateOnlineAwardState(playerId, level, flag, time);

			// 成长任务16：首次领取在线时长奖励 
			ServiceLocator.updateService.updatePlayerGrowthMission(player, GrowthMissionType.FIRST_GET_ONLINE_AWARD_GIFT);
		} else {
			rv.setErrormsg(ExceptionMessage.NOT_LEVEL_TIME);
		}
		return rv;
	}
	
	public String getHHMM(Calendar time) {
		int m = time.get(Calendar.MINUTE);
		int h = time.get(Calendar.HOUR_OF_DAY);
		if(m<10){
			return h+":0"+m;
		}else return h+":"+m;
	}
	
	private List<OnlineAward> getOnlineAwardGiftsByLevel(int level) throws Exception{
		if(level>Constants.LEVEL_MINS.length)return null;
		return getOnlineAwardByTypeAndLevel(1, level);
	}
	
	/**
	 * 根据id获得奖励
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public OnlineAward getOnlineAwardById(int id) throws Exception{
		return onlineAwardDao.getOnlineAwardById(id);
	}
	public OnlineAward getOnlineAwardByTypeAndId(int type ,int id) throws Exception{
		List<OnlineAward> oaList = getOnlineAwardByType(type);
		OnlineAward retOa = null;
		for(OnlineAward oa : oaList){
			if(oa.getId()==id){
				retOa = oa;
				return retOa;
			}
		}
		return retOa;
	}
	/**
	 * 根据多个id获得奖励list
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	public List<OnlineAward> getOnlineAwardsByTypeAndIds(int type ,List<Integer> ids) throws Exception{
		List<OnlineAward> retSysItems = new ArrayList<OnlineAward>();
		List<OnlineAward> oaList = getOnlineAwardByType(type);
		for(Integer id : ids){
			for(OnlineAward oa : oaList){
				if(id==oa.getId())
					retSysItems.add(oa);
			}
		}
		return retSysItems;
	}
	/**
	 * 获得累计签到奖励,系统随机分配奖励
	 * @param level
	 * @return
	 * @throws Exception
	 */
	public List<OnlineAward> getDailyCheckGifts(int level) throws Exception{
		List<OnlineAward> oaList = getOnlineAwardByTypeAndLevel(Constants.ONLINE_AWARD_TYPES.DAILY_CHECK.getValue(), level);
		if(oaList==null||oaList.size()==0)
			log.error("Error happend during  get onlineAward Gifts by level:" + level);
		return randomObjsFromList(1,oaList);
	}
	
	/**
	 * 从给定的list中随机抽取n个不同对象
	 * @param <T>
	 * @param num
	 * @param oaList
	 * @return
	 */
	public <T> List<T> randomDiffObjsFromList(int num,List<T> oaList){
		
		List<T> retList = new ArrayList<T>(0);
		//当传入的list为空或者size为0则返回size为0的List
		if(oaList==null||oaList.size()==0)
			return retList;
		int size = oaList.size();
		//当随机数量大于或等于List的size时直接返回List
		if(num>=size)
			return oaList;
		Collections.shuffle(oaList);
		return oaList.subList(0, num);
	}
	/**
	 * 从给定的list中随机抽取n个对象
	 * @param <T>
	 * @param num
	 * @param oaList
	 * @return
	 */
	public <T> List<T> randomObjsFromList(int num,List<T> oaList){
		
		List<T> retList = new ArrayList<T>(0);
		//当传入的list为空或者size为0则返回size为0的List
		if(oaList==null||oaList.size()==0)
			return retList;
		int size = oaList.size();
		for(int i =0 ;i<num;i++){
		int randomIndex = (int) (size*Math.random());
		retList.add(oaList.get(randomIndex));
		}
		return retList;
	}
	
	public Calendar getTimeAfterMinutes(int m){
		Calendar a=Calendar.getInstance();
		a.add(Calendar.MINUTE, Constants.LEVEL_MINS[m-1]);
		return a;
	}
	
	public Calendar getTimeAfterMinutesByVip(int m){
		Calendar a = Calendar.getInstance();
		a.add(Calendar.MINUTE, Constants.LEVEL_MINS_VIP[m-1]);
		return a;
	}
	
	/*public double getStrengthenProperty1(SysItem si, int currentLevel, int index) {
		if(currentLevel < 0){
			return 0;
		}
		int itemTypeIndex = -1;
		if(Constants.DEFAULT_WEAPON_TYPE == si.getType() && (si.getSubType() == 1 || si.getSubType() == 2 || si.getSubType() == 3)){
			itemTypeIndex = 0;
		} else if(Constants.DEFAULT_COSTUME_TYPE == si.getType()){
			itemTypeIndex = 1;
		} else if(Constants.DEFAULT_PART_TYPE == si.getType() && si.getSubType() == 1){
			itemTypeIndex = 2;
		} else if(Constants.DEFAULT_PART_TYPE == si.getType() && si.getSubType() == 2){
			itemTypeIndex = 3;
		}
		if(itemTypeIndex > -1){
			return Constants.strengthenAppend1[(currentLevel >= 15 ? 14 : currentLevel)][itemTypeIndex][index];
		}
		return 0;
	}*/
	
	public double getStrengthenProperty(SysItem si, int currentLevel, int index) {
		if(currentLevel < 0){
			return 0;
		}
		int itemTypeIndex = -1;
		if(Constants.DEFAULT_WEAPON_TYPE == si.getType() && (si.getSubType() == 1 || si.getSubType() == 2 || si.getSubType() == 3)){
			itemTypeIndex = 1;
		} else if(Constants.DEFAULT_COSTUME_TYPE == si.getType()){
			itemTypeIndex = 2;
		} else if(Constants.DEFAULT_PART_TYPE == si.getType() && si.getSubType() == 1){
			itemTypeIndex = 3;
		} else if(Constants.DEFAULT_PART_TYPE == si.getType() && si.getSubType() == 2){
			itemTypeIndex = 4;
		}
		currentLevel ++;
		if(itemTypeIndex > -1){
			String key = (currentLevel > Constants.MAX_STRENGTH_LEVEL ? Constants.MAX_STRENGTH_LEVEL : currentLevel) + "_" + itemTypeIndex;
			return CombineProperty.strengthenAppendMap.get(key)[index];
		}
		return 0;
	}
	/**
	 * 获得玩家物品数量
	 * @param playerId
	 * @param sysItemId
	 * @return
	 * @throws Exception
	 */
	public int getPlayerItemsTotalQuantity(int playerId,int type,int sysItemId) throws Exception{
		int num =0;
		List<PlayerItem> playerItems = getPlayerItemsByTypeAndSysId(playerId,type, sysItemId);
		for(PlayerItem pi : playerItems){
		if(pi!=null)
			 num += pi.getQuantity();
		}
		return num;
	}
	/**
	 * VIP宝箱随机列表
	 * @param randomSiNum
	 * @return
	 * @throws Exception
	 */
	public List<OnlineAward> getVipRandomAwardList(int randomSiNum) throws Exception {
		List<OnlineAward> vscT1List = getOnlineAwardByTypeAndLevel(Constants.ONLINE_AWARD_TYPES.VIP_SAFECABINET.getValue(),Constants.VIP_SAFE_CABINET_LEVELS[0]);
		List<OnlineAward> vscT2List = getOnlineAwardByTypeAndLevel(Constants.ONLINE_AWARD_TYPES.VIP_SAFECABINET.getValue(),Constants.VIP_SAFE_CABINET_LEVELS[1]);
		List<OnlineAward> vscT3List = getOnlineAwardByTypeAndLevel(Constants.ONLINE_AWARD_TYPES.VIP_SAFECABINET.getValue(),Constants.VIP_SAFE_CABINET_LEVELS[2]);
		List<OnlineAward> retList = new ArrayList<OnlineAward>(randomSiNum);
		List<OnlineAward> randomVscT1List = randomObjsFromList(Constants.VIP_SAFE_CABINET_TYPE_NUMS[0], vscT1List);
		List<OnlineAward> randomVscT2List = randomObjsFromList(Constants.VIP_SAFE_CABINET_TYPE_NUMS[1], vscT2List);
		List<OnlineAward> randomVscT3List = randomObjsFromList(Constants.VIP_SAFE_CABINET_TYPE_NUMS[2], vscT3List);
		if(randomVscT1List!=null&&randomVscT1List.size()>0){
			for(OnlineAward vsc :randomVscT1List){
				if(vsc!=null)
				retList.add(vsc);
			}
		}
		if(randomVscT2List!=null&&randomVscT2List.size()>0){
			for(OnlineAward vsc :randomVscT2List){
				if(vsc!=null)
				retList.add(vsc);
			}
		}
		if(randomVscT3List!=null&&randomVscT3List.size()>0){
			for(OnlineAward vsc :randomVscT3List){
				if(vsc!=null)
				retList.add(vsc);
			}
		}
		Collections.shuffle(retList);
		return retList;
	}
	/**
	 * 根据权重随机抽取num个OnlineAward
	 * @param num
	 * @param allStageClearBrands
	 * @return
	 */
	public List<OnlineAward> randomOnlineAwardFromList(int num,List<OnlineAward> onlineAwards) {
		List<OnlineAward> retList = new ArrayList<OnlineAward>();
		if(onlineAwards!=null&&!onlineAwards.isEmpty()){
			int count =0;
			int index = 0;
			Map<Integer,Integer> map = new HashMap<Integer, Integer>();
			for(OnlineAward scb :onlineAwards){
				map.put(count++, scb.getWeight());
			}
			for(int i=0;i<num ;i++){
			index = CommonUtil.getRandomValueByWeights(map);
			retList.add(onlineAwards.get(index));
			}
		}
		return retList;
	}
	public OnlineAward randomOnlineAward(List<OnlineAward> onlineAwards) {
		OnlineAward retList = new OnlineAward();
		if(onlineAwards!=null&&!onlineAwards.isEmpty()){
			int count =0;
			int index = 0;
			Map<Integer,Integer> map = new HashMap<Integer, Integer>();
			for(OnlineAward scb :onlineAwards){
				map.put(count++, scb.getWeight());
			}
			index = CommonUtil.getRandomValueByWeights(map);
			retList=onlineAwards.get(index);
		}
		return retList;
	}
	public int[] randomDifIndexFromList(int num,List<OnlineAward> onlineAwards) throws Exception{
		int count =1;
		Map<Integer,Integer> map = new HashMap<Integer, Integer>();
		for(OnlineAward scb :onlineAwards){
			map.put(count++, scb.getWeight());
		}
		int[] idxArr =  CommonUtil.getRandomValuesByWeights(num,map);
		
		return idxArr;
	}
	public List<TmpPlayerItem> getMagicBoxOpenTmpPlayerItems() throws Exception{
		List<String> ids =  nosqlService.getPlayerMagixBoxItemIds();
		List<TmpPlayerItem> piList = new ArrayList<TmpPlayerItem>();
		if(ids!=null&&ids.size()>0){
			for(String id : ids){
				String[] ida = id.split(":");
				TmpPlayerItem tmpPi = new TmpPlayerItem();
				if(ida.length>=3)
					tmpPi.setQuantity(Integer.parseInt(ida[2]));
				else
					tmpPi.setQuantity(Constants.NUM_ONE);
				if(ida.length>=2){
					tmpPi.setSysItem(getSysItemByItemId(Integer.parseInt(ida[1])));
					tmpPi.setPlayer(getPlayerById(Integer.parseInt(ida[0])));
					piList.add(0,tmpPi);
				}
			}
		}
		return piList;
	}
	/**
	 * 获取玩家翻牌子 稀有物品的记录
	 * @return List<TmpPlayerItem>
	 * @throws Exception
	 */
	public List<TmpPlayerItem> getMagicBoxOpenTmpPlayerItems_1() throws Exception{
		List<String> ids =  nosqlService.getPlayerMagixBoxItemIds();
		List<TmpPlayerItem> piList = new ArrayList<TmpPlayerItem>();
		if(ids!=null&&ids.size()>0){
			for(String id : ids){
				int quantity = 1;
				SysItem si = new SysItem();
				Player player = new Player();
				String[] ida = id.split(":");
				TmpPlayerItem tmpPi = new TmpPlayerItem();
				if(ida.length>=5){
				switch (ida.length) {
				case 5:
					quantity= Integer.parseInt(ida[4]);	
				case 4:
					si = getSysItemByItemId(Integer.parseInt(ida[3]));
				case 3:
					player.setExp(Integer.parseInt(ida[2]));
				case 2:
					player.setRank(Integer.parseInt(ida[1]));
				case 1:
					player.setName(ida[0]);
				default:
					break;
				}
				tmpPi.setQuantity(quantity);
				tmpPi.setSysItem(si);
				tmpPi.setPlayer(player);
				piList.add(0,tmpPi);
				}
			}
		}
		return piList;
	}
	/**
	 * 获得签到 等级礼品盒（打开类）
	 * @param level
	 * @return
	 * @throws Exception
	 */
	public SysItem getDailyCheckGiftBox(int level) throws Exception{
		SysItem si = null;
		List<SysItem> siList = getSysItemByIID(Constants.SPECIAL_ITEM_IIDS.CHECK_GIFTBOX.getValue(), Constants.DEFAULT_OPEN_TYPE);
		for(SysItem s :siList){
			if(Integer.parseInt(s.getIValue())==level)
				si = s;
		}
		return si;
	}

	/**
	 * <h1>当前玩家可进行的成长任务</h1>
	 *<ul>条件：
	 *<li>1:任务级别达到且是对玩家开放的</li>
	 *<li>2:当前任务未完成</li>
	 *<li>3:前置任务已完成且已领取奖励</li>
	 * @param playerId
	 * @param rank
	 * @param gmTypes
	 * @return
	 * @throws Exception
	 */
	public PlayerGrowthMission getPaGrowthMission(int playerId, int rank, GrowthMissionType... gmTypes) {
		try {
			// step 0,
			final Map<Integer, PlayerGrowthMission> playerGrowthMissionMap = playerGrowthMissionDao.getPaGrowthMissions(playerId);
			for (GrowthMissionType gmType : gmTypes) {
				final PlayerGrowthMission playerGrowthMission = playerGrowthMissionMap.get(gmType.getGrowthMissionId());
				// step 1,playerGrowthMission status is false
				if (null == playerGrowthMission || BooleanBytevalue.TRUE.getBytevalue() == playerGrowthMission.getStatus())
					continue;
				final SysGrowthMission sysGrowthMission = sysGrowthMissionDao.getGrowthMission(gmType);
				if(null == sysGrowthMission)
					continue;
				// step 2,premiseMission status is true
				final PlayerGrowthMission parentMission = playerGrowthMissionMap.get(sysGrowthMission.getParent());
				if(gmType.getGrowthMissionId()<GrowthMissionType.NEW_AWARD_1.getGrowthMissionId()||gmType.getGrowthMissionId()>GrowthMissionType.NEW_AWARD_8.getGrowthMissionId()){
					if(sysGrowthMission.getParent() != 0 && (null == parentMission ||parentMission.getReceived() == BooleanBytevalue.FALSE.getBytevalue())){
						break;
					}
				}
//				if ((gmType.getGrowthMissionId()<GrowthMissionType.NEW_AWARD_1.getGrowthMissionId()||gmType.getGrowthMissionId()>GrowthMissionType.NEW_AWARD_8.getGrowthMissionId())
//						||(sysGrowthMission.getParent() != 0 && (null == parentMission ||parentMission.getReceived() == BooleanBytevalue.FALSE.getBytevalue())))
//					break;
				// step 3,player reach specified rank
				if (sysGrowthMission.getStatus() == BooleanBytevalue.TRUE.getBytevalue() && sysGrowthMission.getRank() <= rank){
					playerGrowthMission.setSysGrowthMission(sysGrowthMission);
					return playerGrowthMission;
				}
			}
		} catch (Exception e) {
			ServiceLocator.growthMissionErrLog.error("GrowthMission getPaGrowthMission exception!:", e);
		}
		return null;
	}
	public PlayerGrowthMission getPaCombineStrengthGrowthMission(int playerId, int rank, GrowthMissionType gmType) {
		try {
			// step 0,
			PlayerGrowthMission playerGrowthMission = playerGrowthMissionDao.getPaGrowthMissions(playerId).get(gmType.getGrowthMissionId());
			// step 1,playerGrowthMission status is false
			if (null == playerGrowthMission || BooleanBytevalue.TRUE.getBytevalue() == playerGrowthMission.getStatus())
				return null;
			final SysGrowthMission sysGrowthMission = sysGrowthMissionDao.getGrowthMission(gmType);
			if(null == sysGrowthMission)
				return null;
			// step 2,premiseMission status is true
			// step 3,player reach specified rank
			if (sysGrowthMission.getStatus() == BooleanBytevalue.TRUE.getBytevalue() && sysGrowthMission.getRank() <= rank){
				playerGrowthMission.setSysGrowthMission(sysGrowthMission);
				return playerGrowthMission;
			}
		} catch (Exception e) {
			ServiceLocator.growthMissionErrLog.error("GrowthMission getPaGrowthMission exception!:", e);
		}
		return null;
	}
	/**
	 * 玩家可以见得任务
	 * 条件：
	 * 1、当前任务未领取奖励
	 * 2、前置任务已完成且已领取奖励
	 * @param player
	 * @return
	 * @throws Exception
	 */
	public List<PlayerGrowthMission> getPlayerGrowthMissionList(final Player player) throws Exception{
		Map<Integer, SysGrowthMission> allGrowthMissionMap = sysGrowthMissionDao.getAllGrowthMissionMap();
		Map<Integer, PlayerGrowthMission> playerGrowthMissionMap = playerGrowthMissionDao.getPaGrowthMissions(player.getId());
		
		List<PlayerGrowthMission> playerGrowthMissions = new ArrayList<PlayerGrowthMission>();
		for(PlayerGrowthMission pgm:playerGrowthMissionMap.values()){
			SysGrowthMission sgm = allGrowthMissionMap.get(pgm.getId());
			if(sgm == null)
				continue;
			PlayerGrowthMission parentMission = playerGrowthMissionMap.get(sgm.getParent());
			if(pgm.getReceived() == BooleanBytevalue.FALSE.getBytevalue()//未领取礼物
					&&(sgm.getParent()==0||
						(null!=parentMission && parentMission.getReceived()==BooleanBytevalue.TRUE.getBytevalue()))){//前置任务已领取
				pgm.setSysGrowthMission(sgm);
				playerGrowthMissions.add(pgm);
			}
		}
		
		
		return playerGrowthMissions;
	}
	
	public List<GrowthMissionVo> getPlayerGrowthMissionVoList(final Player player) throws Exception{
		
		List<PlayerGrowthMission> playerGrowthMissionList = getPlayerGrowthMissionList(player);
		List<GrowthMissionVo> growthMissions = new ArrayList<GrowthMissionVo>(playerGrowthMissionList.size());
		
		for (PlayerGrowthMission pgm : playerGrowthMissionList) {
			int id = pgm.getId();
			String icon = pgm.getSysGrowthMission().getIcon(); 
			String title = pgm.getSysGrowthMission().getTitle();
			String desc = pgm.getSysGrowthMission().getDescription(); 
			int target = pgm.getSysGrowthMission().getNumber(); 
			int number = pgm.getNumber(); 
			byte status = pgm.getStatus(); 
			byte award = pgm.getReceived();
			byte isnew = player.getRank() == pgm.getSysGrowthMission().getRank() ? BooleanBytevalue.TRUE.getBytevalue():BooleanBytevalue.FALSE.getBytevalue(); 
			int money = pgm.getSysGrowthMission().getMoney();
			int exp = pgm.getSysGrowthMission().getExperience(); 
			
			List<AwardItemVo> awardItemVos = pgm.getSysGrowthMission().getAwardItemVos();
			
			GrowthMissionVo vo = new GrowthMissionVo(id,icon,title,desc,target,number,status,award,isnew,money,exp,awardItemVos);
			vo.setIsMain(pgm.getSysGrowthMission().getDefaultopen());
			growthMissions.add(vo);
		}
		Collections.sort(growthMissions);
		return growthMissions;
	}
	
	public GrowthMissionVo getPlayerGrowthMissionGift(final Player player,final int missionId,final int time) throws Exception{
		PlayerGrowthMission  pgm  = playerGrowthMissionDao.getPaGrowthMissions(player.getId()).get(missionId);
		if(null == pgm)
			return null;
		if(pgm.getStatus()==BooleanBytevalue.TRUE.getBytevalue() 
				&& pgm.getReceived() == BooleanBytevalue.FALSE.getBytevalue()){
			SysGrowthMission sgm = sysGrowthMissionDao.getGrowthMission(missionId);
			pgm.setSysGrowthMission(sgm);
			int oldRank = player.getRank();
			int money = sgm.getMoney();
			int exp = sgm.getExperience(); 
			if(time>=300){//防沉迷
				money=exp=0;
			}else if(time>=180){
				money*=0.5;
				exp*=0.5;
			}
			int vipmoney = 0;
			int vimexp = 0; 
			List<AwardItemVo> awardItemVos = pgm.getSysGrowthMission().getAwardItemVos();
			ServiceLocator.createService.awardGrowthMission(player, money, exp, awardItemVos, sgm.getDescriptionCN());
			
			int newRank = player.getRank();
			byte upgrade = oldRank == newRank? BooleanBytevalue.FALSE.getBytevalue() : BooleanBytevalue.TRUE.getBytevalue();
			
			GrowthMissionVo vo = new GrowthMissionVo(money, exp, vipmoney, vimexp, upgrade, awardItemVos);
			
			pgm.setReceived(BooleanBytevalue.TRUE.getBytevalue());
			playerGrowthMissionDao.updatePlayerGrowthMissions(pgm);
			
			int openmodule = sgm.getOpenmodule();
			if(openmodule != 0){
				//push openModule
				ServiceLocator.updateService.pushModuleStatusChanage(player, openmodule);
				if(openmodule == ModuleStatusIndex.DailyMission.ordinal()){
					ServiceLocator.updateService.sendMission(player, true, true);
				}
			}
			
			return vo;
		} 
		return null;

	}
	
	public List<SysItem> getExchangeSysItemList()throws Exception{
		List<SysItem> list=sysItemDao.getAllSystemItem();
		List<SysItem> returnList=new ArrayList<SysItem>();
		for(SysItem si:list){
			if(si.getIsDeleted()!=Constants.BOOLEAN_NO && si.getIsExchange()==1){
				List<Payment> pl = si.getMedalPricesList();
				List<Payment> rl = si.getReviveCoinList();
				List<Payment> al = si.getAchipPricesList();
				List<Payment> bl = si.getBchipPricesList();
				List<Payment> cl = si.getCchipPricesList();
				if ((pl != null && pl.size() > 0)
						|| (rl != null && rl.size() > 0)
						|| (al != null && al.size() > 0)
						|| (bl != null && bl.size() > 0)
						|| (cl != null && cl.size() > 0)) {
					returnList.add(si);
				}
			}
		}
		return returnList;
	}
	/**
	 * 资源争夺战相关的 兑换
	 * @return
	 * @throws Exception
	 */
	public List<SysItem> getZYZDZExchangeSysItemList(final int type)throws Exception{
		List<SysItem> list=sysItemDao.getAllSystemItem();
		List<SysItem> returnList=new ArrayList<SysItem>();
		for(SysItem si:list){
			if(si.getIsDeleted()!=Constants.BOOLEAN_NO && si.getIsExchange()>=2){
				List<Payment> pl=si.getAllResPricesList();
				if(pl!=null && pl.size()>0){
					if(type==1){
						if(si.getIId()>=Constants.SPECIAL_ITEM_IIDS.ZYZDZ_BUFF_ATT.getValue()&&
								si.getIId()<=Constants.SPECIAL_ITEM_IIDS.ZYZDZ_BUFF_ATT_SP.getValue()){ //buff
							returnList.add(si);
						}
					}else{
						if(si.getIId()<Constants.SPECIAL_ITEM_IIDS.BLOOD_BOTTLE.getValue()||
								si.getIId()>Constants.SPECIAL_ITEM_IIDS.ZYZDZ_BUFF_ATT_SP.getValue()){
							returnList.add(si);
						}
					}
					
				}
			}
		}
		return returnList;
	}
	
	/**
	 * 
	 * @param playerId
	 * @return 0：没有 1：每日任务 2：每周任务 4：活动 8:成长任务 3：每日任务+每周任务 5：每日任务+活动 6：每周任务+活动 7：每日任务+每周任务+活动
	 * @throws Exception
	 */
	public int missionNeedAward(int playerId) throws Exception{
		int result = 0x0000;
		int dailyFlag = 0x0000;
		int weeklyFlag = 0x0000;
		int activityFlag = 0x0000;
		int growthFlag = 0x0000;
		try{
			List<PlayerMission> playerMissionList = getService.getPlayerMissionList(playerId);
			for(int i=0; i<playerMissionList.size(); i++){
				PlayerMission pm = playerMissionList.get(i);
				if(pm.getType() == Constants.NUM_ZERO && dailyFlag == Constants.NUM_ZERO){
					if(pm.getAward() == Constants.NUM_ZERO && pm.getStatus() == Constants.NUM_ONE){
						dailyFlag = 1;
						continue;
					}
				} else if(pm.getType() == Constants.NUM_ONE && weeklyFlag == Constants.NUM_ZERO){
					if(pm.getAward() == Constants.NUM_ZERO && pm.getStatus() == Constants.NUM_ONE){
						weeklyFlag = 2;
						continue;
					}
				}
			}
			List<PlayerActivity> playerActivityList=getService.getPlayerActivityOneDayList(playerId);
			for(int i=0; i<playerActivityList.size(); i++){
				PlayerActivity pa = playerActivityList.get(i);
				if(pa.getStatus() == Constants.NUM_ONE && pa.getAward() == Constants.NUM_ZERO && activityFlag == Constants.NUM_ZERO){
					activityFlag = 4;
					break;
				}
			}
			Player player = getSimplePlayerById(playerId);
			List<PlayerGrowthMission> playerGrowthMissionList = getPlayerGrowthMissionList(player);
			for (PlayerGrowthMission playerGrowthMission : playerGrowthMissionList) {
				if(playerGrowthMission.getStatus()==BooleanBytevalue.TRUE.getBytevalue()&&playerGrowthMission.getReceived()==BooleanBytevalue.FALSE.getBytevalue()){
				  growthFlag = 0x0008;
				  break;
				}
			}
		} catch(Exception e){
			log.debug("error in missionNeedAward:", e);
		}
		return (result | dailyFlag | weeklyFlag | activityFlag | growthFlag);
	}
	
	public void checkGrowthShot(Player player) throws Exception{
		char[] playerTutorial = getPlayerTutorial(player);
		if(playerTutorial[ModuleStatusIndex.Shot.ordinal()] == ModuleStatus.CLOSE.getCh()){
			Map<Integer, PlayerGrowthMission> paGrowthMissions = getPlayerGrowthMissionDao().getPaGrowthMissions(player.getId());
			if(null != paGrowthMissions){
				PlayerGrowthMission playerGrowthMission = paGrowthMissions.get(GrowthMissionType.COMPLETE_LIMIT_3.getGrowthMissionId());
				if(null != playerGrowthMission && playerGrowthMission.getStatus() == 1){
					playerTutorial[ModuleStatusIndex.Shot.ordinal()] = ModuleStatus.FLASH.getCh();
					ServiceLocator.updateService.updatePlayerTutorial(player, playerTutorial);
				}
			}
		}
	}
	
	public char[] getPlayerTutorial(Player player) throws Exception{
		String tutorial = player.getTutorial(); 
		if(null == tutorial ){
			tutorial = GrowthMissionConstants.DEFAULTTUTORIAL;
			ServiceLocator.updateService.updatePlayerTutorial(player, tutorial);
		}else if(tutorial.length() != GrowthMissionConstants.MODULE_NUM){
			tutorial = Strings.padEnd(tutorial, GrowthMissionConstants.MODULE_NUM, '0');

			Map<Integer, PlayerGrowthMission> paGrowthMissions = getPlayerGrowthMissionDao().getPaGrowthMissions(player.getId());
			if(null != paGrowthMissions){
				PlayerGrowthMission playerGrowthMission = paGrowthMissions.get(GrowthMissionType.COMPLETE_LIMIT_3.getGrowthMissionId());
				if(null != playerGrowthMission && playerGrowthMission.getStatus() == 1){
					char[] charArray = tutorial.toCharArray();
					charArray[ModuleStatusIndex.Shot.ordinal()] = ModuleStatus.FLASH.getCh();
					tutorial = new String(charArray);
				}
			}
			ServiceLocator.updateService.updatePlayerTutorial(player, tutorial);
		}
		return tutorial.toCharArray();
	}
	public char[] getPlayerTutorial(int playerId) throws Exception{
		return getPlayerTutorial(getPlayerById(playerId));
	}
	public List<PlayerPack> getPlayerPackList(Integer playerId)throws DataAccessException {
		return playerPackDao.getPlayerPackList(playerId);
	}
	//根据真实排名第一和最后一名的数据估算出玩家不同类型的排名
	public int getPersonRankByIdAndType(int playerId, String typeStr,int firstNum, int lastNum) throws Exception {
		int retNum =0;
		Player player = getPlayerById(playerId);
		if ("kWeek".equals(typeStr)) {
			retNum= player.getWeekScore();
			if(retNum>=lastNum){
				return Constants.CURRENT_WEEK_RANK_NUM + (int)(40*Math.random()+1);
			}
			retNum = (int)(((float)(firstNum - retNum)/(firstNum -lastNum))*Constants.CURRENT_WEEK_RANK_NUM);
			return retNum;
		}
		if ("kCommonTop".equals(typeStr)) {
			retNum=  player.getGScore();
		}else if ("kFightNumTop".equals(typeStr)) {
			retNum= player.getMaxFightNum();
		}
		else if ("kControlTop".equals(typeStr)) {
			retNum = player.getGControl();
		}
		else if ("kRevengeTop".equals(typeStr)) {
			retNum = player.getGRevenge();
		}
		else if ("kAssistTop".equals(typeStr)) {
			retNum = player.getGAssist();
		}
		else if ("kKnifeTop".equals(typeStr)) {
			retNum = player.getGKnifeKill();
		}
		else {
			retNum=  player.getGScore();
		}
		if(retNum>=lastNum){
			return Constants.CURRENT_RANK_NUM + (int)(Constants.RANDOM_RANK_NUM*Math.random()+1);
		}
		retNum = (int)(((float)(firstNum - retNum)/(firstNum -lastNum))*Constants.CURRENT_RANK_NUM);
		return retNum;
	}	
	
	public int computeCommmonRank(double value,double firstValue ,double lastValue, int rankNum){
		if(value>=lastValue){
			return rankNum + 1;
		}
		int rank = rankNum + 1 + (int)(((lastValue - value)/(firstValue -lastValue))*rankNum);
		return rank;
	}
	
	public int getPlayerRankNumInTop(int playerId , String typeStr ,Date date) throws Exception{
		NosqlService nosqlService = ServiceLocator.nosqlService;
		NoSql nosql = nosqlService.getNosql();
		String rankKey = NosqlKeyUtil.selfLevelnumInTopByType(playerId, typeStr);
		String[] selfRankValues =nosql.get(rankKey)==null?null:nosql.get(rankKey).split(":");
		int  rankNumInTop =selfRankValues==null?0:StringUtil.toInt( selfRankValues[0]);
		String rcdStr = null;
		if(selfRankValues!=null)
		rcdStr =  selfRankValues.length>1?selfRankValues[1]:null;
		String lastRunDate = nosqlService.getLastRcdInfo(typeStr,0);//保存每次shell脚本执行完之后的时间
		String todayStr = CommonUtil.dateFormatDate.format(date);
		String currentWeekStr = CommonUtil.dateFormatWeek.format(date);
		//如果rankNumInTop==0说明玩家尚未加入排行榜，等待脚本执行完成后，更新玩家排名
		if((rankNumInTop==0||rcdStr!=null&&!lastRunDate.equals(rcdStr))&&(todayStr.equals(lastRunDate)||currentWeekStr.equals(lastRunDate))){
				int firstNum = StringUtil.toInt(nosqlService.getLastRcdInfo(typeStr,1));
				int lastNum = StringUtil.toInt(nosqlService.getLastRcdInfo(typeStr,2));
				rankNumInTop = getPersonRankByIdAndType(playerId ,typeStr,firstNum,lastNum);
				if("kWeek".equals(typeStr)){
					nosql.set(rankKey,String.valueOf(rankNumInTop)+":"+currentWeekStr);
				}else{
					nosql.set(rankKey,String.valueOf(rankNumInTop)+":"+todayStr);
				}
		}
		return rankNumInTop;
	}
	/**
	 * 获得玩家战斗力排名
	 * @param playerId
	 * @param cid 0:总战斗力,1：火箭兵.......
	 * @return
	 * @throws Exception
	 */
	public int getPlayerFightNumRankInTop(int playerId , String cid) throws Exception{
		String key = NosqlKeyUtil.fightNumInRealTopByType(cid);
		Player player = getPlayerById(playerId);
		NoSql  nosql = nosqlService.getNosql();
		int chid = Integer.parseInt(cid);
		int rank = (int)nosqlService.getNosql().zRank(key, String.valueOf(playerId)) + 1;
		if(rank>0&&rank<=Constants.REAL_TOP_RANK_NUM){//如果玩家的排名在前15000内直接返回排名，否则要估算
			return rank;
		}
		int value = 0;
		double firstValue = 0;
		double lastValue = 0;
		Iterator<Tuple> firstItrt = nosql.zrangeWithScores(key, 0, 0).iterator();
		Iterator<Tuple> lastItrt = nosql.zrangeWithScores(key, -1, -1).iterator();
		if(firstItrt.hasNext()){
			firstValue = Math.abs(firstItrt.next().getScore());
			if(lastItrt.hasNext()){
				lastValue= Math.abs(lastItrt.next().getScore());
			}
		}
		int total = (int)nosql.zCard(key);
		if(total>Constants.REAL_TOP_RANK_NUM){
			lastValue = Math.abs(nosql.zrangeWithScores(key, Constants.REAL_TOP_RANK_NUM-1, Constants.REAL_TOP_RANK_NUM-1).iterator().next().getScore());
		}
		if(chid!=0){//0 表示综合战斗力
			Character character = getService.getCharacterByCharacterId(playerId, Integer.parseInt(cid));
			value = character.getFightNum();
		}else{
			value = player.getMaxFightNum();
		}
		if(rank>Constants.REAL_TOP_RANK_NUM){
			rank = getService.computeCommmonRank(value, firstValue, lastValue,Constants.REAL_TOP_RANK_NUM);
		}else if(rank==0){
			String pId = String.valueOf(playerId);
			if(total<Constants.REAL_TOP_RANK_NUM){
				nosql.zAdd(key, -value, pId);
				rank  = (int)nosql.zRank(key, pId) + 1;
			}else{
				if(value > lastValue){
					nosql.zAdd(key, -value, pId);
					rank  = (int)nosql.zRank(key, pId) + 1;
				}else{
					rank = getService.computeCommmonRank(value, firstValue, lastValue,Constants.REAL_TOP_RANK_NUM);
				}
			}
		}
		return rank;
	}
	
	
	/**
	 * 获得玩家积分，击杀/死亡，助攻，胜率排名
	 * @param playerId
	 * @param type
	 * @return int
	 * @throws Exception
	 */
	public int getCommonRankByType(int playerId,String type) throws Exception{
		boolean isDynamicOn = ConfigurationUtil.SWITCH_PSNTOP_DYNAMIC.getIsOn();
		String key = isDynamicOn?NosqlKeyUtil.commonLevelNumInRealTopByType(type):NosqlKeyUtil.commonLevelNumInTopByType(type);
		Player player = getPlayerById(playerId);
		NoSql  nosql = nosqlService.getNosql();
		int rank = (int)nosqlService.getNosql().zRank(key, String.valueOf(playerId)) + 1;
		if(rank>0&&rank<=Constants.REAL_TOP_RANK_NUM){//如果玩家的排名在前15000内直接返回排名，否则要估算
			return rank;
		}
		double value = 0;
		if("kScore".equals(type)){
			value = player.getGScore();
		}else if("kAssist".equals(type)){
			value = player.getGAssist();
		}else if("kWinRate".equals(type)){
			value = player.getGWin()+player.getWinRate()/2;
		}else if("kKillDead".equals(type)){
			int killNum = player.getGKill();
			int deadNum = player.getGDead();
			value = killNum + 1.0/(deadNum+2);
		}
		double firstValue = 0;
		double lastValue = 0;
		Iterator<Tuple> firstItrt = nosql.zrangeWithScores(key, 0, 0).iterator();
		Iterator<Tuple> lastItrt = nosql.zrangeWithScores(key, -1, -1).iterator();
		if(firstItrt.hasNext()){
			firstValue = Math.abs(firstItrt.next().getScore());
			if(lastItrt.hasNext()){
				lastValue= Math.abs(lastItrt.next().getScore());
			}
		}
		int total = (int)nosql.zCard(key);
		if(total>Constants.REAL_TOP_RANK_NUM){
			lastValue = Math.abs(nosql.zrangeWithScores(key, Constants.REAL_TOP_RANK_NUM-1, Constants.REAL_TOP_RANK_NUM-1).iterator().next().getScore());
		}
		if(rank>Constants.REAL_TOP_RANK_NUM){
			rank = getService.computeCommmonRank(value, firstValue, lastValue,Constants.REAL_TOP_RANK_NUM);
		}else {
			String pId = String.valueOf(playerId);	
			if(ConfigurationUtil.SWITCH_PSNTOP_DYNAMIC.getIsOn()){//排行榜实时开关
				if(total<Constants.REAL_TOP_RANK_NUM){
					nosql.zAdd(key, -value, pId);
					rank = (int) nosql.zRank(key, pId);
				}else {
					if(value > lastValue){
						nosql.zAdd(key, -value, pId);
						rank = (int) nosql.zRank(key, pId) + 1;
					}else{
						rank = getService.computeCommmonRank(value, firstValue, lastValue,Constants.REAL_TOP_RANK_NUM);
					}
				}
			}else{
					String mccKey = key + ":" + playerId;
					Integer iRank = mcc.get(mccKey);
					rank = iRank!=null?iRank:0;
					if(rank==0){
						lastValue = Math.abs(nosql.zrangeWithScores(key, -1, -1).iterator().next().getScore());
						rank = getService.computeCommmonRank(value, firstValue, lastValue,total);
						mcc.set(mccKey,Constants.CACHE_TIMEOUT_DAY ,rank);
					}
				}
		}
		return rank;
	}
	
	public List<StrengthenAppend> getAllStreAppList() throws Exception{
		return strengthenAppendDao.getAllStreAppList();
	}
	
	public void initStrengthAppend(){
		Map<String, double[]> strengthenAppendMap = new HashMap<String, double[]>();
		try {
			for (StrengthenAppend each : ServiceLocator.getService.getAllStreAppList()) {
				String key = each.getLevel() + "_" + each.getType();
				double[] value = new double[10];
				value[0] = each.getProperty1();
				value[1] = each.getProperty2();
				value[2] = each.getStreNum();
				value[3] = each.getStreGR();
				value[4] = each.getWinRate();
				value[5] = each.getFalseKeepRate();
				value[6] = each.getFalseFallRate();
				value[7] = each.getFalseRuinRate();
				value[8] = each.getHoleWinRate();
				value[9] = each.getSwitchFallRate();
				strengthenAppendMap.put(key, value);
			}
			CombineProperty.strengthenAppendMap = strengthenAppendMap;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private SysItem medalSysItem;
	public SysItem getMedalSysItem() throws Exception {
		if(null == medalSysItem){
			synchronized (this) {
				if(null == medalSysItem){
					medalSysItem = ServiceLocator.getService.getSysItemByItemId(GrowthMissionConstants.MEDAL_ID);
				}
			}
		}
		medalSysItem.setUnitType(Constants.DEFAULT_NUMBASE_TYPE);
		return medalSysItem;
	}
	
	private SysItem upgardmodSysItem;
	public synchronized SysItem getUpgardmodSysItem() throws Exception {
		if(null == upgardmodSysItem){
			synchronized (this) {
				if(null == upgardmodSysItem){
					upgardmodSysItem = ServiceLocator.getService.getSysItemByItemId(GrowthMissionConstants.UPGARDMOD_ID);
				}
			}
		}
		return upgardmodSysItem;
	}
	
	private SysItem addSucessSysItem;
	public SysItem getAddSucessSysItem() throws Exception {
		if(null == addSucessSysItem){
			synchronized (this) {
				if(null == addSucessSysItem){
					addSucessSysItem = ServiceLocator.getService.getSysItemByItemId(GrowthMissionConstants.ADDSUCESS_ID);
				}
			}
		}
		return addSucessSysItem;
	}
	private static final int PAGESIZE = 32;
	public String getLuckyPackagePage(final int level,final int pageNo) throws Exception{
		final String key = CacheUtil.sLuckyPackagePage(level, pageNo);
		return (String) loadValue(key, new CacheMissHandler() {
			@Override
			public Object loadFromDB(GetService service) throws Exception {
				int no = pageNo;
				List<SysChest> random = getChestListNoDeleted(Constants.PACKAGE_TYPE_RANDOM, level);
				LuckyPackageComparator com = new LuckyPackageComparator();
				Collections.sort(random,com);
				
				int itemCount = random.size();
				int pageCount = itemCount%PAGESIZE == 0 ?itemCount/PAGESIZE:(itemCount/PAGESIZE)+1;
				if(no<0||no>=pageCount){
					no = 0;
				}
				int fromIndex = no*PAGESIZE;
				int toIndex = no == pageCount-1?itemCount:(no+1)*PAGESIZE;
				List<SysChest> randomPage = random.subList(fromIndex, toIndex);
				return Converter.luckyPackages(randomPage,pageCount,no);
			}
		});
	}
	
	public void flushLuckyPackagePageCache() throws Exception{
		//level 1 2 3
		for(int level = 1;level<4;level++){
			List<SysChest> random = getChestListNoDeleted(Constants.PACKAGE_TYPE_RANDOM, level);
			int itemCount = random.size();
			int pageCount = itemCount%PAGESIZE == 0 ?itemCount/PAGESIZE:(itemCount/PAGESIZE)+1;
			for (int pageNo = 0; pageNo < pageCount; pageNo++) {
				mcc.deleteWithNoReply(CacheUtil.sLuckyPackagePage(level, pageNo));
			}
		}
	}
	
	public SysChest getRandomChestByLevelAndType(int level,int type) throws Exception {
		List<SysChest> randomList = getChestListNoDeleted(Constants.PACKAGE_TYPE_RANDOM, level);
		if(randomList.size()==0){
			log.error("Error in no sysItem in lucky package random "+level);
		}
		Map<Integer,Integer> map  = new HashMap<Integer,Integer>();
		SysChest random = null;
		if(type==1){
			for(SysChest item : randomList){
				map.put(item.getId(), item.getWeight());
			}
		}else if(type ==2){
			for(SysChest item : randomList){
				map.put(item.getId(), item.getWeight1());
			}
		}
		int itemId=CommonUtil.getRandomValueByWeights(map);
		for(SysChest sc : randomList){
			if(sc.getId() == itemId){
				random=sc;
			}
		}
		return random;
	}
	//新手保护，玩家第一次抽黄金彩盒，必拿到一件使用次数最多角色的精良武器
	public SysChest getExcellentWeaponForNewer(int characterID,int level)throws Exception{
		List<SysChest> randomList = getChestListNoDeleted(Constants.PACKAGE_TYPE_RANDOM, level);
		if(randomList.size()==0){
			log.error("Error in no sysItem in lucky package random "+level);
		}
	
		//得到对应角色的精良武器
		for(SysChest sc: randomList){
			if(sc.getSysItemId()==Constants.CHEST_WEAPON_FOR_NEWER.get(characterID)){
				return sc;
			}
		}
		
		return null;
	}

	public String getLuckyPackageRandomNames(final int level) throws Exception{
		final String key = CacheUtil.sLuckyPackageRandomNames(level);
		return (String) loadValue(key, new CacheMissHandler() {
			@Override
			public Object loadFromDB(GetService service) throws Exception {
				List<SysChest> random = getChestListNoDeleted(Constants.PACKAGE_TYPE_RANDOM, level);
				LuckyPackageComparator com = new LuckyPackageComparator();
				Collections.sort(random,com);
				StringBuilder sb = new StringBuilder();
				//{name="#$theItem.sysItem.name#",},
				for (SysChest sysChest : random) {
					sb.append("{name=\"").append(sysChest.getSysItem().getName()).append("\",},");
				}
				return sb.toString();
			}
		});
	}
	
	
	public BiochemicalCharacter convertBiochemicalCharacter(SysBioCharacter sysBioCharacter) throws Exception {
		SysCharacter sysCharacter =  new SysCharacter( sysBioCharacter.getCid(), sysBioCharacter.getName(),  
				sysBioCharacter.getRunSpeed(),  sysBioCharacter.getJumpSpeed(),  sysBioCharacter.getThrowSpeed(),  sysBioCharacter.getCostumeResource(),
				sysBioCharacter.getIsDefault(),  sysBioCharacter.getMaxHp(),  sysBioCharacter.getExHp(),  sysBioCharacter.getCost(),  
				sysBioCharacter.getDefaultLevel(),  sysBioCharacter.getIsFired(),  sysBioCharacter.getResourceName(),  sysBioCharacter.getIsEnable(),  
				sysBioCharacter.getControllerHeight(),sysBioCharacter.getControllerRadius(),  sysBioCharacter.getControllerCrouchHeight(),  sysBioCharacter.getScoreOffset());
		List<SysItem> weaponList = new ArrayList<SysItem>();
		String[] weapons = sysBioCharacter.getWeapons().split(",");
		for(String i: weapons ){
			weaponList.add(getService.getSysItemByItemId(Integer.parseInt(i)));
		}

		List<SysItem> costumeList = new ArrayList<SysItem>();
		String[] costumes = sysBioCharacter.getCostumes().split(",");
		for(String i: costumes ){
			costumeList.add(getService.getSysItemByItemId(Integer.parseInt(i)));
		}
		BiochemicalCharacter biochemicalCharacter = new BiochemicalCharacter(sysCharacter, weaponList, costumeList,"biochemical");
		biochemicalCharacter.setName(sysBioCharacter.getName());
		return biochemicalCharacter;
	}
	
	private List<SysBioCharacter> ordinaryDefaultSysBioCharacter = new ArrayList<SysBioCharacter>();
	private List<SysBioCharacter> especialDefaultSysBioCharacter = new ArrayList<SysBioCharacter>();
	private Map<Integer,SysBioCharacter> sysBioCharacterBySysItemId = new HashMap<Integer, SysBioCharacter>();
	
	public void flushSysBioCharacterCache(){
		List<SysBioCharacter> allSysBioCharacterList = sysBioCharacterDao.getAllSysBioCharacterList();
		List<SysBioCharacter> ordinaryDefaultSysBioCharacter = new ArrayList<SysBioCharacter>();
		List<SysBioCharacter> especialDefaultSysBioCharacter = new ArrayList<SysBioCharacter>();
		Map<Integer,SysBioCharacter> sysBioCharacterBySysItemId = new HashMap<Integer, SysBioCharacter>();
		for (SysBioCharacter sysBioCharacter : allSysBioCharacterList) {
			if(sysBioCharacter.getType() == BiochemicalConstants.ordinaryDefaultType){
				ordinaryDefaultSysBioCharacter.add(sysBioCharacter);
			}
			if(sysBioCharacter.getType() == BiochemicalConstants.especialDefaultType){
				especialDefaultSysBioCharacter.add(sysBioCharacter);
			}
			sysBioCharacterBySysItemId.put(sysBioCharacter.getSid(), sysBioCharacter);
		}
		this.ordinaryDefaultSysBioCharacter = ordinaryDefaultSysBioCharacter;
		this.especialDefaultSysBioCharacter = especialDefaultSysBioCharacter;
		this.sysBioCharacterBySysItemId = sysBioCharacterBySysItemId;
	}
	
	public List<BiochemicalCharacter> getBiochemicalCharacterList(int playerId) throws Exception{
		List<SysBioCharacter> list = new ArrayList<SysBioCharacter>(2);
		List<PlayerItem> playerBuffListById = getPlayerBuffListById(playerId);
		PlayerItem ordinarybuff = null;
		for (PlayerItem playerItem : playerBuffListById) {
			if(playerItem.getSysItem().getIBuffId() == BiochemicalConstants.ordinaryBuffId){
				ordinarybuff = playerItem;
				break;
			}
		}
		if(null == ordinarybuff){
			list.addAll(ordinaryDefaultSysBioCharacter);
		}else{
			int sysItemId = ordinarybuff.getItemId();
			list.add(sysBioCharacterBySysItemId.get(sysItemId));
		}
		PlayerItem especialbuff = null;
		for (PlayerItem playerItem : playerBuffListById) {
			if(playerItem.getSysItem().getIBuffId() == BiochemicalConstants.especialBuffId){
				especialbuff = playerItem;
				break;
			}
		}
		if(null == especialbuff){
			list.addAll(especialDefaultSysBioCharacter);
		}else{
			int sysItemId = especialbuff.getItemId();
			list.add(sysBioCharacterBySysItemId.get(sysItemId));
		}
		
		List<BiochemicalCharacter> returnValue = new ArrayList<BiochemicalCharacter>(list.size());
		for (SysBioCharacter sysBioCharacter : list) {
			returnValue.add(convertBiochemicalCharacter(sysBioCharacter));
		}
		
		return returnValue;
	}
	
	/**
	 * @param playerId
	 * @return
	 * ps:获取时出现I/O等异常 会初始化处理
	 * @throws Exception 
	 */
	public PlayerMelting getPlayerMelting(final int playerId,final boolean online) throws Exception{
		PlayerMelting playerMelting = playerMeltingDao.getPlayerMelting(playerId);
		try {
			if(null == playerMelting){
				playerMelting = new PlayerMelting(playerId);
				playerMeltingDao.createPlayerMelting(playerMelting);
			}else{
				if(online){
					playerMelting.playerOnline();
				}
				playerMelting.calibrate();
				ServiceLocator.updateService.updatePlayerMelting(playerMelting);
			}
		} catch (Exception e) {
			ServiceLocator.meltingLog.error(LogUtils.JoinerByColon.join("GetService","getMeltingEnergy",playerId),e);
			throw e;
		}
		return playerMelting;
	}
	
	public int getMeltingLevel(final int playerId){
		return 1;
	}
	
	public int getMeltingGrooveNum(final int playerId){
		return getMeltingLevel(playerId)+1;
	}
	
	public int[] getMeltingOutput(int playerId,Integer[] meltingInputs,Integer meltingProcessingUnits){
		return new int[]{0,1,2};
	}
	
	public int getLookoverFc(double meltingQuality){
		//  -0.0006x^2+3.7006x+15.868
		//INT(熔炼值*4.5)+1
		//return (int) (-0.0006*Math.pow(meltingQuality, 2)+3.7006*meltingQuality+15.868);
		
		double factor = meltingQuality<MeltingConstants.MeltingResultLimit[0]?0.337:(meltingQuality<MeltingConstants.MeltingResultLimit[1]?0.412:2.35);
		return (int) (Math.ceil(meltingQuality*factor));
	}
	
	public MeltingReslut lookoverMeltingOutput(int playerId,int index) throws Exception{
		MeltingReslut meltingReslut = new MeltingReslut();
		//偷看一次的fc点= -0.0029*熔炼值^2+7.1496*熔炼值+12.767，向下取整
		//  -0.0006x^2+3.7006x+15.868
		double meltingQuality = getPlayerMelting(playerId, false).getRemaind()*10;
		int cost = getLookoverFc(meltingQuality);

		PlayerInfo playerInfo = getService.getPlayerInfoById(playerId);
		int cr = playerInfo.getXunleiPoint();
		if (cr < cost) {
			meltingReslut.setResult(1);
			return meltingReslut;
		} else {
			int leftMoney = cr - cost;
			final PayLog payLog = new PayLog();
			payLog.setUserId(getService.getSimplePlayerById(playerId).getUserName());
			payLog.setPlayerId(playerId);
			payLog.setItemId(0);
			payLog.setItemType(0);
			payLog.setPayType(2);
			payLog.setCreateTime(new Date());
			payLog.setLeftMoney(leftMoney);
			payLog.setPayUse(1);
			payLog.setItemName("熔炼偷看");
			payLog.setPayAmount(cost);
			final Runnable writePayLog = new Runnable() {
				@Override
				public void run() {
					ServiceLocator.createService.createPayLog(payLog);

				}
			};
			ServiceLocator.asynTakService.execute(writePayLog);
			
			playerInfo.setXunleiPoint(leftMoney);
			ServiceLocator.updateService.updatePlayerActivity(Constants.ACTION_ACTIVITY.PAY_FC.getValue(), playerId, null, cost, 0, null, null);
			playerInfoDao.update(playerInfo);
			mcc.delete(CacheUtil.oPlayerInfo(playerId));
			soClient.messageUpdatePlayerMoney(getPlayerById(playerId));
		}
		
		if (ConfigurationUtil.SWITCH_XUNLEI_LOG.getIsOn()) {
			Player player=getService.getPlayerById(playerId);
			PlayerMelting playerMelting=getPlayerMelting(playerId, false);
			
				ServiceLocator.nosqlService.addXunleiLog("8.6" + Constants.XUNLEI_LOG_DELIMITER +player.getUserName()
						+ Constants.XUNLEI_LOG_DELIMITER + player.getName()
						+ Constants.XUNLEI_LOG_DELIMITER + player.getRank()
						+ Constants.XUNLEI_LOG_DELIMITER + playerMelting.getLevel()
						+ Constants.XUNLEI_LOG_DELIMITER + cost
						+ Constants.XUNLEI_LOG_DELIMITER + playerInfo.getXunleiPoint()
						+ Constants.XUNLEI_LOG_DELIMITER + CommonUtil.simpleDateFormat.format(new Date()));
		}
//		List<Integer> transform = Lists.newArrayList(Iterables.transform(MeltingConstants.splitterByColon.split(mcc.<String>get(CacheUtil.MeltingAward(playerId))), MeltingConstants.functionStrToInt));
//		transform.set(index, transform.get(index+3));
//		mcc.set(CacheUtil.MeltingAward(playerId), Constants.CACHE_TIMEOUT_DAY, MeltingConstants.JoinerByColon.join(transform));

//		ServiceLocator.meltingLog.debug(LogUtils.JoinerByTab.join("lookoverMeltingOutput",playerId,index,cost,transform.get(index+3)));
		
		meltingReslut.setMeltingAward(getMeltingAward(playerId, index));
		meltingReslut.setMt(32);
		return meltingReslut;
	}
	
	public MeltingAward getMeltingAward(int playerId,int index) throws Exception{
		int[] result = mcc.<int[]>get(CacheUtil.MeltingAward(playerId));
		if(index>result.length-1||index<0){
			index = 0;
		}
		int itemId = result[index*2];
		SysItem item = getSysItemByItemId(itemId);
		int unit =result[index*2+1];
		return new MeltingAward(itemId, item, unit, 1);
	}
	
	
	public MeltingReslut getMeltingOutput(int playerId,int index) throws Exception{
		MeltingReslut meltingReslut = new MeltingReslut();
		int[] result = mcc.<int[]>get(CacheUtil.MeltingAward(playerId));
		if(null == result){
			meltingReslut.setResult(1);
			return meltingReslut;
		}
		//List<Integer> transform = Lists.transform(Lists.newArrayList(MeltingConstants.splitterByColon.split(str)), MeltingConstants.functionStrToInt);
		//MeltingAward meltingAward = meltingAwardDao.getMeltingAward(transform.get(index));
		
//		if(meltingAward.getType() == 5){
//			ServiceLocator.createService.awardCALToPlayer(getPlayerById(playerId),Integer.parseInt(meltingAward.getItem().getIValue()));
//		}else{
//		}
		if(index>result.length-1||index<0){
			index = 0;
		}
		int itemId = result[index*2];
		SysItem item = getSysItemByItemId(itemId);
		int unit =result[index*2+1];
		MeltingAward meltingAward =  new MeltingAward(itemId, item, unit, 1);
		ServiceLocator.createService.sendItem(meltingAward.getItem(), getPlayerById(playerId), new Payment(meltingAward.getUnit(), meltingAward.getUnitType()), Constants.BOOLEAN_NO, Constants.BOOLEAN_YES, Constants.BOOLEAN_NO);
		
		ServiceLocator.meltingLog.debug(LogUtils.JoinerByTab.join("getMeltingOutput",playerId,index,meltingAward.getItemId(),item.getDisplayNameCN(),meltingAward.getUnit(),meltingAward.getUnitType()));
		if (ConfigurationUtil.SWITCH_XUNLEI_LOG.getIsOn()) {
			
			 Player player=getService.getPlayerById(playerId);
			 PlayerMelting playerMelting=getPlayerMelting(playerId, false);
				ServiceLocator.nosqlService.addXunleiLog("8.7" + Constants.XUNLEI_LOG_DELIMITER +player.getUserName()
						+ Constants.XUNLEI_LOG_DELIMITER + player.getName()
						+ Constants.XUNLEI_LOG_DELIMITER + player.getRank()
						+ Constants.XUNLEI_LOG_DELIMITER + playerMelting.getLevel()
						+ Constants.XUNLEI_LOG_DELIMITER + item.getDisplayNameCN()
						+ Constants.XUNLEI_LOG_DELIMITER + unit
						+ Constants.XUNLEI_LOG_DELIMITER + CommonUtil.simpleDateFormat.format(new Date()));
				
				if(item.getId()==GrowthMissionConstants.MEDAL_ID){
					int chipNum=getService.getMedolNumByPlayerId(playerId);
					nosqlService.addXunleiLog("7.4"
							+ Constants.XUNLEI_LOG_DELIMITER + player.getUserName()
							+ Constants.XUNLEI_LOG_DELIMITER + player.getName()
							+ Constants.XUNLEI_LOG_DELIMITER + 1
							+ Constants.XUNLEI_LOG_DELIMITER + meltingAward.getUnit()
							+ Constants.XUNLEI_LOG_DELIMITER + chipNum
							+ Constants.XUNLEI_LOG_DELIMITER+ 12
							+ Constants.XUNLEI_LOG_DELIMITER+ CommonUtil.simpleDateFormat.format(new Date())
							);
				}
				
				if(item.getId()==GrowthMissionConstants.RELIVE_COIN){
					int reliveCoinNum=getService.getReliveCoinNumByPlayerId(playerId);
					nosqlService.addXunleiLog("18.1"
							+ Constants.XUNLEI_LOG_DELIMITER + player.getUserName()
							+ Constants.XUNLEI_LOG_DELIMITER + player.getName()
							+ Constants.XUNLEI_LOG_DELIMITER + 1
							+ Constants.XUNLEI_LOG_DELIMITER + meltingAward.getUnit()
							+ Constants.XUNLEI_LOG_DELIMITER + reliveCoinNum
							+ Constants.XUNLEI_LOG_DELIMITER+ 3
							+ Constants.XUNLEI_LOG_DELIMITER+ CommonUtil.simpleDateFormat.format(new Date())
							);
				}
				
		}
		mcc.deleteWithNoReply(CacheUtil.MeltingAward(playerId));
		
		meltingReslut.setMeltingAward(meltingAward);
		meltingReslut.setMt(32);
		return meltingReslut;
	}
	
	Ordering<Multiset.Entry<MeltingAward>> orderingByCountDesc = new Ordering<Multiset.Entry<MeltingAward>>() {
		@Override
		public int compare( Multiset.Entry<MeltingAward> left,  Multiset.Entry<MeltingAward> right) {
			return Ints.compare(left.getCount(),right.getCount());
		}
	};
	Function<Multiset.Entry<MeltingAward>, MeltingAward> transform = new Function<Multiset.Entry<MeltingAward>, MeltingAward>() {
		@Override
		public MeltingAward apply(Multiset.Entry<MeltingAward> input) {
			MeltingAward element = input.getElement();
			try {
				element.setItem(ServiceLocator.getService.getSysItemByItemId(element.getItemId()));
			} catch (Exception e) {
				e.printStackTrace();
			}
			return element;
		}
	};
	public List<MeltingAward> getMeltingOutputList() throws Exception{
		List<MeltingAward> result = new ArrayList<MeltingAward>(20);
		
//		List<Multiset.Entry<MeltingAward>> ids = new ArrayList<Multiset.Entry<MeltingAward>>(20);
		int[] items = {4888,4800,4479,4889};
		for(int i =20;i<120;i+=20){
			int mq = i*10;
			int unit = mq/4+1;
			
			for (int itemId : items) {
				SysItem item = getSysItemByItemId(itemId);
				result.add(new MeltingAward(itemId, item, unit, 1));
			}
			
//			Multiset<MeltingAward> calcMeltingAwardPool = ServiceLocator.updateService.calcMeltingAwardPool();
//			Set<Multiset.Entry<MeltingAward>> filter = Sets.filter(calcMeltingAwardPool.entrySet(), new Predicate<Multiset.Entry<MeltingAward>>() {
//				@Override
//				public boolean apply(Multiset.Entry<MeltingAward> input) {
//					return input.getElement().getType() != 5;
//				}
//			});
//			ids.addAll(orderingByCountDesc.greatestOf(filter, 4));
		}
//		return Lists.transform(ids, transform);
		return result;
	}
	
	public List<TeamBuffView> getTeamBuffList(PlayerTeam playerTeam,Team team,int rank) throws Exception{
		Collection<SysTeamBuff> sysTeamBuffsByRank = sysTeamBuffDao.getSysTeamBuffsByRank(rank);

		List<TeamBuffView> result = new ArrayList<TeamBuffView>(sysTeamBuffsByRank.size());
		
		Map<Integer, TeamBuff> teamBuffsByTeamId = teamBuffDao.getTeamBuffsByTeamId(team.getId());
		
		
		for (SysTeamBuff sysTeamBuff : sysTeamBuffsByRank) {
			if(!sysTeamBuff.isEnable())	continue;
			
			SysItem sysItem = getSysItemByItemId(sysTeamBuff.getSysItemId());

			if(null == sysItem) continue;
			
			TeamBuff teamBuff = teamBuffsByTeamId.get(sysTeamBuff.getId());
			int status = 1;//0：不可用	1：待解锁	2:待激活		3：已激活 
			int expireMinutesLeft = 0;
			if(team.getLevel()>=rank){
				if(null != teamBuff){
					PlayerItem playerBuff = getPlayerBuff(playerTeam.getPlayerId(),sysItem.getIBuffId());
//					expireMinutesLeft = playerBuff == null?0:playerBuff.getExpireMinutesLeft();
					//此处，因改版战队等级11级战队强化buff需要被删除，会导致部分玩家已经解锁一旦被删除该部分玩家就不能继续强化16，所以该处限定，如同类型强化buff作用强化等级一致，即使id不一致也认为该buff已被解锁
					status = null == playerBuff||playerBuff.getItemId()!=sysTeamBuff.getSysItemId()?null != playerBuff&&playerBuff.getSysItem().getIBuffId()==61&&"17".equals(playerBuff.getSysItem().getIValue())?3:2:3;
				}else if(playerTeam.getJob() == TeamConstants.TEAMJOB.TEAM_CAPTAIN.getValue()){
					status = 1;
				}
			}
			result.add(new TeamBuffView(sysTeamBuff, teamBuff, sysItem,expireMinutesLeft, status));
		}
		
		Collections.sort(result, new Comparator<TeamBuffView>(){
		
			@Override
			public int compare(TeamBuffView o1, TeamBuffView o2) {
				//排序武器
				if((o1.getSysItem().getIBuffId()==0 && o2.getSysItem().getIBuffId()==0)&&(!StringUtils.isNullOrEmpty(o1.getSysItem().getCharacterId())&&!StringUtils.isNullOrEmpty(o2.getSysItem().getCharacterId())&&(!"0".equals(o1.getSysItem().getCharacterId()) && !"0".equals(o1.getSysItem().getCharacterId()))))
				{
					return Integer.valueOf(o1.getSysItem().getCharacterId()).compareTo(Integer.valueOf(o2.getSysItem().getCharacterId()));
				}
				else
					return o1.getSysItem().getIBuffId().compareTo(o2.getSysItem().getIBuffId());
			}});
		
		return result;
	}
	
	public int getMaxTeamLevel() throws Exception{
		Integer maxTeamLevel = null;
		
		try {
			maxTeamLevel = mcc.<Integer>get(TeamConstants.Max_Team_Level);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(null == maxTeamLevel){
			maxTeamLevel = getTeamDao().getMaxTeamLevel();
			mcc.set(TeamConstants.Max_Team_Level, Constants.CACHE_TIMEOUT_DAY, maxTeamLevel,Constants.CACHE_TIMEOUT);
		}
			
		return maxTeamLevel;
	}
	
	public void updateMaxTeamLevel(int level) throws Exception{
		for(int i = 0;i<3;i++){
			GetsResponse<Integer> maxTeamLevel = null;
			try {
				maxTeamLevel = mcc.<Integer>gets(TeamConstants.Max_Team_Level);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			if(null == maxTeamLevel){
				int result = getTeamDao().getMaxTeamLevel();
				mcc.set(TeamConstants.Max_Team_Level, Constants.CACHE_TIMEOUT_DAY, result,Constants.CACHE_TIMEOUT);
			}else if(level > maxTeamLevel.getValue()&&mcc.cas(TeamConstants.Max_Team_Level, Constants.CACHE_TIMEOUT_DAY, level, Constants.CACHE_TIMEOUT, maxTeamLevel.getCas())){
				break;
			}
		}
	}

	public int getTeamBuffStrengthen(int playerId) throws Exception{
		PlayerItem playerBuff = getPlayerBuff(playerId, TeamConstants.Team_Buff_Strengthen_BuffId);
		if(null != playerBuff){
			return Integer.parseInt(playerBuff.getSysItem().getIValue());
		}
		return 0;
	}
	
	public int getVipBuffStrength(int playerId) throws Exception{
		PlayerItem playerBuff = getPlayerBuff(playerId, Constants.DEFAULT_VIP_STRENGTH_BUFF);
		if(null != playerBuff){
			return Integer.parseInt(playerBuff.getSysItem().getIValue());
		}
		return 0;
	}
	
	public int getMaxStrengthLevel(int playerId){
		int maxLevel=Constants.MAX_STRENGTH_LEVEL_DEFAULT;
		try {	
			PlayerItem playerBuff = getPlayerBuff(playerId, TeamConstants.Team_Buff_MaxStrengthLevel_BuffId);
			Player player=getPlayerById(playerId);
			if(null != playerBuff){
				maxLevel=Integer.parseInt(playerBuff.getSysItem().getIValue());
			}
			//vip5\6 级增加强化等级上限
			if(null!=player && player.getIsVip()>=5){
				if(player.getIsVip()==5){
					maxLevel= maxLevel > Constants.MAX_STRENGTH_LEVEL_DEFAULT_FOR_VIP5? maxLevel : Constants.MAX_STRENGTH_LEVEL_DEFAULT_FOR_VIP5;
				}else if(player.getIsVip()==6){
					maxLevel= maxLevel > Constants.MAX_STRENGTH_LEVEL_DEFAULT_FOR_VIP6? maxLevel : Constants.MAX_STRENGTH_LEVEL_DEFAULT_FOR_VIP6;
				}else if(player.getIsVip()==7){
					maxLevel= maxLevel > Constants.MAX_STRENGTH_LEVEL_DEFAULT_FOR_VIP7? maxLevel : Constants.MAX_STRENGTH_LEVEL_DEFAULT_FOR_VIP7;
				}else if(player.getIsVip()==8){
					maxLevel= maxLevel > Constants.MAX_STRENGTH_LEVEL_DEFAULT_FOR_VIP8? maxLevel : Constants.MAX_STRENGTH_LEVEL_DEFAULT_FOR_VIP8;
				}else if(player.getIsVip()==9){
					maxLevel= maxLevel > Constants.MAX_STRENGTH_LEVEL_DEFAULT_FOR_VIP9? maxLevel : Constants.MAX_STRENGTH_LEVEL_DEFAULT_FOR_VIP9;
				}
			}
			// 活动
			List<PlayerActivity> list = getService.getPlayerActivityList(playerId, 0);
			for (PlayerActivity pa : list) {
				if(pa.getAction()==Constants.ACTION_ACTIVITY.ENHANCE_MAX_STRENGTH_LEVEL.getValue()){
					maxLevel =maxLevel > pa.getSysActivity().getTargetNum() ? maxLevel : pa.getSysActivity().getTargetNum();
				}
			}

		} catch (Exception e) {
			log.error("GetService.getMaxStrengthLevel "+playerId, e);
		}
		return maxLevel;
	}
	
	public int getRecoreRanking(int teamId) throws Exception{
		int rank = (int)nosqlService.getNosql().revRankInSortedSet(Constants.TEAMTOP_KEY_PREFIX+Constants.TEAM_TOP_TYPE.BATTLERESULT.getValue(), String.valueOf(teamId));
		return rank+1;
	}
	public int getFightRanking(int teamId){
		return 0;
	}
	
	public int getTeamFight(Team team) throws Exception{
		List<PlayerTeam> memberList = team.getMemberList();
		for (PlayerTeam playerTeam : memberList) {
			playerTeam.setFight(getPlayerById(playerTeam.getPlayerId()).getMaxFightNum());
		}
		
		Collections.sort(memberList, new Comparator<PlayerTeam>() {
			@Override
			public int compare(PlayerTeam a, PlayerTeam b) {
				return Ints.compare(b.getFight(), a.getFight());
			}
		});
		int result = 0;
		
		for (int i =0;i<memberList.size();i++) {
			result += memberList.get(i).getFight() * Math.pow(0.98, i);
		}
		return result;
	}
	
	public boolean isBurnt(final int playerId){
		try {
			Long lashBurnt = (Long) loadValue(CacheUtil.isBurnt(playerId), new CacheMissHandler() {
				@Override
				public Long loadFromDB(GetService service) throws Exception {
					String string = getNosqlService().nosql.get(CacheUtil.isBurnt(playerId));
					return null != string?Long.parseLong(string):0;
				}
			});
			long currentTimeMillis = System.currentTimeMillis();
			if (lashBurnt!=null&&CacheUtil.isToday(lashBurnt)) {
				return false;
			}
			mcc.set(CacheUtil.isBurnt(playerId), Constants.CACHE_TIMEOUT_DAY,currentTimeMillis,Constants.CACHE_TIMEOUT);
			getNosqlService().nosql.set(CacheUtil.isBurnt(playerId), currentTimeMillis+"");
		} catch (Exception e) {
			log.error("isBurnt",e);
		}
		return true;
	}
	/**
	 * 随机玩家拼图位置
	 * @param playerId 玩家id
	 * @param payType 兑换方式：1为黄金卡 2为勋章
	 * @param playerPinTuFlags 玩家拼图标志位
	 * @return int
	 * @throws Exception
	 */
	public int randomPlayerPtIndex(int playerId,int payType,String playerPinTuFlags) throws Exception{
		String flagWts = nosqlService.getNosql().get(Constants.PLAYER_PT_FLAG_GET_WEIGHTS_KEY + payType);
		if(flagWts==null||!flagWts.matches("[0-9]*:[0-9]*")){
			flagWts = Constants.PLAYER_PT_FLAG_GET_WEIGHTS_DFT_VALS[payType-1];
			nosqlService.getNosql().set(Constants.PLAYER_PT_FLAG_GET_WEIGHTS_KEY + payType, flagWts);
		}
		int oneFlagWeight = StringUtil.toInt(flagWts.split(":")[0]);
		int zeroFlagWeight = StringUtil.toInt(flagWts.split(":")[1]);
		Map<Integer,Integer> map  = new HashMap<Integer,Integer>();
		int count = 0;
		for(char c : playerPinTuFlags.substring(0, playerPinTuFlags.length()-1).toCharArray()){
			if(c=='0'){
				map.put(count++, zeroFlagWeight);
			}else{
				map.put(count++, oneFlagWeight);
			}
		}
		return  CommonUtil.getRandomValueByWeights(map);
		
	}
	/**
	 * 获得蓝图熔炼所需物品以及熔炼产物
	 * @param sysItem
	 * @return {@link String}
	 */
	public String getMeltingItemStr(SysItem sysItem){
		Integer[] array = Iterables.toArray(Iterables.transform(MeltingConstants.splitterByUnderScore.split(sysItem.getResourceChangeable()), MeltingConstants.functionStrToInt), Integer.class);
		HashMultiset<Integer> moduleMultiset = HashMultiset.create(Iterables.transform(MeltingConstants.splitterByColon.split(sysItem.getResourceStable()), MeltingConstants.functionStrToInt));
		StringBuilder sb=new StringBuilder();
		if(array.length!=0&&moduleMultiset.size()!=0){
			try{
				sb.append("items={");
				for(int i:moduleMultiset){
					SysItem si=getService.getSysItemByItemId(i);
					sb.append("{");
					sb.append(si.getId()).append(",\"").append(si.getName()).append("\",\"").append(si.getDisplayName()).append("\",");
					sb.append("},");
				}
				sb.append("},");
				sb.append("result={");
				SysItem si=getService.getSysItemByItemId(array[0]);
				sb.append("{");
				sb.append(si.getId()).append(",\"").append(si.getName()).append("\",\"").append(si.getDisplayName())
					.append("\",").append(array[1]).append(",").append(array[2]).append(",");
				sb.append("},");
				sb.append("},");
			}catch (Exception e) {
				sb.append("items={");
				sb.append("},");
				sb.append("result={");
				sb.append("},");
			}
		}
		return sb.toString();
	}
	public Map<Integer,TeamProclamation>   getTeamProclamationByTeamId(Integer teamId){
		return teamProclamationDao.getTeamProclamationByTeamId(teamId);
	}
	public Map<Integer,TeamRecord>   getTeamRecordByTeamId(Integer teamId){
		return teamRecordDao.getTeamRecordByTeamId(teamId);
	}
	public List<TeamRecord> getTeamRecordListByTeamId(int fid){
		return teamRecordDao.getTeamRecordListByTeamId(fid);
	}
	/**
	 * 获得限时抢拍开始结束时间,获得发送道具任务的开始时间
	 * @param type
	 * @return
	 */
	public Date getCompeteBuyTime(int type){
		Calendar time = Calendar.getInstance();
		time.set(Calendar.SECOND, 0);
		time.set(Calendar.MILLISECOND, 0);
		
		switch (type) {
		case 0:
			time.set(Calendar.DAY_OF_WEEK, Constants.CMPT_BUY_STT_WEEK_DAY);
			time.set(Calendar.HOUR_OF_DAY,Constants.CMPT_BUY_STT_TIME_HOUR);
			time.set(Calendar.MINUTE,0);
			break;
		case 1:
			time.set(Calendar.DAY_OF_WEEK, Constants.CMPT_BUY_END_WEEK_DAY);
			time.set(Calendar.HOUR_OF_DAY,Constants.CMPT_BUY_END_TIME_HOUR);
			time.set(Calendar.MINUTE,0);
			break;
		case 2:
			time.set(Calendar.DAY_OF_WEEK, Constants.CMPT_BUY_SENDITEM_START_WEEK_DAY);
			time.set(Calendar.HOUR_OF_DAY,Constants.CMPT_BUY_OVR_TSK_TIME_HOUR);
		//TODO 	set the time just for test
			time.set(Calendar.MINUTE,Constants.CMPT_BUY_SENDITEM_DELAY);
		//	time.set(Calendar.MINUTE,0);
			break;
		default:
			break;
		}
		
		return time.getTime();
	}
	
	/**
	 * 获取限时抢拍发送奖励时间
	 *  
	 * */
	public Date getCompeteBuySendGoodsTime(){
		int sendWeekDay = Constants.CMPT_BUY_SENDITEM_START_WEEK_DAY;
		int endWeekDay = Constants.CMPT_BUY_END_WEEK_DAY;
		endWeekDay=endWeekDay==1?7:--endWeekDay; 
		sendWeekDay=sendWeekDay==1?7:--sendWeekDay;
		Calendar toDay = Calendar.getInstance();
		toDay.set(Calendar.SECOND, 0); 
		toDay.set(Calendar.MILLISECOND, 0);
		toDay.set(Calendar.HOUR_OF_DAY,Constants.CMPT_BUY_OVR_TSK_TIME_HOUR);
		toDay.set(Calendar.MINUTE, Constants.CMPT_BUY_SENDITEM_DELAY);
		int cNum = toDay.get(Calendar.DAY_OF_WEEK);
		cNum=cNum==1?7:--cNum;
		int mNum = cNum;  
		int mCou = 0;
		while(mNum != endWeekDay){
			if(mNum<7)
			{
				mNum++;
			}else{ 
				mNum=1;
			}
			mCou++;
		}  
		
		int mNum2 = endWeekDay;
		int mCou2 = 0;
		while(mNum2 != sendWeekDay){
			if(mNum2<7)
			{
				mNum2++;
			}else{ 
				mNum2=1;
			}
			mCou2++;
		}   
		
		return DateUtils.addDays(toDay.getTime(), mCou2 + mCou);
	}
	
	/**
	 *  获得该次开始抢拍时间
	 *  
	 * */
	public Date getCompeteBuyTime(){
		int startWeekDay = Constants.CMPT_BUY_STT_WEEK_DAY;
		int endWeekDay = Constants.CMPT_BUY_END_WEEK_DAY;
		endWeekDay=endWeekDay==1?7:--endWeekDay; 
		startWeekDay=startWeekDay==1?7:--startWeekDay;
		Calendar toDay = Calendar.getInstance();
		toDay.set(Calendar.HOUR_OF_DAY,Constants.CMPT_BUY_STT_TIME_HOUR);
		int cNum = toDay.get(Calendar.DAY_OF_WEEK);
		cNum=cNum==1?7:--cNum;
		int mNum = cNum;  
		int mCou = 0;
		while(mNum != endWeekDay){
			if(mNum<7)
			{
				mNum++;
			}else{ 
				mNum=1;
			}
			mCou++;
		}  
		
		int mNum2 = startWeekDay;
		int mCou2 = 0;
		while(mNum2 != endWeekDay){
			if(mNum2<7)
			{
				mNum2++;
			}else{ 
				mNum2=1;
			}
			mCou2++;
		}   
		
		return DateUtils.addDays(toDay.getTime(), -(mCou2-mCou));
	}
	 
	/**
	 * 获得限时抢拍时间
	 * 
	 * @param startWeekDay 开始某礼拜，startHourse 开始某时，endWeekDay 结束某礼拜，endHourse 结束某时
	 * @return String{时间,拍卖状态}
	 * @author OuYangGuang
	 * */
	public String[] getCompeteBuyTime(int startWeekDay,int startHourse,int endWeekDay,int endHourse){
		String retTimeStr = new SimpleDateFormat("yyyyMMdd").format(new Date());
		Date now = new Date();
		Calendar toDay = Calendar.getInstance();
		startWeekDay=startWeekDay==1?7:--startWeekDay; 
		startHourse=startHourse==0?24:startHourse;
		endWeekDay=endWeekDay==1?7:--endWeekDay; 
		endHourse=endHourse==0?24:endHourse;
		int cNum = toDay.get(Calendar.DAY_OF_WEEK);
		cNum=cNum==1?7:--cNum;
		
		//0、下次抢购时间，1、最近一次抢购时间(当天)，2、限时抢购进行中
		String timeType = "0";
		
		int mNum = cNum; 
		int mCou = 0;
		while(mNum != endWeekDay){
			if(mNum<7)
			{
				mNum++;
			}else{
				mNum=1;
			}
			mCou++;
		} 
		int endDay = Integer.parseInt(new SimpleDateFormat("dd").format(now))+ mCou;
		int toDayI = Integer.parseInt(new SimpleDateFormat("dd").format(now));
		int cHourse = Integer.parseInt(new SimpleDateFormat("HH").format(now));
		int cendHourse = endDay==toDayI?endHourse:24;
		//1礼拜一 7礼拜天
		if(cNum >= startWeekDay || cNum <= endWeekDay){	//在区间内,只有两种状态 开拍倒计时，或 停拍倒计时
			if(cHourse < startHourse && cNum==startWeekDay){
				timeType="1";//开拍倒计时 
				Calendar time = Calendar.getInstance();
				time.set(Calendar.SECOND, 0);
				time.set(Calendar.MILLISECOND, 0); 
				time.set(Calendar.HOUR_OF_DAY,startHourse); 
				time.set(Calendar.MINUTE,0);
				
				retTimeStr = (int)((time.getTime().getTime()-now.getTime())/1000)+"";
			}else if(toDayI<endDay?true:cHourse < cendHourse){ //正在拍
				timeType="2";
				Calendar time = Calendar.getInstance();
				time.set(Calendar.DAY_OF_MONTH, endDay);
				time.set(Calendar.SECOND, 0);
				time.set(Calendar.MILLISECOND, 0);
				time.set(Calendar.HOUR_OF_DAY,cendHourse);
				time.set(Calendar.MINUTE,0);
				
				retTimeStr = (int)((time.getTime().getTime()-now.getTime())/1000)+"";
			}else{	//下次拍卖时间
				timeType="0";
				int mNum2 = cNum;
				int mCou2 = 0;
				while(mNum2 != startWeekDay){
					if(mNum2<7)
					{ 
						mNum2++;
					}else{
						mNum2=1;
					}
					mCou2++;
				}
				retTimeStr = new SimpleDateFormat("yyyyMMdd").format(DateUtils.addDays(now, mCou2));
			}
		}else{ //不在区间
			timeType="0";
			int mNum2 = cNum;
			int mCou2 = 0;
			while(mNum2 != startWeekDay){ 
				if(mNum2<7)
				{
					mNum2++;
				}else{
					mNum2=1; 
				}
				mCou2++;
			}
			retTimeStr = new SimpleDateFormat("yyyyMMdd").format(DateUtils.addDays(now, mCou2));
		}
	 
		return new String[]{retTimeStr,timeType};
	}
	
	/**
	 * 获得资源争夺战挑战开始结束时间,获得发送道具任务的开始时间
	 * @param type  0开始时间 1结束时间 2 结束发剩余资源时间
	 * @return
	 */
	public Calendar getZYZDZChallengeTime(int type){
		Calendar time = Calendar.getInstance();
		time.set(Calendar.SECOND, 0);
		time.set(Calendar.MILLISECOND, 0);
		time.set(Calendar.MINUTE,0);
		Calendar today=Calendar.getInstance();
		today.set(Calendar.SECOND, 0);
		today.set(Calendar.MILLISECOND, 0);
		today.set(Calendar.MINUTE,0);
		
		switch (type) {
		case 0:
			time.set(Calendar.DAY_OF_WEEK, Constants.TeamSpaceConstants.CMPT_RESWAR_STT_WEEK_DAY);
			time.set(Calendar.HOUR_OF_DAY,Constants.TeamSpaceConstants.CMPT_RESWAR_STT_TIME_HOUR);
			today.set(Calendar.HOUR_OF_DAY,Constants.TeamSpaceConstants.CMPT_RESWAR_STT_TIME_HOUR);
			break;
		case 1:
			time.set(Calendar.DAY_OF_WEEK, Constants.TeamSpaceConstants.CMPT_RESWAR_END_WEEK_DAY);
			time.set(Calendar.HOUR_OF_DAY,Constants.TeamSpaceConstants.CMPT_RESWAR_END_TIME_HOUR);
			today.set(Calendar.HOUR_OF_DAY,Constants.TeamSpaceConstants.CMPT_RESWAR_END_TIME_HOUR);
			break;
		case 2:
			time.set(Calendar.DAY_OF_WEEK, Constants.TeamSpaceConstants.CMPT_RESWAR_SEND_RES_WEEK_DAY);
			time.set(Calendar.HOUR_OF_DAY,Constants.TeamSpaceConstants.CMPT_RESWAR_SEND_RES_WEEK_HOUR);
			today.set(Calendar.HOUR_OF_DAY,Constants.TeamSpaceConstants.CMPT_RESWAR_SEND_RES_WEEK_HOUR);
			break;
		default:
			break;
		}
		if(time.getTime().before(today.getTime())){//如果挑战日期小于当前日期，挑战时间+1周
			time.add(Calendar.DATE, Calendar.DAY_OF_WEEK);
		}
		return time;
	}	
	
	/**
	 * 
	 * @param vipRank
	 * @return vip专属名片
	 * @throws Exception
	 */
	public SysItem getVipCard(int vipRank)throws Exception{
		SysItem vipCard=null;
		List<SysItem> allBuffItem=getSysItemByIID(1, Constants.DEFAULT_ITEM_TYPE);
		for(SysItem si:allBuffItem){
			if(si.getIBuffId()==Constants.DEFAULT_CARD_BUFF_ID&&si.getSubType()==2
					&&si.getIsVip()==vipRank){
				vipCard=si;
				break;
			}
		}
		return vipCard;
	}
	/**
	 * 
	 * @param vipRank
	 * @return vip强化buff
	 * @throws Exception
	 */
	public SysItem getVipStrengthBuff(int vipRank)throws Exception{
		SysItem vipStrengthBuffItem=null;
		List<SysItem> allBuffItem=getSysItemByIID(1, Constants.DEFAULT_ITEM_TYPE);
		
		for(SysItem si:allBuffItem){
			if(si.getIBuffId()==Constants.DEFAULT_VIP_STRENGTH_BUFF
					&&si.getIsVip()==vipRank){
				vipStrengthBuffItem=si;
				break;
			}
		}
		return vipStrengthBuffItem;
	}
	/** 获得当前c币总数 */
	public PlayerGPointTotal getTotalGPoint() {
		return playerDao.getTotalGPoint();
	}
	
	/** 获得玩家vip等级 */
	public int getVipLevel(int playerId) throws Exception {
		return this.getSimplePlayerById(playerId).getIsVip();
	}
		/**
	 * 资源争夺战，布置题图时能带入的装备
	 * @return
	 */
	public List<PlayerItem> getDefaultPackListForResourceWar(){
		return new ArrayList<PlayerItem>();
	}
	
	/**
	 * 根据黑原石获得队伍列表
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Team> getTeamsByOriginStone(int maxUnusableResource,int minUnusableResource)throws Exception{
		List<Team> result =teamDao.getTeamsByOriginStone(maxUnusableResource,minUnusableResource);
		return result;
	}

	/**
	 * 根据队伍是否活跃获得队伍
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Team> getTeamSpaceActiveTeams(boolean isActive)throws Exception{
		List<Team> result =teamDao.getTeamsByTeamSpaceActive(isActive);
		return result;
	}	
	

	/**
	 * 根据队伍是否活跃获得队伍的数量
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public int getTeamSpaceActiveTeamCount(boolean isActive,int myTeamId)throws Exception{
		int count =teamDao.getTeamSpaceActiveTeamCount(isActive,myTeamId);
		return count;
	}
	
	/**
	 * 根据队伍是否活跃获得队伍的数量
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public Team getTeamByTeamSpaceActiveAndIndex(boolean isActive,int index,int myTeamId)throws Exception{
		Team team =teamDao.getTeamByTeamSpaceActiveAndIndex(isActive,index,myTeamId);
		return team;
	}	

	/**
	 * 根据队伍是否活跃获得队伍，仅获得ID
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Team> getTeamSpaceActiveTeamIds(boolean isActive)throws Exception{
		List<Team> result =teamDao.getTeamIdsByTeamSpaceActive(isActive);
		return result;
	}
	
	/**
	 * 根据队伍是否活跃获得队伍，仅获得ID
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Team> getTeamSpaceActiveTeamIdsOrderByRecoreRankingCurr(boolean isActive)throws Exception{
		List<Team> result =teamDao.getTeamSpaceActiveTeamIdsOrderByRecoreRankingCurr(isActive);
		return result;
	}	
	
	/**
	 * 获得资源争夺战正确的开战信息key
	 * 
	 * @param battleFieldStatus
	 * @param mcc
	 * @return
	 * @throws TimeoutException
	 * @throws InterruptedException
	 * @throws MemcachedException
	 */
	public List<String> getCorrectBattleStartKeys(
			BattleFieldStatus battleFieldStatus)
			throws TimeoutException, InterruptedException, MemcachedException {
		// 被开战队伍所有的开战key
		List<String> battleStartKeys = battleFieldStatus.getBattleStartKeys();
		// 用于记录还在战斗的开战key
		List<String> correctBattleStartKeys = new ArrayList<String>();

		// 获取超时的开战key
		for (String battleKey : battleStartKeys) {
			if (mcc.get(battleKey) != null) {
				correctBattleStartKeys.add(battleKey);
			}
		}
		return correctBattleStartKeys;

	}
	
	
	/**
	 * 该战队是否正在被资源争夺战匹配赛攻打
	 * @return
	 * @throws MemcachedException 
	 * @throws InterruptedException 
	 * @throws TimeoutException 
	 */
	public boolean isTeamUnderZYZDZMatchAttack(int teamId) throws TimeoutException, InterruptedException, MemcachedException{
		if(getBattleFieldStatusByTeamId(teamId) != null){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 获得战队资源争夺战匹配战场状况
	 * @return
	 * @throws MemcachedException 
	 * @throws InterruptedException 
	 * @throws TimeoutException 
	 */	
	public BattleFieldStatus getBattleFieldStatusByTeamId(int teamId) throws TimeoutException, InterruptedException, MemcachedException{
		String battleStatusKey = Constants.TeamSpaceConstants.getTeamBattleStatusKey(teamId);
		BattleFieldStatus battleFieldStatus = mcc.get(battleStatusKey);	
		return battleFieldStatus;
	}
	

	/**
	 * 获得资源争夺战正确的交火次数
	 * 
	 * @param battleFieldStatus
	 * @param mcc
	 * @return
	 * @throws TimeoutException
	 * @throws InterruptedException
	 * @throws MemcachedException
	 */
	public int getCorrectBattleCount(
			BattleFieldStatus battleFieldStatus)
			throws TimeoutException, InterruptedException, MemcachedException {
		return getCorrectBattleStartKeys(battleFieldStatus).size();

	}
	
	/**
	 * ===================================================
	 * @throws Exception 
	 */
	public List<TeamItem> getTeamItemList(int teamId,int subType) throws Exception{
		List<TeamItem> resultList = new ArrayList<TeamItem>();
			List<TeamItem> teamItemList=teamItemDao.getTeamItemList(teamId);
			for(TeamItem ti:teamItemList){
				SysItem si = getSysItemByItemId(ti.getItemId());
					if(si.getSubType()==subType){
						ti.setSysItem(si);
						resultList.add(ti);
				}
			}
			return resultList;
	}
	public List<TeamItem> getTeamItemList(int teamId) throws Exception{
		List<TeamItem> resultList = new ArrayList<TeamItem>();
		List<TeamItem> teamItemList=teamItemDao.getTeamItemList(teamId);
		for(TeamItem ti:teamItemList){
			SysItem si = getSysItemByItemId(ti.getItemId());
			ti.setSysItem(si);
			resultList.add(ti);
		}
		return resultList;
	}
	/**
	 * 仅用于升级树
	 * @param teamId
	 * @return
	 * @throws Exception
	 */
	public List<TeamItem> getTeamItemListByTeamId(int teamId,int subType) throws Exception{
		List<TeamItem> resultList = new ArrayList<TeamItem>();
		List<TeamItem> teamItemList=teamItemDao.getTeamItemListByTeamId(teamId);
		for(TeamItem ti:teamItemList){
			SysItem si = getSysItemByItemId(ti.getItemId());
				if(si.getSubType()==subType){
					ti.setSysItem(si);
					resultList.add(ti);
			}
		}
		return resultList;
		
	}
	public List<TeamItem> getAvailableTeamItems(int teamId)throws Exception{
		List<TeamItem> availableItems=new ArrayList<TeamItem>();
		List<TeamItem> allItems=getTeamItemList(teamId);
		for(TeamItem ti: allItems){
			if(ti.getSysItem().getSubType()!=5){  //非油罐
//				int availableCount=ti.getShowQuantity()-ti.getUsedCount();
//				if(availableCount>0){
					availableItems.add(ti);
//				}
			}
		}
		return availableItems;
	}
	
	public List<TeamItem> getTeamItemsInMap(int teamId)throws Exception{
		List<TeamItem> allItems=getTeamItemList(teamId);
		List<TeamItem> itemsInMap=new ArrayList<TeamItem>();
		for(TeamItem ti:allItems){
			if(ti.getUsedCount()>0){
				itemsInMap.add(ti);
			}
		}
		
		return itemsInMap;
	}
	public TeamItem getTeamItemById(final int teamItemId) throws Exception{
		TeamItem ti = teamItemDao.getTeamItemById(teamItemId);
		SysItem si = getSysItemByItemId(ti.getItemId());
		ti.setSysItem(si);
		return ti;
	}
	
	@SuppressWarnings("unchecked")
	public List<SysItem> getSysItemList(final  int type,final int subType)throws Exception{
		List<SysItem> sysItemList=new ArrayList<SysItem>();
		sysItemList=sysItemDao.getSystemItemList(type, subType);
		return sysItemList;
	}
	/**
	 * 获得上周的抢夺资源排名
	 * @return
	 * @throws Exception
	 */
	public List<Team> getTeamTopForPreRes() throws Exception{
		String key = Constants.TEAMTOP_KEY_PREFIX + Constants.TEAM_TOP_TYPE.RESOURCE.getValue();
		NoSql nosql = nosqlService.getNosql();
		String[] teamIds = {};
		//获得已经按照抢夺数量排好序的ID
		teamIds = nosql.revRangeSortedSet(key, 0, ItemIntensifyUtil.PRE_WEEK_RES_TOP_SIZE).toArray(teamIds);
		
		List<Team> list = new ArrayList<Team>();
		
		for(String teamId:teamIds){
			Team team = getService.getTeamById(StringUtil.toInt(teamId));//这里TEAM可能为空
			list.add(team);
		}
		
		if(list.size()> ItemIntensifyUtil.PRE_WEEK_RES_TOP_SIZE){//可能不同版本的nosql返回回来的数值不一样，所以这里做一下sub
			list=list.subList(0, ItemIntensifyUtil.PRE_WEEK_RES_TOP_SIZE);
		}
		return list;
	}
	/**
	 * 获得今天2点前的抢夺资源排名
	 * @return
	 * @throws Exception
	 */
	public List<Team> getTeamTopForRes() throws Exception{
		String key = Constants.TEAMTOP_KEY_PREFIX + Constants.TEAM_TOP_TYPE.NOW_RES.getValue();
		NoSql nosql = nosqlService.getNosql();
		String[] members = {};
		members = nosql.revRangeSortedSet(key, 0, Constants.TEAM_TOP_ZYZDZ_NUM).toArray(members);
		List<Team> list = new ArrayList<Team>();
		for(String member:members){
			Team team = getService.getTeamById(StringUtil.toInt(member));
			if(team!=null){
				//double robed=nosql.zScore(key, member);
				//team.setPredayResAmount(new Double(robed).intValue());
				list.add(team);
			}
		}
		
		return list;
	}
	
	
	public long getTeamZyzdzRank(int teamId) throws Exception{
		String key = Constants.TEAMTOP_KEY_PREFIX + Constants.TEAM_TOP_TYPE.NOW_RES.getValue();
		NoSql nosql = nosqlService.getNosql();
		long rank=nosql.revRankInSortedSet(key, String.valueOf(teamId));
		if(rank<0){
			rank=0;
		}else{
			rank=rank+1;
		}
		return rank;
	}	
	
	public Collection<BattleFieldRobDaily> getBattleFieldRobDailies(String nowTime,String preTime,Constants.BattleFieldRobDailyType type){
		return battleFieldRobDailyDao.getBattleFieldRobDailyForTop(nowTime, preTime,type);
	}
	
	/**
	 * 从时间上判断，资源争夺战挑战赛是否启动
	 * 
	 * @return
	 */
	public boolean isChallengeZYZDZOn() {
		boolean result = false;
		Calendar now=Calendar.getInstance();
		Calendar start=getZYZDZChallengeTime(0);
		Calendar end=getZYZDZChallengeTime(1);
		
		if(now.before(end)&&now.after(start)){
			result=true;
		}
		return result;
	}	

	public TeamItem getTeamItemByTeamIdAndItemId(int teamId,int itemId) throws Exception{
		return teamItemDao.getTeamItemByTeamIdAndItemId(teamId, itemId);
	}
	
	
	public TeamItem getFullTeamItemByTeamIdAndItemId(int teamId,int itemId) throws Exception{
		TeamItem teamItem= teamItemDao.getTeamItemByTeamIdAndItemId(teamId, itemId);
		
		if(teamItem!=null && teamItem.getSysItem()==null){
			SysItem sysItem=getSysItemByItemId(teamItem.getItemId());
			if(sysItem!=null){
				teamItem.setSysItem(sysItem);
			}
		}
		return teamItem;
	}
	
	public TeamItem getFullTeamItemByTeamItemId(int teamItemId) throws Exception{
		TeamItem teamItem= teamItemDao.getTeamItemById(teamItemId);
		if(teamItem!=null && teamItem.getSysItem()==null){
			SysItem sysItem=getSysItemByItemId(teamItem.getItemId());
			if(sysItem!=null){
				teamItem.setSysItem(sysItem);
			}
		}
		return teamItem;
	}	
	
	/**
	 * 判断是否正在建造
	 * @param teamId
	 * @param sysItem
	 * @return
	 * @throws Exception
	 */
	public boolean teamItemUnderBuilding(int teamId,SysItem sysItem) throws Exception {
		long leftCD=getTeamItemCDLeft(teamId, sysItem);
		if(leftCD>0){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 该物品是否正在建造，如果仍在建造，则建造的剩余时间 毫秒级，0为没有CD
	 * @param teamId
	 * @param sysItem
	 * @return
	 * @throws Exception
	 */
	public long getTeamItemCDLeft(int teamId,SysItem sysItem) throws Exception {
		TeamItem teamItem = getService.getFullTeamItemByTeamIdAndItemId(teamId, sysItem.getId());
		return teamItem.getBuyCD();
	}	
	
	
	public TeamTechnology getTeamTechnologyByCurNode(int curNode) throws Exception{
		 Map<Integer, TeamTechnology> map=teamTechnologyDao.getClassifyTeamTechnologyMap();
		 return map.get(curNode);
	}
	
	
	/**
	 * 判断是否正在建造
	 * @param teamId
	 * @param sysItem
	 * @return
	 * @throws Exception
	 */
	public boolean playerItemUnderBuilding(int playerId,SysItem sysItem) throws Exception {
		long leftCD=getPlayerItemCDLeft(playerId, sysItem);
		if(leftCD>0){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 该物品是否正在建造，如果仍在建造，则建造的剩余时间 毫秒级，0为没有CD
	 * @param teamId
	 * @param sysItem
	 * @return
	 * @throws Exception
	 */
	public long getPlayerItemCDLeft(int playerId,SysItem sysItem) throws Exception {
		PlayerItem playerItem = getPlayerItemById(playerId, sysItem.getId());
		if(playerItem!=null){
			return playerItem.getBuyCD();
		}
		return 0l;
	}		
	
	
	
	/**
	 * 获得挑战赛的剩余时间
	 * @param defenceTeam
	 * @return
	 * @throws TimeoutException
	 * @throws InterruptedException
	 * @throws MemcachedException
	 */
	public long timeLeftForChallengeOnWar(Team defenceTeam) throws TimeoutException, InterruptedException, MemcachedException{
		long result=0l;
		Long start = mcc.get(Constants.TeamSpaceConstants
				.getZYZDZChallengeKey(defenceTeam.getId()));
		if (start != null) {// 正在进行资源争夺战攻城战
			long past=System.currentTimeMillis()-start;
			long leftTime=Constants.TeamSpaceConstants.BATTLEFIELD_CACHE_CHALLENGE_TIMEOUT-past;
			result=leftTime;
		}
		result=result<0?0:result;
		return result;
	}	
	
	/**
	 * 该队伍可以被挑战
	 * @param defenceTeam
	 * @return
	 * @throws MemcachedException 
	 * @throws InterruptedException 
	 * @throws TimeoutException 
	 */
	public boolean beOkForChallenge(Team defenceTeam) throws TimeoutException, InterruptedException, MemcachedException{
		boolean result=false;
		if(timeLeftForChallengeOnWar(defenceTeam)==0){
			result=true;
		}
		return result;
	}
	
	/**
	 * 该队伍可以被挑战
	 * @param defenceTeam
	 * @return
	 * @throws MemcachedException 
	 * @throws InterruptedException 
	 * @throws TimeoutException 
	 */
	public boolean beOkForChallenge(long leftTime) throws TimeoutException, InterruptedException, MemcachedException{
		boolean result=false;
		leftTime=leftTime<0?0:leftTime;
		if(leftTime==0){
			result=true;
		}
		return result;
	}	
	
	
	/**
	 * 获得指定被挑战队伍剩余的矿山黑晶石
	 * @param teamid
	 * @param rank
	 * @return
	 * @throws MemcachedException 
	 * @throws InterruptedException 
	 * @throws TimeoutException 
	 */
	public int getHillStonesByTeamID(int teamID,int rank) throws TimeoutException, InterruptedException, MemcachedException{
		int result=0;
		ChallengeHillStatus status=getChallengeHillStatus(teamID);
		if(status==null){//获得基础矿石
			result=getHillStonesByRank(rank);
		}else{//获得实际矿石
			result=status.getStones();
		}
		return result;
	}
	
	
	/**
	 * 根据队伍获得资源争夺战该队伍矿山信息
	 * @param teamId
	 * @return
	 * @throws TimeoutException
	 * @throws InterruptedException
	 * @throws MemcachedException
	 */
	public ChallengeHillStatus getChallengeHillStatus(int teamId) throws TimeoutException, InterruptedException, MemcachedException{
		String key=Constants.TeamSpaceConstants.getChallengeHillKey(teamId);
		ChallengeHillStatus challengeFieldStatus=mcc.get(key);
		return challengeFieldStatus;
	}

	
	public int getHillStonesByRank(int rank){
		int result=Constants.TeamSpaceConstants.getdefHillStoneByRank(rank);
		return result;
	}
	
	
	public String getFormatConfigOption(String config) throws Exception{
		StringBuffer sb=new StringBuffer();
		if(config.contains(";")){
			String [] locates=config.split(";");
			for (int i = 0; i < locates.length; i++) {
				String locate=locates[i];
				if(locate.contains(",")){
					String itemId=locate.split(",")[0];
					locate=locate.substring(itemId.length(),locate.length());
					SysItem sysitem=getSysItemByItemId(StringUtil.getIntParam(itemId, 0));
					if(sysitem!=null){
						locate=sysitem.getName()+locate;
					}else{
						locate=locates[i];
					}
				}
				sb.append(locate+";");
			}
		}else{
			sb.append(config);
		}
		
		return sb.toString();
	}
	
	public List<TeamTechnology> getTeamTechnologyByType(int type){
		return teamTechnologyDao.getTeamTechnologyByType(type);
	}
	
	/**
	 * 获得奖励个人为团队资源争夺战做出贡献
	 */
	public int getRewardToPlayer4TeamZYZDZ(Player player,Team team,int costFC){
		HashMap<String, Integer> playerRes=player.getLatestPlayerRes(team.getTeamSpaceLevel());
		int stones=playerRes.get(Player.RES);
		int reward=costFC*Constants.TeamSpaceConstants.P_RE_RATE;
		if(reward>0){
			int max=Constants.TEAMSPACELEVELCONSTANTS.getTeamSpaceLevelConstants(team.getTeamSpaceLevel()).getpMaxRes();
			if(stones+reward>max){
				reward=max-stones;
			}
		}
		return reward;
	}
	
	public int getChallangeFCCost(int hillLeftZY){
		return Constants.TeamSpaceConstants.getChallengeFCCostByKSZY(hillLeftZY);
	}
	
	/**
	 * 获得资源争夺战可以创建的物品总数
	 * @return
	 */
	public int getTeamItemCreateMaxQuantity(SysItem sysitem,int  placeLevel){
		int maxQuantity=0;
		if(sysitem.getSubType()==1){//城墙
			maxQuantity=Constants.TEAMSPACELEVELCONSTANTS.getTeamSpaceLevelConstants(placeLevel).getMaxCreateWall();
		}else if(sysitem.getSubType()==2){//防御塔
			maxQuantity=Constants.TEAMSPACELEVELCONSTANTS.getTeamSpaceLevelConstants(placeLevel).getMaxCreateTowerTower();
		}
		return maxQuantity;
	}
	
	public long[][] getFinishPricesByTeamIteam(TeamItem teamItem) throws Exception{
		int placeLevel=getService.getTeamById(teamItem.getTeamId()).getTeamSpaceLevel();
		setTeamItemCurrentPayMentAndMaxQuantityByItemAndPLevel(teamItem, placeLevel);
		return getFinishPrices(teamItem.getBuyCD(), teamItem.getSysItem(),teamItem.getCurrentCreatePayMent());
	}
	
	public long[][] getFinishSpacePricesByTeam(Team team) throws Exception{
		ConditionForPlaceUp con = ItemIntensifyUtil.ConditionForPlaceUp.getConditionForPlaceUp(team.getTeamSpaceLevel());
		long fullCD=con.getTime()*1000;
		long buyCD=team.getLevelupCD(fullCD);
		return getFinishPrices(buyCD,fullCD, con.getFbtsMoney());
	}
		

	public long[][] getFinishPricesByPlayerItem(PlayerItem playerItem) throws Exception{
		return getFinishPrices(playerItem.getBuyCD(), playerItem.getSysItem(),getPlayerItemCurrentCreatePayMent(playerItem.getSysItem()));
	}
	
	public Payment getPlayerItemCurrentCreatePayMent(SysItem sysItem){
		 List<Payment> payMentList=sysItem.getResDisPricesList();
		 if(payMentList!=null && payMentList.size()>0){
			 return payMentList.get(0);
		 }else{
			 return null;
		 }
	}

	/***
	 * 根据sysItem和buyCD获得物品半价和全价瞬间建造完成的价格
	 * @param buyCD,sysItem
	 *           {0    ,1            ,2          ,3      ,4                 }
	 * @return  {{0:半价,物品剩余的buycd,物品半fullCD,需要的FC,是否可以使用 0否，1是},{1:全价，物品剩余的buycd,物品的fullCD，需要的FC,是否可以使用 是否可以使用 0否，1是}}
	 * @throws ParseException 
	 */	
	public long[][] getFinishPrices(long buyCD,SysItem sysItem,Payment payment) throws ParseException{
		long fullCD=sysItem.getTimeForCreateMsec();
		long fullFC=payment.getFinishCost();
		return getFinishPrices(buyCD, fullCD, fullFC);
	}
	
	/***
	 * 根据sysItem和buyCD获得物品半价和全价瞬间建造完成的价格
	 * @param buyCD,sysItem
	 *           {0    ,1            ,2          ,3      ,4                 }
	 * @return  {{0:半价,物品剩余的buycd,物品半fullCD,需要的FC,是否可以使用 0否，1是},{1:全价，物品剩余的buycd,物品的fullCD，需要的FC,是否可以使用 是否可以使用 0否，1是}}
	 * @throws ParseException 
	 */	
	public long[][] getFinishPrices(long buyCD,long fullCD,long fullFC) throws ParseException{
		long halfType=Constants.TeamSpaceConstants.FINISH_TYPE_HALF;
		long fullType=Constants.TeamSpaceConstants.FINISH_TYPE_FULL;
		
		long halfCD=fullCD/2;
		
		long halfFC=fullFC/2;
		
		long halfAble=halfCD<buyCD?0:1;
		long fullAble=1;
		
		long[][] result={{halfType,buyCD,halfCD,halfFC,halfAble},{fullType,buyCD,fullCD,fullFC,fullAble}};
		return result;
	}		
	/**
	 * 
	 * @param playerId
	 * @param sysItemId
	 * @return
	 * @throws Exception
	 */
	public BuyItemRecord getBuyItemRecord(final int playerId,int sysItemId)throws Exception{
		List<BuyItemRecord> buyItemRecordsList=buyItemRecordDao.getPlayerItemList(playerId);
		for(BuyItemRecord bir: buyItemRecordsList){
			if(bir.getItemId()==sysItemId){
				return bir;
			}
		}
		return null;
	}
	
	
	//设置当前payment，和防御塔的数量上限
	public void setTeamItemCurrentPayMentAndMaxQuantityByItemListAndPLevel(List<TeamItem> itemList,int placeLevel){
		for (TeamItem teamItem : itemList) {
			int maxQuantity=getService.getTeamItemCreateMaxQuantity(teamItem.getSysItem(),placeLevel);
			teamItem.setMaxQuantity(maxQuantity);
			
			 List<Payment> payMentList=teamItem.getSysItem().getResTeamPricesList();
			 for (Payment payment : payMentList) {
				if(payment.getLevel()==placeLevel){
					teamItem.setCurrentCreatePayMent(payment);
					break;
				}
				
			}
		}
	}	
	
	public void setTeamItemCurrentPayMentAndMaxQuantityByItemAndPLevel(TeamItem teamItem ,int placeLevel){
		int maxQuantity=getService.getTeamItemCreateMaxQuantity(teamItem.getSysItem(),placeLevel);
		teamItem.setMaxQuantity(maxQuantity);
		
		 List<Payment> payMentList=teamItem.getSysItem().getResTeamPricesList();
		 for (Payment payment : payMentList) {
			if(payment.getLevel()==placeLevel){
				teamItem.setCurrentCreatePayMent(payment);
				break;
			}
			
		}
	}	
	
	public Team getTeamByPlayer(Player player)throws Exception{
		Team team=null;
		PlayerTeam p=getPlayerTeamByPlayerId(player.getId());
		if(p!=null){
			team=getSimpleTeamById(p.getTeamId());
		}
		return team;
	}
	
	/**
	 * 得到资源争夺战模式  玩家能够带入游戏的道具
	 * @param iids
	 * @param playerId
	 * @return
	 */
	public HashMap<Integer,List<PlayerItem>> getZYZDZPlayerItems(int playerId,int[] iids)throws Exception{
		HashMap<Integer,List<PlayerItem>> allZyzdzItemsList=new HashMap<Integer, List<PlayerItem>>();
		if (iids.length>0) {
			for(int iid : iids){
				List<PlayerItem> theItems=getService.getPlayerItemByItemIid(playerId, Constants.DEFAULT_ZYZDZ_TYPE, iid);
				int itemsCount=getCount(theItems);
				if(itemsCount>0){	
					allZyzdzItemsList.put(iid,theItems);
				}
			}
		}		
		return allZyzdzItemsList;
	}
	private int getCount(List<PlayerItem> list){
		int total=0;
		if(list!=null && list.size()>0){
			for(PlayerItem pi : list){
				int quantity=pi.getQuantityForZYZDZPersonal();
				if(quantity>0){
					total+=quantity;
				}
			}
		}
		return total;
	}
	
	
	/**
	 * 
	 * @param characterId 角色ID
	 * @param wId   武器ID
	 * @param cId	服装ID
	 * @param replaceItem 无奈的代码，硬写坦克属性到一个sysitem
	 * @return
	 */
	public BossPojo getBossPojo(int characterId,int wId,int cId,PlayerItem... replaceItem )throws Exception{
		BossPojo hero=new BossPojo();
		hero.setPlayerId(0);
		SysCharacter character=getSysCharacter(characterId);
		hero.setSysCharacter(character);
		SysItem mainSysItem=getSysItemByItemId(wId).clone();
		
		if(replaceItem.length>0){
			PlayerItem pItem=replaceItem[0];
			if(pItem!=null){
				int att=Math.round((pItem.getProperty1().get("value").floatValue()-10)*10);
				int speed=Math.round((pItem.getProperty2().get("value").floatValue()-10)*10);
				int hp=Math.round((pItem.getProperty3().get("value").floatValue()-10)*10);
				int moveSpeed=Math.round((pItem.getProperty6().get("value").floatValue()-10)*10);
				
				List<Integer> proList1=mainSysItem.getGunPropertyList1();
				List<Integer> proList2=mainSysItem.getGunPropertyList2();
				List<Integer> proList3=mainSysItem.getGunPropertyList3();
				List<Integer> proList4=mainSysItem.getGunPropertyList4();
				if(proList1!=null && proList1.size()>0&&proList1.size()%4==0){
					proList1.set(1, att);
				}
				if(proList2!=null && proList2.size()>0&&proList2.size()%4==0){
					proList2.set(1, speed);
				}
				if(proList3!=null && proList3.size()>0&&proList3.size()%4==0){
					proList3.set(1, hp);
				}
				if(proList4!=null && proList4.size()>0&&proList4.size()%4==0){
					proList4.set(1, moveSpeed);
				}
				mainSysItem.setGunPropertyList1(proList1);
				mainSysItem.setGunPropertyList2(proList2);
				mainSysItem.setGunPropertyList3(proList3);
				mainSysItem.setGunPropertyList4(proList4);
			}
		}
		
		List<SysItem> weaponList=new ArrayList<SysItem>();
		hero.setWeaponList(weaponList);
		weaponList.add(mainSysItem);

		SysItem clothes=getSysItemByItemId(cId);
		List<SysItem> costumeList=new ArrayList<SysItem>();
		costumeList.add(clothes);
		hero.setCostumeList(costumeList);
		return hero;
	}
	
	/**
	 * <li>进攻方等级<=5，可以进行任意挑战</li>
	 * <li>进攻方等级>5 且 进攻方等级<=防守方等级+1 可以挑战</li>
	 * @param attackTeam 进攻队伍
	 * @param defenceTeam 防守队伍
	 * @return
	 */
	public boolean isLevelOkForChallenge(Team attackTeam,Team defenceTeam){
		boolean result =false;
		if(attackTeam.getLevel()<=5){
			result=true;
		}else if(attackTeam.getLevel()<=defenceTeam.getLevel()+1){
			result=true;
		}
		return result;
	}
	
	
	public SysItem getClonedZyzdzBuff(SysItem sysItem,int playerId) throws Exception{
		SysItem clonedItem=null;
		if(CommonUtil.isZYZDZBuff(sysItem)){
			clonedItem=sysItem.clone(true);
			int count =getBuyItemRecordCount(playerId, sysItem.getId());
			Payment fcPayment=clonedItem.getCrPricesList().get(0);
			CommonUtil.initZYZDZBuffPayment(fcPayment,count,false);
			
			Payment resPayment=clonedItem.getResPricesList().get(0);
			CommonUtil.initZYZDZBuffPayment(resPayment,count,false);

		}
		return clonedItem;
	}	
	
	/**
	 * 获得玩家购买该物品的次数
	 * @param playerId
	 * @param sysItemId
	 * @return
	 * @throws Exception
	 */
	public int getBuyItemRecordCount(int playerId,int sysItemId) throws Exception{
		int count=0;
		BuyItemRecord buyItemRecord=getBuyItemRecord(playerId, sysItemId);
		if(buyItemRecord!=null){
			count=buyItemRecord.getRecord();
		}
		return count;
	}
	
	public QuietBounds getOnlineQuietBounds(int playerId,int level) throws Exception{
		QuietBounds quietBounds=new QuietBounds();
		quietBounds.setDisResBounds(Constants.BOUNDS.ONLINE_DIS_RES_BOUND_BASIC.getBounds()*level);
		return quietBounds;
	}	
	
	public QuietBounds getDailyQuietBounds(){
		QuietBounds quietBounds=new QuietBounds();
		quietBounds.setDisResBounds(Constants.BOUNDS.DAILY_CHECK_DIS_RES_BOUND.getBounds());
		return quietBounds;
	}		
	/**
	 * 根据玩家id从用户总榜获得匹配段位
	 * @param playerId
	 * @return
	 * @throws Exception 
	 */
	public int getQW_MateRank(int playerId) throws Exception {
		QwPlayerSum pSum = getService.getQW_player_Sum(playerId);
		getService.QWPS_fresh__MateRank(pSum,playerId);//根据排行榜获得即使段位,刷新
		if (pSum.isNotNull())
			return -1;
		return pSum.getMateRank();
	}
	/**
	 * 根据id获得玩家当天数据
	 * @param playerId
	 * @return
	 * @throws Exception
	 */
	public QwPlayerDay getQW_player_Day(int playerId) throws Exception{
		String format = DateFormatUtil.getYMDSf().format(new Date());
		String qw_d = nosqlService.getNosql().hashGet(Constants.QW_DAY_KEY_PREFIX+ playerId,format);
		return new QwPlayerDay(qw_d);
	}
	/**
	 * 根据id获得玩家总数据
	 * @param playerId
	 * @return
	 * @throws Exception
	 */
	public QwPlayerSum getQW_player_Sum(int playerId) throws Exception{
		String qw_s = nosqlService.getNosql().hashGet(Constants.QW_SUM_KEY_PREFIX, playerId+"");
		return new QwPlayerSum(qw_s);
	}
	/**
	 * 根据排行榜获得即使段位,刷新
	 * @param playerId
	 * @return
	 * @throws Exception
	 */
	public QwPlayerSum QWPS_fresh__MateRank(QwPlayerSum qwPlayerSum,int playerId) throws Exception{
		if (qwPlayerSum.getScoce()>Constants.QW_RATIO[10]) {
			int rank = (int)nosqlService.getNosql().zRank(Constants.QW_TOP_KEY_PREFIX, String.valueOf(playerId));//获得元素位置 -1为不存在，从0开始
			if (rank>=0&&rank<10) {
				qwPlayerSum.setMateRank(12);
			}
		}
		return qwPlayerSum;
	}
	
	/**
	 * 获得玩家P值
	 * @param player
	 * @return
	 * @throws Exception
	 */
	public int getPlayerPValue(Player player){
		int scoce;
		try {
			scoce = getService.getQW_player_Sum(player.getId()).getScoce();
		} catch (Exception e) {
			log.warn("getService  getPlayerPValue+getQW_player_Sum", e);
			return 0;
		}
		if (scoce <= Constants.QW_RATIO[8]) {//1800
			try {
				return CommonUtil.get_Pvalue(player);
			} catch (Exception e) {
				log.warn("getService  getPlayerPValue+CommonUtil.get_Pvalue(player)", e);
				return 0;
			}
		} else if (scoce > Constants.QW_RATIO[8]) {//1800
			return 120;
		}
		return 0;
	}
}
