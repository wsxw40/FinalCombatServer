package com.pearl.o2o.utils;


public class CacheUtil {
	
	public static String oPackExpiredLag(Integer playerId){
		return new StringBuilder(Constants.OBJECT_TYPE).append(Constants.STORAGE_EXPIRED_TOKEN)
				.append(Constants.DELIMITER).append(playerId)
				.toString();
	}
	
	public static String sUserCR(Integer userId){
		return new StringBuilder(Constants.STRING_TYPE).append(Constants.CR).append(Constants.DELIMITER).append(userId).toString();
	}
	
	public static String oPlayerLocation(Integer playerId)  {		
		return new StringBuilder(Constants.OBJECT_TYPE).append(Constants.PLAYER_LOCATION).append(Constants.DELIMITER).append(playerId).toString();
	}
	
	public static String sStageClear(Integer rid){
		return new StringBuilder(Constants.STRING_TYPE).append(Constants.STAGECLEAR).append(Constants.DELIMITER).append(rid).toString();
	}
	
	public static String sStageQuit(Integer rid, Integer cid){
		return new StringBuilder(Constants.STRING_TYPE).append(Constants.STAGECLEAR).append(Constants.DELIMITER).append(rid).append(cid).toString();
	}
	
	public static String oPlayer(Integer playerId)  {		
		return new StringBuilder(Constants.OBJECT_TYPE).append(Constants.PLAYER).append(Constants.DELIMITER).append(playerId).toString();
	}
	
	public static String sPlayer(Integer playerId)  {
		return new StringBuilder(Constants.STRING_TYPE).append(Constants.PLAYER).append(Constants.DELIMITER).append(playerId).toString();
	}			
	
	public static String oPlayerList(Integer userId)  {
		return new StringBuilder(Constants.OBJECT_TYPE).append(Constants.GET_PLAYER_BY_USER_ID).append(Constants.DELIMITER).append(userId).toString();
	}
	public static String sPlayerList(Integer userId) {
		return new StringBuilder(Constants.STRING_TYPE).append(Constants.GET_PLAYER_BY_USER_ID).append(Constants.DELIMITER).append(userId).toString();
	}
	
	public static String oCharacter() {
		return new StringBuilder(Constants.OBJECT_TYPE).append(Constants.DELIMITER).append(Constants.GET_CHARACTER_LIST).toString();
	}
	public static String sRankList() {
		return new StringBuilder(Constants.STRING_TYPE).append(Constants.DELIMITER).append(Constants.GET_RANK_LIST).toString();
	}
	
	
	public static String oShop(Integer type, Integer subType,String gender) {
		return new StringBuilder(Constants.OBJECT_TYPE).append(Constants.DELIMITER).append(Constants.GET_SYSTEMITEM_LIST).
			append(type).append(Constants.DELIMITER).append(subType).append(Constants.DELIMITER).append(gender).toString();
	}
	
	public static String sShop(Integer type, Integer subType,String gender) {
		return new StringBuilder(Constants.STRING_TYPE).append(Constants.DELIMITER).append(Constants.GET_SYSTEMITEM_LIST).
			append(type).append(Constants.DELIMITER).append(subType).append(Constants.DELIMITER).append(gender).toString();
	}
	
	public static String oShopPart(Integer playerId,Integer subType) {
		return new StringBuilder(Constants.OBJECT_TYPE).append(Constants.DELIMITER).append(Constants.GET_SYSTEMITEM_LIST).
			append(Constants.DEFAULT_PART_TYPE).append(Constants.DELIMITER).append(subType).append(Constants.DELIMITER).
			append(Constants.PART_FILTER).toString();
	}
	public static String sShopPart(Integer playerId,Integer subType) {
		return new StringBuilder(Constants.STRING_TYPE).append(Constants.DELIMITER).append(Constants.GET_SYSTEMITEM_LIST).
			append(Constants.DEFAULT_PART_TYPE).append(Constants.DELIMITER).append(subType).append(Constants.DELIMITER).
			append(Constants.PART_FILTER).toString();
	}
	
	
	
	public static String oStorage(Integer playerId,Integer type) {
		return new StringBuilder(Constants.OBJECT_TYPE).append(Constants.DELIMITER).append(Constants.PLAYER).append(Constants.DELIMITER)
					.append(playerId).append(Constants.DELIMITER).append(type).toString();
	}
	
	public static String sStorage(Integer playerId, Integer type) {
		return new StringBuilder(Constants.STRING_TYPE).append(Constants.DELIMITER).append(Constants.PLAYER).append(Constants.DELIMITER)
					.append(playerId).append(Constants.DOT).append(type).toString();
	}		
	
/*	public static String oWeaponPack(Integer playerId,Integer packId) {
		return new StringBuilder(Constants.OBJECT_TYPE).append(Constants.DELIMITER).append(Constants.PLAYER).append(Constants.DELIMITER)
						.append(playerId).append(Constants.DOT).append(Constants.PACK_TYPE_W).append(Constants.PACK).append(Constants.DOT).append(packId).toString();
	}*/
	
	public static String sWeaponPack(Integer playerId,Integer packId) {
		return new StringBuilder(Constants.STRING_TYPE).append(Constants.DELIMITER).append(Constants.PLAYER).append(Constants.DELIMITER)
						.append(playerId).append(Constants.DOT).append(Constants.PACK_TYPE_W).append(Constants.PACK).append(Constants.DOT).append(packId).toString();
	}
	
	public static String oWeaponsInAllPacks(Integer playerId){
		return new StringBuilder(Constants.OBJECT_TYPE).append(Constants.DELIMITER).append(Constants.PLAYER).append(Constants.DELIMITER)
				.append(playerId).append(Constants.DOT).append(Constants.PACK_TYPE_W).append(Constants.PACK).toString();
	}
	
	
	public static String oCostumePack(Integer playerId,Integer packId,Integer side) {
		return new StringBuilder(Constants.OBJECT_TYPE).append(Constants.DELIMITER).append(Constants.PLAYER).append(Constants.DELIMITER)
			.append(playerId).append(Constants.DOT).append(Constants.PACK_TYPE_C).append(Constants.PACK).append(Constants.DOT).append(side).append(Constants.DOT).append(packId).toString();
	}
	public static String sCostumePack(Integer playerId,Integer packId,Integer side){
		return new StringBuilder(Constants.STRING_TYPE).append(Constants.DELIMITER).append(Constants.PLAYER).append(Constants.DELIMITER)
			.append(playerId).append(Constants.DOT).append(Constants.PACK_TYPE_C).append(Constants.PACK).append(Constants.DOT).append(side).append(Constants.DOT).append(packId).toString();
	}
	public static String oPlayerMessage(Integer playerId){
		return new StringBuilder(Constants.OBJECT_TYPE).append(Constants.DELIMITER).append(Constants.PLAYER).append(Constants.DELIMITER)
		.append(playerId).append(Constants.DOT).append(Constants.MESSAGE).toString();
	}
	public static String sPlayerMessage(Integer playerId){
		return new StringBuilder(Constants.STRING_TYPE).append(Constants.DELIMITER).append(Constants.PLAYER).append(Constants.DELIMITER)
		.append(playerId).append(Constants.DOT).append(Constants.MESSAGE).toString();
	}
	public static String sSysItemTooltip(Integer itemId){
		return new StringBuffer(Constants.STRING_TYPE).append(Constants.DELIMITER).append(Constants.SYSTEMITEM).append(Constants.DELIMITER)
		.append(itemId).append(Constants.DOT).append(Constants.TOOLTIP).toString();
	}
}
