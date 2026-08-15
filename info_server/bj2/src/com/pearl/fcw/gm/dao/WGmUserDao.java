package com.pearl.fcw.gm.dao;

import org.springframework.stereotype.Repository;

import com.pearl.fcw.core.dao.StableCacheDao;
import com.pearl.fcw.gm.pojo.WGmUser;

@Repository
public class WGmUserDao extends StableCacheDao<WGmUser, Integer> {

}
