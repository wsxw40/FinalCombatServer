package com.pearl.o2o.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadPoolExecutor;

import org.lilystudio.smarty4j.Context;
import org.lilystudio.smarty4j.TemplateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.o2o.manager.SmartyManager;
import com.pearl.o2o.nosql.object.playerevent.BasePlayerEvent;
import com.pearl.o2o.nosql.object.team.TeamBattleHistory;
import com.pearl.o2o.pojo.AwardItemVo;
import com.pearl.o2o.pojo.Channel;
import com.pearl.o2o.pojo.Character;
import com.pearl.o2o.pojo.CharacterData;
import com.pearl.o2o.pojo.Friend;
import com.pearl.o2o.pojo.GeneralStageClearInfo;
import com.pearl.o2o.pojo.GrowthMissionVo;
import com.pearl.o2o.pojo.MeltingMenu;
import com.pearl.o2o.pojo.MeltingReslut;
import com.pearl.o2o.pojo.Message;
import com.pearl.o2o.pojo.OnlineAward;
import com.pearl.o2o.pojo.Payment;
import com.pearl.o2o.pojo.Player;
import com.pearl.o2o.pojo.PlayerAchievement;
import com.pearl.o2o.pojo.PlayerActivity;
import com.pearl.o2o.pojo.PlayerInfo;
import com.pearl.o2o.pojo.PlayerItem;
import com.pearl.o2o.pojo.PlayerMelting;
import com.pearl.o2o.pojo.PlayerMission;
import com.pearl.o2o.pojo.PlayerTeam;
import com.pearl.o2o.pojo.Rank;
import com.pearl.o2o.pojo.Server;
import com.pearl.o2o.pojo.Switch;
import com.pearl.o2o.pojo.SysActivity;
import com.pearl.o2o.pojo.SysCharacter;
import com.pearl.o2o.pojo.SysChest;
import com.pearl.o2o.pojo.SysIcon;
import com.pearl.o2o.pojo.SysItem;
import com.pearl.o2o.pojo.Team;
import com.pearl.o2o.pojo.TeamItem;
import com.pearl.o2o.pojo.TeamLevelInfo;
import com.pearl.o2o.pojo.TeamProclamation;
import com.pearl.o2o.pojo.TeamRecord;
import com.pearl.o2o.pojo.TmpPlayerItem;
import com.pearl.o2o.socket.DefaultSocketHandler;
import com.pearl.o2o.utils.Constants.TeamSpaceConstants.FightType;
import com.pearl.o2o.utils.GrowthMissionConstants.ModuleStatus;
import com.pearl.o2o.utils.PerformanceDatas.InterfaceEntry;

public class Converter {
	private static final Logger log = LoggerFactory.getLogger(Converter.class);
	private static final SmartyManager sm = ServiceLocator.sm;
	//lucky package
	//		timelimit = #if $useType==0# "永久使用" #elseif $useType==1# "#$number#天" #elseif $useType==2# "#$number#个" #/if#,
	public static String luckyPackages(List<SysChest> random,int pageCount,int pageNo){
		String value="";
		try{
			Context ctx		 = new Context();
			ctx.set("random", random);
			ctx.set("pageCount", pageCount);
			ctx.set("pageNo", pageNo);
			return sm.getEncodedBody("LuckyPackages.st",  ctx);
		}catch(Exception e){
			log.warn("Error in Convertor: ", e);
		}
		return value;
	}
	
	public static String luckPackagePrice(int chipNum,List<SysChest> fix,int copperyKeyNum , int silverKeyNum,int goldKeyNum,List<SysItem> luckyPackages){
		String value="";
		try{
			Context ctx		 = new Context();
			ctx.set("chipNum", chipNum);
			ctx.set("copperyKeyNum", copperyKeyNum);
			ctx.set("silverKeyNum", silverKeyNum);
			ctx.set("goldKeyNum", goldKeyNum);
			ctx.set("fix", fix);
			ctx.set("luckyPackages", luckyPackages);
			
			return sm.getEncodedBody("LuckyPackagePrice.st",  ctx);
		}catch(Exception e){
			log.warn("Error in Convertor: ", e);
		}
		return value;
	}
	public static String playerLuckyPackage(String randoms,List<SysChest> fix,List<SysChest> multiRandoms,String errMsg,OnlineAward onlineAward,String flags ,String indexStr){
		String value = "";
		try{
			Context ctx	= new Context();
			ctx.set("fix", fix);
			ctx.set("randoms", randoms);
			ctx.set("multiRandoms", multiRandoms);
			ctx.set("erroMsg", errMsg);
			ctx.set("onlineAward", onlineAward);
			ctx.set("flags", flags);
			ctx.set("indexs", indexStr);
			return sm.getEncodedBody("LuckyPackagePlayer.st", ctx);
		}catch(Exception e){
			log.warn("Error in Convertor: ",e);
		}
		return value;
	}
	public static String misticAward(OnlineAward misticAward,String flags){
		String value = "";
		try{
			Context ctx	= new Context();
			ctx.set("misticAward", misticAward);
			ctx.set("flags",flags);
			return sm.getEncodedBody("MisticAward.st", ctx);
		}catch(Exception e){
			log.warn("Error in Convertor: ",e);
		}
		return value;
	}
	//daily check
	public static String dailyGift(String msg,List<OnlineAward> items,int isGift,QuietBounds quietBounds) {
		String value = "";
		try{			
			Context ctx		 = new Context();
			ctx.set("msg", msg);
			ctx.set("daitemsy", items);
			ctx.set("isGift", isGift);
			ctx.set("quietBounds", quietBounds);
			return sm.getEncodedBody("DailyGift.st",  ctx);			
		}
		catch(Exception e){
			log.warn("Error in Convertor: ", e);
		}
		return value;			
	}
	
	
	public static String sendTeamSalary(String msg,SysItem pi,int isGift,Payment pm) {
		String value = "";
		try{			
			Context ctx		 = new Context();
			ctx.set("msg", msg);
			ctx.set("pi", pi);
			ctx.set("pm", pm);
			ctx.set("isSalary", isGift);
			return sm.getEncodedBody("TeamSalary.st",  ctx);			
		}
		catch(Exception e){
			log.warn("Error in Convertor: ", e);
		}
		return value;			
	}
	public static String dailyCheck(String date,int day,List<String> checkList,int result) {
		String value = "";
		try{			
			Context ctx		 = new Context();
			ctx.set("result", result);
			ctx.set("date", date);
			ctx.set("day", day);
			ctx.set("checkList", checkList);
			return sm.getEncodedBody("DailyCheck.st",  ctx);			
		}
		catch(Exception e){
			log.warn("Error in Convertor: ", e);
		}
		return value;			
	}
	
	/**
	 * 
	 * @param myNum  玩家猜的数字（今天）
	 * @param tomorrow 玩家猜的数字（明天）
	 * @param freeCheckTimes 允许玩家免费预签和补签的次数
	 */
	public static String dailyCheckList(String date,int day,List<String> checkList,
			List<SysItem> items,int today,String myNum,String tomorrow,int isGift,int isShowAward, int freeCheckTimes,int checkNum) {
		String value = "";
		try{			
			Context ctx = new Context();
			ctx.set("date", date);
			ctx.set("day", day);
			ctx.set("checkList", checkList);
			ctx.set("items", items);
			ctx.set("today", today);
			ctx.set("myNum", myNum);
			ctx.set("tomorrow", tomorrow);
			ctx.set("isGift", isGift);
			ctx.set("isShowAward", isShowAward);
			ctx.set("freeCheckTimes", freeCheckTimes);
			ctx.set("checkNum", checkNum);
			return sm.getEncodedBody("DailyCheckList.st",  ctx);			
		}
		catch(Exception e){
			log.warn("Error in Convertor: ", e);
		}
		return value;			
	}
	// online award
	public static String onlineAward(int leftSecond,int cExp,String next,List<OnlineAward> gifts,String errorMsg) {
		String value = "";
		try{			
			Context ctx		 = new Context();
			ctx.set("leftSecond", leftSecond);
			ctx.set("cExp", cExp);
			ctx.set("next", next);
			ctx.set("gifts", gifts);
			ctx.set("errorMsg", errorMsg);
			return sm.getEncodedBody("OnlineAward.st",  ctx);			
		}
		catch(Exception e){
			log.warn("Error in Convertor: ", e);
		}
		return value;			
	}
	public static String friendPartners(List<Player> players) {
		String value = "";
		try{			
			Context ctx		 = new Context();				
			
			ctx.set("list", players);
			return sm.getEncodedBody("FriendPartner.st",  ctx);			
		}
		catch(Exception e){
			log.warn("Error in Convertor: ", e);
		}
		return value;			
	}
	public static String playerActivity(List<PlayerActivity> list,int awoke,int pageNo,int pageCount) {
		String value = "";
		try{			
			Context ctx		 = new Context();				
			ctx.set("num", list.size());
			ctx.set("list", list);
			ctx.set("awoke", awoke);
			ctx.set("page", pageNo);
			ctx.set("pages", pageCount);
			return sm.getEncodedBody("PlayerActivity.st",  ctx);			
		}
		catch(Exception e){
			log.warn("Error in Convertor: ", e);
		}
		return value;			
	}
	
	public static String expiredPackNotify(List<PlayerItem> items) {
		String value = "";
		try{			
			Context ctx		 = new Context();				
			
			ctx.set("list", items);
			return sm.getEncodedBody("ExpiredPackNotify_mail.st",  ctx);			
		}
		catch(Exception e){
			log.warn("Error in Convertor: ", e);
		}
		return value;			
	}
	public static String expiredItemNotify(List<PlayerItem> items) {
		String value = "";
		try{			
			Context ctx		 = new Context();				
			
			ctx.set("list", items);
			return sm.getEncodedBody("ExpiredItemNotify_mail.st",  ctx);			
		}
		catch(Exception e){
			log.warn("Error in Convertor: ", e);
		}
		return value;			
	}
	public static String durableNotify(List<PlayerItem> items) {
		String value = "";
		try{			
			Context ctx		 = new Context();				
			
			ctx.set("list", items);
			return sm.getEncodedBody("DurableNotify_mail.st",  ctx);			
		}
		catch(Exception e){
			log.warn("Error in Convertor: ", e);
		}
		return value;			
	}
	
	public static String expiredItemInShortTime(List<PlayerItem> items,List<PlayerItem> durableList) {
		String value = "";
		try{			
			Context ctx	 = new Context();				
			
			ctx.set("list", items);
			ctx.set("durableList", durableList);
			return sm.getEncodedBody("ExpiredItemNotify_window.st",  ctx);			
		}
		catch(Exception e){
			log.warn("Error in Convertor: ", e);
		}
		return value;			
	}
	
	public static String friendNewsEntry(BasePlayerEvent event) {
		String value = "";
		
		try{			
			Context ctx		 = new Context();				
			
			ctx.set("event", event);
			return sm.getEncodedBody("FriendNewsEntry.st",  ctx);			
		}
		catch(Exception e){
			log.warn("Error in Convertor: ", e);
		}
		return value;			
	}
	
	
	public static String friendRankList(List<Friend> list) {
		String value = "";
		
		try{			
			Context ctx		 = new Context();	
			ctx.set("list", list);
			
			return sm.getEncodedBody("FriendRankList.st",  ctx);			
		}
		catch(Exception e){
			log.warn("Error in Convertor: ", e);
		}
		return value;			
	}
	
	
	public static String monitorList( List<InterfaceEntry> datas, List<DefaultSocketHandler> handlers) {
		String value = "";
		
		try{			
			Context ctx		 = new Context();				
			
			ctx.set("channelList", handlers);
			ctx.set("datas", datas);
			ctx.set("asynQueueSize",((ThreadPoolExecutor)(ServiceLocator.asynTakService)).getQueue().size());
			ctx.set("switchs", Switch.getSwitchList());
			return sm.getEncodedBody("Monitor_channelList.st",  ctx);			
		}
		catch(Exception e){
			log.warn("Error in Convertor: ", e);
		}
		return value;			
	}
	
	
	public static String monitor( DefaultSocketHandler handler,  List<String[]> proxyInfo) {
		String value = "";
		
		try{			
			Context ctx		 = new Context();				
			
			ctx.set("proxyName", handler.getDisplayName());
			ctx.set("reqQueueSize", handler.getReqQueue().size());	
			ctx.set("resQueueSize", handler.getResQueue().size());
			
			ThreadPoolExecutor loginPool = (ThreadPoolExecutor) handler.getLoginPool();
			ThreadPoolExecutor serverPool = (ThreadPoolExecutor) handler.getServerPool();
			ThreadPoolExecutor clientPool = (ThreadPoolExecutor) handler.getClientPool(); 
			ThreadPoolExecutor gamePool = (ThreadPoolExecutor) handler.getGamePool(); 
			ThreadPoolExecutor playerPool = (ThreadPoolExecutor) handler.getPlayerPool(); 
			
			ctx.set("loginActive", loginPool.getActiveCount());
			ctx.set("loginTaskQueue", loginPool.getQueue().size());
			ctx.set("loginMax", loginPool.getMaximumPoolSize());
			
			ctx.set("serverActive", serverPool.getActiveCount());
			ctx.set("serverTaskQueue", serverPool.getQueue().size());
			ctx.set("serverMax", serverPool.getMaximumPoolSize());
			
			ctx.set("clientActive", clientPool.getActiveCount());
			ctx.set("clientTaskQueue", clientPool.getQueue().size());
			ctx.set("clientMax", clientPool.getMaximumPoolSize());
			
			ctx.set("gameActive", gamePool.getActiveCount());
			ctx.set("gameTaskQueue", gamePool.getQueue().size());
			ctx.set("gameMax", gamePool.getMaximumPoolSize());
			
			ctx.set("playerActive", playerPool.getActiveCount());
			ctx.set("playerTaskQueue", playerPool.getQueue().size());
			ctx.set("playerMax", playerPool.getMaximumPoolSize());
			
			ctx.set("proxyInfo", proxyInfo);
			
			return sm.getEncodedBody("Monitor.st",  ctx);			
		}
		catch(Exception e){
			log.warn("Error in Convertor: ", e);
		}
		return value;			
	}
	
	public static String error(String msg){
		String value = "";
		
		try{			
			Context ctx		 = new Context();				
			ctx.set("msg", msg);					
			return sm.getEncodedBody("Error.st",  ctx);			
		}
		catch(Exception e){
			log.warn("Error in Convertor: ", e);
		}
		
		return value;			
	}
	public static String warn(String warnMsg){
		String value = "";
		
		try{			
			Context ctx		 = new Context();				
			ctx.set("warnMsg", warnMsg);					
			return sm.getEncodedBody("Warning.st",  ctx);			
		}
		catch(Exception e){
			log.warn("Error in Convertor: ", e);
		}
		
		return value;			
	}
	public static String warn2(String warnMsg1,String warnMsg2){
		String value = "";
		
		try{			
			Context ctx		 = new Context();				
			ctx.set("warnMsg1", warnMsg1);	
			ctx.set("warnMsg2", warnMsg2);
			return sm.getEncodedBody("Warning2.st",  ctx);			
		}
		catch(Exception e){
			log.warn("Error in Convertor: ", e);
		}
		
		return value;			
	}
	public static String createPlayer(Integer id,String erroMsg, String warnMsg){
		String value = "";
		
		try{			
			Context ctx		 = new Context();				
			ctx.set("id", id);	
			ctx.set("erroMsg", erroMsg);	
			ctx.set("warnMsg", warnMsg);
			return sm.getEncodedBody("CreatePlayer.st",  ctx);			
		}
		catch(Exception e){
			log.warn("Error in Convertor: ", e);
		}
		
		return value;		
	}	
	
	public static String player(Player player,int exp){		
		String value = "";
		
		try{			
			Context ctx		 = new Context();
			ctx.set("exp", exp);	
			ctx.set("player", player);					
			return sm.getEncodedBody("Player.st",  ctx);			
		}
		catch(Exception e){
			log.warn("Error in Convertor: ", e);
		}
		
		return value;
	}
	public static String playerInfo(Player player,  @SuppressWarnings("rawtypes") List news ,int top){		
		String value = "";
		
		try{			
			Context ctx		 = new Context();				
			ctx.set("player", player);					
			ctx.set("news", news);
			ctx.set("top", top);
			ctx.set("buffList", player.getBuffList());
			return sm.getEncodedBody("PlayerInfo.st",  ctx);			
		}
		catch(Exception e){
			log.warn("Error in Convertor: ", e);
		}
		
		return value;
	}
	
	public static String playerInfoEdit(Player player,  List<SysIcon> iconList ,String[] titleList,List<PlayerAchievement> resultList, int type, int totalPage){		
		String value = "";
		
		try{			
			Context ctx		 = new Context();				
			ctx.set("player", player);					
			ctx.set("iconList", iconList);
			//ctx.set("titleList", titleList);
			ctx.set("type", type);
			ctx.set("totalPage", totalPage);
			ctx.set("achievementList", resultList);
			
			return sm.getEncodedBody("PlayerInfoEdit.st",  ctx);			
		}
		catch(Exception e){
			log.warn("Error in Convertor: ", e);
		}
		
		return value;
	}
	
	public static String playerAchievement(List<PlayerAchievement> list, int pages, int size, int complete){		
		String value = "";
		
		try{			
			Context ctx		 = new Context();				
			ctx.set("pages", pages);
			ctx.set("list", list);
			ctx.set("size", size);
			ctx.set("complete", complete);
			return sm.getEncodedBody("PlayerAchievement.st",  ctx);			
		}
		catch(Exception e){
			log.warn("Error in Convertor: ", e);
		}
		
		return value;
	}
	
	public static String fontPlayerInfo(Player player,int emailNum,
			int teamRequestNum,String erroMsg,int card,PlayerInfo playerInfo,int top, float winRate,String team,int maxLevel,int vipLeftMins,int currentyEXPItem){		
		String value = "";
		
		try{			
			Context ctx		 = new Context();				
//			ctx.set("newGP", player.getGPoint());					
//			ctx.set("newCR",player.getCredit());
//			ctx.set("newV",player.getVoucher());
			ctx.set("team", team);
			ctx.set("winRate", winRate);
			ctx.set("maxFightNum", player.getMaxFightNum());
			ctx.set("top",top);
			ctx.set("player", player);
			ctx.set("playerInfo", playerInfo);
			ctx.set("buffList", player.getBuffList());
			ctx.set("emailNum", emailNum);
			ctx.set("teamRequestNum", teamRequestNum);
			ctx.set("card", card);
			ctx.set("maxLevel", maxLevel);
			ctx.set("erroMsg", erroMsg);
			ctx.set("vipLeftMins", vipLeftMins);
			ctx.set("currentyEXPItem", currentyEXPItem);
			return sm.getEncodedBody("FontPlayerInfo.st",  ctx);			
		}
		catch(Exception e){
			log.warn("Error in Convertor: ", e);
		}
		
		return value;
	}
	
	public static String playerAvatar(Player player, PlayerItem playerItem){		
		String value = "";
//		String[] cosT = player.getResourceT().split(Constants.DELIMITER_RESOURCE_STABLE);
//		String[] cosP = player.getResourceP().split(Constants.DELIMITER_RESOURCE_STABLE);
//		String[] wpStable = new String[1];
//		ArrayList<String> wpChange = null;
//		
//		if(playerItem != null){
//			wpStable = playerItem.getSysItem().getResourceStable().split(Constants.DELIMITER_RESOURCE_STABLE);
//			wpChange = playerItem.getResources();
//		} 
//		
//		try{			
//			Context ctx		 = new Context();				
//			ctx.set("player", player);
//			ctx.set("playerItem", playerItem);		
//			ctx.set("cosT", player.getCostumeT());
//			ctx.set("cosP", player.getCostumeP());
//			ctx.set("cos0", player.getCostume0());
//			ctx.set("cos1", player.getCostume1());
//			ctx.set("wpStable", wpStable);
//			ctx.set("wpChange", wpChange);			
//			return sm.getEncodedBody("PlayerAvatar.st",  ctx);			
//		}
//		catch(Exception e){
//			log.error("Error in Convertor: ", e);
//		}
		return value;
	}		
	
	
	public static String simplePlayerAvatar(Player player){
		String value = "";
		try{			
			Context ctx		 = new Context();				
			ctx.set("player", player);
			
			return sm.getEncodedBody("SimplePlayerAvatar.st",  ctx);			
		}
		catch(Exception e){
			log.warn("Error in Convertor: ", e);
		}
		return value;
	}
	public static String sysCharacterList(List<SysCharacter> list){
		String value = "";
		try{			
			Context ctx		 = new Context();				
			ctx.set("list", list);
			
			return sm.getEncodedBody("SysCharacterList.st",  ctx);			
		}
		catch(Exception e){
			log.warn("Error in Convertor: ", e);
		}
		return value;
	}
	
	public static String playerList(List<Player> players,int cost){		
		String value = "";
		
		try{			
			Context ctx		 = new Context();				
			ctx.set("players", players);
			ctx.set("cost", cost);
			return sm.getEncodedBody("PlayerList.st",  ctx);			
		}
		catch(Exception e){
			log.warn("Error in Convertor: ", e);
		}
		
		return value;
	}
	
	public static String playerPageList(Integer pages, List<Player> players){		
		String value = "";
		
		try{			
			Context ctx		 = new Context();	
			ctx.set("pages", pages);
			ctx.set("players", players);					
			return sm.getEncodedBody("PlayerPageList.st",  ctx);			
		}
		catch(Exception e){
			log.warn("Error in Convertor: ", e);
		}
		
		return value;
	}	
	
	public static String friendAllList(List<Friend> friends,List<Friend> blackList){		
		String value = "";
		
		try{			
			Context ctx		 = new Context();				
			ctx.set("friends", friends);
			ctx.set("blackList", blackList);
			return sm.getEncodedBody("FriendList.st",  ctx);			
		}
		catch(Exception e){
			log.warn("Error in Convertor: ", e);
		}
		
		return value;
	}
	public static String partnerList(List<Player> friendPartners){		
		String value = "";
		
		try{			
			Context ctx		 = new Context();				
			ctx.set("friendPartners", friendPartners);
			return sm.getEncodedBody("PartnerList.st",  ctx);			
		}
		catch(Exception e){
			log.warn("Error in Convertor: ", e);
		}
		
		return value;
	}
	
	public static String requestList(List<Friend> friends,List<Friend> groupList){		
		String value = "";
		
		try{			
			Context ctx		 = new Context();				
			ctx.set("friends", friends);
			ctx.set("groupList", groupList);
			return sm.getEncodedBody("RequestFriendList.st",  ctx);			
		}
		catch(Exception e){
			log.warn("Error in Convertor: ", e);
		}
		
		return value;
	}
	
	public static String groupList(List<Friend> myGroup,List<List<Friend>> addGroups){		
		String value = "";
		
		try{			
			Context ctx		 = new Context();				
			ctx.set("myGroup", myGroup);
			ctx.set("addGroups", addGroups);
			return sm.getEncodedBody("GroupList.st",  ctx);			
		}
		catch(Exception e){
			log.warn("Error in Convertor: ", e);
		}
		
		return value;
	}
	
	public static String friendList(List<Friend> friends){		
		String value = "";
		
		try{			
			Context ctx		 = new Context();				
			ctx.set("friends", friends);					
			return sm.getEncodedBody("FriendList.st",  ctx);			
		}
		catch(Exception e){
			log.warn("Error in Convertor: ", e);
		}
		
		return value;
	}	
	
	
	public static String channelList(List<Channel> channel){		
		String value = "";
		
		try{			
			Context ctx		 = new Context();				
			ctx.set("channelList", channel);					
			return sm.getEncodedBody("ChannelList.st",  ctx);			
		}
		catch(Exception e){
			log.warn("Error in Convertor: ", e);
		}
		
		return value;
	}
	public static String serverList(List<Server> servers){		
		String value = "";
		
		try{			
			Context ctx		 = new Context();				
			ctx.set("serverList", servers);					
			return sm.getEncodedBody("ServerList.st",  ctx);			
		}
		catch(Exception e){
			log.warn("Error in Convertor: ", e);
		}
		
		return value;
	}
	public static String rankList(List<Rank> ranks){		
		String value = "";
		
		try{			
			Context ctx		 = new Context();				
			ctx.set("rankList", ranks);					
			return sm.getEncodedBody("RankList.st",  ctx);			
		}
		catch(Exception e){
			log.warn("Error in Convertor: ", e);
		}
		
		return value;
	}
	public static String sysItemList(int page,int pages,List<SysItem> list,int rank){		
		String value = "";
		
		try{			
			Context ctx		 = new Context();
			ctx.set("page", page);
			ctx.set("pages", pages);
			ctx.set("list", list);					
			//TODO remove un-used code later	
			ctx.set("gp", 0);
			ctx.set("cr", 0);
			ctx.set("rank", rank);
			return sm.getEncodedBody("SysItemList.st",  ctx);			
		}
		catch(Exception e){
			log.warn("Error in Convertor: ", e);
		}
		
		return value;
	}
	public static String sysItemEntity(SysItem theItem){		
		String value = "";
		try{			
			Context ctx		 = new Context();
			ctx.set("theItem", theItem);
			return sm.getEncodedBody("SysItemEntity.st",  ctx);			
		}
		catch(Exception e){
			log.warn("Error in Convertor: ", e);
		}
		return value;
	}
	
	/**
	 * @param 当前页，总页，等级，勋章数量，复活币数量
	 * */
	public static String exchangeSysItemList(int page,int pages,List<SysItem> list,int rank,int chipNum,int reviceCoinNum){
		String value = "";
		try{
			Context ctx		 = new Context();
			ctx.set("page", page);
			ctx.set("pages", pages);
			ctx.set("list", list);					
			ctx.set("rank", rank);
			ctx.set("chipNum", chipNum);
			ctx.set("reviceCoinNum", reviceCoinNum);
			return sm.getEncodedBody("ExchangeSysItemList.st",  ctx);
		}catch(Exception e){
			log.warn("Error in Convertor: ", e);
		}
		
		return value;
	}
	public static String zyzdzExchangeItemList(int page,int pages,List<SysItem> list,int teamLevel,int pRes){
		String value = "";
		try{
			Context ctx		 = new Context();
			ctx.set("page", page);
			ctx.set("pages", pages);
			ctx.set("list", list);					
			ctx.set("teamLevel", teamLevel);
			ctx.set("pRes", pRes);
			return sm.getEncodedBody("ZyzdzExchangeItemList.st",  ctx);
		}catch(Exception e){
			log.warn("Error in Convertor: ", e);
		}
		
		return value;
	}
	public static String sysItemTooltip(SysItem sysItem,String prices[], List<SysItem> packages){		
		String value = "";
		try{			
			Context ctx		 = new Context();
			ctx.set("sysItem", sysItem);
			ctx.set("prices", prices);
			ctx.set("packages", packages);
			value= sm.getEncodedBody("SysItemTooltip.st",  ctx);			
		}
		catch(Exception e){
			log.warn("Error in Convertor: ", e);
		}
		
		return value;
	}
	public static String sysItem(SysItem sysItem){		
		String value = "";
		String[] wpStable = new String[1];
		String[] wpChange = new String[1];
		
		if(sysItem != null){
			wpStable = sysItem.getResourceStable().split(Constants.DELIMITER_RESOURCE_STABLE);
			wpChange = sysItem.getResourceChangeable().split(Constants.DELIMITER_RESOURCE_CHANGEABLE);
		} 
		
		try{			
			Context ctx		 = new Context();
			ctx.set("sysItem", sysItem);
		
			ctx.set("wpStable", wpStable);
			ctx.set("wpChange", wpChange);		
			return sm.getEncodedBody("SysItem.st",  ctx);			
		}
		catch(Exception e){
			log.warn("Error in Convertor: ", e);
		}
		
		return value;
	}
	public static String brandOpen(int index,OnlineAward oa){		
		try{			
			Context ctx		 = new Context();
			ctx.set("index", index);
			ctx.set("theItem", oa);
			return sm.getEncodedBody("StageClearOpen.st",  ctx);
			
		}
		catch(Exception e){
			log.warn("Error in Convertor: ", e);
		}
		return null;
	}
	public static String playerItemList(int page,int pages,List<PlayerItem> list,int rank){		
		String value = "";
		
		try{			
			Context ctx		 = new Context();
			ctx.set("page", page);
			ctx.set("pages", pages);
			ctx.set("list", list);
			ctx.set("rank", rank);
			ctx.set("currenttime",(Calendar.getInstance().getTimeInMillis()/1000));
			return sm.getEncodedBody("PlayerItemList.st",  ctx);			
		}
		catch(Exception e){
			log.warn("Error in Convertor: ", e);
		}
		
		return value;
	}
	
	/**
	 * @param list 该角色所有武器 params升星经验、等级、每日一次、需要C币
	 * */
	public static String combineItemList(int page,int pages,List<PlayerItem> list,int rank,List<String> params){		
		String value = "";
		
		try{			
			Context ctx		 = new Context();
			ctx.set("page", page);
			ctx.set("pages", pages);
			ctx.set("list", list);
			ctx.set("rank", rank);
			ctx.set("params", params);
			return sm.getEncodedBody("CombineItemList.st",  ctx);			
		}
		catch(Exception e){
			log.warn("Error in Convertor: ", e);
		}
		
		return value;
	}
	
	/*public static String playerTop(int page,int pages,List<Player> list,String template){		
		String value = "";
		try{			
			Context ctx		 = new Context();
			ctx.set("page", page);
			ctx.set("pages", pages);
			ctx.set("list", list);	
			return sm.getEncodedBody(template,  ctx);			
		}
		catch(Exception e){
			log.error("Error in Convertor: ", e);
		}
		
		return value;
	}*/
	
	public static String playerTop(int page,long pages,List<String> list,String template,int topNum,int  leftTime,int rowNum){		
		String value = "";
		try{			
			Context ctx		 = new Context();
			
			ctx.set("leftTime", leftTime);
			ctx.set("topNum", topNum);
			ctx.set("rowNum", rowNum);
			ctx.set("page", page);
			ctx.set("pages", pages);
			ctx.set("list", list);	
			return sm.getEncodedBody(template,  ctx);			
		}
		catch(Exception e){
			log.warn("Error in Convertor: ", e);
		}
		
		return value;
	}
	
	
	public static String teamTop(int page,long pages,List<String> entryList,int mytid,int selectIndex){		
		String value = "";
		
		try{			
			Context ctx		 = new Context();
			ctx.set("page", page);
			ctx.set("pages", pages);
			ctx.set("list", entryList);	
			ctx.set("mytid", mytid);	
			ctx.set("index", selectIndex);	
			return sm.getEncodedBody("TeamTop.st",  ctx);			
		}
		catch(Exception e){
			log.warn("Error in Convertor: ", e);
		}
		
		return value;
	}
	
	public static String teamApproveResult(List<String> successNameList,List<String> sizeNameList, List<String> expireNameList){		
		String value = "";
		try{			
			Context ctx		 = new Context();
			ctx.set("successNameList", successNameList);
			ctx.set("sizeNameList", sizeNameList);
			ctx.set("expireNameList", expireNameList);	
			return sm.getEncodedBody("TeamApproveResult.st",  ctx);			
		}
		catch(Exception e){
			log.warn("Error in Convertor: ", e);
		}
		
		return value;
	}
	
	
	
//	public static String playerPartList(int page,int pages,List<PlayerItem> list,PlayerItem defaultItem){		
//		String value = "";
//		
//		try{			
//			Context ctx		 = new Context();
//			ctx.set("page", page);
//			ctx.set("pages", pages);
//			ctx.set("list", list);	
//			ctx.set("defaultItem", defaultItem);
//			return sm.getEncodedBody("PlayerPartList.st",  ctx);			
//		}
//		catch(Exception e){
//			log.error("Error in Convertor: ", e);
//		}
//		
//		return value;
//	}
//	public static String weaponPackList(int page,int pages,List<PlayerItem> list,int rank){		
//		String value = "";
//		
//		try{			
//			Context ctx		 = new Context();
//			ctx.set("page", page);
//			ctx.set("pages", pages);
//			ctx.set("list", list);
//			ctx.set("rank", rank);
//			return sm.getEncodedBody("WeaponPackList.st",  ctx);			
//		}
//		catch(Exception e){
//			log.error("Error in Convertor: ", e);
//		}
//		
//		return value;
//	}
	public static String activityList(List<SysActivity> list){		
		String value = "";
		
		try{			
			Context ctx		 = new Context();
			ctx.set("list", list);
			return sm.getEncodedBody("SysActivityList.st",  ctx);			
		}
		catch(Exception e){
			log.warn("Error in Convertor: ", e);
		}
		
		return value;
	}

	public static String playerItem(PlayerItem playerItem,SysItem sysitem){		
		String value = "";
		String[] wpStable = new String[12];
		ArrayList<String> wpChange =new ArrayList<String>();
		if(playerItem != null){
			
			wpStable = sysitem.getResourceStable().split(Constants.DELIMITER_RESOURCE_STABLE);
			wpChange = playerItem.getResources();
		}
		try{			
			Context ctx		 = new Context();
			ctx.set("playerItem", playerItem);
			ctx.set("wpStable", wpStable);
			ctx.set("wpChange", wpChange);
			return sm.getEncodedBody("PlayerItem.st",  ctx);			
		}
		catch(Exception e){
			log.warn("Error in Convertor: ", e);
		}
		
		return value;
	}
	
	public static String playerPackList(List<Character> list){		
		String value = "";
		
		try{			
			Context ctx		 = new Context();
			ctx.set("list", list);
			return sm.getEncodedBody("PlayerPack.st",  ctx);			
		}
		catch(Exception e){
			log.warn("Error in Convertor: ", e);
		}
		
		return value;
	}
	public static String playerCharacterList(List<Character> list,String selectIds,int size,int openSize,List<Character> bioListAndState,
			List<SysItem> bioItemList){		
		String value = "";
		
		try{			
			Context ctx		 = new Context();
			ctx.set("list", list);
			ctx.set("selectIds", selectIds);
			ctx.set("size", size);
			ctx.set("openSize", openSize);
			//for bio character
			ctx.set("bioCharList", bioListAndState);
			ctx.set("bioItemList", bioItemList);
			return sm.getEncodedBody("PlayerCharacterList.st",  ctx);			
		}
		catch(Exception e){
			log.warn("Error in Convertor: ", e);
		}
		
		return value;
	}
	
	public static String playerCharDataList(List<CharacterData> list){		
		String value = "";
		
		try{			
			Context ctx		 = new Context();
			ctx.set("list", list);
			return sm.getEncodedBody("CharDataList.st",  ctx);			
		}
		catch(Exception e){
			log.warn("Error in Convertor: ", e);
		}
		
		return value;
	}
	
	public static String playerPack(Character character,String name){		
		String value = "";
		
		try{			
			Context ctx		 = new Context();
			ctx.set("fightnum", character.getFightNum());
			ctx.set("character", character);
			ctx.set("name", name);
			return sm.getEncodedBody("PlayerPack.st",  ctx);			
		}
		catch(Exception e){
			log.warn("Error in Convertor: ", e);
		}
		
		return value;
	}
	
	public static String characterTeamInfo(Player player,int rankInTop,Character character,Team team){
		String value = "";
		
		try{			
			Context ctx		 = new Context();
			ctx.set("fightnum", character.getFightNum());
			ctx.set("player", player);
			ctx.set("rankInTop", rankInTop);
			ctx.set("team", team);
			ctx.set("character", character);
			return sm.getEncodedBody("CharacterTeamInfo.st",  ctx);			
		}
		catch(Exception e){
			log.warn("Error in Convertor: ", e);
		}
		
		return value;
	}
	public static String createPackEquipment(String erroMsg){		
		String value = "";
		
		try{			
			Context ctx		 = new Context();
			ctx.set("erroMsg", erroMsg);
			return sm.getEncodedBody("CreatePackEquipment.st",  ctx);			
		}
		catch(Exception e){
			log.warn("Error in Convertor: ", e);
		}
		
		return value;
	}
	public static String createItemMod(String erroMsg,String desc){		
		String value = "";
		
		try{			
			Context ctx		 = new Context();
			ctx.set("erroMsg", erroMsg);
			ctx.set("desc", desc);
			return sm.getEncodedBody("CreateItemMod.st",  ctx);			
		}
		catch(Exception e){
			log.warn("Error in Convertor: ", e);
		}
		
		return value;
	}
	public static String deletePackEquipment(String erroMsg){		
		String value = "";
		
		try{			
			Context ctx		 = new Context();
			ctx.set("erroMsg", erroMsg);
			return sm.getEncodedBody("DeletePackEquip.st",  ctx);			
		}
		catch(Exception e){
			log.warn("Error in Convertor: ", e);
		}
		
		return value;
	}
	public static String createPlayerItem(int result, String erroMsg,Long... buildTimeInMill ){		
		String value = "";
		Long buildTimeLong=buildTimeInMill.length>0 ? buildTimeInMill[0] : null;
		
		try{			
			Context ctx		 = new Context();
			ctx.set("result", result);
			ctx.set("buildTime", buildTimeLong);
			if(erroMsg!=null && erroMsg.length()>0 && result==-8)
			{
				ctx.set("successMsg", erroMsg);
				ctx.set("erroMsg", "");
			}else{
				ctx.set("erroMsg", erroMsg);
			}
			
			return sm.getEncodedBody("CreatePlayerItem.st",  ctx);			
		}
		catch(Exception e){
			log.warn("Error in Convertor: ", e);
		}
		
		return value;
	}
	
	public static String createTeamItem(int result,Long buildTimeInMill,  String erroMsg){		
		String value = "";
		
		try{			
			Context ctx		 = new Context();
			ctx.set("result", result);
			ctx.set("buildTime", buildTimeInMill);
			ctx.set("erroMsg", erroMsg);
			return sm.getEncodedBody("CreateTeamItem.st",  ctx);			
		}
		catch(Exception e){
			log.warn("Error in Convertor: ", e);
		}
		
		return value;
	}	
	
	public static String createMessage(int permit, String erroMsg, int newGP, int newCR,int result){		
		String value = "";
		
		try{			
			Context ctx		 = new Context();
			ctx.set("result", result);
			ctx.set("permit", permit);
			ctx.set("erroMsg", erroMsg);
			ctx.set("gp", newGP);
			ctx.set("cr", newCR);
			return sm.getEncodedBody("CreateMessage.st",  ctx);			
		}
		catch(Exception e){
			log.warn("Error in Convertor: ", e);
		}
		
		return value;
	}
	
	public static String createMessage(int permit, String erroMsg){		
		String value = "";
		
		try{			
			Context ctx		 = new Context();
			ctx.set("permit", permit);
			ctx.set("erroMsg", erroMsg);
			ctx.set("gp", 0);
			ctx.set("cr", 0);
			return sm.getEncodedBody("CreateMessage.st",  ctx);			
		}
		catch(Exception e){
			log.warn("Error in Convertor: ", e);
		}
		return value;
	}
	
	
	
	public static String messageList(List<Message> list, int type){		
		String value = "";
		
		try{			
			Context ctx		 = new Context();
			ctx.set("messageList", list);
			ctx.set("type", type);
			
			return sm.getEncodedBody("MessageList.st",  ctx);			
		}
		catch(Exception e){
			log.warn("Error in Convertor: ", e);
		}
		
		return value;
	}
	public static String message(Message message,SysItem si){		
		String value = "";
		
		try{			
			Context ctx		 = new Context();
			ctx.set("message", message);
			ctx.set("si", si);
			ctx.set("num", message.getItemList().size());
			return sm.getEncodedBody("Message.st",  ctx);			
		}
		catch(Exception e){
			log.warn("Error in Convertor: ", e);
		}
		
		return value;
	}
	public static String characters(List<SysCharacter> characterlist){		
		String value = "";
		
		try{			
			Context ctx		 = new Context();
			ctx.set("characterList", characterlist);
			return sm.getEncodedBody("CharacterList.st",  ctx);			
		}
		catch(Exception e){
			log.warn("Error in Convertor: ", e);
		}
		
		return value;
	}
//	public static String previewItemMode(PlayerItem playerItem,List<SysItem> newList,List<String> deleteList){		
//		String value = "";
//		
//		try{			
//			Context ctx		 = new Context();
//			ctx.set("theItem", playerItem);
//			ctx.set("newList", newList);
//			ctx.set("deleteList", deleteList);
//			return sm.getEncodedBody("PreviewItemMode.st",  ctx);			
//		}
//		catch(Exception e){
//			log.error("Error in Convertor: ", e);
//		}
//		
//		return value;
//	}
	public static String usePlayerItem(List<SysItem> sysitemList,int cal){		
		String value = "";
		try{			
			Context ctx		 = new Context();
			ctx.set("cal", cal);
			ctx.set("sysitemList", sysitemList);
			return sm.getEncodedBody("UsePlayerItemList.st",  ctx);			
		}
		catch(Exception e){
			log.warn("Error in Convertor: ", e);
		}
		return value;
	}
//	public static String usePlayerItemP(List<PlayerItem> list,int cal){		
//		String value = "";
//		try{			
//			Context ctx		 = new Context();
//			ctx.set("cal", cal);
//			ctx.set("list", list);
//			return sm.getEncodedBody("UsePlayerItemListP.st",  ctx);			
//		}
//		catch(Exception e){
//			log.warn("Error in Convertor: ", e);
//		}
//		return value;
//	}
	public static String commonFeedback(String erroMsg){		
		String value = "";
		try{			
			Context ctx		 = new Context();
			ctx.set("erroMsg", erroMsg);
			return sm.getEncodedBody("CommonFeedback.st",  ctx);			
		}
		catch(Exception e){
			log.warn("Error in Convertor: ", e);
		}
		return value;
	}
	
	public static String commonNoSuchTeam(){
		return  "error=\""+ExceptionMessage.ERROR_MESSAGE_NOTEAM+"\"";	
	}	
	public static String commonTeamError(String erroMsg){		
		String value = "";
		
		try{			
			Context ctx		 = new Context();
			ctx.set("erroMsg", erroMsg);
			ctx.set("arg", 0);
			return sm.getEncodedBody("CommonTeamError.st",  ctx);			
		}
		catch(Exception e){
			log.warn("Error in Convertor: ", e);
		}
		return value;
	}
	public static String commonTeamError(String erroMsg,int code){		
		String value = "";
		
		try{			
			Context ctx		 = new Context();
			ctx.set("erroMsg", erroMsg);
			ctx.set("arg", code);
			return sm.getEncodedBody("CommonTeamError.st",  ctx);			
		}
		catch(Exception e){
			log.warn("Error in Convertor: ", e);
		}
		
		return value;
	}
	public static String creatTeam(String nameStr,String descriptionStr){		
		String value = "";
		
		try{			
			Context ctx		 = new Context();
			ctx.set("nameStr", nameStr);
			ctx.set("descriptionStr", descriptionStr);
			return sm.getEncodedBody("CreateTeam.st",  ctx);			
		}
		catch(Exception e){
			log.warn("Error in Convertor: ", e);
		}
		
		return value;
	}
	public static String team(Team team, int currentNum){		
		String value = "";
		try{	
			//对描述编码
			team.setDescription(StringUtil.stringToAscii(team.getDescription()));
			Context ctx		 = new Context();
			ctx.set("team", team);
			if (team != null){
				ctx.set("createTime", new SimpleDateFormat("yyyy/MM/dd").format(team.getCreateTime()));
				ctx.set("currentMembers", currentNum);
			}
			return sm.getEncodedBody("Team.st",  ctx);			
		}
		catch(Exception e){
			log.warn("Error in Convertor: ", e);
		}
		
		return value;
	}
	public static String teamProclamation(List<TeamProclamation> list){		
		String value = "";
		try{	
			//对描述编码
			//team.setDescription(StringUtil.stringToAscii(team.getDescription()));
			Context ctx= new Context();
			ctx.set("list", list);
			return sm.getEncodedBody("TeamProclamationList.st",  ctx);			
		}
		catch(Exception e){
			log.warn("Error in Convertor: ", e);
		}
		
		return value;
	}
	/**
	 * 
	 * @param list
	 * @return
	 */
	public static String teamRecord(List<TeamRecord> list){		
		String value = "";
		try{	
			//对描述编码
			//team.setDescription(StringUtil.stringToAscii(team.getDescription()));
			Context ctx= new Context();
			ctx.set("list", list);
			return sm.getEncodedBody("TeamRecordList.st",  ctx);			
		}
		catch(Exception e){
			log.warn("Error in Convertor: ", e);
		}
		
		return value;
	}
	
	/**
	 * 
	 * @param list
	 * @return
	 */
	public static String teamResRecord(List<ResRecord> list){		
		String value = "";
		try{	
			//对描述编码
			//team.setDescription(StringUtil.stringToAscii(team.getDescription()));
			Context ctx= new Context();
			ctx.set("list", list);
			return sm.getEncodedBody("TeamResRecordList.st",  ctx);			
		}
		catch(Exception e){
			log.warn("Error in Convertor: ", e);
		}
		
		return value;
	}	
	public static String myTeam(Team team,PlayerTeam pt, String teamApplidName, int currentNum, String leaderName,int num){		
		String value = "";
		
		try{			
			Context ctx		 = new Context();
			if(null != pt && pt.getApproved().equals(Constants.BOOLEAN_NO)){
				team = null;
			}
			//对描述编码
			if(null != team){
				team.setDescription(StringUtil.stringToAscii(team.getDescription()));
			}
			ctx.set("team", team);
			ctx.set("num", num);
			ctx.set("application", pt);
			ctx.set("teamName", teamApplidName);
			if(pt != null)
				ctx.set("job", pt.getJob());
			if (team != null){
				ctx.set("createTime", new SimpleDateFormat("yyyy/MM/dd").format(team.getCreateTime()));
				ctx.set("currentMembers", currentNum);
				ctx.set("leaderName", leaderName);
			}
			return sm.getEncodedBody("MyTeam.st",  ctx);			
		}
		catch(Exception e){
			log.warn("Error in Convertor: ", e);
		}
		
		return value;
	}
	public static String teamList(List<Team> teamList,int page,int pages){		
		String value = "";
		
		try{			
			Context ctx		 = new Context();
			ctx.set("teamList", teamList);
			ctx.set("page", page);
			ctx.set("pages", pages);
			return sm.getEncodedBody("TeamList.st",  ctx);			
		}
		catch(Exception e){
			log.warn("Error in Convertor: ", e);
		}
		
		return value;
	}
	public static String teamList(Team teama,Team teamb){		
		String value = "";
		
		try{			
			Context ctx		 = new Context();
			ctx.set("teama", teama);
			ctx.set("teamb", teamb);
			return sm.getEncodedBody("TeamSimpleList.st",  ctx);			
		}
		catch(Exception e){
			log.warn("Error in Convertor: ", e);
		}
		
		return value;
	}
	public static String teamMember(Team team, int page, int pages){		
		String value = "";
		
		try{			
			Context ctx		 = new Context();
			ctx.set("team", team);
			ctx.set("page", page);
			ctx.set("pages", pages);
			return sm.getEncodedBody("TeamMember.st",  ctx);			
		}
		catch(Exception e){
			log.warn("Error in Convertor: ", e);
		}
		return value;
	}
	
	public static String teamMemberAll(Team team){		
		String value = "";
		try{			
			Context ctx		 = new Context();
			ctx.set("team", team);
			ctx.set("num", team.getMemberList().size());
			return sm.getEncodedBody("TeamMemberAll.st",  ctx);			
		}
		catch(Exception e){
			log.warn("Error in Convertor: ", e);
		}
		return value;
	}
	
	public static String teamHistory(List<TeamBattleHistory> teamHistoryList){		
		String value = "";
		
		try{			
			Context ctx		 = new Context();
			ctx.set("teamHistoryList", teamHistoryList);
			return sm.getEncodedBody("TeamHistory.st",  ctx);			
		}
		catch(Exception e){
			log.warn("Error in Convertor: ", e);
		}
		return value;
	}
	
	public static String teamAlly(Team team){		
		String value = "";
		
		try{			
			Context ctx		 = new Context();
			ctx.set("team", team);
			return sm.getEncodedBody("TeamAlly.st",  ctx);			
		}
		catch(Exception e){
			log.warn("Error in Convertor: ", e);
		}
		return value;
	}
	
	public static String teamRequestList(List<PlayerTeam> list,int page,int pages){		
		String value = "";
		
		try{			
			Context ctx		 = new Context();
			ctx.set("list", list);
			ctx.set("page", page);
			ctx.set("pages", pages);
			return sm.getEncodedBody("TeamRequestList.st",  ctx);			
		}
		catch(Exception e){
			log.warn("Error in Convertor: ", e);
		}
		return value;
	}
	
	
	
	public static String stageClear(GeneralStageClearInfo stageClear,Player mvpPlayer,PlayerItem mvpWeapon){
		String value = "";
		
		try{			
			Context ctx	= new Context();
			ctx.set("stageClear", stageClear);
			ctx.set("mvpPlayer", mvpPlayer);
			ctx.set("mvpWeapon", mvpWeapon);
			return sm.getEncodedBody("StageClear.st",  ctx);			
		}catch(Exception e){
			log.warn("Error in Convertor: ", e);
		}
		return value;
	}
	public static String stageQuit(GeneralStageClearInfo stageClear){
		String value = "";
		
		try{			
			Context ctx	= new Context();
			ctx.set("stageClear", stageClear);
			return sm.getEncodedBody("StageQuit.st",  ctx);			
		}catch(Exception e){
			log.warn("Error in Convertor: ", e);
		}
		return value;
	}
	

	public static String playerNews(List<BasePlayerEvent> list){
		String value = "";
		
		try{			
			Context ctx		 = new Context();
			ctx.set("list", list);
			return sm.getEncodedBody("FriendNews.st",  ctx);			
		}
		catch(Exception e){
			log.warn("Error in Convertor: ", e);
		}
		
		return value;
	}
	
	public static String completedAchievementList(List<PlayerAchievement> list,int fcm_time){
		String value = "";
		
		try{			
			Context ctx		 = new Context();
			ctx.set("list", list);
			ctx.set("time", fcm_time); //防沉迷
			return sm.getEncodedBody("AchievementCompleted.st",  ctx);			
		}
		catch(Exception e){
			log.warn("Error in Convertor: ", e);
		}
		
		return value;
	}
	
	
	/**
	 * 获得资源争夺战邀请信息
	 * @param receiver
	 * @param inviter
	 * @param serverid
	 * @param channelid
	 * @param roomid
	 * @param roompwd
	 * @return
	 */
	public static String getBattleFiledInvitation(Player receiver, Player inviter,int serverid,int channelid,int roomid,String roompwd,FightType fightType,int player_Res_ratio_status){
		String value = "";
		
		try{			
			Context ctx		 = new Context();
			ctx.set("inviter_pid", inviter.getId());
			ctx.set("inviter_name", inviter.getName());
			ctx.set("receiver_pid", receiver.getId());
			ctx.set("serverid",serverid);
			ctx.set("channelid", channelid);
			ctx.set("roomid", roomid);
			ctx.set("roompwd",roompwd);
			ctx.set("restype",fightType.getValue());
			//----------------2015年3月24号14点赵连明---------------
			ctx.set("toPlayer_Res_ratio_status",player_Res_ratio_status);
			return sm.getEncodedBody("PushBattleFiledInvitation.st",  ctx);			
		}
		catch(Exception e){
			log.warn("Error in Convertor: ", e);
		}
		
		return value;
	}	
	
	
	/**
	 * 获得资源争夺战邀请信息
	 * @param receiver
	 * @param inviter
	 * @param serverid
	 * @param channelid
	 * @param roomid
	 * @param roompwd
	 * @return
	 */
	public static String getBattleFiledInfoToAttactMember(Player receiver,Team defenceTeam,int canRobRes,String config,FightType fightType){
		String value = "";
		
		try{			
			Context ctx		 = new Context();
			ctx.set("canRobRes", canRobRes);
			ctx.set("dtname", defenceTeam.getName());
			ctx.set("dtsplevel", defenceTeam.getTeamSpaceLevel());
			ctx.set("dtconfig",config);
			ctx.set("restype",fightType.getValue());
			return sm.getEncodedBody("PushBattleFiledInfo.st",  ctx);			
		}
		catch(Exception e){
			log.warn("Error in Convertor: ", e);
		}
		
		return value;
	}		
	
	/**
	 * 获得资源争夺战防守邀请信息
	 * @param receiver
	 * @param defenseTeam
	 * @param serverid
	 * @param channelid
	 * @param roomid
	 * @param roompwd
	 * @return
	 */
	public static String getBattleFiledDefenseInvitation(Team attackTeam,Player receiver, Team defenseTeam,int serverid,int channelid,int roomid,String roompwd,FightType fightType,String config,int canRobRes,int teamSpaceLevel,int atknum){
		String value = "";
		
		try{			
			Context ctx		 = new Context();
			ctx.set("attackTeam_pid", attackTeam.getId());
			ctx.set("attackTeam_name", attackTeam.getName());	
			ctx.set("attackTeam_teamSpaceLevel", attackTeam.getTeamSpaceLevel());	
			ctx.set("defenseTeam_pid", defenseTeam.getId());
			ctx.set("defenseTeam_name", defenseTeam.getName());
			ctx.set("defenseTeam_teamSpaceLevel",teamSpaceLevel);
			ctx.set("receiver_pid", receiver.getId());
			ctx.set("serverid",serverid);
			ctx.set("channelid", channelid);
			ctx.set("roomid", roomid);
			ctx.set("roompwd",roompwd);			
			ctx.set("restype",fightType.getValue());
			ctx.set("config",config);
			ctx.set("canRobRes",canRobRes);
			ctx.set("atknum",atknum);
			return sm.getEncodedBody("PushBattleFiledDefenseInvitation.st",  ctx);			
		}
		catch(Exception e){
			log.warn("Error in Convertor: ", e);
		}
		
		return value;
	}		
	
	
	public static String missionList(List<PlayerMission> list,int awoke){
		StringBuilder value = new StringBuilder();
		try{			
			Context ctx		 = new Context();
			ctx.set("list", list);
			ctx.set("awoke", awoke);
			ctx.set("num", list.size());
			ctx.set("flag", 0);
			value.append(sm.getEncodedBody("MissionList.st",  ctx));			
		}
		catch(Exception e){
			log.warn("Error in Convertor: ", e);
		}
		return value.toString();
	}
	
	public static String completeMissionList(List<PlayerMission> list){
		StringBuilder value = new StringBuilder();
		value.append("{cmd=\"completed_mission\", ");
		try{			
			Context ctx		 = new Context();
			ctx.set("list", list);
			ctx.set("num", list.size());
			ctx.set("flag", 1);
			value.append(sm.getEncodedBody("MissionList.st",  ctx));			
		}
		catch(Exception e){
			log.warn("Error in Convertor: ", e);
		}
		value.append("}");
		return value.toString();
	}
	
	public static String completeActivity(List<PlayerActivity> list){
		StringBuilder value = new StringBuilder();
		value.append("{cmd=\"completed_activity\", ");
		try{			
			Context ctx		 = new Context();
			ctx.set("list", list);
			ctx.set("num", list.size());
			value.append(sm.getEncodedBody("ActivityList.st",  ctx));			
		}
		catch(Exception e){
			log.warn("Error in Convertor: ", e);
		}
		value.append("}");
		return value.toString();
	}
	/**
	 * 使用锦囊，礼品盒等随机礼盒获得的奖励
	 * @param list
	 * @return
	 */
	public static String useItemAwards(List<OnlineAward> list){
		StringBuilder value = new StringBuilder();
		value.append("{cmd=\"useitem_awards\", ");
		try{			
			Context ctx		 = new Context();
			ctx.set("list", list);
			value.append(sm.getEncodedBody("UseItemAwards.st",  ctx));			
		}
		catch(Exception e){
			log.warn("Error in Convertor: ", e);
		}
		value.append("}");
		return value.toString();
	}
	
	public static String newMissionList(List<PlayerMission> list){
		StringBuilder value = new StringBuilder();
		value.append("{cmd=\"new_mission\", ");
		try{			 
			Context ctx		 = new Context();
			ctx.set("list", list);
			ctx.set("num", list.size());
			ctx.set("flag", 1);
			value.append(sm.getEncodedBody("MissionList.st",  ctx));			
		}
		catch(Exception e){
			log.warn("Error in Convertor: ", e);
		}
		value.append("}");
		return value.toString();
	}
	
	public static String awardList(PlayerActivity pa){
		String value = "";
		Context ctx	= new Context();
		ctx.set("pa", pa);
		try {
			value = sm.getEncodedBody("AwardList.st",  ctx);
		} catch (TemplateException e) {
			log.warn("Error in Convertor: ", e);
		}
		return value;
	}
	public static String awardCycleMissionList(List<AwardItemVo> awardItemVos,int cal,int exp,int vipCal,int vipExp,byte upgrade,boolean isVip){
		String value = "";
		Context ctx	= new Context();
		ctx.set("awardItemVos", awardItemVos);
		ctx.set("cal", cal);
		ctx.set("exp", exp);
		ctx.set("vipCal", vipCal);
		ctx.set("vipExp", vipExp);
		ctx.set("upgrade", upgrade);
		ctx.set("isVip", isVip);
		try {
			value = sm.getEncodedBody("AwardCycleList.st",  ctx);
		} catch (TemplateException e) {
			log.warn("Error in Convertor: ", e);
		}
		return value;
	}
	
	public static String xunleiGiftCMD(List<SysItem> list){
		String value = "";
		Context ctx	= new Context();
		ctx.set("list", list);
		ctx.set("num", list.size());
		try {
			value = sm.getEncodedBody("XunLeiGift.st",  ctx);
		} catch (TemplateException e) {
			log.warn("Error in Convertor: ", e);
		}
		return value;
	}
	
	public static String keepStay(List<PlayerMission> missions, int win, int lose, int kill, int assist, int dead, int score, int exp, int gp){
		String value = "";
		Context ctx	= new Context();
		ctx.set("missions", missions);
		ctx.set("num", missions.size());
		ctx.set("win", win);
		ctx.set("lose", lose);
		ctx.set("kill", kill);
		ctx.set("assist", assist);
		ctx.set("dead", dead);
		ctx.set("score", score);
		ctx.set("exp", exp);
		ctx.set("gp", gp);
		try {
			value = sm.getEncodedBody("KeepStay.st",  ctx);
		} catch (TemplateException e) {
			log.warn("Error in Convertor: ", e);
		}
		return value;
	}
	
	public static String chestList(List<SysChest> list){
		String value = "";
		Context ctx	= new Context();
		ctx.set("list", list);
		ctx.set("num", list.size());
		try {
			value = sm.getEncodedBody("SysChestList.st",  ctx);
		} catch (TemplateException e) {
			log.warn("Error in Convertor: ", e);
		}
		return value;
	}
	
	public static String strength(int result, PlayerItem playerItem, PlayerItem[] itemList,List<SysItem> failAwards){
		String value = "";
		Context ctx	= new Context();
		ctx.set("playerItem", playerItem);
		ctx.set("itemList", itemList);
		ctx.set("result", result);
		ctx.set("failAwards", failAwards);
		try {
			value = sm.getEncodedBody("CombineStrength.st",  ctx);
		} catch (TemplateException e) {
			log.warn("Error in Convertor: ", e);
		}
		return value;
	}
	
	
	/**
	 * 随机获得vip物品
	 * @param player
	 * @return
	 */
	public static String vipRandomList(int openChanceNum,int vipNum,int needVipNum, List<OnlineAward> randomList) {
		String value = "";
	
		try{
			Context ctx		 = new Context();
			ctx.set("openChanceNum", openChanceNum);
			ctx.set("vipNum", vipNum);
			ctx.set("needVipNum", needVipNum);
			ctx.set("randomList", randomList);
			return sm.getEncodedBody("VipRandomList.st",  ctx);			
		}
		catch(Exception e){
			log.warn("Error in Convertor: ", e);
		}
		return value;		
	}
	public static String vipRandomStart(int leftVipOpenChanceNum ,int needVipNum,int playerVipNum,int randomNum) {
		
		String value = "";
		if(leftVipOpenChanceNum<=0){
			value=ExceptionMessage.NO_VIP_OPEN_CHANCE_MSG;
		}
		try{
			Context ctx		 = new Context();
			ctx.set("openChanceNum",leftVipOpenChanceNum);
			ctx.set("vipNum", playerVipNum);
			ctx.set("needVipNum", needVipNum);
			ctx.set("randomNum", randomNum);
			return sm.getEncodedBody("VipRandomStart.st",  ctx);			
		}
		catch(Exception e){
			log.warn("Error in Convertor: ", e);
		}
		return value;
	}
	
	public static String shootingAmmo(List<SysItem> playerITems){
		String value = "";
		try{
			Context ctx		 = new Context();
			ctx.set("playerItems",playerITems);
			return sm.getEncodedBody("ShootingAmmo.st",  ctx);			
		}
		catch(Exception e){
			log.warn("Error in Convertor: ", e);
		}
		return value ;
	}
	public static String shootingJoin(int boutNum , int dartleNum ,int ammoNum,int needDartleNum,SysItem shootingLook ,OnlineAward goldGift ,OnlineAward silverGift,List<OnlineAward> commonGifts,OnlineAward dartleGift ,List<String> dartleTopList,List<TmpPlayerItem> dartleAwardsList){
		String value = "";
		try{
			Context ctx		 = new Context();
			ctx.set("boutNum",boutNum);
			ctx.set("dartleNum",dartleNum);
			ctx.set("ammoNum",ammoNum);
			ctx.set("needDartleNum",needDartleNum);
			ctx.set("shootingLook",shootingLook);
			ctx.set("goldGift",goldGift);
			ctx.set("silverGift",silverGift);
			ctx.set("commonGifts",commonGifts);
			ctx.set("dartleGift",dartleGift);
			ctx.set("dartleTopList",dartleTopList);
			ctx.set("awardsList",dartleAwardsList);
			return sm.getEncodedBody("ShootingJoin.st",  ctx);			
		}
		catch(Exception e){
			log.warn("Error in Convertor: ", e);
		}
		return value ;
	}
	public static String shootingLook(int boutNum , int dartleNum ,int ammoNum,int lookNum,int needDartleNum,List<OnlineAward> gifts,OnlineAward dartleGift ,List<String> dartleTopList,List<TmpPlayerItem> dartleAwardsList){
		String value = "";
		try{
			Context ctx		 = new Context();
			ctx.set("boutNum",boutNum);
			ctx.set("dartleNum",dartleNum);
			ctx.set("ammoNum",ammoNum);
			ctx.set("lookNum",lookNum);
			ctx.set("needDartleNum",needDartleNum);
			ctx.set("gifts",gifts);
			ctx.set("dartleGift",dartleGift);
			ctx.set("dartleTopList",dartleTopList);
			ctx.set("awardsList",dartleAwardsList);
			return sm.getEncodedBody("ShootingLook.st",  ctx);			
		}
		catch(Exception e){
			log.warn("Error in Convertor: ", e);
		}
		return value ;
	}
	/**
	 * 翻牌结束，返回所有物品
	 * @param allRandomBrandSysItemList
	 * @return
	 */
	public static String brandOpenTimeOut(String indexs,List<OnlineAward> allRandomBrandSysItemList) {
		String value = "";
		try{
			Context ctx		 = new Context();
			ctx.set("randomList", allRandomBrandSysItemList);
			ctx.set("indexs", indexs);
			return sm.getEncodedBody("StageClearOpenTimeOut.st",  ctx);			
		}
		catch(Exception e){
			log.warn("Error in Convertor: ", e);
		}
		return value;	
	}
	/**
	 * 将玩家获得翻牌次数加到result中返回
	 * @param playerId
	 * @param result
	 * @return
	 * @throws Exception
	 */
	public static String stageClearAppendOpenChances(int playerId, String result) throws Exception {
		String awardChanceList = ServiceLocator.nosqlService.getPlayerGetChances(playerId);
		String key = Constants.STAGE_GET_CHANCE_PREFIX + playerId;
		ServiceLocator.nosqlService.deleteByKey(key);
		if(awardChanceList==null)
			return result;
		String[] awardChances = awardChanceList.split(":");
		int totalOpenChance = 0;
		for(String chance : awardChances){
			totalOpenChance+=Integer.parseInt(chance);
		}	
		result =  "totalopenchance="+totalOpenChance 
		+"\n" + "gamegetchance="+awardChances[0]
		+"\n" + "vipgetchance="+awardChances[1]
		+"\n" + "netbargetchance="+awardChances[2]
		+"\n" + "activitygetchance="+awardChances[3]+ "\n" + result;
		return result;
	}
	public static String stageClearAppendGetExps(int playerId, String result) throws Exception{
		String getExps = ServiceLocator.nosqlService.getPlayerGetExps(playerId);
		String key = Constants.STAGE_GET_EXP_PREFIX + playerId;
		ServiceLocator.nosqlService.deleteByKey(key);
		if(getExps==null)
			return result;
		String[] exps = getExps.split(":");
		
		result =  new StringBuilder().append("totalgetexp=").append(exps[0]).append("\n") 
		.append("gamegetexp=").append(exps[1]).append("\n")
		.append("vipgetexp=").append(exps[2]).append("\n")
		.append("netbargetexp=").append(exps[3]).append("\n")
		.append("activitygetexp=").append(exps[4]).append("\n")
		.append("itemgetexp=").append(exps[5]).append("\n")
		.append("teamgetexp=").append(exps[6]).append("\n")
		.append(result).toString();
		return result;
	}
	
	public static String stageClearAppendGetGps(int playerId, String result) throws Exception{
		String getGps = ServiceLocator.nosqlService.getPlayerGetGps(playerId);
		String key = Constants.STAGE_GET_GP_PREFIX + playerId;
		ServiceLocator.nosqlService.deleteByKey(key);
		if(getGps==null)
			return result;
		String[] gps = getGps.split(":");
		result =  new StringBuilder().append("totalgetgp=").append(gps[0]).append("\n") 
		.append("gamegetgp=").append(gps[1]).append("\n")
		.append("vipgetgp=").append(gps[2]).append("\n")
		.append("netbargetgp=").append(gps[3]).append("\n")
		.append("activitygetgp=").append(gps[4]).append("\n")
		.append("itemgetgp=").append(gps[5]).append("\n")
		.append("teamgetgp=").append(gps[6]).append("\n")
		.append(result).toString();
		
		return result;
	}
	public static String stageClearAppendGetScore(int playerId, String result) throws Exception{
		String getScore = ServiceLocator.nosqlService.getPlayerGetScore(playerId);
		if(getScore==null)
			return result;
		result =  "getscore="+getScore+ "\n" + result;
		return result;
	}
	public static String combineGetPrice(Integer[] prices, SysItem[] items){
		String value = "";
		Context ctx	= new Context();
		ctx.set("prices", prices);
		ctx.set("items", items);
		try {
			value = sm.getEncodedBody("CombineGetPrice.st",  ctx);
		} catch (TemplateException e) {
			log.warn("Error in Convertor: ", e);
		}
		return value;
	}
	
	public static String combineFastBuy(PlayerItem pi, int result){
		String value = "";
		Context ctx	= new Context();
		ctx.set("theItem", pi);
		ctx.set("result", result);
		try {
			value = sm.getEncodedBody("CombineFastBuy.st",  ctx);
		} catch (TemplateException e) {
			log.warn("Error in Convertor: ", e);
		}
		return value;
	}
	
	public static String combineSlotting(int result, PlayerItem pi, PlayerItem sloterItem){
		String value = "";
		Context ctx	= new Context();
		ctx.set("theItem", pi);
		ctx.set("sloterItem", sloterItem);
		ctx.set("result", result);
		try {
			value = sm.getEncodedBody("CombineSlotting.st",  ctx);
		} catch (TemplateException e) {
			log.warn("Error in Convertor: ", e);
		}
		return value;
	}
	
	public static String combineRemove(int result, PlayerItem pi){
		String value = "";
		Context ctx	= new Context();
		ctx.set("theItem", pi);
		ctx.set("result", result);
		try {
			value = sm.getEncodedBody("CombineRemove.st",  ctx);
		} catch (TemplateException e) {
			log.warn("Error in Convertor: ", e);
		}
		return value;
	}
	
	public static String magicBoxOpen(int magicBoxNum,int hummerNum,int needNum,OnlineAward oa){
		String value = "";
		Context ctx	= new Context();
		ctx.set("magicBoxNum",magicBoxNum);
		ctx.set("hummerNum",hummerNum);
		ctx.set("needNum",needNum);
		ctx.set("theItem", oa);
		try {
			value = sm.getEncodedBody("MagicBoxOpen.st",  ctx);
		} catch (TemplateException e) {
			log.warn("Error in Convertor: ", e);
		}
		return value;
	}
	
	public static String resMagicBoxOpen(OnlineAward oa,Player player,SysItemVariation sysIVa,int teamLevel){
		String value = "";
		Context ctx	= new Context();
		ctx.set("pRes", player.getLatestPlayerRes(teamLevel).get(Player.RES));
		ctx.set("cost", sysIVa.getThisCalCost());
		ctx.set("ncost", sysIVa.getNextCalCost());
		ctx.set("count", sysIVa.getCount());
		ctx.set("theItem", oa);
		try {
			value = sm.getEncodedBody("ResMagicBoxOpen.st",  ctx);
		} catch (TemplateException e) {
			log.warn("Error in Convertor: ", e);
		}
		return value;
	}	
	public static String magicBoxList(int boxNum,int hummerNum,int needNum, SysItem hummer,List<TmpPlayerItem> playerItems )  {
		String value = "";
		Context ctx	= new Context();
		ctx.set("boxNum", boxNum);
		ctx.set("hummerNum", hummerNum);
		ctx.set("needNum", needNum);
		ctx.set("hummer", hummer);
		ctx.set("playerItems", playerItems);
		try {
			value = sm.getEncodedBody("MagicBoxList.st",  ctx);
		} catch (TemplateException e) {
			log.warn("Error in Convertor: ", e);
		}
		return value;
	}
	
	public static String resMagicBoxList(Player player,SysItemVariation sysIVa,int teamLevel,SysItem sysitem)  {
		String value = "";
		Context ctx	= new Context();
		ctx.set("pRes", player.getLatestPlayerRes(teamLevel).get(Player.RES));
		ctx.set("cost", sysIVa.getThisCalCost());
		ctx.set("ncost", sysIVa.getNextCalCost());
		ctx.set("count", sysIVa.getCount());
		ctx.set("iid", sysitem.getIId());
		try {
			value = sm.getEncodedBody("ResMagicBoxList.st",  ctx);
		} catch (TemplateException e) {
			log.warn("Error in Convertor: ", e);
		}
		return value;
	}	
	
	public static String magicBoxHistoryList(List<TmpPlayerItem> playerItems )  {
		String value = "";
		Context ctx	= new Context();
		ctx.set("playerItems", playerItems);
		try {
			value = sm.getEncodedBody("MagicBoxHistoryList.st",  ctx);
		} catch (TemplateException e) {
			log.warn("Error in Convertor: ", e);
		}
		return value;
	}
	
	public static String magicBoxGiftList(int page,int isLastPage ,List<OnlineAward> playerItems )  {
		String value = "";
		Context ctx	= new Context();
		ctx.set("page", page);
		ctx.set("isLastPage", isLastPage);
		ctx.set("playerItems", playerItems);
		try {
			value = sm.getEncodedBody("MagicBoxGiftList.st",  ctx);
		} catch (TemplateException e) {
			log.warn("Error in Convertor: ", e);
		}
		return value;
	}
	public static String combineInsert(int result, PlayerItem pi, String msg){
		String value = "";
		Context ctx	= new Context();
		ctx.set("theItem", pi);
		ctx.set("result", result);
		ctx.set("msg", msg);
		try {
			value = sm.getEncodedBody("CombineInsert.st",  ctx);
		} catch (TemplateException e) {
			log.warn("Error in Convertor: ", e);
		}
		return value;
	}
	
	public static String combineConvert(int rate,int result, PlayerItem from, PlayerItem to,int rateFrom,int rateTo){
		String value = "";
		Context ctx	= new Context();
		ctx.set("fromItem", from);
		ctx.set("toItem", to);
		ctx.set("result", result);
		ctx.set("rate", rate);
		ctx.set("rateFrom", rateFrom);
		ctx.set("rateTo", rateTo);
		try {
			value = sm.getEncodedBody("CombineConvert.st",  ctx);
		} catch (TemplateException e) {
			log.warn("Error in Convertor: ", e);
		}
		return value;
	}
	public static String combineConvertPer(int rate ,PlayerItem from, PlayerItem to,int rateFrom,int rateTo){
		String value = "";
		Context ctx	= new Context();
		ctx.set("rate", rate);
		ctx.set("fromItem", from);
		ctx.set("toItem", to);
		ctx.set("rateFrom", rateFrom);
		ctx.set("rateTo", rateTo);
		try {
			value = sm.getEncodedBody("CombineConvertPer.st",  ctx);
		} catch (TemplateException e) {
			log.warn("Error in Convertor: ", e);
		}
		return value;
	}
	
	public static String combineTwoToOne(PlayerItem item){
		String value = "";
		Context ctx	= new Context();
		ctx.set("mainItem", item);
		try {
			value = sm.getEncodedBody("CombineTwoToOne.st",  ctx);
		} catch (TemplateException e) {
			log.warn("Error in Convertor: ", e);
		}
		return value;
	}
	
	public static String getModuleStatus(ModuleStatus mission,ModuleStatus dailyMission,ModuleStatus compose,ModuleStatus online,ModuleStatus present,ModuleStatus dailyCheck,ModuleStatus freeChange){
		char[] status = new char[7];
		status[0] = mission.getCh();
		status[1] = dailyMission.getCh();
		status[2] = compose.getCh();
		status[3] = online.getCh();
		status[4] = present.getCh();
		status[5] = dailyCheck.getCh();
		status[6] = freeChange.getCh();
		return getModuleStatus(status);
	}
	public static String getModuleStatus(char[] status){
		String value = "";
		Context ctx	= new Context();
		ctx.set("mission", status[0]);
		ctx.set("dailyMission", status[1]);
		ctx.set("compose", status[2]);
		ctx.set("online", status[3]);
		ctx.set("present", status[4]);
		ctx.set("dailyCheck", status[5]);
		ctx.set("freeChange", status[6]);
		ctx.set("shot", status[7]);
		try {
			value = sm.getEncodedBody("ModuleStatus.st",  ctx);
		} catch (TemplateException e) {
			log.warn("Error in Convertor: ", e);
		}
		return value;
	}
	public static String getModuleStatusChangePush(int num , char[] status){
		String value = "";
		Context ctx	= new Context();
		ctx.set("num", num);
		ctx.set("mission", status[0]);
		ctx.set("dailyMission", status[1]);
		ctx.set("compose", status[2]);
		ctx.set("online", status[3]);
		ctx.set("present", status[4]);
		ctx.set("dailyCheck", status[5]);
		ctx.set("freeChange", status[6]);
		ctx.set("shot", status[7]);
		try {
			value = sm.getEncodedBody("ModuleStatusChangePush.st",  ctx);
		} catch (TemplateException e) {
			log.warn("Error in Convertor: ", e);
		}
		return value;
	}
	/**
	 * 发送一个cmd告诉客户端需要更新
	 * @param num
	 * @param status
	 * @return
	 */
	public static String needRefreshProclamation(int num){
		String value = "";
		Context ctx	= new Context();
		ctx.set("num", num);
		try {
			value = sm.getEncodedBody("SendTeamNew.st",  ctx);
		} catch (TemplateException e) {
			log.warn("Error in Convertor: ", e);
		}
		return value;
	}
	public static String getGrowthMissionList(List<GrowthMissionVo> growthMissions,int awoke,int pageNo,int pageCount){
		String value = "";
		Context ctx	= new Context();
		ctx.set("page", pageNo);
		ctx.set("pages", pageCount);
		ctx.set("num", growthMissions.size());
		ctx.set("awoke", awoke);
		ctx.set("missions", growthMissions);
		try {
			value = sm.getEncodedBody("GrowthMissionList.st",  ctx);
		} catch (TemplateException e) {
			log.warn("Error in Convertor: ", e);
		}
		return value;
	}
	public static String getGrowthMissionGift(GrowthMissionVo growthMission){
		String value = "";
		Context ctx	= new Context();
		ctx.set("mi", growthMission);
		try {
			value = sm.getEncodedBody("GrowthGift.st",  ctx);
		} catch (TemplateException e) {
			log.warn("Error in Convertor: ", e);
		}
		return value;
	}
	
	public static String getMeltingInfo(PlayerMelting playerMelting){
		String value = "";
		Context ctx	= new Context();
		ctx.set("playerMelting", playerMelting);
		ctx.set("rate",  0);
		ctx.set("price", 0);
		ctx.set("result", 0);
		try {
			value = sm.getEncodedBody("MeltingInfoGet.st",  ctx);
		} catch (TemplateException e) {
			log.warn("Error in Convertor: ", e);
		}
		return value;
	}
	public static String getMeltingResult(MeltingReslut meltingReslut){
		String value = "";
		Context ctx	= new Context();
		ctx.set("meltingReslut", meltingReslut);
		try {
			value = sm.getEncodedBody("MeltingResult.st",  ctx);
		} catch (TemplateException e) {
			log.warn("Error in Convertor: ", e);
		}
		return value;
	}

	public static String smartTemplate(String view,Context model){
		String value = "";
		try {
			value = sm.getEncodedBody(view,  model);
		} catch (TemplateException e) {
			log.warn("Error in Convertor: ", e);
		}
		return value;
	}

	public static String addPingTu(String errorCode, OnlineAward micticAward) {
		String value = "";
		Context ctx	= new Context();
		ctx.set("errorCode", errorCode);
		ctx.set("misticAward", micticAward);
		try {
			value = sm.getEncodedBody("misticPinTu.st",  ctx);
		} catch (TemplateException e) {
			log.warn("Error in Convertor: ", e);
		}
		return value;
	}
	
	public static String pinTuAwards(List<OnlineAward> awards,int pageCount,int pageNo){
		String value="";
		try{
			Context ctx		 = new Context();
			ctx.set("random", awards);
			ctx.set("pageCount", pageCount);
			ctx.set("pageNo", pageNo);
			return sm.getEncodedBody("PinTuAwards.st",  ctx);
		}catch(Exception e){
			log.warn("Error in Convertor: ", e);
		}
		return value;
	}
	public static String getMeltingMenuStr(MeltingMenu menu){
		String value="";
		try{
			Context ctx		 = new Context();
			ctx.set("theItem", menu);
			return sm.getEncodedBody("BlueMapList.st",  ctx);
		}catch(Exception e){
			log.warn("Error in Convertor: ", e);
		}
		return value;
	}
	public static String getSysItemToolTips(SysItem sysItem ){
		String value="";
		try{
			Context ctx		 = new Context();
			ctx.set("theItem", sysItem);
			return sm.getEncodedBody("ItemToolTips.st",  ctx);
		}catch(Exception e){
			log.warn("Error in Convertor: ", e);
		}
		return value;
	}

	public static String dailyDiscoutItem(List<SysItem> sis, String flag, String discounts) {
		String value="";
		try{
			Context ctx		 = new Context();
			ctx.set("theItem", sis);
			ctx.set("flag", flag);
			ctx.set("discounts", discounts);
			return sm.getEncodedBody("DailyDiscountItem.st",  ctx);
		}catch(Exception e){
			log.warn("Error in Convertor: ", e);
		}
		return value;
	}

	public static String getCompeteBuyItems(String timeType,
			String retTimeStr,  String itemNums, String leastNums, String validNums,
			String mostNums, String myNums,String leftNums) {
		String value="";
		try{
			Context ctx		 = new Context();
			ctx.set("type",timeType);
			ctx.set("time", retTimeStr);
//			ctx.set("items", items);
			ctx.set("itemNums", itemNums);
			ctx.set("leastNums", leastNums);
			ctx.set("validNums", validNums);
			ctx.set("mostNums", mostNums);
			ctx.set("myNums", myNums);
			ctx.set("leftNums", leftNums);
		
			return sm.getEncodedBody("CompeteBuyItems.st",  ctx);
		}catch(Exception e){
			log.warn("Error in Convertor: ", e);
		}
		return value;
	}
	
	public static String GetCompeteBuyTime(int type,int time){
		String value="";
		try{
			Context ctx		 = new Context();
			ctx.set("type", type);
			ctx.set("time", time);

			return sm.getEncodedBody("GetCompeteBuyTime.st",  ctx);
		}catch(Exception e){
			log.warn("Error in Convertor: ", e);
		}
		return value;
	}
	
	public static String GetSecondPasswordStatus(int status,int leftSeconds){
		String value="";
		try{
			Context ctx		 = new Context();
			ctx.set("status", status);
			ctx.set("time", leftSeconds);

			return sm.getEncodedBody("GetSecondPasswordStatus.st",  ctx);
		}catch(Exception e){
			log.warn("Error in Convertor: ", e);
		}
		return value;
	}
	
	public static String GetVipInfo(int vipLevel,int currentExp, int leftMins,int nextLevelExp,SysItem vipItem,int currentyEXPItem){
		String value="";
		try{
			Context ctx		 = new Context();
			ctx.set("vipLevel", vipLevel);
			ctx.set("vipExp", currentExp);
			ctx.set("nextLevelExp", nextLevelExp);
			ctx.set("currentyEXPItem", currentyEXPItem);
			ctx.set("leftMins", leftMins);
			ctx.set("vipItem", vipItem);
			
			return sm.getEncodedBody("GetVipInfo.st", ctx);
		}catch(Exception e){
			log.warn("Error in Convertor: ", e);
		}
		
		return value;
	}
	
	/**
	 * 获得资源争夺战主页面数据
	 * @param ctx
	 * @return
	 */
	public static String GetBattlefieldView(Context ctx) {
		String value = "";
		try {
			return sm.getEncodedBody("GetBattlefieldView.st", ctx);
		} catch (Exception e) {
			log.warn("Error in Convertor: ", e);
		}

		return value;
	}
	
	/**
	 * 获得资源争夺战主页面数据
	 * @param ctx
	 * @return
	 */
	public static String GetZYZDZCurRank(List<Team> curZYZDZRank) {

		String value="";
		try{
			Context ctx		 = new Context();
			ctx.set("curZYZDZRankList", curZYZDZRank);
			
			return sm.getEncodedBody("GetZYZDZCurRank.st", ctx);
		}catch(Exception e){
			log.warn("Error in Convertor: ", e);
		}
		
		return value;
	
	}	
	

	/**
	 * 进行挑战
	 * @param ctx
	 * @return
	 */
	public static String BattlefieldChallenge(boolean result) {
		String value = "";
		try {
			Context ctx		 = new Context();
			ctx.set("result", result);
			return sm.getEncodedBody("BattlefieldChallenge.st", ctx);
		} catch (Exception e) {
			log.warn("Error in Convertor: ", e);
		}

		return value;
	}
	
	/**
	 * 获得被挑战队伍的详细信息
	 * @param ctx
	 * @return
	 */
	public static String GetChallengeInfo(TOPTeam topTeam,TeamLevelInfo teamLevelInfo ) {
		String value = "";
		try {
			Context ctx	= new Context();
			Team team=topTeam.getTeam();
			ctx.set("id", team.getId());
			ctx.set("name", team.getName());
			ctx.set("level", team.getLevel());
			ctx.set("stones", topTeam.getChallengeHillStatus().getStones());
			ctx.set("canRobStones", topTeam.getChallengeHillStatus().getCanBeRob());
			ctx.set("teamSpaceLevel", team.getTeamSpaceLevel());
			ctx.set("battleFieldId", teamLevelInfo.getId());
			ctx.set("battleFieldConfig", teamLevelInfo.getConfigPoints());
			ctx.set("formatedConfig", teamLevelInfo.getFormatedConfig());
			ctx.set("challengeCost", topTeam.getChallengeCost());
			ctx.set("ableBeChallenge", topTeam.getAbleBeChallenge());
			return sm.getEncodedBody("GetChallengeInfo.st", ctx);
		} catch (Exception e) {
			log.warn("Error in Convertor: ", e);
		}

		return value;
	}	
	
		
	
	/**
	 * 进行资源争夺战匹配
	 * 
	 * @param ctx
	 * @return
	 */
	public static String GetMatchedTeam(Team defanceTeam,Team attackTeam, TeamLevelInfo teamLevelInfo,boolean outOfCount,boolean outOfStones,int canRobUnuseRes,Player player) {
		String value = "";
		try {
			if (outOfCount) {
				Context ctx = new Context();
				ctx.set("outOfCount", "OUT_OF_COUNT");
				return sm.getEncodedBody("GetMatchedTeam.st", ctx);
			}
			
			if (outOfStones) {
				Context ctx = new Context();
				ctx.set("outOfStones", "OUT_OF_STONES");
				return sm.getEncodedBody("GetMatchedTeam.st", ctx);
			}			
			if (defanceTeam != null && !outOfCount) {
				HashMap<String,Integer> teamResHashMap=defanceTeam.getLatestTeamRes();
				HashMap<String, Integer> playerRes = player
				.getLatestPlayerRes(attackTeam.getTeamSpaceLevel());
				Context ctx = new Context();
				ctx.set("id", defanceTeam.getId());
				ctx.set("name", defanceTeam.getName());
				ctx.set("level", defanceTeam.getLevel());
				ctx.set("unusableResource", teamResHashMap.get(Team.ORG_RES));
				ctx.set("canRobUnuseRes", canRobUnuseRes);
				ctx.set("teamSpaceLevel", defanceTeam.getTeamSpaceLevel());
				ctx.set("battleFieldId", teamLevelInfo.getId());
				ctx.set("battleFieldConfig", teamLevelInfo.getConfigPoints());
				ctx.set("formatedConfig", teamLevelInfo.getFormatedConfig());
				ctx.set("pDisResStone",playerRes.get(Player.ORG_RES));
				return sm.getEncodedBody("GetMatchedTeam.st", ctx);
			}

		} catch (Exception e) {
			log.warn("Error in Convertor: ", e);
		}

		return value;
	}	
	
	
	/**
	 * 处理资源争夺战mcc中的匹配，战斗结果
	 * 
	 * @param ctx
	 * @return
	 */
	public static String GetHandledBattle(boolean success) {
		String value = "";
		try {

			Context ctx = new Context();
			if (success) {
				ctx.set("result", "ok");
			}else{
				ctx.set("result", "fail");
			}
			return sm.getEncodedBody("HandleBattlefieldBattle.st", ctx);

		} catch (Exception e) {
			log.warn("Error in Convertor: ", e);
		}

		return value;
	}
	public static String getTeamResWarInfo(long time,int storageType,int placeLevel, int isLeader, int curPage, int pages, int resoucesNum,List<TeamItem> itemList,int fc,int playerRes){
		String value = "";
		
		try{			
			Context ctx	 = new Context();
			ctx.set("placeLevel", placeLevel);
			ctx.set("time",time);
			ctx.set("isLeader", isLeader);
			ctx.set("curPage", curPage);
			ctx.set("pages", pages);
			ctx.set("resoucesNum", resoucesNum);
			ctx.set("itemList", itemList);
			ctx.set("storageType", storageType);
			ctx.set("fc", fc);
			ctx.set("playerRes", playerRes);
		//	ctx.set("holeMaxNum",Constants.HOLE_MAX_NUM);
			return sm.getEncodedBody("TeamItemList.st",  ctx);			
		}
		catch(Exception e){
			log.warn("Error in Convertor: ", e);
		}
		
		return value;
		
	}
	@SuppressWarnings("rawtypes")
	public static  String getTreeItem(Map<Integer, SysItem> map,List list,int treeType){
		String value = "";
		
		try{			
			Context ctx	 = new Context();
			ctx.set("map", map);
			ctx.set("list", list);
			ctx.set("treeType", treeType);
		
		//	ctx.set("holeMaxNum",Constants.HOLE_MAX_NUM);
			return sm.getEncodedBody("TreeItemList.st",  ctx);			
		}
		catch(Exception e){
			log.warn("Error in Convertor: ", e);
		}
		
		return value;
	}
//	public static String getTreeItem(Map<Integer, SysItem> map,List<PlayerItem> list,int treeType){
//		String value = "";
//		
//		try{			
//			Context ctx	 = new Context();
//			ctx.set("map", map);
//			ctx.set("list", list);
//			ctx.set("treeType", treeType);
//			
//			//	ctx.set("holeMaxNum",Constants.HOLE_MAX_NUM);
//			return sm.getEncodedBody("TreeItemList.st",  ctx);			
//		}
//		catch(Exception e){
//			log.warn("Error in Convertor: ", e);
//		}
//		
//		return value;
//	}
	public static String getIntensifyInfo(int res,int outOfCost,int levelChange,int intensifyLevel,int isUp,Object newTeamItem){
		String value = "";
		
		try{			
			Context ctx	 = new Context();
			ctx.set("res", res);
			ctx.set("outOfCost", outOfCost);
			ctx.set("levelChange", levelChange);
			ctx.set("intensifyLevel", intensifyLevel);
			ctx.set("newTeamItem", newTeamItem);
			ctx.set("isUp", isUp);
			
		
		//	ctx.set("holeMaxNum",Constants.HOLE_MAX_NUM);
			return sm.getEncodedBody("IntensifyInfo.st",  ctx);			
		}
		catch(Exception e){
			log.warn("Error in Convertor: ", e);
		}
		
		return value;
	}
	
	public static String getTeamPlaceUp(int level,int isCanLevelUp,long levelUpTime,int money){
		String value = "";
		
		try{			
			Context ctx	 = new Context();
			ctx.set("level", level);
			ctx.set("isCanLevelUp", isCanLevelUp);
			ctx.set("levelUpTime", levelUpTime);
			ctx.set("money", money);
		
		//	ctx.set("holeMaxNum",Constants.HOLE_MAX_NUM);
			return sm.getEncodedBody("TeamPlaceUp.st",  ctx);			
		}
		catch(Exception e){
			log.warn("Error in Convertor: ", e);
		}
		
		return value;
	}

	

	public static String getZYZDZTransform(boolean succ,int reward){
		String value = "";
		
		try{			
			Context ctx	 = new Context();
			ctx.set("succ", succ);
			ctx.set("res", reward);
		
			return sm.getEncodedBody("ZYZDZTransform.st",  ctx);			
		}
		catch(Exception e){
			log.warn("Error in Convertor: ", e);
		}
		
		return value;
	}	
	
	public static String getZYZDZFinishCosts(long [] costs){
		String value = "";
		
		try{			
			Context ctx	 = new Context();
			ctx.set("costs", costs);
		
			return sm.getEncodedBody("GETZYZDZFinishCosts.st",  ctx);			
		}
		catch(Exception e){
			log.warn("Error in Convertor: ", e);
		}
		
		return value;
	}
	
	public static String getPersonalPlace(int fc,int storageType,
			int curPage, int pages, int resoucesNum, List<PlayerItem> items) {
		String value = "";
		
		try{			
			Context ctx	 = new Context();
			ctx.set("fc", fc);
			ctx.set("curPage", curPage);
			ctx.set("pages", pages);
			ctx.set("resoucesNum", resoucesNum);
			ctx.set("itemList", items);
			ctx.set("storageType", storageType);
		
		//	ctx.set("holeMaxNum",Constants.HOLE_MAX_NUM);
			return sm.getEncodedBody("PersonalPlace.st",  ctx);			
		}
		catch(Exception e){
			log.warn("Error in Convertor: ", e);
		}
		
		return value;
	}
	
	public static String editTeamMap(String configs,int teamSpaceLevel,String name ){
		String value = "";
		try{			
			Context ctx	 = new Context();
			ctx.set("configs", configs);
			ctx.set("tsl", teamSpaceLevel);
			ctx.set("name", name);
			return sm.getEncodedBody("EditTeamMap.st",  ctx);			
		}
		catch(Exception e){
			log.warn("Error in Convertor: ", e);
		}
		return value;
	}
	//zlm 2016-01-15 qw s
	public static String getQwInfo(int type,int[] qwInfo,List<String[]> days,Integer page,Integer pageNum,List<String[]> sums){
		String value = "";
		try{			
			Context ctx	 = new Context();
			ctx.set("type", type);
			ctx.set("qwInfo", qwInfo);
/* 
scoce=#$day[0]#,积分
kill=#$day[1]#,杀
dead=#$day[2]#,死亡
assist=#$day[3]#,助攻
win=#$day[4]#,胜
fail=#$day[5]#,负
rankNum=#$day[7]#,排名
killSum=#$day[9]#,}, 总杀人
time=#$day[10]#,时间
*/
			ctx.set("days", days);
			ctx.set("page", page);
			ctx.set("pageNum", pageNum);
/*s=#$sum[0]# 积分
 * ,k=#$sum[1]# 杀
 * ,d=#$sum[2]# 死亡
 * ,w=#$sum[4]# 胜
 * ,f=#$sum[5]# 负
 * ,r=#$sum[6]# 段位
 * ,rN=#$sum[7]# 排名
 * ,n=#$sum[9]# 名字
 * ,pR=#$sum[10]# 等级
 * ,vR=#$sum[11]#, vip等级 */
			ctx.set("sums", sums);
			return sm.getEncodedBody("QwInfo.st",  ctx);			
		}
		catch(Exception e){
			log.warn("Error in Convertor: ", e);
		}
		return value;
	}
	//zlm 2016-01-15 qw e
}
