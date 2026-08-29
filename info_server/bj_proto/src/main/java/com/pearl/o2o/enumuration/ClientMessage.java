package com.pearl.o2o.enumuration;

import com.pearl.o2o.protocal.cm.CM;
import com.pearl.o2o.protocal.cm.CM_CancelRPC;
import com.pearl.o2o.protocal.cm.CM_RequestBinaryRPC;
import com.pearl.o2o.protocal.cm.CM_RequestTextRPC;
import com.pearl.o2o.protocal.cm.CM_ResponseOnlineInfo;
import com.pearl.o2o.protocal.cm.CM_ResponseStatus;
import com.pearl.o2o.protocal.cm.CM_SyncRPCQueue;
import com.pearl.o2o.protocal.cm.CM_UpdateServerInfo;
import com.pearl.o2o.protocal.cm.requestBinaryRpc.s_character_offline;
import com.pearl.o2o.protocal.cm.requestBinaryRpc.s_character_online;
import com.pearl.o2o.protocal.cm.requestBinaryRpc.s_end_new;
import com.pearl.o2o.protocal.cm.requestBinaryRpc.s_get_character_info;
import com.pearl.o2o.protocal.cm.requestBinaryRpc.s_get_level_info;
import com.pearl.o2o.protocal.cm.requestBinaryRpc.s_get_level_list;
import com.pearl.o2o.protocal.cm.requestBinaryRpc.s_get_unspeaklist;
import com.pearl.o2o.protocal.cm.requestBinaryRpc.s_keywords;
import com.pearl.o2o.protocal.cm.requestBinaryRpc.s_kill_info;
import com.pearl.o2o.protocal.cm.requestBinaryRpc.s_login_activity;
import com.pearl.o2o.protocal.cm.requestBinaryRpc.s_notice_list;
import com.pearl.o2o.protocal.cm.requestBinaryRpc.s_player_online;
import com.pearl.o2o.protocal.cm.requestBinaryRpc.s_random_player;
import com.pearl.o2o.protocal.cm.requestBinaryRpc.s_server_list;
import com.pearl.o2o.protocal.cm.requestBinaryRpc.s_sys_config;

public enum ClientMessage {
	CM_RequestTextRPC(CM_RequestTextRPC.class), 
	CM_RequestBinaryRPC(CM_RequestBinaryRPC.class), 
	CM_ResponseStatus(CM_ResponseStatus.class), 
	CM_ResponseOnlineInfo(CM_ResponseOnlineInfo.class), 
	CM_UpdateServerInfo(CM_UpdateServerInfo.class), 
	CM_CancelRPC(CM_CancelRPC.class), 
	CM_SyncRPCQueue(CM_SyncRPCQueue.class);
	
	private Class<? extends CM> clzz;
	
	private ClientMessage(Class<? extends CM> clzz) {
		this.clzz = clzz;
	}

	public Class<? extends CM> getClzz(){
		return this.clzz;
	}
	
	public static ClientMessage getClientMessage(int type) {
		return values()[type];
	}

	public static enum CM_RequestBinaryRPCURL {
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

		private Class<? extends CM_RequestBinaryRPC> clzz;

		private CM_RequestBinaryRPCURL(Class<? extends CM_RequestBinaryRPC> clzz) {
			this.clzz = clzz;
		}

		public static Class<? extends CM_RequestBinaryRPC> getCM_RequestBinaryRPCClass(String url) {
			return valueOf(url).clzz;
		}
	}
}
