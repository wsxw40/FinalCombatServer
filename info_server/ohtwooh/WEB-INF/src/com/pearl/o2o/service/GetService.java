package com.pearl.o2o.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeoutException;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.exception.MemcachedException;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;

import com.pearl.o2o.dao.CharacterDao;
import com.pearl.o2o.dao.CharacterLogDao;
import com.pearl.o2o.dao.FriendDao;
import com.pearl.o2o.dao.ItemOrderDao;
import com.pearl.o2o.dao.KeyWordsDao;
import com.pearl.o2o.dao.MessageDao;
import com.pearl.o2o.dao.PlayerDao;
import com.pearl.o2o.dao.PlayerItemDao;
import com.pearl.o2o.dao.PlayerPackDao;
import com.pearl.o2o.dao.PlayerTeamDao;
import com.pearl.o2o.dao.RankDao;
import com.pearl.o2o.dao.ServerDao;
import com.pearl.o2o.dao.SysItemDao;
import com.pearl.o2o.dao.SysItemLogDao;
import com.pearl.o2o.dao.SysLevelDao;
import com.pearl.o2o.dao.SysModeConfigDao;
import com.pearl.o2o.dao.SystemDao;
import com.pearl.o2o.dao.TeamDao;
import com.pearl.o2o.dao.TeamHistoryDao;
import com.pearl.o2o.dao.UserDao;
import com.pearl.o2o.pojo.Channel;
import com.pearl.o2o.pojo.Character;
import com.pearl.o2o.pojo.Friend;
import com.pearl.o2o.pojo.LevelInfoPojo;
import com.pearl.o2o.pojo.LevelModeInfo;
import com.pearl.o2o.pojo.LevelWeapon;
import com.pearl.o2o.pojo.Message;
import com.pearl.o2o.pojo.Player;
import com.pearl.o2o.pojo.PlayerItem;
import com.pearl.o2o.pojo.PlayerTeam;
import com.pearl.o2o.pojo.Rank;
import com.pearl.o2o.pojo.SDOItemOrderPojo;
import com.pearl.o2o.pojo.Server;
import com.pearl.o2o.pojo.SysItem;
import com.pearl.o2o.pojo.SysModeConfig;
import com.pearl.o2o.pojo.Team;
import com.pearl.o2o.pojo.TeamHistory;
import com.pearl.o2o.pojo.User;
import com.pearl.o2o.utils.CacheUtil;
import com.pearl.o2o.utils.CommonUtil;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.GoodsInfoConverter;
import com.pearl.o2o.utils.GoodsInfoConverterImpl;
import com.pearl.o2o.utils.IdComparatorClass;
import com.pearl.o2o.utils.StringUtil;
import com.snda.services.oa.client.bean.PlayerInfo;
import com.snda.services.oa.client.bean.SDOComponent;
import com.snda.services.oa.client.bean.SDOConstants;
import com.snda.services.oa.client.bean.SDOItemOrder;
import com.snda.services.oa.client.callback.UserInfoAuthenNotify;
/**
 * @author bobby
 *
 */
public class GetService {

	static Logger log = Logger.getLogger(GetService.class.getName());

	public int getPlayerTitle(int exp)throws Exception {
		Rank rank=rankDao.getRankTitle(exp);
		return rank.getId();
	}
	public int getPlayerExp(int id)throws Exception {
		Rank rank=rankDao.getRankExp(id);
		return rank.getExp();
	}
	//===============================================================================	
	//								  User Services
	//===============================================================================
	public User getUser(String userName) throws Exception {
		List<User> result = userDao.getUser(userName);
		if (result.size() > 0) {
			return  result.get(0);
		} else {
			return null;
		}
	}
	
	public User getUserById(int id){
		return userDao.getUserById(id);
	}
	public SysModeConfig getSysModeConfig(Integer type)throws Exception{
		SysModeConfig modeConfig=new SysModeConfig();
		modeConfig=sysModeConfigDao.getSysModeConfig(type);
		return modeConfig;
	}
	public String getConfig()throws Exception{
		SysModeConfig config=getSysModeConfig(3);
		return config.getConfig();
	}
	
	
	public int getCR(int userId) throws Exception{
		final User user = getUserById(userId);
		return (Integer) loadValue(CacheUtil.sUserCR(userId), new CacheMissHandler(){
			@Override
			public Object loadFromDB(GetService service) throws Exception {
				//TODO endpointIp
				return sdoComponent.getBalance(
						new PlayerInfo(user.getUserName(), user.getId(), 0,
								"127.0.0.1", SDOConstants.AREAID,
								SDOConstants.GROUPID),
						SDOItemOrder.PayType.TICKET);
			}
		},Constants.CACHE_SDO_CR_TIMEOUT);
	}
	
	//===============================================================================	
	//								  Charater Services
	//===============================================================================
	
	interface CacheMissHandler{
		Object loadFromDB(GetService service) throws Exception;
	}
	
	/**
	 * first, try to load value from cache, if it can not be found in cache, use handler to load it ,and put it into cache
	 * @param key
	 * @param handler
	 * @return
	 * @throws Exception
	 */
	public Object loadValue(String key, CacheMissHandler handler) throws Exception{
		return loadValue(key,handler,Constants.CACHE_ITEM_TIMEOUT);
	}
	
	public Object loadValue(String key, CacheMissHandler handler, int expr) throws Exception{
		Object result = null;
		try{
			 result = mcc.get(key);
		}catch(Exception e){	
			//do nothing
			//TODO: remove it later
			throw e;
		}
		if (result == null){ 
			result = handler.loadFromDB(this);
			if (result == null) {
				return null;
			}
			try{
				mcc.set(key, expr, result);
			}catch(Exception e){
				log.warn("Fail to put object into cache with key: " + key );
				//TODO: remove it later
				throw e;
			}
		}
		return result;
	}
	
	
	/**
	 * method to get default character for create player
	 * @param isDefault
	 * @return List<Character>
	 * @throws Exception
	 */
	public List<Character> getCharacterList(String isDefault)throws Exception{
		List<Character> charaterList = new ArrayList<Character>();
		charaterList = mcc.get(CacheUtil.oCharacter());
		if (charaterList==null) {
			charaterList=characterDao.getCharacterList(isDefault);
			for(Character character:charaterList){
				character.putOnCostume();
			}
			mcc.set(CacheUtil.oCharacter(), Constants.CACHE_ITEM_TIMEOUT, charaterList);
		}
		return charaterList;
	
	}
	//for gm sevice
	/**
	 * method to get all character for GM
	 * @return List<Character>
	 * @throws Exception
	 */
	public List<Character> getCharacterList()throws Exception{
		List<Character> charaterList = new ArrayList<Character>();
			charaterList=characterDao.getCharacterList();
			for(Character character:charaterList){
				character.putOnCostume();
			}
//			mcc.set(CacheUtil.oCharacter(), Constants.CACHE_ITEM_TIMEOUT, charaterList);
		return charaterList;
	}
	
	/**
	 * method to get one character
	 * @param id
	 * @param isDefault
	 * @return Character
	 * @throws Exception
	 */
	public Character getCharacter(Integer id,String isDefault)throws Exception{
		Character returnValue=null;
		List<Character> charaterList = getCharacterList(isDefault);
		for(Character character:charaterList){
			if(id.equals(character.getId())){
				returnValue=character;
			}
		}
		return returnValue;
	}
	public int getLogVersionFromCharacterLog(Integer characterId)throws Exception{
		return characterLogDao.getCharacterLogVersion(characterId);
	}
	public List<Character> getCharacterLog(Integer characterId)throws Exception{
		return	 characterLogDao.getCharacterLogList(characterId);
	}
	
	//===============================================================================	
	//								  Team Services
	//===============================================================================
	public Team getTeam(Integer teamId)throws Exception{
		Team team=teamDao.getTeamById(teamId);
		return team;
	}
	public Team getTeamByPlayerId(Integer playerId)throws Exception{
		return teamDao.getTeamByPlayerId(playerId);
	}
	public int fuzzyCountTeam(String name)throws Exception{
		return teamDao.fuzzyCountTeam(name);
	}
	
	public List<Team> getTeamsByIds(Set<Integer> playerIds){
		return teamDao.getTeamByIds(playerIds);
	}
	public List<Team> getTeamsByName(String name)throws Exception{
		return (List<Team>)teamDao.getTeamByName(name);
	}
	public List<TeamHistory> getTeamHistoryById(Integer id)throws Exception{
		return teamHistoryDao.getTeamHistoryById(id);
	}
	
	public List<PlayerTeam> getUnapprovedMember(int teamId){
		return playerTeamDao.getUnapprovedMember(teamId);
	}
	
	public PlayerTeam getPlayerTeamByPlayerId(int playerId){
		return playerTeamDao.getByPlayerId(playerId);
	}
	
	//===============================================================================	
	//								  Player Services
	//===============================================================================
	public Player getPlayerById(final Integer playerId) throws Exception  {
		String objKey = CacheUtil.oPlayer(playerId);
		Player result = (Player) loadValue(objKey, new CacheMissHandler() {
			@Override
			public Object loadFromDB(GetService service) {
				Player player=playerDao.getPlayerById(playerId);
				return player;
			}
		});
		return result;
	}
	public Player getPlayerById(Integer userId,Integer playerId) throws Exception  {
		return playerDao.getPlayer(userId, playerId);
	}
	
	public List<Player> getPlayersByIds(List<Integer> ids){
		return playerDao.getPlayersByIds(ids);
	}
	
	public Player getPlayerRankById(final Integer playerId) throws Exception{
		Player player = playerDao.getPlayerRankById(playerId);
		return player;
	}
	
	public Player getPlayerByName(String name){
		return playerDao.getPlayerByName(name);
	}
	
	public List<Player> getPlayerByUserId(final Integer userId) throws Exception {
		// Get ID for memcached
		String objKey=CacheUtil.oPlayerList(userId);
		List<Player> result = (List<Player>) loadValue(objKey, new CacheMissHandler() {
			@Override
			public Object loadFromDB(GetService service) {
				return playerDao.getPlayer(userId);
			}
		});
		return result;
	}	
	
	/**
	 * method to check duplication player name
	 * @param name
	 * @return Integer 
	 * @throws Exception
	 */
	public Integer fuzzyCountPlayer(String name) throws Exception {
		return playerDao.fuzzyCountPlayer(name);
	}	
	
	/**
	 * method to fuzzy search player
	 * @param name
	 * @param page
	 * @return List<Player>
	 * @throws Exception
	 */
	public List<Player> fuzzyGetPlayerByName(String name, Integer page) throws Exception {
		return playerDao.fuzzyGetPlayer(name, Constants.FRIEND_PAGE_SIZE * (page - 1), Constants.FRIEND_PAGE_SIZE);
	}
	//for mxml
	public List<Player> getPlayerList() throws Exception{
		List<Player> list=new ArrayList<Player>();
		list=playerDao.getPlayerList();
		return list;
	}
	
	//===============================================================================	
	//								  Friend Services
	//===============================================================================
	@SuppressWarnings("unchecked")
	public List<Friend> getFriendList(Integer userId, Integer playerId, String isBlackList) throws Exception {
		return friendDao.getFriend(userId, playerId, null, isBlackList);
	}	
	@SuppressWarnings("unchecked")
	public List<Friend> getFriendListForMsg(Integer userId, Integer playerId) throws Exception {
		return friendDao.getFriendForMsg(userId, playerId);
	}
	
	public String getClientVersion(){
		//TODO remove hard code
		return systemDao.getValue("client.version").trim();
	}
	
	
	//===============================================================================	
	//								  Server Services
	//===============================================================================	
	public Map<Integer, Server> getServerMap()throws Exception{
		String objKey =  Constants.GET_SERVER_LIST;
		Map<Integer, Server> serverMap = null;
		try{
			serverMap = mcc.get(objKey);
			if (serverMap == null){ 
				serverMap = serverDao.getServerMap();
				mcc.set(objKey, Constants.CACHE_ITEM_TIMEOUT, serverMap);
			}
		}catch(Exception e){	
			throw e;
		}
		return 	 serverMap;
	}
	@SuppressWarnings("unchecked")
	public String getServerList() throws Exception {
		Map<Integer, Server> serverMap = getServerMap();
		return 	 Converter.serverList(getListFromMap(serverMap));
	}
	@SuppressWarnings("unchecked")
	public Server getServer(int serverId) throws Exception {
		Map<Integer, Server> serverMap = getServerMap();
		Server server=serverMap.get(serverId);
		return 	 server;
	}
	@SuppressWarnings("unchecked")
	public Channel getChannel(int channelId) throws Exception {
		Map<Integer, Server> serverMap = getServerMap();
		Channel c=null;
		for(Integer t : serverMap.keySet()){
			Server s=serverMap.get(t);
			if(s!=null && s.getChannelMap()!=null){
			 c=s.getChannelMap().get(channelId);
			}
			if(c!=null){
				break;
			}
		}
		return 	 c;
	}
	//===============================================================================	
	//								  Rank Services
	//===============================================================================	
	@SuppressWarnings("unchecked")
	public String getRankList() throws Exception {
		List<Rank> rankList= rankDao.getRankList();
		return 	 Converter.rankList(rankList);
	}
	
	public List<Rank> getRanks(){
		return rankDao.getRankList();
	}
	
	public Rank getRankByExp(int exp, List<Rank> rankList){
		List<Rank> list = new ArrayList<Rank>(rankList);
		
		Collections.sort(list, new Comparator<Rank>(){
			@Override
			public int compare(Rank o1, Rank o2) {
				return o1.getExp().compareTo(o2.getExp());
			}
		});
		for(int i=1;i<list.size();i++){
			if (exp < list.get(i).getExp()) {
				return list.get(i-1);
			}
		}
		//run here, return the biggest rank 
		return list.get(list.size()-1);
	}
	
	public Rank getRankByExp(int exp){
		List<Rank> rankList= rankDao.getRankList();
		return getRankByExp(exp, rankList);
	}
	
	
	//===============================================================================	
	//								  Key Word Services
	//===============================================================================	
	@SuppressWarnings("unchecked")
	public String getKeyWords() throws Exception {
		return keyWordsDao.getKeyWords();
	}
	//===============================================================================	
	//								  Channel Services
	//===============================================================================	
	@SuppressWarnings("unchecked")
	public String getChannelList(int serverId) throws Exception {
		StringBuffer mid = new StringBuffer(Constants.GET_SERVER_LIST);
		String returnValue = "";
		List channelList = new ArrayList<Channel>();

		try {
			
			Map<Integer, Server> serverMap = (HashMap<Integer, Server>) mcc
					.get(mid.toString());
			if (serverMap != null && serverMap.size() != 0) {
				Server server = (Server) serverMap.get(serverId);
				if (server != null) {
					Map channelMap=new HashMap<Integer, Channel>();
					channelMap=server.getChannelMap();
					if(channelMap==null){
					channelMap=serverDao.getChannelMap(serverId);
					server.setChannelMap(channelMap);
					mcc.set(mid.toString(), Constants.CACHE_ITEM_TIMEOUT, serverMap);
					}
					
					channelList = getListFromMap(server.getChannelMap());
				}
			}
			returnValue = Converter.channelList(channelList);
		} catch (Exception e) {
			log.error("Exception in getChannelList: ", e);
			if (e instanceof MemcachedException
					|| e instanceof TimeoutException) {
				log.error("Exception in getChannelList: ", e);
				returnValue = Converter.channelList(channelList);
			}
		}
		return returnValue;
	}

	
	


	private List getListFromMap(Map map) {
		List list;
		if (map == null) {
			list = new ArrayList();
		} else {
			list = new ArrayList(map.values());
		}
		if (list.size() >= 2) {
			Collections.sort(list, new IdComparatorClass());
		}
		return list;
	}



	//===============================================================================	
	//								  SysItem Services
	//===============================================================================
//	public String getSysItemToolTip(int type, int subType, int itemId) throws Exception{
//		SysItem sysItem=getSysItemByItemId(type, subType, itemId);
//		List<SysItem> packages=new ArrayList<SysItem>();
//		if(type==Constants.DEFAULT_PACKAGE_TYPE){
//			packages=sysItem.getPackages();
//		}
//		
//			return	Converter.sysItemTooltip(sysItem, prices,packages);
//	}
	/**
	 * method to construction SysItem datas
	 * @param itemList
	 * @param type
	 * @param subType
	 * @return ArrayList<SysItem>[]
	 */
	private ArrayList<SysItem>[] constructionData(List<SysItem> itemList,int type, int subType) {
		ArrayList<SysItem> result[] = null;
		ArrayList<SysItem> tmpList = new ArrayList<SysItem>();
		// if memory not find get from database

		int pages = CommonUtil.getListPages(itemList,
				Constants.DEFAULT_PAGE_SIZE);
		tmpList = new ArrayList<SysItem>(Constants.DEFAULT_PAGE_SIZE);
		ArrayList<SysItem> tList = new ArrayList<SysItem>();
		ArrayList<SysItem> pList = new ArrayList<SysItem>();
		for (SysItem si : itemList) {
			si.init();// init
			if (si.getType().equals(Constants.DEFAULT_PACKAGE_TYPE)) {
				List<SysItem> list = sysItemDao.getAllPackgeItem(si.getId());
				si.setPackages(list);
			}
			if (type == Constants.DEFAULT_COSTUME_TYPE
					&& subType != Constants.DEFAULT_FACE_SUBTYPE) {

				if (si.getCSide().equals(Constants.NUM_ZERO)) {
					pList.add(si);
				} else {
					tList.add(si);
				}
			} else {
				if (result == null) {
					result = new ArrayList[1];
				}
				if (tmpList.size() != Constants.DEFAULT_PAGE_SIZE) {
					tmpList.add(si);
				} else {
					int pagePointer = result.length;
					ArrayList tmpArray[] = new ArrayList[pagePointer + 1];
					System.arraycopy(result, 0, tmpArray, 0, pagePointer);
					tmpList = new ArrayList<SysItem>(
							Constants.DEFAULT_PAGE_SIZE);
					tmpList.add(si);
					tmpArray[pagePointer] = tmpList;
					result = tmpArray;
				}
			}
		}
		if (type == Constants.DEFAULT_COSTUME_TYPE
				&& subType != Constants.DEFAULT_FACE_SUBTYPE) {
			//sort costume
			result = new ArrayList[pages];
			for (int j = 0; j < pages; j++) {
				result[j] = new ArrayList<SysItem>();
				for (int k = 0; k < 4; k++) {
					if (pList.size() > 0) {
						result[j].add(pList.get(0));
						pList.remove(0);
					}
					if (tList.size() > 0) {
						result[j].add(tList.get(0));
						tList.remove(0);
					}
				}
			}
		}
		if (result != null && result.length != 0 && result[0] == null) {
			result[0] = tmpList;
		}
		return result;
	}
	/**
	 * method to get SysItem by type,subtype
	 * @param userId
	 * @param playerId
	 * @param type
	 * @param subType
	 * @param gender
	 * @param filter
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<SysItem>[] getSysItem(int userId,int playerId,int type,int subType,String gender,int filter) throws Exception {
		ArrayList<SysItem> result[]=null;
		List<SysItem> itemList=new ArrayList<SysItem>();
		String objKey=CacheUtil.oShop(type,subType,gender);
		if(type == Constants.ITEM_TYPE_PART && filter == Constants.NUM_ONE){
			 objKey=CacheUtil.oShopPart(playerId,subType);
			result = mcc.get(objKey);
			if (result == null) {
				itemList = sysItemDao.getSuitableParts(userId, playerId,type,subType);
				result=constructionData(itemList, type, subType);
				//cache in memory
				if(result!=null){
					mcc.set(objKey, Constants.CACHE_ITEM_TIMEOUT, result);
				}
			}
		}else{
			result = mcc.get(objKey);
			if (result == null) {
				itemList = sysItemDao.getSystemItemList(type,subType,gender);
				result=constructionData(itemList, type, subType);
				//cache in memory
				if(result!=null){
					mcc.set(objKey, Constants.CACHE_ITEM_TIMEOUT, result);
				}
			}
		}
		return result;
	}
	
	/**
	 * @param userId
	 * @param playerId
	 * @param type
	 * @param gender
	 * @param filter
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<SysItem> getSysItemListByType(int userId,int playerId,int type, String gender,int filter) throws Exception{
		ArrayList<SysItem> array[] = null;
		List<SysItem> sysItemList = new ArrayList<SysItem>();
		String objkey;
		if (type < Constants.NUM_ONE || type > Constants.TYPE_NUM ) {
			log.warn("type or subtype not correct.");
		} else {
			int subtypeNum=Constants.SUBTYPE_ARRAY[type-1];
			for(int i=0;i<subtypeNum;i++){
				if(type == Constants.ITEM_TYPE_PART && filter == Constants.NUM_ONE){
					objkey=CacheUtil.oShopPart(playerId,i+1);
				}else{
					objkey=CacheUtil.oShop(type,i+1,gender);
				}
				array = mcc.get(objkey);
				if (array == null) {
					array=getSysItem(userId,playerId,type,i+1,gender,filter);
				}
				if(array!=null){
					for(int j=0;j<array.length;j++){
						ArrayList<SysItem> list=array[j];
						if(list!=null){
							sysItemList.addAll(list);
						}
					}
				}
			}
		}
		return sysItemList;
	}
	
	//return List<SysItem> 
	//for mxml remote object
	/**
	 * method to get sysitem List by type and subtype 
	 * @param type
	 * @param subType
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<SysItem> getSysItemList(int type, int subType)throws Exception{
	
		List<SysItem> sysItemList = new ArrayList<SysItem>();
		if (type < Constants.NUM_ONE || type > Constants.TYPE_NUM || subType < Constants.NUM_ONE || subType > Constants.SUB_TYPE_NUM ) {
			log.warn("type or subtype not correct.");
		} else {
			sysItemList=sysItemDao.getSystemItemList(type,subType);
		}
		return sysItemList;
	}
	/**
	 * method to get sysitem List by type
	 * @param type
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<SysItem> getSysItemList(int type)throws Exception{
	
		List<SysItem> sysItemList = new ArrayList<SysItem>();
		if (type < Constants.NUM_ONE || type > Constants.TYPE_NUM  ) {
			log.warn("type or subtype not correct.");
		} else {
			sysItemList=sysItemDao.getSystemItemList(type);
		}
		return sysItemList;
	}
	
	
	//return one sysitem by Id
	/**
	 * method to get one sysitem for GM
	 * @param userId
	 * @param playerId
	 * @param type
	 * @param subType
	 * @param itemId
	 * @return SysItem
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public SysItem getSysItemByItemId(int userId,int playerId,int type, int subType,int itemId)throws Exception{
		SysItem sysItem=null;
		ArrayList<SysItem> result[] = null;
		result=getSysItem(userId,playerId,type,subType,Constants.GENDER_BOTH,Constants.NUM_ZERO);
		int pages = 0;
		if (result != null) {
			pages = result.length;
		}
		for(int i=0;i<pages;i++){
			ArrayList<SysItem> itemList=result[i];
			for(SysItem item:itemList){
				if(item.getId().equals(itemId)){
					sysItem=item;
					break;
				}
			}
		}
		return sysItem;
	}
	
	public Integer countSysItemSuitable(Integer id, Integer type, Integer subtype) throws Exception {
		return sysItemDao.countSystemItemSuitable(id, type, subtype);
	}
	
	public List<SysItem> getSysItemSuitable(Integer id, Integer page, Integer type, Integer subtype) throws Exception {
		return sysItemDao.getSystemItemSuitable(id, Constants.DEFAULT_PAGE_SIZE * (page - 1), Constants.DEFAULT_PAGE_SIZE, type, subtype);	
	}
	
	
	public int getTicketBalance(PlayerInfo playerInfo) throws Exception{
		int SUCCESS = 0;
		UserInfoAuthenNotify resp = sdoComponent.sendUserInfoAuthenRequest(playerInfo);
		if (resp.getResult() == SUCCESS) {
			return resp.getBalance(SDOItemOrder.PayType.TICKET.getCode());
		}else {
			throw new Exception("fail to get user authen ");
		}
	}
	
	
	
	//return one sysitem by name
	public SysItem getSysItemByItemName(int userId,int playerId,int type, int subType,String itemName)throws Exception{
		SysItem sysItem=new SysItem();
		ArrayList<SysItem> result[] = null;
		result=getSysItem(userId,playerId,type,subType,Constants.GENDER_BOTH,Constants.NUM_ZERO);
		int pages = 0;
		if (result != null) {
			pages = result.length;
		}
		for(int i=0;i<pages;i++){
			ArrayList<SysItem> itemList=result[i];
			for(SysItem item:itemList){
				if(itemName.equals(item.getName())){
					sysItem=item;
					break;
				}
			}
		}
		return sysItem;
	}

//	public SysItem getWeaponByPart(int type, int subType, int itemId)throws Exception {
//		SysItem part=getSysItemByItemId(type, subType, itemId);
//		String str=part.getPSuitable();
//		String pSuit=null;
//		if(str!=null){
//			String temp[]=str.split(Constants.DELIMITER_RESOURCE_STABLE);
//			pSuit=temp[0];
//		}
//		SysItem sysItem=sysItemDao.getWeaponByPartName(pSuit);
//		List<SysItem> parts=new ArrayList<SysItem>();
//		parts.add(part);
//		sysItem.setParts(parts);
//		sysItem.putOnPart();
//		return sysItem;
//	}
	
	//===============================================================================	
	//								  SysItem Services
	//===============================================================================
	public int getLogVersionFromSysItemLog(int itemId)throws Exception{
		return sysItemLogDao.getSysItemLogVersion(itemId);
	}
	public List<SysItem> getSysItemLog(int itemId)throws Exception{
		return	 sysItemLogDao.getSysItemLog(itemId);
	}

	//===============================================================================	
	//								  Player Item Services
	//===============================================================================
	/**
	 * method to get parts for weapon
	 * @param playerId
	 * @param list
	 * @return
	 */
	public  List<PlayerItem> getPartsForPlayerItem(Integer playerId,List<PlayerItem> list){

		//获取可改装的武器ID
		List<Integer> weaponIds=new ArrayList<Integer>();
		for(PlayerItem pi : list){
			if(StringUtil.isCanEquip(pi.getModifiedDesc())){
				weaponIds.add(pi.getId());
			}
		}
		//一次性得到player所有的已改装配件
		List<PlayerItem> parts=playerItemDao.getPlayerItemParts( playerId, weaponIds);
		Map<Integer, List<PlayerItem>> tempMap=new HashMap<Integer, List<PlayerItem>>();
		for(PlayerItem pi : parts){
			if(tempMap.get(pi.getParentItemId())==null){
			tempMap.put(pi.getParentItemId(),new ArrayList<PlayerItem>());
			}
			(tempMap.get(pi.getParentItemId())).add(pi);
		}
		//武器配件关联起来
		for(PlayerItem pi : list){
			pi.setParts(tempMap.get(pi.getId()));
			if(pi.getParts()!=null&&pi.getParts().size()!=0){
				pi.putOnPart();
			}else if(pi.getId()!=null){
				pi.init();
			}
		}
	   return list;
	}
	/**
	 * method to get player item by type
	 * @param userId
	 * @param playerId
	 * @param type
	 * @return List<PlayerItem>[][]
	 * @throws Exception
	 */
	public List<PlayerItem>[][] getPlayerItems_O(Integer userId,Integer playerId, int type)throws Exception {
		String objKey=CacheUtil.oStorage(playerId,type);
		List<PlayerItem> itemArray[][] = null;
		ArrayList<PlayerItem> returnArray[]=null;
		List<PlayerItem> itemList = new ArrayList<PlayerItem>(
				Constants.DEFAULT_PAGE_SIZE);
		int pages = 0;

		if (type < Constants.NUM_ONE || type > Constants.TYPE_NUM ) {
			log.warn("type or subtype not correct in getPlayerItems.");
		} else {
			// 1. Get Player Item from cache
			itemArray = mcc.get(objKey);
			// 2. If cache miss, get data from database and construct into
			if (itemArray == null) {
				List<PlayerItem> list = playerItemDao.getPlayerItemByPlayerId(userId, playerId,type);
				//init parts while is weapon
				if(type==Constants.DEFAULT_WEAPON_TYPE){
					list=getPartsForPlayerItem(playerId, list);
				}
				
				itemArray = new ArrayList[Constants.TYPE_NUM][];
				ArrayList<PlayerItem> tmpList;
				for (PlayerItem pi : list) {
					if(type==Constants.DEFAULT_WEAPON_TYPE&&pi.getSubType()==Constants.DEFAULT_KNIFE_SUBTYPE&&Constants.BOOLEAN_YES.equals(pi.getIsDefault())){
						//exclude knife
					}else{
						if (pi.getPack() == null) {
							pi.setPack(0);// set pi not in any pack
						}
						if(type==Constants.DEFAULT_WEAPON_TYPE&&pi.getParts()!=null&&pi.getParts().size()!=0){
//							pi.putOnPart();
						}else{
							pi.init();
						}
						tmpList = new ArrayList<PlayerItem>(Constants.DEFAULT_PAGE_SIZE);
						int t = pi.getType() - 1;
						// init the array on first time
						if (itemArray[t] == null) {
							itemArray[t] = new ArrayList[1];
							itemArray[t][0] = tmpList;
						}
						int pg = itemArray[t].length - 1;
						// If less than 8 items in array, add item
						if (itemArray[t][pg].size() < Constants.DEFAULT_PAGE_SIZE) {
							itemArray[t][pg].add(pi);
						}
						// 8 item
						else {
							int pagePointer = itemArray[t].length;
							ArrayList tmpArray[] = new ArrayList[pagePointer + 1];
							System.arraycopy(itemArray[t], 0, tmpArray, 0,
									pagePointer);
	
							tmpList = new ArrayList<PlayerItem>(
									Constants.DEFAULT_PAGE_SIZE);
							tmpList.add(pi);
							tmpArray[pagePointer] = tmpList;
							itemArray[t] = tmpArray;
						}
						
					}
				}
				if(itemArray!=null){
						// 3. Set 2-dimension array into cache
						mcc.set(objKey, Constants.CACHE_ITEM_TIMEOUT,
							itemArray);
				}
			}
				
		}
		return itemArray;
	}
	public SysItem getSysItemByItemId(int itemId) throws Exception{
		SysItem sysItem = sysItemDao.getSystemItemById(itemId);//getSysItem();
		return sysItem;
	}
	public Set<SysItem> getSysItemByItemIds(Set<String> itemIds) throws Exception{
		List<SysItem> sysItems = sysItemDao.getAllSystemItem();//getSysItem();
		Set<SysItem> result = new HashSet<SysItem>();
		
		for (SysItem item : sysItems) {
			if (itemIds.contains(String.valueOf(item.getId()))) {
				result.add(item);
			}
		}
				
		return result;
	}
	
	
	
	//get PlayerItem by ID
	/**
	 * method to get one player item by id
	 * @param userId
	 * @param playerId
	 * @param type
	 * @param itemId
	 * @return
	 * @throws Exception
	 */
	public PlayerItem getPlayerItemByItemId(Integer userId,Integer playerId, int type,int itemId)throws Exception{
		List<PlayerItem>[][] itemArray=new ArrayList[Constants.TYPE_NUM][];
		//1.get itemArray
		itemArray=getPlayerItems_O(userId, playerId, type);
		//2.get pages
		List<PlayerItem> pageArray[] = itemArray[type - 1];
		PlayerItem playerItem=null;
		int pages = 0;
		
		if (pageArray != null) {
			pages = pageArray.length;
		}
		//3.get playerItem
		for(int i=0;i<pages;i++){
			List<PlayerItem> itemList=pageArray[i];
			for(PlayerItem item:itemList){
				if(item.getId()==itemId){
					playerItem=item;
					break;
				}
			}
		}
		if(playerItem!=null&&type==Constants.DEFAULT_WEAPON_TYPE&&playerItem.getParts()!=null&&playerItem.getParts().size()!=0){
//			playerItem.putOnPart();
		}
		return playerItem;
	}
	
	/**
	 * method to get item List via pack seq  
	 * @param userId
	 * @param playerId
	 * @param type:pack type
	 * @param packId
	 * @param seq
	 * @return List<PlayerItem> and the packed item first
	 * @throws Exception
	 */
	public List<PlayerItem> getItemPackList(Integer userId,Integer playerId,Integer type,int packId,int seq)throws Exception{
		List<PlayerItem> returnList=new ArrayList<PlayerItem>();
		List<PlayerItem>[][] itemArray=new ArrayList[Constants.TYPE_NUM][];
		List<PlayerItem>[][] setArray=new ArrayList[Constants.TYPE_NUM][];
		//1.get itemArray
		itemArray=getPlayerItems_O(userId, playerId, type);
		Player player=mcc.get(CacheUtil.oPlayer(playerId));
		if(player==null){
			player=getPlayerById(playerId);
		}
		List<PlayerItem> pageArray[] = itemArray[type - 1];
		PlayerItem playerItem=null;
		int pages = 0;
		if (pageArray != null) {
			pages = pageArray.length;
		}
		List<PlayerItem> tmpList=new ArrayList<PlayerItem>();
		//3.get playerItem
		for(int i=0;i<pages;i++){
			List<PlayerItem> itemList=pageArray[i];
			for(PlayerItem item:itemList){
				if(type==Constants.DEFAULT_WEAPON_TYPE){
					if(CommonUtil.getWeaponSeq(item.getWId())==seq&&item.getPack()==packId){
						playerItem=item;
					}
					if(CommonUtil.getWeaponSeq(item.getWId())==seq&&item.getPack()==Constants.NUM_ZERO&&item.getLevel()<=player.getRank()){
						tmpList.add(item);
					}
				}else {
					if(CommonUtil.getCotumeSeq(item.getSubType())==seq&&item.getCSide()==packId&&item.getPack()==Constants.NUM_ONE){
						playerItem=item;
					}
					else if(item.getSeq()==seq&&item.getSubType()==Constants.DEFAULT_COSTUME_SET_SUBTYPE&&item.getCSide()==packId&&item.getPack()==Constants.NUM_ONE){
						playerItem=item;
					}
					if(CommonUtil.getCotumeSeq(item.getSubType())==seq&&item.getCSide()==packId&&item.getPack()==Constants.NUM_ZERO&&item.getLevel()<=player.getRank()){
						tmpList.add(item);
					}else if(item.getSubType()==Constants.DEFAULT_COSTUME_SET_SUBTYPE&&item.getCSide()==packId&&item.getPack()==Constants.NUM_ZERO&&item.getLevel()<=player.getRank()){
						tmpList.add(item);
					}
				}
				
			}
		}
		if(playerItem!=null){
			returnList.add(playerItem);
		}
//		if(returnList.size()==0){
//			returnList.addAll(tmpList.subList(0, Constants.DEFAULT_PAGE_SIZE));
//		}else{
//			returnList.addAll(tmpList.subList(0, Constants.DEFAULT_PAGE_SIZE-1));
//		}
		returnList.addAll(tmpList);
		return returnList;
	}
	//get data from database
	public PlayerItem getPlayerItemByPlayerItemId(Integer userId,Integer playerId, int itemId)throws Exception{
		 	
		return (PlayerItem)playerItemDao.getPlayerItemByPlayerItemId(userId, playerId, itemId);
		
	}
	//get data from database
	public List<PlayerItem> getPlayerItemForTeam(Integer userId,Integer playerId, int type,int subType,int iId)throws Exception{
		 	
		return (List<PlayerItem>)playerItemDao.getPlayerItemForTeam(userId, playerId, type, subType, iId);
		
	}
	//get data from database
	public int countPlayerItemForTeam(Integer userId,Integer playerId, int type,int subType,int iId)throws Exception{
		 	
		return (Integer)playerItemDao.countPlayerItemForTeam(userId, playerId, type, subType, iId);
		
	}
	
	public Integer countPlayerItemSuitable(Integer playerId, Integer id, Integer type, Integer subtype) throws Exception {
		return playerItemDao.countPlayerItemSuitable(playerId, id, type, subtype);
	}
	
	public List<PlayerItem> getPlayerItemSuitable(Integer playerId, Integer id, Integer page, Integer type, Integer subtype) throws Exception {
		return playerItemDao.getPlayerItemSuitable(playerId, id, Constants.DEFAULT_PAGE_SIZE * (page - 1), Constants.DEFAULT_PAGE_SIZE, type, subtype);	
	}	
	//===============================================================================	
	//								  Player Pack Services
	//===============================================================================
	/**
	 * get all equipt weapons 
	 * 
	 * @return key:packNo value:weapons in that pack. if this pack is empty, then the list is empty
	 */
	
	public Map<Integer,List<PlayerItem>> getWeaponsInAllPacks(final int playerId) throws Exception{ 
		String key = CacheUtil.oWeaponsInAllPacks(playerId);
		
		@SuppressWarnings("unchecked")
		Map<Integer,List<PlayerItem>> result = (Map<Integer,List<PlayerItem>>) loadValue(key, new CacheMissHandler() {
			@Override
			public Object loadFromDB(GetService service) throws TimeoutException, InterruptedException, MemcachedException {
				Map<Integer,List<PlayerItem>> weaponsInAllPacks =  playerPackDao.getAllWeaponPacks(playerId);
				mcc.deleteWithNoReply(CacheUtil.oStorage(playerId, Constants.DEFAULT_WEAPON_TYPE));
				mcc.deleteWithNoReply(CacheUtil.sStorage(playerId, Constants.DEFAULT_WEAPON_TYPE));
				for (int i=1;i<=Constants.MAX_WEAPON_PACK_SIZE;i++){
					mcc.deleteWithNoReply(CacheUtil.sWeaponPack(playerId, i));
				}
				return weaponsInAllPacks;
			}
		});
		return result;
	}
	
	
	/**
	 * method to get weapon list
	 * @param playerId
	 * @param packId
	 * @return
	 * @throws Exception
	 */
	public List<PlayerItem> getWeaponPackList(final int playerId, final int packId) throws Exception {
		//final String objKey=CacheUtil.oWeaponPack(playerId, packId);
		Map<Integer,List<PlayerItem>> weaponsInAllPacks = getWeaponsInAllPacks(playerId);
		List<PlayerItem> packList = weaponsInAllPacks.get(packId);
		//List<PlayerItem> packList = playerPackDao.getPlayerPackByPackId(playerId, packId,Constants.PACK_TYPE_W);
		
		if (packList == null || packList.isEmpty()) {
			return null;
		}
		
		packList=getPartsForPlayerItem(playerId, packList);
		return packList;
	}
	
	//get Weapon pack by playerId,packId
	//return String
	/*public String getWeaponPackList(final Integer userId, final int playerId,final  int packId) throws Exception{
		String strKey=CacheUtil.sWeaponPack(playerId, packId);
		List<PlayerItem> packList=(List<PlayerItem>) loadValue(strKey, new CacheMissHandler() {
			@Override
			public Object loadFromDB(GetService service) throws Exception {
				return getWeaponPackList(playerId,packId);
			}
		});
		packList=getPartsForPlayerItem(playerId, packList);
		return Converter.playerPackList(packList);
	}*/
	//get Costume Pack 
	//return List<PlayerItem>
	/**
	 * method to get costume pack
	 * @param playerId
	 * @param packId
	 * @param side
	 * @return
	 * @throws Exception
	 */
	public List<PlayerItem> getCostumePackList(final int playerId, final int packId,final int side) throws Exception {
		String objKey=CacheUtil.oCostumePack(playerId, packId, side);
		List<PlayerItem> packList = (List<PlayerItem>) loadValue(objKey, new CacheMissHandler() {
			@Override
			public Object loadFromDB(GetService service) {
				return playerPackDao.getPlayerPackByPackId(playerId, Constants.NUM_ONE,Constants.PACK_TYPE_C,side);
			}
		});
		return packList;

	}
	//===============================================================================	
	//								  Message Service
	//===============================================================================
	/**
	 * method to get email list by player id
	 * @param userId
	 * @param playerId
	 * @return List<Message>
	 * @throws Exception
	 */
	public List<Message> getMessageList(final Integer userId,final int playerId)throws Exception{
		String objKey=CacheUtil.oPlayerMessage(playerId);
		List<Message> list=(List<Message>) loadValue(objKey, new CacheMissHandler() {
			@Override
			public Object loadFromDB(GetService service) {
				List<Message> list = messageDao.getMessageList(userId,playerId);
				for(Message message:list){
					message.init();
				}
				return list;
			}
		});
		return list;
	}
	public int getNewMessageNum(final Integer userId,final int playerId)throws Exception{
		int returnValue=0;
		String objKey=CacheUtil.oPlayerMessage(playerId);
		List<Message> list=getMessageList(playerId, playerId);
		for(Message message:list){
			if(Constants.BOOLEAN_NO.equals(message.getOpen())){
				returnValue+=1;
			}
		}
		return returnValue;
	}
	public Message getMessage(Integer userId, int playerId,int messageId)throws Exception{
		List<Message> messageList=getMessageList(userId, playerId);
		Message message=new Message();
		for(Message m:messageList){
			if(m.getId()==messageId){
				message=m;
				if("N".equals(m.getOpen())){
					messageDao.updateMessageOpen(messageId);
					for(int i =0;i<messageList.size();i++){
						Message message2=messageList.get(i);
						if(message2.getId()==m.getId()){
							message.setOpen("Y");
							messageList.set(i, message);
							break;
						}
					}
					mcc.delete(CacheUtil.oPlayerMessage(playerId));
					mcc.delete(CacheUtil.sPlayerMessage(playerId));
					mcc.set(CacheUtil.oPlayerMessage(playerId), Constants.CACHE_ITEM_TIMEOUT, messageList);
				}
			}
		}
		return message;
	}
	
	public SDOItemOrder getOrderByOrderId(String id) throws DataAccessException, Exception{
		SDOItemOrderPojo pojo = itemOrderDao.getByOrderId(id);
		pojo.setItemsInfo(goodsInfoConvertor.convertPlainStringToItems(pojo.getGoodsinfo(),this));
		return new SDOItemOrder(pojo);
	}
	//===============================================================================	
	//								  Level Info
	//===============================================================================	
	//use by GM mxml
	public List<LevelInfoPojo> getLevelList(){
		return sysLevelDao.getAllLevels();
	}
	
	public LevelModeInfo getLevelModeInfo(int levelId){
		LevelInfoPojo pojo = sysLevelDao.getLevelInfoById(levelId);
		if (pojo == null) {
			return null;
		}
		return new LevelModeInfo(pojo);
	}
	
	public List<LevelWeapon> getLevelWeaponsByLevelId(int levelId){
		return sysLevelDao.getLevelWeaponByLevelId(levelId);
	}
	//use By GM mxml
	public List<LevelWeapon> getLevelWeapons(int levelId){
		return sysLevelDao.getLevelWeaponByLevelId(levelId);
	}
	
	
	//===============================================================================	
	//								  Injected Objects
	//===============================================================================	
	private MemcachedClient 	mcc;
	private UserDao 			userDao;
	private PlayerDao 			playerDao;
	private FriendDao 			friendDao;
	private ServerDao 			serverDao;
	private SysItemDao 			sysItemDao;
	private SysItemLogDao 		sysItemLogDao;
	private PlayerItemDao 		playerItemDao;
	private PlayerPackDao 		playerPackDao;
	private MessageDao			messageDao;
	private CharacterDao		characterDao;
	private CharacterLogDao		characterLogDao;
	private ItemOrderDao		itemOrderDao;
	private SDOComponent 	    sdoComponent;
	private RankDao 			rankDao;
	private TeamDao 	    	teamDao;
	private TeamHistoryDao 	    teamHistoryDao;
	private KeyWordsDao 		keyWordsDao;
	private SysLevelDao 		sysLevelDao;
	private PlayerTeamDao		playerTeamDao;
	private SysModeConfigDao    sysModeConfigDao;
	private SystemDao			systemDao;	
	
	private static GoodsInfoConverter goodsInfoConvertor = new GoodsInfoConverterImpl();
	
	public void setMessageDao(MessageDao messageDao) {
		this.messageDao = messageDao;
	}


	public void setMcc(MemcachedClient mcc) {
		this.mcc = mcc;
	}

	public void setCharacterLogDao(CharacterLogDao characterLogDao) {
		this.characterLogDao = characterLogDao;
	}
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	
	
	public void setSdoComponent(SDOComponent sdoComponent) {
		this.sdoComponent = sdoComponent;
	}
	public void setFriendDao(FriendDao friendDao) {
		this.friendDao = friendDao;
	}	

	public void setServerDao(ServerDao serverDao) {
		this.serverDao = serverDao;
	}

	public void setPlayerDao(PlayerDao playerDao) {
		this.playerDao = playerDao;
	}
	
	public void setSysItemDao(SysItemDao sysItemDao) {
		this.sysItemDao = sysItemDao;
	}	

	public void setPlayerItemDao(PlayerItemDao playerItemDao) {
		this.playerItemDao = playerItemDao;
	}


	public void setPlayerPackDao(PlayerPackDao playerPackDao) {
		this.playerPackDao = playerPackDao;
	}

	public void setSysItemLogDao(SysItemLogDao sysItemLogDao) {
		this.sysItemLogDao = sysItemLogDao;
	}
	public void setCharacterDao(CharacterDao characterDao) {
		this.characterDao = characterDao;
	}

	public void setItemOrderDao(ItemOrderDao itemOrderDao) {
		this.itemOrderDao = itemOrderDao;
	}

	public void setRankDao(RankDao rankDao) {
		this.rankDao = rankDao;
	}

	public void setKeyWordsDao(KeyWordsDao keyWordsDao) {
		this.keyWordsDao = keyWordsDao;
	}
	public void setTeamDao(TeamDao teamDao) {
		this.teamDao = teamDao;
	}
	

	public void setSysLevelDao(SysLevelDao sysLevelDao) {
		this.sysLevelDao = sysLevelDao;
	}

	public void setPlayerTeamDao(PlayerTeamDao playerTeamDao) {
		this.playerTeamDao = playerTeamDao;
	}
	
	public void setTeamHistoryDao(TeamHistoryDao teamHistoryDao) {
		this.teamHistoryDao = teamHistoryDao;
	}

	public void setSysModeConfigDao(SysModeConfigDao sysModeConfigDao) {
		this.sysModeConfigDao = sysModeConfigDao;
	}

	public void setSystemDao(SystemDao systemDao) {
		this.systemDao = systemDao;
	}
	
	
	
}
