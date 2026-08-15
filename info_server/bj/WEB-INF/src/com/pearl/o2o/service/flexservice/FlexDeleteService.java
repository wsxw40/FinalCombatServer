package com.pearl.o2o.service.flexservice;

import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.o2o.dao.impl.nonjoin.SysItemDao;
import com.pearl.o2o.pojo.BlackIP;
import com.pearl.o2o.pojo.BlackWhiteList;
import com.pearl.o2o.pojo.Channel;
import com.pearl.o2o.pojo.GmGroup;
import com.pearl.o2o.pojo.GmUser;
import com.pearl.o2o.pojo.LevelInfo;
import com.pearl.o2o.pojo.OnlineAward;
import com.pearl.o2o.pojo.Payment;
import com.pearl.o2o.pojo.PlayerItem;
import com.pearl.o2o.pojo.Server;
import com.pearl.o2o.pojo.SysActivity;
import com.pearl.o2o.pojo.SysBioCharacter;
import com.pearl.o2o.pojo.SysCharacter;
import com.pearl.o2o.pojo.SysChest;
import com.pearl.o2o.pojo.SysConfiguration;
import com.pearl.o2o.pojo.SysItem;
import com.pearl.o2o.pojo.SysNotice;
import com.pearl.o2o.pojo.Team;
import com.pearl.o2o.service.DeleteService;
import com.pearl.o2o.utils.CacheUtil;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.ServiceLocator;



public class FlexDeleteService extends DeleteService {
	static Logger log = LoggerFactory.getLogger(FlexDeleteService.class.getName());
	
	//===============================================================================	
	//								  Character Services
	//===============================================================================	
	public void deleteSysCharacter(GmUser gmUser, SysCharacter sysCharacter) throws Exception{
		try{
		sysCharacterDao.deleteSysCharacter(sysCharacter);
		mcc.delete(CacheUtil.oSysCharacterList());
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void deleteSysBioCharacter(GmUser gmUser, SysBioCharacter sysBioCharacter) throws Exception{
		try{
			sysBioCharacterDao.deleteSysBioCharacter(sysBioCharacter);
			mcc.delete(CacheUtil.oSysBioCharacterList());
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	public void deleteSysConfig(GmUser gmUser, String key,String value)throws Exception{
		SysConfiguration sc=new SysConfiguration();
		sc.setKey(key);
		sc.setValue(value);
		systemDao.deleteSysConfig(sc);
		mcc.delete(CacheUtil.oSysConfigMap());
	}
	
	
	//===============================================================================	
	//								  Server Services
	//===============================================================================	
	public void deleteServerById(GmUser gmUser, Server server)throws Exception{
		mcc.delete(CacheUtil.oServerList());
		serverDao.deleteServer(server);
		soClient.refreashServerList();
	}
	
	
	public void deleteChannelById(GmUser gmUser, Channel channel)throws Exception{
		mcc.delete(CacheUtil.oChannelList(channel.getServerId()));
		serverDao.deleteChannel(channel);
		soClient.refreashServerList();
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
//			mcc.delete(CacheUtil.oWeaponsInAllPacks(playerId));
//			mcc.delete(CacheUtil.sWeaponPack(playerId, packId));
//			mcc.delete(CacheUtil.oStorage(playerId,type));
//			mcc.delete(CacheUtil.sStorage(playerId,type));
			
		}else{
			mcc.delete(CacheUtil.oCostumePack(playerId, Constants.NUM_ONE, packId));
//			mcc.delete(CacheUtil.sCostumePack(playerId, Constants.NUM_ONE, packId));
//			mcc.delete(CacheUtil.oStorage(playerId,type));
//			mcc.delete(CacheUtil.sStorage(playerId,type));
			mcc.delete(CacheUtil.oPlayer(playerId));
			mcc.delete(CacheUtil.sPlayer(playerId));
		}
		//2.update database
//		playerPackDao.deletePackAll(userId, playerId, packId, type);
		
		return itemType;
	}		
	
	
	//===============================================================================	
	//								  Sysitem Services
	//===============================================================================
	//TODO cache time issue exist
	public void deleteSysItemHard(GmUser gmUser, SysItem sysItem) throws Exception{
//		try{
//		String[] characterids=sysItem.getCharacterIds();
//		for(String id:characterids){
//			mcc.delete(CacheUtil.sShop(sysItem.getType(),Integer.valueOf(id)));
//			mcc.delete(CacheUtil.oShop(sysItem.getType(),Integer.valueOf(id)));
//		}
//		List<PlayerItem> playerItemList = playerItemDao.getPlayerItemBySysItemId(sysItem.getId());
//		for(PlayerItem pi : playerItemList){
//			playerItemDao.softDeletePlayerItem(pi);
//			playerItemDao.deletePlayerItem(pi.getPlayerId(), pi.getItemId());
//		}
//		for(Payment payment : paymentDao.getPaymentListById(sysItem.getId())){
//			paymentDao.deletePayment(payment);
//		}
//		sysItemDao.deleteSysItem(sysItem);
//		sysItemDao.deleteSysItemHard(sysItem);
//		mcc.delete(CacheUtil.oSystemList(sysItem.getType(), sysItem.getSubType()));
//		}catch (Exception e) {
//		e.printStackTrace();
//		}
	}
	public void deleteSysItemSoft(GmUser gmUser, SysItem sysItem) throws Exception{
		try{
//		String[] characterids=sysItem.getCharacterIds();
//		for(String id:characterids){
//			mcc.delete(CacheUtil.sShop(sysItem.getType(),Integer.valueOf(id)));
//			mcc.delete(CacheUtil.oShop(sysItem.getType(),Integer.valueOf(id)));
//		}
		sysItemDao.updateSysItem(sysItem);
//		mcc.delete(CacheUtil.oSystemList(sysItem.getType(), sysItem.getSubType()));
		
		SysItemDao.setAllSysitem(new HashMap<Integer, SysItem>());//清空本地内存中的Map
		sysItemDao.setClassifySysItemMap(null);
		
		}catch (Exception e) {
		e.printStackTrace();
		}
	}
	//===============================================================================	
	//								 Payment Services
	//===============================================================================
	//TODO cache time issue exist
	public void deletePayment(GmUser gmUser, Payment payment) throws Exception{
		try{
		SysItem sysItem=getService.getSysItemByItemId(payment.getItemId());
//		String[] characterids=sysItem.getCharacterIds();
//		for(String id:characterids){
//			mcc.delete(CacheUtil.sShop(sysItem.getType(),Integer.valueOf(id)));
//			mcc.delete(CacheUtil.oShop(sysItem.getType(),Integer.valueOf(id)));
//		}
		mcc.delete(CacheUtil.oPaymentList(payment.getItemId()));
		paymentDao.deletePayment(payment);
		ServiceLocator.getService.joinPayment(sysItem);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	//===============================================================================	
	//								 PlayerItem Services
	//===============================================================================
	public void deletePlayerItem(GmUser gmUser, PlayerItem playerItem) throws Exception {
		try {
			SysItem item = getService.getSysItemByItemId(playerItem.getItemId());
			int characterId = item.getCharacterList().get(0);
			int type = item.getType();
			List<PlayerItem> list = getService.getPlayerItem_s1(playerItem.getPlayerId(), type, characterId, 0);
			PlayerItem delPi = null;
			for (PlayerItem pi : list) {
				if (playerItem.getId() == pi.getId()) {
					delPi = pi;
				}
			}
			if (delPi != null) {
				playerItemDao.softDeletePlayerItem(delPi);
				log.warn("GM ="+gmUser.getName()+" delete playerItem type="+type+" playerItemId="+delPi.getId()+" itemName="+item.getDisplayNameCN());
				ServiceLocator.deleteService.deletePlayerItemInMemcached(delPi.getPlayerId(), item);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	//===============================================================================	
	//								  Level  Services
	//===============================================================================
	public void deleteLevelInfo(GmUser gmUser, LevelInfo level) throws Exception{
		mcc.delete(CacheUtil.oLevelList());
		sysLevelDao.deleteLevel(level);
		soClient.refreashLevelList();
	}
	
	
	public void deleteLevelWeapon(GmUser gmUser, int id) throws Exception{
		sysLevelDao.deleteLevelWeapon(id);
	}
	//===============================================================================	
	//								  System Notice
	//===============================================================================
	public void deleteSysNotice(GmUser gmUser, SysNotice sysNotice) throws Exception{
		mcc.delete(CacheUtil.oSysNoticeList());
		sysNoticeDao.delete(sysNotice);
		soClient.refreashGMNotice();
	}
	//===============================================================================	
	//								  BlackIP  Services
	//===============================================================================
	public void deleteBlackIP(GmUser gmUser, BlackIP blackIP) throws Exception{
		mcc.delete(CacheUtil.oBlackIpList());
		blackIPDao.deleteBlackIP(blackIP.getId());
	}
	
	//===============================================================================	
	//								  BlackWhiteList Services
	//===============================================================================
	
	public void deleteBlackWhiteList(BlackWhiteList list)throws Exception{
		blackWhiteListDao.deleteBlackWhiteList(list.getId());
	}
	

	public void deleteGmGroup(GmUser gmUser, GmGroup group) throws Exception{
		List<GmGroup> groups = gmGroupDao.getGmGroupsByCode(group.getCode());
		gmGroupPrivilegeDao.delete(groups);
		gmUserGroupDao.delete(groups);
		gmGroupDao.deleteGmGroup(groups);
	}
	
	public void deleteGmUserGroup(GmUser gmUser, int userId, int groupId) throws Exception{
		gmUserGroupDao.delete(userId, groupId);
	}
	

	public void deleteGmUser(GmUser gmUser, GmUser deletedGmUser) throws Exception{
//		GmUser parent = gmUserDao.getParentGmUser(deletedGmUser.getCreatorId());
//		List<GmUser> children = gmUserDao.getGmUserByCreatorId(deletedGmUser.getCreatorId());
//		for(GmUser gu : children){
//			gu.setCreatorId(parent.getId());
//			gmUserDao.update(gu);
//		}
		gmUserGroupDao.deleteByUserId(deletedGmUser.getId());
		gmUserDao.delete(deletedGmUser);
	}
	
	public void deleteActivity(GmUser gmUser, SysActivity sa) throws Exception{
		String key=CacheUtil.oCurrentActivityMap();
		mcc.delete(key);
		mcc.delete(key+"A");
		mcc.delete(key+"B");
		sa.setIsDeleted("Y");
		sysActivityDao.updateMappingBeanInCacheWithDefaultId(sa);
		if(sa.getAction()==Constants.ACTION_ACTIVITY.HOUR2HOUR_ACTIVITY.getValue() || sa.getAction()==Constants.ACTION_ACTIVITY.RANDOM_ACTIVITY.getValue()){
			soClient.refreashSysConfig();
		}
	}
	
	public void deleteTeam(GmUser gmUser, int teamId) throws Exception {
		Team team = getService.getTeam(teamId);
		if(null != team){
			ServiceLocator.deleteService.deleteTeam(0, team.getHeaderId(), teamId);
		}
	}
	
	public int deleteSysChest(GmUser gmUser, SysChest sysChest) throws Exception {
		try{
			if(null != sysChest && sysChest.getId() > 0){
				sysChestDao.delete(sysChest);
				return sysChest.getType();
			}
			return Constants.NUM_ZERO;
		} catch (Exception e) {
			ServiceLocator.fileLog.warn(e.getMessage(), e);
			throw e;
		}
	}
	
	public void deleteOnlineAward(GmUser gmUser,OnlineAward award)throws Exception{
		onlineAwardDao.deleteOnlineAward(award);
		ServiceLocator.deleteService.deleteOnlineAward(award);
		ServiceLocator.deleteService.deleteOnlineAwardsByTypeInMemcache(award.getType());
	}
	
	public void deleteStrengthenAppend(GmUser user,int sid)throws Exception{
		strengthenAppendDao.deleteStreApp(sid);
		getService.initStrengthAppend();
	}
}
