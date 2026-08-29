package com.pearl.o2o.service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.rubyeye.xmemcached.MemcachedClient;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import com.pearl.o2o.dao.CharacterDao;
import com.pearl.o2o.dao.CharacterLogDao;
import com.pearl.o2o.dao.FriendDao;
import com.pearl.o2o.dao.ItemModDao;
import com.pearl.o2o.dao.ItemOrderDao;
import com.pearl.o2o.dao.MessageDao;
import com.pearl.o2o.dao.PlayerBuffDao;
import com.pearl.o2o.dao.PlayerDao;
import com.pearl.o2o.dao.PlayerItemDao;
import com.pearl.o2o.dao.PlayerPackDao;
import com.pearl.o2o.dao.PlayerTeamDao;
import com.pearl.o2o.dao.ServerDao;
import com.pearl.o2o.dao.SysItemDao;
import com.pearl.o2o.dao.SysItemLogDao;
import com.pearl.o2o.dao.SysLevelDao;
import com.pearl.o2o.dao.SysModeConfigDao;
import com.pearl.o2o.dao.TeamDao;
import com.pearl.o2o.dao.UserDao;
import com.pearl.o2o.exception.BaseException;
import com.pearl.o2o.exception.DataTooLongException;
import com.pearl.o2o.exception.DuplicatePlayerException;
import com.pearl.o2o.exception.EmptyInputException;
import com.pearl.o2o.exception.IllegalCharacterException;
import com.pearl.o2o.exception.NotBuyEquipmentException;
import com.pearl.o2o.exception.NotEquipException;
import com.pearl.o2o.exception.NotFindPlayerException;
import com.pearl.o2o.pojo.Channel;
import com.pearl.o2o.pojo.Character;
import com.pearl.o2o.pojo.CharacterLog;
import com.pearl.o2o.pojo.Friend;
import com.pearl.o2o.pojo.ItemMod;
import com.pearl.o2o.pojo.LevelInfoPojo;
import com.pearl.o2o.pojo.LevelWeapon;
import com.pearl.o2o.pojo.Message;
import com.pearl.o2o.pojo.Player;
import com.pearl.o2o.pojo.PlayerItem;
import com.pearl.o2o.pojo.PlayerLocation;
import com.pearl.o2o.pojo.PlayerTeam;
import com.pearl.o2o.pojo.SDOItemOrderPojo;
import com.pearl.o2o.pojo.Server;
import com.pearl.o2o.pojo.SysItem;
import com.pearl.o2o.pojo.SysItemLog;
import com.pearl.o2o.pojo.SysModeConfig;
import com.pearl.o2o.pojo.Team;
import com.pearl.o2o.pojo.User;
import com.pearl.o2o.socket.SocketClient;
import com.pearl.o2o.utils.CacheUtil;
import com.pearl.o2o.utils.CommonMsg;
import com.pearl.o2o.utils.CommonUtil;
import com.pearl.o2o.utils.ConfigurationUtil;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.GoodsInfoConverter;
import com.pearl.o2o.utils.GoodsInfoConverterImpl;
import com.pearl.o2o.utils.KeywordFilterUtil;
import com.pearl.o2o.utils.StringUtil;
import com.snda.services.oa.client.bean.ItemsPurchaseInfo.ItemPurchaseEntry;
import com.snda.services.oa.client.bean.PlayerInfo;
import com.snda.services.oa.client.bean.PurchaseResult;
import com.snda.services.oa.client.bean.SDOComponent;
import com.snda.services.oa.client.bean.SDOConstants;
import com.snda.services.oa.client.bean.SDOItemOrder;
import com.snda.services.oa.client.callback.SDOItemAccountLockNotify;
import com.snda.services.oa.client.callback.SDOItemAccountUnlockNotify;

/**
 * @author bobby
 *
 */
public class CreateService {

	static Logger logger = Logger.getLogger(CreateService.class.getName());

	
	//===============================================================================	
	//								  User Services
	//===============================================================================	
	public User createUser(String userName, String password) throws Exception {
		User user = new User();
		user.setUserName(userName);
		user.setPassword(password);
		return userDao.create(user);
	}
	public void createSysModeConfig(Integer type,String config)throws Exception{
		SysModeConfig modeConfig=new SysModeConfig();
		modeConfig.setType(type);
		modeConfig.setConfig(config);
		sysModeConfigDao.createSysModeConfigDao(modeConfig);
	}

	//===============================================================================	
	//								  Character Services
	//===============================================================================
	@Transactional	
	public void createCharacter(Character character)throws Exception{
		mcc.delete(CacheUtil.oCharacter());
		
		character=characterDao.createCharacter(character);
		CharacterLog characterLog=new CharacterLog();
		characterLog.setCharacter(character);
		characterLog.setLogVersion(0);
		characterLogDao.createCharacterLog(characterLog);
	}
	//===============================================================================	
	//								  Team Services
	//===============================================================================
	private int createTeam(Team team)throws Exception{
		return teamDao.createTeam(team);
	}
	/**
     * Method to create team with a team item
     * @param userId
     * @param playerId
     * @param type: Team Item's type
     * @param playerItemId: Team Item's ID
     * @param name: Team name
     * @param declaration: Team declaration
     * @param description: Team description
     * @param board: Team board
     * @param logo: Team logo icon
     * @return boolean
     */
	@Transactional	
	public void createTeamByItem(Integer userId,Integer playerId,int type,int playerItemId,
				String name,String declaration,String description,String board,String logo)throws Exception{
		//1.check if been a team member
		Team isteam=getService.getTeamByPlayerId(playerId);
		if(isteam!=null){
			throw new BaseException(ExceptionMessage.NOT_CREATE_TEAM);
		}
		//2.get playerItem for team ,get size for team
		List<PlayerItem> playerItemList=getService.getPlayerItemForTeam(userId, playerId,  Constants.DEFAULT_ITEM_TYPE, Constants.DEFAULT_SPECIEL_ITEM_SUBTYPE, Constants.DEFAULT_TEAM_IID);
		if(playerItemList==null||playerItemList.size()==0){
			throw new BaseException(ExceptionMessage.NOT_ITEM_TEAM);
		}
		PlayerItem playerItem=playerItemList.get(0);
		mcc.delete(CacheUtil.oStorage(playerId, Constants.DEFAULT_ITEM_TYPE));
		mcc.delete(CacheUtil.sStorage(playerId, Constants.DEFAULT_ITEM_TYPE));
		//3.use team item
		useTeamItem(playerItem, userId, playerId);
		Player player=getService.getPlayerById(playerId);
		//3.remove all application history of this player
		playerTeamDao.removeAllApplicationFromPlayer(playerId);
		
		//4.create team
		Team team=new Team();
		team.setName(name);
		team.setDeclaration(declaration);
		team.setDescription(description);
		team.setBoard(board);
		team.setLogo(logo);
		team.setSize((int)playerItem.getIValue().floatValue());
		int teamId=createTeam(team);
		
		//5.create captain(the creator) for team 
		PlayerTeam playerTeam=new PlayerTeam();
		playerTeam.setPlayerId(playerId);
		playerTeam.setTeamId(teamId);
		playerTeam.setKillNum(player.getGKill());
		playerTeam.setDeadNum(player.getGDead());
		playerTeam.setJob(Constants.TEAMJOB.TEAM_CAPTAIN.getValue());
		playerTeam.setApproved(Constants.BOOLEAN_YES);
		playerTeamDao.createPlayerTeam(playerTeam);
	}

	//===============================================================================	
	//								  Player Services
	//===============================================================================
	/**
	 * Method to create player
	 * @param userId
	 * @param name:player name
	 * @param id:sys character id
	 * @param side:default side for player
	 * @return Player
	 * @throws Exception
	 */
	@Transactional
	public Player createPlayer(Integer userId, String name, Integer id,int side) throws Exception {
		//1.check list:
		//empty input;too long input;illegal character input;duplicate player;player size
		if(name == null || "".equals(name)){
			throw new EmptyInputException(ExceptionMessage.EMPTY_STR_CREATE_PLAYER);
		}
		if (name != null && name.getBytes("GBK").length > 10) {
			throw new DataTooLongException(ExceptionMessage.TOO_LONG_CREATE_PLAYER);
		}		
		if(StringUtil.filter(name)){
			throw new IllegalCharacterException(ExceptionMessage.ILLEGAL_CHARACTER_CREATE_PLAYER);
		}
		if(!KeywordFilterUtil.isLegalInput(name)){
			throw new IllegalCharacterException(ExceptionMessage.ILLEGAL_CHARACTER_CREATE_PLAYER);
		}
		
		List<Player> oldPlayer = playerDao.getPlayer(name);
		if (oldPlayer != null && oldPlayer.size() > 0) {
			throw new DuplicatePlayerException();
		}
		List<Player> list=playerDao.getPlayer(userId);
		if(list!=null&&list.size()>=3){
			throw new BaseException(ExceptionMessage.MORE_THAN_THREE_PLAAYER);
		}
		//2.get sys character,load the default parameter for player
		Character character=new Character();
		Player player = new Player();
		player.setUserId(userId);
		player.setName(name);
		player.setLastGameSide(0);
		character=getService.getCharacter(id,Constants.BOOLEAN_YES);
		int cost=character.getCost();
		player.setResourceP(character.getResourceP());
		player.setResourceT(character.getResourceT());
		player.setGender(character.getGender());
		player.setSysCharacterId(id);

		//TODO : for test
		player.setRank(10);
		player.setExp(99999);
		
		player.setLastGameSide(side);
		player.setWPackSize(Constants.DEFAULT_WEAPON_PACK_SIZE);
		player.setGPoint(999999);//for test
		player.setFlowerTotal(0);

		mcc.delete(CacheUtil.oPlayerList(userId));
		mcc.delete(CacheUtil.sPlayerList(userId));
		//3.create player
		User user = getService.getUserById(userId);
		List<Player> playerList=getService.getPlayerByUserId(userId);
		
		player = playerDao.create(player);
		playerPackDao.create(userId,player.getId());
		
		//4.update CR
		if(playerList!=null&&playerList.size()>1){
			/*int cr = getService.getCR(userId); 
			if (cr < cost) {
				mcc.delete(CacheUtil.sUserCR(userId));//if CR is not enough, remove it from cache because it's much possible for user to recharge 
				throw new NotBuyEquipmentException(ExceptionMessage.NOT_ENOUGH_CR);//not have enough GPoint
			}*/
			String sdoUid = ConfigurationUtil.sdo_switch.equals("on")? user.getUserName() : "1055423031";
			PlayerInfo playerInfo = new PlayerInfo(sdoUid, player.getUserId(), 0,"127.0.0.1",SDOConstants.AREAID,SDOConstants.GROUPID);
			
			PurchaseResult result = purchaseCharacter(playerInfo,cost);
			int crBalance = result.getBalance();
			player.setCredit(crBalance);
			mcc.set(CacheUtil.sUserCR(userId), Constants.CACHE_SDO_CR_TIMEOUT , crBalance);
		}
		return player;
	}

	
	//===============================================================================	
	//								  Friend Services
	//===============================================================================
	public void createFriend(Integer userId, Integer playerId, Integer friendId, String isBlackList) throws Exception {
		Friend friend = new Friend();
		friend.setUserId(userId);
		friend.setPlayerId(playerId);
		friend.setFriendId(friendId);
		friend.setIsBlackList(isBlackList);
		friendDao.create(friend);
	}

	
	//===============================================================================	
	//								  Server Services
	//===============================================================================		
	/**
	 * method to cache server map 
	 * @throws Exception
	 */
	private void putServerListToMem() throws Exception {
		StringBuffer serverInfo = new StringBuffer(Constants.GET_SERVER_LIST);
		Map<Integer, Server> serverMap = new HashMap<Integer, Server>();

		try {
			// get server map from database
			// set in cache
			serverMap = new HashMap<Integer, Server>();
			serverMap = serverDao.getServerMap();
			if (serverMap != null && serverMap.size() != 0) {
				mcc.set(serverInfo.toString(), Constants.CACHE_ITEM_TIMEOUT,
						serverMap);
			}
		} catch (Exception e) {
			logger.error("Exception in putServerListToMem: ", e);
		}
	}
	
	
	//===============================================================================	
	//								  Channel Services
	//===============================================================================	
	public void createChannel(int channelId, int serverId) throws Exception {
//		StringBuffer server = new StringBuffer(Constants.GET_SERVER_LIST);
//
//		Server serverById = null;
//		Map<Integer, Server> serverMap = new HashMap<Integer, Server>();
//		Map<Integer, Channel> channelMap = new HashMap<Integer, Channel>();
//		
//		serverMap = (Map<Integer, Server>) mcc.get(server.toString());
//		if (serverMap == null) {
//			// get server by server id from database
//			logger.warn("Not find server information in memory!");
//			putServerListToMem();
//			serverMap = (Map<Integer, Server>) mcc.get(server.toString());
//		} else {
//			DeleteService.deleteChannelById(channelId, serverMap);
//		}
//
//		// server not in mem but in database
//		// get server by server id from database
//		serverById = serverMap.get(serverId);
//		if (serverById == null) {
//			throw new ServerNotFoundException();
//		} else {
//			channelMap = serverById.getChannelMap();
//			if (channelMap == null) {
//				channelMap = new HashMap<Integer, Channel>();
//			}
//			Channel channelInfo = new Channel();
//			channelInfo.setId(channelId);
//			channelInfo.setName(Constants.CHANNEL_NAME + channelId);
//			channelInfo.setServerId(serverId);
//			channelInfo.setMax(Constants.CHANNEL_MAX_DEFAULT);
//			channelMap.put(channelId, channelInfo);
//
//			serverById.setMax(serverById.getMax()
//					+ Constants.CHANNEL_MAX_DEFAULT);
//			serverById.setChannelMap(channelMap);
//			serverMap.put(serverId, serverById);
//			mcc.set(server.toString(), Constants.CACHE_ITEM_TIMEOUT, serverMap);
//		}
	}
	//===============================================================================	
	//								  Level  Services
	//===============================================================================
	public void createLevelInfo(LevelInfoPojo info)throws Exception{
		info.setId(null);
		sysLevelDao.insertLevelInfo(info);
	}
	public void createLevelWeapon(LevelWeapon levelWeapon)throws Exception{
		levelWeapon.setId(null);
		sysLevelDao.insertLevelWeapon(levelWeapon);
	}
	
	//===============================================================================	
	//								  System Item Services
	//===============================================================================
	@Transactional
	public void createSysItem(SysItem sysItem)throws Exception{
		mcc.delete(CacheUtil.sShop(sysItem.getType(),sysItem.getSubType(),sysItem.getCGender()));
		mcc.delete(CacheUtil.oShop(sysItem.getType(),sysItem.getSubType(),sysItem.getCGender()));
		
		int id=sysItemDao.createSysItem(sysItem);
		if(sysItem.getType().equals(Constants.DEFAULT_PACKAGE_TYPE)){
			String items=sysItem.getItems();
			if(items!=null){
				String item[]=items.split(",");
				for(int i=0;i<item.length;i++){
					sysItemDao.createSysPackage(id, StringUtil.toInt(item[i]));
				}
				//to do create sys_package
			}else{
				throw new Exception(ExceptionMessage.NOT_PACKAGE_ITEM);
			}
		}
		
		SysItemLog sysItemLog =new SysItemLog();
		sysItemLog.setId(null);
		sysItem.setId(id);
		sysItemLog.setSysItem(sysItem);
		sysItemLog.setLogVersion(0);
	
		sysItemLogDao.createSysItemLog(sysItemLog);
		
	}
	//===============================================================================	
	//								  System Item Log Services
	//===============================================================================
	
	public void createSysItemLog(SysItemLog sysItemLog)throws Exception{
		sysItemLogDao.createSysItemLog(sysItemLog);
	}
	
	public void createSDOItem(SDOItemOrder order) throws Exception{
		SDOItemOrderPojo pojo = order.getSDOItemOrderPojo();
		pojo.setGoodsinfo(goodsInfoConvertor.convertItemsToPlainString(pojo.getItemsInfo()));
		itemOrderDao.create(pojo);
	}
	
	
	public PurchaseResult purchaseCharacter(PlayerInfo playerInfo, int cost) throws NotBuyEquipmentException{
		Set<ItemPurchaseEntry> itemsInfo = new HashSet<ItemPurchaseEntry>();
		//fake system item, only cost filed is important
		SysItem newCharacter = new SysItem();
		newCharacter.setId(0);
		newCharacter.setCost1(cost);
		newCharacter.setCost2(cost);
		newCharacter.setCost3(cost);
		newCharacter.setCost4(cost);
		newCharacter.setCost5(cost);
		
		ItemPurchaseEntry entry = new ItemPurchaseEntry(newCharacter, 1, 1, Constants.BOOLEAN_NO);
		//because it's not a real sys item when purchasing the character. so must set false to field 'needPersist'
		//so that when invoking the method 'purchaseSDOItem', it will no persist the sys item.
		entry.setNeedPersist(false);
		itemsInfo.add(entry);
		return purchaseSDOItem(playerInfo,itemsInfo);
	}
	
	
	/**
	 * @param playerInfo
	 * @param itemsInfo
	 * @return    balance
	 * @throws NotBuyEquipmentException
	 */
	public PurchaseResult purchaseSDOItem(PlayerInfo playerInfo, Set<ItemPurchaseEntry> itemsInfo) throws NotBuyEquipmentException{
		Logger logger = Logger.getLogger("sdoLogger");
		final int SUCCESS = 0;
		String errorStr = "";
		SDOItemOrder order = null;
		
		try {//try build order object
			order = new SDOItemOrder(itemsInfo, playerInfo, SDOItemOrder.PayType.TICKET, sdoComponent.generateUniqueId());
			//order = new SDOItemOrder(itemsInfo, playerInfo, SDOItemOrder.PayType.TICKET, "1");
		} catch (Exception e) {
			errorStr = "build sdo order parameters error ";
			logger.error("build order fail, exception is " + e.getMessage());
			throw new NotBuyEquipmentException(ExceptionMessage.BUY_FAIL);
		}
		//use order as the contextId
		final String contextId = order.getOrderId();
		logger.info("[player: " + playerInfo.getPlayerId() + ", user: " + playerInfo.getO2oUid() + "] buy items through SDO. contextId : " + contextId);
		//finish building order, try to send account lock request
		try {
			SDOItemAccountLockNotify lockRes = sdoComponent.sendAccountLockRequest(order);
			 if(lockRes == null || lockRes.getResult() != SUCCESS) {
				 throw new Exception("account lock fail, contextId :" + contextId + " error code is " + lockRes.getResult());
			 }
			 logger.info("account lock successfully " + "contextId: " + contextId);
			//persist the order
			createSDOItem(order);
		}catch (Exception e) {
			//even timeout, the unlock operation maybe already successfully, for safety, do roll-back anyway
			errorStr = "create order fail, contextId:" + contextId + " exception is :" + e.getMessage();
			logger.error(errorStr);
			rollbackItemOrder(order);
			throw new NotBuyEquipmentException(ExceptionMessage.BUY_FAIL);
		}
		//lock account successful, try to create item for user
		try{
			//after persist order, try to create item
			Set<Integer> itemIds = buyPlayerItem(order);
			logger.info("send item successfully contextId:" + contextId);
			int balance = commitItemOrder(order);
			logger.info("account unlock successfully "  + "contextId: " + contextId );
			return new PurchaseResult(balance, itemIds);
		}catch(Exception e){// exception occurred when create item, need roll-back
			errorStr = "create Order [" + contextId + "]fail,  exception is:" + e.getMessage();
			logger.error(errorStr);
			throw new NotBuyEquipmentException(ExceptionMessage.BUY_FAIL);
		}finally{
			try{//update the final state of order
				itemOrderDao.updateOrderState(contextId,order.getOrderState());
			}catch(Exception e){
				logger.fatal("update order status fail contextId is" + contextId + " final status is " + order.getOrderState().name());
			}
		}
	}
	
	//must set the final state for order
	private int rollbackItemOrder(SDOItemOrder order) {
		final int SUCCESS = 0;
		String contextId = order.getOrderId();
		
		//try to roll-back 
		SDOItemAccountUnlockNotify rollBackResponse = null;
		try{
			rollBackResponse = sdoComponent.sendAccountRollbackRequest(order);
			if (rollBackResponse.getResult() == SUCCESS) {
				order.changeStateTo(SDOItemOrder.OrderState.ROLLBACKED);
			}else{
				logger.error("rollback operation fail: contextId:" + contextId + " result code is : " + rollBackResponse.getResult());
				order.changeStateTo(SDOItemOrder.OrderState.ROLLBACK_FAIL);
			}
			return rollBackResponse.getBalance();
		} catch (Exception e){
			logger.error("rollback operation fail: contextId:" + contextId);
			order.changeStateTo(SDOItemOrder.OrderState.ROLLBACK_FAIL);
			return  -1;
		}
	}
	
	//must set the final state for order
	private int commitItemOrder(SDOItemOrder order)  {
		final int SUCCESS = 0;
		String contextId = order.getOrderId();
		
		SDOItemAccountUnlockNotify unlockResponse = null;
		try {
			unlockResponse = sdoComponent.sendAccountUnlockRequest(order);
			if (unlockResponse.getResult() == SUCCESS) {//update order state
				order.changeStateTo(SDOItemOrder.OrderState.COMMITTED);
			}else{
				logger.error("unlock operation fail: contextId:" + contextId + " result code is : " + unlockResponse.getResult());
				order.changeStateTo(SDOItemOrder.OrderState.COMMIT_FAIL);
			}
			return unlockResponse.getBalance();
		} catch (Exception e) {
			logger.error("unlock operation fail: contextId:" + contextId );
			order.changeStateTo(SDOItemOrder.OrderState.COMMIT_FAIL);
			return -1;
		}
	}
	
	@Transactional
	private Set<Integer> buyPlayerItem(SDOItemOrder order) throws Exception{
		//REFACTOR
		Set<Integer> result = new HashSet<Integer>();
		int playerId = order.getPlayerInfo().getPlayerId();
		int userId = order.getPlayerInfo().getO2oUid();
		Player player = getService.getPlayerById(playerId);
		
		for (ItemPurchaseEntry itemInfo : order.getItemsPurchaseInfo().getItemsInfo()){
			if (!itemInfo.isNeedPersist()){//currently, this field will be false only when purchasing character 
				continue;
			}
			int costType = itemInfo.getCostType();
			SysItem item = itemInfo.getItem();
			
			int unit;
			if(costType==1){
				unit=item.getUnit1();
			}else if(costType==2){
				unit=item.getUnit2();
			}else if(costType==3){
				unit=item.getUnit3();
			}else if(costType==4){
				unit=item.getUnit4();
			}else{
				unit=item.getUnit5();
			}
			for (int i=0;i<itemInfo.getQuantity();i++){
				int playerItemId = sendItems(item, player, itemInfo.getIsGift(), userId, playerId, unit, item.getCurrency(), item.getUnitType());
				result.add(playerItemId);
			}
		}
		return result;
	}
	

	
	//===============================================================================	
	//								  Player Item Services
	//===============================================================================
	/**
	 * method to buy player Item 
	 * @param userId
	 * @param player
	 * @param sysItem
	 * @param costType:the cost number
	 * @param isGift:is buying for a gift
	 * @return playerItemId
	 * @throws Exception
	 */
	@Transactional
	private int buyPlayerItem(Integer userId,Player player,SysItem sysItem,Integer costType,String isGift ) throws Exception{
		//1.check the player's rank
		int playerId=player.getId();
		int playerItemId=0;
		if(player.getRank()<sysItem.getLevel()){
			throw new 	NotBuyEquipmentException(ExceptionMessage.NOT_MATCH_LEVEL_BUY);
		}
		//2.get the item's cost,unit,unitType,currency
		int gp=player.getGPoint();
		int unitType=sysItem.getUnitType();
		int currency=sysItem.getCurrency();
		
		int cost;
		int unit;
		if(costType==1){
			cost=sysItem.getCost1();
			unit=sysItem.getUnit1();
		}else if(costType==2){
			cost=sysItem.getCost2();
			unit=sysItem.getUnit2();
		}else if(costType==3){
			cost=sysItem.getCost3();
			unit=sysItem.getUnit3();
		}else if(costType==4){
			cost=sysItem.getCost4();
			unit=sysItem.getUnit4();
		}else{
			cost=sysItem.getCost5();
			unit=sysItem.getUnit5();
		}
		//3.update the money things
		User user = getService.getUserById(player.getUserId());
		//TODO change first parameter to user.getUserName()
		//digital account of qfth1 
		String sdoUid = ConfigurationUtil.sdo_switch.equals("on")? user.getUserName() : "1055423031";
		PlayerInfo playerInfo = new PlayerInfo(sdoUid, player.getUserId(), player.getId(),"127.0.0.1",SDOConstants.AREAID,SDOConstants.GROUPID);

		if(Constants.GP_PAY.equals(currency)){
			if(gp<cost){
				throw new NotBuyEquipmentException(ExceptionMessage.NOT_ENOUGH_GP);//not have enough GPoint
			}else{
				//a.update player's gpoint
				gp=gp-cost;
				playerDao.updateGP(gp, playerId);
				//insert into PlayItem
				playerItemId=sendItems(sysItem,player,isGift,userId,playerId,unit,currency,unitType);
				player.setGPoint(gp);
				String playerKey=CacheUtil.oPlayer(playerId);
//				mcc.set(playerKey, Constants.CACHE_ITEM_TIMEOUT, player);
				player.setCredit(getService.getCR(userId));
			}
		}else if (Constants.CR_PAY.equals(currency)){
			//TODO
			/*int cr = getService.getCR(userId); 
			if (cr < cost) {
				mcc.delete(CacheUtil.sUserCR(userId));//if CR is not enough, remove it from cache because it's much possible for user to recharge 
				throw new NotBuyEquipmentException(ExceptionMessage.NOT_ENOUGH_CR);//not have enough GPoint
			}*/
			
			//REFACTOR: currently only one item will be purchased per request
			Set<ItemPurchaseEntry> itemsInfo = new HashSet<ItemPurchaseEntry>();
			ItemPurchaseEntry entry = new ItemPurchaseEntry(sysItem, 1, costType, isGift);
			itemsInfo.add(entry);
			
			PurchaseResult result = purchaseSDOItem(playerInfo,itemsInfo);
			try{
				playerItemId = result.getIds().iterator().next(); 
				int crBalance = result.getBalance();
				player.setCredit(crBalance);
				mcc.set(CacheUtil.sUserCR(userId), Constants.CACHE_SDO_CR_TIMEOUT , crBalance);
			}catch(Exception e){// all operation after "purchaseSDOItem" must not lead to roll-back, so catch all exception and convert them to checked exception
				throw new Exception(e);
			}
		}
		return playerItemId;
	}
	
	/**
	 * method to send items to player
	 * @param sysItem
	 * @param player
	 * @param isGift
	 * @param userId
	 * @param playerId
	 * @param unit
	 * @param currency
	 * @param unitType
	 * @return player item id
	 * @throws Exception
	 */
	private int sendItems(SysItem sysItem,Player player,String isGift,int userId,int playerId,int unit,int currency,int unitType) throws Exception{
		int playerItemId=0;
		if(sysItem.getType()==Constants.DEFAULT_PACKAGE_TYPE && isGift.equals(Constants.BOOLEAN_NO)){
			//package
			//TODO bobby:to be improved
			List<SysItem> packageList=sysItem.getPackages();
			for(SysItem si:packageList){
				playerItemId=playerItemDao.createPlayItem(userId,playerId,si.getId(),si.getModifiedDesc(),unit,Constants.BOOLEAN_NO
						,currency,unitType);
				mcc.delete(CacheUtil.oStorage(playerId, si.getType()));
				mcc.delete(CacheUtil.sStorage(playerId, si.getType()));
			}
		}else if(sysItem.getType()==Constants.DEFAULT_ITEM_TYPE&&isGift.equals(Constants.BOOLEAN_NO)&&sysItem.getIId()==Constants.NUM_TWO){
			//flower item 
			boolean isToday=CommonUtil.isToday(player.getFlowerLastTime());
			playerDao.updateFlowerItem(userId, playerId,player,unit,isToday);
			mcc.delete(CacheUtil.oPlayer(playerId));
			mcc.delete(CacheUtil.sPlayer(playerId));
			mcc.delete(CacheUtil.oPlayerList(userId));
			mcc.delete(CacheUtil.sPlayerList(userId));
		}else if(sysItem.getType()==Constants.DEFAULT_COSTUME_TYPE&&isGift.equals(Constants.BOOLEAN_NO)&&sysItem.getSubType()==Constants.DEFAULT_FACE_SUBTYPE){
			//costume item
			mcc.delete(CacheUtil.oPlayerList(userId));
			mcc.delete(CacheUtil.sPlayerList(userId));
			mcc.delete(CacheUtil.oPlayer(playerId));
			mcc.delete(CacheUtil.sPlayer(playerId));
			//get Player
			String[] resourcesT = player.getResourceT().split(Constants.DELIMITER_RESOURCE_STABLE);
			resourcesT[2]=sysItem.getName();
			String[] resourcesP = player.getResourceP().split(Constants.DELIMITER_RESOURCE_STABLE);
			resourcesP[2]=sysItem.getName();
			playerDao.update("RESOURCE_T", CommonUtil.arrayToString(resourcesT), playerId);
			playerDao.update("RESOURCE_P", CommonUtil.arrayToString(resourcesP), playerId);
			
		}
		else{
			//other item
			playerItemId=playerItemDao.createPlayItem(userId,playerId,sysItem.getId(),sysItem.getModifiedDesc(),unit,isGift
					,currency,unitType);
		}
		return playerItemId;
	}
	/**
	 * method to buy item and equip it to packages 
	 * @param userId
	 * @param playerId
	 * @param playerItemId
	 * @param sysItemId
	 * @param type
	 * @param subType
	 * @param costType
	 * @param isGift
	 * @return map with player's gp,cr,and player item's id
	 * @throws Exception
	 */
	@Transactional
	public Map BuyAndEquipPlayerItem(Integer userId,Integer playerId,Integer playerItemId,Integer sysItemId,Integer type,Integer subType,Integer costType, String isGift)throws Exception{
		String strKey=CacheUtil.sStorage(playerId, type);
		String objKey=CacheUtil.oStorage(playerId,type);
		mcc.delete(CacheUtil.sPlayer(playerId));
		mcc.delete(CacheUtil.oPlayerList(userId));
		mcc.delete(CacheUtil.sPlayerList(userId));
		mcc.delete(strKey);
		mcc.delete(objKey);
		mcc.delete(CacheUtil.oPlayer(playerId));
		mcc.delete(CacheUtil.oStorage(playerId,type));
		mcc.delete(CacheUtil.sStorage(playerId, type));
		//mcc.delete(CacheUtil.oWeaponPack(playerId, Constants.NUM_ONE));
		mcc.delete(CacheUtil.oWeaponsInAllPacks(playerId));
		mcc.delete(CacheUtil.sWeaponPack(playerId, Constants.NUM_ONE));
		//1.get player info by playerId
		Player player=getService.getPlayerById(playerId);
		//2.get item info by sysitemId
		SysItem sysItem=getService.getSysItemByItemId(userId,playerId,type, subType, sysItemId);
		//delete the pack info from memcache
		mcc.delete(CacheUtil.oCostumePack(playerId, Constants.NUM_ONE, sysItem.getCSide()));
		mcc.delete(CacheUtil.sCostumePack(playerId, Constants.NUM_ONE,sysItem.getCSide()));
		int id=buyPlayerItem(userId, player, sysItem, costType,Constants.BOOLEAN_NO);//500
		//player=getService.getPlayerById(playerId);
		if(Constants.DEFAULT_WEAPON_TYPE == type){
			//get seq
			int seq=CommonUtil.getWeaponSeq(sysItem.getWId());
			if(type!=Constants.DEFAULT_WEAPON_TYPE || seq == 0 ){
				throw new NotEquipException("Weapon can not be equiped!");
			} 
			//update the pack info in database
			playerPackDao.createWeaponPackEquipment(userId, playerId, playerItemId,  Constants.NUM_ONE, seq);
		
		}else if(Constants.DEFAULT_COSTUME_TYPE == type){
			 //if have item in pack,judge if have costume set
			Integer seq=CommonUtil.getCotumeSeq(sysItem.getSubType());
			 List<PlayerItem> packList = mcc.get(CacheUtil.oCostumePack(playerId, Constants.NUM_ONE, seq));
			 if(packList==null){
				 packList=getService.getCostumePackList(playerId, Constants.NUM_ONE, seq);
			 }
			 for(PlayerItem pi:packList){
				 if(pi.getId()!=null&&pi.getType()==Constants.DEFAULT_COSTUME_TYPE&&pi.getSubType()==Constants.DEFAULT_COSTUME_SET_SUBTYPE){
					 playerPackDao.deletePackEquipment(userId, playerId, Constants.NUM_ONE, type, pi.getSeq());
					 break;
				 }
			 }
			if(sysItem.getSubType()==Constants.DEFAULT_COSTUME_SET_SUBTYPE){
				seq=Constants.NUM_ONE;		//在仓库里面直接装备
				playerPackDao.deletePackAll(userId, playerId, Constants.NUM_ONE, type);
			}
			if(type!=Constants.DEFAULT_COSTUME_TYPE || seq == 0 ||!(sysItem.getCGender()).equals(player.getGender())){
				throw new NotEquipException("Costume can not be equiped!");
			} 
			//update the pack info in database
			playerPackDao.createCostumePackEquipment(userId, playerId, playerItemId, Constants.NUM_ONE, seq, sysItem.getCSide());
		}
		
		Map map=new HashMap<String, Object>();
		map.put("gp", player.getGPoint());
		map.put("id", id);
		map.put("cr", player.getCredit());
		
		return map;
		
	}
	/**
	 * method to create player item then buy it
	 * @param userId
	 * @param playerId
	 * @param type
	 * @param subType
	 * @param sysItemId
	 * @param costType
	 * @param isGift
	 * @return
	 * @throws Exception
	 */
	@Transactional
	public Map createPlayerItem(Integer userId, Integer playerId,Integer type, Integer subType,Integer sysItemId,Integer costType,String isGift)throws Exception{
		String strKey=CacheUtil.sStorage(playerId, type);
		String objKey=CacheUtil.oStorage(playerId,type);
		mcc.delete(CacheUtil.oPlayer(playerId));
		mcc.delete(CacheUtil.sPlayer(playerId));
		mcc.delete(CacheUtil.oPlayerList(userId));
		mcc.delete(CacheUtil.sPlayerList(userId));
		mcc.delete(strKey);
		mcc.delete(objKey);
		//1.get player info by playerId
		Player player=playerDao.getPlayerById(playerId);

		//2.get item info by sysitemId
		SysItem sysItem=getService.getSysItemByItemId(userId,playerId,type, subType, sysItemId);
			
		for(int i=0;i<Constants.SUB_TYPE_NUM;i++){
			mcc.delete(CacheUtil.oShopPart(playerId,i+1));
			mcc.delete(CacheUtil.sShopPart(playerId,i+1));
		}
		int playerItemId=buyPlayerItem(userId, player, sysItem, costType,Constants.BOOLEAN_NO);//500
		//player=getService.getPlayerById(playerId);
		Map map=new HashMap<String, Object>();
		map.put("gp", player.getGPoint());
		map.put("id", playerItemId);
		map.put("cr", player.getCredit());
		return map;
		
	}
	/**
	 * method to detach a package gift
	 * @param userId
	 * @param playerId
	 * @param type
	 * @param playerItemId:gift id
	 * @throws Exception
	 */
	@Transactional
	public 	void detachPackage(Integer userId,Integer playerId,Integer type,Integer playerItemId) throws Exception{
		mcc.delete(CacheUtil.oStorage(playerId, type));
		mcc.delete(CacheUtil.oStorage(playerId, type));
		List<PlayerItem> list= playerItemDao.getGiftPackageById(userId, playerId, playerItemId);
		if(list!=null){
			for(PlayerItem item:list){
				SysItem sysItem=sysItemDao.getSystemItemById(item.getItemId());
				playerItemDao.createPlayItem(userId, playerId, item.getItemId(), sysItem.getModifiedDesc(),item.getQuantity(), 
						Constants.BOOLEAN_NO,item.getCurrency(),item.getUnitType(),
						item.getValidDate(), item.getExpireDate());
			
			}
			playerItemDao.deletePlayerItem(userId, playerId, playerItemId);
		}
	}
	
	
	private boolean validateMessage(String playerName,String message) throws BaseException{
		if("".equals(message)){
			throw new BaseException(ExceptionMessage.EMPTY_STR);
		}
		if(message.length()>50){
			throw new BaseException(ExceptionMessage.TOO_LONG);
		}
		return true;
	}
	private void sendMsgToServer(Server server, String message) {
		if (server != null && server.getChannelMap() != null) {
			Set<Integer> set = server.getChannelMap().keySet();
			soClient.messageDLB(set, message);
		}
	}
	/**
	 * @throws BaseException 
	 * @param serverId 
	 * @param roomId 
	 * @param channelId 
	 * @param serverId 
	 * method to use item 
	 * @param userId
	 * @param playerId
	 * @param type
	 * @param playerItemId:item's id
	 * @throws Exception
	 */
	@Transactional
	public void useItemById(Integer userId, int playerId, int type, int playerItemId,String message, int channelId, int serverId) throws Exception{
		PlayerItem playerItem=getService.getPlayerItemByItemId(userId, playerId, type, playerItemId);
		Player player=getService.getPlayerById(playerId);
		mcc.delete(CacheUtil.sStorage(playerId, type));
		mcc.delete(CacheUtil.oStorage(playerId, type));
		mcc.delete(CacheUtil.sPlayer(playerId));
		mcc.delete(CacheUtil.oPlayer(playerId));
		if(playerItem.getIId()==1){
			//buff item
			useBuffItem(playerItem);
		}else if(playerItem.getIId()==3){
			//speaker item
			if(message!=null&&serverId!=0){
				Server server = getService.getServer(serverId);
				message="["+player.getName()+"]:"+KeywordFilterUtil.filter(message);
				if(validateMessage(player.getName(), message)){
					sendMsgToServer(server, message);
					useSpeakerItem(playerItem,userId,playerId,playerItemId);
				}
			}else if(message!=null&&channelId!=0){
				Channel channel=getService.getChannel(channelId);
				Server server = getService.getServer(channel.getServerId());
				message="["+player.getName()+"]:"+KeywordFilterUtil.filter(message);
				if(validateMessage(player.getName(), message)){
					sendMsgToServer(server, message);
					useSpeakerItem(playerItem,userId,playerId,playerItemId);
				}
			}else {
				throw new BaseException("没有位置信息");
			}
		}else if(playerItem.getIId()==5){//new pack
			//new bag item,upgrade the bag size
			useBagItem(playerItem,userId,playerId);
		}else if(playerItem.getIId()==6){
		}else if(playerItem.getIId()==7){
			//team item
			useTeamItem(playerItem,userId,playerId);
		}else if(playerItem.getIId()==8){
			//upgrade team item
//			useTeamItem(playerItem,userId,playerId);
		}else if(playerItem.getIId()==9){
		}else if(playerItem.getIId()==10){
		}else if(playerItem.getIId()==11||playerItem.getIId()==12){
			Channel channel=getService.getChannel(channelId);
			Server server = getService.getServer(channel.getServerId());
			if(validateMessage(player.getName(), message)){
				String[] str={player.getName(),message};
				String[] str1={player.getTeam(),message};
				if(playerItem.getIId()==11){
					if(playerItem.getIValue()==1){
						//personal 
						message=CommonUtil.messageFormat(CommonMsg.BOXING_MSG, message);
					}else{
						//team
						message=CommonUtil.messageFormat(CommonMsg.BOXING_TEAM_MSG, message);
					}
				}else if(playerItem.getIId()==12){
					if(playerItem.getIValue()==1){
						//personal
						message=CommonUtil.messageFormat(CommonMsg.CHALLENGE_MSG, str);
					}else{
						//team
						message=CommonUtil.messageFormat(CommonMsg.CHALLENGE_TEAM_MSG, str1);
					}
				}
				sendMsgToServer(server, message);
				useChallengeItem(playerItem,userId,playerId,playerItemId);
			}
		}
//		else if(playerItem.getIId()==12){
//			
//		}
	}
	/**
	 * method to use buff item,select then update method
	 * @param playerItem
	 * @throws Exception
	 */
	public void useBuffItem(PlayerItem playerItem)throws Exception{
		int count=playerBuffDao.fuzzyCountPlayerBuff(playerItem);
		if(count!=0){
			playerBuffDao.updatePlayerBuff(playerItem);
		}else{
			playerBuffDao.creatPlayerBuff(playerItem);
		}
	}
	/**
	 * method to use new bag item
	 * @param playerItem
	 * @param userId
	 * @param playerId
	 * @param playerItemId
	 * @throws Exception
	 */
	@Transactional
	public void useBagItem(PlayerItem playerItem, Integer userId, int playerId){
		//get pack NO of item
		int packNo = playerItem.getIValue().intValue();
		if (packNo <= Constants.DEFAULT_WEAPON_PACK_SIZE || packNo > Constants.MAX_WEAPON_PACK_SIZE) {
			throw new RuntimeException("pack number error");
		}
		logger.debug("use bag item,No." + packNo);
		try{
			playerPackDao.clearExpiredWeaponPack(playerId, packNo);//first release all relative weapons except knife
			playerPackDao.updatePackExpriedTime(userId, playerId, packNo, playerItem.getExpireDate());//update the expired time 
			
			mcc.delete(CacheUtil.sPlayer(playerId));
			mcc.delete(CacheUtil.oPlayer(playerId));
			mcc.delete(CacheUtil.sWeaponPack(playerId, packNo));
			mcc.delete(CacheUtil.oWeaponsInAllPacks(playerId));
			//TODO: whether delete it
			playerItemDao.deletePlayerItem(userId, playerId, playerItem.getId());
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * method to use speaker item
	 * @param playerItem
	 * @param userId
	 * @param playerId
	 * @param playerItemId
	 * @throws Exception
	 */
	public void useSpeakerItem(PlayerItem playerItem, Integer userId, int playerId, int playerItemId)throws Exception{
		playerItemDao.updatePlayerItem(userId, playerId, playerItemId, "QUANTITY", playerItem.getQuantity()-1);
	}
	
	public void useChallengeItem(PlayerItem playerItem, Integer userId, int playerId, int playerItemId)throws Exception{
		playerItemDao.updatePlayerItem(userId, playerId, playerItemId, "QUANTITY", playerItem.getQuantity()-1);
	}
	/**
	 * method to use team item
	 * @param playerItem
	 * @param userId
	 * @param playerId
	 * @throws Exception
	 */
	public void useTeamItem(PlayerItem playerItem, Integer userId, int playerId)throws Exception{
		playerItemDao.updatePlayerItem(userId, playerId, playerItem.getId(), "QUANTITY", playerItem.getQuantity()-1);
	}
	//===============================================================================	
	//								  Item Mod Service
	//===============================================================================
	/**
	 * method to re-equip weapon with one part
	 * @param userId
	 * @param playerId
	 * @param weaponId
	 * @param partId
	 * @param type
	 * @param subtype
	 * @throws Exception
	 */
	@Transactional
	public void createItemMod(Integer userId,Integer playerId, Integer weaponId, Integer partId, Integer type, Integer subtype) throws Exception {
		//get player item by id
		PlayerItem playerItem=getService.getPlayerItemByPlayerItemId(userId, playerId, weaponId);
		String modifiedDesc = playerItem.getModifiedDesc();
		//is can be changeable
		boolean isCanEquip=StringUtil.isCanEquip(modifiedDesc, subtype);
		//is in suit
		boolean isSuit=false;
		PlayerItem partItem=getService.getPlayerItemByItemId(userId, playerId, type, partId);
		String suitStr=partItem.getPSuitable();
		String suit[]=suitStr.split(Constants.DELIMITER_RESOURCE_CHANGEABLE);
		for(int i=0;i<suit.length;i++){
			if(suit[i].equals(playerItem.getName())){
				isSuit=true;
			}
		}

		if(isCanEquip&&isSuit){
			ItemMod itemMod = new ItemMod();
			itemMod.setParentItemId(weaponId);
			itemMod.setChildItemId(partId);
			itemMod.setSeq(subtype);
			String newModifiedDesc=StringUtil.updateModifiedDesc(modifiedDesc, subtype, "2");
			
			mcc.delete(CacheUtil.oStorage(playerId, Constants.DEFAULT_WEAPON_TYPE));
			mcc.delete(CacheUtil.sStorage(playerId, Constants.DEFAULT_WEAPON_TYPE));
			mcc.delete(CacheUtil.oStorage(playerId, Constants.DEFAULT_PART_TYPE));
			mcc.delete(CacheUtil.sStorage(playerId, Constants.DEFAULT_PART_TYPE));		
			//mcc.delete(CacheUtil.oWeaponPack(playerId, 1));
			mcc.delete(CacheUtil.oWeaponsInAllPacks(playerId));
			mcc.delete(CacheUtil.sWeaponPack(playerId, 1));
			//mcc.delete(CacheUtil.oWeaponPack(playerId, 2));
			mcc.delete(CacheUtil.sWeaponPack(playerId, 2));
			
			playerItemDao.updateModifiedDesc(userId, playerId, weaponId, newModifiedDesc);
			List<ItemMod> result = itemModDao.getItemMod(itemMod);
			if(result!=null && result.size()>0){
				itemModDao.update(itemMod);
			}
			else{
//				itemModDao.create(itemMod);
			}
		}else{
			throw new NotEquipException();
		}
	}
	/**
	 * method to re-equip weapon with a array parts 
	 * @param userId
	 * @param playerId
	 * @param weaponId
	 * @param array
	 * @throws Exception
	 */
	public void createItemMod(Integer userId,Integer playerId,Integer weaponId, String array) throws Exception {
		String part[] = null;
		int num=0;
		String parts="";
		String seqs="";
		
		PlayerItem weapon = getService.getPlayerItemByItemId(userId,playerId, Constants.DEFAULT_WEAPON_TYPE, weaponId);
		String newModifiedDesc=weapon.getSModifiedDesc();
		if (array != null && !"".equals(array.trim())) {
			part = array.split(",");
		}
		if (part != null && part.length != 0) {
			for (int i = 0; i < part.length; i++) {
				parts += part[i+1]+",";
				seqs +=part[i]+",";
				newModifiedDesc=StringUtil.updateModifiedDesc(newModifiedDesc, StringUtil.toInt(part[i]), Constants.NUM_TWO+"");
				num++;
				i++;
			}
			parts=parts.substring(0, parts.length()-1);
			seqs=seqs.substring(0, seqs.length()-1);
		}
		
		mcc.delete(CacheUtil.oStorage(playerId, Constants.DEFAULT_WEAPON_TYPE));
		mcc.delete(CacheUtil.sStorage(playerId, Constants.DEFAULT_WEAPON_TYPE));
		mcc.delete(CacheUtil.oStorage(playerId, Constants.DEFAULT_PART_TYPE));
		mcc.delete(CacheUtil.sStorage(playerId, Constants.DEFAULT_PART_TYPE));
		//mcc.delete(CacheUtil.oWeaponPack(playerId, 1));
		mcc.delete(CacheUtil.oWeaponsInAllPacks(playerId));
		mcc.delete(CacheUtil.sWeaponPack(playerId, 1));
		//mcc.delete(CacheUtil.oWeaponPack(playerId, 2));
		mcc.delete(CacheUtil.sWeaponPack(playerId, 2));
		itemModDao.create(weaponId, num, parts, seqs,newModifiedDesc);
		
	}
	//===============================================================================	
	//								  Message Service
	//===============================================================================
	/**
	 * common method to create email
	 * @param message
	 * @throws Exception
	 */
	@Transactional
	private void createMessageCommon(int userId,Message message)throws Exception{
		message=messageDao.createMessage(message);
		if (Constants.BOOLEAN_YES.equals(message.getIsAttached())) {
			messageDao.createMessageItem(message);
		}
		int playerId=message.getReceiverId();
		Player player=getService.getPlayerById(playerId);
		int num=getService.getNewMessageNum(userId, playerId);
		soClient.pushCMD(player.getName(),"{"+CommonUtil.messageFormat(CommonMsg.REFRESH_EMAIL_NUM, num)+"}");
	}
	public void testPushCmd(){
		soClient.pushCMD("bobby",CommonUtil.messageFormat(CommonMsg.REFRESH_SHOP, 5));
	}
	/**
	 * method to create email 
	 * @param senderId
	 * @param receiverName
	 * @param subject
	 * @param content
	 * @throws Exception
	 */
	@Transactional
	public void createMessage(int userId,int senderId,String receiverName,String subject,String content)throws Exception{
		Player receiver=playerDao.getPlayerByName(receiverName);
		if(receiver==null){
			throw new NotFindPlayerException(ExceptionMessage.NOT_FIND_PLAYER);
		}
		Player sender=playerDao.getPlayerById(senderId);
		
		Message message=new Message();
		message.setSenderId(senderId);
		message.setReceiverId(receiver.getId());
		message.setSubject(subject);
		message.setContent(content);
	
		message.setSender(sender.getName());
		
		mcc.delete(CacheUtil.oPlayerMessage(receiver.getId()));
		mcc.delete(CacheUtil.sPlayerMessage(receiver.getId()));
		
		createMessageCommon(userId,message);
		
	}
	
	/**
	 * method to create a email with a item attachment 
	 * @param userId
	 * @param playerId
	 * @param receiverId
	 * @param itemId
	 * @param costType
	 * @return
	 * @throws Exception
	 */
	@Transactional
	public Map<String,String> createMessageWithItem(Integer userId,int playerId,int receiverId,int itemId,int costType)throws Exception{
		
		//1.get player info by playerId
		Player sender=playerDao.getPlayerById(playerId);
		//2.get item info by sysitemId
		SysItem sysItem=sysItemDao.getSystemItemById(itemId);
		
		mcc.delete(CacheUtil.oPlayer(playerId));
		mcc.delete(CacheUtil.sPlayer(playerId));
		mcc.delete(CacheUtil.oPlayerList(userId));
		mcc.delete(CacheUtil.sPlayerList(userId));
		mcc.delete(CacheUtil.oPlayerMessage(receiverId));
		mcc.delete(CacheUtil.sPlayerMessage(receiverId));
		int playerItemId=buyPlayerItem(userId, sender, sysItem, costType,Constants.BOOLEAN_YES);
		
		Message message=new Message();
		message.setSenderId(playerId);
		message.setReceiverId(receiverId);
		message.setSubject(CommonUtil.messageFormat(CommonMsg.GIFT_EMAIL, sender.getName()));
		message.setContent(CommonUtil.messageFormat(CommonMsg.GIFT_EMAIL, sender.getName()));
		message.setIsAttached(Constants.BOOLEAN_YES);
		message.setPlayerItemId(playerItemId);
		createMessageCommon(userId,message);
		
		Map<String,String> resultMap = new HashMap<String,String>();
		resultMap.put("gp", String.valueOf(sender.getGPoint()));
		resultMap.put("cr", String.valueOf(sender.getCredit()));
		return resultMap;
	}
	/**
	 * method to detach a attachment email 
	 * @param userId
	 * @param playerId
	 * @param messageId
	 * @throws Exception
	 */
	@Transactional
	public void detachMessageItem(Integer userId, int playerId, int messageId)throws Exception{
		Message message=getService.getMessage(userId, playerId, messageId);
		if("Y".equals(message.getIsAttached())){
			int playerItemId=message.getPlayerItemId();
			PlayerItem item=playerItemDao.getPlayerItemForMessage(playerItemId);
			Player player=playerDao.getPlayer(userId, playerId);
			mcc.delete(CacheUtil.oStorage(playerId,item.getType()));
			mcc.delete(CacheUtil.sStorage(playerId, item.getType()));
			mcc.delete(CacheUtil.oPlayerMessage(playerId));
			mcc.delete(CacheUtil.sPlayerMessage(playerId));
			mcc.delete(CacheUtil.oPlayer(playerId));
			mcc.delete(CacheUtil.sPlayer(playerId));
			if(item.getType()==Constants.DEFAULT_PACKAGE_TYPE){
				//package item 
				List<PlayerItem> list= playerItemDao.getGiftPackageById(userId, message.getSenderId(), playerItemId);
				if(list!=null){
					for(PlayerItem playerItem:list){
						SysItem sysItem=sysItemDao.getSystemItemById(playerItem.getItemId());
						playerItemDao.createPlayItem(userId, playerId, playerItem.getItemId(), sysItem.getModifiedDesc(),playerItem.getQuantity(), 
								Constants.BOOLEAN_NO,playerItem.getCurrency(),playerItem.getUnitType(),
								playerItem.getValidDate(), playerItem.getExpireDate());
						mcc.delete(CacheUtil.oStorage(playerId,sysItem.getType()));
						mcc.delete(CacheUtil.sStorage(playerId, sysItem.getType()));
					
					}
					// message item have a foreign key with playerItem
					// delete MESSAGEITEM first 
					messageDao.deleteMessageItem(message.getId());
					playerItemDao.deletePlayerItem(userId, playerId, playerItemId);
				}
			}else if(item.getType()==Constants.DEFAULT_ITEM_TYPE&&item.getIId()==Constants.NUM_TWO){
				//flower item
				boolean isToday=CommonUtil.isToday(player.getFlowerLastTime());
				playerDao.updateFlowerItem(userId, playerId,player,item.getQuantity(),isToday);
				messageDao.deleteMessageItem(message.getId());
				playerItemDao.deletePlayerItem(userId, playerId, playerItemId);
			}else if(item.getType()==Constants.DEFAULT_COSTUME_TYPE&&item.getSubType()==Constants.DEFAULT_FACE_SUBTYPE){
				//costume item
				mcc.delete(CacheUtil.oPlayer(playerId));
				mcc.delete(CacheUtil.sPlayer(playerId));
				//get Player
				String[] resourcesT = player.getResourceT().split(Constants.DELIMITER_RESOURCE_STABLE);
				resourcesT[2]=item.getName();
				String[] resourcesP = player.getResourceP().split(Constants.DELIMITER_RESOURCE_STABLE);
				resourcesP[2]=item.getName();
				playerDao.update("RESOURCE_T", CommonUtil.arrayToString(resourcesT), playerId);
				playerDao.update("RESOURCE_P", CommonUtil.arrayToString(resourcesP), playerId);
				
			}else{
				//other item
				messageDao.deleteMessageItem(message.getId());
				playerItemDao.updatePlayerForItem(userId, playerId, playerItemId);
			}
			
		
		}else{
			throw new Exception(ExceptionMessage.NOT_HAVE_ATTACH);
		}
	}
	//===============================================================================	
	//								  Pack service
	//===============================================================================
	
	
/*	@Transactional
	public void createNewWeaponPack(int uid, int cid, int packNo, PlayerItem playerItem) throws Exception  {
		Player p = playerDao.getPlayerById(cid);
		int weaponPackCount = p.getWPackSize();
		if (weaponPackCount + 1 > Constants.MAX_WEAPON_PACK_SIZE) {
			throw new Exception(ExceptionMessage.PACK_SIZE_MAX);
		}
		//1.  buy default weapon for player
		//TODO remove hard code
		int knifeId = playerItemDao.createDefaultKnife(uid, cid);
		Map<Integer,Integer> defaultWeapon = new HashMap<Integer,Integer>();
		defaultWeapon.put(3, knifeId);
		
		//2. create new weapon pack
		playerPackDao.createWeaponPack(uid, cid, Constants.SEQ_NUM, packNo, defaultWeapon, playerItem.getExpireDate());
		
		//3. update the pack size
		playerDao.update("W_PACK_SIZE",weaponPackCount + 1, cid);
	}*/
	
	//===============================================================================	
	//								  Injected Objects
	//===============================================================================	
	private MemcachedClient 	mcc;
	private UserDao 			userDao;
	private PlayerDao 			playerDao;
	private FriendDao 			friendDao;
	private ServerDao 			serverDao;
	private PlayerPackDao       playerPackDao;
	private PlayerItemDao       playerItemDao;
	private SysItemDao 			sysItemDao;
	private SysItemLogDao		sysItemLogDao;
	private ItemModDao			itemModDao;
	private MessageDao			messageDao;
	private GetService 			getService;
	private CharacterDao		characterDao;
	private CharacterLogDao		characterLogDao;
	private PlayerBuffDao		playerBuffDao;
	private ItemOrderDao 	    itemOrderDao;
	private TeamDao 	    	teamDao;
	private PlayerTeamDao       playerTeamDao;
	private SysLevelDao			sysLevelDao;
	private SDOComponent 	    sdoComponent;
	private SysModeConfigDao    sysModeConfigDao;
	private GoodsInfoConverter  goodsInfoConvertor = new GoodsInfoConverterImpl();
	private SocketClient soClient = SocketClient.getInstance();
	public void setCharacterDao(CharacterDao characterDao) {
		this.characterDao = characterDao;
	}
	public void setCharacterLogDao(CharacterLogDao characterLogDao) {
		this.characterLogDao = characterLogDao;
	}

	public void setGetService(GetService getService) {
		this.getService = getService;
	}
	public void setSysItemDao(SysItemDao sysItemDao) {
		this.sysItemDao = sysItemDao;
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

	public void setServerDao(ServerDao serverDao) {
		this.serverDao = serverDao;
	}

	public void setPlayerPackDao(PlayerPackDao playerPackDao) {
		this.playerPackDao = playerPackDao;
	}

	public void setPlayerItemDao(PlayerItemDao playerItemDao) {
		this.playerItemDao = playerItemDao;
	}
	
	public void setItemModDao(ItemModDao itemModDao) {
		this.itemModDao = itemModDao;
	}

	public void setSysItemLogDao(SysItemLogDao sysItemLogDao) {
		this.sysItemLogDao = sysItemLogDao;
	}

	public void setMessageDao(MessageDao messageDao) {
		this.messageDao = messageDao;

	}

	public void setItemOrderDao(ItemOrderDao itemOrderDao) {
		this.itemOrderDao = itemOrderDao;
	}

	public SDOComponent getSdoComponent() {
		return sdoComponent;
	}

	public void setSdoComponent(SDOComponent sdoComponent) {
		this.sdoComponent = sdoComponent;

	}

	public void setPlayerBuffDao(PlayerBuffDao playerBuffDao) {
		this.playerBuffDao = playerBuffDao;

	}


	public void setTeamDao(TeamDao teamDao) {
		this.teamDao = teamDao;
	}


	public void setPlayerTeamDao(PlayerTeamDao playerTeamDao) {
		this.playerTeamDao = playerTeamDao;
	}


	public void setSysLevelDao(SysLevelDao sysLevelDao) {
		this.sysLevelDao = sysLevelDao;
	}


	public void setSysModeConfigDao(SysModeConfigDao sysModeConfigDao) {
		this.sysModeConfigDao = sysModeConfigDao;
	}
}
