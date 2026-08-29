package com.pearl.manager.utils;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;


public class CacheUtil {
	public static final Joiner JoinerByUnderScore = Joiner.on('_');
	public static final Joiner JoinerByColon = Joiner.on(':');
	
	public static final Splitter splitterByUnderScore = Splitter.on('_').trimResults();
	public static final Splitter splitterByColon = Splitter.on(':').trimResults();
	
	
	public static String isRunDailyNumTask(){
		return new StringBuilder(Constants.OBJECT_TYPE).append(Constants.DELIMITER).append(Constants.DAILY_NUM_REDIS).toString();
	}
	public static String oPlayerTeam(int teamID){
		return new StringBuilder(Constants.OBJECT_TYPE).append(Constants.DELIMITER).append(Constants.PLAYER_TEAM_LIST).toString();
	}
	public static String isRunSynCacheTask(){
		return new StringBuilder(Constants.OBJECT_TYPE).append(Constants.DELIMITER).append(Constants.SYN_CACHE_DB).toString();
	}
	public static String oCharacterList(int playerId){
		return new StringBuilder(Constants.OBJECT_TYPE).append(playerId).append(Constants.CHARACTER_LIST).toString();
	}
	public static String oCharacter(int playerId){
		return new StringBuilder(Constants.OBJECT_TYPE).append(playerId).append(Constants.CHARACTER_LIST).toString();
	}
	public static String oBlackPlayerList(){
		return new StringBuilder(Constants.OBJECT_TYPE).append(Constants.BLACK_PLAYER_LIST).toString();
	}
	public static String oWhitePlayerList(){
		return new StringBuilder(Constants.OBJECT_TYPE).append(Constants.WHITE_PLAYER_LIST).toString();
	}
	public static String oSysNoticeList(){
		return new StringBuilder(Constants.OBJECT_TYPE).append(Constants.SYS_NOTICE_LIST).toString();
	}
	public static String oSysConfigMap(){
		return new StringBuilder(Constants.OBJECT_TYPE).append(Constants.SYS_CONFIG_MAP).toString();
	}
	//-------------------------------------------
	public static String oSysMissionMap(){
		return new StringBuilder(Constants.OBJECT_TYPE).append(Constants.SYS_MISSION_MAP).toString();
	}
	public static String oPlayerMissionList(int playerId){
		return new StringBuilder(Constants.OBJECT_TYPE).append(playerId).append(Constants.PLAYER_MISSION_LIST).toString();
	}
	public static String oGetAwardMap(int activityId){
		return new StringBuilder(Constants.OBJECT_TYPE).append(Constants.GET_AWARD).append(activityId).toString();
	}
	public static String oDailyMissionMap(){
		return new StringBuilder(Constants.OBJECT_TYPE).append(Constants.D_MISSION).toString();
	}
	public static String oWeekMissionMap(){
		return new StringBuilder(Constants.OBJECT_TYPE).append(Constants.W_MISSION).toString();
	}
	public static String oMonthMissionDate(int playerId){
		return new StringBuilder(Constants.OBJECT_TYPE).append(Constants.M_MISSION_DATE).append(playerId).toString();
	}
	public static String oWeekMissionDate(int playerId){
		return new StringBuilder(Constants.OBJECT_TYPE).append(Constants.W_MISSION_DATE).append(playerId).toString();
	}
	public static String oDailyMissionDate(int playerId){
		return new StringBuilder(Constants.OBJECT_TYPE).append(Constants.D_MISSION_DATE).append(playerId).toString();
	}
	public static String oGameDate(int playerId){
		return new StringBuilder(Constants.OBJECT_TYPE).append(Constants.D_GAME_DATE).append(playerId).toString();
	}
	public static String oBlackList(int playerId){
		return new StringBuilder(Constants.OBJECT_TYPE).append(playerId).append(Constants.BLACK_LIST).toString();
	}
	public static String oFriendList(int playerId){
		return new StringBuilder(Constants.OBJECT_TYPE).append(playerId).append(Constants.FRIEND_LIST).toString();
	}
	public static String oGroupList(int playerId){
		return new StringBuilder(Constants.OBJECT_TYPE).append(playerId).append(Constants.GROUP_LIST).toString();
	}
	
	public static String oReqBlackList(int playerId){
		return new StringBuilder(Constants.OBJECT_TYPE).append(playerId).append(Constants.REQ_BLACK_LIST).toString();
	}
	public static String oReqFriendList(int playerId){
		return new StringBuilder(Constants.OBJECT_TYPE).append(playerId).append(Constants.REQ_FRIEND_LIST).toString();
	}
	public static String oReqGroupList(int playerId){
		return new StringBuilder(Constants.OBJECT_TYPE).append(playerId).append(Constants.REQ_GROUP_LIST).toString();
	}
	public static String oPlayerAchievementList(int playerId){
		return new StringBuilder(Constants.OBJECT_TYPE).append(playerId).append(Constants.PLAYER_ACHIEVEMENT_LIST).toString();
	}
	public static String oSysAchievementMap(){
		return new StringBuilder(Constants.OBJECT_TYPE).append(Constants.SYS_ACHIEVEMENT_MAP).toString();
	}
	public static String oSysAchievementBaseMap(){
		return new StringBuilder(Constants.OBJECT_TYPE).append(Constants.SYS_ACHIEVEMENT_BASE_MAP).toString();
	}
	public static String oServerList(){
		return new StringBuilder(Constants.OBJECT_TYPE).append(Constants.SERVER_LIST).toString();
	}
	public static String oChannelList(int serverId){
		return new StringBuilder(Constants.OBJECT_TYPE).append(serverId).append(Constants.CHANNEL_LIST).toString();
	}
	
	public static String oLevelList(){
		return new StringBuilder(Constants.OBJECT_TYPE).append(Constants.LEVEL_LIST).toString();
	}
	public static String oBannedWords(){
		return new StringBuilder(Constants.OBJECT_TYPE).append(Constants.BANNED_WORD).toString();
	}
	public static String oBlackIpList(){
		return new StringBuilder(Constants.OBJECT_TYPE).append(Constants.BLACK_IP_LIST).toString();
	}
	public static String oSystemList(Integer type, Integer subType){
		return new StringBuilder(Constants.OBJECT_TYPE).append(Constants.SYSITEM_LIST).append(type).append(subType).toString();
	}
	public static String oPaymentList(Integer itemId){
		return new StringBuilder(Constants.OBJECT_TYPE).append(Constants.PAYMENT_LIST).append(itemId).toString();
	}

	public static String oTeam(int teamId){
		return new StringBuilder(Constants.OBJECT_TYPE).append(Constants.TEAM).append(Constants.DELIMITER).append(teamId).toString();
	}
	
	public static String oRankList(){
		 return new StringBuilder(Constants.OBJECT_TYPE).append(Constants.RANK).toString();
	}
	public static String oCurrentActivityMap(){
		return new StringBuilder(Constants.OBJECT_TYPE).append(Constants.CURRENT_ACTIVITY).toString();
	}
	
	public static String oPlayerLocation(Integer playerId)  {		
		return new StringBuilder(Constants.OBJECT_TYPE).append(Constants.PLAYER_LOCATION).append(Constants.DELIMITER).append(playerId).toString();
	}
	
	public static String sStageClear(Integer rid, Integer channelId, Integer serverId){
		return new StringBuilder(Constants.STRING_TYPE)
				.append(Constants.STAGECLEAR).append(Constants.DELIMITER)
				.append(rid).append(Constants.DELIMITER).append(channelId)
				.append(Constants.DELIMITER).append(serverId)
				.toString();
	}
	
	public static String sStageQuit(Integer rid, Integer cid, Integer channelId, Integer serverId){
		return new StringBuilder(Constants.STRING_TYPE)
			.append(Constants.STAGEQUIT).append(Constants.DELIMITER)
			.append(rid).append(Constants.DELIMITER).append(channelId)
			.append(Constants.DELIMITER).append(serverId)
			.append(Constants.DELIMITER).append(cid)
			.toString();
	}
	
	public static String oPlayer(Integer playerId)  {		
		return new StringBuilder(Constants.OBJECT_TYPE).append(Constants.PLAYER).append(Constants.DELIMITER).append(playerId).toString();
	}
	public static String oPlayerInfo(Integer playerId)  {		
		return new StringBuilder(Constants.OBJECT_TYPE).append(Constants.PLAYER_INFO).append(Constants.DELIMITER).append(playerId).toString();
	}
	public static String oOnlineAward(Integer awardId)  {		
		return new StringBuilder(Constants.OBJECT_TYPE).append(Constants.ONLINEAWARD).append(Constants.DELIMITER).append(awardId).toString();
	}
	public static String oOnlineAwardList(Integer type)  {		
		return new StringBuilder(Constants.OBJECT_TYPE).append(Constants.DELIMITER).append(Constants.ONLINEAWARD_LIST).append(Constants.DELIMITER).append(type).toString();
	}
	public static String oOnlineAwardList()  {		
		return new StringBuilder(Constants.OBJECT_TYPE).append(Constants.DELIMITER).append(Constants.ONLINEAWARD_LIST).toString();
	}
	
	public static String sPlayerIdByName(String playerName)  {		
		return new StringBuilder(Constants.STRING_TYPE).append(Constants.PLAYER).append(Constants.DELIMITER).append(playerName).toString();
	}
	
	
	
	public static String oPlayerWithRank(Integer playerId)  {		
		return new StringBuilder(Constants.OBJECT_TYPE).append(Constants.PLAYERWITHRANK).append(Constants.DELIMITER).append(playerId).toString();
	}
	
	
	public static String sPlayer(Integer playerId)  {
		return new StringBuilder(Constants.STRING_TYPE).append(Constants.PLAYER).append(Constants.DELIMITER).append(playerId).toString();
	}			
	
	public static String oSysCharacterList() {
		return new StringBuilder(Constants.OBJECT_TYPE).append(Constants.DELIMITER).append(Constants.GET_SYSCHARACTER_LIST).toString();
	}
	public static String oSysBioCharacterList() {
		return new StringBuilder(Constants.OBJECT_TYPE).append(Constants.DELIMITER).append(Constants.GET_SYSBIOCHARACTER_LIST).toString();
	}
	public static String sRankList() {
		return new StringBuilder(Constants.STRING_TYPE).append(Constants.DELIMITER).append(Constants.GET_RANK_LIST).toString();
	}
	
	public static String oTeamList() {
		return new StringBuilder(Constants.OBJECT_TYPE).append(Constants.DELIMITER).append(Constants.TEAM_LIST).toString();
	}
	public static String oPlayerActivityList(int playerId) {
		return new StringBuilder(Constants.OBJECT_TYPE).append(Constants.DELIMITER).append(Constants.PLAYER_CURRENT_ACTIVITY)
		.append(Constants.DELIMITER).append(playerId).toString();
	}
	public static String oShop(Integer type, Integer characterId) {
		return new StringBuilder(Constants.OBJECT_TYPE).append(Constants.DELIMITER).append(Constants.GET_SYSTEMITEM_LIST).
			append(type).append(Constants.DELIMITER).append(characterId).toString();
	}
	public static String oShop(Integer type) {
		return new StringBuilder(Constants.OBJECT_TYPE).append(Constants.DELIMITER).append(Constants.GET_SYSTEMITEM_LIST).
			append(type).toString();
	}
	
	public static String sShop(Integer type, Integer subType) {
		return new StringBuilder(Constants.STRING_TYPE).append(Constants.DELIMITER).append(Constants.GET_SYSTEMITEM_LIST).
			append(type).append(Constants.DELIMITER).append(subType).toString();
	}
	
	
	
	
//	public static String oStorage(Integer playerId,Integer type) {
//		return new StringBuilder(Constants.OBJECT_TYPE).append(Constants.DELIMITER).append(Constants.PLAYER).append(Constants.DELIMITER)
//					.append(playerId).append(Constants.DELIMITER).append(type).toString();
//	}
//	
//	public static String sStorage(Integer playerId, Integer type) {
//		return new StringBuilder(Constants.STRING_TYPE).append(Constants.DELIMITER).append(Constants.PLAYER).append(Constants.DELIMITER)
//					.append(playerId).append(Constants.DOT).append(type).toString();
//	}		

//	public static String oStorage(Integer playerId,Integer type,Integer characherId) {
//		return new StringBuilder(Constants.OBJECT_TYPE).append(Constants.DELIMITER).append(Constants.PLAYER).append(Constants.DELIMITER)
//					.append(playerId).append(Constants.DELIMITER).append(type).append(Constants.DELIMITER).append(characherId).toString();
//	}
	
	public static String oStorage1(Integer playerId, Integer type, Integer characherId, Integer subType) {//subtype对应客户端传过来的subtype（主武器，副武器...）
		return new StringBuilder(Constants.OBJECT_TYPE).append(Constants.DELIMITER).append(Constants.PLAYER).append(Constants.DELIMITER)
					.append(playerId).append(Constants.DELIMITER).append(type).append(Constants.DELIMITER).append(characherId).append(Constants.DELIMITER).append(subType).toString();
	}
	
//	public static String sStorage(Integer playerId,Integer type,Integer characherId) {
//		return new StringBuilder(Constants.STRING_TYPE).append(Constants.DELIMITER).append(Constants.PLAYER).append(Constants.DELIMITER)
//					.append(playerId).append(Constants.DELIMITER).append(type).append(Constants.DELIMITER).append(characherId).toString();
//	}
	
	public static String oWeaponPack(Integer playerId,Integer packId,Integer characherId) {
		return new StringBuilder(Constants.OBJECT_TYPE).append(Constants.DELIMITER).append(Constants.PLAYER).append(Constants.DELIMITER)
						.append(playerId).append(Constants.DOT).append(Constants.PACK_TYPE_W).append(Constants.PACK).append(Constants.DOT).append(packId).append(Constants.DOT).append(characherId).toString();
	}
	
//	public static String oWeaponsInAllPacks(Integer playerId){
//		return new StringBuilder(Constants.OBJECT_TYPE).append(Constants.DELIMITER).append(Constants.PLAYER).append(Constants.DELIMITER)
//				.append(playerId).append(Constants.DOT).append(Constants.PACK_TYPE_W).append(Constants.PACK).toString();
//	}
	
	
	public static String oCostumePack(Integer playerId,Integer packId,Integer characterId) {
		return new StringBuilder(Constants.OBJECT_TYPE).append(Constants.DELIMITER).append(Constants.PLAYER).append(Constants.DELIMITER)
			.append(playerId).append(Constants.DOT).append(Constants.PACK_TYPE_C).append(Constants.PACK).append(Constants.DOT).append(characterId).append(Constants.DOT).append(packId).toString();
	}
	public static String oPlayerMessage(Integer playerId){
		return new StringBuilder(Constants.OBJECT_TYPE).append(Constants.DELIMITER).append(Constants.PLAYER).append(Constants.DELIMITER)
		.append(playerId).append(Constants.DOT).append(Constants.MESSAGE).toString();
	}
	public static String sPlayerMessage(Integer playerId){
		return new StringBuilder(Constants.STRING_TYPE).append(Constants.DELIMITER).append(Constants.PLAYER).append(Constants.DELIMITER)
		.append(playerId).append(Constants.DOT).append(Constants.MESSAGE).toString();
	}
	public static String oPlayerOutBoxMessage(Integer playerId){
		return new StringBuilder(Constants.OBJECT_TYPE).append(Constants.DELIMITER).append(Constants.PLAYER).append(Constants.DELIMITER)
		.append(playerId).append(Constants.DOT).append(Constants.OUT_BOX_MESSAGE).toString();
	}
	public static String sSysItemTooltip(Integer itemId){
		return new StringBuffer(Constants.STRING_TYPE).append(Constants.DELIMITER).append(Constants.SYSTEMITEM).append(Constants.DELIMITER)
		.append(itemId).append(Constants.DOT).append(Constants.TOOLTIP).toString();
	}
	
	public static String oDefaultTeamList(){
		return new StringBuilder(Constants.OBJECT_TYPE).append(Constants.DELIMITER).append("DefaultTeamList").toString();
	}
	
	public static String oUnSpeakerList(){
		return new StringBuilder(Constants.OBJECT_TYPE).append(Constants.DELIMITER).append("UnSpeakerList").toString();
	}
	public static String sLuckyPackagePage(int level,int pageNo){
		return Constants.STRING_TYPE+Constants.DELIMITER+Constants.LUCKYPACKAGE+Constants.DELIMITER+Constants.DELIMITER+level+Constants.DOT+pageNo;
	}
	public static String sLuckyPackageRandomNames(int level){
		return Constants.STRING_TYPE+Constants.DELIMITER+Constants.LUCKYPACKAGERANDOMNAMES+Constants.DELIMITER+Constants.DELIMITER+level;
	}
	
	public static String MeltingEnergy(int playerId){
		return JoinerByUnderScore.join(MeltingConstants.Player_Melting_Energy_Num,playerId);
	}
	public static String MeltingAward(int playerId){
		return JoinerByUnderScore.join(MeltingConstants.Player_Melting_Award,playerId);
	}
}
