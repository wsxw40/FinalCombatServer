package com.pearl.o2o.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.exception.MemcachedException;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import com.pearl.o2o.dao.AllyDao;
import com.pearl.o2o.dao.CharacterDao;
import com.pearl.o2o.dao.CharacterLogDao;
import com.pearl.o2o.dao.FriendDao;
import com.pearl.o2o.dao.MessageDao;
import com.pearl.o2o.dao.PlayerDao;
import com.pearl.o2o.dao.PlayerItemDao;
import com.pearl.o2o.dao.PlayerPackDao;
import com.pearl.o2o.dao.PlayerTeamDao;
import com.pearl.o2o.dao.SysItemDao;
import com.pearl.o2o.dao.SysItemLogDao;
import com.pearl.o2o.dao.SysLevelDao;
import com.pearl.o2o.dao.SysModeConfigDao;
import com.pearl.o2o.dao.SystemDao;
import com.pearl.o2o.dao.TeamDao;
import com.pearl.o2o.dao.TeamHistoryDao;
import com.pearl.o2o.dao.UserDao;
import com.pearl.o2o.exception.BaseException;
import com.pearl.o2o.exception.DuplicateAllyException;
import com.pearl.o2o.exception.IllegalCharacterException;
import com.pearl.o2o.exception.NotEquipException;
import com.pearl.o2o.pojo.Channel;
import com.pearl.o2o.pojo.Character;
import com.pearl.o2o.pojo.CharacterLog;
import com.pearl.o2o.pojo.Friend;
import com.pearl.o2o.pojo.LevelInfoPojo;
import com.pearl.o2o.pojo.LevelWeapon;
import com.pearl.o2o.pojo.Player;
import com.pearl.o2o.pojo.PlayerItem;
import com.pearl.o2o.pojo.PlayerTeam;
import com.pearl.o2o.pojo.Rank;
import com.pearl.o2o.pojo.Server;
import com.pearl.o2o.pojo.StageClearPlayerInfo;
import com.pearl.o2o.pojo.SysItem;
import com.pearl.o2o.pojo.SysItemLog;
import com.pearl.o2o.pojo.SysModeConfig;
import com.pearl.o2o.pojo.Team;
import com.pearl.o2o.pojo.TeamScoreIncrement;
import com.pearl.o2o.utils.CacheUtil;
import com.pearl.o2o.utils.CommonUtil;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.Constants.GAMETYPE;
import com.pearl.o2o.utils.Constants.TEAMJOB;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.KeywordFilterUtil;

public class UpdateService {
	static Logger log = Logger.getLogger(UpdateService.class.getName());


	//===============================================================================
	//								  User Services
	//===============================================================================

	public void updateSysModeConfig(Integer type,String config)throws Exception{
		SysModeConfig modeConfig=new SysModeConfig();
		modeConfig.setType(type);
		modeConfig.setConfig(config);
		sysModeConfigDao.updateSysModeConfigDao(modeConfig);
	}
	public void updateConfig(String config)throws Exception{
		updateSysModeConfig(3,config);
	}
	//===============================================================================
	//								  Character Services
	//===============================================================================
	@Transactional
	public void updateCharacter(Character character) throws Exception{
		mcc.flushAll();
		CharacterLog characterLog=new CharacterLog();
		int version=getService.getLogVersionFromCharacterLog(character.getId());
		characterDao.updateCharacter(character);
		characterLog.setCharacter(character);
		characterLog.setLogVersion(version+1);
		characterLogDao.createCharacterLog(characterLog);
	}

	public void updateCharacter(List<Character> characters)throws Exception{
		boolean isUpdate=false;
		for(Character character:characters){
			if(character.getIsChange()==1){
				if(!isUpdate){
					mcc.delete(CacheUtil.oCharacter());//only delete once for a series update
				}
				isUpdate=true;
				updateCharacter(character);
			}
		}
	}

	//===============================================================================
	//								  Player Services
	//===============================================================================
	//for mxml
	public void updatePlayer(Player player)throws Exception{
		mcc.flushAll();
		mcc.delete(CacheUtil.oPlayer(player.getId()));
		mcc.delete(CacheUtil.oPlayer(player.getId()));
		mcc.delete(CacheUtil.oPlayer(player.getId()));
		mcc.delete(CacheUtil.oPlayer(player.getId()));
		mcc.delete(CacheUtil.oPlayer(player.getId()));
		mcc.delete(CacheUtil.oPlayer(player.getId()));
		mcc.delete(CacheUtil.oPlayer(player.getId()));

		playerDao.updatePlayer(player);
	}
	@Transactional
	public void updatePlayer(List<Player> playerList)throws Exception{
		boolean isUpdate=false;
		for(Player p:playerList){
			if(p.getIsChange()==1){
				isUpdate=true;
				updatePlayer(p);
			}
		}
	}

	public void updatePlayer(Integer userId, Integer playerId, Integer friendId, String isBlackList) throws Exception {
		Friend friend = new Friend();
		friend.setUserId(userId);
		friend.setPlayerId(playerId);
		friend.setFriendId(friendId);
		friend.setIsBlackList(isBlackList);

		List<Friend> list = friendDao.getFriend(userId, playerId, friendId, null);
		if(list!=null && list.size()>0){
			friendDao.update(friend);
		}
		else{
			friendDao.create(friend);
		}
	}
	public void updatePlayerWhileOnline(Player player) throws Exception {
		mcc.delete(CacheUtil.oPlayer(player.getId()));
		mcc.delete(CacheUtil.sPlayer(player.getId()));
		playerDao.updateWhileOnline(player.getLastLoginIp(), player.getId());
	}


	//this method will change the value of parameter 'playerInfos'
	@Transactional(rollbackFor = Exception.class)
	public void updatePlayerStageClear(List<StageClearPlayerInfo> playerInfos, final int type, final int winnerSide) throws Exception{
		List<Rank> rankList = getService.getRanks();
		//key : teamId, value:increment for team
		Map<Integer,TeamScoreIncrement> teamIncrement = new HashMap<Integer,TeamScoreIncrement>();
		Collection<Player> players = new ArrayList<Player>();

		GAMETYPE gameType = Constants.GAMETYPE.getTypeByValue(type);
		if(gameType == null){
			log.error("unknown gameType when processing the stage clear, type :" + type);
			return ;
		}

		for (StageClearPlayerInfo playerInfo : playerInfos) {
			//TODO change back later
			Player player = playerDao.getPlayerById(playerInfo.getId());
			//Player player = playerDao.getPlayerByName(playerInfo.getName());
			TeamScoreIncrement tsr = teamIncrement.get(player.getTeamId());
			if (tsr == null && player.getTeamId()!= null) {
				tsr = new TeamScoreIncrement();
				tsr.setTeamId(player.getTeamId());
				teamIncrement.put(tsr.getTeamId(), tsr);
			}

			//calculate exp and gp
			float weight = 1;
			if (playerInfo.getOnlineMinutes() > 300) {//5 hours,  gain no exp and gp
				weight = 0;
			}else if(playerInfo.getOnlineMinutes() >= 180){//3 hours, gain 50% exp and gp
				weight = 0.5f;
			}//TODO : offline minutes?

			//TODO: some item may change the expIncrement and gpIncrement
			int expIncrement = (int) (Math.sqrt(playerInfo.getScore()) * 1.7 * weight);
			int gpIncrement = (int) (Math.ceil(Math.sqrt(playerInfo.getScore())) * weight);

			//set some old value to 'playerInfo'
			//the 'playerInfo' object will be return to client as a DTO, so client needs to get some old value before stage clear from this object
			playerInfo.setCurr_exp(player.getExp());
			playerInfo.setExp(expIncrement);
			playerInfo.setGp(gpIncrement);
			playerInfo.setRank(player.getRank());// rank and exp in playerInfo represent old value, can not move their position
			//set player
			player.setGPoint(player.getGPoint() + gpIncrement);
			player.setExp(player.getExp() + expIncrement);
			int newRank = getService.getRankByExp(player.getExp(), rankList).getId();
			if (newRank > player.getRank()){//TODO: allow decrease the level if the exp has been changed ?
				player.setRank(newRank);
			}
			player.setLastGameSide(playerInfo.getSide());
			//general
			player.setGGrenadeKill(player.getGGrenadeKill() + playerInfo.getGrenade_kill());
			player.setGDead(player.getGDead() + playerInfo.getDead());
			player.setGHeadShot(player.getGHeadShot() + playerInfo.getHead_shot());
			player.setGHitPoint(player.getGHitPoint() + playerInfo.getHit_point());
			player.setGKill(player.getGKill() + playerInfo.getKill());
			player.setGKnifeKill(player.getGKnifeKill() + playerInfo.getKnife_kill());
			player.setGScore(player.getGScore() + playerInfo.getScore());
			player.setGTotal(player.getGTotal() + 1);
			//general for team
			if (tsr != null){
				tsr.setKill(tsr.getKill() + playerInfo.getKill());
				tsr.setHeadShot(tsr.getHeadShot() + playerInfo.getHead_shot());
				tsr.setGameTotal(tsr.getGameTotal() + 1);
			}
			boolean isWin = playerInfo.getSide() == winnerSide;
			if (isWin){
				player.setGWin(player.getGWin() + 1);
				if (tsr != null){
					tsr.setGameWin(tsr.getGameWin() + 1);
				}
			}
			switch(gameType){
				case TEAM :
					{
						player.setTKill(player.getTKill() + playerInfo.getKill() );
						player.setTDead(player.getTDead() + playerInfo.getDead());
						player.setTGrenadeKill(player.getTGrenadeKill() + playerInfo.getGrenade_kill());
						player.setTHeadShot(player.getTHeadShot() + playerInfo.getHead_shot());
						player.setTKnifeKill((player.getTKnifeKill() + playerInfo.getKnife_kill()));
						player.setTHitPoint(player.getTHitPoint() + playerInfo.getHit_point());
						player.setTScore(player.getTScore() + playerInfo.getScore());
						player.setTTotal(player.getTTotal() + 1);
						if (isWin) {
							player.setTWin(player.getTWin() + 1 );
						}
					}break;
				case BIO :
					 {//bio
						player.setBioInfect(player.getBioInfect() + playerInfo.getBio_infect());
						player.setBioScore(player.getBioScore() + playerInfo.getScore());
						player.setBioKill(player.getBioKill() + playerInfo.getKill());
					}break;
				case FLAG :
					 {//flag
						player.setFlagReturn(player.getFlagReturn() + playerInfo.getFlag_return());
						player.setFlagSucceed(player.getFlagSucceed() + playerInfo.getFlag_succeed());
						player.setFlagTotal(player.getFlagTotal() + 1);
						player.setFlagScore(player.getFlagScore() + playerInfo.getScore());
						if (isWin) {
							player.setFlagWin(player.getFlagWin() + 1);
						}
					 }break;
				case BLAST :
					{//blast
						player.setBlastDisable(player.getBlastDisable() + playerInfo.getBlast_disable());
						player.setBlastSucceed(player.getBlastSucceed() + playerInfo.getBlast_succeed());
						player.setBlastScore(player.getBlastScore() + playerInfo.getScore());
						player.setBlastTotal(player.getBlastTotal() + 1);
						if (isWin){
							player.setBlastWin(player.getBlastWin() + 1);
						}
					}break;

				case DEATH:
					{
						//TODO currently use TEAM, will changed lately
						player.setTKill(player.getTKill() + playerInfo.getKill() );
						player.setTDead(player.getTDead() + playerInfo.getDead());
						player.setTGrenadeKill(player.getTGrenadeKill() + playerInfo.getGrenade_kill());
						player.setTHeadShot(player.getTHeadShot() + playerInfo.getHead_shot());
						player.setTKnifeKill((player.getTKnifeKill() + playerInfo.getKnife_kill()));
						player.setTHitPoint(player.getTHitPoint() + playerInfo.getHit_point());
						player.setTScore(player.getTScore() + playerInfo.getScore());
						player.setTTotal(player.getTTotal() + 1);
						if (isWin) {
							player.setTWin(player.getTWin() + 1 );
						}
					}
				default : 	log.warn("unhandle game type when calculate the stage clear:" + gameType.getValue());
			}
			players.add(player);
		}
		//update players
		playerDao.updatePlayerStageClear(players);
		//update players's weapon
		//TODO: packId
		//playerItemDao.batchDecreaseWeaponDurable(players, Constants.WEAPON_DURABLE_COST);
		//update team
		teamDao.updateTeamGameInfo(teamIncrement.values());

		if (false) { //TODO team battle?
			//teamHistoryDao.addHistory(teamId, opponentName, result, null);
			//teamHistoryDao.addHistory(teamId, opponentName, result, null);
		}

		//use item
		//TODO

		//clear cache
		for (Player player : players) {
			mcc.delete(CacheUtil.oPlayer(player.getId()));
			mcc.delete(CacheUtil.sPlayer(player.getId()));
			mcc.delete(CacheUtil.oStorage(player.getId(),Constants.DEFAULT_WEAPON_TYPE));
			mcc.delete(CacheUtil.sStorage(player.getId(), Constants.DEFAULT_WEAPON_TYPE));
			mcc.delete(CacheUtil.oWeaponsInAllPacks(player.getId()));
			for (int i=1;i<=Constants.MAX_WEAPON_PACK_SIZE;i++){
				mcc.deleteWithNoReply(CacheUtil.sWeaponPack(player.getId(), i));
			}
		}
		//put into cache
		try{
			for (Player player : players) {
				mcc.setWithNoReply(CacheUtil.oPlayer(player.getId()),  Constants.CACHE_ITEM_TIMEOUT, player);
				mcc.setWithNoReply(CacheUtil.sPlayer(player.getId()),  Constants.CACHE_ITEM_TIMEOUT, player);
			}
		}catch(Exception e){
			//do nothing, just catch all exception during set into cache for not roll back
		}
	}

	//===============================================================================
	//								  Friend Services
	//===============================================================================
	public void updateFriend(Integer userId, Integer playerId, Integer friendId, String isBlackList) throws Exception {
		Friend friend = new Friend();
		friend.setUserId(userId);
		friend.setPlayerId(playerId);
		friend.setFriendId(friendId);
		friend.setIsBlackList(isBlackList);

		List<Friend> list = friendDao.getFriend(userId, playerId, friendId, null);
		if(list!=null && list.size()>0){
			friendDao.update(friend);
		}
		else{
			friendDao.create(friend);
		}
	}

	//===============================================================================
	//								  Server Services
	//===============================================================================

	//===============================================================================
	//								  Level  Services
	//===============================================================================

	@Transactional
	private void updateLevelInfoPojo(LevelInfoPojo info)throws Exception{
		mcc.flushAll();
		sysLevelDao.updateLevel(info);
	}

	public void updateLevelInfoList(List<LevelInfoPojo> infoList)throws Exception{
		try{
		boolean  isUpdate=false;
		for(LevelInfoPojo info:infoList){
			if(info.getIsChange()==1){
//				if(!isUpdate){
//					mcc.delete(CacheUtil.sShop(sysItem.getType(),sysItem.getSubType(),sysItem.getCGender()));
//					mcc.delete(CacheUtil.oShop(sysItem.getType(),sysItem.getSubType(),sysItem.getCGender()));
//				}
				isUpdate=true;
				updateLevelInfoPojo(info);
			}
		}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Transactional
	private void updateLevelWeapon(LevelWeapon info)throws Exception{
		mcc.flushAll();
		sysLevelDao.updateLevelWeapon(info);
	}

	public void updateLevelWeapons(List<LevelWeapon> infoList)throws Exception{
		try{
		boolean  isUpdate=false;
		for(LevelWeapon info:infoList){
			if(info.getIsChange()==1){
//				if(!isUpdate){
//					mcc.delete(CacheUtil.sShop(sysItem.getType(),sysItem.getSubType(),sysItem.getCGender()));
//					mcc.delete(CacheUtil.oShop(sysItem.getType(),sysItem.getSubType(),sysItem.getCGender()));
//				}
				isUpdate=true;
				updateLevelWeapon(info);
			}
		}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	//===============================================================================
	//								  Channel Services
	//===============================================================================
	public void updateChannel(int channelId, int online, int max) throws Exception  {
		StringBuffer server = new StringBuffer(Constants.GET_SERVER_LIST);
		Map<Integer, Server> serverMap = new HashMap<Integer, Server>();

		serverMap = (Map<Integer, Server>) mcc.get(server.toString());
		if (serverMap != null && serverMap.size() != 0) {
			Iterator iterator = serverMap.keySet().iterator();
			while (iterator.hasNext()) {
				Server si = (Server) serverMap.get(iterator.next());
				if (si != null) {
					Map<Integer, Channel> map = si.getChannelMap();
					if (map != null && map.size() != 0) {
						Channel ci = map.get(channelId);
						if (ci != null) {
							int dev = online-ci.getOnline() ;
							int maxdec = max - ci.getMax();
							ci.setOnline(online);
							ci.setMax(max);
							si.setChannelMap(map);
							si.setOnline(si.getOnline()+dev);
							si.setMax(si.getMax()+maxdec);
							serverMap.put(si.getId(), si);
							break;
						}
					}
				}
			}
		} else {
			serverMap=getService.getServerMap();
			for(Integer t : serverMap.keySet()){
				Server si=serverMap.get(t);
				if (si != null) {
					getService.getChannelList(si.getId());
				}
			}
			serverMap = (Map<Integer, Server>) mcc.get(server.toString());
			for(Integer t : serverMap.keySet()){
				Server si=serverMap.get(t);
				if (si != null) {
					Map<Integer, Channel> map = si.getChannelMap();
					if (map != null && map.size() != 0) {
						Channel ci = map.get(channelId);
						if (ci != null) {
							int dev = online-ci.getOnline() ;
							int maxdec = max - ci.getMax();
							ci.setOnline(online);
							ci.setMax(max);
							si.setChannelMap(map);
							si.setOnline(si.getOnline()+dev);
							si.setMax(si.getMax()+maxdec);
							serverMap.put(si.getId(), si);
							break;
						}
					}
				}

		    }


		}
		mcc.set(server.toString(), Constants.CACHE_ITEM_TIMEOUT, serverMap);
	}
	//===============================================================================
	//								  Pack Services
	//===============================================================================
	@Transactional
	public void updateWeaponPackEquipment(Integer userId, Integer playerId,Integer type,Integer playerItemId,Integer packId)throws Exception{
		//get  playerItemId
		PlayerItem playerItem= getService.getPlayerItemByItemId(userId, playerId, type, playerItemId);//2695
		//get seq
		int seq=CommonUtil.getWeaponSeq(playerItem.getWId());
		if(type!=Constants.DEFAULT_WEAPON_TYPE || seq == 0 ){
			throw new NotEquipException("Weapon can not be equiped!");
		}

		//judge whether this item was in pack can not only by  playerItem.pack==0? In some case,
		//item.pack ==0, but the expired pack is still in cache, update the database will violate the unique constraint
		//so use 'getWeaponsInAllPacks' to judge whether this item was in any packs
		Map<Integer,List<PlayerItem>> weaponsInPack = getService.getWeaponsInAllPacks(playerId);


		//check weapon in pack?
		for (List<PlayerItem> pack : weaponsInPack.values()) {
			for (PlayerItem pi : pack) {
				if (pi.getId()!= null &&pi.getId().equals(playerItemId)) {
					return ;// this item was in some pack.
				}
			}
		}

		//delete the pack info from memcache
		mcc.delete(CacheUtil.oWeaponsInAllPacks(playerId));
		mcc.delete(CacheUtil.sWeaponPack(playerId, packId));
		mcc.delete(CacheUtil.oStorage(playerId,type));
		mcc.delete(CacheUtil.sStorage(playerId,type));
		//get Player
		//Player player=getService.getPlayerById(playerId);

		//update the pack info in database
		playerPackDao.createWeaponPackEquipment(userId, playerId, playerItemId, packId, seq);
	}

	@Transactional
	public void updateCostumePackEquipment(Integer userId, Integer playerId,Integer type,Integer playerItemId,Integer packId,Integer seqIndex)throws Exception{
		PlayerItem playerItem= getService.getPlayerItemByItemId(userId, playerId, type, playerItemId);
		mcc.delete(CacheUtil.oPlayer(playerId));
		mcc.delete(CacheUtil.sPlayer(playerId));
		mcc.delete(CacheUtil.oStorage(playerId,type));
		mcc.delete(CacheUtil.sStorage(playerId, type));
	 if(playerItem.getPack()==0){
		 //if have item in pack,judge if have costume set
		 List<PlayerItem> packList = mcc.get(CacheUtil.oCostumePack(playerId, Constants.NUM_ONE, packId));
		 if(packList==null){
			 packList=getService.getCostumePackList(playerId, Constants.NUM_ONE, packId);
		 }
		 for(PlayerItem pi:packList){
			 if(pi.getId()!=null&&pi.getType()==Constants.DEFAULT_COSTUME_TYPE&&pi.getSubType()==Constants.DEFAULT_COSTUME_SET_SUBTYPE){
				 playerPackDao.deletePackEquipment(userId, playerId, packId, type, pi.getSeq());
				 break;
			 }
		 }
			//get Player
			Player player=getService.getPlayerById(playerId);
			//get seq
			Integer seq=CommonUtil.getCotumeSeq(playerItem.getSubType());
			if(playerItem.getSubType()==Constants.DEFAULT_COSTUME_SET_SUBTYPE){
				if(seqIndex!=0){
					seq = seqIndex;      		//从服装选择界面进入
				}else{
					seq=Constants.NUM_ONE;		//在仓库里面直接装备
				}
				playerPackDao.deletePackAll(userId, playerId, packId, type);
			}
			if(type!=Constants.DEFAULT_COSTUME_TYPE || seq == 0 ||!(playerItem.getCGender()).equals(player.getGender())){
				throw new NotEquipException("Costume can not be equiped!");
			}
			//delete the pack info from memcache
			mcc.delete(CacheUtil.oCostumePack(playerId, Constants.NUM_ONE, playerItem.getCSide()));
			mcc.delete(CacheUtil.sCostumePack(playerId, Constants.NUM_ONE,playerItem.getCSide()));

			//update the pack info in database
			playerPackDao.createCostumePackEquipment(userId, playerId, playerItemId, Constants.NUM_ONE, seq, playerItem.getCSide());
		}
	}
	//===============================================================================
	//								  SysItem Services
	//===============================================================================
	@Transactional
	private void updateSysItem(SysItem sysItem)throws Exception{
		mcc.flushAll();
		SysItemLog sysItemLog=new SysItemLog();
		int version=getService.getLogVersionFromSysItemLog(sysItem.getId());
		sysItemDao.updateSysItem(sysItem);
		sysItemLog.setSysItem(sysItem);
		sysItemLog.setLogVersion(version+1);
		sysItemLogDao.createSysItemLog(sysItemLog);

	}

	public void updateSysItem(List<SysItem> sysItemList)throws Exception{

		boolean isUpdate=false;
		for(SysItem sysItem:sysItemList){
			if(sysItem.getIsChange()==1){
				if(!isUpdate){
					mcc.delete(CacheUtil.sShop(sysItem.getType(),sysItem.getSubType(),sysItem.getCGender()));
					mcc.delete(CacheUtil.oShop(sysItem.getType(),sysItem.getSubType(),sysItem.getCGender()));
				}
				isUpdate=true;
				updateSysItem(sysItem);
			}
		}
	}

	//===============================================================================
	//								 Player Item Services
	//===============================================================================
	@Transactional
	public void fixPlayerItem(int userId,int playerId,int playerItemId,int type)throws Exception{
		PlayerItem playerItem=getService.getPlayerItemByItemId(userId, playerId, type, playerItemId);
		/*if(playerItem.getPack()!=0){
			//mcc.delete(CacheUtil.oWeaponPack(playerId, playerItem.getPack()));
			mcc.delete(CacheUtil.oWeaponsInAllPacks(playerId));
		}*/
		Player player=getService.getPlayerById(playerId);

		playerItem.calculateParam();
		int gp=player.getGPoint();
		int rc=playerItem.getRepairCost();
		if(gp<rc){
			throw new BaseException(ExceptionMessage.NOT_ENOUGH_GP_FIX);
		}

		mcc.delete(CacheUtil.oStorage(playerId, type));
		mcc.delete(CacheUtil.sStorage(playerId, type));
		mcc.delete(CacheUtil.oPlayer(playerId));
		mcc.delete(CacheUtil.sPlayer(playerId));
		//clear cache for pack
		mcc.delete(CacheUtil.oWeaponsInAllPacks(playerId));
		for (int i=1;i<=Constants.MAX_WEAPON_PACK_SIZE;i++){
			mcc.deleteWithNoReply(CacheUtil.sWeaponPack(playerId, i));
		}
		//1.update GP
		playerDao.updateGP(gp-rc, playerId);
		//2.update durable
		playerItemDao.updatePlayerItem(userId, playerId, playerItemId, "DURABLE", 100);
	}

	//===============================================================================
	//								  Message Services
	//===============================================================================
	@Transactional
	public void updateMessage(int playerId,int messageId)throws Exception{
		mcc.delete(CacheUtil.oPlayerMessage(playerId));
		mcc.delete(CacheUtil.sPlayerMessage(playerId));

		messageDao.deleteMessage(messageId);
		messageDao.deleteMessageItem(messageId);
	}

	//===============================================================================
	//								  Team Services
	//===============================================================================
	@Transactional
	public void updateTeamGameInfo(Collection<TeamScoreIncrement> teamScoreIncrement) throws SQLException{
		teamDao.updateTeamGameInfo(teamScoreIncrement);
	}
	public void upgradeTeam(int userId,int playerId,int playerItemId,int teamId,int type)throws Exception{
		//1.get PlayerItem and get upgrade size,use it
//		PlayerItem playerItem=getService.getPlayerItemByItemId(userId, playerId, type, playerItemId);
//		int size=playerItem.getIValue();//get upgrade size
		//2.get team info
		Team team=getService.getTeam(teamId);
//		team.setSize();//TODO for test
		//3.update team size
		teamDao.updateSize(teamId, team.getSize()+20);
	}

	public void updateTeamInfo(String field,String value, int teamId) throws IllegalCharacterException{
		if (!KeywordFilterUtil.isLegalInput(value)) {
			throw new IllegalCharacterException(ExceptionMessage.ILLEGAL_CHARACTER);
		}
		teamDao.updateTeamInfo(field, value, teamId);
	}

	public void addAlly(int teamId, int allyId) throws Exception{
		//verify
		Team team = teamDao.getTeamById(teamId);
		for (Team ally : team.getTeamList()) {
			if (ally.getId().equals(allyId) || ally.getId().equals(teamId)) {
				throw new DuplicateAllyException();
			}
		}
		allyDao.addAlly(teamId, allyId);
	}

	public void removeAlly(int teamId, int allyId){
		allyDao.removeAlly(teamId, allyId);
	}


	@Transactional
	public void applyTeam(int teamId,  int playerId) throws Exception{
		//first check the player's apply history
		PlayerTeam pt = playerTeamDao.getByPlayerId(playerId);
		if (pt != null) {
			if (Constants.BOOLEAN_YES.equals(pt.getApproved())) {
				//TODO CREATE exception type
				throw new Exception("此玩家已加入您的战队");
			}

			//update the application if has old one
			playerTeamDao.updateTeamApply(teamId, playerId);
		}
		//create new
		else {
			PlayerTeam playerTeam = new PlayerTeam ();
			playerTeam.setApproved(Constants.BOOLEAN_NO);
			playerTeam.setJob(Constants.TEAMJOB.TEAM_MEMBER.getValue());
			playerTeam.setTeamId(teamId);
			playerTeam.setPlayerId(playerId);
			playerTeamDao.createPlayerTeam(playerTeam);
		}
	}

	//teamMember accept the team invitation
	@Transactional
	public void acceptJoinTeam(int teamId, int playerId) throws Exception{
		PlayerTeam pt = playerTeamDao.getByPlayerId(playerId);
		if (pt != null ) {
			if (Constants.BOOLEAN_YES.equals(pt.getApproved())){
				//do nothing
				return ;
			}
			playerTeamDao.removeAllApplicationFromPlayer(playerId);
		}
		PlayerTeam playerTeam = new PlayerTeam ();
		playerTeam.setApproved(Constants.BOOLEAN_YES);
		playerTeam.setJob(Constants.TEAMJOB.TEAM_MEMBER.getValue());
		playerTeam.setTeamId(teamId);
		playerTeam.setPlayerId(playerId);
		playerTeamDao.createPlayerTeam(playerTeam);
	}

	//team leader approve the application
	public Map<String,List<String>> approve(int teamId, String[] applyIds) throws Exception{
		Map<String,List<String>> fails = new HashMap<String,List<String>>();
		fails.put(ExceptionMessage.TEAM_SIZE_NOT_ENOUGH, new ArrayList<String>());
		fails.put(ExceptionMessage.APPROVE_ERROR, new ArrayList<String>());

		Team team = getService.getTeam(teamId);
		int orginalMemberNums = team.getMemberList().size();
		int updateNums = 0;

		for (String playerId : applyIds) {
			int id = Integer.valueOf(playerId);
			Player player = getService.getPlayerById(id);
			//size not enough
			if (orginalMemberNums + updateNums + 1 >  team.getSize()) {
				fails.get(ExceptionMessage.TEAM_SIZE_NOT_ENOUGH).add(player.getName());
				continue;//continue to add id into fail list
			}
			int inffectRows = playerTeamDao.approve(player, teamId);
			//0 rows effected, means this application has already been changed, can not find in db
			if (inffectRows == 0) {
				fails.get(ExceptionMessage.APPROVE_ERROR).add(player.getName());
			}else {
				updateNums ++ ;
				mcc.deleteWithNoReply(CacheUtil.oPlayer(id));
				mcc.deleteWithNoReply(CacheUtil.sPlayer(id));
			}
		}
		return fails;
	}

	//team leader reject the application, or kick someone
	public void removePlayerTeam(int teamId, String[] playerIds ){
		playerTeamDao.batchRemove(teamId, playerIds);
	}

	@Transactional
	public void handoverLeader(int oldLeaderId, int newLeaderId) throws InterruptedException, MemcachedException{
		PlayerTeam oldLeader = playerTeamDao.getByPlayerId(oldLeaderId);
		PlayerTeam newLeader = playerTeamDao.getByPlayerId(newLeaderId);
		if (oldLeader == null || newLeader == null || !oldLeader.getJob().equals(Constants.TEAMJOB.TEAM_CAPTAIN.getValue()) || !oldLeader.getTeamId().equals(newLeader.getTeamId())) {
			throw new IllegalArgumentException("parameter not correct");
		}

		playerTeamDao.updateJob(newLeaderId, Constants.TEAMJOB.TEAM_CAPTAIN.getValue());
		playerTeamDao.updateJob(oldLeaderId, Constants.TEAMJOB.TEAM_MEMBER.getValue());
	}

	//team member ask for quit or he has been kicked
	public void quit(int teamId, int playerId) throws InterruptedException, MemcachedException, TimeoutException{
		playerTeamDao.remove(teamId, playerId);
		mcc.delete(CacheUtil.oPlayer(playerId));
		mcc.delete(CacheUtil.sPlayer(playerId));
	}

	public void updatePlayerJob(PlayerTeam oper, PlayerTeam target, int newJob) throws Exception{
		if (target == null || oper == null) {
			throw new Exception(ExceptionMessage.TEAM_OP_FAIL);
		}

		if (oper.getPlayerId().equals(target.getPlayerId())) {// can not manage himself
			throw new Exception(ExceptionMessage.TEAM_OP_FAIL);
		}

		TEAMJOB opTeamJob = Constants.TEAMJOB.getTypeByValue(oper.getJob());
		TEAMJOB targetTeamJob = Constants.TEAMJOB.getTypeByValue(target.getJob());

		//only captain and officer can manager, and only high level can do operation on low level
		if (!opTeamJob.isGreateThan(targetTeamJob) || !(opTeamJob.equals(TEAMJOB.TEAM_CAPTAIN) || opTeamJob.equals(TEAMJOB.TEAM_OFFICER)) ){
			throw new Exception(ExceptionMessage.TEAM_OP_FAIL);
		}
		playerTeamDao.updateJob(target.getPlayerId(), newJob);
	}
	//===============================================================================
	//								  Player Pack
	//===============================================================================


	//===============================================================================
	//								  System Config
	//===============================================================================
	public void updateSysValue(String key, String value){
		systemDao.setValue(key, value);
	}

	//===============================================================================
	//								  Injected Objects
	//===============================================================================
	private MemcachedClient 	mcc;
	private UserDao 			userDao;
	private PlayerDao 			playerDao;
	private FriendDao 			friendDao;
	private SysItemDao			sysItemDao;
	private SysItemLogDao		sysItemLogDao;
	private PlayerPackDao       playerPackDao;
	private PlayerItemDao       playerItemDao;
	private MessageDao			messageDao;
	private CharacterDao		characterDao;
	private CharacterLogDao		characterLogDao;
	private GetService 			getService;
	private TeamDao				teamDao;
	private TeamHistoryDao 	    teamHistoryDao;
	private PlayerTeamDao		playerTeamDao;
	private SysLevelDao			sysLevelDao;
	private SysModeConfigDao    sysModeConfigDao;
	private AllyDao				allyDao;
	private SystemDao			systemDao;

	public void setSysModeConfigDao(SysModeConfigDao sysModeConfigDao) {
		this.sysModeConfigDao = sysModeConfigDao;
	}

	public void setSysLevelDao(SysLevelDao sysLevelDao) {
		this.sysLevelDao = sysLevelDao;
	}

	public void setGetService(GetService getService) {
		this.getService = getService;
	}
	public void setPlayerPackDao(PlayerPackDao playerPackDao) {
		this.playerPackDao = playerPackDao;
	}
	public void setMcc(MemcachedClient mcc) {
		this.mcc = mcc;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public void setPlayerDao(PlayerDao playerDao) {
		this.playerDao = playerDao;
	}

	public void setFriendDao(FriendDao friendDao) {
		this.friendDao = friendDao;
	}



	public void setSysItemDao(SysItemDao sysItemDao) {
		this.sysItemDao = sysItemDao;
	}



	public void setSysItemLogDao(SysItemLogDao sysItemLogDao) {
		this.sysItemLogDao = sysItemLogDao;
	}



	public void setMessageDao(MessageDao messageDao) {
		this.messageDao = messageDao;
	}



	public void setCharacterDao(CharacterDao characterDao) {
		this.characterDao = characterDao;
	}
	public void setCharacterLogDao(CharacterLogDao characterLogDao) {
		this.characterLogDao = characterLogDao;
	}
	public void setPlayerItemDao(PlayerItemDao playerItemDao) {
		this.playerItemDao = playerItemDao;
	}

	public void setTeamDao(TeamDao teamDao) {
		this.teamDao = teamDao;
	}

	public void setPlayerTeamDao(PlayerTeamDao playerTeamDao) {
		this.playerTeamDao = playerTeamDao;
	}

	public void setAllyDao(AllyDao allyDao) {
		this.allyDao = allyDao;
	}
	public SystemDao getSystemDao() {
		return systemDao;
	}
	public void setSystemDao(SystemDao systemDao) {
		this.systemDao = systemDao;
	}
	public void setTeamHistoryDao(TeamHistoryDao teamHistoryDao) {
		this.teamHistoryDao = teamHistoryDao;
	}

}
