package com.pearl.fcw.utils;

import com.pearl.o2o.pojo.PlayerLocation;

/**
 * 和业务有直接关联的远程或者本地缓存Key
 */
public class LobbyCacheKey {

    /**
     * 获取远程缓存系统物品的每日折扣记录。该Key每天要删除<br/>
     * 对应的Value是map结构，sysItemId:<playerId,List<flag>>，flag是玩家是否已使用当天折扣的Boolean标记
     * @param sysItemId
     * @return
     */
    public String getRemoteDailyDiscountKey(int sysItemId) {
        return "PDLDCP:" + sysItemId;
    }

    /**
     * 获取远程缓存玩家房间记录
     * @param playerId
     * @return
     */
    public String getRemotePlayerLocationKey(int playerId) {
        return PlayerLocation.class.getSimpleName() + "|" + playerId;
    }
}
