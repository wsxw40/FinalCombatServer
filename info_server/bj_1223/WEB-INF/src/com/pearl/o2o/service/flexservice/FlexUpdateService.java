package com.pearl.o2o.service.flexservice;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeoutException;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;

import net.rubyeye.xmemcached.KeyIterator;
import net.rubyeye.xmemcached.exception.MemcachedException;

import com.mchange.v2.beans.BeansUtils;
import com.pde.log.common.LogMessage;
import com.pde.log.common.LogMessage.Level;
import com.pearl.o2o.dao.impl.nonjoin.SysItemDao;
import com.pearl.o2o.enumuration.LogServerMessage;
import com.pearl.o2o.pojo.BannedWord;
import com.pearl.o2o.pojo.BaseMappingBean;
import com.pearl.o2o.pojo.BlackIP;
import com.pearl.o2o.pojo.Channel;
import com.pearl.o2o.pojo.GmGroup;
import com.pearl.o2o.pojo.GmUpdateLog;
import com.pearl.o2o.pojo.GmUser;
import com.pearl.o2o.pojo.LevelInfo;
import com.pearl.o2o.pojo.LevelWeapon;
import com.pearl.o2o.pojo.OnlineAward;
import com.pearl.o2o.pojo.Payment;
import com.pearl.o2o.pojo.Player;
import com.pearl.o2o.pojo.PlayerInfo;
import com.pearl.o2o.pojo.PlayerItem;
import com.pearl.o2o.pojo.PlayerVO;
import com.pearl.o2o.pojo.Server;
import com.pearl.o2o.pojo.StrengthenAppend;
import com.pearl.o2o.pojo.SysActivity;
import com.pearl.o2o.pojo.SysBioCharacter;
import com.pearl.o2o.pojo.SysCharacter;
import com.pearl.o2o.pojo.SysCharacterLog;
import com.pearl.o2o.pojo.SysChest;
import com.pearl.o2o.pojo.SysConfiguration;
import com.pearl.o2o.pojo.SysItem;
import com.pearl.o2o.pojo.SysItemLog;
import com.pearl.o2o.pojo.SysModeConfig;
import com.pearl.o2o.pojo.SysNotice;
import com.pearl.o2o.pojo.UnSpeaker;
import com.pearl.o2o.service.UpdateService;
import com.pearl.o2o.utils.CacheUtil;
import com.pearl.o2o.utils.CommonUtil;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.DaoCacheUtil;
import com.pearl.o2o.utils.KeywordFilterUtil;
import com.pearl.o2o.utils.LogUtils;
import com.pearl.o2o.utils.ServiceLocator;


public class FlexUpdateService extends UpdateService{

	//===============================================================================	
	//								  Sys activity Services
	//===============================================================================
	public void resetSysItem(GmUser gmUser)throws Exception{
		try {
			resetService.resetSysItem();
		}catch (Exception e) {
			throw e;
		}
	}
	public List<SysActivity> updateActivityList(List<SysActivity> newlist,List<SysActivity> oldlist) throws Exception{
		List<SysActivity> insertList=new ArrayList<SysActivity>();
		List<SysActivity> updateList=new ArrayList<SysActivity>();
//		for(SysActivity sa:newlist){
//			if(sa.getId()==0){
//				insertList.add(sa);
//				continue;
//			}
//			for(SysActivity sa2:oldlist){
//				if(sa.getId() == sa2.getId()){
//					if(!sa.getStartTime().equals(sa2.getStartTime())
//							||!sa.getEndTime().equals(sa2.getEndTime())
//							||!sa.getIsDeleted().equals(sa2.getIsDeleted())){
//						updateList.add(sa);
//					}
//					break;
//				}
//			}
//		}
//		if(insertList.size()!=0){
//			sysActivityDao.createActivityList(insertList);
//			mcc.delete(CacheUtil.oCurrentActivityList());
//		}
//		if(updateList.size()!=0){
//			sysActivityDao.updateActivityList(updateList);
//			mcc.delete(CacheUtil.oCurrentActivityList());
//		}
		List<SysActivity> returnList=new ArrayList<SysActivity>();
//		returnList=getService.getActivityList();
		return returnList;
	}	
	
	
	public void updateConfig(String config)throws Exception{
		updateSysModeConfig(3,config);
	}	
	
	public void updateSysModeConfig(Integer type,String config)throws Exception{
		SysModeConfig modeConfig=new SysModeConfig();
		modeConfig.setType(type);
		modeConfig.setConfig(config);
		sysModeConfigDao.updateSysModeConfigDao(modeConfig);
	}	
	
	
	//===============================================================================	
	//								  Character Services
	//===============================================================================

	public void updateSingleCharacter(SysCharacter character) throws Exception{
		try{
			character.setIsChange(0);
			SysCharacterLog characterLog=new SysCharacterLog();
			int version=getService.getLogVersionFromCharacterLog(character.getId());
			sysCharacterDao.updateCharacter(character);
			characterLog.setSysCharacter(character);
			characterLog.setLogVersion(version+1);
			sysCharacterLogDao.createSysCharacterLog(characterLog);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void updateSysCharacter(GmUser gmUser, List<SysCharacter> characters)throws Exception{
		boolean isUpdate=false;
		for(SysCharacter character:characters){
			if(character.getIsChange()==1){
				if(!isUpdate){
					mcc.delete(CacheUtil.oSysCharacterList());//only delete once for a series update
				}
				isUpdate=true;
				updateSingleCharacter(character);
			}
		}
	}
	public void updateSysBioCharacter(GmUser gmUser, List<SysBioCharacter> characters)throws Exception{
		for(SysBioCharacter character:characters){
			if(character.getIsChange()==1){
				sysBioCharacterDao.updateBioCharacter(character);
			}
		}
	}
	//===============================================================================	
	//								  Player Services
	//===============================================================================	

	public void updatePlayer(GmUser gmUser, List<PlayerVO> playerList)throws Exception{
		for(PlayerVO p:playerList){
			Player player = getService.getPlayerById(p.getId());
			PlayerInfo playerInfo = getService.getPlayerInfoById(p.getId());
			
			Player olderP = (Player) BeanUtils.cloneBean(player);
			PlayerInfo olderPIf = (PlayerInfo) BeanUtils.cloneBean(playerInfo);
			
			player.setRank(p.getRank());
			player.setExp(p.getExp());
			player.setIsVip(p.getIsVip());
			player.setVipExp(p.getVipExp());
			player.setGPoint(p.getGPoint());
			player.setGScore(p.getGScore());
			playerInfo.setXunleiPoint(p.getCredit());
			player.setVoucher(p.getVoucher());
			player.setUnusableResource(p.getUnusableResource());
			player.setUsableResource(p.getUsableResource());
			player.setpResourceBeginTime(new Long(System.currentTimeMillis()/1000).intValue());
		
			writeGmLog(new GmUpdateLog(gmUser,olderP,player,new Date()));
			updatePlayerTutorialByGM(player, p.getTutorial());
			playerDao.updatePlayerInCache(player);
			playerInfoDao.update(playerInfo);
			writeGmLog(new GmUpdateLog(gmUser,olderPIf,playerInfo,new Date()));
			mcc.delete(CacheUtil.oPlayerInfo(p.getId()));
			mcc.delete(CacheUtil.oPlayer(p.getId()));
			mcc.delete(CacheUtil.sPlayer(p.getId()));
			soClient.messageUpdatePlayerMoney(player);
		}
	}
	
	private void writeGmLog(GmUpdateLog gmUpdateLog) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		Map<String, Object[]> vals = CommonUtil.getDifferences(gmUpdateLog.getOlder(), gmUpdateLog.getNewer());
		if(!vals.isEmpty()){
			StringBuilder diffs = new StringBuilder();
			Iterator<String> keyItt = vals.keySet().iterator();
			for(;keyItt.hasNext();){
				String key = keyItt.next();
				Object[] arrs = vals.get(key);
				if(arrs.length==2)
					diffs.append(String.format("%s:%s->%s,", key,arrs[0],arrs[1]));
			}
			String msg = LogUtils.JoinerByTab.join(gmUpdateLog.getGm().getName(),gmUpdateLog.getOlder().getClass().getSimpleName(),PropertyUtils.isReadable(gmUpdateLog.getOlder(), "id")?PropertyUtils.getProperty(gmUpdateLog.getOlder(), "id"):"null",diffs.toString());
//			infoLogger.log(LogServerMessage.gmUpdateLog.name(),Level.INFO_INT, msg);
			ServiceLocator.gmUpdateLog.info(msg);
//			infoLogger.log(LogServerMessage.xunleiLog.name(),Level.INFO_INT, msg);
		}
	}
	
	public void updateXunleiId(GmUser gmUser, PlayerVO p)throws Exception{
		playerDao.updatePlayerXunleiId(p);
		mcc.delete(CacheUtil.oPlayer(p.getId()));
		mcc.delete(CacheUtil.sPlayer(p.getId()));
	}
	
	//===============================================================================	
	//								  Server Services
	//===============================================================================
	public void updateServer(Server server) throws Exception{		
		serverDao.updateServer(server);
		mcc.delete(CacheUtil.oServerList());
	}
	public void updateServerList(GmUser gmUser, List<Server> serverList) throws Exception{
		boolean isUpdate=false;
		for(Server server:serverList){
			if(server.getIsChange()==1){
				if(!isUpdate){
					mcc.delete(CacheUtil.oServerList());
				}
				isUpdate=true;
				writeGmLog(new GmUpdateLog(gmUser,getService.getServer(server.getId()),server,new Date()));
				updateServer(server);
			}
		}
		soClient.refreashServerList();
	}
	

	//===============================================================================	
	//								  Level  Services
	//===============================================================================	
	public void updateLevelInfoList(GmUser gmUser, List<LevelInfo> infoList)throws Exception{
		boolean isUpdate=false;
		for(LevelInfo info:infoList){
			if(info.getIsChange()==1){
				if(!isUpdate){
					mcc.delete(CacheUtil.oLevelList());
				}
				writeGmLog(new GmUpdateLog(gmUser,getService.getSysLevelDao().getLevelInfoById(info.getId()),info,new Date()));
				isUpdate=true;
				updateLevelInfoPojo(info);
			}
		}
	}	
	
	public void updateLevelInfo(GmUser gmUser, LevelInfo levelInfo)throws Exception{
		writeGmLog(new GmUpdateLog(gmUser,getService.getSysLevelDao().getLevelInfoById(levelInfo.getId()),levelInfo,new Date()));
		updateLevelInfoPojo(levelInfo);
	}	
	
	public void updateLevelWeapons(GmUser gmUser, List<LevelWeapon> infoList)throws Exception{
		for(LevelWeapon info:infoList){
			if(info.getIsChange()==1){
				writeGmLog(new GmUpdateLog(gmUser,getService.getSysLevelDao().getLevelWeaponById(info.getId()),info,new Date()));
				updateLevelWeapon(info);
			}
		}
	}	


	private void updateLevelInfoPojo(LevelInfo info)throws Exception{
		sysLevelDao.updateLevel(info);
		mcc.delete(CacheUtil.oLevelList());
		soClient.refreashLevelList();
	}
	
	public void issueLevelInfoList()throws Exception{
		mcc.delete(CacheUtil.oLevelList());
		soClient.refreashLevelList();
	}
	


	private void updateLevelWeapon(LevelWeapon info)throws Exception{
		sysLevelDao.updateLevelWeapon(info);
	}	
	
	
	//===============================================================================	
	//								  Channel Services
	//===============================================================================
	public void updateChannel(Channel channel) throws Exception{
		serverDao.updateChannel(channel);
	}
	
	
	public void updateChannelList(GmUser gmUser, List<Channel> channelList) throws Exception{
		boolean isUpdate=false;
		for(Channel channle:channelList){
			if(channle.getIsChange()==1){
				if(!isUpdate){
					mcc.delete(CacheUtil.oChannelList(channle.getServerId()));
				}
				writeGmLog(new GmUpdateLog(gmUser,getService.getChannel(channle.getId()),channle,new Date()));
				isUpdate=true;
				updateChannel(channle);
			}
		}
		soClient.refreashServerList();
	}	
	
	
	//===============================================================================	
	//								  SysItem Services
	//===============================================================================	
	/**
	 * TODO: cache time issue exist
	 */ 
	public void updateSysItem(GmUser gmUser, List<SysItem> sysItemList)throws Exception{
		
		boolean isUpdate=false;
		for(SysItem sysItem:sysItemList){
			if(sysItem.getIsChange()==1){
				if(!isUpdate){
					String[] characterids=sysItem.getCharacterIds();
					for(String id:characterids){
						mcc.delete(CacheUtil.sShop(sysItem.getType(),Integer.valueOf(id)));
						mcc.delete(CacheUtil.oShop(sysItem.getType(),Integer.valueOf(id)));
					}
				}
				isUpdate=true;
				writeGmLog(new GmUpdateLog(gmUser,getService.getSysItemByItemId(sysItem.getId()),sysItem,new Date()));
				updateSysItem(sysItem);
			}
		}
		if(isUpdate){
			SysItemDao.setAllSysitem(new HashMap<Integer, SysItem>());//清空本地内存中的Map
			sysItemDao.setClassifySysItemMap(null);
		}
	}	
	
	public void updateSingleSysItem(GmUser gmUser,SysItem sysItem)throws Exception{
		writeGmLog(new GmUpdateLog(gmUser,getService.getSysItemByItemId(sysItem.getId()),sysItem,new Date()));
		updateSysItem(sysItem);
		SysItemDao.setAllSysitem(new HashMap<Integer, SysItem>());//清空本地内存中的Map
		sysItemDao.setClassifySysItemMap(null);
	}

	private void updateSysItem(SysItem sysItem)throws Exception{
		sysItem.setIsChange(0);
		SysItemLog sysItemLog=new SysItemLog();
		int version=getService.getLogVersionFromSysItemLog(sysItem.getId());
		sysItemDao.updateSysItem(sysItem);
//		sysItemDao.updateObjInDB(sysItem);
//TODO	push refresh sysitem cmd to client 
		sysItemLog.setSysItem(sysItem);
		sysItemLog.setLogVersion(version+1);
		sysItemLogDao.createSysItemLog(sysItemLog);
	}
	//===============================================================================	
	//								  System Notice
	//===============================================================================
	public int updateSysNotice(GmUser gmUser, SysNotice sysNotice)throws Exception{
		//FIXME
		if(sysNotice.getContent().length()>60){
			return 1;
		}
		else {
			mcc.delete(CacheUtil.oSysNoticeList());
			//sysNotice.setContent(KeywordFilterUtil.filter(sysNotice.getContent()));
			writeGmLog(new GmUpdateLog(gmUser,getService.getSysNoticeDao().getSysNoticeMap(sysNotice.getId()),sysNotice,new Date()));
			sysNoticeDao.update(sysNotice);
			soClient.refreashGMNotice();
			return 0;
		}
	}
	
	//===============================================================================	
	//								  BlackIP  Services
	//===============================================================================
	public void updateBlackIP(GmUser gmUser, List<BlackIP> list)throws Exception{
		for(BlackIP b : list){
			if(b.getIsChanged()==1){
				mcc.delete(CacheUtil.oBlackIpList());
				b.setCreateTime((int)(new Date().getTime()/1000L));
				blackIPDao.updateBlackIP(b);
			}
		}
	}
	
	//===============================================================================	
	//								  BlackWhiteList Services
	//===============================================================================
	
	public void updateBlackWhite(GmUser gmUser, PlayerVO player)throws Exception{
		Player cachePlayer = getService.getPlayerById(player.getId());
		cachePlayer.setBlackWhite(player.getBlackWhite());
		mcc.delete(CacheUtil.oPlayer(player.getId()));
		mcc.delete(CacheUtil.oBlackPlayerList());
		mcc.delete(CacheUtil.oWhitePlayerList());
		playerDao.updateMappingBeanInCache(cachePlayer, cachePlayer.getId());
		playerDao.updateBlackWhite(cachePlayer);
	}
	//===============================================================================	
	//								  Banned Word
	//===============================================================================
	public void updateBannedWord(GmUser gmUser, BannedWord bw) throws Exception{
		bannedWordDao.updateBannedWord(bw);
		mcc.delete(CacheUtil.oBannedWords());
		soClient.refreashKeyWords();
		KeywordFilterUtil.loadKeyWords();
	}
	
	public int loadBannedWordFile(GmUser gmUser) throws Exception{
		String filePath = "/usr/app";
		//String filePath = ConfigurationUtil.APP_ROOT_PATH + "/work";
		File file = new File(filePath);
		File[]	subFiles = file.listFiles(new FilenameFilter(){
			@Override
			public boolean accept(File dir, String name) {
				if(name.indexOf(".txt")==-1){
					return false;
				} else {
					return true;
				}
			}
			
		});
		//存在文件时进行操作
		if(subFiles.length>0){
			List<File> subFileList = Arrays.asList(subFiles);
			Collections.sort(subFileList, new Comparator<File>(){
				@Override
				public int compare(File o1, File o2) {
					return (int)(o2.lastModified() - o1.lastModified());
				}
				
			});
			File latestFile = subFileList.get(0);
			StringBuilder sb = new StringBuilder();
			BufferedReader fr = null;
			try{
				fr = new BufferedReader(new InputStreamReader(new FileInputStream(latestFile),"UTF-8"));
				int c = (char) fr.read();
				while(c != -1){
					sb.append((char)c);
					c =  fr.read();
				}
			}finally{
				fr.close();
			}
			String [] words = sb.toString().split("\\s+");
			List<String> strList=Arrays.asList(words);
			Collections.sort(strList,new Comparator<String>(){
				public int compare(String s1, String s2) {
					return ((Integer)s2.length()).compareTo((Integer)s1.length());
				  } 
			});
			List<String> keywords = Collections.unmodifiableList(strList);
			bannedWordDao.deleteAllBannedWords();
			bannedWordDao.createBannedWrods(keywords);
			mcc.delete(CacheUtil.oBannedWords());
			KeywordFilterUtil.loadKeyWords();
			return 1;
		} else {
			return 0;
		}
		
	}
	
	public void updateSysConfig(GmUser gmUser, List<SysConfiguration> configs )throws TimeoutException, InterruptedException, MemcachedException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		for(SysConfiguration config : configs){
			writeGmLog(new GmUpdateLog(gmUser,getService.getSysConfig().get(config.getKey()),config,new Date()));
			ServiceLocator.updateService.updateSysValue(config.getKey(), config.getValue());
		}
		mcc.delete(CacheUtil.oSysConfigMap());
		soClient.refreashSysConfig();
	}
	
	public void updateGmUser(GmUser gmUser, GmUser gu){
		//gu.setPassword(PasswordUtil.encrypt(gu.getPassword()));
		gmUserDao.update(gu);
	}
	
	public GmGroup updateGmGroup(GmUser gmUser, GmGroup group) throws Exception{
		writeGmLog(new GmUpdateLog(gmUser,gmGroupDao.getGmGroupById(group.getId()),group,new Date()));
		gmGroupDao.updateGmGroup(group);
		return gmGroupDao.getGmGroupById(group.getId());
	}
	
	public void updateActivity(GmUser gmUser, SysActivity sa) throws Exception{
		writeGmLog(new GmUpdateLog(gmUser,getService.getSysActivityDao().getSysActivityMap().get(sa.getId()),sa,new Date()));
		sa.initActivity();
		if(sa.getAction()==Constants.ACTION_ACTIVITY.RANDOM_ACTIVITY.getValue()){
			if(sa.getTargetNum() < 5){
				sa.setTargetNum(5);
			}
		}
		sysActivityDao.updateMappingBeanInCacheWithDefaultId(sa);
		sysActivityDao.updateObjInDB(sa);
		if(sa.getAction()==Constants.ACTION_ACTIVITY.HOUR2HOUR_ACTIVITY.getValue() || sa.getAction()==Constants.ACTION_ACTIVITY.RANDOM_ACTIVITY.getValue()){
			soClient.refreashSysConfig();
		}
		String key=CacheUtil.oCurrentActivityMap();
		mcc.delete(key);
	}
	
	public void updatePayment(GmUser gmUser, List<Payment> paymentList) throws Exception{
		for(Payment payment : paymentList){
			//if(payment.isChange == 1){
				SysItem sysItem=getService.getSysItemByItemId(payment.getItemId());
//				String[] characterids=sysItem.getCharacterIds();
//				for(String id:characterids){
//					mcc.delete(CacheUtil.sShop(sysItem.getType(),Integer.valueOf(id)));
//					mcc.delete(CacheUtil.oShop(sysItem.getType(),Integer.valueOf(id)));
//				}
				writeGmLog(new GmUpdateLog(gmUser,getService.getPaymentById(payment.getItemId(), payment.getId()),payment,new Date()));
				paymentDao.updatePayment(payment);
				mcc.delete(CacheUtil.oPaymentList(payment.getItemId()));
				ServiceLocator.getService.joinPayment(sysItem);
			//}
		}
	}
	public void resetPlayerWeapon(GmUser gmUser, int playerId) throws Exception{
		resetPlayerWeapon(playerId);
	}
	
	/**
	 * GM装备物品的接口
	 * @param gmUser
	 * @param playerItemId
	 * @throws Exception
	 */
	public void putOnPlayerItem(GmUser gmUser, int playerItemId, int playerId){
		try{
		PlayerItem item = getService.getPlayerItemById(playerId, playerItemId);
		if (null != item) {
			SysItem si=sysItemDao.getAllSysItemMap().get(item.getItemId());
			int type = si.getType();
			int characterId = si.getCharacterList().get(0);
			if (Constants.DEFAULT_WEAPON_TYPE == type) {
				ServiceLocator.updateService.updateWeaponPackEquipment(playerId, type, playerItemId, characterId);
			} else if (Constants.DEFAULT_COSTUME_TYPE == type || Constants.DEFAULT_PART_TYPE == type) {
				ServiceLocator.updateService.updateCostumePackEquipment(playerId, type, playerItemId, characterId);
			}
		}
		} catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void updateUnSpeaker(GmUser gmUser, PlayerVO player, int type) throws Exception{
		if(type == 0){
			unSpeakerDao.deleteUnSpeakerByPlayerId(player.getId());
		} else if(type == 1){
			List<UnSpeaker> list = getService.getAllUnSpeakerList();
			for(UnSpeaker unSpeaker : list){
				if(unSpeaker.getPlayerId() == player.getId()){
					return;
				}
			}
			int id = unSpeakerDao.insertUnSpeaker(player.getId());
		}
		mcc.delete(CacheUtil.oUnSpeakerList());
		soClient.refreashUnSpeakerList();
	}
	public int updateSysChest(GmUser gmUser, SysChest sysChest) throws Exception{
		try {
			sysChestDao.update(sysChest);
			getService.flushLuckyPackagePageCache();
			return sysChest.getType();
		} catch (Exception e) {
			ServiceLocator.fileLog.warn(e.getMessage(), e);
			throw e;
		}
	}
	
	public int clearLoginInfo(GmUser gmUser, int type) throws Exception {
		try {
			if (1 == type) {// day
				// clear memcached
				String memDayPre = new StringBuilder(Constants.OBJECT_TYPE).append(Constants.D_MISSION_DATE).toString();
				String key = null;
				int iteratorKeyCounts = 0;
				for (InetSocketAddress isa : mcc.getAvaliableServers()) {
					KeyIterator iterator = mcc.getKeyIterator(isa);
					iterator.setOpTimeout(Constants.CACHE_TIMEOUT);
					try {
						while (iterator.hasNext()) {
							key = iterator.next();
							if (key.startsWith(memDayPre)) {
								mcc.delete(key);
								iteratorKeyCounts++;
							}
						}
					} catch (Exception e) {
						ServiceLocator.fileLog.warn("clearLoginInfo has error");
					}
				}

				// clear redis
				String redisDayPre = Constants.DAILY_MISSION_PREFIX + "*";
				Set<String> dayKeys = ServiceLocator.nosqlService.getNosql().getKeysByPattern(redisDayPre);
				if (dayKeys.size() > 0) {
					ServiceLocator.nosqlService.getNosql().delete(dayKeys);
				}
			} else if (2 == type) {// week
				String memWeekPre = new StringBuilder(Constants.OBJECT_TYPE).append(Constants.W_MISSION_DATE).toString();
				// clear memcached
				String key = null;
				int iteratorKeyCounts = 0;
				for (InetSocketAddress isa : mcc.getAvaliableServers()) {
					KeyIterator iterator = mcc.getKeyIterator(isa);
					iterator.setOpTimeout(Constants.CACHE_TIMEOUT);
					try {
						while (iterator.hasNext()) {
							key = iterator.next();
							if (key.startsWith(memWeekPre)) {
								mcc.delete(key);
								iteratorKeyCounts++;
							}
						}
					} catch (Exception e) {
						ServiceLocator.fileLog.warn("clearLoginInfo has error");
					}
				}

				// clear redis
				String redisWeekPre = Constants.WEEK_MISSION_PREFIX + "*";
				Set<String> weekKeys = ServiceLocator.nosqlService.getNosql().getKeysByPattern(redisWeekPre);
				if (weekKeys.size() > 0) {
					ServiceLocator.nosqlService.getNosql().delete(weekKeys);
				}
			}
			return Constants.NUM_ZERO;
		} catch (Exception e) {
			ServiceLocator.fileLog.warn(e.getMessage(), e);
			throw e;
		}
	}
	
	public void updateOnlineAward(GmUser gmUser,OnlineAward award)throws Exception{
		OnlineAward oa = ServiceLocator.getService.getOnlineAwardById(award.getId());
		writeGmLog(new GmUpdateLog(gmUser,oa,award,new Date()));
		int oldType = oa.getType();
		int newType = award.getType();
		onlineAwardDao.updateOnlineAward(award);
		ServiceLocator.deleteService.deleteOnlineAwardsByTypeInMemcache(newType);
		if(oldType!=newType){
			ServiceLocator.deleteService.deleteOnlineAwardsByTypeInMemcache(oldType);	
		}
	}
	
	public void updateStrengthenAppend(GmUser user,StrengthenAppend stre)throws Exception{
		strengthenAppendDao.updateStreApp(stre);
		getService.initStrengthAppend();
	}
	
	public void resetStrengthenAppend(GmUser user)throws Exception{
		resetService.resetStrengthAppend();
	}
	@SuppressWarnings("unchecked")
	public void revDeletedItems(GmUser gmUser,int playerId,List playerItemIdList) throws Exception{
			playerItemDao.revDeletedPlayerItem(playerId, playerItemIdList);
	}
	public void cleanPlayerItemsCache(GmUser gmUser,int playerId) throws Exception{
		//玩家仓库外层cache
		for(SysCharacter sc : getService.getDefaultSysCharacterList()){
			mcc.delete(CacheUtil.oStorage1(playerId, Constants.DEFAULT_WEAPON_TYPE, sc.getId(), 0)); 
			mcc.delete(CacheUtil.oStorage1(playerId, Constants.DEFAULT_COSTUME_TYPE, sc.getId(), 0)); 
			mcc.delete(CacheUtil.oStorage1(playerId, Constants.DEFAULT_PART_TYPE, sc.getId(), 0)); 
		}
		mcc.delete(CacheUtil.oStorage1(playerId, Constants.DEFAULT_ITEM_TYPE,0, 0));
		mcc.delete(CacheUtil.oStorage1(playerId, Constants.DEFAULT_MATERIAL_TYPE,0, 0));
		mcc.delete(CacheUtil.oStorage1(playerId, Constants.DEFAULT_BLUEPRINT_TYPE,0, 0));
		mcc.delete(CacheUtil.oStorage1(playerId, Constants.DEFAULT_PACKAGE_TYPE,0, 0));
		mcc.delete(CacheUtil.oStorage1(playerId, Constants.DEFAULT_OPEN_TYPE,0, 0));
		//玩家仓库最底层cache，删除需保证该cache已同步至数据库，且玩家不在线。　
		String key  = DaoCacheUtil.oCacheKey(PlayerItem.class, playerId);
		mcc.delete(key);
	}
	
	public void modifyPlayerItems(GmUser gmUser,List<PlayerItem> playerItems) throws Exception{
		List<PlayerItem> updateList = new ArrayList<PlayerItem>();
		Date time = new Date();
		for(PlayerItem piVo : playerItems){
			PlayerItem pi = getService.getPlayerItemById(piVo.getPlayerId(), piVo.getId());
			PlayerItem older = new PlayerItem();
			BeanUtils.copyProperties(older, pi);
			boolean isUpdate =false;
			if(pi.getLevel()!=piVo.getLevel()){
				pi.setLevel(piVo.getLevel());
				isUpdate = true;
			}
			if(!pi.getGunProperty2().equals(piVo.getGunProperty2())){
				pi.setGunProperty2(piVo.getGunProperty2());
				isUpdate = true;
			}
			if(!pi.getGunProperty3().equals(piVo.getGunProperty3())){
				pi.setGunProperty3(piVo.getGunProperty3());
				isUpdate = true;
			}
			if(!pi.getGunProperty4().equals(piVo.getGunProperty4())){
				pi.setGunProperty4(piVo.getGunProperty4());
				isUpdate = true;
			}
			if(!pi.getGunProperty5().equals(piVo.getGunProperty5())){
				pi.setGunProperty5(piVo.getGunProperty5());
				isUpdate = true;
			}
			if(!pi.getGunProperty6().equals(piVo.getGunProperty6())){
				pi.setGunProperty6(piVo.getGunProperty6());
				isUpdate = true;
			}
			if(!pi.getGunProperty7().equals(piVo.getGunProperty7())){
				pi.setGunProperty7(piVo.getGunProperty7());
				isUpdate = true;
			}
			if(isUpdate){
				updateList.add(pi);
				writeGmLog(new GmUpdateLog(gmUser, older, pi, time));
			}
			
		}
		if(!updateList.isEmpty()){
			for(PlayerItem pi : updateList){
				ServiceLocator.updateService.updatePlayerItem(pi);
			}
		}
	}
	public void updatePlayerPtWeights(GmUser gmUser,int type,String value) throws Exception{
		if(type==1&&value!=null&&value.matches("0\\.[0-9]*")){
			nosqlService.getNosql().set(Constants.PLAYER_PT_GET_WEIGHTS_KEY, value); 
		}else if ((type ==2 || type==3)&&value!=null&&value.matches("[0-9]*:[0-9]*")){
			nosqlService.getNosql().set(Constants.PLAYER_PT_FLAG_GET_WEIGHTS_KEY + (type-1) , value);
		}
	}
}
