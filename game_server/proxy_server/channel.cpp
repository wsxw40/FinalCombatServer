#include "pch.h"

enum EChannelState
{
	CS_Close,
	CS_Connecting,
	CS_Connected,
	CS_Ready,
};

enum EServerMessage
{
	SM_SyncData,
	SM_BroadcastChat,
	SM_UpdateFilterKeywords,
	SM_ResponseRPC,
	SM_ResponseEnterChannel,
	SM_RequestClientLeave,
	SM_UpdateCharacterData,
	SM_NotifyCharacterRefusePreserve,
	SM_RequestSearchRoom,
	SM_SetClientConnectMode,
	SM_SetServerNovice,
	SM_UpdateBlackList,
	SM_UpdateLevelList,

	SM_SetServerType,

	// [2015/10/14 dengxiaobo]
	SM_RequestMatchingRoomEnter,
	SM_ResponseMatchingClient,
	// end
	
	SM_RequestHageBattleRoomCreate,
	SM_RequestHageBattleRoomEnter,
	SM_RequestHageBattleHappyJumpEnter,
	
	SM_RequestDebugCmd = 50,
};

enum EClientMessage
{
	CM_SyncData,
	CM_RequestRPC,
	CM_RequestEnterChannel,
	CM_NotifyLeaveChannel,
	CM_NotifyClientRoomChanged,
	CM_NotifyClientStatusChanged,
	CM_NotifyRoomPreserveSlot,
	CM_NotifyRoomCancelPreserveSlot,
	CM_ResponseSearchRoom,
	CM_UpdateChannelStatus,

	// [2015/10/13 dengxiaobo]
	CM_ResponseMatchingRoomEnter,
	CM_RequestMatchingClient,

	CM_RequestIntoTargetRoom,
	// end
	
	CM_ResponseHageBattleRoomCreate,
	CM_ResponseHageBattleRoomEnter,
	CM_ResponseHageBattleHappyJumpEnter,
};

// -----------------------------------------------------------------
// class channel
// -----------------------------------------------------------------
ChannelConnection::ChannelConnection()
	: BinaryStream(appcfg.channel_buffersize)
	, state(CS_Close)
	, channel_id(-1)
	, client_count(0)
	, uid(0)
{
	client_port = 9201;
	connection = this;
	stream = this;
}

ChannelConnection::~ChannelConnection()
{
}

// on client connected
void ChannelConnection::OnConnected()
{
	state = CS_Connected;
	room_count = 0;
	game_count = 0;
	game_client_count = 0;

	server.m_mapClientCount[server_id][channel_id] = client_count;
}

void ChannelConnection::OnDisconnected()
{
	state = CS_Close;
	client_count = 0;

	ChannelInfo * channel_info = server.GetChannelInfo(server_id, channel_id);

	if (channel_info && channel_info->connection == this)
	{
		for (ClientConnection * client = server.client_pool.Begin(); client < server.client_pool.End(); client++)
		{
			if (client->IsOnline() && client->character_server_id == server_id && client->character_channel_id == channel_id)
			{
				client->uid_in_channel = 0;
				client->ChangeAddress(client->character_server_id, 0, 0);
			}
		}

		channel_info->connection = NULL;
		log_write(LOG_INFO, "channel %d-%d closed.\n", server_id, channel_id);
		server_id = -1;
		channel_id = -1;

		// update server info
		server.UpdateServerInfo();
	}

	server.channel_pool.Free(uid);
}

// receive message
void ChannelConnection::OnMessage()
{
	byte msg = 255;
	
	try
	{
		switch (state)
		{
		case CS_Connected:
			OnInitialize();
			break;

		case CS_Ready:
			{
				ReadByte(msg);

				switch (msg)
				{
				case CM_SyncData: 				OnSyncData(); break;
				case CM_RequestRPC:				OnRequestRPC(); break;
				case CM_RequestEnterChannel:	OnRequestEnterChannel(); break;
				case CM_NotifyLeaveChannel:		OnNotifyLeaveChannel(); break;
				case CM_NotifyClientRoomChanged:OnNotifyClientRoomChanged(); break;
				case CM_NotifyClientStatusChanged: OnNotifyClientStatusChagned(); break;
				case CM_NotifyRoomPreserveSlot: OnNotifyRoomPreserveSlot(); break;
				case CM_NotifyRoomCancelPreserveSlot: OnNotifyRoomCancelPreserveSlot(); break;
				case CM_ResponseSearchRoom:		OnResponseSearchRoom(); break;
				case CM_UpdateChannelStatus:	OnUpdateChannelStatus(); break;

				case CM_ResponseMatchingRoomEnter: OnReponseMatchingRoomEnter(); break;
				case CM_RequestMatchingClient:	OnRequestMatchingClient(); break;
				case CM_RequestIntoTargetRoom:	OnRequestIntoTargetRoom(); break;
				case CM_ResponseHageBattleRoomCreate: OnResponseHageBattleRoomCreate(); break;
				case CM_ResponseHageBattleRoomEnter: break;
				case CM_ResponseHageBattleHappyJumpEnter: OnResponseHageBattleHappyJumpEnter(); break;
				}
			}
			break;
		}
	}
	catch (...)
	{
		log_write(LOG_ERROR, "ChannelConnection::OnMessage() exp. state = %d, msg = %d", state, msg);
	}
}

// is online
bool ChannelConnection::IsReady()
{
	return state == CS_Ready;
}

void ChannelConnection::OnSyncData()
{
	ReadInt(client_count);
	server.m_mapClientCount[server_id][channel_id] = client_count;
}

// on initialize
void ChannelConnection::OnInitialize()
{
	ReadInt(server_id);
	ReadInt(channel_id);
	ReadString(address, 512);
	ReadShort(client_port);
	ReadInt(client_max);

	ChannelInfo * channel_info = server.GetChannelInfo(server_id, channel_id);

	ServerInfo* pServerInfo = server.GetServerInfo(server_id);
	ServerType eServerType = SvrType_None;
	if(pServerInfo)
	{
		eServerType = pServerInfo->m_eServerType;
	}

	if (channel_info)
	{
		if (channel_info->connection == NULL)
		{
			channel_info->connection = this;

			state = CS_Ready;
			log_write(LOG_INFO, "channel %d-%d initialized, address : %s, port : %d", server_id, channel_id, address, client_port);

			// update server info
			server.UpdateServerInfo();

			// update keywords
			UpdateFilterKeywords();
			
			// update blacklist
			UpdateBlackList();
			
			// update levellist
			UpdateLevelList();
			
			// 
			SetClientConnectMode(channel_info->istcp);

			SetServerNovice(channel_info->isnovice);

			SetServerType((int)eServerType);

			return;
		}
	}

	log_write(LOG_DEBUG1, "channel %d-%d initialize failed, address : %s, port : %d\n", server_id, channel_id, address, client_port);
	Disconnect();
}

// on response rcp
static void ResponseRPC(RpcRequest * request)
{
	uint channel_id;

	request->ReadInt(channel_id);

	ChannelConnection * channel = server.channel_pool.Get(channel_id);

	if (channel)
	{
		log_write(LOG_DEBUG1, "%s, %s, error : %d, size : %d, url : %s", __FILE__, __FUNCTION__, request->error, request->read_end - request->read_position, request->url);
		channel->BeginWrite();
		channel->WriteByte(SM_ResponseRPC);
		channel->WriteInt(request->error);
		channel->Write(request->read_position, request->read_end - request->read_position);
		channel->EndWrite();
	}
}

// on request rpc
void ChannelConnection::OnRequestRPC()
{
	char url[256];
	char buff[256];
	uint head_size;

	ReadString(url, sizeof(url));
	snprintf(buff, sizeof(buff), "s_%s", url);

	ReadInt(head_size);

	RpcRequest * request = server.rpc.Allocate(RpcQueue::kGameQueue);

	if (request)
	{
		request->BeginWrite();
		request->WriteBinaryRPC(buff, &ResponseRPC);
		request->WriteInt(uid);
		if (head_size > 0)
			request->Write(ReadData(head_size), head_size);
		request->EndRPCUserdata();
		request->Write(read_position, read_end - read_position);
		request->EndWrite();
		log_write(LOG_DEBUG1, "%s, %s, uid : %d, head size : %d, size : %d, url : %s", __FILE__, __FUNCTION__, uid, head_size, read_end - read_position, request->url);
	}
	else
	{
		log_write(LOG_DEBUG1, "%s, %s, error : %d, head size : %d", __FILE__, __FUNCTION__, 404, head_size);
		BeginWrite();
		WriteByte(SM_ResponseRPC);
		WriteInt(404);
		if (head_size > 0)
			Write(ReadData(head_size), head_size);
		EndWrite();
	}
}

// broadcast chat message
void ChannelConnection::BroadcastChatMessage(const char * to, const char * name, const char * msg)
{
	BeginWrite();
	WriteByte(SM_BroadcastChat);
	WriteString(to);
	WriteString(name);
	WriteString(msg);
	EndWrite();
}

// update keywords
void ChannelConnection::UpdateFilterKeywords()
{
	BeginWrite();
	WriteByte(SM_UpdateFilterKeywords);
	WriteInt((uint)server.filter_keywords.size());
	for (std::vector<std::string>::iterator i = server.filter_keywords.begin(); i < server.filter_keywords.end(); ++i)
		WriteString(i->c_str());
	EndWrite();
}

// update blacklist
void ChannelConnection::UpdateBlackList()
{
	BeginWrite();
	WriteByte(SM_UpdateBlackList);
	WriteInt((uint)server.banned_userid.size());
	for (std::set<uint>::iterator i = server.banned_userid.begin(); i != server.banned_userid.end(); ++i)
		WriteInt(*i);
	EndWrite();
}

// update levellist
void ChannelConnection::UpdateLevelList()
{
	BeginWrite();
	WriteByte(SM_UpdateLevelList);
	
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
			WriteByte(iter->is_gm);
			*level_count = *level_count + 1;
		}
	}
	EndWrite();
}

// set client connect mode
void ChannelConnection::SetClientConnectMode(byte is_tcp)
{
	BeginWrite();
	WriteByte(SM_SetClientConnectMode);
	WriteByte(is_tcp);
	EndWrite();
}

void ChannelConnection::SetServerNovice(byte novice)
{
	BeginWrite();
	WriteByte(SM_SetServerNovice);
	WriteByte(novice);
	EndWrite();
}

void ChannelConnection::SetServerType( int eServerType )
{
	BeginWrite();
	WriteByte(SM_SetServerType);
	byte type = (byte)eServerType;
	WriteByte(type);
	EndWrite();
}

// on request channel enter
void ChannelConnection::OnRequestEnterChannel()
{
	uint uid_in_channel;
	uint uid_in_proxy;
	uint character_id;
	int request_client_count;

	ReadInt(uid_in_channel);
	ReadInt(uid_in_proxy);
	ReadInt(character_id);
	ReadInt(request_client_count);

	ClientConnection * client = server.client_pool.Get(uid_in_proxy);

	if (client && client->character_id == character_id)
	{
		int result = kErrorNone;
		ChannelInfo * channel_info = server.GetChannelInfo(server_id, channel_id);

		if (channel_info && channel_info->online_max < client_count + request_client_count)
			result = kErrorProxyChannelConnectClientCount;

		BeginWrite();
		WriteByte(SM_ResponseEnterChannel);
		WriteInt(result);
		WriteInt(uid_in_channel);
		WriteInt(uid_in_proxy);
		WriteCharacterData(client);

		WriteInt(channel_id);
		WriteString(channel_info ? channel_info->name : "");
		EndWrite();
		
		if (result == kErrorNone)
		{
			client->uid_in_channel = uid_in_channel;
			client->ChangeAddress(server_id, channel_id, 0);
		}
	}
	else
	{
		BeginWrite();
		WriteByte(SM_ResponseEnterChannel);
		WriteInt(kErrorProxyChannelConnect);
		WriteInt(uid_in_channel);
		WriteInt(0);
		EndWrite();
	}

}

// on notify leave channel
void ChannelConnection::OnNotifyLeaveChannel()
{
	uint uid_in_channel;
	uint uid_in_proxy;
	uint character_id;

	ReadInt(uid_in_channel);
	ReadInt(uid_in_proxy);
	ReadInt(character_id);

	// notify info server client leave channel
	ClientConnection * client = server.client_pool.Get(uid_in_proxy);

	if (client)
	{
		if (client->uid_in_channel == uid_in_channel && client->character_id == character_id)
		{
			if (client->character_channel_id == channel_id)
			{
				client->uid_in_channel = 0;

				client->ChangeAddress(client->character_server_id, 0, 0);
			}
		}
	}
}

// request client leave
void ChannelConnection::RequestClientLeave(uint uid_in_proxy, uint uid_in_channel)
{
	BeginWrite();
	WriteByte(SM_RequestClientLeave);
	WriteInt(uid_in_proxy);
	WriteInt(uid_in_channel);
	EndWrite();
}

// request sync data
void ChannelConnection::RequestSyncData()
{
	BeginWrite();
	WriteByte(SM_SyncData);
	EndWrite();
}

// on notify client room changed
void ChannelConnection::OnNotifyClientRoomChanged()
{
	uint client_uid;
	uint room_id;

	ReadInt(client_uid);
	ReadInt(room_id);

	ClientConnection * client = server.client_pool.Get(client_uid);

	if (client && client->character_channel_id == channel_id)
	{
		client->ChangeAddress(server_id, channel_id, room_id);
	}
}

// on notify client status changed
void ChannelConnection::OnNotifyClientStatusChagned()
{
	uint client_uid;
	uint status;

	ReadInt(client_uid);
	ReadInt(status);

	ClientConnection * client = server.client_pool.Get(client_uid);

	if (client && client->character_channel_id == channel_id)
	{
		client->ChangeStatus(status);
	}
}

// on notify room preserve slot
void ChannelConnection::OnNotifyRoomPreserveSlot()
{
	uint room_id;
	byte slot_id;
	char name[character_name_length];
	char invite_name[character_name_length];

	ReadInt(room_id);
	ReadByte(slot_id);
	ReadString(name, sizeof(name));
	ReadString(invite_name, sizeof(name));

	ClientConnection * client = server.GetClientByName(name);

	if (client)
	{
		client->NotifyRoomPreserve(channel_id, room_id, slot_id, invite_name);
	}
	else
	{
		NotifyCharacterRefusePreserve(room_id, slot_id);
	}
}

// on notify room cancel preserve slot
void ChannelConnection::OnNotifyRoomCancelPreserveSlot()
{
	uint room_id;
	byte slot_id;
	char name[character_name_length];

	ReadInt(room_id);
	ReadByte(slot_id);
	ReadString(name, sizeof(name));

	ClientConnection * client = server.GetClientByName(name);

	if (client)
	{
		client->NotifyRoomCancelPreserve(channel_id, room_id, slot_id);
	}
}

// update character data
void ChannelConnection::UpdateCharacterData(ClientConnection * client)
{
	if (client)
	{
		log_write(LOG_DEBUG4, "update character data: name=%s", client->character_name);

		BeginWrite();
		WriteByte(SM_UpdateCharacterData);
		WriteInt(client->uid_in_channel);
		WriteCharacterData(client);
		EndWrite();
	}
}

// write character data
void ChannelConnection::WriteCharacterData(ClientConnection * client)
{
	if (client)
	{
		WriteInt(client->character_id);
		WriteByte(client->is_vip);
		WriteByte(client->net_bar_level);
		WriteByte(client->business_card);
		WriteByte(client->is_gm);
		WriteString(client->head_icon);
		WriteString(client->character_name);
		WriteInt(client->character_group_id);
		WriteString(client->character_group);
		WriteInt(client->character_level);
		WriteInt(client->character_exp);
		WriteInt(client->fcm_online_minutes);
		WriteInt(client->fcm_offline_minutes);
		WriteInt(client->top);
		WriteInt(client->fightnum);
		WriteFloat(client->win_rate);
	}
}

// notify character refuse preserve
void ChannelConnection::NotifyCharacterRefusePreserve(uint room_id, uint slot_id)
{
	BeginWrite();
	WriteByte(SM_NotifyCharacterRefusePreserve);
	WriteInt(room_id);
	WriteByte(slot_id);
	EndWrite();
}

// search room
void ChannelConnection::RequestSearchRoom(uint client_id, SearchRoomOptions & options)
{
	BeginWrite();
	WriteByte(SM_RequestSearchRoom);
	WriteInt(client_id);
	WriteInt(options.game_type);
	WriteInt(options.level_id);
	WriteByte(options.num_clients);
	WriteByte(options.playing);
	WriteByte(options.waiting);
	EndWrite();
}

// set loglevel
void ChannelConnection::RequestDebugCmd(const std::vector<std::string> & cmd_list)
{
	BeginWrite();
	WriteByte(SM_RequestDebugCmd);
	WriteInt((int)cmd_list.size());
	for (uint i = 0; i < cmd_list.size(); i++)
	{
		WriteString(cmd_list[i].c_str());
	}
	EndWrite();
}

// response search room
void ChannelConnection::OnResponseSearchRoom()
{
	uint client_uid;
	uint room_id;

	ReadInt(client_uid);
	ReadInt(room_id);

	ClientConnection * client = server.GetClient(client_uid);

	if (client)
	{
		client->FinishSearchRoom(server_id, channel_id, room_id);
	}
}

// on update channel status
void ChannelConnection::OnUpdateChannelStatus()
{
	uint ping_status[10];
	uint resend_status[10];

	try
	{
		Read(ping_status, sizeof(ping_status));
		Read(resend_status, sizeof(resend_status));

		for (int i = 0; i < 10; i++)
		{
			status.client_ping_sum[i] += ping_status[i];
			status.client_resend_sum[i] += resend_status[i];
		}

		ReadInt(room_count);
		ReadInt(game_count);
		ReadInt(game_client_count);
	}
	catch (...)
	{
	}
}

void ChannelConnection::OnReponseMatchingRoomEnter()
{
	uint dwLevelId = 0;
	uint dwMatchingId1 = 0;
	uint dwMatchingId2 = 0;
	uint dwRoomId = 0;

	ReadInt(dwLevelId);
	ReadInt(dwMatchingId1);
	ReadInt(dwMatchingId2);
	ReadInt(dwRoomId);

	MatchingTeamGroup* pMatchingGroup1 = server.m_pollMatchingTeamGroups.Get(dwMatchingId1);
	MatchingTeamGroup* pMatchingGroup2 = server.m_pollMatchingTeamGroups.Get(dwMatchingId2);

	if (pMatchingGroup1)
	{
		pMatchingGroup1->roomop.level_id = dwLevelId;
		pMatchingGroup1->room_id = dwRoomId;
	}
	if (pMatchingGroup2)
	{
		pMatchingGroup2->roomop.level_id = dwLevelId;
		pMatchingGroup2->room_id = dwRoomId;
	}
}

void ChannelConnection::RequestMatchingRoomEnter( byte cGameMode, int nLevelId, uint dwMatchingId1, uint dwMatchingId2 )
{
	BeginWrite();
	WriteByte(SM_RequestMatchingRoomEnter);
	WriteByte(cGameMode);
	WriteInt(nLevelId);
	int* pdwMatchingLevel = reinterpret_cast<int*>(write_position);
	WriteInt(0);
	// 匹配的ID要加上 回调的时候用
	WriteInt(dwMatchingId1);
	WriteInt(dwMatchingId2);

	uint dwCount = 0;
	
	float fMaxLevel = 0.0f;
	float fTotalLevel = 0.0f;
	MatchingTeamGroup* pTemMatchingGroup1 = server.m_pollMatchingTeamGroups.Get(dwMatchingId1);
	if (pTemMatchingGroup1)
	{
		WriteInt((int)(pTemMatchingGroup1->characters_uid.size()));
		for (std::set<uint>::iterator it = pTemMatchingGroup1->characters_uid.begin(); it != pTemMatchingGroup1->characters_uid.end(); ++it)
		{
			// 注意 *it是uid 要发给channel的是另外一个
			ClientConnection* pClient = server.client_pool.Get(*it);
			if (pClient)
			{
				// 这个时候 客户端不一定在channel中 就要给客户端发消息 就要把客户端的uid带上 给channel发过去 0 表示不在这个频道
				if (pClient->character_channel_id != channel_id)
				{
					WriteInt(0);
				}
				else
				{
					WriteInt(pClient->uid_in_channel);
				}
				WriteInt(pClient->uid);
				log_write(LOG_DEBUG1, "%s, %s, client mathcing level : %d, client name : %s, matching level : %d", __FILE__, __FUNCTION__, pClient->matching_level, pClient->character_name, *pdwMatchingLevel);
				*pdwMatchingLevel += pow(pClient->matching_level, 3);
				++dwCount;
				if (fMaxLevel < pClient->matching_level)
				{
					fMaxLevel = pClient->matching_level;
				}
				fTotalLevel += pClient->matching_level;
			}
			else
			{
				WriteInt(0);
				WriteInt(*it);
			}
		}
	}
	else
	{
		WriteInt(0);
	}
	MatchingTeamGroup* pTemMatchingGroup2 = server.m_pollMatchingTeamGroups.Get(dwMatchingId2);
	if (pTemMatchingGroup2)
	{
		WriteInt((int)(pTemMatchingGroup2->characters_uid.size()));
		for (std::set<uint>::iterator it = pTemMatchingGroup2->characters_uid.begin(); it != pTemMatchingGroup2->characters_uid.end(); ++it)
		{
			ClientConnection* pClient = server.client_pool.Get(*it);
			if (pClient)
			{
				if (pClient->character_channel_id != channel_id)
				{
					WriteInt(0);
				}
				else
				{
					WriteInt(pClient->uid_in_channel);
				}
				WriteInt(pClient->uid);
				log_write(LOG_DEBUG1, "%s, %s, client mathcing level : %d, client name : %s, matching level : %d", __FILE__, __FUNCTION__, pClient->matching_level, pClient->character_name, *pdwMatchingLevel);
				*pdwMatchingLevel += pow(pClient->matching_level, 3);
				++dwCount;
				if (fMaxLevel < pClient->matching_level)
				{
					fMaxLevel = pClient->matching_level;
				}
				fTotalLevel += pClient->matching_level;
			}
			else
			{
				WriteInt(0);
				WriteInt(*it);
			}
		}
	}
	else
	{
		WriteInt(0);
	}

	if (dwCount)
	{
		*pdwMatchingLevel /= dwCount;
		*pdwMatchingLevel = pow(*pdwMatchingLevel, 1.f / 3.f) + 0.5f;
		
		if ((fMaxLevel - fMaxLevel / dwCount) / (fMaxLevel / dwCount) > 0.3f)
		{
			*pdwMatchingLevel = (uint)fMaxLevel;
		}
	}
	log_write(LOG_DEBUG1, "%s, %s, matching level %d", __FILE__, __FUNCTION__, *pdwMatchingLevel);
	EndWrite();
}

void ChannelConnection::OnRequestMatchingClient()
{
	uint dwRoomId = 0;
	uint dwMatchingLevel = 0;
	uint dwLevelId = 0;
	byte cTeamType = 0;
	byte cGameMode = 0;
	byte cTeam1Count = 0;
	byte cTeam2Count = 0;
	
	ReadInt(dwRoomId);
	ReadInt(dwMatchingLevel);
	ReadInt(dwLevelId);
	ReadByte(cTeamType);
	ReadByte(cGameMode);
	ReadByte(cTeam1Count);
	ReadByte(cTeam2Count);

	if (server.matching_connection.IsConnected())
	{
		server.matching_connection.RequestMatchingClient(server_id, channel_id, dwRoomId, dwLevelId, cGameMode, dwMatchingLevel, cTeamType, cTeam1Count, cTeam2Count);
	}
	else
	{
		BeginWrite();
		WriteByte(SM_ResponseMatchingClient);
		WriteInt(dwRoomId);
		WriteByte(false);
		EndWrite();
	}
}

void ChannelConnection::ResponseMatchingClient( uint dwRoomId, byte cRet, uint arrMatchingIds1[], uint arrMatchingIds2[] )
{
	BeginWrite();
	WriteByte(SM_ResponseMatchingClient);
	WriteInt(dwRoomId);
	WriteByte(cRet);
	EndWrite();
	if (!cRet)
	{
		return;
	}

	for (int i = 0; i < elementsof(arrMatchingIds1); ++i)
	{
		MatchingTeamGroup* pMatchingGroup = server.m_pollMatchingTeamGroups.Get(arrMatchingIds1[i]);
		if (pMatchingGroup)
		{
			for (std::set<uint>::iterator it = pMatchingGroup->characters_uid.begin(); it != pMatchingGroup->characters_uid.end(); ++it)
			{
				ClientConnection * pClient = server.client_pool.Get(*it);
				if (pClient)
				{
					// 给客户端发消息 退出所在频道 进入新的频道
					pClient->NotifyGoToMetchingRoom(server_id, channel_id, dwRoomId, max_room_client_count / 2 - i);
				}
			}
		}
	}
	
	for (int i = 0; i < elementsof(arrMatchingIds1); ++i)
	{
		MatchingTeamGroup* pMatchingGroup = server.m_pollMatchingTeamGroups.Get(arrMatchingIds2[i]);
		if (pMatchingGroup)
		{
			for (std::set<uint>::iterator it = pMatchingGroup->characters_uid.begin(); it != pMatchingGroup->characters_uid.end(); ++it)
			{
				ClientConnection * pClient = server.client_pool.Get(*it);
				if (pClient)
				{
					// 给客户端发消息 退出所在频道 进入新的频道
					pClient->NotifyGoToMetchingRoom(server_id, channel_id, dwRoomId, max_room_client_count - i);
				}
			}
		}
	}
	return;
	// 后面的代码不执行

	// todo 要把玩家从现有的频道T出去 并进入新的频道 这个过程比价复杂 后面再说吧 现在就一个频道 到时候 让玩家自己进入游戏就可以了
	{
		byte* cCount = reinterpret_cast<byte*>(write_position);
		WriteByte(0);
		for (int i = 0; i < elementsof(arrMatchingIds1); ++i)
		{
			MatchingTeamGroup* pMatchingGroup = server.m_pollMatchingTeamGroups.Get(arrMatchingIds1[i]);
			if (pMatchingGroup)
			{
				for (std::set<uint>::iterator it = pMatchingGroup->characters_uid.begin(); it != pMatchingGroup->characters_uid.end(); ++it)
				{
					ClientConnection * pClient = server.client_pool.Get(*it);
					if (pClient)
					{
						WriteInt(pClient->uid_in_channel);
						*cCount += 1;
					}
				}
			}
		}

		cCount = reinterpret_cast<byte*>(write_position);
		WriteByte(0);
		for (int i = 0; i < elementsof(arrMatchingIds1); ++i)
		{
			MatchingTeamGroup* pMatchingGroup = server.m_pollMatchingTeamGroups.Get(arrMatchingIds1[i]);
			if (pMatchingGroup)
			{
				for (std::set<uint>::iterator it = pMatchingGroup->characters_uid.begin(); it != pMatchingGroup->characters_uid.end(); ++it)
				{
					ClientConnection * pClient = server.client_pool.Get(*it);
					if (pClient)
					{
						WriteInt(pClient->uid_in_channel);
						*cCount += 1;
					}
				}
			}
		}

		//EndWrite();
	}

}

void ChannelConnection::OnRequestIntoTargetRoom()
{
	uint dwUid = 0;
	uint dwServerId = 0;
	uint dwChannelId = 0;
	uint dwRoomId = 0;
	uint dwSlotId = 0;

	ReadInt(dwUid);
	ReadInt(dwServerId);
	ReadInt(dwChannelId);
	ReadInt(dwRoomId);
	ReadInt(dwSlotId);

	ClientConnection* pClient = server.GetClient(dwUid);
	if (pClient)
	{
		log_write(LOG_DEBUG1, "%s, %s, uid : %d, server_id : %d, channel_id : %d, room_id : %d, slot_id : %d", __FILE__, __FUNCTION__, dwUid, dwServerId, dwChannelId, dwRoomId, dwSlotId);
		pClient->NotifyGoToMetchingRoom(dwServerId,dwChannelId, dwRoomId, dwSlotId);
	}
	else
	{
		log_write(LOG_DEBUG1, "%s, %s, uid : %d, can't find client server_id : %d, channel_id : %d, room_id : %d, slot_id : %d", __FILE__, __FUNCTION__, dwUid, dwServerId, dwChannelId, dwRoomId, dwSlotId);
	}
}

bool ChannelConnection::IsMatchingChannel()
{
	ServerInfo* pInfo = server.GetServerInfo(server_id);
	if (pInfo && (pInfo->m_eServerType == SvrType_Match || pInfo->m_eServerType == SvrType_MatchFighting))
	{
		return true;
	}
	return false;
}

void ChannelConnection::RequestHageBattleRoomCreate( uint dwBattleUid, uint dwClientUid, RoomOption& refRoomOp )
{
	BeginWrite();
	WriteByte(SM_RequestHageBattleRoomCreate);
	WriteInt(dwBattleUid);
	WriteInt(dwClientUid);
	WriteRoomOption(*this, refRoomOp);
	EndWrite();
}

void ChannelConnection::OnResponseHageBattleRoomCreate()
{
	uint dwResult = 0;
	uint dwBattleId = 0;
	uint dwClient = 0;
	uint dwRoomId = 0;
	
	ReadInt(dwResult);
	ReadInt(dwBattleId);
	ReadInt(dwClient);
	ReadInt(dwRoomId);
	
	if (dwResult != kErrorNone)
	{
		return;
	}
	
	HageBattleGroup* pHageBattleGroup = server.m_pollHageBattleGroup.Get(dwBattleId);
	if (!pHageBattleGroup) 
	{
		log_write(LOG_DEBUG1, "%s, %s, no pHageBattleGroup id : %d, client id : %d", __FILE__, __FUNCTION__, dwBattleId, dwClient);
		return;
	}
	
	log_write(LOG_DEBUG1, "%s, %s", __FILE__, __FUNCTION__);
	pHageBattleGroup->OnRoomCreate(dwClient, dwRoomId);
}

void ChannelConnection::RequestHageBattleRoomEnter( uint dwClientUid, uint dwRoomId )
{
	BeginWrite();
	WriteByte(SM_RequestHageBattleRoomEnter);
	WriteInt(dwClientUid);
	WriteInt(dwRoomId);
	EndWrite();
}

void ChannelConnection::RequestHageBattleHappyJumpEnter( uint dwClientUid, std::set<uint>& refsetRoomIds, RoomOption& refRoomOption )
{
	BeginWrite();
	WriteByte(SM_RequestHageBattleHappyJumpEnter);
	WriteInt(dwClientUid);
	WriteRoomOption(*this, refRoomOption);
	WriteInt((uint)refsetRoomIds.size());
	for (std::set<uint>::iterator it = refsetRoomIds.begin(); it != refsetRoomIds.end(); ++it)
	{
		log_write(LOG_DEBUG1, "%s, %s, room id : %d", __FILE__, __FUNCTION__, *it);
		WriteInt(*it);
	}
	EndWrite();
}

void ChannelConnection::OnResponseHageBattleHappyJumpEnter()
{
	uint dwNewRoomCount = 0;
	ReadInt(dwNewRoomCount);
	for (uint i = 0; i < dwNewRoomCount; ++i)
	{
		uint dwRoomId = 0;
		ReadInt(dwRoomId);
		HageBattleGroup::s_HappyJumpBattle.m_setRoomIds.insert(dwRoomId);
		log_write(LOG_DEBUG1, "%s, %s, add room id : %d", __FILE__, __FUNCTION__, dwRoomId);
	}
	uint dwEraseRoomCount = 0;
	ReadInt(dwEraseRoomCount);
	for (uint i = 0; i < dwEraseRoomCount; ++i)
	{
		uint dwRoomId = 0;
		ReadInt(dwRoomId);
		HageBattleGroup::s_HappyJumpBattle.m_setRoomIds.erase(dwRoomId);
		log_write(LOG_DEBUG1, "%s, %s, del room id : %d", __FILE__, __FUNCTION__, dwRoomId);
	}
}
