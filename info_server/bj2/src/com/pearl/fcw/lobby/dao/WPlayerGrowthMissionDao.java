package com.pearl.fcw.lobby.dao;

import org.springframework.stereotype.Repository;

import com.pearl.fcw.core.dao.CacheDao;
import com.pearl.fcw.lobby.pojo.WPlayerGrowthMission;

@Repository
public class WPlayerGrowthMissionDao extends CacheDao<WPlayerGrowthMission, Integer> {

    /**
     * 该表设置了双主键，父类的add方法无法直接获得新增对象，必须手动查询
     */
    @Override
    public WPlayerGrowthMission add(WPlayerGrowthMission t) throws Exception {
        super.add(t);
        return findEntity(t);
    }

}
