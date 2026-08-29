package com.pearl.fcw.lobby.dao;

import org.springframework.stereotype.Repository;

import com.pearl.fcw.core.dao.CacheDao;
import com.pearl.fcw.lobby.pojo.WPlayerPack;

@Repository
public class WPlayerPackDao extends CacheDao<WPlayerPack, Integer> {

    /**
     * 该表设置了双主键，父类的add方法无法直接获得新增对象，必须手动查询
     */
    @Override
    public WPlayerPack add(WPlayerPack t) throws Exception {
        super.add(t);
        return findEntity(t);
    }

}
