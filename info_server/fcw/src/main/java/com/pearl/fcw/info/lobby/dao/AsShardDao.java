package com.pearl.fcw.info.lobby.dao;

import org.springframework.stereotype.Repository;

import com.pearl.fcw.info.core.persistence.dao.SingleGenericDao;
import com.pearl.fcw.info.lobby.pojo.AsShard;

@Repository
public class AsShardDao extends SingleGenericDao<AsShard> {

    public AsShardDao() {
        super(AsShard.class);
    }

}