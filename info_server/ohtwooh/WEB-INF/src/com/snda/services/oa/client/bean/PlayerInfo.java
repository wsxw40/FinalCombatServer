package com.snda.services.oa.client.bean;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import com.pearl.o2o.pojo.Player;

/**
 * player's snapshot when do the reservation
 */
public final class PlayerInfo{
		private final String sdoUid;
		private final int o2oUid;
		private final int playerId;
		private final String endpointIp;
		private final int areaIdPlayer;
		private final int groupIdPlayer;
		
		public PlayerInfo(String sdoUid,int o2oUid,int playerId,String endpointIp,int areaIdPlayer, int groupIdPlayer) {
			this.sdoUid = sdoUid;
			this.playerId = playerId;
			this.endpointIp = endpointIp;
			this.areaIdPlayer = areaIdPlayer;
			this.groupIdPlayer = groupIdPlayer;
			this.o2oUid = o2oUid;
		}
		
		public PlayerInfo(Player player, String sdoUid, String endpointIp){
			this.sdoUid = sdoUid;
			this.endpointIp = endpointIp;
			this.areaIdPlayer = SDOConstants.AREAID;
			this.groupIdPlayer = SDOConstants.GROUPID;
			this.playerId = player.getId();
			this.o2oUid = player.getUserId();
		}
		
		
		public String getEndpointIp() {
			return endpointIp;
		}
		
		public int getAreaIdPlayer() {
			return areaIdPlayer;
		}
		public int getGroupIdPlayer() {
			return groupIdPlayer;
		}
		
		public String getSdoUid() {
			return sdoUid;
		}
		public int getPlayerId() {
			return playerId;
		}
		
		

		public int getO2oUid() {
			return o2oUid;
		}

		@Override
		public String toString() {
			 return ReflectionToStringBuilder.toString(this);
		}
	}