package com.pearl.o2o.dao;

import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.pearl.o2o.pojo.Channel;
import com.pearl.o2o.pojo.Server;

public class ServerDao  extends SqlMapClientDaoSupport{
	
	@SuppressWarnings("unchecked")
	public Map<Integer,Server> getServerMap()throws DataAccessException{
		return this.getSqlMapClientTemplate().queryForMap("Server.select", null, "id");
	}
	@SuppressWarnings("unchecked")
	public Map<Integer,Channel> getChannelMap(int serverId)throws DataAccessException{
		return this.getSqlMapClientTemplate().queryForMap("Channel.select", serverId, "id");
	}
}
