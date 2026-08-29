package com.pearl.fcw.info.lobby.dao;

import org.springframework.stereotype.Repository;

import com.pearl.fcw.info.core.persistence.dao.CacheMultiGenericDao;
import com.pearl.fcw.info.lobby.pojo.PlayerActivity;


@Repository
public class PlayerActivityDao  extends CacheMultiGenericDao<PlayerActivity> {

	public PlayerActivityDao() {
		super(PlayerActivity.class);
	}
	
}
