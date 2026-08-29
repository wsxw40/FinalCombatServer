package com.pearl.fcw.lobby.servlet.server.game;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.pearl.fcw.core.service.Servletable;
import com.pearl.o2o.servlet.server.BaseServerServlet;
import com.pearl.o2o.utils.BinaryReader;
import com.pearl.o2o.utils.Constants;

/**
 * 过关结算
 */
@Service("fcw_s_stage_clear")
public class SStageClear extends BaseServerServlet implements Servletable {

	private static final long serialVersionUID = 4487709443294408047L;
	private Logger logger = LoggerFactory.getLogger(getClass());
    @Resource
	private SStageClear fcw_s_stage_clear;

    @Override
    public String getLockedKey(BinaryReader reader) {
        try {
            reader.readInt();
            String userName = reader.readString().trim();
            return userName;
        } catch (Exception e) {
            logger.error("getLockedKey has error ", e);
        }
        return Servletable.super.getLockedKey(reader);
    }

    @Override
    protected byte[] innerService(BinaryReader in) throws IOException, Exception {
        try {
			return fcw_s_stage_clear.server(in);
		} catch (Exception e) {
			logger.error("fcw_s_stage_clear has error : ", e);
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            return out.toByteArray();
        }
    }

    @Override
    public byte[] server(BinaryReader reader) throws Exception {

		//		GeneralStageClearInfo stageClearInfo = new GeneralStageClearInfo();
		//		stageClearInfo.setClear(true);
		//
		//		//FCW
		//		//zlm2015-10-9-匹配-开始
		//		//			int isMatch =  r.readInt();
		//		//			stageClearInfo.setIsMatch(isMatch);
		//		//zlm2015-10-9-匹配-结束
		//		int roomId = reader.readInt();
		//		stageClearInfo.setRid(roomId);
		//
		//		int serverId = reader.readInt();
		//		stageClearInfo.setServerId(serverId);
		//
		//		int channelId = reader.readInt();
		//		stageClearInfo.setChannelId(channelId);
		//		int hostId = reader.readInt();
		//		stageClearInfo.setHostId(hostId);
		//
		//		int type = reader.readByte();
		//		stageClearInfo.setType(type);
		//		int gameStart = reader.readInt();//second
		//		stageClearInfo.setGameStart(new Date(gameStart * 1000l));
		//		int gameEnd = reader.readInt();
		//		stageClearInfo.setGameEnd(new Date(gameEnd * 1000l));
		//		int levelId = reader.readInt();
		//		stageClearInfo.setLevelId(levelId);
		//		int isTeam = reader.readByte();
		//		stageClearInfo.setIsTeam(isTeam);
		//		int teamaId = 0;
		//		int teambId = 0;
		//		if (isTeam != 0) {
		//			teamaId = reader.readInt();
		//			teambId = reader.readInt();
		//			stageClearInfo.setTeamaId(teamaId);
		//			stageClearInfo.setTeambId(teambId);
		//		}
		//		int winner = reader.readByte();
		//		stageClearInfo.setWinner(winner);
		//		//得分计算
		//		//资源争夺战
		//		if (type == Constants.GAMETYPE.TEAMZYZDZ.getValue()) {
		//			String bodygameKey = reader.readString();
		//			if (!bodygameKey.equals("")) {//  非  挑战模式
		//				String gameKey = Constants.TeamSpaceConstants.BATTLEFIELD_START_PREFIX + bodygameKey;
		//				Integer totalRes = mcc.get(gameKey, Constants.CACHE_TIMEOUT);
		//				if (totalRes == null) {
		//					totalRes = 0;
		//				}
		//				mcc.delete(gameKey);
		//				stageClearInfo.setTotalRes(Float.valueOf(totalRes));
		//			} else {
		//				stageClearInfo.setTotalRes(-1);
		//			}
		//
		//			//战队资源战模式基地还剩血量
		//			float damagePercent = reader.readFloat();
		//			stageClearInfo.setDamagePercent(1 - damagePercent);
		//
		//		} else {	//其他模式
		//			int terroristScore = reader.readInt();//恐怖分子胜利
		//			stageClearInfo.setTerroristScore(terroristScore);
		//			int policeScore = reader.readInt();//警察胜利
		//			stageClearInfo.setPoliceScore(policeScore);
		//		}
		//
		//		int mvpId = reader.readInt();
		//		stageClearInfo.setMvpId(mvpId);
		//		int firstKill = reader.readInt();
		//		int firstDead = reader.readInt();
		//		stageClearInfo.setFirstKill(firstKill);
		//		stageClearInfo.setFirstDead(firstDead);
		//		@SuppressWarnings("unused")
		//		int itemType = reader.readByte();
		//		//player list
		//		int playerArrayLength = reader.readInt();
		//
		//		List<StageClearPlayerInfo> playerInfoListTeam0 = new ArrayList<StageClearPlayerInfo>();//team0
		//		List<StageClearPlayerInfo> playerInfoListTeam1 = new ArrayList<StageClearPlayerInfo>();//team1
		//		List<StageClearPlayerInfo> playerInfoListTeam2 = new ArrayList<StageClearPlayerInfo>();//team1
		//
		//		MvpScore mvpScore = new MvpScore();
		//		;
		//		stageClearInfo.setStageClearPlayerInfoTeam0(playerInfoListTeam0);
		//		stageClearInfo.setStageClearPlayerInfoTeam1(playerInfoListTeam1);
		//		stageClearInfo.setStageClearPlayerInfoTeam2(playerInfoListTeam2);
		//		timeLog.append(" action all player " + (new Date().getTime() - timec) + "ms\t");
		//		for (int i = 0; i < playerArrayLength; i++) {
		//			StageClearPlayerInfo playerInfo = new StageClearPlayerInfo();
		//			int id = reader.readInt();
		//			playerIds.add(id);
		//			playerInfo.setId(id);
		//			String characterName = reader.readString();
		//			playerInfo.setName(characterName);
		//
		//			byte side = reader.readByte();
		//			playerInfo.setSide(side);
		//			switch (side) {
		//			case 0: //红方
		//				playerInfoListTeam0.add(playerInfo);
		//				break;
		//			case 1: //蓝方
		//				playerInfoListTeam1.add(playerInfo);
		//				break;
		//			case 2:
		//				playerInfoListTeam2.add(playerInfo);
		//				break;
		//			default:
		//				break;
		//			}
		//			if (side == winner) {	//赢家
		//				playerInfo.setIsWiner(1);
		//			} else {
		//				playerInfo.setIsWiner(0);
		//			}
		//
		//			int onlineMinutes = reader.readInt();
		//			playerInfo.setOnlineMinutes(onlineMinutes);
		//
		//			int offlineMinutes = reader.readInt();
		//			playerInfo.setOfflineMinutes(offlineMinutes);
		//
		//			byte decrNum = reader.readByte();
		//			nosqlService.setPlayerRemainVoterNum(id, decrNum);
		//			//剩余复活币数
		//			int remainReliveCoinNum = reader.readInt();
		//
		//			/*复活币扣除计算***********/
		//			List<PlayerItem> reliveCoinList = getService.getPlayerItemByItemIid(id, Constants.DEFAULT_ITEM_TYPE, Constants.SPECIAL_ITEM_IIDS.RELIVE_COIN.getValue());
		//			int totalReliveCoinNum = 0;
		//
		//			for (PlayerItem pi : reliveCoinList) {
		//				if (pi.getQuantity() != 0) {
		//					totalReliveCoinNum += pi.getQuantity();
		//				}
		//			}
		//			int costReliveCoinNum = totalReliveCoinNum - remainReliveCoinNum;
		//			int costReliveCoinNumLog = costReliveCoinNum;
		//			//not use
		//			playerInfo.setCostReliveCoin(costReliveCoinNum);
		//			if (costReliveCoinNum > 0) {
		//				if (remainReliveCoinNum == 0) {	//扣除所有复活币
		//					for (PlayerItem pi : reliveCoinList) {
		//						int quantity = pi.getQuantity();
		//						if (quantity == 0)
		//							continue;
		//						createService.updateItemQuantity(pi, quantity);
		//						deleteService.deletePlayerItemInMemcached(id, getService.getSysItemByItemId(pi.getItemId()));
		//					}
		//				} else {						//扣除一部分
		//					for (PlayerItem pi : reliveCoinList) {
		//						int quantity = pi.getQuantity();
		//						if (quantity == 0)
		//							continue;
		//						if (quantity < costReliveCoinNum) {
		//							createService.updateItemQuantity(pi, quantity);
		//							costReliveCoinNum -= quantity;
		//							deleteService.deletePlayerItemInMemcached(id, getService.getSysItemByItemId(pi.getItemId()));
		//						} else {
		//							createService.updateItemQuantity(pi, costReliveCoinNum);
		//							costReliveCoinNum = 0;
		//							deleteService.deletePlayerItemInMemcached(id, getService.getSysItemByItemId(pi.getItemId()));
		//							break;
		//						}
		//					}
		//				}
		//				if (ConfigurationUtil.SWITCH_XUNLEI_LOG.getIsOn()) {
		//					Player player = getService.getPlayerById(id);
		//					nosqlService.addXunleiLog("18.1" + Constants.XUNLEI_LOG_DELIMITER + player.getUserName() + Constants.XUNLEI_LOG_DELIMITER + player.getName() + Constants.XUNLEI_LOG_DELIMITER + 0
		//							+ Constants.XUNLEI_LOG_DELIMITER + costReliveCoinNumLog + Constants.XUNLEI_LOG_DELIMITER + (totalReliveCoinNum - costReliveCoinNumLog) + Constants.XUNLEI_LOG_DELIMITER + 6
		//							+ Constants.XUNLEI_LOG_DELIMITER + CommonUtil.simpleDateFormat.format(new Date()));
		//				}
		//			}
		//			/*复活币扣除计算 end***********/
		//			timeLog.append(" relive coin over " + (new Date().getTime() - timec) + "ms\t");
		//			logger.debug("stageClear :id " + playerInfo.getId() + " online" + onlineMinutes + " offline " + offlineMinutes + " start time " + stageClearInfo.getGameStart() + " end time "
		//					+ stageClearInfo.getGameEnd());
		//
		//			/* 个人分数战况等基本信息录入 **/
		//			int start = reader.readInt();
		//			int end = reader.readInt();
		//			playerInfo.setStart(new Date(start * 1000l));
		//			playerInfo.setEnd(new Date(end * 1000l));
		//			int score = reader.readInt();
		//			playerInfo.setScore(score);
		//			short kill = reader.readShort();
		//			playerInfo.setKill(kill);
		//			short dead = reader.readShort();
		//			playerInfo.setDead(dead);
		//
		//			short controlNum = reader.readShort();
		//			playerInfo.setControlNum(controlNum);
		//			short revengeNum = reader.readShort();
		//			playerInfo.setRevengeNum(revengeNum);
		//			short assistNum = reader.readShort();
		//			playerInfo.setAssistNum(assistNum);
		//			short knifeKill = reader.readShort();
		//			playerInfo.setKnifeKill(knifeKill);
		//
		//			int maxHeadshotNum = reader.readInt();
		//			playerInfo.setMaxHeadshotNum(maxHeadshotNum);
		//			int maxHeadshotCharacter = reader.readInt();
		//			playerInfo.setMaxHeadshotCharacter(maxHeadshotCharacter);
		//			if (mvpId == id && maxHeadshotNum != 0 && maxHeadshotNum * 160 > mvpScore.getScore()) {
		//				mvpScore.setScore(maxHeadshotNum * 160);
		//				mvpScore.setCharacterId(maxHeadshotCharacter);
		//			}
		//
		//			int maxKillNum = reader.readInt();
		//			playerInfo.setMaxKillNum(maxKillNum);
		//			int maxKillNumCharacter = reader.readInt();
		//			playerInfo.setMaxKillNumCharacter(maxKillNumCharacter);
		//			if (mvpId == id && maxKillNum != 0) {
		//				int score2 = 135 * maxKillNum;
		//				for (int m = 1; m < maxKillNum; m++) {
		//					score2 += 5;
		//				}
		//				if (score2 > mvpScore.getScore()) {
		//					mvpScore.setScore(score2);
		//					mvpScore.setCharacterId(maxKillNumCharacter);
		//				}
		//			}
		//
		//			int maxHealthNum = reader.readInt();
		//			playerInfo.setMaxHealthNum(maxHealthNum);
		//			int maxHealthNumCharacter = reader.readInt();
		//			playerInfo.setMaxHealthNumCharacter(maxHealthNumCharacter);
		//			if (mvpId == id && maxHealthNum != 0 && maxHealthNum * 15 > mvpScore.getScore()) {
		//				mvpScore.setScore(maxHealthNum * 1);
		//				mvpScore.setCharacterId(maxHealthNumCharacter);
		//			}
		//
		//			int maxDamageNum = reader.readInt();
		//			playerInfo.setMaxDamageNum(maxDamageNum);
		//			int maxDamageNumCharacter = reader.readInt();
		//			playerInfo.setMaxDamageNumCharacter(maxDamageNumCharacter);
		//			int maxLiveTime = reader.readInt();
		//			playerInfo.setMaxLiveTime(maxLiveTime);
		//			int maxLiveTimeCharacter = reader.readInt();
		//			playerInfo.setMaxLiveTimeCharacter(maxLiveTimeCharacter);
		//			if (mvpId == id && mvpScore.getScore() == 0 && mvpScore.getCharacterId() == 0) {
		//				mvpScore.setScore(0);
		//				mvpScore.setCharacterId(maxLiveTimeCharacter);
		//			}
		//
		//			short bulletNum = reader.readShort();
		//			short bottleHpNum = reader.readShort();
		//
		//			playerInfo.setBottleHpNum(bottleHpNum);
		//			playerInfo.setBulletNum(bulletNum);
		//			int characterDatasize = reader.readInt();
		//
		//			/* 个人分数战况等基本信息录入 end**/
		//			timeLog.append(" basic info " + (new Date().getTime() - timec) + "ms\t");
		//			//单角色对局信息 boos
		//			for (int j = 0; j < characterDatasize; j++) {
		//				int characterId = reader.readInt();
		//				int characterKill = reader.readShort();
		//				if (characterId == Constants.DEFAULT_BOSS_CHARACTER_ID) {
		//					updateService.updatePlayerActivity(Constants.ACTION_ACTIVITY.BOSS_KILL_ACTIVITY.getValue(), playerInfo.getId(), null, characterKill, 0, null, null);
		//				}
		//				int characterDead = reader.readShort();
		//				int characterControlNum = reader.readShort();
		//				int characterRevengeNum = reader.readShort();
		//				int characterAssistNum = reader.readShort();
		//				int characterGrenadeKill = reader.readShort();
		//				int characterKnifeKill = reader.readShort();
		//				int characterUsed = reader.readShort();
		//				int characterHeadshot = reader.readShort();
		//				int characterMaxDamage = reader.readShort();
		//				int characterBoostKill = reader.readShort();
		//				int characterSustainKill = reader.readShort();
		//				int characterHealthNum = reader.readInt();
		//				if (characterHealthNum > 0 && characterId == 6) {
		//					playerInfo.setHealth(characterHealthNum);
		//				}
		//				logger.debug("playerId=" + id + " characterId=" + characterId + " characterKill=" + characterKill + " characterUsed=" + characterUsed);
		//				playerInfo.addSingleCharacterInfo(characterId, characterKill, characterDead, characterControlNum, characterRevengeNum, characterAssistNum, characterKnifeKill, characterUsed,
		//						characterGrenadeKill, characterHeadshot, characterMaxDamage, characterBoostKill, characterSustainKill, characterHealthNum);
		//				// 角色使用次数
		//				// 角色id=characterId, 使用次数=characterUsed, 地图id= levelId
		//				// 记日志
		//				if (characterUsed > 0) { // 只记录使用次数大于0的情况
		//					final String amountMessage = String.format("%s\t%s\t%s", characterId, levelId, characterUsed);
		//					// 职业 KD 值
		//					final String kDMessage = String.format("%s\t%s\t%s\t%s", characterId, levelId, characterKill, characterDead);
		//					// 记到analyser server
		//
		//					transferDataToDc.addLog("bjStageClear_ChAmount", amountMessage);
		//					transferDataToDc.addLog("bjStageClear_ChKD", kDMessage);
		//
		//				}
		//			}
		//			//单角色对局信息 end
		//			timeLog.append(" character info " + (new Date().getTime() - timec) + "ms\t");
		//
		//			// 记录模式和地图使用情况
		//			String ltAmount = String.format("%s\t%s", levelId, type);
		//			transferDataToDc.addLog("bjStageClear_LTAmount", ltAmount);
		//
		//			int killboss2 = 0;
		//			int killSize = reader.readInt();
		//			for (int k = 0; k < killSize; k++) {
		//				int characterId = reader.readInt();
		//				if (characterId == Constants.DEFAULT_BOSS_CHARACTER_ID) {
		//					int num = reader.readInt();
		//					updateService.updatePlayerActivity(Constants.ACTION_ACTIVITY.KILL_BOSS_ACTIVITY.getValue(), playerInfo.getId(), null, num, 0, null, null);
		//				} else if (characterId == BiochemicalConstants.ordinaryCId || characterId == BiochemicalConstants.especialCId || characterId == Constants.FIRST_BIOCHEMICAL_CHARACTER_ID
		//						|| characterId == Constants.NORMAL_BIOCHEMICAL_CHARACTER_ID || characterId == Constants.FINAL_BIOCHEMICAL_CHARACTER_ID) {
		//					int num = reader.readInt();
		//					updateService.updatePlayerActivity(Constants.ACTION_ACTIVITY.KILL_BIOCHEMICAL.getValue(), playerInfo.getId(), null, num, 0, null, null);
		//				} else if (characterId == Constants.DEFAULT_BOSS2_CHARACTER_ID || characterId == Constants.DEFAULT_BOSS3_CHARACTER_ID || characterId == Constants.DEFAULT_BOSS4_CHARACTER_ID
		//						|| characterId == Constants.DEFAULT_BOSS5_CHARACTER_ID) {
		//					int num = reader.readInt();
		//					killboss2 += num;
		//				} else if (characterId > 10) {//boss2
		//
		//					int num = reader.readInt();
		//					logger.warn("stage clear characterId out of range==" + characterId + " and num=" + num);
		//					//					System.out.println("num2="+num);
		//					//					logger.warn("num="+num);
		//					continue;
		//				} else {
		//					int num = reader.readInt();
		//					if (characterId > Constants.MAX_CHARACTER_SIZE) {
		//						logger.warn("stage clear characterId out of range==" + characterId);
		//						logger.warn("num=" + num);
		//					}
		//					playerInfo.getKillCharacter()[characterId] = num;
		//				}
		//			}
		//
		//			if (type == GAMETYPE.BOSS2.getValue()) {
		//				updateService.updatePlayerActivity(Constants.ACTION_ACTIVITY.KILL_BOSS2_ACTIVITY.getValue(), playerInfo.getId(), null, killboss2, 0, null, null);
		//			}
		//			timeLog.append(" boos2 info " + (new Date().getTime() - timec) + "ms \t");
		//			//boos 掉落
		//			int size = reader.readInt();
		//			List<OnlineAward> boss2Awards = new ArrayList<OnlineAward>();
		//			for (int j = 0; j < size; j++) {
		//				int sysItemId = reader.readInt();//物品ID
		//				int num = reader.readInt();//物品数量
		//				SysItem si = getService.getSysItemByItemId(sysItemId);
		//				if (si != null) {
		//					createService.awardToPlayer(getService.getPlayerById(id), si, new Payment(num, 1), null, Constants.BOOLEAN_YES);
		//					//					createService.sendItem(si, getService.getPlayerById(id),new Payment(num,1), "N", "Y", "N");
		//					ServiceLocator.stageClearLog.info("Boss2/Award:\t" + id + "\t" + si.getDisplayNameCN() + "\t" + num + "\t" + 1);
		//					OnlineAward oa = new OnlineAward();
		//					oa.setSysItem(si);
		//					oa.setUnit(num);
		//					oa.setUnitType(1);
		//					boss2Awards.add(oa);
		//				} else {
		//					logger.warn("Boss2/SysItemNull:\t" + id + "\t" + sysItemId + "\t" + num);
		//				}
		//			}
		//			playerInfo.setBoss2Awards(boss2Awards);
		//			int level = reader.readInt();
		//			playerInfo.setPassLevel(level);
		//			timeLog.append(" send boos2 goods to player " + (new Date().getTime() - timec) + "ms\t");
		//			//资源争夺战物品
		//			if (type == GAMETYPE.TEAMZYZDZ.getValue()) {
		//				short zyzdzItemSize = reader.readShort();
		//				for (int count = 0; count < zyzdzItemSize; count++) {
		//					int sysItemId = reader.readInt();
		//					int costItemNum = reader.readShort();
		//					PlayerItem playerItem = new PlayerItem();
		//					playerItem.setItemId(sysItemId);
		//					playerItem.setSysItem(getService.getSysItemByItemId(sysItemId));
		//					playerItem.setQuantity(costItemNum);
		//					playerInfo.getZyzdzCostItem().add(playerItem);
		//					List<PlayerItem> playerItems = getService.getPlayerItemListBySysItemId(id, Constants.DEFAULT_ZYZDZ_TYPE, sysItemId);
		//					if (costItemNum > 0) {
		//						for (PlayerItem pi : playerItems) {
		//							int quantity = pi.getQuantityForZYZDZPersonal();
		//							if (quantity == 0)
		//								continue;
		//							if (quantity < costItemNum) {
		//								createService.updateItemQuantity(pi, quantity);
		//								costItemNum -= quantity;
		//								deleteService.deletePlayerItemInMemcached(id, getService.getSysItemByItemId(pi.getItemId()));
		//							} else {
		//								createService.updateItemQuantity(pi, costItemNum);
		//								costItemNum = 0;
		//								deleteService.deletePlayerItemInMemcached(id, getService.getSysItemByItemId(pi.getItemId()));
		//								break;
		//							}
		//						}
		//					}
		//				}
		//			}
		//
		//			timeLog.append(" TEAMZYZDZ goods deduct " + (new Date().getTime() - timec) + "ms\t");
		//			if (type == GAMETYPE.AGRAVITY2.getValue()) {
		//
		//				//在跳跳乐模式中获得的物品
		//				int itemsNum[] = { reader.readInt(), //0 cb、
		//						reader.readInt(),	//1 勋章、
		//						reader.readInt(),	//2 强化部件、
		//						reader.readInt(),	//3 箱子A GM 地图顶点箱子、
		//						reader.readInt(),	//4 箱子B GM 地图中间箱子、
		//						reader.readInt(),	//5 箱子C 玩家 地图顶点箱子、
		//						reader.readInt(),	//6 箱子D 玩家 地图中间箱子
		//				};
		//
		//				String[] mccKeys = { CacheUtil.sPlayerItemsTopLimitCGold(id),		//
		//						CacheUtil.sPlayerItemsTopLimitMerit(id),		//
		//						CacheUtil.sPlayerItemsTopLimitStrength(id),		//
		//						CacheUtil.sPlayerItemsTopLimitBoxA(id),			//
		//						CacheUtil.sPlayerItemsTopLimitBoxB(id),			//
		//						CacheUtil.sPlayerItemsTopLimitBoxC(id),			//
		//						CacheUtil.sPlayerItemsTopLimitBoxD(id)			//
		//				};
		//
		//				int[] topLimit = { 67, 15, 12, 1, 1, 1, 1 };
		//				for (int itemIndex = 0; itemIndex < itemsNum.length; itemIndex++) {
		//					if (mcc.get(mccKeys[itemIndex]) == null) {
		//						Integer itemNum = 0;
		//						itemsNum[itemIndex] = (topLimit[itemIndex] - itemNum) >= itemsNum[itemIndex] ? itemsNum[itemIndex] : (topLimit[itemIndex]);
		//						mcc.set(mccKeys[itemIndex], Constants.CACHE_TIMEOUT_HALF_DAY, itemsNum[itemIndex]);
		//					} else {
		//						Integer itemNum = mcc.get(mccKeys[itemIndex]);
		//						if (itemNum < topLimit[itemIndex]) {
		//							mcc.set(mccKeys[itemIndex], Constants.CACHE_TIMEOUT_HALF_DAY, itemNum + itemsNum[itemIndex]);
		//							itemsNum[itemIndex] = (topLimit[itemIndex] - itemNum) >= itemsNum[itemIndex] ? itemsNum[itemIndex] : (topLimit[itemIndex] - itemNum);
		//						} else {
		//							itemsNum[itemIndex] = 0;
		//						}
		//					}
		//				}
		//
		//				int medalId = GrowthMissionConstants.MEDAL_ID;			//勋章
		//				int upgardmodId = GrowthMissionConstants.UPGARDMOD_ID;		//部件
		//
		//				playerInfo.setGp(playerInfo.getGp() + (itemsNum[0] * 3000));
		//
		//				HashMap<Integer, Integer> itemIdNum = new HashMap<Integer, Integer>();
		//				for (int k = 3; k < 7; k++) {				//派送箱子
		//					if (itemsNum[k] > 0) {
		//						itemIdNum.put(Constants.AGRAVITYBOXITEM[k - 3], 1);
		//					}
		//				}
		//
		//				if (itemsNum[1] > 0) {
		//					itemIdNum.put(medalId, itemsNum[1]);
		//				}
		//
		//				if (itemsNum[2] > 0) {
		//					itemIdNum.put(upgardmodId, itemsNum[2]);
		//				}
		//				SysItem si = null;
		//				int num = 0;
		//				for (Integer itemId : itemIdNum.keySet()) {
		//					num = itemIdNum.get(itemId);
		//					if (num <= 0)
		//						continue;
		//					si = getService.getSysItemByItemId(itemId);
		//					createService.awardToPlayer(getService.getPlayerById(id), si, new Payment(num, 1), null, Constants.BOOLEAN_YES);
		//					ServiceLocator.stageClearLog.info("AGRAVITY/Award:\t" + id + "\t" + si.getDisplayNameCN() + "\t" + num + "\t" + 1);
		//				}
		//			}
		//
		//			timeLog.append(" AGRAVITY SEND GIFT " + (new Date().getTime() - timec) + "ms/t");
		//
		//			//该结算为战队战
		//			if (isTeam > 0) {
		//				updateService.updatePlayerActivity(Constants.ACTION_ACTIVITY.TEAM_COMBAT_FINISH.getValue(), playerInfo.getId(), null, 0, 0, null, null);
		//			}
		//
		//		}
		//		if (type == GAMETYPE.HITBOSS.getValue() || type == GAMETYPE.BIOCHEMICAL.getValue() || type == GAMETYPE.BOSS2.getValue() || type == GAMETYPE.BIOCHEMICAL2.getValue()
		//				|| type == Constants.GAMETYPE.BIOCHEMICAL2_MATCH.getValue()) {
		//			playerInfoListTeam0.addAll(playerInfoListTeam1);
		//			playerInfoListTeam1 = new ArrayList<StageClearPlayerInfo>();
		//			stageClearInfo.setStageClearPlayerInfoTeam1(playerInfoListTeam1);
		//		} else {
		//			Collections.sort(playerInfoListTeam1);
		//		}
		//		Collections.sort(playerInfoListTeam0);
		//		timeLog.append(" HITBOSS  BIOCHEMICAL BOSS2 BIOCHEMICAL2 deduct" + (new Date().getTime() - timec) + "ms\t");
		//		PlayerItem mvpWeapon = null;
		//		Player mvpPlayer = null;
		//		if (mvpId != 0) {
		//			mvpPlayer = getService.getSimplePlayerById(mvpId);
		//			stageClearInfo.setMvpCharacterId(mvpScore.getCharacterId());
		//			List<PlayerItem> weaponList = getService.getWeaponPackList(mvpId, 1, mvpScore.characterId);
		//			if (type != Constants.GAMETYPE.KNIFE.getValue()) {
		//				mvpWeapon = weaponList.get(0);
		//				SysItem si = getService.getSysItemByItemId(mvpWeapon.getItemId());
		//				mvpWeapon.setSysItem(si);
		//			} else {
		//				mvpWeapon = weaponList.get(2);
		//				SysItem si = getService.getSysItemByItemId(mvpWeapon.getItemId());
		//				mvpWeapon.setSysItem(si);
		//			}
		//		}
		//		timeTrack.debug("StageClear;decode stageClearInfo;");
		//
		//		if (type == Constants.GAMETYPE.TEAMZYZDZ.getValue()) {
		//			updateService.updatePlayerStageClearZYZDZ(stageClearInfo);	//资源争夺战过关结算
		//
		//		} else {
		//			updateService.updatePlayerStageClear(stageClearInfo);		//其余模式过关结算
		//
		//		}
		//		timeLog.append(" !model and missions update " + (new Date().getTime() - timec) + "ms\t");
		//
		//		//10为3牌5箱子的结算方法，0为正常9牌
		//		LevelInfo levelInfo = getService.getLevelInfo(stageClearInfo.getLevelId());
		//		int level_close_type = 0;
		//		if (levelInfo != null)
		//			level_close_type = levelInfo.getIsGm();//地图信息
		//		String result = Converter.stageClear(stageClearInfo, mvpPlayer, mvpWeapon, level_close_type);
		//		timeTrack.debug("StageClear;updatePlayerStageClear;");
		//
		//		String key = CacheUtil.sStageClear(stageClearInfo.getRid(), stageClearInfo.getChannelId(), stageClearInfo.getServerId());
		//
		//		logger.error(gameStart + "--" + gameEnd + "--" + key + "--" + result.length());
		//
		//		//			Thread.sleep(30000);	//模拟结算超时
		//
		//		mcc.set(key, STAGECLEAR_EXPR, result, Constants.CACHE_TIMEOUT);
		//		timeTrack.debug("StageClear;Converter stageClear;");
		//		timeLog.append(" set result key" + (new Date().getTime() - timec) + "ms\t");
		//
		//		String sumHurt = reader.readString(); //资源争夺战，所有阵营玩家单句总伤害 20140710 OuYangGuang
		//		if (ConfigurationUtil.SWITCH_CMPT_HURT_SUM_LOG.getIsOn() && null != sumHurt && sumHurt.length() > 0) {
		//			//格式: 名称|PLAYER_ID|总伤害
		//			ServiceLocator.cMPTHurtLogConfig.info("========Players Sum Hurt(" + DateUtil.getDf().format(new Date()) + ")========\r\n\r\n" + sumHurt);
		//		}
		//		timeLog.append(" write SWITCH_CMPT_HURT_SUM_LOG" + (new Date().getTime() - timec) + "ms\t");
		//
		//		timeLog.append(" over" + (new Date().getTime() - timec) + "ms\t");
		//
		//		logger.info(timeLog.toString());

		return Constants.EMPTY_BYTE_ARRAY;

    }
}
