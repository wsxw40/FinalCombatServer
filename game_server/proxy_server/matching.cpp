#include "pch.h"
#include "proxyserver.h"

enum EServerMessage
{
	//SM_Initialize,
	//SM_SyncData,
	//SM_BroadcastChat,
	//SM_UpdateLevelList,
	//SM_UpdateBuffList,
	//SM_UpdateLevelQuest,
	//SM_UpdateFilterKeywords,
	//SM_ResponseRPC,
	//SM_ResponseEnterChannel,
	//SM_RequestClientLeave,
	//SM_UpdateCharacterData,
	//SM_RequestRoomInvite,
	//SM_RequestRoomRefuse,
	//SM_RequestRoomCreate,
	//SM_ResponseMatchingClient,
	//SM_RequestServerStatus,
	//SM_UpdateCharacterVipLevel,

	// [2015/10/9 dengxiaobo]
	SM_ResponseMatching,
	SM_ResponseCancelMatching,

	SM_ResponseMatchingRoomClient,
	SM_MatchingCompleted,
	SM_ResponseMatchingProgress,
	// end
};

enum EClientMessage
{
	CM_RequestMatching,
	CM_RequestCancelMatching,
	CM_RequestMatchingRoomClient,
	CM_RequestMatchingProgress,

	CM_ResponseMatchingEventTime,
	CM_CCU,
};

static void ReconnectMatchServer(MatchingConnection *conn)
{
	conn->Connect(conn->addr);
}

MatchingConnection::MatchingConnection()
:BinaryStream(appcfg.matching_buffersize)
{
	connection = this;
	stream = this;
	connected = false;
}
void MatchingConnection::OnConnected()
{
	connected = true;
	server.UpdateGuildTeamMatchingEventTime();
}
void MatchingConnection::OnDisconnected()
{
	log_write(LOG_INFO, "match server disconnected: %s:%d.", inet_ntoa(addr.sin_addr), ntohs(addr.sin_port));

	if (connected)
	{
		for (ClientConnection * client = server.client_pool.Begin(); client < server.client_pool.End(); client++)
		{
			if (client->matching)
			{
				//client->ResponseMatching(kErrorProxyMatchingFailedServerOffline, false);
				client->ResponseCancelMatching(kErrorProxyMatchingFailedServerOffline, false);
				client->matching = false;
			}
		}
		connected = false;
	}

	Event::AddTimer((EventHandler)&ReconnectMatchServer, this, 1);
}

void MatchingConnection::OnMatchingCompleted()
{
	byte team_type;
	char game_mode;
	int level_id = 0;
	uint dwServerId = 0;

	ReadInt(dwServerId);
	ReadByte(team_type);
	ReadByte(game_mode);

	byte cMatchingCount = 0;
	ReadByte(cMatchingCount);
	ChannelConnection* pChannel = NULL;
	MatchingTeamGroup* pTemMatchingGroup1 = NULL;
	MatchingTeamGroup* pTemMatchingGroup2 = NULL;
	for (byte i = 0; i < cMatchingCount; ++i)
	{
		uint dwMatchingId = 0;
		ReadInt(dwMatchingId);
		MatchingTeamGroup* pMatchingTeamGroup = server.m_pollMatchingTeamGroups.Get(dwMatchingId);
		if (pMatchingTeamGroup)
		{
			if (!pChannel)
			{
				pChannel = server.GetChannel(pMatchingTeamGroup->server_id, pMatchingTeamGroup->channel_id);
			}

			if (!pTemMatchingGroup1)
			{
				pTemMatchingGroup1 = pMatchingTeamGroup;
			}
			else
			{
				for (std::set<uint>::iterator it = pMatchingTeamGroup->characters_uid.begin(); it != pMatchingTeamGroup->characters_uid.end(); ++it)
				{
					ClientConnection* pClient = server.client_pool.Get(*it);
					if (pClient)
					{
						pClient->m_dwMatchingTeamGroupId = 0;
						pTemMatchingGroup1->AddCharacter(pClient);
						pMatchingTeamGroup->DelCharacter(pClient, false);
					}
				}
				server.m_pollMatchingTeamGroups.Free(dwMatchingId);
			}
		}
		else
		{
			log_write(LOG_DEBUG1, "can't find matchinggroup id : %d", dwMatchingId);
		}
	}

	ReadByte(cMatchingCount);
	for (byte i = 0; i < cMatchingCount; ++i)
	{
		uint dwMatchingId = 0;
		ReadInt(dwMatchingId);
		MatchingTeamGroup* pMatchingTeamGroup = server.m_pollMatchingTeamGroups.Get(dwMatchingId);
		if (pMatchingTeamGroup)
		{
			if (!pTemMatchingGroup2)
			{
				pTemMatchingGroup2 = pMatchingTeamGroup;
			}
			else
			{
				for (std::set<uint>::iterator it = pMatchingTeamGroup->characters_uid.begin(); it != pMatchingTeamGroup->characters_uid.end(); ++it)
				{
					ClientConnection* pClient = server.client_pool.Get(*it);
					if (pClient)
					{
						pClient->m_dwMatchingTeamGroupId = 0;
						pTemMatchingGroup2->AddCharacter(pClient);
						pMatchingTeamGroup->DelCharacter(pClient, false);
					}
					
				}
				server.m_pollMatchingTeamGroups.Free(dwMatchingId);
			}
		}
	}
	
	if (game_mode == -1)
	{
		game_mode = 4;
		byte mode = rand() % matching_mode_count.size();
		byte i = 0;
		for (std::map<uint, std::list<uint> >::iterator it = matching_mode_count.begin(); it != matching_mode_count.end(); ++ it)
		{
			if (i == mode)
			{
				game_mode = it->first;
			}
			++i;
		}
	}

	if (IsGameModeValid(game_mode))
	{
		level_id = *(matching_mode_count[game_mode].begin());
		matching_mode_count[game_mode].pop_front();
		matching_mode_count[game_mode].push_back(level_id);
	}
	else
	{
		level_id = 0;
	}
	
	if (level_id == 0)
	{
		if (matching_mode_count.find(game_mode) == matching_mode_count.end())
		{
			if (pTemMatchingGroup1)
			{
				for (std::set<uint>::iterator it = pTemMatchingGroup1->characters_uid.begin(); it != pTemMatchingGroup1->characters_uid.end(); ++it)
				{
					if (ClientConnection *client = server.client_pool.Get(*it))
					{
						if (client->IsConnected())
						{
							client->SetMatching(false);
							client->ResponseCancelMatching(kErrorProxyChannelConnectLevel, false);
						}
					}
				}
				pTemMatchingGroup1->SetMatching(false);
			}
			
			if (pTemMatchingGroup2)
			{
				for (std::set<uint>::iterator it = pTemMatchingGroup2->characters_uid.begin(); it != pTemMatchingGroup2->characters_uid.end(); ++it)
				{
					if (ClientConnection *client = server.client_pool.Get(*it))
					{
						if (client->IsConnected())
						{
							client->SetMatching(false);
							client->ResponseCancelMatching(kErrorProxyChannelConnectLevel, false);
						}
					}
				}
				pTemMatchingGroup2->SetMatching(false);
			}

			log_write(LOG_ERROR, "error game type : %d", game_mode);
			return;
		}

	}
	if (pChannel)
	{
		pChannel->RequestMatchingRoomEnter(game_mode, level_id,
			pTemMatchingGroup1 ? pTemMatchingGroup1->uid : 0,
			pTemMatchingGroup2 ? pTemMatchingGroup2->uid : 0);
	}
	else
	{
		if (pTemMatchingGroup1)
		{
			for (std::set<uint>::iterator it = pTemMatchingGroup1->characters_uid.begin(); it != pTemMatchingGroup1->characters_uid.end(); ++it)
			{
				if (ClientConnection *client = server.client_pool.Get(*it))
				{
					if (client->IsConnected())
					{
						client->SetMatching(false);
						client->ResponseCancelMatching(kErrorProxyChannelConnect, false);
					}
				}
			}
			pTemMatchingGroup1->SetMatching(false);
		}
		
		if (pTemMatchingGroup2)
		{
			for (std::set<uint>::iterator it = pTemMatchingGroup2->characters_uid.begin(); it != pTemMatchingGroup2->characters_uid.end(); ++it)
			{
				if (ClientConnection *client = server.client_pool.Get(*it))
				{
					if (client->IsConnected())
					{
						client->SetMatching(false);
						client->ResponseCancelMatching(kErrorProxyChannelConnect, false);
					}
				}
			}
			pTemMatchingGroup2->SetMatching(false);
		}

		log_write(LOG_DEBUG1, "can't find channel server_id : %d, channel_id : %d", pTemMatchingGroup1 ? pTemMatchingGroup1->server_id : -1, pTemMatchingGroup1? pTemMatchingGroup1->channel_id : -1);
	}

	log_write(LOG_DEBUG1, "matching completed game_mode : %d, level_id : %d", game_mode, level_id);
	
	//log_write(LOG_DEBUG1, "Matching room game mode = 0, set game_mode = %d", game_mode);

	// todo 给channel server 发消息
	//if (ChannelConnection *channel = server.curr_channel)
	//{
	//	int team_count[2];
	//	int level = 0;
	//	channel->BeginWrite();
	//	channel->WriteByte(SM_RequestRoomCreate);
	//	channel->WriteByte(1);
	//	channel->WriteByte(team_type);
	//	channel->WriteByte(game_mode);
	//	byte * room_level = reinterpret_cast<byte*>(channel->write_position);
	//	channel->WriteByte(level);
	//	channel->WriteByte(team_count[0]);
	//	channel->WriteByte(team_count[1]);

	//	WriteMatchingClient(team_type, team_count, level, *channel, game_mode, level_id);

	//	*room_level = level;
	//	*(room_level + 1) = team_count[0];
	//	*(room_level + 2) = team_count[1];

	//	RoomInfoDesc desc;
	//	memset(&desc, 0, sizeof(desc));
	//	desc.use_password = 0;
	//	desc.level_id = level_id;
	//	desc.game_type = game_mode;
	//	desc.max_client_num = max_room_client_count;
	//	desc.spawn_time = 3;
	//	desc.join_halfway = 1;
	//	desc.check_balance = 0;

	//	desc.WriteInfoDesc(channel);

	//	channel->EndWrite();

	//	if (matching_mode_count.find(game_mode) != matching_mode_count.end())
	//	{
	//		matching_mode_count[game_mode]++;
	//	}
	//}
	//else
	//	WriteMatchingClientFailed(kErrorProxyChannelConnect);
}

void MatchingConnection::OnMessage()
{
	byte opcode;
	ReadByte(opcode);

	switch (opcode)
	{
		case SM_ResponseMatching:
			OnResponseMatching();
			break;
		case SM_ResponseCancelMatching:
			OnResponseCancelMatching();
			break;
		case SM_ResponseMatchingRoomClient:
			OnResponseMatchingRoomClient();
			break;
		case SM_MatchingCompleted:
			OnMatchingCompleted();
			break;
		case SM_ResponseMatchingProgress:
			OnResponseMatchingProgress();
			break;
	}
}

void MatchingConnection::OnResponseMatching()
{
	int error;
	uint matching_id;
	byte total_progress;

	ReadInt(error);
	ReadInt(matching_id);
	ReadByte(total_progress);

	MatchingTeamGroup* pMatchingGroup = server.m_pollMatchingTeamGroups.Get(matching_id);
	if (pMatchingGroup)
	{
		for (std::set<uint>::iterator it = pMatchingGroup->characters_uid.begin(); it != pMatchingGroup->characters_uid.end(); ++it)
		{
			if (ClientConnection *client = server.GetClient(*it))
			{
				client->ResponseMatching(error, error == kErrorNone ? true : false, total_progress);
			}
		}
	}
}

void MatchingConnection::RequestMatching( uint server_id, uint matching_id, byte matching_level, byte client_count, byte game_mode, byte team_type, int nLevelId /*= 0*/ )
	{
	BeginWrite();
	WriteByte(CM_RequestMatching);
	WriteInt(server_id);
	WriteInt(matching_id);
	WriteByte(matching_level);
	WriteByte(client_count);
	WriteByte(game_mode);
	WriteByte(team_type);
	// [2015/10/12 dengxiaobo] 地图ID
	WriteInt(nLevelId);
	// end
	EndWrite();
}

void MatchingConnection::RequestCancelMatching(uint matching_id, bool no_return)
{
	BeginWrite();
	WriteByte(CM_RequestCancelMatching);
	WriteInt(matching_id);
	WriteByte(no_return);
	EndWrite();
}
void MatchingConnection::OnResponseCancelMatching()
{
	int error;
	uint matching_id;
	ReadInt(error);
	ReadInt(matching_id);

	byte succ = 0;
	ReadByte(succ);
	if (!succ)
	{
		return;
	}

	uint uid = matching_id;

	MatchingTeamGroup* pMatchingGroup = server.m_pollMatchingTeamGroups.Get(matching_id);
	if (pMatchingGroup)
	{
		for (std::set<uint>::iterator it = pMatchingGroup->characters_uid.begin(); it != pMatchingGroup->characters_uid.end(); ++it)
		{
			if (ClientConnection *client = server.GetClient(*it))
			{
				client->SetMatching(false);
				client->ResponseCancelMatching(error, false);
			}
		}
		pMatchingGroup->SetMatching(false);
	}
}

void MatchingConnection::OnResponseMatchingRoomClient()
{
	byte ret;
	uint dwServerId = 0;
	uint dwChannelId = 0;
	uint dwRoomId = 0;
	ReadInt(dwServerId);
	ReadInt(dwChannelId);
	ReadInt(dwRoomId);
	ReadByte(ret);

	uint arrMatchingClients1[max_room_client_count / 2] = {0};
	uint arrMatchingClients2[max_room_client_count / 2] = {0};

	if (ret)
	{
		byte cTeamCount = 0;
		ReadByte(cTeamCount);
		for (byte i = 0; i < cTeamCount; ++i)
		{
			ReadInt(arrMatchingClients1[i]);
		}
		
		ReadByte(cTeamCount);
		for (byte i = 0; i < cTeamCount; ++i)
		{
			ReadInt(arrMatchingClients2[i]);
		}
	}
	
	if (ChannelConnection *channel = server.GetChannel(dwServerId, dwChannelId))
	{
		channel->ResponseMatchingClient(dwRoomId, ret, arrMatchingClients1, arrMatchingClients2);
	}
	else
	{
		for (int i = 0; i < max_room_client_count/ 2; ++i)
		{
			MatchingTeamGroup* pMatchingGroup = server.m_pollMatchingTeamGroups.Get(arrMatchingClients1[i]);
			if (pMatchingGroup)
			{
				for (std::set<uint>::iterator it = pMatchingGroup->characters_uid.begin(); it != pMatchingGroup->characters_uid.end(); ++it)
				{
					if (ClientConnection *client = server.client_pool.Get(*it))
					{
						if (client->IsConnected())
						{
							client->SetMatching(false);
							client->ResponseCancelMatching(kErrorProxyChannelConnect, false);
						}
					}
				}
				pMatchingGroup->SetMatching(false);
			}

			pMatchingGroup = server.m_pollMatchingTeamGroups.Get(arrMatchingClients2[i]);
			if (pMatchingGroup)
			{
				for (std::set<uint>::iterator it = pMatchingGroup->characters_uid.begin(); it != pMatchingGroup->characters_uid.end(); ++it)
				{
					if (ClientConnection *client = server.client_pool.Get(*it))
					{
						if (client->IsConnected())
						{
							client->SetMatching(false);
							client->ResponseCancelMatching(kErrorProxyChannelConnect, false);
						}
					}
				}
				pMatchingGroup->SetMatching(false);
			}
		}
		
	}
}

uint MatchingConnection::GetGameModeByRandom()
{
	if (matching_mode_count.size() > 0)
	{
		uint mode = 0, count = 0;
		for (std::map<uint, std::list<uint> >::iterator iter = matching_mode_count.begin(); iter != matching_mode_count.end(); ++iter)
		{
			if (mode == 0 || iter->second.size() <= count)
			{
				mode = iter->first;
				count = iter->second.size();
			}
		}
		return mode;
	}
	return 0;
}
void MatchingConnection::OnUpdateLevelList(const std::vector<Level>& level_list)
{
	std::vector<uint> modes;

	matching_mode_count.clear();
	for (std::vector<Level>::const_iterator iter = level_list.begin(); iter != level_list.end(); ++iter)
	{
		// todo 地图信息更新
		//if (iter->type == kGameTypeNovice || iter->type == kGameTypeBoss)
		//	continue;

		if (iter->type / 100)
		{
			matching_mode_count[iter->type % 100].push_back(iter->level_id);
		}
		// 就这样 不用下面的了
		//std::vector<uint>::iterator it = find (modes.begin(), modes.end(), iter->type);
		//if (it == modes.end())
		//{
		//	modes.push_back(iter->type);
		//}
	}

	//std::map<uint, uint> matching_mode;
	//for (std::vector<uint>::const_iterator iter = modes.begin(); iter != modes.end(); ++iter)
	//{
	//	if (matching_mode_count.find(*iter) != matching_mode_count.end())
	//	{
	//		matching_mode[*iter] = matching_mode_count[*iter];
	//	}
	//	else
	//	{
	//		matching_mode[*iter] = 0;
	//	}
	//}

	//matching_mode_count = matching_mode;
}
void MatchingConnection::RequestMatchingProgress(uint matching_id, uint client_uid)
{
	BeginWrite();
	WriteByte(CM_RequestMatchingProgress);
	WriteInt(matching_id);
	WriteInt(client_uid);
	EndWrite();
}
void MatchingConnection::OnResponseMatchingProgress()
{
	uint matching_id;
	uint client_uid;

	ReadInt(matching_id);
	ReadInt(client_uid);

	if (ClientConnection *client = server.GetClient(client_uid))
	{
		byte progress, total_progress;
		ReadByte(progress);
		ReadByte(total_progress);
		client->ResponseMatchingProgress(progress, total_progress);
	}

}

void MatchingConnection::OnUpdateGuildTeamEventTimeCallback( void* pData, uint dwLen )
{
	BeginWrite();
	WriteByte(CM_ResponseMatchingEventTime);
	Write(pData, dwLen);
	EndWrite();
}

void MatchingConnection::RequestMatchingClient( int dwServerId, int dwChannelId, int dwRoomId, int dwLevelId, byte cGameMode, uint dwMatchingLevel, byte cTeamType, byte dwTeam1Count, byte dwTeam2Count )
{
	BeginWrite();
	WriteByte(CM_RequestMatchingRoomClient);
	WriteInt(dwServerId);
	WriteInt(dwChannelId);
	WriteInt(dwRoomId);
	WriteInt(dwLevelId);
	WriteInt(dwMatchingLevel);
	WriteByte(cTeamType);
	WriteByte(cGameMode);
	WriteByte(dwTeam1Count);
	WriteByte(dwTeam2Count);
	EndWrite();
}

void MatchingConnection::UpdateCCU(void* parg)
{
	server.matching_connection.BeginWrite();
	server.matching_connection.WriteByte(CM_CCU);
	uint dwServerId = 0;
	uint dwCount = 0;

	for (std::map<uint, std::map<uint, uint> >::iterator it = server.m_mapClientCount.begin(); it != server.m_mapClientCount.end(); ++it)
	{
		ServerInfo* pInfo = server.GetServerInfo(it->first);
		if (pInfo && ((pInfo->m_eServerType == SvrType_Match) || (pInfo->m_eServerType == SvrType_MatchFighting)))
		{
			for (std::map<uint, uint>::iterator it1 = it->second.begin(); it1 != it->second.end(); ++it1)
			{
				dwCount += it1->second;
			}
		}
	}
	server.matching_connection.WriteInt(dwCount);
	server.matching_connection.EndWrite();

}
