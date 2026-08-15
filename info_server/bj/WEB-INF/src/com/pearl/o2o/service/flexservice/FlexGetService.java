package com.pearl.o2o.service.flexservice;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.o2o.exception.GmException;
import com.pearl.o2o.nosql.object.playerevent.BasePlayerLogEvent;
import com.pearl.o2o.nosql.object.team.TeamRecordObj;
import com.pearl.o2o.pojo.BannedWord;
import com.pearl.o2o.pojo.BlackIP;
import com.pearl.o2o.pojo.Channel;
import com.pearl.o2o.pojo.GmGroup;
import com.pearl.o2o.pojo.GmLog;
import com.pearl.o2o.pojo.GmPrivilege;
import com.pearl.o2o.pojo.GmUser;
import com.pearl.o2o.pojo.LevelInfo;
import com.pearl.o2o.pojo.LevelWeapon;
import com.pearl.o2o.pojo.Message;
import com.pearl.o2o.pojo.OnlineAward;
import com.pearl.o2o.pojo.PayLog;
import com.pearl.o2o.pojo.Payment;
import com.pearl.o2o.pojo.Player;
import com.pearl.o2o.pojo.PlayerInfo;
import com.pearl.o2o.pojo.PlayerItem;
import com.pearl.o2o.pojo.PlayerLocation;
import com.pearl.o2o.pojo.PlayerVO;
import com.pearl.o2o.pojo.Server;
import com.pearl.o2o.pojo.StrengthenAppend;
import com.pearl.o2o.pojo.SysAchievement;
import com.pearl.o2o.pojo.SysActivity;
import com.pearl.o2o.pojo.SysBioCharacter;
import com.pearl.o2o.pojo.SysCharacter;
import com.pearl.o2o.pojo.SysChest;
import com.pearl.o2o.pojo.SysConfiguration;
import com.pearl.o2o.pojo.SysItem;
import com.pearl.o2o.pojo.SysModeConfig;
import com.pearl.o2o.pojo.SysNotice;
import com.pearl.o2o.pojo.Team;
import com.pearl.o2o.pojo.UnSpeaker;
import com.pearl.o2o.pojo.XunleiOrderLog;
import com.pearl.o2o.service.GetService;
import com.pearl.o2o.utils.CacheUtil;
import com.pearl.o2o.utils.CommonUtil;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.PasswordUtil;
import com.pearl.o2o.utils.StringUtil;


public class FlexGetService extends GetService{

	static Logger log = LoggerFactory.getLogger(FlexGetService.class.getName());
	
	
	//----------------------------Sys config-------------------------------------
	public String getConfig()throws Exception{
		SysModeConfig config=getSysModeConfig(3);
		return config.getConfig();
	}		
	
	public SysModeConfig getSysModeConfig(Integer type) {
		SysModeConfig modeConfig=new SysModeConfig();
		modeConfig=sysModeConfigDao.getSysModeConfig(type);
		return modeConfig;
	}
	public List<SysConfiguration> getSysConfigList() throws Exception{
		return getService.getSysConfigList();
	}	

	//----------------------------Sys activity-------------------------------------
	public List<SysActivity> getActivityList() throws Exception{
		List<SysActivity> returnList=new ArrayList<SysActivity>();
//		returnList=sysActivityDao.getActivityList();
		return returnList;
	}	
	//----------------------------Sys character-------------------------------------
	/**
	 * method to get all character for GM
	 * @return List<Character>
	 * @throws Exception
	 */
	public List<SysCharacter> getSysCharacterList()throws Exception{
		List<SysCharacter> charaterList = new ArrayList<SysCharacter>();
			charaterList=sysCharacterDao.getSysCharacterList();
		return charaterList;
	}	
	public List<SysBioCharacter> getSysBioCharacterList()throws Exception{
		List<SysBioCharacter> charaterList = new ArrayList<SysBioCharacter>();
		charaterList=sysBioCharacterDao.getAllSysBioCharacterList();
		getService.flushSysBioCharacterCache();
		return charaterList;
	}	
	

	public List<SysCharacter> getSysCharacterLog(Integer characterId)throws Exception{
		return	 sysCharacterLogDao.getSysCharacterLogList(characterId);
	}
	
	//----------------------------player character-------------------------------------
	public List<PlayerVO> getPlayerList() throws Exception{
		List<PlayerVO> list=new ArrayList<PlayerVO>();
		list=playerDao.getPlayerList();
		return list;
	}	
	public String checkPlayerData(GmUser gmUser,int playerId) throws Exception{
		StringBuilder sb=new StringBuilder();
		Player player = getService.getPlayerById(playerId);
		sb.append(super.checkNewPackage(player));
		sb.append(super.checkPlayerPack(player));
		sb.append(super.checkCharacterData(player));
		//check fourth weapon
		sb.append(super.checkDefaultFourthWeapon(player));
		sb.append(super.checkPlayerItem(player));
		log.info("GM:"+gmUser.getName()+" check player="+playerId+" 's data");
		if(sb.length()==0){
			sb.append("no error data finded!");
		}
		return sb.toString();
	}
	public int getPlayerTitle(int exp) throws Exception{
		
		return getRankByExp(exp).getId();
	}
	
	public int getPlayerExp(int rank) throws Exception{
		
		return getRankByLevel(rank).getExp();
	}
	
	//-------------------------------------------------------
	//          Get standard VipRank and VipExp
	//------------------------------------------------------
	public int getVipExp(int vipRank){
		if(vipRank<=0){
			return 0;
		}else{
			int maxRank=Constants.VIP_LEVEL_EXP.length-1;
			if(vipRank>maxRank){
				return Constants.VIP_LEVEL_EXP[maxRank];
			}
			return Constants.VIP_LEVEL_EXP[vipRank-1];
		}
	}
	public int getVipRank(int vipExp){
		for(int i=0;i<Constants.VIP_LEVEL_EXP.length;i++){
			if(vipExp<Constants.VIP_LEVEL_EXP[i]){
				return i;
			}
		}
		return Constants.VIP_LEVEL_EXP.length-1;
	}
	
	public List<PlayerVO> getPlayerByIdForXunlei(String id)  {
		List<PlayerVO> result = new ArrayList<PlayerVO>();
		int idInt = 0;
		if(id!=null){
			idInt = Integer.valueOf(id);
			PlayerVO playerVO = getService.getPlayerDao().getPlayerByIdFormDb(idInt);
			result.add(playerVO);
		}
		return result;
	}
	public List<PlayerVO> fuzzySelectByUserName(String userName){
		List<PlayerVO> list = playerDao.fuzzySelectByUserName(userName);
		return list;
	}
	//----------------------------playerItem-------------------------------------
	public List<PlayerItem> getPlayerItemList(int playerId) throws Exception {
		List<PlayerItem> list=new ArrayList<PlayerItem>();
		List<PlayerItem> resultList=new ArrayList<PlayerItem>();
		List<Integer> playerItemIdList = new ArrayList<Integer>();
		try{
			for(SysCharacter sc : getService.getDefaultSysCharacterList()){
				list.addAll(getPlayerItems(playerId, Constants.DEFAULT_WEAPON_TYPE, sc.getId(), 0));
				list.addAll(getPlayerItems(playerId, Constants.DEFAULT_COSTUME_TYPE, sc.getId(), 0));
				list.addAll(getPlayerItems(playerId, Constants.DEFAULT_PART_TYPE, sc.getId(), 0));
			}
			list.addAll(getPlayerItems(playerId, Constants.DEFAULT_MATERIAL_TYPE, 0, 0));
			list.addAll(getPlayerItems(playerId, Constants.DEFAULT_BLUEPRINT_TYPE, 0, 0));
			list.addAll(getPlayerItems(playerId, Constants.DEFAULT_PACKAGE_TYPE, 0, 0));
			list.addAll(getPlayerItems(playerId, Constants.DEFAULT_ITEM_TYPE, 0, 0));
			list.addAll(getPlayerItems(playerId, Constants.DEFAULT_OPEN_TYPE, 0, 0));
			for (PlayerItem pi : list) {
				if (pi.getIsDefault().equals("N")) {
					if (pi.getPlayerItemUnitType() == 2) {
						pi.calculateTimeLeft();
						if (pi.getTimeLeft() > 0) {
							if(!playerItemIdList.contains(pi.getId())){
								resultList.add(pi);
								playerItemIdList.add(pi.getId());
							}
						}
					} else if (pi.getPlayerItemUnitType() == 1 && pi.getQuantity() > 0) {
						if(!playerItemIdList.contains(pi.getId())){
							resultList.add(pi);
							playerItemIdList.add(pi.getId());
						}
					} else if (pi.getPlayerItemUnitType() == 0) {
						if(!playerItemIdList.contains(pi.getId())){
							resultList.add(pi);
							playerItemIdList.add(pi.getId());
						}
					}
				}
			}
			Collections.sort(resultList, new Comparator<PlayerItem>(){
				@Override
				public int compare(PlayerItem o1, PlayerItem o2) {
					return ((Integer)o1.getSysItem().getType()).compareTo((Integer)o2.getSysItem().getType());
				}  
			});
		} catch (Exception e){
			log.warn("error in getPlayerItemList", e);
			throw e;
		}
		return resultList;
	}
	
	public List<PlayerItem> getPlayerDefaultItemList(int playerId) throws Exception {
		List<PlayerItem> list=new ArrayList<PlayerItem>();
		List<PlayerItem> resultList=new ArrayList<PlayerItem>();
		try{
			List<SysCharacter> cList = getService.getDefaultSysCharacterList();
			for(SysCharacter sc : cList){
				list.addAll(getPlayerItems(playerId, Constants.DEFAULT_WEAPON_TYPE, sc.getId(), 0));
				list.addAll(getPlayerItems(playerId, Constants.DEFAULT_COSTUME_TYPE, sc.getId(), 0));
				list.addAll(getPlayerItems(playerId, Constants.DEFAULT_PART_TYPE, sc.getId(), 0));
			}
			list.addAll(getPlayerItems(playerId, Constants.DEFAULT_ITEM_TYPE, 0, 0));
			list.addAll(getPlayerItems(playerId, Constants.DEFAULT_MATERIAL_TYPE, 0, 0));
			list.addAll(getPlayerItems(playerId, Constants.DEFAULT_BLUEPRINT_TYPE, 0, 0));
			list.addAll(getPlayerItems(playerId, Constants.DEFAULT_PACKAGE_TYPE, 0, 0));
			list.addAll(getPlayerItems(playerId, Constants.DEFAULT_OPEN_TYPE, 0, 0));
			for(PlayerItem item : list){
				if(item.getIsDefault().equals("Y")){
					resultList.add(item);
				}
			}
			Collections.sort(resultList, new Comparator<PlayerItem>(){
				@Override
				public int compare(PlayerItem o1, PlayerItem o2) {
					return ((Integer)o1.getPack()).compareTo((Integer)o2.getPack());
				}  
			});
		} catch(Exception e){
			log.warn("error in getPlayerDefaultItemList", e);
			throw e;
		}
		return resultList;
	}
	public List<PlayerItem> getPlayerStrengthWeapeonList(int playerId) throws Exception {
		List<PlayerItem> list=new ArrayList<PlayerItem>();
		List<PlayerItem> resultList=new ArrayList<PlayerItem>();
		List<Integer> playerItemIdList = new ArrayList<Integer>();
		try{
			for(SysCharacter sc : getService.getDefaultSysCharacterList()){
				list.addAll(getPlayerItems(playerId, Constants.DEFAULT_WEAPON_TYPE, sc.getId(), 0));
				list.addAll(getPlayerItems(playerId, Constants.DEFAULT_COSTUME_TYPE, sc.getId(), 0));
				list.addAll(getPlayerItems(playerId, Constants.DEFAULT_PART_TYPE, sc.getId(), 0));
			}
//			list.addAll(getPlayerItems(playerId, Constants.DEFAULT_MATERIAL_TYPE, 0, 0));
//			list.addAll(getPlayerItems(playerId, Constants.DEFAULT_BLUEPRINT_TYPE, 0, 0));
//			list.addAll(getPlayerItems(playerId, Constants.DEFAULT_PACKAGE_TYPE, 0, 0));
//			list.addAll(getPlayerItems(playerId, Constants.DEFAULT_ITEM_TYPE, 0, 0));
//			list.addAll(getPlayerItems(playerId, Constants.DEFAULT_OPEN_TYPE, 0, 0));
			for (PlayerItem pi : list) {
				if (pi.getIsDefault().equals("N")&&pi.getSysItem().getIsStrengthen()==1) {
					if (pi.getPlayerItemUnitType() == 2) {
						pi.calculateTimeLeft();
						if (pi.getTimeLeft() > 0) {
							if(!playerItemIdList.contains(pi.getId())){
								resultList.add(pi);
								playerItemIdList.add(pi.getId());
							}
						}
					} else if (pi.getPlayerItemUnitType() == 1 && pi.getQuantity() > 0) {
						if(!playerItemIdList.contains(pi.getId())){
							resultList.add(pi);
							playerItemIdList.add(pi.getId());
						}
					} else if (pi.getPlayerItemUnitType() == 0) {
						if(!playerItemIdList.contains(pi.getId())){
							resultList.add(pi);
							playerItemIdList.add(pi.getId());
						}
					}
				}
			}
			Collections.sort(resultList, new Comparator<PlayerItem>(){
				@Override
				public int compare(PlayerItem o1, PlayerItem o2) {
					return ((Integer)o1.getSysItem().getType()).compareTo((Integer)o2.getSysItem().getType());
				}  
			});
		} catch (Exception e){
			log.warn("error in getPlayerItemList", e);
			throw e;
		}
		return resultList;
	}
	
	public List<PlayerItem> getDeletedPlayerItemList(Integer playerId,Integer id,Integer sysitemId,Integer level ,Integer useType,Integer sysItemType) throws Exception {
		List<PlayerItem> deletedList = playerItemDao.getDeletedPlayerItem( playerId, id, sysitemId, level , useType);
		List<PlayerItem> retList = new ArrayList<PlayerItem>();
		for(PlayerItem pi : deletedList){
			SysItem si = getService.getSysItemByItemId(pi.getItemId());
			if(si!=null && (sysItemType!=null?sysItemType.equals(si.getType()):true)){
				pi.setItemDisplayName(si.getDisplayNameCN());
				pi.setSysItem(si);
				retList.add(pi);
			}
		}
		Collections.sort(retList, new Comparator<PlayerItem>(){
			@Override
			public int compare(PlayerItem o1, PlayerItem o2) {
				return ((Integer)o1.getSysItem().getType()).compareTo((Integer)o2.getSysItem().getType());
			}  
		});
		return retList;
	}
	
	//----------------------------Sys sysitem-------------------------------------
	public List<SysItem> getUndeletedSysItemList(int type, String deleted) throws Exception{
		List<SysItem> sysItemList = new ArrayList<SysItem>();
		List<SysItem> returnList = new ArrayList<SysItem>();
		if(type < Constants.NUM_ONE || type > Constants.TYPE_NUM){
			log.warn("type or subtype not correct.");
		}else{
			sysItemList = getSysItemList(type);
		}
		for(SysItem si:sysItemList){
			if(Constants.BOOLEAN_NO.equals(si.getIsDeleted())){
				returnList.add(si);
			}
		}
		return returnList;
	}	
	
	
	/**
	 * method to get sysitem List by type
	 * @param type
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<SysItem> getSysItemList(int type)throws Exception{
	
		List<SysItem> sysItemList = new ArrayList<SysItem>();
		if (type < Constants.NUM_ONE || type > Constants.TYPE_NUM  ) {
			log.warn("type or subtype not correct.");
		} else {
			sysItemList=sysItemDao.getSystemItemList(type);
		}
		return sysItemList;
	}	
	
	
	@SuppressWarnings("unchecked")
	public List<SysItem> getSysItemList(final  int type,final int subType)throws Exception{
		List<SysItem> sysItemList=new ArrayList<SysItem>();
		sysItemList=sysItemDao.getSystemItemList(type, subType);
		return sysItemList;
	}
	
	
	public List<SysItem> getSysItemLog(int itemId)throws Exception{
		return	 sysItemLogDao.getSysItemLog(itemId);
	}	
	//----------------------------Sysitem payment-------------------------------------
	public List<Payment> getPaymentListById(int itemId) throws Exception{
		List<Payment> list = paymentDao.getPaymentListById(itemId);
		return list;
	}
	
	//----------------------------Sys Map-------------------------------------
	public List<LevelInfo> getLevelList() throws Exception{
		return getService.getLevelListUseCache();
	}
	
	
	public List<LevelWeapon> getLevelWeapons(int levelId){
		return sysLevelDao.getLevelWeaponByLevelId(levelId);
	}
	
	//----------------------------Sys server channel-------------------------------------
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
	//								  System Notice
	//===============================================================================
	public List<SysNotice> getSysNoticeList()throws Exception{
		return getService.getSysNotice();
	}
	
	//---------------------------- BlackIP  Services-------------------------------------
	public List<BlackIP> getBlackIP() throws Exception {
		return blackIPDao.selectBlackIP();
	}
	public List<BlackIP> getByIP(String ip) throws Exception{
		return blackIPDao.selectByIP(ip);
	}
	
	//---------------------------- BlackWhiteList Services-------------------------------------
	public List<PlayerVO> fuzzySelectByName(String name){
		List<PlayerVO> list = playerDao.fuzzySelectByName(name);
		return list;
	}
	public List<PlayerVO> getPlayerById(String id) throws Exception{
		List<PlayerVO> result = new ArrayList<PlayerVO>();
		int idInt = 0;
		if(id!=null){
			idInt = Integer.valueOf(id);
			Player player = getService.getPlayerById(idInt);
			if(null != player){
				PlayerInfo playerInfo = getService.getPlayerInfoById(idInt);
				PlayerVO playerVO = new PlayerVO();
				playerVO.setBlackWhite(player.getBlackWhite());
				playerVO.setId(player.getId());
				playerVO.setDeleted(player.getDeleted());
				playerVO.setUserName(player.getUserName());
				playerVO.setName(player.getName());
				playerVO.setGPoint(player.getGPoint());
				playerVO.setExp(player.getExp());
				playerVO.setRank(player.getRank());
				playerVO.setIsVip(player.getIsVip());
				playerVO.setVipExp(player.getVipExp());
				playerVO.setGScore(player.getGScore());
				playerVO.setTutorial(player.getTutorial());
				if(null == playerInfo){
					playerVO.setCredit(0);
				} else {
					playerVO.setCredit(playerInfo.getXunleiPoint());
				}
				playerVO.setVoucher(player.getVoucher());
				playerVO.setCharacters(player.getCharacters());
				result.add(playerVO);
			}
		}
		return result;
	}
	
	public List<PlayerVO> getPlayerById1(String id) throws Exception {
		List<PlayerVO> result = new ArrayList<PlayerVO>();
		try {

			int idInt = 0;
			if (id != null) {
				
				idInt = Integer.valueOf(id);
				PlayerInfo playerInfo = getService.getPlayerInfoById(idInt);
				Player player = getService.getPlayerById(idInt);
				
				PlayerVO playerVO = new PlayerVO();
				playerVO.setBlackWhite(player.getBlackWhite());
				playerVO.setId(player.getId());
				playerVO.setDeleted(player.getDeleted());
				playerVO.setUserName(player.getUserName());
				playerVO.setName(player.getName());
				playerVO.setGPoint(player.getGPoint());
				playerVO.setExp(player.getExp());
				playerVO.setRank(player.getRank());
				playerVO.setIsVip(player.getIsVip());
				playerVO.setVipExp(player.getVipExp());
				playerVO.setGScore(player.getGScore());
				playerVO.setTutorial(player.getTutorial());
				//zlm2015-10-9-匹配-gm后台-开始 
				playerVO.setMatchWin(player.getMatchWin());
				//zlm2015-10-9-匹配-gm后台-结束
				playerVO.setPvalue(player.getPvalue());
				playerVO.setMvalue(player.getMvalue());
				Team team=getTeamByPlayer(player);
//				if(team!=null){
//					HashMap<String, Integer> playerRes=player.getLatestPlayerRes(team.getTeamSpaceLevel());
//					playerVO.setUnusableResource(playerRes.get(Player.ORG_RES));
//					playerVO.setUsableResource(playerRes.get(Player.RES));	
//					playerVO.setUnusableResource(playerRes.get(Player.ORG_RES));
//					playerVO.setUsableResource(playerRes.get(Player.RES));
//					
//				}
				playerVO.setUnusableResource(player.getDBUnusableResource());
				playerVO.setUsableResource(player.getDBUsableResource());
				playerVO.setLastLogin(CommonUtil.simpleDateFormat.format(new Date(player.getLastLoginTime()*1000l)));
				playerVO.setLastLogout(CommonUtil.simpleDateFormat.format(new Date(player.getLastLogoutTime()*1000l)));
				playerVO.setCreateTime(CommonUtil.simpleDateFormat.format(player.getCreateTime()));
				PlayerLocation location=mcc.get(CacheUtil.oPlayerLocation(idInt),Constants.CACHE_TIMEOUT);
				if (location != null) {
					playerVO.setIsOnline(1);
				}else{
					playerVO.setIsOnline(0);
				}
				if(null == playerInfo){
					playerVO.setCredit(0);
				} else {
					playerVO.setCredit(playerInfo.getXunleiPoint());
				}
				playerVO.setVoucher(player.getVoucher());
				playerVO.setCharacters(player.getCharacters());

				Class clazz=playerVO.getClass();
				for (String ids : player.getCharactersIds()) {
					int characterId = Integer.parseInt(ids);

					if(characterId>0 && characterId<8){
						List<PlayerItem> wlist = getService.getWeaponPackList(idInt, 1, characterId);
						List<PlayerItem> clist = getService.getCostumePackList(idInt, 1, characterId);
						StringBuilder sb = new StringBuilder();
						for (PlayerItem pi : wlist) {
							if (null != pi) {
								SysItem si = getService.getSysItemByItemId(pi.getItemId());
								sb.append(si.getDisplayNameCN()).append("(Lv ").append(pi.getLevel()).append("),");
							} else {
								sb.append("无").append(",");
							}
						}
						sb.append("\n");
						for (PlayerItem pi : clist) {
							if (null != pi) {
								SysItem si = getService.getSysItemByItemId(pi.getItemId());
								sb.append(si.getDisplayNameCN()).append("(Lv ").append(pi.getLevel()).append("),");
							} else {
								sb.append("无").append(",");
							}
						}
						
						String setChmethodStr="setCharacter"+characterId+"W";
						Method setChmethod=clazz.getDeclaredMethod(setChmethodStr, String.class);
						setChmethod.invoke(playerVO, sb.toString());
					
					}

				}
				
			
				result.add(playerVO);
			}
		} catch (Exception e) {
			log.warn("error in getPlayerById1", e);
			throw e;
		}
		return result;
	}
	
	public List<PlayerVO> selectBlackList() throws Exception{
		List<PlayerVO> result = getService.getPlayerBlackList();
		return result;
	}
	public List<PlayerVO> selectWhiteList() throws Exception{
		List<PlayerVO> result = getService.getPlayerWhiteList();
		return result;
	}
	//===============================================================================	
	//								  Banned Word
	//===============================================================================
	@SuppressWarnings("unchecked")
	public List<BannedWord> getBannedWords() throws Exception{
		String key = CacheUtil.oBannedWords();
		return (List<BannedWord>)loadValue(key, new CacheMissHandler(){
			@Override
			public Object loadFromDB(GetService service) throws Exception {
				return bannedWordDao.getAllBannedWrodListIncludeDeleted();
			}
			
		});
	}
	public List<BannedWord> getByBannedWord(String word) throws Exception{
		return this.bannedWordDao.getBannedWrodsByWord(word);
	}
	// ===============================================================================
	// 										PayLog
	// ===============================================================================
	public List<PayLog> getpayloListByPlayerId(int id){
		List<PayLog> list = getService.getPayLogList(id);
		return list;
	}
	public List<XunleiOrderLog> getTopUplogListByPlayerId(int id) throws Exception{
		return getService.getAddXunleiOrderById(id);
	}
	// ===============================================================================
	// 										Message
	// ===============================================================================
	public List<Message> getSysMessage(int id) throws Exception{
		List<Message> retList = getService.getMessageFromOutBoxBySenderName(null,id);
		for(Message mes : retList){
			mes.init();
		}
		return retList;
	}
	// ===============================================================================
	// 										TeamRecord
	// ===============================================================================
    public List<Team> getTeamList() throws Exception{
    	List<Team> retList  = new ArrayList<Team>();
    	try{
    		retList = getService.getTeamList();
    	} catch (Exception e){
    		e.printStackTrace();
    	}
    	return retList;
	}
    public List<TeamRecordObj> getAllTeamRecord(int id) throws Exception{
		return nosqlService.getAllTeamRecord(id);
	}
    // ===============================================================================
	// 										Nosql
	// ===============================================================================
    public List<BasePlayerLogEvent> getPlayerLogList(int playerId,String playerName){
    	List<BasePlayerLogEvent> list=nosqlService.getPlayerLogEvent(playerId, playerName);
    	return list;
    }
    
    
    
    public List<GmUser> getGmUsers(GmUser gu) {
		return gmUserDao.getGmUsers();
	}	
	
	public List<GmUser> getAllGmUsers(){
		return gmUserDao.getAllGmUsers();
	}
	
	public List<GmUser> getGmUsersByGroupId(GmGroup group){
		List<GmUser> userList = gmUserDao.getGmUsersByGroupId(group.getId());
		List<Integer> result = new ArrayList<Integer>();
		for(GmUser user :userList){
			String groupId = user.getGroupIds();
			if(null!=groupId){
				String [] groupIds =groupId.split(",");
				for(String s:groupIds){
					result.add(Integer.valueOf(s));
				}
			}
		}
		if(null!=result&&result.size()>0){
			return gmUserDao.getGmUsersByGroupIds(result);
		}else{
			return new ArrayList<GmUser>();
		}
	}
	
	public List<GmGroup> getGmGroups(GmUser gu){	
		return gmGroupDao.getGmGroups(gu.getId());	
	}
	
	public List<GmGroup> getGmUserGroups(GmUser gu){
		List<GmGroup> groups = gmGroupDao.selectUserCreateGroupsAndSonGroups(gu.getId());
		return groups;
	}
	
	
	public List<GmPrivilege> getGmPrivilegesByGroupId(int groupId){
		return gmPrivilegeDao.getGmPrivilegesByGroupId(groupId);
	}
	public void checkCanWrite(String groups,String Msg) throws Exception{
		CommonUtil.checkNull(groups, null);
		//GROUP_CONCAT函数默认的分隔符为‘,’
		String[] groupId=groups.split(",");
		for(int i=0;i<groupId.length;i++){
			List<GmPrivilege> gmPrivilegeList=gmPrivilegeDao.getGmAdminPrivilegesByGroupId(StringUtil.toInt(groupId[i]));
			if(gmPrivilegeList==null||gmPrivilegeList.size()!=2){
				throw new GmException(Msg);
			}else{
				int flag=0;
				for(GmPrivilege privilege:gmPrivilegeList){
					if(privilege.getId()==17||privilege.getId()==18){
						flag++;
					}
				}
				if(flag!=2){
					throw new GmException(Msg);
				}
			}
		}
	}
	public List<GmPrivilege> getGmPrivilegesByUserId(int userId){
		List<GmPrivilege> list = gmPrivilegeDao.getGmPrivilegesByUserId(userId);
		return list;
	}
	
	public List<GmUser> getLoginGmUser(String gmUserName, String passWord){
		List<GmUser> list = gmUserDao.getLoginGmUser(gmUserName, PasswordUtil.encrypt(passWord));
		return list;
	}
	//修改密码使用。不放在flexUpdateService类中是因为不要记录用户操作。
	//flexUpdateService类中会判断读写权限导致不能修改密码
	public List<GmUser> getGmUserByNameAndPassword(String gmUserName, String oldPswd, String newPswd){
		List<GmUser> list = gmUserDao.getLoginGmUser(gmUserName, PasswordUtil.encrypt(oldPswd));
		if(list.size() > 0){
			GmUser gu = list.get(0);
			gu.setPassword(PasswordUtil.encrypt(newPswd));
			gmUserDao.update(gu);
		}
		return list;
	}
	public List<GmLog> getGmLogs(int gmUserId, String gmUserName, String type,  Date start, Date end){
		return gmLogDao.getGmLogs(gmUserId, gmUserName, type, start, end);
	}
	
	// ===============================================================================
	// 										Activity
	// ===============================================================================
	@SuppressWarnings("unchecked")
	public List<SysActivity> getActivitysList() throws Exception{
		List<SysActivity> list = getService.getAllActivitiesList();
		Collections.sort(list, new Comparator(){

			@Override
			public int compare(Object o1, Object o2) {
				SysActivity s1 = (SysActivity)o1;
				SysActivity s2 = (SysActivity)o2;
				return s1.getId()-s2.getId();
			}
			
		});
		return list;
	}
	
	private List<Integer> achievementIds = java.util.Arrays.asList(1,27,28,29,30,31,32,33,72,73,74,75,76,77,78,79,80,81,82,83,84,85,86,87,558,559,560);
	@SuppressWarnings("unchecked")
	public List<SysAchievement> getAchievementList() throws Exception{
		List<SysAchievement> list = getService.getSysAchievementList();
		List<SysAchievement> returnList = new ArrayList<SysAchievement>();
		for(SysAchievement sa : list){
			if(sa.getType() == 1&&sa.getAction()<38){
				if(!achievementIds.contains(sa.getId())){
					returnList.add(sa);
				}
			}
		}
		Collections.sort(returnList, new Comparator(){

			@Override
			public int compare(Object o1, Object o2) {
				SysAchievement s1 = (SysAchievement)o1;
				SysAchievement s2 = (SysAchievement)o2;
				return s1.getId()-s2.getId();
			}
			
		});
		return returnList;
	}
	
	public List<PlayerVO> getUnSpeakerList() throws Exception{
		List<PlayerVO> resultList = new ArrayList<PlayerVO>();
		List<UnSpeaker> unSpeakerList = getAllUnSpeakerList();
		for(UnSpeaker unSpeaker : unSpeakerList){
			int playerId = unSpeaker.getPlayerId();
			Player player = getPlayerById(playerId);
			PlayerVO playerVO = new PlayerVO();
			playerVO.setId(player.getId());
			playerVO.setDeleted(player.getDeleted());
			playerVO.setUserName(player.getUserName());
			playerVO.setName(player.getName());
			resultList.add(playerVO);
		}
		return resultList;
	}
	
	/*
	 * ------------------------------------------------------ Lucky Package -------------------------------------------------------------
	 */
	public List<SysChest> getChestList(int type, int level) throws Exception{
		List<SysChest> resultList = new ArrayList<SysChest>();
		Map<Integer, SysChest> map = getAllSysChestMap();
		Map<Integer, SysItem> sysItemMap = sysItemDao.getAllSysItemMap();
		
		for(SysChest sc : map.values()){
			if(sc.getType() == type && (level == Constants.NUM_ZERO || sc.getLevel() == level)){
				sc.setSysItemName(sysItemMap.get(sc.getSysItemId()).getDisplayNameCN());
//				sc.setSysItem(sysItemMap.get(sc.getSysItemId()));
				resultList.add(sc);
			}
		}
		
		Collections.sort(resultList);
		
		return resultList;
	}
	public List<SysChest> getFixedList() throws Exception {
		return getChestList(Constants.NUM_ONE, Constants.NUM_ZERO);
	}

	public List<SysChest> getRandomList() throws Exception {
		return getChestList(Constants.NUM_TWO, Constants.NUM_ZERO);
	}
	
	public int test() throws Exception {
//		soClient.proxyBroadCast(Constants.MSG_NBA, Constants.SYSTEM_SPEAKER,
//				CommonUtil.messageFormat(CommonMsg.STRENGTH_SYS, new Object[] { (5 + "##!" +  "我的名字"), (5 + "##!" +  10), (5 + "##!" +  "机枪") }));
		soClient.proxyBroadCast(Constants.MSG_NBA, Constants.SYSTEM_SPEAKER,"@!0@!恭喜玩家@!0@!0@!在强化装备时，运气不错！将“@!2@!0@!”强化到了@!1@!0@!级！战斗力大增！");
		return 1;
	}
	
	/*=========online award=========*/
	
	public List<OnlineAward> getOnlineAwardList()throws Exception {
		return getService.getOnlineAwardSysItemList();
	}
	
	public List<StrengthenAppend> getStrengthenAppendList()throws Exception{
		return strengthenAppendDao.getAllStreAppList();
	}
	
	public String getPlayerPtWeights() throws Exception{
		String playerGetPtWtRds = nosqlService.getNosql().get(Constants.PLAYER_PT_GET_WEIGHTS_KEY);
		if(playerGetPtWtRds==null || !playerGetPtWtRds.matches("0\\.[0-9]*")){
			playerGetPtWtRds = String.valueOf(Constants.PLAYER_PT_GET_DFT_WT);
			nosqlService.getNosql().set(Constants.PLAYER_PT_GET_WEIGHTS_KEY, playerGetPtWtRds);
		}
		return playerGetPtWtRds;
	}
	public String getPlayerPtFlagWeights_1() throws Exception{
		String flagWts = nosqlService.getNosql().get(Constants.PLAYER_PT_FLAG_GET_WEIGHTS_KEY + 1);
		if(flagWts==null||!flagWts.matches("[0-9]*:[0-9]*")){
			flagWts = Constants.PLAYER_PT_FLAG_GET_WEIGHTS_DFT_VALS[0];
			nosqlService.getNosql().set(Constants.PLAYER_PT_FLAG_GET_WEIGHTS_KEY + 1,flagWts );
		}
		return flagWts;
	}
	public String getPlayerPtFlagWeights_2() throws Exception{
		String flagWts = nosqlService.getNosql().get(Constants.PLAYER_PT_FLAG_GET_WEIGHTS_KEY + 2);
		if(flagWts==null||!flagWts.matches("[0-9]*:[0-9]*")){
			flagWts = Constants.PLAYER_PT_FLAG_GET_WEIGHTS_DFT_VALS[1];
			nosqlService.getNosql().set(Constants.PLAYER_PT_FLAG_GET_WEIGHTS_KEY + 2,flagWts );
		}
		return flagWts;
	}
	
}
