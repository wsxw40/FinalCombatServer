package com.pearl.o2o.service;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeoutException;

import net.rubyeye.xmemcached.exception.MemcachedException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.Iterables;
import com.google.common.collect.Multiset;
import com.google.common.collect.Ordering;
import com.google.common.primitives.Ints;
import com.pearl.o2o.exception.BaseException;
import com.pearl.o2o.exception.CRNotEnoughException;
import com.pearl.o2o.exception.DuplicateAllyException;
import com.pearl.o2o.exception.NotBuyEquipmentException;
import com.pearl.o2o.exception.NotEquipException;
import com.pearl.o2o.nosql.NoSql;
import com.pearl.o2o.nosql.NosqlKeyUtil;
import com.pearl.o2o.nosql.object.playerevent.AchievementEvent;
import com.pearl.o2o.nosql.object.playerevent.DeadEvent;
import com.pearl.o2o.nosql.object.playerevent.GetExpEvent;
import com.pearl.o2o.nosql.object.playerevent.GetGpEvent;
import com.pearl.o2o.nosql.object.playerevent.KillEvent;
import com.pearl.o2o.nosql.object.playerevent.LevelUpEvent;
import com.pearl.o2o.nosql.object.playerevent.TeamBuffActivateEvent;
import com.pearl.o2o.nosql.object.playerevent.TitleEvent;
import com.pearl.o2o.nosql.object.team.TeamRecordObj;
import com.pearl.o2o.pojo.Ally;
import com.pearl.o2o.pojo.AwardItemVo;
import com.pearl.o2o.pojo.BattleFieldRobDaily;
import com.pearl.o2o.pojo.BuyItemRecord;
import com.pearl.o2o.pojo.CharacterData;
import com.pearl.o2o.pojo.CombineProperty;
import com.pearl.o2o.pojo.Friend;
import com.pearl.o2o.pojo.GeneralStageClearInfo;
import com.pearl.o2o.pojo.GunProperty;
import com.pearl.o2o.pojo.LevelInfo;
import com.pearl.o2o.pojo.LevelModeInfo;
import com.pearl.o2o.pojo.MeltingAward;
import com.pearl.o2o.pojo.MeltingReslut;
import com.pearl.o2o.pojo.Message;
import com.pearl.o2o.pojo.OnlineAward;
import com.pearl.o2o.pojo.PayLog;
import com.pearl.o2o.pojo.Payment;
import com.pearl.o2o.pojo.Player;
import com.pearl.o2o.pojo.PlayerAchievement;
import com.pearl.o2o.pojo.PlayerActivity;
import com.pearl.o2o.pojo.PlayerGrowthMission;
import com.pearl.o2o.pojo.PlayerInfo;
import com.pearl.o2o.pojo.PlayerItem;
import com.pearl.o2o.pojo.PlayerMelting;
import com.pearl.o2o.pojo.PlayerMission;
import com.pearl.o2o.pojo.PlayerPack;
import com.pearl.o2o.pojo.PlayerTeam;
import com.pearl.o2o.pojo.PlayerVO;
import com.pearl.o2o.pojo.Rank;
import com.pearl.o2o.pojo.StageClearPlayerInfo;
import com.pearl.o2o.pojo.SysAchievement;
import com.pearl.o2o.pojo.SysActivity;
import com.pearl.o2o.pojo.SysCharacter;
import com.pearl.o2o.pojo.SysChest;
import com.pearl.o2o.pojo.SysGrowthMission;
import com.pearl.o2o.pojo.SysItem;
import com.pearl.o2o.pojo.SysMission;
import com.pearl.o2o.pojo.SysModeConfig;
import com.pearl.o2o.pojo.SysTeamBuff;
import com.pearl.o2o.pojo.Team;
import com.pearl.o2o.pojo.TeamBuff;
import com.pearl.o2o.pojo.TeamItem;
import com.pearl.o2o.pojo.TeamLevelInfo;
import com.pearl.o2o.pojo.TeamRecord;
import com.pearl.o2o.pojo.TeamScoreIncrement;
import com.pearl.o2o.pojo.StageClearPlayerInfo.GetChances;
import com.pearl.o2o.pojo.StageClearPlayerInfo.GetExps;
import com.pearl.o2o.pojo.StageClearPlayerInfo.GetGps;
import com.pearl.o2o.pojo.StageClearPlayerInfo.SingleCharacterInfo;
import com.pearl.o2o.utils.CacheUtil;
import com.pearl.o2o.utils.ChallengeHillStatus;
import com.pearl.o2o.utils.CommonMsg;
import com.pearl.o2o.utils.CommonUtil;
import com.pearl.o2o.utils.ConfigurationUtil;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.DateUtil;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.GrowthMissionConstants;
import com.pearl.o2o.utils.LogUtils;
import com.pearl.o2o.utils.MeltingConstants;
import com.pearl.o2o.utils.ServiceLocator;
import com.pearl.o2o.utils.StringUtil;
import com.pearl.o2o.utils.TeamConstants;
import com.pearl.o2o.utils.TeamUtils;
import com.pearl.o2o.utils.TimeTrack;
import com.pearl.o2o.utils.Constants.GAMETYPE;
import com.pearl.o2o.utils.GrowthMissionConstants.BooleanBytevalue;
import com.pearl.o2o.utils.GrowthMissionConstants.CycleMissionIncome;
import com.pearl.o2o.utils.GrowthMissionConstants.GrowthMissionType;
import com.pearl.o2o.utils.GrowthMissionConstants.ModuleStatus;
import com.pearl.o2o.utils.GrowthMissionConstants.ModuleStatusIndex;
import com.pearl.o2o.utils.TeamConstants.TEAMJOB;

public class UpdateService extends BaseService {
	private static Logger log = LoggerFactory.getLogger(UpdateService.class.getName());
	private CreateService createService;
	private MessageService messageService;
	public static Random random = new Random(47);

	/**
	 * 更新玩家成长任务进度
	 * @param playerId
	 * @param gmTypes
	 * @return
	 */
	public boolean updatePlayerGrowthMission(final int playerId, final GrowthMissionType... gmTypes) {
		try {
			Player player = getService.getPlayerById(playerId);
			return updatePlayerGrowthMission(player, gmTypes);
		} catch (Exception e) {
			ServiceLocator.growthMissionErrLog.error("GrowthMission updatePlayerGrowthMission getPlayerById exception!:", e);
		}
		return false;
	}

	/**
	 * 更新玩家成长任务进度
	 * 本模块当前设计 因嵌套在其他模块中 不外抛Exception，
	 * @param player
	 * @param gmTypes not <code>null</code> and not empty
	 * @return 是否更新了任务
	 */
	public boolean updatePlayerGrowthMission(final Player player, final GrowthMissionType... gmTypes) {
		try {
			// step 0 ,check submit gmTypes
			if (gmTypes==null||gmTypes.length == 0)
				return false;
			// step 1 ,get executable mission by rank and gmTypes
			PlayerGrowthMission playerGrowthMission = null;
			if(gmTypes[0].getGrowthMissionId()<GrowthMissionType.COMBINE_STRENGTH_MAINARM_3.getGrowthMissionId()){
				playerGrowthMission = getService.getPaGrowthMission(player.getId(), player.getRank(), gmTypes);
			}else if(gmTypes[0].getGrowthMissionId()>=GrowthMissionType.NEW_AWARD_1.getGrowthMissionId()
					&&gmTypes[0].getGrowthMissionId()<=GrowthMissionType.NEW_AWARD_8.getGrowthMissionId()){
				playerGrowthMission = getService.getPaGrowthMission(player.getId(), player.getRank(), gmTypes);
				if(playerGrowthMission!=null){
					playerGrowthMission.setNumber(player.getRank());
				}
				
			}else{
				playerGrowthMission = getService.getPaCombineStrengthGrowthMission(player.getId(), player.getRank(), gmTypes[0]);
			}
			//for new player award
			if(null != playerGrowthMission&&gmTypes[0].getGrowthMissionId()>=GrowthMissionType.NEW_AWARD_1.getGrowthMissionId()
					&&gmTypes[0].getGrowthMissionId()<=GrowthMissionType.NEW_AWARD_8.getGrowthMissionId()){
				SysGrowthMission sysGrowthMission = playerGrowthMission.getSysGrowthMission();
				if(playerGrowthMission.getNumber()>= sysGrowthMission.getNumber()){
					playerGrowthMission.setNumber(sysGrowthMission.getNumber());
					playerGrowthMission.setStatus(BooleanBytevalue.TRUE.getBytevalue());
					pushModuleStatusChanage(player, ModuleStatusIndex.Mission);
					playerGrowthMissionDao.updatePlayerGrowthMissions(playerGrowthMission);
				}else{
					playerGrowthMissionDao.updatePlayerGrowthMissions(playerGrowthMission);
				}
				return true;
			}
			// step 2 ,handle 
			if (null != playerGrowthMission) {
				//playerGrowthMission number increase
				playerGrowthMission.setNumber(playerGrowthMission.getNumber() + 1);
				// step 3 , if mission complete
				SysGrowthMission sysGrowthMission = playerGrowthMission.getSysGrowthMission();
				if (playerGrowthMission.getNumber() >= sysGrowthMission.getNumber()) {
					playerGrowthMission.setStatus(BooleanBytevalue.TRUE.getBytevalue());
					// step 4 ,mission complete,push Mission_Module.FLASH cmd
					pushModuleStatusChanage(player, ModuleStatusIndex.Mission);
					playerGrowthMissionDao.updatePlayerGrowthMissions(playerGrowthMission);
				}else{
					playerGrowthMissionDao.updatePlayerGrowthMissions(playerGrowthMission);
				}
				return true;
			}
		} catch (Exception e) {
			ServiceLocator.growthMissionErrLog.error("GrowthMission updatePlayerGrowthMission exception!:", e);
		}
		return false;
	}
	//only add xunlei point use this one
	public boolean updatePlayerGrowthMission(final Player player,final int num, final GrowthMissionType... gmTypes) {
		try {
			// step 0 ,check submit gmTypes
			if (gmTypes.length == 0)
				return false;
			// step 1 ,get executable mission by rank and gmTypes
			PlayerGrowthMission playerGrowthMission = null;
			if(gmTypes[0].getGrowthMissionId()<GrowthMissionType.COMBINE_STRENGTH_MAINARM_3.getGrowthMissionId()){
				playerGrowthMission = getService.getPaGrowthMission(player.getId(), player.getRank(), gmTypes);
			}else{
				playerGrowthMission = getService.getPaCombineStrengthGrowthMission(player.getId(), player.getRank(), gmTypes[0]);
			}
			// step 2 ,handle 
			if (null != playerGrowthMission) {
				//playerGrowthMission number increase
				// step 3 , if mission complete
				SysGrowthMission sysGrowthMission = playerGrowthMission.getSysGrowthMission();
				if(playerGrowthMission.getNumber() + num > sysGrowthMission.getNumber()){
					playerGrowthMission.setNumber(sysGrowthMission.getNumber());
				}else{
					playerGrowthMission.setNumber(playerGrowthMission.getNumber() + num);
				}
//				playerGrowthMission.setNumber(playerGrowthMission.getNumber() + 1);
				if (playerGrowthMission.getNumber() >= sysGrowthMission.getNumber()) {
					playerGrowthMission.setStatus(BooleanBytevalue.TRUE.getBytevalue());
					// step 4 ,mission complete,push Mission_Module.FLASH cmd
					pushModuleStatusChanage(player, ModuleStatusIndex.Mission);
					playerGrowthMissionDao.updatePlayerGrowthMissions(playerGrowthMission);
				}else{
					playerGrowthMissionDao.updatePlayerGrowthMissions(playerGrowthMission);
				}
				return true;
			}
		} catch (Exception e) {
			ServiceLocator.growthMissionErrLog.error("GrowthMission updatePlayerGrowthMission exception!:", e);
		}
		return false;
	}
	public boolean updatePlayerCombineStrengthGrowthMission(final Player player, final GrowthMissionType gmType) {
		try {
			// step 0 ,check submit gmTypes
//			if (gmTypes.length == 0)
//				return false;
			// step 1 ,get executable mission by rank and gmTypes
			PlayerGrowthMission playerGrowthMission = getService.getPaCombineStrengthGrowthMission(player.getId(), player.getRank(), gmType);
			// step 2 ,handle 
			if (null != playerGrowthMission) {
				//playerGrowthMission number increase
				playerGrowthMission.setNumber(playerGrowthMission.getNumber() + 1);
				// step 3 , if mission complete
				SysGrowthMission sysGrowthMission = playerGrowthMission.getSysGrowthMission();
				if (playerGrowthMission.getNumber() == sysGrowthMission.getNumber()) {
					playerGrowthMission.setStatus(BooleanBytevalue.TRUE.getBytevalue());
					// step 4 ,mission complete,push Mission_Module.FLASH cmd
					pushModuleStatusChanage(player, ModuleStatusIndex.Mission);
					playerGrowthMissionDao.updatePlayerGrowthMissions(playerGrowthMission);
				}else{
					playerGrowthMissionDao.updatePlayerGrowthMissions(playerGrowthMission);
				}
				return true;
			}
		} catch (Exception e) {
			ServiceLocator.growthMissionErrLog.error("GrowthMission updatePlayerGrowthMission exception!:", e);
		}
		return false;
	}

	/**
	 * 向玩家推送模块变化信息 会改变player的tutorial字段
	 * 
	 * @param player
	 * @param moduleStatusIndex
	 * @throws Exception
	 */
	public void pushModuleStatusChanage(Player player, ModuleStatusIndex moduleStatusIndex) throws Exception {
		pushModuleStatusChanage(player, moduleStatusIndex.ordinal());
	}

	public void pushModuleStatusChanage(Player player, int openmodule) throws Exception {
		char[] status = ServiceLocator.getService.getPlayerTutorial(player);
		status[openmodule] = ModuleStatus.FLASH.getCh();
		updatePlayerTutorial(player, status);
		soClient.puchCMDtoClient(player.getName(), Converter.getModuleStatusChangePush(openmodule, status));
	}

	public void updatePlayerTutorial(Player player, String tutorial) throws Exception {
		player.setTutorial(tutorial);
		updatePlayerInfo(player);
	}
	public void updatePlayerTutorial(Player player, char[] status) throws Exception {
		updatePlayerTutorial(player, new String(status));
	}

	public void updatePlayerTutorialByGM(Player player, String tutorial) throws Exception {
		char dailyMissionOld = player.getTutorial().charAt(ModuleStatusIndex.DailyMission.ordinal());
		char dailyMissionNew = tutorial.charAt(ModuleStatusIndex.DailyMission.ordinal());
		if (dailyMissionOld == ModuleStatus.CLOSE.getCh() && dailyMissionNew != ModuleStatus.CLOSE.getCh()) {
			ServiceLocator.updateService.sendMission(player, true, true);
		}
		updatePlayerTutorial(player, tutorial);
		pushModuleStatusChanage(player, ModuleStatusIndex.Mission);
	}

	/**
	 * 向老玩家派发成长任务
	 * 
	 * @param player
	 */
	public void initGrowthMission4OldPlayer(final Player player) {
		int playerId = player.getId();
		int currRank = player.getRank();
		// ServiceLocator.growthMissionLog.debug(String.format("initGrowthMissionByOldPlayer playerId:%s ;currRank:%s ;",playerId,currRank));
		try {
			Map<Integer, SysGrowthMission> allGrowthMissionMap = sysGrowthMissionDao.getAllGrowthMissionMap();
			Map<Integer, PlayerGrowthMission> paGrowthMissions = playerGrowthMissionDao.getPaGrowthMissions(playerId);
			for (SysGrowthMission sgm : allGrowthMissionMap.values()) {
				if (!paGrowthMissions.containsKey(sgm.getId()) && sgm.getStatus() == BooleanBytevalue.TRUE.getBytevalue() && sgm.getRank() <= currRank) {
					PlayerGrowthMission playerGrowthMission = new PlayerGrowthMission(playerId, sgm.getId());
					if (sgm.getDefaultopen() == BooleanBytevalue.TRUE.getBytevalue() && sgm.getRank()<currRank) {
						playerGrowthMission.setStatus(sgm.getDefaultopen());
						playerGrowthMission.setNumber(sgm.getNumber());
					}
					playerGrowthMissionDao.createPlayerGrowthMission(playerId, playerGrowthMission);
					// ServiceLocator.growthMissionLog.debug(String.format("initGrowthMissionByOldPlayer playerId:%s ;currRank:%s ;playerGrowthMission:%s ;",playerId,currRank,playerGrowthMission.getId()));
				}
			}
		} catch (Exception e) {
			ServiceLocator.growthMissionErrLog.error(String.format("initGrowthMissionByOldPlayer playerId:%s ;currRank:%s ;", playerId, currRank), e);
		}
	}

	/**
	 * 向新玩家派发成长任务
	 * 
	 * @param player
	 */
	public void initGrowthMission4NewPlayer(final Player player) {
		// 初始化 玩家信息 自动派发 以及任务给玩家
		final int currRank = player.getRank(); 
		final int preRank = 0;
		sendGrowthMission(player, currRank, preRank);
	}

	/**
	 * 玩家升级时派发新任务
	 * @param player
	 * @param currRank
	 * @param preRank
	 */
	public void sendGrowthMission(final Player player, int currRank, int preRank) {
		int playerId = player.getId();
		try {
			Map<Integer, SysGrowthMission> allGrowthMissionMap = sysGrowthMissionDao.getAllGrowthMissionMap();
			Map<Integer, PlayerGrowthMission> paGrowthMissions = playerGrowthMissionDao.getPaGrowthMissions(playerId);
			// flag push Mission_Module.Flash
			boolean flag = false;
			for (SysGrowthMission sgm : allGrowthMissionMap.values()) {
				if (sgm.getRank() <= currRank && sgm.getStatus() == BooleanBytevalue.TRUE.getBytevalue() && !paGrowthMissions.containsKey(sgm.getId())) {
					final PlayerGrowthMission playerGrowthMission = new PlayerGrowthMission(playerId, sgm.getId());
					playerGrowthMissionDao.createPlayerGrowthMission(playerId, playerGrowthMission);
					PlayerGrowthMission parentMission = paGrowthMissions.get(sgm.getParent());
					if (!flag  && (sgm.getParent() == 0 || (null != parentMission && parentMission.getReceived() == BooleanBytevalue.TRUE.getBytevalue()))) {
						flag = true;
					}
				}else if(sgm.getId()>=GrowthMissionType.NEW_AWARD_1.getGrowthMissionId()&&sgm.getId()<=GrowthMissionType.NEW_AWARD_8.getGrowthMissionId()){
					PlayerGrowthMission playerGrowthMission = paGrowthMissions.get(sgm.getId());
					if(playerGrowthMission.getNumber()>= sgm.getNumber()){
						playerGrowthMission.setStatus(BooleanBytevalue.TRUE.getBytevalue());
					}
					playerGrowthMission.setNumber(currRank);
					playerGrowthMissionDao.updatePlayerGrowthMissions(playerGrowthMission);
				}
			}
			if (flag) {
				// push openModule.Flash cmd
				pushModuleStatusChanage(player, ModuleStatusIndex.Mission);
			}
		
		} catch (Exception e) {
			ServiceLocator.growthMissionErrLog.error(String.format("sendGrowthMission playerId:%s ;currRank:%s ;preRank:%s ;", playerId, currRank, preRank), e);
		}
	}
	
	public void updateItemQuantity(PlayerItem playerItem,int num) throws Exception{
		playerItem.setQuantity(playerItem.getQuantity() + num);
		playerItemDao.updatePlayerItem(playerItem);
	}

	// ===============================================================================
	// Sys activity Services
	// ===============================================================================
	public OnlineAward updatePlayerLuckyPackagePay(int playerId, int level, int payType,int multiple,  List<SysChest> fixList, List<SysChest> multiRandoms) throws Exception {

		SysChest sysChest = fixList.get(0);
		Player p = getService.getPlayerById(playerId);
		if (payType == 1) {
			int font = ServiceLocator.getService.getLuckypackageCardNum(playerId, level);
			List<PlayerItem> luckypackageCards = getService.getPlayerItemsByIIdAndIValue(playerId, Constants.DEFAULT_MATERIAL_TYPE,Constants.SPECIAL_ITEM_IIDS.LUCKYPACKAGE_CARD.getValue(),level );
			for(PlayerItem pi :luckypackageCards ){
				if(pi.getQuantity()>=1){
					if(pi.getQuantity()<multiple){
						ServiceLocator.deleteService.deleteCombiningItem(pi,pi.getQuantity());
						multiple -=pi.getQuantity();
					}else{
						ServiceLocator.deleteService.deleteCombiningItem(pi,multiple);
						break;
					}
				}
			}
			if (ConfigurationUtil.SWITCH_XUNLEI_LOG.getIsOn()) {
				nosqlService.addXunleiLog("7.1"
						+ Constants.XUNLEI_LOG_DELIMITER + p.getUserName()
						+ Constants.XUNLEI_LOG_DELIMITER + p.getName()
						+ Constants.XUNLEI_LOG_DELIMITER + (4+level)
						+ Constants.XUNLEI_LOG_DELIMITER + sysChest.getLevel()
						+ Constants.XUNLEI_LOG_DELIMITER + sysChest.getPrice()
						+ Constants.XUNLEI_LOG_DELIMITER+ CommonUtil.simpleDateFormat.format(new Date())
						+ Constants.XUNLEI_LOG_DELIMITER + font
//						+ Constants.XUNLEI_LOG_DELIMITER + playerInfo.getXunleiPoint()
						+ Constants.XUNLEI_LOG_DELIMITER + (font-multiple)
						);
			}
		} else {
			SysItem medal = getService.getSysItemByIID(Constants.DEFAULT_MEDAL_IID, Constants.DEFAULT_ITEM_TYPE).get(0);
			int totalNum = sysChest.getChipPrice()*multiple;
			List<PlayerItem> list = getService.getPlayerItems(playerId, Constants.DEFAULT_ITEM_TYPE, 0, 0);

			for (PlayerItem pi : list) {
				if (pi != null && pi.getItemId() == medal.getId()) {
					if (pi.getQuantity() >= totalNum) {
						ServiceLocator.deleteService.deleteCombiningItem(pi, totalNum);
						break;
					} else {
						totalNum = totalNum - pi.getQuantity();
						ServiceLocator.deleteService.deleteCombiningItem(pi, pi.getQuantity());
					}
				}
			}
			if (ConfigurationUtil.SWITCH_XUNLEI_LOG.getIsOn()) {
				int total=getService.getMedolNumByPlayerId(playerId);
				nosqlService.addXunleiLog("7.1"
						+ Constants.XUNLEI_LOG_DELIMITER + p.getUserName()
						+ Constants.XUNLEI_LOG_DELIMITER + p.getName()
						+ Constants.XUNLEI_LOG_DELIMITER + 4
						+ Constants.XUNLEI_LOG_DELIMITER + sysChest.getLevel()
						+ Constants.XUNLEI_LOG_DELIMITER + sysChest.getChipPrice()
						+ Constants.XUNLEI_LOG_DELIMITER+ CommonUtil.simpleDateFormat.format(new Date())
						+ Constants.XUNLEI_LOG_DELIMITER + (total + totalNum)
						+ Constants.XUNLEI_LOG_DELIMITER + total
						);
				nosqlService.addXunleiLog("7.4"
						+ Constants.XUNLEI_LOG_DELIMITER + p.getUserName()
						+ Constants.XUNLEI_LOG_DELIMITER + p.getName()
						+ Constants.XUNLEI_LOG_DELIMITER + 0
						+ Constants.XUNLEI_LOG_DELIMITER + totalNum
						+ Constants.XUNLEI_LOG_DELIMITER+ 998
						+ Constants.XUNLEI_LOG_DELIMITER+ CommonUtil.simpleDateFormat.format(new Date())
						);
				
			}
			CommonUtil.sortMaterial(playerId, Constants.DEFAULT_ITEM_TYPE, medal.getId());
			if (level == 1) {
				// 成长任务26：使用勋章汇换低级博彩宝箱
				updatePlayerGrowthMission(playerId, GrowthMissionType.GET_LOWGAMINGCHEST_BY_MEDAL);
			}
		}
		OnlineAward onlineAward=null;
		int isActivity=updatePlayerActivity(Constants.ACTION_ACTIVITY.OPEN_LUCKYPACKAGE.getValue(), playerId, null, 0, 0, null, null);
		if(isActivity==1){
			if(CommonUtil.getRandowMapByLevel(level)==0){
				List<PlayerActivity> playerActivityList=getService.getPlayerActivityList(playerId, Constants.ACTION_ACTIVITY.OPEN_LUCKYPACKAGE.getValue());
				for(PlayerActivity pa : playerActivityList){
					List<OnlineAward> onlineAwards=getService.getOnlineAwardByTypeAndLevel(Constants.ONLINE_AWARD_TYPES.LUCK_PACKAGE.getValue(),level);
					onlineAward=getService.randomOnlineAward(onlineAwards);
					pa.getSysActivity().setSysItem(getService.getSysItemByItemId(onlineAward.getItemId()));
					pa.getSysActivity().setUnit(onlineAward.getUnit());
					pa.getSysActivity().setUnitType(onlineAward.getUnitType());
					awardActivity(pa);
				}
			}
		}
		createService.awardToPlayer(p, fixList, null, Constants.BOOLEAN_YES);
		createService.awardToPlayer(p, multiRandoms, null, Constants.BOOLEAN_YES);
		return onlineAward;
	}

	public void updateStageKillActivity(PlayerActivity playerActivity) throws Exception {
		playerActivityDao.updatePlayerActivity(playerActivity);
	}

	public void updatePlayerActivity(PlayerActivity playerActivity) throws Exception {
		playerActivityDao.updatePlayerActivity(playerActivity);
	}

	public String awardActivity(PlayerActivity playerActivity) throws Exception {
		Player player = getService.getPlayerById(playerActivity.getPlayerId());
		String result = createService.sendActivityGift(player, playerActivity);
		playerActivity.setAward(1);
		updatePlayerActivity(playerActivity);
		String key = CacheUtil.oPlayerActivityList(playerActivity.getPlayerId());
		mcc.delete(key);
		return result;
	}

	// ===============================================================================
	// User Services
	// ===============================================================================

	public void updateSysModeConfig(Integer type, String config) throws Exception {
		SysModeConfig modeConfig = new SysModeConfig();
		modeConfig.setType(type);
		modeConfig.setConfig(config);
		sysModeConfigDao.updateSysModeConfigDao(modeConfig);
	}

	public void updateConfig(String config) throws Exception {
		updateSysModeConfig(3, config);
	}

	public void updateUserProfile(int uid, String profile) {
		userDao.updateUserProfile(uid, profile);
	}

	// ===============================================================================
	// Player Services
	// ===============================================================================
	public void updateXunleiId(PlayerVO p)throws Exception{
		playerDao.updatePlayerXunleiId(p);
		mcc.delete(CacheUtil.oPlayer(p.getId()));
		mcc.delete(CacheUtil.sPlayer(p.getId()));
	}
	public void updatePlayerProfile(int uid, int cid, String profile) throws Exception {
		Player p = playerDao.getPlayerById(cid);
		p.setProfile(profile);
		playerDao.updatePlayerInCache(p);
	}

	public void updatePlayerWhileOffline(Player player) throws Exception {
		mcc.delete(CacheUtil.oPlayer(player.getId()));
		mcc.delete(CacheUtil.sPlayer(player.getId()));
		player.setLastLogoutTime((int) (System.currentTimeMillis() / 1000));
		playerDao.updatePlayerInCache(player);
	}

	public void updatePlayerWhileOnline(final Player player, String loginIp) throws Exception {
		mcc.delete(CacheUtil.oPlayer(player.getId()));
		mcc.delete(CacheUtil.sPlayer(player.getId()));
		player.setLastLoginIp(loginIp);

		/** notify client if has new friend news since last logout */
		try {
			if (getService.hasNewFriendNewsSinceLastLogin(player)) {
				soClient.messageCMD(player.getName(), CommonMsg.REFRESH_FRIENDNEWS);
			}
		} catch (IOException e) {
			log.warn("error happend during send friend news to player while player online, exception is" + e);
		}

		player.setLastLoginTime((int) (System.currentTimeMillis() / 1000));

		/** update the lastLoginTime and lastLoginIp .... */
		playerDao.updatePlayerInCache(player);
	}

	/**
	 * this method will update the fields of player during stageClear/stageQuit
	 * 
	 * @param player
	 * @param playerInfo
	 * @param gameType
	 * @param stageClearAndWin
	 * @throws Exception
	 */
	private void setPlayerInfoStageClear(Player player, StageClearPlayerInfo playerInfo, GAMETYPE gameType, boolean isClear) throws Exception {
		if (gameType.getValue() != Constants.GAMETYPE.NEWTRAIN.getValue()) {
			player.setGTotal(player.getGTotal() + 1);// whether player win or
														// lose Totoal plus 1
			if (isClear && 1 == playerInfo.getIsWiner()) {
				player.setGWin(player.getGWin() + 1);
				player.setWeekWin(player.getWeekWin() + 1);
			}
			boolean isNonFail = false;
			boolean isUndead = false;
			for (PlayerItem buff : player.getBuffList()) {
				SysItem si = sysItemDao.getSystemItemById(buff.getItemId());
				if (Constants.DEFAULT_ITEM_TYPE == si.getType() && si.getIId() == 1 && si.getIBuffId() == 7) {
					isNonFail = true;
				}
				if (Constants.DEFAULT_ITEM_TYPE == si.getType() && si.getIId() == 1 && si.getIBuffId() == 8) {
					isUndead = true;
				}
			}
			boolean isIgnoreDead = (updatePlayerActivity(Constants.ACTION_ACTIVITY.IGNORE_DEAD.getValue(), player.getId(), null, 0, 0, playerInfo, null) == 1) ? true : false;
			boolean isIgnoreLose = (updatePlayerActivity(Constants.ACTION_ACTIVITY.IGNORE_LOSE.getValue(), player.getId(), null, 0, 0, playerInfo, null) == 1) ? true : false;
			// 更新挽留机制数据
			nosqlService.updateStayData(player, playerInfo, (!isNonFail && 0 == playerInfo.getIsWiner() && !isIgnoreLose), (isUndead || isIgnoreDead));
			if (!isNonFail && !isIgnoreLose && 0 == playerInfo.getIsWiner()) {// 没有不败神话道具，并且输掉的，记一场失败
				player.setWeekTotal(player.getWeekTotal() + 1);
				player.setGLose(player.getGLose() + 1);
			}

			if (isUndead || isIgnoreDead) {
				player.setGDead(player.getGDead());
				player.setWeekDead(playerInfo.getDead());
			} else {
				player.setGDead(player.getGDead() + playerInfo.getDead());
				nosqlService.publishEvent(new DeadEvent(CommonUtil.getPostiveNumber(playerInfo.getDead()), System.currentTimeMillis() / 1000, player.getId(), player.getName()));
				player.setWeekDead(playerInfo.getDead() + player.getWeekDead());
			}
			player.setGKill(player.getGKill() + CommonUtil.getPostiveNumber(playerInfo.getKill()));
			nosqlService.publishEvent(new KillEvent(CommonUtil.getPostiveNumber(playerInfo.getKill()), System.currentTimeMillis() / 1000, player.getId(), player.getName()));
			player.setGKnifeKill(player.getGKnifeKill() + CommonUtil.getPostiveNumber(playerInfo.getKnifeKill()));
			player.setGAssist(playerInfo.getAssistNum() + player.getGAssist());
			player.setGControl(playerInfo.getControlNum() + player.getGControl());
			player.setGRevenge(playerInfo.getRevengeNum() + player.getGRevenge());
			player.setGHealth(playerInfo.getHealth() + player.getGHealth());
			player.setWeekScore(playerInfo.getScore() + player.getWeekScore());
			player.setWeekKill(playerInfo.getKill() + player.getWeekKill());
			player.setWeekAssist(playerInfo.getAssistNum() + player.getWeekAssist());
		}
	}
	private void updatePlayerItemDurablewhileStage(StageClearPlayerInfo playerInfo, int useTotal, double punish) throws Exception {
		long startTime = System.currentTimeMillis();
		List<SingleCharacterInfo> characterInfoList = playerInfo.getCharacterInfoList();
		for (SingleCharacterInfo ci : characterInfoList) {
			if (ci.getUsedCount() > 0 &&ci.getCharacterId()<10) {
				int cost = 0;
				cost = (int) (punish * (Math.sqrt(playerInfo.getScore()) * 0.1 * (ci.getUsedCount() * 1.0 / useTotal)));
				List<PlayerItem> playerItemList = getService.getWeaponPackList(playerInfo.getId(), 1, ci.getCharacterId());
				/** update players's weapon durable */
				batchDecreaseWeaponDurable(playerItemList, cost);
				ServiceLocator.stageClearLog.debug(playerInfo.getName() + " decrease durable " + cost + " getScore " + playerInfo.getScore() + " UsedCount " + ci.getUsedCount()
						+ " UseTotal " + playerInfo.getUseTotal());
			}
		}
		long endTime = System.currentTimeMillis();
		log.debug("7.0 use " + (endTime - startTime) + " ms");
		startTime = endTime;
	}

	private int updateCharacterDataWhileStage(GeneralStageClearInfo stageClearInfo, StageClearPlayerInfo playerInfo, Player player, LevelInfo levelInfo) throws Exception {
		long startTime = System.currentTimeMillis();
		int useTotal = 0;
		int boostKillNum = 0;
		List<SingleCharacterInfo> characterInfoList = playerInfo.getCharacterInfoList();
		boolean isUndead = false;
		boolean isIgnoreDead = (updatePlayerActivity(Constants.ACTION_ACTIVITY.IGNORE_DEAD.getValue(), playerInfo.getId(), null, 0, 0, playerInfo, null) == 1) ? true : false;
		long endTime = System.currentTimeMillis();
		log.debug("6.0 use " + (endTime - startTime) + " ms");
		startTime = endTime;
		for (PlayerItem buff : player.getBuffList()) {
			SysItem si = sysItemDao.getSystemItemById(buff.getItemId());
			if (Constants.DEFAULT_ITEM_TYPE == si.getType() && si.getIId() == 1 && si.getIBuffId() == 8) {
				isUndead = true;
			}
		}
		for (SingleCharacterInfo ci : characterInfoList) {
			int characherId = ci.getCharacterId();
			CharacterData characterData = getService.getCharacterData(playerInfo.getId(), characherId);
			if (characterData != null) {
				characterData.setKill(characterData.getKill() + ci.getKill());
				if (!isUndead && !isIgnoreDead) {
					characterData.setDead(characterData.getDead() + ci.getDead());
				}
				log.debug("UsedCount  characherId=" + characherId + " ci.getUsedCount()=" + ci.getUsedCount());
				boostKillNum += ci.getBoostKill();
				useTotal += ci.getUsedCount();
				characterData.setControlNum(characterData.getControlNum() + ci.getControlNum());
				characterData.setRevengeNum(characterData.getRevengeNum() + ci.getRevengeNum());
				characterData.setAssistNum(characterData.getAssistNum() + ci.getAssistNum());
				characterData.setKnifeKill(characterData.getKnifeKill() + ci.getKnifeKill());
				characterData.setUsedCount(characterData.getUsedCount() + ci.getUsedCount());
				int maxHeadshot = characterData.getMaxHeadshot();
				int maxKill = characterData.getMaxKill();
				int maxHealth = characterData.getMaxHealth();
				int maxDamage = characterData.getMaxDamage();
				int maxAliveTime = characterData.getMaxAliveTime();
				int maxVipNum = characterData.getMvpNum();
				if (playerInfo.getMaxHeadshotCharacter() == characherId) {
					if (playerInfo.getMaxHeadshotNum() > maxHeadshot) {
						characterData.setMaxHeadshot(playerInfo.getMaxHeadshotNum());
					}
				}
				if (playerInfo.getMaxKillNumCharacter() == characherId) {
					if (playerInfo.getMaxKillNum() > maxKill) {
						characterData.setMaxKill(playerInfo.getMaxKillNum());
					}
				}
				if (playerInfo.getMaxHealthNumCharacter() == characherId) {
					if (playerInfo.getMaxHealthNum() > maxHealth) {
						characterData.setMaxHealth(playerInfo.getMaxHealthNum());
					}
				}
				if (playerInfo.getMaxDamageNumCharacter() == characherId) {
					if (playerInfo.getMaxDamageNum() > maxDamage) {
						characterData.setMaxDamage(playerInfo.getMaxDamageNum());
					}
				}
				if (playerInfo.getMaxLiveTimeCharacter() == characherId) {
					if (playerInfo.getMaxLiveTime() > maxAliveTime) {
						characterData.setMaxAliveTime(playerInfo.getMaxLiveTime());
					}
				}
				if (playerInfo.getId() == stageClearInfo.getMvpId() && stageClearInfo.getMvpCharacterId() == characherId) {
					characterData.setMvpNum(maxVipNum + 1);
				}
				updateCharacterDataService(characterData, player.getId());
				if (ConfigurationUtil.SWITCH_XUNLEI_LOG.getIsOn()) {
					ServiceLocator.nosqlService.addXunleiLog("4.2" + Constants.XUNLEI_LOG_DELIMITER + levelInfo.getDisplayNameCN() + Constants.XUNLEI_LOG_DELIMITER
							+ levelInfo.getType() + Constants.XUNLEI_LOG_DELIMITER + player.getUserName() + Constants.XUNLEI_LOG_DELIMITER + player.getName()
							+ Constants.XUNLEI_LOG_DELIMITER + player.getRank() + Constants.XUNLEI_LOG_DELIMITER + playerInfo.getKill() + Constants.XUNLEI_LOG_DELIMITER
							+ playerInfo.getDead() + Constants.XUNLEI_LOG_DELIMITER + characterData.getCharacterId() + Constants.XUNLEI_LOG_DELIMITER + ci.getUsedCount()
							+ Constants.XUNLEI_LOG_DELIMITER + ci.getKill() + Constants.XUNLEI_LOG_DELIMITER + ci.getDead() + Constants.XUNLEI_LOG_DELIMITER
							+ CommonUtil.simpleDateFormat.format(new Date()));
				}
			} else {
				log.debug("ssss 222 character data happened some erro in stageclear" + characherId + "  " + playerInfo.getId());
			}
			endTime = System.currentTimeMillis();
			log.debug("6.5 use " + (endTime - startTime) + " ms");
			startTime = endTime;
			log.debug("ssss 333     "+ci.getCharacterId() + "  " + ci.getUsedCount() + " " + useTotal+"  "+ playerInfo.getId());
		}
		playerInfo.setUseTotal(useTotal);
		return boostKillNum;
	}

	/**
	 * update the date if the game is over. will be invoked in
	 * stageClear/stageQuit
	 * 
	 * @param stageClearInfo
	 * @throws Exception
	 */
	// this method will change the value of parameter 'playerInfos'

	public void updatePlayerStageClear(final GeneralStageClearInfo stageClearInfo) throws Exception {
		TimeTrack timeTrack = new TimeTrack(ServiceLocator.stageClearLog);
		int gameTpye = stageClearInfo.getType();

		GAMETYPE gameType = Constants.GAMETYPE.getTypeByValue(gameTpye);
		if (gameType == null) {
			log.error("unknown gameType when processing the stage clear, type :" + stageClearInfo.getType());
			return;
		}
		/** init variable * */
		final List<Player> players = new ArrayList<Player>();
		final String stageStr = CommonUtil.simpleDateFormat.format(new Date());

		Date gameStart = stageClearInfo.getGameStart();// game start time
		
		// 地图活动
		float expMapActivityAdd = 1;
		float gpMapActivityAdd = 1;
		LevelInfo levelInfo = sysLevelDao.getLevelInfoById(stageClearInfo.getLevelId());

		Date activityEnd = levelInfo.getActivityEnd();
		Date activityStart = levelInfo.getActivityStart();

		if (null != activityEnd && null != activityStart && activityEnd.after(gameStart) && activityStart.before(gameStart)) {// game start time between activity time
			expMapActivityAdd = (levelInfo.getExpAdd() > 1 ? levelInfo.getExpAdd() : 1);
			gpMapActivityAdd = (levelInfo.getGpAdd() > 1 ? levelInfo.getGpAdd() : 1);
		}
		
		timeTrack.debug("2.0 use ");

		List<StageClearPlayerInfo>[] stateClearPlayerInfo = stageClearInfo.getStageClearPlayerInfo();
		int gameSize = stateClearPlayerInfo[0].size() + stateClearPlayerInfo[1].size();

		double punish = 1;
		if (stageClearInfo.getType() != 3 && gameSize < Constants.numParam[gameTpye][1]) {
			punish = gameSize / Constants.numParam[gameTpye][1];
		}
		
		int offset = getService.getOnlineActivityOffset();
		stageClearInfo.setOnlineActivityOffset(offset);
		
		timeTrack.debug("3.0 use ");
		/** iterator */
		int iterator = 0;
		while (iterator < 2) {
			for (final StageClearPlayerInfo playerInfo : stateClearPlayerInfo[iterator]) {
				// 'SimplePlayer' only have id and name, used for recent player
				Player simplePlayer = new Player();
				simplePlayer.setId(playerInfo.getId());
				simplePlayer.setName(playerInfo.getName());
				players.add(simplePlayer);
				int playerId = playerInfo.getId();
				Player player = getService.getPlayerById(playerId);
				
				playerInfo.setBuffList(getService.filterBuffList(player));
				playerInfo.setInternetCafe(player.getInternetCafe());
				
//				int offset=updatePlayerActivity(Constants.ACTION_ACTIVITY.ONLINE_ACTIVITY.getValue(), player.getId(), null, 0, 0, null, null);
				int isTeamAdd=updatePlayerActivity(Constants.ACTION_ACTIVITY.TEAM_BATTLE_ADD.getValue(), player.getId(), null, 0, 0, null, null);
				// team buff exp
				PlayerItem Team_Buff_Exp_Add_Buff = getService.getPlayerBuff(player, TeamConstants.Team_Buff_Exp_Add_BuffId);
				PlayerItem Team_Buff_Gp_Add_Buff = getService.getPlayerBuff(player, TeamConstants.Team_Buff_Gp_Add_BuffId);
				//战队相关信息处理
				this.dealTeamExp(stageClearInfo, playerInfo,isTeamAdd);

				/** calculate exp and gp */
				float getExp = 0;
				float getGP = 0;
				int getScore = 0;

				int gameGetExp = 0;
				int gameGetGp = 0;

				int gameGetChance = 0;
				int vipGetChance = 0;
				int netBarGetChance = 0;
				int activityGetChance = 0;
				
				int teamBuffAddExp =0;
				int teamBuffAddGp = 0;
				
				int addedVipExp=0;
				
				if (playerInfo.getOnlineMinutes() < 300) {// if more than 5  hours,
					int baseScore = playerInfo.getScore();
					ServiceLocator.stageClearLog.debug("clear " + playerInfo.getName() + " get baseScore" + baseScore);
					int score = (int) (Constants.numParam[gameTpye][8] * (((playerInfo.getEnd().getTime() - playerInfo.getStart().getTime()) / (Constants.numParam[gameTpye][7] * 1000))));
					if (baseScore > score) {
						baseScore = score;
					}
					ServiceLocator.stageClearLog.debug("clear " + playerInfo.getName() + " get baseScore" + baseScore);
					double baseExp = (Math.sqrt(baseScore) * Constants.numParam[gameTpye][2]);
					double baseGp = (Math.sqrt(baseScore) * Constants.numParam[gameTpye][6]);
					int winExp = playerInfo.getIsWiner() == 1 ? (int) Constants.numParam[gameTpye][3] : (int) Constants.numParam[gameTpye][4];
					int winGp = playerInfo.getIsWiner() == 1 ? (int) Constants.numParam[gameTpye][3] : (int) Constants.numParam[gameTpye][4];

					int mvpExp = (stageClearInfo.getMvpId() == playerInfo.getId()) ? (int) Constants.numParam[gameTpye][5] : 0;
					if (stageClearInfo.isClear()) {// TODO delete all is clear
						double finalExtraExp = player.getExpIncrement();
						double finalExtraGp = player.getGpIncrement();
						if (player.getIsVip() >= 1) {
						
							finalExtraExp += (double)Constants.VIP_EXTRA_EXP_PERCENT[player.getIsVip()-1] / 100;
							finalExtraGp += (double)Constants.VIP_EXTRA_GP_PERCENT[player.getIsVip()-1] / 100;
							
						}
						// add activity gp and exp
						int expOffset = 1;
						int gpOffset = 1;
						if (offset > 1) {
							expOffset = gpOffset = offset;
						}
						getScore = baseScore;
						double extraExp = (playerInfo.getEnd().getTime() - playerInfo.getStart().getTime()) / (1000 * Constants.numParam[gameTpye][9]);
						double extraGp = (playerInfo.getEnd().getTime() - playerInfo.getStart().getTime()) / (1000 * Constants.numParam[gameTpye][10]);
						gameGetExp = (int) (punish * (extraExp + baseExp + winExp + mvpExp));
						gameGetGp = (int) (punish * (extraGp + baseGp + winGp));
						getExp = (int) (punish * (extraExp + baseExp + winExp + mvpExp) * (1 + player.getInternetCafe() * 0.1 + finalExtraExp) * expOffset * expMapActivityAdd);
						getGP = (int) (punish * (extraGp + baseGp + winGp) * (1 + finalExtraGp) * gpOffset * gpMapActivityAdd);
					}
					if(null != Team_Buff_Exp_Add_Buff){
						try {
							teamBuffAddExp = Integer.parseInt(Team_Buff_Exp_Add_Buff.getSysItem().getIValue());
							getExp += getExp/100*teamBuffAddExp;
						} catch (Exception e) {
							log.error("TeamBuff AddExp ParseInt Error!",e);
						}
					}
					if(null != Team_Buff_Gp_Add_Buff){
						try {
							teamBuffAddGp = Integer.parseInt(Team_Buff_Gp_Add_Buff.getSysItem().getIValue());
							getGP += getGP/100*teamBuffAddGp;
						} catch (Exception e) {
							log.error("TeamBuff AddGp ParseInt Error!",e);
						}
					}
					if (playerInfo.getOnlineMinutes() >= 180) {// 3 hours, gain
																// 50% exp
						getExp *= 0.5f;
						getGP *= 0.5f;
						getScore *= 0.5f;
						gameGetExp *= 0.5f;
						gameGetGp *= 0.5f;
					}
				}
				long fightTime = playerInfo.getEnd().getTime()-playerInfo.getStart().getTime();
				int vipExtraGpPercent = 0;
				int vipExtraExpPercent = 0;
				if (player.getIsVip() >=1) {
					vipExtraGpPercent = Constants.VIP_EXTRA_GP_PERCENT[player.getIsVip()-1];
					vipExtraExpPercent = Constants.VIP_EXTRA_EXP_PERCENT[player.getIsVip()-1];
					vipGetChance = (int)Math.ceil(player.getIsVip()/2.0);
				}
				int netbarExtraGpPercent = 0;
				int netbarExtraExpPercent = 0;
				if (player.getInternetCafe() != 0) {
					netbarExtraGpPercent = Constants.netbarExtraGpPercent[player.getInternetCafe() - 1];
					netbarExtraExpPercent = Constants.netbarExtraExpPercent[player.getInternetCafe() - 1];
					netBarGetChance = 1;
				}
				if(fightTime <3*60*1000){//小于3分钟，VIP和网吧不加翻牌机会 
					vipGetChance =0;
					netBarGetChance = 0;
				}
				double c = getScore/Constants.gameTypeNum[gameTpye];
				if(c<250){
					gameGetChance=0;
				}else if(c>=250&&c<1000){
					gameGetChance=1;
				}else if(c>=1000&&c<4000){
					gameGetChance=2;
				}else if(c>=4000){
					gameGetChance=3;
				}
				int mapActivityExtraGpPercent = (int) ((gpMapActivityAdd*offset - 1) * 100);
				int mapActivityExtraExpPercent = (int) ((expMapActivityAdd*offset - 1) * 100);
				timeTrack.debug("3.9 use ");
				ServiceLocator.stageClearLog.debug("GP:" +playerId + "\t"+ (int) getGP + ":" + gameGetGp + ":" + vipExtraGpPercent + ":" + netbarExtraGpPercent + ":" + mapActivityExtraGpPercent);
				ServiceLocator.stageClearLog.debug("EXP:" + playerId + "\t"+ (int) getExp + ":" + gameGetExp + ":" + vipExtraExpPercent + ":" + netbarExtraExpPercent + ":" + mapActivityExtraExpPercent);
				ServiceLocator.stageClearLog.debug("CHANCE:"+playerId+"\t" + gameGetChance + ":" + vipGetChance + ":" + netBarGetChance + ":" + activityGetChance);
				//玩家过关结算经验，Gpoint,翻牌机会收益==========================================
				GetExps playerGetExps = playerInfo.new GetExps(gameGetExp,vipExtraExpPercent,netbarExtraExpPercent,mapActivityExtraGpPercent,(int)(player.getExpIncrement()*100),teamBuffAddExp);
				GetGps playerGetGps = playerInfo.new GetGps(gameGetGp,vipExtraGpPercent,netbarExtraGpPercent,mapActivityExtraGpPercent,(int)(player.getGpIncrement()*100),teamBuffAddGp);
				GetChances playerGetChances = playerInfo.new GetChances(gameGetChance,vipGetChance,netBarGetChance,activityGetChance);
				playerInfo.setGetExps(playerGetExps);
				playerInfo.setGetGps(playerGetGps);
				playerInfo.setGetChances(playerGetChances);
				//==========================================================================
				//玩家翻拍次数存入mcc
				int randomBrandsLevel = 1;
				int openBrandsNum = nosqlService.getOpenBrandsNum(playerId ,new Date());
				timeTrack.debug("3.9.1 use ");

				if(openBrandsNum>20&&openBrandsNum<=40){
					randomBrandsLevel = 2;
				}else if(openBrandsNum>40){
					randomBrandsLevel = 3;
				}
				List<OnlineAward> commonBrandList = getService.getOnlineAwardByTypeAndLevel(Constants.ONLINE_AWARD_TYPES.STAGE_CLEAR.getValue(), 0);//公有样本
				List<OnlineAward> allBrandList = getService.getOnlineAwardByTypeAndLevel(Constants.ONLINE_AWARD_TYPES.STAGE_CLEAR.getValue(),randomBrandsLevel);
				allBrandList.addAll(commonBrandList);
				List<OnlineAward> brandList = null;
				timeTrack.debug("3.95 use");
				if(gameTpye==Constants.GAMETYPE.BOSS2.getValue()){//Boss2牌子奖励
					playerGetChances.setGameGet(playerInfo.getPassLevel());
					playerGetChances.setVipGet(0);
					playerGetChances.setActivityGet(0);
					playerGetChances.setNetBarGet(0);
					List<OnlineAward> boss2Awards = getService.getOnlineAwardByTypeAndLevel(Constants.ONLINE_AWARD_TYPES.STAGE_CLEAR.getValue(), 4);//level 4 表示boss2特有牌子
					allBrandList.addAll(boss2Awards);
					brandList = getService.randomOnlineAwardFromList(playerGetChances.getTotalGet(), allBrandList);
					//TODO  to improve 
					for(OnlineAward oa : brandList){
						SysItem si = getService.getSysItemByItemId(oa.getItemId());
						if(si!=null){
							createService.awardToPlayer(player,si,new Payment(oa.getUnit(),oa.getUnitType()),null,Constants.BOOLEAN_YES);
//							createService.sendItem(si, player, new Payment(oa.getUnit(),oa.getUnitType()), "N", "Y", "N");
							oa.setSysItem(si);
							ServiceLocator.stageClearLog.info("Boss2/Brand:\t"+playerId+"\t"+si.getDisplayNameCN()+"\t"+oa.getUnit()+"\t"+oa.getUnitType());
						}else{
							log.warn("UpdateService/SysItemNull:\t"+playerId+"\t"+ oa.getId()+"\t"+oa.getItemId());
						}
					}
					Collections.sort(brandList, new Comparator<OnlineAward>() {
						@Override
						public int compare(OnlineAward arg0, OnlineAward arg1) {
							return arg0.getSysItem().getRareLevel()-arg0.getSysItem().getRareLevel();
						}
					});
					playerInfo.setBrands(brandList);
					timeTrack.debug("3.96 use");
				}else{
					brandList = getService.randomOnlineAwardFromList(Constants.STAGE_BRAND_OPEN_NUM, allBrandList);
					List<Integer> brdIdList = new ArrayList<Integer>();
					for(OnlineAward oa : brandList){
						brdIdList.add(oa.getId());
					}
					String brdListKey = Constants.STAGE_BRAND_LIST_PREFIX + playerId;
					mcc.set(brdListKey,Constants.BRAND_CACHE_ITEM_TIMEOUT, brdIdList, Constants.CACHE_TIMEOUT);
					String brandOpenChancekey = Constants.STAGE_OPEN_CHANCE_PREFIX + playerId; 
					mcc.set(brandOpenChancekey, Constants.BRAND_CACHE_ITEM_TIMEOUT, playerGetChances.getTotalGet());
					timeTrack.debug("3.97 use");
				}
				timeTrack.debug("4.0 use ");
				
				
				
				playerInfo.setScore(getScore);
				playerInfo.setCurrExp(player.getExp());
				playerInfo.setCurrGp(player.getGPoint());
				playerInfo.setExp((int) getExp);
				playerInfo.setGp((int) getGP);
				playerInfo.setCurrRank(player.getRank());// rank and exp in
				playerInfo.setRank(player.getRank());
				playerInfo.setHeadGif(player.getIcon());

				// set player
				player.setGPoint((int) (player.getGPoint() + getGP));
				player.setExp((int) (player.getExp() + getExp));
				player.setGScore((int) (player.getGScore() + getScore));


				Rank newRank = getService.getRankByExp(player.getExp());
				int newLevel = newRank.getId();
				// just escape the bug
				if (newLevel != player.getRank() && newLevel < player.getRank()) {
					player.setRank(newLevel);
				}
				timeTrack.debug("4.1 use " );

				/* about level up thing */
				if (newLevel > player.getRank()) {
					{
						ServiceLocator.updateService.updatePlayerGrowthMission(player, GrowthMissionType.NEW_AWARD_1);
						ServiceLocator.updateService.updatePlayerGrowthMission(player, GrowthMissionType.NEW_AWARD_2);
						ServiceLocator.updateService.updatePlayerGrowthMission(player, GrowthMissionType.NEW_AWARD_3);
						ServiceLocator.updateService.updatePlayerGrowthMission(player, GrowthMissionType.NEW_AWARD_4);
						ServiceLocator.updateService.updatePlayerGrowthMission(player, GrowthMissionType.NEW_AWARD_5);
						ServiceLocator.updateService.updatePlayerGrowthMission(player, GrowthMissionType.NEW_AWARD_6);
						ServiceLocator.updateService.updatePlayerGrowthMission(player, GrowthMissionType.NEW_AWARD_7);
						ServiceLocator.updateService.updatePlayerGrowthMission(player, GrowthMissionType.NEW_AWARD_8);
						// 玩家升级 派发新任务 给玩家
						sendGrowthMission(player, newLevel, player.getRank());
					}
					if (ConfigurationUtil.SWITCH_XUNLEI_LOG.getIsOn()) {
						ServiceLocator.nosqlService.addXunleiLog("4.5" + Constants.XUNLEI_LOG_DELIMITER + levelInfo.getDisplayNameCN() + Constants.XUNLEI_LOG_DELIMITER
								+ levelInfo.getType() + Constants.XUNLEI_LOG_DELIMITER + player.getUserName() + Constants.XUNLEI_LOG_DELIMITER + player.getName()
								+ Constants.XUNLEI_LOG_DELIMITER + CommonUtil.simpleDateFormat.format(new Date()) + Constants.XUNLEI_LOG_DELIMITER + player.getRank()
								+ Constants.XUNLEI_LOG_DELIMITER + newLevel);
					}
					updatePlayerActivity(Constants.ACTION_ACTIVITY.LEVEL_ACTIVITY.getValue(), player.getId(), null, player.getRank(), newLevel, null, null);
					{
						player.setRank(newLevel);
						playerInfo.setRank(newLevel);
					}
					{
						// level achievement
						List<PlayerAchievement> paList = getService.getPlayerAchievementVisible(player.getId());
						List<PlayerAchievement> finishedList = new ArrayList<PlayerAchievement>();
						for (PlayerAchievement pa : paList) {
							if (pa.getStatus() != Constants.NUM_ZERO) {
								continue;
							}
							
							SysAchievement sa = pa.getAchievement();
							int action = sa.getAction();
							int numberThisRound = 0;
							if (Constants.ACTION.LEVELUP.getValue() == action) {
								if (player.getRank() >= pa.getAchievement().getNumber()) {// 达到指定等级
									numberThisRound = player.getRank();
								}
								updatePlayerAchievement(pa, numberThisRound, player, finishedList, stageClearInfo, playerInfo, sa);
								// 通知客户端完成的成就
								if (!finishedList.isEmpty()) {
									ServiceLocator.soClient.sendAchievementCompleted(finishedList,player.getId(), player.getName());
								}
								break;
							}
						}
					}
					soClient.updateCharacterInfo(player, player.getTeam(), player.getRank());
					nosqlService.publishEvent(new LevelUpEvent(DateUtil.getCurrentTimeStr(), getService.getRankByExp(player.getExp()), player.getId(), player.getName()));
					if (player.getRank().equals(Constants.JOIN_TEAM_LIMIT)) {
						soClient.messageCMD(player.getName(), CommonMsg.REFRESH_ABLETOJOINTEAM);
					}
					timeTrack.debug("4.15 use " );
				}
				timeTrack.debug("4.2 use " );

				boolean isFirstKill = stageClearInfo.isClear() && gameTpye != 5 && (playerInfo.getId() == stageClearInfo.getFirstKill());
				boolean isFirstDead = stageClearInfo.isClear() && gameTpye != 5 && (playerInfo.getId() == stageClearInfo.getFirstDead());
				if (isFirstKill) {
					playerInfo.setIsFirstKill(1);
				}

				if (isFirstDead) {
					playerInfo.setIsFirstDead(1);
				}
				/** update battle fields for player and team */
				setPlayerInfoStageClear(player, playerInfo, gameType, stageClearInfo.isClear());
				timeTrack.debug("5.0 use " );

				int boostKillNum = updateCharacterDataWhileStage(stageClearInfo, playerInfo, player, levelInfo);
				timeTrack.debug("5.1 use " );
				updatePlayerItemDurablewhileStage(playerInfo, playerInfo.getUseTotal(), punish);//TODO XXX
				timeTrack.debug("5.2 use " );
				int getGps = (int) getGP;
				int getExps = (int) getGP;
				nosqlService.publishEvent(new GetGpEvent(getGps, System.currentTimeMillis() / 1000, player.getId(), player.getName()));
				nosqlService.publishEvent(new GetExpEvent(getExps, System.currentTimeMillis() / 1000, player.getId(), player.getName()));
				timeTrack.debug("6.0 use " );

				// 三人局以上的新手任务
				if (gameSize >= 3) {
					boolean flag_next = true;
					if (playerInfo.getKill() >= 1) {
						flag_next = !updatePlayerGrowthMission(player, GrowthMissionType.getGameKill(playerInfo.getKill()));
					}
					if (flag_next && playerInfo.getAssistNum() >= 1) {
						flag_next = !updatePlayerGrowthMission(player, GrowthMissionType.getGameAssist(playerInfo.getAssistNum()));
					}
					if (flag_next && playerInfo.getIsWiner() == 1) {
						flag_next = !updatePlayerGrowthMission(player, GrowthMissionType.getWinLimitGame());
					}
					if (flag_next) {
						updatePlayerGrowthMission(player, GrowthMissionType.getCompleteLimitGame());
					}
				}
				timeTrack.debug("7.0 use " );

				// 六人局以上更新成就、任务
				if (gameSize >= 3) {
					// 判断并更新成就信息
					updatePlayerAchievementInStageClear(player, playerInfo, stageClearInfo);
					timeTrack.debug("8.0 use " );

					updatePlayerMissionInStageClear(player, playerInfo, stageClearInfo, boostKillNum);
					timeTrack.debug("9.0 use " );
				}
				// 更新活动信息
				
				updatePlayerActivity(Constants.ACTION_ACTIVITY.KILL_ACTIVITY.getValue(), playerInfo.getId(), null, playerInfo.getKill(), 0, null, null);
				timeTrack.debug("9.2 use " );
				
				updatePlayerActivity(Constants.ACTION_ACTIVITY.ACHIEVEMENT_ACTIVITY.getValue(), playerInfo.getId(), null, playerInfo.getKill(), 0, playerInfo, stageClearInfo);
				if (ConfigurationUtil.SWITCH_XUNLEI_LOG.getIsOn()) {
					addXuleiLogWhileSteageClear(player, stageClearInfo, playerInfo.getExp(), playerInfo.getGp(), stageStr, levelInfo, (int) playerInfo.getScore(),playerInfo);
				}
				timeTrack.debug("10.0 use ");

				updatePlayerInfo(player);
				updatePlayerTop(player);
				//必须等上面的player更新完 ,才能再次更新
				if(player.getIsVip()>=1){
					//vip 获得升级经验
					if(fightTime >3*60*1000){//小于3分钟，无vip经验
						addedVipExp=updateVipUpExp(Constants.VIP_EARN_EXP_METHODS.STAGECLEAR.getValue(), playerId);	
					}
					
				}
				playerInfo.setIsVip(player.getIsVip());
				playerInfo.setCurrVipExp(player.getVipExp());
				playerInfo.setVipExp(addedVipExp);
				
				timeTrack.debug("10.5 use ");
				/** clear cache */
				try {
					for (int i = 1; i <= Constants.MAX_CHARACTER_SIZE; i++) {
						mcc.deleteWithNoReply(CacheUtil.oWeaponPack(playerInfo.getId(), 1, i));
						mcc.deleteWithNoReply(CacheUtil.oCostumePack(playerInfo.getId(), 1, i));
					}
					mcc.deleteWithNoReply(CacheUtil.oPlayer(player.getId()));
					mcc.deleteWithNoReply(CacheUtil.sPlayer(player.getId()));
					for (SysCharacter sc : getService.getDefaultSysCharacterList()) {
						mcc.deleteWithNoReply(CacheUtil.oStorage1(player.getId(), Constants.DEFAULT_WEAPON_TYPE, sc.getId(), 0));
						mcc.deleteWithNoReply(CacheUtil.oStorage1(player.getId(), Constants.DEFAULT_WEAPON_TYPE, sc.getId(), 1));
						mcc.deleteWithNoReply(CacheUtil.oStorage1(player.getId(), Constants.DEFAULT_WEAPON_TYPE, sc.getId(), 2));
						mcc.deleteWithNoReply(CacheUtil.oStorage1(player.getId(), Constants.DEFAULT_WEAPON_TYPE, sc.getId(), 3));
						mcc.deleteWithNoReply(CacheUtil.oStorage1(player.getId(), Constants.DEFAULT_WEAPON_TYPE, sc.getId(), 4));
					}
					mcc.deleteWithNoReply(CacheUtil.oCharacterList(player.getId()));
				} catch (Exception e) {
					// do nothing, just catch all exception during set into  cache for not roll back
					ServiceLocator.stageClearLog.warn("error happend during remove player cache in stageClear/stageQuit, cid:" + player.getId());
				}
				
				timeTrack.debug("11.0 use "  );
			}
			iterator++;
		}
		if (ConfigurationUtil.SWITCH_XUNLEI_LOG.getIsOn()) {
			Player host = getService.getSimplePlayerById(stageClearInfo.getHostId());
			ServiceLocator.nosqlService.addXunleiLog("4.4" + Constants.XUNLEI_LOG_DELIMITER + host.getUserName() + Constants.XUNLEI_LOG_DELIMITER + levelInfo.getDisplayNameCN()
					+ Constants.XUNLEI_LOG_DELIMITER + stageClearInfo.getType() + Constants.XUNLEI_LOG_DELIMITER
					+ CommonUtil.simpleDateFormat.format(stageClearInfo.getGameStart()) + Constants.XUNLEI_LOG_DELIMITER
					+ CommonUtil.simpleDateFormat.format(stageClearInfo.getGameEnd()) + Constants.XUNLEI_LOG_DELIMITER + stageClearInfo.getWinner());
		}
		
		
		// 记到analyser server
		String message = String.format(
				"%s\t%s\t%s\t%s\t%s", levelInfo.getName(),levelInfo.getDisplayNameCN(), stageClearInfo.getType(), 
				 CommonUtil.simpleDateFormat.format(stageClearInfo.getGameStart()),
				 CommonUtil.simpleDateFormat.format(stageClearInfo.getGameEnd()));
		transferDataToDc.addLog("bjStageClear_LTTime", message);		
		
		log.debug("finsih calculate player date, channle id"
				+ stageClearInfo.getChannelId() + " rid "
				+ stageClearInfo.getRid());
		// 1表示是战队战,保存战绩
		final int teamaId = stageClearInfo.getTeamaId();
		final int teambId = stageClearInfo.getTeambId();
		if (stageClearInfo.getIsTeam() == 1) {
			Team teama = null;
			Team teamb = null;
			if (teamaId != 0) {
				teama = getService.getTeam(teamaId);
			}
			if (teambId != 0) {
				teamb = getService.getTeam(teambId);
			}
			if (teama != null) {
				TeamUtils.updateTeamWinTotal(teama.getId(), stageClearInfo
						.getWinner() == 0 ? 1 : 0);
				if (teamb != null) {
					TeamRecord teamRecorda = new TeamRecord();
					teamRecorda.setTeamId(teama.getId());
					teamRecorda.setScore(stageClearInfo.getTerroristScore());
					teamRecorda.setLevelId(stageClearInfo.getLevelId());
					teamRecorda.setBTeamId(teamb.getId());
					teamRecorda.setBScore(stageClearInfo.getPoliceScore());
					teamRecorda.setCreateTime(new Date().getTime());
					teamRecorda.setResult(stageClearInfo.getWinner()==0?1:0);
					//记录a战队此局游戏战果
					Map<Integer,TeamRecord> mapa=getService.getTeamRecordByTeamId(teama.getId());
					if(mapa.size()>=Constants.TEAM_RECORD_COUNT_MAX){
					    TeamService t=new TeamService();
						int smallestId=t.getSmallestTeamRecordId(mapa);
						getService.getTeamRecordDao().deleteTeamRecord(mapa.get(smallestId));
					}
					createService.createTeam(teamRecorda);
					
					TeamRecord teamRecordb=new TeamRecord();
					teamRecordb.setTeamId(teamb.getId());
					teamRecordb.setScore(stageClearInfo.getPoliceScore());
					teamRecordb.setLevelId(stageClearInfo.getLevelId());
					teamRecordb.setBTeamId(teama.getId());
					teamRecordb.setBScore(stageClearInfo.getTerroristScore());
					teamRecordb.setCreateTime(new Date().getTime());
					teamRecordb.setResult(stageClearInfo.getWinner());
					Map<Integer, TeamRecord> mapb= getService.getTeamRecordByTeamId(teamb.getId());
					if(mapb.size()>=Constants.TEAM_RECORD_COUNT_MAX){
						 TeamService t=new TeamService();
						 int smallestId=t.getSmallestTeamRecordId(mapb);
						 getService.getTeamRecordDao().deleteTeamRecord(mapb.get(smallestId));
					}
					createService.createTeam(teamRecordb);
					
					if (ConfigurationUtil.SWITCH_XUNLEI_LOG.getIsOn()) {
						ServiceLocator.nosqlService.addXunleiLog("4.6" + Constants.XUNLEI_LOG_DELIMITER + levelInfo.getDisplayNameCN() 
								+ Constants.XUNLEI_LOG_DELIMITER + stageClearInfo.getType() 
								+ Constants.XUNLEI_LOG_DELIMITER + teama.getId()
								+ Constants.XUNLEI_LOG_DELIMITER + teama.getName()
								+ Constants.XUNLEI_LOG_DELIMITER + stageClearInfo.getTerroristScore()
								+ Constants.XUNLEI_LOG_DELIMITER + teamb.getId()
								+ Constants.XUNLEI_LOG_DELIMITER + teamb.getName()
								+ Constants.XUNLEI_LOG_DELIMITER + stageClearInfo.getPoliceScore()
								+ Constants.XUNLEI_LOG_DELIMITER + (stageClearInfo.getWinner()==0?1:0));
					}
		    	}else{
		    		log.warn(teama.getName()+ExceptionMessage.TEAM_RECORD_NEED_AGAINST_WARN);
		    	}
		    }
		    if(teamb!=null){
				TeamUtils.updateTeamWinTotal(teamb.getId(),stageClearInfo.getWinner());
				if(teama==null){
					log.warn(teamb.getName()+ExceptionMessage.TEAM_RECORD_NEED_AGAINST_WARN);
				}
		    }
			    /*if(teamaId!=0&&teambId!=0){//
					    if(teama!=null&&teamb!=null){
							teamRecorda.setTeamId(teama.getId());
							teamRecorda.setScore(stageClearInfo.getTerroristScore());
							teamRecorda.setLevelId(stageClearInfo.getLevelId());
							teamRecorda.setBTeamId(teamb.getId());
							teamRecorda.setBScore(stageClearInfo.getPoliceScore());
							teamRecorda.setCreateTime(new Date().getTime());
							
							teamRecordb.setTeamId(teamb.getId());
							teamRecordb.setScore(stageClearInfo.getPoliceScore());
							teamRecordb.setLevelId(stageClearInfo.getLevelId());
							teamRecordb.setBTeamId(teama.getId());
							teamRecordb.setBScore(stageClearInfo.getTerroristScore());
							teamRecordb.setCreateTime(new Date().getTime());
							if(stageClearInfo.getWinner()==0){
								teama.setGameWin(teama.getGameWin()+1);
							    teama.setGameTotal(teama.getGameTotal()+1);
							    teamb.setGameTotal(teamb.getGameTotal()+1);
									  
								teamRecorda.setResult(1);
							    teamRecordb.setResult(0);
							}else{
								teama.setGameTotal(teama.getGameTotal()+1);
								teamb.setGameWin(teamb.getGameWin()+1);
								teamb.setGameTotal(teamb.getGameTotal()+1);
									
								teamRecorda.setResult(0);
							    teamRecordb.setResult(1);
						    }
							TeamUtils.updateTeamWinTotal(teama.getId(), teama);
							TeamUtils.updateTeamWinTotal(teamb.getId(), teamb);
							updateTeamTop(teama, Constants.TEAM_TOP_TYPE.BATTLERESULT.getValue());
							updateTeamTop(teamb, Constants.TEAM_TOP_TYPE.BATTLERESULT.getValue());
							//记录a战队此局游戏战果
							Map<Integer, com.pearl.o2o.pojo.TeamRecord> mapa=getService.getTeamRecordByTeamId(teama.getId());
							if(mapa.size()>=20){
							    TeamService t=new TeamService();
								int smallestId=t.getSmallestTeamRecordId(mapa);
								getService.getTeamRecordDao().deleteTeamRecord(mapa.get(smallestId));
								getService.getTeamRecordDao().createTeam(teamRecorda);
							}else{
								getService.getTeamRecordDao().createTeam(teamRecorda);
							}
							//记录b战队此局游戏战果
							Map<Integer, com.pearl.o2o.pojo.TeamRecord> mapb= getService.getTeamRecordByTeamId(teamb.getId());
							if(mapb.size()>=Constants.TEAM_RECORD_COUNT_MAX){
								 TeamService t=new TeamService();
								 int smallestId=t.getSmallestTeamRecordId(mapb);
								 getService.getTeamRecordDao().deleteTeamRecord(mapb.get(smallestId));
								 createService.createTeam(teamRecordb);
							}else{
								 getService.getTeamRecordDao().createTeam(teamRecordb);
							}
							if (ConfigurationUtil.SWITCH_XUNLEI_LOG.getIsOn()) {
								ServiceLocator.nosqlService.addXunleiLog("4.6" + Constants.XUNLEI_LOG_DELIMITER + levelInfo.getDisplayName() 
										+ Constants.XUNLEI_LOG_DELIMITER + stageClearInfo.getType() 
										+ Constants.XUNLEI_LOG_DELIMITER + teama.getId()
										+ Constants.XUNLEI_LOG_DELIMITER + teama.getName()
										+ Constants.XUNLEI_LOG_DELIMITER + stageClearInfo.getTerroristScore()
										+ Constants.XUNLEI_LOG_DELIMITER + teamb.getId()
										+ Constants.XUNLEI_LOG_DELIMITER + teamb.getName()
										+ Constants.XUNLEI_LOG_DELIMITER + stageClearInfo.getPoliceScore()
										+ Constants.XUNLEI_LOG_DELIMITER + (stageClearInfo.getWinner()==0?1:0));
							}
					    }else if(teama!=null){
							if(stageClearInfo.getWinner()==0){
								teama.setGameWin(teama.getGameWin()+1);
							    teama.setGameTotal(teama.getGameTotal()+1);
							}else{
								teama.setGameTotal(teama.getGameTotal()+1);
						    }
							updateTeamInfo(teama);
					    }else if(teamb!=null){
							if(stageClearInfo.getWinner()==0){
							    teamb.setGameTotal(teamb.getGameTotal()+1);
							}else{
								teamb.setGameWin(teamb.getGameWin()+1);
								teamb.setGameTotal(teamb.getGameTotal()+1);
						    }
							updateTeamInfo(teamb);
					    }
					    
					 }else if(teamaId!=0){
							if(stageClearInfo.getWinner()==0){
								teama.setGameWin(teama.getGameWin()+1);
							    teama.setGameTotal(teama.getGameTotal()+1);
							}else{
								teama.setGameTotal(teama.getGameTotal()+1);
						    }
							updateTeamInfo(teama);
							updateTeamTop(teama, Constants.TEAM_TOP_TYPE.BATTLERESULT.getValue());
							log.warn(teama.getName()+ExceptionMessage.TEAM_RECORD_NEED_AGAINST_WARN);
					 }else if(teambId!=0){
				    	   if(stageClearInfo.getWinner()==0){
							    teamb.setGameTotal(teamb.getGameTotal()+1);
							}else{
								teamb.setGameWin(teamb.getGameWin()+1);
								teamb.setGameTotal(teamb.getGameTotal()+1);
						    }
							updateTeamInfo(teamb);
							updateTeamTop(teamb, Constants.TEAM_TOP_TYPE.BATTLERESULT.getValue());
							log.warn(teamb.getName()+ExceptionMessage.TEAM_RECORD_NEED_AGAINST_WARN);
					 }*/
			    timeTrack.debug("12.0 use "  );	
		}
		
		final Runnable addBattleFriend = new Runnable() {
			@Override
			public void run() {
				try {
					for (Player p : players) {
						nosqlService.addRecentPlayers(players, p.getId(), p.getName());
					}
					TeamUtils.pushCMDToOnlineTeamPlayer(teamaId, Constants.TEAM_CMD_RECORD);
					TeamUtils.pushCMDToOnlineTeamPlayer(teambId, Constants.TEAM_CMD_RECORD);
				} catch (Exception e) {
					ServiceLocator.stageClearLog.warn("error happend during add battle friend stage clear exception is " , e);
				}
			}
		};
		ServiceLocator.asynTakService.submit(addBattleFriend);
	}
	
	/**
	 * 资源争夺战过关结算
	 * update the date if the game is over. will be invoked in
	 * stageClear/stageQuit
	 * 
	 * @param stageClearInfo
	 * @throws Exception
	 */
	// this method will change the value of parameter 'playerInfos'
	//team0 必定进攻方  team1必定防守方
	public void updatePlayerStageClearZYZDZ(final GeneralStageClearInfo stageClearInfo) throws Exception {
		int gameTpye = stageClearInfo.getType();

		GAMETYPE gameType = Constants.GAMETYPE.getTypeByValue(gameTpye);
		if (gameType != Constants.GAMETYPE.TEAMZYZDZ) {
			log.error("gameType is not zyzdz when processing the stage clear, type :" + stageClearInfo.getType());
			return;
		}
		final int hostId=stageClearInfo.getHostId();
		PlayerTeam hostPlayerTeam=getService.getPlayerTeamByPlayerId(hostId);
		
		int attTeamId=0;
		if(hostPlayerTeam!=null){
			attTeamId=hostPlayerTeam.getTeamId();
		}
		final Team attTeam=getService.getSimpleTeamById(attTeamId)== null ? new Team() : getService.getSimpleTeamById(attTeamId);
		//	stageClearInfo.setTeamaId(attTeamId);
		int defTeamId=0;	
		//获得防守方 teamID		
		TeamLevelInfo teamLevelInfo=getService.getTeamLevelInfo(stageClearInfo.getLevelId());
		if(teamLevelInfo!=null){
			defTeamId=teamLevelInfo.getTeamId();
		}
		final Team defTeam=getService.getSimpleTeamById(defTeamId);
		if(defTeam==null){
			log.warn("unknown def team for zyzdz stageClear!");
		}
		
		//处理 设施损耗
		List<TeamItem> mapItems=getService.getTeamItemsInMap(defTeamId);
		if(mapItems.size()>0){
			for(TeamItem ti:mapItems){
				if(ti.getDurable()>0){
					ti.setDurable((ti.getDurable()-1)>0 ? (ti.getDurable()-1):0);
				}
				if(ti.getBullet()>0&&ti.getSysItem().getSubType()==2){ //只有塔才扣子弹
					ti.setBullet((ti.getBullet()-1)>0 ? (ti.getBullet()-1) :0);
				}
				updateTeamItem(defTeamId, ti);
			}
		}
		
		final float damagePercent=stageClearInfo.getDamagePercent();   //损害度
		
		if(stageClearInfo.getTotalRes()==-1){//挑战模式
			ChallengeHillStatus chs=ServiceLocator.getService.getChallengeHillStatus(defTeamId);
			float totalRes=chs.getCanBeRob();
			
			float attGetRes=totalRes*damagePercent;  		//抢夺资源
			float defRemRes=totalRes*(1-damagePercent);		//剩余资源
			stageClearInfo.setWinnerRes(Math.round(attGetRes));
			stageClearInfo.setLoserRes(Math.round(defRemRes));
			
			int capGetRes=Math.round(ServiceLocator.getService.getChallangeFCCost(chs.getStones())*1.5f*8*damagePercent);				//队长获得 晶石
			int memGetRes=Math.round(ServiceLocator.getService.getChallangeFCCost(chs.getStones())/2*damagePercent * Constants.TeamSpaceConstants.PERSON_ORG_TO_RES_SCALE); //队员获得 原石
			
			chs.setStones(Math.round(chs.getStones()-attGetRes));
			createService.setMccChallengeHill(chs,defTeamId);
			updateZYZDZPlayerStageClear(stageClearInfo.getStageClearPlayerInfoTeam0(),stageClearInfo.getStageClearPlayerInfoTeam1(),
					hostId,capGetRes,memGetRes,0,attTeam.getTeamSpaceLevel(),defTeam.getTeamSpaceLevel(),1);
			
			updateTeamRes(attTeamId,attGetRes);
			updateTeamRes(defTeamId,defRemRes);
			
			//广播结果
			final Runnable boardResult = new Runnable() {
				@Override
				public void run() {
					try {
						if(damagePercent>0.5f){
							Player hostPlayer=getService.getPlayerById(hostId);
							if(hostPlayer!=null){
								soClient.updateBillBoardList( CommonUtil.messageFormatI18N(CommonMsg.ATK_WIN_CHALLENGE, new Object[]{GunProperty.GOLD + "@!" + attTeam.getName(),GunProperty.GOLD + "@!" + 
										hostPlayer.getName(), GunProperty.GOLD + "@!" + defTeam.getName()}));
							}
							
						}else{
							soClient.updateBillBoardList( CommonUtil.messageFormatI18N(CommonMsg.ATK_LOSE_CHALLENGE, new Object[]{GunProperty.GOLD + "@!" + defTeam.getName(),
										GunProperty.GOLD + "@!" + attTeam.getName()}));
						}
						
					} catch (Exception e) {
						ServiceLocator.zyzdzStageClearLog.warn("error happend during board result ! exception is " , e);
					}
				}
			};
			ServiceLocator.asynTakService.submit(boardResult);
			
		}else{
			//处理资源相关
			int teamNowremainRes=0; //当前战队 还剩余的原石数
			if(defTeam!=null){
				HashMap<String, Integer> teamResHashMap=defTeam.getLatestTeamRes();
				teamNowremainRes=teamResHashMap.get(Team.ORG_RES);
			}
			final float totalRes=stageClearInfo.getTotalRes()<teamNowremainRes ? stageClearInfo.getTotalRes() : teamNowremainRes;		//本场比赛 能抢夺的资源总数 
			
			float attRealRes=totalRes*damagePercent;	//本场比赛 抢的资源
			float attTeamRealRes=attRealRes*Constants.TEAM_RES_PRECENT_AT_END_OF_ZYZDZ;
			float attPersonalRealRes=attRealRes*Constants.PERSONAL_RES_PRECENT_AT_END_OF_ZYZDZ;
			
			float defRealRes=totalRes*(1-damagePercent);  //未抢到的资源
			float defTeamRealRes=defRealRes*Constants.TEAM_RES_PRECENT_AT_END_OF_ZYZDZ;
			float defPersonalRealRes=defRealRes*Constants.PERSONAL_RES_PRECENT_AT_END_OF_ZYZDZ;
			
			//进攻方队员数
//			int attTeamPCount=(stageClearInfo.getTeamaId()==hostPlayerTeam.getTeamId()) ? stageClearInfo.getStageClearPlayerInfoTeam1().size() 
//					:stageClearInfo.getStageClearPlayerInfoTeam2().size();
			//防守方队员数
			int defTeamPCount=stageClearInfo.getStageClearPlayerInfoTeam1().size();
			
			int winnerTeamId=stageClearInfo.getWinner()==0 ? attTeamId : defTeamId;

			int attCaptainRes;
			int attMemberRes;
			int defMemberRes;
			
			//进攻方胜利
			if(attTeamId==winnerTeamId){
				//进攻 队长获得资源
				 attCaptainRes=Math.round(attPersonalRealRes*Constants.CAPTAIN_PERCENT_AT_END_OF_ZYZDZ*(1+Constants.ATT_WINNER_ADDED_PER_AT_END_OF_ZYZDZ));
				//进攻 队员获得资源
				 attMemberRes=Math.round(attPersonalRealRes*(1-Constants.CAPTAIN_PERCENT_AT_END_OF_ZYZDZ)/(Constants.ZYZDZ_ATT_TEAM_MAX_PERSON-1)
						* (1+Constants.ATT_WINNER_ADDED_PER_AT_END_OF_ZYZDZ));
		 
				//防守 队员获得资源
				 defMemberRes=Math.round(defPersonalRealRes*Constants.DEF_PERCENT_AT_END_OF_ZYZDZ/Constants.ZYZDZ_DEF_TEAM_MAX_PERSON);
				 //防守方 少于最大人数时，多的资源 返还战队
				 int remainRes=Math.round(defPersonalRealRes-defMemberRes*defTeamPCount);
				 
				 int needattTeamCost=Math.round(attMemberRes*(Constants.ZYZDZ_ATT_TEAM_MAX_PERSON-1)+attCaptainRes-attPersonalRealRes);
				 if(needattTeamCost<0){
					 needattTeamCost=0;
				 }
				 
				 updateTeamRobResAfterGame(defTeamId, attTeamId,totalRes-(defTeamRealRes+remainRes), attTeamRealRes-needattTeamCost);

				 stageClearInfo.setWinnerRes((int)attTeamRealRes);
				 stageClearInfo.setLoserRes((int)(defTeamRealRes+remainRes));
				 
				 //进攻方胜利 ，清空失败次数
				 String loseKey = Constants.TeamSpaceConstants.getTeamBattleFieldLoseKey(attTeamId);
				 mcc.delete(loseKey);
			}else{
				//进攻 队长获得资源
				 attCaptainRes=Math.round(attPersonalRealRes*Constants.CAPTAIN_PERCENT_AT_END_OF_ZYZDZ);
				//进攻 队员获得资源
				 attMemberRes=Math.round(attPersonalRealRes*(1-Constants.CAPTAIN_PERCENT_AT_END_OF_ZYZDZ)/(Constants.ZYZDZ_ATT_TEAM_MAX_PERSON-1));
				
				//防守 队员获得资源
				 defMemberRes=Math.round(defPersonalRealRes*Constants.DEF_PERCENT_AT_END_OF_ZYZDZ/Constants.ZYZDZ_DEF_TEAM_MAX_PERSON*Constants.DEF_WINNER_ADDED_PER_AT_END_OF_ZYZDZ);			
				 //防守方 少于最大人数时，多的资源 返还战队
				 int remainRes=Math.round(defPersonalRealRes-defMemberRes*defTeamPCount);
				 
				 updateTeamRobResAfterGame(defTeamId, attTeamId, totalRes-(defTeamRealRes+remainRes), attTeamRealRes);
				 stageClearInfo.setWinnerRes((int)attTeamRealRes);
				 stageClearInfo.setLoserRes((int)(defTeamRealRes+remainRes)); 
				 
				 //进攻方失败 ，记录失败次数
				 String loseKey = Constants.TeamSpaceConstants.getTeamBattleFieldLoseKey(attTeamId);
				 Integer loseCountInCache = mcc.get(loseKey);
				 int Newlostcount= loseCountInCache==null? 1: (loseCountInCache+1);
				 mcc.set(loseKey,  Constants.CACHE_TIMEOUT_HALF_DAY,Newlostcount,Constants.CACHE_TIMEOUT);
			}
			
//			if(attTeamId==stageClearInfo.getTeamaId()){
			
			attCaptainRes*=Constants.TeamSpaceConstants.PERSON_ORG_TO_RES_SCALE;
			attMemberRes*=Constants.TeamSpaceConstants.PERSON_ORG_TO_RES_SCALE;
			defMemberRes*=Constants.TeamSpaceConstants.PERSON_ORG_TO_RES_SCALE;
			updateZYZDZPlayerStageClear(stageClearInfo.getStageClearPlayerInfoTeam0(),stageClearInfo.getStageClearPlayerInfoTeam1(),
						hostId,attCaptainRes,attMemberRes,defMemberRes,attTeam.getTeamSpaceLevel(),defTeam.getTeamSpaceLevel(),0);
//			}else{
//				updateZYZDZPlayerStageClear(stageClearInfo.getStageClearPlayerInfoTeam1(),stageClearInfo.getStageClearPlayerInfoTeam0(),
//						hostId,attCaptainRes,attMemberRes,defMemberRes);
//			}
		}
				
		List<PlayerItem> team0Items=getTeamCostItem4ZYZDZ(stageClearInfo.getStageClearPlayerInfoTeam0());
		List<PlayerItem> team1Items=getTeamCostItem4ZYZDZ(stageClearInfo.getStageClearPlayerInfoTeam1());
		stageClearInfo.setTeamaCostItems(team0Items);
		stageClearInfo.setTeambCostItems(team1Items);
		
	}
	/**
	 * 
	 * @param attInfos
	 * @param defInfos
	 * @param hostId
	 * @param attCaptainRes
	 * @param attMemberRes
	 * @param defMemberRes
	 * @param attTeamSpaceLevel
	 * @param defTeamSpaceLevel
	 * @param type			0匹配 1挑战
	 * @throws Exception
	 */
	private void updateZYZDZPlayerStageClear(List<StageClearPlayerInfo> attInfos,List<StageClearPlayerInfo> defInfos,
			int hostId,int attCaptainRes,int attMemberRes,int defMemberRes,int attTeamSpaceLevel,int defTeamSpaceLevel,int type)throws Exception{
		
		for(StageClearPlayerInfo playerInfo: attInfos){
			int playerId=playerInfo.getId();
		
			if(playerId==hostId){
				if(type==1){
					playerInfo.setCaptainAddRes(-1);   //标识 队长 
					updatePlayerRes(playerId,attCaptainRes,playerInfo,attTeamSpaceLevel);
				}else{
					//队长多拿的资源
					int captainExEarn=attCaptainRes-attMemberRes;
					playerInfo.setCaptainAddRes(captainExEarn); 
					updatePlayerRobRes(playerId,attCaptainRes,playerInfo,attTeamSpaceLevel);
				}
				
			}else{
				updatePlayerRobRes(playerId,attMemberRes,playerInfo,attTeamSpaceLevel);
			}
		}
		for(StageClearPlayerInfo playerInfo: defInfos){
			int playerId=playerInfo.getId();
			updatePlayerRobRes(playerId,defMemberRes,playerInfo,defTeamSpaceLevel);
		}
	}
	//更新个人原石数
	private void updatePlayerRobRes(int playerId,int res,StageClearPlayerInfo playerInfo,int teamSpaceLevel)throws Exception{
		Player player=getService.getPlayerById(playerId);
		HashMap<String, Integer> playerRes=player.getLatestPlayerRes(teamSpaceLevel);
		int finalPersonRes=playerRes.get(Player.ORG_RES)+res > Constants.TEAMSPACELEVELCONSTANTS.
				getTeamSpaceLevelConstants(teamSpaceLevel).getpMaxOrgRes()
				?  Constants.TEAMSPACELEVELCONSTANTS.
						getTeamSpaceLevelConstants(teamSpaceLevel).getpMaxOrgRes() : playerRes.get(Player.ORG_RES)+res;
		
		playerInfo.setCurrRobRes(playerRes.get(Player.ORG_RES));
		player.setUnusableResource(finalPersonRes);
		playerInfo.setEarnRobRes(res);
		playerInfo.setIsVip(player.getIsVip());
		playerInfo.setCurrRank(player.getRank());
		playerInfo.setRank(player.getRank());
		playerInfo.setHeadGif(player.getIcon());
		if(res!=0){
			updatePlayerInfoOnly(player);
		}
		
	}
	//更新队长黑晶石数
	private void updatePlayerRes(int playerId,int res,StageClearPlayerInfo playerInfo,int teamSpaceLevel)throws Exception{
		Player player=getService.getPlayerById(playerId);
		HashMap<String, Integer> playerRes=player.getLatestPlayerRes(teamSpaceLevel);
		int finalPersonRes=playerRes.get(Player.RES)+res > Constants.TEAMSPACELEVELCONSTANTS.
				getTeamSpaceLevelConstants(teamSpaceLevel).getpMaxRes()
				?  Constants.TEAMSPACELEVELCONSTANTS.
						getTeamSpaceLevelConstants(teamSpaceLevel).getpMaxRes() : playerRes.get(Player.RES)+res;
		
		playerInfo.setCurrRobRes(playerRes.get(Player.RES));	
		player.setUsableResource(finalPersonRes);
		playerInfo.setEarnRobRes(res);
		playerInfo.setIsVip(player.getIsVip());
		playerInfo.setCurrRank(player.getRank());
		playerInfo.setRank(player.getRank());
		playerInfo.setHeadGif(player.getIcon());
		if(res!=0){
			updatePlayerInfoOnly(player);
		}
	}
	
	//统计资源争夺战中 战队队员 消耗道具总数
	private List<PlayerItem> getTeamCostItem4ZYZDZ(List<StageClearPlayerInfo> stageClearPlayerInfos){
		List<PlayerItem> teamItems=new ArrayList<PlayerItem>();
		for(StageClearPlayerInfo playerInfo: stageClearPlayerInfos){
			for(PlayerItem p:playerInfo.getZyzdzCostItem()){
				int flag=0;
				for(PlayerItem pt: teamItems){
					if(pt.getItemId()==p.getItemId()){
						pt.setQuantity(p.getQuantity()+pt.getQuantity());
						flag=1;
						break;
					}
				}
				if(flag==0){
					if(p.getQuantity()>0){
						teamItems.add(p);
					}
				}
				
			}
		}
		return teamItems;
	}
	/**
	 * 更新战队排行榜 1：战绩，2：等级，3：热门 ,4:最新 ,5:上周资源抢夺数,6:当前资源抢夺数
	 * @param team
	 * @param type
	 * @throws Exception
	 */
	public void updateTeamTop(Team team , int type) throws Exception{
		String key = Constants.TEAMTOP_KEY_PREFIX + type;
		double value=0.0;
		switch (type) {
		case 1:
			value = team.getGameWin()+1.0/(team.getGameTotal()+2);
			break;
		case 2:
			value = team.getLevel();
			break;
		case 3:
			value = team.getNumber();
			break;
		case 4:
			nosqlService.getNosql().addToQueue(key, String.valueOf(team.getId()), Constants.TEAM_TOP_NUM);
			return ;
		case 5:
			return ;
		case 6:
			return;
		default:
			break;
		}
		nosqlService.getNosql().zAdd(key, value, String.valueOf(team.getId()));
	}
	
	/**
	 * 5:上周资源抢夺数,6:当前资源抢夺数
	 * @param team
	 * @param type
	 * @throws Exception
	 */
	public void updateTeamTopZYZDZ(int value ,int teamid, int type) throws Exception{
		String key = Constants.TEAMTOP_KEY_PREFIX + type;
		nosqlService.getNosql().zAdd(key, value, String.valueOf(teamid));
	}	
	
	/**
	 * 5:上周资源抢夺数,6:当前资源抢夺数
	 * @param team
	 * @param type
	 * @throws Exception
	 */
	public void clearTopTeamZYZDZ(int type) throws Exception{
		String key = Constants.TEAMTOP_KEY_PREFIX + type;
		nosqlService.getNosql().delete(key);
	}		
	
	/**
	 * 6:当前资源抢夺数
	 * @param value
	 * @param teamId
	 * @throws Exception
	 */
	public void updateTeamResTop(int value,int teamId) throws Exception{
		String key = Constants.TEAMTOP_KEY_PREFIX + Constants.TEAM_TOP_TYPE.NOW_RES.getValue();
		nosqlService.getNosql().zAdd(key, value, String.valueOf(teamId));
	}
	
	/**
	 * 处理战队队员获得的战队经验，战队队员对战队的贡献
	 * @param stageClearInfo
	 * @param playerInfo
	 * @throws Exception 
	 */
	public void dealTeamExp(GeneralStageClearInfo stageClearInfo,StageClearPlayerInfo playerInfo,int isTeamAdd ) throws Exception{
		long fightTime = playerInfo.getEnd().getTime()-playerInfo.getStart().getTime();
		if(fightTime <3*60*1000){//小于3分钟，无战队贡献和战队经验 
			return;
		}
		//某个玩家 当天获取的战队经验值key
		int playerId=playerInfo.getId();
		PlayerTeam playerTeam=getService.getPlayerTeamByPlayerId(playerId);
		
		//vip 增加每日最大战队战经验	
		Player player=getService.getPlayerById(playerId);
		CommonUtil.checkNull(player, ExceptionMessage.NO_HAVE_THE_CHARACTER);
		int dayMaxForTeam=player.getIsVip()>=1 ? Constants.TEAMEXP_TEAM_DAY_MAX+Constants.VIP_TEAM_EXP_ADD[(player.getIsVip()-1)/2]
				: Constants.TEAMEXP_TEAM_DAY_MAX;
		
		if(stageClearInfo.getIsTeam()==1){
			//记录战队队员在此战役中队战队的表现情况	
			if(playerTeam!=null){
				if (Constants.BOOLEAN_NO.equals(playerTeam.getApproved())) {
					return;
				}
				playerTeam.setKillNum(playerInfo.getKill()+playerTeam.getKillNum());
				playerTeam.setDeadNum(playerInfo.getDead()+playerTeam.getDeadNum());
				playerTeam.setScore(playerInfo.getScore() +playerTeam.getScore());
				playerTeam.setTimes(playerTeam.getTimes() +1);//打过多少次战队战
				playerTeam.setAssist(playerTeam.getAssist()+playerInfo.getAssistNum());
				if(playerInfo.getIsWiner()==1){	//该玩家赢得比赛
					this.updatePlayerTeamAndSetPlayerInfo(playerTeam, playerInfo, dayMaxForTeam, Constants.TEAMEXP_TEAM_WIN,stageClearInfo.getIsTeam(),isTeamAdd);
				}else if(playerInfo.getIsWiner()==0){//该玩家输掉
					this.updatePlayerTeamAndSetPlayerInfo(playerTeam,  playerInfo, dayMaxForTeam, Constants.TEAMEXP_TEAM_LOSE,stageClearInfo.getIsTeam(),isTeamAdd);
				}
				this.getPlayerTeamDao().updatePlayerTeam(playerTeam);
			}
	   }else{
		//普通战役中个人对战队的贡献值
		if(playerTeam!=null){
			if (Constants.BOOLEAN_NO.equals(playerTeam.getApproved())) {
				return;
			}
			if(playerInfo.getIsWiner()==1){
				this.updatePlayerTeamAndSetPlayerInfo(playerTeam, playerInfo, Constants.TEAMEXP_PERSONAL_DAY_MAX, Constants.TEAMEXP_PERSONAL_WIN,stageClearInfo.getIsTeam(),0);
			}else{
				this.updatePlayerTeamAndSetPlayerInfo(playerTeam,  playerInfo, Constants.TEAMEXP_PERSONAL_DAY_MAX, Constants.TEAMEXP_PERSONAL_LOSE,stageClearInfo.getIsTeam(),0);
			}
		this.getPlayerTeamDao().updatePlayerTeam(playerTeam);
		}
	}
	}
	/**
	 * 
	 * @param playerTeam 
	 * @param team 玩家所在战队
	 * @param playerInfo 
	 * @param dayMax 玩家所玩战役模式当天可以取得的战队经验最大值
	 * @param fightExp 玩家当前战役取得的战队经验值
	 * @throws Exception
	 */
	public void  updatePlayerTeamAndSetPlayerInfo(PlayerTeam playerTeam,StageClearPlayerInfo playerInfo,int dayMax,int fightExp,int type,int isTeamAdd ) throws Exception{
		playerInfo.setIsTeamAdd(isTeamAdd);
		Team team=getService.getTeam(playerTeam.getTeamId());
		if(team!=null){
			int level = team.getLevel();
			int gainExp = 0;
			int todayFightExp=0;
			//team exp current
			if(type==1){
				todayFightExp=playerTeam.getTeamFightExp();
			}else{
				todayFightExp=playerTeam.getPersonalFightExp();
			}
			if(todayFightExp>=dayMax){
				playerInfo.setPlayerTeamExp(Constants.TEAMEXP_TEAM_FULL);
				playerInfo.setPlayerContribution(Constants.TEAMEXP_TEAM_FULL);
			}else if((dayMax-todayFightExp)<=fightExp){//
				int exp=dayMax-todayFightExp;
				playerInfo.setPlayerTeamExp(exp);
				playerInfo.setPlayerContribution(exp);
				if(type==1){//战队战
					playerTeam.setTeamFightExp(playerTeam.getTeamFightExp()+exp);//当天通过战队战获得战队经验
					exp*=(isTeamAdd==1?2:1);//战队战活动开启，经验
					playerTeam.setContribution(playerTeam.getContribution()+exp);
//					team=team.addExp(exp);
					gainExp = exp;
				}else{
					gainExp = dayMax-todayFightExp;
//					team=team.addExp((dayMax-todayFightExp));
					playerTeam.setPersonalFightExp(playerTeam.getPersonalFightExp()+(dayMax-todayFightExp));
					playerTeam.setContribution(playerTeam.getContribution()+(dayMax-todayFightExp));
				}
			}else{
				playerInfo.setPlayerTeamExp(fightExp);
				playerInfo.setPlayerContribution(fightExp);
				if(type==1){//战队战
					playerTeam.setTeamFightExp(playerTeam.getTeamFightExp()+fightExp);//当天通过战队战获得战队经验
					int exp=fightExp*(isTeamAdd==1?2:1);
					playerTeam.setContribution(playerTeam.getContribution()+exp);
					gainExp = exp;
//					team=team.addExp(exp);
				}else{
					playerTeam.setContribution(playerTeam.getContribution()+fightExp);
					playerTeam.setPersonalFightExp(playerTeam.getPersonalFightExp()+fightExp);
					gainExp = fightExp;
//					team=team.addExp(fightExp);
				}
				
			}
			TeamUtils.updateTeamExp(team.getId(), gainExp);
			team.addExp(gainExp);
			if(team.getLevel()>level){
//				updateTeamTop(team, 2);//更新战队等级排名
				List<PlayerTeam> ptList = getService.getPlayerTeamByTeamIdSimple(team.getId());
				for (PlayerTeam pt : ptList) {
					Player p=getService.getPlayerById(pt.getPlayerId());
					soClient.updateCharacterInfo(p, team.getName(), p.getRank());
				}
			}
			log.debug("TeamAdd/Exp\t"+playerInfo.getName()+"\t"+ gainExp);
			
		}else{
			if(type==1){
				log.error(ExceptionMessage.TEAM_NOT_EXIST);
			}
		}
	
	
	}
	public void updatePlayerStageQuit(int playerId, final GeneralStageClearInfo stageClearInfo) throws Exception {
		
		int type = stageClearInfo.getType();
		GAMETYPE gameType = Constants.GAMETYPE.getTypeByValue(type);
		if (gameType == null) {
			log.warn("unknown gameType when processing the stage clear, type :" + type);
			return;
		}
		final List<Player> vipPlayers = new ArrayList<Player>();
		final List<Player> players = new ArrayList<Player>();
		final List<Player> levelUpPlayers = new ArrayList<Player>();
		Player player = getService.getPlayerById(playerId);

		if (player != null) {
			players.add(player);
		} else {
			throw new BaseException(ExceptionMessage.NOT_FIND_PLAYER);
		}

		// Date stageTime=new Date();
		// final String stageStr=CommonUtil.simpleDateFormat.format(stageTime);
		LevelInfo levelInfo = sysLevelDao.getLevelInfoById(stageClearInfo.getLevelId());

		// 地图活动
		float expMapActivityAdd = 1;
		// float gpMapActivityAdd = 1;
		LevelInfo sysLevel = sysLevelDao.getLevelInfoById(stageClearInfo.getLevelId());
		Date activityEnd = sysLevel.getActivityEnd();
		Date date = stageClearInfo.getGameStart();
		if (null != activityEnd && activityEnd.after(date)) {
			Date activityStart = sysLevel.getActivityStart();
			if (null != activityStart && activityStart.before(date)) {
				expMapActivityAdd = (sysLevel.getExpAdd() > 1 ? sysLevel.getExpAdd() : 1);
				// gpMapActivityAdd = (sysLevel.getGpAdd() > 1 ?
				// sysLevel.getExpAdd() : 1);
			}
		}

		List<StageClearPlayerInfo> stateClearPlayerInfo = stageClearInfo.getStageClearPlayerInfoAll();

		double punish = 1;

		/** iterator */
		for (final StageClearPlayerInfo playerInfo : stateClearPlayerInfo) {

			if (player.getIsVip() >= 1 && gameType.getValue() != Constants.GAMETYPE.NEWTRAIN.getValue()) {
				// vip player
			
				int offset = updatePlayerActivity(Constants.ACTION_ACTIVITY.ONLINE_ACTIVITY.getValue(), playerInfo.getId(), new Date(), 0, 0, null, null);
				offset = (offset > 1) ? offset : 1;
				// initial buffList
				playerInfo.setBuffList(getService.filterBuffList(player));
				vipPlayers.add(player);
				/** calculate exp and gp */
				float getExp = 0;
				float getGP = 0;
				int getScore = 0;
				if (playerInfo.getOnlineMinutes() < 300) {// if more than 5
															// hours,
					int baseScore = playerInfo.getScore();
					ServiceLocator.stageClearLog.debug("clear " + playerInfo.getName() + " get baseScore" + baseScore);
					int score = (int) (Constants.numParam[type][8] * (((playerInfo.getEnd().getTime() - playerInfo.getStart().getTime()) / (Constants.numParam[type][7] * 1000))));
					if (baseScore > score) {
						baseScore = score;
					}
					ServiceLocator.stageClearLog.debug("clear " + playerInfo.getName() + " get baseScore" + baseScore);
					double baseExp = (Math.sqrt(baseScore) * Constants.numParam[type][2]);
					double baseGp = (Math.sqrt(baseScore) * Constants.numParam[type][6]);
					int winExp = playerInfo.getIsWiner() == 1 ? (int) Constants.numParam[type][3] : (int) Constants.numParam[type][4];
					int winGp = playerInfo.getIsWiner() == 1 ? (int) Constants.numParam[type][3] : (int) Constants.numParam[type][4];

					int mvpExp = (stageClearInfo.getMvpId() == playerInfo.getId()) ? (int) Constants.numParam[type][5] : 0;
					if (stageClearInfo.isClear()) {// exp and gp will get only
													// stage clear
						double finalExtraExp = player.getExpIncrement();
						double finalExtraGp = player.getGpIncrement();
						if (player.getIsVip() >= 1) {
							finalExtraExp += (double)Constants.VIP_EXTRA_EXP_PERCENT[player.getIsVip()-1] / 100;
							finalExtraGp += (double)Constants.VIP_EXTRA_GP_PERCENT[player.getIsVip()-1] / 100;
						}
						// add activity gp and exp
						getScore = baseScore;
						double extraExp = (playerInfo.getEnd().getTime() - playerInfo.getStart().getTime()) / (1000 * Constants.numParam[type][9]);
						double extraGp = (playerInfo.getEnd().getTime() - playerInfo.getStart().getTime()) / (1000 * Constants.numParam[type][10]);
						getExp = (int) (punish * (extraExp + baseExp + winExp + mvpExp) * (1 + player.getInternetCafe() * 10 / 100 + finalExtraExp) * offset * expMapActivityAdd);
						getGP = (int) (punish * (extraGp + baseGp + winGp) * (1 + finalExtraGp) * offset * expMapActivityAdd);

					}

					if (playerInfo.getOnlineMinutes() >= 180) {// 3 hours, gain
																// 50% exp
						getExp *= 0.5f;
						getGP *= 0.5f;
						getScore *= 0.5f;
					}
				}
				// int
				// index=(int)Math.sqrt((4000*Constants.gameTypeNum[type]+getScore)/(1000*Constants.gameTypeNum[type]))-2;
				// List<StageClearBox>
				// stageClearBoxList=CommonUtil.getStageClearBoxList(index,player,false);
				// playerInfo.setStageClearBoxList(stageClearBoxList);
				playerInfo.setScore(getScore);
				playerInfo.setCurrExp(player.getExp());
				playerInfo.setCurrGp(player.getGPoint());
				playerInfo.setExp((int) getExp);
				playerInfo.setGp((int) getGP);
				playerInfo.setCurrRank(player.getRank());// rank and exp in
				playerInfo.setRank(player.getRank());
				playerInfo.setIsVip(player.getIsVip());
				playerInfo.setHeadGif(player.getIcon());

				// set player
				boolean isNonFail = false;
				for (PlayerItem buff : player.getBuffList()) {
					SysItem si = sysItemDao.getSystemItemById(buff.getItemId());
					if (si.getIId() == 1 && si.getIBuffId() == 7) {
						isNonFail = true;
					}
				}
				if (!isNonFail) {
					player.setGLose(player.getGLose() + 1);
				}
				player.setGTotal(player.getGTotal() + 1);
				player.setWeekTotal(player.getWeekTotal() + 1);
				player.setRunAway(player.getRunAway() + 1);

				player.setGPoint((int) (player.getGPoint() + getGP));
				player.setExp((int) (player.getExp() + getExp));
				player.setGScore((int) (player.getGScore() + getScore));
				// just for syn
				final Player finalPlayer = player;

				Rank newRank = getService.getRankByExp(player.getExp());
				int newLevel = newRank.getId();
				if (newLevel != player.getRank() && newLevel < player.getRank()) {
					player.setRank(newLevel);
				}
				if (newLevel > player.getRank()) {
					if (ConfigurationUtil.SWITCH_XUNLEI_LOG.getIsOn()) {
						ServiceLocator.nosqlService.addXunleiLog("4.5" + Constants.XUNLEI_LOG_DELIMITER + levelInfo.getDisplayName() + Constants.XUNLEI_LOG_DELIMITER
								+ levelInfo.getType() + Constants.XUNLEI_LOG_DELIMITER + player.getUserName() + Constants.XUNLEI_LOG_DELIMITER + player.getName()
								+ Constants.XUNLEI_LOG_DELIMITER + CommonUtil.simpleDateFormat.format(new Date()) + Constants.XUNLEI_LOG_DELIMITER + player.getRank()
								+ Constants.XUNLEI_LOG_DELIMITER + newLevel);
					}
					updatePlayerActivity(Constants.ACTION_ACTIVITY.LEVEL_ACTIVITY.getValue(), player.getId(), null, player.getRank(), newLevel, null, null);
					player.setRank(newLevel);
					playerInfo.setRank(newLevel);
					levelUpPlayers.add(player);

					// 解锁角色成就单独处理
					List<PlayerAchievement> paList = getService.getPlayerAchievementVisible(player.getId());
					List<PlayerAchievement> finishedList = new ArrayList<PlayerAchievement>();
					for (PlayerAchievement pa : paList) {
						if (pa.getStatus() != Constants.NUM_ZERO) {
							continue;
						}

						SysAchievement sa = pa.getAchievement();

						int action = sa.getAction();

						// 本局游戏后，获得的完成数
						int numberThisRound = 0;
						if (Constants.ACTION.LEVELUP.getValue() == action) {
							ServiceLocator.missionLog.info("[debug]level up achievement rank =" + player.getRank() + " characters=" + player.getCharacters());
							if (player.getRank() >= pa.getAchievement().getNumber()) {// 达到指定等级
								numberThisRound = player.getRank();
							}
							updatePlayerAchievement(pa, numberThisRound, player, finishedList, stageClearInfo, playerInfo, sa);
							// 通知客户端完成的成就
							if (!finishedList.isEmpty()) {
								ServiceLocator.soClient.sendAchievementCompleted(finishedList,player.getId(), player.getName());
							}
							break;
						}
					}
				}
				boolean isFirstKill = stageClearInfo.isClear() && type != 5 && (playerInfo.getId() == stageClearInfo.getFirstKill());
				boolean isFirstDead = stageClearInfo.isClear() && type != 5 && (playerInfo.getId() == stageClearInfo.getFirstDead());
				if (isFirstKill) {
					playerInfo.setIsFirstKill(1);
				}

				if (isFirstDead) {
					playerInfo.setIsFirstDead(1);
				}
				/** update battle fields for player and team */
				setPlayerInfoStageClear(player, playerInfo, gameType, false);

				int boostKillNum = updateCharacterDataWhileStage(stageClearInfo, playerInfo, player, levelInfo);
				updatePlayerItemDurablewhileStage(playerInfo, playerInfo.getUseTotal(), punish);
				final int bossKill = boostKillNum;
				try {
					// vip无论多少人局,都更新任务
//					if (stageClearInfo.getStageClearPlayerInfoAll().size() >= 1) {
//
//						updatePlayerMissionInStageClear(finalPlayer, playerInfo, stageClearInfo, bossKill);
//					}
					//never enter in,because stageClearInfo.getStageClearPlayerInfoAll().size() is always 1
					if(stageClearInfo.getStageClearPlayerInfoAll().size() >= 3){
						updatePlayerMissionInStageClear(finalPlayer, playerInfo, stageClearInfo, bossKill);
						// 判断并更新成就信息
						updatePlayerAchievementInStageClear(finalPlayer, playerInfo, stageClearInfo);
					}
					// 更新活动信息
					updatePlayerActivity(Constants.ACTION_ACTIVITY.KILL_ACTIVITY.getValue(), playerInfo.getId(), null, playerInfo.getKill(), 0, null, null);
					updatePlayerActivity(Constants.ACTION_ACTIVITY.ACHIEVEMENT_ACTIVITY.getValue(), playerInfo.getId(), null, playerInfo.getKill(), 0, playerInfo, stageClearInfo);
					if (ConfigurationUtil.SWITCH_XUNLEI_LOG.getIsOn()) {
						// addXuleiLogWhileSteageClear(finalPlayer,
						// stageClearInfo, playerInfo.getExp(),
						// playerInfo.getGp(),
						// stageStr,levelInfo,(int)playerInfo.getScore());
					}
				} catch (Exception e) {
					ServiceLocator.stageClearLog.warn("error happend during achieveActivityMission task stage clear, cid:" + finalPlayer.getId());
				}

				/** clear cache */
				try {
					mcc.delete(CacheUtil.oPlayer(player.getId()));
					mcc.delete(CacheUtil.sPlayer(player.getId()));
					for (SysCharacter sc : getService.getDefaultSysCharacterList()) {
						for (int i = 0; i < 5; i++) {
							mcc.delete(CacheUtil.oStorage1(player.getId(), Constants.DEFAULT_WEAPON_TYPE, sc.getId(), i));
						}
					}
					// mcc.delete(CacheUtil.oStorage(player.getId(),
					// Constants.DEFAULT_WEAPON_TYPE, 0));
					// mcc.delete(CacheUtil.sStorage(player.getId(),
					// Constants.DEFAULT_WEAPON_TYPE, 0));
					mcc.delete(CacheUtil.oCharacterList(player.getId()));
				} catch (Exception e) {
					// do nothing, just catch all exception during set into
					// cache
					// for not roll back
					ServiceLocator.stageClearLog.warn("error happend during remove player cache in stageClear/stageQuit, cid:" + player.getId());
				}

			} else if (player.getIsVip() < 1 && gameType.getValue() != Constants.GAMETYPE.NEWTRAIN.getValue()) {
				// normal player
				int useTotal = 0;
				final List<SingleCharacterInfo> characterInfoList = playerInfo.getCharacterInfoList();
				int boostKillNum = 0;
				for (SingleCharacterInfo ci : characterInfoList) {
					boostKillNum += ci.getBoostKill();
					useTotal += ci.getUsedCount();
				}
				playerInfo.setUseTotal(useTotal);
				// set player
				boolean isNonFail = false;
				boolean isUndead = false;
				for (PlayerItem buff : player.getBuffList()) {
					SysItem si = sysItemDao.getSystemItemById(buff.getItemId());
					if (si.getIId() == 1 && si.getIBuffId() == 7) {
						isNonFail = true;
					}
					if (si.getIId() == 1 && si.getIBuffId() == 8) {
						isUndead = true;
					}
				}
				if (!isNonFail) {
					player.setGLose(player.getGLose() + 1);
				}
				boolean isIgnoreDead = (updatePlayerActivity(Constants.ACTION_ACTIVITY.IGNORE_DEAD.getValue(), player.getId(), null, 0, 0, playerInfo, null) == 1) ? true : false;
				boolean isIgnoreLose = (updatePlayerActivity(Constants.ACTION_ACTIVITY.IGNORE_LOSE.getValue(), player.getId(), null, 0, 0, playerInfo, null) == 1) ? true : false;
				nosqlService.updateStayData(player, playerInfo, (!isNonFail && 0 == playerInfo.getIsWiner() && !isIgnoreLose), (isUndead || isIgnoreDead));
				player.setGTotal(player.getGTotal() + 1);
				player.setWeekTotal(player.getWeekTotal() + 1);
				player.setRunAway(player.getRunAway() + 1);

				updatePlayerItemDurablewhileStage(playerInfo, useTotal, punish);
				/** clear cache */
				try {
					playerDao.updatePlayerInCache(player);
					updatePlayerTop(player);
					mcc.delete(CacheUtil.oPlayer(player.getId()));
					mcc.delete(CacheUtil.sPlayer(player.getId()));
					// mcc.delete(CacheUtil.oStorage(player.getId(),
					// Constants.DEFAULT_WEAPON_TYPE, 0));
					for (SysCharacter sc : getService.getDefaultSysCharacterList()) {
						mcc.delete(CacheUtil.oStorage1(player.getId(), Constants.DEFAULT_WEAPON_TYPE, sc.getId(), 0));
						mcc.delete(CacheUtil.oStorage1(player.getId(), Constants.DEFAULT_WEAPON_TYPE, sc.getId(), 1));
						mcc.delete(CacheUtil.oStorage1(player.getId(), Constants.DEFAULT_WEAPON_TYPE, sc.getId(), 2));
						mcc.delete(CacheUtil.oStorage1(player.getId(), Constants.DEFAULT_WEAPON_TYPE, sc.getId(), 3));
						mcc.delete(CacheUtil.oStorage1(player.getId(), Constants.DEFAULT_WEAPON_TYPE, sc.getId(), 4));
					}
					// mcc.delete(CacheUtil.sStorage(player.getId(),
					// Constants.DEFAULT_WEAPON_TYPE, 0));
					mcc.delete(CacheUtil.oCharacterList(player.getId()));
				} catch (Exception e) {
					// do nothing, just catch all exception during set into
					// cache
					// for not roll back
					ServiceLocator.stageClearLog.warn("error happend during remove player cache in stageClear/stageQuit, cid:" + player.getId());
				}
				throw new BaseException();
			}

		}
		log.debug("finsih calculate player date, channle id" + stageClearInfo.getChannelId() + " rid " + stageClearInfo.getRid());
		/** update players battle fields */

		playerDao.updatePlayerStageClear(players);

		/** push exp change event */
		for (Player p : vipPlayers) {
			soClient.updateCharacterInfo(p, p.getTeam(), p.getRank());
		}

		final Runnable addBattleFriend = new Runnable() {
			@Override
			public void run() {
				try {
					/** add recentPlayers for each player */
					if (!stageClearInfo.isClear() && vipPlayers.size() >= 1) {// stage
																				// quit,
						Player playerWhoQuit = vipPlayers.iterator().next();
						nosqlService.addRecentPlayersBidirection(players, playerWhoQuit.getId(), playerWhoQuit.getName());
					} else {// stage clear
						for (Player p : vipPlayers) {
							nosqlService.addRecentPlayers(players, p.getId(), p.getName());
						}
					}
				} catch (Exception e) {
					ServiceLocator.stageClearLog.warn("error happend during add battle friend stage clear exception is " + e);
				}
			}
		};

		final Runnable leveupEvent = new Runnable() {
			@Override
			public void run() {
				try {
					for (Player p : levelUpPlayers) {
						nosqlService.publishEvent(new LevelUpEvent(DateUtil.getCurrentTimeStr(), getService.getRankByExp(p.getExp()), p.getId(), p.getName()));
						if (p.getRank().equals(Constants.JOIN_TEAM_LIMIT)) {
							soClient.messageCMD(p.getName(), CommonMsg.REFRESH_ABLETOJOINTEAM);
						}
					}
				} catch (Exception e) {
					ServiceLocator.stageClearLog.warn("error happend during notify the player can join team stage clear. exception is " + e);
				}
			}
		};

		final Runnable allAsynTaskWrapper = new Runnable() {
			@Override
			public void run() {
				// sendSysGiftMail.run();
				addBattleFriend.run();
				leveupEvent.run();
			}
		};

		ServiceLocator.asynTakService.submit(allAsynTaskWrapper);
	}

	public void updatePlayerStageQuitZYZDZ(int playerId, final GeneralStageClearInfo stageClearInfo) throws Exception{
		
	}
	
	// switch is on run
	private void addXuleiLogWhileSteageClear(Player player, GeneralStageClearInfo stageClearInfo, int getExp, int getGP, String stageStr, LevelInfo levelInfo, int getScore,StageClearPlayerInfo playerInfo)
			throws Exception {
		int top = 0;
		List<StageClearPlayerInfo> list = stageClearInfo.getStageClearPlayerInfoTeam0();
		for (int i = 1; i <= list.size(); i++) {
			StageClearPlayerInfo scp = list.get(i - 1);
			if (scp.getId() == player.getId()) {
				top = i;
				break;
			}
		}
		if (top == 0) {
			list = stageClearInfo.getStageClearPlayerInfoTeam1();
			for (int i = 1; i <= list.size(); i++) {
				StageClearPlayerInfo scp = list.get(i - 1);
				if (scp.getId() == player.getId()) {
					top = i;
					break;
				}
			}
		}
		ServiceLocator.nosqlService.addXunleiLog("4.3" + Constants.XUNLEI_LOG_DELIMITER + levelInfo.getDisplayNameCN() + Constants.XUNLEI_LOG_DELIMITER + levelInfo.getType()
				+ Constants.XUNLEI_LOG_DELIMITER + player.getUserName() + Constants.XUNLEI_LOG_DELIMITER + player.getName() + Constants.XUNLEI_LOG_DELIMITER + player.getRank()
				+ Constants.XUNLEI_LOG_DELIMITER + getExp + Constants.XUNLEI_LOG_DELIMITER + getGP + Constants.XUNLEI_LOG_DELIMITER + top + Constants.XUNLEI_LOG_DELIMITER
				+ CommonUtil.simpleDateFormat.format(stageClearInfo.getGameStart()) + Constants.XUNLEI_LOG_DELIMITER
				+ CommonUtil.simpleDateFormat.format(stageClearInfo.getGameEnd()) + Constants.XUNLEI_LOG_DELIMITER + getScore + Constants.XUNLEI_LOG_DELIMITER 
				+ playerInfo.getIsWiner() + Constants.XUNLEI_LOG_DELIMITER + playerInfo.getKill() + Constants.XUNLEI_LOG_DELIMITER 
				+ playerInfo.getDead() + Constants.XUNLEI_LOG_DELIMITER 
				+ playerInfo.getAssistNum() + Constants.XUNLEI_LOG_DELIMITER+stageClearInfo.getIsTeam());
	}

	// ===============================================================================
	// Friend Services
	// ===============================================================================
	public void updateFriend(Integer playerId, Integer friendId, int type, String acceptted) throws Exception {
		List<Friend> friendList = friendDao.getFriendAllById(playerId, friendId);

		Friend friend = null;
		if (type == Constants.BLACKLIST || type == Constants.FRIEND) {
			for (Friend f : friendList) {
				if (f.getType().equals(Constants.BLACKLIST) || f.getType().equals(Constants.FRIEND)) {
					friend = f;
					break;
				}
			}
		}
		if (friend != null) {
			int oldType = friend.getType();
			friend.setType(type);
			friend.setAcceptted(acceptted);
			friendDao.update(friend);
			//TODO need performance optimize
			if(type==Constants.BLACKLIST||oldType==Constants.BLACKLIST){	
				mcc.delete(CacheUtil.oBlackList(playerId));
				mcc.delete(CacheUtil.oReqBlackList(friendId));
			}
			if(type==Constants.FRIEND||oldType==Constants.FRIEND){
				mcc.delete(CacheUtil.oFriendList(playerId));
				mcc.delete(CacheUtil.oReqFriendList(friendId));
			}
			
		} else {
			friend = new Friend();
			friend.setFriendId(friendId);
			friend.setPlayerId(playerId);
			friend.setType(type);
			friend.setAcceptted(acceptted);
			friendDao.create(friend);
			//TODO need performance optimize
			if(type==Constants.FRIEND){
				mcc.delete(CacheUtil.oFriendList(playerId));
				mcc.delete(CacheUtil.oReqFriendList(friendId));
			}else if(type==Constants.BLACKLIST){
				mcc.delete(CacheUtil.oBlackList(playerId));
				mcc.delete(CacheUtil.oReqBlackList(friendId));
			}

			// Player self = getService.getPlayerById(playerId);
			// Player f = getService.getPlayerById(friendId);
			// nosqlService.publishEvent(new AddFriendEvent(f, self.getId(),
			// self.getName()));
		}
	}

	public void updateGroup(Integer playerId, Integer friendId, int type, String name) throws Exception {

		Friend friend = friendDao.getFriend(playerId, friendId, type);

		if (friend != null) {
			friend.setGroupName(name);
			friendDao.update(friend);
		} else {
			String acceptted = Constants.BOOLEAN_NO;
			if (friendId.equals(playerId)) {
				acceptted = Constants.BOOLEAN_YES;
			}
			friend = new Friend();
			friend.setFriendId(friendId);
			friend.setPlayerId(playerId);
			friend.setType(type);
			friend.setAcceptted(acceptted);
			friend.setGroupName(name);
			friendDao.create(friend);

		}

		mcc.delete(CacheUtil.oGroupList(playerId));
		mcc.delete(CacheUtil.oReqGroupList(friendId));
	}

	public void updateGroupAccept(Integer playerId, Integer friendId, int type, String name) throws Exception {

		Friend friend = friendDao.getFriend(playerId, friendId, type);

		String acceptted = Constants.BOOLEAN_YES;
		if (friend != null) {
			friend.setGroupName(name);
			friendDao.update(friend);
		} else {
			friend = new Friend();
			friend.setFriendId(friendId);
			friend.setPlayerId(playerId);
			friend.setType(type);
			friend.setAcceptted(acceptted);
			friend.setGroupName(name);
			friendDao.create(friend);

		}

		mcc.delete(CacheUtil.oGroupList(playerId));
		mcc.delete(CacheUtil.oReqGroupList(friendId));
	}

	public void acceptFriend(Integer playerId, Integer friendId, int type) throws Exception {
		Friend friend = friendDao.getFriend(playerId, friendId, type);
		if (type == Constants.GROUP) {
			if (friend != null && friend.getAcceptted().equals(Constants.BOOLEAN_NO)) {
				friend.setAcceptted(Constants.BOOLEAN_YES);
				friendDao.update(friend);

				mcc.delete(CacheUtil.oGroupList(playerId));
				mcc.delete(CacheUtil.oGroupList(friendId));
				mcc.delete(CacheUtil.oReqGroupList(friendId));
			}
		} else {
			if (friend != null && friend.getAcceptted().equals(Constants.BOOLEAN_NO)) {
				friend.setAcceptted(Constants.BOOLEAN_YES);
				friendDao.update(friend);
				updateFriend(friendId, playerId, type, Constants.BOOLEAN_YES);

				mcc.delete(CacheUtil.oBlackList(playerId));
				mcc.delete(CacheUtil.oFriendList(playerId));
				mcc.delete(CacheUtil.oBlackList(friendId));
				mcc.delete(CacheUtil.oFriendList(friendId));
				mcc.delete(CacheUtil.oReqFriendList(friendId));
				mcc.delete(CacheUtil.oReqBlackList(friendId));

				int friendNumber = getService.getFriendList(playerId, Constants.FRIEND).size();
				int friends = getService.getFriendList(friendId, Constants.FRIEND).size();
				updatePlayerAchievementNotInStageClear(getService.getPlayerById(playerId), Constants.ACTION.FRIENDS.getValue(), friendNumber);
				updatePlayerAchievementNotInStageClear(getService.getPlayerById(friendId), Constants.ACTION.FRIENDS.getValue(), friends);

			}
		}

	}

	// ===============================================================================
	// Pack Services
	// ===============================================================================
	public void updatePlayerPackWithDefault(int playerId, String packType, int characterId, int type, int seq) throws Exception {
		List<PlayerItem> defaultList = getService.getDefaultPackList(playerId, packType, characterId);
		for (PlayerItem pi : defaultList) {
			if (Constants.PACK_TYPE_W.equals(packType)) {
				if (pi.getIsDefault().equals("Y") && CommonUtil.getWeaponSeq(pi.getSysItem().getWId()) == seq) {
					updateWeaponPackEquipment(playerId, type, pi.getId(), characterId);
				}
			} else if (Constants.PACK_TYPE_C.equals(packType)) {
				if (pi.getIsDefault().equals("Y") && CommonUtil.getCotumeSeq(pi.getSysItem().getCId()) == seq) {
					updateCostumePackEquipment(playerId, type, pi.getId(), characterId);
				}
			}
		}

	}

	public void updateWeaponPackEquipment(Integer playerId, Integer type, Integer playerItemId, Integer characterId) throws Exception {
		// get playerItemId
		PlayerItem playerItem = getService.getPlayerItemByItemId(playerId, type, playerItemId);// 2695
		if(playerItem==null){
			log.warn("updateWeaponPackEquipment not have item="+playerItemId+" playerId="+playerId+" type="+type+" characterId="+characterId);
			throw new NotEquipException(ExceptionMessage.NOT_PLAYER_ITEM);
		}
		SysItem si = sysItemDao.getSystemItemById(playerItem.getItemId());
		// get seq
		int seq = CommonUtil.getWeaponSeq(si.getWId());
		if (type != Constants.DEFAULT_WEAPON_TYPE || seq == 0) {
			throw new NotEquipException(ExceptionMessage.NOT_EQUIPED);
		}
		boolean isFit = false;
		for (String str : si.getCharacterIds()) {
			if (StringUtil.toInt(str) == characterId) {
				isFit = true;
				break;
			}
		}
		if (!isFit) {
			throw new NotEquipException(ExceptionMessage.NOT_EQUIPED);
		}
		// update the pack info in database
		List<PlayerPack> packList = playerPackDao.getWeaponPackByPackId(playerId, characterId);
		for (PlayerPack pp : packList) {
			if (seq == pp.getSeq()) {
				pp.setPlayerItemId(playerItem.getId());
				playerPackDao.updateMappingBeanInCache(pp, pp.getPlayerId());
				break;
			}
		}
		// begain useing
		begainUseItem(playerItem);

		mcc.delete(CacheUtil.oWeaponPack(playerId, Constants.NUM_ONE, characterId));
		// mcc.delete(CacheUtil.oStorage(playerId, type, 0));

		ServiceLocator.deleteService.deletePlayerItemInMemcached(playerId, si);

		mcc.delete(CacheUtil.oCharacterList(playerId));
	}

	public void updateCostumePackEquipment(Integer playerId, Integer type, Integer playerItemId, Integer characterId) throws Exception {
		PlayerItem playerItem = getService.getPlayerItemByItemId(playerId, type, playerItemId);
		if(playerItem==null){
			log.warn("updateCostumePackEquipment not have item="+playerItemId+" playerId="+playerId+" type="+type+" characterId="+characterId);
			throw new NotEquipException(ExceptionMessage.NOT_PLAYER_ITEM);
		}
		SysItem si = sysItemDao.getSystemItemById(playerItem.getItemId());
		boolean isFit = false;
		for (String str : si.getCharacterIds()) {
			if (StringUtil.toInt(str) == characterId) {
				isFit = true;
				break;
			}
		}
		if (!isFit) {
			throw new NotEquipException(ExceptionMessage.NOT_EQUIPED);
		}
		if (playerItem != null && playerItem.getPack() == 0) {

			// get seq
			int seq = CommonUtil.getCotumeSeq(si.getCId());

			if (seq != 0) {
				// update the pack info in database
				List<PlayerPack> packList = playerPackDao.getCostumePackByPackId(playerId, characterId);
				for (PlayerPack pp : packList) {
					if (seq == pp.getSeq()) {
						pp.setPlayerItemId(playerItem.getId());
						playerPackDao.updateMappingBeanInCache(pp, pp.getPlayerId());
						break;
					}
				}
				// mcc.delete(CacheUtil.oStorage(playerId, type, 0));

				// begain useing
				begainUseItem(playerItem);

				ServiceLocator.deleteService.deletePlayerItemInMemcached(playerId, si);

				mcc.delete(CacheUtil.oCharacterList(playerId));
				mcc.delete(CacheUtil.oCostumePack(playerId, Constants.NUM_ONE, characterId));
			} else {
				throw new NotEquipException(ExceptionMessage.NOT_EQUIPED);
			}

		}
	}

	// ===============================================================================
	// SysItem Services
	// ===============================================================================

	// ===============================================================================
	// Player Item Services
	// ===============================================================================
	public boolean renewPlayerItem(int playerId, int playerItemId, int type, int costId) throws Exception {
		PlayerItem playerItem = getService.getPlayerItemByItemId(playerId, type, playerItemId);
		SysItem si = sysItemDao.getSystemItemById(playerItem.getItemId());
		final Player player = getService.getPlayerById(playerId);
		if (CommonUtil.minutesToDate(playerItem.getExpireDate()) >= 24 * 30 * 60) {
			throw new BaseException(ExceptionMessage.NOT_RENEW);
		}
		Payment payment = getService.getPaymentById(playerItem.getItemId(), costId);
		int cost = payment.getCost();
		int frontPay = 0;
		int leftMoney = 0;
		// 1.handle money
		if (payment.getPayType() == Constants.GP_PAY) {

			int gp = player.getGPoint();
			frontPay = gp;
			leftMoney = gp - cost;
			if (gp < cost) {
				// NotBuyEquipmentException(ExceptionMessage.NOT_ENOUGH_GP);//not
				// have enough GPoint
				throw new BaseException(ExceptionMessage.NOT_ENOUGH_GP_RENEW);
			} else {
				gp = gp - cost;
				player.setGPoint(gp);
				player.setFunGpTotal(player.getFunGpTotal() + cost);
				playerDao.updatePlayerInCache(player);
				soClient.messageUpdatePlayerMoney(player);
			}
		} else if (payment.getPayType() == Constants.CR_PAY) {
			PlayerInfo playerInfo = getService.getPlayerInfoById(playerId);
			int cr = playerInfo.getXunleiPoint();
			frontPay = cr;
			leftMoney = cr - cost;
			if (cr < cost) {
				throw new BaseException(ExceptionMessage.NOT_ENOUGH_CR_RENEW);
			} else {
				cr = cr - cost;
				playerInfo.setXunleiPoint(cr);
				ServiceLocator.updateService.updatePlayerActivity(Constants.ACTION_ACTIVITY.PAY_FC.getValue(), playerId, null, cost, 0, null, null);
				playerInfoDao.update(playerInfo);
				mcc.delete(CacheUtil.oPlayerInfo(playerId));;
				soClient.messageUpdatePlayerMoney(player);
			}

		} else if (payment.getPayType() == Constants.V_PAY) {
			int voucher = player.getVoucher();
			frontPay = voucher;
			leftMoney = voucher - cost;
			if (voucher < cost) {
				throw new BaseException(ExceptionMessage.NOT_ENOUGH_V_RENEW);
			} else {
				voucher = voucher - cost;
				player.setVoucher(voucher);
				playerDao.updatePlayerInCache(player);
				soClient.messageUpdatePlayerMoney(player);
			}

		} else {
			throw new Exception("unknown pay type" + playerItem.getPlayerItemCurrency());
		}
		// mcc.delete(CacheUtil.oStorage(playerId,type,0));

		ServiceLocator.deleteService.deletePlayerItemInMemcached(playerId, si);

		mcc.delete(CacheUtil.oCharacterList(playerId));
		mcc.delete(CacheUtil.oPlayer(playerId));
		mcc.delete(CacheUtil.sPlayer(playerId));// player info and buff
		// TODO if playerItem.getPack()>0 delete the pack
		for (int i = 1; i <= 10; i++) {
			mcc.deleteWithNoReply(CacheUtil.oWeaponPack(playerId, 1, i));
			mcc.deleteWithNoReply(CacheUtil.oCostumePack(playerId, 1, i));
		}
		// if (playerItem.getSysItem().getType() ==
		// Constants.DEFAULT_WEAPON_TYPE) {
		// // mcc.delete(CacheUtil.oWeaponsInAllPacks(playerId));
		// } else if (playerItem.getSysItem().getType() ==
		// Constants.DEFAULT_COSTUME_TYPE || playerItem.getSysItem().getType()
		// == Constants.DEFAULT_PART_TYPE) {
		// // mcc.delete(CacheUtil.oCostumePack(playerId, Constants.NUM_ONE,
		// playerItem.getSysItem().getCSide()));
		// }
		// 2.renew expire time
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(playerItem.getExpireDate());
		String expireDate=CommonUtil.simpleDateFormat.format(playerItem.getExpireDate());
		if(playerItem.getExpireDate().getTime()<System.currentTimeMillis()){
			calendar.setTimeInMillis(System.currentTimeMillis());
		}
		calendar.add(Calendar.DAY_OF_YEAR, payment.getUnit());
		Date time = calendar.getTime();
		playerItem.setExpireDate(time);
		playerItem.setLeftSeconds(playerItem.getLeftSeconds() + payment.getUnit() * 24 * 60 * 60);
		playerItemDao.updatePlayerItem(playerItem);
		String renewDate=CommonUtil.simpleDateFormat.format(playerItem.getExpireDate());
		if(ConfigurationUtil.SWITCH_XUNLEI_LOG.getIsOn()){
			nosqlService.addXunleiLog("1.2" + Constants.XUNLEI_LOG_DELIMITER+ player.getUserName() + 
					Constants.XUNLEI_LOG_DELIMITER+ player.getName() + 
					Constants.XUNLEI_LOG_DELIMITER+ si.getId() + 
					Constants.XUNLEI_LOG_DELIMITER+ si.getDisplayNameCN() +
					Constants.XUNLEI_LOG_DELIMITER+ (payment.getUnitType()==1?payment.getUnit():1) +
					Constants.XUNLEI_LOG_DELIMITER+ payment.getPayType() +
					Constants.XUNLEI_LOG_DELIMITER + cost    
					+ Constants.XUNLEI_LOG_DELIMITER+ (payment.getUnitType() == 2 ? payment.getUnit() : "")
					+ Constants.XUNLEI_LOG_DELIMITER+ CommonUtil.simpleDateFormat.format(new Date())
					+ Constants.XUNLEI_LOG_DELIMITER + frontPay
					+ Constants.XUNLEI_LOG_DELIMITER + leftMoney
					+ Constants.XUNLEI_LOG_DELIMITER + 2
					+ Constants.XUNLEI_LOG_DELIMITER + ""+ Constants.XUNLEI_LOG_DELIMITER + ""
					+ Constants.XUNLEI_LOG_DELIMITER + player.getRank());
		}
		final PayLog payLog = new PayLog();
		payLog.setUserId(player.getUserName());
		payLog.setPlayerId(player.getId());
		payLog.setItemId(si.getId());
		payLog.setItemName(si.getDisplayNameCN());
		payLog.setItemType(si.getType());
		payLog.setPayType(payment.payType);
		payLog.setPayAmount(payment.getCost());
		payLog.setCreateTime(new Date());
		payLog.setLeftMoney(leftMoney);
		payLog.setPayUse(2);
		log.info("renew playeritem id=" + playerItem.getId() + " playerId="
				+ playerItem.getPlayerId() + " from expireDate=" + expireDate
				+ " to renewDate=" + renewDate);
		// just for log into dc
		final int payUnit = payment.getUnit();
		final int payUnitType = payment.getUnitType();
		final Runnable writePayLog = new Runnable() {
			@Override
			public void run() {
				try {
					createService.createPayLog(payLog);

					String message = String.format(
							"%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s", player.getId(), CommonUtil.simpleDateFormat.format(new Date()), payLog.getPayAmount(),
							payLog.getPayType(), payLog.getItemName(), payLog.getItemId(), payLog.getItemType(),
							payUnitType, payUnit, payLog.getPayUse());
					// 记到analyser server
					transferDataToDc.addLog("bjItemCost", message);
				} catch (Exception e) {
					log.warn("Exception in renewPlayerItem", e);
				}

			}
		};
		ServiceLocator.asynTakService.execute(writePayLog);
		return true;
	}

	public void fixPlayerItem(int userId, int playerId, int playerItemId, int type) throws Exception {
		PlayerItem playerItem = getService.getPlayerItemByItemId(playerId, type, playerItemId);

		Player player = getService.getSimplePlayerById(playerId);
		SysItem si = sysItemDao.getSystemItemById(playerItem.getItemId());
		// playerItem.calculateParam(si);
		int gp = player.getGPoint();
		int rc = playerItem.getRepairCost();
		if (gp < rc) {
			throw new BaseException(ExceptionMessage.NOT_ENOUGH_GP_FIX);
		}

		// mcc.delete(CacheUtil.oStorage(playerId, type,0));

		ServiceLocator.deleteService.deletePlayerItemInMemcached(playerId, si);

		mcc.delete(CacheUtil.oCharacterList(playerId));
		mcc.delete(CacheUtil.oPlayer(playerId));
		mcc.delete(CacheUtil.sPlayer(playerId));
		// clear cache for pack
		// mcc.delete(CacheUtil.oWeaponsInAllPacks(playerId));
		for (int i = 1; i <= 10; i++) {
			mcc.deleteWithNoReply(CacheUtil.oWeaponPack(playerId, 1, i));
		}
		// 1.update GP
		player.setGPoint(gp - rc);
		player.setFunGpTotal(player.getFunGpTotal() + rc);
		playerDao.updatePlayerInCache(player);
		// 2.update durable
		playerItem.setDurable(Constants.MAX_DURABLE);
		playerItemDao.updatePlayerItem(playerItem);
		soClient.messageUpdatePlayerMoney(player);
	}

	// ===============================================================================
	// Message Services
	// ===============================================================================

	public void updateMessage(int playerId, int messageId) throws Exception {
		mcc.delete(CacheUtil.oPlayerMessage(playerId));
		mcc.delete(CacheUtil.sPlayerMessage(playerId));

		messageDao.deleteMessage(messageId);
		messageDao.deleteMessageItem(messageId);
	}

	// ===============================================================================
	// Team Services
	// ===============================================================================

	public void updateTeamGameInfo(Collection<TeamScoreIncrement> teamScoreIncrements) throws SQLException {
		teamDao.updateTeamGameInfo(teamScoreIncrements);
	}

	public void upgradeTeam(int playerId, PlayerItem playerItem, int teamId) throws Exception {
		// 1.get PlayerItem and get upgrade size,use it
		SysItem si = sysItemDao.getSystemItemById(playerItem.getItemId());
		String[] temp = si.getIValue().split(",");
		int size = StringUtil.toInt(temp[0]);// get upgrade size
		// 2.get team info
		Team team = getService.getTeam(teamId);
		int teamSize = team.getSize();
		if (teamSize >= size) {
			throw new BaseException(ExceptionMessage.UPGRADE_TEAM_FAIL);
		}
		// 3.update team size
		team.setSize(size);
		teamDao.updateTeam(team);
		mcc.delete(CacheUtil.oTeam(teamId));
		mcc.delete(CacheUtil.oSimpleTeam(teamId));
	}

	public void updateTeamInfo(String logo, int rank, String description, int teamId) throws Exception {
		Team team = getService.getTeam(teamId);
		team.setLogo(logo);
		team.setRank(rank);
		team.setDescription(description);
		updateTeamInfo(team);
	}
	public void updateTeamInfo(Team team) throws Exception {
		int teamId = team.getId();
		teamDao.updateTeam(team);
		teamDao.updateObjInDB(team);
		mcc.delete(CacheUtil.oTeam(teamId));
		mcc.delete(CacheUtil.oSimpleTeam(teamId));
		// GM使用
		mcc.delete(CacheUtil.oTeamList());
		mcc.delete(CacheUtil.oDefaultTeamList());
	}

	public void addAlly(int teamId, int allyId) throws Exception {
		// verify
		Team team = getService.getTeamById(teamId);
		if (team != null) {
			for (Team ally : team.getTeamList()) {
				if (ally.getId() == allyId || ally.getId() == teamId) {
					throw new DuplicateAllyException();
				}
			}

			Ally ally = new Ally();
			ally.setTeamId(teamId);
			ally.setAllyId(allyId);
			allyDao.create(ally);
		} else {
			throw new Exception(ExceptionMessage.NOT_FIND_TEAM);
		}
	}

	public void removeAlly(int teamId, int allyId) throws Exception {
		Ally ally = allyDao.getAllyById(teamId, allyId);
		if (ally != null) {
			allyDao.delete(ally);
		}

	}

	public void applyTeam(int teamId, int playerId) throws Exception {
		Player p = getService.getSimplePlayerById(playerId);
		Team team = getService.getTeamById(teamId);
		if (null == team) {
			throw new BaseException(ExceptionMessage.DISMISS_TEAM);
		}
		if (null == p) {
			throw new BaseException(ExceptionMessage.PALERY_NOT_EXIST);
		}
		if (team.getMemberList().size() >= team.getSize()) {
			throw new BaseException(ExceptionMessage.TEAM_FULL);
		}
		if (p.getRank() < Constants.JOIN_TEAM_LIMIT) {
			throw new BaseException(ExceptionMessage.JOIN_ERROR);
		}
		if (p.getRank() < team.getRank()) {
			throw new BaseException(ExceptionMessage.JOIN_LEVEL_ERROR);
		}

		PlayerTeam pt = getService.getPlayerTeamByPlayerId(playerId);
		if (pt != null) {
			if (Constants.BOOLEAN_YES.equals(pt.getApproved())) {
				throw new BaseException(ExceptionMessage.TEAM_ALREADY_JOIN);
			}
			playerTeamDao.deletePlayerTeam(pt);
		}

		List<PlayerTeam> unapprovedMemberList = playerTeamDao.getUnapprovedMember(teamId);
		if (unapprovedMemberList.size() >= 99) {
			throw new BaseException(ExceptionMessage.TEAM_OVER_MAX_APPLY);
		}

		// add log
		List<TeamRecordObj> list = new ArrayList<TeamRecordObj>();
		TeamRecordObj record = new TeamRecordObj();
		record.setCreateTime(new Date());
		record.setTeam(team.getId());
		record.setPlayerid(playerId);
		record.setType(0);
		list.add(record);
		nosqlService.addTeamRecord(list);
		// create new
		PlayerTeam playerTeam = new PlayerTeam();
		playerTeam.setApproved(Constants.BOOLEAN_NO);
		playerTeam.setJob(TeamConstants.TEAMJOB.TEAM_MEMBER.getValue());
		playerTeam.setTeamId(teamId);
		playerTeam.setPlayerId(playerId);
		playerTeam.setKillNum(0);
		playerTeam.setDeadNum(0);
		playerTeam.setAssist(0);
		playerTeam.setScore(0);
		playerTeam.setCreateTime(System.currentTimeMillis()/1000);
		try {
			playerTeamDao.createPlayerTeam(playerTeam);
			// get unapproved team member and send to leader
			List<PlayerTeam> unapporovedMember = getService.getUnapprovedMember(teamId);
			// notify
			List<PlayerTeam> memberList = team.getMemberList();
			for (PlayerTeam tmp : memberList) {
				if(tmp.getJob() == TeamConstants.TEAMJOB.TEAM_CAPTAIN.getValue()||tmp.getJob()==TeamConstants.TEAMJOB.TEAM_OFFICER.getValue()){
					Player playerById = getService.getPlayerById(tmp.getPlayerId());
					if (null != playerById&&null != mcc.get(CacheUtil.oPlayerLocation(playerById.getId()),Constants.CACHE_TIMEOUT)) {
						soClient.puchCMDtoClient(playerById.getName(), CommonUtil.messageFormat(CommonMsg.REFRESH_TEAM_APPLICATION_NUM, unapporovedMember.size()));
					}
				}
			}
		} catch (Exception e) {
			log.warn("error happend during notify team application for cid:" + playerId + " exception :" + e);
		}
	}

	// not used now

	public void acceptJoinTeam(int teamId, int playerId) throws Exception {
		PlayerTeam pt = playerTeamDao.getByPlayerId(playerId);
		if (pt != null) {
			if (Constants.BOOLEAN_YES.equals(pt.getApproved())) {
				// do nothing
				return;
			}
			playerTeamDao.removeAllApplicationFromPlayer(playerId);
		}
		PlayerTeam playerTeam = new PlayerTeam();
		playerTeam.setApproved(Constants.BOOLEAN_YES);
		playerTeam.setJob(TeamConstants.TEAMJOB.TEAM_MEMBER.getValue());
		playerTeam.setTeamId(teamId);
		playerTeam.setPlayerId(playerId);
		playerTeamDao.createPlayerTeam(playerTeam);
	}

	// team leader approve the application
	/**
	 * @param successNameList
	 *            outer parameter
	 * @param sizeFailNameList
	 *            outer parameter
	 * @param expireNameList
	 *            outer parameter
	 */
	public void approve(PlayerTeam cpt,int teamId, String[] applyIds, List<String> successNameList, List<String> sizeFailNameList, List<String> expireNameList) throws Exception {
		final Team team = getService.getTeam(teamId);
		if (team == null) {
			throw new Exception("team not exist");
		}
		// 人满啦
		List<PlayerTeam> ptList = getService.getPlayerTeamByTeamId(teamId);
		if ((ptList.size() + applyIds.length) > team.getSize()) {
			int joinSize = (team.getSize() - ptList.size()) > 0 ? (team.getSize() - ptList.size()) : 0;
			if(joinSize==0){
				throw new BaseException(ExceptionMessage.TEAM_MEMBER_FULL);
			}
			throw new BaseException(CommonUtil.messageFormatI18N(ExceptionMessage.TEAM_MEMBER, joinSize));
		}
		// int orginalMemberNums = team.getMemberList().size();
//		int orginalMemberNums = team.getMemberCount();
		int orginalMemberNums = ptList.size();
		int updateNums = 0;
		final List<Player> successList = new ArrayList<Player>();
		// add log
		final List<TeamRecordObj> list = new ArrayList<TeamRecordObj>();
		int faiedNum = 0;
		int alreadyAppove = 0;
		StringBuilder failedPlayerNames = new StringBuilder();
		StringBuilder alreadyAppoveNames = new StringBuilder();
		for (String playerId : applyIds) {
			int id = Integer.valueOf(playerId);
			PlayerTeam pt = getService.getPlayerTeamByPlayerId(id);
			Player player = getService.getSimplePlayerById(id);
			// 判断用户是否已经在别的战队了
			if ((pt != null && pt.getTeamId() != teamId)) {
				sizeFailNameList.add(player.getName());
				failedPlayerNames.append(player.getName()).append("|");
				faiedNum++;
				continue;
			}else if(pt != null &&pt.getApproved().equals(Constants.BOOLEAN_YES)){
				sizeFailNameList.add(player.getName());
				alreadyAppoveNames.append(player.getName()).append("|");
				alreadyAppove++;
				continue;
			}

			// size not enough
			if (orginalMemberNums + updateNums + 1 > team.getSize()) {
				sizeFailNameList.add(player.getName());
				continue;// continue to add id into fail list
			}
			int inffectRows = playerTeamDao.approve(player, teamId);
			// 0 rows effected, means this application has already been changed,
			// can not find in db
			if (inffectRows == 0) {
				expireNameList.add(player.getName());
			} else {
				ServiceLocator.playerLog.info("player="+player.getId()+" join team="+teamId+" successfully");
				successList.add(player);
				// add log
				TeamRecordObj record = new TeamRecordObj();
				record.setCreateTime(new Date());
				record.setTeam(team.getId());
				record.setPlayerid(player.getId());
				record.setType(1);
				list.add(record);

				// 成长任务24：加入战队 判断条件：加入审核成功后进行flow
				updatePlayerGrowthMission(player, GrowthMissionType.JOIN_TEAM);

				updateNums++;
			}
			soClient.puchCMDtoClient(player.getName(), CommonUtil.messageFormat(CommonMsg.REFRESH_MAX_STRENGHT_LEVEL, getService.getMaxStrengthLevel(player.getId())));
		}
		team.setNumber(team.getNumber() + successList.size());
//		TeamUtils.updateTeamMemberNum(teamId, successList.size());
		ServiceLocator.updateService.updateTeamInfo(team);
		updateTeamTop(team, Constants.TEAM_TOP_TYPE.HOT.getValue());//更新战队热门排名
		for (Player p : successList) {
			successNameList.add(p.getName());
			try {
				p.setTeamId(teamId);
				p.setTeam(team.getName());
				updatePlayerInfo(p);
				// TODO thread pool to do
//				soClient.updateCharacterInfo(p, team.getName(), p.getRank());
//				mcc.deleteWithNoReply(CacheUtil.oPlayer(p.getId()));
//				mcc.deleteWithNoReply(CacheUtil.sPlayer(p.getId()));
				messageService.sendApprovedNotifyMail(p, team);
			} catch (Exception e) {
				log.warn("sending the approve email to" + p.getName() + "happen error");
			}
		}
		// 传输给队长失败的人员名字
		if (faiedNum > 0) {
			Object[] objs = new Object[2];
			objs[0] = faiedNum;
			objs[1] = failedPlayerNames.toString();
			soClient.puchCMDtoClient(getService.getSimplePlayerById(cpt.getPlayerId()).getName(), CommonUtil.messageFormat(CommonMsg.REFRESH_TEAM_APPROVE_FAILED, objs));
		}
		if(alreadyAppove>0){
			Object[] objs = new Object[2];
			objs[0] = faiedNum;
			objs[1] = failedPlayerNames.toString();
			soClient.puchCMDtoClient(getService.getSimplePlayerById(cpt.getPlayerId()).getName(), CommonUtil.messageFormat(CommonMsg.REFRESH_TEAM_APPROVE_FAILED2, objs));
		}
		// mcc.delete(CacheUtil.oTeam(teamId));
//		String	objKey=CacheUtil.oPlayerTeam(teamId);
//		mcc.delete(objKey);
		Runnable task = new Runnable() {
			@Override
			public void run() {
				nosqlService.addTeamRecord(list);
				// 通知成员已经改变
				try {
					for (PlayerTeam tempPt : getService.getPlayerTeamByTeamId(team.getId())) {
						soClient.puchCMDtoClient(tempPt.getNickName(), CommonUtil.messageFormat(CommonMsg.TEAM_NUMBER_CHANGE, null));
					}
				} catch (Exception e) {
					log.warn("error in push cmd to notify team members a new member added.");
				}
			}
		};
		ServiceLocator.asynTakService.submit(task);
	}

	// team leader reject the application, or kick someone
	public void removePlayerTeam(int teamId, String[] playerIds) throws Exception {
		if (playerIds != null && playerIds.length > 0) {
			playerTeamDao.batchRemove(teamId, playerIds);
			mcc.delete(CacheUtil.oTeam(teamId));
			mcc.delete(CacheUtil.oSimpleTeam(teamId));
		}
	}

	public void handoverLeader(PlayerTeam oldLeader, PlayerTeam newLeader) throws Exception {
		if (oldLeader == null || newLeader == null || !oldLeader.getJob().equals(TeamConstants.TEAMJOB.TEAM_CAPTAIN.getValue()) || !oldLeader.getTeamId().equals(newLeader.getTeamId())) {
			throw new IllegalArgumentException("parameter not correct");
		}

		playerTeamDao.updateJob(newLeader, TeamConstants.TEAMJOB.TEAM_CAPTAIN.getValue());
		playerTeamDao.updateJob(oldLeader, TeamConstants.TEAMJOB.TEAM_MEMBER.getValue());
		mcc.delete(CacheUtil.oTeam(oldLeader.getTeamId()));
		mcc.delete(CacheUtil.oSimpleTeam(oldLeader.getTeamId()));
		messageService.sendAppointNotifyMail(getService.getPlayerById(newLeader.getPlayerId()),getService.getPlayerById(oldLeader.getPlayerId()), getService.getTeam(newLeader.getTeamId()), newLeader);
		ServiceLocator.teamRequestOpLog.debug(LogUtils.JoinerByTab.join("handoverLeader",oldLeader.getPlayerId(),newLeader.getPlayerId()));
	}

	// team member ask for quit or he has been kicked
	public void quit(int teamId, int playerId) throws Exception {
		//vip保留一定vip经验
		Player p = getService.getPlayerById(playerId);
		CommonUtil.checkNull(p, ExceptionMessage.NO_HAVE_THE_CHARACTER);
		PlayerTeam beforeRemove=getService.getPlayerTeam(teamId, playerId);
		if(p.getIsVip()>=1){		
			int remainsExp=(int)(beforeRemove.getContribution()*Constants.VIP_REMAINS_TEAM_EXP_PER[(p.getIsVip()-1)/2]/100.0);
			p.setVipRemainsTeamExp(remainsExp);
		
		}
		//一般玩家退出战队后保留这个战队的战队贡献一定时间，避免玩家误操作
		p.setLastExitTeamId(teamId);
		p.setLastTeamExp(beforeRemove.getContribution());
		p.setLastTeamExpExpireTime((int)(System.currentTimeMillis()/1000));
		
		playerTeamDao.remove(teamId, playerId);
		
		// add log
		List<TeamRecordObj> list = new ArrayList<TeamRecordObj>();
		TeamRecordObj record = new TeamRecordObj();
		record.setCreateTime(new Date());
		record.setTeam(teamId);
		record.setPlayerid(playerId);
		record.setType(2);
		list.add(record);
		nosqlService.addTeamRecord(list);
		
	
		p.setTeamId(0);
		p.setTeam("");
		updatePlayerInfo(p);
		
//		soClient.updateCharacterInfo(p, "", p.getRank());
		mcc.delete(CacheUtil.oTeam(teamId));
		mcc.delete(CacheUtil.oSimpleTeam(teamId));
//		mcc.delete(CacheUtil.oPlayer(playerId));
//		mcc.delete(CacheUtil.sPlayer(playerId));

		// 通知成员已经改变
		Team team = getService.getTeam(teamId);
		List<PlayerTeam> ptList = getService.getPlayerTeamByTeamId(teamId);
		team.setNumber(ptList.size());
//		TeamUtils.updateTeamMemberNum(teamId, -1);
		updateTeamInfo(team);
		updateTeamTop(team, 3);
		ServiceLocator.deleteService.deletePlayerTeamBuff(playerId);
		
		soClient.puchCMDtoClient(p.getName(),  
				CommonUtil.messageFormat(CommonMsg.REFRESH_MAX_STRENGHT_LEVEL, getService.getMaxStrengthLevel(p.getId())));
		
		for (PlayerTeam tempPt : ptList) {
			Player player = getService.getPlayerById(tempPt.getPlayerId());
			soClient.puchCMDtoClient(player.getName(), CommonUtil.messageFormat(CommonMsg.TEAM_NUMBER_CHANGE, null));
		}
	}

	public void updatePlayerJob(PlayerTeam oper, PlayerTeam target, int newJob) throws Exception {
		// can not manage himself  only captain can manager 
		if (target == null || oper == null||oper.getPlayerId().equals(target.getPlayerId()) || !oper.getJob().equals(TEAMJOB.TEAM_CAPTAIN.getValue())) {
			throw new BaseException(ExceptionMessage.TEAM_OP_FAIL);
		}
		if(newJob == TEAMJOB.TEAM_CAPTAIN.getValue()){
			handoverLeader(oper ,target );
		}else{
			playerTeamDao.updateJob(target, newJob);
			messageService.sendAppointNotifyMail(getService.getPlayerById(target.getPlayerId()),getService.getPlayerById(oper.getPlayerId()), getService.getTeam(target.getTeamId()), target);
		}
		mcc.delete(CacheUtil.oTeam(oper.getTeamId()));
		ServiceLocator.teamRequestOpLog.debug(LogUtils.JoinerByTab.join("updatePlayerJob",oper.getPlayerId(),target.getPlayerId(),newJob));
	}

	// ===============================================================================
	// Player Pack
	// ===============================================================================
	/**
	 * 
	 * @param playerPacks
	 *            key:cid value:list of pack ids
	 * 
	 * @throws Exception
	 * 
	 */
	public void batchDecreaseWeaponDurable(List<PlayerItem> list, float cost) throws Exception {
		for (PlayerItem pi : list) {
			if (pi != null && Constants.BOOLEAN_NO.equals(pi.getIsDefault()) && pi.getPlayerItemUnitType() == Constants.UNITTYPE_FOREVER) {
				float newDurable = pi.getDurable() - cost;
				if (newDurable < 0) {
					newDurable = 0;
				}
				pi.setDurable(newDurable);
				playerItemDao.updatePlayerItem(pi);
			}
		}

	}

	// ===============================================================================
	// System Config
	// ===============================================================================

	public void updateSysValue(String key, String value) throws TimeoutException, InterruptedException, MemcachedException {
		int i = -1;
		if (key.indexOf(".button") > -1) {
			i = (int) Float.parseFloat(value);
			value = i + "";
		}
		systemDao.setValue(key, value);
	}

	public CreateService getCreateService() {
		return createService;
	}

	public void setCreateService(CreateService createService) {
		this.createService = createService;
	}

	public MessageService getMessageService() {
		return messageService;
	}

	public void setMessageService(MessageService messageService) {
		this.messageService = messageService;
	}

	public void updateCharacterDataService(CharacterData c, int playerId) throws Exception {
		characterDataDao.updateCharacterDataInCache(c, playerId);
	}

	public void updatePlayerInfo(Player p) throws Exception {
		playerDao.updatePlayerInCache(p);
//		updatePlayerTop(p);
		mcc.delete(CacheUtil.oPlayer(p.getId()));
		mcc.delete(CacheUtil.sPlayer(p.getId()));
		soClient.updateCharacterInfo(p, p.getTeam(), p.getRank());
	}

	public void updatePlayerInfoOnly(Player p) throws Exception {
		playerDao.updatePlayerInCache(p);
//		updatePlayerTop(p);
		mcc.delete(CacheUtil.oPlayer(p.getId()));
		mcc.delete(CacheUtil.sPlayer(p.getId()));
		// soClient.updateCharacterInfo(p, p.getTeam(), p.getRank());
	}
	
	public void updatePlayerTop(Player p) throws Exception{
		NoSql nosql = nosqlService.getNosql();
		String pId = String.valueOf(p.getId());
		for(String type : Constants.PERSONAL_TOP_TYPE){
			if("kFightNum".equals(type)){
				continue;
			}
			String key = NosqlKeyUtil.commonLevelNumInRealTopByType(type);
			double valueInTop = Math.abs(nosql.zScore(key,pId));
			double pValue = 0.0;
			if("kScore".equals(type)){
				pValue = p.getGScore();
			}else if("kAssist".equals(type)){
				pValue = p.getGAssist();
			}else if("kWinRate".equals(type)){
				pValue = p.getGWin()+p.getWinRate()/2;
			}else if("kKillDead".equals(type)){
				pValue = p.getGKill() + 1.0/(p.getGDead()+2);
			}
			if(pValue!=valueInTop){
				int total = (int)nosql.zCard(NosqlKeyUtil.commonLevelNumInTopByType(type));
				if(total<Constants.REAL_TOP_RANK_NUM)
				nosql.zAdd(NosqlKeyUtil.commonLevelNumInRealTopByType(type),- pValue, pId);
				else {
					double lastValue = Math.abs(nosql.zrangeWithScores(key, Constants.REAL_TOP_RANK_NUM-1, Constants.REAL_TOP_RANK_NUM-1).iterator().next().getScore());
					if(pValue>lastValue){
						nosql.zAdd(NosqlKeyUtil.commonLevelNumInRealTopByType(type),- pValue, pId);
					}
				}
			}
			
		}
	}
	/**
	 * 完成成就类活动
	 * @param player
	 * @param action
	 * @param numberThisRound
	 * @throws Exception
	 */
	public void updatePlayerAchievementActivityNotInStageClear(Player player,int action,int number) throws Exception{
		List<PlayerActivity> activityList = getAvailableAchievementActivityByPlayerId(player.getId());
		List<PlayerActivity> completeList = new ArrayList<PlayerActivity>();
		for (PlayerActivity pa : activityList) {
			if(pa.getAchievementAction()==action){
				pa.setNumber(number);
				if (pa.getTarget() <= number) {
					pa.setStatus(1);
					pa.setNumber(pa.getTarget());
					completeList.add(pa);
				}
				updatePlayerActivity(pa);
			}
			if (completeList.size() > 0) {
				soClient.puchCMDtoClient(player.getName(), Converter.completeActivity(completeList));
			}
		}
	}
	public void updatePlayerAchievementInStageClear(Player p, StageClearPlayerInfo player, GeneralStageClearInfo stageClearInfo) throws Exception {
		int playerId = player.getId();
		// Player p = getService.getPlayerById(playerId);
		List<PlayerAchievement> paList = getService.getPlayerAchievementVisible(playerId);
		int[] count = { 0, 0, 0, 0, 0, 0, 0 ,0};//TODO 新成就
		List<PlayerAchievement> finishedList = new ArrayList<PlayerAchievement>();
		for (PlayerAchievement pa : paList) {
			if (pa.getStatus() != Constants.NUM_ZERO) {
				continue;
			}
			SysAchievement sa = pa.getAchievement();
			int action = sa.getAction();
			// 本局游戏后，获得的完成数
			int numberThisRound = 0;
			// 以下根据成就需求，按相应字段更新完成数
			if (pa.getAchievement().getType() == Constants.NUM_ONE) {
				SingleCharacterInfo cha = null;
				for (SingleCharacterInfo ci : player.getCharacterInfoList()) {
					if (pa.getAchievement().getCharacterId() == ci.getCharacterId()) {
						cha = ci;
					}
				}
				if (Constants.ACTION.GAMEWIN.getValue() == action) {
					if (player.getIsWiner() == 1) {// 如果赢了一盘游戏，进度+1
						numberThisRound = 1;
					}
				} else if (Constants.ACTION.GENERALKILL.getValue() == action) {// 普通人数击杀
					numberThisRound = player.getKill();
				} else if (Constants.ACTION.LEVELUP.getValue() == action) {
					if (player.getRank() >= pa.getAchievement().getNumber()) {// 达到指定等级
						numberThisRound = player.getRank();
					}
				} else if (Constants.ACTION.CONTROLKILL.getValue() == action) {// 控制击杀
					numberThisRound = player.getControlNum();
				} else if (Constants.ACTION.REVENGEKILL.getValue() == action) {// 复仇击杀
					numberThisRound = player.getRevengeNum();
				} else if (Constants.ACTION.CHARCTERKILL.getValue() == action && sa.getCharacterId() != 0) {// 杀死特定职业
					numberThisRound = player.getKillCharacter()[sa.getCharacterId()];
				} else if (Constants.ACTION.BLOODBAG.getValue() == action) {// 吃血包
					numberThisRound = player.getBottleHpNum();
				} else if (Constants.ACTION.BULLETBAG.getValue() == action) {// 吃弹药包
					numberThisRound = player.getBulletNum();
				} else if (Constants.ACTION.FIRSTKILL.getValue() == action) {// 首杀
					if (player.getIsFirstKill() == 1) {
						numberThisRound = 1;
					}
				} else if (Constants.ACTION.FIRSTDEAD.getValue() == action) {// 首死
					if (player.getIsFirstDead() == 1) {
						numberThisRound = 1;
					}
				} else if (Constants.ACTION.CONTINUEKILL.getValue() == action) {// 生存
					numberThisRound = player.getMaxKillNum();
				} else if (Constants.ACTION.FINISHGAME.getValue() == action) {// 完成比赛
					if (stageClearInfo.getType() != 3) {
						numberThisRound = 1;
					}
				}
			} else if (pa.getAchievement().getType() == Constants.NUM_THREE) {
				SingleCharacterInfo cha = null;
				for (SingleCharacterInfo ci : player.getCharacterInfoList()) {
					if (pa.getAchievement().getCharacterId() == ci.getCharacterId()) {
						cha = ci;
					}
				}
				if (cha != null) {
					if (Constants.ACTION.CONTROLKILL.getValue() == action) {// 控制击杀
						numberThisRound = cha.getControlNum();
					} else if (Constants.ACTION.REVENGEKILL.getValue() == action) {// 复仇击杀
						numberThisRound = cha.getRevengeNum();
					} else if (Constants.ACTION.KNIFEKILL.getValue() == action) {// 近战武器击杀
						numberThisRound = cha.getKnifeKill();
					} else if (Constants.ACTION.MAXDAMAGE.getValue() == action && player.getMaxDamageNumCharacter() == pa.getAchievement().getCharacterId()) {// 最大破坏量
						if (stageClearInfo.getType() != 5) {// BOSS战除外
							numberThisRound = player.getMaxDamageNum();
						}
					} else if (Constants.ACTION.ASSISTNUM.getValue() == action) {// 助攻
						numberThisRound = cha.getAssistNum();
					} else if (Constants.ACTION.BOOSTKILL.getValue() == action) {// 暴击杀人
						numberThisRound = cha.getBoostKill();
					} else if (Constants.ACTION.MAXALIVE.getValue() == action && player.getMaxLiveTimeCharacter() == pa.getAchievement().getCharacterId()) {// 存活超过20分钟
						if (player.getMaxLiveTime() / 60 >= 20) {
							numberThisRound = 1;
						}
					} else if (Constants.ACTION.CONTINUEKILL.getValue() == action && player.getMaxKillNumCharacter() == pa.getAchievement().getCharacterId()) {// 连杀10以上
						System.out.println(player.getMaxKillNum());
						if (player.getMaxKillNum() >= 10) {
							numberThisRound = 1;
						}
					} else if (Constants.ACTION.MVPNUM.getValue() == action && stageClearInfo.getMvpId() == p.getId()
							&& stageClearInfo.getMvpCharacterId() == pa.getAchievement().getCharacterId()) {// mvp
						numberThisRound = 1;
					} else if (Constants.ACTION.HEADSHOT.getValue() == action) {// 爆头击杀
						numberThisRound = cha.getHeadshot();
					} else if (Constants.ACTION.GRENADEKILL.getValue() == action) {// 雷杀
						numberThisRound = cha.getGrenadeKill();
					} else if (Constants.ACTION.SUSTAINKILL.getValue() == action) {// 余焰击杀
						numberThisRound = cha.getSustainKill();
					} else if (Constants.ACTION.MILESTONE.getValue() == action) {// 成就数量
						numberThisRound = count[pa.getAchievement().getCharacterId()];
					} else if (Constants.ACTION.HEALTHNUM.getValue() == action) {// 治疗量
						numberThisRound = cha.getHealthNum();
					} else if (Constants.ACTION.HEALTHMVP.getValue() == action) {// mvp
						if (cha.getKill() == 0 && stageClearInfo.getMvpId() == playerId) {
							numberThisRound = 1;
						}
					} else if (Constants.ACTION.HEALTHMAX.getValue() == action) {// 只用一条命达成XXX治疗量
						if(cha.getDead() == 0){
							numberThisRound = cha.getHealthNum();
						}
					}
				}
			}

			// 完成数等可能为0

			if (numberThisRound > 0) {
				boolean isFinished = updatePlayerAchievementByNumber(pa, numberThisRound);
				if (isFinished) {
					if (pa.getAchievement().getColor() == 1) {
						p.setBronze(p.getBronze() + 1);
					} else if (pa.getAchievement().getColor() == 2) {
						p.setSilver(p.getSilver() + 1);
					} else if (pa.getAchievement().getColor() == 3) {
						p.setGold(p.getGold() + 1);
					}
					if (null == pa.getAchievement().getTitle() || "".equals(pa.getAchievement().getTitle())) {
						p.setTotal((p.getTotal()) + 1);
					}
					if (pa.getAchievement().getType() == 3) {
						count[pa.getAchievement().getCharacterId()] += 1;
					}

					updatePlayerAchievementNotInStageClear(p, Constants.ACTION.MILESTONE.getValue(), pa.getAchievement().getCharacterId());
					finishedList.add(pa);
					String gift = achievementGift(p, pa);
					nosqlService.publishEvent(new AchievementEvent(DateUtil.getCurrentTimeStr(), p.getId(), p.getName(), pa));
					if (pa.getAchievement().getTitle() != null && !pa.getAchievement().getTitle().equals("")) {
						nosqlService.publishEvent(new TitleEvent(DateUtil.getCurrentTimeStr(), player.getId(), player.getName(), pa));
					}
				
					if (ConfigurationUtil.SWITCH_XUNLEI_LOG.getIsOn()) {
						LevelModeInfo levelInfo = getService.getLevelModeInfo(stageClearInfo.getLevelId());
						ServiceLocator.nosqlService.addXunleiLog("3.1" + Constants.XUNLEI_LOG_DELIMITER + stageClearInfo.getRid() + Constants.XUNLEI_LOG_DELIMITER
								+ stageClearInfo.getRid() + Constants.XUNLEI_LOG_DELIMITER + stageClearInfo.getChannelId() + Constants.XUNLEI_LOG_DELIMITER + p.getUserName()
								+ Constants.XUNLEI_LOG_DELIMITER + p.getName() + Constants.XUNLEI_LOG_DELIMITER + sa.getDescriptionCN() + Constants.XUNLEI_LOG_DELIMITER
								+ CommonUtil.simpleDateFormat.format(new Date()) + Constants.XUNLEI_LOG_DELIMITER + levelInfo.getDisplayNameCN() + Constants.XUNLEI_LOG_DELIMITER
								+ levelInfo.getType() + Constants.XUNLEI_LOG_DELIMITER + gift);
					}
				}
				// ServiceLocator.updateService.updatePlayerInfo(p);
				// ServiceLocator.missionLog.debug("[debug]after update player characters="+p.getCharacters());
			}
		}

		// 通知客户端完成的成就
		if (!finishedList.isEmpty()) {
			ServiceLocator.soClient.sendAchievementCompleted(finishedList, player.getId(),player.getName());
		}
	}

	// public void updatePlayerAchievement(PlayerAchievement pa, int
	// numberThisRound, Player p, List<PlayerAchievement> finishedList,
	// GeneralStageClearInfo stageClearInfo, StageClearPlayerInfo player,
	// SysAchievement sa) throws Exception{
	//
	// boolean isFinished = updatePlayerAchievementByNumber(pa,
	// numberThisRound);
	// if (isFinished) {
	// if(pa.getAchievement().getColor() ==1){
	// p.setBronze(p.getBronze()+1);
	// }else if(pa.getAchievement().getColor() ==2){
	// p.setSilver(p.getSilver()+1);
	// }else if(pa.getAchievement().getColor() ==3){
	// p.setGold(p.getGold()+1);
	// }
	// if(null == pa.getAchievement().getTitle() ||
	// "".equals(pa.getAchievement().getTitle())){
	// p.setTotal((p.getTotal()) + 1);
	// }
	// finishedList.add(pa);
	// String gift=achievementGift(p,pa);
	// //
	// ServiceLocator.missionLog.debug("[debug]after GIFT to player characters="+p.getCharacters());
	// nosqlService.publishEvent(new
	// AchievementEvent(DateUtil.getCurrentTimeStr(),p.getId(), p.getName(),
	// pa));
	// if(pa.getAchievement().getTitle() != null &&
	// !pa.getAchievement().getTitle().equals("")){
	// nosqlService.publishEvent(new
	// TitleEvent(DateUtil.getCurrentTimeStr(),player.getId(), player.getName(),
	// pa));
	// }
	// if(ConfigurationUtil.SWITCH_XUNLEI_LOG.getIsOn()){
	// LevelModeInfo
	// levelInfo=getService.getLevelModeInfo(stageClearInfo.getLevelId());
	// ServiceLocator.nosqlService.addXunleiLog("3.1"+Constants.XUNLEI_LOG_DELIMITER+stageClearInfo.getRid()
	// +Constants.XUNLEI_LOG_DELIMITER+stageClearInfo.getRid()+Constants.XUNLEI_LOG_DELIMITER+stageClearInfo.getChannelId()
	// +Constants.XUNLEI_LOG_DELIMITER+p.getUserName()
	// +Constants.XUNLEI_LOG_DELIMITER+p.getName()
	// +Constants.XUNLEI_LOG_DELIMITER+sa.getDescription()
	// +Constants.XUNLEI_LOG_DELIMITER+CommonUtil.simpleDateFormat.format(new
	// Date())
	// +Constants.XUNLEI_LOG_DELIMITER+levelInfo.getDisplayName()
	// +Constants.XUNLEI_LOG_DELIMITER+levelInfo.getType()
	// +Constants.XUNLEI_LOG_DELIMITER+gift);
	// }
	// }
	// ServiceLocator.updateService.updatePlayerInfo(p);
	// //
	// ServiceLocator.missionLog.debug("[debug]after update player characters="+p.getCharacters());
	//
	// }
	public void updatePlayerAchievement(PlayerAchievement pa, int numberThisRound, Player p, List<PlayerAchievement> finishedList, GeneralStageClearInfo stageClearInfo,
			StageClearPlayerInfo player, SysAchievement sa) throws Exception {

		boolean isFinished = updatePlayerAchievementByNumber(pa, numberThisRound);
		if (isFinished) {
			if (pa.getAchievement().getColor() == 1) {
				p.setBronze(p.getBronze() + 1);
			} else if (pa.getAchievement().getColor() == 2) {
				p.setSilver(p.getSilver() + 1);
			} else if (pa.getAchievement().getColor() == 3) {
				p.setGold(p.getGold() + 1);
			}
			if (null == pa.getAchievement().getTitle() || "".equals(pa.getAchievement().getTitle())) {
				p.setTotal((p.getTotal()) + 1);
			}
			finishedList.add(pa);
			
			String gift = achievementGift(p, pa);
			// ServiceLocator.missionLog.debug("[debug]after GIFT to player characters="+p.getCharacters());
			nosqlService.publishEvent(new AchievementEvent(DateUtil.getCurrentTimeStr(), p.getId(), p.getName(), pa));
			if (pa.getAchievement().getTitle() != null && !pa.getAchievement().getTitle().equals("")) {
				nosqlService.publishEvent(new TitleEvent(DateUtil.getCurrentTimeStr(), player.getId(), player.getName(), pa));
			}
			if (ConfigurationUtil.SWITCH_XUNLEI_LOG.getIsOn()) {
				LevelModeInfo levelInfo = getService.getLevelModeInfo(stageClearInfo.getLevelId());
				ServiceLocator.nosqlService.addXunleiLog("3.1" + Constants.XUNLEI_LOG_DELIMITER + stageClearInfo.getRid() + Constants.XUNLEI_LOG_DELIMITER
						+ stageClearInfo.getRid() + Constants.XUNLEI_LOG_DELIMITER + stageClearInfo.getChannelId() + Constants.XUNLEI_LOG_DELIMITER + p.getUserName()
						+ Constants.XUNLEI_LOG_DELIMITER + p.getName() + Constants.XUNLEI_LOG_DELIMITER + sa.getDescription() + Constants.XUNLEI_LOG_DELIMITER
						+ CommonUtil.simpleDateFormat.format(new Date()) + Constants.XUNLEI_LOG_DELIMITER + levelInfo.getDisplayName() + Constants.XUNLEI_LOG_DELIMITER
						+ levelInfo.getType() + Constants.XUNLEI_LOG_DELIMITER + gift);
			}
		}
		ServiceLocator.updateService.updatePlayerInfo(p);
		// ServiceLocator.missionLog.debug("[debug]after update player characters="+p.getCharacters());

	}

	/**
	 * 过关结算时计算玩家的任务
	 * 
	 * @param p
	 * @param player
	 * @param stageClearInfo
	 * @param boostKillNum
	 *            暴击击杀次数
	 * @throws Exception
	 */
	public void updatePlayerMissionInStageClear(Player p, StageClearPlayerInfo player, GeneralStageClearInfo stageClearInfo, int boostKillNum) throws Exception {
		List<PlayerMission> playerMissions = getService.getPlayerMissionList(p.getId());
		List<PlayerMission> finishedMissionList = new ArrayList<PlayerMission>();
		List<PlayerMission> updateMissionList = new ArrayList<PlayerMission>();
		for (PlayerMission pm : playerMissions) {
			if (0 == pm.getStatus()) {// 已完成任务不做处理
				SysMission sysMission = getService.getSysMissionById(pm.getSysMissionId());
				int action = sysMission.getAction();
				int completeNum = 0;// 本任务在本局完成的数量
				if (Constants.ACTION_M.MAP1_MODEL1.getValue() == action) {
					if (38 == stageClearInfo.getLevelId()) {// 匹配地图模式
//						if (player.getIsWiner() == 1) {// 完成了一场比赛
							completeNum = 1;
//						}
					}
				} else if (Constants.ACTION_M.MAP2_MODEL1.getValue() == action) {
					if (29 == stageClearInfo.getLevelId()) {// 风车小镇的占点
//						if (player.getIsWiner() == 1) {
							completeNum = 1;
//						}
					}
				} else if (Constants.ACTION_M.MAP3_MODEL1.getValue() == action) {
					if (43 == stageClearInfo.getLevelId()) {// 乡间伐木场占点
//						if (player.getIsWiner() == 1) {
							completeNum = 1;
//						}
					}
				} else if (Constants.ACTION_M.MAP2_MODEL2.getValue() == action) {
					if (28 == stageClearInfo.getLevelId()) {// 风车小镇的团战
//						if (player.getIsWiner() == 1) {
							completeNum = 1;
//						}
					}
				} else if (Constants.ACTION_M.MAP3_MODEL2.getValue() == action) {
					if (30 == stageClearInfo.getLevelId()) {// 乡间伐木场团战
//						if (player.getIsWiner() == 1) {
							completeNum = 1;
//						}
					}
				} else if (Constants.ACTION_M.MAP4_MODEL2.getValue() == action) {
					if (51 == stageClearInfo.getLevelId()) {// 盛夏农场的团战
//						if (player.getIsWiner() == 1) {
							completeNum = 1;
//						}
					}
				} else if (Constants.ACTION_M.MAP5_MODEL3.getValue() == action) {
					if (36 == stageClearInfo.getLevelId()) {// 法老神殿的推车
//						if (player.getIsWiner() == 1) {
							completeNum = 1;
//						}
					}
				} else if (Constants.ACTION_M.MAP6_MODEL3.getValue() == action) {
					if (39 == stageClearInfo.getLevelId()) {// 神庙遗迹的推车
//						if (player.getIsWiner() == 1) {
							completeNum = 1;
//						}
					}
				} else if (Constants.ACTION_M.MAP6_MODEL4.getValue() == action) {
					if (47 == stageClearInfo.getLevelId()) {// 神庙遗迹的歼灭
//						if (player.getIsWiner() == 1) {
							completeNum = 1;
//						}
					}
				} else if (Constants.ACTION_M.MAP4_MODEL4.getValue() == action) {
					if (48 == stageClearInfo.getLevelId()) {// 盛夏农场的歼灭
//						if (player.getIsWiner() == 1) {
							completeNum = 1;
//						}
					}
				}else if (Constants.ACTION_M.MAP1_MODEL7.getValue() == action) {
					if (96 == stageClearInfo.getLevelId()) {//阿拉伯古城的爆破模式
//						if (player.getIsWiner() == 1) {
							completeNum = 1;
//						}
					}
				}else if (Constants.ACTION_M.MAP1_MODEL5.getValue() == action) {
					if (67 == stageClearInfo.getLevelId()) {//阴霾教堂的BOSS模式
//						if (player.getIsWiner() == 1) {
							completeNum = 1;
//						}
					}
				}else if (Constants.ACTION_M.MAP1_MODEL8.getValue() == action) {
					if (104 == stageClearInfo.getLevelId()) {//九龙街区的英雄模式
//						if (player.getIsWiner() == 1) {
							completeNum = 1;
//						}
					}
				}else if (Constants.ACTION_M.MAP1_MODEL9.getValue() == action) {
					if (119 == stageClearInfo.getLevelId()) {//生化研究所的生化模式
//						if (player.getIsWiner() == 1) {
							completeNum = 1;
//						}
					}
				}else if (Constants.ACTION_M.MAP32_MODEL0.getValue() == action) {
					if (117 == stageClearInfo.getLevelId()) {//激战麦田团战
//						if (player.getIsWiner() == 1) {
							completeNum = 1;
//						}
					}
				}else if (Constants.ACTION_M.MAP33_MODEL0.getValue() == action) {
					if (74 == stageClearInfo.getLevelId()) {//埃及古墓团战
//						if (player.getIsWiner() == 1) {
							completeNum = 1;
//						}
					}
				}else if (Constants.ACTION_M.WIN.getValue() == action) {// 赢得胜利
					if (player.getIsWiner() == 1) {
						completeNum = 1;
					}
				} else if (Constants.ACTION_M.COMPLETE.getValue() == action) {// 完成胜利
				// if (player.getIsWiner() == 1) {
					completeNum = 1;
					// }
				} 
				
				else if(Constants.ACTION_M.MAP34_MODEL3.getValue()==action){
					if(178== stageClearInfo.getLevelId()){ //金色农场的推车模式战役
						completeNum = 1;
					}
				}else if(Constants.ACTION_M.MAP35_MODEL13.getValue()==action){
					if(179== stageClearInfo.getLevelId()){ //地心遗迹的道具战模式战役
						completeNum = 1;
					}
				}else if(Constants.ACTION_M.MAP36_MODEL13.getValue()==action){
					if(175== stageClearInfo.getLevelId()){ //北斗导航战的道具战模式战役
						completeNum = 1;
					}
				} else if (Constants.ACTION_M.GENERALKILL.getValue() == action) {// 击杀敌人
					completeNum = player.getKill();
				} else if (Constants.ACTION_M.AWARDKILL.getValue() == action) {// 赏金击杀
					completeNum = player.getControlNum();
				} else if (Constants.ACTION_M.REVENGEKILL.getValue() == action) {// 复仇击杀
					completeNum = player.getRevengeNum();
				} else if (Constants.ACTION_M.CLOSEKILL.getValue() == action) {// 近身击杀
					completeNum = player.getKnifeKill();
				} else if (Constants.ACTION_M.DOUBLEDKILL.getValue() == action) {// 暴击击杀
					completeNum = boostKillNum;
				} else if (Constants.ACTION_M.CONTINUEKILL.getValue() == action) {// 完成连杀2人
					if (player.getMaxKillNum() >= 2) {
						completeNum = 1;
					}
				} else if (Constants.ACTION_M.HELPKILL.getValue() == action) {// 助攻
					completeNum = player.getAssistNum();
				} else if (Constants.ACTION_M.MVP.getValue() == action) {// MVP
					if (stageClearInfo.getMvpId() == p.getId()) {
						completeNum = 1;
					}
				} else if (Constants.ACTION_M.MAXD.getValue() == action) {// 最大破坏力1200以上
					if (player.getMaxDamageNum() >= 1200) {
						completeNum = 1;
					}
				}

				if (completeNum > 0) {
					pm.setNumber((pm.getNumber() + completeNum));
					if (pm.getNumber() >= pm.getTarget()) {// 任务完成
						pm.setStatus(1);
						finishedMissionList.add(pm);
					} else {
						updateMissionList.add(pm);
					}
				}
			}
		}
		if (updateMissionList.size() > 0) {// 更新的任务
			for (PlayerMission pmis : updateMissionList) {
				updatePlayerMission(pmis);
			}
			String key = CacheUtil.oPlayerMissionList(p.getId());
			mcc.delete(key);
		}
		if (finishedMissionList.size() > 0) {// 有完成的任务
			boolean finish_everyday_mission = false;
			for (PlayerMission pmis : finishedMissionList) {
				ServiceLocator.missionLog.debug("PlayerName=" + p.getName() + " PlayerId=" + p.getId() + " finshed a mission missionId=" + pmis.getSysMissionId() + " type="
						+ pmis.getSysMission().getType());
				// ServiceLocator.deleteService.deletePlayerMission(pmis);
				updatePlayerMission(pmis);
				// FIXME if not have task activity
				awardTaskActivity(p.getId());
				// Join奖品
				SysMission m = getService.getSysMissionById(pmis.getSysMissionId());
				pmis.setDescription(CommonUtil.messageFormat(m.getDescription(), pmis.getTarget()));// 拼接描述信息
				// String[] normalItems = m.getNormalItems().split(",");
				// List<SysItem> normalItemList = new ArrayList<SysItem>();
				// for(String s : normalItems){
				// normalItemList.add(getService.getSysItemByItemId(StringUtil.toInt(s)));
				// }
				// m.setNormalItemList(normalItemList);
				// if(p.getIsVip() == 1){
				// String[] vipItems = m.getVipItems().split(",");
				// List<SysItem> vipItemList = new ArrayList<SysItem>();
				// for(String s : vipItems){
				// vipItemList.add(getService.getSysItemByItemId(StringUtil.toInt(s)));
				// }
				// m.setVipItemList(vipItemList);
				// }
				pmis.setSysMission(m);
				Integer type = pmis.getType();
				if(type == 0){
					finish_everyday_mission = true;
				}
				//pid rank type pmid
				if(ServiceLocator.missionCompleteLog.isDebugEnabled()){
					ServiceLocator.missionCompleteLog.debug(String.format("%s\t%s\t%s\t%s", 
							player.getId(),player.getRank(),type,pmis.getId()));
				}
			}
			mcc.delete(CacheUtil.oPlayerMissionList(p.getId()));
			String msg = Converter.completeMissionList(finishedMissionList);
			soClient.puchCMDtoClient(player.getName(), msg);
			if(finish_everyday_mission){
				updatePlayerGrowthMission(p, GrowthMissionType.FIRST_FINISH_EVERYDAY_MISSION);
			}
			pushModuleStatusChanage(p, ModuleStatusIndex.Mission);
			//TODO 未尽的战斗			
//			for (PlayerMission playerMission : playerMissions) {
//				if((playerMission.getType() == 0&&playerMission.getStatus() == 0)||(playerMission.getSysMissionId() == GetService.DAILY_MISSION_LAST)){
//					return;
//				}
//			}
//			{
//				SysMission m = sysMissionDao.getAllSysMissionMap().get(GetService.DAILY_MISSION_LAST);
//				PlayerMission temp = new PlayerMission();
//				temp.setType(m.getType());
//				temp.setPlayerId(player.getId());
//				temp.setSysMissionId(m.getId());
//				temp.setTarget(m.getNormalTarget());
//				temp.setStatus(0);
//				temp.setCreateTime(new Date());
//				temp.setNumber(0);
//				temp.setDescription(CommonUtil.messageFormat(m.getDescription(), temp.getTarget()));
//				temp.setSysMission(m);
//				createService.createPlayerMission(temp);
//			}
		}
	}

	public void setEndNewPlayerAchievement(final Player player, final int playerAction, final int number) throws Exception {
		// Runnable task = new Runnable(){
		// @Override
		// public void run() {
		// list;
		// try {
		List<String> list = updatePlayerAchievementNotInStageClear(player, playerAction, number);
		if (ConfigurationUtil.SWITCH_XUNLEI_LOG.getIsOn()) {
			String giftStr = "";
			for (String str : list) {
				giftStr += str;
			}
			ServiceLocator.nosqlService.addXunleiLog("3.1" + Constants.XUNLEI_LOG_DELIMITER + 0 + Constants.XUNLEI_LOG_DELIMITER + 0 + Constants.XUNLEI_LOG_DELIMITER + 0
					+ Constants.XUNLEI_LOG_DELIMITER + player.getUserName() + Constants.XUNLEI_LOG_DELIMITER + player.getName() + Constants.XUNLEI_LOG_DELIMITER
					+ CommonMsg.TONGZIJUNBIYE + Constants.XUNLEI_LOG_DELIMITER + CommonUtil.simpleDateFormat.format(new Date()) + Constants.XUNLEI_LOG_DELIMITER
					+ CommonMsg.NEW_PLAYER_G + Constants.XUNLEI_LOG_DELIMITER + 3 + Constants.XUNLEI_LOG_DELIMITER + giftStr);
		}
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		// }
		//
		// };
		// ServiceLocator.asynTakService.submit(task);
	}

	// 一些不在过关结算统计的成就
	public List<String> updatePlayerAchievementNotInStageClear(Player player, int playerAction, int number) throws Exception {
		List<String> giftList = new ArrayList<String>();
		int playerId = player.getId();
		int[] count = { 0, 0, 0, 0, 0, 0, 0 ,0};//TODO 新成就
		// FIXME:xiaofei synchronize
		List<PlayerAchievement> paList = getService.getPlayerAchievementVisible(playerId);

		List<PlayerAchievement> finishedList = new ArrayList<PlayerAchievement>();

		for (PlayerAchievement pa : paList) {
			if (pa.getStatus() != Constants.NUM_ZERO) {
				continue;
			}

			SysAchievement sa = pa.getAchievement();

			int action = sa.getAction();

			// 本局游戏后，获得的完成数
			int numberThisRound = 0;
			// 以下根据成就需求，按相应字段更新完成数
			if (pa.getAchievement().getType() == Constants.NUM_ONE) {
				if (playerAction == Constants.ACTION.DALABA.getValue() && Constants.ACTION.DALABA.getValue() == action) {
					numberThisRound = 1;
				} else if (playerAction == Constants.ACTION.FRIENDS.getValue() && Constants.ACTION.FRIENDS.getValue() == action) {
					numberThisRound = number;
				} else if (playerAction == Constants.ACTION.USECR.getValue() && action == Constants.ACTION.USECR.getValue()) {
					numberThisRound = number;
				} else if (playerAction == Constants.ACTION.GIFTCR.getValue() && action == Constants.ACTION.GIFTCR.getValue()) {
					numberThisRound = number;
				} else if (playerAction == Constants.ACTION.NEWPLAYER.getValue() && action == Constants.ACTION.NEWPLAYER.getValue()) {
					numberThisRound = number;
				}
			} else if (pa.getAchievement().getType() == Constants.NUM_THREE) {
				if (pa.getAchievement().getCharacterId() != Constants.NUM_ZERO && pa.getAchievement().getCharacterId() != number) {
					continue;
				}
				if (playerAction == Constants.ACTION.GETWEAPON.getValue() && action == Constants.ACTION.GETWEAPON.getValue()) {
					numberThisRound = 1;
				} else if (playerAction == Constants.ACTION.GETKNIFE.getValue() && action == Constants.ACTION.GETKNIFE.getValue()) {
					numberThisRound = 1;
				} else if (playerAction == Constants.ACTION.GETCOSTUME.getValue() && action == Constants.ACTION.GETCOSTUME.getValue()) {
					numberThisRound = 1;
				} else if (Constants.ACTION.MILESTONE.getValue() == action) {// 成就数量
				// if(pa.getAchievement().getCharacterId() == number){
				// numberThisRound = 1;
				// }
					numberThisRound = count[pa.getAchievement().getCharacterId()];
				}
			}

			// 完成数等可能为0

			if (numberThisRound > 0) {
				boolean isFinished = updatePlayerAchievementByNumber(pa, numberThisRound);
				if (isFinished) {
					if (pa.getAchievement().getColor() == 1) {
						player.setBronze(player.getBronze() + 1);
					} else if (pa.getAchievement().getColor() == 2) {
						player.setSilver(player.getSilver() + 1);
					} else if (pa.getAchievement().getColor() == 3) {
						player.setGold(player.getGold() + 1);
					}
					if (null == pa.getAchievement().getTitle() || "".equals(pa.getAchievement().getTitle())) {
						player.setTotal((player.getTotal()) + 1);
					}
					if (pa.getAchievement().getType() == 3) {
						count[pa.getAchievement().getCharacterId()] += 1;
					}
					// updatePlayerAchievementNotInStageClear(player,
					// Constants.ACTION.MILESTONE.getValue(),
					// pa.getAchievement().getCharacterId());
					finishedList.add(pa);
					String gift = achievementGift(player, pa);
					nosqlService.publishEvent(new AchievementEvent(DateUtil.getCurrentTimeStr(), player.getId(), player.getName(), pa));
					if (pa.getAchievement().getTitle() != null && !pa.getAchievement().getTitle().equals("")) {
						nosqlService.publishEvent(new TitleEvent(DateUtil.getCurrentTimeStr(), player.getId(), player.getName(), pa));
					}
			
					giftList.add(gift);
					if (ConfigurationUtil.SWITCH_XUNLEI_LOG.getIsOn()) {
						if (playerAction == Constants.ACTION.NEWPLAYER.getValue() && action == Constants.ACTION.NEWPLAYER.getValue()) {
							// do nothing
						} else {
							ServiceLocator.nosqlService.addXunleiLog("3.1" + Constants.XUNLEI_LOG_DELIMITER + 0 + Constants.XUNLEI_LOG_DELIMITER + 0
									+ Constants.XUNLEI_LOG_DELIMITER + 0 + Constants.XUNLEI_LOG_DELIMITER + player.getUserName() + Constants.XUNLEI_LOG_DELIMITER
									+ player.getName() + Constants.XUNLEI_LOG_DELIMITER + sa.getDescription() + Constants.XUNLEI_LOG_DELIMITER
									+ CommonUtil.simpleDateFormat.format(new Date()) + Constants.XUNLEI_LOG_DELIMITER + "" + Constants.XUNLEI_LOG_DELIMITER + 0
									+ Constants.XUNLEI_LOG_DELIMITER + gift);
						}
					}
				}
			}
		}
		ServiceLocator.updateService.updatePlayerInfo(player);

		// 通知客户端完成的成就
		if (!finishedList.isEmpty()) {
			ServiceLocator.soClient.sendAchievementCompleted(finishedList,player.getId(), player.getName());
		}
		return giftList;
	}
	/**
	 * 合成系统成就
	 * @param player
	 * @param playerAction
	 * @param number
	 * @return
	 * @throws Exception
	 */
	public List<String> updatePlayerAchievementInCombine(Integer playerId, int playerAction, int number, Constants.COMBINE_TYPE combineType) throws Exception{
		List<String> giftList = new ArrayList<String>();
		Player player = getService.getPlayerById(playerId);
//		int[] count = { 0, 0, 0, 0, 0, 0, 0 };
		List<PlayerAchievement> paList = getService.getPlayerAchievementVisible(playerId);
		List<PlayerAchievement> finishedList = new ArrayList<PlayerAchievement>();
		for (PlayerAchievement pa : paList) {
			if (pa.getStatus() != Constants.NUM_ZERO) {
				continue;
			}
			SysAchievement sa = pa.getAchievement();
			int action = sa.getAction();
			// 本次合成后，获得的完成数
			int numberThisRound = 0;
			// 以下根据成就需求，按相应字段更新完成数
			if(playerAction==action){
				if (pa.getAchievement().getType() == Constants.NUM_ONE) {
					switch (combineType) {
					case STRENGTH:
						Constants.COMBINE_STRENGTH_ACTION[] combineActions = Constants.COMBINE_STRENGTH_ACTION.values();
						for(Constants.COMBINE_STRENGTH_ACTION ca : combineActions){
							if(action == ca.getValue()){
								numberThisRound = number;
								break;
							}
						}
						break;
					case CONVERT:
						Constants.COMBINE_CONVERT_ACTION[] convertActions = Constants.COMBINE_CONVERT_ACTION.values();
						for(Constants.COMBINE_CONVERT_ACTION ca : convertActions){
							if(action == ca.getValue()){
								numberThisRound = number;
								break;
							}
						}
						break;
					case INSERT:
						Constants.COMBINE_INSERT_ACTION[] insertActions = Constants.COMBINE_INSERT_ACTION.values();
						for(Constants.COMBINE_INSERT_ACTION ca : insertActions){
							if(action == ca.getValue()){
								numberThisRound = number;
								break;
							}
						}
						break;
					case SLOT:
						Constants.COMBINE_SLOT_ACTION[] slotActions = Constants.COMBINE_SLOT_ACTION.values();
						for(Constants.COMBINE_SLOT_ACTION ca : slotActions){
							if(action == ca.getValue()){
								numberThisRound = number;
								break;
							}
						}
						break;

					default:
						break;
					}
					
				}else if(pa.getAchievement().getType() == Constants.NUM_THREE){
				} 
			}
			
			// 完成数等可能为0
			if (numberThisRound > 0) {
				
				boolean isFinished = updatePlayerAchievementByNumber(pa, numberThisRound);
				if (isFinished) {
					if (pa.getAchievement().getColor() == 1) {
						player.setBronze(player.getBronze() + 1);
					} else if (pa.getAchievement().getColor() == 2) {
						player.setSilver(player.getSilver() + 1);
					} else if (pa.getAchievement().getColor() == 3) {
						player.setGold(player.getGold() + 1);
					}
					if (null == pa.getAchievement().getTitle() || "".equals(pa.getAchievement().getTitle())) {
						player.setTotal((player.getTotal()) + 1);
					}
//					if (pa.getAchievement().getType() == 3) {
//						count[pa.getAchievement().getCharacterId()] += 1;
//					}
					finishedList.add(pa);
					String gift = achievementGift(player, pa);
					nosqlService.publishEvent(new AchievementEvent(DateUtil.getCurrentTimeStr(), player.getId(), player.getName(), pa));
					if (pa.getAchievement().getTitle() != null && !pa.getAchievement().getTitle().equals("")) {
						nosqlService.publishEvent(new TitleEvent(DateUtil.getCurrentTimeStr(), player.getId(), player.getName(), pa));
					}
					
					giftList.add(gift);
					if (ConfigurationUtil.SWITCH_XUNLEI_LOG.getIsOn()) {
							ServiceLocator.nosqlService.addXunleiLog("3.1" + Constants.XUNLEI_LOG_DELIMITER + 0 + Constants.XUNLEI_LOG_DELIMITER + 0
									+ Constants.XUNLEI_LOG_DELIMITER + 0 + Constants.XUNLEI_LOG_DELIMITER + player.getUserName() + Constants.XUNLEI_LOG_DELIMITER
									+ player.getName() + Constants.XUNLEI_LOG_DELIMITER + sa.getDescription() + Constants.XUNLEI_LOG_DELIMITER
									+ CommonUtil.simpleDateFormat.format(new Date()) + Constants.XUNLEI_LOG_DELIMITER + "" + Constants.XUNLEI_LOG_DELIMITER + 0
									+ Constants.XUNLEI_LOG_DELIMITER + gift);
					}
				}
				
				updatePlayerAchievementActivityNotInStageClear(player,playerAction,pa.getNumber() + numberThisRound);
			}
		}
		ServiceLocator.updateService.updatePlayerInfo(player);
		// 通知客户端完成的成就
		if (!finishedList.isEmpty()) {
			ServiceLocator.soClient.sendAchievementCompleted(finishedList,player.getId(), player.getName());
		}
		return giftList;
	}
	private boolean updatePlayerAchievementByNumber(PlayerAchievement pa, int gainNumber) throws Exception {
		System.out.println(pa.getAchievement().getDescriptionCN()+"   "+gainNumber);
		boolean isFinished = false;
		// 如果统计方式是要最大，并且并未达到目标，则不用更新任何东西
		if (Constants.STATTYPE.MAX.getValue() == pa.getAchievement().getStatType() && pa.getAchievement().getNumber() > gainNumber) {
			return isFinished;
		}
		// 这里要么统计方式为累积，要么为已经达到目标的统计最大成就，更新状态
		pa.setNumber(pa.getNumber() + gainNumber);
		if (pa.getAchievement().getNumber() <= pa.getNumber()) {
			pa.setNumber(pa.getAchievement().getNumber());
			pa.setStatus(Constants.NUM_ONE);
			isFinished = true;
		}
		playerAchievementDao.updatePlayerAchievement(pa);
		String key = CacheUtil.oPlayerAchievementList(pa.getPlayerId());
		mcc.delete(key);
		return isFinished;
	}

	public String achievementGift(final Player player, PlayerAchievement pa) throws Exception {
		StringBuilder names = new StringBuilder("");
		SysItem sysItem = getService.getSysItemByItemId(4479);// 送勋章
		Payment payment = new Payment();
		payment.setUnitType(1);
		
		//防沉迷
		int fcm_time=0;
		try {
			String fcm_time_value=ServiceLocator.nosqlService.getNosql().get(NosqlKeyUtil.FCMTime(player.getId()));
			if(fcm_time_value!=null){
				fcm_time=Integer.parseInt(fcm_time_value);
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int baseNum=1;
		if(fcm_time>=300){
			baseNum=300;
		}else if(fcm_time>=180){
			baseNum=2;
		}
		
		switch (pa.getAchievement().getColor()) {
		case Constants.NUM_ONE:
			payment.setUnit(1/baseNum);
			break;
		case Constants.NUM_TWO:
			payment.setUnit(1/baseNum);
			break;
		case Constants.NUM_THREE:
			payment.setUnit(2/baseNum);
			break;
		}
//		System.out.println("+++++++++   dd"+pa.getAchievement().getDescriptionCN()+"  "+payment.getUnit()+"  "+baseNum+"  "+fcm_time);
		Object[] objs = new Object[1];
		objs[0] = pa.getAchievement().getDescription();

		StringBuilder ids = new StringBuilder("");
		names.append(sysItem.getDisplayName()).append(",");
		ids.append(sysItem.getId()).append(",");
		ServiceLocator.createService.awardAchievement(player, sysItem, payment);
		if (ConfigurationUtil.SWITCH_XUNLEI_LOG.getIsOn()) {
			nosqlService.addXunleiLog("7.4"
					+ Constants.XUNLEI_LOG_DELIMITER + player.getUserName()
					+ Constants.XUNLEI_LOG_DELIMITER + player.getName()
					+ Constants.XUNLEI_LOG_DELIMITER + 1
					+ Constants.XUNLEI_LOG_DELIMITER + payment.getUnit()
					+ Constants.XUNLEI_LOG_DELIMITER+ 11
					+ Constants.XUNLEI_LOG_DELIMITER+ CommonUtil.simpleDateFormat.format(new Date())
					);
		}
		log.debug("Achievement/Finished:\t" + pa.getAchievement().getDescription() + "\t" + player.getName()+"\t"+sysItem.getName()+"\t"+payment.getUnit()+"\t"+payment.getUnitType());
//		createService.sendSystemMail(player, CommonMsg.GIFT_EMAIL_SYS, CommonUtil.messageFormat(CommonMsg.FINISH_ACHIEVEMENT_WITH_GIFT, objs), ids.toString());
		return names.toString();
	}

	// ===============================================================================
	// activity service
	// ===============================================================================
	public int updatePlayerActivity(int type, int playerId, Date loginTime, int value, int newLevel, StageClearPlayerInfo playerInfo, GeneralStageClearInfo stageClearInfo)
			throws Exception {
		switch (type) {
		case 1:
			awardLevelActivity(playerId, value, newLevel);
			break;
		case 2:
			updatePlayerActivityGift(playerId,type);
			break;
		case 3:
			updatePlayerActivityGift(playerId,type);
			break;
		case 4:
			return updatePlayerActivityParameter(playerId,type);// 无需领奖
		case 5:
			awardAchievementActivity(playerId, playerInfo, stageClearInfo);
			break;
		case 8:
			awardTaskActivity(playerId);
			break;
		case 6:
			updatePlayerActivityTarget(playerId, value, type);
			break;
		case 7:
			updatePlayerActivityTarget(playerId, value, type);
			break;
		// case 9:
		// return discountActivity(type);
		case 10:
			updatePlayerActivityTarget(playerId, value, type);
			break;
		case 11:
			awradHour2hour(playerId, value, loginTime);
			break;
		case 13:
			return updatePlayerActivityParameter(playerId, type);
		case 14:
			return updatePlayerActivityParameter(playerId, type);
		case 15:
			updatePlayerActivityGift(playerId,type);
			break;
		case 16:
			return updatePlayerActivityParameter(playerId, type);
		case 17:
			return updatePlayerActivityParameter(playerId, type);
		case 18:
			updatePlayerActivityTarget(playerId,value, type);
			 break;
		case 19:
			updatePlayerActivityTarget(playerId,value, type);
			 break;
		case 20:
			updatePlayerActivityTarget(playerId,value, type);
			 break;
		case 21:
			updatePlayerActivityTarget(playerId,value, type);
			 break;
		case 22:
			return updatePlayerActivityParameter(playerId, type);
		case 23:
			return updatePlayerActivityParameter(playerId, type);
		case 24:
			updatePlayerActivityTarget(playerId, value, type);
			break;
		case 25:
			return updatePlayerActivityParameter(playerId,type);
		case 28:// GM活动，赠送原石，黑铁和黑晶石功能
			//updatePlayerActivityTarget(playerId, value, type);
			break;
		default:
			log.warn("not find the type=" + type);
			break;
		}
		mcc.delete(CacheUtil.oPlayerActivityList(playerId));
		return 0;
	}
	/**
	 * @param playerId
	 * @param value
	 * @param action TODO
	 * @throws Exception
	 * activity have one value 
	 * add the value or adjust the value
	 * player need to get activity gift himself 
	 * complete once
	 */
	private void updatePlayerActivityTarget(int playerId, int value, int action) throws Exception{
		Player player = getService.getPlayerById(playerId);
		List<PlayerActivity> list = getService.getPlayerActivityList(playerId, 0);
		List<PlayerActivity> completeList = new ArrayList<PlayerActivity>();
		SysActivity activity = null;
		for (PlayerActivity pa : list) {
			if (pa.getStatus() == 0 && pa.getAction() == action){
				activity = pa.getSysActivity();
				if(pa.getAction() == Constants.ACTION_ACTIVITY.CHARGE_FC.getValue()||pa.getAction() == Constants.ACTION_ACTIVITY.PAY_FC.getValue()
						||pa.getAction() == Constants.ACTION_ACTIVITY.KILL_BIOCHEMICAL.getValue()||pa.getAction() == Constants.ACTION_ACTIVITY.KILL_BOSS_ACTIVITY.getValue()
						||pa.getAction() == Constants.ACTION_ACTIVITY.KILL_BOSS2_ACTIVITY.getValue()
						||pa.getAction() == Constants.ACTION_ACTIVITY.BOSS_KILL_ACTIVITY.getValue()||pa.getAction() == Constants.ACTION_ACTIVITY.KILL_ACTIVITY.getValue()){//add the values
					pa.setNumber(pa.getNumber() + value);
					if (pa.getNumber() >= activity.getTargetNum()) {
						pa.setStatus(1);
						completeList.add(pa);
					}
					
				}else if(pa.getAction() == Constants.ACTION_ACTIVITY.STRENGTH_TO_RANK.getValue()){//adjust the value once
					if (value >= activity.getTargetNum()) {
						pa.setStatus(1);
						completeList.add(pa);
						pa.setNumber(value);
					}
				}
				updatePlayerActivity(pa);
			}
		}
		if (completeList.size() > 0) {
			soClient.puchCMDtoClient(player.getName(), Converter.completeActivity(completeList));
		}
	}
	/**
	 * @param playerId
	 * @param action TODO
	 * @return int
	 * @throws Exception
	 * no gift but return int 
	 * completed more than once
	 * 25 16 17 11 
	 */
	private int updatePlayerActivityParameter(int playerId, int action) throws Exception {
		List<PlayerActivity> list = getService.getPlayerActivityList(playerId, 0);
		for (PlayerActivity pa : list) {
			if(pa.getAction() == action){
				if (pa.getAction() == Constants.ACTION_ACTIVITY.STRENGTH_NON_LOSE.getValue()||pa.getAction() == Constants.ACTION_ACTIVITY.STRENGTH_ADD.getValue()
						||pa.getAction() == Constants.ACTION_ACTIVITY.STRENGTH_MUST.getValue()) {
					//16 17 23
//					updatePlayerActivity(pa);
//					pa.setStatus(1);
//					pa.setAward(1);
					if(pa.getAction() == Constants.ACTION_ACTIVITY.STRENGTH_NON_LOSE.getValue()||pa.getAction() == Constants.ACTION_ACTIVITY.STRENGTH_ADD.getValue()){
						Calendar c=Calendar.getInstance();
						int h=c.get(Calendar.HOUR_OF_DAY);
						if(pa.getSysActivity().getValue()>h||pa.getSysActivity().getTargetNum()<=h){
							return 0;
						}
					
					}
					return pa.getSysActivity().getUnit();
				}else if(pa.getAction() == Constants.ACTION_ACTIVITY.IGNORE_DEAD.getValue()||pa.getAction() == Constants.ACTION_ACTIVITY.IGNORE_LOSE.getValue()
						||pa.getAction() == Constants.ACTION_ACTIVITY.OPEN_LUCKYPACKAGE.getValue()||pa.getAction() == Constants.ACTION_ACTIVITY.TEAM_BATTLE_ADD.getValue()){
					//13 22 14 25
//					pa.setStatus(1);
//					pa.setAward(1);
//					updatePlayerActivity(pa);
					if(pa.getAction() == Constants.ACTION_ACTIVITY.TEAM_BATTLE_ADD.getValue()){
						Calendar c=Calendar.getInstance();
						int h=c.get(Calendar.HOUR_OF_DAY);
						if(pa.getSysActivity().getValue()>h||pa.getSysActivity().getTargetNum()<=h){
							return 0;
						}
					}
					return 1;
				}else if(pa.getAction() == Constants.ACTION_ACTIVITY.ONLINE_ACTIVITY.getValue()){
					//4
//					pa.setStatus(1);
//					pa.setAward(1);
//					updatePlayerActivity(pa);
					return  pa.getTarget() > 1 ? pa.getTarget() : 1;
				}
			}
		}
		return 0;
	}
	/**
	 * @param playerId
	 * @throws Exception
	 * send activity gift to player
	 * complete once RANDOM_ACTIVITY
	 * complete more than once  OTHER
	 */
	private void updatePlayerActivityGift(int playerId, int action) throws Exception {
		Player player = getService.getPlayerById(playerId);
		List<PlayerActivity> list = getService.getPlayerActivityList(playerId, 0);
		List<PlayerActivity> completeList = new ArrayList<PlayerActivity>();
		StringBuilder ids = new StringBuilder();
		StringBuilder units = new StringBuilder();
		StringBuilder unitTypes = new StringBuilder();
		for (PlayerActivity pa : list) {
			if(pa.getAction() == action ){
				if (pa.getAction() == Constants.ACTION_ACTIVITY.RANDOM_ACTIVITY.getValue()
						||(pa.getStatus()==0&&pa.getAction() == Constants.ACTION_ACTIVITY.LOGIN_ACTIVITY.getValue()
											   ||pa.getAction() == Constants.ACTION_ACTIVITY.LOGIN_ACTIVITY.getValue()
											   ||(pa.getAction() == Constants.ACTION_ACTIVITY.LEVEL_FIRST_LOGIN.getValue()&&pa.getSysActivity().getValue() == player.getRank()))) {
					completeList.add(pa);
					if(pa.getAction() != Constants.ACTION_ACTIVITY.RANDOM_ACTIVITY.getValue()){
						pa.setStatus(1);
						pa.setAward(1);
					}
					SysItem item = pa.getSysActivity().getSysItem();
					if (item.getType() == Constants.DEFAULT_PACKAGE_TYPE) {// 大礼包
						ServiceLocator.createService.packageActivity(item, player, pa.getSysActivity());
						units.append(pa.getSysActivity().getUnit()).append(",");
						unitTypes.append(pa.getSysActivity().getUnitType()).append(",");
						ids.append(item.getId()).append(",");
					} else {
						ServiceLocator.createService.awardActivity(player, item, ids,units, unitTypes,pa.getSysActivity());
					}
					if(pa.getAction() == Constants.ACTION_ACTIVITY.RANDOM_ACTIVITY.getValue()){
						String msg = CommonUtil.messageFormatI18N(CommonMsg.AWARD_DLB, new Object[] { player.getName(), item.getDisplayName() });
						soClient.proxyBroadCast(Constants.MSG_NOTICE, Constants.SYSTEM_SPEAKER, msg);
						ServiceLocator.lotteryLogger.info("random lottery name=" + player.getName() + " sysiem=" + item.getDisplayNameCN());
					}
					StringBuilder content = new StringBuilder(CommonUtil.messageFormatI18N(CommonMsg.FINISH_ACTIVITY, pa.getName()));
					messageService.sendSystemMailWithSysItemInContent(player, CommonMsg.GIFT_EMAIL_SYS, content.toString(), ids.toString(),units.toString(),unitTypes.toString());
				}
			}
		}
		if (completeList.size() > 0) {
			soClient.puchCMDtoClient(player.getName(), Converter.completeActivity(completeList));
		}
	}
	private void awradHour2hour(int playerId, int type, Date loginTime) throws Exception {// type
																							// 0:login
																							// 1:logout
		Player player = getService.getPlayerById(playerId);
		List<PlayerActivity> list = getService.getPlayerActivityList(playerId, Constants.ACTION_ACTIVITY.HOUR2HOUR_ACTIVITY.getValue());
		List<PlayerActivity> completeList = new ArrayList<PlayerActivity>();
		SysActivity activity = null;
		Calendar now = Calendar.getInstance();
		now.setTime(new Date());
		for (PlayerActivity pa : list) {
			activity = pa.getSysActivity();
			if (null != activity && pa.getAction() == Constants.ACTION_ACTIVITY.HOUR2HOUR_ACTIVITY.getValue() && pa.getStatus() == 0) {
				int startHour = activity.getValue();
				int endHour = activity.getTargetNum();
				int currentHour = now.get(Calendar.HOUR_OF_DAY);
				Map<Integer, Date> getMap = mcc.get(CacheUtil.oGetAwardMap(activity.getId()), Constants.CACHE_TIMEOUT);
				StringBuilder ids = new StringBuilder();
				StringBuilder units = new StringBuilder();
				StringBuilder unitTypes = new StringBuilder();
				// 标志是否发送本活动奖品
				boolean isSend = false;
				if (0 == type) {
					if (currentHour >= startHour && currentHour < endHour) {
						if (null == getMap) {
							// pa.getSysActivity().initNeedAward();
							// joinActivityPrize(activity);
							getMap = new HashMap<Integer, Date>();
//							sendGiftHour2Hour(getMap, player, activity.getSysItem(), now, ids, activity);
							sendGiftHour2Hour(getMap, player, activity.getSysItem(), now, ids,units, unitTypes,activity);
							pa.setStatus(1);
							pa.setAward(1);
							updatePlayerActivity(pa);
							completeList.add(pa);
							isSend = true;
						} else {
							Date lastAward = getMap.get(playerId);
							Calendar lastAwardCalendar = Calendar.getInstance();
							if (null == lastAward) {
								// 没有送礼品的记录
								// if (currentHour >= startHour && currentHour
								// <= endHour) {
								pa.setStatus(1);
								pa.setAward(1);
								updatePlayerActivity(pa);
								// pa.getSysActivity().initNeedAward();
								// joinActivityPrize(pa.getSysActivity());
//								sendGiftHour2Hour(getMap, player, activity.getSysItem(), now, ids, activity);
								sendGiftHour2Hour(getMap, player, activity.getSysItem(), now, ids,units, unitTypes,activity);
								completeList.add(pa);
								isSend = true;
								// }
							} else {
								lastAwardCalendar.setTime(lastAward);
								// 存在送礼品的记录，判断今天有没有送
								if (lastAwardCalendar.get(Calendar.DAY_OF_YEAR) != now.get(Calendar.DAY_OF_YEAR)) {
									// if (currentHour >= startHour &&
									// currentHour <= endHour) {
									pa.setStatus(1);
									pa.setAward(1);
									updatePlayerActivity(pa);
									// pa.getSysActivity().initNeedAward();
									// joinActivityPrize(pa.getSysActivity());
//									sendGiftHour2Hour(getMap, player, activity.getSysItem(), now, ids, activity);
									sendGiftHour2Hour(getMap, player, activity.getSysItem(), now, ids,units, unitTypes,activity);
									completeList.add(pa);
									isSend = true;
									// }
								}
							}
						}
					}
				} else if (1 == type) {
					Calendar loginCalendar = Calendar.getInstance();
					loginCalendar.setTime(loginTime);
					if (loginCalendar.get(Calendar.HOUR_OF_DAY) < startHour) {// 活动时间前登录
						if (now.get(Calendar.HOUR_OF_DAY) > startHour) {// 在活动开始后下线
							Date lastAward = getMap.get(playerId);
							Calendar lastAwardCalendar = Calendar.getInstance();
							if (null != lastAward) {
								if (lastAwardCalendar.get(Calendar.DAY_OF_YEAR) != now.get(Calendar.DAY_OF_YEAR)) {
									pa.setStatus(1);
									pa.setAward(1);
									updatePlayerActivity(pa);
									// pa.getSysActivity().initNeedAward();
									// joinActivityPrize(pa.getSysActivity());
//									sendGiftHour2Hour(getMap, player, activity.getSysItem(), now, ids, activity);
									sendGiftHour2Hour(getMap, player, activity.getSysItem(), now, ids,units, unitTypes,activity);
									completeList.add(pa);
									isSend = true;
								}
							} else {
								pa.setStatus(1);
								pa.setAward(1);
								updatePlayerActivity(pa);
								// pa.getSysActivity().initNeedAward();
								// joinActivityPrize(pa.getSysActivity());
//								sendGiftHour2Hour(getMap, player, activity.getSysItem(), now, ids, activity);
								sendGiftHour2Hour(getMap, player, activity.getSysItem(), now, ids,units, unitTypes,activity);
								completeList.add(pa);
								isSend = true;
							}
						}
					}
				}
				if (isSend) {
					StringBuilder content = new StringBuilder(CommonUtil.messageFormatI18N(CommonMsg.FINISH_ACTIVITY, pa.getName()));
//					messageService.sendSystemMailWithSysItemInContent(player, CommonMsg.GIFT_EMAIL_SYS, content.toString(), ids.toString());
					messageService.sendSystemMailWithSysItemInContent(player, CommonMsg.GIFT_EMAIL_SYS, content.toString(), ids.toString(),units.toString(),unitTypes.toString());
				}
			}
		}
		if (completeList.size() > 0) {
			soClient.puchCMDtoClient(player.getName(), Converter.completeActivity(completeList));
		}
	}

	public void awardRandom(int playerId) throws Exception {}

	@SuppressWarnings("unused")
	private int discountActivity(int sysitemId) throws Exception {
		List<SysActivity> list = getService.getAvailableActivities();
		SysActivity activity = null;
		int returnvlue = 1;
		for (SysActivity sa : list) {
			if (sa.getAction() == Constants.ACTION_ACTIVITY.DISCOUNT_ACTIVITY.getValue()) {
				activity = sa;
				break;
			}
		}
		if (activity != null) {
			String[] items = activity.getItems().split(",");
			for (String id : items) {
				if (StringUtil.toInt(id) == sysitemId) {
					returnvlue = activity.getTargetNum();
					break;
				}
			}
		}
		return returnvlue;
	}
	private void awardTaskActivity(int playerId) throws Exception {
		Player player = getService.getPlayerById(playerId);
		List<PlayerActivity> list = getService.getPlayerActivityList(playerId, Constants.ACTION_ACTIVITY.TASK_ACTIVITY.getValue());
		List<PlayerActivity> completeList = new ArrayList<PlayerActivity>();
		for (PlayerActivity pa : list) {
			if (pa.getAction() == Constants.ACTION_ACTIVITY.TASK_ACTIVITY.getValue() && pa.getStatus() == 0) {
				int num = pa.getNumber() + 1;
				if (pa.getTarget() <= num) {
					pa.setStatus(1);
					completeList.add(pa);
				}
				pa.setNumber(num);
				updatePlayerActivity(pa);
			}
		}
		if (completeList.size() > 0) {
			soClient.puchCMDtoClient(player.getName(), Converter.completeActivity(completeList));
		}
	}

	private List<PlayerActivity> getAvailableAchievementActivityByPlayerId(int playerId) throws Exception {
		List<PlayerActivity> list = getService.getPlayerActivityList(playerId, Constants.ACTION_ACTIVITY.ACHIEVEMENT_ACTIVITY.getValue());
		List<PlayerActivity> activityList = new ArrayList<PlayerActivity>();
		for (PlayerActivity pa : list) {
			if (pa.getAction() == Constants.ACTION_ACTIVITY.ACHIEVEMENT_ACTIVITY.getValue() && pa.getStatus() == 0) {
				activityList.add(pa);
			}
		}
		return activityList;
	}

	private void awardAchievementActivity(int playerId, StageClearPlayerInfo playerInfo, GeneralStageClearInfo stageClearInfo) throws Exception {
		StringBuilder names = new StringBuilder();
		int numberThisRound = 0;
		List<PlayerActivity> activityList = getAvailableAchievementActivityByPlayerId(playerId);
		List<PlayerActivity> completeList = new ArrayList<PlayerActivity>();
		LevelInfo level = sysLevelDao.getLevelInfoById(stageClearInfo.getLevelId());
		for (PlayerActivity pa : activityList) {
			if (Constants.ACTION.GAMEWIN.getValue() == pa.getAchievementAction()) {
				if (playerInfo.getIsWiner() == 1) {// 如果赢了一盘游戏，进度+1 3
					numberThisRound = 1;
				}
			} else if (Constants.ACTION.CONTROLKILL.getValue() == pa.getAchievementAction()) {// 控制击杀
																								// 5
				numberThisRound = playerInfo.getControlNum();
			} else if (Constants.ACTION.REVENGEKILL.getValue() == pa.getAchievementAction()) {// 复仇击杀
																								// 6
				numberThisRound = playerInfo.getRevengeNum();
			} else if (Constants.ACTION.CHARCTERKILL.getValue() == pa.getAchievementAction() && pa.getCharacterId() != 0) {// 杀死特定职业
																															// 9
				numberThisRound = playerInfo.getKillCharacter()[pa.getCharacterId()];
			} else if (Constants.ACTION.BLOODBAG.getValue() == pa.getAchievementAction()) {// 吃血包
																							// 7
				if (playerInfo.getBottleHpNum() >= pa.getTarget()) {
					numberThisRound = playerInfo.getBottleHpNum();
				}
			} else if (Constants.ACTION.BULLETBAG.getValue() == pa.getAchievementAction()) {// 吃弹药包
																							// 8
				if (playerInfo.getBulletNum() >= pa.getTarget()) {
					numberThisRound = playerInfo.getBulletNum();
				}
			} else if (Constants.ACTION.FIRSTKILL.getValue() == pa.getAchievementAction()) {// 首杀
																							// 15
				if (playerInfo.getIsFirstKill() == 1 && level.getType() != 5) {
					numberThisRound = 1;
				}
			} else if (Constants.ACTION.FIRSTDEAD.getValue() == pa.getAchievementAction()) {// 首死
																							// 16
				if (playerInfo.getIsFirstDead() == 1 && level.getType() != 5) {
					numberThisRound = 1;
				}
			} else if (Constants.ACTION.CONTINUEKILL.getValue() == pa.getAchievementAction()) {// 生存
				if ((playerInfo.getMaxKillNum() - 1) >= pa.getTarget() && level.getType() != 5) {// 连杀数达标
					numberThisRound = playerInfo.getMaxKillNum() - 1;
				}
			}
			//FIXME 
			pa.setNumber(pa.getNumber() + numberThisRound);
			if (numberThisRound > 0 && pa.getTarget() <= pa.getNumber()) {
				pa.setStatus(1);
				// pa.getSysActivity().initNeedAward();
				// joinActivityPrize(pa.getSysActivity());
				completeList.add(pa);
				// pa.setAward(1);
				// SysItem
				// item=getService.getSysItemByItemId(StringUtil.toInt(pa.getSysActivity().getItems()));
				// createService.awardToPlayer(player, item, names);
				// StringBuilder giftNames = new
				// StringBuilder(CommonUtil.messageFormat(CommonMsg.FINISH_ACTIVITY,pa.getName()));
				// messageService.sendSystemMail(player,
				// CommonMsg.GIFT_EMAIL_SYS,giftNames.append(CommonUtil.cutLastWord(names.toString())).toString());
			}
			updatePlayerActivity(pa);
		}
		if (completeList.size() > 0) {
			Player player = getService.getPlayerById(playerInfo.getId());
			soClient.puchCMDtoClient(player.getName(), Converter.completeActivity(completeList));
		}
	}

	private void awardLevelActivity(int playerId, int rank, int newLevel) throws Exception {
		long start = System.currentTimeMillis();
		Player player = getService.getPlayerById(playerId);
		List<PlayerActivity> list = getService.getPlayerActivityList(playerId, Constants.ACTION_ACTIVITY.LEVEL_ACTIVITY.getValue());
		List<PlayerActivity> completeList = new ArrayList<PlayerActivity>();
		SysActivity activity = null;
		long end = System.currentTimeMillis();
		log.debug("awardLevelActivity1.0 use " + (end - start) + "ms");
		start = System.currentTimeMillis();
		for (PlayerActivity pa : list) {
			if (pa.getAction() == Constants.ACTION_ACTIVITY.LEVEL_ACTIVITY.getValue()) {
				activity = pa.getSysActivity();
				if (activity != null && activity.getTheSwitch().equals(Constants.BOOLEAN_YES) && pa.getStatus() == 0) {
					if (rank < activity.getValue() && activity.getValue() <= newLevel) {
						StringBuilder ids = new StringBuilder();
						pa.setStatus(1);
						pa.setAward(1);
						updatePlayerActivity(pa);
						// pa.getSysActivity().initNeedAward();//设置是否需要领奖位
						// joinActivityPrize(pa.getSysActivity());
						completeList.add(pa);
						end = System.currentTimeMillis();
						log.debug("awardLevelActivity2.0 use " + (end - start) + "ms");
						start = System.currentTimeMillis();
						// for(SysItem item: pa.getSysActivity().getItemList()){
						if (pa.getSysActivity().getSysItem().getType() == Constants.DEFAULT_PACKAGE_TYPE) {// 大礼包
							ServiceLocator.createService.packageActivity(pa.getSysActivity().getSysItem(), player, pa.getSysActivity());
							ids.append(pa.getSysActivity().getSysItem().getId()).append(",");
						} else {
							ServiceLocator.createService.awardActivity(player, pa.getSysActivity().getSysItem(), ids, pa.getSysActivity());
						}
						// }
						StringBuilder content = new StringBuilder(CommonUtil.messageFormatI18N(CommonMsg.FINISH_ACTIVITY, activity.getName()));
						messageService.sendSystemMailWithSysItemInContent(player, CommonMsg.GIFT_EMAIL_SYS, content.toString(), ids.toString());
						end = System.currentTimeMillis();
						log.debug("awardLevelActivity3.0 use " + (end - start) + "ms");
						start = System.currentTimeMillis();
					}
				}
			}
		}
		if (completeList.size() > 0) {
			soClient.puchCMDtoClient(player.getName(), Converter.completeActivity(completeList));
		}
		end = System.currentTimeMillis();
		log.debug("awardLevelActivity4.0 use " + (end - start) + "ms");
		start = System.currentTimeMillis();
	}
	public void sendGiftHour2Hour(Map<Integer, Date> getMap, Player player, SysItem sysItem, Calendar now, StringBuilder ids, SysActivity sa) throws Exception {
		getMap.put(player.getId(), now.getTime());
		mcc.set(CacheUtil.oGetAwardMap(sa.getId()), Constants.CACHE_TIMEOUT_DAY, getMap, Constants.CACHE_TIMEOUT);
		if (sysItem.getType() == Constants.DEFAULT_PACKAGE_TYPE) {// 大礼包
			createService.packageActivity(sysItem, player, sa);
			ids.append(sysItem.getId()).append(",");
		} else {
			createService.awardActivity(player, sysItem, ids, sa);
		}
	}
	public void sendGiftHour2Hour(Map<Integer, Date> getMap, Player player, SysItem sysItem, Calendar now, StringBuilder ids,StringBuilder units,StringBuilder unitTypes, SysActivity sa) throws Exception {
		getMap.put(player.getId(), now.getTime());
		mcc.set(CacheUtil.oGetAwardMap(sa.getId()), Constants.CACHE_TIMEOUT_DAY, getMap, Constants.CACHE_TIMEOUT);
		if (sysItem.getType() == Constants.DEFAULT_PACKAGE_TYPE) {// 大礼包
			createService.packageActivity(sysItem, player, sa);
			ids.append(sysItem.getId()).append(",");
		} else {
			createService.awardActivity(player, sysItem, ids,units,unitTypes, sa);
		}
	}

	// ===============================================================================
	// SysMission Services
	// ===============================================================================
	public void updatePlayerMission(PlayerMission playerMission) throws Exception {
		playerMissionDao.updatePlayerMission(playerMission);
	}

	// 派送任务
	public void sendMission(Player player, boolean isDailySend, boolean isWeekSend) throws Exception {
		String playerName = player.getName();
		int playerId = player.getId();

		Calendar now = Calendar.getInstance();
		boolean isSend = false;
		StringBuilder missionNames = new StringBuilder();
		char moduleStatus = player.getTutorial().toCharArray()[ModuleStatusIndex.DailyMission.ordinal()];
		boolean moduleIsOpen = ModuleStatus.CLOSE.getCh() != moduleStatus;

		if (!moduleIsOpen) {
			{
				ServiceLocator.deleteService.deletePlayerMissions(playerId, Constants.NUM_ZERO);
				String key = CacheUtil.oDailyMissionDate(playerId);
				mcc.delete(key);
			}
			{
				ServiceLocator.deleteService.deletePlayerMissions(playerId, Constants.NUM_ONE);
				String key = CacheUtil.oWeekMissionDate(playerId);
				mcc.delete(key);
			}
			return;
		}

		List<PlayerMission> dailyMissionList = new ArrayList<PlayerMission>();
		if (isDailySend) {
			{
			ServiceLocator.deleteService.deletePlayerMissions(playerId, Constants.NUM_ZERO);
			isSend = true;
			}
			List<SysMission> randomDailyMission = getService.getDailyMission();
			for(int i =0 ;i<randomDailyMission.size();i++){
				SysMission m = randomDailyMission.get(i);
				PlayerMission temp = new PlayerMission();
				temp.setType(Constants.NUM_ZERO);
				temp.setPlayerId(playerId);
				temp.setSysMissionId(m.getId());
				if (randomDailyMission.indexOf(m) == 2) {// 设置一个中等的任务
					temp.setTarget(m.getNormalTarget());
				}else if (randomDailyMission.indexOf(m) == 3) {// 设置一个难的任务
					temp.setTarget(m.getHardTarget());
				}else {
					temp.setTarget(m.getSimpleTarget());
				}
				temp.setStatus(Constants.NUM_ZERO);
				temp.setCreateTime(now.getTime());
				temp.setNumber(Constants.NUM_ZERO);
				temp.setDescription(CommonUtil.messageFormat(m.getDescription(), temp.getTarget()));
				// Join奖品
				temp.setSysMission(m);
				dailyMissionList.add(temp);
				createService.createPlayerMission(temp);
				missionNames.append(m.getTitle()).append("、");
			}

			String key = CacheUtil.oDailyMissionDate(playerId);
			mcc.delete(key);
		}

		List<PlayerMission> weekMissionList = new ArrayList<PlayerMission>();
		if (isWeekSend) {
			ServiceLocator.deleteService.deletePlayerMissions(playerId, Constants.NUM_ONE);

			isSend = true;
			List<SysMission> randomWeekMission = getService.getRandomWeekMission();
			for(int i =0 ;i<randomWeekMission.size();i++){
				SysMission m = randomWeekMission.get(i);
				PlayerMission temp = new PlayerMission();
				temp.setType(Constants.NUM_ONE);
				temp.setPlayerId(playerId);
				temp.setSysMissionId(m.getId());
				if (i == 1) {// 设置一个 简单 的任务
					temp.setTarget(m.getSimpleTarget());
				} else if (i == 2){// 设置一个 普通 的任务
					temp.setTarget(m.getNormalTarget());
				}else{// 设置一个 困难 的任务
					temp.setTarget(m.getHardTarget());
				}
				temp.setStatus(Constants.NUM_ZERO);
				temp.setCreateTime(now.getTime());
				temp.setNumber(Constants.NUM_ZERO);
				temp.setDescription(CommonUtil.messageFormat(m.getDescription(), temp.getTarget()));
				weekMissionList.add(temp);
				createService.createPlayerMission(temp);
				missionNames.append(m.getTitle()).append("、");
			}
			String key = CacheUtil.oWeekMissionDate(playerId);
			mcc.delete(key);
		}

		// 通知Client
		if (isSend) {
			String result = Converter.newMissionList(dailyMissionList);
			soClient.messageCMD(playerName, result);
		}
	}

	/**
	 * Join Mission 奖品
	 * 
	 * @param sysMission
	 * @throws Exception
	 */
	public void joinPrize(SysMission sysMission) throws Exception {
		Map<Integer, SysItem> itemMap = getService.getAllSysItemMap();
		List<SysItem> normalItemList = new ArrayList<SysItem>();
		List<SysItem> vipItemList = new ArrayList<SysItem>();
		if (!sysMission.getNormalItems().isEmpty()) {
			String[] normalItems = sysMission.getNormalItems().split(",");
			for (String s : normalItems) {
				SysItem tempItem = itemMap.get(StringUtil.toInt(s));
				tempItem.initGunProperty();
				normalItemList.add(tempItem);
			}
		}
		if (!sysMission.getVipItems().isEmpty()) {
			String[] vipItems = sysMission.getVipItems().split(",");
			for (String s : vipItems) {
				SysItem tempItem = itemMap.get(StringUtil.toInt(s));
				tempItem.initGunProperty();
				vipItemList.add(tempItem);
			}
		}
		sysMission.setNormalItemList(normalItemList);
		sysMission.setVipItemList(vipItemList);
	}

	/**
	 * Join Activity奖品
	 * 
	 * @param sysMission
	 * @throws Exception
	 */
	public void joinActivityPrize1(SysActivity sysActivity) throws Exception {
		String[] sysItems = sysActivity.getItems().split(",");
		List<SysItem> itemList = new ArrayList<SysItem>();
		for (String s : sysItems) {
			itemList.add(getService.getSysItemByItemId(StringUtil.toInt(s)));
		}
		sysActivity.setItemList(itemList);
	}

	/**
	 * Join Message
	 * 
	 * @param
	 * @throws Exception
	 */
	public List<SysItem> joinMessageItem(Message message) throws Exception {
		List<SysItem> itemList = new ArrayList<SysItem>();
		if (!"".equals(message.getItems())) {
			String[] sysItems = message.getItems().split(",");
			String[] itemUnits = message.getItemsUnits().split(",");
			String[] itemUnitTypes = message.getItemsUnitTypes().split(",");
			
			for (int i=0 ;i < sysItems.length;i++) {
				int itemId = StringUtil.toInt(sysItems[i]);
				SysItem si = getService.getSysItemByItemId(itemId);
				if(si==null){
					ServiceLocator.fileLog.warn("joinMessageItem/SysItemNull:\t" + itemId);
					continue;
				}
				SysItem newSi = si.clone();
				if(itemUnits.length>i&&!StringUtil.isEmptyString(itemUnits[i])&&itemUnitTypes.length>i&&!StringUtil.isEmptyString(itemUnitTypes[i])){
						newSi.setUnit(StringUtil.toInt(itemUnits[i]));
						newSi.setUnitType(StringUtil.toInt(itemUnitTypes[i]));
				}else{
					newSi.setUnit(-1);//如果没有，则返回-1，客户端以此判断是否显示
					newSi.setUnitType(-1);
				}
				itemList.add(newSi);
			}
		}
		return itemList;
	}

	public String awardMission(PlayerMission playerMission) throws Exception {
		Player player = getService.getPlayerById(playerMission.getPlayerId());
		CommonUtil.checkNull(player, ExceptionMessage.NO_HAVE_THE_CHARACTER);
		int oldRank = player.getRank();
		SysMission sysMission = getService.getSysMissionById(playerMission.getSysMissionId());
		playerMission.setDescription(CommonUtil.messageFormat(sysMission.getDescription(), playerMission.getTarget()));
		// final StringBuilder content = new
		// StringBuilder(CommonUtil.messageFormat(CommonMsg.FINISH_MISSION,
		// playerMission.getDescription()));
		StringBuilder ids = new StringBuilder("");
		List<SysItem> normalList = new ArrayList<SysItem>();
		List<SysItem> vipList = new ArrayList<SysItem>();
		CycleMissionIncome income = CycleMissionIncome.getCycleMissionIncome(sysMission, playerMission,player.getIsVip());

		if(nosqlService.getFCMTime(player.getId())>=300){//防沉迷
			income=CycleMissionIncome.NONEAWARDMISSION;
		}else if(nosqlService.getFCMTime(player.getId())>=180){
			income=CycleMissionIncome.getCycleMissionIncomeForFCM(income);
		}
	
		int cal = income.getCal();
		int exp = income.getExp();
		int medalNum = income.getMedal();
		
		for (SysItem item : sysMission.getNormalItemList()) {
			normalList.add(item);
			ServiceLocator.createService.awardToPlayer(player, item, null, ids, Constants.BOOLEAN_YES);
		}
		if (1 <= player.getIsVip()) {// vip奖品
			//目前没有任务有vip奖品 
			for (SysItem item : sysMission.getVipItemList()) {
				vipList.add(item);
				ServiceLocator.createService.awardToPlayer(player, item, null, ids, Constants.BOOLEAN_YES);
			}
			medalNum += income.getVipMedal();
			cal += income.getVipCal();
			exp += income.getVipExp();
		}
		// 邮件通知
		// ServiceLocator.createService.sendSystemMail(player,
		// CommonMsg.GIFT_EMAIL_SYS, content.toString(), ids.toString());
		playerMission.setAward(1);
		updatePlayerMission(playerMission);
		String key = CacheUtil.oPlayerMissionList(playerMission.getPlayerId());
		mcc.delete(key);

		createService.awardCALToPlayer(player, cal);
		createService.awardExpToPlayer(player, exp);
		int newRank = player.getRank();
		SysItem medalSysItem = getService.getMedalSysItem();
		Payment payment = new Payment(medalNum, Constants.NUM_ONE);
		createService.sendItem(medalSysItem, player, payment, Constants.BOOLEAN_NO, Constants.BOOLEAN_YES, Constants.BOOLEAN_NO);
		ServiceLocator.nosqlService.updateStayDataAddExp(player, exp);
		
		if (ConfigurationUtil.SWITCH_XUNLEI_LOG.getIsOn()&&medalSysItem.getId()==GrowthMissionConstants.MEDAL_ID) {
			int type=sysMission.getType();
			nosqlService.addXunleiLog("7.4"
					+ Constants.XUNLEI_LOG_DELIMITER + player.getUserName()
					+ Constants.XUNLEI_LOG_DELIMITER + player.getName()
					+ Constants.XUNLEI_LOG_DELIMITER + 1
					+ Constants.XUNLEI_LOG_DELIMITER + medalNum
					+ Constants.XUNLEI_LOG_DELIMITER+ (type==0?4:5)
					+ Constants.XUNLEI_LOG_DELIMITER+ CommonUtil.simpleDateFormat.format(new Date())
					);
			nosqlService.addXunleiLog("9.2"
					+ Constants.XUNLEI_LOG_DELIMITER + player.getUserName()
					+ Constants.XUNLEI_LOG_DELIMITER + player.getName()
					+ Constants.XUNLEI_LOG_DELIMITER + player.getRank()
					+ Constants.XUNLEI_LOG_DELIMITER + type
					+ Constants.XUNLEI_LOG_DELIMITER + sysMission.getDescriptionCN()
					+ Constants.XUNLEI_LOG_DELIMITER + medalNum
					+ Constants.XUNLEI_LOG_DELIMITER + exp
					+ Constants.XUNLEI_LOG_DELIMITER + cal
					+ Constants.XUNLEI_LOG_DELIMITER+ CommonUtil.simpleDateFormat.format(new Date())
					);
			
		}
		byte upgrade = oldRank == newRank ? BooleanBytevalue.FALSE.getBytevalue() : BooleanBytevalue.TRUE.getBytevalue();
		//vip 增加升级经验  必须是每日任务
		if(player.getIsVip()>0 && playerMission.getType()==0){
			updateVipUpExp(income.getLevel()+1, player.getId());
		}
	
		return  getAwardCycleMissionListView(player, normalList, vipList, income, upgrade);
	}

	public List<SysItem> sendMissionGift(final Player player, SysMission sysMission, PlayerMission playerMission) throws Exception {
		List<SysItem> list = new ArrayList<SysItem>();// 全部获得的奖品
		playerMission.setDescription(CommonUtil.messageFormat(sysMission.getDescription(), playerMission.getTarget()));
		// final StringBuilder content = new
		// StringBuilder(CommonUtil.messageFormat(CommonMsg.FINISH_MISSION,
		// playerMission.getDescription()));
		StringBuilder ids = new StringBuilder("");
		for (SysItem item : sysMission.getNormalItemList()) {
			list.add(item);
			ServiceLocator.createService.awardToPlayer(player, item, null, ids, Constants.BOOLEAN_YES);
		}
		if (1 <= player.getIsVip()) {// vip奖品
			for (SysItem item : sysMission.getVipItemList()) {
				list.add(item);
				ServiceLocator.createService.awardToPlayer(player, item, null, ids, Constants.BOOLEAN_YES);
			}
		}

		// ServiceLocator.createService.sendSystemMail(player,
		// CommonMsg.GIFT_EMAIL_SYS, content.toString(), ids.toString());
		return list;
	}

	public void resetPlayerWeapon(int playerId) throws Exception {
		List<PlayerItem> weaponList = new ArrayList<PlayerItem>();
		List<PlayerItem> customList = new ArrayList<PlayerItem>();
		List<PlayerItem> peishiList = new ArrayList<PlayerItem>();
		for (SysCharacter sc : getService.getDefaultSysCharacterList()) {
			weaponList.addAll(getService.getPlayerItems(playerId, Constants.DEFAULT_WEAPON_TYPE, sc.getId(), 0));
			customList.addAll(getService.getPlayerItems(playerId, Constants.DEFAULT_COSTUME_TYPE, sc.getId(), 0));
			peishiList.addAll(getService.getPlayerItems(playerId, Constants.DEFAULT_PART_TYPE, sc.getId(), 0));
		}
		Set<Integer> playerDefaultItemIdSet = new HashSet<Integer>();
		for (PlayerItem pi : weaponList) {
			SysItem si = sysItemDao.getSystemItemById(pi.getItemId());
			if (pi.getIsDefault().equals("Y")) {
				updateWeaponPackEquipment(playerId, Constants.DEFAULT_WEAPON_TYPE, pi.getId(), Integer.parseInt(si.getCharacterId()));
				playerDefaultItemIdSet.add(si.getId());
			}
		}
		for (PlayerItem pi : customList) {
			SysItem si = sysItemDao.getSystemItemById(pi.getItemId());
			if (pi.getIsDefault().equals("Y")) {
				updateCostumePackEquipment(playerId, Constants.DEFAULT_COSTUME_TYPE, pi.getId(), Integer.parseInt(si.getCharacterId()));
				playerDefaultItemIdSet.add(si.getId());
			}
		}
		for (PlayerItem pi : peishiList) {
			SysItem si = sysItemDao.getSystemItemById(pi.getItemId());
			if (pi.getIsDefault().equals("Y")) {
				updateCostumePackEquipment(playerId, Constants.DEFAULT_PART_TYPE, pi.getId(), Integer.parseInt(si.getCharacterId()));
				playerDefaultItemIdSet.add(si.getId());
			}
		}

		// default item miss then create them
		Set<Integer> allDefaultItemIdSet = new HashSet<Integer>();
		for (int[] ids : Constants.weaponPack) {
			for (int id : ids) {
				if (Constants.NUM_ZERO != id) {
					allDefaultItemIdSet.add(id);
				}
			}
		}
		for (int[] ids : Constants.costumePack) {
			for (int id : ids) {
				if (Constants.NUM_ZERO != id) {
					allDefaultItemIdSet.add(id);
				}
			}
		}
		try {
			if (allDefaultItemIdSet.size() > playerDefaultItemIdSet.size()) {
				for (int id : allDefaultItemIdSet) {
					if (!playerDefaultItemIdSet.contains(id)) {
						SysItem si = sysItemDao.getSystemItemById(id);
						int piId = createService.sendItem(si, getService.getPlayerById(playerId), new Payment(0, 1), Constants.BOOLEAN_NO, Constants.BOOLEAN_YES,
								Constants.BOOLEAN_YES);
						ServiceLocator.deleteService.deletePlayerItemInMemcached(playerId, si);
						if (si.getType() == Constants.DEFAULT_WEAPON_TYPE) {
							updateWeaponPackEquipment(playerId, Constants.DEFAULT_WEAPON_TYPE, piId, Integer.parseInt(si.getCharacterId()));
						} else if (si.getType() == Constants.DEFAULT_COSTUME_TYPE) {
							updateCostumePackEquipment(playerId, Constants.DEFAULT_COSTUME_TYPE, piId, Integer.parseInt(si.getCharacterId()));
						} else if (si.getType() == Constants.DEFAULT_PART_TYPE) {
							updateCostumePackEquipment(playerId, Constants.DEFAULT_PART_TYPE, piId, Integer.parseInt(si.getCharacterId()));
						}
						log.warn("playerId=" + playerId + " create Default Item" + " sysItemId=" + si.getId() + " playerItemId=" + piId);
					}
				}
			}
		} catch (Exception e) {
			log.warn(e.getMessage(), e);
		}

		// for(SysCharacter sc : getService.getSysCharacterList()){
		// if(sc.getId() != 4){
		// ServiceLocator.deleteService.deletePackEquip(playerId, sc.getId(),
		// Constants.DEFAULT_WEAPON_TYPE, 4);
		// }
		// }
		Player player = getService.getPlayerById(playerId);
		messageService.sendSystemMail(player, CommonMsg.RESET_WEAPON_MAIL_SUBJECT, CommonMsg.RESET_WEAPON_MAIL_CONTENT);
	}

	public void resetPlayerWeapon(int playerId, int characterId) throws Exception {
		List<PlayerItem> weaponList = getService.getPlayerItemList1(playerId, Constants.DEFAULT_WEAPON_TYPE, characterId, 0);
		List<PlayerItem> customList = getService.getPlayerItemList1(playerId, Constants.DEFAULT_COSTUME_TYPE, characterId, 0);
		List<PlayerItem> peishiList = getService.getPlayerItemList1(playerId, Constants.DEFAULT_PART_TYPE, characterId, 0);
		// List<PlayerItem> weaponList = getService.getPlayerItemList(playerId,
		// Constants.DEFAULT_WEAPON_TYPE,characterId);
		// List<PlayerItem> customList = getService.getPlayerItemList(playerId,
		// Constants.DEFAULT_COSTUME_TYPE,characterId);
		// List<PlayerItem> peishiList = getService.getPlayerItemList(playerId,
		// Constants.DEFAULT_PART_TYPE,characterId);
		for (PlayerItem pi : weaponList) {
			SysItem si = sysItemDao.getSystemItemById(pi.getItemId());
			if (pi.getIsDefault().equals("Y")) {
				updateWeaponPackEquipment(playerId, Constants.DEFAULT_WEAPON_TYPE, pi.getId(), Integer.parseInt(si.getCharacterId()));
				
			}
		}
		for (PlayerItem pi : customList) {
			SysItem si = sysItemDao.getSystemItemById(pi.getItemId());
			if (pi.getIsDefault().equals("Y")) {
				updateCostumePackEquipment(playerId, Constants.DEFAULT_COSTUME_TYPE, pi.getId(), Integer.parseInt(si.getCharacterId()));
			}
		}
		for (PlayerItem pi : peishiList) {
			SysItem si = sysItemDao.getSystemItemById(pi.getItemId());
			if (pi.getIsDefault().equals("Y")) {
				updateCostumePackEquipment(playerId, Constants.DEFAULT_PART_TYPE, pi.getId(), Integer.parseInt(si.getCharacterId()));
			}
		}

		// for(SysCharacter sc : getService.getSysCharacterList()){
		// if(sc.getId() != 4){
		// ServiceLocator.deleteService.deletePackEquip(playerId, sc.getId(),
		// Constants.DEFAULT_WEAPON_TYPE, 4);
		// }
		// }
	}

	public void checkAndUpdateCharacters(Player player) throws Exception {
		List<Integer> idList = new ArrayList<Integer>();
		String characters = player.getCharacters();
		for (String s : player.getCharactersIds()) {
			idList.add(Integer.parseInt(s));
		}
		boolean isCharacterChange = false;
		if (player.getRank() >= 3 && !idList.contains(5)) {
			characters = characters + "," + 5;
			isCharacterChange = true;
		}
		if (player.getRank() >= 6 && !idList.contains(2)) {
			characters = characters + "," + 2;
			isCharacterChange = true;
		}
		if (isCharacterChange) {
			player.setCharacters(characters);
			playerDao.updatePlayerInCache(player);
			mcc.delete(CacheUtil.oPlayer(player.getId()));
			mcc.delete(CacheUtil.sPlayer(player.getId()));
			// soClient.puchCMDtoClient(player.getName(),
			// CommonUtil.messageFormat(CommonMsg.RELEASE_CHARACTER,new
			// Object[]{player.getId()}));
			// ServiceLocator.missionLog.debug("[debug] Unlock character when GetCharacterList playerId="
			// + player.getId() + " level=" + player.getRank());
		}
	}

	/**
	 * 解除物品封印，如果是计时物品，开始计时 调用方法之后，要删除对应玩家对应的cache
	 * 
	 * @param playerItem
	 * @throws Exception
	 */
	public void begainUseItem(PlayerItem playerItem) throws Exception {
		if (playerItem.getIsBind().equals(Constants.BOOLEAN_NO)) {
			playerItem.setIsBind(Constants.BOOLEAN_YES);
			Calendar c = Calendar.getInstance();
			playerItem.setValidDate(c.getTime());
			c.add(Calendar.SECOND, playerItem.getLeftSeconds());
			playerItem.setExpireDate(c.getTime());
			playerItemDao.updatePlayerItem(playerItem);
		}
	}

	/**
	 * 封印物品，如果是计时物品，将不再计时 调用方法之后，要删除对应玩家对应的cache
	 * 
	 * @param playerItem
	 * @throws Exception
	 */
	public void endUseItem(PlayerItem playerItem) throws Exception {
		if (playerItem.getIsBind().equals(Constants.BOOLEAN_YES)) {
			playerItem.setIsBind(Constants.BOOLEAN_NO);
			Calendar c = Calendar.getInstance();
			int secondsUsed = (int) (c.getTimeInMillis() / 1000L - playerItem.getValidDate().getTime() / 1000L);
			playerItem.setLeftSeconds(playerItem.getLeftSeconds() - secondsUsed);
			playerItemDao.updatePlayerItem(playerItem);
		}
	}

	/**
	 * 更新在线时长奖励的状态，
	 * 
	 */
	public void updateOnlineAward(int playerId, Boolean isOn) throws Exception {
		nosqlService.updateOnlineAward(playerId, isOn);
	}
	public int getEquipmentTyperevise(int type,int subType){
		if(type==1){
			return 6;
		}else if(type==2){
			return 3;
		}else if(type==3&&subType==2){
			return 2;
		}else{
			return 1;
		}
	}
	/**
	 * 合成系统-强化
	 * 
	 * @param playerId
	 * @param playerItemId
	 *            强化的物品，必须
	 * @param strengthenItemId
	 *            强化部件，必须
	 * @param otherItem
	 *            其他装置，选用
	 * @throws Exception
	 */
	public int strengthenPlayerItem(int playerId, int playerItemId, int strengthenItemId, int safeItemId, int stableItemId,PlayerItem[] itemList,List<SysItem> failAwards) throws Exception {
		
		// check parameters
		Player player = getService.getPlayerById(playerId);
		CommonUtil.checkNull(player, ExceptionMessage.NO_HAVE_THE_CHARACTER);

		PlayerItem pi = getService.getPlayerItemById(playerId, playerItemId);
		CommonUtil.checkNull(pi, ExceptionMessage.NOT_FIND_ITEM);
		SysItem si = pi.getSysItem();
		if (pi.getLevel() >= getService.getMaxStrengthLevel(playerId)) {
			throw new BaseException(ExceptionMessage.ITEM_IS_MAX_LEVEL+":"+si.getDisplayName()+" id="+pi.getId());
		}
		
		if (Constants.NUM_ZERO == si.getIsStrengthen()) {
			throw new BaseException(ExceptionMessage.CANNOT_STRENGTH+":"+si.getDisplayName()+" id="+pi.getId());
		}
		
		
		//check strengthen material
		PlayerItem strengthenItem = getService.getPlayerItemByItemId(playerId, Constants.DEFAULT_MATERIAL_TYPE, strengthenItemId);
		CommonUtil.checkNull(strengthenItem, ExceptionMessage.NOT_STRENGTH_ITEM);
		if (!(strengthenItem.getSysItem().getType() == 5 && strengthenItem.getSysItem().getSubType() == 1 && strengthenItem.getSysItem().getMType() == 1)) {
			throw new BaseException(ExceptionMessage.STRENGTH_ITEM_NOT_MATCH);
		}
		
		PlayerItem safeItem = null;
		if (Constants.NUM_ZERO < safeItemId) {
			safeItem = getService.getPlayerItemByItemId(playerId, Constants.DEFAULT_MATERIAL_TYPE, safeItemId);
			
			log.info("SafeItem is "+safeItem.getItemDisplayName()+"--" +safeItem.getItemName());
			CommonUtil.checkNull(safeItem, ExceptionMessage.NOT_AVOID_REDUCE_LEVEL_ITEM);
			if (!(safeItem.getSysItem().getType() == Constants.DEFAULT_MATERIAL_TYPE && safeItem.getSysItem().getSubType() == Constants.NUM_ONE && (safeItem.getSysItem()
					.getMType() == Constants.SAFE_ITEM || safeItem.getSysItem().getMType() == Constants.UNBREAK_ITEM))) {
				throw new BaseException(ExceptionMessage.STRENGTH_ITEM_NOT_MATCH);
			}
		}
		
		PlayerItem stableItem = null;
		if (Constants.NUM_ZERO < stableItemId) {
			stableItem = getService.getPlayerItemByItemId(playerId, Constants.DEFAULT_MATERIAL_TYPE, stableItemId);
			CommonUtil.checkNull(stableItem, ExceptionMessage.NOT_ADD_RATE_ITEM);
			if (!(stableItem.getSysItem().getType() == Constants.DEFAULT_MATERIAL_TYPE && stableItem.getSysItem().getSubType() == Constants.NUM_ONE && (stableItem.getSysItem()
					.getMType() == Constants.STABLE_ITEM || stableItem.getSysItem().getMType() == Constants.SUCCESS_ITEM))) {
				throw new BaseException(ExceptionMessage.STRENGTH_ITEM_NOT_MATCH);
			}
		}
		// strengthen
		int currentLevel = pi.getLevel();
		
		int equipmentTyperevise = getEquipmentTyperevise(si.getType(), si.getSubType());
		int needCCoins = (currentLevel+1)*equipmentTyperevise*1000;
		if (player.getGPoint() < needCCoins) {
			throw new BaseException(ExceptionMessage.NOT_ENOUGH_GPOINT);
		}
		int strengthenItemNumberNeed = (currentLevel+1)*equipmentTyperevise;
		if (strengthenItem.getQuantity() < strengthenItemNumberNeed) {
			throw new BaseException(CommonUtil.messageFormatI18N(ExceptionMessage.NOT_ENOUGH_STRENGTHEN_ITEM, new Object[] { strengthenItemNumberNeed,
					strengthenItemNumberNeed - strengthenItem.getQuantity() }));
		}

		
		player.setGPoint(player.getGPoint() - needCCoins);
		updatePlayerInfo(player);
		soClient.messageUpdatePlayerMoney(player);

		double successRate = 0;

		int isNonLose= updatePlayerActivity(Constants.ACTION_ACTIVITY.STRENGTH_NON_LOSE.getValue(), player.getId(), new Date(), 0, 0, null, null);
	
		int maxTeamLevel = getService.getMaxTeamLevel();
		double serverRevise =Math.pow(1.06, Math.max(0, maxTeamLevel - 5));
		int isAdd = 0;
		
		if (null != stableItem && stableItem.getSysItem().getMType() == Constants.SUCCESS_ITEM && stableItem.getSysItem().getMValue()>currentLevel) {// 强化增幅能源
			successRate = 1;
		}else{
			double baseSuccessRate = 2.1848*(currentLevel+1)/Math.pow(1.8, currentLevel+1);
			
			double propAdd = stableItem == null ?0:(stableItem.getSysItem().getMType() == Constants.STABLE_ITEM?stableItem.getSysItem().getMValue():0);// 增幅能源
			double teamAdd = getService.getTeamBuffStrengthen(playerId);
			//VIP 增加
			double vipAdd=player.getIsVip()>0 ? getService.getVipBuffStrength(playerId) :0;
			
			isAdd= updatePlayerActivity(Constants.ACTION_ACTIVITY.STRENGTH_ADD.getValue(), player.getId(), new Date(), 0, 0, null, null);

			double propRevise = 1 + propAdd/100;
			double teamRevise = 1 + teamAdd/100;
			double vipRevise= 1 + vipAdd/100.0;
			double activityRevise = 1 + isAdd/100.0;
			double nonLoseRevise = 1 + isNonLose/100.0;
			successRate = baseSuccessRate*propRevise*teamRevise*activityRevise*serverRevise*nonLoseRevise*vipRevise;
			successRate = Math.max(successRate, 0);
			successRate = Math.min(successRate, 1);
		}
		
		int isMust= updatePlayerActivity(Constants.ACTION_ACTIVITY.STRENGTH_MUST.getValue(), player.getId(), new Date(), 0, 0, null, null);
		
		double faiedNotLoseLevelRate = (1-successRate)/2;
		double faiedLoseLevelRate = faiedNotLoseLevelRate;
		
		if ((null != safeItem && safeItem.getSysItem().getMType() == Constants.SAFE_ITEM)||isNonLose!=0) {// 保险装置 安定芯片
			//successRate-=isNonLose;
			faiedLoseLevelRate = 0;
			faiedNotLoseLevelRate =1-successRate;
		}
		boolean isMustSuccess=false;
		if(isMust!=0){
			isMustSuccess=dealStrengthFailTime(playerId, playerItemId, currentLevel);
		}
		if(isMustSuccess){
			successRate = 1;
			faiedLoseLevelRate = 0;
			faiedNotLoseLevelRate = 0;
		}
		int result = strengthen(successRate, faiedNotLoseLevelRate, faiedLoseLevelRate);
		switch (result) {
		case Constants.SUCCESS:
			pi.setLevel(pi.getLevel() + 1);
			if (pi.isLevelChangeLevel()) {
				pi.openHole();
			}
			// set is bind
			if (pi.getIsBind().equals(Constants.BOOLEAN_NO)) {
				pi.setIsBind(Constants.BOOLEAN_YES);
			}
			playerItemDao.updatePlayerItem(pi);
			// success delete cache in memcached
			ServiceLocator.deleteService.deletePlayerItemInMemcached(playerId, si);
			pi.initGunProperty(si);
			// send sys notice
			if (pi.getLevel() >= 7 && pi.getLevel() < 10) {
				soClient.proxyBroadCast(Constants.MSG_NBA, Constants.SYSTEM_SPEAKER,
						CommonUtil.messageFormatI18N(CommonMsg.STRENGTH_SYS, new Object[] { (GunProperty.RED + "@!" +  player.getName()+"@!"+GunProperty.RED + "@!" + player.getId()), (pi.getGunProperty().getColor() + "@!" +  si.getDisplayName()), (pi.getGunProperty().getColor() + "@!" +  pi.getLevel()) }));
			} else if (pi.getLevel() >= 10&& pi.getLevel() < 14) {
				soClient.updateBillBoardList(CommonUtil.messageFormatI18N(CommonMsg.STRENGTH_MIDDLE_SYS, new Object[] { (GunProperty.GOLD + "@!" +  player.getName()), (pi.getGunProperty().getColor() + "@!" +  si.getDisplayName()), (pi.getGunProperty().getColor() + "@!" +  pi.getLevel()) }));
				soClient.proxyBroadCast(Constants.MSG_NBA, Constants.SYSTEM_SPEAKER,
						CommonUtil.messageFormatI18N(CommonMsg.STRENGTH_MIDDLE_SYS, new Object[] { (GunProperty.RED + "@!" +  player.getName()+"@!"+GunProperty.RED + "@!" + player.getId()),(pi.getGunProperty().getColor() + "@!" +  si.getDisplayName()), (pi.getGunProperty().getColor() + "@!" +  pi.getLevel())  }));
			}else if (pi.getLevel() >= 14) {
				soClient.updateBillBoardList(CommonUtil.messageFormatI18N(CommonMsg.STRENGTH_MAX_SYS, new Object[] { (GunProperty.GOLD + "@!" +  player.getName()), (pi.getGunProperty().getColor() + "@!" +  si.getDisplayName()), (pi.getGunProperty().getColor() + "@!" +  pi.getLevel()) }));
				soClient.proxyBroadCast(Constants.MSG_NBA, Constants.SYSTEM_SPEAKER,
						CommonUtil.messageFormatI18N(CommonMsg.STRENGTH_MAX_SYS, new Object[] { (GunProperty.RED + "@!" +  player.getName()+"@!"+GunProperty.RED + "@!" + player.getId()) , (pi.getGunProperty().getColor() + "@!" +  si.getDisplayName()) , (pi.getGunProperty().getColor() + "@!" +  pi.getLevel()) }));
			}
			// 成长任务：强化到3-15级
			GrowthMissionType combineStrengthGrowthMissionTypeBy = GrowthMissionType.getCombineStrengthGrowthMissionTypeBy(si.getType(), si.getSubType(), pi.getLevel());
			if (null != combineStrengthGrowthMissionTypeBy) {
				updatePlayerCombineStrengthGrowthMission(player, combineStrengthGrowthMissionTypeBy);
			}
			mcc.delete(CacheUtil.strengtFailTimes(playerId, playerItemId));
			break;
		case Constants.FAILED_NOT_LOSE_LEVEL:
			break;
		case Constants.FAILED_LOSE_LEVEL:
			if (pi.isLevelChangeLevel()) {
				pi.closeHole();
			}
			pi.setLevel(pi.getLevel() > 0 ? pi.getLevel() - 1 : 0);
			playerItemDao.updatePlayerItem(pi);
			// failed delete cache in memcached
			ServiceLocator.deleteService.deletePlayerItemInMemcached(playerId, si);
			break;
		}
		ServiceLocator.strengthLog.debug(LogUtils.JoinerByTab.join("",playerId,playerItemId,result,currentLevel,pi.getLevel(),strengthenItemId,
				safeItemId,safeItem==null?"none":safeItem.getSysItem().getDisplayNameCN(),
				stableItemId,stableItem==null?"none":stableItem.getSysItem().getDisplayNameCN(),
				isNonLose,isMust,isMustSuccess,maxTeamLevel,isAdd,
				CommonUtil.toFourFloat(successRate),CommonUtil.toFourFloat(faiedNotLoseLevelRate),CommonUtil.toFourFloat(faiedLoseLevelRate),CommonUtil.toFourFloat(serverRevise)));
		// 成长任务：强化1次
		GrowthMissionType missionType=GrowthMissionType.getCombineStrengthGrowthMissionTypeBy(si.getType(), si.getSubType());
		updatePlayerGrowthMission(player, missionType==null?null:missionType);
		if(si.getType()==1){
			updatePlayerActivity(Constants.ACTION_ACTIVITY.STRENGTH_TO_RANK.getValue(), playerId, null, pi.getLevel(), 0, null, null);
		}
		if (ConfigurationUtil.SWITCH_XUNLEI_LOG.getIsOn()) {
			nosqlService.addXunleiLog("8.1"
					+ Constants.XUNLEI_LOG_DELIMITER + player.getUserName()
					+ Constants.XUNLEI_LOG_DELIMITER + player.getName()
					+ Constants.XUNLEI_LOG_DELIMITER + player.getRank()
					+ Constants.XUNLEI_LOG_DELIMITER + si.getDisplayNameCN()
					+ Constants.XUNLEI_LOG_DELIMITER + pi.getLevel()
					+ Constants.XUNLEI_LOG_DELIMITER+ strengthenItemNumberNeed
					+ Constants.XUNLEI_LOG_DELIMITER+ (safeItem==null?0:1)
					+ Constants.XUNLEI_LOG_DELIMITER+ (stableItem==null?0:1)
					+ Constants.XUNLEI_LOG_DELIMITER+ result
					+ Constants.XUNLEI_LOG_DELIMITER+ CommonUtil.simpleDateFormat.format(new Date())
					);
		}	
		// delete player pack and character data
		ServiceLocator.deleteService.deletePlayerPack(playerId, si);
		// delete items
		strengthenItem=ServiceLocator.deleteService.deleteCombiningItem(strengthenItem, strengthenItemNumberNeed);
		safeItem=ServiceLocator.deleteService.deleteCombiningItem(safeItem, Constants.NUM_ONE);
		stableItem=ServiceLocator.deleteService.deleteCombiningItem(stableItem, Constants.NUM_ONE);
		itemList[0]=strengthenItem;
		itemList[1]=safeItem;
		itemList[2]=stableItem;
//		ServiceLocator.strengthLog.debug("\t" + playerId + "\t" + result + "\t" + pi.getLevel() + "\t" + playerItemId + "\t" + strengthenItemId + "\t" + safeItemId + "\t" + stableItemId);
		if(result!=Constants.SUCCESS){//强化失败送碎片
			combineFailRetrun(playerId,strengthenItemNumberNeed,failAwards);
		}
		return result;
	}
	/**
	 * 强化失败返回碎片
	 * @param playerId 玩家ID
	 * @param costNum  消耗强化部件数量
	 * @param failAwards 返回碎片
	 * @throws Exception
	 */
	private void combineFailRetrun(int playerId,int costNum,List<SysItem> failAwards) throws Exception{
		int costCount = (int)(5*costNum*(0.5*(1+Math.random()+Math.random())));
		int[] awardNumLvs  = {costCount%5,(costCount%25)/5,(costCount%125)/25,(costCount%625)/125,costCount/625};
		int tempCt = 0;
		for(int sysId : Constants.COMBINE_FAIL_AWARD_IDS){
			int awardNum = awardNumLvs[tempCt++];
			if(awardNum>0){
				SysItem failSi= getService.getSysItemByItemId(sysId);
				if(failSi!=null){
					SysItem tmpSi = failSi.clone();
					createService.awardToPlayer(getService.getSimplePlayerById(playerId), tmpSi, new Payment(awardNum,1), null, Constants.BOOLEAN_YES);
					tmpSi.setUnit(awardNum);
					tmpSi.setUnitType(1);
					failAwards.add(tmpSi);
				}else{
					log.error("CombineFail/SysItemNull:\t" + "playerId="+playerId + "\tSysItemId=" +sysId );
				}
			}
		}
	}
	private boolean dealStrengthFailTime(int playerId, int playerItemId,int level) throws TimeoutException, InterruptedException, MemcachedException{
		String key=CacheUtil.strengtFailTimes(playerId, playerItemId);
		Integer times=mcc.get(key);
		if(times==null){
			mcc.set(key,Constants.CACHE_TIMEOUT_DAY ,0);
			times=0;
		}else{
			mcc.set(key,Constants.CACHE_TIMEOUT_DAY ,times);
		}
		if(Constants.STRENGTH_MUST_TIMES[level]<=times){
			return true;
		}
		return false;
		
	}
	/**
	 * 强化的运算器
	 * 
	 * @param successRate
	 * @param faiedNotLoseLevelRate
	 * @param faiedLoseLevelRate
	 * @param faiedBreakItemRate
	 * @return
	 */
	private int strengthen(double successRate, double faiedNotLoseLevelRate, double faiedLoseLevelRate) {
//		successRate = Double.parseDouble(NumberUtil.df.format(successRate));
//		faiedNotLoseLevelRate = Double.parseDouble(NumberUtil.df.format(faiedNotLoseLevelRate));
//		faiedLoseLevelRate = Double.parseDouble(NumberUtil.df.format(faiedLoseLevelRate));
////		int[] counter = new int[1000];
//		List<Integer> list=new ArrayList<Integer>();
//		for (int j = 0; j < (1000 * successRate); j++) {
////			counter[index++] = Constants.SUCCESS;
//			list.add(Constants.SUCCESS);
//		}
//		for (int j = 0; j < (1000 * faiedNotLoseLevelRate); j++) {
////			counter[index++] = Constants.FAILED_NOT_LOSE_LEVEL;
//			list.add(Constants.FAILED_NOT_LOSE_LEVEL);
//		}
//		for (int j = 0; j < (1000 * faiedLoseLevelRate); j++) {
////			counter[index++] = Constants.FAILED_LOSE_LEVEL;
//			list.add(Constants.FAILED_LOSE_LEVEL);
//		}
//		Collections.shuffle(list);
		double random = Math.random();
		if(random<successRate){
			return Constants.SUCCESS;
		}
		
		if(random < successRate + faiedNotLoseLevelRate){
			return Constants.FAILED_NOT_LOSE_LEVEL;
		}
		
		return Constants.FAILED_LOSE_LEVEL;
		
//		return list.get(random.nextInt(1000));
//		return counter[random.nextInt(1000)];
	}

	/**
	 * 合成系统-打孔
	 * 
	 * @param playerId
	 * @param playerItemId
	 * @param index
	 *            孔的位置
	 * @param sloterItemId
	 * @throws Exception
	 */
	public boolean slottingOnPlayerItem(int playerId, int playerItemId, int index, int sloterItemId) throws Exception {
		// check
		Player player = getService.getPlayerById(playerId);
		CommonUtil.checkNull(player, ExceptionMessage.NO_HAVE_THE_CHARACTER);
		// check gp
		int needCCoins = Constants.SLOTTING_COST;
		if (player.getGPoint() < needCCoins) {
			throw new BaseException(ExceptionMessage.NOT_ENOUGH_GPOINT);
		}

		PlayerItem pi = getService.getPlayerItemById(playerId, playerItemId);
		CommonUtil.checkNull(pi, ExceptionMessage.NOT_FIND_ITEM);
		SysItem si = pi.getSysItem();
		if (Constants.NUM_ZERO == si.getIsStrengthen()) {
			throw new BaseException(ExceptionMessage.CANNOT_STRENGTH);
		}
		if (pi.getMaxHoleNum() <= pi.getSlottedHoleNum()) {
			throw new BaseException(ExceptionMessage.NOT_ENOUGH_HOLES);
		}
		if (si.getType() == Constants.DEFAULT_WEAPON_TYPE && (index > 6 || index < 1)) {
			throw new BaseException(ExceptionMessage.ERROR_MESSAGE_ALL);
		}
		if ((si.getType() == Constants.DEFAULT_COSTUME_TYPE || si.getType() == Constants.DEFAULT_PART_TYPE) && (index > 3 || index < 1)) {
			throw new BaseException(ExceptionMessage.ERROR_MESSAGE_ALL);
		}

		PlayerItem sloterItem = getService.getPlayerItemById(playerId, sloterItemId);
		CommonUtil.checkNull(sloterItem, ExceptionMessage.NOT_SLOT_ITEM);
		if (!(sloterItem.getSysItem().getType() == Constants.DEFAULT_MATERIAL_TYPE && sloterItem.getSysItem().getMType() == 11)) {
			throw new BaseException(ExceptionMessage.NOT_SLOT_ITEM);
		}

		player.setGPoint(player.getGPoint() - needCCoins);
		updatePlayerInfo(player);
		soClient.messageUpdatePlayerMoney(player);

		boolean success = slotting(pi, index);

		ServiceLocator.deleteService.deletePlayerPack(playerId, si);
		ServiceLocator.deleteService.deleteCombiningItem(sloterItem, Constants.NUM_ONE);
		if (ConfigurationUtil.SWITCH_XUNLEI_LOG.getIsOn()) {
			nosqlService.addXunleiLog("8.3"
					+ Constants.XUNLEI_LOG_DELIMITER + player.getUserName()
					+ Constants.XUNLEI_LOG_DELIMITER + player.getName()
					+ Constants.XUNLEI_LOG_DELIMITER + player.getRank()
					+ Constants.XUNLEI_LOG_DELIMITER + 1
					+ Constants.XUNLEI_LOG_DELIMITER + (success?1:2)
					+ Constants.XUNLEI_LOG_DELIMITER+ CommonUtil.simpleDateFormat.format(new Date())
					);
		}	
		ServiceLocator.slotLog.debug("\t" + playerId + "\t" + success + "\t" + playerItemId + "\t" + index + "\t" + sloterItemId);

		return success;
	}

	/**
	 * @param pi
	 * @param index
	 * @return
	 * @throws Exception
	 */
	private boolean slotting(PlayerItem pi, int index) throws Exception {
		if (pi.getGunPropertyByHoleIndex(index).split(Constants.SEMICOLON)[0].equals("1")) {
			throw new BaseException(ExceptionMessage.IS_SLOTTED);
		}
		boolean success = false;
		double rate = pi.getSlotRate();

		// 根据vip等级，调整打孔成功率
		Player player = getService.getPlayerById(pi.getPlayerId());
		switch (player.getIsVip()) {
			case 1:
				rate *= 1.25;
				break;
			case 2:
				rate *= 1.5;
				break;
			case 3:
				rate *= 1.75;
				break;
			case 4:
				rate *= 2;
				break;
			case 5:
				rate *= 2.25;
				break;
			case 6:
				rate *= 2.5;
				break;
			default:
				break;
		}

		rate = Double.valueOf(new DecimalFormat("#0.00").format(rate));
		Map<Integer, Integer> randomMap = new HashMap<Integer, Integer>();
		randomMap.put(0, (int) (rate * 100));
		randomMap.put(1, (100 - (int) (rate * 100)));
		int result = CommonUtil.getRandomValueByWeights(randomMap);
		if (Constants.SUCCESSED == result) {
			pi.setGunPropertyByHoleIndex(index, "1;");
			updatePlayerItem(pi);
			success = true;
		}
		return success;
	}

	/**
	 * 合成系统-嵌入
	 * 
	 * @param playerId
	 * @param playerItemId
	 * @param index
	 * @param propertyItemId
	 * @throws Exception
	 */
	public boolean insertToPlayerItem(final int playerId, int playerItemId, int index, int propertyItemId, StringBuilder sb) throws Exception {
		// check 
	
		Player player = getService.getPlayerById(playerId);
		CommonUtil.checkNull(player, ExceptionMessage.NO_HAVE_THE_CHARACTER);

		PlayerItem pi = getService.getPlayerItemById(playerId, playerItemId);
		CommonUtil.checkNull(pi, ExceptionMessage.NOT_FIND_ITEM);
		SysItem si = pi.getSysItem();
		if (Constants.NUM_ZERO == si.getIsStrengthen()) {
			sb.append(ExceptionMessage.CANNOT_STRENGTH);
			return false;
		}

		PlayerItem propertyItem = getService.getPlayerItemById(playerId, propertyItemId);
		CommonUtil.checkNull(propertyItem, ExceptionMessage.NO_PROPERTY_ITEM);
		if (!(propertyItem.getSysItem().getType() == 5 && (propertyItem.getSysItem().getMType() == 9 || propertyItem.getSysItem().getMType() == 8))) {
			sb.append(ExceptionMessage.PROPERTY_ITEM_NOT_MATCH);
			return false;
		}

		if (!(((pi.getSysItem().getType() == 2 || pi.getSysItem().getType() == 3) && propertyItem.getSysItem().getMType() == 9) || (pi.getSysItem().getType() == 1
				&& propertyItem.getSysItem().getMType() == 8 && (pi.getSysItem().getSubType() == 1 || pi.getSysItem().getSubType() == 2 || pi.getSysItem().getSubType() == 3)))) {
			sb.append(ExceptionMessage.PROPERTY_ITEM_NOT_MATCH);
			return false;
		}
		// check gp
		int needCCoins = Constants.INSERT_COST[propertyItem.getSysItem().getMValue() - 1];
		if (player.getGPoint() < needCCoins) {
			sb.append(ExceptionMessage.NOT_ENOUGH_GPOINT);
			return false;
		}

		String currentHoleProperty = pi.getGunPropertyByHoleIndex(index);
		if (currentHoleProperty.length() <= 0) {
			sb.append(ExceptionMessage.HOLE_NOT_OPEN);
			return false;
		} else if (currentHoleProperty.length() > 2) {
			sb.append(ExceptionMessage.HOLE_ALREAD_INSERT);
			return false;
		} else if (currentHoleProperty.equals(CombineProperty.OPENED)) {
			sb.append(ExceptionMessage.HOLE_NOT_SLOT);
			return false;
		}

		player.setGPoint(player.getGPoint() - needCCoins);
		updatePlayerInfo(player);
		soClient.messageUpdatePlayerMoney(player);

		String property = CombineProperty.getProperty(si.getType(), si.getSubType(), propertyItem.getSysItem().getMValue());

		while (pi.isRepeatProperty(property)) {
//			ServiceLocator.insertLog.debug("\t" + playerId + "\t" + property + "\t");
			property = CombineProperty.getProperty(si.getType(), si.getSubType(), propertyItem.getSysItem().getMValue());
		}

		String stringToInsert = "1;" + propertyItem.getSysItem().getMValue() + ";" + property;

		pi.setGunPropertyByHoleIndex(index, stringToInsert);
		updatePlayerItem(pi);
		if (ConfigurationUtil.SWITCH_XUNLEI_LOG.getIsOn()) {
			nosqlService.addXunleiLog("8.4"
					+ Constants.XUNLEI_LOG_DELIMITER + player.getUserName()
					+ Constants.XUNLEI_LOG_DELIMITER + player.getName()
					+ Constants.XUNLEI_LOG_DELIMITER + player.getRank()
					+ Constants.XUNLEI_LOG_DELIMITER + playerItemId
					+ Constants.XUNLEI_LOG_DELIMITER + si.getDisplayNameCN()
					+ Constants.XUNLEI_LOG_DELIMITER + propertyItem.getSysItem().getMValue()
					+ Constants.XUNLEI_LOG_DELIMITER + 1
					+ Constants.XUNLEI_LOG_DELIMITER+ CommonUtil.simpleDateFormat.format(new Date())
					);
		}	
		ServiceLocator.insertLog.debug("\t" + playerId + "\t" + true + "\t" + playerItemId + "\t" + propertyItem.getSysItem().getMValue() + "\t" + index + "\t" + property);
		ServiceLocator.deleteService.deleteCombiningItem(propertyItem, Constants.NUM_ONE);
		ServiceLocator.deleteService.deletePlayerPack(playerId, si);
		return true;
	}

	/**
	 * @param pi
	 * @param index
	 * @throws Exception
	 */
	public int clearDiamond(int playerId, int playerItemId, int index) throws Exception {
		Player player = getService.getPlayerById(playerId);
		PlayerItem pi = getService.getPlayerItemById(playerId, playerItemId);
		if (index < 1 || index > 6) {
			throw new BaseException(ExceptionMessage.ERROR_MESSAGE_ALL);
		}
		SysItem si = getService.getSysItemByItemId(pi.getItemId());
		if (si.getIsStrengthen() == Constants.NUM_ZERO) {
			throw new BaseException(ExceptionMessage.CANT_STRENGTHEN);
		}
		if (si.getType() == Constants.DEFAULT_WEAPON_TYPE && (index > 6 || index < 1)) {
			throw new BaseException(ExceptionMessage.ERROR_MESSAGE_ALL);
		}
		if ((si.getType() == Constants.DEFAULT_COSTUME_TYPE || si.getType() == Constants.DEFAULT_PART_TYPE) && (index > 3 || index < 1)) {
			throw new BaseException(ExceptionMessage.ERROR_MESSAGE_ALL);
		}
		String property = pi.getGunPropertyByHoleIndex(index);
		if (property.length() <= 0) {
			throw new BaseException(ExceptionMessage.HOLE_NOT_OPEN);
		}
		if (property.charAt(0) == '0') {
			throw new BaseException(ExceptionMessage.HOLE_NOT_SLOT);
		} else if (property.length() < 2) {
			throw new BaseException(ExceptionMessage.HOLE_UNINSERT);
		}
		// check gp
		// PlayerInfo playerInfo = getService.getPlayerInfoById(playerId);
		int needGP = Constants.REMOVE_COST;
		if (player.getGPoint() < needGP) {
			throw new BaseException(ExceptionMessage.NOT_ENOUGH_GPOINT);
		}
		pi.takeOffDiamond(index);
		updatePlayerItem(pi);
		player.setGPoint((player.getGPoint() - needGP));
		updatePlayerInfo(player);
		// playerInfo.setXunleiPoint((playerInfo.getXunleiPoint() -
		// needFCPoint));
		// playerInfoDao.update(playerInfo);
		soClient.messageUpdatePlayerMoney(player);

		ServiceLocator.removeLog.debug("\t" + playerId + "\t" + index + "\t" + playerItemId + "\t" + property);
		return 0;
	}

	public void updatePlayerItem(PlayerItem pi) throws Exception {
		ServiceLocator.deleteService.deletePlayerItemInMemcached(pi.getPlayerId(), pi.getSysItem());
		playerItemDao.updatePlayerItem(pi);
	}
	
	public void updatePlayerItemPersonal(PlayerItem pi) throws Exception {
		ServiceLocator.deleteService.deletePlayerItemInMemcached(pi.getPlayerId(), pi.getSysItem());
		playerItemDao.updatePlayerItemPersonal(pi);
	}
	
	
	public int convertPlayerItemWithGP(int playerId, int fromItemId, int toItemId,boolean isLoseCoins) throws Exception {
		PlayerItem from = getService.getPlayerItemById(playerId, fromItemId);
		CommonUtil.checkNull(from, ExceptionMessage.NOT_FIND_ITEM);
		PlayerItem to = getService.getPlayerItemById(playerId, toItemId);
		CommonUtil.checkNull(to, ExceptionMessage.NOT_FIND_ITEM);
		SysItem fromSysItem = from.getSysItem();
		SysItem toSysItem = to.getSysItem();
		Player player = getService.getPlayerById(playerId);
		CommonUtil.checkNull(player, ExceptionMessage.NO_HAVE_THE_CHARACTER);
		int fromOrdLevel = from.getLevel(); 
		int toOrdLevel = to.getLevel(); 
		
		// check gp
		int fromRate=CombineProperty.calcConvertPlayerItemLoseRate(from.getLevel(),from.getSysItem().getRareLevel(),to.getSysItem().getRareLevel());
		int toRate=CombineProperty.calcConvertPlayerItemLoseRate(to.getLevel(),to.getSysItem().getRareLevel(),from.getSysItem().getRareLevel());
		int[] param={6,3,1};
		Double st = 100.0 * param[(from.getSysItem().getType()-1)] * (fromRate/100.0*Math.pow(1.8, from.getLevel())+toRate/100.0*Math.pow(1.8, to.getLevel()));
		Double num=Math.pow(10, ((Math.floor(Math.log10(st)))-1));
		Double needCCoins=(Math.floor(st/num))*num;
	
		int need=isLoseCoins? needCCoins.intValue()+ Constants.CONVERT_COST : Constants.CONVERT_COST;
	
		if (player.getGPoint() < need) {
			throw new BaseException(ExceptionMessage.NOT_ENOUGH_GPOINT);
		}
		player.setGPoint(player.getGPoint() - need);
		updatePlayerInfo(player);
		soClient.messageUpdatePlayerMoney(player);
		
		convert(from, to);

		if (from.getIsBind().equals(Constants.BOOLEAN_NO)) {
			from.setIsBind(Constants.BOOLEAN_YES);
		}
		if (to.getIsBind().equals(Constants.BOOLEAN_NO)) {
			to.setIsBind(Constants.BOOLEAN_YES);
		}

		// update new data in memcached
		playerItemDao.updatePlayerItem(from);
		playerItemDao.updatePlayerItem(to);
		// delete memcached
		ServiceLocator.deleteService.deletePlayerItemInMemcached(playerId, fromSysItem);
		ServiceLocator.deleteService.deletePlayerItemInMemcached(playerId, toSysItem);
		ServiceLocator.deleteService.deletePlayerPack(playerId, fromSysItem);
		ServiceLocator.deleteService.deletePlayerPack(playerId, toSysItem);

		// 成长任务20：进行1次装备转化
		ServiceLocator.updateService.updatePlayerGrowthMission(player, GrowthMissionType.ARM_CONVERT);
		if (ConfigurationUtil.SWITCH_XUNLEI_LOG.getIsOn()) {
			nosqlService.addXunleiLog("8.2"
					+ Constants.XUNLEI_LOG_DELIMITER + player.getUserName()
					+ Constants.XUNLEI_LOG_DELIMITER + player.getName()
					+ Constants.XUNLEI_LOG_DELIMITER + player.getRank()
					+ Constants.XUNLEI_LOG_DELIMITER + fromSysItem.getDisplayNameCN()
					+ Constants.XUNLEI_LOG_DELIMITER + from.getLevel()
					+ Constants.XUNLEI_LOG_DELIMITER + toSysItem.getDisplayNameCN()
					+ Constants.XUNLEI_LOG_DELIMITER + to.getLevel()
					+ Constants.XUNLEI_LOG_DELIMITER + 5
					+ Constants.XUNLEI_LOG_DELIMITER+ CommonUtil.simpleDateFormat.format(new Date())
					);
		}	
		ServiceLocator.convertLog.debug("\t" + playerId + "\t" + true + "\t"  + from.getId() + "\t" + fromOrdLevel + "\t" + from.getLevel()  + "\t" + to.getId() + "\t" + toOrdLevel + "\t" + to.getLevel() + "\t" + 5 + "\t useGP" +need);
		
		return 5;
	}
	/**
	 * 合成系统-转换
	 * 
	 * @param playerId
	 * @param fromItemId
	 * @param toItemId
	 * @param converterItemId
	 *            转换工具
	 * @param saferItemId
	 *            转换安定装置
	 * @throws Exception
	 */
	public int convertPlayerItem(int playerId, int fromItemId, int toItemId) throws Exception {
		// check
		Player player = getService.getPlayerById(playerId);
		CommonUtil.checkNull(player, ExceptionMessage.NO_HAVE_THE_CHARACTER);
		// check gp
		int needCCoins = Constants.CONVERT_COST;
		if (player.getGPoint() < needCCoins) {
			throw new BaseException(ExceptionMessage.NOT_ENOUGH_GPOINT);
		}

		
		PlayerItem from = getService.getPlayerItemById(playerId, fromItemId);
		CommonUtil.checkNull(from, ExceptionMessage.NOT_FIND_ITEM);
		PlayerItem to = getService.getPlayerItemById(playerId, toItemId);
		CommonUtil.checkNull(to, ExceptionMessage.NOT_FIND_ITEM);
		SysItem fromSysItem = from.getSysItem();
		SysItem toSysItem = to.getSysItem();

		int fromOrdLevel = from.getLevel(); 
		int toOrdLevel = to.getLevel(); 
				
		if (!(fromSysItem.getType().equals(toSysItem.getType()) && fromSysItem.getSubType().equals(toSysItem.getSubType()))) {
			throw new BaseException(ExceptionMessage.PLAYER_ITEM_NOT_MATCH);
		}

		if (toSysItem.getIsStrengthen() == Constants.NUM_ZERO || fromSysItem.getIsStrengthen() == Constants.NUM_ZERO) {
			throw new BaseException(ExceptionMessage.CANT_STRENGTHEN);
		}
		// check 耐久
//		if (from.getDurable() != 100 || to.getDurable() != 100) {
//			throw new BaseException(ExceptionMessage.DURABLE_NOT_FULL);
//		}

		player.setGPoint(player.getGPoint() - needCCoins);
		updatePlayerInfo(player);
		soClient.messageUpdatePlayerMoney(player);
		int isFromLoseLevel = 1;
		int isToLoseLevel = 4;
		
		
		if (CombineProperty.getConvertPlayerItemLoseLevelResult(from.getLevel(),fromSysItem.getRareLevel(),toSysItem.getRareLevel())) {
			if (from.isLevelChangeLevel()) {
				from.closeHole();
			}
			from.setLevel(from.getLevel() - 1);
			isFromLoseLevel = 2;
		}
		if (CombineProperty.getConvertPlayerItemLoseLevelResult(to.getLevel(),toSysItem.getRareLevel(),fromSysItem.getRareLevel())) {
			if (to.isLevelChangeLevel()) {
				to.closeHole();
			}
			to.setLevel(to.getLevel() - 1);
			isToLoseLevel = 8;
		}
		
		convert(from, to);

		if (from.getIsBind().equals(Constants.BOOLEAN_NO)) {
			from.setIsBind(Constants.BOOLEAN_YES);
		}
		if (to.getIsBind().equals(Constants.BOOLEAN_NO)) {
			to.setIsBind(Constants.BOOLEAN_YES);
		}

		// update new data in memcached
		playerItemDao.updatePlayerItem(from);
		playerItemDao.updatePlayerItem(to);
		// delete memcached
		ServiceLocator.deleteService.deletePlayerItemInMemcached(playerId, fromSysItem);
		ServiceLocator.deleteService.deletePlayerItemInMemcached(playerId, toSysItem);
		ServiceLocator.deleteService.deletePlayerPack(playerId, fromSysItem);
		ServiceLocator.deleteService.deletePlayerPack(playerId, toSysItem);

		// 成长任务20：进行1次装备转化
		ServiceLocator.updateService.updatePlayerGrowthMission(player, GrowthMissionType.ARM_CONVERT);
		if (ConfigurationUtil.SWITCH_XUNLEI_LOG.getIsOn()) {
			nosqlService.addXunleiLog("8.2"
					+ Constants.XUNLEI_LOG_DELIMITER + player.getUserName()
					+ Constants.XUNLEI_LOG_DELIMITER + player.getName()
					+ Constants.XUNLEI_LOG_DELIMITER + player.getRank()
					+ Constants.XUNLEI_LOG_DELIMITER + fromSysItem.getDisplayNameCN()
					+ Constants.XUNLEI_LOG_DELIMITER + from.getLevel()
					+ Constants.XUNLEI_LOG_DELIMITER + toSysItem.getDisplayNameCN()
					+ Constants.XUNLEI_LOG_DELIMITER + to.getLevel()
					+ Constants.XUNLEI_LOG_DELIMITER + (isToLoseLevel | isFromLoseLevel)
					+ Constants.XUNLEI_LOG_DELIMITER+ CommonUtil.simpleDateFormat.format(new Date())
					);
		}	
		ServiceLocator.convertLog.debug("\t" + playerId + "\t" + true + "\t"  + from.getId() + "\t" + fromOrdLevel + "\t" + from.getLevel()  + "\t" + to.getId() + "\t" + toOrdLevel + "\t" + to.getLevel() + "\t" + (isToLoseLevel | isFromLoseLevel));
		return (isToLoseLevel | isFromLoseLevel);
	}

	private void convert(PlayerItem from, PlayerItem to) {
		int toLevel = from.getLevel();
		String toPro2 = from.getGunProperty2();
		String toPro3 = from.getGunProperty3();
		String toPro4 = from.getGunProperty4();
		String toPro5 = from.getGunProperty5();
		String toPro6 = from.getGunProperty6();
		String toPro7 = from.getGunProperty7();

		from.setLevel(to.getLevel());
		from.setGunProperty2(to.getGunProperty2());
		from.setGunProperty3(to.getGunProperty3());
		from.setGunProperty4(to.getGunProperty4());
		from.setGunProperty5(to.getGunProperty5());
		from.setGunProperty6(to.getGunProperty6());
		from.setGunProperty7(to.getGunProperty7());

		to.setLevel(toLevel);
		to.setGunProperty2(toPro2);
		to.setGunProperty3(toPro3);
		to.setGunProperty4(toPro4);
		to.setGunProperty5(toPro5);
		to.setGunProperty6(toPro6);
		to.setGunProperty7(toPro7);

		// from.closeAllHoles();
		// to.closeAllHoles();
		// while((from.getOpenedUnSlottedHoleNum() + from.getSlottedHoleNum()) <
		// from.getMaxHoleNum()){
		// from.openHole();
		// }
		// while((to.getOpenedUnSlottedHoleNum() + to.getSlottedHoleNum()) <
		// to.getMaxHoleNum()){
		// to.openHole();
		// }
	}
	
	public MeltingReslut meltingPlayerItem(final int playerId,final ArrayList<Integer> meltingInputs,final int qualityPropId)throws Exception{
		MeltingReslut meltingReslut = new MeltingReslut();
		StringBuilder playeritems=new StringBuilder();
		Map<Integer, SysItem> sysItemMap = sysItemDao.getAllSysItemMap();	
		Map<Integer, PlayerItem> playerItemMap = playerItemDao.getPlayerItemMap(playerId);
		
		PlayerMelting melting = getService.getPlayerMelting(playerId, false);
		
		
		int mType = MeltingConstants.ProcessingUnitMType;
		PlayerItem qualityProp = null;
		
		if(qualityPropId>0){
			qualityProp = playerItemMap.get(qualityPropId);
			if(null != qualityProp){
				SysItem qsi=sysItemMap.get(qualityProp.getItemId());
				qualityProp.setSysItem(qsi);
				qualityProp.initWithoutPart(qsi);
				mType = qualityProp.getSysItem().getMType();
				if(!MeltingConstants.MeltingPropMTypes.contains(mType)){
					meltingReslut.setResult(7);
					return meltingReslut;
				}
			}else {
				meltingReslut.setResult(2);
				return meltingReslut;
			}
		}
		
		if(mType == MeltingConstants.ProcessingUnitMType && melting.getNum()<1){
			meltingReslut.setResult(1);
			return meltingReslut;
		}
		
		HashMultiset<Integer> meltingInputMultiset = HashMultiset.create(meltingInputs);
		HashMultiset<PlayerItem> playerItemMultiset = HashMultiset.<PlayerItem>create();
		
		for(Multiset.Entry<Integer> entry:meltingInputMultiset.entrySet()){
			PlayerItem playerItemById = playerItemMap.get(entry.getElement());
			if(null == playerItemById||playerItemById.getIsDeleted().equals(Constants.BOOLEAN_YES)){
				meltingReslut.setResult(3);
				return meltingReslut;
			}
			
			SysItem si=sysItemMap.get(playerItemById.getItemId());
			playerItemById.setSysItem(si);
			playerItemById.initWithoutPart(si);
			
			if(playerItemById.getQuantity()<entry.getCount()){
				meltingReslut.setResult(4);
				return meltingReslut;
			}
			if(playerItemById.getSysItem().getEvaluateRmb()<=0&&playerItemById.getLevel()<=0){
				meltingReslut.setResult(5);
				return meltingReslut;
			}
			playerItemMultiset.add(playerItemById, entry.getCount());
			playeritems.append(playerItemById.getSysItem().getDisplayNameCN()).append(",");
		}
		
		if(meltingInputs.size()>melting.getSlotNum()){
			meltingReslut.setResult(6);
			return meltingReslut;
		}

		double meltingQuality = calcMeltingQuality(melting, playerItemMultiset, qualityProp);
		int exp = (int) (meltingQuality*50);
		double meltingRemaind = calcMeltingRemaind(meltingQuality);
		if(mType == MeltingConstants.ProcessingUnitMType){
			melting.setRemaind(meltingRemaind);
			melting.addExp(exp);
		}
		if(mType == MeltingConstants.ProcessingUnitMType){//加成装置
			meltingPlayerItemByProcessingUnit( playerId, playerItemMultiset, qualityProp, melting,meltingQuality);
			meltingReslut.setRate((int) (Math.random()*5)+1);
			meltingReslut.setPrice(ServiceLocator.getService.getLookoverFc(melting.getRemaind()*10));
		}else if(mType == MeltingConstants.ModuleMType){//设计模块
			meltingReslut.setMeltingAward(meltingPlayerItemByModule( playerId,playerItemMultiset, qualityProp, melting));
		}
		
		meltingReslut.setMt(mType);
		
		for(Multiset.Entry<PlayerItem> entry:playerItemMultiset.entrySet()){
			PlayerItem playerItem = entry.getElement();
			SysItem sysItem = playerItem.getSysItem();
			if(sysItem.getUnitType() == 1){
				ServiceLocator.deleteService.deleteCombiningItem(playerItem, entry.getCount());
			}else{
				ServiceLocator.deleteService.deletePlayerItem(playerItem);
				ServiceLocator.deleteService.deletePlayerPack(playerId, sysItem);
			}
		}
		ServiceLocator.deleteService.deleteCombiningItem(qualityProp,1);
		Player player = getService.getPlayerById(playerId);
		if(mType == MeltingConstants.ProcessingUnitMType){
			melting.consume(player.getIsVip());
		}
		updatePlayerMelting(melting);
		
		updatePlayerGrowthMission(playerId, GrowthMissionType.COMBINE_MELTING);
		
		ServiceLocator.meltingLog.debug(LogUtils.JoinerByTab.join("meltingPlayerItem",playerId,meltingInputs.toString(),qualityPropId));
		meltingReslut.setPlayerMelting(melting);
		
		if (ConfigurationUtil.SWITCH_XUNLEI_LOG.getIsOn()) {
			 SysItem qsi=null;
			 if(qualityProp!=null){qsi=sysItemMap.get(qualityProp.getItemId());}
				ServiceLocator.nosqlService.addXunleiLog("8.5" + Constants.XUNLEI_LOG_DELIMITER +player.getUserName()
						+ Constants.XUNLEI_LOG_DELIMITER + player.getName()
						+ Constants.XUNLEI_LOG_DELIMITER + player.getRank()
						+ Constants.XUNLEI_LOG_DELIMITER + melting.getLevel()
						+ Constants.XUNLEI_LOG_DELIMITER + CommonUtil.cutLastWord(playeritems.toString())
						+ Constants.XUNLEI_LOG_DELIMITER + (null==qsi?"":qsi.getDisplayNameCN())
						+ Constants.XUNLEI_LOG_DELIMITER + meltingRemaind
						+ Constants.XUNLEI_LOG_DELIMITER + CommonUtil.simpleDateFormat.format(new Date()));
		}
		 
		return meltingReslut;
	}
	
	public MeltingAward meltingPlayerItemByModule(int playerId,HashMultiset<PlayerItem> playerItemMultiset,PlayerItem meltingModule,PlayerMelting melting) throws Exception{
		HashMultiset<Integer> sysItemMultiset = HashMultiset.<Integer>create(); 
		for(Multiset.Entry<PlayerItem> entry:playerItemMultiset.entrySet()){
			sysItemMultiset.add(entry.getElement().getItemId(), entry.getCount());
		}
		
		HashMultiset<Integer> moduleMultiset = HashMultiset.create(Iterables.transform(MeltingConstants.splitterByColon.split(meltingModule.getSysItem().getResourceStable()), MeltingConstants.functionStrToInt));
		
		Integer[] array = Iterables.toArray(Iterables.transform(MeltingConstants.splitterByUnderScore.split(meltingModule.getSysItem().getResourceChangeable()), MeltingConstants.functionStrToInt), Integer.class);
		MeltingAward meltingAward = new MeltingAward(array[0], getService.getSysItemByItemId(array[0]), array[1], (byte)(int)array[2]);
		
		for(Multiset.Entry<Integer> entry:moduleMultiset.entrySet()){
			if(entry.getCount()!=sysItemMultiset.count(entry.getElement())){
				throw new BaseException(ExceptionMessage.Melting_By_Module_ERROR);
			}
		}
		if(sysItemMultiset.size()!=moduleMultiset.size()){
			throw new BaseException(ExceptionMessage.Melting_By_Module_ERROR);
		}
		
		ServiceLocator.createService.sendItem(meltingAward.getItem(), getService.getPlayerById(playerId), new Payment(meltingAward.getUnit(), meltingAward.getUnitType()), Constants.BOOLEAN_NO, Constants.BOOLEAN_YES, Constants.BOOLEAN_NO);
		
		ServiceLocator.meltingLog.debug(LogUtils.JoinerByTab.join("meltingByModule",playerId,meltingAward.getItemId(),meltingAward.getUnit(),meltingAward.getUnitType()));
		
		return meltingAward;
	}
	
	public int getMeltingAwardUnit(double meltingQuality){
		return (int) Math.ceil(meltingQuality*(0.5+Math.random())*0.1);
	}
	
	//								复活币	勋章		强化部件
	//int[] MeltingResultItem = {	4800,	4479,	125};
	//int[] MeltingResultProp = {	1,		1,		1};
	public List<MeltingAward> getRandomMeltingAward(double meltingQuality,int num){
		//final int limit  = meltingQuality<100?(int) (meltingQuality/200):MeltingConstants.MeltingResultItem.length-1;
		final int limit  = meltingQuality<MeltingConstants.MeltingResultLimit[0]?1:(meltingQuality<MeltingConstants.MeltingResultLimit[1]?2:MeltingConstants.MeltingResultItem.length);
		Multiset<Integer> map = HashMultiset.create(limit);
		for(int i = 0;i<limit;i++){
			map.add(MeltingConstants.MeltingResultItem[i], MeltingConstants.MeltingResultProp[i]);
		}
		
		List<Integer> resultItems = CommonUtil.getRandomValueByWeightsWithoutIntArrayWithNum(map, num);
		
		List<MeltingAward> awards = new ArrayList<MeltingAward>(num);
		for(int n = 0;n<num ;n++){
			awards.add(new MeltingAward(resultItems.get(n), getMeltingAwardUnit(meltingQuality), 1));
		}
		
		return awards;
	}
	
	
	public void meltingPlayerItemByProcessingUnit(int playerId,HashMultiset<PlayerItem> meltingInputs,PlayerItem meltingProcessingUnit,PlayerMelting melting,double meltingQuality) throws Exception{
		
//		Multiset<MeltingAward> calcMeltingAwardPool = calcMeltingAwardPool(meltingQuality);
		
		List<MeltingAward> awards = getRandomMeltingAward(meltingQuality,3);
		int[] tmp = new int[awards.size()*2];
		for (int i =0;i<awards.size();i++) {
			tmp[i*2]	=awards.get(i).getItemId();
			tmp[i*2+1]	=awards.get(i).getUnit();
		}
		//List<MeltingAward> fineAward = CommonUtil.getRandomValueByWeightsWithoutIntArrayWithNum(calcMeltingAwardPool, 3);
//		List<Integer> transform = Lists.transform(defaultAward, new Function<MeltingAward,Integer>() {
//			@Override
//			public Integer apply(MeltingAward input) {
//				return input.getId();
//			}
//		});
		mcc.set(CacheUtil.MeltingAward(playerId), Constants.CACHE_TIMEOUT_DAY, tmp);
		ServiceLocator.meltingLog.debug(LogUtils.JoinerByTab.join("meltingByProcessingUnit",playerId,Arrays.toString(tmp)));
	}
	
	
	/**
	 * 本次熔炼值（浮点）=0.9*（放入物品的价值评估总和*（0.7+0.3*放入物品个数）*（0.8+品质道具提升值）+上次熔炼残余值），最大1000
	 * @param player
	 * @param meltingInputs
	 * @param meltingProcessingUnit
	 * @return
	 * @throws Exception 
	 * @throws NumberFormatException 
	 */
	public double calcMeltingQuality(PlayerMelting melting,HashMultiset<PlayerItem> meltingInputs,PlayerItem meltingProcessingUnit) throws Exception{
		double evaluateRmbSum = 0;
		double evaluateLevelSum = 0;
		for (Multiset.Entry<PlayerItem> entry : meltingInputs.entrySet()) {
			evaluateRmbSum+=entry.getElement().getSysItem().getEvaluateRmb()*entry.getCount();
			double evaluateLevelType = 0;
			switch (entry.getElement().getSysItem().getType()) {
				case Constants.DEFAULT_WEAPON_TYPE:evaluateLevelType =9; break;
				case Constants.DEFAULT_COSTUME_TYPE:evaluateLevelType =4.5; break;
				case Constants.DEFAULT_PART_TYPE:evaluateLevelType =1.5; break;
				case Constants.DEFAULT_ITEM_TYPE:evaluateLevelType =3; break;
			}
			
			evaluateLevelSum+=(evaluateLevelType)*(Math.pow(1.8, entry.getElement().getLevel())-1)/(0.8);
		}
		
		double evaluateSum = evaluateRmbSum+evaluateLevelSum;
		
		int mValue = 0;
		if(null != meltingProcessingUnit){
			mValue = meltingProcessingUnit.getSysItem().getMValue();
		}
		double meltingQuality = 0.9*(evaluateSum*(0.7+0.03*meltingInputs.size())*(0.8+mValue/100.0)+melting.getRemaind());
		return Math.min(1314, meltingQuality);
	}
	
	/**
	 * 本次熔炼残余值（浮点）=本次熔炼值/10，最大100
	 * @param meltingQuality
	 * @return
	 */
	public double calcMeltingRemaind(double meltingQuality){
		return Math.min(131, meltingQuality/10);
	}
	
	/**
	 * 熔炼奖池，物品及概率
	 * @param meltingQuality
	 * @return
	 * @throws Exception
	 */
	public Multiset<MeltingAward> calcMeltingAwardPool(double meltingQuality) throws Exception{
		Map<Integer, MeltingAward> allMeltingAward = meltingAwardDao.getAllMeltingAward();
		
		Map<MeltingAward, Double> meltingAwardScale = new HashMap<MeltingAward, Double>();
		Map<Byte, Double> meltingAwardScaleSumByType = new HashMap<Byte, Double>();
		
		double coefficient = meltingQuality < 500 ? 0.3323 * Math.log(meltingQuality) - 2.0776 : 0.0008 * meltingQuality - 0.4;
		
		for (MeltingAward meltingAward : allMeltingAward.values()) {
			double scale = Math.pow(meltingAward.getUnit(), coefficient);
			meltingAwardScale.put(meltingAward, scale);
			Double sum = meltingAwardScaleSumByType.get(meltingAward.getType());
			if(null == sum){
				meltingAwardScaleSumByType.put(meltingAward.getType(), scale);
			}else{
				meltingAwardScaleSumByType.put(meltingAward.getType(), scale+sum);
			}
		}

		Multiset<MeltingAward> meltingAwardRate = HashMultiset.<MeltingAward>create();
		
		for (MeltingAward meltingAward : allMeltingAward.values()) {
			meltingAwardRate.add(meltingAward,(int) (15000*meltingAwardScale.get(meltingAward)/meltingAwardScaleSumByType.get(meltingAward.getType())));
		}
		
		return meltingAwardRate;
	}
	
	
	private String getAwardCycleMissionListView(Player player, List<SysItem> normalList, List<SysItem> vipList, CycleMissionIncome income, byte upgrade) throws Exception {
		boolean isVip = player.getIsVip() >= 1;
		SysItem medalSysItem = getService.getMedalSysItem();
		List<AwardItemVo> awardItemVos = new ArrayList<AwardItemVo>();
		if(income.getMedal()>0){
			
			awardItemVos.add(new AwardItemVo(0, medalSysItem, income.getMedal()));
		}
		if (isVip&&income.getVipMedal()>0) {
			
			awardItemVos.add(new AwardItemVo(1, medalSysItem, income.getVipMedal()));
		}
		if(null != normalList){
			for (SysItem sysItem : normalList) {
				awardItemVos.add(new AwardItemVo(0, sysItem, 1));
			}
		}
		if(isVip && null != vipList){
			for (SysItem sysItem : vipList) {
				awardItemVos.add(new AwardItemVo(0, sysItem, 1));
			}
		}
		return  Converter.awardCycleMissionList(awardItemVos, income.getCal(),income.getExp(),income.getVipCal(),income.getVipExp(), upgrade, isVip);
	}
	public String fillCycleMissionListView(List<PlayerMission> playerMissionList,int playerId) throws Exception {
		int missionNeedAward = getService.missionNeedAward(playerId);
		SysItem medalSysItem = getService.getMedalSysItem();
		for (PlayerMission playerMission : playerMissionList) {
			CycleMissionIncome income = playerMission.getCmIncome();			
			List<AwardItemVo> awardItemVos = new ArrayList<AwardItemVo>();
			if(income.getMedal()>0){
				awardItemVos.add(new AwardItemVo(0, medalSysItem, income.getMedal()));
			}
			if ( income.getVipMedal()>0) {
				awardItemVos.add(new AwardItemVo(1, medalSysItem, income.getVipMedal()));
			}
			List<SysItem> normalList = playerMission.getSysMission().getNormalItemList();
			List<SysItem> vipList = playerMission.getSysMission().getVipItemList();
			if(null != normalList){
				for (SysItem sysItem : normalList) {
					awardItemVos.add(new AwardItemVo(0, sysItem, 1));
				}
			}
			if( null != vipList){
				for (SysItem sysItem : vipList) {
					awardItemVos.add(new AwardItemVo(0, sysItem, 1));
				}
			}
			playerMission.setAwardItemVos(awardItemVos);
			//vip升级经验
			switch (income.getLevel()) {
			case 1:
				playerMission.setVipUpGradeExp(Constants.VIP_UPGRADE_EXP[2][0]);
				break;
			case 2:
				playerMission.setVipUpGradeExp(Constants.VIP_UPGRADE_EXP[2][1]);
				break;
			case 3 :
				playerMission.setVipUpGradeExp(Constants.VIP_UPGRADE_EXP[2][2]);
				break;
			default:
				break;
			}
		}
		return Converter.missionList(playerMissionList,missionNeedAward);
	}
	public void updateOnlineAwardInMemcache(OnlineAward onlineAwardId) throws Exception{
		onlineAwardDao.updateOnlineAward(onlineAwardId);
	}
	/**
	 * ps:更新失败不会外抛异常
	 * @param meltingEnergy
	 * @throws Exception 
	 */
	public PlayerMelting updatePlayerMelting(PlayerMelting playerMelting) throws Exception{
		return playerMeltingDao.updatePlayerMelting(playerMelting);
	}
	
	public PlayerMelting consumeMeltingEnergy(final int playerId) throws Exception{
		Player player = getService.getPlayerById(playerId);
		return updatePlayerMelting(ServiceLocator.getService.getPlayerMelting(playerId,false).consume(player.getIsVip()));
	}

	public void payFc(int playerId,int cost) throws Exception{
		PlayerInfo playerInfo = getService.getPlayerInfoById(playerId);
		int cr = playerInfo.getXunleiPoint();
		if (cr < cost) {
			throw new CRNotEnoughException(ExceptionMessage.NOT_ENOUGH_CR);// not have enough FC point
		} else {
			int leftMoney = cr - cost;
			playerInfo.setXunleiPoint(leftMoney);
			ServiceLocator.updateService.updatePlayerActivity(Constants.ACTION_ACTIVITY.PAY_FC.getValue(), playerId, null, cost, 0, null, null);
			playerInfoDao.update(playerInfo);
			mcc.delete(CacheUtil.oPlayerInfo(playerId));
		}
	}
	
	public String teamBuffActivate(PlayerTeam playerTeam,SysTeamBuff sysTeamBuff,TeamBuff teamBuff,Player player)throws Exception{
		if(null == teamBuff) return TeamConstants.BUFF_NOT_DEBLOCK;
		SysItem sysItem = getService.getSysItemByItemId(sysTeamBuff.getSysItemId());
		if(null == sysItem) return TeamConstants.BUFF_NOT_EXIST;
		if(playerTeam.getContribution()<Team.getContributionLimitByLevel(sysTeamBuff.getRank())) return TeamConstants.BUFF_CONTRIBUTIONLIMIT;
		PlayerItem playerBuff = getService.getPlayerBuff(playerTeam.getPlayerId(), sysItem.getIBuffId());
		if(null != playerBuff&&playerBuff.getItemId()==sysTeamBuff.getSysItemId()){
//			item2=playerBuff.getSysItem();
			return TeamConstants.BUFF_YET_GET;
		} 
		if (player.getGPoint() < sysTeamBuff.getCost()) {
			throw new NotBuyEquipmentException(TeamConstants.BUFF_ACTIVATE_FAIL);// not have enough GPoint
		}
		
		if(null!=playerBuff&&StringUtil.toInt(playerBuff.getSysItem().getIValue())>StringUtil.toInt(sysItem.getIValue())) return TeamConstants.BUFF_LOW_GET;
		if(playerTeam.getContribution()<Team.getContributionLimitByLevel(sysTeamBuff.getRank())) return TeamConstants.BUFF_NOT_ENOUGH_CONTRIBUTION;
		try{
			int playerItem = createService.buyPlayerItem(null,player.getUserName(), player, sysItem, new Payment(sysTeamBuff.getPayType(),sysTeamBuff.getUnitType(),sysTeamBuff.getCost(),sysTeamBuff.getUnit()), Constants.BOOLEAN_NO, 1, player);
			ServiceLocator.createService.useItemById(player, sysItem.getType(), playerItem, "", 0, 0);
			nosqlService.publishEvent(new TeamBuffActivateEvent( player.getId(), player.getName(),sysItem));
		}catch (BaseException e) {
			throw new NotBuyEquipmentException(TeamConstants.BUFF_ACTIVATE_FAIL);// not have enough GPoint
		}
		
		int playerId = player.getId();
		mcc.delete(CacheUtil.oPlayer(playerId));
		mcc.delete(CacheUtil.sPlayer(playerId));
		// mcc.delete(CacheUtil.oStorage(playerId,type,0));
		ServiceLocator.deleteService.deletePlayerItemInMemcached(playerId, sysItem);
		return null;
	}
	
	public void checkTeamLeaderLogin() throws Exception{
		List<PlayerTeam> selectCheckTeamList = playerTeamDao.selectCheckTeamList();
		for (PlayerTeam ptid : selectCheckTeamList) {
			Map<Integer, PlayerTeam> playerTeams = playerTeamDao.getPlayerTeams(ptid.getTeamId());
			for (PlayerTeam playerTeam : playerTeams.values()) {
				playerTeam.setPlayer(getService.getSimplePlayerById(playerTeam.getPlayerId()));
			}
			
			Collection<PlayerTeam> filter = Collections2.filter(playerTeams.values(),new Predicate<PlayerTeam>() {
				@Override
				public boolean apply(PlayerTeam input) {
					return System.currentTimeMillis() - input.getPlayer().getLastLoginTime()*1000L<TeamConstants.OneDay*15;
				}
			});
			if(filter.size()>0){
				PlayerTeam max = new Ordering<PlayerTeam>(){
					@Override
					public int compare(PlayerTeam left,PlayerTeam right) {
						return Ints.compare(left.getContribution(),right.getContribution());
					}
				}.max(filter);
				if(ptid.getPlayerId() != max.getPlayerId()){
					playerTeamDao.updateJob(playerTeams.get(ptid.getId()), TeamConstants.TEAMJOB.TEAM_MEMBER.getValue());
					playerTeamDao.updateJob(max, TeamConstants.TEAMJOB.TEAM_CAPTAIN.getValue());
					ServiceLocator.teamCheckLog.info(LogUtils.JoinerByTab.join(ptid.getTeamId(),ptid.getPlayerId(),max.getPlayerId()));
				}
			}
		}
	}
	
	public String teamBuffDeblock(PlayerTeam playerTeam,Team team,int rank,Player player)throws Exception{
		if(playerTeam.getJob() != TeamConstants.TEAMJOB.TEAM_CAPTAIN.getValue()) return TeamConstants.Not_Enough_Right;
		if(rank>team.getLevel()) return TeamConstants.Not_Enough_Rank;
		
		Map<Integer, TeamBuff> teamBuffsByTeamId = teamBuffDao.getTeamBuffsByTeamId(team.getId());
		Collection<SysTeamBuff> sysTeamBuffsByRank = sysTeamBuffDao.getSysTeamBuffsByRank(rank);
		
		for (SysTeamBuff sysTeamBuff : sysTeamBuffsByRank) {
			TeamBuff teamBuff = teamBuffsByTeamId.get(sysTeamBuff.getId());
			if(null == teamBuff){
				teamBuff = new TeamBuff();
				teamBuff.setId(sysTeamBuff.getId());
				teamBuff.setIsEnable(true);
				teamBuff.setTeamId(playerTeam.getTeamId());
				teamBuffDao.createTeamBuff(teamBuff);
			}
		}
		return null;
	}

	/**
	 * 
	 * @param type
	 * @param playerId
	 * @param earnExp 目前只在vip经验块,充值时用
	 * @throws Exception
	 */
	public int updateVipUpExp(int type, int playerId, int... earnExp)
			throws Exception {
		
		int addedExp = 0;

		if (type != Constants.VIP_EARN_EXP_METHODS.CHARGE.getValue()) {
			// 获取状态位
			int status = nosqlService.hasGetVipUpExp(type, playerId);

			if (!nosqlService.isVipUpExpStatusFull(type, status)) {
				nosqlService.updateVipUpExpStatus(type, playerId, status);

				if (earnExp.length > 0) {
					if (earnExp[0] > 0) {
						addedExp = earnExp[0];
					}
				} else {
					if (type == Constants.VIP_EARN_EXP_METHODS.DAILYCHECK.getValue()) {
						addedExp = Constants.VIP_UPGRADE_EXP[0][0];
					} else if (type == Constants.VIP_EARN_EXP_METHODS.ONLINETIME.getValue()) {
						addedExp = Constants.VIP_UPGRADE_EXP[1][status];
					} else if (type == Constants.VIP_EARN_EXP_METHODS.DAILYMISSIONSIMPLE.getValue()) {
						addedExp = Constants.VIP_UPGRADE_EXP[2][0];
					} else if (type == Constants.VIP_EARN_EXP_METHODS.DAILYMISSIONNORMAL.getValue()) {
						addedExp = Constants.VIP_UPGRADE_EXP[2][1];
					} else if (type == Constants.VIP_EARN_EXP_METHODS.DAILYMISSIONHARD.getValue()) {
						addedExp = Constants.VIP_UPGRADE_EXP[2][2];
					} else if (type == Constants.VIP_EARN_EXP_METHODS.STAGECLEAR.getValue()) {
						if (status < 5) { // 每天前0-5局游戏
							addedExp = Constants.VIP_UPGRADE_EXP[3][0];
						} else if (status >= 5 && status < 15) { // 每天前6-15局游戏
							addedExp = Constants.VIP_UPGRADE_EXP[3][1];
						} else {
							addedExp = Constants.VIP_UPGRADE_EXP[3][2];
						}
					}

				}
			}
		} else {
			if (earnExp.length > 0) {
				if (earnExp[0] > 0) {
					addedExp = earnExp[0];
				}
			}
		}
		if (addedExp > 0) {
			Player player = getService.getPlayerById(playerId);
			CommonUtil
					.checkNull(player, ExceptionMessage.NO_HAVE_THE_CHARACTER);
			int preVipExp = player.getVipExp();
			int preVip = player.getIsVip();
			int preMaxStrengthLevel = ServiceLocator.getService
					.getMaxStrengthLevel(playerId);
			int currVipExp = preVipExp + addedExp > Constants.VIP_LEVEL_EXP[Constants.VIP_LEVEL_EXP.length - 1] ? Constants.VIP_LEVEL_EXP[Constants.VIP_LEVEL_EXP.length - 1]
					: preVipExp + addedExp;
			player.setVipExp(currVipExp);
			updatePlayerInfoOnly(player);

			int maxStrengthLevel = ServiceLocator.getService
					.getMaxStrengthLevel(playerId);
			if (maxStrengthLevel > preMaxStrengthLevel) {
				soClient.puchCMDtoClient(player.getName(), CommonUtil
						.messageFormat(CommonMsg.REFRESH_MAX_STRENGHT_LEVEL,
								maxStrengthLevel));
			}
			// vip升级
			int currVipRank = player.getIsVip();
			if (currVipRank > preVip) {
				ServiceLocator.createService.createItemForVipLevelUp(player,
						currVipRank);
			}

			soClient.puchCMDtoClient(player.getName(), CommonUtil
					.messageFormat(CommonMsg.VIP_LEVEL_EXP_CMD,
							player.getIsVip(), String.valueOf(player.getVipExp())));
			
		}

		return addedExp;

	}
	
	/**
	 * 每场比赛后 更新战队资源
	 * @param teamId
	 * @param resNum
	 * @throws Exception
	 */
	public void updateTeamRobResAfterGame(int defTeamId,int attTeamId,float defRes,float attRes)throws Exception{
		updateTeamRobRes(defTeamId, -defRes);
		updateTeamRobRes(attTeamId, attRes);
	}
	//目前只在 过关结算用 会记统计表
	public void updateTeamRobRes(int teamId,float res)throws Exception{
		Team team=getService.getSimpleTeamById(teamId);
		if(team!=null){
			HashMap<String, Integer> teamResHashMap=team.getLatestTeamRes();
			int finalRes=Math.round(teamResHashMap.get(Team.ORG_RES)+res) >  Constants.TEAMSPACELEVELCONSTANTS.
					getTeamSpaceLevelConstants(team.getTeamSpaceLevel()).gettMaxOrgRes() 
					?  Constants.TEAMSPACELEVELCONSTANTS.
							getTeamSpaceLevelConstants(team.getTeamSpaceLevel()).gettMaxOrgRes()  : Math.round(teamResHashMap.get(Team.ORG_RES)+res);
			
			if(finalRes<0){
				finalRes=0;
			}
			team.setUnusableResource(finalRes);
			updateTeamInfo(team);
			
			final BattleFieldRobDaily bfrd=new BattleFieldRobDaily();
			if(res>0){
				bfrd.setRobAmount(Math.round(res)) ;
			}else{
				bfrd.setBeRobAmount(Math.round(-res));
			}
			bfrd.setTeamId(teamId);
			bfrd.setRobDate(new Date());
			
			final Runnable addBfrdLog = new Runnable() {
				@Override
				public void run() {
					try {
						createService.createBattleFieldRobDaily(bfrd);
					} catch (Exception e) {
						ServiceLocator.zyzdzStageClearLog.warn("error happend during add BattleFieldRobDaily log after zyzdz stage clear, exception is " , e);
					}
				}
			};
			ServiceLocator.asynTakService.submit(addBfrdLog);
			
			ServiceLocator.zyzdzStageClearLog.info(String.format("%s\t%s\t%s\t%s", 
					CommonUtil.simpleDateFormat.format(new Date()),team.getName(),teamId,res));
		}
	}
	
	//抢夺模式 更新战队可用资源
	public void updateTeamRes(int teamId,float res)throws Exception{
		Team team=getService.getSimpleTeamById(teamId);
		if(team!=null){
			HashMap<String, Integer> teamResHashMap=team.getLatestTeamRes();
			int finalRes=Math.round(teamResHashMap.get(Team.RES)+res) >  Constants.TEAMSPACELEVELCONSTANTS.
					getTeamSpaceLevelConstants(team.getTeamSpaceLevel()).gettMaxRes() 
					?  Constants.TEAMSPACELEVELCONSTANTS.
							getTeamSpaceLevelConstants(team.getTeamSpaceLevel()).gettMaxRes()  : Math.round(teamResHashMap.get(Team.RES)+res);
			
			team.setUsableResource(finalRes);
			updateTeamInfo(team);
		}
	}
	/**
	 * 更新战队地图
	 * @param teamLevelInfo
	 */
	public void updateTeamLevelInfo(TeamLevelInfo teamLevelInfo)throws Exception{
		teamLevelDao.updateTeamLevelInfoInCache(teamLevelInfo);
	//	mcc.delete(CacheUtil.oTeamLevelByTL(teamLevelInfo.getTeamId(),teamLevelInfo.getRefLevelId()));
	}
	public void updateTeamItem(int teamId,TeamItem item) throws Exception{
		teamItemDao.updateTeamItem(item);
	}
	
	public void updateBuyItemRecord(BuyItemRecord buyItemRecord)throws Exception{
		buyItemRecordDao.updateBuyItemRecord(buyItemRecord);
	}
}
