package com.pearl.fcw.lobby.dao;

import org.springframework.stereotype.Repository;

import com.pearl.fcw.core.dao.CacheDao;
import com.pearl.fcw.lobby.pojo.WPlayerTeam;

@Repository
public class WPlayerTeamDao extends CacheDao<WPlayerTeam, Integer> {
    /**
     * 根据玩家ID查找
     * @param playerId
     * @return
     * @throws Exception
     */
    public WPlayerTeam findEntityByPlayerId(Integer playerId) throws Exception {
        return getSession().selectOne(mapperName + ".selectByPlayerId", playerId);
    }
}
