package com.pearl.fcw.info.lobby.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeoutException;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.pde.log.client.InforLogger;
import com.pde.log.common.LogMessage;
import com.pearl.fcw.info.core.persistence.cache.RedisClient;
import com.pearl.fcw.info.core.persistence.utils.NosqlKeyUtil;
import com.pearl.fcw.info.lobby.pojo.Friend;
import com.pearl.fcw.info.lobby.pojo.Player;
import com.pearl.fcw.info.lobby.pojo.enums.LogServerMessage;
import com.pearl.fcw.info.lobby.pojo.events.BasePlayerEvent;
import com.pearl.fcw.info.lobby.pojo.events.BasePlayerLogEvent;
import com.pearl.fcw.info.lobby.pojo.records.PlayerNosqlRecord;
import com.pearl.fcw.info.lobby.utils.CommonUtil;
import com.pearl.fcw.info.lobby.utils.Constants;
import com.pearl.fcw.info.lobby.utils.StringUtil;

@Service
public class NosqlService {
	@Resource
	protected RedisClient redisClient;
	protected InforLogger xunleiLogger;
	private static Logger logger = LoggerFactory.getLogger(NosqlService.class);

	/**
	 * ===========================================xunlei
	 * log============================================
	 */
	public void addXunleiLog(final String log) {
		try {
			xunleiLogger.log(LogServerMessage.xunleiLog.name(),
					LogMessage.Level.INFO_INT, log);
		} catch (Exception e) {
			e.printStackTrace();
			// ServiceLocator.xunleiLog.info(log);
		}
	}

	/**
	 * ============================================friend======================
	 * ========================
	 */
	public void publishEvent(BasePlayerEvent pe) {
		try {
			redisClient.addToQueue(pe.getKey(), pe.generateNosqlPlainStr(),
					Constants.PLAYER_NEWS_MAX_SIZE);
			// System.out.println(pe.generateNosqlPlainStr());
		} catch (Exception e) {
			logger.warn("fail to publish player event. playerId:"
					+ pe.getPlayerId() + "  " + e + " " + e.getStackTrace());
		}
	}

	public void publishEvent(BasePlayerLogEvent pe) {
		try {
			redisClient.addToQueue(pe.getKey(), pe.generateNosqlPlainStr(),
					Constants.PLAYER_NEWS_MAX_SIZE);
		} catch (Exception e) {
			logger.warn("fail to publish player event. playerId:"
					+ pe.getPlayerId() + "  " + e + " " + e.getStackTrace());
		}
	}

	public List<BasePlayerEvent> getPlayerEvent(int playerId, String playerName) {
		List<BasePlayerEvent> result = new ArrayList<BasePlayerEvent>();
		try {
			String key = PlayerNosqlRecord.getKey(playerId, playerName,
					BasePlayerEvent.DATATYPE, BasePlayerEvent.RECORDTYPE);
			List<String> events = redisClient.getQueue(key);
			for (String s : events) {
				result.add(BasePlayerEvent.getFromNosqlPlainStr(s, playerId,
						playerName));
			}
		} catch (Exception e) {
			logger.warn("fail to getPlayerEvent. playerId: " + playerId
					+ " exception is " + e);
		}
		return result;
	}

	public List<BasePlayerEvent> getLatestEventsFromFriends(List<Friend> friends)
			throws Exception {
		List<BasePlayerEvent> result = new ArrayList<BasePlayerEvent>();
		// for (Friend f : friends) {
		// List<BasePlayerEvent> recentEvents = getPlayerEvent(f.getFriendId(),
		// f.getName());
		// for (BasePlayerEvent pe : recentEvents) {
		// if ((System.currentTimeMillis()/1000 - pe.getTime()) <
		// Constants.PLAYER_NEWS_EXPIRE) {//only put none-expire data
		// result.add(pe);
		// }
		// }
		// }
		// Collections.sort(result, new Comparator<BasePlayerEvent>(){
		// @Override
		// public int compare(BasePlayerEvent o1, BasePlayerEvent o2) {
		// return
		// Long.valueOf(o2.getTime()).compareTo(Long.valueOf(o1.getTime()));
		// }
		// });//sort by time
		//
		// if (result.size() < Constants.PLAYER_NEWS_MAX_SIZE) {
		// return result;
		// }else{
		// return result.subList(0, Constants.PLAYER_NEWS_MAX_SIZE);
		// }
		return result;
	}

	public List<BasePlayerLogEvent> getPlayerLogEvent(int playerId,
			String playerName) {
		List<BasePlayerLogEvent> result = new ArrayList<BasePlayerLogEvent>();
		try {
			String key = PlayerNosqlRecord.getKey(playerId, playerName,
					BasePlayerLogEvent.DATATYPE, BasePlayerLogEvent.RECORDTYPE);
			List<String> events = redisClient.getQueue(key);
			for (String s : events) {
				result.add(BasePlayerLogEvent.getFromNosqlPlainStr(s, playerId,
						playerName));
			}
		} catch (Exception e) {
			logger.warn("fail to getPlayerEvent. playerId: " + playerId
					+ " exception is " + e);
		}
		return result;
	}

	public List<BasePlayerLogEvent> getPlayerLogList(int playerId,
			String playerName) throws Exception {
		List<BasePlayerLogEvent> result = new ArrayList<BasePlayerLogEvent>();
		List<BasePlayerLogEvent> recentEvents = getPlayerLogEvent(playerId,
				playerName);

		for (BasePlayerLogEvent pe : recentEvents) {
			if ((System.currentTimeMillis() / 1000 - pe.getTime()) < Constants.PLAYER_NEWS_EXPIRE) {// only
																									// put
																									// none-expire
																									// data
				result.add(pe);
			}
		}

		Collections.sort(result, new Comparator<BasePlayerLogEvent>() {

			@Override
			public int compare(BasePlayerLogEvent o1, BasePlayerLogEvent o2) {
				return Long.valueOf(o2.getTime()).compareTo(
						Long.valueOf(o1.getTime()));
			}
		});// sort by time

		if (result.size() < Constants.PLAYER_NEWS_MAX_SIZE) {
			return result;
		} else {
			return result.subList(0, Constants.PLAYER_NEWS_MAX_SIZE);
		}
	}

	public void deletePlayer(int playerId) {
		String pattern = PlayerNosqlRecord.PREFIX + playerId + ":*";
		try {
			// del 'p:id:*'
			redisClient.deleteAll(pattern);
		} catch (Exception e) {
			logger.warn("fail to delete player info. cid: " + playerId
					+ " delete pattern is " + pattern + " exception is " + e);
		}
	}

	public Date getDailyMissionDate(int playerId) throws Exception {
		String key = Constants.DAILY_MISSION_PREFIX + playerId + ":";
		String value = redisClient.get(key);// int:long
		if (null == value) {
			return null;
		}
		long millisecond = Long.parseLong(value.split(":")[1]);
		return new Date(millisecond);
	}

	public Date getWeekMissionDate(int playerId) throws Exception {
		String key = Constants.WEEK_MISSION_PREFIX + playerId + ":";
		String value = redisClient.get(key);// int:long
		if (null == value) {
			return null;
		}
		long millisecond = Long.parseLong(value.split(":")[1]);
		return new Date(millisecond);
	}

	public Date getMonthMissionDate(int playerId) throws Exception {
		String key = Constants.MONTH_MISSION_PREFIX + playerId + ":";
		String value = redisClient.get(key);// int:long
		if (null == value) {
			return null;
		}
		long millisecond = Long.parseLong(value.split(":")[1]);
		return new Date(millisecond);
	}

	public Date getGameDate(int playerId) throws Exception {
		String key = Constants.DAILY_GAME_PREFIX + playerId + ":";
		String value = redisClient.get(key);// int:long
		if (null == value) {
			return null;
		}
		long millisecond = Long.parseLong(value);
		return new Date(millisecond);
	}

	public void updateMissionDate(int playerId, Date date, int type)
			throws Exception {
		if (0 == type) {// everyday
			String key = Constants.DAILY_MISSION_PREFIX + playerId + ":";
			String value = new StringBuilder().append(playerId).append(":")
					.append(date.getTime()).toString();
			redisClient.delete(key);
			redisClient.set(key, value);
		} else if (1 == type) {// everyweek
			String key = Constants.WEEK_MISSION_PREFIX + playerId + ":";
			String value = new StringBuilder().append(playerId).append(":")
					.append(date.getTime()).toString();
			redisClient.delete(key);
			redisClient.set(key, value);
		} else if (2 == type) {// everymonth
			String key = Constants.MONTH_MISSION_PREFIX + playerId + ":";
			String value = new StringBuilder().append(playerId).append(":")
					.append(date.getTime()).toString();
			redisClient.delete(key);
			redisClient.set(key, value);
		}
	}

	public void updateGameDate(int playerId, Date date) throws Exception {
		String key = Constants.DAILY_GAME_PREFIX + playerId + ":";
		String value = date.getTime() + "";
		redisClient.set(key, value);
	}

	public List<Integer> getStayData(Player player) throws Exception {
		String key = Constants.YESTODAY_DATA + player.getId() + ":";
		String value = redisClient.get(key);
		List<Integer> returnList = new ArrayList<Integer>();
		if (null == value) {
			clearStayData(player);
			value = redisClient.get(key);
		}
		String[] values = value.split(",");
		for (String s : values) {
			returnList.add(Integer.parseInt(s));
		}
		return returnList;
	}

	public void clearStayData(Player player) throws Exception {
		String key = Constants.YESTODAY_DATA + player.getId() + ":";
		redisClient.delete(key);
		redisClient.set(key, "0,0,0,0,0,0,0,0");
	}

	public void updateStayDataAddExp(Player player, int expAdd)
			throws Exception {
		int expIndex = 6;
		String key = Constants.YESTODAY_DATA + player.getId() + ":";
		String value = redisClient.get(key);
		if (null == value) {
			clearStayData(player);
			value = redisClient.get(key);
		}
		String[] values = value.split(",");
		int exp = Integer.parseInt(values[expIndex]) + expAdd;
		values[expIndex] = exp + "";
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < values.length - 1; i++) {
			sb.append(values[i]).append(",");
		}
		sb.append(values[values.length - 1]);
		redisClient.delete(key);
		redisClient.set(key, sb.toString());
	}

	// ==========================================================================
	// dailycheckService
	// ==========================================================================
	public void initPlayerCheckList(Integer playerId) throws Exception {
		String key = Constants.DAILY_CHECK_PREFIX + playerId;// key(DAILY_CHECK_PREFIX
																// + playerId)
		List<String> checkList = new ArrayList<String>(0);
		redisClient.set(key, checkList);
	}

	public List<String> getPlayerCheckList(Integer playerId)
			throws TimeoutException, Exception {
		String key = Constants.DAILY_CHECK_PREFIX + playerId;// key(DAILY_CHECK_PREFIX
																// + playerId)
		return redisClient.getQueue(key);
	}

	/**
	 * 获得玩家预签和补签的天数
	 * 
	 * @param type
	 *            0-补签； 1-预签
	 */
	private int getCheckTimes(String key, int type, String yearMonth)
			throws Exception {
		String value = redisClient.get(key);
		if (value != null) {
			String[] values = value.split(":");
			if (values.length > 2 && values[2].equals(yearMonth))
				return Integer.parseInt(values[type]);
		}
		return 0;
	}

	/**
	 * 获得当玩家作为vip时，预签和补签的天数
	 *
	 * @param playerId
	 * @param type
	 *            0-补签； 1-预签
	 * @param yearMonth
	 * @param isVip
	 * @return
	 * @throws Exception
	 */
	public int getVipPlayerCheckTimes(int playerId, int type, String yearMonth)
			throws Exception {
		String key = Constants.VIP_DAILY_CHECK_TIMES_PREFIX + playerId;
		return this.getCheckTimes(key, type, yearMonth);
	}

	/**
	 * 获得玩家预签和补签的总天数（含vip和非vip）
	 *
	 * @param playerId
	 * @param type
	 *            0-补签； 1-预签
	 * @param yearMonth
	 * @param isVip
	 * @return
	 * @throws Exception
	 */
	public int getPlayerCheckTimes(int playerId, int type, String yearMonth)
			throws Exception {
		String key = Constants.DAILY_CHECK_TIMES_PREFIX + playerId;
		return this.getCheckTimes(key, type, yearMonth);
	}

	/**
	 * 更新玩家的预签和补签天数
	 * 
	 * @param key
	 * @param 0 补签 1 预签
	 * @param yearMonth
	 *            当前年月格式 20147(月份为：0-11) return
	 * @throws Exception
	 * */
	private void updateCheckTimes(String key, int type, String yearMonth)
			throws Exception {
		String value = redisClient.get(key); // 获得当前玩家的总签到补签次数，没有月份判定
		String[] values = { "0", "0", yearMonth };
		if (value != null) {
			values = value.split(":");
		}
		// Vip玩家签到，判定跨月之后，初始化预签和补签天数为0
		// 201407071742 OuYangGuang
		if (key != null
				&& key.indexOf(Constants.VIP_DAILY_CHECK_TIMES_PREFIX) > -1
				&& !values[2].equals(yearMonth)) {
			values[0] = "0"; // 补签次数
			values[1] = "0"; // 预签次数
		}
		int valueNum = Integer.parseInt(values[type]);
		int totalChance = type == 0 ? Constants.DAILY_CHECK_AGO_CHANCE
				: type == 1 ? Constants.DAILY_CHECK_FUTURE_CHANCE : 0;
		if (valueNum < totalChance) {
			values[type] = String.valueOf(valueNum + 1); // 无论是否签到补签超过次数，都增加补签或预签次数
			value = values[0] + ":" + values[1] + ":" + yearMonth;
			redisClient.set(key, value);
		}
	}

	/**
	 * 更新玩家的预签和补签天数（无论其是不是vip）
	 * 
	 * @param playerId
	 * @param type
	 *            0-补签； 1-预签
	 * @throws Exception
	 */
	public void updatePlayerCheckTimes(int playerId, int type, String yearMonth)
			throws Exception {

		String key = Constants.DAILY_CHECK_TIMES_PREFIX + playerId;
		this.updateCheckTimes(key, type, yearMonth);
	}

	/**
	 * 更新玩家的预签和补签天数(仅当玩家是vip时)
	 * 
	 * @param playerId
	 * @param type
	 *            0-补签； 1-预签
	 * @param yearMOnth
	 * @param isVip
	 * @throws Exception
	 */
	public void updateVipPlayerCheckTimes(int playerId, int type,
			String yearMonth) throws Exception {

		String key = Constants.VIP_DAILY_CHECK_TIMES_PREFIX + playerId;
		this.updateCheckTimes(key, type, yearMonth);
	}

	public void initCheckItemList(Integer playerId) throws Exception {
		String key = Constants.CHECK_ITEM_PREFIX + playerId;// key(CHECK_ITEM_PREFIX
															// + playerId)
		List<String> itemList = new ArrayList<String>(0);
		redisClient.set(key, itemList);
	}

	/**
	 * 
	 * @param playerId
	 * @param dateStr
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("static-access")
	public int isTeamSalary(int playerId) throws Exception {
		String key = Constants.TEAM_SALARY_DATE_PREFIX + playerId;// key(DAILY_CHECK_PREFIX
																	// +
																	// playerId)
		String lastGiftDate = redisClient.get(key);
		int flag = 1;
		Calendar ca = Calendar.getInstance();
		int thisWeek = ca.get(Calendar.WEEK_OF_YEAR);
		int thisYear = ca.get(Calendar.YEAR);
		if (lastGiftDate == null) {
			flag = 0;
		} else {
			ca.setTime(CommonUtil.dateFormatDate.parse(lastGiftDate));
			if (thisWeek != ca.get(ca.WEEK_OF_YEAR)
					|| thisYear != ca.get(Calendar.YEAR)) {
				redisClient.delete(key);
				flag = 0;
			}
		}
		return flag;
	}

	/**
	 * 存储领取战队工资的时间
	 * 
	 * @param playerId
	 * @param dateStr
	 * @throws Exception
	 */
	public void saveTeamSalary(int playerId, String dateStr) throws Exception {
		String key = Constants.TEAM_SALARY_DATE_PREFIX + playerId;// key(DAILY_CHECK_PREFIX
																	// +
																	// playerId)
		String lastGiftDate = redisClient.get(key);
		if (lastGiftDate == null || lastGiftDate.indexOf(dateStr) == -1)
			redisClient.set(key, dateStr);
	}

	public void dailyGusAward(int playerId, String date) throws Exception {
		String key = Constants.IS_DAILY_GUS_AWARD_PREFIX + playerId;
		redisClient.set(key, date);
	}

	/**
	 * 玩家猜中数字，系统是否发送奖励 0：没有 1：已经发
	 * 
	 * @param playerId
	 * @param date
	 * @return
	 * @throws Exception
	 */
	public int getIsDailyGusAward(int playerId, String date) throws Exception {
		String key = Constants.IS_DAILY_GUS_AWARD_PREFIX + playerId;
		String dtr = redisClient.get(key);
		if (dtr != null && dtr.equals(date))
			return 1;
		return 0;
	}

	// if player check today
	public boolean isPlayerCheck(int playerId, String dateStr)
			throws TimeoutException, Exception {
		List<String> checkList = getPlayerCheckList(playerId);
		return checkList.contains(dateStr);
	}

	public boolean isInitPlayerNum(int playerId, String dateStr)
			throws TimeoutException, Exception {
		String key = Constants.IS_INIT_PLAYER_NUM_PREFIX + playerId;
		String value = redisClient.get(key);
		if (dateStr.equals(value)) {
			return true;
		}
		return false;
	}

	public void setIsInitPlayerNum(int playerId, String dateStr)
			throws Exception {
		String key = Constants.IS_INIT_PLAYER_NUM_PREFIX + playerId;
		redisClient.set(key, dateStr);
	}

	/**
	 * 判断本月是否初始化签到列表
	 * 
	 * @param playerId
	 * @param date
	 * @return
	 * @throws TimeoutException
	 * @throws Exception
	 */
	public boolean isInitDailyCheckDays(int playerId, Calendar date)
			throws TimeoutException, Exception {
		List<String> checkList = getPlayerCheckList(playerId);
		if (checkList == null || checkList.size() == 0) {
			return true;
		}
		for (String checkDay : checkList) {
			Date day = CommonUtil.dateFormatDate.parse(checkDay);
			Calendar cDay = Calendar.getInstance();
			cDay.setTime(day);
			if (date.get(Calendar.MONTH) != cDay.get(Calendar.MONTH)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 产生随机数（0-9），写入Redis
	 * 
	 * @throws Exception
	 */
	public int dailyRandomNum(String dateStr) throws Exception {
		String key = Constants.DAILY_RANDOM_NUM_KEY;
		if (!isDailyRandomNum(dateStr)) {
			int randomNum = (int) (Math.random() * 10);
			redisClient.set(key, randomNum + ":" + dateStr);
		}
		return getDailyRandomNum(dateStr);
	}

	public int getDailyRandomNum(String dateStr) throws Exception {
		String key = Constants.DAILY_RANDOM_NUM_KEY;
		String value = redisClient.get(key);
		if (value == null || value.startsWith("-1")
				|| value.indexOf(dateStr) == -1)
			return -1;
		else
			return Integer.parseInt(value.substring(0, 1));
	}

	/**
	 * 系统是否已经产生随机幸运数，false:没有，true:已经产生
	 * 
	 * @param dateStr
	 * @return
	 * @throws Exception
	 */
	public boolean isDailyRandomNum(String dateStr) throws Exception {
		String key = Constants.DAILY_RANDOM_NUM_KEY;
		String nowNumStr = redisClient.get(key);
		if (nowNumStr == null || nowNumStr.indexOf(dateStr) == -1
				|| nowNumStr.startsWith("-1"))
			return false;
		return true;
	}

	/**
	 * 玩家猜数字（单个）
	 * 
	 * @param playerId
	 * @param num
	 * @param tomDateStr
	 * @throws Exception
	 */
	public void dailyGuessNum(int playerId, int num, String tomDateStr)
			throws Exception {
		String tomKey = Constants.DAILY_PLAYER_TOM_NUM_PREFIX + playerId;
		redisClient.set(tomKey, num + ":" + tomDateStr);
	}

	/**
	 * 玩家猜数字（多个）
	 * 
	 * @param playerId
	 * @param num
	 * @param tomDateStr
	 * @throws Exception
	 */
	public void dailyGuessNums(int playerId, String num, String tomDateStr)
			throws Exception {
		String tomKey = Constants.DAILY_PLAYER_TOM_NUM_PREFIX + playerId;
		redisClient.set(tomKey, num + ":" + tomDateStr);
	}

	/**
	 * 玩家当天第一次登陆，更新玩家所猜数，把玩家今天的数字替换为昨天猜的数字，把今天要猜的数字初始化为-1（表示玩家还没有猜）
	 * 
	 * @param playerId
	 * @param dateStr
	 * @param tomDateStr
	 * @throws Exception
	 */
	public void updatePlayerGuessNum(int playerId, String dateStr,
			String tomDateStr) throws Exception {
		String todayKey = Constants.DAILY_PLAYER_NUM_PREFIX + playerId;
		String tomKey = Constants.DAILY_PLAYER_TOM_NUM_PREFIX + playerId;
		String tomNum = redisClient.get(tomKey);
		if (tomNum == null || tomNum.indexOf(dateStr) == -1)
			redisClient.set(todayKey, "-1:" + dateStr);
		else
			redisClient.set(todayKey, tomNum + ":" + dateStr);
		redisClient.set(tomKey, "-1:" + tomDateStr);
	}

	public void deleteByKey(String key) throws Exception {
		redisClient.delete(key);
	}

	/**
	 * 获得玩家所猜数字（非vip用户，只能猜一个数）
	 * 
	 * @param playerId
	 * @param type
	 *            1-playerDailyNum; 2-playerDailyTomorrowNum
	 * @return int 若没猜过，返回-1
	 * @throws Exception
	 */
	public int getPlayerNum(int playerId, int type, String dateStr)
			throws Exception {

		String key = "";

		switch (type) {
		case 1:
			key = Constants.DAILY_PLAYER_NUM_PREFIX + playerId;
			break;
		case 2:
			key = Constants.DAILY_PLAYER_TOM_NUM_PREFIX + playerId;
			break;
		default:
			return -1;
		}

		if (!"".equals(key)) {
			String value = redisClient.get(key);
			if (value == null || value.isEmpty() || value.startsWith("-1")
					|| !value.contains(dateStr))
				return -1;
			else
				return Integer.parseInt(value.substring(0, 1));
		}
		return -1;
	}

	/**
	 * 获得玩家所猜数字（vip用户能猜3个数）
	 * 
	 * @param playerId
	 * @param type
	 *            1-playerDailyNum; 2-playerDailyTomorrowNum
	 * @return String 若没猜过，返回 "-1" （非vip时猜了一个数字，现在升vip了，只传这一位数过去）
	 * @throws Exception
	 */
	public String getPlayerNums(int playerId, int type, String dateStr)
			throws Exception {

		String key = "";

		switch (type) {
		case 1:
			key = Constants.DAILY_PLAYER_NUM_PREFIX + playerId;
			break;
		case 2:
			key = Constants.DAILY_PLAYER_TOM_NUM_PREFIX + playerId;
			break;
		default:
			return "-1";
		}

		if (!key.isEmpty()) {
			String value = redisClient.get(key);
			// 未猜过
			if (value == null || value.isEmpty() || value.startsWith("-1")
					|| !value.contains(dateStr))
				return "-1";
			// 非vip时猜了一个数字，现在升vip了，传这一位过去
			else if (":".equals(value.substring(1, 2)))
				return value.substring(0, 1);
			// vip 猜5个数
			else
				return value.substring(0, 5);
		}
		return "-1";
	}

	/**
	 * 获得玩家牌子id List
	 * 
	 * @param playerId
	 * @return
	 * @throws Exception
	 */
	public List<String> getPlayerBrandIdList(int playerId) throws Exception {
		String playerBrandListKey = Constants.PLAYER_BRAND_ID_LIST_PREFIX
				+ playerId;
		return redisClient.getQueue(playerBrandListKey);
	}

	public void deletePlayerBrandIdList(int playerId) throws Exception {
		String playerBrandListKey = Constants.PLAYER_BRAND_ID_LIST_PREFIX
				+ playerId;
		redisClient.delete(playerBrandListKey);
	}

	/**
	 * 设置玩家获得不同机会
	 * 
	 * @param playerId
	 * @param gameGetChance
	 * @param vipGetChance
	 * @param netBarGetChance
	 * @param activityGetChance
	 * @throws Exception
	 */
	public void setPlayerGetChances(int playerId, int gameGetChance,
			int vipGetChance, int netBarGetChance, int activityGetChance)
			throws Exception {
		String key = Constants.STAGE_GET_CHANCE_PREFIX + playerId;
		redisClient.delete(key);
		redisClient.set(key, gameGetChance + ":" + vipGetChance + ":"
				+ netBarGetChance + ":" + activityGetChance);
	}

	public String getPlayerGetChances(int playerId) throws Exception {
		String key = Constants.STAGE_GET_CHANCE_PREFIX + playerId;
		return redisClient.get(key);
	}

	public void setPlayerGetExps(int playerId, int getTotalExp, int gameGetExp,
			int vipGetExp, int netBarGetExp, int activityGetExp, int itemAdd,
			int teamAdd) throws Exception {
		String key = Constants.STAGE_GET_EXP_PREFIX + playerId;
		redisClient.delete(key);
		redisClient.set(
				key,
				new StringBuilder().append(getTotalExp + ":")
						.append(gameGetExp + ":").append(vipGetExp + ":")
						.append(netBarGetExp + ":")
						.append(activityGetExp + ":").append(itemAdd + ":")
						.append(teamAdd + "").toString());
	}

	public String getPlayerGetExps(int playerId) throws Exception {
		String key = Constants.STAGE_GET_EXP_PREFIX + playerId;
		return redisClient.get(key);
	}

	public void setPlayerGetGps(int playerId, int getTotalGp, int gameGetGp,
			int vipGetGp, int netBarGetGp, int activityGetGp, int itemAdd,
			int teamAdd) throws Exception {
		String key = Constants.STAGE_GET_GP_PREFIX + playerId;
		redisClient.delete(key);
		redisClient.set(
				key,
				new StringBuilder().append(getTotalGp + ":")
						.append(gameGetGp + ":").append(vipGetGp + ":")
						.append(netBarGetGp + ":").append(activityGetGp + ":")
						.append(itemAdd + ":").append(teamAdd + "").toString());
	}

	public String getPlayerGetGps(int playerId) throws Exception {
		String key = Constants.STAGE_GET_GP_PREFIX + playerId;
		return redisClient.get(key);
	}

	public void setPlayerGetScore(int playerId, int getScore) throws Exception {
		String key = Constants.STAGE_GET_SCORE_PREFIX + playerId;
		redisClient.delete(key);
		redisClient.set(key, String.valueOf(getScore));
	}

	public String getPlayerGetScore(int playerId) throws Exception {
		String key = Constants.STAGE_GET_SCORE_PREFIX + playerId;
		return redisClient.get(key);
	}

	public List<String> getPlayerMagixBoxItemIds() throws Exception {
		String key = Constants.MAGIC_BOX_ITEM_IDS_KEY;
		return redisClient.getQueue(key);
	}

	public List<String> getPlayerResMagixBoxItemIds() throws Exception {
		String key = Constants.RES_MAGIC_BOX_ITEM_IDS_KEY;
		return redisClient.getQueue(key);
	}

	/**
	 * 加入靶场奖励列表
	 * 
	 * @param player
	 * @param oa
	 * @throws Exception
	 */
	public void addDartleAwardToTop(Integer playerId, Integer oaId)
			throws Exception {
		String dartleAwardTopRedisKey = Constants.DARTLE_AWARD_TOP_REDIS_KEY;
		String value = playerId + ":" + oaId;
		redisClient.addToQueue(dartleAwardTopRedisKey, value,
				Constants.DARTLE_TOP_NUM);
	}

	public void setVipRandomList(int playerId, String randomSysItemIds)
			throws Exception {
		String vipRandomListKey = Constants.VIP_RANDOM_VSC_LIST_PREFIX
				+ playerId;
		redisClient.set(vipRandomListKey, randomSysItemIds);
	}

	public String getVipRandomList(int playerId) throws Exception {
		String vipRandomListKey = Constants.VIP_RANDOM_VSC_LIST_PREFIX
				+ playerId;
		return redisClient.get(vipRandomListKey);
	}

	public void setRandomStartIndexs(int playerId, String playerRandomIndexs)
			throws Exception {
		String vipRandomStartListKey = Constants.VIP_RANDOM_START_LIST_PREFIX
				+ playerId;
		redisClient.set(vipRandomStartListKey, playerRandomIndexs);
	}

	public String getRandomStartIndexs(int playerId) throws Exception {
		String vipRandomStartListKey = Constants.VIP_RANDOM_START_LIST_PREFIX
				+ playerId;
		return redisClient.get(vipRandomStartListKey);
	}

	public void updateOnlineAward(int playerId, Boolean isOn) throws Exception {
		String key = Constants.ONLINEAWARDSTATE + ":" + playerId;
		if (isOn) {
			redisClient.set(key, Constants.ONLINE_AWARD_LEVEL_ONE + ";"
					+ Constants.FLAGE_OTHER + ";" + Constants.LEVEL_IS_OUT_LINE);
		} else {
			String value = redisClient.get(key);
			if (value != null) {
				String[] states = value.split(";");
				if (states.length != 3) {
					redisClient.set(key, Constants.ONLINE_AWARD_LEVEL_ONE + ";"
							+ Constants.FLAGE_OTHER + ";"
							+ Constants.LEVEL_IS_OUT_LINE);
				} else {
					Date now = new Date();
					int level = Integer.parseInt(states[0]);
					long time = Long.parseLong(states[2]);
					if (level <= Constants.LEVEL_MINS.length
							&& !states[2].equals(Constants.LEVEL_IS_OUT_LINE)
							&& now.getTime() > time) {
						redisClient.set(key, level + ";"
								+ Constants.FLAG_NOT_GET_AWARD + ";"
								+ Constants.LEVEL_IS_OUT_LINE);
					} else
						redisClient.set(key, level + ";" + Constants.FLAGE_OTHER
								+ ";" + Constants.LEVEL_IS_OUT_LINE);
				}
			}
		}
	}

	/**
	 * 根据不同类型返回真实排名首尾数值的记录
	 * 
	 * @param typeStr
	 * @param type
	 *            0：更新时间 ，1：首记录，2：尾记录
	 * @return String
	 * @throws Exception
	 */
	public String getLastRcdInfo(String typeStr, int type) throws Exception {
		String key = NosqlKeyUtil.firstLastNumInTopRcdByType(typeStr);
		String value = redisClient.get(key);
		return value == null ? null : value.split(":")[type];
	}

	/**
	 * 更新玩家今天翻牌成功次数
	 * 
	 * @param playerId
	 * @param date
	 * @throws Exception
	 */
	public void updatePlayerOpenBrandsNum(int playerId, Date date)
			throws Exception {
		String key = Constants.STAGE_BRAND_OPEN_COUNT_PREFIX + playerId;
		String value = redisClient.get(key);
		String dateStr = CommonUtil.dateFormatDate.format(date);
		String[] values = value == null ? null : value.split(":");
		int count = values != null && values.length > 0 ? Integer
				.parseInt(values[0]) : 0;
		String rDateStr = values != null && values.length > 1 ? values[1]
				: null;
		String newValue = null;
		if (count == 0 || dateStr == null || !rDateStr.equals(dateStr)) {
			newValue = "1:" + dateStr;
		} else {
			newValue = ++count + ":" + dateStr;
		}
		redisClient.set(key, newValue);

	}

	public int getOpenBrandsNum(int playerId, Date date) throws Exception {
		String key = Constants.STAGE_BRAND_OPEN_COUNT_PREFIX + playerId;
		String value = redisClient.get(key);
		String dateStr = CommonUtil.dateFormatDate.format(date);
		String[] values = value == null ? null : value.split(":");
		int count = values != null && values.length > 0 ? Integer
				.parseInt(values[0]) : 0;
		String rDateStr = values != null && values.length > 1 ? values[1]
				: null;
		if (count == 0 || dateStr == null || !rDateStr.equals(dateStr)) {
			return 0;
		}
		return count;
	}

	public String getOnlineAwardState(int playerId) throws Exception {
		String key = Constants.ONLINEAWARDSTATE + ":" + playerId;
		String value = redisClient.get(key);
		if (value == null) {
			redisClient.set(key, Constants.ONLINE_AWARD_LEVEL_ONE + ";"
					+ Constants.FLAGE_OTHER + ";" + Constants.LEVEL_IS_OUT_LINE);
			return Constants.ONLINE_AWARD_LEVEL_ONE + ";"
					+ Constants.FLAGE_OTHER + ";" + Constants.LEVEL_IS_OUT_LINE;
		} else {
			String[] states = value.split(";");
			if (states.length != 3) {
				redisClient.set(key, Constants.ONLINE_AWARD_LEVEL_ONE + ";"
						+ Constants.FLAGE_OTHER + ";"
						+ Constants.LEVEL_IS_OUT_LINE);
				return Constants.ONLINE_AWARD_LEVEL_ONE + ";"
						+ Constants.FLAGE_OTHER + ";"
						+ Constants.LEVEL_IS_OUT_LINE;
			} else
				return redisClient.get(key);
		}
	}

	public void updateOnlineAwardState(int playerId, int level, String flag,
			long time) throws Exception {
		String key = Constants.ONLINEAWARDSTATE + ":" + playerId;
		redisClient.set(key, level + ";" + flag + ";" + time);
	}

	public byte initPlayerRemainVoterNum(int playerId, boolean isVip)
			throws Exception {
		byte playerRemainVoternum = isVip ? Constants.PLAYER_VIP_REMAIN_VOTERNUM
				: Constants.PLAYER_NORMAL_REMAIN_VOTERNUM;
		setPlayerRemainVoterNum(playerId, playerRemainVoternum);
		return playerRemainVoternum;
	}

	/**
	 * 获得玩家每日折扣某物品的购买标志
	 * 
	 * @param playerId
	 *            玩家id
	 * @param sysItemId
	 *            物品id
	 * @return int
	 * @throws Exception
	 */
	public int getPlayerDailyDiscountBuyFlag(int playerId, int sysItemId)
			throws Exception {
		String key = Constants.PLAYER_DAILY_DISCOUNT_PREX + sysItemId;
		String value = redisClient.hashGet(key, String.valueOf(playerId));
		if (value == null) {
			return 0;
		}
		return StringUtil.toInt(value);
	}

	/**
	 * 更新玩家每日折扣标志
	 * 
	 * @param playerId
	 * @param sysItemId
	 * @param index
	 * @throws Exception
	 */
	public void addPlayerDailyDiscountBuyFlag(int playerId, int sysItemId,
			int index) throws Exception {
		int flagNum = getPlayerDailyDiscountBuyFlag(playerId, sysItemId);
		redisClient.hashSet(Constants.PLAYER_DAILY_DISCOUNT_PREX + sysItemId,
				String.valueOf(playerId),
				String.valueOf(flagNum | (int) Math.pow(2, index)));
	}

	/**
	 * 获得每日折扣某物品折扣数
	 * 
	 * @param sysItemId
	 * @return
	 * @throws Exception
	 */
	public String[] getItemDailyDiscount(int sysItemId) throws Exception {
		String key = Constants.DAILY_DISCOUNT_SYSITEM_KEY;
		String value = redisClient.hashGet(key, String.valueOf(sysItemId));
		if (value == null) {
			return null;
		}
		return value.split("|");
	}

	public void setPlayerRemainVoterNum(int playerId, byte playerRemainVoternum)
			throws Exception {
		String key = Constants.PLAYERREMAINVOTERNUM + ":" + playerId;
		// nosql.decr(key, decrNum);
		redisClient.set(key, playerRemainVoternum + "");
	}

	public void initMeltingEnergy(int playerId) {

	}

	/**
	 * 更新用户防沉迷时间
	 * 
	 * @return
	 * @throws Exception
	 */
	public void updateFCMTime(int playerId, int time) throws Exception {
		String key = NosqlKeyUtil.FCMTime(playerId);
		String value = redisClient.get(key);
		// 若用户不属于防沉迷监控对象（即time一直为0），则不存时间
		if (time == 0) {
			if (value != null && Integer.parseInt(value) > 0) {
				redisClient.delete(key);
				redisClient.set(key, String.valueOf(time));
			} else if (value != null && Integer.parseInt(value) == 0) {
				redisClient.delete(key); // 清空为0的
			}
		} else {
			if (value != null) {
				redisClient.delete(key);
			}
			redisClient.set(key, String.valueOf(time));
		}

	}

	public int getFCMTime(int playerId) {
		int fcm_time = 0;
		try {
			String fcm_time_value = getNosql().get(
					NosqlKeyUtil.FCMTime(playerId));

			if (fcm_time_value != null) {
				fcm_time = Integer.parseInt(fcm_time_value);

			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return fcm_time;
	}

	/**
	 * 判断当前 行为是否能获得vip升级经验
	 * 
	 * @param type
	 *            0:签到,1:在线时长,2-4:每日任务(2 简单任务 3 正常任务 4困难任务),5:vip经验块;6:过关结算
	 * @param playerId
	 * @return
	 * @throws Exception
	 */
	public int hasGetVipUpExp(int type, int playerId) throws Exception {
		// redis 实现
		if (getNosql().hashGet(Constants.VIP_UPGRADE_EXP_STATUS,
				String.valueOf(playerId)) != null) {
			String status = getNosql().hashGet(
					Constants.VIP_UPGRADE_EXP_STATUS, String.valueOf(playerId));
			if (type >= status.length()) {
				return -1; // argument error
			} else if (type == Constants.VIP_EARN_EXP_METHODS.STAGECLEAR
					.getValue()) { // 6位以后 16进制计算 过关结算次数
				return Integer.valueOf(status.substring(type, status.length()),
						16);
			}
			// char to int
			return status.charAt(type) - 48;
			// return Integer.valueOf(status.substring(type,type+1),16);
		} else {
			getNosql().hashSet(Constants.VIP_UPGRADE_EXP_STATUS,
					String.valueOf(playerId),
					Constants.VIP_UPGRADE_EXP_STATUS_INIT);
		}

		// if(mcc.get(CacheUtil.sVipDailyExpStatus())!=null){
		// Object object=mcc.get(CacheUtil.sVipDailyExpStatus());
		//
		// }
		return 0;
	}

	public void updateVipUpExpStatus(int type, int playerId, int currentStatus)
			throws Exception {
		String status = getNosql().hashGet(Constants.VIP_UPGRADE_EXP_STATUS,
				String.valueOf(playerId));
		if (type == Constants.VIP_EARN_EXP_METHODS.STAGECLEAR.getValue()) {
			String newStageStatus = Integer.toHexString(currentStatus + 1);
			if (newStageStatus.length() == 1) {
				String updatedStatus = status.substring(0, type) + "0"
						+ Integer.toHexString(currentStatus + 1);
				getNosql().hashSet(Constants.VIP_UPGRADE_EXP_STATUS,
						String.valueOf(playerId), updatedStatus);
			} else {
				String updatedStatus = status.substring(0, type)
						+ Integer.toHexString(currentStatus + 1);
				getNosql().hashSet(Constants.VIP_UPGRADE_EXP_STATUS,
						String.valueOf(playerId), updatedStatus);
			}
		} else {
			String updatedStatus = StringUtil.replaceByIndex(status, type,
					Integer.toHexString(currentStatus + 1).charAt(0));
			getNosql().hashSet(Constants.VIP_UPGRADE_EXP_STATUS,
					String.valueOf(playerId), updatedStatus);
		}
	}

	public boolean isVipUpExpStatusFull(int type, int currentStatus) {
		// have no simple method to use switch..case with enum instance
		if (type == Constants.VIP_EARN_EXP_METHODS.DAILYCHECK.getValue()) {
			return currentStatus >= 1; // 签到 每天1次
		} else if (type == Constants.VIP_EARN_EXP_METHODS.ONLINETIME.getValue()) {
			return currentStatus >= 3; // 在线时长 每天领取3次
		} else if (type == Constants.VIP_EARN_EXP_METHODS.DAILYMISSIONSIMPLE
				.getValue()) {
			return currentStatus >= 2; // 简单任务 每天2个
		} else if (type == Constants.VIP_EARN_EXP_METHODS.DAILYMISSIONNORMAL
				.getValue()) {
			return currentStatus >= 1; // 正常任务 每天1个
		} else if (type == Constants.VIP_EARN_EXP_METHODS.DAILYMISSIONHARD
				.getValue()) {
			return currentStatus >= 1; // 困难任务 每天1个
		} else if (type == Constants.VIP_EARN_EXP_METHODS.VIPEXPITEM.getValue()) {
			return currentStatus >= 1; // vip经验块 每天1个
		} else if (type == Constants.VIP_EARN_EXP_METHODS.STAGECLEAR.getValue()) {
			return currentStatus >= 256; // 过关结算
		} else {
			return true;
		}

	}

	public RedisClient getNosql() {
		return redisClient;
	}

	public void setNosql(RedisClient nosql) {
		this.redisClient = nosql;
	}

	public InforLogger getXunleiLogger() {
		return xunleiLogger;
	}

	public void setXunleiLogger(InforLogger xunleiLogger) {
		this.xunleiLogger = xunleiLogger;
	}
}
