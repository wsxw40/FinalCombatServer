package com.pearl.o2o.service;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeoutException;

import net.rubyeye.xmemcached.exception.MemcachedException;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pde.log.common.LogMessage.Level;
import com.pearl.o2o.dao.impl.nonjoin.BaseMappingDao;
import com.pearl.o2o.enumuration.CostFunction;
import com.pearl.o2o.enumuration.CostType;
import com.pearl.o2o.enumuration.LogServerMessage;
import com.pearl.o2o.exception.BaseException;
import com.pearl.o2o.exception.CRNotEnoughException;
import com.pearl.o2o.exception.DataTooLongException;
import com.pearl.o2o.exception.DuplicatePlayerException;
import com.pearl.o2o.exception.EmptyInputException;
import com.pearl.o2o.exception.IllegalCharacterException;
import com.pearl.o2o.exception.NotBuyEquipmentException;
import com.pearl.o2o.exception.STONENotEnoughException;
import com.pearl.o2o.nosql.NoSql;
import com.pearl.o2o.nosql.NosqlKeyUtil;
import com.pearl.o2o.nosql.object.playerevent.BuyItemEvent;
import com.pearl.o2o.nosql.object.playerevent.LevelUpEvent;
import com.pearl.o2o.nosql.object.playerevent.PayCrEvent;
import com.pearl.o2o.nosql.object.playerevent.PayGpEvent;
import com.pearl.o2o.nosql.object.playerevent.PayStoneEvent;
import com.pearl.o2o.nosql.object.playerevent.PayVoucherEvent;
import com.pearl.o2o.nosql.object.playerevent.PresentEvent;
import com.pearl.o2o.nosql.object.playerevent.PurchaseEvent;
import com.pearl.o2o.pojo.AwardItemVo;
import com.pearl.o2o.pojo.BattleFieldRobDaily;
import com.pearl.o2o.pojo.BuyItemRecord;
import com.pearl.o2o.pojo.Character;
import com.pearl.o2o.pojo.CharacterData;
import com.pearl.o2o.pojo.Friend;
import com.pearl.o2o.pojo.GunProperty;
import com.pearl.o2o.pojo.Message;
import com.pearl.o2o.pojo.OnlineAward;
import com.pearl.o2o.pojo.PayLog;
import com.pearl.o2o.pojo.Payment;
import com.pearl.o2o.pojo.Player;
import com.pearl.o2o.pojo.PlayerAchievement;
import com.pearl.o2o.pojo.PlayerActivity;
import com.pearl.o2o.pojo.PlayerBuff;
import com.pearl.o2o.pojo.PlayerInfo;
import com.pearl.o2o.pojo.PlayerItem;
import com.pearl.o2o.pojo.PlayerMission;
import com.pearl.o2o.pojo.PlayerPack;
import com.pearl.o2o.pojo.PlayerTeam;
import com.pearl.o2o.pojo.Rank;
import com.pearl.o2o.pojo.SysAchievement;
import com.pearl.o2o.pojo.SysActivity;
import com.pearl.o2o.pojo.SysCharacter;
import com.pearl.o2o.pojo.SysCharacterLog;
import com.pearl.o2o.pojo.SysChest;
import com.pearl.o2o.pojo.SysConfiguration;
import com.pearl.o2o.pojo.SysItem;
import com.pearl.o2o.pojo.SysModeConfig;
import com.pearl.o2o.pojo.Team;
import com.pearl.o2o.pojo.TeamItem;
import com.pearl.o2o.pojo.TeamLevelInfo;
import com.pearl.o2o.pojo.TeamRecord;
import com.pearl.o2o.pojo.User;
import com.pearl.o2o.pojo.XunleiOrderLog;
import com.pearl.o2o.utils.BiochemicalConstants;
import com.pearl.o2o.utils.CacheUtil;
import com.pearl.o2o.utils.ChallengeHillStatus;
import com.pearl.o2o.utils.CommonMsg;
import com.pearl.o2o.utils.CommonUtil;
import com.pearl.o2o.utils.ComparatorUtil;
import com.pearl.o2o.utils.ConfigurationUtil;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.DaoCacheUtil;
import com.pearl.o2o.utils.DateUtil;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.GrowthMissionConstants;
import com.pearl.o2o.utils.KeywordFilterUtil;
import com.pearl.o2o.utils.LogUtils;
import com.pearl.o2o.utils.QuietBounds;
import com.pearl.o2o.utils.ServiceLocator;
import com.pearl.o2o.utils.StringUtil;
import com.pearl.o2o.utils.TOPTeam;
import com.pearl.o2o.utils.TeamConstants;
import com.pearl.o2o.utils.TeamUtils;
import com.pearl.o2o.utils.ZYZDZUndefinedItem;
import com.pearl.o2o.utils.GrowthMissionConstants.GrowthMissionType;

/**
 * @author bobby
 * 
 */
public class CreateService extends BaseService {

	static Logger logger = LoggerFactory.getLogger(CreateService.class
			.getName());
	protected MessageService messageService;

	public void initConfigInCache() throws Exception {
		// mcc.set(CacheUtil.isRunSynCacheTask(), 0, ,Constants.CACHE_TIMEOUT);
		getService.getAvailableActivitiesMap();
	}

	public boolean getIsRunSynCacheTask() {
		Map<String, String> result = systemDao.getSysConfig();
		String isRunSynCacheTask = result.get("isRunSynCacheTask");
		if ("0".equals(isRunSynCacheTask)) {
			return false;
		} else {
			return true;
		}
	}

	public void closeIsRunSynCacheTask() {
		systemDao.setValue("isRunSynCacheTask", "1");
	}

	public void openIsRunSynCacheTask() {
		systemDao.setValue("isRunSynCacheTask", "0");
	}

	// ===============================================================================
	// player activity Services
	// ===============================================================================
	public String sendActivityGift(final Player player,PlayerActivity playerActivity) throws Exception {
//		final StringBuilder content = new StringBuilder(CommonUtil.messageFormat(CommonMsg.FINISH_ACTIVITY, playerActivity.getSysActivity().getName()));
		StringBuilder ids = new StringBuilder("");
		SysItem item = playerActivity.getSysActivity().getSysItem();
		if (null != item) {
			if (item.getType() == Constants.DEFAULT_PACKAGE_TYPE) {// 大礼包
				packageActivity(item, player,playerActivity.getSysActivity());
				ids.append(item.getId()).append(",");
			} else {
				awardActivity(player, item, ids,playerActivity.getSysActivity());
			}
		}
		// 邮件通知
//		sendSystemMail(player, CommonMsg.GIFT_EMAIL_SYS, content.toString(),ids.toString());
		return Converter.awardList(playerActivity);
	}

	// ===============================================================================
	// User Services
	// ===============================================================================
	public User createUser(String userName, String password) {
		User user = new User();
		user.setUserName(userName);
		user.setPassword(password);
		return userDao.create(user);
	}

	public void createSysModeConfig(Integer type, String config)
			throws Exception {
		SysModeConfig modeConfig = new SysModeConfig();
		modeConfig.setType(type);
		modeConfig.setConfig(config);
		sysModeConfigDao.createSysModeConfigDao(modeConfig);
	}

	public void createSysConfig(String key, String value) throws Exception {
		SysConfiguration sc = new SysConfiguration();
		sc.setKey(key);
		sc.setValue(value);
		systemDao.createSysConfig(sc);
	}

	// ===============================================================================
	// Character Services
	// ===============================================================================

	public void createCharacter(SysCharacter character) throws Exception {
		mcc.delete(CacheUtil.oSysCharacterList());

		character = sysCharacterDao.createSysCharacter(character);
		SysCharacterLog characterLog = new SysCharacterLog();
		characterLog.setSysCharacter(character);
		characterLog.setLogVersion(0);
		sysCharacterLogDao.createSysCharacterLog(characterLog);
	}

	// ===============================================================================
	// Team Services
	// ===============================================================================
	private int createTeam(Team team) throws Exception {
		return teamDao.createTeam(team);
	}

	/**
	 * Method to create team with a team item
	 * 
	 * @param playerId
	 * @param type:
	 *            Team Item's type
	 * @param playerItemId:
	 *            Team Item's ID
	 * @param name:
	 *            Team name
	 * @param declaration:
	 *            Team declaration
	 * @param description:
	 *            Team description
	 * @param board:
	 *            Team board
	 * @param logo:
	 *            Team logo icon
	 * @return boolean
	 */

	public void createTeamByItem(Integer playerId, String name,
			String description, String logo, String province, String city,
			String rank, int playerItemId) throws Exception {
		// 1.check if been a team member
		PlayerTeam pt = playerTeamDao.getByPlayerId(playerId);
		if (pt != null && pt.getApproved().equals(Constants.BOOLEAN_YES)) {
			throw new BaseException(ExceptionMessage.NOT_CREATE_TEAM);
		} else if (pt != null && pt.getApproved().equals(Constants.BOOLEAN_NO)) {
			playerTeamDao.remove(pt.getTeamId(), pt.getPlayerId());
		}
		Player player = getService.getPlayerById(playerId);
		PlayerItem playerItem = null;
		if (playerItemId > 0) {
			playerItem = getService.getPlayerItemByItemId(playerId,
					Constants.DEFAULT_ITEM_TYPE, playerItemId);
			if (playerItem == null || playerItem.getQuantity() <= 0) {
				throw new BaseException(ExceptionMessage.NOT_PLAYER_ITEM);
			}
			useItemById(player, 4, playerItemId, null, 0, 0);
		} else {
			// judge level 20
			if (player.getRank() < Constants.CREATE_TEAM_LIMIT) {
				throw new BaseException(ExceptionMessage.NOT_CREATE_TEAM_1);
			}
			if (player.getGPoint() < 10000) {
				throw new BaseException(ExceptionMessage.NOT_CREATE_TEAM_2);
			}
			player.setGPoint((player.getGPoint() - 10000));
			soClient.messageUpdatePlayerMoney(player);
		}

		// 3.remove all application history of this player
		playerTeamDao.removeAllApplicationFromPlayer(playerId);
		playerDao.updatePlayerInCache(player);
		mcc.delete(CacheUtil.oPlayer(playerId));
		mcc.delete(CacheUtil.sPlayer(playerId));
		// 4.create team
		Team team = new Team();
		team.setName(name);
		team.setDescription(description);
		team.setLogo(logo);
		// team.setSize(StringUtil.toInt(playerItem.getSysItem().getIValue()));
		team.setSize(55);
		team.setProvince(province);
		team.setCity(city);
		team.setRank(Integer.valueOf(rank));
		team.setNumber(1);
		team.setTeamSpaceLevel(1);
		team.setUnusableResource(Constants.DEFAULT_TEAM_UNUSABLE_RESOURCE);
		team.setUsableResource(Constants.DEFAULT_TEAM_USABLE_RESOURCE);
		int teamId = createTeam(team);
		ServiceLocator.updateService.updateTeamTop(team, 4);//更新战队最新排行
		// 5.create captain(the creator) for team
		PlayerTeam playerTeam = new PlayerTeam();
		playerTeam.setPlayerId(playerId);
		playerTeam.setTeamId(teamId);
		playerTeam.setKillNum(0);
		playerTeam.setDeadNum(0);
		playerTeam.setJob(TeamConstants.TEAMJOB.TEAM_CAPTAIN.getValue());
		playerTeam.setApproved(Constants.BOOLEAN_YES);
		//vip有上次退出战队保留的贡献
		if(player.getIsVip()>=1){
			playerTeam.setContribution(player.getVipRemainsTeamExp());
			player.setVipRemainsTeamExp(0);
		
			ServiceLocator.updateService.updatePlayerInfoOnly(player);
		}
		playerTeamDao.createPlayerTeam(playerTeam);
		soClient.updateCharacterInfo(player, name, player.getRank());
		// useItemById(player.getId(), Constants.DEFAULT_ITEM_TYPE,
		// playerItemId, null, 0, 0 );
		// GM使用
		mcc.delete(CacheUtil.oTeamList());
		mcc.delete(CacheUtil.oDefaultTeamList());
		
		
		//create team level
		createTeamLevel(teamId);
	}
	public void createPlayerPack(PlayerPack pack) throws Exception{
		playerPackDao.create(pack);
	}
	// ===============================================================================
	// Player Services
	// ===============================================================================
	/**
	 * Method to create player
	 * 
	 * @param name:player
	 *            name
	 * @param id:sys
	 *            character id
	 * @param side:default
	 *            side for player
	 * @return Player
	 * @throws Exception
	 */

	public Player createPlayer(String userName, String name,int accountType) throws Exception {
		// 1.check list:
		// empty input;too long input;illegal character input;duplicate
		// player;player size
		if (name == null || "".equals(name)) {
			throw new EmptyInputException(
					ExceptionMessage.EMPTY_STR_CREATE_PLAYER);
		}
		//FIXME
		if (name.length() > StringUtil.toInt(getService.getSysConfig().get("playername.maxlength"))) {
			throw new DataTooLongException(
					ExceptionMessage.TOO_LONG_CREATE_PLAYER);
		}else if(name.length()< StringUtil.toInt(getService.getSysConfig().get("playername.minlength"))){
			throw new BaseException(ExceptionMessage.TOO_SHORT_CREATE_PLAYER);
		}
		if (StringUtil.filter(name)) {
			throw new IllegalCharacterException(
					ExceptionMessage.ILLEGAL_CHARACTER_CREATE_PLAYER);
		}
		if (!KeywordFilterUtil.isLegalInput(name)) {
			throw new IllegalCharacterException(
					ExceptionMessage.ILLEGAL_CHARACTER_CREATE_PLAYER);
		}
		int size = playerDao.duplicatePlayerName(name);
		if (size > 0) {
			throw new DuplicatePlayerException();
		}
		// 2.get sys character,load the default parameter for player
		Player player = new Player();
		player.setName(name);
		player.setUserName(userName);
		// player.setCredit(Constants.DEFAULT_PLAYER_CR);
		player.setVoucher(Constants.DEFAULT_PLAYER_V);
		player.setCharacterSize(Constants.DEFAULT_CHARACTER_SIZE);
		player.setTutorial(Constants.TUTORIAL_DEFAULT);
		player.setLastLoginIp("127.0.0.1");
		player.setLastLoginTime((int) (System.currentTimeMillis() / 1000));
		player.setLastLogoutTime((int) (System.currentTimeMillis() / 1000));
		player.setIsNew(0);
		player.setIsVip(0);
		player.setIcon(Constants.DEFAULT_PLAYER_ICON);
		player.setTitle("");
		player.setAchievement("");
		player.setTotal(getService.getVisibleSysAchievementSize());
		player.setCharacters(Constants.DEFAULT_CHARACTERS);
		player.setSelectedCharacters(Constants.DEFAULT_CHARACTERS_SELECT);
		player.setCreateTime(new Date());
		player.setIsXunlei(0);
		player.setUnusableResource(Constants.DEFAULT_PLAYER_UNUSABLE_RESOURCE);
		player.setUsableResource(Constants.DEFAULT_PLAYER_USABLE_RESOURCE);
//		FIXME for test
		player.setRank(Constants.DEFAULT_PLAYER_RANK);
		player.setExp(Constants.DEFAULT_PLAYER_EXP);
		player.setGPoint(Constants.DEFAULT_PLAYER_GP);// for test
		player.setTutorial(GrowthMissionConstants.DEFAULTTUTORIAL);
		
//		player.setRank(20);
//		player.setExp(118306);
//		player.setGPoint(9999999);
//		player.setTutorial("22222222");// for test
		player.setIsCheck(Constants.NUM_ONE);
		player.setMaxFightNum(Constants.DEFAULT_PLAYER_FIGHTNUM);//设置玩家默认战斗力
		player.setAccountType(accountType);
		player.setpResourceBeginTime((int) (System.currentTimeMillis() / 1000));
		// 3.create player
		return createCommonPlayer(player);
	}
	private Player createCommonPlayer(Player player) throws Exception {
		player = playerDao.create(player);
		for (int i = 1; i <= Constants.MAX_CHARACTER_SIZE; i++) {
			// init player pack
			PlayerPack pack = new PlayerPack();
			for (int k = 1; k <= Constants.DEFAULT_COSTUME_SEQ_SIZE; k++) {
				SysItem sysItem = getService.getSysItemByItemId(Constants.costumePack[i - 1][k - 1]);
				int playerItemId =playerItemDao.createPlayItem(player.getId(), sysItem, 1, 0, Constants.BOOLEAN_NO, Constants.BOOLEAN_YES, Constants.BOOLEAN_YES);
				pack.setPackId(1);
				pack.setExpireTime(null);
				pack.setPlayerId(player.getId());
				pack.setPlayerItemId(playerItemId);
				pack.setSeq(k);
				pack.setSysCharacterId(i);
				pack.setType("C");
				playerPackDao.create(pack);
			}
			for (int k = 1; k <= Constants.DEFAULT_SEQ_SIZE; k++) {
				if (Constants.weaponPack[i - 1][k - 1] == 0) {
					pack.setPackId(1);
					pack.setExpireTime(null);
					pack.setPlayerId(player.getId());
					pack.setPlayerItemId(null);
					pack.setSeq(k);
					pack.setSysCharacterId(i);
					pack.setType("W");
					playerPackDao.create(pack);
				} else {
					SysItem sysItem = getService.getSysItemByItemId(Constants.weaponPack[i - 1][k - 1]);
					int playerItemId =playerItemDao.createPlayItem(player.getId(), sysItem, 1, 0, Constants.BOOLEAN_NO, Constants.BOOLEAN_YES, Constants.BOOLEAN_YES);
					pack.setPackId(1);
					pack.setExpireTime(null);
					pack.setPlayerId(player.getId());
					pack.setPlayerItemId(playerItemId);
					pack.setSeq(k);
					pack.setSysCharacterId(i);
					pack.setType("W");
					playerPackDao.create(pack);
				}
			}
			
			mcc.delete(CacheUtil.oCostumePack(player.getId(), 1, i));
			mcc.delete(CacheUtil.oWeaponPack(player.getId(), 1, i));
			CharacterData characterData = new CharacterData();
			characterData.setCharacterId(i);
			characterData.setPlayerId(player.getId());
			characterDataDao.create(characterData);
		}
//		NoSql nosql = nosqlService.getNosql();
//		nosql.zAdd(NosqlKeyUtil.commonLevelNumInRealTopByType("kScore"), 0, String.valueOf(player.getId()));
//		nosql.zAdd(NosqlKeyUtil.commonLevelNumInRealTopByType("kAssist"), 0, String.valueOf(player.getId()));
//		nosql.zAdd(NosqlKeyUtil.commonLevelNumInRealTopByType("kWinRate"), 0.0, String.valueOf(player.getId()));
//		nosql.zAdd(NosqlKeyUtil.commonLevelNumInRealTopByType("kKillDead"), 0.0, String.valueOf(player.getId()));
		// boss
		CharacterData characterData = new CharacterData();
		characterData.setCharacterId(120);
		characterData.setPlayerId(player.getId());
		characterDataDao.create(characterData);
		
		createPlayerInfo(player.getId());
		
		for(int i:Constants.defalutItems){
			int playerItemId =playerItemDao.createPlayItem(player.getId(), getService.getSysItemByItemId(i), 1, 1, Constants.BOOLEAN_NO, Constants.BOOLEAN_YES, Constants.BOOLEAN_NO);
		}
		// characterDataDao.initCharacterData(player.getId());
		final Player finalPlayer = player;
		Runnable task = new Runnable() {
			@Override
			public void run() {
				try {
					messageService.sendWelcomeMail(finalPlayer);
				} catch (Exception e) {
					logger.warn("Exception in createCommonPlayer", e);
				}

			}
		};
		ServiceLocator.asynTakService.submit(task);
		return player;
	}

	public PlayerInfo createPlayerInfo(int playerId)throws Exception{
		PlayerInfo playerInfo = new PlayerInfo();
		playerInfo.setId(playerId);
		playerInfo.setPlayerId(playerId);
		playerInfo.setXunleiPoint(Constants.DEFAULT_PLAYER_CR);
		return  playerInfoDao.create(playerInfo);
	}
	// ===============================================================================
	// Player Item Services
	// ===============================================================================
	//
	public int buyPlayerItemList(StringBuffer check,int playerId, List<String[]> playeritems, int packId, int characterId) throws Exception {
		Player player = getService.getPlayerById(playerId);
		PlayerInfo playerInfo = getService.getPlayerInfoById(playerId);
		List<SysItem> sysItemList = new ArrayList<SysItem>();
		List<Integer> paymentIdList = new ArrayList<Integer>();

		int fcCost = 0;
		int cCost = 0;

		for (String[] str : playeritems) {
			int sysItemId = StringUtil.toInt(str[0]);
			// int type = StringUtil.toInt(str[1]);
			int payId = StringUtil.toInt(str[2]);
			Payment payment = getService.getPaymentById(sysItemId, payId);
			SysItem sysItem = getService.getSysItemByItemId(sysItemId);
			if(sysItem.getIsVip()> player.getIsVip()){
				throw new BaseException(ExceptionMessage.NOT_VIP_BUY);
			}
			if(sysItem.getIsWeb()==1 && player.getInternetCafe()==0){
				throw new BaseException(ExceptionMessage.NOT_WEB_BUY);
			}
			sysItemList.add(sysItem);
			paymentIdList.add(payment.getId());
			if (Constants.GP_PAY == payment.getPayType()) {
				cCost += payment.getCost();
			} else if (Constants.CR_PAY == payment.getPayType()) {
				fcCost += payment.getCost();
			}
		}

		if (fcCost > playerInfo.getXunleiPoint() && cCost > player.getGPoint()) {
			return -3;
		}
		if (fcCost > playerInfo.getXunleiPoint()) {
			return -2;
		}
		if (cCost > player.getGPoint()) {
			return -1;
		}

		for (int i = 0; i < sysItemList.size(); i++) {
			SysItem sysItem = sysItemList.get(i);
			int type = sysItem.getType();
			int paymentId = paymentIdList.get(i);
			//zlm2015-8-4-start 8-26-改
			//游戏内市场玩家身上物品的一键购买，玩家会用非正规渠道购买别的物品
			Payment payment =getService.getPaymentById(sysItem.getId(), paymentId);
			if (!CommonUtil.isZYZDZBuff(sysItem)// 不对资源争夺战的物品进行判断
					&& (	Constants.BOOLEAN_YES.equals(sysItem.getIsDeleted()) || (payment != null && payment
							.getIsShow() == 0))		)
				throw new NotBuyEquipmentException(	ExceptionMessage.NOT_HAVE_PAYTYPE);
			//zlm2015-8-4-end	
			int playerItemId = buyPlayerItem(check,player.getUserName(), player, sysItem, paymentId, Constants.BOOLEAN_NO, packId);
			if (packId == 1 && playerItemId != 0) {
				if (type == Constants.DEFAULT_WEAPON_TYPE) {
					ServiceLocator.updateService.updateWeaponPackEquipment(playerId, type, playerItemId, characterId);
				} else if (type == Constants.DEFAULT_COSTUME_TYPE) {
					ServiceLocator.updateService.updateCostumePackEquipment(playerId, type, playerItemId, characterId);
				} else if (type == Constants.DEFAULT_PART_TYPE) {
					ServiceLocator.updateService.updateCostumePackEquipment(playerId, type, playerItemId, characterId);
				}
			}
		}
		return 0;
	}

	/**
	 * method to buy player Item
	 * 
	 * @param check (can be null, just used to log the process)
	 * @param player
	 * @param sysItem
	 * @param costType:the
	 *            cost number
	 * @param isGift:is
	 *            buying for a gift
	 * @return playerItemId
	 * @throws Exception
	 */
	
	public int buyPlayerItem(StringBuffer check,String userName, Player player, SysItem sysItem, Integer costId, String isGift, int useIt, final Player... p) throws Exception {
		Payment payment =getService.getPaymentById(sysItem.getId(), costId);
		if(CommonUtil.isZYZDZBuff(sysItem)){
			int count=getService.getBuyItemRecordCount(player.getId(), sysItem.getId());
			payment=CommonUtil.initZYZDZBuffPayment(payment, count, true);
			tryCreateShopBuyItemRecord(player.getId(),sysItem.getId(),costId,payment,sysItem);
		}
		return buyPlayerItem(check,userName, player, sysItem, payment, isGift, useIt, p);
	}
	
	public int buyPlayerItem(StringBuffer check,String userName, Player player, SysItem sysItem, Payment payment, String isGift, int useIt, final Player... p) throws Exception {
		// 1.check the player's rank
		int playerId = player.getId();
		Player receiver = null;
		if(p!=null&&p.length>=1)
			receiver=p[0];
		PlayerInfo playerInfo = getService.getPlayerInfoById(playerId);
		int playerItemId = -1;
		if (player.getRank() < sysItem.getLevel()&&sysItem.getType()!=Constants.DEFAULT_ZYZDZ_TYPE) {
			throw new NotBuyEquipmentException(ExceptionMessage.NOT_MATCH_LEVEL_BUY);
		}
		
		if (sysItem.getIsVip() >= 1) {
			if (player.getIsVip() < sysItem.getIsVip()) {
				throw new NotBuyEquipmentException(ExceptionMessage.NOT_VIP_BUY);
			}
		}
		if(sysItem.getIsWeb() == 1 && player.getInternetCafe() == 0){
			throw new NotBuyEquipmentException(ExceptionMessage.NOT_WEB_BUY);
		}
		// 2.get the item's cost,unit,unitType,currency
		int gp = player.getGPoint();
		int cr = playerInfo.getXunleiPoint();

		int voucher = player.getVoucher();
		if (payment == null) {
			throw new NotBuyEquipmentException(ExceptionMessage.NOT_HAVE_PAYTYPE);
		}
		// 3.update the money things
		int payTpye = payment.getPayType();
		int cost = payment.getCost();
		// int discount=ServiceLocator.updateService.updatePlayerActivity(Constants.ACTION_ACTIVITY.DISCOUNT_ACTIVITY.getValue(),0, null, sysItem.getId(), 0,null,null);
		// if(discount!=1){
		// cost=cost*discount/100;
		// }
		if(sysItem.getType()==Constants.DEFAULT_MATERIAL_TYPE){
			useIt=1;
		}
		String isBind = useIt == 1 ? Constants.BOOLEAN_YES : Constants.BOOLEAN_NO;
		// 判断大礼包
		boolean isPresentPackage = (sysItem.getType() == Constants.DEFAULT_PACKAGE_TYPE);
		int leftMoney = 0;
		int frontPay = 0;
//		int playerAction = 0;
		if (Constants.GP_PAY == payTpye) {
			if (gp < cost) {
				throw new NotBuyEquipmentException(ExceptionMessage.NOT_ENOUGH_GP);// not have enough GPoint
			} else {
				frontPay = gp;
				leftMoney = gp - cost;
				player.setGPoint(leftMoney);
				player.setFunGpTotal(player.getFunGpTotal() + cost);
				
				//add log for have paid
				if(check!=null){
					check.append("paid "+cost+" GP|");
				}
				// 发布购买事件
				if (!Constants.BOOLEAN_YES.equals(isGift)&&!(sysItem.getIBuffId()>=61&&sysItem.getIBuffId()<=74)) {//战队buff:ibuffId:61-74,不会显示通知
					nosqlService.publishEvent(new PurchaseEvent(DateUtil.getCurrentTimeStr(), player.getId(), player.getName(), sysItem));
				}
				// 发布道具
				if (isPresentPackage && !Constants.BOOLEAN_YES.equals(isGift)) {
					// 购买
					packageToPlayer(player, sysItem, null, new StringBuilder(), isGift);
					playerItemId = 1;
				} else {
					playerItemId = sendItem(sysItem, player, payment, isGift, isBind, Constants.BOOLEAN_NO);
				}
				//add log for have sent sysItem
				if(check!=null){
					check.append("get item "+playerItemId+" |");
				}
				
				// player.setCredit(getService.getCR(userId));
				playerDao.updatePlayerInCache(player);
				mcc.delete(CacheUtil.oPlayer(playerId));
				mcc.delete(CacheUtil.sPlayer(playerId));
				soClient.messageUpdatePlayerMoney(player);
				//add log for finish process
				if(check!=null){
					check.append("su\t");
				}
				
				nosqlService.publishEvent(new PayGpEvent(cost, System.currentTimeMillis() / 1000, player.getId(), player.getName()));
			}
		} else if (Constants.CR_PAY == payTpye) {//使用FC币进行消费
			if (cr < cost) {
				throw new CRNotEnoughException(ExceptionMessage.NOT_ENOUGH_CR);// not have enough FC point
			} else {
				frontPay = cr;
				leftMoney = cr - cost;
				ServiceLocator.updateService.updatePlayerActivity(Constants.ACTION_ACTIVITY.PAY_FC.getValue(), playerId, null, cost, 0, null, null);
				playerInfo.setXunleiPoint(leftMoney);
				playerInfoDao.update(playerInfo);
				mcc.delete(CacheUtil.oPlayerInfo(playerId));
				//add log for have paid
				if(check!=null){
					check.append("paid "+cost+" CR|");
				}
				
				// 发布购买事件
				if (!Constants.BOOLEAN_YES.equals(isGift)&&!(sysItem.getIBuffId()>=61&&sysItem.getIBuffId()<=74)) {//战队buff:ibuffId:61-74,不会显示通知
					nosqlService.publishEvent(new PurchaseEvent(DateUtil.getCurrentTimeStr(), player.getId(), player.getName(), sysItem));
				}
				// 发布道具
				if (isPresentPackage && !Constants.BOOLEAN_YES.equals(isGift)) {
					packageToPlayer(player, sysItem, null, new StringBuilder(), isGift);
					playerItemId = 1;
				} else {
					playerItemId = sendItem(sysItem, player, payment, isGift, isBind, Constants.BOOLEAN_NO);
				}
				//add log for have sent sysItem
				if(check!=null){
					check.append("get item "+playerItemId+" |");
				}
				
//				playerInfoDao.update1(playerInfo);
//				mcc.delete(CacheUtil.oPlayerInfo(playerId));
				playerDao.updatePlayerInCache(player);
				mcc.delete(CacheUtil.oPlayer(playerId));
				mcc.delete(CacheUtil.sPlayer(playerId));
				soClient.messageUpdatePlayerMoney(player);
				//add log for finish process
				if(check!=null){
					check.append("su\t");
				}
				nosqlService.publishEvent(new PayCrEvent(cost, System.currentTimeMillis() / 1000, player.getId(), player.getName()));
				
				// 成长任务34：首次购买 
				if(cost > 0){ //首次购买，物品价值必须大于0
					ServiceLocator.updateService.updatePlayerGrowthMission(player, GrowthMissionType.FIRST_PURCHASE);
				}
				ServiceLocator.updateService.addPlayerTrack(playerId, "", 0, 0, cost, DateUtil.getDf().format(new Date()), "2"+Constants.XUNLEI_LOG_DELIMITER+sysItem.getId()+Constants.XUNLEI_LOG_DELIMITER+payment.getUnitType()+Constants.XUNLEI_LOG_DELIMITER+payment.getUnit());
				
			}

//			if (isGift == Constants.BOOLEAN_YES) {
//				playerAction = Constants.ACTION.GIFTCR.getValue();
//			} else {
//				playerAction = Constants.ACTION.USECR.getValue();
//			}
		} else if (Constants.V_PAY == payTpye&&!(sysItem.getIBuffId()>=61&&sysItem.getIBuffId()<=74)) {//战队buff:ibuffId:61-74,不会显示通知
			if (voucher < cost) {
				throw new NotBuyEquipmentException(ExceptionMessage.NOT_ENOUGH_V);
			} else {
				frontPay = voucher;
				leftMoney = voucher - cost;
				player.setVoucher(leftMoney);
				
				//add log for have paid
				if(check!=null){
					check.append("paid "+cost+" V|");
				}
				// 发布购买事件
				if (!Constants.BOOLEAN_YES.equals(isGift)&&!(sysItem.getIBuffId()>=61&&sysItem.getIBuffId()<=74)) {
					nosqlService.publishEvent(new PurchaseEvent(DateUtil.getCurrentTimeStr(), player.getId(), player.getName(), sysItem));
				}
				// 发布道具
				if (isPresentPackage && !Constants.BOOLEAN_YES.equals(isGift)) {
					packageToPlayer(player, sysItem, null, new StringBuilder(), isGift);
					playerItemId = 1;
				} else {
					playerItemId = sendItem(sysItem, player, payment, isGift, isBind, Constants.BOOLEAN_NO);
				}
				//add log for have sent sysItem
				if(check!=null){
					check.append("get item "+playerItemId+" |");
				}
				
				playerDao.updatePlayerInCache(player);
				mcc.delete(CacheUtil.oPlayer(playerId));
				mcc.delete(CacheUtil.sPlayer(playerId));
				soClient.messageUpdatePlayerMoney(player);
				//add log for finish process
				if(check!=null){
					check.append("su\t");
				}
				
				nosqlService.publishEvent(new PayVoucherEvent(cost, System.currentTimeMillis() / 1000, player.getId(), player.getName()));
			}
		}else if(Constants.MD_PAY == payTpye){
			int chipNum=getService.getMedolNumByPlayerId(playerId);
			if(chipNum < cost){
				throw new NotBuyEquipmentException(ExceptionMessage.NOT_ENOUGH_CHIP);
			}else{
				frontPay = chipNum;
				leftMoney = chipNum - cost;
				
				SysItem medal = getService.getSysItemByIID(Constants.DEFAULT_MEDAL_IID,Constants.DEFAULT_ITEM_TYPE).get(0);
				List<PlayerItem> list = getService.getPlayerItems(playerId, Constants.DEFAULT_ITEM_TYPE, 0, 0);
				int totalNum= cost;
				for(PlayerItem pi : list) {  
					if(pi!=null && pi.getItemId()==medal.getId()){
						if(pi.getQuantity()>=totalNum){
							ServiceLocator.deleteService.deleteCombiningItem(pi, totalNum);
							break;
						}else{
							totalNum=totalNum-pi.getQuantity();
							ServiceLocator.deleteService.deleteCombiningItem(pi, pi.getQuantity());
						}
					}
				}
				//add log for have paid
				if(check!=null){
					check.append("paid "+cost+" MD|");
				}
				
				CommonUtil.sortMaterial(playerId, Constants.DEFAULT_MATERIAL_TYPE , medal.getId());
				if (!Constants.BOOLEAN_YES.equals(isGift)&&!(sysItem.getIBuffId()>=61&&sysItem.getIBuffId()<=74)) {//战队buff:ibuffId:61-74,不会显示通知
					nosqlService.publishEvent(new PurchaseEvent(DateUtil.getCurrentTimeStr(), player.getId(), player.getName(), sysItem));
				}
				if(sysItem.getIId() == 8){	//购买C币直接增加账户不做道具形式发放操作
					//int unit = payment.unit
					awardCALToPlayer(player,(payment.getUnit()*Integer.parseInt(sysItem.getIValue())));
					//player.setGPoint(player.getGPoint() + (payment.getUnit()*Integer.parseInt(sysItem.getIValue())));
					playerItemId = 1;
//					playerDao.updatePlayerInCache(player);
//					mcc.delete(CacheUtil.oPlayer(playerId));
//					mcc.delete(CacheUtil.sPlayer(playerId));
//					soClient.messageUpdatePlayerMoney(player);
				}else{
					// 发布道具
					if (isPresentPackage && !Constants.BOOLEAN_YES.equals(isGift)) {
						packageToPlayer(player, sysItem, payment, new StringBuilder(), isGift);
						playerItemId = 1;
					} else {
						playerItemId = sendItem(sysItem, player, payment, isGift, Constants.BOOLEAN_YES, Constants.BOOLEAN_NO);
					}
				}
				//add log for have sent sysItem and finish process
				if(check!=null){
					check.append("get item "+playerItemId+" |su\t");
				}
				if (ConfigurationUtil.SWITCH_XUNLEI_LOG.getIsOn()) {
					nosqlService.addXunleiLog("7.4"
							+ Constants.XUNLEI_LOG_DELIMITER + player.getUserName()
							+ Constants.XUNLEI_LOG_DELIMITER + player.getName()
							+ Constants.XUNLEI_LOG_DELIMITER + 0
							+ Constants.XUNLEI_LOG_DELIMITER + cost
							+ Constants.XUNLEI_LOG_DELIMITER + leftMoney
							+ Constants.XUNLEI_LOG_DELIMITER+ 999
							+ Constants.XUNLEI_LOG_DELIMITER+ CommonUtil.simpleDateFormat.format(new Date())
							);
				}
				
				if(sysItem.getId() == GrowthMissionConstants.UPGARDMOD_ID){
					//成长任务：使用勋章购买强化部件
					ServiceLocator.updateService.updatePlayerGrowthMission(player, GrowthMissionType.BUY_STRENGTH_BY_MEDAL);
				}
			}
		}else if(Constants.RES_PAY == payTpye){//使用个人黑晶石进行付费
			Team team=getService.getTeamByPlayerId(player.getId());
			HashMap<String, Integer> playerRes=player.getLatestPlayerRes(team.getTeamSpaceLevel());
			int stones= team==null ? 0 : playerRes.get(Player.RES);//可用黑晶石
			if (stones < cost) {
				//throw new NotBuyEquipmentException(ExceptionMessage.NOT_ENOUGH_GP);
				throw new STONENotEnoughException(ExceptionMessage.LACK_P_USABLE_STONE);// 需要进行国际化
			} else {
				frontPay = stones;
				leftMoney = stones - cost;
				player.setUsableResource(leftMoney);
				
				/**这里应该是FunStoneTotal*/
				//player.setFunGpTotal(player.getFunGpTotal() + cost);
				
				//add log for have paid
				if(check!=null){
					check.append("paid "+cost+" RES_PAY|");
				}
				// 发布购买事件
				if (!Constants.BOOLEAN_YES.equals(isGift)&&!(sysItem.getIBuffId()>=61&&sysItem.getIBuffId()<=74)) {//战队buff:ibuffId:61-74,不会显示通知
					nosqlService.publishEvent(new PurchaseEvent(DateUtil.getCurrentTimeStr(), player.getId(), player.getName(), sysItem));
				}
				// 发布道具
				if (isPresentPackage && !Constants.BOOLEAN_YES.equals(isGift)) {
					// 购买
					packageToPlayer(player, sysItem, null, new StringBuilder(), isGift);
					playerItemId = 1;
				} else {
					playerItemId = sendItem(sysItem, player, payment, isGift, isBind, Constants.BOOLEAN_NO);
				}
				//add log for have sent sysItem
				if(check!=null){
					check.append("get item "+playerItemId+" |");
				}
				
				// player.setCredit(getService.getCR(userId));
				playerDao.updatePlayerInCache(player);
				mcc.delete(CacheUtil.oPlayer(playerId));
				mcc.delete(CacheUtil.sPlayer(playerId));
				soClient.messageUpdatePlayerMoney(player);
				//add log for finish process
				if(check!=null){
					check.append("su\t");
				}
				
				nosqlService.publishEvent(new PayStoneEvent(cost, System.currentTimeMillis() / 1000, player.getId(), player.getName()));
			}
		}else if(Constants.RES_DIS_PAY == payTpye){//使用个人黑原石进行付费
			Team team=getService.getSimpleTeamById(player.getTeamId());
			HashMap<String, Integer> playerRes=player.getLatestPlayerRes(team.getTeamSpaceLevel());
			int disStones= team==null ? 0 : playerRes.get(Player.ORG_RES);//不可用资源
			if (disStones < cost) {
				//throw new NotBuyEquipmentException(ExceptionMessage.NOT_ENOUGH_GP);
				throw new STONENotEnoughException(ExceptionMessage.LACK_P_UNUSABLE_STONE);// 需要进行国际化
			} else {
				frontPay = disStones;
				leftMoney = disStones - cost;
				player.setUnusableResource(leftMoney);
				
				/**这里应该是FunStoneTotal*/
				//player.setFunGpTotal(player.getFunGpTotal() + cost);
				
				//add log for have paid
				if(check!=null){
					check.append("paid "+cost+" RES_DIS_PAY|");
				}
				// 发布购买事件
				if (!Constants.BOOLEAN_YES.equals(isGift)&&!(sysItem.getIBuffId()>=61&&sysItem.getIBuffId()<=74)) {//战队buff:ibuffId:61-74,不会显示通知
					nosqlService.publishEvent(new PurchaseEvent(DateUtil.getCurrentTimeStr(), player.getId(), player.getName(), sysItem));
				}
				// 发布道具
				if (isPresentPackage && !Constants.BOOLEAN_YES.equals(isGift)) {
					// 购买
					packageToPlayer(player, sysItem, null, new StringBuilder(), isGift);
					playerItemId = 1;
				} else {
					playerItemId = sendItem(sysItem, player, payment, isGift, isBind, Constants.BOOLEAN_NO);
				}
				//add log for have sent sysItem
				if(check!=null){
					check.append("get item "+playerItemId+" |");
				}
				
				// player.setCredit(getService.getCR(userId));
				playerDao.updatePlayerInCache(player);
				mcc.delete(CacheUtil.oPlayer(playerId));
				mcc.delete(CacheUtil.sPlayer(playerId));
				soClient.messageUpdatePlayerMoney(player);
				//add log for finish process
				if(check!=null){
					check.append("su\t");
				}
				
				nosqlService.publishEvent(new PayStoneEvent(cost, System.currentTimeMillis() / 1000, player.getId(), player.getName()));
			}
		}else if (Constants.REVIVE_COIN_PAY == payTpye) {	//复活币消费
			//整理仓库
			final Integer type = Constants.DEFAULT_ITEM_TYPE;
			if (type == Constants.DEFAULT_MATERIAL_TYPE || type == Constants.DEFAULT_OPEN_TYPE) {
				CommonUtil.sortAllMaterial(playerId, type);
			}
			if(type == Constants.DEFAULT_ITEM_TYPE){
				CommonUtil.sortMaterialsBySubType(playerId, type, Constants.DEFAULT_ITEM_SUBTYPE.COST.getValue());
			}
			
			int chipNum= ServiceLocator.getService.getPlayerItemsTotalQuantityByIid(playerId, Constants.DEFAULT_ITEM_TYPE, Constants.SPECIAL_ITEM_IIDS.RELIVE_COIN.getValue());
			List<PlayerItem> items = ServiceLocator.getService.getPlayerItemByItemIid(playerId, Constants.DEFAULT_ITEM_TYPE, Constants.SPECIAL_ITEM_IIDS.RELIVE_COIN.getValue());
			
			int costClone=cost;
			if (chipNum < cost) {
				throw new NotBuyEquipmentException(ExceptionMessage.NOT_ENOUGH_REVIVE_COIN);// not have enough REVIVE_COIN
			} else {
//				frontPay = chipNum;
//				leftMoney = chipNum - cost;
//				for(PlayerItem item : items){
//					int qy = item.getQuantity();
//					if(cost > 0){
//						item.setQuantity(qy<cost?0:(qy-cost));
//						cost -= qy;
//						if(item.getQuantity()==0)
//							item.setIsDeleted("Y");
//						playerItemDao.updatePlayerItem(item);
//					}else{
//						break;
//					}
//				}

				frontPay = chipNum;
				leftMoney = chipNum - cost;
				int totalNum = cost;
				for (PlayerItem pi : items) {
					if (pi.getQuantity() >= totalNum) {
						ServiceLocator.deleteService.deleteCombiningItem(pi,totalNum);
						break;
					} else {
						totalNum = totalNum - pi.getQuantity();
						ServiceLocator.deleteService.deleteCombiningItem(pi, pi.getQuantity());
					}
				}
				
				
//				player.setGPoint(leftMoney);
//				player.setFunGpTotal(player.getFunGpTotal() + cost);
				
				//add log for have paid
				if(check!=null){
					check.append("paid "+costClone+" REVIVE_COIN_PAY|");
				}
				
				check.append("cb up "+player.getGPoint()+" |"+sysItem.getIId()+" |");
				
				if(sysItem.getIId() == 8){	//购买C币直接增加账户不做道具形式发放操作
					//int unit = payment.unit
					awardCALToPlayer(player,(payment.getUnit()*Integer.parseInt(sysItem.getIValue())));
					//player.setGPoint(player.getGPoint() + (payment.getUnit()*Integer.parseInt(sysItem.getIValue())));
					playerItemId = 1;
				}else{
					// 发布购买事件
//					if (!Constants.BOOLEAN_YES.equals(isGift)&&!(sysItem.getIBuffId()>=61&&sysItem.getIBuffId()<=74)) {//战队buff:ibuffId:61-74,不会显示通知
//						nosqlService.publishEvent(new PurchaseEvent(DateUtil.getCurrentTimeStr(), player.getId(), player.getName(), sysItem));
//					}
//					// 发布道具
//					if (isPresentPackage && !Constants.BOOLEAN_YES.equals(isGift)) {
//						// 购买
//						packageToPlayer(player, sysItem, null, new StringBuilder(), isGift);
//						playerItemId = 1;
//					} else {
//						playerItemId = sendItem(sysItem, player, payment, isGift, isBind, Constants.BOOLEAN_NO);
//					}
				}
				
				
				check.append("cb cu "+player.getGPoint()+" |");
				
				//add log for have sent sysItem
				if(check!=null){
					check.append("get item "+playerItemId+" |");
				}
				
				// player.setCredit(getService.getCR(userId));
//				playerDao.updatePlayerInCache(player);
//				mcc.delete(CacheUtil.oPlayer(playerId));
//				mcc.delete(CacheUtil.sPlayer(playerId));
//				soClient.messageUpdatePlayerMoney(player);
				//add log for finish process
				if(check!=null){
					check.append("su\t");
				}
				
				nosqlService.publishEvent(new PayGpEvent(cost, System.currentTimeMillis() / 1000, player.getId(), player.getName()));
			}
		}else if (Constants.A_CHIP_PAY == payTpye||Constants.B_CHIP_PAY == payTpye||Constants.C_CHIP_PAY == payTpye) {	//C币消费
			//整理仓库
			final Integer type = Constants.DEFAULT_ITEM_TYPE;
			if (type == Constants.DEFAULT_MATERIAL_TYPE || type == Constants.DEFAULT_OPEN_TYPE) 
				CommonUtil.sortAllMaterial(playerId, type);
			if(type == Constants.DEFAULT_ITEM_TYPE)
				CommonUtil.sortMaterialsBySubType(playerId, type, Constants.DEFAULT_ITEM_SUBTYPE.COST.getValue());
			int chipNum=getService.getPlayerItemsTotalQuantityByIid(playerId, Constants.DEFAULT_MATERIAL_TYPE, payTpye);
			List<PlayerItem> items = getService.getPlayerItemByItemIid(playerId, Constants.DEFAULT_MATERIAL_TYPE, payTpye);
			int costClone=cost;
			if (chipNum < cost) {
				if (Constants.A_CHIP_PAY == payTpye) 
					throw new NotBuyEquipmentException(ExceptionMessage.NOT_ENOUGH_A_COIN);// not have enough REVIVE_COIN
				if (Constants.B_CHIP_PAY == payTpye) 
					throw new NotBuyEquipmentException(ExceptionMessage.NOT_ENOUGH_B_COIN);// not have enough REVIVE_COIN
				if (Constants.C_CHIP_PAY == payTpye) 
					throw new NotBuyEquipmentException(ExceptionMessage.NOT_ENOUGH_C_COIN);// not have enough REVIVE_COIN
			} else {
				frontPay = chipNum;
				leftMoney = chipNum - cost;
				int totalNum= cost;
				for(PlayerItem pi : items) {  
						if(pi.getQuantity()>=totalNum){
							ServiceLocator.deleteService.deleteCombiningItem(pi, totalNum);
							break;
						}else{
							totalNum=totalNum-pi.getQuantity();
							ServiceLocator.deleteService.deleteCombiningItem(pi, pi.getQuantity());
						}
				}
				if(check!=null)	check.append("paid "+costClone+" REVIVE_COIN_PAY|");
				check.append(payTpye+"_Chip up "+player.getGPoint()+" |"+sysItem.getIId()+" |");
				if(sysItem.getIId() == 8){	//购买C币直接增加账户不做道具形式发放操作
					awardCALToPlayer(player,(payment.getUnit()*Integer.parseInt(sysItem.getIValue())));
					playerItemId = 1;
				}else{
					// 发布道具
					if (isPresentPackage && !Constants.BOOLEAN_YES.equals(isGift)) {
						packageToPlayer(player, sysItem, payment, new StringBuilder(), isGift);
						playerItemId = 1;
					} else {
						playerItemId = sendItem(sysItem, player, payment, isGift, Constants.BOOLEAN_YES, Constants.BOOLEAN_NO);
					}
				}
				check.append(payTpye+"_Chip cu "+player.getGPoint()+" |");
				if(check!=null)	check.append("get item "+playerItemId+" |");
				if(check!=null)	check.append("su\t");
				nosqlService.publishEvent(new PayGpEvent(cost, System.currentTimeMillis() / 1000, player.getId(), player.getName()));
			}
		}
		savePayLog(player, sysItem, payTpye, leftMoney, payment.getCost());
		
		if(ConfigurationUtil.SWITCH_XUNLEI_LOG.getIsOn()){
			nosqlService.addXunleiLog("1.2" + Constants.XUNLEI_LOG_DELIMITER+ player.getUserName() + 
					Constants.XUNLEI_LOG_DELIMITER+ player.getName() + 
					Constants.XUNLEI_LOG_DELIMITER+ sysItem.getId() + 
					Constants.XUNLEI_LOG_DELIMITER+ sysItem.getDisplayNameCN() +
					Constants.XUNLEI_LOG_DELIMITER+ (payment.getUnitType()==1?payment.getUnit():1) +
					Constants.XUNLEI_LOG_DELIMITER+ payment.getPayType() +
					Constants.XUNLEI_LOG_DELIMITER + payment.getCost()    
					+ Constants.XUNLEI_LOG_DELIMITER+ (payment.getUnitType() == 2 ? payment.getUnit() : "")
					+ Constants.XUNLEI_LOG_DELIMITER+ CommonUtil.simpleDateFormat.format(new Date())
					+ Constants.XUNLEI_LOG_DELIMITER + frontPay
					+ Constants.XUNLEI_LOG_DELIMITER + leftMoney
					+ Constants.XUNLEI_LOG_DELIMITER + 1
					+ Constants.XUNLEI_LOG_DELIMITER + ""+ Constants.XUNLEI_LOG_DELIMITER + ""
					+ Constants.XUNLEI_LOG_DELIMITER + player.getRank());
			
			if(sysItem.getId() == GrowthMissionConstants.RELIVE_COIN){
				int reliveCoinNum=getService.getReliveCoinNumByPlayerId(playerId);
				nosqlService.addXunleiLog("18.1"
						+ Constants.XUNLEI_LOG_DELIMITER + player.getUserName()
						+ Constants.XUNLEI_LOG_DELIMITER + player.getName()
						+ Constants.XUNLEI_LOG_DELIMITER + 1
						+ Constants.XUNLEI_LOG_DELIMITER + payment.getUnit()
						+ Constants.XUNLEI_LOG_DELIMITER + reliveCoinNum
						+ Constants.XUNLEI_LOG_DELIMITER + 1
						+ Constants.XUNLEI_LOG_DELIMITER + CommonUtil.simpleDateFormat.format(new Date())
						);
			}
			
		}
		return playerItemId;
	}
	
	/**
	 * 记录玩家的每日购买记录，用于按照购买次数计算，目前只有资源争夺战购买buff是走的这条路
	 * @throws Exception 
	 */
	private void tryCreateShopBuyItemRecord(int playerId,int sysItemId,int costId,Payment payment,SysItem sysItem){
		try {
			if(CommonUtil.isZYZDZBuff(sysItem)){
				BuyItemRecord buyItemRecord=getService.getBuyItemRecord(playerId, sysItemId);
				if(buyItemRecord==null){
					buyItemRecord=createBuyItemRecord(playerId, sysItemId, costId, payment.getPayType(), payment.getCost());
				}
				buyItemRecord.setRecord(buyItemRecord.getRecord()+1);
				ServiceLocator.updateService.updateBuyItemRecord(buyItemRecord);
			}
		} catch (Exception e) {
			logger.warn(e.getMessage(), e);
		}
	}
	
	
	public int buyTeamItem(StringBuffer check,String userName, Player player, SysItem sysItem, Integer costId, String isGift, int useIt, final Player... p) throws Exception {
		Payment payment = getService.getPaymentById(sysItem.getId(), costId);
		return buyTeamItem(check,userName, player, sysItem, payment, isGift, useIt, p);
	}		
	
	public int buyTeamItem(StringBuffer check,String userName, Player player, SysItem sysItem, Payment payment, String isGift, int useIt, final Player... p) throws Exception {
		// 1.check the player's teams's level
		int playerId = player.getId();
		PlayerInfo playerInfo = getService.getPlayerInfoById(playerId);
		Team team=getService.getTeamByPlayerId(playerInfo.getId());
		int teamItemId = -1;
//		if (team.getTeamSpaceActive() < sysItem.getLevel()) {//战队等级不够
//			throw new NotBuyEquipmentException(ExceptionMessage.NOT_MATCH_LEVEL_BUY);
//		}
		
		if (sysItem.getIsVip() >= 1) {
			if (player.getIsVip() < sysItem.getIsVip()) {
				throw new NotBuyEquipmentException(ExceptionMessage.NOT_VIP_BUY);
			}
		}
		if(sysItem.getIsWeb() == 1 && player.getInternetCafe() == 0){
			throw new NotBuyEquipmentException(ExceptionMessage.NOT_WEB_BUY);
		}
		// 2.get the item's cost,unit,unitType,currency
		int cr = playerInfo.getXunleiPoint();
		HashMap<String, Integer> teamResHashMap=team.getLatestTeamRes();
		int teamStones=teamResHashMap.get(Team.RES);
		if (payment == null) {
			throw new NotBuyEquipmentException(ExceptionMessage.NOT_HAVE_PAYTYPE);
		}
		// 3.update the money things
		int payTpye = payment.getPayType();
		int cost = payment.getCost();
		
		
		/**是否绑定*/
//{
//		if(sysItem.getType()==Constants.DEFAULT_MATERIAL_TYPE){
//			useIt=1;
//		}
//		String isBind = useIt == 1 ? Constants.BOOLEAN_YES : Constants.BOOLEAN_NO;
		String isBind=Constants.BOOLEAN_NO;
//	}
		
		// 判断大礼包
		boolean isPresentPackage = (sysItem.getType() == Constants.DEFAULT_PACKAGE_TYPE);
		
		int leftMoney = 0;
		int frontPay = 0;
		if (Constants.CR_PAY == payTpye) {
			if (cr < cost) {
				throw new CRNotEnoughException(ExceptionMessage.NOT_ENOUGH_CR);// not have enough FC point
			} else {
				frontPay = cr;
				leftMoney = cr - cost;
				ServiceLocator.updateService.updatePlayerActivity(Constants.ACTION_ACTIVITY.PAY_FC.getValue(), playerId, null, cost, 0, null, null);
				playerInfo.setXunleiPoint(leftMoney);
				playerInfoDao.update(playerInfo);
				mcc.delete(CacheUtil.oPlayerInfo(playerId));
				//add log for have paid
				if(check!=null){
					check.append("paid "+cost+" CR|");
				}
				
				// 发布购买事件
				if (!Constants.BOOLEAN_YES.equals(isGift)&&!(sysItem.getIBuffId()>=61&&sysItem.getIBuffId()<=74)) {//战队buff:ibuffId:61-74,不会显示通知
					nosqlService.publishEvent(new PurchaseEvent(DateUtil.getCurrentTimeStr(), player.getId(), player.getName(), sysItem));
				}
				// 发布道具
				teamItemId = sendTeamItem(sysItem, team.getId(), payment, isGift, isBind, Constants.BOOLEAN_NO).getId();
				
				//add log for have sent sysItem
				if(check!=null){
					check.append("get item "+teamItemId+" |");
				}
				
//				playerInfoDao.update1(playerInfo);
//				mcc.delete(CacheUtil.oPlayerInfo(playerId));
				playerDao.updatePlayerInCache(player);
				mcc.delete(CacheUtil.oPlayer(playerId));
				mcc.delete(CacheUtil.sPlayer(playerId));
				soClient.messageUpdatePlayerMoney(player);
				//add log for finish process
				if(check!=null){
					check.append("su\t");
				}
				nosqlService.publishEvent(new PayCrEvent(cost, System.currentTimeMillis() / 1000, player.getId(), player.getName()));
				
				// 成长任务34：首次购买
				ServiceLocator.updateService.updatePlayerGrowthMission(player, GrowthMissionType.FIRST_PURCHASE);
				
				ServiceLocator.updateService.addPlayerTrack(player.getId(), "", 0, 0, payment.getCost(), DateUtil.getDf().format(new Date()), "2"+Constants.XUNLEI_LOG_DELIMITER+sysItem.getId()+Constants.XUNLEI_LOG_DELIMITER+payment.getUnitType()+Constants.XUNLEI_LOG_DELIMITER+payment.getUnit());
				
			}
		} else if(Constants.RES_PAY_TEAM == payTpye){//使用团队黑晶石进行付费
			if (teamStones < cost) {
				throw new STONENotEnoughException(ExceptionMessage.LACK_T_USABLE_STONE);// 需要进行国际化
			} else {
				frontPay = teamStones;
				leftMoney = teamStones - cost;
				team.setUsableResource(leftMoney);
				
				if(check!=null){
					check.append("paid "+cost+" RES_PAY_TEAM|");
				}
				// 发布购买事件
				if (!Constants.BOOLEAN_YES.equals(isGift)&&!(sysItem.getIBuffId()>=61&&sysItem.getIBuffId()<=74)) {//战队buff:ibuffId:61-74,不会显示通知
					nosqlService.publishEvent(new PurchaseEvent(DateUtil.getCurrentTimeStr(), player.getId(), player.getName(), sysItem));
				}
				
				teamItemId = sendTeamItem(sysItem, team.getId(), payment, isGift, isBind, Constants.BOOLEAN_NO).getId();
				
				//add log for have sent sysItem
				if(check!=null){
					check.append("get item "+teamItemId+" |");
				}
				teamDao.updateTeam(team);
				mcc.delete(CacheUtil.oTeam(team.getId()));
				
				if(check!=null){
					check.append("su\t");
				}
				
				nosqlService.publishEvent(new PayStoneEvent(cost, System.currentTimeMillis() / 1000, player.getId(), player.getName()));
			}
		}
		final PayLog payLog = new PayLog();
		payLog.setUserId(userName);
		payLog.setPlayerId(player.getId());
		payLog.setItemId(sysItem.getId());
		payLog.setItemName(sysItem.getDisplayNameCN());
		payLog.setItemType(sysItem.getType());
		payLog.setPayType(payment.payType);
		payLog.setPayAmount(payment.getCost());
		payLog.setCreateTime(new Date());
		payLog.setLeftMoney(leftMoney);
		
		if (isGift == Constants.BOOLEAN_YES&&payTpye==Constants.CR_PAY) {
			ServiceLocator.updateService.updatePlayerAchievementNotInStageClear(player, Constants.ACTION.GIFTCR.getValue(), cost);
		} else if(payTpye==Constants.CR_PAY){
			ServiceLocator.updateService.updatePlayerAchievementNotInStageClear(player, Constants.ACTION.USECR.getValue(), cost);
		}
		
		if (!Constants.BOOLEAN_YES.equals(isGift)) {
			nosqlService.publishEvent(new BuyItemEvent(System
					.currentTimeMillis() / 1000, player.getId(), player
					.getName(), sysItem));
			if (ConfigurationUtil.SWITCH_XUNLEI_LOG.getIsOn()) {
				nosqlService.addXunleiLog("1.2"
						+ Constants.XUNLEI_LOG_DELIMITER + player.getUserName()
						+ Constants.XUNLEI_LOG_DELIMITER + player.getName()
						+ Constants.XUNLEI_LOG_DELIMITER + sysItem.getId()
						+ Constants.XUNLEI_LOG_DELIMITER
						+ sysItem.getDisplayNameCN()
						+ Constants.XUNLEI_LOG_DELIMITER
						+ (payment.getUnitType() == 1 ? payment.getUnit() : 1)
						+ Constants.XUNLEI_LOG_DELIMITER + payment.getPayType()
						+ Constants.XUNLEI_LOG_DELIMITER + cost
						+ Constants.XUNLEI_LOG_DELIMITER
						+ (payment.getUnitType() == 2 ? payment.getUnit() : "")
						+ Constants.XUNLEI_LOG_DELIMITER
						+ CommonUtil.simpleDateFormat.format(new Date())
						+ Constants.XUNLEI_LOG_DELIMITER + frontPay
						+ Constants.XUNLEI_LOG_DELIMITER + leftMoney
						+ Constants.XUNLEI_LOG_DELIMITER + 1
						+ Constants.XUNLEI_LOG_DELIMITER + ""
						+ Constants.XUNLEI_LOG_DELIMITER + ""
						+ Constants.XUNLEI_LOG_DELIMITER + player.getRank());
				
			}
			payLog.setPayUse(1);
		} else {
			if (ConfigurationUtil.SWITCH_XUNLEI_LOG.getIsOn()) {
				nosqlService.addXunleiLog("1.2"
						+ Constants.XUNLEI_LOG_DELIMITER + player.getUserName()
						+ Constants.XUNLEI_LOG_DELIMITER + player.getName()
						+ Constants.XUNLEI_LOG_DELIMITER + sysItem.getId()
						+ Constants.XUNLEI_LOG_DELIMITER + sysItem.getDisplayNameCN()
						+ Constants.XUNLEI_LOG_DELIMITER + (payment.getUnitType() == 1 ? payment.getUnit() : 1)
						+ Constants.XUNLEI_LOG_DELIMITER + payment.getPayType()
						+ Constants.XUNLEI_LOG_DELIMITER + cost
						+ Constants.XUNLEI_LOG_DELIMITER + (payment.getUnitType() == 2 ? payment.getUnit() : "")
						+ Constants.XUNLEI_LOG_DELIMITER + CommonUtil.simpleDateFormat.format(new Date())
						+ Constants.XUNLEI_LOG_DELIMITER + frontPay
						+ Constants.XUNLEI_LOG_DELIMITER + leftMoney
						+ Constants.XUNLEI_LOG_DELIMITER + 3
						+ Constants.XUNLEI_LOG_DELIMITER + p[0].getUserName()
						+ Constants.XUNLEI_LOG_DELIMITER + p[0].getName()
						+ Constants.XUNLEI_LOG_DELIMITER + player.getRank());
				
			}
			payLog.setPayUse(3);
		}
		{
			infoLogger.log(LogServerMessage.secondLog.name(), Level.INFO_INT, 
					LogUtils.JoinerByTab.join("5.1",player.getUserName(),CommonUtil.simpleDateFormat.format(new Date()),cost,payment.getPayType(),sysItem.getDisplayNameCN(),
							player.getName(),sysItem.getId(),payment.getUnitType(),payment.getUnit(),leftMoney));


		}
		if (ConfigurationUtil.SWITCH_XUNLEI_LOG.getIsOn()) {
			Date expireDate = new Date();
			if (!isPresentPackage) {
				//				expireDate = teamItem.getExpireDate();
			}
//			nosqlService.addXunleiLog("1.4"
//					+ Constants.XUNLEI_LOG_DELIMITER
//					+ player.getUserName()
//					+ Constants.XUNLEI_LOG_DELIMITER
//					+ player.getName()
//					+ Constants.XUNLEI_LOG_DELIMITER
//					+ sysItem.getId()
//					+ Constants.XUNLEI_LOG_DELIMITER
//					+ sysItem.getDisplayNameCN()
//					+ Constants.XUNLEI_LOG_DELIMITER
//					+ (payment.getUnitType() == 2 ? payment.getUnit() : "")
//					+ Constants.XUNLEI_LOG_DELIMITER
//					+ CommonUtil.simpleDateFormat.format(new Date())
//					+ Constants.XUNLEI_LOG_DELIMITER
//					+ (payment.getUnitType() == 2 ? CommonUtil.simpleDateFormat
//							.format(expireDate) : ""));
			
			nosqlService.addXunleiLog("1.4"
					+ Constants.XUNLEI_LOG_DELIMITER
					+ player.getUserName()
					+ Constants.XUNLEI_LOG_DELIMITER
					+ player.getName()
					+ Constants.XUNLEI_LOG_DELIMITER
					+ sysItem.getId()
					+ Constants.XUNLEI_LOG_DELIMITER
					+ sysItem.getDisplayNameCN()
					+ Constants.XUNLEI_LOG_DELIMITER
					+ (payment.getUnitType() == 2 ? payment.getUnit() : "")
					+ Constants.XUNLEI_LOG_DELIMITER
					+ CommonUtil.simpleDateFormat.format(new Date())
					+ Constants.XUNLEI_LOG_DELIMITER);
		}
		
		//just for log into dc
		final int payUnit=payment.getUnit();
		final int payUnitType=payment.getUnitType();
		final Runnable writePayLog = new Runnable() {
			@Override
			public void run() {
				createPayLog(payLog);

				String message = String.format(
						"%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s", payLog.getPlayerId(),CommonUtil.simpleDateFormat.format(new Date()),payLog.getPayAmount(),payLog.getPayType(),
						payLog.getItemName(),payLog.getItemId(),payLog.getItemType(),payUnitType,payUnit,payLog.getPayUse());
				// 记到analyser server
				transferDataToDc.addLog("bjItemCost", message);
			}
		};	
		ServiceLocator.asynTakService.execute(writePayLog);
		
		return teamItemId;
	}	
	
	public TeamItem sendTeamItem(SysItem sysItem, int teamId, Payment payment,String isGift,String isBind,String isDefault) throws Exception {
		 int teamItemId=teamItemDao.createOrUpdateTeamItem(teamId, sysItem, payment.getUnit(), payment.getUnitType(), isGift, isBind, isDefault,0);
		 TeamItem ti=getService.getTeamItemById(teamItemId);
		 return ti;
	}
	public TeamItem createTeamItem(int teamId, SysItem sysItem, int unit,int unitType,String isGift,String isBind,String isDefault,int usedCount) throws Exception{
		int teamItemId=teamItemDao.createOrUpdateTeamItem(teamId, sysItem, unit, unitType, isGift, isBind, isDefault,usedCount);
		 TeamItem ti=getService.getTeamItemById(teamItemId);
		 return ti;
	}
	/**
	 * 用于个人空间，个人第一次进入时没有坦克和buff，会创建一个个数为0的playerItem
	 * @param teamId
	 * @param sysItem
	 * @param unit
	 * @param unitType
	 * @param isGift
	 * @param isBind
	 * @param isDefault
	 * @return
	 * @throws Exception
	 */
	public int createPlayerItem(int playerId, SysItem sysItem, int unit,int unitType,String isGift,String isBind,String isDefault,Date lastCreateDate) throws Exception{
		int retult= playerItemDao.createPlayItemForPersonalPlace(playerId, sysItem, unit, unitType, isGift, isBind, isDefault,lastCreateDate);
		//由于以上dao层在存储cache的时候，造成cache中没有完整的 zyzdz物品列表，（只有tank1，没有buff）所以在资源争夺战商城购买物品的时候，
		//chache中type的0_0列表就没有buff类！会造成直接去新增了一个新的buff（数据库已有该buff，不过数量为0），造成个人buff分堆叠放，
		//没有找到增加的方法，顾这里人工清除一下，让找的时候到数据库去找。
		ServiceLocator.deleteService.deletePlayerItemInMemcached(playerId, sysItem);
		return retult;
		
	}
	/**
	 * create PLAYERITEM to DB
	 * @param sysItem
	 * @param player
	 * @param payment unitType=0 the first payment(visible or invisible) 
	 * @param isGift "N"
	 * @param isBind "N"
	 * @param isDefault "N"
	 * @return
	 * @throws Exception
	 */
	public int sendItem(SysItem sysItem, Player player, Payment payment,String isGift,String isBind,String isDefault) throws Exception {
		if(isGift==null){
			isGift=Constants.BOOLEAN_NO;
		}
		if(isBind==null){
			isBind=Constants.BOOLEAN_NO;
		}
		//zlm2015-5-7-限时装备-开始---------判断id段来判定是否绑定----------------------------
		isBind=sysItem.getProvisional_item_flag()? Constants.BOOLEAN_YES:isBind;
		//zlm2015-5-7-限时装备-结束-------------------------------------
		//ugly
		if(Constants.BOOLEAN_YES.equals(isBind)||sysItem.getType()==Constants.DEFAULT_MATERIAL_TYPE||(sysItem.getType()==Constants.DEFAULT_ITEM_TYPE&&sysItem.getSubType()==7)){
			isBind=Constants.BOOLEAN_YES;
		}else if(sysItem.getIBuffId()==Constants.DEFAULT_ON_CARD_BUFF){	//I_BUFF_ID为月卡时
			isBind=Constants.BOOLEAN_YES;
		}else if(sysItem.getIId() == 59 || sysItem.getIId() == 60){
			isDefault=Constants.BOOLEAN_YES;;
		}
		
		if(isDefault==null){
			isDefault=Constants.BOOLEAN_NO;
		}
		
		
		int playerItemId = 0;
		int unitType = 0;
		int unit = 1;
		if (null == payment) {
			if (null != sysItem.getAllGpPricesList()&& sysItem.getAllGpPricesList().size() > 0) {
				unitType = sysItem.getAllGpPricesList().get(0).getUnitType();
				unit = sysItem.getAllGpPricesList().get(0).getUnit();
			} else if (null != sysItem.getAllCrPricesList()&& sysItem.getAllCrPricesList().size() > 0) {
				unitType = sysItem.getAllCrPricesList().get(0).getUnitType();
				unit = sysItem.getAllCrPricesList().get(0).getUnit();
			} else if (null != sysItem.getAllVoucherPricesList()&& sysItem.getAllVoucherPricesList().size() > 0) {
				unitType = sysItem.getAllVoucherPricesList().get(0).getUnitType();
				unit = sysItem.getAllVoucherPricesList().get(0).getUnit();
			}
		} else {
			unit = payment.getUnit();
			unitType = payment.getUnitType();
		}
		
		payment=new Payment(unit,unitType);
		List<PlayerItem> piList = null;
		if(needToPlusPersonalItem(sysItem, isGift)) {//materials & open type must overlap
			piList = getService.getPlayerItemListByItemId(player.getId(), sysItem.getId(), sysItem.getType());
			if(piList.isEmpty()){
				playerItemId = playerItemDao.createPlayItem(player.getId(), sysItem, unit, unitType, isGift, isBind, isDefault);
			} else {
				if(sysItem.getType()!=Constants.DEFAULT_ZYZDZ_TYPE){//非资源争夺战物品打包方式

					boolean isExist = false;
					
					//payment.getUnit > Constants.MAX_STACK_SIZE
					int redundantUnit=payment.getUnit()%Constants.MAX_STACK_SIZE;
					int maxSizeUnit=payment.getUnit()/Constants.MAX_STACK_SIZE;
					
					while(maxSizeUnit>0){
						playerItemId = playerItemDao.createPlayItem(player.getId(), sysItem, Constants.MAX_STACK_SIZE, unitType, isGift, isBind, isDefault);
						
						maxSizeUnit--;
					}
					
					for(PlayerItem each : piList){
						if((each.getQuantity() +  redundantUnit) <= Constants.MAX_STACK_SIZE){
							playerItemId = each.getId();
							each.setQuantity((each.getQuantity() +  redundantUnit));
							each.setPlayerItemUnitType(payment.getUnitType());
							if(sysItem.getType()==Constants.DEFAULT_MATERIAL_TYPE){
								each.setIsBind("Y");
							}
							if(sysItem.getType()==Constants.DEFAULT_OPEN_TYPE&&(sysItem.getIId()==20||sysItem.getIId()==22)){
								each.setIsBind("Y");
							}
							playerItemDao.updatePlayerItem(each);
							isExist = true;
							break;
						}
					}
					if(!isExist){
						playerItemId = playerItemDao.createPlayItem(player.getId(), sysItem, redundantUnit, unitType, isGift, isBind, isDefault);
					}
				
					
				}else{//源争夺战物品打包方式，无上限
					boolean isExist = false;
					
					//payment.getUnit > Constants.MAX_STACK_SIZE
					int redundantUnit=payment.getUnit()%Constants.MAX_ZYZDZ_STACK_SIZE;
					int maxSizeUnit=payment.getUnit()/Constants.MAX_ZYZDZ_STACK_SIZE;
					
					while(maxSizeUnit>0){
						playerItemId = playerItemDao.createPlayItem(player.getId(), sysItem, Constants.MAX_ZYZDZ_STACK_SIZE, unitType, isGift, isBind, isDefault);
						
						maxSizeUnit--;
					}
					
					for(PlayerItem each : piList){
						if((each.getQuantity() +  redundantUnit) <= Constants.MAX_ZYZDZ_STACK_SIZE){
							playerItemId = each.getId();
							each.setQuantity((each.getQuantity() +  redundantUnit));
							each.setPlayerItemUnitType(payment.getUnitType());
							if(sysItem.getType()==Constants.DEFAULT_MATERIAL_TYPE){
								each.setIsBind("Y");
							}
							if(sysItem.getType()==Constants.DEFAULT_OPEN_TYPE&&(sysItem.getIId()==20||sysItem.getIId()==22)){
								each.setIsBind("Y");
							}
							each.setValidDate(new Date());
							playerItemDao.updatePlayerItem(each);
							isExist = true;
							break;
						}
					}
					if(!isExist){
						playerItemId = playerItemDao.createPlayItem(player.getId(), sysItem, redundantUnit, unitType, isGift, isBind, isDefault);
					}	
					
				}
				
				
			}
		} else {
			playerItemId = playerItemDao.createPlayItem(player.getId(), sysItem, unit, unitType, isGift, isBind, isDefault);
		}
		int playerAction = 0;
		if (sysItem.getType() == Constants.DEFAULT_WEAPON_TYPE && sysItem.getSeq() == 1) {
			playerAction = Constants.ACTION.GETWEAPON.getValue();
		} else if (sysItem.getType() == Constants.DEFAULT_WEAPON_TYPE && sysItem.getSeq() == 3) {
			playerAction = Constants.ACTION.GETKNIFE.getValue();
		} else if (sysItem.getType() == Constants.DEFAULT_COSTUME_TYPE && sysItem.getSeq() == 1) {
			playerAction = Constants.ACTION.GETCOSTUME.getValue();
		}
		if (playerAction != 0) {
			ServiceLocator.updateService.updatePlayerAchievementNotInStageClear(player, playerAction, sysItem.getCharacterList().get(0));
		}
		
		ServiceLocator.deleteService.deletePlayerItemInMemcached(player.getId(), sysItem);
		if(Constants.BOOLEAN_NO.equals(isGift)&&!(sysItem.getIBuffId()>=61&&sysItem.getIBuffId()<=74)&&sysItem.getType()!=9){//战队buff:ibuffId:61-74,不会显示通知
			soClient.puchCMDtoClient(player.getName(), CommonUtil.messageFormat(CommonMsg.HIGHLIGHT_STORAGE,sysItem.getType(),sysItem.getSubType()));
		}
		//新手馈赠，直接使用
		if(sysItem.getType()==Constants.DEFAULT_ITEM_TYPE&&sysItem.getIId()==Constants.SPECIAL_ITEM_IIDS.NEW_AWARD.getValue()){
			useItemById(player, Constants.DEFAULT_ITEM_TYPE, playerItemId, null, 0, 0);
		}
		//vip专属名片 直接使用
		if(sysItem.getType()==Constants.DEFAULT_ITEM_TYPE&&sysItem.getSubType()==2&&sysItem.getIId()==1&&sysItem.getIBuffId()==Constants.DEFAULT_CARD_BUFF_ID&&
				sysItem.getIsVip()>0){
			useItemById(player, Constants.DEFAULT_ITEM_TYPE, playerItemId, null, 0, 0);
		}
		//zlm2015-5-7-限时装备-开始-----购买----计算可续费天数----------------------------
		if(sysItem.getProvisional_item_flag()){
			//payment.getUnit();//天数
			String key = Constants.PROVISIONAL_ITEM_FLAG_KEY + player.getId() + Constants.DELIMITER +  playerItemId;
			Integer provisional_flag_int=null;
			if(payment.getUnit()==3){
				 provisional_flag_int=30;//等于三天，那表示这是礼包默认送的时间，不算入可续费时间
			}else {
				 provisional_flag_int=(30-payment.getUnit()==23)? 21:(30-payment.getUnit());//算入可续费时间
			}
			nosqlService.getNosql().set(key, provisional_flag_int.toString());
		}
		//zlm2015-5-7-限时装备-结束-------------------------------------
		return playerItemId;
	}
	
	
	/**
	 * 是否需要进行道具叠加
	 * @return
	 */
	private boolean needToPlusPersonalItem(SysItem sysItem,String isGift){
		boolean result=false;
		//最初的道具判断逻辑，现在需要加上该道具是不资源争夺战类型
		if(isGift.equals("N")&& (sysItem.getType() == Constants.DEFAULT_MATERIAL_TYPE || sysItem.getType() == Constants.DEFAULT_OPEN_TYPE||sysItem.getSubType()==Constants.DEFAULT_ITEM_SUBTYPE.COST.getValue())){
			if(sysItem.getType()!=Constants.DEFAULT_ZYZDZ_TYPE){
				return true;
			}
		}
		
		//资源争夺战个人物品购买
		if( sysItem.getType() ==Constants.DEFAULT_ZYZDZ_TYPE && (sysItem.getSubType()==3|| sysItem.getSubType()==6)){//资源争夺战个人消耗品或者技能
			return true;
		}
		return result;
	}
	/**
	 * 发送玩家物品，不pushCmd
	 * @param sysItem
	 * @param player
	 * @param payment
	 * @param isGift
	 * @param isBind
	 * @param isDefault
	 * @return
	 * @throws Exception
	 */
	public int sendItemWithoutPushCmd(SysItem sysItem, Player player, Payment payment,String isGift,String isBind,String isDefault) throws Exception{
		if(isGift==null){
			isGift=Constants.BOOLEAN_NO;
		}
		if(isBind==null){
			isBind=Constants.BOOLEAN_NO;
		}
		//ugly
		if(Constants.BOOLEAN_YES.equals(isBind)||sysItem.getType()==Constants.DEFAULT_MATERIAL_TYPE||(sysItem.getType()==Constants.DEFAULT_ITEM_TYPE&&sysItem.getSubType()==7)){
			isBind=Constants.BOOLEAN_YES;
		}
		if(isDefault==null){
			isDefault=Constants.BOOLEAN_NO;
		}
		int playerItemId = 0;
		int unitType = 0;
		int unit = 1;
		if (null == payment) {
			if (null != sysItem.getAllGpPricesList()&& sysItem.getAllGpPricesList().size() > 0) {
				unitType = sysItem.getAllGpPricesList().get(0).getUnitType();
				unit = sysItem.getAllGpPricesList().get(0).getUnit();
			} else if (null != sysItem.getAllCrPricesList()&& sysItem.getAllCrPricesList().size() > 0) {
				unitType = sysItem.getAllCrPricesList().get(0).getUnitType();
				unit = sysItem.getAllCrPricesList().get(0).getUnit();
			} else if (null != sysItem.getAllVoucherPricesList()&& sysItem.getAllVoucherPricesList().size() > 0) {
				unitType = sysItem.getAllVoucherPricesList().get(0).getUnitType();
				unit = sysItem.getAllVoucherPricesList().get(0).getUnit();
			}
		} else {
			unit = payment.getUnit();
			unitType = payment.getUnitType();
		}
		
		payment=new Payment(unit,unitType);
		List<PlayerItem> piList = null;
		if(isGift.equals("N") && (sysItem.getType() == Constants.DEFAULT_MATERIAL_TYPE || sysItem.getType() == Constants.DEFAULT_OPEN_TYPE||sysItem.getSubType()==Constants.DEFAULT_ITEM_SUBTYPE.COST.getValue())) {//materials & open type must overlap
			piList = getService.getPlayerItemListByItemId(player.getId(), sysItem.getId(), sysItem.getType());
			if(piList.isEmpty()){
				playerItemId = playerItemDao.createPlayItem(player.getId(), sysItem, unit, unitType, isGift, isBind, isDefault);
			} else {
				boolean isExist = false;
				for(PlayerItem each : piList){
					if((each.getQuantity() +  payment.getUnit()) <= Constants.MAX_STACK_SIZE){
						playerItemId = each.getId();
						each.setQuantity((each.getQuantity() +  payment.getUnit()));
						each.setPlayerItemUnitType(payment.getUnitType());
						if(sysItem.getType()==Constants.DEFAULT_MATERIAL_TYPE){
							each.setIsBind("Y");
						}
						if(sysItem.getType()==Constants.DEFAULT_OPEN_TYPE&&(sysItem.getIId()==20||sysItem.getIId()==22)){
							each.setIsBind("Y");
						}
						playerItemDao.updatePlayerItem(each);
						isExist = true;
						break;
					}
				}
				if(!isExist){
					playerItemId = playerItemDao.createPlayItem(player.getId(), sysItem, unit, unitType, isGift, isBind, isDefault);
				}
			}
		} else {
			playerItemId = playerItemDao.createPlayItem(player.getId(), sysItem, unit, unitType, isGift, isBind, isDefault);
		}
		int playerAction = 0;
		if (sysItem.getType() == Constants.DEFAULT_WEAPON_TYPE && sysItem.getSeq() == 1) {
			playerAction = Constants.ACTION.GETWEAPON.getValue();
		} else if (sysItem.getType() == Constants.DEFAULT_WEAPON_TYPE && sysItem.getSeq() == 3) {
			playerAction = Constants.ACTION.GETKNIFE.getValue();
		} else if (sysItem.getType() == Constants.DEFAULT_COSTUME_TYPE && sysItem.getSeq() == 1) {
			playerAction = Constants.ACTION.GETCOSTUME.getValue();
		}
		if (playerAction != 0) {
			ServiceLocator.updateService.updatePlayerAchievementNotInStageClear(player, playerAction, sysItem.getCharacterList().get(0));
		}
		
		ServiceLocator.deleteService.deletePlayerItemInMemcached(player.getId(), sysItem);
		return playerItemId;
	}
	
	
	public String sendItems(List<SysItem> sysItemList, Player player, Payment payment,String isGift,String isBind,String isDefault) throws Exception {
		StringBuilder result=null;
		try {
			if(sysItemList!=null&&sysItemList.size()!=0){
				for(SysItem si:sysItemList){
					int itemId=sendItem(si, player, payment, isGift, isBind, isDefault);
					result.append(itemId+",");
				}
			}
		} catch (Exception e) {
			logger.warn("Exception catch in sendItems",e);
		}
		return result.toString();
	}
	public int presentItem(Player receiver, Player player, SysItem sysItem,Integer costId, String isGift) throws Exception {
		int returnValue = buyPlayerItem(null,player.getUserName(), player, sysItem,costId, isGift, 0, receiver);
		nosqlService.publishEvent(new PresentEvent(DateUtil.getCurrentTimeStr(), player, receiver.getName(),sysItem));

		return returnValue;
	}
	private boolean validateMessage(String playerName, String message)throws BaseException, UnsupportedEncodingException {
		if ("".equals(message)) {
			throw new BaseException(ExceptionMessage.EMPTY_STR);
		}
		//FIXME
		if (message.length() > 64) {
			throw new BaseException(ExceptionMessage.TOO_LONG);
		}
		return true;
	}

	public void msgDLB(String msg, String name) {
		try {
			soClient.messageDLB(msg, name);
		} catch (Exception e) {
			logger.warn("error happend during DLB, exception is" + e);
		}
	}
	public void msgXLB(int serverId, String msg,
			String name) {
		try {
			soClient.messageXLB(serverId, msg, name);
		} catch (Exception e) {
			logger.warn("error happend during DLB, exception is" + e);
		}
	}
	public String useSpeak(Player player, int iId, String message, int channelId, int serverId) throws Exception {
		//String returnStr=null;
		//final int playerId = player.getId();
		SysItem sysItem = getService.getSysItemByIID(iId, Constants.DEFAULT_ITEM_TYPE).get(0);
		//PlayerItem playerItem = getService.getPlayerItemByItemId(playerId, Constants.DEFAULT_ITEM_TYPE, playerItemId);
		PlayerItem playerItem = null;
		List<PlayerItem> playerItem_s1 = getService.getPlayerItem_s1(player.getId(), Constants.DEFAULT_ITEM_TYPE, 0, 4);
		for (PlayerItem item : playerItem_s1) {
			if(item.getItemId() == sysItem.getId()&&item.getQuantity()>0){
				playerItem = item;
				playerItem.setSysItem(sysItem);
				break;
			}
		}
		return useItemById(player, Constants.DEFAULT_ITEM_TYPE, playerItem, message, channelId, serverId);
	}
	/**
	 * @throws BaseException
	 * @param serverId
	 * @param roomId
	 * @param channelId
	 * @param serverId
	 *            method to use item
	 * @param userId
	 * @param playerId
	 * @param type
	 * @param playerItemId:item's
	 *            id
	 * @throws Exception
	 */
	public String useItemById(Player player, int type, int playerItemId, String message, int channelId, int serverId) throws Exception {
			PlayerItem playerItem = getService.getPlayerItemByItemId(player.getId(), type, playerItemId);
			return useItemById(player, type, playerItem, message, channelId, serverId);
	}

	public String useItemById(Player player, int type, PlayerItem playerItem, String message, int channelId, int serverId) throws Exception {
		String returnStr=null;
		final int playerId = player.getId();
		if (playerItem == null) {//
			throw new NullPointerException(ExceptionMessage.NOT_PLAYER_ITEM);
		}
		if (playerItem.getPlayerItemUnitType()==1&&playerItem.getQuantity()<=0) {//
			throw new NullPointerException(ExceptionMessage.NOT_PLAYER_ITEM);
		}
		SysItem si = sysItemDao.getSystemItemById(playerItem.getItemId());
		
		if (si.getIId() == 1) {
			// buff item
			useBuffItem(playerItem,player);
			playerDao.updatePlayerInCache(player);
			mcc.delete(CacheUtil.sPlayer(playerId));
			mcc.delete(CacheUtil.oPlayer(playerId));
			if (playerItem.getId() != 0 && si.getIId() == 1 && si.getIBuffId() == Constants.DEFAULT_CARD_BUFF_ID) {
				List<PlayerItem> buffList=getService.getPlayerBuffListById(playerId);
				player.setBuffList(buffList);
				soClient.updateCharacterInfo(player, player.getTeam(), player.getRank());
			}
			if(si.getIBuffId()!=BiochemicalConstants.ordinaryBuffId){
				soClient.puchCMDtoClient(player.getName(), CommonUtil.messageFormat(CommonMsg.REFRESH_BUFF_LIST, null));
			}
			
		} else if (si.getIId() == 2 || si.getIId() == 3) {// 大小喇叭
			if (message == null || "".equals(message)) {
				throw new BaseException(ExceptionMessage.EMPTY_STR);
			}
			//FIXME
			if (message.length() > 64) {
				throw new DataTooLongException(ExceptionMessage.TOO_LONG);
			}
			if(!(playerItem.getQuantity() > 0)){
				throw new NullPointerException(ExceptionMessage.NOT_PLAYER_ITEM);
			}
			// speaker item
			if (serverId != 0 && si.getIId() == 3) {// 大喇叭
				message = KeywordFilterUtil.filter(StringUtil.escapeIndex(message));
				msgDLB(message, player.getName());
				updateItemQuantity(playerItem);
				ServiceLocator.updateService.updatePlayerAchievementNotInStageClear(player,Constants.ACTION.DALABA.getValue(), 1);
				if (ConfigurationUtil.SWITCH_XUNLEI_LOG.getIsOn()) {
					ServiceLocator.nosqlService.addXunleiLog("1.3"
							+ Constants.XUNLEI_LOG_DELIMITER
							+ player.getUserName()
							+ Constants.XUNLEI_LOG_DELIMITER + player.getName()
							+ Constants.XUNLEI_LOG_DELIMITER + serverId
							+ Constants.XUNLEI_LOG_DELIMITER + message
							+ Constants.XUNLEI_LOG_DELIMITER + si.getId()
							+ Constants.XUNLEI_LOG_DELIMITER + "大喇叭"
							+ Constants.XUNLEI_LOG_DELIMITER
							+ CommonUtil.simpleDateFormat.format(new Date()));
				}
				//成长任务22：使用大喇叭
				ServiceLocator.updateService.updatePlayerGrowthMission(player, GrowthMissionType.USE_DLB);
			} else if (channelId != 0 && si.getIId() == 2) {// 小喇叭
				message = KeywordFilterUtil.filter(StringUtil.escapeIndex(message));
				msgXLB(serverId, message, player.getName());
				updateItemQuantity(playerItem);
				if (ConfigurationUtil.SWITCH_XUNLEI_LOG.getIsOn()) {
					ServiceLocator.nosqlService.addXunleiLog("1.3"
							+ Constants.XUNLEI_LOG_DELIMITER
							+ player.getUserName()
							+ Constants.XUNLEI_LOG_DELIMITER + player.getName()
							+ Constants.XUNLEI_LOG_DELIMITER + serverId
							+ Constants.XUNLEI_LOG_DELIMITER + message
							+ Constants.XUNLEI_LOG_DELIMITER + si.getId()
							+ Constants.XUNLEI_LOG_DELIMITER + "小喇叭"
							+ Constants.XUNLEI_LOG_DELIMITER
							+ CommonUtil.simpleDateFormat.format(new Date()));
				}
			} else {
				throw new BaseException(ExceptionMessage.ERROR_MESSAGE_ALL);
			}

		} else if (si.getIId() == 4) {// 改头换面
			if (StringUtil.filter(message)) {
				throw new IllegalCharacterException(
						ExceptionMessage.ILLEGAL_CHARACTER_CREATE_PLAYER);
			}
			if (!KeywordFilterUtil.isLegalInput(message)) {
				throw new IllegalCharacterException(
						ExceptionMessage.ILLEGAL_CHARACTER_CREATE_PLAYER);
			}
			//FIXME
			if (message == null || "".equals(message)) {
				throw new BaseException(ExceptionMessage.NAME_CANT_NULL);
			} else if (message.length() > StringUtil.toInt(getService.getSysConfig().get("playername.maxlength"))) {
				throw new BaseException(ExceptionMessage.TOO_LONG_CREATE_PLAYER);
			} else if (message.length() < StringUtil.toInt(getService.getSysConfig().get("playername.minlength"))) {
				throw new BaseException(
						ExceptionMessage.TOO_SHORT_CREATE_PLAYER);
			}
			Player temp = getService.getPlayerByName(message);
			if (null == temp) {
				player.setName(message);
				playerDao.updatePlayerInCache(player);
				playerDao.updateObjInDB(player);
				Runnable notifyFriend = new Runnable() {
					@Override
					public void run() {
						try {
							List<Friend> friends = getService.getFriendList(playerId, 1);
							friends.addAll(getService.getFriendList(playerId, 2));
							friends.addAll(getService.getFriendList(playerId, 3));
							for (Friend f : friends) {
								mcc.delete(CacheUtil.oFriendList(f.getFriendId()));
								mcc.delete(CacheUtil.oBlackList(f.getFriendId()));
								mcc.delete(CacheUtil.oGroupList(f.getFriendId()));
								soClient.puchCMDtoClient(f.getName(),CommonMsg.REFRESH_FRIEND);
							}
						} catch (Exception e) {
							logger.warn("error happened in sending mail to player");
						}
					};
				};
				ServiceLocator.asynTakService.execute(notifyFriend);
				mcc.delete(CacheUtil.oPlayer(playerId));
				mcc.delete(CacheUtil.sPlayer(playerId));
				soClient.updateCharacterInfo(player, player.getTeam(), player.getRank());
				updateItemQuantity(playerItem);
			} else {
				throw new BaseException(ExceptionMessage.NAME_EXIST);
			}
		} else if (si.getIId() == 5 || si.getIId() == 11 || si.getIId() == 12
				|| si.getIId() == 13) {// 各种清零卡
			int iid = si.getIId();
			if (5 == iid) {// 战绩清零卡
				player.setGWin(0);
				player.setWinRate(0);
				player.setGLose(0);
				// player.setGAssist(0);
				playerDao.updatePlayerInCache(player);
				//当玩家战绩变化是更新玩家战绩排行榜
				ServiceLocator.updateService.updatePlayerTop(player);
				mcc.delete(CacheUtil.oPlayer(playerId));
				mcc.delete(CacheUtil.sPlayer(playerId));
				soClient.updateCharacterInfo(player, player.getTeam(), player.getRank());
			} else if (11 == iid) {// 死亡清零卡

			} else if (12 == iid) {// 逃跑清零卡
				player.setRunAway(0);
				player.setRunAwayRate(0);
				player.setGTotal((player.getGWin() + player.getGLose()));
				playerDao.updatePlayerInCache(player);
				//当玩家战绩变化是更新玩家战绩排行榜
//				ServiceLocator.updateService.updatePlayerTop(player);
				mcc.delete(CacheUtil.oPlayer(playerId));
				mcc.delete(CacheUtil.sPlayer(playerId));
				soClient.updateCharacterInfo(player, player.getTeam(), player.getRank());
			} else if (13 == iid) {// 战队清零卡
				PlayerTeam pt = getService.getPlayerTeamByPlayerId(playerId);
				if (null != pt&& Constants.BOOLEAN_YES.equals(pt.getApproved())) {
//					pt.setKillNum(0);
//					pt.setDeadNum(0);
//					pt.setAssist(0);
//					playerTeamDao.updatePlayerTeam(pt);
					Team team=getService.getTeam(pt.getTeamId());
					team.setGameWin(0);
					team.setGameTotal(0);
					//清楚战队战绩详情列表
					ServiceLocator.deleteService.deleteTeamRecordByTeamId(pt.getTeamId());
					TeamUtils.updateTeamWinTotal(pt.getTeamId(), team);
					//更新战队战绩排行榜
					nosqlService.getNosql().zRem(Constants.TEAMTOP_KEY_PREFIX+ Constants.TEAM_TOP_TYPE.BATTLERESULT.getValue(), String.valueOf(pt.getTeamId()));
				} else {
					throw new BaseException(ExceptionMessage.NOT_APPROVE_ERROR);
				}
			}
			updateItemQuantity(playerItem);
		} else if (si.getIId() == 6) {// 大家一起来升级

		} else if (si.getIId() == 7) {// 有钱同赚
			// team item
			// useTeamItem(playerItem,playerId);
		} else if (si.getIId() == 8) {// CAL类道具
			int gpAdd = StringUtil.toInt(si.getIValue());
			awardCALToPlayer(player, gpAdd);
			updateItemQuantity(playerItem);
		} else if (si.getIId() == 9) {// 扩充战队
			PlayerTeam pt = getService.getPlayerTeamByPlayerId(playerId);
			if (null == pt || pt.getApproved().equals("N")) {
				throw new BaseException(ExceptionMessage.NOT_APPROVE_ERROR);
			}
			if (pt.getJob() != TeamConstants.TEAMJOB.TEAM_CAPTAIN.getValue()) {
				throw new BaseException(ExceptionMessage.TEAM_CAPTAIN_RIGHT);
			} else {
				Team team = getService.getTeam(pt.getTeamId());
				int sizeAdd = StringUtil.toInt(si.getIValue());
				int size=team.getSize()+sizeAdd;
				if (team.getSize()< Constants.TEAME_MAX_SIZE) {
					team.setSize(size<=Constants.TEAME_MAX_SIZE?Constants.TEAME_MAX_SIZE:size);
					TeamUtils.updateTeamSize(pt.getTeamId(), size);
				}else{
					throw new BaseException(ExceptionMessage.TEAM_OVER_MAX_SIZE);
				}
			}
			updateItemQuantity(playerItem);
		} else if (si.getIId() == 10) {// 解锁角色道具
			awardReleaseItemToPlayer(player, si);
			updateItemQuantity(playerItem);
		} else if (si.getIId() == 14) {// 创建战队卡
			updateItemQuantity(playerItem);
		}
		else if (si.getIId()==15){// VIP道具
			if(player.getVipToExpire() == -1){
				throw new BaseException(ExceptionMessage.ALREADY_VIP);
			}
			if(playerItem.getPlayerItemUnitType() == 0){//永久的
				player.setVipToExpire(-1);
			} else if(playerItem.getPlayerItemUnitType() == 2){//时限的
				Calendar c = Calendar.getInstance();
				int secondsAdd = playerItem.getLeftSeconds();
				if(player.getIsVip()>=1){
					c.setTimeInMillis(player.getVipToExpire());
					c.add(Calendar.SECOND, secondsAdd);	
					player.setVipToExpire(c.getTimeInMillis());
				} else {	
					if(player.getRenewVipLevel()>0 ){
						player.setIsVip(player.getRenewVipLevel()) ;
						 //送vip专属名片 
						createItemForVipLevelUp(player, player.getRenewVipLevel());
					}else{
						player.setIsVip(1);
						 //送vip专属名片 
						createItemForVipLevelUp(player, 1);
					}
					
					c.add(Calendar.SECOND, secondsAdd);
					player.setVipToExpire(c.getTimeInMillis());
				}
			}
			ServiceLocator.updateService.updatePlayerInfo(player);
			playerItem.setIsDeleted("Y");
//			playerItemDao.softDeletePlayerItem(playerItem);
//			ServiceLocator.deleteService.deletePlayerItem(playerId, type, playerItemId);
			updateItemQuantity(playerItem);
			soClient.puchCMDtoClient(player.getName(), CommonUtil.messageFormat(CommonMsg.BECOME_VIP, null));
		} else if (si.getIId() == 16) {// 战队更名卡
			final Team team = getService.getTeamByPlayerId(playerId);
			if (null == team) {
				throw new BaseException(ExceptionMessage.NOT_APPROVE_ERROR);
			}
			PlayerTeam pt = getService.getPlayerTeamByPlayerId(playerId);
			if (null == pt) {
				throw new BaseException(ExceptionMessage.NOT_APPROVE_ERROR);
			} else if (pt.getJob() != TeamConstants.TEAMJOB.TEAM_CAPTAIN.getValue()) {
				throw new BaseException(ExceptionMessage.TEAM_CAPTAIN_RIGHT);
			}
			boolean isError = false;
			String nameStr = "";
			if (StringUtil.isEmptyString(message)) {
				isError = true;
				nameStr += ExceptionMessage.EMPTY_STR + "\\n";
			}
			if (!KeywordFilterUtil.isLegalInput(message)
					|| StringUtil.filter(message)) {
				isError = true;
				nameStr += ExceptionMessage.ILLEGAL_CHARACTER+ "\\n";
			}
			int count = getService.fuzzyCountTeam(message);
			if (count > 0) {
				isError = true;
				nameStr += ExceptionMessage.TEAM_EXIST + "\\n";
			}
			//FIXME
			if (message != null && message.length() > StringUtil.toInt(getService.getSysConfig().get("teamname.maxlength"))) {
				isError = true;
				nameStr += ExceptionMessage.TOO_LONG + "\\n";
			}
			if (!isError) {
				final String oldTeamName = team.getName();
				final String newTeamName = message;
				TeamUtils.updateTeamName(team.getId(), message);
				updateItemQuantity(playerItem);
				mcc.delete(CacheUtil.oTeam(team.getId()));
				Runnable task = new Runnable() {

					@Override
					public void run() {
						try {
							List<PlayerTeam> ptList = getService.getPlayerTeamByTeamIdSimple(team.getId());
							for (PlayerTeam pt : ptList) {
								Player p=getService.getPlayerById(pt.getPlayerId());
								soClient.updateCharacterInfo(p, newTeamName, p.getRank());
								messageService.sendSystemMail(getService.getPlayerById(pt.getPlayerId()), CommonMsg.TEAM_RENAME_MAIL_SUBJECT, CommonUtil.messageFormatI18N(CommonMsg.TEAM_RENAME_MAIL_CONTENT, new Object[] { oldTeamName, newTeamName }));
							}
						} catch (Exception e) {
							logger.warn("error in send email to player when change name of team.");
						}
					}

				};
				ServiceLocator.asynTakService.submit(task);
				// GM使用
				mcc.delete(CacheUtil.oTeamList());
			} else {
				throw new BaseException(nameStr);
			}
		}else if(si.getIId()==Constants.SPECIAL_ITEM_IIDS.MYSTIC_BAG.getValue()||si.getIId()==Constants.SPECIAL_ITEM_IIDS.CHECK_GIFTBOX.getValue()){//18:神秘锦囊   19:每日签到累计天数礼品盒
			List<SysItem> returnList=new ArrayList<SysItem>();
			Payment pm = new Payment();
			int ivalue = Integer.parseInt(si.getIValue());
			List<OnlineAward> tmpList = new ArrayList<OnlineAward>();
			List<OnlineAward> randomAwards = new ArrayList<OnlineAward>();
			if(si.getIId()==Constants.SPECIAL_ITEM_IIDS.MYSTIC_BAG.getValue()){
				tmpList = getService.getOnlineAwardByTypeAndLevel(Constants.ONLINE_AWARD_TYPES.MYSTIC_BAG.getValue(), ivalue);
				randomAwards =getService.randomOnlineAwardFromList(Constants.NUM_ONE, tmpList);
			}
			else{
				tmpList = getService.getOnlineAwardByTypeAndLevel(Constants.ONLINE_AWARD_TYPES.DAILY_CHECK.getValue(), ivalue);
				randomAwards =getService.randomOnlineAwardFromList(Constants.NUM_ONE, tmpList);
				}
			List<OnlineAward> cvtList= new ArrayList<OnlineAward>();
			for(OnlineAward oa :randomAwards){
				SysItem sysItem = getService.getSysItemByItemId(oa.getItemId());
				if (sysItem != null) {
					oa.setSysItem(sysItem);
					cvtList.add(oa);
					pm.setUnit(oa.getUnit());
					pm.setUnitType(oa.getUnitType());
					int itemId=sendItem(sysItem, player, pm, Constants.BOOLEAN_NO, Constants.BOOLEAN_YES, Constants.BOOLEAN_NO);
					SysItem item=getService.getPlayerItemById(player.getId(), itemId).getSysItem();
					SysItem item2=(SysItem)item.clone();
					item2.setUnit(oa.getUnit());
					returnList.add(item2);
					int chipNum=getService.getMedolNumByPlayerId(playerId);
					int reliveCoinNum=getService.getReliveCoinNumByPlayerId(playerId);
					if (si.getIId() == Constants.SPECIAL_ITEM_IIDS.MYSTIC_BAG.getValue()) {
						logger.info("UseBoss2Package/Award:\t"+ playerId + "\t" +si.getId() +"\t"+si.getDisplayNameCN()+ "\t" +sysItem.getDisplayNameCN()+"\t"+oa.getUnit() + "\t" +  oa.getUnitType());
						if (ConfigurationUtil.SWITCH_XUNLEI_LOG.getIsOn()&&oa.getItemId()==GrowthMissionConstants.MEDAL_ID) {
							nosqlService.addXunleiLog("7.4"
									+ Constants.XUNLEI_LOG_DELIMITER + player.getUserName()
									+ Constants.XUNLEI_LOG_DELIMITER + player.getName()
									+ Constants.XUNLEI_LOG_DELIMITER + 1
									+ Constants.XUNLEI_LOG_DELIMITER + oa.getUnit()
									+ Constants.XUNLEI_LOG_DELIMITER + chipNum
									+ Constants.XUNLEI_LOG_DELIMITER+ 9
									+ Constants.XUNLEI_LOG_DELIMITER+ CommonUtil.simpleDateFormat.format(new Date())
									);
						}
//						int totalRare = sysItem.getRareLevel();
//						if(oa.getUnitType() == Constants.NUM_ONE){
//							totalRare *= oa.getUnit();
//						}
//						if (totalRare >= Constants.HIGH_RARE_LEVEL) {
//							soClient.proxyBroadCast(Constants.MSG_NOTICE, Constants.SYSTEM_SPEAKER, CommonUtil.messageFormat(CommonMsg.MYSTERY_BOX_SYS, new Object[] { player.getName(), sysItem.getDisplayName() }));
//						}
					} else {
						if (ConfigurationUtil.SWITCH_XUNLEI_LOG.getIsOn()&&oa.getItemId()==GrowthMissionConstants.MEDAL_ID) {
							nosqlService.addXunleiLog("7.4"
									+ Constants.XUNLEI_LOG_DELIMITER + player.getUserName()
									+ Constants.XUNLEI_LOG_DELIMITER + player.getName()
									+ Constants.XUNLEI_LOG_DELIMITER + 1
									+ Constants.XUNLEI_LOG_DELIMITER + oa.getUnit()
									+ Constants.XUNLEI_LOG_DELIMITER + chipNum
									+ Constants.XUNLEI_LOG_DELIMITER + 10
									+ Constants.XUNLEI_LOG_DELIMITER + CommonUtil.simpleDateFormat.format(new Date())
									);
						}
//						int totalRare = sysItem.getRareLevel();
//						if(oa.getUnitType() == Constants.NUM_ONE){
//							totalRare *= oa.getUnit();
//						}
//						if (totalRare >= Constants.HIGH_RARE_LEVEL) {
//							soClient.proxyBroadCast(Constants.MSG_NOTICE, Constants.SYSTEM_SPEAKER, CommonUtil.messageFormat(CommonMsg.SIGN_SYS, new Object[] { player.getName(), sysItem.getDisplayName() }));
//						}
					}
					
					if(item.getIId()==Constants.SPECIAL_ITEM_IIDS.RELIVE_COIN.getValue()){
						if (ConfigurationUtil.SWITCH_XUNLEI_LOG.getIsOn()&&oa.getItemId()==GrowthMissionConstants.RELIVE_COIN) {
							nosqlService.addXunleiLog("18.1"
									+ Constants.XUNLEI_LOG_DELIMITER + player.getUserName()
									+ Constants.XUNLEI_LOG_DELIMITER + player.getName()
									+ Constants.XUNLEI_LOG_DELIMITER + 1
									+ Constants.XUNLEI_LOG_DELIMITER + oa.getUnit()
									+ Constants.XUNLEI_LOG_DELIMITER + reliveCoinNum
									+ Constants.XUNLEI_LOG_DELIMITER + 4
									+ Constants.XUNLEI_LOG_DELIMITER + CommonUtil.simpleDateFormat.format(new Date())
									);
						}
					}
				}else{
					logger.warn("OnlineAward id:" + oa.getId() +" relating SysItem id:" +oa.getItemId()+ " is null");
				}
			}
			updateItemQuantity(playerItem);
			soClient.puchCMDtoClient(player.getName(), Converter.useItemAwards(cvtList));
			if(returnList.size()!=0){
				Collections.sort(returnList, new ComparatorUtil.IdComparatorClass());
				returnStr=Converter.usePlayerItem(returnList, 0);
			}
		}else if(si.getIId() == 20){//Vip宝箱(币)开启
//			updateItemQuantity(playerItem);
		}else if(si.getIId() == 21){
			awardExpToPlayer(player, StringUtil.toInt(si.getIValue()));
			updateItemQuantity(playerItem);
		}else if(si.getIId() == 23 || si.getIId() == 59 || si.getIId() == 60){//等级礼包
			switch(si.getIId()){
				case 23:
					//成长任务2： 打开新手礼包
					returnStr=useLevelPackage(player, si, 0);
					break;
				case 59:
					returnStr=useLevelPackage(player, si, 1);
					break;
				case 60:
					returnStr=useLevelPackage(player, si, 2);
					break;
				
			}
			
			updateItemQuantity(playerItem);
		}else if(si.getIId() == 24){//隔天礼盒
			returnStr=useNewPackage(player, playerItem);
			
			updateItemQuantity(playerItem);
		}else if(si.getIId() == 25){//进阶礼盒
			int[][] awardList=Constants.ADVANCED_LEVEL_PACKAGE;
			List<SysItem> returnList=new ArrayList<SysItem>();
			int itemId=0;
			int unit=0;
			int unitType=0;
			for(int i=0;i<awardList.length;i++){
				if(awardList[i].length!=3){
					logger.error("Error in get items in award level package");
					throw new BaseException(ExceptionMessage.ERROR_MESSAGE_ALL);
				}
				itemId=awardList[i][0];
				unit=awardList[i][1];
				unitType=awardList[i][2];
				SysItem item=getService.getSysItemByItemId(itemId);
				if(item==null){
					logger.warn("Error in get sysItem  with id " + itemId);
					throw new BaseException(ExceptionMessage.ERROR_MESSAGE);
				}else{
					int pii=sendItem(item, player, new Payment(unit,unitType), Constants.BOOLEAN_NO, Constants.BOOLEAN_YES, Constants.BOOLEAN_NO);
//					int totalRare = item.getRareLevel();
//					if(unitType == Constants.NUM_ONE){
//						totalRare *= unit;
//					}
//					if(totalRare >= Constants.HIGH_RARE_LEVEL){
//						soClient.proxyBroadCast(Constants.MSG_NOTICE, Constants.SYSTEM_SPEAKER, CommonUtil.messageFormat(CommonMsg.ADWANCED_LEVEL_PACAKGE_SYS, new Object[]{player.getName(), item.getDisplayName()}));
//					}
					SysItem sysItem=getService.getPlayerItemById(playerId, pii).getSysItem();
					SysItem item2=(SysItem)sysItem.clone();
					item2.setUnit(unit);
					item2.setUnitType(unitType);
					returnList.add(item2);
					
					if (ConfigurationUtil.SWITCH_XUNLEI_LOG.getIsOn()&&sysItem.getId()==GrowthMissionConstants.MEDAL_ID) {
						int chipNum=getService.getMedolNumByPlayerId(playerId);
						nosqlService.addXunleiLog("7.4"
								+ Constants.XUNLEI_LOG_DELIMITER + player.getUserName()
								+ Constants.XUNLEI_LOG_DELIMITER + player.getName()
								+ Constants.XUNLEI_LOG_DELIMITER + 1
								+ Constants.XUNLEI_LOG_DELIMITER + unit
								+ Constants.XUNLEI_LOG_DELIMITER + chipNum
								+ Constants.XUNLEI_LOG_DELIMITER+ 3
								+ Constants.XUNLEI_LOG_DELIMITER+ CommonUtil.simpleDateFormat.format(new Date())
								);
					}
				}
				
			}
			if(returnList.size()!=0){
				updateItemQuantity(playerItem);
				return Converter.usePlayerItem(returnList,0 );
			}else{
				logger.error("Error iid=25 returnList is null");
				return Converter.commonFeedback(null);
			}
		}else if(si.getIId() == 26){//TODO 多选一礼包礼盒 缺奖励东西
			returnStr=useChoiceBox(si);
			
			//zlm2015-5-7-限时装备-开始 里面物品有时间限制的多选一礼包
		}else if(si.getIId() == Constants.SPECIAL_ITEM_IIDS.XSZB_CHOICE_BOX.getValue()){// 多选一礼包礼盒 默认送装备 3天时间 61
			returnStr=use_SXZB_Choice_Box(si);
			//zlm2015-5-7-限时装备-结束
			
		}else if(si.getIId() == 27){
			if(playerItem.getPlayerItemCurrency()!=1){
				SysItem item=getService.getSysItemByItemId(4403);//1000C币道具
				List<SysItem> returnList=new ArrayList<SysItem>();
				SysItem item2=(SysItem)item.clone();
				item2.setUnit(1);
				returnList.add(item2);
				sendItem(item, player, new Payment(1,1), Constants.BOOLEAN_NO, Constants.BOOLEAN_YES, Constants.BOOLEAN_NO);
				updateItemQuantity(playerItem);
				return Converter.usePlayerItem(returnList,0 );
			}else{
				throw new BaseException(ExceptionMessage.NOT_OPEN_FRIENDPACKAGE);
			}
		}else if(si.getIId()==Constants.SPECIAL_ITEM_IIDS.SHOP_GIFT_PACKAGE.getValue()){//商城大礼包
			List<SysItem> returnList = new ArrayList<SysItem>();
			String items = si.getItems();
			if(!StringUtil.isEmptyString(items)){
				String[] itemArr = items.split(";");
				for(String item : itemArr){
					String values[] = item.split(",");
					if(values.length==3){
						SysItem sysItem = getService.getSysItemByItemId(StringUtil.toInt(values[0])).clone();
						if(sysItem!=null){
							int unit = StringUtil.toInt(values[1]);
							int unitType = StringUtil.toInt(values[2]);
							Payment payment = new Payment(unit, unitType);
							sendItem(sysItem, player, payment, Constants.BOOLEAN_NO, Constants.BOOLEAN_YES, Constants.BOOLEAN_NO);
							logger.info("UseGiftBag/Award:\t"+ playerId + "\t" +sysItem.getId() + "\t" + unit + "\t" + unitType);
							sysItem.setUnit(unit);
							sysItem.setUnitType(unitType);
							returnList.add(sysItem);
						}else{
							logger.error("UseGiftBag/SysItemNull:\t" + playerId + "\t" + si.getId());
						}
					}else{
						logger.error("UseGiftBag/ItemsWrong:\t"+ playerId + "\t"+ si.getId() + "\t" + si.getItems());
					}
				}
				updateItemQuantity(playerItem);
			}else{
				logger.error("UseGiftBag/ItemsWrong:\t"+ playerId + "\t"+ si.getId() + "\t" + si.getItems());
			}
			if(returnList.size()!=0){
				returnStr=Converter.usePlayerItem(returnList, 0);
			}		
		}else if(si.getIId()==Constants.SPECIAL_ITEM_IIDS.NEW_AWARD.getValue()){
			for(int i=1;i<=Constants.MAX_CHARACTER_SIZE;i++){
				List<PlayerItem> weaponList = getService.getDefaultPackList(playerId, "W", i);
				List<PlayerItem> customList = getService.getDefaultPackList(playerId, "C", i);
				for(PlayerItem pi:weaponList){
					if (pi.getIsDefault().equals("Y")&&CommonUtil.getWeaponSeq(pi.getSysItem().getWId())==1) {
						SysItem s=sysItemDao.getSystemItemById(pi.getItemId());
						pi.setSysItem(s);
						pi.setLevel(StringUtil.toInt(si.getIValue()));
						ServiceLocator.updateService.updatePlayerItem(pi);
						ServiceLocator.deleteService.deletePlayerPack(playerId, s);
					}
				}
				for(PlayerItem pi:customList){
					if (pi.getIsDefault().equals("Y")&&CommonUtil.getCotumeSeq(pi.getSysItem().getCId())==1) {
						SysItem s=sysItemDao.getSystemItemById(pi.getItemId());
						pi.setSysItem(s);
						pi.setLevel(StringUtil.toInt(si.getIValue()));
						ServiceLocator.updateService.updatePlayerItem(pi);
						ServiceLocator.deleteService.deletePlayerPack(playerId, s);
					}
				}
				
			}
			updateItemQuantity(playerItem);
		}else if(si.getIId()==38){ //vip经验块
			if(StringUtil.isAllNumber(si.getIValue())){
				if(0< ServiceLocator.updateService.updateVipUpExp(Constants.VIP_EARN_EXP_METHODS.VIPEXPITEM.getValue(), playerId, Integer.parseInt(si.getIValue()))){
				//	playerItem.setIsDeleted("Y");
					updateItemQuantity(playerItem);
				}else{
					return Converter.error(TeamConstants.ONE_TIME_DAYLY);
				}
				
			}
			
		}else if(
				(si.getIId() >= Constants.SPECIAL_ITEM_IIDS.SUPER_POWER1.getValue() &&
				si.getIId() <= Constants.SPECIAL_ITEM_IIDS.SUPER_POWER9.getValue()) ||
				si.getIId() == Constants.SPECIAL_ITEM_IIDS.SUPER_POWER10.getValue()
				){	//超级能量块
			
			Team team = getService.getTeamByPlayer(player);
			int pre = team.getExp();
			TeamUtils.updateTeamExp(team.getId(), Integer.parseInt(si.getIValue()));	//增加相对应的战队经验
			updateItemQuantity(playerItem);
			logger.info("UseSuperPower/Award:\t"+ playerId + "\t" +si.getIId() + "\t" + si.getUnit() + "\t" + si.getUnitType() + "\t team:"+team.getId()+"\t "+team.getName()+"\t "+pre+"\t"+team.getExp());
		}else if(si.getIId()==Constants.SPECIAL_ITEM_IIDS.AGRAVITYBOXITEM.getValue()){//58 圣诞跳跳乐箱子
			List<SysItem> returnList=new ArrayList<SysItem>();
			Payment pm = new Payment();
			int ivalue = Integer.parseInt(si.getIValue());
			List<OnlineAward> tmpList = new ArrayList<OnlineAward>();
			List<OnlineAward> randomAwards = new ArrayList<OnlineAward>();
			
			tmpList = getService.getOnlineAwardByTypeAndLevel(Constants.ONLINE_AWARD_TYPES.AGRAVITYBOXITEM.getValue(), ivalue);
			randomAwards =getService.randomOnlineAwardFromList(Constants.NUM_ONE, tmpList);
			
			List<OnlineAward> cvtList= new ArrayList<OnlineAward>();
			for(OnlineAward oa :randomAwards){
				SysItem sysItem = getService.getSysItemByItemId(oa.getItemId());
				if (sysItem != null) {
					oa.setSysItem(sysItem);
					cvtList.add(oa);
					pm.setUnit(oa.getUnit());
					pm.setUnitType(oa.getUnitType());
					int itemId=sendItem(sysItem, player, pm, Constants.BOOLEAN_NO, Constants.BOOLEAN_YES, Constants.BOOLEAN_NO);
					SysItem item=getService.getPlayerItemById(player.getId(), itemId).getSysItem();
					SysItem item2=(SysItem)item.clone();
					item2.setUnit(oa.getUnit());
					returnList.add(item2);
					logger.info("UseAGRAVITYBOXITEM/Award:\t"+ playerId + "\t" +si.getId() +"\t"+si.getDisplayNameCN()+ "\t" +sysItem.getDisplayNameCN()+"\t"+oa.getUnit() + "\t" +  oa.getUnitType());
				}else{
					logger.warn("OnlineAward id:" + oa.getId() +" relating SysItem id:" +oa.getItemId()+ " is null");
				}
			}
			updateItemQuantity(playerItem);
			soClient.puchCMDtoClient(player.getName(), Converter.useItemAwards(cvtList));
			if(returnList.size()!=0){
				Collections.sort(returnList, new ComparatorUtil.IdComparatorClass());
				returnStr=Converter.usePlayerItem(returnList, 0);
			}
		}else if(si.getIId()==Constants.SPECIAL_ITEM_IIDS.ONE_TO_MANY_BOX.getValue()){//62 暑假礼包
			List<SysItem> returnList=new ArrayList<SysItem>();
			Payment pm = new Payment();
			int ivalue = Integer.parseInt(si.getIValue());
			List<OnlineAward> tmpList = new ArrayList<OnlineAward>();
//			List<OnlineAward> randomAwards = new ArrayList<OnlineAward>();
			
			tmpList = getService.getOnlineAwardByTypeAndLevel(Constants.ONLINE_AWARD_TYPES.ONE_TO_MANY_BOX.getValue(), ivalue);
//			randomAwards =getService.randomOnlineAwardFromList(Constants.NUM_ONE, tmpList);
			
			List<OnlineAward> cvtList= new ArrayList<OnlineAward>();
			for(OnlineAward oa :tmpList){
				SysItem sysItem = getService.getSysItemByItemId(oa.getItemId());
				if (sysItem != null) {
					oa.setSysItem(sysItem);
					cvtList.add(oa);
					pm.setUnit(oa.getUnit());
					pm.setUnitType(oa.getUnitType());
					int itemId=sendItem(sysItem, player, pm, Constants.BOOLEAN_NO, Constants.BOOLEAN_YES, Constants.BOOLEAN_NO);
					SysItem item=getService.getPlayerItemById(player.getId(), itemId).getSysItem();
					SysItem item2=(SysItem)item.clone();
					item2.setUnit(oa.getUnit());
					returnList.add(item2);
					logger.info("UseAGRAVITYBOXITEM/Award:\t"+ playerId + "\t" +si.getId() +"\t"+si.getDisplayNameCN()+ "\t" +sysItem.getDisplayNameCN()+"\t"+oa.getUnit() + "\t" +  oa.getUnitType());
				}else{
					logger.warn("OnlineAward id:" + oa.getId() +" relating SysItem id:" +oa.getItemId()+ " is null");
				}
			}
			updateItemQuantity(playerItem);
			soClient.puchCMDtoClient(player.getName(), Converter.useItemAwards(cvtList));
			if(returnList.size()!=0){
				Collections.sort(returnList, new ComparatorUtil.IdComparatorClass());
				returnStr=Converter.usePlayerItem(returnList, 0);
			}
		}
		
		if(Constants.BOOLEAN_NO.equals(playerItem.getIsBind())){
			Date expireDate=new Date(System.currentTimeMillis()+playerItem.getLeftSeconds()*1000l);
			playerItem.setExpireDate(expireDate);
		}
		playerItem.setIsBind(Constants.BOOLEAN_YES);
		playerItemDao.updatePlayerItem(playerItem);
		ServiceLocator.deleteService.deletePlayerItemInMemcached(playerId, si);
		
		mcc.delete(CacheUtil.sPlayer(playerId));
		mcc.delete(CacheUtil.oPlayer(playerId));
		return returnStr;
	}
	
	public void choicePlayerItem(int playerId,int playerItemId,int itemId) throws Exception{
		PlayerItem pi=getService.getPlayerItemById(playerId, playerItemId);
		if(pi.getQuantity()<=0){
			throw new BaseException(ExceptionMessage.ERROR_MESSAGE);
		}
		if (pi.getType() == Constants.DEFAULT_OPEN_TYPE) {
			useItemById(getService.getPlayerById(playerId), pi.getType(), pi.getId(), null, 0, 0);
		}
		int value=StringUtil.toInt(pi.getSysItem().getIValue());
		boolean isContain=false;
		if(pi.getSysItem().getType()==Constants.DEFAULT_OPEN_TYPE&&pi.getSysItem().getIId()==26){
			int[] array=Constants.CHOICE_BOX[value-1];
			//hard code,ugly
			//仅仅为了抢购宝箱可配置
			if(value>=14&& value<=16){
			
				switch (value) {
				case 14:
					int[] rareArray=StringUtil.convertToInt(ServiceLocator.getService.getSysConfig().get("compete.rareBox").split(","));
					if(rareArray.length==6){
						array=rareArray;
					}
					break;
				case 15:
					int[] masterworArray=StringUtil.convertToInt(ServiceLocator.getService.getSysConfig().get("compete.masterworkBox").split(","));
					if(masterworArray.length==6){
						array=masterworArray;
					}
					break;
				case 16:
					int[] uncommon=StringUtil.convertToInt(ServiceLocator.getService.getSysConfig().get("compete.uncommonBox").split(","));
					if(uncommon.length==6){
						array=uncommon;
					}
					break;
				default:
					break;
				}
			}
			
			for(int i:array){
				if(i==itemId){
					isContain=true;
					break;
				}
			}
			if(!isContain){
				logger.warn("choice player item happed exception playerId="+playerId+",playerItemId="+playerItemId+",itemId="+itemId);
				throw new BaseException(ExceptionMessage.ERROR_MESSAGE);
			}else{
				awardToPlayer(getService.getPlayerById(playerId), getService.getSysItemByItemId(itemId), null, null, Constants.BOOLEAN_YES);
			}
			updateItemQuantity(pi);
		}
		//zlm2015-5-7-限时装备-开始   里面物品有时间限制的多选一礼包送东西 ，默认有3天 
		else if(pi.getSysItem().getType()==Constants.DEFAULT_OPEN_TYPE&&pi.getSysItem().getIId()==Constants.SPECIAL_ITEM_IIDS.XSZB_CHOICE_BOX.getValue()){
			int[] array=Constants.SXZB_CHOICE_BOX[value-1];
			//hard code,ugly
			//仅仅为了抢购宝箱可配置
			for(int i:array){
				if(i==itemId){
					isContain=true;
					break;
				}
			}
			if(!isContain){
				logger.warn("choice player item happed exception playerId="+playerId+",playerItemId="+playerItemId+",itemId="+itemId);
				throw new BaseException(ExceptionMessage.ERROR_MESSAGE);
			}else{
				Payment payment = new Payment();
				payment.setUnit(3);//3天 
				payment.setUnitType(2);//天数
				awardToPlayer(getService.getPlayerById(playerId), getService.getSysItemByItemId(itemId), payment, null, Constants.BOOLEAN_YES);
			}
			updateItemQuantity(pi);
		}
		//zlm2015-5-7-限时装备-结束
		
		
	}
	private String useNewPackage(Player player,PlayerItem playerItem) throws Exception{
		List<SysItem> returnList=new ArrayList<SysItem>();
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(playerItem.getValidDate());
		int[][] NEW_PACKAGE = null;
		switch(playerItem.getSysItem().getId()){
			case 4671:	//战斗补给箱
				calendar.add(Calendar.HOUR_OF_DAY, 8);
				NEW_PACKAGE=Constants.NEW_PACKAGE;
				break;
			case 5815:	//
				calendar.add(Calendar.MINUTE, 15);
				NEW_PACKAGE=Constants.NEW_PACKAGE2;
				break;
			case 5816:	//战斗补给箱
				calendar.add(Calendar.MINUTE, 30);
				NEW_PACKAGE=Constants.NEW_PACKAGE3;
				break;
			case 5817:	//战斗补给箱
				calendar.add(Calendar.MINUTE, 60);
				NEW_PACKAGE=Constants.NEW_PACKAGE4;
				break;
			case 5818:	//战斗补给箱
				calendar.add(Calendar.HOUR_OF_DAY, 2);
				NEW_PACKAGE=Constants.NEW_PACKAGE5;
				break;
			case 5819:	//战斗补给箱
				calendar.add(Calendar.HOUR_OF_DAY, 12);
				NEW_PACKAGE=Constants.NEW_PACKAGE6;
				break;
			case 5820:	//战斗补给箱
				calendar.add(Calendar.HOUR_OF_DAY, 24);
				NEW_PACKAGE=Constants.NEW_PACKAGE7;
				break;
			case 5821:	//战斗补给箱
				calendar.add(Calendar.HOUR_OF_DAY, 48);
				NEW_PACKAGE=Constants.NEW_PACKAGE8;
				break;
			case 5822:	//战斗补给箱
				calendar.add(Calendar.HOUR_OF_DAY, 72);
				NEW_PACKAGE=Constants.NEW_PACKAGE9;
				break;
			default:
				break;
		}
		if(new Date().getTime()<calendar.getTimeInMillis()){
			throw new BaseException(ExceptionMessage.NOT_LEVEL_TIME);
		}else{
			for(int i[]:NEW_PACKAGE){
				SysItem sysItem=getService.getSysItemByItemId(i[0]);
				int playerItemId=sendItem(sysItem, player, new Payment(i[2],i[1]), Constants.BOOLEAN_NO, Constants.BOOLEAN_YES, Constants.BOOLEAN_NO);
				SysItem si=getService.getPlayerItemById(player.getId(), playerItemId).getSysItem();
				SysItem item2=(SysItem)si.clone();
				item2.setUnit(i[2]);
				returnList.add(item2);
				//non medal
//				if (ConfigurationUtil.SWITCH_XUNLEI_LOG.getIsOn()&&sysItem.getId()==GrowthMissionConstants.MEDAL_ID) {
//					nosqlService.addXunleiLog("7.4"
//							+ Constants.XUNLEI_LOG_DELIMITER + player.getUserName()
//							+ Constants.XUNLEI_LOG_DELIMITER + player.getName()
//							+ Constants.XUNLEI_LOG_DELIMITER + 2
//							+ Constants.XUNLEI_LOG_DELIMITER + unit
//							+ Constants.XUNLEI_LOG_DELIMITER+ 1
//							+ Constants.XUNLEI_LOG_DELIMITER+ CommonUtil.simpleDateFormat.format(new Date())
//							);
//				}
			}
		}
		if(returnList.size()!=0){
			return Converter.usePlayerItem(returnList, 0);
		}
		return null;
	}
	public String useChoiceBox(SysItem si) throws Exception{
		List<SysItem> returnList=new ArrayList<SysItem>();
		int value=StringUtil.toInt(si.getIValue());
		int[] array=Constants.CHOICE_BOX[value-1];
		//hard code,ugly
		//仅仅为了抢购宝箱可配置
		if(value>=14&& value<=16){
		
			switch (value) {
			case 14:
				int[] rareArray=StringUtil.convertToInt(ServiceLocator.getService.getSysConfig().get("compete.rareBox").split(","));
				if(rareArray.length==6){
					array=rareArray;
				}
				break;
			case 15:
				int[] masterworArray=StringUtil.convertToInt(ServiceLocator.getService.getSysConfig().get("compete.masterworkBox").split(","));
				if(masterworArray.length==6){
					array=masterworArray;
				}
				break;
			case 16:
				int[] uncommon=StringUtil.convertToInt(ServiceLocator.getService.getSysConfig().get("compete.uncommonBox").split(","));
				if(uncommon.length==6){
					array=uncommon;
				}
				break;
			default:
				break;
			}
		}				
		for(int i:array){
			SysItem sysItem=getService.getSysItemByItemId(i);
			if(sysItem!=null){
				returnList.add(sysItem);
			}
		}
		if(returnList.size()!=0){
			return Converter.usePlayerItem(returnList, 0);
		}
		return null;
	}
	private String useLevelPackage(Player player,SysItem si,int giftType) throws Exception{
		List<SysItem> returnList=new ArrayList<SysItem>();
		if(player.getRank()<si.getLevel()){
			throw new BaseException(ExceptionMessage.NOT_MATCH_LEVEL_USE);
		}
		int cal=0;
		for(int i=0;i<Constants.GREEN_GR_AWARD[giftType].length;i++){
			int[] levelGR=Constants.GREEN_GR_AWARD[giftType][i];
			if(levelGR[0]==si.getId()){
				awardCALToPlayer(player, levelGR[1]);
				cal=levelGR[1];
				int[][] awardList=Constants.GREEN_ITEM_AWARD[giftType][i];
				for(int j=0;j<awardList.length;j++){
					if(awardList[j].length!=3){
						logger.error("Error in useItem with constants green_item_award "+i+":"+j);
					}
					int itemId=awardList[j][0];
					int unitType=awardList[j][1];
					int unit=awardList[j][2];
					SysItem item=getService.getSysItemByItemId(itemId);
					if(item==null){
						logger.error("Error in get sysItem with id "+itemId);
					}
//					int totalRare = item.getRareLevel();
//					if(unitType == Constants.NUM_ONE){
//						totalRare *= unit;
//					}
//					if(totalRare >= Constants.HIGH_RARE_LEVEL){
//						soClient.proxyBroadCast(Constants.MSG_NOTICE, Constants.SYSTEM_SPEAKER, CommonUtil.messageFormat(CommonMsg.LEVEL_PACAKGE_SYS, new Object[]{player.getName(), item.getDisplayName()}));
//					}
					int playerItemId=sendItem(item, player, new Payment(unit,unitType), Constants.BOOLEAN_NO, Constants.BOOLEAN_YES, Constants.BOOLEAN_NO);
					SysItem sysItem=getService.getPlayerItemById(player.getId(), playerItemId).getSysItem();
					SysItem item2=(SysItem)sysItem.clone();
					item2.setUnit(unit);
					item2.setUnitType(unitType);
					returnList.add(item2);
					
					if (ConfigurationUtil.SWITCH_XUNLEI_LOG.getIsOn()&&itemId==GrowthMissionConstants.MEDAL_ID) {
						int chipNum=getService.getMedolNumByPlayerId(player.getId());
						nosqlService.addXunleiLog("7.4"
								+ Constants.XUNLEI_LOG_DELIMITER + player.getUserName()
								+ Constants.XUNLEI_LOG_DELIMITER + player.getName()
								+ Constants.XUNLEI_LOG_DELIMITER + 1
								+ Constants.XUNLEI_LOG_DELIMITER + unit
								+ Constants.XUNLEI_LOG_DELIMITER + chipNum
								+ Constants.XUNLEI_LOG_DELIMITER+ 2
								+ Constants.XUNLEI_LOG_DELIMITER+ CommonUtil.simpleDateFormat.format(new Date())
								);
					}
				}
				break;
			}
		}
		if(giftType==0)//0 为新手礼包[游戏内礼包]，1 为新手升级礼包[网页领取活动]，2 为老玩家回归礼包[网页领取活动]
			ServiceLocator.updateService.updatePlayerGrowthMission(player, GrowthMissionType.OPEN_NEW_GIFT);
		if(returnList.size()!=0){
			return Converter.usePlayerItem(returnList, cal);
		}
		return null;
	}
	public SysItem getRandomMedol()throws Exception{
		Random r = new Random();
		int value=r.nextInt(Constants.MEDOL_LIST.length-1);
		return getService.getSysItemByItemId(Constants.MEDOL_LIST[value]);
	}

	/**
	 * method to use buff item,select then update method
	 * 
	 * @param playerItem
	 * @throws Exception
	 */

	public void useBuffItem(PlayerItem playerItem,Player player) throws Exception {
		//首次使用前置操作,获得当前使用的月卡buff
		//根据playerItem获得当前使用的buff
		PlayerItem pItem = null;
		//月卡
		if(playerItem!=null&&playerItem.getSysItem().getIBuffId() == Constants.DEFAULT_ON_CARD_BUFF)
		{	
			pItem = getService.getPlayerBuff(player.getId(), Constants.DEFAULT_ON_CARD_BUFF);
		}//天天礼
		else if(playerItem!=null&&playerItem.getSysItem().getIBuffId() == Constants.DEFAULT_EVERY_DAY_GIFT_BUFF){
			pItem = getService.getPlayerBuff(player.getId(), Constants.DEFAULT_EVERY_DAY_GIFT_BUFF);
			//防止同一buff被修改封包后发送到服务器后重复使用
			if(pItem!=null&&pItem.getId() == playerItem.getId()){
				throw new BaseException(ExceptionMessage.ILLEGAL_REQUEST);
			}
		}else if(playerItem!=null&&playerItem.getSysItem().getIBuffId() == Constants.NWE_YEAR_EVERY_DAY_GIFT_BUFF){
			pItem = getService.getPlayerBuff(player.getId(), Constants.NWE_YEAR_EVERY_DAY_GIFT_BUFF);
			//防止同一buff被修改封包后发送到服务器后重复使用
			if(pItem!=null&&pItem.getId() == playerItem.getId()){
				throw new BaseException(ExceptionMessage.ILLEGAL_REQUEST);
			}
		}
		PlayerBuff playerBuff = new PlayerBuff();
		playerBuff.setPlayerId(playerItem.getPlayerId());
		playerBuff.setPlayerItemId(playerItem.getId());
		
		SysItem sysItem = getService.getSysItemByItemId(playerItem.getItemId());
		playerBuff.setBuffId(sysItem.getIBuffId());
		PlayerBuff buff = playerBuffDao.fuzzyGetPlayerBuffById(playerItem
				.getPlayerId(), playerBuff.getBuffId());
		if (buff != null) {
			playerBuff.setId(buff.getId());
			playerBuffDao.updatePlayerBuff(playerBuff);
		} else {
			playerBuffDao.creatPlayerBuff(playerBuff);
		}
		
		/* 此处使用BUFF，现有系统内限定不允许卸下，所以也可以看做首次使用除却装备外的某件道具*/
		firstUse(playerBuff,player,pItem,playerItem);
	}

	/**
	 * 使用某些物品做出某些操作
	 * @param playerBuff 玩家使用的buff ， iCurrenty 当前被点击使用的物品 ，pItem 同类型正在使用的buff
	 * @date 2014-6-13
	 * @author OuYangGuang
	 * */
	public void firstUse(PlayerBuff playerBuff,Player player,PlayerItem pItem,PlayerItem iCurrenty){
		//使用一次赠送一次奖励的物品,需要同时判定,之前是否已使用，做时间叠加
		if (playerBuff.getBuffId() == Constants.DEFAULT_ON_CARD_BUFF
				|| playerBuff.getBuffId() == Constants.DEFAULT_EVERY_DAY_GIFT_BUFF
				|| playerBuff.getBuffId() == Constants.NWE_YEAR_EVERY_DAY_GIFT_BUFF) {
			try {
				
				if(pItem !=null){	//不为空则证明，该BUFF正在使用，二次叠加时间，但不赠送FC点
					
					int ExpireDay = 1 + DateUtil.getTimeDifference(new Date(),pItem.getExpireDate());	//得到正在使用物品剩余天
					
					int seconds = ExpireDay * 24 * 60 * 60;	//物品剩余秒
					
					iCurrenty.setExpireDate(DateUtils.addDays(iCurrenty.getExpireDate(), ExpireDay));	//叠加时间
					
					iCurrenty.setLeftSeconds(iCurrenty.getLeftSeconds() + seconds);
					
					ServiceLocator.deleteService.deletePlayerItem(player.getId(),Constants.DEFAULT_ITEM_TYPE, pItem);	//删除之前使用过的，留下当下使用的
					
					mcc.delete(CacheUtil.oPlayerInfo(iCurrenty.getPlayerId()));
					String key = DaoCacheUtil.oCacheKey(PlayerItem.class, player.getId());
					mcc.delete(key);
					//ServiceLocator.deleteService.deletePlayerItemInMemcached(player.getId(), pItem.getSysItem());
					
					//playerItemDao.updateObjInDB(iCurrenty);
					
				}else{  
					int expireDay;
					SysItem sysItem=null;
					String content=null;
					switch(playerBuff.getBuffId()){
						case Constants.DEFAULT_ON_CARD_BUFF:		//月卡 
							ServiceLocator.updateService.onCardsWelfare(player);
							break;
						case Constants.DEFAULT_EVERY_DAY_GIFT_BUFF:	//天天礼 首次使用赠送VIP
							expireDay 	= ServiceLocator.updateService.everydayGiftWelfare(player);
							sysItem 	= sysItemDao.getSystemItemById(4305);
							content 	= CommonUtil.messageFormatI18N(CommonMsg.EG_SUCCESS_MESSAGE_CON, sysItem.getDisplayName(),expireDay);
							//发送奖励
							ServiceLocator.createService.sendSystemMail(player, CommonMsg.EG_SUCCESS_MESSAGE_TITLE, content , sysItem.getId(), new Payment(2,2,0,30));
							nosqlService.addXunleiLog("17.3"
									+ Constants.XUNLEI_LOG_DELIMITER + player.getUserName()
									+ Constants.XUNLEI_LOG_DELIMITER + player.getId()
									+ Constants.XUNLEI_LOG_DELIMITER + player.getName()
									+ Constants.XUNLEI_LOG_DELIMITER + player.getRank()
									+ Constants.XUNLEI_LOG_DELIMITER + sysItem.getId()
									+ Constants.XUNLEI_LOG_DELIMITER + sysItem.getDisplayNameCN()
									+ Constants.XUNLEI_LOG_DELIMITER +  expireDay
									+ Constants.XUNLEI_LOG_DELIMITER + CommonUtil.simpleDateFormat.format(new Date())
									);
							break;
						case Constants.NWE_YEAR_EVERY_DAY_GIFT_BUFF:	//新春天天礼 首次使用赠送VIP
							expireDay 	= ServiceLocator.updateService.everydayGiftWelfare_6275(player);
							sysItem 	= sysItemDao.getSystemItemById(4305);//首次使用赠送VIP
							content 	= CommonUtil.messageFormatI18N(CommonMsg.NWE_YEAR_EG_SUCCESS_MESSAGE_CON, sysItem.getDisplayName(),expireDay);
							//发送奖励
							ServiceLocator.createService.sendSystemMail(player, CommonMsg.NWE_YEAR_EG_SUCCESS_MESSAGE_TITLE, content , sysItem.getId(), new Payment(2,2,0,30));
							nosqlService.addXunleiLog("17.6"
									+ Constants.XUNLEI_LOG_DELIMITER + player.getUserName()
									+ Constants.XUNLEI_LOG_DELIMITER + player.getId()
									+ Constants.XUNLEI_LOG_DELIMITER + player.getName()
									+ Constants.XUNLEI_LOG_DELIMITER + player.getRank()
									+ Constants.XUNLEI_LOG_DELIMITER + sysItem.getId()
									+ Constants.XUNLEI_LOG_DELIMITER + sysItem.getDisplayNameCN()
									+ Constants.XUNLEI_LOG_DELIMITER +  expireDay
									+ Constants.XUNLEI_LOG_DELIMITER + CommonUtil.simpleDateFormat.format(new Date())
									);
							break;
						default: break;
					}
					
				}
			} catch (Exception e) {
				logger.error("On Card firstUse error playerid:"+player.getId()+"buffid:"+playerBuff.getId(),e);
			}
		}
	}
	
	/**
	 * method to use speaker item
	 * 
	 * @param playerItem
	 * @param userId
	 * @param playerId
	 * @param playerItemId
	 * @throws Exception
	 */
	public void updateItemQuantity(PlayerItem playerItem) throws Exception {
		playerItem.setQuantity(playerItem.getQuantity() - 1);
		playerItemDao.updatePlayerItem(playerItem);
	}
	public void updateItemQuantity(PlayerItem playerItem,int num) throws Exception{
		playerItem.setQuantity(playerItem.getQuantity() - num);
		playerItemDao.updatePlayerItem(playerItem);
	}

	/**
	 * method to use team item
	 * 
	 * @param playerItem
	 * @param userId
	 * @param playerId
	 * @throws Exception
	 */
	public void useTeamItem(PlayerItem playerItem, int playerId)
			throws Exception {
		updateItemQuantity(playerItem);
		// playerItemDao.updatePlayerItem(userId, playerId, playerItem.getId(),
		// "QUANTITY", playerItem.getQuantity()-1);
	}


	public void useUpgradeTeamItem(PlayerItem playerItem, int playerId,
			int teamId) throws Exception {

		// playerItemDao.updatePlayerItem(userId, playerId, playerItem.getId(),
		// "QUANTITY", playerItem.getQuantity()-1);
		ServiceLocator.updateService.upgradeTeam(playerId, playerItem, teamId);
		updateItemQuantity(playerItem);
	}

	// ===============================================================================
	// Message Service
	// ===============================================================================

	/**
	 * method to detach a attachment email
	 * 
	 * @param userId
	 * @param playerId
	 * @param messageId
	 * @throws Exception
	 */

	public void detachMessageItem(int playerId, int messageId, int action)throws Exception {
		long start = System.currentTimeMillis();
		Message message = getService.getMessageFromInbox(playerId, messageId);
		Player receiver = getService.getSimplePlayerById(playerId);
		
		logger.debug("get mail use : " + (System.currentTimeMillis() - start) + "ms");
		start = System.currentTimeMillis();
		if (Constants.BOOLEAN_YES.equals(message.getHaveAttached())) {
			int playerItemId = message.getPlayerItemId();
			Player sender = getService.getPlayerById(message.getSenderId());
			PlayerItem item =null;
			if(message.getSenderId()!=0){
				item = getService.getPlayerItemById(sender.getId(),
					message.getPlayerItemId());
			}else{
				item = getService.getPlayerItemById(playerId,
						message.getPlayerItemId());
			}
			SysItem si = sysItemDao.getSystemItemById(item.getItemId());
			String unit = String.valueOf(item.getQuantity());
			String unitType = String.valueOf(item.getType());
			String sysId = String.valueOf(si.getId());
			mcc.delete(CacheUtil.oPlayerMessage(playerId));
			mcc.delete(CacheUtil.sPlayerMessage(playerId));
			logger.debug("get gift use : " + (System.currentTimeMillis() - start)
					+ "ms");
			start = System.currentTimeMillis();
			if (si.getType() == Constants.DEFAULT_PACKAGE_TYPE) {
				// package item
				if (action == 1) {// 接受礼物
//					SysItem sysItem = getService.getSysItemByItemId(si.getId());
					packageToPlayer(receiver, si, null, new StringBuilder(), Constants.BOOLEAN_NO);
					logger.debug("get big gift package use : "
							+ (System.currentTimeMillis() - start) + "ms");
					start = System.currentTimeMillis();
					messageDao.updateMessageContent(CommonUtil.messageFormatI18N(
							CommonMsg.GIFT_EAMIL_ACCEPT, message.getContent()),sysId,unit,unitType,
							messageId);
					logger.debug("update mail content use : "
							+ (System.currentTimeMillis() - start) + "ms");
					start = System.currentTimeMillis();
				}
				messageDao.deleteMessageItem(message.getId());
				PlayerItem playerItem = getService.getPlayerItemById(sender.getId(), playerItemId);
				playerItemDao.softDeletePlayerItem(playerItem);
				logger.debug("delete mail use : " + (System.currentTimeMillis() - start)+ "ms");
				start = System.currentTimeMillis();
			} else {
				// other item
				if (action == 1) {// 接受礼物
					ServiceLocator.deleteService.deletePlayerItemInMemcached(playerId, si);
					int senderId = item.getPlayerId();
					int itemId = item.getId();
					item.setPlayerId(playerId);
					item.setIsGift("N");
					item.setId(0);
					if(si.getIId()==27){
						item.setPlayerItemCurrency(0);
					}
					// playerItemDao.updatePlayerItem(item);
					playerItemDao.createPlayItem(item);
					item.setPlayerId(senderId);
					item.setIsGift("Y");
					item.setId(itemId);
					playerItemDao.softDeletePlayerItem(item);

					messageDao.updateMessageContent(CommonUtil.messageFormatI18N(CommonMsg.GIFT_EAMIL_ACCEPT),sysId,unit,unitType, messageId);
					soClient.puchCMDtoClient(receiver.getName(), CommonUtil.messageFormat(CommonMsg.HIGHLIGHT_STORAGE,item.getSysItem().getType(),item.getSysItem().getSubType()));
				}
				messageDao.deleteMessageItem(message.getId());
				logger.debug("delete detache item use : " + (System.currentTimeMillis() - start)
						+ "ms");
				start = System.currentTimeMillis();
			}
			//附件物品数量大于仓库限制后，整理仓库
			if(StringUtil.toInt(unit)>=Constants.MAX_STACK_SIZE){
				if (si.getType() == Constants.DEFAULT_MATERIAL_TYPE || si.getType() == Constants.DEFAULT_OPEN_TYPE) {
					CommonUtil.sortAllMaterial(playerId, si.getType());
				}
				if(si.getType() == Constants.DEFAULT_ITEM_TYPE){
					CommonUtil.sortMaterialsBySubType(playerId, si.getType(), Constants.DEFAULT_ITEM_SUBTYPE.COST.getValue());
				}
			}
		} else {
			throw new Exception(ExceptionMessage.NOT_HAVE_ATTACH);
		}
	}

	// ===============================================================================
	// Pay Log Service
	// ===============================================================================
	public void createPayLog(PayLog payLog) {
		payLogDao.createPayLog(payLog);
	}

	public MessageService getMessageService() {
		return messageService;
	}

	public void setMessageService(MessageService messageService) {
		this.messageService = messageService;
	}

	// ===============================================================================
	// xunlei order Service
	// ===============================================================================
	public Player createAddXunleiOrderLog(XunleiOrderLog xunleiPayLog, int playerId,StringBuffer checkSuccess)
			throws Exception {
		xunleiOrderLogDao.createXunleiOrderLog(xunleiPayLog);
		Player player = getService.getPlayerById(playerId);
		PlayerInfo playerInfo = getService.getPlayerInfoById(playerId);
		playerInfo.setXunleiPoint(playerInfo.getXunleiPoint() + xunleiPayLog.getAmount());
		playerInfoDao.update(playerInfo);
		
		mcc.delete(CacheUtil.oPlayerInfo(playerId));
		// playerDao.updatePlayerInCache(player);
	
		soClient.messageUpdatePlayerMoney(player);
		//add vip exp
		ServiceLocator.updateService.updateVipUpExp(Constants.VIP_EARN_EXP_METHODS.CHARGE.getValue(), playerId, xunleiPayLog.getRmb()*Constants.VIP_CHARGE_EXP);
		//add success log 
		checkSuccess.append(" --su");
		return player;
	}

	
	public void createReduceXunleiOrderLog(XunleiOrderLog xunleiPayLog, int playerId,PlayerInfo playerInfo)
			throws Exception {
		xunleiOrderLogDao.createXunleiOrderLog(xunleiPayLog);
		Player player = getService.getSimplePlayerById(playerId);
		playerInfo.setXunleiPoint(playerInfo.getXunleiPoint() - xunleiPayLog.getAmount());
		ServiceLocator.updateService.updatePlayerActivity(Constants.ACTION_ACTIVITY.PAY_FC.getValue(), player.getId(), null, xunleiPayLog.getAmount(), 0, null, null);
		playerInfoDao.update(playerInfo);
		mcc.delete(CacheUtil.oPlayerInfo(playerId));
		mcc.delete(CacheUtil.oPlayer(playerId));
		mcc.delete(CacheUtil.sPlayer(playerId));
		soClient.messageUpdatePlayerMoney(player);
	}
	
	public void createXunleiOrderLog(XunleiOrderLog xunleiPayLog)throws Exception {
		xunleiOrderLogDao.createXunleiOrderLog(xunleiPayLog);
	}
	
	
	public void presentToPlayer(List<SysItem> selects, String userName,int playerId, String content) throws Exception {
		final Player player = getService.getPlayerById(playerId);
		StringBuilder ids = new StringBuilder("");
		StringBuilder units = new StringBuilder("");
		StringBuilder unitTypes = new StringBuilder("");
		int unit = 1;
		int unitType = 1;
		for (SysItem sysItem : selects) {
			int i = sendItem(sysItem, player, null, Constants.BOOLEAN_NO, Constants.BOOLEAN_YES, Constants.BOOLEAN_NO);
			if (null != sysItem.getAllGpPricesList()&& sysItem.getAllGpPricesList().size() > 0) {
				unitType = sysItem.getAllGpPricesList().get(0).getUnitType();
				unit = sysItem.getAllGpPricesList().get(0).getUnit();
			} else if (null != sysItem.getAllCrPricesList()&& sysItem.getAllCrPricesList().size() > 0) {
				unitType = sysItem.getAllCrPricesList().get(0).getUnitType();
				unit = sysItem.getAllCrPricesList().get(0).getUnit();
			} else if (null != sysItem.getAllVoucherPricesList()&& sysItem.getAllVoucherPricesList().size() > 0) {
				unitType = sysItem.getAllVoucherPricesList().get(0).getUnitType();
				unit = sysItem.getAllVoucherPricesList().get(0).getUnit();
			}
			ids.append(sysItem.getId()).append(",");
			units.append(unit).append(",");
			unitTypes.append(unitType).append(",");
			logger.debug("GM send gift to player=" + playerId);
		}
		sendSystemMail(player, CommonMsg.GIFT_EMAIL_SYS, content, ids.toString(),units.toString(),unitTypes.toString());
	}
	public void packageActivity(SysItem sysItem,  Player player,SysActivity sa)throws Exception {
		StringBuilder ids = new StringBuilder("");
		StringBuilder units = new StringBuilder("");
		StringBuilder unitTypes = new StringBuilder("");
		for (SysItem si : sysItem.getPackages()) {
			awardActivity(player, si, ids,units,unitTypes,sa);
			logger.debug("open packages send sysitems to player="+ player.getId());
		}
		sendSystemMail(player, CommonMsg.PACKAGE_EMAIL_SYS, CommonUtil.messageFormatI18N(CommonMsg.PACKAGE_EMAIL, sysItem.getDisplayName()), ids.toString(),units.toString(),unitTypes.toString());
	}
//	public void packageAchievement(SysItem sysItem,  Player player)throws Exception {
//		StringBuilder ids = new StringBuilder("");
//		for (SysItem si : sysItem.getPackages()) {
//			awardAchievement(player, si, ids);
//			logger.debug("open packages send sysitems to player="+ player.getId());
//		}
//		sendSystemMail(player, CommonMsg.PACKAGE_EMAIL_SYS, CommonUtil.messageFormat(CommonMsg.PACKAGE_EMAIL, sysItem.getDisplayName()), ids.toString());
//	}	
	public int createPlayerMission(PlayerMission playerMission)
			throws Exception {
		int id = playerMissionDao.createPlayerMission(playerMission);
		String key = CacheUtil.oPlayerMissionList(playerMission.getPlayerId());
		mcc.delete(key);
		return id;
	}
	/**
	 * 
	 * @param check (can be null, just used to log the process)
	 * @param sysItem
	 * @param player
	 * @param payment
	 * @return
	 * @throws Exception
	 */
	public int buyPlayerItemByNewPayment(StringBuffer check,SysItem sysItem, Player player, Payment payment) throws Exception{
		Payment tempPayment = paymentDao.getPaymentListById(sysItem.getId()).get(0);
		int unit = payment.getUnit();
		
		PlayerInfo playerInfo = getService.getPlayerInfoById(player.getId());
		int leftMoney = 0;
		int frontPay = 0;
		if(sysItem.getIsVip()>0){
			if(player.getIsVip()<sysItem.getIsVip()){
				throw new NotBuyEquipmentException(ExceptionMessage.NOT_VIP_BUY);
			}
		}
		//make check not be null
		if(check==null){
			check=new StringBuffer();
		}
		if(tempPayment.getPayType() == 1){//C币
			if(player.getGPoint() < unit * payment.getCost()){
				throw new BaseException(ExceptionMessage.NOT_ENOUGH_GP);
			}
			frontPay=player.getGPoint();
			leftMoney = player.getGPoint() - unit * payment.getCost();
			player.setGPoint(leftMoney);
			playerDao.updatePlayerInCache(player);
			mcc.delete(CacheUtil.oPlayer(player.getId()));
			mcc.delete(CacheUtil.sPlayer(player.getId()));
			//add log for paid 
			check.append("paid "+unit * payment.getCost()+" GP|");
			
			nosqlService.publishEvent(new PayGpEvent(unit * payment.getCost(), System.currentTimeMillis() / 1000, player.getId(), player.getName()));
		} else if(tempPayment.getPayType() == 2){
			if(playerInfo.getXunleiPoint() < unit * payment.getCost()){
				return 0;
			}
			frontPay=playerInfo.getXunleiPoint();
			leftMoney = playerInfo.getXunleiPoint() - unit * payment.getCost();
			playerInfo.setXunleiPoint(leftMoney);
			ServiceLocator.updateService.updatePlayerActivity(Constants.ACTION_ACTIVITY.PAY_FC.getValue(), player.getId(), null, unit * payment.getCost(), 0, null, null);
			playerInfoDao.update(playerInfo);
			mcc.delete(CacheUtil.oPlayerInfo(player.getId()));
			nosqlService.publishEvent(new PurchaseEvent(DateUtil.getCurrentTimeStr(), player.getId(), player.getName(), sysItem));
			nosqlService.publishEvent(new PayCrEvent(unit * payment.getCost(), System.currentTimeMillis() / 1000, player.getId(), player.getName()));
			//add log for paid 
			check.append("paid "+unit * payment.getCost()+" CR|");
			
			ServiceLocator.updateService.updatePlayerGrowthMission(player, GrowthMissionType.FIRST_PURCHASE);
			ServiceLocator.updateService.addPlayerTrack(player.getId(), "", 0, 0, payment.getCost()*unit, DateUtil.getDf().format(new Date()), "2"+Constants.XUNLEI_LOG_DELIMITER+sysItem.getId()+Constants.XUNLEI_LOG_DELIMITER+payment.getUnitType()+Constants.XUNLEI_LOG_DELIMITER+payment.getUnit());
		}
		
		if (ConfigurationUtil.SWITCH_XUNLEI_LOG.getIsOn()) {
			nosqlService.addXunleiLog("7.3"
					+ Constants.XUNLEI_LOG_DELIMITER + player.getUserName()
					+ Constants.XUNLEI_LOG_DELIMITER + player.getName()
					+ Constants.XUNLEI_LOG_DELIMITER + tempPayment.getPayType()
					+ Constants.XUNLEI_LOG_DELIMITER + sysItem.getId()
					+ Constants.XUNLEI_LOG_DELIMITER+ sysItem.getDisplayNameCN()
					+ Constants.XUNLEI_LOG_DELIMITER+ (payment.getUnitType() == 1 ? payment.getUnit() : 1)
					+ Constants.XUNLEI_LOG_DELIMITER + unit * payment.getCost()
					+ Constants.XUNLEI_LOG_DELIMITER+ CommonUtil.simpleDateFormat.format(new Date())
					+ Constants.XUNLEI_LOG_DELIMITER + frontPay
					+ Constants.XUNLEI_LOG_DELIMITER + leftMoney
					);
		}
		{
			infoLogger.log(LogServerMessage.secondLog.name(), Level.INFO_INT, 
					LogUtils.JoinerByTab.join("5.1",player.getUserName(),CommonUtil.simpleDateFormat.format(new Date()),ConfigurationUtil.XUNLEI_SERVER_IP,unit*payment.getCost(),payment.getPayType(),sysItem.getDisplayNameCN(),
							player.getName(),sysItem.getId(),payment.getUnitType(),payment.getUnit(),leftMoney));
		}
		int newItemId = sendItem(sysItem, player, payment, Constants.BOOLEAN_NO, Constants.BOOLEAN_YES,  Constants.BOOLEAN_NO);
		
		//add log for have send the item for this buy process
		check.append("get item "+newItemId+" |su\t");
		
		soClient.messageUpdatePlayerMoney(player);
		//save log
		savePayLog(player, sysItem, tempPayment.getPayType(), leftMoney,unit * payment.getCost());
		int maxNumItemId = CommonUtil.sortMaterial(player.getId(), Constants.DEFAULT_MATERIAL_TYPE, sysItem.getId());
		return (maxNumItemId > 0) ? maxNumItemId : newItemId;
	}
	
	/**
	 * 发送礼品盒给玩家
	 * @param checkDays
	 * @param playerId
	 * @param payment
	 * @throws Exception
	 */
	public Player presentDailyCheckGiftBox(int checkDays, int playerId ,Payment payment) throws Exception{
		Player player=getService.getPlayerById(playerId);
		CommonUtil.checkNull(player, ExceptionMessage.NO_HAVE_THE_CHARACTER);
		Logger log = ServiceLocator.dailyCheckLog;
		SysItem si = null;
		
		switch (checkDays) {
			case Constants.DAILY_CHECK_1ST_DAY :
				si = getService.getDailyCheckGiftBox(Constants.DAILY_CHECK_AWARD_LEVEL.PRIMARY_CHECK.getValue());
				if(si!=null){
				awardToPlayer(player, si, payment, null, Constants.BOOLEAN_YES);
				log.info("DailyCheck/Award:\t" + playerId +"\t" + si.getDisplayNameCN());
				}else{
					log.warn("DailyCheck/SysItem:\t"+ "GiftBox1");
					throw new BaseException(ExceptionMessage.NOT_GET_SYSITEM_BY_ID_MSG);
				}
				break;
			case Constants.DAILY_CHECK_2ND_DAY :
				si = getService.getDailyCheckGiftBox(Constants.DAILY_CHECK_AWARD_LEVEL.MIDDLE_CHECK.getValue());
				if(si!=null){
				awardToPlayer(player, si, payment, null, Constants.BOOLEAN_YES);
				log.info("DailyCheck/Award:\t" + playerId +"\t" + si.getDisplayNameCN());
				}else{
					log.error("DailyCheck/SysItem:\t"+ "GiftBox2");
					throw new BaseException(ExceptionMessage.NOT_GET_SYSITEM_BY_ID_MSG);
				}
				break;
			case Constants.DAILY_CHECK_3RD_DAY :
				si = getService.getDailyCheckGiftBox(Constants.DAILY_CHECK_AWARD_LEVEL.HIGH_CHECK.getValue());
				if(si!=null){ 
				awardToPlayer(player, si, payment, null, Constants.BOOLEAN_YES);
				log.info("DailyCheck/Award:\t" + playerId +"\t" + si.getDisplayNameCN());
				}else{
					log.error("DailyCheck/SysItem:\t"+ "GiftBox3");
					throw new BaseException(ExceptionMessage.NOT_GET_SYSITEM_BY_ID_MSG);
				}
				break;
			case Constants.DAILY_CHECK_4TH_DAY :
				si = getService.getDailyCheckGiftBox(Constants.DAILY_CHECK_AWARD_LEVEL.SUPER_CHECK.getValue());
				if(si!=null){
				awardToPlayer(player, si, payment, null, Constants.BOOLEAN_YES);
				log.info("DailyCheck/Award:\t" + playerId +"\t" + si.getDisplayNameCN());
				}else{
					log.error("DailyCheck/SysItem:\t"+ "GiftBox4");
					throw new BaseException(ExceptionMessage.NOT_GET_SYSITEM_BY_ID_MSG);
				}
				break;
			default:
				//全勤礼包	20141104 
				if(Constants.DAILY_CHECK_5TH_DAY()==checkDays){	
					si = getService.getDailyCheckGiftBox(Constants.DAILY_CHECK_AWARD_LEVEL.LUXURY_CHECK.getValue());
					if(si!=null){
					awardToPlayer(player, si, payment, null, Constants.BOOLEAN_YES);
					log.info("DailyCheck/Award:\t" + playerId +"\t" + si.getDisplayNameCN());
					}else{
						log.error("DailyCheck/SysItem:\t"+ "GiftBox5");
						throw new BaseException(ExceptionMessage.NOT_GET_SYSITEM_BY_ID_MSG);
					}
					break;
				}
				break;
		}
		return player;
	}
	/**
	 * Online award,Daily check award
	 * @param oaList
	 * @param playerId
	 * @throws Exception
	 */
	public void presentAwardToPlayer(List<OnlineAward> oaList,int playerId) throws Exception{
		final Player player=getService.getPlayerById(playerId);
		CommonUtil.checkNull(player, ExceptionMessage.NO_HAVE_THE_CHARACTER);
		Payment pm = new Payment();
		if(oaList==null||oaList.size()==0){
			return;
		}
		for(OnlineAward oa : oaList){
			SysItem si = getService.getSysItemByItemId(oa.getItemId());
			pm.setUnit(oa.getUnit());
			pm.setUnitType(oa.getUnitType());
			awardToPlayer(player, si, pm, null, Constants.BOOLEAN_YES);
		}
	}

	/**
	 * 玩家每日预测消耗C币（单个数字）
	 * @param playerId
	 * @param cost
	 * @throws Exception
	 */
	public Player dailyNumPay(int playerId,int cost,int num,String tomDateStr) throws Exception{
		Player player = getService.getSimplePlayerById(playerId);
		int gp = player.getGPoint();
		if (cost > gp) {
			throw new BaseException(ExceptionMessage.GUS_NUM_NOT_ENOUGH_GP);
		}
		nosqlService.dailyGuessNum(playerId, num, tomDateStr);
		int leftMoney = gp - cost;
		player.setGPoint(leftMoney);
		ServiceLocator.updateService.updatePlayerInfoOnly(player);
		mcc.delete(CacheUtil.oPlayer(playerId));
		mcc.delete(CacheUtil.sPlayer(playerId));
		soClient.messageUpdatePlayerMoney(player);
		nosqlService.publishEvent(new PayGpEvent(cost, System.currentTimeMillis() / 1000, playerId, player.getName()));
		return player;
	}

	/**
	 * 玩家每日预测消耗C币（多个数字）
	 * 
	 * @param playerId
	 * @param cost
	 * @throws Exception
	 */
	public Player dailyNumsPay(int playerId, int cost, String nums, String tomDateStr) throws Exception{
		Player player = getService.getSimplePlayerById(playerId);
		int gp = player.getGPoint();
		if (cost > gp) {
			throw new BaseException(ExceptionMessage.GUS_NUM_NOT_ENOUGH_GP);
		}
		nosqlService.dailyGuessNums(playerId, nums, tomDateStr);
		int leftMoney = gp - cost;
		player.setGPoint(leftMoney);
		ServiceLocator.updateService.updatePlayerInfoOnly(player);
		mcc.delete(CacheUtil.oPlayer(playerId));
		mcc.delete(CacheUtil.sPlayer(playerId));
		soClient.messageUpdatePlayerMoney(player);
		nosqlService.publishEvent(new PayGpEvent(cost, System.currentTimeMillis() / 1000, playerId, player.getName()));
		return player;
	}

	/**
	 * 产生幸运数
	 * @param todayNum
	 * @param dailyRandomKey
	 * @return
	 * @throws Exception
	 */
	public int dailyRandomNumCreate(int todayNum,String dailyRandomKey) throws Exception{
		String dateStr = CommonUtil.dateFormatDate.format(new Date());
		int count = Constants.NUM_ZERO;
		//5次拿不到随机数，则抛出异常
		while(todayNum==-1&&count++<Constants.TRY_LOCK_COUNT){//循环一直等到随机数产生
			boolean isLocked =false;
			int lockTime = Constants.RANDOM_NUM_LOCK_TIME;//ms
			isLocked = ServiceLocator.ml.tryLock(dailyRandomKey, lockTime);
			if (isLocked) { // 加锁了便需要解锁
				try {
					todayNum = nosqlService.dailyRandomNum(dateStr);
				} finally {
					ServiceLocator.ml.unlock(dailyRandomKey);// 无论如何要在这里解锁
				}
			} else {//拿锁失败
				ServiceLocator.fileLog.warn("dailyRandomNumCreate/LockFail:\t"+dailyRandomKey +"\t"+lockTime);
				throw new BaseException(ExceptionMessage.CAN_NOT_GET_DAILY_NUM_MSG); // 在指定时间内拿不到锁，抛出此异常，提示用户无法获得随机数
			}   
		}
		if(todayNum ==-1){
//			ServiceLocator.dailyNumLog.warn("CreateService.dailyRandomNumCreate : can not create random num in "+Constants.TRY_LOCK_COUNT +" times when daily random num create");
			ServiceLocator.dailyNumLog.warn("DailyNumCreate/LockFailCount:\t"+Constants.TRY_LOCK_COUNT );
			throw new BaseException(ExceptionMessage.CAN_NOT_GET_DAILY_NUM_MSG);
		}else{
//			ServiceLocator.dailyNumLog.info("CreateService.dailyRandomNumCreate : daily random num is " +todayNum +"when daily random num create");
			ServiceLocator.dailyNumLog.info("DailyNumCreate/Success:\t" +todayNum);
		}
		return todayNum;
	}
	/**
	 * 玩家补签和提前签到消耗FC币
	 * @param playerId
	 * @param cost
	 * @param dateStr
	 * @throws Exception
	 * type 1补签 2正常签 3预签 
	 */
	public String dailyCheckPay(int playerId,int type,Calendar checkDate ,Calendar today) throws Exception{

		Logger log = ServiceLocator.dailyCheckLog;
		PlayerInfo playerInfo = getService.getPlayerInfoById(playerId);
		CommonUtil.checkNull(playerInfo, ExceptionMessage.NO_HAVE_THE_CHARACTER);
		String dateStr = CommonUtil.dateFormatDate.format(checkDate.getTime());
		String todayStr = CommonUtil.dateFormatDate.format(today.getTime());
		List<String> cDays= nosqlService.getPlayerCheckList(playerId);
		int cDay = checkDate.get(Calendar.DAY_OF_MONTH);
		int sDay = today.get(Calendar.DAY_OF_MONTH);
//		System.out.println("cDay="+cDay+" && sDay="+sDay+" && type="+type);
		boolean flag = cDay<sDay&&type!=1?true:cDay==sDay&&type!=2?true:cDay>sDay&&type!=3;//验证客户端签到类型与服务器端时间是否冲突
		if(flag){
			return Converter.dailyCheck(todayStr, today.get(Calendar.DAY_OF_WEEK)-1, cDays,Constants.DAILY_CHECK_RESULTS.C_S_NOT_SYN.getValue());
		}
		String yearMonth = String.valueOf(today.get(Calendar.YEAR)) + today.get(Calendar.MONTH);
//		System.out.println("dateStr="+dateStr+" && playerId="+playerId+" && type="+type+" && yearMonth="+yearMonth+" && todayStr="+todayStr);		
		if (!nosqlService.isPlayerCheck(playerId, dateStr)) {//判断是否签过
			int cr = playerInfo.getXunleiPoint();
			int cost = 0;
			Player player = getService.getPlayerById(playerId);
			int vipLevel = player.getIsVip();
			int vipCheckTimes = Constants.VIP_DAILY_CHECK_COUNT[vipLevel>0?vipLevel:0]; // vip会员允许免费补签和预签的次数
		
			// 玩家作为vip时已预签和已补签次数合
			int vipCheckedTimes = nosqlService.getVipPlayerCheckTimes(playerId,0,yearMonth) + nosqlService.getVipPlayerCheckTimes(playerId,1,yearMonth);
//			System.out.println("vip checkTimes="+vipCheckedTimes);
			
//			int checkedTimes = nosqlService.getPlayerCheckTimes(playerId,0,yearMonth) + nosqlService.getPlayerCheckTimes(playerId,1,yearMonth);
//			System.out.println("all checkTimes="+checkedTimes);

			if (type==1) {//补签
				if (nosqlService.getPlayerCheckTimes(playerId,0,yearMonth)>=Constants.DAILY_CHECK_AGO_CHANCE) {
					return Converter.dailyCheck(todayStr, today.get(Calendar.DAY_OF_WEEK)-1, cDays,Constants.DAILY_CHECK_RESULTS.NO_CHECK_AGO_CHANCE.getValue());
				}
				int checkOverVip = vipCheckedTimes - vipCheckTimes;//Vip 补预签剩余次数
				int checkOver = nosqlService.getPlayerCheckTimes(playerId,0,yearMonth) + nosqlService.getPlayerCheckTimes(playerId,1,yearMonth);	//非Vip补预签次数
				checkOver = (checkOverVip>=0?((checkOver-vipCheckTimes)):vipLevel>0?vipCheckTimes>=vipCheckedTimes?0:checkOver:checkOver+checkOverVip);
				int costC	=	( 5 * checkOver * (checkOver+1))+Constants.DAILY_CHECK_AGO_COST;
				cost = checkOverVip<0&&checkOver<=0&&vipLevel>0?0:costC;//vipCheckTimes>=vipCheckedTimes ? 0 : costC;
//				System.out.println("type=1 and cost="+cost+" && vipCheckTimes="+vipCheckTimes);
				if (cost > cr) {
					return Converter.dailyCheck(todayStr, today.get(Calendar.DAY_OF_WEEK)-1, cDays,Constants.DAILY_CHECK_RESULTS.FC_NOT_ENOUGH.getValue());
				}
				nosqlService.updatePlayerCheckTimes(playerId, 0,yearMonth);
				if (vipLevel>0)
					nosqlService.updateVipPlayerCheckTimes(playerId, 0,yearMonth);
			} else if (type==3) {//预签
				if (nosqlService.getPlayerCheckTimes(playerId,1,yearMonth)>=Constants.DAILY_CHECK_FUTURE_CHANCE) {
					return Converter.dailyCheck(todayStr, today.get(Calendar.DAY_OF_WEEK)-1, cDays,Constants.DAILY_CHECK_RESULTS.NO_CHECK_FUTURE_CHANCE.getValue());
				}
				cost = vipCheckTimes>vipCheckedTimes ? 0 : Constants.DAILY_CHECK_FUTURE_COST;
//				System.out.println("type=3 and cost="+cost+" && vipCheckTimes="+vipCheckTimes);
				if (cost > cr) {
					return Converter.dailyCheck(todayStr, today.get(Calendar.DAY_OF_WEEK)-1, cDays,Constants.DAILY_CHECK_RESULTS.FC_NOT_ENOUGH.getValue());
				}
				nosqlService.updatePlayerCheckTimes(playerId, 1,yearMonth);
				if (vipLevel>0)
					nosqlService.updateVipPlayerCheckTimes(playerId, 1,yearMonth);
			}
			
			{
				int leftMoney = cr - cost;
				if(type != 2){
					final PayLog payLog = new PayLog();
					payLog.setUserId(getService.getSimplePlayerById(playerId).getUserName());
					payLog.setPlayerId(player.getId());
					payLog.setItemId(0);
					payLog.setItemType(0);
					payLog.setPayType(2);
					payLog.setCreateTime(new Date());
					payLog.setLeftMoney(leftMoney);
					payLog.setPayUse(1);
					if(type==1){
						payLog.setItemName("补签");
						payLog.setPayAmount(cost);
					}else if(type==3){
						payLog.setItemName("预签");
						payLog.setPayAmount(cost);
					}
					final Runnable writePayLog = new Runnable() {
						@Override
						public void run() {
							createPayLog(payLog);

						}
					};
					ServiceLocator.asynTakService.execute(writePayLog);
					
					playerInfo.setXunleiPoint(leftMoney);
					ServiceLocator.updateService.updatePlayerActivity(Constants.ACTION_ACTIVITY.PAY_FC.getValue(), player.getId(), null, cost, 0, null, null);
					playerInfoDao.update(playerInfo);
					mcc.delete(CacheUtil.oPlayerInfo(playerId));
					soClient.messageUpdatePlayerMoney(player);
					nosqlService.publishEvent(new PayCrEvent(cost, System.currentTimeMillis() / 1000, playerId, player.getName()));
				}
				if (ConfigurationUtil.SWITCH_XUNLEI_LOG.getIsOn()) {
					nosqlService.addXunleiLog("7.2"
							+ Constants.XUNLEI_LOG_DELIMITER + player.getUserName()
							+ Constants.XUNLEI_LOG_DELIMITER + player.getName()
							+ Constants.XUNLEI_LOG_DELIMITER + 2
							+ Constants.XUNLEI_LOG_DELIMITER + type
							+ Constants.XUNLEI_LOG_DELIMITER + cost
							+ Constants.XUNLEI_LOG_DELIMITER+ CommonUtil.simpleDateFormat.format(new Date())
							+ Constants.XUNLEI_LOG_DELIMITER+cr
							+ Constants.XUNLEI_LOG_DELIMITER+leftMoney
							);
				}
			}
			nosqlService.playerCheck(playerId, dateStr);
			cDays= nosqlService.getPlayerCheckList(playerId);
			log.info("DailyCheck/Check:\t"+playerId +"\t" + dateStr);
			int checkDays =  cDays.size();
			Payment payment = new Payment();
			payment.setUnit(Constants.NUM_ONE);
			payment.setUnitType(Constants.NUM_ONE);
			presentDailyCheckGiftBox(checkDays, playerId, payment);
			// 成长任务12：首次签到
			ServiceLocator.updateService.updatePlayerGrowthMission(player, GrowthMissionType.FIRST_CHECKDAY);
			mcc.delete(CacheUtil.oPlayer(playerId));
			mcc.delete(CacheUtil.sPlayer(playerId));

			nosqlService.publishEvent(new PayCrEvent(cost, System.currentTimeMillis() / 1000, playerId, player.getName()));

		}else{
//			log.info("Player:" + playerId + "already checked on " + dateStr);
			log.info("DailyCheck/Checked:\t" + playerId + "\t" + dateStr);
		}
		return Converter.dailyCheck(todayStr, today.get(Calendar.DAY_OF_WEEK)-Constants.NUM_ONE, cDays,Constants.DAILY_CHECK_RESULTS.CHECK_SUCCESS.getValue());
	}
	
	
	
	
	/**
	 * 发放每日签到的每日奖励，和每月签到合计奖励分开发放
	 * @throws Exception 
	 */
	public QuietBounds createBoundsDaily(int playerId) throws Exception{
		QuietBounds quietBounds=getService.getDailyQuietBounds();
		//个人黑原石奖励
		createResBoundsDaily(playerId,quietBounds.getDisResBounds());
		
		return quietBounds;
	}
	
	/**
	 * 在线时长奖励
	 * @throws Exception 
	 */
	public QuietBounds createBoundsOnlineAward(int playerId,int level) throws Exception{
		QuietBounds quietBounds=getService.getOnlineQuietBounds(playerId,level);
		//个人黑原石奖励
		createResBoundsDaily(playerId,quietBounds.getDisResBounds());
		
		return quietBounds;
	}	
	
	/**
	 * 每日个人黑原石奖励
	 * @param playerId
	 * @throws Exception
	 */
	private void createResBoundsDaily(int playerId,int awardBound) throws Exception{
		Team team=getService.getTeamByPlayerId(playerId);
		if(team!=null){
			Player player=getService.getPlayerById(playerId);	
			int dis=player.getUnusableResource();
			int max=Constants.TEAMSPACELEVELCONSTANTS.getTeamSpaceLevelConstants(team.getTeamSpaceLevel()).getpMaxOrgRes();
			int bounds=awardBound;
			int disBounded=0;
			if(dis+bounds>max){
				disBounded=max;
			}else{
				disBounded=dis+bounds;
			}
			player.setUnusableResource(disBounded);
			refreshPlayerTeamALLMessages(player, null, null);
		}
	}
	
	
	/**
	 * 开启vip币，随即15个奖励，并将奖励的id 存入redis
	 * @param playerId
	 * @return
	 * @throws Exception
	 */
	public List<OnlineAward> createVipRandomAwardList(int playerId) throws Exception {
		List<OnlineAward> vscList = getService.getVipRandomAwardList(Constants.VIP_RANDOM_SYSITEM_NUM);
		List<OnlineAward> retList = new ArrayList<OnlineAward>();
		Logger log = ServiceLocator.vipSafecabinetLog;
		//每次进入列表页面重新随机好15个VipSafeCabinet,并将VipSafeCabinet id存入redis
		String vipRandomListKey = Constants.VIP_RANDOM_VSC_LIST_PREFIX + playerId;
		nosqlService.deleteByKey(vipRandomListKey);
		StringBuilder randomVscIds = new StringBuilder();
		for(OnlineAward vsc :vscList){
			if(vsc!=null){
				OnlineAward oa = vsc;
				randomVscIds.append(vsc.getId()+":");
				SysItem si = getService.getSysItemByItemId(vsc.getItemId());
				if(si!=null){
					oa.setSysItem(si);
				}else{
				log.warn("VipRandomList/NoSysItem:\t"+vsc.getItemId()+"\t" + vsc.getId());
				}
				retList.add(oa);
			}
		}
		log.debug("VipRandomList/RandomIds:\t"+playerId+"\t" + randomVscIds.toString());
		nosqlService.setVipRandomList(playerId,randomVscIds.toString());
		return retList;
	}
	/**
	 * 随机5个index，存放玩家将要获得的奖励，将index写入redis
	 * @param playerId
	 * @param vscList
	 * @throws Exception
	 */
	public void createRandomVipAwardIndexs(int playerId,List<OnlineAward> vscList) throws Exception{
		int[] randomIndexs = getService.randomDifIndexFromList(Constants.VIP_OPEN_CHANCE_NUM,vscList);
		//存入redis
		StringBuilder playerRandomIndexs = new StringBuilder();
		for(int  idx : randomIndexs){
			playerRandomIndexs.append((idx-1) +":");
		}
		String playerRandomStartIndexsKey = Constants.VIP_RANDOM_START_LIST_PREFIX + playerId;
		nosqlService.deleteByKey(playerRandomStartIndexsKey);
		nosqlService.setRandomStartIndexs(playerId,playerRandomIndexs.toString());
		ServiceLocator.vipSafecabinetLog.debug("VipRandomStart/RandomIndexs:\t"+playerId+"\t" + playerRandomIndexs.toString());
	}
	/**
	 * 异步发送邮件接口
	 * 
	 * @param player
	 * @param title
	 * @param content
	 * @param itemsId
	 *            如果没有物品，此参数为""或者null
	 */
	public void sendSystemMail(final Player player, final String title,final String content, String itemsId) {
		if (null == itemsId) {
			itemsId = "";
		}
		final String temp = itemsId;
		Runnable sendMailToPlayer = new Runnable() {
			@Override
			public void run() {
				try {
					messageService.sendSystemMailWithSysItemInContent(player,
							title, content, temp);
				} catch (Exception e) {
					logger.warn("error happened in sending mail to player");
				}
			};
		};
		ServiceLocator.asynTakService.execute(sendMailToPlayer);
	}

	public void sendSystemMail(final Player player, final String title,final String content, String itemsId,String itemUnits,String itemUnitTypes) {
		if (null == itemsId) {
			itemsId = "";
		}
		final String temp = itemsId;
		final String temp1 = itemUnits;
		final String temp2 = itemUnitTypes;
		Runnable sendMailToPlayer = new Runnable() {
			@Override
			public void run() {
				try {
					messageService.sendSystemMailWithSysItemInContent(player,title, content, temp,temp1,temp2);
				} catch (Exception e) {
					logger.warn("error happened in sending mail to player");
				}
			};
		};
		ServiceLocator.asynTakService.execute(sendMailToPlayer);
	}
	
	public void sendSystemMail(final Player receiver ,final String subject,  final String content ,final int itemId,final Payment payment ) {
		
		Runnable sendMailToPlayer = new Runnable() {
			@Override
			public void run() {
				try {
					messageService.sendSystemMailWithSysItemInContent(receiver , subject,  content , itemId, payment );
				} catch (Exception e) {
					logger.warn("error happened in sending mail to player pid:" + receiver.getId() + "\titemid:"+itemId +"\tunit:" +payment.getUnit() + "\tunittype:"+payment.getUnitType());
				}
			};
		};
		ServiceLocator.asynTakService.execute(sendMailToPlayer);
	}
	
	
	/**Common method award item to player
	 * @param player
	 * @param sysItem
	 * @param payment
	 * @param ids
	 * @param isGift
	 * @param isBind
	 * @param isDefault
	 * @throws Exception
	 */
	public void awardToPlayer(Player player, SysItem sysItem,Payment payment,StringBuilder ids,String isBind) throws Exception{
		if (sysItem.getType() == 4 && sysItem.getIId() == 8) {// CAL类道具
			int unit = 1;
			if(payment!=null){
				unit=payment.getUnit()>unit?payment.getUnit():unit;
			}
			awardCALToPlayer(player, (StringUtil.toInt(sysItem.getIValue()) * unit));
		} else if (sysItem.getType() == 4 && sysItem.getIId() == 10) {// 解锁角色道具
			awardReleaseItemToPlayer(player, sysItem);
		} else {
			sendItem(sysItem, player, payment, Constants.BOOLEAN_NO, isBind, Constants.BOOLEAN_NO);
		}
		if (null != ids) {
			ids.append(sysItem.getId()).append(",");
		}
	}
	/**
	 * 发送多个物品给玩家
	 * @param player
	 * @param sysItems
	 * @param payment
	 * @param ids
	 * @param isBind
	 * @throws Exception
	 */
	public void awardToPlayer(Player player, List<SysChest> sysChests,StringBuilder ids,String isBind) throws Exception{
		int calTotal = 0;
		int flag = 0;//标志位，通过二进制位运算，判断是否包含
		for(SysChest sc : sysChests){
			SysItem si = sc.getSysItem();
			if(si==null){
				si = getService.getSysItemByItemId(sc.getSysItemId());
			}
			if (si.getType() == 4 && si.getIId() == 8) {// CAL类道具
				calTotal +=(StringUtil.toInt(si.getIValue())*sc.getNumber());
			} else if (si.getType() == 4 && si.getIId() == 10) {// 解锁角色道具
				awardReleaseItemToPlayer(player, si);
			} else {
				sendItemWithoutPushCmd(si, player, new Payment(sc.getNumber(), sc.getUseType()),Constants.BOOLEAN_NO, isBind, Constants.BOOLEAN_NO);
				if(si.getIBuffId()<61||si.getIBuffId()>74){
					int f = (int)Math.pow(2, si.getType());
					if((flag&f)==0){
						flag=flag|f;
						soClient.puchCMDtoClient(player.getName(), CommonUtil.messageFormat(CommonMsg.HIGHLIGHT_STORAGE,si.getType(),si.getSubType()));
					}
				}
			}
			if (null != ids) {
				ids.append(si.getId()).append(",");
			}
		}
		if(calTotal>0){
			awardCALToPlayer(player, calTotal);
		}
	}
	/**
	 * common method package item
	 * @param player
	 * @param sysItem
	 * @param payment
	 * @param ids
	 * @param isGift
	 * @throws Exception
	 */
	public void packageToPlayer(Player player, SysItem sysItem,Payment payment,StringBuilder ids,String isGift) throws Exception{
		for (SysItem si : sysItem.getPackages()) {
			if (si.getType() == 4 && si.getIId() == 8) {// CAL类道具
				awardCALToPlayer(player, StringUtil.toInt(sysItem.getIValue()));
			} else if (si.getType() == 4 && si.getIId() == 10) {// 解锁角色道具
				awardReleaseItemToPlayer(player, sysItem);
			} else {
				sendItem(si, player, payment, isGift, Constants.BOOLEAN_NO, Constants.BOOLEAN_NO);
			}
			if (null != ids) {
				ids.append(si.getId()).append(",");
			}
		}
		sendSystemMail(player, CommonMsg.PACKAGE_EMAIL_SYS, CommonUtil.messageFormatI18N(CommonMsg.PACKAGE_EMAIL, sysItem.getDisplayName()), ids.toString());
	}
	/**
	 * award player finished  the activity 
	 * @param player
	 * @param sysItem
	 * @param ids: names in mail
	 * @param sa
	 * @throws Exception
	 */
	public void awardActivity(final Player player, SysItem sysItem,StringBuilder ids,SysActivity sa) throws Exception {
		Payment payment=new Payment();
		if (sysItem.getType() == 4 && sysItem.getIId() == 8) {// CAL类道具
			awardCALToPlayer(player, StringUtil.toInt(sysItem.getIValue()) * sa.getUnit());
		} else if (sysItem.getType() == 4 && sysItem.getIId() == 10) {// 解锁角色道具
			awardReleaseItemToPlayer(player, sysItem);
		} else if(sa!=null){
			payment.setUnit(sa.getUnit());
			payment.setUnitType(sa.getUnitType());
			sendItem(sysItem, player, payment, null, Constants.BOOLEAN_YES, null);
		}else {
			sendItem(sysItem, player, null, null, Constants.BOOLEAN_YES, null);
		}
		if(null != ids){
			ids.append(sysItem.getId()).append(",");
		}
	}
	/**
	 * award player finished  the activity  with unit and unitTypes
	 * @param player
	 * @param sysItem
	 * @param ids
	 * @param units
	 * @param unitTypes
	 * @param sa
	 * @throws Exception
	 */
	public void awardActivity(final Player player, SysItem sysItem,StringBuilder ids,StringBuilder units ,StringBuilder unitTypes,SysActivity sa) throws Exception {
		Payment payment=new Payment();
		int unit = 1;
		int unitType = 1;
		if (sysItem.getType() == 4 && sysItem.getIId() == 8) {// CAL类道具
			if(payment!=null){
				unit=sa.getUnit()>unit?sa.getUnit():unit;
			}
			awardCALToPlayer(player, (StringUtil.toInt(sysItem.getIValue()) * unit));
		} else if (sysItem.getType() == 4 && sysItem.getIId() == 10) {// 解锁角色道具
			awardReleaseItemToPlayer(player, sysItem);
		} else if(sa!=null){
			payment.setUnit(sa.getUnit());
			payment.setUnitType(sa.getUnitType());
			unit = sa.getUnit();
			unitType = sa.getUnitType();
			sendItem(sysItem, player, payment, null, Constants.BOOLEAN_YES, null);
		}else {
			if (null != sysItem.getAllGpPricesList()&& sysItem.getAllGpPricesList().size() > 0) {
				unitType = sysItem.getAllGpPricesList().get(0).getUnitType();
				unit = sysItem.getAllGpPricesList().get(0).getUnit();
			} else if (null != sysItem.getAllCrPricesList()&& sysItem.getAllCrPricesList().size() > 0) {
				unitType = sysItem.getAllCrPricesList().get(0).getUnitType();
				unit = sysItem.getAllCrPricesList().get(0).getUnit();
			} else if (null != sysItem.getAllVoucherPricesList()&& sysItem.getAllVoucherPricesList().size() > 0) {
				unitType = sysItem.getAllVoucherPricesList().get(0).getUnitType();
				unit = sysItem.getAllVoucherPricesList().get(0).getUnit();
			}
			sendItem(sysItem, player, null, null, Constants.BOOLEAN_YES, null);
		}
		if(null != ids){
			ids.append(sysItem.getId()).append(",");
			units.append(unit).append(",");
			unitTypes.append(unitType).append(",");
		}
	}
	/**
	 * award player finished  the achievement 
	 * @param player
	 * @param sysItem
	 * @param ids
	 * @throws Exception
	 */
	public void awardAchievement(Player player, SysItem sysItem, Payment payment) throws Exception {
		if (sysItem.getType() == 4 && sysItem.getIId() == 8) {// CAL类道具
			int unit = 1;
			if(payment!=null){
				unit=payment.getUnit()>unit?payment.getUnit():unit;
			}
			awardCALToPlayer(player, (StringUtil.toInt(sysItem.getIValue()) * unit));
		} /*else if (sysItem.getType() == 4 && sysItem.getIId() == 10) {// 解锁角色道具
			awardReleaseItemToPlayer(player, sysItem);
		} */ else {
			sendItem(sysItem, player, payment, null, Constants.BOOLEAN_YES, null);
			ServiceLocator.deleteService.deletePlayerItemInMemcached(player.getId(),sysItem);
		}
	}
	/**
	 * @param player
	 * @param sysItem CAL物品
	 * @throws Exception
	 */
	void awardCALToPlayer(Player player, int money) throws Exception{
		player.setGPoint(player.getGPoint() + money);
		nosqlService.updateStayData(player, money);// 今日表现
		playerDao.updatePlayerInCache(player);
		mcc.delete(CacheUtil.oPlayer(player.getId()));
		mcc.delete(CacheUtil.sPlayer(player.getId()));
		soClient.messageUpdatePlayerMoney(player);
		nosqlService.publishEvent(new PayGpEvent(money, System.currentTimeMillis() / 1000, player.getId(), player.getName()));
	}
	/**
	 * 派发玩家经验奖励
	 * @param player
	 * @param exp
	 * @return 玩家最终等级
	 * @throws Exception
	 */
	void awardExpToPlayer(Player player, int exp) throws Exception{
		//经验道具
		player.setExp(exp+player.getExp());
		Rank newRank = getService.getRankByExp(player.getExp());
		int newLevel = newRank.getId();
		int oldLevel = player.getRank();
		/* about level up thing */
		if (newLevel > player.getRank()) {
			//TODO xunlei log miss
			//玩家升级 派发新任务给玩家
			ServiceLocator.updateService.sendGrowthMission(player, newLevel, player.getRank());
			
			ServiceLocator.updateService.updatePlayerActivity(Constants.ACTION_ACTIVITY.LEVEL_ACTIVITY.getValue(), player.getId(), null,player.getRank(),newLevel,null,null);
			player.setRank(newLevel);
			
			//level achievement 
			List<PlayerAchievement> paList = getService.getPlayerAchievementVisible(player.getId());
			List<PlayerAchievement> finishedList = new ArrayList<PlayerAchievement>();
			for (PlayerAchievement pa : paList){
				if (pa.getStatus() != Constants.NUM_ZERO){
					continue ;
				}
				
				SysAchievement sa = pa.getAchievement();

				int action = sa.getAction();
				int numberThisRound = 0;
				if (Constants.ACTION.LEVELUP.getValue() == action){
					if(player.getRank()>=pa.getAchievement().getNumber()){//达到指定等级
						numberThisRound = player.getRank();
					}
					ServiceLocator.updateService.updatePlayerAchievementNotInStageClear(player,Constants.ACTION.LEVELUP.getValue(),numberThisRound);
					ServiceLocator.updateService.updatePlayerGrowthMission(player, GrowthMissionType.NEW_AWARD_1);
					ServiceLocator.updateService.updatePlayerGrowthMission(player, GrowthMissionType.NEW_AWARD_2);
					ServiceLocator.updateService.updatePlayerGrowthMission(player, GrowthMissionType.NEW_AWARD_3);
					ServiceLocator.updateService.updatePlayerGrowthMission(player, GrowthMissionType.NEW_AWARD_4);
					ServiceLocator.updateService.updatePlayerGrowthMission(player, GrowthMissionType.NEW_AWARD_5);
					ServiceLocator.updateService.updatePlayerGrowthMission(player, GrowthMissionType.NEW_AWARD_6);
					ServiceLocator.updateService.updatePlayerGrowthMission(player, GrowthMissionType.NEW_AWARD_7);
					ServiceLocator.updateService.updatePlayerGrowthMission(player, GrowthMissionType.NEW_AWARD_8);
					//通知客户端完成的成就
					if (!finishedList.isEmpty()){
						ServiceLocator.soClient.sendAchievementCompleted(finishedList,player.getId(), player.getName());
					}
					break;
				}
			}
			if (ConfigurationUtil.SWITCH_XUNLEI_LOG.getIsOn()) {
				ServiceLocator.nosqlService.addXunleiLog("4.5" + Constants.XUNLEI_LOG_DELIMITER + "任务获得" + Constants.XUNLEI_LOG_DELIMITER
						+ 999 + Constants.XUNLEI_LOG_DELIMITER + player.getUserName() + Constants.XUNLEI_LOG_DELIMITER + player.getName()
						+ Constants.XUNLEI_LOG_DELIMITER + CommonUtil.simpleDateFormat.format(new Date()) + Constants.XUNLEI_LOG_DELIMITER + oldLevel
						+ Constants.XUNLEI_LOG_DELIMITER + newLevel);
			}
			soClient.updateCharacterInfo(player, player.getTeam(), player.getRank());
			nosqlService.publishEvent(new LevelUpEvent(DateUtil.getCurrentTimeStr(), getService.getRankByExp(player.getExp()), player.getId(), player.getName()));
			if (player.getRank().equals(Constants.JOIN_TEAM_LIMIT)) {
				soClient.messageCMD(player.getName(),CommonMsg.REFRESH_ABLETOJOINTEAM);
			}
		}
		playerDao.updatePlayerInCache(player);
		mcc.delete(CacheUtil.oPlayer(player.getId()));
		mcc.delete(CacheUtil.sPlayer(player.getId()));
	}
	/**
	 * @param player
	 * @param sysItem CAL物品
	 * @throws Exception
	 */
	private void awardReleaseItemToPlayer(Player player, SysItem sysItem) throws Exception{
		//function remove			
//		String id = sysItem.getIValue();
//		List<String> characters = Arrays.asList(player.getCharacters().split(","));
//		if (!characters.contains(id)) {
//			player.setCharacters((player.getCharacters() + "," + id));
//		}
//		playerDao.updatePlayerInCache(player);
//		mcc.delete(CacheUtil.oPlayer(player.getId()));
//		mcc.delete(CacheUtil.sPlayer(player.getId()));
//		if (player.getCharacters().indexOf(id) == -1) {
//			soClient.puchCMDtoClient(player.getName(), CommonUtil.messageFormat(CommonMsg.RELEASE_CHARACTER,new Object[] { StringUtil.toInt(id) }));
//		}
	}
	
	public void savePayLog(Player player, SysItem sysItem, int payType, int leftMoney,int cost){
		final PayLog payLog = new PayLog();
		payLog.setUserId(player.getUserName());
		payLog.setPlayerId(player.getId());
		payLog.setItemId(sysItem.getId());
		payLog.setItemName(sysItem.getDisplayNameCN());
		payLog.setItemType(sysItem.getType());
		payLog.setPayType(payType);
		payLog.setPayAmount(cost);
		payLog.setCreateTime(new Date());
		payLog.setLeftMoney(leftMoney);
		payLog.setPayUse(1);
		final Runnable writePayLog = new Runnable() {
			@Override
			public void run() {
				createPayLog(payLog);
			}
		};
		ServiceLocator.asynTakService.execute(writePayLog);
	}
	/**
	 * 完成成长任务，奖励
	 * @param player
	 * @param cal 金币奖励
	 * @param exp 经验奖励
	 * @throws Exception 
	 */
	public void awardGrowthMission(Player player, int cal, int exp,List<AwardItemVo> awardItemVos,String missionName) throws Exception{
		awardCALToPlayer(player, cal);
		awardExpToPlayer(player, exp);
		int medalNum=0;
		if(null != awardItemVos){
			for (AwardItemVo entry : awardItemVos) {
				SysItem gift = entry.getItem();
				Integer num = entry.getItemNum();
				Payment payment = new Payment(num, Constants.NUM_ONE);
				if (gift.getId()==GrowthMissionConstants.MEDAL_ID) {
					medalNum+=num;
				}
				if(gift.getType()==Constants.DEFAULT_ITEM_TYPE && gift.getIId()==27){
					sendItem(gift, player, payment, Constants.BOOLEAN_NO, Constants.BOOLEAN_NO, Constants.BOOLEAN_YES);
				}else{
					sendItem(gift, player, payment, Constants.BOOLEAN_NO, Constants.BOOLEAN_YES, Constants.BOOLEAN_NO);
				}
				if (ConfigurationUtil.SWITCH_XUNLEI_LOG.getIsOn()&&gift.getId()==GrowthMissionConstants.MEDAL_ID) {
					int chipNum=getService.getMedolNumByPlayerId(player.getId());
					nosqlService.addXunleiLog("7.4"
							+ Constants.XUNLEI_LOG_DELIMITER + player.getUserName()
							+ Constants.XUNLEI_LOG_DELIMITER + player.getName()
							+ Constants.XUNLEI_LOG_DELIMITER + 1
							+ Constants.XUNLEI_LOG_DELIMITER + num
							+ Constants.XUNLEI_LOG_DELIMITER + chipNum
							+ Constants.XUNLEI_LOG_DELIMITER+ 1
							+ Constants.XUNLEI_LOG_DELIMITER+ CommonUtil.simpleDateFormat.format(new Date())
							);
				}
			}
		}
		if (ConfigurationUtil.SWITCH_XUNLEI_LOG.getIsOn()) {
			nosqlService.addXunleiLog("9.2"
					+ Constants.XUNLEI_LOG_DELIMITER + player.getUserName()
					+ Constants.XUNLEI_LOG_DELIMITER + player.getName()
					+ Constants.XUNLEI_LOG_DELIMITER + player.getRank()
					+ Constants.XUNLEI_LOG_DELIMITER + 2
					+ Constants.XUNLEI_LOG_DELIMITER + missionName
					+ Constants.XUNLEI_LOG_DELIMITER + medalNum
					+ Constants.XUNLEI_LOG_DELIMITER + exp
					+ Constants.XUNLEI_LOG_DELIMITER + cal
					+ Constants.XUNLEI_LOG_DELIMITER+ CommonUtil.simpleDateFormat.format(new Date())
					);
		}
		if(exp>0){
			//update StayDataAddExp
			ServiceLocator.nosqlService.updateStayDataAddExp(player, exp);
		}
	}
	/**
	 * 每天第一次登录，重新初始化玩家战斗力排名
	 * @param playerId
	 * @param cid
	 * @throws Exception
	 */
	public void initPlayerFightNumRankInTop(int playerId , String cid) throws Exception{
		String key = NosqlKeyUtil.fightNumInRealTopByType(cid);
		Player player = getService.getPlayerById(playerId);
		NoSql  nosql = nosqlService.getNosql();
		int chid = Integer.parseInt(cid);
		int rank = (int)nosqlService.getNosql().zRank(key, String.valueOf(playerId)) + 1;
		if(rank==0){
			int value = 0;
			if(chid!=0){//0 表示综合战斗力
				Character character = getService.getCharacterByCharacterId(playerId, Integer.parseInt(cid));
				if(character==null)
					return;
				value = character.getFightNum();
			}else{
				value = player.getMaxFightNum();
			}
			int total = (int)nosql.zCard(key);
			if(total<Constants.REAL_TOP_RANK_NUM){
				nosql.zAdd(key, -value, String.valueOf(playerId));
			}else {
				double lastValue = Math.abs(nosql.zrangeWithScores(key, -1, -1).iterator().next().getScore());
				if(value > lastValue){
					nosql.zAdd(key, -value, String.valueOf(playerId));
				}
			}
		}
	}
	/**
	 * 每天第一次登录，重新初始化玩家积分,胜率，击杀/死亡，助攻排名
	 * @param playerId
	 * @param type
	 * @throws Exception
	 */
	public void initPlayerCommonRankInTop(int playerId , String type) throws Exception{
		boolean isDynamicOn = ConfigurationUtil.SWITCH_PSNTOP_DYNAMIC.getIsOn();
		String key = isDynamicOn?NosqlKeyUtil.commonLevelNumInRealTopByType(type):NosqlKeyUtil.commonLevelNumInTopByType(type);
		Player player = getService.getPlayerById(playerId);
		NoSql  nosql = nosqlService.getNosql();
		int rank = (int)nosqlService.getNosql().zRank(key, String.valueOf(playerId)) + 1;
		if(rank==0){
			double value = 0;
			if("kScore".equals(type)){
				value = player.getGScore();
			}else if("kAssist".equals(type)){
				value = player.getGAssist();
			}else if("kWinRate".equals(type)){
				value = player.getGWin()+player.getWinRate()/2;
			}else if("kKillDead".equals(type)){
				int killNum = player.getGKill();
				int deadNum = player.getGDead();
				value =killNum +  1.0/(deadNum+2);
			}
			int total = (int)nosql.zCard(key);
			if(isDynamicOn){
				if(total<Constants.REAL_TOP_RANK_NUM){
					nosql.zAdd(key, -value, String.valueOf(playerId));
				}else {
					double lastValue = Math.abs(nosql.zrangeWithScores(key, total-1, total).iterator().next().getScore());
					if(value > lastValue){
						nosql.zAdd(key, -value, String.valueOf(playerId));
					}
				}
			}else{
				String mccKey = key + ":" + playerId;
				double firstValue = Math.abs(nosql.zrangeWithScores(key, 0, 0).iterator().next().getScore());
				double lastValue = Math.abs(nosql.zrangeWithScores(key,-1, -1).iterator().next().getScore());
				rank = getService.computeCommmonRank(value, firstValue, lastValue, total);
				mcc.set(mccKey, Constants.CACHE_TIMEOUT_DAY, rank);
			}
		}
	}
	public int createTeam(TeamRecord team) throws Exception{
		return teamRecordDao.createTeam(team);
	}
	public void useBurnt(int teamId,int playerId,int type){
		
	}
	/**
	 * 拼图完成
	 * @param playerId 玩家id
	 * @return {@link OnlineAward}
	 * @throws Exception
	 */
	public OnlineAward completePinTu(int playerId) throws Exception{
		List<OnlineAward> oaList = getService.getOnlineAwardByType(Constants.ONLINE_AWARD_TYPES.MISTIC_PINTU.getValue());
		if(oaList==null||oaList.isEmpty()){
			logger.error("CmptPintu/OnlineAwardsEmpty:\tPid:" + playerId + "\tOnlineAwardType:" + Constants.ONLINE_AWARD_TYPES.MISTIC_PINTU.getValue());
			return null;
		}
		OnlineAward misticOa = getService.randomOnlineAward(oaList);
		SysItem si  = getService.getSysItemByItemId(misticOa.getItemId());
		if(si!=null){
			awardToPlayer(getService.getSimplePlayerById(playerId), si, new Payment(misticOa.getUnit(),misticOa.getUnitType()), null, Constants.BOOLEAN_YES);
			nosqlService.getNosql().set(Constants.PLAYER_PT_FLAG_KEY+ playerId, Constants.PLAYER_PT_PRI_FLAGS + Constants.PLAYER_PT_TOTAL_CHANCE);
			misticOa.setSysItem(si);
			int color = si.getIsStrengthen() == 1 ? si.getGunProperty().getColor() : 0;
			Player player = getService.getSimplePlayerById(playerId);
			soClient.proxyBroadCast(Constants.MSG_NBA, 
					Constants.SYSTEM_SPEAKER, CommonUtil.messageFormatI18N(CommonMsg.PINTU_CMT_SYS, new Object[]{GunProperty.RED + "@!" + getService.getPlayerById(playerId).getName()+"@!"+GunProperty.RED + "@!" + player.getId(), color + "@!" + si.getDisplayName(), misticOa.getUnit()}));
			ServiceLocator.getLuckyPackageLog.info("getPintuAward:\t" + playerId + "\t" + misticOa.getId() + "\t" + si.getDisplayNameCN());
		}else{
			logger.error("CmptPintu/sysItemNull:\t" + playerId + "\t" + misticOa.getId() + "\t" + misticOa.getItemId());
		}
		return misticOa;
	}
	public OnlineAward completePinTu(int playerId, int type) throws Exception{
		List<OnlineAward> oaList = getService.getOnlineAwardByTypeAndLevel(Constants.ONLINE_AWARD_TYPES.MISTIC_PINTU.getValue(), type);
		if(oaList==null||oaList.isEmpty()){
			logger.error("CmptPintu/OnlineAwardsEmpty:\tPid:" + playerId + "\tOnlineAwardType:" + Constants.ONLINE_AWARD_TYPES.MISTIC_PINTU.getValue());
			return null;
		}
		OnlineAward misticOa = getService.randomOnlineAward(oaList);
		SysItem si  = getService.getSysItemByItemId(misticOa.getItemId());
		if(si!=null){
			awardToPlayer(getService.getSimplePlayerById(playerId), si, new Payment(misticOa.getUnit(),misticOa.getUnitType()), null, Constants.BOOLEAN_YES);
			nosqlService.getNosql().set(Constants.PLAYER_PT_FLAG_KEY+ playerId, Constants.PLAYER_PT_PRI_FLAGS + Constants.PLAYER_PT_TOTAL_CHANCE);
			misticOa.setSysItem(si);
			int color = si.getIsStrengthen() == 1 ? si.getGunProperty().getColor() : 0;
			Player player = getService.getSimplePlayerById(playerId);
			soClient.proxyBroadCast(Constants.MSG_NBA, 
					Constants.SYSTEM_SPEAKER, CommonUtil.messageFormatI18N(CommonMsg.PINTU_CMT_SYS, new Object[]{GunProperty.RED + "@!" + getService.getPlayerById(playerId).getName()+"@!"+GunProperty.RED + "@!" + player.getId(), color + "@!" + si.getDisplayName(), misticOa.getUnit()}));
			ServiceLocator.getLuckyPackageLog.info("getPintuAward:\t" + playerId + "\t" + misticOa.getId() + "\t" + si.getDisplayNameCN());
		}else{
			logger.error("CmptPintu/sysItemNull:\t" + playerId + "\t" + misticOa.getId() + "\t" + misticOa.getItemId());
		}
		return misticOa;
	}
	/**
	 * 多次抽取拼图
	 * @param playerId
	 * @param multiple
	 * @param payType
	 * @return String[] 第一个是抽取之前玩家拼图状态，第二个是玩家新获得的拼图位置
	 * @throws Exception
	 */
	public String[] multiRandomPinTu(int playerId,int multiple,int payType) throws Exception{
		String playerPinTuFlags = ServiceLocator.nosqlService.getNosql().get(Constants.PLAYER_PT_FLAG_KEY+playerId); 
		if(playerPinTuFlags==null||playerPinTuFlags.length()!=Constants.PLAYER_PT_PRI_FLAGS.length()+1||!playerPinTuFlags.matches("[01]*[0-" + Constants.PLAYER_PT_TOTAL_CHANCE + "]")){
			playerPinTuFlags = Constants.PLAYER_PT_PRI_FLAGS + Constants.PLAYER_PT_TOTAL_CHANCE;
		}
		//拼图模块
		List<Integer> indexs = new ArrayList<Integer>();
		
		String playerPinTuFlagsRet = playerPinTuFlags.replaceAll("", ",").replaceFirst(",", "");//返回给客户端的当前拼图状态
		String playerGetPtWtRds = nosqlService.getNosql().get(Constants.PLAYER_PT_GET_WEIGHTS_KEY);//从redis中获取玩家获得拼图的概率
		double playerGetPtWt = Constants.PLAYER_PT_GET_DFT_WT;
		if(playerGetPtWtRds!=null && playerGetPtWtRds.matches("0\\.[0-9]*")){
			playerGetPtWt = Double.valueOf(playerGetPtWtRds);
		}else{
			nosqlService.getNosql().set(Constants.PLAYER_PT_GET_WEIGHTS_KEY, String.valueOf(Constants.PLAYER_PT_GET_DFT_WT));
		}
		//黄金彩盒有一定概率获得拼图
		//FIXME
		for(int i =0;i<multiple;i++){
			int retIndex = -1;
			if(Math.random()<playerGetPtWt){//这种随机方式是否太简单（Math.random()<0.5?Math.random()<playerGetPtWt:Math.random()>=1.0-playerGetPtWt）
				retIndex = getService.randomPlayerPtIndex(playerId, payType, playerPinTuFlags);
			}
			indexs.add(retIndex);
			if(retIndex>=0&&playerPinTuFlags.charAt(retIndex)!='1'){//神秘拼图
				playerPinTuFlags = StringUtil.replaceByIndex(playerPinTuFlags, retIndex, '1');
				ServiceLocator.getLuckyPackageLog.info("getPintu:\t" + playerId + "\t" + retIndex);
					nosqlService.getNosql().set(Constants.PLAYER_PT_FLAG_KEY+ playerId, playerPinTuFlags);
			}
		}
		return new String[]{playerPinTuFlagsRet,StringUtils.join(indexs, ",")};
	}
	
	/**
	 * vip等级提升时所作的操作
	 * @param player
	 * @param vipRank
	 * @throws Exception
	 */
	public void createItemForVipLevelUp(Player player,int vipRank)throws Exception{
		//10.18此版本不加   个人信息记录中vip升级信息
		//nosqlService.publishEvent(new VipLevelUpEvent(DateUtil.getCurrentTimeStr(), vipRank, player.getId(), player.getName()));
		
		ServiceLocator.playerLog.info("Vip rank is upgrade/renew to vip "+vipRank+"\t for player "+player.getId());
		//送vip专属名片
		SysItem vipCard=ServiceLocator.getService.getVipCard(vipRank);
		if(vipCard!=null){
			PlayerItem vipCardBuff = getService.getPlayerBuff(player.getId(), vipCard.getIBuffId());
			if (vipCardBuff==null || vipCardBuff.getItemId() != vipCard.getId()) {
				sendItem(vipCard, player, new Payment(30, 2),
						Constants.BOOLEAN_NO, Constants.BOOLEAN_YES,
						Constants.BOOLEAN_NO);
			}
		}
		
		
		//送强化buff	
		SysItem vipStrengthItem=ServiceLocator.getService.getVipStrengthBuff(vipRank);
		
		if (vipStrengthItem != null) {
			PlayerItem playerBuff = getService.getPlayerBuff(player.getId(), vipStrengthItem.getIBuffId());
			
			if (null == playerBuff|| playerBuff.getItemId() != vipStrengthItem.getId()) {
				int playerItemId = sendItem(vipStrengthItem, player,
						new Payment(0, 0), Constants.BOOLEAN_NO,
						Constants.BOOLEAN_YES, Constants.BOOLEAN_NO);
				PlayerItem playerItem = getService.getPlayerItemByItemId(player.getId(),
								vipStrengthItem.getType(), playerItemId);
				useItemById(player, vipStrengthItem.getType(), playerItem, "",0, 0);
				// 删道具
				updateItemQuantity(playerItem);
				int playerId = player.getId();
				mcc.delete(CacheUtil.oPlayer(playerId));
				mcc.delete(CacheUtil.sPlayer(playerId));
				ServiceLocator.deleteService.deletePlayerItemInMemcached(playerId, vipStrengthItem);
				soClient.puchCMDtoClient(player.getName(), CommonUtil.messageFormat(CommonMsg.REFRESH_BUFF_LIST, null));

			}
		}
		
	}
	
	/**
	 * 创建战队地图
	 */
	public TeamLevelInfo createTeamLevel(int teamId,int... lid)throws Exception{
		//确保同一个战队 基于同一张基础地图 只能建一张图
		if(ServiceLocator.getService.getTeamLevelInfo(teamId, Constants.DEFAULT_TEAM_LEVEL_RES_ID)!=null){
			return null;
		}
		Random random=new Random();
		int confRandomIndex=random.nextInt(TeamLevelInfo.DEFAULT_NEW_TEAM_CONFIG.length);
		TeamLevelInfo teamLevelInfo=new TeamLevelInfo();
		teamLevelInfo.setTeamId(teamId);
		teamLevelInfo.setCreateTime((int)(System.currentTimeMillis()/1000));
		teamLevelInfo.setLastBeginUpdateTime((int)(System.currentTimeMillis()/1000));
		teamLevelInfo.setLastUpdateTime((int)(System.currentTimeMillis()/1000));
		teamLevelInfo.setConfigPoints(TeamLevelInfo.DEFAULT_NEW_TEAM_CONFIG[confRandomIndex]);
		teamLevelInfo.setIsEditable("N");
		if(lid.length>0){
			teamLevelInfo.setRefLevelId(lid[0]);
		}else{
			teamLevelInfo.setRefLevelId(Constants.DEFAULT_TEAM_LEVEL_RES_ID);
		}
		
		teamLevelInfo.setType(Constants.GAMETYPE.TEAMZYZDZ.getValue());
		
		return teamLevelDao.create(teamLevelInfo);
	}
	
	
	public boolean createChallengeBattle(Player player,TOPTeam topTeam) throws Exception{
		boolean result =false;
		String challengeKey=Constants.TeamSpaceConstants.getZYZDZChallengeKey(topTeam.getTeam().getId());
		//1先看玩家钱够不够
		PlayerInfo playerInfo = getService.getPlayerInfoById(player.getId());
		int cr = playerInfo.getXunleiPoint();
		int challengeCost=topTeam.getChallengeCost();
		int leftMoney=cr-challengeCost;
		if(leftMoney>=0){//有资格(钱)进行挑战	
			//尝试进行挑战
			//key,时间1，开始时间，时间2
			boolean startSuc=mcc.add(challengeKey,Constants.TeamSpaceConstants.BATTLEFIELD_CACHE_CHALLENGE_LIFE_TIMEOUT,System.currentTimeMillis(),Constants.TeamSpaceConstants.BATTLEFIELD_CACHE_CHALLENGE_TIMEOUT);
			if(startSuc){
				playerInfo.setXunleiPoint(leftMoney);
				refreshPlayerTeamALLMessages(player, playerInfo, null);
				result=true;
				createPlayerBuyFunctionLog(player.getId(), CostType.CR_PAY, cr, CostFunction.ZYZDZ_CHALLENGE);
				createZYZDZPayLog(player, ZYZDZUndefinedItem.getStartChallenge(), challengeCost, leftMoney, Constants.CR_PAY, null);
			}
		}else{
			throw new CRNotEnoughException(ExceptionMessage.NOT_ENOUGH_CR_FOR_CHALLENGE);//
		}
		return result;
	}
	
	
	public void setMccChallengeHill(ChallengeHillStatus challengeHillStatus,int teamId) throws TimeoutException, InterruptedException, MemcachedException{
		String key=Constants.TeamSpaceConstants.getChallengeHillKey(teamId);
		mcc.set(key,Constants.TeamSpaceConstants.BATTLEFIELD_CACHE_HILLSTU_LIFE_TIMEOUT,challengeHillStatus,Constants.TeamSpaceConstants.BATTLEFIELD_CACHE_HILLSTU_TIMEOUT);
	}
	
	
	public List<TOPTeam> createTopTeams(List<Team> preZYZDZRank,boolean isChallengeZYZDZOn,Team attackTeam) throws Exception {
		List<TOPTeam> result = new ArrayList<TOPTeam>();
		if (preZYZDZRank != null) {
			for (Team team : preZYZDZRank) {
				TOPTeam topTeam=createTopTeam(team,preZYZDZRank,isChallengeZYZDZOn,attackTeam);
				result.add(topTeam);
			}
		}
		return result;
	}	
	
	/**
	 * 挑战队伍
	 * @param defenceTeam
	 * @param preZYZDZRank
	 * @param isChallengeZYZDZOn
	 * @param attackTeam
	 * @return
	 * @throws Exception
	 */
	public TOPTeam createTopTeam(Team defenceTeam,List<Team> preZYZDZRank,boolean isChallengeZYZDZOn,Team attackTeam) throws Exception{
		if(defenceTeam!=null&&preZYZDZRank.contains(defenceTeam)){
			/**初始化*/
			TOPTeam topTeam=new TOPTeam(defenceTeam);
			//设置rank
			int rank=preZYZDZRank.indexOf(defenceTeam) + 1;
			// 下次可挑战的时间
			long leftTime=0;
			//矿山状态
			ChallengeHillStatus challengeHillStatus=null;
			
			/**处理*/
			if(isChallengeZYZDZOn){
				leftTime = getService.timeLeftForChallengeOnWar(topTeam
						.getTeam());
				challengeHillStatus = getService.getChallengeHillStatus(topTeam.getTeam().getId());
				if(challengeHillStatus==null){
					challengeHillStatus = tryInitChallengeFieldStatus(topTeam.getTeam().getId(),isChallengeZYZDZOn);
				}
			}else{
				challengeHillStatus = ChallengeHillStatus.createDefaultByRank(rank);
			}
			
			/**set操作*/
			if (isChallengeZYZDZOn&&getService.beOkForChallenge(leftTime)
				&&getService.isLevelOkForChallenge(attackTeam, defenceTeam)) {
				topTeam.setAbleBeChallenge(true);
			}	
			topTeam.setLeftTime(leftTime);
			topTeam.setChallengeHillStatus(challengeHillStatus);
			topTeam.setRank(rank);
			topTeam.setChallengeCost(getService.getChallangeFCCost(challengeHillStatus.getStones()));
		
			return topTeam;
		}else{
			return null;
		}
	}
	
	/**
	 * 尝试初始化挑战status
	 * @param teamId
	 * @param rank
	 * @throws Exception 
	 */
	public ChallengeHillStatus tryInitChallengeFieldStatus(int teamId,boolean isChallengeOn)throws Exception {
		String key=Constants.TeamSpaceConstants.getChallengeHillKey(teamId);
		ChallengeHillStatus challengeHillStatus=mcc.get(key);
		if (challengeHillStatus == null && isChallengeOn) {
			List<Team> preZYZDZRank = getService.getTeamTopForPreRes();
			Team team= getService.getTeamById(teamId);
			if(preZYZDZRank.contains(team)&&team!=null){
				int rank=preZYZDZRank.indexOf(team)+1;
				challengeHillStatus=new ChallengeHillStatus(rank);
				mcc.add(key,Constants.TeamSpaceConstants.BATTLEFIELD_CACHE_HILLSTU_LIFE_TIMEOUT,challengeHillStatus,Constants.TeamSpaceConstants.BATTLEFIELD_CACHE_HILLSTU_TIMEOUT);	
			}
		}
		return mcc.get(key);
	}	
	
	
	public void costMatchUnUsableStones(Player player) throws Exception{
		PlayerTeam playerTeam=getService.getPlayerTeamByPlayerId(player.getId());
		Team team=getService.getSimpleTeamById(playerTeam.getTeamId());
		HashMap<String, Integer> playerRes=player.getLatestPlayerRes(team.getTeamSpaceLevel());
		int stones=playerRes.get(Player.ORG_RES);
		stones=stones-Constants.TeamSpaceConstants.MATCH_COST_STONES;
		if(stones<0){
			throw new STONENotEnoughException();
		}else{
			player.setUnusableResource(stones);
			playerDao.updatePlayerInCache(player);
			mcc.delete(CacheUtil.oPlayer(player.getId()));
			mcc.delete(CacheUtil.sPlayer(player.getId()));
			createPlayerBuyFunctionLog(player.getId(), CostType.UNUSE_RES_PAY, Constants.TeamSpaceConstants.MATCH_COST_STONES, CostFunction.ZYZDZ_MATCH);
		}
	}
	
	
	
	/**
	 * 奖励个人为团队资源争夺战做出贡献
	 */
	public Player pushRewardToPlayer4TeamZYZDZ(Player player,Team team,int costFC){
		HashMap<String, Integer> playerRes=player.getLatestPlayerRes(team.getTeamSpaceLevel());
		int stones=playerRes.get(Player.RES);
		int reward=getService.getRewardToPlayer4TeamZYZDZ(player, team, costFC);
		player.setUsableResource(stones+reward);
		return player;
	}
	
	/**
	 * 统一创建玩家购买团队物品的消费历史记录
	 */
	public void createPlayerBuyTeamItemLog(String s){
		infoLogger.log(LogServerMessage.buyPlayerItem.name(), Level.INFO_INT, 
				LogUtils.JoinerByTab.join("1.5",DateUtil.getDf3().format(new Date()),s));
	}
	
	public void createIntensifyTeamItemLog(String s){
		infoLogger.log(LogServerMessage.buyPlayerItem.name(), Level.INFO_INT, 
				LogUtils.JoinerByTab.join("8.8",DateUtil.getDf3().format(new Date()),s));
	}
	
	/**
	 * 统一创建玩家购买功能消费历史记录，如挑战，匹配
	 * @param playerId
	 * @param costType
	 * @param cost 消耗
	 * @param costFunction
	 * @param sOtherMessage
	 * 
	 */
	public void createPlayerBuyFunctionLog(int playerId,CostType costType,int cost,CostFunction costFunction,String... sOtherMessage){
		StringBuffer sbBuffer=new StringBuffer(playerId+"|"+costType+"|"+cost+"|"+costFunction);
		if(sOtherMessage.length>0){
			sbBuffer.append("-");
			sbBuffer.append(sOtherMessage);
		}
		infoLogger.log(LogServerMessage.buyPlayerItem.name(), Level.INFO_INT, 
				LogUtils.JoinerByTab.join("13.1",CommonUtil.simpleDateFormat.format(new Date()),sbBuffer.toString()));
	}	
	


	/**
	 * 立刻转化个人黑原石
	 */
	public boolean createPTransForm(int playerId,int fcCost)throws Exception {
		boolean result=false;
		Player player=getService.getPlayerById(playerId);
		Team team = getService.getTeamByPlayerId(playerId);
		PlayerInfo playerInfo = getService.getPlayerInfoById(playerId);
		int cr = playerInfo.getXunleiPoint();
		if (cr < fcCost) {
			throw new CRNotEnoughException(ExceptionMessage.NOT_ENOUGH_CR);// not have enough FC point
		} 		
		
		if (team != null) {
				HashMap<String, Integer> playerRes=player.getLatestPlayerRes(team.getTeamSpaceLevel());
				int transFromStone=fcCost*Constants.TeamSpaceConstants.P_TF_RATE;
				int needDisStone=transFromStone*Constants.TeamSpaceConstants.P_TF_COST_DIS_RATE;
				int disStones = playerRes.get(Player.ORG_RES);
				int ableStone = playerRes.get(Player.RES);
				int ableMax=Constants.TEAMSPACELEVELCONSTANTS.getTeamSpaceLevelConstants(team.getTeamSpaceLevel()).getpMaxRes();
				if(disStones<needDisStone){
					throw new BaseException(ExceptionMessage.LACK_P_UNUSE_RES_STONE); 
				}
				if(ableMax<transFromStone+ableStone){
					throw new BaseException(ExceptionMessage.OUT_OF_RES_STONE);
				}
				
				player.setUnusableResource(disStones-needDisStone);
				player.setUsableResource(transFromStone+ableStone);
				playerInfo.setXunleiPoint(cr-fcCost);
				
				refreshPlayerTeamALLMessages(player, playerInfo, null);
				createPlayerBuyFunctionLog(player.getId(), CostType.CR_PAY, fcCost, CostFunction.ZYZDZ_P_TRANSFORM);
				result=true;
				createZYZDZPayLog(player, ZYZDZUndefinedItem.getTransformStoneP(), fcCost, cr-fcCost,Constants.CR_PAY,null);
		}
		return result;

	}	
	
	/**
	 * 进行团队原石转换
	 * @param playerId
	 * @param fcCost
	 * @throws Exception
	 */
	public int createTTransForm(int playerId,int fcCost) throws Exception{
		int reward=0;
		Player player=getService.getPlayerById(playerId);
		Team team = getService.getTeamByPlayerId(playerId);
		PlayerInfo playerInfo = getService.getPlayerInfoById(playerId);
		int cr = playerInfo.getXunleiPoint();
		if (cr < fcCost) {
			throw new CRNotEnoughException(ExceptionMessage.NOT_ENOUGH_CR);// not have enough FC point
		} 		
		
		if (team != null) {
			if (getService.isTeamUnderZYZDZMatchAttack(team.getId())) {
				throw new BaseException(ExceptionMessage.UNDER_ZYZDZ_ATTACK);
			} else {
				HashMap<String, Integer> teamResHashMap=team.getLatestTeamRes();
				int transFromStone=new Double(fcCost*Constants.TeamSpaceConstants.T_TF_RATE).intValue();
				int costDisStones=transFromStone*Constants.TeamSpaceConstants.T_TF_COST_DIS_RATE;
				int disStones = teamResHashMap.get(Team.ORG_RES);
				int ableStone = teamResHashMap.get(Team.RES);
				int ableMax=Constants.TEAMSPACELEVELCONSTANTS.getTeamSpaceLevelConstants(team.getTeamSpaceLevel()).gettMaxRes();
				if(disStones<costDisStones){
					throw new BaseException(ExceptionMessage.LACK_T_UNUSE_RES_STONE); 
				}
				if(ableMax<transFromStone+ableStone){
					throw new BaseException(ExceptionMessage.OUT_OF_RES_STONE);
				}
				
				team.setUnusableResource(disStones-costDisStones);
				team.setUsableResource(transFromStone+ableStone);
				playerInfo.setXunleiPoint(cr-fcCost);
				
				reward=getService.getRewardToPlayer4TeamZYZDZ(player, team, fcCost);
				player=pushRewardToPlayer4TeamZYZDZ(player, team, fcCost);
				
				
				refreshPlayerTeamALLMessages(player, playerInfo, team);
				createZYZDZPayLog(player, ZYZDZUndefinedItem.getTransformStoneT(), fcCost, cr-fcCost,Constants.CR_PAY,null);
				createPlayerBuyFunctionLog(player.getId(), CostType.CR_PAY, fcCost, CostFunction.ZYZDZ_TEAM_TRANSFORM);
			}
		}
		return reward;
	}
	
	
	/**
	 * 进行团队原石购买
	 * @param playerId
	 * @param fcCost
	 * @throws Exception
	 */
	public int createTBUYStone(int playerId,int fcCost) throws Exception{
		int reward=0;
		Player player=getService.getPlayerById(playerId);
		Team team = getService.getTeamByPlayerId(playerId);
		PlayerInfo playerInfo = getService.getPlayerInfoById(playerId);
		int cr = playerInfo.getXunleiPoint();
		if (cr < fcCost) {
			throw new CRNotEnoughException(ExceptionMessage.NOT_ENOUGH_CR);// not have enough FC point
		} 		
		
		if (team != null) {
			if (getService.isTeamUnderZYZDZMatchAttack(team.getId())) {
				throw new BaseException(ExceptionMessage.UNDER_ZYZDZ_ATTACK);
			} else {
				HashMap<String,Integer> teamRes=team.getLatestTeamRes();
				int transFromStone=new Double(fcCost*Constants.TeamSpaceConstants.T_BUY_RATE).intValue();
				int ableStone = teamRes.get(Team.RES);
				int ableMax=Constants.TEAMSPACELEVELCONSTANTS.getTeamSpaceLevelConstants(team.getTeamSpaceLevel()).gettMaxRes();
				if(ableMax<transFromStone+ableStone){
					throw new BaseException(ExceptionMessage.OUT_OF_RES_STONE);
				}
				team.setUsableResource(transFromStone+ableStone);
				playerInfo.setXunleiPoint(cr-fcCost);
				
				reward=getService.getRewardToPlayer4TeamZYZDZ(player, team, fcCost);
				player=pushRewardToPlayer4TeamZYZDZ(player, team, fcCost);
				
				
				refreshPlayerTeamALLMessages(player, playerInfo, team);
				createZYZDZPayLog(player, ZYZDZUndefinedItem.getBuyStoneT(), fcCost, cr-fcCost,Constants.CR_PAY,null);
				createPlayerBuyFunctionLog(player.getId(), CostType.CR_PAY, fcCost, CostFunction.ZYZDZ_TEAM_STONE_BUY);
			}
		}
		return reward;
	}	
	
	
	public void createZYZDZPayLog(Player player,SysItem sysItem,int trueCOST,int leftMoney,int payType,String message){
			final PayLog payLog = new PayLog();
			payLog.setUserId(player.getUserName());//?
			payLog.setPlayerId(player.getId());
			if(sysItem instanceof ZYZDZUndefinedItem){
				payLog.setItemName(sysItem.getDisplayNameCN());
			}else{
				payLog.setItemName(message==null?"":message+sysItem.getDisplayNameCN());
			}
			payLog.setItemId(sysItem.getId());
			payLog.setItemType(sysItem.getType());	
			payLog.setPayType(payType);//2.FC点购买
			payLog.setPayAmount(trueCOST);
			payLog.setCreateTime(new Date());
			payLog.setLeftMoney(leftMoney);		
			final Runnable writePayLog = new Runnable() {
				@Override
				public void run() {
					createPayLog(payLog);
				}
			};	
			ServiceLocator.asynTakService.execute(writePayLog);			
	
	}
	
	 /**
	 * 刷新所有玩家和团队的信息
	 * @throws Exception 
	 */
	public void refreshPlayerTeamALLMessages(Player player,PlayerInfo playerInfo,Team team) throws Exception{
		if(player!=null){
			ServiceLocator.updateService.updatePlayerInfoOnly(player);
		}
		
		if(playerInfo!=null){
			playerInfoDao.update(playerInfo);
			mcc.delete(CacheUtil.oPlayerInfo(playerInfo.getPlayerId()));
		}
		
		if(team!=null){
			ServiceLocator.updateService.updateTeamInfo(team);
		}
		
		
		if(player!=null||playerInfo!=null){
			soClient.messageUpdatePlayerMoney(player);
		}
	}
	
	 /**
	 * 刷新所有玩家和团队的信息
	 * @throws Exception 
	 */
	public void refreshPlayerTeamALLMessages(Player player,PlayerInfo playerInfo,Team team,TeamItem teamItem) throws Exception{
		refreshPlayerTeamALLMessages(player, playerInfo, team);
		if(teamItem!=null){
			teamItemDao.updateTeamItem(teamItem);
			mcc.delete(CacheUtil.oTeamItem(teamItem.getId()));
		}
	}	
	
	 /**
	 * 刷新所有玩家和团队的信息
	 * @throws Exception 
	 */
	public void refreshPlayerTeamALLMessages(Player player,PlayerInfo playerInfo,Team team,TeamItem teamItem,PlayerItem playerItem) throws Exception{
		refreshPlayerTeamALLMessages(player, playerInfo, team,teamItem);
		if(playerItem!=null){
			ServiceLocator.deleteService.deletePlayerItemInMemcached(playerItem.getPlayerId(), playerItem.getSysItem());
			playerItemDao.updatePlayerItem(playerItem);
		}
	}		
	
	/**
	 * 完成创建操作
	 * @param playerId
	 * @param fcCOST
	 * @param teamItem
	 * @throws Exception 
	 */
	public void finishCreateItem(int playerId,int fcCOST,Object item,SysItem sysItem) throws Exception{
		boolean createPayLog=false;
		//1先看玩家钱够不够
		Player player=getService.getPlayerById(playerId);
		PlayerInfo playerInfo = getService.getPlayerInfoById(player.getId());
		int cr = playerInfo.getXunleiPoint();
		int leftMoney=cr-fcCOST;
		try {
			if(leftMoney>0){//有资格(钱)进行购买	
				//key,时间1，开始时间，时间2
				playerInfo.setXunleiPoint(leftMoney);
				if(item instanceof TeamItem){
					TeamItem teamItem=(TeamItem)item;
					teamItem.setLastBuildTime(null);
					refreshPlayerTeamALLMessages(player, playerInfo, null,teamItem);
				}else if(item instanceof PlayerItem){
					PlayerItem playerItem=(PlayerItem)item;
					long date=0;
					playerItem.setValidDate(new Date(date));
					refreshPlayerTeamALLMessages(player, playerInfo, null,null,playerItem);
				}else if(item instanceof Team){
					Team team=(Team)item;
					team.setLastTeamPlaceLevelUpTime(0);
					team.setTeamSpaceLevel(team.getTeamSpaceLevel()+1);
					refreshPlayerTeamALLMessages(player, playerInfo, team);
				}else{
					throw new BaseException(ExceptionMessage.ERROR_MESSAGE_ALL);
				}
				createPayLog=true;
				createPlayerBuyFunctionLog(player.getId(), CostType.FINISH_CREATE, fcCOST, CostFunction.ZYZDZ_FINISH_CREATE);
			}else{
				throw new CRNotEnoughException(ExceptionMessage.NOT_ENOUGH_CR);//
			}
		} catch (Exception e) {
			throw e;
		}finally{
			if(createPayLog){
				if(sysItem==null){
					sysItem=ZYZDZUndefinedItem.getFinishTeamSpaceLevelUp();
				}
				createZYZDZPayLog(player, sysItem, fcCOST, leftMoney,Constants.CR_PAY,"加速完成");
			}
		}
		
	}
	
	/**
	 * 完成创建操作
	 * @param playerId
	 * @param fcCOST
	 * @param teamItem
	 * @throws Exception 
	 */
	public void finishCreateTeamSpace(Player player,int fcCOST,Object item,SysItem sysItem) throws Exception{
		//1先看玩家钱够不够
		PlayerInfo playerInfo = getService.getPlayerInfoById(player.getId());
		int cr = playerInfo.getXunleiPoint();
		int leftMoney=cr-fcCOST;
		if(leftMoney>0){//有资格(钱)进行购买	
			//key,时间1，开始时间，时间2
			playerInfo.setXunleiPoint(leftMoney);
			if(item instanceof TeamItem){
				TeamItem teamItem=(TeamItem)item;
				teamItem.setLastBuildTime(null);
				refreshPlayerTeamALLMessages(player, playerInfo, null,teamItem);
			}else if(item instanceof PlayerItem){
				PlayerItem playerItem=(PlayerItem)item;
				long date=System.currentTimeMillis()-sysItem.getTimeForCreateMsec()-86400000;
				playerItem.setValidDate(new Date(date));
				refreshPlayerTeamALLMessages(player, playerInfo, null,null,playerItem);
			}else{
				throw new BaseException(ExceptionMessage.ERROR_MESSAGE_ALL);
			}
			
			createPlayerBuyFunctionLog(player.getId(), CostType.FINISH_CREATE, fcCOST, CostFunction.ZYZDZ_FINISH_CREATE_TeamSpace);
		}else{
			throw new CRNotEnoughException(ExceptionMessage.NOT_ENOUGH_CR);//
		}	
		
	}	
	
	public BuyItemRecord createBuyItemRecord(int playerId,int sysItemId,int costId,int payType,int payAmount)throws Exception{
		BuyItemRecord buyItemRecord=new BuyItemRecord();
		buyItemRecord.setCostId(costId);
		buyItemRecord.setPlayerId(playerId);
		buyItemRecord.setPayAmount(payAmount);
		buyItemRecord.setPayType(payType);
		buyItemRecord.setItemId(sysItemId);
		buyItemRecord.setRecord(0);
		buyItemRecord.setCreateTime(new Date());
		buyItemRecord.setLastBuyDate(new Date());
		buyItemRecordDao.createBuyItemRecord(buyItemRecord);
		return buyItemRecord;
	}
		
	public void createBattleFieldRobDaily(final BattleFieldRobDaily bfrd)throws Exception{
		if(bfrd!=null){
			battleFieldRobDailyDao.insert(bfrd);
		}
	}
	
	/**
	 *  该方法为GetPinTuStates.java中移出作为公共使用
	 *  根据VIP等级产生拼图位置以及拼图个数
	 *  @param num ：VIP 等级
	 * */
	private HashSet<Integer> getPinTuStatesGetRandoms(int num){
		HashSet<Integer> cards=new HashSet<Integer>();
		Random random=new Random();
		int length=Constants.PLAYER_PT_PRI_FLAGS.length()-1;
		//make sure num<5
		num= num>7 ? 5: Math.round(num/2.0f)+1;
		while(cards.size()<(num+1)){
			cards.add(random.nextInt(length));
		}
		
		cards.remove(cards.iterator().next());
		return cards;	
	}
	/**
	 *  该方法为GetPinTuStates.java中移出作为公共使用
	 *  根据VIP等级产生拼图位置以及拼图个数
	 *  @param playerId 用户ID，playerPinTuFlags 玩家现有拼图状态
 	 * */
	public String getPinTuStatesOpenForVip(int playerId,String playerPinTuFlags)throws Exception{
		//新图时，不同vip等级翻不同张数 ,vip6直接翻4张
		Player player=getService.getPlayerById(playerId);
		CommonUtil.checkNull(player, ExceptionMessage.NO_HAVE_THE_CHARACTER);
		int vipLevel=player.getIsVip();
		if(vipLevel>=3){
			HashSet<Integer> openCards=getPinTuStatesGetRandoms(vipLevel);
			for(int i : openCards){
				playerPinTuFlags = StringUtil.replaceByIndex(playerPinTuFlags, i, '1');
				ServiceLocator.getLuckyPackageLog.info("Vip "+vipLevel+" open Pintu Card when begin new Pintu:\t" + playerId + "\t" + i);					
			}
			nosqlService.getNosql().set(Constants.PLAYER_PT_FLAG_KEY+ playerId, playerPinTuFlags);
		}
		
		return playerPinTuFlags;
	}
	
	/***
	 * 获得经验奖励 
	 *@param player
	 *@author OuYangGuang
	 *@date 20140808 15:00
	 */
	public void characterOnlineAddExp(Player player,int aExp){
		//int aExp = 190 ; 	//每天登陆固定增加 190 经验
		try {
			ServiceLocator.createService.awardExpToPlayer(player,aExp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//zlm2015-5-7-限时装备-开始  里面物品有时间限制的多选一礼包 返回客服端显示 持续时间有3天  
	public String use_SXZB_Choice_Box(SysItem si) throws Exception{
		List<SysItem> returnList=new ArrayList<SysItem>();
		int value=StringUtil.toInt(si.getIValue());
		int[] array=Constants.SXZB_CHOICE_BOX[value-1];
		for(int i:array){
			SysItem sysItem=getService.getSysItemByItemId(i);
			sysItem.setUnit(3);//3天
			sysItem.setUnitType(2);//天数
			if(sysItem!=null){
				returnList.add(sysItem);
			}
		}
		if(returnList.size()!=0){
			return Converter.usePlayerItem(returnList, 0);
		}
		return null;
	}
	//zlm2015-5-7-限时装备-结束
}
