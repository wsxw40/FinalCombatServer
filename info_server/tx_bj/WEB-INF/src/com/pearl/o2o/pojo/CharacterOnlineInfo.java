package com.pearl.o2o.pojo;

import java.util.HashMap;
import java.util.Map;

public class CharacterOnlineInfo{
		private int activeConnections;
		private int onlineCharacter;
		//key:serverId, value: Map (key channelId, val: client_count)
		private Map<Integer,Map<Integer,Integer>> channelClientCount = new HashMap<Integer,Map<Integer,Integer>>();
		
		public int getActiveConnections() {
			return activeConnections;
		}
		public void setActiveConnections(int activeConnections) {
			this.activeConnections = activeConnections;
		}
		public int getOnlineCharacter() {
			return onlineCharacter;
		}
		public void setOnlineCharacter(int onlineCharacter) {
			this.onlineCharacter = onlineCharacter;
		}
		public Map<Integer,Map<Integer,Integer>> getChannelClientCount() {
			return channelClientCount;
		}
		
		public void addCount(int serverId, int channelId, int count){
			Map<Integer,Integer> server = channelClientCount.get(serverId);
			if (server == null) {
				Map<Integer,Integer> map = new HashMap<Integer,Integer>();
				map.put(channelId, count);
				channelClientCount.put(serverId, map);
			}else {
				Integer c = server.get(channelId);
				if (c == null) {
					server.put(channelId, count);
				}else{
					server.put(channelId, c+count);
				}
			}
		}
	}