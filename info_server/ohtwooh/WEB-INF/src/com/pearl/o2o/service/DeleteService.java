package com.pearl.o2o.service;

import java.util.HashMap;
import java.util.Map;

import net.rubyeye.xmemcached.MemcachedClient;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import com.pearl.o2o.dao.CharacterDao;
import com.pearl.o2o.dao.FriendDao;
import com.pearl.o2o.dao.ItemModDao;
import com.pearl.o2o.dao.MessageDao;
import com.pearl.o2o.dao.PlayerDao;
import com.pearl.o2o.dao.PlayerItemDao;
import com.pearl.o2o.dao.PlayerPackDao;
import com.pearl.o2o.dao.SysItemDao;
import com.pearl.o2o.dao.SysLevelDao;
import com.pearl.o2o.dao.TeamDao;
import com.pearl.o2o.dao.UserDao;
import com.pearl.o2o.pojo.Friend;
import com.pearl.o2o.pojo.Player;
import com.pearl.o2o.pojo.PlayerItem;
import com.pearl.o2o.pojo.Server;
import com.pearl.o2o.pojo.SysItem;
import com.pearl.o2o.utils.CacheUtil;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.StringUtil;

public class DeleteService {
	static Logger log = Logger.getLogger(DeleteService.class.getName());

	
	
	//===============================================================================	
	//								  User Services
	//===============================================================================	
	
	//===============================================================================	
	//								  Character Services
	//===============================================================================
	
	public void deleteCharacter(Integer characterId) throws Exception{
		characterDao.deleteCharacter(characterId);
		
		mcc.delete(CacheUtil.oCharacter());
	}
	
	//===============================================================================	
	//								  Player Services
	//===============================================================================
	public boolean deletePlayer(Integer userId, Integer playerId) throws Exception{

		mcc.delete(CacheUtil.oPlayer(playerId));
		mcc.delete(CacheUtil.sPlayer(playerId));
		mcc.delete(CacheUtil.oPlayerList(userId));
		mcc.delete(CacheUtil.sPlayerList(userId));
		playerDao.delete(playerId);
		return true;
	}
	//===============================================================================	
	//								  Team Services
	//===============================================================================
	/**
	 * method to soft delete team and delete the player team info
	 * @param userId
	 * @param playerId
	 * @param teamId
	 * @throws Exception
	 */
	@Transactional
	public void deleteTeam(Integer userId, Integer playerId,Integer teamId) throws Exception{
		teamDao.deleteTeamById(userId, playerId, teamId);
		deletePlayerTeam(userId, playerId, teamId);
	}
	public void deletePlayerTeam(Integer userId, Integer playerId,Integer teamId) throws Exception{
		teamDao.deletePlayerTeamById(userId, playerId, teamId);
	}
	
	//===============================================================================	
	//								  PlayerItem Services
	//===============================================================================
	
	/**
	 * method to destroy player item 
	 * @param userId
	 * @param playerId
	 * @param type
	 * @param subType
	 * @param itemId
	 * @return boolean
	 * @throws Exception
	 */
	public boolean deletePlayerItem(Integer userId, Integer playerId,int type,int subType,int itemId) throws Exception{
		//TODO delete cache by item type
		mcc.delete(CacheUtil.oStorage(playerId, type));
		mcc.delete(CacheUtil.sStorage(playerId, type));
		//mcc.delete(CacheUtil.oWeaponPack(playerId, 1));
		mcc.delete(CacheUtil.oWeaponsInAllPacks(playerId));
		mcc.delete(CacheUtil.sWeaponPack(playerId, 1));
		//mcc.delete(CacheUtil.oWeaponPack(playerId, 2));
		mcc.delete(CacheUtil.sWeaponPack(playerId, 2));
		mcc.delete(CacheUtil.oCostumePack(playerId, 1, 0));
		mcc.delete(CacheUtil.sCostumePack(playerId, 1, 0));
		mcc.delete(CacheUtil.oCostumePack(playerId, 1,1));
		mcc.delete(CacheUtil.sCostumePack(playerId, 1,1));
		for(int i=0;i<Constants.SUB_TYPE_NUM;i++){
		mcc.delete(CacheUtil.oShopPart(playerId,i+1));
		mcc.delete(CacheUtil.sShopPart(playerId,i+1));
		}
		if(type == Constants.DEFAULT_ITEM_TYPE){//buff item
			mcc.delete(CacheUtil.sPlayer(playerId));
			mcc.delete(CacheUtil.oPlayer(playerId));
		}
		
//		if(itemId==Constants.WEAPON_KNIFE_ID){throw new BaseException(ExceptionMessage.CANNOT_DESTROY);}
		playerItemDao.softDeletePlayerItem(userId, playerId, itemId);
		return true;
	}
	//===============================================================================	
	//								  Friend Services
	//===============================================================================
	public void deleteFriend(Integer userId, Integer playerId, Integer friendId) throws Exception {
		Friend friend = new Friend();
		friend.setUserId(userId);
		friend.setPlayerId(playerId);
		friend.setFriendId(friendId);		
		friendDao.delete(friend);
	}
	
	
	
	//===============================================================================	
	//								  Server Services
	//===============================================================================
	
	
	
	
	//===============================================================================	
	//								  Channel Services
	//===============================================================================	
	public void deleteChannel(int channelId, int serverId) throws Exception {
//		StringBuffer server = new StringBuffer(Constants.GET_SERVER_LIST);
//
//		Server serverById = null;
//		Map<Integer, Server> serverMap = new HashMap<Integer, Server>();
//		Map<Integer, Channel> channelMap = new HashMap<Integer, Channel>();
//
//		serverMap = (Map<Integer, Server>) mcc.get(server.toString());
//		if (serverMap != null || serverMap.size() != 0) {
//			serverById = serverMap.get(serverId);
//			if (serverById != null) {
//				channelMap = serverById.getChannelMap();
//
//				if (channelMap != null && channelMap.size() != 0) {
//					Channel c = channelMap.get(channelId);
//					if (c != null) {
//						int onlineNum = serverById.getOnline() - c.getOnline();
//						int dev = serverById.getMax() - c.getMax();
//						channelMap.remove(channelId);
//						serverById.setMax(dev);
//						serverById.setOnline(onlineNum);
//						serverById.setChannelMap(channelMap);
//						serverMap.put(serverId, serverById);
//					}
//				}
//			}
//		}
//		mcc.set(server.toString(), Constants.CACHE_ITEM_TIMEOUT, serverMap);

	}

	public void deleteChannel(int channelId) throws Exception {

		StringBuffer server = new StringBuffer(Constants.GET_SERVER_LIST);
		int serverId = 0;
		Map<Integer, Server> serverMap = new HashMap<Integer, Server>();

		serverMap = (Map<Integer, Server>) mcc.get(server.toString());
		if (serverMap != null && serverMap.size() != 0) {
			deleteChannelById(channelId, serverMap);
			mcc.set(server.toString(), Constants.CACHE_ITEM_TIMEOUT,
					serverMap);
		} else {
			log.error("Not find the Server.List in memory!");
		}
	}
	

	// if the channel id exist
	// remove the channel from serverMap
	public static void deleteChannelById(int channelId, Map<Integer, Server> serverMap) throws Exception{
//		Iterator iterator = serverMap.keySet().iterator();
//		while (iterator.hasNext()) {
//			Server si = (Server) serverMap.get(iterator.next());
//			if (si != null) {
//				Map<Integer, Channel> map = si.getChannelMap();
//				if (map != null && map.size() != 0) {
//					Channel ci = (Channel) map.get(channelId);
//					if (ci != null) {
//						int dev = si.getMax() - ci.getMax();
//						int onlineNum = si.getOnline() - ci.getOnline();
//						map.remove(channelId);
//						si.setMax(dev);
//						si.setOnline(onlineNum);
//						si.setChannelMap(map);
//						break;
//					}
//				}
//			}
//		}
	}
	//===============================================================================	
	//								  Pack Services
	//===============================================================================
	/**
	 * method to unpack the player item from bag
	 * @param userId
	 * @param playerId
	 * @param packId
	 * @param type
	 * @param seq
	 * @return 
	 * @throws Exception
	 */
	public int deletePackEquip(int userId,int playerId, int packId, int type,int seq) throws Exception{
		int itemType=0;
		
		//1.update memcache
		if(type == Constants.DEFAULT_WEAPON_TYPE){//not have type param
			//mcc.delete(CacheUtil.oWeaponPack(playerId, packId));
			mcc.delete(CacheUtil.oWeaponsInAllPacks(playerId));
			mcc.delete(CacheUtil.sWeaponPack(playerId, packId));
			mcc.delete(CacheUtil.oStorage(playerId,type));
			mcc.delete(CacheUtil.sStorage(playerId,type));
			
		}else{
			mcc.delete(CacheUtil.oCostumePack(playerId, Constants.NUM_ONE, packId));
			mcc.delete(CacheUtil.sCostumePack(playerId, Constants.NUM_ONE, packId));
			mcc.delete(CacheUtil.oStorage(playerId,type));
			mcc.delete(CacheUtil.sStorage(playerId,type));
			mcc.delete(CacheUtil.oPlayer(playerId));
			mcc.delete(CacheUtil.sPlayer(playerId));
		}
		//2.update database
		playerPackDao.deletePackEquipment(userId, playerId, packId,type,seq);
		
		return itemType;
	}
	
	
	/**
	 * method to unpack all items form bag
	 * @param userId
	 * @param playerId
	 * @param packId
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public int deletePackAll(int userId,int playerId, int packId, int type) throws Exception{
		int itemType=0;
		
		//1.update memcache
		if(type == Constants.DEFAULT_WEAPON_TYPE){//not have type param
			//mcc.delete(CacheUtil.oWeaponPack(playerId, packId));
			mcc.delete(CacheUtil.oWeaponsInAllPacks(playerId));
			mcc.delete(CacheUtil.sWeaponPack(playerId, packId));
			mcc.delete(CacheUtil.oStorage(playerId,type));
			mcc.delete(CacheUtil.sStorage(playerId,type));
			
		}else{
			mcc.delete(CacheUtil.oCostumePack(playerId, Constants.NUM_ONE, packId));
			mcc.delete(CacheUtil.sCostumePack(playerId, Constants.NUM_ONE, packId));
			mcc.delete(CacheUtil.oStorage(playerId,type));
			mcc.delete(CacheUtil.sStorage(playerId,type));
			mcc.delete(CacheUtil.oPlayer(playerId));
			mcc.delete(CacheUtil.sPlayer(playerId));
		}
		//2.update database
		playerPackDao.deletePackAll(userId, playerId, packId, type);
		
		return itemType;
	}
	
	/**
	 * method to delete parts from weapon
	 * @param userId
	 * @param playerId
	 * @param weaponId
	 * @param partId
	 * @param type
	 * @param subType
	 * @throws Exception
	 */
	@Transactional
	public void deleteItemMod(Integer userId, Integer playerId,Integer weaponId, Integer partId,  int type, int subType) throws Exception {
		
		mcc.delete(CacheUtil.oStorage(playerId,type));
		mcc.delete(CacheUtil.sStorage(playerId, Constants.ITEM_TYPE_WEAPON));
		mcc.delete(CacheUtil.oStorage(playerId,type));
		mcc.delete(CacheUtil.sStorage(playerId, Constants.ITEM_TYPE_PART));		
		//mcc.delete(CacheUtil.oWeaponPack(playerId, 1));
		mcc.delete(CacheUtil.oWeaponsInAllPacks(playerId));
		mcc.delete(CacheUtil.sWeaponPack(playerId, 1));
		//mcc.delete(CacheUtil.oWeaponPack(playerId, 2));
		mcc.delete(CacheUtil.sWeaponPack(playerId, 2));	
		
		itemModDao.delete(partId);
		PlayerItem playerItem=getService.getPlayerItemByPlayerItemId(userId, playerId, weaponId);
		String modifiedDesc = playerItem.getModifiedDesc();
		String newModifiedDesc=StringUtil.updateModifiedDesc(modifiedDesc, subType, "1");
		playerItemDao.updateModifiedDesc(userId, playerId, weaponId, newModifiedDesc);
			
		
	}
	
	/*public void removeWeaponPack(int uid, int cid, int packNo) throws Exception{
		if (packNo <= Constants.DEFAULT_WEAPON_PACK_SIZE ) {
			throw new Exception("can not remove default pack");
		}
		int row = playerPackDao.deleteWeaponPack(uid, cid, packNo);
		Player p = getService.getPlayerById(cid);
		if (row !=0){
			playerDao.update("W_PACK_SIZE",p.getWPackSize()-row, cid);
		}
		mcc.delete(CacheUtil.oPlayer(cid));
		mcc.delete(CacheUtil.sPlayer(cid));
		mcc.delete(CacheUtil.oWeaponPack(cid, packNo));
		mcc.delete(CacheUtil.sWeaponPack(cid, packNo));
	}*/
	
	//===============================================================================	
	//								  sysitem Services
	//===============================================================================
	public void deleteSysItem(SysItem sysItem) throws Exception{
		mcc.delete(CacheUtil.sShop(sysItem.getType(),sysItem.getSubType(),sysItem.getCGender()));
		mcc.delete(CacheUtil.oShop(sysItem.getType(),sysItem.getSubType(),sysItem.getCGender()));
		sysItemDao.deleteSysItem(sysItem);
		
	}
	//===============================================================================	
	//								  Level  Services
	//===============================================================================
	public void deleteLevelInfo(int id) throws Exception{
		sysLevelDao.deleteLevel(id);
	}
	public void deleteLevelWeapon(int id) throws Exception{
		sysLevelDao.deleteLevelWeapon(id);
	}
	//===============================================================================	
	//								  Message Services
	//===============================================================================
	public void deleteMessageItem(Integer messageItemId) throws Exception{
		messageDao.deleteMessageItem(messageItemId);
	}
	
	//===============================================================================	
	//								  Injected Objects
	//===============================================================================	
	private MemcachedClient 	mcc;	
	private UserDao 			userDao;
	private PlayerDao 			playerDao;
	private FriendDao 			friendDao;	
	private PlayerPackDao       playerPackDao;
	private PlayerItemDao 		playerItemDao;
	private ItemModDao			itemModDao;	
	private SysItemDao			sysItemDao;
	private MessageDao			messageDao;	
	private CharacterDao		characterDao;
	private SysLevelDao			sysLevelDao;	
	private TeamDao				teamDao;
	private GetService			getService;
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

	public void setPlayerItemDao(PlayerItemDao playerItemDao) {
		this.playerItemDao = playerItemDao;
	}

	public void setItemModDao(ItemModDao itemModDao) {
		this.itemModDao = itemModDao;
	}




	public void setSysItemDao(SysItemDao sysItemDao) {
		this.sysItemDao = sysItemDao;
	}




	public void setMessageDao(MessageDao messageDao) {
		this.messageDao = messageDao;
	}

	public void setCharacterDao(CharacterDao characterDao) {
		this.characterDao = characterDao;
	}

	public void setSysLevelDao(SysLevelDao sysLevelDao) {
		this.sysLevelDao = sysLevelDao;
	}

	public void setTeamDao(TeamDao teamDao) {
		this.teamDao = teamDao;
	}		
}
