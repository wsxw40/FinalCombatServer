#include "pch.h"
#include <string.h>

#define USE_ENCODER 1

static const int network_version = ((1 << 0) | (0 << 8) | (0 << 16) | (27 << 24));

enum EServerMessage
{
	SM_ResponseRPC,
	SM_ResponseEnterLobby,
	SM_ResponseLeaveLobby,
	SM_ResponseChannelConnect,
	SM_NotifyChat,
	SM_NotifyFCM,
	SM_ForceDisconnect,
	SM_ResponseTeamInvite,
	SM_ResponseTeamJoin,
	SM_NotifyTeamInvite,
	SM_NotifyTeamMemberJoin,
	SM_NotifyTeamMemberLeave,
	SM_NotifyTeamChangeLeader,
	SM_NotifyTeamMemberInfo,
	SM_NotifyTeamLeave,
	SM_NotifyTeamRefuse,
	SM_NotifyRoomPreserve,
	SM_NotifyRoomCancelPreserve,
	SM_NotifyTeamCall,
	SM_ResponseTeamCall,
	SM_ResponseSearchRoom,
	SM_NotifyUpdateLevelList,
	SM_NotifyRefusePreserve,
	SM_NotifyRPCMessage,
	SM_ResponseEnterServer,
	SM_ResponseEnterChannel,
	SM_ResponseLeaveServer,
	SM_NotifyRefreshServerList,
	SM_NotifyRefreshChannelList,
	SM_ResponseCharacterAddress,
	
	SM_NotifyChatGroupInvite,
	SM_NotifyChatGroupJoin,
	SM_NotifyChatGroupLeave,
	SM_NotifyChatGroupCall,
	SM_ResponseChatGroupCreate,
	SM_ResponseChatGroupMember,
	
	SM_NotifyMultiChat,
	SM_NotifyLoopMsg,
	SM_RequestApex,
	
	SM_ResponseBattleBattleGroups,
	SM_ResponseBattleGroupCreate,
	SM_ResponseBattleGroupJoin,
	SM_ResponseBattleGroupStartSearch,
	SM_ResponseBattleGroupChallenge,
	SM_NotifyBattleGroupInvite,
	SM_NotifyBattleGroupKick,
	SM_NotifyBattleGroupChange,
	SM_NotifyBattleGroupInfo,
	SM_NotifyBattleGroupSearching,
	SM_NotifyBattleGroupGameStart,


	SM_UpdateBillBoardInfo,

	// [2015-10-8] add by dengxiaobo ����ƥ��
	SM_ResponseMatchingTeamCreate,
	SM_ResponseMatchingTeamInvite,
	SM_ResponseMatchingTeamJoin,
	SM_ResponseMatchingTeamLeave,
	SM_ResponseMatchingTeamKick,
	SM_ResponseMatchingTeamChangeLeader,

	SM_NotifyMatchingTeamInvite,
	SM_NotifyMatchingTeamMemberJoin,
	SM_NotifyMatchingTeamMemberLeave,
	SM_NotifyMatchingTeamChangeLeader,
	SM_NotifyMatchingTeamMemberInfo,
	SM_NotifyMatchingTeamLeave,
	SM_NotifyMatchingTeamKick,
	SM_NotifyMatchingTeamChange,

	SM_ResponseMatching,
	SM_ResponseCancelMatching,

	SM_ResponseMatchingProgress,

	// ������as�����е� ��ʱ��֪����ʲô���� �ȼӽ�ȥ
	SM_NotifyPunishedNames,		//ƥ��ʱ�����ڳͷ�ʱ����
	SM_NotifyBeginMatch,		//��ʼƥ��
	SM_NotifyLeftPunishedTime,		//����ʣ��ͷ�ʱ��

	SM_NotifyGoToMetchingRoom,		// ƥ���ʱ�� ��������ֱ�ӽ���ĳ������

	SM_ResponseLestPersonChannel,

	SM_ResponseIntoMatchingTeam,

	CM_ResponseChangeMatchMap,				// �޸ĵ�ͼ
	CM_ResponseChangeMatchGameType,				// �޸���Ϸ����
	//end by dengxiaobo
	
	// ս������
	SM_ResponseBattleGroups,
	SM_ResponseHageBattleGroupCreate,
	SM_ResponseHageBattleGroupJoin,
	SM_ResponseHageBattleGroupStartSearch,
	SM_ResponseHageBattleGroupChallenge,
	SM_NotifyHageBattleGroupInvite,
	SM_NotifyHageBattleGroupKick,
	SM_NotifyHageBattleGroupChange,
	SM_NotifyHageBattleGroupInfo,
	SM_NotifyHageBattleGroupSearching,
	SM_NotifyHageBattleGroupGameStart,
	SM_ResponseHageBattleHappyJumpJoin,
};

enum EClientMessage
{
	CM_RequestRPC,
	CM_RequestEnterLobby,
	CM_RequestLeaveLobby,
	CM_RequestChannelConnect,
	CM_RequestChat,
	CM_RequestTeamInvite,
	CM_RequestTeamJoin,
	CM_RequestTeamLeave,
	CM_RequestTeamKick,
	CM_RequestTeamChangeLeader,
	CM_RequestTeamRefuse,
	CM_RequestTeamCall,
	CM_RequestSearchRoom,
	CM_RequestCancelSearchRoom,
	CM_RequestRefusePreserve,
	CM_RequestEnterServer,
	CM_RequestEnterChannel,
	CM_RequestLeaveServer,
	CM_RequestRefreshServerList,
	CM_RequestRefreshChannelList,
	CM_RequestCharacterAddress,

	CM_SaveUserProfile,
	CM_SaveCharacterProfile,
	
	CM_RequestChatGroupCreate,
	CM_RequestChatGroupInvite,
	CM_RequestChatGroupJoin,
	CM_RequestChatGroupLeave,
	CM_RequestChatGroupCall,
	CM_RequestChatGroupMember,
	
	CM_NotifyMultiChat,
	CM_RequestApex,
	
	CM_RequestBattleGroups,
	CM_RequestBattleGroupCreate,
	CM_RequestBattleGroupInvite,
	CM_RequestBattleGroupJoin,
	CM_RequestBattleGroupLeave,
	CM_RequestBattleGroupInfo,
	CM_RequestBattleGroupReady,
	CM_RequestBattleGroupStartSearch,
	CM_RequestBattleGroupChallenge,

	// [2015-10-8] add by dengxiaobo ����ƥ��
	CM_RequestMatchingTeamCreate,			// ��������ƥ��Ķ���(������ȵý��뷿����ܷ�)
	CM_RequestMatchingTeamJoin,				// ����
	CM_RequestMatchingTeamInvite,			// ����
	CM_RequestMatchingTeamLeave,			// �뿪(��һ����)
	CM_RequestMatchingTeamKick,				// T��
	CM_RequestMatchingTeamChangeLeader,		// ���ӳ�(��һ����)

	CM_RequestMatchingProgress,				//

	CM_RequestMatching,						// ����ƥ��
	CM_RequestCancelMatching,				// ����ƥ��

	CM_RequestLestPersonChannel,				// �����ٵ�Ƶ��

	CM_RequestIntoMatchingTeam,				// ������Һ� ������Ҫ����һ����������ͬ��ƥ����
	CM_RequestChangeMatchMap,				// �޸ĵ�ͼ
	CM_RequestChangeMatchGameType,				// �޸���Ϸ����
	//end by dengxiaobo
	
	// ս��������
	CM_RequestHageBattleGroups,
	CM_RequestHageBattleGroupCreate,
	CM_RequestHageBattleGroupInvite,
	CM_RequestHageBattleGroupJoin,
	CM_RequestHageBattleGroupLeave,
	CM_RequestHageBattleGroupInfo,
	CM_RequestHageBattleGroupReady,
	CM_RequestHageBattleGroupStartSearch,
	CM_RequestHageBattleGroupChallenge,
	CM_RequestHageBattleHappyJumpJoin,
};

enum EClientState
{
	CS_Idle,
	CS_Connected,
	CS_Authentication,
	CS_WaitNickName,
	CS_Login,
	CS_Lobby,
};

enum EAuthenticationState
{
	CLogin_Success,
	CLogin_Failed,
	CLogin_NeedNickName,
	CLogin_NickNameFailed,
};

// -----------------------------------------------------------------
// class http
// -----------------------------------------------------------------
static void ClientRPCCallback(RpcRequest * conn)
{
	uint request_id;
	uint client_uid;

	conn->ReadInt(request_id);
	conn->ReadInt(client_uid);

	ClientConnection * client = server.client_pool.Get(client_uid);

	if (client)
	{
		client->rpc_call_id = 0;
		size_t size = conn->read_end - conn->read_position;

		client->BeginWrite();
		client->WriteByte(SM_ResponseRPC);
		client->WriteInt(request_id);
		if (conn->error == 0)
		{
			client->WriteInt((int)size);
			client->Write(conn->read_position, size);
		}
		else
			client->WriteInt(0);
		client->EndWrite();
		
		if (conn->error != 0)
		{
			char msg[32];
			sprintf(msg, "rpc_error: %d", conn->error);
			client->ForceDisconnect(msg);
		}
	}
}

static void ClientLoginCallback(RpcRequest * conn)
{
	uint client_uid;
	byte sdo_login;

	conn->ReadInt(client_uid);
	log_write(LOG_DEBUG3, "ClientLoginCallback, uid: %x", client_uid);

	ClientConnection * client = server.client_pool.Get(client_uid);
	
	if (client && client->state == CS_WaitNickName)
	{
		if (conn->error == 0)
		{
			try
			{
				char error_message[256];
				char profile[4096];
				
				conn->ReadInt(client_uid);
				conn->ReadInt(client->user_id);
				conn->ReadString(client->character_name, sizeof(client->character_name));
				
				client->character_id = client->user_id;
				conn->ReadInt(client->character_level);
				conn->ReadInt(client->character_exp);
				conn->ReadString(profile, sizeof(profile));
				client->user_profile = profile;
				conn->ReadString(error_message, sizeof(error_message));
				conn->ReadByte(client->first_game);
				conn->ReadByte(client->is_vip);
				conn->ReadByte(client->net_bar_level);
				conn->ReadByte(client->business_card);
				conn->ReadString(client->head_icon, sizeof(client->head_icon));
				conn->ReadByte(client->is_PlayerCheckToday);
				conn->ReadInt(client->top);
				conn->ReadInt(client->fightnum);
				conn->ReadFloat(client->win_rate);
				conn->ReadInt(client->character_group_id);
				conn->ReadString(client->character_group, sizeof(client->character_group));
				conn->ReadInt(client->character_group_level);
				conn->ReadByte(client->is_gm);
				if (client->user_id != 0)
				{
					client->OnLoginSuccess();
					return;
				}
				else
				{
					client->OnNickNameFailed(error_message);
					return;
				}
			}
			catch (...)
			{
				log_write(LOG_ERROR, "login internal error");
			}
		}
		client->OnLoginFailed("server_timeout");
	}
	else
	{
		log_write(LOG_DEBUG3, "No Player Found");
	}
}

static void ClientCharacterLoginCallback(RpcRequest * conn)
{
	uint client_uid;

	conn->ReadInt(client_uid);
	log_write(LOG_DEBUG3, "CharacterLoginCallback, uid: %x", client_uid);

	ClientConnection * client = server.client_pool.Get(client_uid);

	if (client && client->state == CS_Authentication)
	{
		if (conn->error == 0)
		{
			try
			{
				char error_message[256];
				char profile[4096];
				
				conn->ReadInt(client_uid);
				conn->ReadInt(client->user_id);
				if (client->user_id == -1)
				{
					//error msg
					conn->ReadString(error_message, sizeof(error_message));
					client->OnLoginFailed(error_message);
					
					return;
				}
				else if(client->user_id == -2)
				{
					int user_id;
					conn->ReadInt(user_id);
					
					log_write(LOG_DEBUG1, "Same Account Login %d", user_id);

					client->OnLoginFailed("account_already_online");

					/*ClientConnection * & c = server.online_account_map[user_id];

					if (c && c->user_id == user_id)
					{
						c->ForceDisconnect("login_from_otherip");
					}*/
					return;
					
				}
				else if (client->user_id == 0)
				{
					// to client need nick name
					client->OnLoginRequestNickName();
					
					return;
				}
				else
				{
					client->character_id = client->user_id;
					conn->ReadString(client->character_name, sizeof(client->character_name));
					conn->ReadInt(client->character_level);
					conn->ReadInt(client->character_exp);
					conn->ReadString(profile, sizeof(profile));
					conn->ReadByte(client->first_game);
					conn->ReadByte(client->is_vip);
					conn->ReadByte(client->net_bar_level);
					conn->ReadByte(client->business_card);
					conn->ReadString(client->head_icon, sizeof(client->head_icon));
					conn->ReadByte(client->is_PlayerCheckToday);
					conn->ReadInt(client->top);
					conn->ReadInt(client->fightnum);
					conn->ReadFloat(client->win_rate);
					conn->ReadInt(client->character_group_id);
					conn->ReadString(client->character_group, sizeof(client->character_group));
					conn->ReadInt(client->character_group_level);
					uint dwGm = 0;
					conn->ReadInt(dwGm);
					client->is_gm = dwGm;
					conn->ReadInt(client->matching_hege_level);
					conn->ReadInt(client->matching_fighting_level);
					if (client->matching_hege_level < 1)
					{
						client->matching_hege_level = 1;
					}
					if (client->matching_fighting_level < 1)
					{
						client->matching_fighting_level = 1;
					}
					
					//log_write(LOG_DEBUG1, "%s, %s, matching level : %d, client name : %s", __FILE__, __FUNCTION__, client->matching_level, client->character_name);
					client->user_profile = profile;
					client->OnLoginSuccess();
					
					return;
				}
			}
			catch (...)
			{
				log_write(LOG_ERROR, "login internal error");
			}
		}
		client->OnLoginFailed("server_timeout");
	}
	else
	{
		log_write(LOG_DEBUG1, "No Player Found");
	}
}


// -----------------------------------------------------------------
// class client
// -----------------------------------------------------------------
ClientConnection::ClientConnection()
	: BinaryStream(appcfg.client_buffersize)
	, state(CS_Idle)
	, user_id(0)
	, character_id(0)
	, character_server_id(0)
	, character_channel_id(0)
	, character_room_id(0)
	, team_leader_uid(0)
	, m_dwMatchingTeamGroupId(0)
	, fcm_refresh_time(0.f)
	, fcm_left_time(10000)
	, list_refresh_time(0.f)
	, matching_level(0xffffffff)
{
	memset(team_members, 0, sizeof(team_members));
	memset(client_version, 0, sizeof(client_version));
	memset(account, 0, sizeof(account));
	
	memset(head_icon, 0, sizeof(head_icon));
	
	joined_battlegroup = 0;
	battlegroup_state = 0;

	m_dwMatchingTeamGroupId = 0;
	
	created_chatgroup = 0;
	
	connection = this;
	stream = this;
	first_game = 0;
	is_vip = 0;
	is_PlayerCheckToday = 0;
	net_bar_level = 0;
	business_card = 0;
	
	top = 0;
	fightnum = 0;
	win_rate = 0;
	memset(character_group, 0, sizeof(character_group));
	character_group_id = 0;
	character_group_level = 0;
	is_gm = 0;
}

ClientConnection::~ClientConnection()
{
}

// is ready
bool ClientConnection::IsOnline()
{
	return state >= CS_Lobby;
}

// on client connected
void ClientConnection::OnConnected()
{
	log_write(LOG_DEBUG4, "client connected from %s(uid:%x).", inet_ntoa(client_ip), uid);

	state = CS_Connected;
	rpc_call_id = 0;
	character_exp = 0;
	
	joined_battlegroup = 0;
	battlegroup_state = 0;

	m_dwMatchingTeamGroupId = 0;
	
	created_chatgroup = 0;
	chatgroups.clear();

	user_profile.clear();
	character_profile.clear();	

#if USE_ENCODER
  xor_encoder.Reset();
  des_encoder.Reset();
  encoder = &xor_encoder;
  unsigned char key[8];
  des_encoder.GetKey(key, sizeof(key));
  unsigned char keyc[8];
  des_encoder.GetKeyPublic(keyc);
  log_write(LOG_DEBUG1, "banner key_c=%02x%02x%02x%02x%02x%02x%02x%02x key=%02x%02x%02x%02x%02x%02x%02x%02x",
      keyc[0],keyc[1],keyc[2],keyc[3],keyc[4],keyc[5],keyc[6],keyc[7],
      key[0],key[1],key[2],key[3],key[4],key[5],key[6],key[7]);
  BeginWrite();
  Write(key, sizeof(key));
  EndWrite();
#endif
  
}

void ClientConnection::OnDisconnected()
{
	log_write(LOG_DEBUG1, "%s, %s, uid : %d, name : %s", __FILE__, __FUNCTION__, uid, character_name);

	if (m_dwMatchingTeamGroupId)
	{
		MatchingTeamGroup* pGroup = server.m_pollMatchingTeamGroups.Get(m_dwMatchingTeamGroupId);
		if (pGroup)
		{
			uint dwId = m_dwMatchingTeamGroupId;
			bool bMatching = pGroup->IsMatching();
			if(pGroup->DelCharacter(this))
			{
				if (bMatching)
				{
					server.matching_connection.RequestCancelMatching(dwId, false);
				}
			}
		}
		else
		{
			m_dwMatchingTeamGroupId = 0;
		}
	}
	if(xlinfo.fcmflag != 0)
	{
		if(server.xl_fcm_connection.IsConnected())
			server.xl_fcm_connection.RequestHeatBeat(this,false);
	}
	
	// 
	server.apex_connection.ClientLogOut(user_id, client_ip.s_addr, user_name);
	
	// team leave
	OnRequestTeamLeave();
	
	// battlegroup
	NotifyBattleGroupLeave(joined_battlegroup);

	// chat group 
	for (std::set<uint>::iterator it = chatgroups.begin(); it != chatgroups.end(); it++)
	{
		NotifyChatGroupLeave(*it);
	}

	// leave lobby
	if (state >= CS_Lobby)
	{
		OnLeaveLobby();
	}
	else if(state == CS_Login)
	{
		// notify character offline
		RpcRequest * request = server.rpc.Allocate(RpcQueue::kLoginQueue);

		if (request)
		{
			// HACK: much more timeout for offline requests.
			request->timeout *= 10;
			request->BeginWrite();
			request->WriteBinaryRPC("s_character_offline");
			request->EndRPCUserdata();
			request->WriteInt(user_id);
			request->WriteInt(character_id);
			request->WriteString(inet_ntoa(client_ip));
			request->EndWrite();
		}
	}

	// cancel rpc calls
	if (rpc_call_id)
	{
		server.rpc.CancelRPC(RpcQueue::kClientQueue, rpc_call_id);
		rpc_call_id = 0;
	}

	// offline
	if (state >= CS_Login)
	{
		if (user_id > 0)
		{
			server.online_account_map.erase(user_id);
		}
	}

	server.client_pool.Free(uid);

	state = CS_Idle;
	user_id = 0;
	user_name[0] = 0;
	
	character_id = 0;
	character_name[0] = 0;
	character_group[0] = 0;
	character_group_id = 0;
	character_group_level = 0;
	is_gm = 0;

	user_profile.clear();
	character_profile.clear();
	
	top = 0;
	fightnum = 0;
	win_rate = 0;
}

// receive message
void ClientConnection::OnMessage()
{
	log_write(LOG_DEBUG1, "ClientConnection::OnMessage state=%d read_end=%d", (int)state, (int)(read_end - read_position));
	switch (state)
	{
	case CS_Connected: 		OnMessageConnected(); break; 
	case CS_Authentication: break; 
	case CS_WaitNickName:	OnMessageNickName();  break;
	case CS_Login: 			OnMessageLogin(); break;
	case CS_Lobby: 			OnMessageLobby(); break;
	}
}

// on message login
void ClientConnection::OnMessageLogin()
{
	byte msg;
	ReadByte(msg);

	switch (msg)
	{
	case CM_RequestRPC:			OnRequestRPC();	break;
	case CM_RequestEnterLobby:	OnRequestEnterLobby(); break;

	case CM_SaveUserProfile:	OnSaveUserProfile(); break;
	
	default:					OnClose(); break;
	}
}

// on message lobby
void ClientConnection::OnMessageLobby()
{
	byte msg;
	ReadByte(msg);

	switch (msg)
	{
	case CM_RequestRPC:				OnRequestRPC();	break;
	case CM_RequestChat:			OnRequestChat(); break;
	case CM_RequestLeaveLobby:		OnRequestLeaveLobby(); break;
	case CM_RequestChannelConnect:	OnRequestChannelConnect(); break;
	case CM_RequestTeamInvite:		OnRequestTeamInvite(); break;
	case CM_RequestTeamJoin:		OnRequestTeamJoin(); break;
	case CM_RequestTeamLeave:		OnRequestTeamLeave(); break;
	case CM_RequestTeamKick:		OnRequestTeamKick(); break;
	case CM_RequestTeamChangeLeader:OnRequestTeamChangeLeader(); break;
	case CM_RequestTeamRefuse:		OnRequestTeamRefuse(); break;
	case CM_RequestTeamCall:		OnRequestTeamCall(); break;
	case CM_RequestSearchRoom:		OnRequestSearchRoom(); break;
	case CM_RequestCancelSearchRoom:OnRequestCancelSearchRoom(); break;
	case CM_RequestRefusePreserve:	OnRequestRefusePreserve(); break;
	case CM_RequestEnterServer:		OnRequestEnterServer(); break;
	case CM_RequestEnterChannel:	break;
	case CM_RequestLeaveServer:		OnRequestLeaveServer(); break;
	case CM_RequestRefreshServerList:OnRequestRefreshServerList(); break;
	case CM_RequestRefreshChannelList: OnRequestRefreshChannelList(); break;
	case CM_RequestCharacterAddress:OnRequestCharacterAddress(); break;

	case CM_SaveUserProfile:		OnSaveUserProfile(); break;
	case CM_SaveCharacterProfile:	OnSaveCharacterProfile(); break;
	
	case CM_RequestChatGroupCreate:	OnRequestChatGroupCreate(); break;
	case CM_RequestChatGroupInvite:	OnRequestChatGroupInvite(); break;
	case CM_RequestChatGroupJoin:	OnRequestChatGroupJoin(); break;
	case CM_RequestChatGroupLeave:	OnRequestChatGroupLeave(); break;
	case CM_RequestChatGroupCall:	OnRequestChatGroupCall(); break;
	case CM_RequestChatGroupMember:	OnRequestChatGroupMember(); break;
	
	case CM_NotifyMultiChat:		OnNotifyMultiChat(); break;
	case CM_RequestApex:			OnRequestApex(); break;
	
	case CM_RequestBattleGroups:			OnRequestBattleGroups(); break;
	case CM_RequestBattleGroupCreate:		OnRequestBattleGroupCreate(); break;
	case CM_RequestBattleGroupInvite:		OnRequestBattleGroupInvite(); break;
	case CM_RequestBattleGroupJoin:			OnRequestBattleGroupJoin(); break;
	case CM_RequestBattleGroupLeave:		OnRequestBattleGroupLeave(); break;
	case CM_RequestBattleGroupInfo:			OnRequestBattleGroupInfo(); break;
	case CM_RequestBattleGroupReady:		OnRequestBattleGroupReady(); break;
	case CM_RequestBattleGroupStartSearch:	OnRequestBattleGroupStartSearch(); break;
	case CM_RequestBattleGroupChallenge:	OnRequestBattleGroupChallenge(); break;
		
	case CM_RequestMatchingTeamCreate:			OnRequestMatchingTeamCreate(); break;
	case CM_RequestMatchingTeamJoin:			OnRequestMatchingTeamJoin(); break;
	case CM_RequestMatchingTeamInvite:			OnRequestMatchingTeamInvite(); break;
	case CM_RequestMatchingTeamLeave:			OnRequestMatchingTeamLeave(); break;
	case CM_RequestMatchingTeamKick:			OnRequestMatchingTeamKick(); break;
	case CM_RequestMatchingTeamChangeLeader:	OnRequestMatchingTeamChangeLeader(); break;

	case CM_RequestMatching:				OnRequestMatching(); break;
	case CM_RequestCancelMatching:			OnRequestCancelMatching(); break;

	case CM_RequestLestPersonChannel:		OnRequestLestPersonChannel(); break;

	case CM_RequestIntoMatchingTeam:		OnRequestIntoMatchingTeam(); break;

	case CM_RequestChangeMatchMap:			OnRequestChangeMatchMap(); break;
	case CM_RequestChangeMatchGameType:		OnRequestChangeMatchGameType(); break;
	
	case CM_RequestHageBattleGroupJoin:		OnRequestHageBattleGroupJoin(); break;
	case CM_RequestHageBattleGroupLeave:	OnRequestHageBattleGroupLeave(); break;
	case CM_RequestHageBattleGroups:		OnRequestHageBattleGroups(); break;
	case CM_RequestHageBattleHappyJumpJoin: OnRequestHageBattleHappyJumpJoin(); break;
	}
}

bool ClientConnection::RequestRealNameAuth(const char* in, int len, uint uid)
{
	char bin[256] = {0};
	char out[256] = {0};
	int outlen = Base64::base64ToBin(in, (unsigned char* )bin, Base64::calculateBase64Len(strlen(in)));

	log_write(LOG_DEBUG3, "outlen %d", outlen);

	if(1 == AesDecrypt(&server.xl_connection[0][0].aes,(unsigned char*)bin,(unsigned char*)out))
	{
		log_write(LOG_ERROR, "AesDec Failed");
		
		return false;
	}
	
	char buffer[16] = {0};
	
	memcpy(buffer,out + 100,2);
	log_write(LOG_DEBUG3, "Connection Type %s", buffer);
	
	int connection_id = 0;
	
	if(strcmp(buffer,"11") == 0)
	{
		connection_id = 0;
	}
	else if(strcmp(buffer,"12") == 0)
	{
		connection_id = 1;
	}
	else
	{
		log_write(LOG_ERROR, " error Connection Type %s", buffer);
		
		return false;
	}
	
	bool is_find = false;
	int rand_id = rand() % xl_connection_num;
	
	if(server.xl_connection[connection_id][rand_id].IsConnected())
	{
		is_find = true;
	}
	else
	{
		for (int i = 0; i < xl_connection_num; i++)
		{
			if(server.xl_connection[connection_id][i].IsConnected())
			{
				rand_id = i;
				is_find = true;
				break;
			}
		}

		if(!is_find)
		{
			connection_id = connection_id == 0 ? 1 : 0;
			
			for (int i = 0; i < xl_connection_num; i++)
			{
				if(server.xl_connection[connection_id][i].IsConnected())
				{
					rand_id = i;
					is_find = true;
					break;
				}
			}
		}
	}
	
	if(!is_find)
	{
		log_write(LOG_ERROR, "XL Login Server No Connection connection_id : %d", connection_id);
		
		return false;
	}
	
	unsigned short random_id = server.xl_connection[connection_id][rand_id].GetRandomID();
	int ip = htonl(client_ip.s_addr);

	server.xl_connection[connection_id][rand_id].BeginWrite();
	server.xl_connection[connection_id][rand_id].WriteHead("00045");
	server.xl_connection[connection_id][rand_id].WriteVersion("      ");

	server.xl_connection[connection_id][rand_id].BeginAesCompress();
	server.xl_connection[connection_id][rand_id].WriteString(server.xl_connection[connection_id][rand_id].login_conmand, 10);
	server.xl_connection[connection_id][rand_id].WriteShort(random_id);
	server.xl_connection[connection_id][rand_id].WriteString(out,144);
	server.xl_connection[connection_id][rand_id].WriteInt(ip);
	server.xl_connection[connection_id][rand_id].EndAesCompress();
	
	if (server.xl_connection[connection_id][rand_id].writeerr)
	{
		log_write(LOG_ERROR, "connection id : %d, rand_id : %d, buffer : %s, in : %s", connection_id, rand_id, buffer, in);
	}

	server.xl_connection[connection_id][rand_id].EndWrite();

	log_write(LOG_DEBUG3, "xl_log_write_end, %d    %d", random_id, uid);

	server.xl_connection[connection_id][rand_id].clientconnection_map[random_id] = uid;
	
	return true;
}

void ClientConnection::OnMessageConnected()
{
	int client_network_version = 0;
	byte xl_check = false;
	char xl_check_info[256] = {0};
	char login_info[256] = {0};

	// set status to authentication first
	state = CS_Authentication;

	uint count = server.client_pool.GetCount();

	if (count > appcfg.max_client_current_count)
	{
		log_write_sys(LOG_INFO, "online_client_count >= appcfg.max_client_current_count");
		OnLoginFailed("max_client_limit");
		
		return;
	}
	
	// network version
	ReadInt(client_network_version);
	if (client_network_version != network_version)
	{
		OnLoginFailed("version_error");
		
		return;
	}
	
	// version
	ReadString(client_version, sizeof(client_version));
	// player name
	ReadString(account, sizeof(account));
	// player xlcheckinfo
	ReadString(xl_check_info, sizeof(xl_check_info));
	// player login_info
	ReadString(login_info, sizeof(login_info));

	log_write(LOG_DEBUG1, "OnMessageConnected: nv=%x client_version=%s account=%s xl_check=%s login_info=%s",
			client_network_version, client_version, account, xl_check_info, login_info);

#if USE_ENCODER
  unsigned char key[8];
  Read(key, sizeof(key));
  des_encoder.SetKey(key, sizeof(key));
  encoder = &des_encoder;
#endif
	
	if (strlen(xl_check_info) != 0)
	{
		/* xl_check 屏蔽：不进行实名/海外验证。
		 * account 为空时直接用 xl_check 作为账号。 */
		if (account[0] == 0)
		{
			strcpy(account, xl_check_info);
			xl_check_info[0] = 0;
		}
		else
		{
			xl_check_info[0] = 0;
		}
	}
	else if (strlen(login_info) != 0)
	{
		/* 忽略 login_info（票据），不做海外验证 */
		login_info[0] = 0;
	}
	{
		RpcRequest * request = server.rpc.Allocate(RpcQueue::kLoginQueue);

		if (request)
		{
			const char * player_ip = inet_ntoa(client_ip);
			
			request->BeginWrite();
			request->WriteBinaryRPC("s_character_online", &ClientCharacterLoginCallback);
			request->WriteInt(uid);
			request->EndRPCUserdata();
			request->WriteInt(uid);
			request->WriteString(account);
			request->WriteString(player_ip);
			request->WriteString(client_version);
			request->WriteString(xlinfo.xl_vip_account_level);
			request->WriteString(xlinfo.xl_bar_level);
			request->WriteShort(xlinfo.account_type);
			request->EndWrite();
			strcpy(user_name, account);
		}
		else
		{
			OnLoginFailed("server_busy");
			return;
		}
	}
}


void ClientConnection::OnMessageNickName()
{
	char nickname[64] = {0};
	log_write(LOG_DEBUG4, "OnMessageNickName");
	
	ReadString(nickname, sizeof(nickname));
	const char * player_ip = inet_ntoa(client_ip);

	RpcRequest * request = server.rpc.Allocate(RpcQueue::kCreateQueue);

	if (request)
	{
		request->BeginWrite();
		request->WriteBinaryRPC("s_player_online", &ClientLoginCallback);
		request->WriteInt(uid); // lijia: uid must returned when timeout.
		request->EndRPCUserdata();
		request->WriteInt(uid);
		request->WriteString(account);
		request->WriteString(nickname);
		request->WriteString(player_ip);
		request->WriteString(xlinfo.xl_vip_account_level);
		request->WriteString(xlinfo.xl_bar_level);
		request->WriteShort(xlinfo.account_type);
		request->EndWrite();
	}
	else
	{
		OnLoginFailed("server_busy");
		return;
	}
}

void ClientConnection::OnResponseXLConnection(const char* ac)
{
	RpcRequest * request = server.rpc.Allocate(RpcQueue::kLoginQueue);
	const char * player_ip = inet_ntoa(client_ip);

	if (request)
	{
		request->BeginWrite();
		request->WriteBinaryRPC("s_character_online", &ClientCharacterLoginCallback);
		request->WriteInt(uid);
		request->EndRPCUserdata();
		request->WriteInt(uid);
		request->WriteString(ac);
		strcpy(user_name, ac);
		request->WriteString(player_ip);
		request->WriteString(client_version);
		request->WriteString(xlinfo.xl_vip_account_level);
		request->WriteString(xlinfo.xl_bar_level);
		request->WriteShort(xlinfo.account_type);
		request->EndWrite();

		memcpy(account,ac,sizeof(account));
	}
	else
	{
		OnLoginFailed("server_busy");
		return;
	}
}

void ClientConnection::OnResponseAbroadLoginInfoConnection(const char* ac)
{
	RpcRequest * request = server.rpc.Allocate(RpcQueue::kLoginQueue);
	const char * player_ip = inet_ntoa(client_ip);

	if (request)
	{
		request->BeginWrite();
		request->WriteBinaryRPC("s_character_online", &ClientCharacterLoginCallback);
		request->WriteInt(uid);
		request->EndRPCUserdata();
		request->WriteInt(uid);
		request->WriteString(ac);
		strcpy(user_name, ac);
		request->WriteString(player_ip);
		request->WriteString(client_version);
		request->WriteString(xlinfo.xl_vip_account_level);
		request->WriteString(xlinfo.xl_bar_level);
		request->WriteShort(xlinfo.account_type);
		request->EndWrite();

		memcpy(account,ac,sizeof(account));
	}
	else
	{
		OnLoginFailed("server_busy");
		return;
	}
}

// on update
void ClientConnection::OnUpdate(double time)
{
	if (character_channel_id == 0)
	{
		list_refresh_time += time;
		if (list_refresh_time > 5)
		{
			// refresh server list
			if (character_server_id == 0)
			{
				ResponseRefreshServerList();
			}
			else
			{
				ResponseRefreshChannelList();
			}
		}
	}

	// fcm
	{
		if(xlinfo.fcmflag != 0)
		{
			fcm_refresh_time += time;
			float f = float(appcfg.fcm_time_interval);
			if(fcm_refresh_time > f)
			{
				if(server.xl_fcm_connection.IsConnected())
				{
					server.xl_fcm_connection.RequestHeatBeat(this);
				}
				else
				{
					log_write(LOG_DEBUG1, "xl fcm connect is not connected, heatbeat is cannelled");
				}
			}
		}
	}
}

void ClientConnection::OnLoginSuccess()
{
	if (state == CS_Authentication || state == CS_WaitNickName)
	{
		ClientConnection * & c = server.online_account_map[user_id];

		if (c && c->user_id == user_id)
		{
			c->ForceDisconnect("login_from_otherip");

			// the online info is already erased.
			server.online_account_map[user_id] = this;
		}
		else
		{
			// update online account map.
			c = this;
		}
		
		log_write(LOG_DEBUG4, "login success : %d", uid);
		state = CS_Login;
		BeginWrite();
		WriteInt(CLogin_Success);
		WriteByte(server.sys_config.ads_on);
		WriteInt(uid);
		WriteInt(user_id);
		WriteString(user_name);
		WriteString(character_name);
		WriteString(user_profile.c_str());
		WriteShort(xlinfo.account_type);

		{
			WriteString(server.http_config.http_xl_logo.c_str());
			WriteString(server.http_config.http_xl_report.c_str());
			WriteString(server.http_config.http_xl_info.c_str());

			WriteString(server.http_config.http_fcm.c_str());
			WriteString(server.http_config.http_gw.c_str());
			WriteString(server.http_config.http_advertising_v.c_str());
			WriteString(server.http_config.http_advertising_h.c_str());
			
			WriteString(server.http_config.http_paydraw.c_str());

			int size = server.http_config.http_pay_map.size();
			WriteInt(size);
			std::map<int,std::string>::iterator itor = server.http_config.http_pay_map.begin();
			for (;itor != server.http_config.http_pay_map.end(); itor++)
			{
				WriteInt(itor->first);
				WriteString(itor->second.c_str());
			}
			WriteString(server.http_config.achievement_list.c_str());

			WriteByte(server.http_config.nick_name_length_min);
			WriteByte(server.http_config.nick_name_length_max);
			WriteByte(server.http_config.battle_group_name_length_min);
			WriteByte(server.http_config.battle_group_name_length_max);
			WriteByte(server.http_config.room_name_length_min);
			WriteByte(server.http_config.room_name_length_max);
			WriteByte(server.http_config.time_sell_start);
			WriteByte(server.http_config.time_sell_start_date);
			WriteByte(server.http_config.time_sell_end);
			WriteByte(server.http_config.time_sell_end_date);
			WriteByte(server.http_config.time_sell_giftH);
			WriteByte(server.http_config.time_sell_giftM);
			WriteByte(server.http_config.time_sell_date);
			WriteString(server.http_config.buffversion.c_str());
			WriteString(server.http_config.quick_sell_data.c_str());
			WriteString(server.http_config.time_sell_gold.c_str());
			WriteString(server.http_config.time_sell_silver.c_str());
			WriteString(server.http_config.time_sell_cooper.c_str());


			WriteString(server.http_config.UISystemFlag.c_str());
			WriteByte(server.http_config.xl_exe_open);

			//log_write(LOG_ERROR, "%d %d %d %d %d", server.http_config.nick_name_length_min, server.http_config.nick_name_length_max, server.http_config.battle_group_name_length_min, server.http_config.battle_group_name_length_max, server.http_config.xl_exe_open);
			
			
		}

		EndWrite();

		if(xlinfo.fcmflag != 0)
		{
			if(server.xl_fcm_connection.IsConnected())
				server.xl_fcm_connection.RequestHeatBeat(this);
		}
		else
		{
			fcm_left_time = 0;
			fcm_online_minutes = 0;
		}
	}
	
	server.apex_connection.ClientLogIn(user_id, client_ip.s_addr, user_name);
}

void ClientConnection::OnLoginFailed(const char * msg)
{
	if (state == CS_Authentication || state == CS_WaitNickName)
	{
		log_write(LOG_DEBUG4, "OnLoginFailed msg[%s]", msg);
		
		BeginWrite();
		WriteInt(CLogin_Failed);
		WriteByte(0);
		WriteInt(0);
		WriteInt(0);
		WriteString(msg);
		EndWrite();
		
		Disconnect();
	}
}

void ClientConnection::OnNickNameFailed(const char * msg)
{
	if (state == CS_Authentication || state == CS_WaitNickName)
	{
		log_write(LOG_DEBUG4, "OnNickNameFailed msg[%s]", msg);

		BeginWrite();
		WriteInt(CLogin_NickNameFailed);
		WriteByte(0);
		WriteInt(0);
		WriteInt(0);
		WriteString(msg);
		EndWrite();
	}
}

void ClientConnection::OnLoginRequestNickName()
{
	if (state == CS_Authentication)
	{
		BeginWrite();
		WriteInt(CLogin_NeedNickName);
		WriteByte(0);
		WriteInt(0);
		WriteInt(0);
		EndWrite();
		state = CS_WaitNickName;
	}
}

static bool is_security_url(char * url)
{
	while (*url)
	{
		char ch = *url;

		if ((ch >= 'a' && ch <= 'z') ||
			(ch >= 'A' && ch <= 'Z') ||
			(ch >= '0' && ch <= '9') ||
			ch == '_')
			url ++;
		else
			return false;
	}

	return true;
}

// request
void ClientConnection::OnRequestRPC()
{
	int id;
	char buff1[256];
	char buff2[4096]; 

	ReadInt(id);

	if (rpc_call_id)
	{
		BeginWrite();
		WriteByte(SM_ResponseRPC);
		WriteInt(id);
		WriteStringf("error = %d", 500);
		EndWrite();
		return;
	}

	ReadString(buff1, sizeof(buff1));

	if (!is_security_url(buff1))
	{
		BeginWrite();
		WriteByte(SM_ResponseRPC);
		WriteInt(id);
		WriteStringf("error = %d", 400);
		EndWrite();
		return;
	}

	RpcRequest * request = server.rpc.Allocate(RpcQueue::kClientQueue);

	if (!request)
	{
		BeginWrite();
		WriteByte(SM_ResponseRPC);
		WriteInt(id);
		WriteStringf("error = %d", 500);
		EndWrite();
		return;
	}

	switch (state)
	{
	case CS_Login:	snprintf(buff2, sizeof(buff2), "l_%s", buff1); break;
	case CS_Lobby:	snprintf(buff2, sizeof(buff2), "c_%s", buff1); break;
	default:		snprintf(buff2, sizeof(buff2), "x_%s", buff1); break;
	}

	request->BeginWrite();
	request->WriteTextRPC(buff2, &ClientRPCCallback);
	request->WriteInt(id);
	request->WriteInt(uid);
	request->EndRPCUserdata();

	switch (state)
	{
	case CS_Lobby:	request->WriteParameterf("cid", "%d", character_id);
	case CS_Login:	request->WriteParameterf("uid", "%d", user_id);
	}

	for(;;)
	{
		int size = ReadString(buff1, sizeof(buff1));

		if (size > 0)
		{
			size = ReadString(buff2, sizeof(buff2));
			request->WriteParameter(buff1, buff2, size);
		}
		else
		{
			break;
		}
	}

	request->EndWrite();
	rpc_call_id = request->uid;
}

// request chat
void ClientConnection::OnRequestChat()
{
	char to[character_name_length];
	char msg[chat_length];

	ReadString(to, sizeof(to));
	ReadString(msg, sizeof(msg));
	
	bool can_talk = gag.Send();
	bool is_banned = server.banned_userid.find(user_id) != server.banned_userid.end();

	// gag check
	if (can_talk && !is_banned)
	{
		// dict match
		DictMatch::Replace(msg);

		if (to[0] == '/')
		{
			switch (to[1])
			{
			case 'l':
				if (to[2] == 0)
				{
					ClientConnection * leader = server.GetClient(team_leader_uid);

					if (leader)
					{
						for (int i = 0; i < elementsof(leader->team_members); i ++)
						{
							ClientConnection * client = server.GetClient(leader->team_members[i]);

							if (client)
							{
								client->NotifyChat(to, character_name, msg);
							}
						}
						return;
					}

					NotifyChat("/no_l", to, msg);
				}
				break;

			case 'z':
				if (to[2] == 0)
				{
					if (character_group[0])
					{
						ClientGroupMap::iterator it = server.online_client_group_map.lower_bound(character_group);
						ClientGroupMap::iterator end = server.online_client_group_map.upper_bound(character_group);

						while (it != end)
						{
								it->second->NotifyChat(to, character_name, msg);

							++it;
						}
						return;
					}

					NotifyChat("/no_z", to, msg);
				}
				break;
			}
		}
		else
		{
			ClientConnection * client = server.GetClientByName(to);

			if (client /* && client != this */)
			{
				client->NotifyChat(to, character_name, msg);
				NotifyChat(to, character_name, msg);
			}
			else
			{
				NotifyChat("/offline", to, msg);
			}
		}
	}
	else if (is_banned)
	{
		NotifyChat("/banned", "", "");
	}
	else if (!can_talk)
	{
		NotifyChat("/gag", "", "");
	}
}

// on request enter lobby
void ClientConnection::OnRequestEnterLobby()
{
	OnEnterLobby();
}

// on enter lobby
void ClientConnection::OnEnterLobby()
{
	state = CS_Lobby;

	// update online character map
	server.online_client_map[character_name] = this;
	if (character_group[0])
		server.online_client_group_map.insert(ClientGroupMap::value_type(character_group, this));

	// response enter lobby
	BeginWrite();
	WriteByte(SM_ResponseEnterLobby);
	WriteByte(first_game);
	WriteByte(is_vip);
	WriteByte(net_bar_level);
	WriteByte(business_card);
	WriteByte(is_gm);
	WriteString(head_icon);
	WriteByte(is_PlayerCheckToday);
	
	int size = server.bill_board_list.size();
	WriteInt(size);
	for (int i = 0;i < size ;i++ )
	{
		const std::string & str = server.bill_board_list[i];
		WriteString(str.c_str());
	}
	
	//WriteInt(character_id);
	//WriteString(character_name);
	//WriteString(character_group);
	//WriteInt(character_level);
	//WriteString(character_profile.c_str());
	EndWrite();

	// notify client update level list
	NotifyUpdateLevelList();

	// update server list
	ResponseRefreshServerList();

	log_write(LOG_DEBUG4, "uid : %x, cid : %d, name : %s, client enter lobby", uid, character_id, character_name);
}

// on request leave lobby
void ClientConnection::OnRequestLeaveLobby()
{
	OnLeaveLobby();
}

// on leave lobby
void ClientConnection::OnLeaveLobby()
{
	// notify offline
	if (state >= CS_Lobby)
	{
		// leave channel
		LeaveChannel();

		// leave server
		LeaveServer();

		// notify character offline
		RpcRequest * request = server.rpc.Allocate(RpcQueue::kLoginQueue);

		if (request)
		{
			request->BeginWrite();
			request->WriteBinaryRPC("s_character_offline");
			request->EndRPCUserdata();
			request->WriteInt(user_id);
			request->WriteInt(character_id);
			request->WriteString(inet_ntoa(client_ip));
			request->EndWrite();
		}

		// leave team
		if (team_leader_uid)
		{
			ClientConnection * leader = server.client_pool.Get(team_leader_uid);

			if (leader)
				leader->TeamMemberLeave(this);
		}

		// update online character map
		server.online_client_map.erase(character_name);

		// update online character group map
		if (character_group[0])
		{
			ClientGroupMap::iterator it = server.online_client_group_map.lower_bound(character_group);
			ClientGroupMap::iterator end = server.online_client_group_map.upper_bound(character_group);

			while (it != end)
			{
				if (it->second == this)
				{
					server.online_client_group_map.erase(it);
					break;
				}
				++it;
			}
		}
	}

	character_id = 0;
	character_server_id = 0;
	character_channel_id = 0;
	character_room_id = 0;
	character_gender = 0;
	character_name[0] = 0;
	character_group[0] = 0;
	fcm_online_minutes = 0;
	fcm_offline_minutes = 0;

	team_leader_uid = 0;
	memset(team_members, 0, sizeof(team_members));

	if (m_dwMatchingTeamGroupId)
	{
		MatchingTeamGroup* pMatchGroup = server.m_pollMatchingTeamGroups.Get(m_dwMatchingTeamGroupId);
		if (pMatchGroup)
		{
			pMatchGroup->DelCharacter(this);
		}
	}
	
	state = CS_Login;

	if (IsConnected())
	{
		BeginWrite();
		WriteByte(SM_ResponseLeaveLobby);
		EndWrite();

		log_write(LOG_DEBUG4, "uid : %x, cid : %d, name : %s, client leave lobby", uid, character_id, character_name);
	}
}


// on request channel connect
void ClientConnection::OnRequestChannelConnect()
{
	int result = kErrorNone;
	int channel_id;
	ReadInt(channel_id);
	ChannelInfo * channel = server.GetChannelInfo(character_server_id, channel_id);

	if (!channel || !channel->connection || !channel->connection->IsReady())
	{
		result = kErrorProxyChannelConnect;
	}

	// check level
	else if (character_level > channel->max_character_level)
	{
		result = kErrorProxyChannelConnectLevel;
	}
	
	else if (character_level < channel->min_character_level)
	{
		result = kErrorProxyChannelConnectLevelLow;
	}
	
	// check number
	else if (channel->connection->client_count >= channel->online_max)
	{
		result = kErrorProxyChannelConnectClientCount;
	}

	// already in channel
	else if (character_channel_id == channel_id)
	{
		result = kErrorProxyChannelConnectAlreadyIn;
	}

	else
	{
		// leave channel
		ChannelConnection * old_channel = server.GetChannel(character_server_id, character_channel_id);
		if (old_channel)
			old_channel->RequestClientLeave(uid, uid_in_channel);

		BeginWrite();
		WriteByte(SM_ResponseChannelConnect);
		WriteInt(result);
		WriteInt(channel->id);
		WriteShort(channel->connection->client_port);
		WriteString(channel->connection->address);
		WriteByte(channel->istcp);
		EndWrite();
		log_write(LOG_DEBUG4, "uid : %x, cid : %d, name : %s, client connect channel : channel id %d", uid, character_id, character_name, channel_id);
		return;
	}

	BeginWrite();
	WriteByte(SM_ResponseChannelConnect);
	WriteInt(result);
	EndWrite();
	log_write(LOG_DEBUG3, "uid : %x, cid : %d, name : %s, client connect channel failed : channel id %d, result : %d", uid, character_id, character_name, channel_id, result);
}

// notify chat
void ClientConnection::NotifyChat(const char * to, const char * name, const char * msg)
{
	BeginWrite();
	WriteByte(SM_NotifyChat);
	WriteString(to);
	WriteString(name);
	WriteString(msg);
	EndWrite();
}

void ClientConnection::NotifyLoopMsg(InfoConnection * info)
{
	if (info)
	{
		char name[character_name_length];
		int size;

		info->ReadInt(size);

		BeginWrite();
		WriteByte(SM_NotifyLoopMsg);
		WriteInt(size);
		for (uint i = 0; i < size; i++)
		{
			info->ReadString(name,sizeof(name));
			WriteString(name);
		}
		EndWrite();
	}
	else
	{
		log_write(LOG_ERROR, "NotifyLoopMsg Info Connection Error!!");
	}
}

// notify fcm
void ClientConnection::NotifyFCM()
{
	//fcm_online_minutes = online_minutes;
	//fcm_offline_minutes = offline_minutes;

	//log_write(LOG_DEBUG4, "sdo notify fcm: user_name=%s, character_name=%s, online_minutes=%d, offline_minutes=%d",
	//		user_name, character_name, online_minutes, offline_minutes);
	log_write(LOG_DEBUG1, "notify fcm %d", fcm_online_minutes);
	BeginWrite();
	WriteByte(SM_NotifyFCM);
	WriteInt(fcm_online_minutes);
	WriteInt(fcm_offline_minutes);
	WriteInt(xlinfo.fcmflag);
	EndWrite();

	OnCharacterInfoChanged();
}

// on address changed
void ClientConnection::ChangeAddress(uint server_id, uint channel_id, uint room_id)
{
	bool changed = false;

	if (character_server_id != server_id)
	{
		character_server_id = server_id;

		if (character_server_id == 0)
		{
			channel_id = 0;
		}
		changed = true;
	}

	if (character_channel_id != channel_id)
	{
		character_channel_id = channel_id;

		if (character_channel_id == 0)
		{
			room_id = 0;
			uid_in_channel = 0;
			if (m_dwMatchingTeamGroupId)
			{
				// ��Ƶ���˳�
				MatchingTeamGroup* pMatchingGroup = server.m_pollMatchingTeamGroups.Get(m_dwMatchingTeamGroupId);
				if (pMatchingGroup)
				{
					pMatchingGroup->DelCharacter(this);
				}
			}
			
		}
		changed = true;
	}

	if (character_room_id != room_id)
	{
		character_room_id = room_id;
		character_status = 0;
		changed = true;
	}

	if (room_id == 0)
	{
		ChannelConnection* pChannel = server.GetChannel(server_id, channel_id);

		//����Ǵ�ĳ�����뿪
		if (pChannel && pChannel->IsMatchingChannel())
		{
			MatchingTeamGroup* pMatchingGroup = server.m_pollMatchingTeamGroups.Get(m_dwMatchingTeamGroupId);
			if (pMatchingGroup)
			{
				pMatchingGroup->DelCharacter(this);
			}
		}
	}
	
	if (changed)
	{
		// notify team status
		if (team_leader_uid)
		{
			ClientConnection * leader = server.client_pool.Get(team_leader_uid);

			if (leader)
			{
				leader->TeamMemberInfoChange(this);
			}
		}
	}
}

// change status
void ClientConnection::ChangeStatus(uint status)
{
	if (character_status != status)
	{
		character_status = status;

		// notify team status
		if (team_leader_uid)
		{
			ClientConnection * leader = server.client_pool.Get(team_leader_uid);

			if (leader)
			{
				leader->TeamMemberInfoChange(this);
			}
		}
	}
}

// ForceDisconnect
void ClientConnection::ForceDisconnect(const char * error_message)
{
	if (IsConnected())
	{
		BeginWrite();
		WriteByte(SM_ForceDisconnect);
		WriteString(error_message);
		EndWrite();
	}
	Disconnect();
}

// write team member info
void ClientConnection::WriteTeamMemberInfo(ClientConnection * member)
{
	if (member == NULL)
		return;

	WriteString(member->character_name);
	WriteByte(member->character_gender);
	WriteInt(member->character_level);
	WriteInt(member->character_exp);
	WriteString(member->character_group);
	WriteInt(member->character_server_id);
	WriteInt(member->character_channel_id);
	WriteInt(member->character_room_id);
	WriteInt(member->character_status);
}

// notify room preserve
void ClientConnection::NotifyRoomPreserve(uint channel_id, ushort room_id, byte slot_id, const char * invite_name)
{
	BeginWrite();
	WriteByte(SM_NotifyRoomPreserve);
	WriteInt(channel_id);
	WriteShort(room_id);
	WriteByte(slot_id);
	WriteString(invite_name);
	EndWrite();
}

// notify room preserve
void ClientConnection::NotifyRoomCancelPreserve(uint channel_id, ushort room_id, byte slot_id)
{
	BeginWrite();
	WriteByte(SM_NotifyRoomCancelPreserve);
	WriteInt(channel_id);
	WriteShort(room_id);
	WriteByte(slot_id);
	EndWrite();
}

// notify update level list
void ClientConnection::NotifyUpdateLevelList()
{
	BeginWrite();
	WriteByte(SM_NotifyUpdateLevelList);

	int* level_count = reinterpret_cast<int*>(write_position);
	WriteInt(0);

	for (std::vector<Level>::const_iterator iter = server.level_list.begin(); iter < server.level_list.end(); iter++)
	{
		if (iter->type < 100)
		{
			WriteInt(iter->level_id);
			WriteString(iter->name);
			WriteByte(iter->type);

			WriteString(iter->show_name);
			WriteString(iter->description.c_str());

			WriteByte(iter->is_vip);
			WriteByte(iter->is_new);
			
			WriteInt(iter->gai_lv);
			WriteByte(iter->is_gm);
			*level_count = *level_count + 1;
		}
	}

	EndWrite();
}

// notify rpc message
void ClientConnection::NotifyRPCMessage(int size, const char * data)
{
	BeginWrite();
	WriteByte(SM_NotifyRPCMessage);
	WriteInt(size);
	Write(data, size);
	EndWrite();
}

// notify chatgroup leave
void ClientConnection::NotifyChatGroupLeave(uint chatgroup_uid)
{
	std::set<uint>::iterator it = chatgroups.find(chatgroup_uid);
	if (it != chatgroups.end())
	{
		ChatGroup *chat_group = server.GetChatGroup(*it);
		if (chat_group && chat_group->DelCharacter(uid))
		{
			for (std::set<uint>::iterator it2 = chat_group->characters_uid.begin(); 
				it2 != chat_group->characters_uid.end(); it2++)
			{
				ClientConnection *c = server.GetClient(*it2);
				if (c)
				{
					c->BeginWrite();
					c->WriteByte(SM_NotifyChatGroupLeave);
					c->WriteInt(chatgroup_uid);
					c->WriteString(character_name);
					c->EndWrite();
				}
			}
			
			if (chat_group->characters_uid.size() == 0)
			{
				log_write(LOG_DEBUG3, "delete chatgroup[%s, %d]", chat_group->creater_name, chat_group->uid);
			
				server.chatgroup_pool.Free(chat_group->uid);
			}
		}
		
		chatgroups.erase(it);
	}
}

// notify chatgroup call
void ClientConnection::NotifyChatGroupCall(uint chatgroup_uid, const char *name, const char *msg)
{
	std::set<uint>::iterator it = chatgroups.find(chatgroup_uid);
	if (it != chatgroups.end())
	{
		ChatGroup *chat_group = server.GetChatGroup(*it);
		if (chat_group)
		{
			if (gag.Send())
			{
				for (std::set<uint>::iterator it2 = chat_group->characters_uid.begin(); 
					it2 != chat_group->characters_uid.end(); it2++)
				{
					ClientConnection *c = server.GetClient(*it2);
					if (c)
					{
						c->BeginWrite();
						c->WriteByte(SM_NotifyChatGroupCall);
						c->WriteInt(chatgroup_uid);
						c->WriteString(name);
						c->WriteString(msg);
						c->EndWrite();
					}
				}
			}
			else
			{
				NotifyChat("/gag", "", "");
			}
		}
	}
}

// notify apex client
void ClientConnection::NotifyApexClient(const char *pBuf, int nBufLen)
{
	if (pBuf && nBufLen > 0)
	{
		BeginWrite();
		WriteByte(SM_RequestApex);
		WriteInt(nBufLen);
		Write(pBuf, nBufLen);
		EndWrite();
	}
}

// notify battlegroupinfo change
void ClientConnection::NotifyBattleGroupInfo(BattleGroup *battlegroup)
{
	if (battlegroup)
	{
		BeginWrite();
		WriteByte(SM_NotifyBattleGroupInfo);
		BattleGroup::WriteBattleGroupInfo(battlegroup, *this);
		EndWrite();
	}
}

void ClientConnection::NotifyUpdateBillBoardInfo(const std::string & str, bool flag)
{
	if (state >= CS_Lobby)
	{

		BeginWrite();
		WriteByte(SM_UpdateBillBoardInfo);
		WriteString(str.c_str());
		WriteByte(flag == 0 ? 0 : 1);
		EndWrite();
	}
}

// notify battlegroup leave
void ClientConnection::NotifyBattleGroupLeave(uint battlegroup_uid)
{
	BattleGroup *battlegroup = server.battlegroup_pool.Get(battlegroup_uid);
	if (battlegroup && battlegroup->DelCharacter(this))
	{
		for (std::set<uint>::iterator itr = battlegroup->characters_uid.begin(); 
			itr != battlegroup->characters_uid.end(); itr++)
		{
			ClientConnection *c = server.GetClient(*itr);
			if (c)
			{
				c->BeginWrite();
				c->WriteByte(SM_NotifyBattleGroupChange);
				c->WriteInt(battlegroup_uid);
				c->EndWrite();
			}
		}
	}
}

// notify battlegroup leave
void ClientConnection::NotifyBattleGroupKick(uint battlegroup_uid)
{
	BeginWrite();
	WriteByte(SM_NotifyBattleGroupKick);
	WriteInt(battlegroup_uid);
	EndWrite();
}

// notify battlegroup searching
void ClientConnection::NotifyBattleGroupSearching(uint battlegroup_uid)
{
	BattleGroup *battlegroup = server.battlegroup_pool.Get(battlegroup_uid);
	if (battlegroup)
	{
		for (std::set<uint>::iterator itr = battlegroup->characters_uid.begin(); 
			itr != battlegroup->characters_uid.end(); itr++)
		{
			ClientConnection *c = server.GetClient(*itr);
			if (c)
			{
				c->BeginWrite();
				c->WriteByte(SM_NotifyBattleGroupSearching);
				c->WriteInt(battlegroup_uid);
				c->WriteByte(battlegroup->IsSearching());
				c->EndWrite();
			}
		}
	}
}

// notify battlegroup game start
void ClientConnection::NotifyBattleGroupGameStart(uint battlegroup_uid, 
												uint server_id, uint channel_id, uint room_id, 
												byte change_room, byte is_challenge, uint group_id1, uint group_id2)
{
	BattleGroup *battlegroup = server.battlegroup_pool.Get(battlegroup_uid);
	if (battlegroup)
	{
		for (std::set<uint>::iterator itr = battlegroup->characters_uid.begin(); 
			itr != battlegroup->characters_uid.end(); itr++)
		{
			ClientConnection *c = server.GetClient(*itr);
			if (c)
			{
				c->BeginWrite();
				c->WriteByte(SM_NotifyBattleGroupGameStart);
				c->WriteInt(battlegroup_uid);
				c->WriteByte(change_room);
				c->WriteInt(server_id);
				c->WriteInt(channel_id);
				c->WriteInt(room_id);
				c->WriteByte(is_challenge);
				c->WriteInt(group_id1);
				c->WriteInt(group_id2);
				c->EndWrite();
			}
		}
	}
}

void ClientConnection::NotifyHageBattleGroupGameStart( uint battlegroup_uid, uint server_id, uint channel_id, uint room_id, byte change_room, byte is_challenge, uint group_id1, uint group_id2 )
{
	HageBattleGroup *battlegroup = server.m_pollHageBattleGroup.Get(battlegroup_uid);
	if (battlegroup)
	{
		for (std::map<uint, bool>::iterator itr = battlegroup->mapCharactersUid.begin(); 
			itr != battlegroup->mapCharactersUid.end(); itr++)
		{
			if (itr->second)
			{
				ClientConnection *c = server.GetClientById(itr->first);
				if (c)
				{
					if (c->character_server_id != server_id || c->character_channel_id != channel_id)
					{
						log_write(LOG_DEBUG1, "%s, %s, server id : %d, channel id : %d, name : %s, roomid : %d, changeroom : %d not in channel role server id : %d, channel id : %d", __FILE__, __FUNCTION__, server_id, channel_id, c->character_name, room_id, change_room, c->character_server_id, c->character_channel_id);
						continue;
					}
					
					c->BeginWrite();
					// todo �ǲ���Ҫ��Э���
					c->WriteByte(SM_NotifyHageBattleGroupGameStart);
					c->WriteInt(battlegroup_uid);
					c->WriteByte(change_room);
					c->WriteInt(server_id);
					c->WriteInt(channel_id);
					c->WriteInt(room_id);
					c->WriteByte(is_challenge);
					c->WriteInt(group_id1);
					c->WriteInt(group_id2);
					c->EndWrite();
					log_write(LOG_DEBUG1, "%s, %s, server id : %d, channel id : %d, name : %s, roomid : %d, changeroom : %d", __FILE__, __FUNCTION__, server_id, channel_id, c->character_name, room_id, change_room);
				}
			}
		}
	}
}

// write clientbaseinfo
void ClientConnection::WriteClientBaseInfo(BinaryStream &stream)
{
	stream.WriteInt(uid);
	stream.WriteString(character_name);
	stream.WriteString(head_icon);
	stream.WriteInt(character_level);
	stream.WriteInt(character_exp);
	stream.WriteByte(is_vip);
	stream.WriteByte(net_bar_level);
	stream.WriteByte(business_card);
	stream.WriteByte(is_gm);
}

// join team
int ClientConnection::TeamMemberJoin(ClientConnection * member)
{
	bool join_team = false;

	if (member == NULL)
		return kErrorProxyTeamJoinMemberError;
	
	if (team_leader_uid != uid && team_leader_uid != 0)
		return kErrorProxyTeamJoinLeaderIdError;

	// become a team leader
	if (team_leader_uid == 0)
	{
		memset(team_members, 0, sizeof(team_members));
		team_members[0] = uid;
		team_leader_uid = uid;

		BeginWrite();
		WriteByte(SM_NotifyTeamMemberJoin);
		WriteTeamMemberInfo(this);
		EndWrite();

		join_team = true;
	}

	for (int i = 0; i < elementsof(team_members); i ++)
	{
		if (team_members[i] == 0)
		{
			team_members[i] = member->uid;
			member->team_leader_uid = uid;

			for (int j = 0; j < elementsof(team_members); j ++)
			{
				ClientConnection * client = server.client_pool.Get(team_members[j]);

				if (client && client->IsOnline())
				{
					if (client != member)
					{
						client->BeginWrite();
						client->WriteByte(SM_NotifyTeamMemberJoin);
						client->WriteTeamMemberInfo(member);
						client->EndWrite();
					}

					member->BeginWrite();
					member->WriteByte(SM_NotifyTeamMemberJoin);
					member->WriteTeamMemberInfo(client);
					member->EndWrite();
				}
			}

			if (join_team)
			{
				BeginWrite();
				WriteByte(SM_ResponseTeamJoin);
				WriteInt(kErrorNone);
				WriteString(character_name);
				EndWrite();
			}

			return kErrorNone;
		}
	}

	return kErrorProxyTeamJoinTeamFull;
}

// leave team
bool ClientConnection::TeamMemberLeave(ClientConnection * member)
{
	if (member == NULL)
		return false;
	
	if (team_leader_uid != uid && team_leader_uid != 0)
		return false;

	if (member->team_leader_uid != uid)
		return false;

	for (int i = 0; i < elementsof(team_members); i++)
	{
		if (team_members[i] == member->uid)
		{
			team_members[i] = 0;

			member->team_leader_uid = 0;
			member->BeginWrite();
			member->WriteByte(SM_NotifyTeamLeave);
			member->EndWrite();
			break;
		}
	}

	uint member_count = 0;
	for (int i = 0; i < elementsof(team_members); i++)
	{
		ClientConnection * client = server.client_pool.Get(team_members[i]);

		if (client && client->IsOnline())
		{
			member_count++;

			client->BeginWrite();
			client->WriteByte(SM_NotifyTeamMemberLeave);
			client->WriteString(member->character_name);
			client->EndWrite();
		}
	}

	if (member_count <= 1)
	{
		for (int i = 0; i < elementsof(team_members); i++)
		{
			ClientConnection * client = server.client_pool.Get(team_members[i]);

			if (client && client->IsOnline())
			{
				client->team_leader_uid = 0;
				client->BeginWrite();
				client->WriteByte(SM_NotifyTeamLeave);
				client->EndWrite();
			}
		}

		memset(team_members, 0, sizeof(team_members));
	}
}

// team member info change
bool ClientConnection::TeamMemberInfoChange(ClientConnection * member)
{
	if (member == NULL)
		return false;
	
	if (member->team_leader_uid != uid)
		return false;

	for (int i = 0; i < elementsof(team_members); i++)
	{
		ClientConnection * client = server.client_pool.Get(team_members[i]);

		if (client && client->IsOnline())
		{
			client->BeginWrite();
			client->WriteByte(SM_NotifyTeamMemberInfo);
			client->WriteTeamMemberInfo(member);
			client->EndWrite();
		}
	}
}

// change leader
bool ClientConnection::TeamChangeLeader(ClientConnection * leader)
{
	if (team_leader_uid != uid)
		return false;

	if (leader == NULL)
		return false;

	if (leader == this)
		return false;

	for (int i = 0; i < elementsof(team_members); i++)
	{
		if (team_members[i] == leader->uid)
		{
			memset(leader->team_members, 0, sizeof(leader->team_members));

			for (int j = 0; j < elementsof(team_members); j++)
			{
				ClientConnection * client = server.client_pool.Get(team_members[j]);

				if (client)
				{
					leader->team_members[j] = client->uid;
					client->team_leader_uid = leader->uid;

					client->BeginWrite();
					client->WriteByte(SM_NotifyTeamChangeLeader);
					client->WriteString(leader->character_name);
					client->EndWrite();
				}
			}

			return true;
		}
	}
}

// on team invite
void ClientConnection::OnRequestTeamInvite()
{
	int result = kErrorProxyTeamInvite;
	char name[character_name_length];
	ReadString(name, sizeof(name));

	ClientConnection * client = server.GetClientByName(name);

	if (team_leader_uid == 0 || team_leader_uid == uid)
	{
		bool is_team_full = true;

		for (int i = 0; i < elementsof(team_members); i ++)
		{
			if (team_members[i] == 0)
			{
				is_team_full = false;
				break;
			}
		}

		if (is_team_full == false)
		{
			if (client)
			{
				if (client != this)
				{
					if (client->team_leader_uid == 0)
					{
						client->BeginWrite();
						client->WriteByte(SM_NotifyTeamInvite);
						client->WriteString(character_name);
						client->WriteInt(uid);
						client->EndWrite();
						result = kErrorNone;
					}
					else
					{
						if (client->team_leader_uid == team_leader_uid)
							result = kErrorProxyTeamInviteAlreadyInTeam;
						else
							result = kErrorProxyTeamInviteAlreadyInOtherTeam;
					}
				}
				else
					result = kErrorProxyTeamInviteSelf;
			}
			else
			{
				result = kErrorProxyTeamInviteClientError;
			}
		}
		else
		{
			result = kErrorProxyTeamInviteTeamFull;
		}
	}
	else
	{
		result = kErrorProxyTeamInviteNotTeamLeader;
	}

	BeginWrite();
	WriteByte(SM_ResponseTeamInvite);
	WriteInt(result);
	WriteString(name);
	EndWrite();
}

// on team join
void ClientConnection::OnRequestTeamJoin()
{
	int result = kErrorProxyTeamJoin;
	uint uid;
	char name[character_name_length];

	if (team_leader_uid != 0)
		return;

	ReadString(name, sizeof(name));
	ReadInt(uid);

	ClientConnection * leader = server.client_pool.Get(uid);

	if (leader && leader != this &&
		strcmp(leader->character_name, name) == 0)
	{
		if (leader->team_leader_uid == 0 ||
			leader->team_leader_uid == leader->uid)
		{
			result = leader->TeamMemberJoin(this);
		}
		else
		{
			result = kErrorProxyTeamJoinLeaderIdError;
		}
	}
	else
	{
		result = kErrorProxyTeamJoinLeaderError;
	}

	BeginWrite();
	WriteByte(SM_ResponseTeamJoin);
	WriteInt(result);
	if (result == kErrorNone)
		WriteString(leader->character_name);
	EndWrite();
}

// on request team refuse
void ClientConnection::OnRequestTeamRefuse()
{
	byte ret;
	uint uid;
	char name[character_name_length];

	if (team_leader_uid != 0)
		return;

	ReadString(name, sizeof(name));
	ReadInt(uid);

	ClientConnection * leader = server.client_pool.Get(uid);

	if (leader && leader != this &&
		strcmp(leader->character_name, name) == 0)
	{
		leader->BeginWrite();
		leader->WriteByte(SM_NotifyTeamRefuse);
		leader->WriteString(character_name);
		leader->EndWrite();
	}
}

// on team leave
void ClientConnection::OnRequestTeamLeave()
{
	ClientConnection * leader = server.client_pool.Get(team_leader_uid);

	if (leader)
	{
		uint member_count = 0;
		ClientConnection * new_leader = NULL;

		for (int i = 0; i < elementsof(team_members); i++)
		{
			ClientConnection * client = server.client_pool.Get(team_members[i]);

			if (client)
			{
				member_count++;

				if (new_leader == NULL && leader == this && client != this)
					new_leader = client;
			}
		}

		if (member_count > 2)
		{
			if (new_leader)
			{
				leader->TeamChangeLeader(new_leader);
				leader = new_leader;
			}
		}

		leader->TeamMemberLeave(this);
	}
}

// on team kick
void ClientConnection::OnRequestTeamKick()
{
	char name[character_name_length];
	ReadString(name, sizeof(name));

	if (team_leader_uid == uid)
	{
		ClientConnection * client = server.GetClientByName(name);

		if (client)
		{
			for (int i = 0; i < elementsof(team_members); i++)
			{
				if (team_members[i] == client->uid)
				{
					TeamMemberLeave(client);
					return;
				}
			}
		}
	}
}

// on team change leader
void ClientConnection::OnRequestTeamChangeLeader()
{
	char name[character_name_length];
	ReadString(name, sizeof(name));

	if (team_leader_uid == uid)
	{
		ClientConnection * client = server.GetClientByName(name);

		if (client)
			TeamChangeLeader(client);
	}
}

// on request team call
void ClientConnection::OnRequestTeamCall()
{
	char name[character_name_length];
	ReadString(name, sizeof(name));
	int result = kErrorProxyTeamCall;

	if (team_leader_uid == uid)
	{
		ClientConnection * client = server.GetClientByName(name);

		if (client)
		{
			for (int i = 0; i < elementsof(team_members); i++)
			{
				if (team_members[i] == client->uid)
				{
					client->BeginWrite();
					client->WriteByte(SM_NotifyTeamCall);
					client->WriteString(character_name);
					client->WriteInt(character_server_id);
					client->WriteInt(character_channel_id);
					client->WriteInt(character_room_id);
					client->EndWrite();
					result = kErrorNone;
					break;
				}
			}
		}
	}

	BeginWrite();
	WriteByte(SM_ResponseTeamCall);
	WriteInt(result);
	EndWrite();
}

// on request search room
void ClientConnection::OnRequestSearchRoom()
{
	if (search_channel_id == 0)
	{
		ReadByte((byte&)search_room_options.all_channels);
		ReadInt(search_room_options.game_type);
		ReadInt(search_room_options.level_id);
		ReadByte(search_room_options.num_clients);
		ReadByte(search_room_options.playing);
		ReadByte(search_room_options.waiting);

		if (search_room_options.all_channels)
		{
			search_server_id = 1;
			search_channel_id = 1;
		}
		else
		{
			search_server_id = character_server_id;
			search_channel_id = character_channel_id;
		}

		ContinueSearchRoom();
	}
	else
	{
		BeginWrite();
		WriteByte(SM_ResponseSearchRoom);
		WriteByte(2);
		EndWrite();
	}
}

// on request cancel search room
void ClientConnection::OnRequestCancelSearchRoom()
{
	search_channel_id = 0;
}

// on request refuse preserve
void ClientConnection::OnRequestRefusePreserve()
{
	char leader[character_name_length];
	int channel_id;
	int room_id;
	int result;

	ReadString(leader, sizeof(leader));
	ReadInt(channel_id);
	ReadInt(room_id);
	ReadInt(result);

	ClientConnection * client = server.GetClientByName(leader);

	if (client)
	{
		client->BeginWrite();
		client->WriteByte(SM_NotifyRefusePreserve);
		client->WriteInt(result);
		client->WriteString(character_name);
		client->EndWrite();
	}
}

// on request enter server
void ClientConnection::OnRequestEnterServer()
{
	int result = kErrorNone;
	uint server_id;

	ReadInt(server_id);
	ServerInfo * info = server.GetServerInfo(server_id);

	if (!info)
	{
		result = kErrorProxyEnterServer;
	}
	// check level Hight
	else if (character_level > info->max_character_level)
	{
		result = kErrorProxyEnterServerLevelHight;
	}
	
	// check level low
	else if (character_level < info->min_character_level)
	{
		result = kErrorProxyEnterServerLevelLow;
	}
	
	// check fightnum low
	else if (fightnum < info->min_fightnum)
	{
		result = kErrorBattlePower;
	}

	// check number
	else if (info->online_count >= info->online_max)
	{
		result = kErrorProxyEnterServerClientCount;
	}
	else
	{
		if (server_id != character_server_id)
		{
			ServerInfo * old_server = server.GetServerInfo(character_server_id);

			LeaveChannel();
			ChangeAddress(server_id, 0, 0);
			ResponseRefreshChannelList();

			if (old_server)
			{
				old_server->online_count--;
				
				if (old_server->online_count < 0)
				{
					old_server->online_count = 0;
					
					log_write(LOG_WARNING, "online_count < 0");
				}
			}

			info->online_count++;
		}
	}

	BeginWrite();
	WriteByte(SM_ResponseEnterServer);
	WriteInt(result);
	WriteInt(server_id);
	WriteString(info ? info->name : "");
	EndWrite();
}

// on request leave server
void ClientConnection::OnRequestLeaveServer()
{
	uint server_id = character_server_id;
	LeaveServer();

	BeginWrite();
	WriteByte(SM_ResponseLeaveServer);
	WriteInt(server_id);
	EndWrite();
	ResponseRefreshServerList();
}

// on request refresh server list
void ClientConnection::OnRequestRefreshServerList()
{
	ResponseRefreshServerList();
}

// on request refresh channel list
void ClientConnection::OnRequestRefreshChannelList()
{
	ResponseRefreshChannelList();
}

// on request character adress
void ClientConnection::OnRequestCharacterAddress()
{
	char name[character_name_length];
	uint userdata;
	byte online = 0;
	uint server_id = 0;
	uint channel_id = 0;
	uint room_id = 0;
	const char * server_name = "";
	const char * channel_name = "";

	ReadInt(userdata);
	ReadString(name, sizeof(name));

	ClientConnection * c = server.GetClientByName(name);
	if (c)
	{
		online = 1;
		server_id = c->character_server_id;
		channel_id = c->character_channel_id;
		room_id = c->character_room_id;

		ServerInfo * si = server.GetServerInfo(server_id);
		ChannelInfo * ci = server.GetChannelInfo(server_id, channel_id);

		if (si)
			server_name = si->name;

		if (ci)
			channel_name = ci->name;
	}

	BeginWrite();
	WriteByte(SM_ResponseCharacterAddress);
	WriteInt(userdata);
	WriteString(name);
	WriteByte(online);
	WriteInt(server_id);
	WriteInt(channel_id);
	WriteInt(room_id);
	WriteString(server_name);
	WriteString(channel_name);
	EndWrite();
}

// on save profile user
void ClientConnection::OnSaveUserProfile()
{
	char profile[4096];
	ReadString(profile, sizeof(profile));
	user_profile = profile;
	
	RpcRequest * request = server.rpc.Allocate(RpcQueue::kServerQueue);

	if (request)
	{
		request->BeginWrite();
		request->WriteBinaryRPC("s_save_user_profile");
		request->EndRPCUserdata();
		request->WriteInt(user_id);
		request->WriteString(profile);
		request->EndWrite();
	}
}

// on save profile character
void ClientConnection::OnSaveCharacterProfile()
{
	//char profile[4096];
	//ReadString(profile, sizeof(profile));
	//character_profile = profile;

	//RpcRequest * request = server.rpc.Allocate(RpcQueue::kServerQueue);

	//if (request)
	//{
	//	request->BeginWrite();
	//	request->WriteBinaryRPC("s_save_character_profile");
	//	request->EndRPCUserdata();
	//	request->WriteInt(user_id);
	//	request->WriteInt(character_id);
	//	request->WriteString(profile);
	//	request->EndWrite();
	//}
}

// on request chatgroup create
void ClientConnection::OnRequestChatGroupCreate()
{
	if (created_chatgroup == 0)
	{
		ChatGroup * chat_group = server.chatgroup_pool.Allocate();
		if (chat_group)
		{
			chat_group->Init(character_name);
			chat_group->AddCharacter(uid);
			
			chatgroups.insert(chat_group->uid);
			
			created_chatgroup = chat_group->uid;
			
			log_write(LOG_DEBUG3, "create chatgroup[%s, %d]", chat_group->creater_name, chat_group->uid);
			
			BeginWrite();
			WriteByte(SM_ResponseChatGroupCreate);
			WriteInt(chat_group->uid);
			WriteString(chat_group->creater_name);
			EndWrite();
			
			return;
		}
	}
	
	// error
	BeginWrite();
	WriteByte(SM_ResponseChatGroupCreate);
	WriteInt(0);
	WriteString("");
	EndWrite();
}

// on request chatgroup invite
void ClientConnection::OnRequestChatGroupInvite()
{
	uint chatgroup_uid;
	char name[user_name_length];
	
	ReadInt(chatgroup_uid);
	ReadString(name, sizeof(name));
	
	ChatGroup *chat_group = server.GetChatGroup(chatgroup_uid);
	ClientConnection *c = server.GetClientByName(name);
	if (chat_group && c)
	{
		c->BeginWrite();
		c->WriteByte(SM_NotifyChatGroupInvite);
		c->WriteInt(chat_group->uid);
		c->WriteString(chat_group->creater_name);
		c->WriteString(character_name);
		c->EndWrite();
	}
}

// on request chatgroup join
void ClientConnection::OnRequestChatGroupJoin()
{
	uint chatgroup_uid;
	
	ReadInt(chatgroup_uid);
	
	ChatGroup *chat_group = server.GetChatGroup(chatgroup_uid);
	if (chat_group && 
		chat_group->AddCharacter(uid))
	{
		chatgroups.insert(chat_group->uid);
		
		for (std::set<uint>::iterator it = chat_group->characters_uid.begin(); it != chat_group->characters_uid.end(); it++)
		{
			ClientConnection *c = server.GetClient(*it);
			if (c)
			{
				c->BeginWrite();
				c->WriteByte(SM_NotifyChatGroupJoin);
				c->WriteInt(chat_group->uid);
				c->WriteString(character_name);
				c->WriteByte(is_vip);
				c->WriteByte(net_bar_level);
				c->WriteByte(business_card);
				c->WriteByte(is_gm);
				c->EndWrite();
			}
		}
	}
	else
	{
		BeginWrite();
		WriteByte(SM_NotifyChatGroupJoin);
		WriteInt(0);
		WriteString("");
		WriteByte(0);
		WriteByte(0);
		WriteByte(0);
		EndWrite();
	}
}

// on request chatgroup leave
void ClientConnection::OnRequestChatGroupLeave()
{
	uint chatgroup_uid;
	
	ReadInt(chatgroup_uid);
	
	if (chatgroup_uid == created_chatgroup)
	{
		created_chatgroup = 0;
	}
	
	NotifyChatGroupLeave(chatgroup_uid);
}

// on request chatgroup call
void ClientConnection::OnRequestChatGroupCall()
{
	uint chatgroup_uid;
	char msg[chat_length];
	
	ReadInt(chatgroup_uid);
	ReadString(msg, sizeof(msg));
	
	bool is_banned = server.banned_userid.find(user_id) != server.banned_userid.end();
	
	DictMatch::Replace(msg);
	
	if (!is_banned)
	{
		NotifyChatGroupCall(chatgroup_uid, character_name, msg);
	}
	else
	{
		NotifyChat("/banned", "", "");
	}
}

// on request chatgroup member
void ClientConnection::OnRequestChatGroupMember()
{
	uint chatgroup_uid;
	
	ReadInt(chatgroup_uid);
	
	ChatGroup *chat_group = server.GetChatGroup(chatgroup_uid);
	if (chat_group)
	{
		BeginWrite();
		WriteByte(SM_ResponseChatGroupMember);
		WriteInt(chatgroup_uid);
		WriteInt((int)chat_group->characters_uid.size());
		for (std::set<uint>::iterator it = chat_group->characters_uid.begin(); 
			it != chat_group->characters_uid.end(); it++)
		{
			ClientConnection *c = server.GetClient(*it);
			if (c)
			{
				WriteInt(c->character_id);
				WriteString(c->character_name);
				WriteByte(c->is_vip);
				WriteByte(c->net_bar_level);
				WriteByte(c->business_card);
				WriteByte(c->is_gm);
			}
		}
		EndWrite();
	}
}

// on notify multichat
void ClientConnection::OnNotifyMultiChat()
{
	char group[group_name_length];
	char msg[chat_length];
	int user_num;

	ReadString(group, sizeof(group));
	ReadString(msg, sizeof(msg));
	
	bool is_banned = server.banned_userid.find(user_id) != server.banned_userid.end();
	
	DictMatch::Replace(msg);
	
	if (!is_banned)
	{
		ReadInt(user_num);
		for (int i = 0; i < user_num; i++)
		{
			char name[user_name_length];
			
			ReadString(name, sizeof(name));
			ClientConnection *c = server.GetClientByName(name);
			if (c)
			{
				c->BeginWrite();
				c->WriteByte(SM_NotifyMultiChat);
				c->WriteString(character_name);
				c->WriteString(group);
				c->WriteString(msg);
				ServerInfo* pInfo = server.GetServerInfo(character_server_id);
				c->WriteInt(pInfo ? (int)pInfo->m_eServerType : 0);
				c->EndWrite();
			}
		}
	}
	else
	{
		NotifyChat("/banned", "", "");
	}
}

// on request apex
void ClientConnection::OnRequestApex()
{
	int data_len;
	
	ReadInt(data_len);
	if (data_len > 0)
	{
		server.apex_connection.ClientDataUpdate(user_id, (const char *)ReadData(data_len), data_len);
	}
}

// on request battlegroups
void ClientConnection::OnRequestBattleGroups()
{
	char search_group_name[group_name_length];
	uint start_count;
	uint need_count;
	byte is_searchonly;
	
	ReadString(search_group_name, sizeof(search_group_name));
	ReadInt(start_count);
	ReadInt(need_count);
	ReadByte(is_searchonly);
	
	bool search_notmy = (strlen(search_group_name) == 0);
	
	if (strlen(character_group) > 0)
	{
		BeginWrite();
		WriteByte(SM_ResponseBattleBattleGroups);
	
		uint cur_count = 0;
		uint send_count = 0;
		for (std::set<uint>::const_iterator itr = BattleGroup::battlegroup_list.begin(); 
			itr != BattleGroup::battlegroup_list.end(); itr++)
		{
			BattleGroup *battlegroup = server.battlegroup_pool.Get(*itr);
			if (battlegroup)
			{
				if (is_searchonly && !battlegroup->IsSearching())
					continue;
				
				bool find = false;
				if (search_notmy)
					find = (strcmp(battlegroup->group_name, character_group) != 0);
				else
					find = (strcmp(battlegroup->group_name, search_group_name) == 0);
				if (find)
				{
					if (cur_count >= start_count)
					{
						if (send_count < need_count)
						{
							BattleGroup::WriteBattleGroupInfo(battlegroup, *this);
							
							send_count++;
						}
						else
							break;
					}
					
					cur_count++;
				}
			}
		}
		
		WriteInt(0);
		EndWrite();
	}
	else
	{
		BeginWrite();
		WriteByte(SM_ResponseBattleBattleGroups);
		WriteInt(0);
		EndWrite();
	}
}

// on request battlegroup create
void ClientConnection::OnRequestBattleGroupCreate()
{
	uint server_id;
	uint channel_id;
	uint room_id;
	
	RoomOption roomop;
	
	ReadInt(server_id);
	ReadInt(channel_id);
	ReadInt(room_id);
	ReadRoomOption(*this, roomop);
	
	if (server_id != character_server_id || 
		channel_id != character_channel_id || 
		room_id != character_room_id)
	{
		BeginWrite();
		WriteByte(SM_ResponseBattleGroupCreate);
		WriteInt(0);
		EndWrite();
		
		return;
	}

	if (joined_battlegroup == 0)
	{
		BattleGroup *battlegroup = server.battlegroup_pool.Allocate();
		if (battlegroup && battlegroup->Initialize(this, roomop))
		{
			log_write(LOG_DEBUG3, "create battlegroup[%d, %d]", uid, joined_battlegroup);
			
			BeginWrite();
			WriteByte(SM_ResponseBattleGroupCreate);
			WriteInt(joined_battlegroup);
			EndWrite();
			
			return;
		}
		
		if (battlegroup)
			server.battlegroup_pool.Free(battlegroup->uid);
	}
	
	BeginWrite();
	WriteByte(SM_ResponseBattleGroupCreate);
	WriteInt(0);
	EndWrite();
}

// on request battlegroup invite
void ClientConnection::OnRequestBattleGroupInvite()
{
	char name[user_name_length];
	
	ReadString(name, sizeof(name));
	
	ClientConnection *c = server.GetClientByName(name);
	BattleGroup *battlegroup = server.battlegroup_pool.Get(joined_battlegroup);
	if (c && c != this && battlegroup && battlegroup->ownerclient_uid == uid)
	{
		c->BeginWrite();
		c->WriteByte(SM_NotifyBattleGroupInvite);
		c->WriteInt(joined_battlegroup);
		c->WriteString(character_name);
		c->EndWrite();
	
		return;
	}
}

// on request battlegroup join
void ClientConnection::OnRequestBattleGroupJoin()
{
	uint battlegroup_uid;
	
	ReadInt(battlegroup_uid);
	
	BattleGroup *battlegroup = server.battlegroup_pool.Get(battlegroup_uid);
	
	if (battlegroup && battlegroup_uid == joined_battlegroup)
	{
		return;
	}
	
	if (battlegroup && 
		!battlegroup->IsPlaying() &&
		battlegroup->AddCharacter(this))
	{
		BeginWrite();
		WriteByte(SM_ResponseBattleGroupJoin);
		WriteByte(1);
		WriteInt(battlegroup_uid);
		WriteInt(battlegroup->server_id);
		WriteInt(battlegroup->channel_id);
		WriteInt(battlegroup->room_id);
		EndWrite();
		
		for (std::set<uint>::iterator it = battlegroup->characters_uid.begin(); 
			it != battlegroup->characters_uid.end(); it++)
		{
			ClientConnection *c = server.GetClient(*it);
			if (c)
			{
				c->BeginWrite();
				c->WriteByte(SM_NotifyBattleGroupChange);
				c->WriteInt(battlegroup->uid);
				c->EndWrite();
			}
		}
	}
	else
	{
		BeginWrite();
		WriteByte(SM_ResponseBattleGroupJoin);
		WriteByte(0);
		WriteInt(battlegroup_uid);
		WriteInt(-1);
		WriteInt(-1);
		WriteInt(-1);
		EndWrite();
	}
}

// on request battlegroup leave
void ClientConnection::OnRequestBattleGroupLeave()
{
	uint battlegroup_uid;
	
	ReadInt(battlegroup_uid);
	
	//if (battlegroup_uid == joined_battlegroup)
	if (joined_battlegroup != 0)
	{
		NotifyBattleGroupLeave(joined_battlegroup);
	}
}

// on request battlegroup info
void ClientConnection::OnRequestBattleGroupInfo()
{
	uint battlegroup_uid;
	
	ReadInt(battlegroup_uid);
	
	BattleGroup *battlegroup = server.battlegroup_pool.Get(battlegroup_uid);
	if (battlegroup)
	{
		NotifyBattleGroupInfo(battlegroup);
	}
}

// on request battlegroup ready
void ClientConnection::OnRequestBattleGroupReady()
{
	BattleGroup *battlegroup = server.battlegroup_pool.Get(joined_battlegroup);
	if (battlegroup)
	{
		if (battlegroup_state == 0)
			battlegroup_state = 1;
		else if (battlegroup_state == 1)
			battlegroup_state = 0;
		
		battlegroup->is_searching = false;
		
		for (std::set<uint>::iterator it = battlegroup->characters_uid.begin(); 
			it != battlegroup->characters_uid.end(); it++)
		{
			ClientConnection *c = server.GetClient(*it);
			if (c)
			{
				c->BeginWrite();
				c->WriteByte(SM_NotifyBattleGroupChange);
				c->WriteInt(battlegroup->uid);
				c->EndWrite();
			}
		}
	}
}

// on request battlegroup start search
void ClientConnection::OnRequestBattleGroupStartSearch()
{
	byte is_cancel;
	
	ReadByte(is_cancel);
	
	BattleGroup *battlegroup = server.battlegroup_pool.Get(joined_battlegroup);
	if (battlegroup && 
		battlegroup->ownerclient_uid == uid)
	{
		byte is_ok = false;
		
		if (is_cancel && battlegroup->IsSearching())
		{
			BeginWrite();
			WriteByte(SM_ResponseBattleGroupStartSearch);
			WriteByte(0);
			WriteInt(joined_battlegroup);
			EndWrite();
			
			battlegroup->is_searching = false;
			
			is_ok = true;
		}
		else if (!is_cancel && battlegroup->CanStartSearch())
		{
			BeginWrite();
			WriteByte(SM_ResponseBattleGroupStartSearch);
			WriteByte(1);
			WriteInt(joined_battlegroup);
			EndWrite();
			
			battlegroup->is_searching = true;
			battlegroup->searching_interval = appcfg.battlegroup_searching_interval;
			
			is_ok = true;
		}
		
		if (is_ok)
		{
			NotifyBattleGroupSearching(joined_battlegroup);
			
			return;
		}
	}
	
	{
		BeginWrite();
		WriteByte(SM_ResponseBattleGroupStartSearch);
		WriteByte(0);
		WriteInt(joined_battlegroup);
		EndWrite();
	}
}

void ClientConnection::OnRequestBattleGroupChallenge()
{
	byte is_ok = 0;
	uint challenge_battlegroup;
	
	ReadInt(challenge_battlegroup);
	
	BattleGroup *battlegroup = server.battlegroup_pool.Get(joined_battlegroup);
	if (battlegroup && 
		battlegroup->ownerclient_uid == uid && 
		battlegroup->CanStartSearch())
	{
		is_ok = battlegroup->StartPlay(challenge_battlegroup, true);
	}
	
	{
		BeginWrite();
		WriteByte(SM_ResponseBattleGroupChallenge);
		WriteByte(is_ok);
		WriteInt(joined_battlegroup);
		EndWrite();
	}
}

// continue search room
void ClientConnection::ContinueSearchRoom()
{
	ChannelInfo * channel_info = server.GetChannelInfo(search_server_id, search_channel_id);

	if (channel_info && channel_info->connection && channel_info->connection->IsReady())
	{
		channel_info->connection->RequestSearchRoom(uid, search_room_options);
	}
	else
	{
		FinishSearchRoom(search_server_id, search_channel_id, 0);
	}
}

// on finish search room
void ClientConnection::FinishSearchRoom(uint server_id, uint channel_id, uint room_id)
{
	if (room_id)
	{
		search_server_id = 0;
		search_channel_id = 0;
		BeginWrite();
		WriteByte(SM_ResponseSearchRoom);
		WriteByte(0);
		WriteInt(server_id);
		WriteInt(channel_id);
		WriteInt(room_id);
		EndWrite();
		return;
	}

	if (search_room_options.all_channels == false)
	{
		search_server_id = 0;
		search_channel_id = 0;
	}

	if (search_channel_id)
	{
		if (++search_channel_id <= appcfg.max_channel_count)
		{
			ContinueSearchRoom();
			return;
		}
	}

	if (search_server_id)
	{
		if (++search_server_id <= appcfg.max_server_count)
		{
			search_channel_id = 1;
			ContinueSearchRoom();
			return;
		}
	}

	search_server_id = 0;
	search_channel_id = 0;
	BeginWrite();
	WriteByte(SM_ResponseSearchRoom);
	WriteByte(1);
	EndWrite();
}

// leave channel
void ClientConnection::LeaveChannel()
{
	if (character_channel_id)
	{
		ChannelInfo * channel = server.GetChannelInfo(character_server_id, character_channel_id);

		if (channel && channel->connection)
		{
			channel->connection->RequestClientLeave(uid, uid_in_channel);
		}

		character_channel_id = 0;
		// ���ܿͻ�����Ϣ�ȷ����� ��ô�Ͱ�������T��ȥ �����Ͽ����е�����
		if (m_dwMatchingTeamGroupId)
		{
			MatchingTeamGroup* pMatchGroup = server.m_pollMatchingTeamGroups.Get(m_dwMatchingTeamGroupId);
			if (pMatchGroup)
			{
				pMatchGroup->DelCharacter(this);
			}
			
		}
		
	}
}

// leave server
void ClientConnection::LeaveServer()
{
	if (character_server_id)
	{
		ServerInfo * server_info = server.GetServerInfo(character_server_id);

		if (server_info)
		{
			server_info->online_count--;
			
			if (server_info->online_count < 0)
			{
				server_info->online_count = 0;
				log_write(LOG_WARNING, "online_count < 0");
			}
		}

		LeaveChannel();
		character_server_id = 0;

	}
}

// response refresh server list
void ClientConnection::ResponseRefreshServerList()
{
	int server_count = 0;

	for (std::map<uint, ServerInfo>::const_iterator itr = server.servers.begin(); 
		itr != server.servers.end(); itr++)
	{
		if (itr->second.id)
			server_count++;
	}

	BeginWrite();
	WriteByte(SM_NotifyRefreshServerList);
	WriteInt(server_count);

	for (std::map<uint, ServerInfo>::const_iterator itr = server.servers.begin(); 
		itr != server.servers.end(); itr++)
	{
		const ServerInfo & s = itr->second;

		if (s.id)
		{
			WriteInt(s.id);
			WriteString(s.name);
			WriteInt(s.online_count);
			WriteInt(s.online_max);
			WriteByte(s.isnovice);
			WriteString(s.gametype_limit.c_str());
			WriteInt((int)s.m_eServerType);
			WriteInt(s.min_character_level);
			WriteInt(s.max_character_level);

			int channel_count = 0;

			for (std::map<uint, ChannelInfo>::const_iterator itrChannel = itr->second.channels.begin(); 
				itrChannel != itr->second.channels.end(); itrChannel++)
			{
				if (itrChannel->second.connection)
					channel_count++;
			}
			WriteInt(channel_count);
		}
	}

	EndWrite();

	list_refresh_time = 0;
}

// response refresh server list
void ClientConnection::ResponseRefreshChannelList()
{
	ServerInfo * server_info = server.GetServerInfo(character_server_id);

	if (server_info)
	{
		int channel_count = 0;

		for (std::map<uint, ChannelInfo>::const_iterator itr = server_info->channels.begin(); 
			itr != server_info->channels.end(); itr++)
		{
			if (itr->second.id)
				channel_count++;
		}

		BeginWrite();
		WriteByte(SM_NotifyRefreshChannelList);
		WriteInt(channel_count);

		for (std::map<uint, ChannelInfo>::const_iterator itr = server_info->channels.begin(); 
			itr != server_info->channels.end(); itr++)
		{
			const ChannelInfo & c = itr->second;

			if (c.id)
			{
				WriteInt(c.id);
				WriteString(c.name);

				if (c.connection && c.connection->IsReady())
				{
					WriteByte(1);
					WriteInt(c.connection->client_count);
					WriteInt(c.online_max);
					WriteInt(c.min_level);
					WriteInt(c.max_level);
				}
				else
				{
					WriteByte(0);
					WriteInt(0);
					WriteInt(c.online_max);
					WriteInt(c.min_level);
					WriteInt(c.max_level);
				}
			}
		}

		EndWrite();
	}

	list_refresh_time = 0;
}

// on character info changed
void ClientConnection::OnCharacterInfoChanged()
{
	if (uid_in_channel)
	{
		ChannelConnection * channel = server.GetChannel(character_server_id, character_channel_id);

		if (channel && channel->IsReady())
		{
			channel->UpdateCharacterData(this);
		}
	}

	// notify team status
	if (team_leader_uid)
	{
		ClientConnection * leader = server.client_pool.Get(team_leader_uid);

		if (leader)
		{
			leader->TeamMemberInfoChange(this);
		}
	}
}


void ClientConnection::UpdateFCMTime(int left_time)
{
	static const int play_time_max = 5 * 60 *60;

	fcm_left_time = left_time;
	fcm_online_minutes = (play_time_max - fcm_left_time)/60;
	fcm_offline_minutes = fcm_left_time/60;
	NotifyFCM();
}

void ClientConnection::OnRequestMatching()
{
	if (!server.matching_connection.IsConnected())
	{
		ResponseMatching(kErrorProxyMatchingFailedServerOffline, false);
		log_write(LOG_DEBUG1, "kErrorProxyMatchingFailedServerOffline");
		return;
	}

	if (m_dwMatchingTeamGroupId == 0)
	{
		ResponseMatching(kErrorProxyMatchingFailed, false);
		log_write(LOG_DEBUG1, "kErrorProxyMatchingFailed");
		return;
	}

	MatchingTeamGroup* pMatchingGroup = server.m_pollMatchingTeamGroups.Get(m_dwMatchingTeamGroupId);
	if (!pMatchingGroup)
	{
		log_write(LOG_DEBUG1, "can't find matching group id : %d", m_dwMatchingTeamGroupId);
		return;
	}

	////todo �ÿͻ���ȥ�������
	//if (pMatchingGroup->ownerclient_uid != uid)
	//{
	//	ResponseMatching(kErrorProxyTeamMatchingNotLeader, false);
	//	return;
	//}

	if (pMatchingGroup->IsMatching())
	{
		log_write(LOG_DEBUG1, "group id matching id : %d", m_dwMatchingTeamGroupId);
		return;
	}

	uint now = Event::GetTime();

	byte client_count;
	uint matching_level = 0;

	uint matching_id = 0;

	matching_id = pMatchingGroup->uid;
	client_count = pMatchingGroup->characters_uid.size();

	const uint max_client_count = appcfg.matching_group_character_limit;

	float fMaxLevel = 0.0f;
	float fTotalLevel = 0.0f;
	for (std::set<uint>::iterator it = pMatchingGroup->characters_uid.begin(); it != pMatchingGroup->characters_uid.end(); ++it)
	{
		if (ClientConnection *client = server.GetClient(*it))
		{
			if (fMaxLevel < client->matching_level)
			{
				fMaxLevel = client->matching_level;
			}
			fTotalLevel += client->matching_level;
			
			matching_level += pow(client->matching_level, 3);
			client->SetMatching(true, now, true, matching_random);
			
			if (pMatchingGroup->roomop.use_password)
			{
				// ������ �������ֶν���
				log_write(LOG_DEBUG1, "%s, %s, user in room with improper method name : %s", __FILE__, __FUNCTION__, client->character_name);
			}
			
		}
	}
	matching_level /= client_count;
	matching_level = pow(matching_level, 1.f / 3.f) + 0.5f;
	
	if ((fMaxLevel - fMaxLevel / client_count) / (fMaxLevel / client_count) > 0.3f)
	{
		matching_level = (uint)fMaxLevel;
	}

	pMatchingGroup->SetMatching(true);
	//	if (team->IsGuildTeam())
	//	{
	//		game_mode = kGameTypeNone;
	//	}
	//}

	log_write(LOG_DEBUG1, "request matching group id : %d, level : %d", m_dwMatchingTeamGroupId, matching_level);
	server.matching_connection.RequestMatching(pMatchingGroup->server_id, matching_id, matching_level, client_count, pMatchingGroup->roomop.game_type, 0, pMatchingGroup->roomop.level_id);
}

void ClientConnection::OnRequestCancelMatching( bool no_return /*= false*/ )
{
	log_write(LOG_DEBUG1, "%s, %s, id : %d, no return : %d", __FILE__, __FUNCTION__, m_dwMatchingTeamGroupId, no_return);
	if (!server.matching_connection.IsConnected())
	{
		log_write(LOG_DEBUG1, "$s, %s, matching server disconnect", __FILE__, __FUNCTION__);

		return;
	}

	MatchingTeamGroup* pMatchingTeamGroup = server.m_pollMatchingTeamGroups.Get(m_dwMatchingTeamGroupId);
	if (!pMatchingTeamGroup)
	{
		log_write(LOG_DEBUG1, "$s, %s, can't find group", __FILE__, __FUNCTION__);
		return;
	}

	////todo �ŵ��ͻ���ȥ��
	//if (pMatchingTeamGroup->ownerclient_uid != uid)
	//{
	//	return;
	//}
	if (!pMatchingTeamGroup->IsMatching())
	{
		log_write(LOG_DEBUG1, "$s, %s, not matching", __FILE__, __FUNCTION__);
		return;
	}
	uint matching_id = 0;
	matching_id = pMatchingTeamGroup->uid;
	server.matching_connection.RequestCancelMatching(matching_id, no_return);
}

void ClientConnection::ResponseMatching( uint error, bool matching, byte total_progress /*= 0*/ )
{
	BeginWrite();
	WriteByte(SM_ResponseMatching);
	WriteInt(error);
	WriteByte((byte)matching);
	WriteByte(total_progress);
	EndWrite();
}

void ClientConnection::ResponseCancelMatching( uint error, bool matching )
{
	BeginWrite();
	WriteByte(SM_ResponseCancelMatching);
	WriteInt(error);
	WriteByte((byte)matching);
	EndWrite();
}

void ClientConnection::OnRequestMatchingTeamCreate()
{
	uint server_id;
	uint channel_id;
	uint room_id;
	
	RoomOption roomop;
	
	ReadInt(server_id);
	ReadInt(channel_id);
	ReadInt(room_id);
	ReadRoomOption(*this, roomop);
	
	if (server_id != character_server_id || 
		channel_id != character_channel_id || 
		room_id != character_room_id)
	{
		BeginWrite();
		WriteByte(SM_ResponseMatchingTeamCreate);
		WriteInt(0);
		EndWrite();
		
		return;
	}

	if (m_dwMatchingTeamGroupId)
	{
		MatchingTeamGroup* pGroup = server.m_pollMatchingTeamGroups.Get(m_dwMatchingTeamGroupId);
		if (pGroup)
		{
			pGroup->DelCharacter(this);
		}
		m_dwMatchingTeamGroupId = 0;
	}
	
	if (m_dwMatchingTeamGroupId == 0)
	{
		MatchingTeamGroup* battlegroup = server.m_pollMatchingTeamGroups.Allocate();
		if (battlegroup && battlegroup->Initialize(this, roomop))
		{
			log_write(LOG_DEBUG3, "create matching group [%d, %d] character : %s", uid, m_dwMatchingTeamGroupId, character_name);
			
			BeginWrite();
			WriteByte(SM_ResponseMatchingTeamCreate);
			WriteInt(m_dwMatchingTeamGroupId);
			EndWrite();

			battlegroup->server_id = server_id;
			battlegroup->channel_id = channel_id;
			
			return;
		}
		else
		{
			log_write(LOG_ERROR, "create matching group failed [%d, %d] character : %s", uid, m_dwMatchingTeamGroupId, character_name);
		}
		
		if (battlegroup)
			server.m_pollMatchingTeamGroups.Free(battlegroup->uid);
	}
	
	BeginWrite();
	WriteByte(SM_ResponseMatchingTeamCreate);
	WriteInt(0);
	EndWrite();

}

void ClientConnection::OnRequestMatchingTeamJoin()
{
	uint battlegroup_uid;
	
	ReadInt(battlegroup_uid);
	
	MatchingTeamGroup* battlegroup = server.m_pollMatchingTeamGroups.Get(battlegroup_uid);
	
	if (battlegroup && battlegroup_uid == m_dwMatchingTeamGroupId)
	{
		return;
	}
	
	if (battlegroup && 
		battlegroup->AddCharacter(this))
	{
		BeginWrite();
		WriteByte(SM_ResponseMatchingTeamJoin);
		WriteByte(1);
		WriteInt(battlegroup_uid);
		WriteInt(battlegroup->server_id);
		WriteInt(battlegroup->channel_id);
		WriteInt(battlegroup->room_id);
		EndWrite();
		
		for (std::set<uint>::iterator it = battlegroup->characters_uid.begin(); 
			it != battlegroup->characters_uid.end(); it++)
		{
			ClientConnection *c = server.GetClient(*it);
			if (c)
			{
				c->BeginWrite();
				c->WriteByte(SM_NotifyMatchingTeamChange);
				c->WriteInt(battlegroup->uid);
				c->EndWrite();
			}
		}
	}
	else
	{
		BeginWrite();
		WriteByte(SM_ResponseMatchingTeamJoin);
		WriteByte(0);
		WriteInt(battlegroup_uid);
		WriteInt(-1);
		WriteInt(-1);
		WriteInt(-1);
		EndWrite();
	}

}

void ClientConnection::OnRequestMatchingTeamInvite()
{
	char name[user_name_length];
	
	ReadString(name, sizeof(name));
	
	ClientConnection *c = server.GetClientByName(name);
	MatchingTeamGroup* battlegroup = server.m_pollMatchingTeamGroups.Get(m_dwMatchingTeamGroupId);
	if (c && c != this && battlegroup)// && battlegroup->ownerclient_uid == uid) //�ŵ��ͻ���
	{
		c->BeginWrite();
		c->WriteByte(SM_NotifyMatchingTeamInvite);
		c->WriteInt(m_dwMatchingTeamGroupId);
		c->WriteString(name);
		c->EndWrite();
	
		return;
	}
}

void ClientConnection::OnRequestMatchingTeamLeave()
{
	uint battlegroup_uid;
	
	ReadInt(battlegroup_uid);
	
	//if (battlegroup_uid == joined_battlegroup)
	if (m_dwMatchingTeamGroupId != 0)
	{
		NotifyMatchingTeamLeave(m_dwMatchingTeamGroupId);
	}
}

void ClientConnection::OnRequestMatchingTeamKick()
{

}

void ClientConnection::OnRequestMatchingTeamChangeLeader()
{

}

void ClientConnection::OnRequestLestPersonChannel()
{
	uint dwServerId = 0;
	ReadInt(dwServerId);
	if (dwServerId != character_server_id)
	{
		BeginWrite();
		WriteByte(SM_ResponseLestPersonChannel);
		WriteInt(dwServerId);
		WriteInt(-1);
		EndWrite();
		return;
	}
	if (server.m_mapClientCount.find(dwServerId) == server.m_mapClientCount.end())
	{
		BeginWrite();
		WriteByte(SM_ResponseLestPersonChannel);
		WriteInt(dwServerId);
		WriteInt(-1);
		EndWrite();
		return;
	}

	uint dwChannelId = 0;
	uint dwCount = (uint)-1;
	for (std::map<uint, uint>::iterator it = server.m_mapClientCount[dwServerId].begin();
		it != server.m_mapClientCount[dwServerId].end(); ++it)
	{
		if (dwCount > it->second)
		{
			ChannelInfo * pChannelInfo = server.GetChannelInfo(dwServerId, it->first);
			if (pChannelInfo && pChannelInfo->connection && pChannelInfo->connection->IsReady())
			{
				dwCount = it->second;
				dwChannelId = it->first;
			}
		}
		
	}
	
	BeginWrite();
	WriteByte(SM_ResponseLestPersonChannel);
	WriteInt(dwServerId);
	WriteInt(dwChannelId);
	EndWrite();
	return;
}

void ClientConnection::OnRequestIntoMatchingTeam()
{
	char name[user_name_length];

	ReadString(name, sizeof(name));

	ClientConnection *pClient = server.GetClientByName(name);
	if (pClient)
	{
		MatchingTeamGroup* pMatchingGroup = server.m_pollMatchingTeamGroups.Get(pClient->m_dwMatchingTeamGroupId);
		if (pMatchingGroup)
		{
			if (pMatchingGroup->IsMatching())
			{
				BeginWrite();
				WriteByte(SM_ResponseIntoMatchingTeam);
				WriteInt(kErrorProxyMatchingTeamIsmatching);
				EndWrite();
				return;
			}
			
			if (pMatchingGroup->room_id != character_room_id)
			{
				// ���Ӧ�ò��ᷢ��
				BeginWrite();
				WriteByte(SM_ResponseIntoMatchingTeam);
				WriteInt(kErrorProxyMatchingNone);
				EndWrite();
				return;
			}
			
			if (pMatchingGroup->AddCharacter(this))
			{
				BeginWrite();
				WriteByte(SM_ResponseIntoMatchingTeam);
				WriteInt(kErrorNone);
				EndWrite();

				log_write(LOG_DEBUG1, "%s, %s, into match group success name : %s", __FILE__, __FUNCTION__, character_name);
				return;
			}
			else
			{
				BeginWrite();
				WriteByte(SM_ResponseIntoMatchingTeam);
				WriteInt(kErrorProxyMatchingNone);
				EndWrite();
			}
		}
		else
		{
			BeginWrite();
			WriteByte(SM_ResponseIntoMatchingTeam);
			WriteInt(kErrorProxyTeamMatchingCannotFind);
			EndWrite();
		}
	}
	else
	{
		BeginWrite();
		WriteByte(SM_ResponseIntoMatchingTeam);
		WriteInt(kErrorProxyTeamMatchingCannotFindLeader);
		EndWrite();
	}
	log_write(LOG_DEBUG1, "%s, %s, into match group failed", __FILE__, __FUNCTION__);
	//MatchingTeamGroup* battlegroup = server.m_pollMatchingTeamGroups.Get(m_dwMatchingTeamGroupId);
	//if (c && c != this && battlegroup)// && battlegroup->ownerclient_uid == uid) //�ŵ��ͻ���
	//{
	//	c->BeginWrite();
	//	c->WriteByte(SM_NotifyMatchingTeamInvite);
	//	c->WriteInt(m_dwMatchingTeamGroupId);
	//	c->WriteString(name);
	//	c->EndWrite();

	//	return;
	//}
}

void ClientConnection::SetMatching( bool matching, uint matching_time /*= 0*/, bool matching_team /*= false*/, bool matching_random /*= false*/ )
{
	if (matching)
	{
		BeginWrite();
		WriteByte(SM_NotifyBeginMatch);
		EndWrite();
	}

	this->matching = matching;
	this->matching_time = matching_time;
	this->matching_team = matching_team;
	this->matching_random = matching_random;
}

void ClientConnection::ResponseMatchingProgress( byte progress, byte total_progress )
{
	BeginWrite();
	WriteByte(SM_ResponseMatchingProgress);
	WriteByte(progress);
	WriteByte(total_progress);
	EndWrite();
}

void ClientConnection::NotifyMatchingTeamLeave( int dwMatchingTeamId )
{
	MatchingTeamGroup* battlegroup = server.m_pollMatchingTeamGroups.Get(dwMatchingTeamId);
	if (battlegroup && battlegroup->DelCharacter(this))
	{
		for (std::set<uint>::iterator itr = battlegroup->characters_uid.begin(); 
			itr != battlegroup->characters_uid.end(); itr++)
		{
			ClientConnection *c = server.GetClient(*itr);
			if (c)
			{
				c->BeginWrite();
				c->WriteByte(SM_NotifyMatchingTeamChange);
				c->WriteInt(m_dwMatchingTeamGroupId);
				c->EndWrite();
			}
		}
	}
}

void ClientConnection::NotifyMatchingTeamKick( uint dwMatchingTeamId )
{
	BeginWrite();
	WriteByte(SM_NotifyMatchingTeamKick);
	WriteInt(dwMatchingTeamId);
	EndWrite();
}

void ClientConnection::NotifyGoToMetchingRoom( uint dwServerId, uint dwChannelId, uint dwRoomId, uint dwSlotId /*= 0*/ )
{
	BeginWrite();
	WriteByte(SM_NotifyGoToMetchingRoom);
	WriteInt(dwServerId);
	WriteInt(dwChannelId);
	WriteInt(dwRoomId);
	WriteInt(dwSlotId);
	EndWrite();
}

void ClientConnection::OnRequestChangeMatchMap()
{
	uint dwLevelId = 0;
	ReadInt(dwLevelId);

	if (m_dwMatchingTeamGroupId)
	{
		MatchingTeamGroup* pGroup = server.m_pollMatchingTeamGroups.Get(m_dwMatchingTeamGroupId);
		if (pGroup)
		{
			if (!pGroup->IsMatching())
			{
				pGroup->roomop.level_id = dwLevelId;
			}
		}
	}
}

void ClientConnection::OnRequestChangeMatchGameType()
{
	byte cGameType = -1;
	ReadByte(cGameType);
	
	if (m_dwMatchingTeamGroupId)
	{
		MatchingTeamGroup* pGroup = server.m_pollMatchingTeamGroups.Get(m_dwMatchingTeamGroupId);
		if (pGroup)
		{
			if (!pGroup->IsMatching())
			{
				pGroup->roomop.game_type = cGameType;
			}
		}
	}
}

void ClientConnection::OnRequestHageBattleGroupJoin()
{
	uint dwGameType = 0;
	
	ReadInt(dwGameType);
	RoomOption oRoomOp;
	ReadRoomOption(*this, oRoomOp);
	
	switch(dwGameType)
	{
		case 0:
			{
				dwGameType = 4;
			}break;
		case 1:
			{
				dwGameType = 2;
			}break;
		case 2:
			{
				dwGameType = 0;
			}break;
		case 3:
			{
				dwGameType = 1;
			}break;
		case 4:
			{
				dwGameType = 6;
			}break;
		case 5:
			{
				dwGameType = 17;
			}break;
		default:
		{
			dwGameType = 17;
		}break;
	}
	
	if (!HageBattleGroup::s_bReadyTime)
	{
		//log_write(LOG_DEBUG1, "%s, %s, not ready, name : %s", __FILE__, __FUNCTION__, character_name);
		//BeginWrite();
		//WriteByte(SM_ResponseHageBattleGroupJoin);
		//WriteInt(kErrorProxyHageBattleTime);
		//EndWrite();
		//return;
		{
			if (!HappyJumpBattle::s_bReadyTime)
			{
				BeginWrite();
				WriteByte(SM_ResponseHageBattleHappyJumpJoin);
				WriteInt(kErrorProxyHageBattleTime);
				EndWrite();
				return;
			}

			if (HageBattleGroup::s_HappyJumpBattle.m_setParticipator.find(user_id) != HageBattleGroup::s_HappyJumpBattle.m_setParticipator.end())
			{
				BeginWrite();
				WriteByte(SM_ResponseHageBattleHappyJumpJoin);
				WriteInt(kErrorProxyHappyHageBattleAlready);
				EndWrite();
				return;
			}

			HageBattleGroup::s_HappyJumpBattle.JoinGame(this, oRoomOp);
			BeginWrite();
			WriteByte(SM_ResponseHageBattleHappyJumpJoin);
			WriteInt(kErrorNone);
			EndWrite();
		}
		return;
	}
	
	if (HageBattleGroup::s_mapBattleGroupList.find(character_group_id) == HageBattleGroup::s_mapBattleGroupList.end())
	{
		log_write(LOG_DEBUG1, "%s, %s, cant find group : %d, name : %s", __FILE__, __FUNCTION__, character_group_id, character_name);
		BeginWrite();
		WriteByte(SM_ResponseHageBattleGroupJoin);
		WriteInt(kErrorProxyTeamJoin);
		EndWrite();
		return;
	}
	
	if (HageBattleGroup::s_mapBattleGroupList[character_group_id].find(dwGameType) == HageBattleGroup::s_mapBattleGroupList[character_group_id].end())
	{
		log_write(LOG_DEBUG1, "%s, %s, cant find game type : %d, name : %s", __FILE__, __FUNCTION__, dwGameType, character_name);
		BeginWrite();
		WriteByte(SM_ResponseHageBattleGroupJoin);
		WriteInt(kErrorProxyTeamJoin);
		EndWrite();
		return;
	}
	
	HageBattleGroup* pHageBattleGroup = server.m_pollHageBattleGroup.Get(HageBattleGroup::s_mapBattleGroupList[character_group_id][dwGameType]);
	if (!pHageBattleGroup)
	{
		log_write(LOG_DEBUG1, "%s, %s, cant find hage battle : %d, name : %s", __FILE__, __FUNCTION__, HageBattleGroup::s_mapBattleGroupList[character_group_id][dwGameType], character_name);
		BeginWrite();
		WriteByte(SM_ResponseHageBattleGroupJoin);
		WriteInt(kErrorProxyTeamJoin);
		EndWrite();
		return;
	}
	
	if (pHageBattleGroup->mapCharactersUid.find(user_id) == pHageBattleGroup->mapCharactersUid.end())
	{
		log_write(LOG_DEBUG1, "%s, %s, cant find client : %d, name : %s", __FILE__, __FUNCTION__, user_id, character_name);
		BeginWrite();
		WriteByte(SM_ResponseHageBattleGroupJoin);
		WriteInt(kErrorProxyTeamJoin);
		EndWrite();
		return;
	}
	
	if (!pHageBattleGroup->AddCharacter(this, oRoomOp))
	{
		log_write(LOG_DEBUG1, "%s, %s, add character failed name : %s", __FILE__, __FUNCTION__, character_name);
		BeginWrite();
		WriteByte(SM_ResponseHageBattleGroupJoin);
		WriteInt(kErrorProxyTeamJoin);
		EndWrite();
		return;
	}
	
	BeginWrite();
	WriteByte(SM_ResponseHageBattleGroupJoin);
	WriteInt(kErrorNone);
	EndWrite();
}

void ClientConnection::OnRequestHageBattleGroupLeave()
{
	uint dwGameType = 0;
	ReadInt(dwGameType);
	
	if (HageBattleGroup::s_mapBattleGroupList.find(character_group_id) == HageBattleGroup::s_mapBattleGroupList.end())
	{
		log_write(LOG_DEBUG1, "%s, %s, cant find group : %d, name : %s", __FILE__, __FUNCTION__, character_group_id, character_name);
		return;
	}
	
	if (HageBattleGroup::s_mapBattleGroupList[character_group_id].find(dwGameType) == HageBattleGroup::s_mapBattleGroupList[character_group_id].end())
	{
		log_write(LOG_DEBUG1, "%s, %s, cant find game type : %d, name : %s", __FILE__, __FUNCTION__, dwGameType, character_name);
		return;
	}
	
	HageBattleGroup* pHageBattleGroup = server.m_pollHageBattleGroup.Get(HageBattleGroup::s_mapBattleGroupList[character_group_id][dwGameType]);
	if (!pHageBattleGroup)
	{
		log_write(LOG_DEBUG1, "%s, %s, cant find hage battle : %d, name : %s", __FILE__, __FUNCTION__, HageBattleGroup::s_mapBattleGroupList[character_group_id][dwGameType], character_name);
		return;
	}
	
	if (pHageBattleGroup->mapCharactersUid.find(user_id) == pHageBattleGroup->mapCharactersUid.end())
	{
		log_write(LOG_DEBUG1, "%s, %s, cant find client : %d, name : %s", __FILE__, __FUNCTION__, user_id, character_name);
		return;
	}
	
	if (!pHageBattleGroup->DelCharacter(this))
	{
		log_write(LOG_DEBUG1, "%s, %s, del character failed name : %s", __FILE__, __FUNCTION__, character_name);
		return;
	}
}

void ClientConnection::OnRequestHageBattleGroups()
{
}

void ClientConnection::OnRequestHageBattleHappyJumpJoin()
{
	//uint dwGameType = 0;
	//
	//ReadInt(dwGameType);
	RoomOption oRoomOp;
	ReadRoomOption(*this, oRoomOp);
	
	if (!HappyJumpBattle::s_bReadyTime)
	{
		BeginWrite();
		WriteByte(SM_ResponseHageBattleHappyJumpJoin);
		WriteInt(kErrorProxyHageBattleTime);
		EndWrite();
		return;
	}
	
	if (HageBattleGroup::s_HappyJumpBattle.m_setParticipator.find(user_id) != HageBattleGroup::s_HappyJumpBattle.m_setParticipator.end())
	{
		BeginWrite();
		WriteByte(SM_ResponseHageBattleHappyJumpJoin);
		WriteInt(kErrorProxyHappyHageBattleAlready);
		EndWrite();
		return;
	}
	
	
	HageBattleGroup::s_HappyJumpBattle.JoinGame(this, oRoomOp);
	BeginWrite();
	WriteByte(SM_ResponseHageBattleHappyJumpJoin);
	WriteInt(kErrorNone);
	EndWrite();
}










