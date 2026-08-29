package com.pearl.o2o.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeoutException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pde.log.client.InforLogger;
import com.pearl.o2o.enumuration.LogServerMessage;
import com.pearl.o2o.nosql.NoSql;
import com.pearl.o2o.nosql.NosqlKeyUtil;
import com.pearl.o2o.nosql.object.PlayerNosqlRecord;
import com.pearl.o2o.nosql.object.TeamNosqlRecord;
import com.pearl.o2o.nosql.object.deathrecord.DeathRecord;
import com.pearl.o2o.nosql.object.outmessage.PlayerOutMessageContent;
import com.pearl.o2o.nosql.object.outmessage.PlayerOutMessageMeta;
import com.pearl.o2o.nosql.object.playerevent.BasePlayerEvent;
import com.pearl.o2o.nosql.object.playerevent.BasePlayerLogEvent;
import com.pearl.o2o.nosql.object.playerrate.PlayerRate;
import com.pearl.o2o.nosql.object.recentplayer.RecentPlayer;
import com.pearl.o2o.nosql.object.team.TeamBattleHistory;
import com.pearl.o2o.nosql.object.team.TeamRecordObj;
import com.pearl.o2o.pojo.Friend;
import com.pearl.o2o.pojo.Message;
import com.pearl.o2o.pojo.OnlineAward;
import com.pearl.o2o.pojo.Player;
import com.pearl.o2o.pojo.StageClearPlayerInfo;
import com.pearl.o2o.pojo.SysItem;
import com.pearl.o2o.pojo.TmpPlayerItem;
import com.pearl.o2o.utils.CommonUtil;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.Constants.GAMETYPE;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.ServiceLocator;
import com.pearl.o2o.utils.StringUtil;

import edu.emory.mathcs.backport.java.util.Collections;

public class NosqlService {
	protected NoSql nosql;
	protected InforLogger xunleiLogger;
	private static Logger logger = LoggerFactory.getLogger(NosqlService.class);
	
	/**===========================================xunlei log============================================*/
	public void addXunleiLog(final String log){
		try{
			xunleiLogger.log(LogServerMessage.xunleiLog.name(), com.pde.log.common.LogMessage.Level.INFO_INT, log);
		}catch (Exception e) {
			e.printStackTrace();
//			ServiceLocator.xunleiLog.info(log);
		}
	}
	/**============================================friend==============================================*/
	public void publishEvent(BasePlayerEvent pe) {
		try{
			nosql.addToQueue(pe.getKey(), pe.generateNosqlPlainStr(), Constants.PLAYER_NEWS_MAX_SIZE);
//			System.out.println(pe.generateNosqlPlainStr());
		}catch(Exception e){
			logger.warn("fail to publish player event. playerId:" + pe.getPlayerId() + "  " + e + " " + e.getStackTrace());
		}
	}
	public void publishEvent(BasePlayerLogEvent pe) {
		try{
			nosql.addToQueue(pe.getKey(), pe.generateNosqlPlainStr(), Constants.PLAYER_NEWS_MAX_SIZE);
		}catch(Exception e){
			logger.warn("fail to publish player event. playerId:" + pe.getPlayerId() + "  " + e + " " + e.getStackTrace());
		}
	}
	public List<BasePlayerEvent> getPlayerEvent(int playerId, String playerName) {
		List<BasePlayerEvent> result = new ArrayList<BasePlayerEvent>();
		try{
			String key = PlayerNosqlRecord.getKey(playerId,playerName, BasePlayerEvent.DATATYPE, BasePlayerEvent.RECORDTYPE);
			List<String> events = nosql.getQueue(key);
			for (String s : events) {
				result.add(BasePlayerEvent.getFromNosqlPlainStr(s, playerId, playerName));
			}
		}catch(Exception e){
			logger.warn("fail to getPlayerEvent. playerId: " + playerId + " exception is " + e);
		}
		return result;
	}
	public List<BasePlayerEvent> getLatestEventsFromFriends(List<Friend> friends) throws Exception{
		List<BasePlayerEvent> result = new ArrayList<BasePlayerEvent>();
//		for (Friend f : friends) {
//			List<BasePlayerEvent> recentEvents = getPlayerEvent(f.getFriendId(), f.getName());
//			for (BasePlayerEvent pe : recentEvents) {
//				if ((System.currentTimeMillis()/1000 - pe.getTime()) < Constants.PLAYER_NEWS_EXPIRE) {//only put none-expire data
//					result.add(pe);
//				}
//			}
//		}
//		Collections.sort(result, new Comparator<BasePlayerEvent>(){
//			@Override
//			public int compare(BasePlayerEvent o1, BasePlayerEvent o2) {
//				return Long.valueOf(o2.getTime()).compareTo(Long.valueOf(o1.getTime())); 
//			}
//		});//sort by time
//		
//		if (result.size() < Constants.PLAYER_NEWS_MAX_SIZE) {
//			return result;
//		}else{
//			return result.subList(0, Constants.PLAYER_NEWS_MAX_SIZE);
//		}
		return result;
	}
	public List<BasePlayerLogEvent> getPlayerLogEvent(int playerId, String playerName) {
		List<BasePlayerLogEvent> result = new ArrayList<BasePlayerLogEvent>();
		try{
			String key = PlayerNosqlRecord.getKey(playerId,playerName, BasePlayerLogEvent.DATATYPE, BasePlayerLogEvent.RECORDTYPE);
			List<String> events = nosql.getQueue(key);
			for (String s : events) {
				result.add(BasePlayerLogEvent.getFromNosqlPlainStr(s, playerId, playerName));
			}
		}catch(Exception e){
			logger.warn("fail to getPlayerEvent. playerId: " + playerId + " exception is " + e);
		}
		return result;
	}
	public List<BasePlayerLogEvent> getPlayerLogList(int playerId,String playerName) throws Exception{
		List<BasePlayerLogEvent> result = new ArrayList<BasePlayerLogEvent>();
		List<BasePlayerLogEvent> recentEvents = getPlayerLogEvent(playerId, playerName);
		for (BasePlayerLogEvent pe : recentEvents) {
			if ((System.currentTimeMillis()/1000 - pe.getTime()) < Constants.PLAYER_NEWS_EXPIRE) {//only put none-expire data
				result.add(pe);
			}
		}
		
		Collections.sort(result, new Comparator<BasePlayerEvent>(){
			@Override
			public int compare(BasePlayerEvent o1, BasePlayerEvent o2) {
				return Long.valueOf(o2.getTime()).compareTo(Long.valueOf(o1.getTime())); 
			}
		});//sort by time
		
		if (result.size() < Constants.PLAYER_NEWS_MAX_SIZE) {
			return result;
		}else{
			return result.subList(0, Constants.PLAYER_NEWS_MAX_SIZE);
		}
	}
	
	public void deletePlayer(int playerId) {
		String pattern = PlayerNosqlRecord.PREFIX + playerId + ":*";
		try{
			// del 'p:id:*'
			nosql.deleteAll(pattern);
		}catch(Exception e){
			logger.warn("fail to delete player info. cid: " + playerId + " delete pattern is " + pattern + " exception is " + e);
		}
	}

	/** ***********************************Team ************************************************************/ 
	
	public void saveTeamHistory(TeamBattleHistory teamHistory) {
		try{
			nosql.addToQueue(teamHistory.getKey(), teamHistory.generateNosqlPlainStr(), Constants.TEAM_HISTORY_MAX_SIZE);
		}catch(Exception e){
			logger.warn("fail to save team battle history " + teamHistory.getTeamId() + " exception is " + e);
		}
	}
	
	public List<TeamBattleHistory> getTeamHistory(int teamId) throws TimeoutException, Exception{
		List<TeamBattleHistory> result = new ArrayList<TeamBattleHistory>();
		try{
			String key = TeamNosqlRecord.getKey(teamId, TeamBattleHistory.DATATYPE, TeamBattleHistory.RECORDTYPE);
			List<String> histories = nosql.getQueue(key);
			for (String str : histories) {
				result.add(new TeamBattleHistory(str, teamId));
			}
		}catch(Exception e){
			logger.warn("fail to get TeamHistory " + teamId + " exception is " + e);
		}
		return result;
	}
	
	/*********************************************** player rate ***************************************************/
	
	public List<PlayerRate> getPlayerRatesByType(int playerId, String playerName, GAMETYPE gameType){
		List<PlayerRate> ratesHistory = new ArrayList<PlayerRate>();
		Calendar c = Calendar.getInstance();
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.setMinimalDaysInFirstWeek(1);
		int thisWeek = c.get(Calendar.WEEK_OF_YEAR);
		
		try{
			String key = PlayerNosqlRecord.getKey(playerId, playerName, PlayerRate.DATATYPE, PlayerRate.RECORDTYPE_PREFIX + gameType);
			List<String> rateHistories = nosql.getQueue(key, Constants.PLAYER_RATE_HISTORY_SIZE);
			for (String s : rateHistories) {
				PlayerRate rate = new PlayerRate(s, playerId, playerName, gameType);
				if (thisWeek != rate.getTime()) {
					ratesHistory.add(rate);
				}
			}
		}catch(Exception e){
			logger.warn("failt to get player rate " + e);
		}
		return ratesHistory;
	}
	
	
	public void addPlayerRate(PlayerRate playerRate){
		try{
			String key = PlayerNosqlRecord.getKey(playerRate.getPlayerId(),
					playerRate.getPlayerName(), PlayerRate.DATATYPE,
					PlayerRate.RECORDTYPE_PREFIX + playerRate.getGameType());
			nosql.addToQueue(key, playerRate.generateNosqlPlainStr(), Constants.PLAYER_RATE_HISTORY_SIZE);
		}catch(Exception e){
			logger.warn("failt to add player rate. " + e);
		}
	}
	/*********************************************** player out box ***************************************************/
	
	public List<PlayerOutMessageMeta> getOutMessageMessage(int playerId, String playerName){
		List<PlayerOutMessageMeta> result = new ArrayList<PlayerOutMessageMeta>();
		try{
			String key = PlayerNosqlRecord.getKey(playerId, playerName, PlayerOutMessageMeta.DATATYPE, PlayerOutMessageMeta.RECORDTYPE);
			List<String> messages = nosql.getQueue(key);
			for (String s : messages) {
				result.add(new PlayerOutMessageMeta(playerId, playerName, s));
			}
		}catch(Exception e){
			logger.warn("failt to get player outmessage list. " + e);
		}
		return result;
	}
	
	public List<Message> getOutMessageList(int playerId, String playerName){
		List<Message> result = new ArrayList<Message>();
		for (PlayerOutMessageMeta messageMeta : getOutMessageMessage(playerId, playerName)) {
			result.add(messageMeta.toMessage());
		}
		return result;
	}

	public String getOutMessageContent(int playerId, String playerName, int messageId) throws Exception{
		String key = PlayerOutMessageContent.getKey(playerId, playerName,
				PlayerOutMessageContent.DATATYPE,
				PlayerOutMessageContent.RECORDTYPE)
				+ ":" + messageId;
		return nosql.get(key);
	}
	
	public void addOutMessage(Message message) {
		try{
			int  playerId = message.getSenderId();
			String playerName = message.getSender();
			//PlayerOutMessageContent messageContent = new PlayerOutMessageContent(playerId, playerName, message.getContent());
			String contentKey = PlayerOutMessageContent.getKey(playerId, playerName,
								PlayerOutMessageContent.DATATYPE,
								PlayerOutMessageContent.RECORDTYPE)
								+ ":" + message.getId();
			PlayerOutMessageMeta messageMeta = new PlayerOutMessageMeta(playerId, playerName, message, contentKey);
	
			//set message meta and content
			//no transaction guarantee, if fails, an exception will be thrown, let outer code rollback the db operation
			//nosql.set(key, messageContent.getContent());
			nosql.addToQueue(messageMeta.getKey(), messageMeta.generateNosqlPlainStr(),Constants.OUT_BOX_MAX_SIZE);
		}catch(Exception e){
			logger.warn("Error happened during add out message, exception is " + e);
		}
	}
	
	public void deleteOutMessages(int playerId, String playerName, List<String> messageIds) throws TimeoutException, Exception{
		try{
			List<PlayerOutMessageMeta> metas = getOutMessageMessage(playerId, playerName);
			List<PlayerOutMessageMeta> newList = new ArrayList<PlayerOutMessageMeta>();
			
			for (PlayerOutMessageMeta meta: metas){
				if (!messageIds.contains(String.valueOf(meta.getMessageId()))) {
					newList.add(meta);
				}
			}
			
			//delete old list
			String key = PlayerOutMessageMeta.getKey(playerId, playerName, PlayerOutMessageMeta.DATATYPE, PlayerOutMessageMeta.RECORDTYPE);
			nosql.delete(key);
			
			for (PlayerOutMessageMeta meta: newList) {
				nosql.addToQueue(key, meta.generateNosqlPlainStr(),Constants.OUT_BOX_MAX_SIZE);
			}
		}catch(Exception e){
			logger.warn("Error happened during delete out message, exception is " + e);
		}
	}
	/***********************************************recent player ***************************************************/
	public  List<Integer> getRecentPlayerIds(int playerId, String playerName){
		String key = RecentPlayer.getKey(playerId, playerName, RecentPlayer.DATATYPE, RecentPlayer.RECORDTYPE);
		List<String> recentPlayerIds = new ArrayList<String>();
		try {
			recentPlayerIds = nosql.getQueue(key);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		List<Integer> result = new ArrayList<Integer>();
		for (String id : recentPlayerIds) {
			result.add(Integer.valueOf(id));
		}
		return result;
	}
	
	/**
	 * used in stage quit, the player who quit will add all other players, 
	 * meanwhile, those players will also add this player
	 * 
	 * @param playerList
	 * @param playerId
	 * @param playerName
	 */
	public void addRecentPlayersBidirection(List<Player> playerList, int playerId, String playerName){
		try{
			addRecentPlayers(playerList,playerId,playerName);
			List<Player> list = new ArrayList<Player>();
			Player p = new Player();
			p.setId(playerId);
			list.add(p);
			for (Player player : playerList) {
				addRecentPlayers(list, player.getId(), player.getName());
			}
		}catch(Exception e){
			logger.warn("error happened during add recentplayer bidirectionly");
		}
	}
	
	public void addRecentPlayers(List<Player> playerList, int playerId, String playerName){
		String key = RecentPlayer.getKey(playerId, playerName, RecentPlayer.DATATYPE, RecentPlayer.RECORDTYPE);
		try{
			List<String> oldIds = nosql.getQueue(key);
			List<String> newList = new ArrayList<String>(Constants.PLAYER_RECENTPLAYER_SIZE);
			//add new ids firstly
			for(Player p : playerList){
				if (p.getId() != playerId){//do not add self
					newList.add(String.valueOf(p.getId()));
				}
			}
			//add old id and remove duplicate one
			for (int i=0;i<oldIds.size() && newList.size()<Constants.PLAYER_RECENTPLAYER_SIZE;i++) {
				String id = oldIds.get(i);
				if (!newList.contains(id)) {
					newList.add(String.valueOf(id));
				}
			}
			//update list
			nosql.set(key, newList);
		}catch(Exception e){
			logger.warn("error happend during add recentplayer", e);
		}
	}
	
	/*****************************************death record*****************************************/
	/**
	 * the record in list will be duplicate, so just iterator all and incr 1 each time
	 */
	public void addDeathRecord(List<DeathRecord> list,int gameType, int level){
//		String keyPrifix = "DR:" + gameType + ":" + level+":";
//		Map<String,Integer> map = new HashMap<String,Integer>();
//		for (DeathRecord dr : list){
//			//DR:gameType:levle:X:Y:Z:TEAM:TYPE
//			String key = keyPrifix + dr.getSuffixKey();
//			
//			Integer value = map.get(key);
//			if (value == null) {
//				map.put(key, 1);
//			}else{
//				map.put(key, value+1);
//			}
//		}
//		try{
//			nosql.batchIncrBy(map);
//		}catch(Exception e){
//			logger.error("error", e);
//		}
	}
	
	
	public List<DeathRecord> getDeathRecord(int gameType, int level) throws Exception{
		String keyPattern = "DR:" + gameType + ":" + level+":*";
		long start = System.currentTimeMillis();
		Set<String> keys = nosql.getKeysByPattern(keyPattern);
		
		List<DeathRecord> result = new ArrayList<DeathRecord>(keys.size());
		
//		for (String key : keys){
//			int value = Integer.valueOf(nosql.get(key));
//			String[] fields = key.split(":");
//			if (fields.length <8) {//TODO ignore abnormal data
//				continue;
//			}
//			int x = Integer.valueOf(fields[3]);
//			int y = Integer.valueOf(fields[4]);
//			int z = Integer.valueOf(fields[5]);
//			int team = Integer.valueOf(fields[6]);
//			int type = Integer.valueOf(fields[7]);
//			
//			DeathRecord dr = new DeathRecord();
//			dr.setX(x);dr.setY(y);dr.setZ(z);dr.setTeam(team);dr.setType(type);
//			dr.setValue(value);
//			result.add(dr);
//		}
		long end = System.currentTimeMillis();
		
		logger.error("getDeathRecordCost" + (end - start) + "  count " + keys.size());
		return result;
	}
	
	/*****************************************death record*****************************************/
	public void addTeamRecord(List<TeamRecordObj> list){
		String key = "";
		try {
			for(TeamRecordObj record : list){
				key = Constants.TEAMRECORD_PREFIX + record.getTeam();
				nosql.addToQueue(key, record.getStringValue(), Constants.TEAM_RECORD_MAX_SIZE);
//				nosql.rpush(key, record.getStringValue(), 50);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<TeamRecordObj> getAllTeamRecord(int id) throws Exception{
		String key = Constants.TEAMRECORD_PREFIX + id;
		List<TeamRecordObj> resultList = new ArrayList<TeamRecordObj>();
		try {
				List<String> values= nosql.getQueue(key);
				if(null != values && values.size()>0){
					for(String value:values){
						resultList.add(TeamRecordObj.toObject(value));
					}
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return resultList;
	}
	
	public Date getDailyMissionDate(int playerId) throws Exception{
		String key = Constants.DAILY_MISSION_PREFIX + playerId + ":";
		String value = nosql.get(key);//int:long
		if(null == value){
			return null;
		}
		long millisecond = Long.parseLong(value.split(":")[1]);
		return new Date(millisecond);
	}
	
	public Date getWeekMissionDate(int playerId) throws Exception{
		String key = Constants.WEEK_MISSION_PREFIX + playerId + ":";
		String value = nosql.get(key);//int:long
		if(null == value){
			return null;
		}
		long millisecond = Long.parseLong(value.split(":")[1]);
		return new Date(millisecond);
	}
	
	public Date getMonthMissionDate(int playerId) throws Exception{
		String key = Constants.MONTH_MISSION_PREFIX + playerId + ":";
		String value = nosql.get(key);//int:long
		if(null == value){
			return null;
		}
		long millisecond = Long.parseLong(value.split(":")[1]);
		return new Date(millisecond);
	}
	
	public Date getGameDate(int playerId) throws Exception{
		String key = Constants.DAILY_GAME_PREFIX + playerId + ":";
		String value = nosql.get(key);//int:long
		if(null == value){
			return null;
		}
		long millisecond = Long.parseLong(value);
		return new Date(millisecond);
	}
	
	public void updateMissionDate(int playerId, Date date, int type) throws Exception{
		if(0 == type){//everyday
			String key = Constants.DAILY_MISSION_PREFIX + playerId + ":";
			String value = new StringBuilder().append(playerId).append(":").append(date.getTime()).toString();
			nosql.delete(key);
			nosql.set(key, value);
		} else if(1 == type){//everyweek
			String key = Constants.WEEK_MISSION_PREFIX + playerId + ":";
			String value = new StringBuilder().append(playerId).append(":").append(date.getTime()).toString();
			nosql.delete(key);
			nosql.set(key, value);
		}else if(2 == type){//everymonth
			String key = Constants.MONTH_MISSION_PREFIX + playerId + ":";
			String value = new StringBuilder().append(playerId).append(":").append(date.getTime()).toString();
			nosql.delete(key);
			nosql.set(key, value);
		}
	}
	public void updateGameDate(int playerId, Date date) throws Exception{
		String key = Constants.DAILY_GAME_PREFIX + playerId + ":";
		String value = date.getTime() + "";
		nosql.set(key, value);
	}
	
	public List<Integer> getStayData(Player player) throws Exception{
		String key = Constants.YESTODAY_DATA + player.getId() + ":";
		String value = nosql.get(key);
		List<Integer> returnList = new ArrayList<Integer>();
		if(null == value){
			clearStayData(player);
			value = nosql.get(key);
		}
		String[] values = value.split(",");
		for(String s: values){
			returnList.add(Integer.parseInt(s));
		}
		return returnList;
	}
	
	public void clearStayData(Player player) throws Exception{
		String key = Constants.YESTODAY_DATA + player.getId() + ":";
		nosql.delete(key);
		nosql.set(key, "0,0,0,0,0,0,0,0");
	}
	
	public void updateStayData(Player player, StageClearPlayerInfo playerInfo, boolean isLose, boolean isIgnoreDead) throws Exception{//数据结构=win,lose,kill,assist,dead,score,exp,gp
		String key = Constants.YESTODAY_DATA + player.getId() + ":";
		String value = nosql.get(key);
		if(null == value){
			clearStayData(player);
			value = nosql.get(key);
		}
		String[] values = value.split(",");
		StringBuilder valueAddStr = new StringBuilder();
		List<Integer> valueAdd = new ArrayList<Integer>();
		valueAdd.add(playerInfo.getIsWiner());
		if (isLose) {
			//valueAdd.add((playerInfo.getIsWiner() == 1 ? 0 : 1));
			valueAdd.add(1);
		} else {
			valueAdd.add(0);
		}
		valueAdd.add(playerInfo.getKill());
		valueAdd.add(playerInfo.getAssistNum());
		if(!isIgnoreDead){
			valueAdd.add(playerInfo.getDead());
		} else {
			valueAdd.add(0);
		}
		valueAdd.add(playerInfo.getScore());
		valueAdd.add(playerInfo.getExp());
		valueAdd.add(playerInfo.getGp());
		
		for(int i = 0; i < values.length; i++){
			valueAddStr.append((Integer.parseInt(values[i]) + valueAdd.get(i))).append(",");
		}
		
		nosql.delete(key);
		nosql.set(key, com.pearl.o2o.utils.CommonUtil.cutLastWord(valueAddStr.toString()));
	}
	
	public void updateStayDataAddExp(Player player, int expAdd) throws Exception{
		int expIndex = 6;
		String key = Constants.YESTODAY_DATA + player.getId() + ":";
		String value = nosql.get(key);
		if(null == value){
			clearStayData(player);
			value = nosql.get(key);
		}
		String[] values = value.split(",");
		int exp = Integer.parseInt(values[expIndex]) + expAdd;
		values[expIndex] = exp +"";
		StringBuilder sb = new StringBuilder();
		for(int i = 0;i<values.length -1 ;i++){
			sb.append(values[i]).append(",");
		}
		sb.append(values[values.length - 1]);
		nosql.delete(key);
		nosql.set(key, sb.toString());
	}
	
	public void updateStayData(Player player, int gpAdd) throws Exception{
		String key = Constants.YESTODAY_DATA + player.getId() + ":";
		String value = nosql.get(key);
		if(null == value){
			clearStayData(player);
			value = nosql.get(key);
		}
		String[] values = value.split(",");
		StringBuilder valueAddStr = new StringBuilder();
		List<Integer> valueAdd = new ArrayList<Integer>();
		valueAdd.add(0);
		valueAdd.add(0);
		valueAdd.add(0);
		valueAdd.add(0);
		valueAdd.add(0);
		valueAdd.add(0);
		valueAdd.add(0);
		valueAdd.add(gpAdd);
		
		for(int i = 0; i < values.length; i++){
			valueAddStr.append((Integer.parseInt(values[i]) + valueAdd.get(i))).append(",");
		}
		
		nosql.delete(key);
		nosql.set(key, com.pearl.o2o.utils.CommonUtil.cutLastWord(valueAddStr.toString()));
	}
	
	//==========================================================================
	//dailycheckService
	//==========================================================================
	public void initPlayerCheckList(Integer playerId) throws Exception{
		String key = Constants.DAILY_CHECK_PREFIX + playerId  ;//key(DAILY_CHECK_PREFIX + playerId)
		List<String> checkList = new ArrayList<String>(0);
		nosql.set(key,checkList);
	}
	public List<String> getPlayerCheckList(Integer playerId) throws TimeoutException, Exception{
		String key = Constants.DAILY_CHECK_PREFIX + playerId  ;//key(DAILY_CHECK_PREFIX + playerId)
		return nosql.getQueue(key);
	}

	/** 
	 * 获得玩家预签和补签的天数
	 * 
	 * @param type 0-补签； 1-预签
	 */
	private int getCheckTimes(String key, int type, String yearMonth) throws Exception {
		String value = nosql.get(key);
		if (value!=null) {
			String[] values = value.split(":");
			if(values.length>2 && values[2].equals(yearMonth))
				return Integer.parseInt(values[type]);
		}
		return 0;
	}

	/**
	 * 获得当玩家作为vip时，预签和补签的天数
	 *
	 * @param playerId
	 * @param type 0-补签； 1-预签
	 * @param yearMonth
	 * @param isVip
	 * @return
	 * @throws Exception
	 */
	public int getVipPlayerCheckTimes(int playerId, int type, String yearMonth) throws Exception {
		String key = Constants.VIP_DAILY_CHECK_TIMES_PREFIX + playerId;
		return this.getCheckTimes(key, type, yearMonth);
	}

	/**
	 * 获得玩家预签和补签的总天数（含vip和非vip）
	 *
	 * @param playerId
	 * @param type 0-补签； 1-预签
	 * @param yearMonth
	 * @param isVip
	 * @return
	 * @throws Exception
	 */
	public int getPlayerCheckTimes(int playerId ,int type,String yearMonth) throws Exception{
		String key = Constants.DAILY_CHECK_TIMES_PREFIX + playerId;
		return this.getCheckTimes(key, type, yearMonth);
	}

	/** 
	 * 更新玩家的预签和补签天数 
	 * 
	 * @param key 
	 * @param 0 补签 1 预签
	 * @param yearMonth 当前年月格式 20147(月份为：0-11)
	 * return 
	 * @throws Exception
	 * */
	private void updateCheckTimes(String key, int type,String yearMonth) throws Exception {
		String value = nosql.get(key);					//获得当前玩家的总签到补签次数，没有月份判定
		String[] values = { "0", "0", yearMonth };	
		if (value != null) {
			values = value.split(":");
		}
		//Vip玩家签到，判定跨月之后，初始化预签和补签天数为0
		//201407071742 OuYangGuang
		if(key !=null && key.indexOf(Constants.VIP_DAILY_CHECK_TIMES_PREFIX)>-1 && !values[2].equals(yearMonth)){		
			values[0]="0";	//补签次数
			values[1]="0";	//预签次数
		}
		int valueNum = Integer.parseInt(values[type]);
		int totalChance = type == 0 ? Constants.DAILY_CHECK_AGO_CHANCE : type == 1 ? Constants.DAILY_CHECK_FUTURE_CHANCE : 0;
		if (valueNum < totalChance) {
			values[type] = String.valueOf(valueNum + 1); //无论是否签到补签超过次数，都增加补签或预签次数
			value = values[0] + ":" + values[1] + ":" + yearMonth;
			nosql.set(key, value);
		}
	}
	
	/**
	 * 更新玩家的预签和补签天数（无论其是不是vip）
	 * 
	 * @param playerId
	 * @param type 0-补签； 1-预签
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
	 * @param type 0-补签； 1-预签
	 * @param yearMOnth
	 * @param isVip
	 * @throws Exception
	 */
	public void updateVipPlayerCheckTimes(int playerId, int type, String yearMonth)
			throws Exception {

		String key = Constants.VIP_DAILY_CHECK_TIMES_PREFIX + playerId;
		this.updateCheckTimes(key, type, yearMonth);
	}
	
	public void initCheckItemList(Integer playerId) throws Exception{
		String key = Constants.CHECK_ITEM_PREFIX + playerId  ;//key(CHECK_ITEM_PREFIX + playerId)
		List<String> itemList = new ArrayList<String>(0);
		nosql.set(key,itemList);
	}
	public List<SysItem> getCheckItemList(Integer playerId) throws TimeoutException, Exception{
		String key = Constants.CHECK_ITEM_PREFIX + playerId  ;//key(CHECK_ITEM_PREFIX + playerId)
		List<SysItem> itemList = new ArrayList<SysItem>(0);
//		SysItemDao sid = new SysItemDao();
//		for(String itemId : nosql.getQueue(key)){
//			itemList.add(sid.getSystemItemById(Integer.parseInt(itemId)));
//		}
		return itemList;
	}
	
	public void playerCheck(int playerId,String dateStr) throws Exception{
		String key = Constants.DAILY_CHECK_PREFIX + playerId  ;//key(DAILY_CHECK_PREFIX + playerId)
		if(!isPlayerCheck(playerId, dateStr))
		nosql.rpush(key, dateStr);
	}
	public void dailyGift(int playerId,String dateStr) throws Exception{
		String key = Constants.DAILY_GIFT_DATE_PREFIX + playerId  ;//key(DAILY_CHECK_PREFIX + playerId)
		String lastGiftDate = nosql.get(key);
		if(lastGiftDate==null||lastGiftDate.indexOf(dateStr)==-1)
			nosql.set(key, dateStr);
	}
	public int isDailyGift(int playerId,String dateStr) throws Exception{
		String key = Constants.DAILY_GIFT_DATE_PREFIX + playerId  ;//key(DAILY_CHECK_PREFIX + playerId)
		String lastGiftDate = nosql.get(key);
		if(lastGiftDate==null||lastGiftDate.indexOf(dateStr)==-1)
			return 0;
		return 1;
	}
	/**
	 * 
	 * @param playerId
	 * @param dateStr
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("static-access")
	public int isTeamSalary(int playerId) throws Exception{
		String key = Constants.TEAM_SALARY_DATE_PREFIX + playerId  ;//key(DAILY_CHECK_PREFIX + playerId)
		String lastGiftDate = nosql.get(key);
		int flag = 1;
		Calendar ca=Calendar.getInstance();
		int thisWeek=ca.get(Calendar.WEEK_OF_YEAR);
		int thisYear=ca.get(Calendar.YEAR);
		if(lastGiftDate==null){
			flag = 0;
		}else{
			ca.setTime(CommonUtil.dateFormatDate.parse(lastGiftDate));
			if(thisWeek!=ca.get(ca.WEEK_OF_YEAR)||thisYear!=ca.get(Calendar.YEAR)){
				nosql.delete(key);
				flag = 0;
			}
		}
		return flag;
	}
	/**
	 * 存储领取战队工资的时间
	 * @param playerId
	 * @param dateStr
	 * @throws Exception
	 */
	public void saveTeamSalary(int playerId,String dateStr) throws Exception{
		String key = Constants.TEAM_SALARY_DATE_PREFIX + playerId  ;//key(DAILY_CHECK_PREFIX + playerId)
		String lastGiftDate = nosql.get(key);
		if(lastGiftDate==null||lastGiftDate.indexOf(dateStr)==-1)
			nosql.set(key, dateStr);
	}
	public void dailyGusAward(int playerId,String date) throws Exception{
		String key = Constants.IS_DAILY_GUS_AWARD_PREFIX + playerId;
		nosql.set(key, date);
	}
	/**
	 * 玩家猜中数字，系统是否发送奖励 0：没有 1：已经发
	 * @param playerId
	 * @param date
	 * @return
	 * @throws Exception
	 */
	public  int getIsDailyGusAward(int playerId,String date) throws Exception{
		String key = Constants.IS_DAILY_GUS_AWARD_PREFIX + playerId;
		String dtr = nosql.get(key);
		if(dtr!=null&&dtr.equals(date))
			return 1;
		return 0;
	}
	
	//if player check today
	public boolean isPlayerCheck(int playerId,String dateStr) throws TimeoutException, Exception{
		List<String> checkList = getPlayerCheckList(playerId);
		return checkList.contains(dateStr);
	}
	public boolean isInitPlayerNum(int playerId,String dateStr) throws TimeoutException, Exception{
		String key = Constants.IS_INIT_PLAYER_NUM_PREFIX + playerId;
		String value = nosql.get(key);
		if(dateStr.equals(value)){
			return true;
		}
		return false;
	}
	public void setIsInitPlayerNum(int playerId,String dateStr) throws Exception{
		String key = Constants.IS_INIT_PLAYER_NUM_PREFIX + playerId;
		nosql.set(key, dateStr);
	}
	/**
	 * 判断本月是否初始化签到列表
	 * @param playerId
	 * @param date
	 * @return
	 * @throws TimeoutException
	 * @throws Exception
	 */
	public boolean isInitDailyCheckDays(int playerId,Calendar date) throws TimeoutException, Exception{
		List<String> checkList = getPlayerCheckList(playerId);
		if(checkList==null||checkList.size()==0){
			return true;
		}
		for(String checkDay : checkList){
		Date day = CommonUtil.dateFormatDate.parse(checkDay);
		Calendar cDay = Calendar.getInstance();
		cDay.setTime(day);
		if(date.get(Calendar.MONTH)!=cDay.get(Calendar.MONTH)){
			return false;
			}
		}
		return true;
	}
	/**
	 * 产生随机数（0-9），写入Redis
	 * @throws Exception
	 */
	 public int dailyRandomNum(String dateStr) throws Exception{
		String key = Constants.DAILY_RANDOM_NUM_KEY;
		if(!isDailyRandomNum(dateStr))
		{
		int randomNum = (int) (Math.random()*10);
		nosql.set(key,randomNum+":"+dateStr);
		}
		return getDailyRandomNum(dateStr);
	 }
	 
	 public int getDailyRandomNum(String dateStr) throws Exception{
		String key = Constants.DAILY_RANDOM_NUM_KEY;
		String value = nosql.get(key);
		if(value==null||value.startsWith("-1")||value.indexOf(dateStr)==-1)
			return -1;
		else
			return Integer.parseInt(value.substring(0,1));
	 }
	 /**
	  * 系统是否已经产生随机幸运数，false:没有，true:已经产生
	  * @param dateStr
	  * @return
	  * @throws Exception
	  */
	 public boolean isDailyRandomNum(String dateStr) throws Exception{
		String key = Constants.DAILY_RANDOM_NUM_KEY;
		String nowNumStr = nosql.get(key);
		if(nowNumStr==null||nowNumStr.indexOf(dateStr)==-1||nowNumStr.startsWith("-1"))
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
	 public void dailyGuessNum(int playerId, int num, String tomDateStr) throws Exception{
		String tomKey = Constants.DAILY_PLAYER_TOM_NUM_PREFIX + playerId;
		nosql.set(tomKey, num+":"+tomDateStr);
	 }
	 
	 /**
	  * 玩家猜数字（多个）
	  * 
	  * @param playerId
	  * @param num
	  * @param tomDateStr
	  * @throws Exception
	  */
	 public void dailyGuessNums(int playerId, String num, String tomDateStr) throws Exception{
		String tomKey = Constants.DAILY_PLAYER_TOM_NUM_PREFIX + playerId;
		nosql.set(tomKey, num+":"+tomDateStr);
	 }
	 /**
	  * 玩家当天第一次登陆，更新玩家所猜数，把玩家今天的数字替换为昨天猜的数字，把今天要猜的数字初始化为-1（表示玩家还没有猜）
	  * @param playerId
	  * @param dateStr
	  * @param tomDateStr
	  * @throws Exception
	  */
	 public void updatePlayerGuessNum(int playerId,String dateStr,String tomDateStr) throws Exception{
		 String todayKey = Constants.DAILY_PLAYER_NUM_PREFIX + playerId  ;
		 String tomKey = Constants.DAILY_PLAYER_TOM_NUM_PREFIX + playerId  ;
		 String tomNum = nosql.get(tomKey);
		 if(tomNum==null||tomNum.indexOf(dateStr)==-1)
			 nosql.set(todayKey,"-1:"+dateStr);
		 else 
			 nosql.set(todayKey,tomNum+":"+dateStr);
		 nosql.set(tomKey, "-1:" + tomDateStr);
	 }
	 public void deleteByKey(String key) throws Exception{
		 nosql.delete(key) ;
	 }
	 
	 /**
	  * 获得玩家所猜数字（非vip用户，只能猜一个数）
	  * 
	  * @param playerId
	  * @param type 1-playerDailyNum; 2-playerDailyTomorrowNum
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
			String value = nosql.get(key);
			if (value == null || value.isEmpty() || value.startsWith("-1") || !value.contains(dateStr))
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
	  * @param type 1-playerDailyNum; 2-playerDailyTomorrowNum
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
			String value = nosql.get(key);
			// 未猜过
			if (value == null || value.isEmpty() || value.startsWith("-1") || !value.contains(dateStr))
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
	  * 设置玩家牌子id List
	  * @param playerId
	  * @param brandIdList
	  * @throws Exception
	  */
	 public void setPlayerBrandIdList(int playerId,List<OnlineAward> brandList) throws Exception{
		 List<String> brdIdList = new ArrayList<String>();
		 StringBuilder brdIds = new StringBuilder();
		for(OnlineAward brd : brandList){
			brdIdList.add(String.valueOf(brd.getId()));
			brdIds.append(brd.getId() +":");
		}
		ServiceLocator.stageClearLog.debug("NosqlService/SetBrandIds:\t" + playerId + "\t"+ brdIds.toString());
		String playerBrandListKey = Constants.PLAYER_BRAND_ID_LIST_PREFIX + playerId ;
		nosql.delete(playerBrandListKey);
		nosql.set(playerBrandListKey,brdIdList);
	 }
	 /**
	  * 获得玩家牌子id List
	  * @param playerId
	  * @return
	  * @throws Exception
	  */
	 public List<String> getPlayerBrandIdList(int playerId) throws  Exception {
		 String playerBrandListKey = Constants.PLAYER_BRAND_ID_LIST_PREFIX + playerId;
		 return nosql.getQueue(playerBrandListKey);
		}
	 
	 public void deletePlayerBrandIdList(int playerId) throws Exception{
		 String playerBrandListKey = Constants.PLAYER_BRAND_ID_LIST_PREFIX + playerId;
		 nosql.delete(playerBrandListKey); 
	 }
	 /**
	  * 设置玩家获得不同机会
	  * @param playerId
	  * @param gameGetChance
	  * @param vipGetChance
	  * @param netBarGetChance
	  * @param activityGetChance
	  * @throws Exception
	  */
	 public void setPlayerGetChances(int playerId, int gameGetChance,int vipGetChance, int netBarGetChance, int activityGetChance) throws Exception {
			String key = Constants.STAGE_GET_CHANCE_PREFIX + playerId  ;
			nosql.delete(key);
			nosql.set(key, gameGetChance+":"+vipGetChance+":"+netBarGetChance+":"+activityGetChance);
		}
		
	 public String getPlayerGetChances(int playerId) throws  Exception{
	 	String key = Constants.STAGE_GET_CHANCE_PREFIX + playerId  ;
	 	return nosql.get(key);
	 }
	 
	 public void setPlayerGetExps(int playerId,int getTotalExp,int gameGetExp,int vipGetExp,int netBarGetExp,int activityGetExp,int itemAdd,int teamAdd) throws Exception{
		String key = Constants.STAGE_GET_EXP_PREFIX + playerId  ;
		nosql.delete(key);
		nosql.set(key, new StringBuilder().append(getTotalExp+":").append(gameGetExp+":").append(vipGetExp+":").append(netBarGetExp+":").append(activityGetExp+":").append(itemAdd+":").append(teamAdd+"").toString()); 
	 }
	 
	 public String getPlayerGetExps(int playerId) throws  Exception{
		 	String key = Constants.STAGE_GET_EXP_PREFIX + playerId  ;
		 	return nosql.get(key);
		 }
	 public void setPlayerGetGps(int playerId,int getTotalGp,int gameGetGp,int vipGetGp,int netBarGetGp,int activityGetGp,int itemAdd,int teamAdd) throws Exception{
			String key = Constants.STAGE_GET_GP_PREFIX + playerId  ;
			nosql.delete(key);
			nosql.set(key, new StringBuilder().append(getTotalGp+":").append(gameGetGp+":").append(vipGetGp+":").append(netBarGetGp+":").append(activityGetGp+":").append(itemAdd+":").append(teamAdd+"").toString()); 
		 }
		 
	 public String getPlayerGetGps(int playerId) throws  Exception{
			String key = Constants.STAGE_GET_GP_PREFIX + playerId  ;
			return nosql.get(key);
		}
	 public void setPlayerGetScore(int playerId,int getScore) throws Exception{
			String key = Constants.STAGE_GET_SCORE_PREFIX + playerId  ;
			nosql.delete(key);
			nosql.set(key, String.valueOf(getScore)); 
		 }
		 
	 public String getPlayerGetScore(int playerId) throws  Exception{
			String key = Constants.STAGE_GET_SCORE_PREFIX + playerId  ;
			return nosql.get(key);
		}
	 /**
	  * 添加玩家打开魔罐获得装备playerItem 的id
	  * @param playerItemId
	  * @throws Exception
	  */
	 public void addPlayerMagixBoxItemId(int playerId,int sysItemId,int quantity) throws Exception{
		 String key = Constants.MAGIC_BOX_ITEM_IDS_KEY; 
		 List<String> ids = getPlayerMagixBoxItemIds();
		 if(ids!=null&&ids.size()>=Constants.MAGIC_BOX_ITEM_NUM){
			ids.remove(0);
		 	ids.add(String.valueOf(playerId+":"+sysItemId+":"+quantity));
		 	nosql.delete(key);
		 	nosql.set(key, ids);
		 }else{
		 nosql.rpush(key, String.valueOf(playerId+":"+sysItemId+":"+quantity));
		 }
	 }
	 /**
	  * 记录玩家打开魔罐获得稀有物品时，玩家信息，物品ID，和数量
	  * @param player
	  * @param sysItemId
	  * @param quantity
	  * @throws Exception
	  */
	 public void addPlayerMagixBoxItemId(Player player,int sysItemId,int quantity) throws Exception{
		 String key = Constants.MAGIC_BOX_ITEM_IDS_KEY; 
		 List<String> ids = getPlayerMagixBoxItemIds();
		 if(ids!=null&&ids.size()>=Constants.MAGIC_BOX_ITEM_NUM){
			ids.add(String.valueOf(player.getName()+":"+player.getRank()+":"+player.getExp()+":"+sysItemId+":"+quantity));
		 	nosql.set(key, ids.subList(ids.size()-Constants.MAGIC_BOX_ITEM_NUM,ids.size()));
		 }else{
			 nosql.rpush(key, String.valueOf(player.getName()+":"+player.getRank()+":"+player.getExp()+":"+sysItemId+":"+quantity));
		 }
	 }
	 
	 /**
	  * 记录玩家打开资源魔罐获得稀有物品时，玩家信息，物品ID，和数量
	  * @param player
	  * @param sysItemId
	  * @param quantity
	  * @throws Exception
	  */
	 public void addPlayerResMagixBoxItemId(Player player,int sysItemId,int quantity) throws Exception{
		 String key = Constants.RES_MAGIC_BOX_ITEM_IDS_KEY; 
		 List<String> ids = getPlayerResMagixBoxItemIds();
		 if(ids!=null&&ids.size()>=Constants.RES_MAGIC_BOX_ITEM_NUM){
			ids.add(String.valueOf(player.getName()+":"+player.getRank()+":"+player.getExp()+":"+sysItemId+":"+quantity));
		 	nosql.set(key, ids.subList(ids.size()-Constants.RES_MAGIC_BOX_ITEM_NUM,ids.size()));
		 }else{
			 nosql.rpush(key, String.valueOf(player.getName()+":"+player.getRank()+":"+player.getExp()+":"+sysItemId+":"+quantity));
		 }
	 }
	 
	public List<String> getPlayerMagixBoxItemIds() throws  Exception{
		String key = Constants.MAGIC_BOX_ITEM_IDS_KEY;
		return nosql.getQueue(key);
	}
	
	public List<String> getPlayerResMagixBoxItemIds() throws  Exception{
		String key = Constants.RES_MAGIC_BOX_ITEM_IDS_KEY;
		return nosql.getQueue(key);
	}	
	/**
	 * 加入靶场奖励列表
	 * @param player
	 * @param oa
	 * @throws Exception
	 */
	public void addDartleAwardToTop(Integer playerId,Integer oaId) throws Exception{
		String dartleAwardTopRedisKey = Constants.DARTLE_AWARD_TOP_REDIS_KEY;
		String value = playerId+":"+oaId;
		nosql.addToQueue(dartleAwardTopRedisKey, value,Constants.DARTLE_TOP_NUM);
	}
	/**
	 * 靶场奖励列表
	 * @return
	 * @throws Exception
	 */
	public List<TmpPlayerItem> getDartleAwardsTopList() throws Exception{
		String dartleAwardTopRedisKey = Constants.DARTLE_AWARD_TOP_REDIS_KEY;
		List<String> dartleTopList = nosql.getQueue(dartleAwardTopRedisKey);
		List<TmpPlayerItem> retList = new ArrayList<TmpPlayerItem>();
		GetService getService = ServiceLocator.getService;
		for(String s : dartleTopList){
			String[] ids = s.split(":");
			if(ids.length==2){
				Player player = getService.getPlayerById(Integer.parseInt(ids[0]));
				OnlineAward oa = getService.getOnlineAwardById(Integer.parseInt(ids[1]));
				SysItem si = getService.getSysItemByItemId(oa.getItemId()).clone();
				TmpPlayerItem tmpPi = new TmpPlayerItem(player,si,oa.getUnit());
				retList.add(tmpPi);
			}else{
				logger.warn("DartleAwardTop ids error :\t"+s);
			}
		}
		return retList;
	}
	
	public void setVipRandomList(int playerId,String randomSysItemIds) throws Exception{
		String vipRandomListKey = Constants.VIP_RANDOM_VSC_LIST_PREFIX + playerId  ;
		nosql.set(vipRandomListKey, randomSysItemIds);
	}
	public String getVipRandomList(int playerId) throws Exception{
		String vipRandomListKey = Constants.VIP_RANDOM_VSC_LIST_PREFIX+ playerId  ;
		return nosql.get(vipRandomListKey);
	}
	public void setRandomStartIndexs(int playerId,String playerRandomIndexs) throws Exception{
		String vipRandomStartListKey = Constants.VIP_RANDOM_START_LIST_PREFIX + playerId  ;
		nosql.set(vipRandomStartListKey, playerRandomIndexs);
	}
	public String getRandomStartIndexs(int playerId) throws Exception{
		String vipRandomStartListKey = Constants.VIP_RANDOM_START_LIST_PREFIX + playerId  ;
		return nosql.get(vipRandomStartListKey);
	}
		public void updateOnlineAward(int playerId,Boolean isOn) throws Exception{
		String key = Constants.ONLINEAWARDSTATE+":"+playerId;
		if(isOn){
			nosql.set(key, Constants.ONLINE_AWARD_LEVEL_ONE+";"+Constants.FLAGE_OTHER+";"+Constants.LEVEL_IS_OUT_LINE);
		}else{
			String value=nosql.get(key);
			if(value!=null){
				String [] states=value.split(";");
				if(states.length!=3){
					nosql.set(key, Constants.ONLINE_AWARD_LEVEL_ONE+";"+Constants.FLAGE_OTHER+";"+Constants.LEVEL_IS_OUT_LINE);
				}else{
					Date now = new Date();
					int level 	 = Integer.parseInt(states[0]);
					long time	 = Long.parseLong(states[2]);
					if(level<=Constants.LEVEL_MINS.length && !states[2].equals(Constants.LEVEL_IS_OUT_LINE) && now.getTime() > time){
						nosql.set(key,level+";"+Constants.FLAG_NOT_GET_AWARD+";"+Constants.LEVEL_IS_OUT_LINE);
					}else nosql.set(key, level+";"+Constants.FLAGE_OTHER+";"+Constants.LEVEL_IS_OUT_LINE);
				}
			}
		}
	}
	/**
	 * 根据不同类型返回真实排名首尾数值的记录
	 * @param typeStr
	 * @param type 0：更新时间 ，1：首记录，2：尾记录
	 * @return String
	 * @throws Exception
	 */
	public String getLastRcdInfo(String typeStr,int type) throws Exception{
		 String key = NosqlKeyUtil.firstLastNumInTopRcdByType(typeStr);
		 String value = nosql.get(key);
		 return value==null?null:value.split(":")[type];
	 }
	/**
	 * 更新玩家今天翻牌成功次数
	 * @param playerId
	 * @param date
	 * @throws Exception 
	 */
	public void updatePlayerOpenBrandsNum(int playerId, Date date) throws Exception {
		String  key = Constants.STAGE_BRAND_OPEN_COUNT_PREFIX + playerId;
		String value = nosql.get(key);
		String dateStr = CommonUtil.dateFormatDate.format(date);
		String[] values = value==null?null:value.split(":");
		int count = values!=null&&values.length>0?Integer.parseInt(values[0]):0;
		String rDateStr = values!=null&&values.length>1?values[1]:null;
		String newValue = null;
		if(count==0||dateStr==null||!rDateStr.equals(dateStr)){
			newValue = "1:"+dateStr;
		}else{
			newValue = ++count +":" + dateStr;
		}
		nosql.set(key, newValue);
		
	}
	public int getOpenBrandsNum(int playerId, Date date) throws Exception{
		String  key = Constants.STAGE_BRAND_OPEN_COUNT_PREFIX + playerId;
		String value = nosql.get(key);
		String dateStr = CommonUtil.dateFormatDate.format(date);
		String[] values = value==null?null:value.split(":");
		int count = values!=null&&values.length>0?Integer.parseInt(values[0]):0;
		String rDateStr = values!=null&&values.length>1?values[1]:null;
		if(count==0||dateStr==null||!rDateStr.equals(dateStr)){
			return 0;
		}
		return count;
	}
	
	public String getOnlineAwardState(int playerId) throws Exception{
		String key = Constants.ONLINEAWARDSTATE+":"+playerId;
		String value = nosql.get(key);
		if(value==null){
			nosql.set(key, Constants.ONLINE_AWARD_LEVEL_ONE+";"+Constants.FLAGE_OTHER+";"+Constants.LEVEL_IS_OUT_LINE);
			return Constants.ONLINE_AWARD_LEVEL_ONE+";"+Constants.FLAGE_OTHER+";"+Constants.LEVEL_IS_OUT_LINE;
		}else{
			String [] states=value.split(";");
			if(states.length!=3){
				nosql.set(key, Constants.ONLINE_AWARD_LEVEL_ONE+";"+Constants.FLAGE_OTHER+";"+Constants.LEVEL_IS_OUT_LINE);
				return Constants.ONLINE_AWARD_LEVEL_ONE+";"+Constants.FLAGE_OTHER+";"+Constants.LEVEL_IS_OUT_LINE;
			}else return nosql.get(key);
		}
	}
	
	public void updateOnlineAwardState(int playerId,int level,String flag,long time)throws Exception{
		String key = Constants.ONLINEAWARDSTATE+":"+playerId;
		nosql.set(key, level+";"+flag+";"+time);
	}
	
	public byte initPlayerRemainVoterNum(int playerId,boolean isVip)throws Exception{
		byte playerRemainVoternum = isVip?Constants.PLAYER_VIP_REMAIN_VOTERNUM:Constants.PLAYER_NORMAL_REMAIN_VOTERNUM;
		setPlayerRemainVoterNum(playerId, playerRemainVoternum);
		return playerRemainVoternum;
	}
	
	public byte getPlayerRemainVoterNum(int playerId)throws Exception{
		String key = Constants.PLAYERREMAINVOTERNUM+":"+playerId;
		String strValue = nosql.get(key);
		if(strValue==null){
			Player player=ServiceLocator.getService.getPlayerById(playerId);
			CommonUtil.checkNull(player, ExceptionMessage.NO_HAVE_THE_CHARACTER);
			boolean isVip = player.getIsVip()>=1;
			initPlayerRemainVoterNum(playerId, isVip);
			byte playerRemainVoternum = isVip?Constants.PLAYER_VIP_REMAIN_VOTERNUM:Constants.PLAYER_NORMAL_REMAIN_VOTERNUM;
			return playerRemainVoternum;
		}
		return Byte.parseByte(strValue);
	}
	/**
	 * 获得玩家每日折扣某物品的购买标志
	 * @param playerId 玩家id
	 * @param sysItemId 物品id
	 * @return int
	 * @throws Exception
	 */
	public int getPlayerDailyDiscountBuyFlag(int playerId,int sysItemId) throws Exception{
		String key = Constants.PLAYER_DAILY_DISCOUNT_PREX + sysItemId;
		String value = nosql.hashGet(key, String.valueOf(playerId));
		if(value==null){
			return 0;
		}
		return StringUtil.toInt(value);
	}
	/**
	 * 更新玩家每日折扣标志
	 * @param playerId
	 * @param sysItemId
	 * @param index
	 * @throws Exception
	 */
	public void addPlayerDailyDiscountBuyFlag(int playerId,int sysItemId,int index) throws Exception{
		int flagNum = getPlayerDailyDiscountBuyFlag(playerId, sysItemId);
		nosql.hashSet(Constants.PLAYER_DAILY_DISCOUNT_PREX + sysItemId, String.valueOf(playerId), String.valueOf(flagNum|(int)Math.pow(2, index)));
	}
	/**
	 * 获得每日折扣某物品折扣数
	 * @param sysItemId
	 * @return
	 * @throws Exception
	 */
	public String[] getItemDailyDiscount(int sysItemId) throws Exception{
		String key = Constants.DAILY_DISCOUNT_SYSITEM_KEY;
		String value = nosql.hashGet(key, String.valueOf(sysItemId));
		if(value==null){
			return null;
		}
		return value.split("|");
	}
	public void setPlayerRemainVoterNum(int playerId,byte playerRemainVoternum)throws Exception{
		String key = Constants.PLAYERREMAINVOTERNUM+":"+playerId;
		//nosql.decr(key, decrNum);
		nosql.set(key, playerRemainVoternum+"");
	}
	
	public void initMeltingEnergy(int playerId){
		
	}
	
	
	/**
	 * 更新用户防沉迷时间
	 * @return
	 * @throws Exception 
	 */
	public void updateFCMTime(int playerId, int time) throws Exception{
		String key=NosqlKeyUtil.FCMTime(playerId);
		String value=nosql.get(key);
		// 若用户不属于防沉迷监控对象（即time一直为0），则不存时间
		if(time==0){
			if(value!=null && Integer.parseInt(value)>0){
				nosql.delete(key);
				nosql.set(key, String.valueOf(time));
			}else if(value!=null && Integer.parseInt(value)==0){
				nosql.delete(key);   //清空为0的
			}
		}else{
			if(value!=null){
				nosql.delete(key);
			}
			nosql.set(key, String.valueOf(time));
		}
			
		
	}
	
	public int getFCMTime(int playerId){
		int fcm_time=0;
		try {
			String fcm_time_value=getNosql().get(NosqlKeyUtil.FCMTime(playerId));
			
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
		return fcm_time;
	}
	
	
	/**
	 * 判断当前 行为是否能获得vip升级经验
	 * 
	 * @param type
	 *            0:签到,1:在线时长,2-4:每日任务(2 简单任务  3 正常任务  4困难任务),5:vip经验块;6:过关结算
	 * @param playerId
	 * @return
	 * @throws Exception 
	 */
	public int hasGetVipUpExp(int type,int playerId) throws Exception {
		//redis 实现
		if(getNosql().hashGet(Constants.VIP_UPGRADE_EXP_STATUS, String.valueOf(playerId))!=null){
			String status=getNosql().hashGet(Constants.VIP_UPGRADE_EXP_STATUS, String.valueOf(playerId));
			if(type>=status.length()){
				return -1;      //argument error
			}
			else if(type==Constants.VIP_EARN_EXP_METHODS.STAGECLEAR.getValue()){ //6位以后 16进制计算 过关结算次数
				return Integer.valueOf(status.substring(type,status.length()), 16); 
			}
			//char to int
			return status.charAt(type)-48;
		//	return Integer.valueOf(status.substring(type,type+1),16);
		}else{
			getNosql().hashSet(Constants.VIP_UPGRADE_EXP_STATUS, String.valueOf(playerId), Constants.VIP_UPGRADE_EXP_STATUS_INIT);
		}
	
//		if(mcc.get(CacheUtil.sVipDailyExpStatus())!=null){
//			Object object=mcc.get(CacheUtil.sVipDailyExpStatus());
//			
//		}
		return 0;
	}
	public void updateVipUpExpStatus(int type,int playerId,int currentStatus)throws Exception{
		String status=getNosql().hashGet(Constants.VIP_UPGRADE_EXP_STATUS, String.valueOf(playerId));
		if(type==Constants.VIP_EARN_EXP_METHODS.STAGECLEAR.getValue()){
			String newStageStatus=Integer.toHexString(currentStatus+1);
			if(newStageStatus.length()==1){
				String updatedStatus=status.substring(0,type)+"0"+Integer.toHexString(currentStatus+1);
				getNosql().hashSet(Constants.VIP_UPGRADE_EXP_STATUS, String.valueOf(playerId),updatedStatus);
			}else{
				String updatedStatus=status.substring(0,type)+Integer.toHexString(currentStatus+1);
				getNosql().hashSet(Constants.VIP_UPGRADE_EXP_STATUS, String.valueOf(playerId),updatedStatus);
			}	
		}else{
			String updatedStatus=StringUtil.replaceByIndex(status, type, Integer.toHexString(currentStatus+1).charAt(0));
			getNosql().hashSet(Constants.VIP_UPGRADE_EXP_STATUS, String.valueOf(playerId),updatedStatus);
		}		
	}
	public boolean isVipUpExpStatusFull(int type,int currentStatus){
		//have no simple method to use switch..case with enum instance
		if(type==Constants.VIP_EARN_EXP_METHODS.DAILYCHECK.getValue()){
			return currentStatus>=1;	// 签到 每天1次
		}else if(type==Constants.VIP_EARN_EXP_METHODS.ONLINETIME.getValue()){
			return currentStatus>=3;     //在线时长 每天领取3次
		}else if(type==Constants.VIP_EARN_EXP_METHODS.DAILYMISSIONSIMPLE.getValue()){
			return currentStatus>=2;	// 简单任务 每天2个
		}else if(type==Constants.VIP_EARN_EXP_METHODS.DAILYMISSIONNORMAL.getValue()){
			return currentStatus>=1;	// 正常任务 每天1个
		}else if(type==Constants.VIP_EARN_EXP_METHODS.DAILYMISSIONHARD.getValue()){
			return currentStatus>=1;	// 困难任务 每天1个
		}else if(type==Constants.VIP_EARN_EXP_METHODS.VIPEXPITEM.getValue()){
			return currentStatus>=1;	// vip经验块 每天1个
		}else if(type==Constants.VIP_EARN_EXP_METHODS.STAGECLEAR.getValue()){
			return currentStatus>=256;	// 过关结算
		}
		else{ 
			return true;
		}
		
	}
	
	public NoSql getNosql() {
		return nosql;
	}

	public void setNosql(NoSql nosql) {
		this.nosql = nosql;
	}
	public InforLogger getXunleiLogger() {
		return xunleiLogger;
	}
	public void setXunleiLogger(InforLogger xunleiLogger) {
		this.xunleiLogger = xunleiLogger;
	}
}
