package com.pearl.fcw.info.lobby.dao;

import org.springframework.stereotype.Repository;

import com.pearl.fcw.info.core.persistence.dao.CacheMultiGenericDao;
import com.pearl.fcw.info.lobby.pojo.PlayerItem;

@Repository
public class PlayerItemDao extends CacheMultiGenericDao<PlayerItem>{

    public PlayerItemDao() {
        super(PlayerItem.class);
    }
}
