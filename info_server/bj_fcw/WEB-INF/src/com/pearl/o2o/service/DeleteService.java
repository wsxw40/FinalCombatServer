package com.pearl.o2o.service;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import net.rubyeye.xmemcached.exception.MemcachedException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.o2o.exception.BaseException;
import com.pearl.o2o.nosql.NoSql;
import com.pearl.o2o.pojo.Friend;
import com.pearl.o2o.pojo.OnlineAward;
import com.pearl.o2o.pojo.Player;
import com.pearl.o2o.pojo.PlayerBuff;
import com.pearl.o2o.pojo.PlayerItem;
import com.pearl.o2o.pojo.PlayerMission;
import com.pearl.o2o.pojo.PlayerTeam;
import com.pearl.o2o.pojo.SysCharacter;
import com.pearl.o2o.pojo.SysConfiguration;
import com.pearl.o2o.pojo.SysItem;
import com.pearl.o2o.pojo.Team;
import com.pearl.o2o.pojo.TeamItem;
import com.pearl.o2o.pojo.TeamProclamation;
import com.pearl.o2o.pojo.TeamRecord;
import com.pearl.o2o.utils.BiochemicalConstants;
import com.pearl.o2o.utils.CacheUtil;
import com.pearl.o2o.utils.CommonMsg;
import com.pearl.o2o.utils.CommonUtil;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.ServiceLocator;
import com.pearl.o2o.utils.TeamConstants;


public class DeleteService extends BaseService {
	static Logger log = LoggerFactory.getLogger(DeleteService.class.getName());
	private MessageService messageService;
	
	public void flushAllCached() throws Exception{
		mcc.flushAll();
	}
	//===============================================================================	
	//								  User Services
	//===============================================================================	
	
	//===============================================================================	
	//								  Character Services
	//===============================================================================
	
	public void deleteSysConfig(String key,String value)throws Exception{
		SysConfiguration sc=new SysConfiguration();
		sc.setKey(key);
		sc.setValue(value);
		systemDao.deleteSysConfig(sc);
		mcc.delete(CacheUtil.oSysConfigMap());
	}
	
	//===============================================================================	
	//								  Player Services
	//===============================================================================

	public boolean deletePlayer(Integer userId, Integer playerId) throws Exception{
		Player p = getService.getSimplePlayerById(playerId);
		if (p == null ) {
			return false;
		}
		//remove all application or team job
		PlayerTeam pt = getService.getPlayerTeamByPlayerId(playerId);
		//delete related playerTeam
		if (pt !=null && pt.getJob() == TeamConstants.TEAMJOB.TEAM_CAPTAIN.getValue() ) {//if is a captain, hand over job
			//deleteTeam(userId,playerId,pt.getTeamId());
			Team t = getService.getTeamByPlayerId(playerId);
			if (t.getMemberList().size() <=1) {//only exist self
				t.setDeleted("Y");
				teamDao.updateTeam(t);
				mcc.delete(CacheUtil.oTeam(t.getId()));
				mcc.delete(CacheUtil.oSimpleTeam(t.getId()));
			}else{
				// member list will be sorted desc, so the first is old leader and the second  member is the new leader id
				int newLeaderId = t.getMemberList().get(1).getPlayerId();
				//handover leader
				playerTeamDao.updateJob(t.getMemberList().get(1), TeamConstants.TEAMJOB.TEAM_CAPTAIN.getValue());
				mcc.delete(CacheUtil.oPlayer(newLeaderId));
				mcc.delete(CacheUtil.sPlayer(newLeaderId));
			}
			playerTeamDao.deletePlayerTeam(pt);
		}else{
			//delete playerteam of his own
			playerTeamDao.deletePlayerTeam(pt);
		}
		
		mcc.deleteWithNoReply(CacheUtil.oPlayer(playerId));
		mcc.deleteWithNoReply(CacheUtil.sPlayer(playerId));
		playerDao.delete(p);
		try{
			nosqlService.deletePlayer(playerId);
		}catch(Exception e){
			log.warn("fail to delete player info in NoSql, cid:" + playerId);
		}
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

	public void deletePlayerTeamBuff(int playerId) throws Exception{
		//删除战队buff
		Map<Integer, PlayerItem>  playerItemMap=getService.getPlayerItemMapByType1(playerId, Constants.DEFAULT_ITEM_TYPE, 0, 0);
		for(PlayerItem playerItem:playerItemMap.values()){
			SysItem sysItemByItemId = getService.getSysItemByItemId(playerItem.getItemId());
			if(null != sysItemByItemId&&sysItemByItemId.getIId() == 1&&sysItemByItemId.getIBuffId()>=61&&sysItemByItemId.getIBuffId()<=74){
				playerItem.setSysItem(sysItemByItemId);
				ServiceLocator.deleteService.deletePlayerItem(playerItem);
			}
		}
		Player player = getService.getPlayerById(playerId);
		soClient.puchCMDtoClient(player.getName(), CommonUtil.messageFormat(CommonMsg.REFRESH_BUFF_LIST));
	}
	
	public String deleteTeam(Integer userId, Integer playerId,Integer teamId) throws Exception{
		Team team = getService.getTeamById(teamId);
		if(team.getMemberList().size()>1){
			return TeamConstants.TEAM_DISMISS_ERR;
		}
		mcc.delete(CacheUtil.oTeam(teamId));
		mcc.delete(CacheUtil.oSimpleTeam(teamId));
		try{
			for (PlayerTeam pt : playerTeamDao.getPlayerTeamBase(team.getId())){
				Player p = getService.getPlayerById(pt.getPlayerId());
				CommonUtil.checkNull(p, ExceptionMessage.NO_HAVE_THE_CHARACTER);
				//vip保留一定vip经验
				if(p.getIsVip()>=1){
					PlayerTeam beforeRemove=getService.getPlayerTeam(teamId, playerId);
					int remainsExp=(int)(beforeRemove.getContribution()*Constants.VIP_REMAINS_TEAM_EXP_PER[(p.getIsVip()-1)/2]/100.0);
					p.setVipRemainsTeamExp(remainsExp);
				
				}
				playerTeamDao.deletePlayerTeam(pt);
//				mcc.delete(CacheUtil.sPlayer(p.getId()));
//				mcc.delete(CacheUtil.oPlayer(p.getId()));
				soClient.puchCMDtoClient(p.getName(), CommonUtil.messageFormat(CommonMsg.TEAM_NUMBER_CHANGE));
							
				p.setTeamId(0);
				p.setTeam("");
				ServiceLocator.updateService.updatePlayerInfo(p);
				if(Constants.BOOLEAN_YES.equals(pt.getApproved())){
					messageService.sendTeamDismissNotifyMail(p, team);
				}
				deletePlayerTeamBuff(pt.getPlayerId());
			}
			
			teamDao.deleteTeam(team);
			this.deleteTeamProclamationByTeamId(teamId);
			this.deleteTeamRecordByTeamId(teamId);
			NoSql nosql = nosqlService.getNosql();
			for(Constants.TEAM_TOP_TYPE type : Constants.TEAM_TOP_TYPE.values()){
				if(type==Constants.TEAM_TOP_TYPE.NEW){
					nosql.removeFromList(Constants.TEAMTOP_KEY_PREFIX+ type.getValue(), 1, String.valueOf(teamId));
				}else{
					nosql.zRem(Constants.TEAMTOP_KEY_PREFIX+ type.getValue(), String.valueOf(teamId));
				}
			}
			//GM使用
			mcc.delete(CacheUtil.oTeamList());
			mcc.delete(CacheUtil.oDefaultTeamList());
		}catch(Exception e){
			log.warn("Error happend during send email notify for delete team, exception is ", e);
			throw e;
		}
		return null;
	}
	public void deleteTeamRecordByTeamId(int teamId){
		//get all team record of team
		List<TeamRecord> list=teamRecordDao.getTeamRecordListByTeamId(teamId);
		if(list!=null&&list.size()>0){
			for(TeamRecord tr:list){
				try {
					teamRecordDao.deleteTeamRecord(tr);
				} catch (Exception e) {
					log.warn(ExceptionMessage.TEAM_DISMISS_DEPRO_WARN);
				}
			}
		}

	}
	public void deleteTeamProclamationByTeamId(int teamId){
		 Map<Integer, TeamProclamation> map=getService.getTeamProclamationByTeamId(teamId);
		 if(map!=null&&map.size()>0){
			 for(Iterator<Map.Entry<Integer, TeamProclamation>> it=map.entrySet().iterator();it.hasNext();){
				 Map.Entry<Integer, TeamProclamation> e= it.next();
				 TeamProclamation t= e.getValue();
				 try {
					teamProclamationDao.deleteTeam(t);
				} catch (Exception e1) {
					log.warn(ExceptionMessage.TEAM_DISMISS_WARN);
					e1.printStackTrace();
				}
			 }
		 }
	}
	public void deletePlayerTeam(Integer userId, Integer playerId,Integer teamId) throws Exception{
		PlayerTeam pt = getService.getPlayerTeamByPlayerId(playerId);
		playerTeamDao.deletePlayerTeam(pt);
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
	
	public boolean deletePlayerItem(int playerId,int type,PlayerItem pi) throws Exception{
		if(pi.getSysItem().getType()==Constants.DEFAULT_OPEN_TYPE&&(pi.getSysItem().getIId()==23||pi.getSysItem().getIId()==59||pi.getSysItem().getIId()==60)){
			throw new BaseException(ExceptionMessage.NOT_DELETE_LEVEL_PACKAGE);
		}
		return deletePlayerItem(pi,playerId, type);
	}

	public boolean deletePlayerItem(PlayerItem pi) throws Exception{
		return deletePlayerItem(pi,pi.getPlayerId(), pi.getType());
	}
	
	public boolean deletePlayerItem(PlayerItem pi,int playerId,int type) throws Exception{

//		mcc.delete(CacheUtil.oStorage(playerId, type,0));//根据type决定
		
		deletePlayerItemInMemcached(playerId, pi.getSysItem());
		
		
		if(type == Constants.DEFAULT_ITEM_TYPE){//buff item
			mcc.delete(CacheUtil.sPlayer(playerId));
			mcc.delete(CacheUtil.oPlayer(playerId));
		}else if(type == Constants.DEFAULT_PART_TYPE){
//			mcc.delete(CacheUtil.oStorage(playerId, Constants.DEFAULT_WEAPON_TYPE));
//			mcc.delete(CacheUtil.sStorage(playerId, Constants.DEFAULT_WEAPON_TYPE));
		}
		
		playerItemDao.softDeletePlayerItem(pi);
//		if(Constants.DEFAULT_WEAPON_TYPE == pi.getSysItem().getType()&&pi.getParts()!=null&&pi.getParts().size()!=0){
//			deleteItemMod(itemId);
//			mcc.delete(CacheUtil.oStorage(playerId, Constants.DEFAULT_PART_TYPE));
//			mcc.delete(CacheUtil.sStorage(playerId, Constants.DEFAULT_PART_TYPE));
//		}
		//update playerbuff set player_item_id = null
		SysItem sysItem =getService.getSysItemByItemId(pi.getItemId());
		if(Constants.DEFAULT_ITEM_TYPE == sysItem.getType() && sysItem.getIId()==1){
			List<PlayerBuff> buffList = playerBuffDao.getPlayerBuffListByPlayerId(playerId);
			for(PlayerBuff bf : buffList){
				Integer playerItemId = bf.getPlayerItemId();
				if(null != playerItemId){
					if(playerItemId == pi.getId()){
						bf.setPlayerItemId(null);
						playerBuffDao.updateMappingBeanInCache(bf, playerId);
					}
				}
			}
			if(sysItem.getIBuffId() == Constants.DEFAULT_CARD_BUFF_ID||(sysItem.getIBuffId() >=61&&sysItem.getIBuffId() <=74)
					||sysItem.getIBuffId()==BiochemicalConstants.ordinaryBuffId ){
				Player player = getService.getPlayerById(playerId);
				soClient.updateCharacterInfo(player, player.getTeam(), player.getRank());
			}
//TODO 	CharacterSize		
//			if(sysItem.getIBuffId() == Constants.DEFAULT_RELEASE_SIZE_ID){
//				Player player = getService.getPlayerById(playerId);
//				player.setCharacterSize(Constants.NUM_THREE);
//				playerDao.updatePlayerInCache(player);
//				soClient.puchCMDtoClient(player.getName(), CommonUtil.messageFormat(CommonMsg.REFRESH_BUFF_LIST,null));
//				mcc.delete(CacheUtil.sPlayer(playerId));
//				mcc.delete(CacheUtil.oPlayer(playerId));
//			}
		}
		return true;
	}
	
	public boolean deletePlayerBuff(PlayerItem playerItem) throws Exception{
//		mcc.delete(CacheUtil.oStorage(playerItem.getPlayerId(), playerItem.getSysItem().getType()));
//		mcc.delete(CacheUtil.sStorage(playerItem.getPlayerId(), playerItem.getSysItem().getType()));
		
		PlayerBuff playerBuff = playerBuffDao.fuzzyGetPlayerBuffById(playerItem.getPlayerId(), playerItem.getSysItem().getIBuffId());

		playerBuffDao.deletePlayerBuff(playerBuff);
		playerItemDao.updatePlayerItem(playerItem);
		
		SysItem si=sysItemDao.getSystemItemById(playerItem.getItemId());
		deletePlayerItemInMemcached(playerItem.getPlayerId(), si);
		mcc.delete(CacheUtil.sPlayer(playerItem.getPlayerId()));
		mcc.delete(CacheUtil.oPlayer(playerItem.getPlayerId()));
	
		
		//special for item 'extra pack'
//		if (playerItem.getSysItem().getIId() == Constants.DEFAULT_PACK_IID) {
//			//release all weapon except the knife , and update now to expire time
//			int packNo = Integer.valueOf(playerItem.getSysItem().getIValue());
//			playerPackDao.updatePackExpriedTime(playerItem.getPlayerId(), packNo, Constants.EXPIREDATEOBJECT);
//			mcc.delete(CacheUtil.sPlayer( playerItem.getPlayerId()));
//			mcc.delete(CacheUtil.oPlayer( playerItem.getPlayerId()));
////			mcc.delete(CacheUtil.oWeaponsInAllPacks( playerItem.getPlayerId()));
//		}
		return true;
	}
	
	
	
	//===============================================================================	
	//								  Friend Services
	//===============================================================================
	public void deleteFriend(Integer playerId, Integer friendId, Integer type) throws Exception {
		Friend friend = getService.getFriendById(playerId, friendId, type);
		if(type.equals(Constants.BLACKLIST)){
			mcc.delete(CacheUtil.oBlackList(playerId));
		}else if(type.equals(Constants.FRIEND)){
			mcc.delete(CacheUtil.oFriendList(playerId));
		}else{
			mcc.delete(CacheUtil.oGroupList(playerId));
		}
		if(friendId.equals(playerId)){
			friendDao.deleteHard(friend,type);
		}else if(null != friend){
			friendDao.delete(friend);
		}
	}
	
	public void deleteUnAcceptFriend(Integer playerId, Integer type) throws Exception {
		mcc.delete(CacheUtil.oGroupList(playerId));
		List<Friend> friendList = friendDao.getFriendAll(playerId, type);
		for(Friend f:friendList){
			if(f.getAcceptted().equals(Constants.BOOLEAN_NO)){
				friendDao.delete(f);
			}
		}

	}
	//===============================================================================	
	//								  Pack Services
	//===============================================================================
	/**
	 * 卸载辅助武器
	 * @param playerId
	 * @param characterId
	 * @param type
	 * @param seq
	 * @return 
	 * @throws Exception
	 */
	public void deletePackEquip(int playerId, int characterId, int type,int seq) throws Exception{
//		List<PlayerPack> packs = null;
//		
//		//1.update memcache
//		if(seq != 4){//not have type param
//			throw new NotEquipException(ExceptionMessage.CANNOT_UNPACK);
//		}else{
//			mcc.delete(CacheUtil.oWeaponPack(playerId, Constants.NUM_ONE,characterId));
////			mcc.delete(CacheUtil.oStorage(playerId, type, 0));//4号武器
//			
//			ServiceLocator.deleteService.deletePlayerItemInMemcached(playerId, type, characterId, 4);
//			
////			mcc.delete(CacheUtil.oStorage1(playerId, type, characterId, 0)); 
////			int subType = getService.getSubType(pi.getSysItem());
////			mcc.delete(CacheUtil.oStorage1(playerId, type, characterId, subType));
//			
//			mcc.delete(CacheUtil.oCharacterList(playerId));
//			
//			packs = playerPackDao.getWeaponPackByPackId(playerId, characterId);
//		}
//		//2.update database
//		for (PlayerPack pp : packs){
//			if (seq == pp.getSeq()){
//				pp.setPlayerItemId(null);
//				playerPackDao.updateMappingBeanInCache(pp, playerId);
//				break;
//			}
//		}
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

	public void deleteItemMod(Integer weaponId) throws Exception {
		itemModDao.delete(weaponId);
	}

	public void deleteItemModForPart(Integer partId) throws Exception {
		itemModDao.deletePart(partId);
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
	//===============================================================================	
	//								  Level  Services
	//===============================================================================
	public void deleteLevelWeapon(int id) throws Exception{
		sysLevelDao.deleteLevelWeapon(id);
	}
	//===============================================================================	
	//								  Message Services
	//===============================================================================
	public void deleteMessageItem(Integer messageItemId) throws Exception{
		messageDao.deleteMessageItem(messageItemId);
	}

	public MessageService getMessageService() {
		return messageService;
	}

	public void setMessageService(MessageService messageService) {
		this.messageService = messageService;
	}
	//===============================================================================	
	//								  SysMission Services
	//===============================================================================
//	public void deleteSysMission(SysMission sysMission) throws Exception {
//		sysMissionDao.deleteSysMissionInDBAndCache(sysMission);
//		String key = CacheUtil.oSysMissionMap();
//		mcc.delete(key);
//	}
	public void deletePlayerMission(PlayerMission playerMission) throws Exception {
		playerMissionDao.deletePlayerMissionFromDBAndCache(playerMission);
	}
	public void deletePlayerMissions(int playerId, int type) throws Exception {
		List<PlayerMission> list = getService.getPlayerMissionList(playerId);
		for(PlayerMission m : list){
			if(type == m.getType()){
				deletePlayerMission(m);
			}
		}
		String key = CacheUtil.oPlayerMissionList(playerId);
		mcc.delete(key);
	}
	
	public void deletePlayerItemInMemcached(int playerId, SysItem si) throws Exception{
		int subType = si.getSubType();
		if(si.getType() == Constants.DEFAULT_PART_TYPE && si.getSubType() == 2){//通用物品所以角色都要删除
			for(SysCharacter sc : getService.getDefaultSysCharacterList()){
				mcc.delete(CacheUtil.oStorage1(playerId, si.getType(), sc.getId(), 0)); 
				mcc.delete(CacheUtil.oStorage1(playerId, si.getType(), sc.getId(), subType)); 
				if(subType==5){
					mcc.delete(CacheUtil.oStorage1(playerId, si.getType(), sc.getId(), 4)); 
				}
			}
		} else {
			mcc.delete(CacheUtil.oStorage1(playerId, si.getType(), Integer.parseInt(si.getCharacterId()), 0)); 
			mcc.delete(CacheUtil.oStorage1(playerId, si.getType(), Integer.parseInt(si.getCharacterId()), subType)); 
			if(subType==5){
				mcc.delete(CacheUtil.oStorage1(playerId, si.getType(), Integer.parseInt(si.getCharacterId()), 4)); 
			}
		}
	}
	
	public void deletePlayerItemInMemcached(int playerId, int type, int characterId, int subType) throws Exception{
		mcc.delete(CacheUtil.oStorage1(playerId, type, characterId, 0)); 
		mcc.delete(CacheUtil.oStorage1(playerId, type, characterId, subType)); 
		if(subType==5)
		{
			mcc.delete(CacheUtil.oStorage1(playerId, type, characterId, 4)); 
		}
	}
	
	public void deleteOnlineAwardsByTypeInMemcache(int type) throws Exception{
		mcc.delete(CacheUtil.oOnlineAwardList(type));
	}
	public void deleteOnlineAward(OnlineAward onlineAward)throws Exception{
		onlineAwardDao.deleteOnlineAward(onlineAward);
	}
	
	public PlayerItem deleteCombiningItem(PlayerItem pi, int usedNumber) throws Exception{
		if(null != pi){
			if(null == pi.getSysItem()){
				pi.setSysItem(getService.getSysItemByItemId(pi.getItemId()));
			}
			if(pi.getQuantity() == usedNumber){
				deletePlayerItem(pi.getPlayerId(), pi.getSysItem().getType(), pi);
				pi=null;
			} else {
				pi.setQuantity(pi.getQuantity() - usedNumber);
				deletePlayerItemInMemcached(pi.getPlayerId(), pi.getSysItem());
				playerItemDao.updatePlayerItem(pi);
			}
		}
		return pi;
	}
	
	public void deletePlayerPack(int playerId, SysItem sysItem) throws TimeoutException, InterruptedException, MemcachedException{
		for(int cid : sysItem.getCharacterList()){
			if(sysItem.getType() == Constants.NUM_ONE){
				mcc.delete(CacheUtil.oWeaponPack(playerId, Constants.NUM_ONE, cid));
			} else {
				mcc.delete(CacheUtil.oCostumePack(playerId, Constants.NUM_ONE, cid));
			}
		}
		mcc.delete(CacheUtil.oCharacterList(playerId));
	}
	public void deleteTeamRecord(TeamRecord team) throws Exception {
		teamRecordDao.deleteTeam(team);
	}
	/**
	 * 清除redis中玩家的冗余数据
	 * @param playerId
	 * @throws Exception 
	 */
	public void cleanUnUsedDataInRedis(int playerId) throws Exception {
		NoSql nosql = nosqlService.getNosql();
		nosql.delete(Constants.VIP_RANDOM_VSC_LIST_PREFIX + playerId);//清除VIP宝箱冗余数据
		nosql.delete(Constants.VIP_RANDOM_START_LIST_PREFIX + playerId);//清除VIP宝箱冗余数据
	}
	
	
	/**
	 * 清除redis中玩家的冗余数据
	 * @param playerId
	 * @throws Exception 
	 */
	public void cleanUnUsedBattleFieldRobDailyInMysql(Date endDate) throws Exception {
		battleFieldRobDailyDao.cleanUnUsedBattleFieldRobDaily(endDate);
	}	
	/**
	 * 清除mcc中玩家的冗余数据
	 * @param playerId
	 * @throws MemcachedException 
	 * @throws InterruptedException 
	 * @throws TimeoutException 
	 */
	public void cleanUnUsedDataInMcc(int playerId) throws TimeoutException, InterruptedException, MemcachedException {
		mcc.delete(Constants.SHOOTING_MCC_KEY_PREFX + playerId);//清除靶场冗余数据
		mcc.delete(Constants.SHOOT_RDM_GIFTS_ID_MCC_KEY_PRFX + playerId);//清除靶场奖励池数据
		mcc.delete(Constants.STAGE_BRAND_LIST_PREFIX + playerId);//过关结算随机生成牌子
		mcc.delete(Constants.STAGE_OPEN_CHANCE_PREFIX + playerId);//过关结算玩家获得次数
		mcc.delete(Constants.STAGE_BRAND_OPEN_PREFIX + playerId);//过关结算玩家翻的牌子数量
		
	}
	
	//===============================================================================	
	//								  Vip Services
	//===============================================================================
	public Player deleteVipBuff(Player player) throws Exception{
		//删除vip buff
		Map<Integer, PlayerItem>  playerItemMap=getService.getPlayerItemMapByType1(player.getId(), Constants.DEFAULT_ITEM_TYPE, 0, 0);
		for(PlayerItem playerItem:playerItemMap.values()){
			SysItem sysItemByItemId = getService.getSysItemByItemId(playerItem.getItemId());
			if(null != sysItemByItemId&&sysItemByItemId.getIId() == 1&&sysItemByItemId.getIBuffId()==Constants.DEFAULT_VIP_STRENGTH_BUFF){
				playerItem.setSysItem(sysItemByItemId);
				ServiceLocator.deleteService.deletePlayerItem(playerItem);
			}
		}
		player.setBuffList(getService.getPlayerBuffListById(player.getId()));
		
		return player;
	}
	
	public void deleteTeamItem(int teamId,TeamItem ti) throws Exception{
		teamItemDao.deleteTeamItem(ti);
	}
}
