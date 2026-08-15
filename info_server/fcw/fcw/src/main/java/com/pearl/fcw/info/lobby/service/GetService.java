package com.pearl.fcw.info.lobby.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import redis.clients.jedis.Tuple;

import com.pearl.fcw.info.core.persistence.cache.RedisClient;
import com.pearl.fcw.info.core.persistence.utils.CommonMsg;
import com.pearl.fcw.info.core.persistence.utils.NosqlKeyUtil;
import com.pearl.fcw.info.lobby.exception.BaseException;
import com.pearl.fcw.info.lobby.pojo.PlayerCharacter;
import com.pearl.fcw.info.lobby.pojo.Message;
import com.pearl.fcw.info.lobby.pojo.Player;
import com.pearl.fcw.info.lobby.pojo.PlayerItem;
import com.pearl.fcw.info.lobby.pojo.SysActivity;
import com.pearl.fcw.info.lobby.pojo.SysCharacter;
import com.pearl.fcw.info.lobby.pojo.SysItem;
import com.pearl.fcw.info.lobby.utils.CacheUtil;
import com.pearl.fcw.info.lobby.utils.CommonUtil;
import com.pearl.fcw.info.lobby.utils.Constants;
import com.pearl.fcw.info.lobby.utils.ExceptionMessage;
import com.pearl.fcw.info.lobby.utils.ServiceLocator;
import com.pearl.fcw.info.lobby.utils.StringUtil;

@Service
public class GetService extends BaseService {
	static Logger log = LoggerFactory.getLogger(GetService.class.getName());

	public SysItem getSysItemByItemId(int itemId) throws Exception {
		return null;//FIXME sysItemDao.get(itemId);
	}

	@SuppressWarnings("unchecked")
	public Map<String, String> getSysConfig() {
		String key = CacheUtil.oSysConfigMap();
		Map<String, String> result = new HashMap<String, String>();
		try {
			result = (Map<String, String>) loadValue(key,
					new CacheMissHandler() {
						@Override
						public Object loadFromDB(GetService service) {
							Map<String, String> list = new HashMap<String, String>();
							sysConfigDao.getAllList().forEach(config -> {
								list.put(config.getName(), config.getValue());
							});
							return list;
						}
					});
		} catch (Exception e) {
			log.error("happen in getSysConfig", e);
		}
		return result;
	}

	public Object loadValue(String key, CacheMissHandler handler)
			throws Exception {
		return loadValue(key, handler, Constants.CACHE_TIMEOUT_DAY);
	}

	public interface CacheMissHandler {
		Object loadFromDB(GetService service) throws Exception;
	}

	public Object loadValue(String key, CacheMissHandler handler, int expr)
			throws Exception {
		Object result = null;
		try {
			result = redisClient.get(key);
		} catch (Exception e) {
			log.warn("error happend during get key from memcache " + e);
		}
		if (result == null) {
			result = handler.loadFromDB(this);
			if (result == null) {
				return null;
			}
			try {
				redisClient.setex(key, Constants.CACHE_TIMEOUT, result);
			} catch (Exception e) {
				log.warn("Fail to put object into cache with key: " + key);
				e.printStackTrace();
			}
		}
		return result;
	}

	public List<String> getBannedWordsStrList() throws Exception {
		List<String> result = new ArrayList<String>();
		return result;
	}

	@SuppressWarnings("unchecked")
	public Map<Integer, SysActivity> getAvailableActivitiesMap()
			throws Exception {
		String key = CacheUtil.oCurrentActivityMap();
		Map<Integer, SysActivity> returnMap = (Map<Integer, SysActivity>) loadValue(
				key, new CacheMissHandler() {
					@Override
					public Object loadFromDB(GetService service)
							throws Exception {
						List<SysActivity> activityList = null;// FIXME　sysActivityDao.getAllList();
						Map<Integer, SysActivity> returnList = new HashMap<Integer, SysActivity>();
						for (SysActivity as : activityList) {
							if (as.getTheSwitch().equals(Constants.BOOLEAN_YES)
									&& Constants.BOOLEAN_NO.equals(as
											.getIsDeleted())
									&& (as.getAction() != Constants.ACTION_ACTIVITY.TOP_PLAYER_ACTIVITY
											.getValue())
									&& as.getEndTime().after(new Date())) {
								as.initActivity();
								as.initNeedAward();
								as.initTimeStr();
								if (as.getWithAward() == 1) {
									SysItem sysItem = getSysItemByItemId(StringUtil
											.toInt(as.getItems()));
									as.setSysItem(sysItem);
								}
								returnList.put(as.getId(), as);
							}
						}
						return returnList;
					}
				}, Constants.CACHE_TIMEOUT_HALF_DAY);
		return returnMap;
	}

	public List<SysActivity> getAvailableActivities() throws Exception {
		List<SysActivity> returnList = new ArrayList<SysActivity>(
				getAvailableActivitiesMap().values());
		if (returnList.size() > 0) {
			Collections.sort(returnList, new Comparator<SysActivity>() {
				@Override
				public int compare(SysActivity o1, SysActivity o2) {
					return ((Integer) o1.getAction()).compareTo(((Integer) o2
							.getAction()));
				}

			});
		}
		return returnList;
	}

	/**
	 * method to get email list by player id
	 * 
	 * @param userId
	 * @param playerId
	 * @return List<Message>
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Message> getMessageListFromInbox(final Integer userId,
			final int playerId) throws Exception {
		String objKey = CacheUtil.oPlayerMessage(playerId);
		List<Message> list = (List<Message>) loadValue(objKey,
				new CacheMissHandler() {
					@Override
					public Object loadFromDB(GetService service) {
						// List<Message> list =
						// messageDao.getMessageListFromInBox(userId, playerId);
						List<Message> list = null; // FIXME
						List<Message> messageList = new ArrayList<Message>();
						int i = 1;
						for (Message message : list) {
							message.init();
							messageList.add(message);
							i++;
							if (i > 50) {
								break;
							}
						}
						return messageList;
					}
				});
		Collections.sort(list, new Comparator<Message>() {

			@Override
			public int compare(Message o1, Message o2) {
				// TODO Auto-generated method stub
				Integer o11 = "Y".equals(o1.getOpen()) ? 1 : 0;
				Integer o22 = "Y".equals(o2.getOpen()) ? 1 : 0;
				return o11.compareTo(o22);
			}

		});
		return list;
	}

	public int getNewMessageNum(final int playerId) throws Exception {
		int returnValue = 0;
		List<Message> list = getMessageListFromInbox(playerId, playerId);
		for (Message message : list) {
			if (Constants.BOOLEAN_NO.equals(message.getOpen())) {
				returnValue += 1;
			}
		}
		return returnValue;
	}

	public List<PlayerCharacter> getCharacterListById(Integer playerId)
			throws Exception {
		String objKey = CacheUtil.oCharacterList(playerId);
		List<PlayerCharacter> characterList = new ArrayList<PlayerCharacter>();
		characterList = redisClient.get(objKey);
		if (characterList == null) {
			characterList = new ArrayList<PlayerCharacter>();
			List<Integer> characterIds = new ArrayList<Integer>();
			// characterIds=playerPackDao.getCharacterIdFromPlayerPack(playerId);
			characterIds.add(1); // FIXME
			if (characterIds.size() != 0) {
				for (Integer id : characterIds) {
					// List<PlayerItem> costumeList=getCostumePackList(playerId,
					// Constants.NUM_ONE, id);
					// List<PlayerItem> weaponList=getWeaponPackList(playerId,
					// Constants.NUM_ONE, id);
					// SysCharacter
					// sysCharacter=sysCharacterDao.getSysCharacterById(id);
					List<PlayerItem> costumeList = null; // FIXME
					List<PlayerItem> weaponList = null; // FIXME
					SysCharacter sysCharacter = null; // FIXME

					PlayerCharacter character = new PlayerCharacter();
					character.setPlayerId(playerId);
					character.setSysCharacter(sysCharacter);
					character.setWeaponList(weaponList);
					character.setCostumeList(costumeList);
//					character.putOnCostume();	FIXME
//					character.initFightNum();	FIXME
					characterList.add(character);
				}
			}
			if (characterList.size() != 0) {
				redisClient.setex(objKey, Constants.CACHE_ITEM_TIMEOUT,
						characterList);
			}
		}
		return characterList;
	}

	/**
	 * 获得玩家最大战斗力职业
	 * 
	 * @param playerId
	 * @return
	 * @throws Exception
	 */
	public PlayerCharacter getMaxFightnumCharacterById(int playerId) throws Exception {
		PlayerCharacter character = new PlayerCharacter();
		List<PlayerCharacter> characterList = new ArrayList<PlayerCharacter>();
		characterList = getCharacterListById(playerId);
		int maxFightNum = 0;
		for (PlayerCharacter ch : characterList) {
			int fightNum = ch.getFightNum();
			if (fightNum > maxFightNum) {
				character = ch;
				maxFightNum = fightNum;
			}
		}

		return character;
	}

	public PlayerCharacter getCharacterByCharacterId(Integer playerId,
			Integer characterId) throws Exception {
		NosqlService nosqlService = ServiceLocator.nosqlService;
		RedisClient nosql = nosqlService.getNosql();
		PlayerCharacter character = null;
		List<PlayerCharacter> characterList = getCharacterListById(playerId);
		List<Integer> fightNumList = new ArrayList<Integer>();
		boolean isNeedUpdate = false;
		for (PlayerCharacter ch : characterList) {
			int fightNum = ch.getFightNum();
//			int lastFightNum = ch.initFightNum(); FIXME
//			if (fightNum != lastFightNum) { 	FIXME
//				isNeedUpdate = true;
//			}
			fightNumList.add(fightNum);
			if (ch.getSysCharacter().getId() == characterId) {
				character = ch;
			}
		}

		if (character == null) {
			throw new BaseException(ExceptionMessage.NO_HAVE_THE_CHARACTER);
		}
		Collections.sort(fightNumList, new Comparator<Integer>() {
			@Override
			public int compare(Integer i1, Integer i2) {
				return i2.compareTo(i1);
			}
		});
		double maxCFightNum = 0;
		if (isNeedUpdate) {
			// update charcter in cache
			String objKey = CacheUtil.oCharacterList(playerId);
			redisClient.setex(objKey, Constants.CACHE_ITEM_TIMEOUT,
					characterList);
		}
		for (int i = 0; i < fightNumList.size(); i++) {
			maxCFightNum += fightNumList.get(i)
					* Math.pow(Constants.FUNDUS_NUM, i);
		}
		Player player = playerService.get(playerId);
		String totalFightNumKey = NosqlKeyUtil.fightNumInRealTopByType("0");
		if ((int) maxCFightNum != player.getMaxFightNum()) {
			soClient.puchCMDtoClient(
					player.getName(),
					CommonUtil.messageFormat(CommonMsg.REFRESH_FIGHT_NUM, ""
							+ maxCFightNum));
			player.setMaxFightNum((int) maxCFightNum);
			// ServiceLocator.updateService.updatePlayerInfo(player); FIXME
		}
		int totalFightNumScore = (int) Math.abs(nosql.zScore(totalFightNumKey,
				String.valueOf(playerId)));
		if ((int) maxCFightNum != totalFightNumScore) {
			nosql.zAdd(totalFightNumKey, -maxCFightNum,
					String.valueOf(playerId));// 为了保证按照战斗力由大到小排列，将真实战斗力符号取反
		}
		String fightnumKey = NosqlKeyUtil.fightNumInRealTopByType(String
				.valueOf(characterId));
		int fightNumScore = (int) Math.abs(nosql.zScore(fightnumKey,
				String.valueOf(playerId)));
		if (character.getFightNum() != fightNumScore) {
			nosql.zAdd(fightnumKey, -character.getFightNum(),
					String.valueOf(playerId));
		}
		return character;
	}

	public int computeCommmonRank(double value, double firstValue,
			double lastValue, int rankNum) {
		if (value >= lastValue) {
			return rankNum + 1;
		}
		int rank = rankNum
				+ 1
				+ (int) (((lastValue - value) / (firstValue - lastValue)) * rankNum);
		return rank;
	}

	/**
	 * 获得玩家战斗力排名
	 * 
	 * @param playerId
	 * @param cid
	 *            0:总战斗力,1：火箭兵.......
	 * @return
	 * @throws Exception
	 */
	public int getPlayerFightNumRankInTop(int playerId, String cid)
			throws Exception {
		String key = NosqlKeyUtil.fightNumInRealTopByType(cid);
		Player player = getService.playerService.get(playerId);
		RedisClient nosql = redisClient;
		int chid = Integer.parseInt(cid);
		int rank = (int) nosql.zRank(key, String.valueOf(playerId)) + 1;
		if (rank > 0 && rank <= Constants.REAL_TOP_RANK_NUM) {// 如果玩家的排名在前15000内直接返回排名，否则要估算
			return rank;
		}
		int value = 0;
		double firstValue = 0;
		double lastValue = 0;
		Iterator<Tuple> firstItrt = nosql.zrangeWithScores(key, 0, 0)
				.iterator();
		Iterator<Tuple> lastItrt = nosql.zrangeWithScores(key, -1, -1)
				.iterator();
		if (firstItrt.hasNext()) {
			firstValue = Math.abs(firstItrt.next().getScore());
			if (lastItrt.hasNext()) {
				lastValue = Math.abs(lastItrt.next().getScore());
			}
		}
		int total = (int) nosql.zCard(key);
		if (total > Constants.REAL_TOP_RANK_NUM) {
			lastValue = Math.abs(nosql
					.zrangeWithScores(key, Constants.REAL_TOP_RANK_NUM - 1,
							Constants.REAL_TOP_RANK_NUM - 1).iterator().next()
					.getScore());
		}
		if (chid != 0) {// 0 表示综合战斗力
			PlayerCharacter character = getService.getCharacterByCharacterId(
					playerId, Integer.parseInt(cid));
			value = character.getFightNum();
		} else {
			value = player.getMaxFightNum();
		}
		if (rank > Constants.REAL_TOP_RANK_NUM) {
			rank = getService.computeCommmonRank(value, firstValue, lastValue,
					Constants.REAL_TOP_RANK_NUM);
		} else if (rank == 0) {
			String pId = String.valueOf(playerId);
			if (total < Constants.REAL_TOP_RANK_NUM) {
				nosql.zAdd(key, -value, pId);
				rank = (int) nosql.zRank(key, pId) + 1;
			} else {
				if (value > lastValue) {
					nosql.zAdd(key, -value, pId);
					rank = (int) nosql.zRank(key, pId) + 1;
				} else {
					rank = getService.computeCommmonRank(value, firstValue,
							lastValue, Constants.REAL_TOP_RANK_NUM);
				}
			}
		}
		return rank;
	}

}
