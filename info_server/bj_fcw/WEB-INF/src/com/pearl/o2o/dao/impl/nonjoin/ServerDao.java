package com.pearl.o2o.dao.impl.nonjoin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.pearl.o2o.pojo.Channel;
import com.pearl.o2o.pojo.Server;
import com.pearl.o2o.utils.IdComparatorClass;

public class ServerDao  extends BaseMappingDao{
	private static final int DEFAULTID = 1;
	//----------------------get------------------------------------
	public Map<Integer,Server> getServerMap()throws DataAccessException{
		return queryMappingBeanMapByRelatedId(Server.class,DEFAULTID);
	}
	public List<Server> getServerList()throws DataAccessException{
		Map<Integer,Server> serverMap=getServerMap();
		List<Server> returnList=new ArrayList<Server>(serverMap.values());
		Collections.sort(returnList, new IdComparatorClass());
		return returnList;
	}
	
	public Map<Integer,Channel> getChannelMap(int serverId)throws DataAccessException{
		return queryMappingBeanMapByRelatedId(Channel.class,serverId);
	}
	public List<Channel> getChannelrList(int serverId)throws DataAccessException{
		Map<Integer,Channel> channelMap=getChannelMap(serverId);
		List<Channel> returnList=new ArrayList<Channel>(channelMap.values());
		Collections.sort(returnList, new IdComparatorClass());
		return returnList;
	}
	//----------------------create------------------------------------
	public void createServer(Server server)throws Exception{
		 insertObjIntoDBAndCache(server, DEFAULTID);
	}
	public void createChannel(Channel channel)throws Exception{
		insertObjIntoDBAndCache(channel,channel.getServerId());
	}
	//----------------------update------------------------------------
	public void updateServer(Server server) throws Exception{
		updateMappingBeanInCache(server, DEFAULTID);
	}
	public void updateChannel(Channel channel) throws Exception{
		updateMappingBeanInCache(channel, channel.getServerId());
	}
	//----------------------delete------------------------------------
	public void deleteServer(Server server) throws Exception{
		List<Channel> list = getChannelrList(server.getId());
		for(Channel c : list){
			deleteChannel(c);
		}
		deleteObjFromDBAndCache(server, DEFAULTID);
	}
	public void deleteChannel(Channel channel) throws Exception{
		deleteObjFromDBAndCache(channel, channel.getServerId());
	}
}
