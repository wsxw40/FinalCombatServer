package com.pearl.fcw.info.lobby.utils;

import org.eclipse.jetty.server.Authentication.User;

public class CacheUtil {


    /***
     * 定时任务，抢占令牌
     */
    public static String sScheduleToken(String taskName) {
        return Constants.STRING_TYPE + Constants.DELIMITER + "scheduleToken" + Constants.DELIMITER + taskName;
    }

    public static String getLockCacheKeyForPlayer(long playerId) {
        return Constants.SYNCHRONIZE_PREFIX + Constants.DELIMITER + User.class.getSimpleName() + Constants.DELIMITER + playerId;
    }

    public static String getLockCacheKeyForUser(long userId) {
        return Constants.SYNCHRONIZE_PREFIX + Constants.DELIMITER + User.class.getSimpleName() + Constants.DELIMITER + userId;
    }

    /**
     * 在角色上下线时更新，用于标识角色是否在线
     */
    public static String sPlayerOnlineFlag(long playerId) {
        return Constants.STRING_TYPE + Constants.DELIMITER + "PlayerOnlineFlag" + Constants.DELIMITER + playerId;
    }

    public static String[] getLockCacheKeyForPlayer(Long... playerIds) {

        String[] keys = new String[playerIds.length];

        for (int i = 0; i < playerIds.length; i++) {
            keys[i] = getLockCacheKeyForPlayer(playerIds[i]);
        }

        return keys;

    }

}
