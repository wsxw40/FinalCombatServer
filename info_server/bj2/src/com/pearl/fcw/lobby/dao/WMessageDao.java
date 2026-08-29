package com.pearl.fcw.lobby.dao;

import org.springframework.stereotype.Repository;

import com.pearl.fcw.core.dao.CacheDao;
import com.pearl.fcw.lobby.pojo.WMessage;

@Repository
public class WMessageDao extends CacheDao<WMessage, Integer> {

}
