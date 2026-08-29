package com.pearl.fcw.info.core.persistence.utils;

import com.pearl.fcw.info.lobby.utils.Constants;

public class NosqlKeyUtil {

	/**
	 * -- t = kCommon(常规), kMode(模式), kJoy(趣味), kWeapon(武器专精) -- st = 1:{},
	 * 2{kTeam,kTeamDead,kExplode,kBanner,kZombie,kTreasure}
	 * 3:{1:kCr,2:kGp,3:kGrenade,4:kDrop,5:kGun,6:kReequip,7:kFlower,8:kDead}
	 * 4:{"id of activity"}
	 * 
	 * @param playerId
	 * @param typeStr
	 * @return key in nosql
	 */
	public static String selfRownumInTopByType(int playerId, String typeStr) {
		return Constants.PLAYERTOP_ROWNUM_KEY_PREFIX + typeStr + ":" + playerId;
	}

	public static String selfLevelnumInTopByType(int playerId, String typeStr) {
		return Constants.PLAYERTOP_RANK_KEY_PREFIX + typeStr + ":" + playerId;
	}

	public static String fightNumInTopByType(String typeStr) {
		return Constants.PLAYERTOP_FIGHTNUM_RANK_KEY_PREFIX + typeStr;
	}

	public static String commonLevelNumInTopByType(String typeStr) {
		return Constants.PLAYERTOP_RANK_KEY_PREFIX + typeStr;
	}

	public static String fightNumInRealTopByType(String typeStr) {
		return Constants.PLAYERTOP_FIGHTNUM_REAL_RANK_KEY_PREFIX + typeStr;
	}

	public static String commonLevelNumInRealTopByType(String typeStr) {
		return Constants.PLAYERTOP_REAL_RANK_KEY_PREFIX + typeStr;
	}

	public static String firstLastNumInTopRcdByType(String typeStr) {
		return Constants.PLAYERTOP_FST_LST_RCD_KEY_PREFIX + typeStr;
	}

	public static String weaponRecord(int activityId, int playerId,
			String playerName) {
		return Constants.PLAYERTOP_WEAPON_PREFIX + activityId + ":" + playerId
				+ ":" + playerName;
	}

	public static String FCMTime(int playerId) {
		return Constants.FCM_TIME + ":" + playerId;
	}

	public static String topByType(String typeStr) {
		return "t:" + typeStr;
	}

	public static String competeBuyBid(int awardId) {
		return Constants.COMPETE_BUY_ITEM_KEY_PREX + awardId;
	}
}
