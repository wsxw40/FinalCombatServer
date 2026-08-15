package com.pearl.o2o.enumuration;

import com.pearl.o2o.protocal.sm.SM;
import com.pearl.o2o.protocal.sm.SM_BroadcastChannelChat;
import com.pearl.o2o.protocal.sm.SM_BroadcastProxyChat;
import com.pearl.o2o.protocal.sm.SM_BroadcastServerChat;
import com.pearl.o2o.protocal.sm.SM_ExpiredPack;
import com.pearl.o2o.protocal.sm.SM_KickPlayer;
import com.pearl.o2o.protocal.sm.SM_NotifyClient;
import com.pearl.o2o.protocal.sm.SM_RequestBlackList;
import com.pearl.o2o.protocal.sm.SM_RequestKeywords;
import com.pearl.o2o.protocal.sm.SM_RequestLevelList;
import com.pearl.o2o.protocal.sm.SM_RequestNotice;
import com.pearl.o2o.protocal.sm.SM_RequestOnlineInfo;
import com.pearl.o2o.protocal.sm.SM_RequestServerList;
import com.pearl.o2o.protocal.sm.SM_RequestStatus;
import com.pearl.o2o.protocal.sm.SM_ResponseBinaryRPC;
import com.pearl.o2o.protocal.sm.SM_ResponseTextRPC;
import com.pearl.o2o.protocal.sm.SM_SendChat;
import com.pearl.o2o.protocal.sm.SM_SyncRPCQueue;
import com.pearl.o2o.protocal.sm.SM_SysConfig;
import com.pearl.o2o.protocal.sm.SM_UpdateCharacterInfo;
import com.pearl.o2o.protocal.sm.responseBinaryRPC.s_character_offline;
import com.pearl.o2o.protocal.sm.responseBinaryRPC.s_character_online;
import com.pearl.o2o.protocal.sm.responseBinaryRPC.s_end_new;
import com.pearl.o2o.protocal.sm.responseBinaryRPC.s_get_character_info;
import com.pearl.o2o.protocal.sm.responseBinaryRPC.s_get_level_info;
import com.pearl.o2o.protocal.sm.responseBinaryRPC.s_get_level_list;
import com.pearl.o2o.protocal.sm.responseBinaryRPC.s_get_unspeaklist;
import com.pearl.o2o.protocal.sm.responseBinaryRPC.s_keywords;
import com.pearl.o2o.protocal.sm.responseBinaryRPC.s_kill_info;
import com.pearl.o2o.protocal.sm.responseBinaryRPC.s_login_activity;
import com.pearl.o2o.protocal.sm.responseBinaryRPC.s_notice_list;
import com.pearl.o2o.protocal.sm.responseBinaryRPC.s_player_online;
import com.pearl.o2o.protocal.sm.responseBinaryRPC.s_random_player;
import com.pearl.o2o.protocal.sm.responseBinaryRPC.s_server_list;
import com.pearl.o2o.protocal.sm.responseBinaryRPC.s_sys_config;

public enum ServerMessage {
	SM_ResponseTextRPC(SM_ResponseTextRPC.class), 
	SM_ResponseBinaryRPC(SM_ResponseBinaryRPC.class), 
	SM_SendChat(SM_SendChat.class), 
	SM_BroadcastProxyChat(SM_BroadcastProxyChat.class), 
	SM_BroadcastChannelChat(SM_BroadcastChannelChat.class), 
	SM_RequestStatus(SM_RequestStatus.class), 
	SM_RequestOnlineInfo(SM_RequestOnlineInfo.class), 
	SM_NotifyClient(SM_NotifyClient.class), 
	SM_UpdateCharacterInfo(SM_UpdateCharacterInfo.class), 
	SM_BroadcastServerChat(SM_BroadcastServerChat.class), 
	SM_RequestKeywords(SM_RequestKeywords.class), 
	SM_RequestServerList(SM_RequestServerList.class), 
	SM_RequestLevelList(SM_RequestLevelList.class), 
	SM_RequestNotice(SM_RequestNotice.class), 
	SM_KickPlayer(SM_KickPlayer.class), 
	SM_SysConfig(SM_SysConfig.class), 
	SM_ExpiredPack(SM_ExpiredPack.class), 
	SM_SyncRPCQueue(SM_SyncRPCQueue.class), 
	SM_RequestBlackList(SM_RequestBlackList.class);
	private Class<? extends SM> clzz;
	private ServerMessage(Class<? extends SM> clzz) {
		this.clzz = clzz;
	}

	public Class<? extends SM> getClzz(){
		return this.clzz;
	}
	
	public static ServerMessage getClientMessage(int type) {
		return values()[type];
	}

	public static enum SM_ResponseBinaryRPCURL {
		s_end_new(s_end_new.class), 
		s_notice_list(s_notice_list.class), 
		s_get_level_info(s_get_level_info.class), 
		s_get_character_info(s_get_character_info.class), 
		s_keywords(s_keywords.class),
		s_server_list(s_server_list.class), 
		s_get_level_list(s_get_level_list.class), 
		s_player_online(s_player_online.class), 
		s_character_online(s_character_online.class), 
		s_character_offline(s_character_offline.class), 
		s_kill_info(s_kill_info.class), 
		s_sys_config(s_sys_config.class), 
		s_random_player(s_random_player.class), 
		s_login_activity(s_login_activity.class), 
		s_get_unspeaklist(s_get_unspeaklist.class);

		private Class<? extends SM_ResponseBinaryRPC> clzz;

		private SM_ResponseBinaryRPCURL(Class<? extends SM_ResponseBinaryRPC> clzz) {
			this.clzz = clzz;
		}

		public static Class<? extends SM_ResponseBinaryRPC> getSM_ResponseBinaryRPCClass(String url) {
			return valueOf(url).clzz;
		}
	}
}
