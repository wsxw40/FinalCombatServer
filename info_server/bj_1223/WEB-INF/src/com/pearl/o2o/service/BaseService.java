package com.pearl.o2o.service;

import net.rubyeye.xmemcached.MemcachedClient;

import com.pde.log.client.InforLogger;
import com.pearl.o2o.dao.impl.nonjoin.AllyDao;
import com.pearl.o2o.dao.impl.nonjoin.BannedWordDao;
import com.pearl.o2o.dao.impl.nonjoin.BattleFieldRobDailyDao;
import com.pearl.o2o.dao.impl.nonjoin.BlackIPDao;
import com.pearl.o2o.dao.impl.nonjoin.BlackWhiteListDao;
import com.pearl.o2o.dao.impl.nonjoin.BuyItemRecordDao;
import com.pearl.o2o.dao.impl.nonjoin.CharacterDataDao;
import com.pearl.o2o.dao.impl.nonjoin.FriendDao;
import com.pearl.o2o.dao.impl.nonjoin.GmGroupDao;
import com.pearl.o2o.dao.impl.nonjoin.GmGroupPrivilegeDao;
import com.pearl.o2o.dao.impl.nonjoin.GmLogDao;
import com.pearl.o2o.dao.impl.nonjoin.GmPrivilegeDao;
import com.pearl.o2o.dao.impl.nonjoin.GmReportDao;
import com.pearl.o2o.dao.impl.nonjoin.GmUserDao;
import com.pearl.o2o.dao.impl.nonjoin.GmUserGroupDao;
import com.pearl.o2o.dao.impl.nonjoin.ItemModDao;
import com.pearl.o2o.dao.impl.nonjoin.MeltingAwardDao;
import com.pearl.o2o.dao.impl.nonjoin.MessageDao;
import com.pearl.o2o.dao.impl.nonjoin.OnlineAwardDao;
import com.pearl.o2o.dao.impl.nonjoin.PayLogDao;
import com.pearl.o2o.dao.impl.nonjoin.PaymentDao;
import com.pearl.o2o.dao.impl.nonjoin.PlayerAchievementDao;
import com.pearl.o2o.dao.impl.nonjoin.PlayerActivityDao;
import com.pearl.o2o.dao.impl.nonjoin.PlayerBuffDao;
import com.pearl.o2o.dao.impl.nonjoin.PlayerChestDao;
import com.pearl.o2o.dao.impl.nonjoin.PlayerDao;
import com.pearl.o2o.dao.impl.nonjoin.PlayerGrowthMissionDao;
import com.pearl.o2o.dao.impl.nonjoin.PlayerInfoDao;
import com.pearl.o2o.dao.impl.nonjoin.PlayerItemDao;
import com.pearl.o2o.dao.impl.nonjoin.PlayerMeltingDao;
import com.pearl.o2o.dao.impl.nonjoin.PlayerMissionDao;
import com.pearl.o2o.dao.impl.nonjoin.PlayerPackDao;
import com.pearl.o2o.dao.impl.nonjoin.PlayerTeamDao;
import com.pearl.o2o.dao.impl.nonjoin.RankDao;
import com.pearl.o2o.dao.impl.nonjoin.ServerDao;
import com.pearl.o2o.dao.impl.nonjoin.StrengthenAppendDao;
import com.pearl.o2o.dao.impl.nonjoin.SysAchievementDao;
import com.pearl.o2o.dao.impl.nonjoin.SysActivityDao;
import com.pearl.o2o.dao.impl.nonjoin.SysBioCharacterDao;
import com.pearl.o2o.dao.impl.nonjoin.SysCharacterDao;
import com.pearl.o2o.dao.impl.nonjoin.SysCharacterLogDao;
import com.pearl.o2o.dao.impl.nonjoin.SysChestDao;
import com.pearl.o2o.dao.impl.nonjoin.SysGrowthMissionDao;
import com.pearl.o2o.dao.impl.nonjoin.SysIconDao;
import com.pearl.o2o.dao.impl.nonjoin.SysItemDao;
import com.pearl.o2o.dao.impl.nonjoin.SysItemLogDao;
import com.pearl.o2o.dao.impl.nonjoin.SysLevelDao;
import com.pearl.o2o.dao.impl.nonjoin.SysMissionDao;
import com.pearl.o2o.dao.impl.nonjoin.SysNoticeDao;
import com.pearl.o2o.dao.impl.nonjoin.SysTeamBuffDao;
import com.pearl.o2o.dao.impl.nonjoin.TeamBuffDao;
import com.pearl.o2o.dao.impl.nonjoin.TeamDao;
import com.pearl.o2o.dao.impl.nonjoin.TeamItemDao;
import com.pearl.o2o.dao.impl.nonjoin.TeamLevelDao;
import com.pearl.o2o.dao.impl.nonjoin.TeamProclamationDao;
import com.pearl.o2o.dao.impl.nonjoin.TeamRecordDao;
import com.pearl.o2o.dao.impl.nonjoin.TeamTechnologyDao;
import com.pearl.o2o.dao.impl.nonjoin.UnSpeakerDao;
import com.pearl.o2o.dao.impl.nonjoin.XunleiOrderLogDao;
import com.pearl.o2o.dao.impl.usejoin.KeyWordsDao;
import com.pearl.o2o.dao.impl.usejoin.SysModeConfigDao;
import com.pearl.o2o.dao.impl.usejoin.SystemDao;
import com.pearl.o2o.dao.impl.usejoin.TeamHistoryDao;
import com.pearl.o2o.dao.impl.usejoin.UserDao;
import com.pearl.o2o.socket.SocketClientNew;
import com.pearl.o2o.utils.TransferDataToDC;


public class BaseService {
	protected MemcachedClient 		mcc;
	protected PaymentDao			paymentDao;
	protected UserDao 				userDao;
	protected PlayerDao 			playerDao;
	protected PlayerInfoDao 		playerInfoDao;
	protected FriendDao 			friendDao;
	protected ServerDao 			serverDao;
	protected PlayerPackDao       	playerPackDao;
	protected PlayerItemDao       	playerItemDao;
	protected SysItemDao 			sysItemDao;
	protected SysActivityDao 		sysActivityDao;
	protected SysItemLogDao			sysItemLogDao;
	protected ItemModDao			itemModDao;
	protected MessageDao			messageDao;
	protected GetService 			getService;
	protected ResetService			resetService;
	protected SysCharacterDao		sysCharacterDao;
	protected SysBioCharacterDao	sysBioCharacterDao;
	protected SysCharacterLogDao	sysCharacterLogDao;
	protected PlayerBuffDao			playerBuffDao;
	protected TeamDao 	    		teamDao;
	protected AllyDao 	    		allyDao;
	protected PlayerTeamDao       	playerTeamDao;
	protected SysLevelDao			sysLevelDao;
	protected SysModeConfigDao    	sysModeConfigDao;
	protected SocketClientNew 		soClient;
	protected RankDao 				rankDao;
	protected TeamHistoryDao 	    teamHistoryDao;
	protected SystemDao				systemDao;
	protected CharacterDataDao		characterDataDao;
	protected KeyWordsDao 			keyWordsDao;
	protected NosqlService			nosqlService;
	protected SysNoticeDao			sysNoticeDao;
	protected SysIconDao			sysIconDao;
	protected BlackIPDao 			blackIPDao;
	protected BlackWhiteListDao     blackWhiteListDao;
	protected PlayerAchievementDao	playerAchievementDao;
	protected BannedWordDao			bannedWordDao;
	protected SysAchievementDao		sysAchievementDao;
	protected PayLogDao 			payLogDao;
	protected XunleiOrderLogDao 	xunleiOrderLogDao;
	protected GmUserDao				gmUserDao;
	protected GmReportDao			gmReportDao;
	protected GmLogDao				gmLogDao;
	protected GmGroupDao			gmGroupDao;
	protected GmPrivilegeDao		gmPrivilegeDao;
	protected GmGroupPrivilegeDao	gmGroupPrivilegeDao;
	protected GmUserGroupDao		gmUserGroupDao;
	protected PlayerMissionDao		playerMissionDao;
	protected PlayerActivityDao		playerActivityDao;
	protected SysMissionDao			sysMissionDao;
	protected UnSpeakerDao 			unSpeakerDao;
	protected SysChestDao 			sysChestDao;
	protected PlayerChestDao 		playerChestDao;
	protected OnlineAwardDao		onlineAwardDao;
	protected SysGrowthMissionDao	sysGrowthMissionDao;
	protected PlayerGrowthMissionDao	playerGrowthMissionDao;
	protected StrengthenAppendDao	strengthenAppendDao;
	protected PlayerMeltingDao	playerMeltingDao;
	protected MeltingAwardDao	meltingAwardDao;
	protected TeamProclamationDao	teamProclamationDao;
	protected TeamBuffDao	teamBuffDao;
	protected TeamRecordDao	teamRecordDao;
	protected SysTeamBuffDao	sysTeamBuffDao;

	protected TeamLevelDao teamLevelDao;
	
	protected InforLogger infoLogger;
	protected BattleFieldRobDailyDao battleFieldRobDailyDao;
	protected TeamItemDao teamItemDao;
	protected TeamTechnologyDao teamTechnologyDao;
	protected BuyItemRecordDao buyItemRecordDao;
	
	//log to analyser
	protected TransferDataToDC transferDataToDc;

	public TransferDataToDC getTransferDataToDc() {
		return transferDataToDc;
	}
	public void setTransferDataToDc(TransferDataToDC transferDataToDc) {
		this.transferDataToDc = transferDataToDc;
	}
	public InforLogger getInfoLogger() {
		return infoLogger; 
	}
	public void setInfoLogger(InforLogger infoLogger) {
		this.infoLogger = infoLogger;
	}
	public TeamBuffDao getTeamBuffDao() {
		return teamBuffDao;
	}
	public void setTeamBuffDao(TeamBuffDao teamBuffDao) {
		this.teamBuffDao = teamBuffDao;
	}
	public TeamRecordDao getTeamRecordDao() {
		return teamRecordDao;
	}
	public void setTeamRecordDao(TeamRecordDao teamRecordDao) {
		this.teamRecordDao = teamRecordDao;
	}
	public SysTeamBuffDao getSysTeamBuffDao() {
		return sysTeamBuffDao;
	}
	public void setSysTeamBuffDao(SysTeamBuffDao sysTeamBuffDao) {
		this.sysTeamBuffDao = sysTeamBuffDao;
	}
	public TeamProclamationDao getTeamProclamationDao() {
		return teamProclamationDao;
	}
	public void setTeamProclamationDao(TeamProclamationDao teamProclamationDao) {
		this.teamProclamationDao = teamProclamationDao;
	}
	public MeltingAwardDao getMeltingAwardDao() {
		return meltingAwardDao;
	}
	public void setMeltingAwardDao(MeltingAwardDao meltingAwardDao) {
		this.meltingAwardDao = meltingAwardDao;
	}
	public PlayerMeltingDao getPlayerMeltingDao() {
		return playerMeltingDao;
	}
	public void setPlayerMeltingDao(PlayerMeltingDao playerMeltingDao) {
		this.playerMeltingDao = playerMeltingDao;
	}
	public PlayerAchievementDao getPlayerAchievementDao() {
		return playerAchievementDao;
	}
	public PlayerInfoDao getPlayerInfoDao() {
		return playerInfoDao;
	}
	public void setPlayerAchievementDao(PlayerAchievementDao playerAchievementDao) {
		this.playerAchievementDao = playerAchievementDao;
	}
	public SysAchievementDao getSysAchievementDao() {
		return sysAchievementDao;
	}
	public PaymentDao getPaymentDao() {
		return paymentDao;
	}
	public SysActivityDao getSysActivityDao() {
		return sysActivityDao;
	}
	public PayLogDao getPayLogDao() {
		return payLogDao;
	}
	public void setSysAchievementDao(SysAchievementDao sysAchievementDao) {
		this.sysAchievementDao = sysAchievementDao;
	}
	public SysIconDao getSysIconDao() {
		return sysIconDao;
	}
	public void setSysIconDao(SysIconDao sysIconDao) {
		this.sysIconDao = sysIconDao;
	}
	public MemcachedClient getMcc() {
		return mcc;
	}
	public void setMcc(MemcachedClient mcc) {
		this.mcc = mcc;
	}
	public UserDao getUserDao() {
		return userDao;
	}
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	public PlayerDao getPlayerDao() {
		return playerDao;
	}
	public void setPlayerDao(PlayerDao playerDao) {
		this.playerDao = playerDao;
	}
	public void setPayLogDao(PayLogDao payLogDao) {
		this.payLogDao = payLogDao;
	}
	public FriendDao getFriendDao() {
		return friendDao;
	}
	public void setFriendDao(FriendDao friendDao) {
		this.friendDao = friendDao;
	}
	public ServerDao getServerDao() {
		return serverDao;
	}
	public void setServerDao(ServerDao serverDao) {
		this.serverDao = serverDao;
	}
	public PlayerPackDao getPlayerPackDao() {
		return playerPackDao;
	}
	public void setPlayerPackDao(PlayerPackDao playerPackDao) {
		this.playerPackDao = playerPackDao;
	}
	public PlayerItemDao getPlayerItemDao() {
		return playerItemDao;
	}
	public void setPlayerItemDao(PlayerItemDao playerItemDao) {
		this.playerItemDao = playerItemDao;
	}
	public SysItemDao getSysItemDao() {
		return sysItemDao;
	}
	public void setSysItemDao(SysItemDao sysItemDao) {
		this.sysItemDao = sysItemDao;
	}
	public SysItemLogDao getSysItemLogDao() {
		return sysItemLogDao;
	}
	public void setSysItemLogDao(SysItemLogDao sysItemLogDao) {
		this.sysItemLogDao = sysItemLogDao;
	}
	public ItemModDao getItemModDao() {
		return itemModDao;
	}
	public void setItemModDao(ItemModDao itemModDao) {
		this.itemModDao = itemModDao;
	}
	public MessageDao getMessageDao() {
		return messageDao;
	}
	public void setMessageDao(MessageDao messageDao) {
		this.messageDao = messageDao;
	}
	public GetService getGetService() {
		return getService;
	}
	public void setGetService(GetService getService) {
		this.getService = getService;
	}
	public SysCharacterDao getSysCharacterDao() {
		return sysCharacterDao;
	}
	public void setSysCharacterDao(SysCharacterDao characterDao) {
		this.sysCharacterDao = characterDao;
	}
	public SysCharacterLogDao getSysCharacterLogDao() {
		return sysCharacterLogDao;
	}
	public void setSysCharacterLogDao(SysCharacterLogDao characterLogDao) {
		this.sysCharacterLogDao = characterLogDao;
	}
	public void setPlayerBuffDao(PlayerBuffDao playerBuffDao) {
		this.playerBuffDao = playerBuffDao;
	}
	public TeamDao getTeamDao() {
		return teamDao;
	}
	public void setTeamDao(TeamDao teamDao) {
		this.teamDao = teamDao;
	}
	public PlayerTeamDao getPlayerTeamDao() {
		return playerTeamDao;
	}
	public void setPlayerTeamDao(PlayerTeamDao playerTeamDao) {
		this.playerTeamDao = playerTeamDao;
	}
	public SysLevelDao getSysLevelDao() {
		return sysLevelDao;
	}
	public void setSysLevelDao(SysLevelDao sysLevelDao) {
		this.sysLevelDao = sysLevelDao;
	}
	public SysModeConfigDao getSysModeConfigDao() {
		return sysModeConfigDao;
	}
	public void setSysModeConfigDao(SysModeConfigDao sysModeConfigDao) {
		this.sysModeConfigDao = sysModeConfigDao;
	}
	
	public SocketClientNew getSoClient() {
		return soClient;
	}
	public RankDao getRankDao() {
		return rankDao;
	}
	public void setRankDao(RankDao rankDao) {
		this.rankDao = rankDao;
	}
	public TeamHistoryDao getTeamHistoryDao() {
		return teamHistoryDao;
	}
	public void setTeamHistoryDao(TeamHistoryDao teamHistoryDao) {
		this.teamHistoryDao = teamHistoryDao;
	}
	public SystemDao getSystemDao() {
		return systemDao;
	}
	public void setSystemDao(SystemDao systemDao) {
		this.systemDao = systemDao;
	}
	public KeyWordsDao getKeyWordsDao() {
		return keyWordsDao;
	}
	public void setKeyWordsDao(KeyWordsDao keyWordsDao) {
		this.keyWordsDao = keyWordsDao;
	}
	public AllyDao getAllyDao() {
		return allyDao;
	}
	public void setAllyDao(AllyDao allyDao) {
		this.allyDao = allyDao;
	}
	public NosqlService getNosqlService() {
		return nosqlService;
	}
	public void setNosqlService(NosqlService nosqlService) {
		this.nosqlService = nosqlService;
	}
	public void setSoClient(SocketClientNew soClient) {
		this.soClient = soClient;
	}
	public void setSysActivityDao(SysActivityDao sysActivityDao) {
		this.sysActivityDao = sysActivityDao;
	}
	public PlayerBuffDao getPlayerBuffDao() {
		return playerBuffDao;
	}
	public void setPaymentDao(PaymentDao paymentDao) {
		this.paymentDao = paymentDao;
	}
	public void setCharacterDataDao(CharacterDataDao characterDataDao) {
		this.characterDataDao = characterDataDao;
	}
	public SysNoticeDao getSysNoticeDao() {
		return sysNoticeDao;
	}
	public void setSysNoticeDao(SysNoticeDao sysNoticeDao) {
		this.sysNoticeDao = sysNoticeDao;
	}
	public BlackIPDao getBlackIPDao() {
		return blackIPDao;
	}
	public void setBlackIPDao(BlackIPDao blackIPDao) {
		this.blackIPDao = blackIPDao;
	}
	public BlackWhiteListDao getBlackWhiteListDao() {
		return blackWhiteListDao;
	}
	public void setBlackWhiteListDao(BlackWhiteListDao blackWhiteListDao) {
		this.blackWhiteListDao = blackWhiteListDao;
	}
	public BannedWordDao getBannedWordDao() {
		return bannedWordDao;
	}
	public XunleiOrderLogDao getXunleiOrderLogDao() {
		return xunleiOrderLogDao;
	}
	public void setXunleiOrderLogDao(XunleiOrderLogDao xunleiOrderLogDao) {
		this.xunleiOrderLogDao = xunleiOrderLogDao;
	}
	public void setBannedWordDao(BannedWordDao bannedWordDao) {
		this.bannedWordDao = bannedWordDao;
	}
	public CharacterDataDao getCharacterDataDao() {
		return characterDataDao;
	}
	public GmUserDao getGmUserDao() {
		return gmUserDao;
	}
	public void setGmUserDao(GmUserDao gmUserDao) {
		this.gmUserDao = gmUserDao;
	}
	public GmReportDao getGmReportDao() {
		return gmReportDao;
	}
	public void setGmReportDao(GmReportDao gmReportDao) {
		this.gmReportDao = gmReportDao;
	}
	public GmLogDao getGmLogDao() {
		return gmLogDao;
	}
	public void setGmLogDao(GmLogDao gmLogDao) {
		this.gmLogDao = gmLogDao;
	}
	public GmGroupDao getGmGroupDao() {
		return gmGroupDao;
	}
	public void setGmGroupDao(GmGroupDao gmGroupDao) {
		this.gmGroupDao = gmGroupDao;
	}
	public GmPrivilegeDao getGmPrivilegeDao() {
		return gmPrivilegeDao;
	}
	public void setGmPrivilegeDao(GmPrivilegeDao gmPrivilegeDao) {
		this.gmPrivilegeDao = gmPrivilegeDao;
	}
	public GmGroupPrivilegeDao getGmGroupPrivilegeDao() {
		return gmGroupPrivilegeDao;
	}
	public void setGmGroupPrivilegeDao(GmGroupPrivilegeDao gmGroupPrivilegeDao) {
		this.gmGroupPrivilegeDao = gmGroupPrivilegeDao;
	}
	public GmUserGroupDao getGmUserGroupDao() {
		return gmUserGroupDao;
	}
	public void setGmUserGroupDao(GmUserGroupDao gmUserGroupDao) {
		this.gmUserGroupDao = gmUserGroupDao;
	}
	public PlayerMissionDao getPlayerMissionDao() {
		return playerMissionDao;
	}
	public void setPlayerMissionDao(PlayerMissionDao playerMissionDao) {
		this.playerMissionDao = playerMissionDao;
	}
	public SysMissionDao getSysMissionDao() {
		return sysMissionDao;
	}
	public void setSysMissionDao(SysMissionDao sysMissionDao) {
		this.sysMissionDao = sysMissionDao;
	}
	public StrengthenAppendDao getStrengthenAppendDao() {
		return strengthenAppendDao;
	}
	public void setStrengthenAppendDao(StrengthenAppendDao strengthenAppendDao) {
		this.strengthenAppendDao = strengthenAppendDao;
	}
	public PlayerActivityDao getPlayerActivityDao() {
		return playerActivityDao;
	}
	public void setPlayerActivityDao(PlayerActivityDao playerActivityDao) {
		this.playerActivityDao = playerActivityDao;
	}
	public void setPlayerInfoDao(PlayerInfoDao playerInfoDao) {
		this.playerInfoDao = playerInfoDao;
	}
	public UnSpeakerDao getUnSpeakerDao() {
		return unSpeakerDao;
	}
	public void setUnSpeakerDao(UnSpeakerDao unSpeakerDao) {
		this.unSpeakerDao = unSpeakerDao;
	}
	public SysChestDao getSysChestDao() {
		return sysChestDao;
	}
	public void setSysChestDao(SysChestDao sysChestDao) {
		this.sysChestDao = sysChestDao;
	}
	public PlayerChestDao getPlayerChestDao() {
		return playerChestDao;
	}
	public void setPlayerChestDao(PlayerChestDao playerChestDao) {
		this.playerChestDao = playerChestDao;
	}
	public OnlineAwardDao getOnlineAwardDao() {
		return onlineAwardDao;
	}
	public void setOnlineAwardDao(OnlineAwardDao onlineAwardDao) {
		this.onlineAwardDao = onlineAwardDao;
	}
	public SysGrowthMissionDao getSysGrowthMissionDao() {
		return sysGrowthMissionDao;
	}
	public void setSysGrowthMissionDao(SysGrowthMissionDao sysGrowthMissionDao) {
		this.sysGrowthMissionDao = sysGrowthMissionDao;
	}
	public PlayerGrowthMissionDao getPlayerGrowthMissionDao() {
		return playerGrowthMissionDao;
	}
	public void setPlayerGrowthMissionDao(PlayerGrowthMissionDao playerGrowthMissionDao) {
		this.playerGrowthMissionDao = playerGrowthMissionDao;
	}
	public ResetService getResetService() {
		return resetService;
	}
	public void setResetService(ResetService resetService) {
		this.resetService = resetService;
	}
	public SysBioCharacterDao getSysBioCharacterDao() {
		return sysBioCharacterDao;
	}
	public void setSysBioCharacterDao(SysBioCharacterDao sysBioCharacterDao) {
		this.sysBioCharacterDao = sysBioCharacterDao;
	}
	public TeamLevelDao getTeamLevelDao() {
		return teamLevelDao;
	}
	public void setTeamLevelDao(TeamLevelDao teamLevelDao) {
		this.teamLevelDao = teamLevelDao;
	}
	public BattleFieldRobDailyDao getBattleFieldRobDailyDao() {
		return battleFieldRobDailyDao;
	}
	public void setBattleFieldRobDailyDao(
			BattleFieldRobDailyDao battleFieldRobDailyDao) {
		this.battleFieldRobDailyDao = battleFieldRobDailyDao;
	}
	public TeamItemDao getTeamItemDao() {
		return teamItemDao;
	}
	public void setTeamItemDao(TeamItemDao teamItemDao) {
		this.teamItemDao = teamItemDao;
	}
	public TeamTechnologyDao getTeamTechnologyDao() {
		return teamTechnologyDao;
	}
	public void setTeamTechnologyDao(TeamTechnologyDao teamTechnologyDao) {
		this.teamTechnologyDao = teamTechnologyDao;
	}
	public BuyItemRecordDao getBuyItemRecordDao() {
		return buyItemRecordDao;
	}
	public void setBuyItemRecordDao(BuyItemRecordDao buyItemRecordDao) {
		this.buyItemRecordDao = buyItemRecordDao;
	}
	
}
