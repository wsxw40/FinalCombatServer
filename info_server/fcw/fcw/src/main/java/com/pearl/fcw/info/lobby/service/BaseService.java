package com.pearl.fcw.info.lobby.service;

import javax.annotation.Resource;

import com.pearl.fcw.info.core.network.SocketClientNew;
import com.pearl.fcw.info.core.persistence.cache.RedisClient;
import com.pearl.fcw.info.lobby.dao.MessageDao;
import com.pearl.fcw.info.lobby.dao.SysActivityDao;
import com.pearl.fcw.info.lobby.dao.SysConfigDao;
import com.pearl.fcw.info.lobby.dao.SysItemDao;

public class BaseService {

	@Resource
	protected RedisClient redisClient;

	@Resource
	protected SysItemDao sysItemDao;

	@Resource
	protected SysConfigDao sysConfigDao;

	@Resource
	protected SysActivityDao sysActivityDao;

	@Resource
	protected GetService getService;
	
	@Resource
	protected MessageDao messageDao;
	
	@Resource
	protected PlayerService playerService;
	
	@Resource
	protected SocketClientNew soClient;
	
	public RedisClient getRedisClient() {
		return redisClient;
	}

	public void setRedisClient(RedisClient redisClient) {
		this.redisClient = redisClient;
	}

	public SysItemDao getSysItemDao() {
		return sysItemDao;
	}

	public void setSysItemDao(SysItemDao sysItemDao) {
		this.sysItemDao = sysItemDao;
	}

	public SysConfigDao getSysConfigDao() {
		return sysConfigDao;
	}

	public void setSysConfigDao(SysConfigDao sysConfigDao) {
		this.sysConfigDao = sysConfigDao;
	}

	public SysActivityDao getSysActivityDao() {
		return sysActivityDao;
	}

	public void setSysActivityDao(SysActivityDao sysActivityDao) {
		this.sysActivityDao = sysActivityDao;
	}

}
