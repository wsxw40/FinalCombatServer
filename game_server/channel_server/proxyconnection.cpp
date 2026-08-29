#include "pch.h"
#include <time.h>

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

enum ERPC_Type
{
	RPC_GameRPC,
	RPC_LevelInfo,
	RPC_CharacterInfo,
};

// -----------------------------------------------------------------
// class server connection
// -----------------------------------------------------------------
ProxyConnection::ProxyConnection()
	: BinaryStream(64 * 1024)
	, status_client_count(0)
	, rpc_head_position(0)
	, request_client_count(0)
{
	connection = this;
	stream = this;
	
	memset(client_ping_status, 0, sizeof(client_ping_status));
	memset(client_resend_status, 0, sizeof(client_ping_status));
	status_update_time = 0;
}

ProxyConnection::~ProxyConnection()
{
}

// on client connected
void ProxyConnection::OnConnected()
{
	request_client_count = 0;
	
	BeginWrite();
	WriteInt(server.server_id);
	WriteInt(server.channel_id);
	//WriteString(inet_ntoa(server.client_listener.addr.sin_addr));
	WriteString(server.m_strDomainName.c_str());
	WriteShort(ntohs(server.client_listener.addr.sin_port));
	WriteInt(appcfg.max_client_count);
	EndWrite();
	
	log_write_sys(LOG_INFO, "proxy server connected: %s", sockaddr_ntoa(server.proxy_addr));
}

static void ReconnectProxyServer(void * data)
{
	server.proxy_connection.Connect(server.proxy_addr);
}

void ProxyConnection::OnDisconnected()
{
	// notify all user offline.
	for (Client * client = server.client_pool.Begin(); client < server.client_pool.End(); client++)
	{
		client->tcp_connection.Disconnect();
		client->udp_connection.Disconnect();
	}

	// TODO: make this work.
	//if (!IsConnecting())
	{
		log_write_sys(LOG_INFO, "proxy connection disconnected: %s, reconnecting..!", sockaddr_ntoa(server.proxy_addr));
	}

	Event::AddTimer(&ReconnectProxyServer, 0, 10);
}

	// on message
void ProxyConnection::OnMessage()
{
	byte msg = 255;
	
	try
	{
		if (IsConnected())
		{
			ReadByte(msg);

			switch (msg)
			{
			case SM_SyncData: 				SyncData(); break;
			case SM_BroadcastChat:			OnBroadcastChat(); break;
			case SM_UpdateFilterKeywords:	OnUpdateFilterKeywords(); break;
			case SM_ResponseRPC:			OnResponseRPC(); break;
			case SM_ResponseEnterChannel:	OnResponseEnterChannel(); break;
			case SM_RequestClientLeave:		OnRequestClientLeave(); break;
			case SM_UpdateCharacterData:	OnUpdateCharacterData(); break;
			case SM_NotifyCharacterRefusePreserve: OnNotifyCharacterRefusePreserve(); break;
			case SM_RequestSearchRoom:		OnRequestSearchRoom(); break;
			case SM_SetClientConnectMode:	OnSetClientConnectMode(); break;
			case SM_SetServerNovice:		OnSetServerNovice(); break;
			case SM_UpdateBlackList:		OnUpdateBlackList(); break;
			case SM_UpdateLevelList:		OnUpdateLevelList(); break;
			case SM_SetServerType:			OnSetServerType(); break;
			case SM_RequestMatchingRoomEnter:		OnRequestMatchingRoomEnter(); break;
			case SM_ResponseMatchingClient:	OnResponseMatchingClient(); break;
			case SM_RequestHageBattleRoomCreate: OnRequestHageBattleRoomCreate(); break;
			case SM_RequestHageBattleRoomEnter: OnRequestHageBattleRoomEnter(); break;
			case SM_RequestHageBattleHappyJumpEnter: OnRequestHageBattleHappyJumpEnter(); break;
			
			case SM_RequestDebugCmd: 		OnRequestDebugCmd(); break;
			}
		}
	}
	catch (...)
	{
		log_write(LOG_ERROR, "ProxyConnection::OnMessage() exp. msg = %d", msg);
	}
}

// sync data
void ProxyConnection::SyncData()
{
	if (IsConnected())
	{
		uint client_count = server.online_client_set.size();

		if (status_client_count != client_count)
		{
			status_client_count = client_count;

			BeginWrite();
			WriteByte(CM_SyncData);
			WriteInt(status_client_count);
			EndWrite();
		}
	}
}

// begin request rpc
void ProxyConnection::BeginRPCHead(const char * url)
{
	WriteByte(CM_RequestRPC);
	WriteString(url);
	WriteInt(0);
	rpc_head_position = write_position;
}

// begin rpc param
void ProxyConnection::EndRPCHead()
{
	if (rpc_head_position)
	{
		int size = write_position - rpc_head_position;
		memcpy(rpc_head_position - 4, &size, sizeof(int));
		rpc_head_position = NULL;
	}
}

// forward binary rpc
void ProxyConnection::ForwardBinaryRPC(Game & game)
{
	uint userdata_len;
	char url[256];

	game.ReadString(url, sizeof(url));
	game.ReadInt(userdata_len);

	BeginWrite();
	BeginRPCHead(url);
	WriteByte(RPC_GameRPC);
	WriteInt(game.room->id);
	if (game.room->host_client)
		WriteInt(game.room->host_client->uid);
	else
		WriteInt(0);
	Write(game.ReadData(userdata_len), userdata_len);
	EndRPCHead();
	Write(game.read_position, game.read_end - game.read_position);
	EndWrite();
}

// request character info
void ProxyConnection::RequestCharacterInfo(Client & client, int level_id, byte character_id, byte is_knife)
{
	BeginWrite();
	BeginRPCHead("get_character_info");
	WriteByte(RPC_CharacterInfo);
	WriteInt(client.uid);
	EndRPCHead();
	WriteInt(client.uid);
	WriteInt(client.character_id);
	WriteInt(level_id);
	WriteByte(character_id);
	WriteByte(is_knife);
	EndWrite();
}

// broadcast chat
void ProxyConnection::OnBroadcastChat()
{
	char to[character_name_length];
	char name[character_name_length];
	char msg[chat_length];
	ReadString(to, sizeof(to));
	ReadString(name, sizeof(name));
	ReadString(msg, sizeof(msg));

	for (Client * c = server.client_pool.Begin(); c < server.client_pool.End(); c++)
	{
		if (c->state > Client::CS_Connected)
		{
			c->NotifyChat(to, name, msg);
		}
	}
}

// update filter keywords
void ProxyConnection::OnUpdateFilterKeywords()
{
	server.filter_keywords.clear();

	int keyword_count = 0;
	ReadInt(keyword_count);
	server.filter_keywords.reserve(keyword_count);

	for (int i = 0; i < keyword_count; i++)
	{
		char keyword[256] = {0};
		ReadString(keyword, sizeof(keyword));
		server.filter_keywords.push_back(keyword);
	}

	// build keywords
	DictMatch::Clear();
	for (std::vector<std::string>::iterator i = server.filter_keywords.begin(); i < server.filter_keywords.end(); ++i)
		DictMatch::AddKeyword(i->c_str());
	DictMatch::Build();

	log_write(LOG_INFO, "filter keyword updated.");
}

// update blacklist
void ProxyConnection::OnUpdateBlackList()
{
	server.banned_userid.clear();
	
	int banned_count = 0;
	ReadInt(banned_count);
	
	for (int i = 0; i < banned_count; i++)
	{
		uint tmp_userid;
		
		ReadInt(tmp_userid);
		
		server.banned_userid.insert(tmp_userid);
	}
	
	log_write(LOG_INFO, "blacklist updated.");
}

// update levellist
void ProxyConnection::OnUpdateLevelList()
{
	server.level_list.clear();
	
	int level_count = 0;
	
	ReadInt(level_count);
	
	server.level_list.resize(level_count);
	
	for (int i = 0; i < level_count; i++)
	{
		ReadInt(server.level_list[i].level_id);
		ReadString(server.level_list[i].name, sizeof(server.level_list[i].name));
		ReadByte(server.level_list[i].type);
		
		ReadString(server.level_list[i].show_name, sizeof(server.level_list[i].show_name));
		char str[1024] = { 0 };
		ReadString(str, sizeof(str));
		server.level_list[i].description = str;
		
		ReadByte(server.level_list[i].is_vip);
		ReadByte(server.level_list[i].is_new);
		ReadByte(server.level_list[i].is_gm);
	}

	log_write(LOG_INFO, "levellist updated.");
}

// response rpc
void ProxyConnection::OnResponseRPC()
{
	try
	{
		int error;
		byte type;

		ReadInt(error);
		ReadByte(type);
		
		log_write(LOG_DEBUG1,"%s, %s, error = %d, type = %d, size = %d", __FILE__, __FUNCTION__, error, type, read_end - read_position);
		
		switch (type)
		{
			case RPC_GameRPC:		OnResponseGameRPC(error); break;
			case RPC_LevelInfo:		OnResponseLevelInfo(error); break;
			case RPC_CharacterInfo:	OnResponseCharacterInfo(error); break;
		}
	}
	catch (...)
	{
		log_write(LOG_ERROR, "rpc parse error.");
		throw;
	}
}

// response game rpc
void ProxyConnection::OnResponseGameRPC(uint error)
{
	int room_id;
	int client_id;
	
	ReadInt(room_id);
	ReadInt(client_id);

	Room* room = server.GetRoom(room_id);

	if (room == NULL)
	{
		Client* client = server.GetClient(client_id);

		if (client)
			room = client->room_novice;
	}

	if (room && room->game.IsConnected())
		room->game.ResponseBinaryRPC(error, read_position, read_end - read_position);
}

// response level info
void ProxyConnection::OnResponseLevelInfo(uint error)
{
	int room_id;
	int level_id;
	int client_id;
	int rpc_index;
	
	ReadInt(room_id);
	ReadInt(client_id);
	ReadInt(level_id);
	ReadInt(rpc_index);

	Room* room = server.GetRoom(room_id);

	if (room == NULL)
	{
		Client* client = server.GetClient(client_id);

		if (client)
			room = client->room_novice;
	}

	if (room && rpc_index == room->rpc_index)
	{
		if (error == 0)
		{
			try
			{
				LevelInfo tmp_info;
				ReadLevelInfo(*this, tmp_info);
				room->game.level_info = tmp_info;
			}
			catch (...)
			{
				room->game.level_info.level_id = 0;
				log_write(LOG_ERROR, "level info parse error. room_id=%d, levelid=%d", room_id, level_id);
			}
		}
		else
		{
			room->game.level_info.level_id = 0;
			log_write(LOG_ERROR, "level info rpc error : error=%d, room_id=%d, level_id=%d", error, room_id, level_id);
		}

		room->ResponseLevelInfo();
	}
}

// response character info
void ProxyConnection::OnResponseCharacterInfo(uint error)
{
	uint client_id;
	
	ReadInt(client_id);
	Client* client = server.GetClient(client_id);

	log_write(LOG_DEBUG1,"%s, %s, client id : %d ,error : %d ,size : %d", __FILE__, __FUNCTION__, client_id, error, read_end - read_position);
	
	if (client)
	{
		if (error == 0 && read_end - read_position > 0)
		{
			ReadInt(client_id);
			Room* room = server.GetRoom(client->room_id);

			if (room == NULL)
				room = client->room_novice;

			if (room)
			{
				room->ResponseCharacterInfo(*client, read_position, read_end - read_position);
			}
		}
		else
			client->OnGameEnterFailed(kErrorChannelGameEnterCharacterInfoError);
	}
}

// request level info
void ProxyConnection::RequestLevelInfo(const Room & room)
{
	log_write(LOG_DEBUG4,"RequestLevelInfo level_id : %d", room.option.level_id);
	
	BeginWrite();
	BeginRPCHead("get_level_info");
	WriteByte(RPC_LevelInfo);
	
	WriteInt(room.id);
	if (room.host_client)
		WriteInt(room.host_client->uid);
	else
		WriteInt(0);
	WriteInt(room.option.level_id);
	WriteInt(room.rpc_index);
	EndRPCHead();
	
	WriteInt(room.option.level_id);
	WriteInt(room.id);
	if (room.host_client)
		WriteInt(room.host_client->character_id);
	else
		WriteInt(0);

	WriteInt(server.server_id);
	WriteInt(server.channel_id);
	
	byte group_match = room.option.group_match;
	WriteByte(group_match);
	
	int team_count[2];
	team_count[0] = team_count[1] = 0;

	for (const RoomSlot * slot = room.slots; slot < endof(room.slots); slot++)
	{
		if (slot->client && slot->team < 2)
			team_count[slot->team]++;
	}

	for (int i = 0; i < 2; i++)
	{
		WriteInt(team_count[i]);
		for (const RoomSlot * slot = room.slots; slot < endof(room.slots); slot++)
		{
			if (slot->client && slot->team == i)
				WriteInt(slot->client->character_id);
		}
	}
	
	//	int 房间创建时间
	//	int 游戏开始时间		
	int starttime =	time(NULL);
	WriteInt(starttime);
	WriteInt(starttime);
	
	
	/*WriteInt(room.rpc_index);
	EndRPCHead();
	WriteInt(room.option.level_id);
	WriteInt(room.option.item_id);
	if (room.option.item_id)
	{
		if (room.host_client)
			WriteInt(room.host_client->character_id);
		else
			WriteInt(0);


		int team_count[2];
		team_count[0] = team_count[1] = 0;

		for (const RoomSlot * slot = room.slots; slot < endof(room.slots); slot++)
		{
			if (slot->client && slot->team < 2)
				team_count[slot->team]++;
		}

		for (int i = 0; i < 2; i++)
		{
			WriteInt(team_count[i]);
			for (const RoomSlot * slot = room.slots; slot < endof(room.slots); slot++)
			{
				if (slot->client && slot->team == i)
					WriteInt(slot->client->character_id);
			}
		}
	}*/
	WriteByte(room.option.special_mode);
	byte character_id = room.option.character_id;
	WriteByte(character_id);
	EndWrite();
}

// request channel enter
void ProxyConnection::RequestEnterChannel(uint uid_in_channel, uint uid_in_proxy, uint character_id)
{
	request_client_count++;
	
	BeginWrite();
	WriteByte(CM_RequestEnterChannel);
	WriteInt(uid_in_channel);
	WriteInt(uid_in_proxy);
	WriteInt(character_id);
	WriteInt(request_client_count);
	EndWrite();
}

// notify channel leave
void ProxyConnection::NotifyLeaveChannel(uint uid_in_channel, uint uid_in_proxy, uint character_id)
{
	BeginWrite();
	WriteByte(CM_NotifyLeaveChannel);
	WriteInt(uid_in_channel);
	WriteInt(uid_in_proxy);
	WriteInt(character_id);
	EndWrite();
}

// response channel enter
void ProxyConnection::OnResponseEnterChannel()
{
	request_client_count--;

	int result;
	uint uid_in_channel;

	ReadInt(result);
	ReadInt(uid_in_channel);

	Client * client = server.GetClient(uid_in_channel);

	if (client)
	{
		ReadInt(client->uid_in_proxy);

		if (client->uid_in_proxy)
		{
			uint channel_id;
			char channel_name[256];

			ReadCharacterData(client);

			ReadInt(channel_id);
			ReadString(channel_name, sizeof(channel_name));

			client->ResponseChannelEnter(channel_id, channel_name, result);
		}
		else
		{
			client->Disconnect();
		}
	}
}

// on request client leave
void ProxyConnection::OnRequestClientLeave()
{
	uint uid_in_proxy;
	uint uid_in_channel;

	ReadInt(uid_in_proxy);
	ReadInt(uid_in_channel);

	Client * client = server.GetClient(uid_in_channel);

	if (client && client->uid_in_proxy == uid_in_proxy)
	{
		client->Disconnect();
	}
}

// notify client room changed
void ProxyConnection::NotifyClientRoomChanged(uint uid_in_proxy, int room_id)
{
	BeginWrite();
	WriteByte(CM_NotifyClientRoomChanged);
	WriteInt(uid_in_proxy);
	WriteInt(room_id);
	EndWrite();
}

// notify client status changed
void ProxyConnection::NotifyClientStatusChanged(uint uid_in_proxy, int status)
{
	BeginWrite();
	WriteByte(CM_NotifyClientStatusChanged);
	WriteInt(uid_in_proxy);
	WriteInt(status);
	EndWrite();
}

// notify room preserve
void ProxyConnection::NotifyRoomPreserveSlot(uint room_id, byte slot_id, const char * character_name, const char * invite_name)
{
	BeginWrite();
	WriteByte(CM_NotifyRoomPreserveSlot);
	WriteInt(room_id);
	WriteByte(slot_id);
	WriteString(character_name);
	WriteString(invite_name);
	EndWrite();
}

// notify room cancel preserve
void ProxyConnection::NotifyRoomCancelPreserveSlot(uint room_id, byte slot_id, const char * character_name)
{
	BeginWrite();
	WriteByte(CM_NotifyRoomCancelPreserveSlot);
	WriteInt(room_id);
	WriteByte(slot_id);
	WriteString(character_name);
	EndWrite();
}

// on update character data
void ProxyConnection::OnUpdateCharacterData()
{
	uint client_uid;
	ReadInt(client_uid);

	Client * client = server.GetClient(client_uid);

	if (client)
	{
		log_write(LOG_DEBUG3, "update character data: %s", client->character_name);

		ReadCharacterData(client);

		Room * room = server.GetRoom(client->room_id);

		if (room)
		{
			room->UpdateCharacterData(*client);
		}
	}
}

// on notify character refuse preserve
void ProxyConnection::OnNotifyCharacterRefusePreserve()
{
	uint room_id;
	byte slot_id;
	byte result;

	ReadInt(room_id);
	ReadByte(slot_id);

	Room * room = server.GetRoom(room_id);

	if (room)
	{
		if (0 == room->DoCancelPreserveSlot(slot_id))
		{
			room->UpdateClientCount();
		}
	}
}


// on request search room
void ProxyConnection::OnRequestSearchRoom()
{
	uint client_id;
	uint game_type;
	uint level_id;
	byte num_clients;
	byte playing;
	byte waiting;
	uint room_id = 0;

	ReadInt(client_id);
	ReadInt(game_type);
	ReadInt(level_id);
	ReadByte(num_clients);
	ReadByte(playing);
	ReadByte(waiting);
	
	for (Room * room = server.room_pool.Begin(); room < server.room_pool.End(); room++)
	{
		if (!room->IsReady())
			continue;

		if (game_type != -1 && room->option.game_type != game_type)
			continue;

		if (level_id != 0 && room->option.level_id != level_id)
			continue;

		if (room->option.use_password)
			continue;

		if (room->client_count < num_clients)
			continue;

		if (room->client_count >= room->option.client_count)
			continue;

		if (playing && room->IsPlaying())
		{
			room_id = room->id;
			break;
		}

		if (waiting && room->IsWaiting())
		{
			room_id = room->id;
			break;
		}
	}

	BeginWrite();
	WriteByte(CM_ResponseSearchRoom);
	WriteInt(client_id);
	WriteInt(room_id);
	EndWrite();
}

void ProxyConnection::OnSetClientConnectMode()
{
	ReadByte(server.is_tcp);
	
	log_write(LOG_DEBUG3, "set client connect mode: %d", server.is_tcp);
}

void ProxyConnection::OnSetServerNovice()
{
	ReadByte(server.novice);
}

void ProxyConnection::OnSetServerType()
{
	ReadByte(server.cServerType);
}

void ProxyConnection::OnRequestMatchingRoomEnter()
{
	byte cGameMode = 0;
	int nLevelId = 0;
	uint dwMatchingLevel = 0;
	uint dwMatchingId1 = 0;
	uint dwMatchingId2 = 0;

	uint arrTeam1[max_room_client_count / 2] = {0};
	uint arrTeam2[max_room_client_count / 2] = {0};

	ReadByte(cGameMode);
	ReadInt(nLevelId);
	ReadInt(dwMatchingLevel);
	ReadInt(dwMatchingId1);
	ReadInt(dwMatchingId2);
	uint dwTeamCount1 = 0;

	Client* pLeader = NULL;

	RoomOption option;
	//option.name[128];
	//option.level_id;
	//option.map_level;
	//option.rand_key[64];
	//option.character_id;
	//option.is_knife;
	//option.is_gm;
	//option.map_name[256];

	//option.use_random_map;

	//option.client_count;
	//option.viewer_count;

	//option.game_type;
	//option.rule_value;
	//option.special_mode;

	//option.round_rebirth_time_max;
	//option.dead_view_mode;
	//option.can_join_on_playing;
	//option.check_game_balance;
	//option.team_hurt;
	//option.group_match;
	//option.auto_start;

	//option.use_password;
	//option.password[64];

	//// room itme
	//option.item_id;
	//option.item_resource[64];
	//option.item_name[64];

	//option.m_bIsMatchingRoom;
	option.game_type = cGameMode;
	option.level_id = nLevelId;
	option.is_gm = 0;
	option.client_count = max_room_client_count;
	option.m_bIsMatchingRoom = true;

	Room* room = server.GetRoomFree();

	int result = kErrorChannelRoomCreate;

	if (!room)
	{
		result = kErrorChannelRoomCreateFullRoom;
	}
	room->m_dwMatchingLevel = dwMatchingLevel;
	ReadInt(dwTeamCount1);
	for (int i = 0; i < dwTeamCount1; ++i)
	{
		ReadInt(arrTeam1[i]);
		uint dwIdInProxy = 0;
		ReadInt(dwIdInProxy);
		int dwTargetSlotId = i + 1;
		// 先将玩家从以前的房间T出去
		Client* pClient = server.GetClient(arrTeam1[i]);
		if (pClient)
		{
			Room* pRoom = server.GetRoom(pClient->room_id);
			if (pRoom)
			{
				if (pRoom == room)
				{
					log_write(LOG_DEBUG1, "the same room line : %d", __LINE__);
				}
				else
				{
					if (!pLeader)
					{
						option = pRoom->option;
						
						option.game_type = cGameMode;
						option.level_id = nLevelId;
						
						// 客户端设置的人数可能会少
						option.client_count = max_room_client_count;
						option.m_bIsMatchingRoom = true;
						// 击杀数都是100人
						switch(option.game_type)
						{
							case RoomOption::kTeam:
							{
								option.rule_value = appcfg.match_rule_value;
							}
							break;
							case RoomOption::kHoldPoint:
							case RoomOption::kPushVehicle:
							case RoomOption::kNovice:
							case RoomOption::kTeamDeathMatch:
							case RoomOption::kBossMode:
							case RoomOption::kKnifeMode:
							case RoomOption::kBombMode:
							case RoomOption::kStreetBoyMode:
							case RoomOption::kZombieMode:
							case RoomOption::kBossPVE:
							case RoomOption::kCommonZombieMode:
							case RoomOption::kBossMode2:
							case RoomOption::kItemMode:
							case RoomOption::kEditMode:
							case RoomOption::kTDMode:
							case RoomOption::KMoonMode:
							case RoomOption::kAdvenceMode:
							case RoomOption::kSurvivalMode:
							{
								option.rule_value = 3;
							}
							break;
							default:
							{
							}
							break;
						}
						option.level_id = nLevelId;
					}
					pClient->ResponseRoomLeave(pRoom);
					//pRoom->DoRoomClientLeave(*pClient);
				}
				if (pRoom->client_count != 4)
				{
					log_write(LOG_DEBUG1, "%s, %s, user in room with improper method name : %s", __FILE__, __FUNCTION__, pClient->character_name);
				}
			}
			else
			{
				log_write(LOG_DEBUG1, "can't find room id : %d", pClient->room_id);
			}
			if (!pLeader)
			{
				pLeader = pClient;
				if (room && result == kErrorChannelRoomCreate)
				{
					result = room->DoRoomCreate(*pLeader, option);
					pClient->ResponseRoomCreate(result, room);
					log_write(LOG_DEBUG1, "%s, %s, room create ret : %d, state : %d, room_id : %d, level_id : %d", __FILE__, __FUNCTION__, result, room->state, room->id, room->option.level_id);
				}
			}
			else
			{
				if (room && result == kErrorNone)
				{
					result = room->DoRoomClientEnter(*pClient, "", NULL, 0);
					pClient->ResponseRoomEnter(result, room);
					log_write(LOG_DEBUG1, "room enter ret : %d, room_id : %d, character name : %s", result, room->id, pClient->character_name);
				}
			}
			if (room && result == kErrorNone)
			{
				//room->DoClientChangeTeam(*pClient, 0);
				int dwOldSlotId = room->GetClientSlot(*pClient);
				int ret = room->DoClientChangeSlot(*pClient, dwTargetSlotId);
				int dwNewSlotId = room->GetClientSlot(*pClient);
				log_write(LOG_DEBUG1, "change slot ret : %d, old slot id : %d, request slot id : %d, new slot id : %d character name : %s", ret, dwOldSlotId, dwTargetSlotId, dwNewSlotId, pClient->character_name);
				ret = room->DoClientReady(*pClient, true);
				log_write(LOG_DEBUG1, "client ready ret : %d, slot id : %d, character name : %s", ret, room->GetClientSlot(*pClient), pClient->character_name);
			}
		}
		else
		{
			log_write(LOG_DEBUG1, "can't find client id : %d", arrTeam1[i]);

			// 这时候 可能玩家还没进这个房间 需要玩家自己进
			BeginWrite();
			WriteByte(CM_RequestIntoTargetRoom);
			WriteInt(dwIdInProxy);
			WriteInt(server.server_id);
			WriteInt(server.channel_id);
			WriteInt(room->id);
			WriteInt(dwTargetSlotId);	// 坑位
			EndWrite();
		}
	}

	uint dwTeamCount2 = 0;
	ReadInt(dwTeamCount2);
	for (int i = 0; i < dwTeamCount2; ++i)
	{
		ReadInt(arrTeam2[i]);
		uint dwIdInProxy = 0;
		ReadInt(dwIdInProxy);
		int dwTargetSlotId = i + max_room_client_count / 2 + 1;
		// 先将玩家从以前的房间T出去
		Client* pClient = server.GetClient(arrTeam2[i]);
		if (pClient)
		{
			Room* pRoom = server.GetRoom(pClient->room_id);
			if (pRoom)
			{
				if (pRoom == room)
				{
					log_write(LOG_DEBUG1, "the same room line : %d", __LINE__);
				}
				else
				{
					pClient->ResponseRoomLeave(pRoom);
					//pRoom->DoRoomClientLeave(*pClient);
				}
				if (pRoom->client_count != 4)
				{
					log_write(LOG_DEBUG1, "%s, %s, user in room with improper method name : %s", __FILE__, __FUNCTION__, pClient->character_name);
				}
			}
			else
			{
				log_write(LOG_DEBUG1, "can't find room id : %d", pClient->room_id);
			}
			if (room && result == kErrorNone)
			{
				result = room->DoRoomClientEnter(*pClient, "", NULL, 0);
				pClient->ResponseRoomEnter(result, room);
				log_write(LOG_DEBUG1, "room enter ret : %d, room_id : %d, character name : %s", result, room->id, pClient->character_name);
			}
			if (room && result == kErrorNone)
			{
				int dwOldSlotId = room->GetClientSlot(*pClient);
				//room->DoClientChangeTeam(*pClient, 0);
				int ret = room->DoClientChangeSlot(*pClient, dwTargetSlotId);
				int dwNewSlotId = room->GetClientSlot(*pClient);
				log_write(LOG_DEBUG1, "change slot ret : %d, old slot id : %d, request slot id : %d, new slot id : %d character name : %s", ret, dwOldSlotId, dwTargetSlotId, dwNewSlotId, pClient->character_name);
				room->DoClientReady(*pClient, true);
				log_write(LOG_DEBUG1, "client ready ret : %d, slot id : %d, character name : %s", ret, room->GetClientSlot(*pClient), pClient->character_name);
			}
		}
		else
		{
			log_write(LOG_DEBUG1, "can't find client id : %d", arrTeam2[i]);
			// 这时候 可能玩家还没进这个房间 需要玩家自己进
			BeginWrite();
			WriteByte(CM_RequestIntoTargetRoom);
			WriteInt(dwIdInProxy);
			WriteInt(server.server_id);
			WriteInt(server.channel_id);
			WriteInt(room->id);
			WriteInt(dwTargetSlotId);	// 坑位
			EndWrite();
		}
	}

	//启动游戏
	int ret = kErrorChannelGameStart;
	log_write(LOG_DEBUG1, "room state : %d, room_id : %d, level_id : %d", room->state, room->id, room->option.level_id);
	ret = room->DoGameStart();

	log_write(LOG_DEBUG1, "ProxyConnection::OnRequestMatchingRoomEnter game_mode : %d, level_id : %d, matching_level : %d, matching_id1 : %d, matching_id2 : %d, teamcount1 : %d, teamcount2 : %d, result : %d", cGameMode, nLevelId, dwMatchingLevel, dwMatchingId1, dwMatchingId2, dwTeamCount1, dwTeamCount2, ret);

	if (ret == kErrorNone)
	{
		if (pLeader)
		{
			pLeader->ResponseGameStart(ret);
		}
	}

	room->option.m_bIsMatchingRoom = true;
	log_write(LOG_DEBUG1, "%s, %s, room id : %d, is matching room %d", __FILE__, __FUNCTION__, room->id, room->option.m_bIsMatchingRoom);

	BeginWrite();
	WriteByte(CM_ResponseMatchingRoomEnter);
	WriteInt(nLevelId);
	WriteInt(dwMatchingId1);
	WriteInt(dwMatchingId2);
	WriteInt(room->id);

	EndWrite();


	//BeginWrite();
	//WriteByte(SM_ResponseRoomCreate);
	//WriteInt(result);
	//if (result == kErrorNone && room)
	//{
	//	WriteRoomInfo(*room);

	//	for (RoomSlot * slot = room->slots; slot < endof(room->slots); slot++)
	//	{
	//		WriteByte(slot->id);
	//		WriteByte(slot->team);
	//		WriteInt(slot->status);
	//		WriteString(slot->preserve_character_name);
	//	}
	//}
	//EndWrite();
}

void ProxyConnection::RequestMatchingClient( uint room_id, uint client_level, byte team_type, byte game_mode, int dwLevelId, byte team0_client_count, byte team1_client_count )
{
	BeginWrite();
	WriteByte(CM_RequestMatchingClient);
	WriteInt(room_id);
	WriteInt(client_level);
	WriteInt(dwLevelId);
	WriteByte(team_type);
	WriteByte(game_mode);
	WriteByte(team0_client_count);
	WriteByte(team1_client_count);
	EndWrite();
}

void ProxyConnection::OnResponseMatchingClient()
{
	// todo 这里要说明下 已经收到消息了 因为房间是在不停刷新的
	uint dwRoomId = 0;
	byte cRet = 0;
	ReadInt(dwRoomId);
	ReadByte(cRet);

	uint arrTeam0[max_room_client_count / 2];
	uint arrTeam1[max_room_client_count / 2];
	if (cRet)
	{
		//byte cCount = 0;
		//ReadByte(cCount);
		//for(int i = 0; i < cCount; ++i)
		//{
		//	ReadInt(arrTeam0[i]);
		//}
		//ReadByte(cCount);
		//for(int i = 0; i < cCount; ++i)
		//{
		//	ReadInt(arrTeam1[i]);
		//}
	}

	Room* pRoom = server.GetRoom(dwRoomId);
	if (pRoom)
	{
		pRoom->m_bWaitForMatchingCallBack = false;
	}

	if(!cRet)
	{
		// todo 不设了 就5秒请求一次
		//pRoom->m_fWaitForMatchingCallBackTime = 0.0f;
	}
	// 不在这里往里加了 就让room每隔一段时间检查 人没齐就继续请求
}

// on request debugcmd
void ProxyConnection::OnRequestDebugCmd()
{
	char szBuffer[512] = {0};
	
	uint cmd_size;
	std::vector<std::string> cmd_list;
	std::string cmd;
	
	ReadInt(cmd_size);
	for (uint i = 0; i < cmd_size; i++)
	{	
		ReadString(szBuffer, sizeof(szBuffer));
		
		cmd = szBuffer;
		cmd_list.push_back(cmd);
	}
	
	if (cmd_list.size() > 0)
	{
		if (cmd_list[0] == "loglevel" && 
			cmd_list.size() == 2)
		{
			uint loglevel = atoi(cmd_list[1].c_str());
			log_set_output_level(loglevel);
		}
		else if (cmd_list[0] == "reloadcfg")
		{
			appcfg.ReloadConfigFile();
		}
	}
}

// read character data
void ProxyConnection::ReadCharacterData(Client * client)
{
	ReadInt(client->character_id);
	ReadByte(client->is_vip);
	ReadByte(client->net_bar_level);
	ReadByte(client->business_card);
	ReadByte(client->is_gm);

	ReadString(client->head_icon, sizeof(client->head_icon));
	ReadString(client->character_name, sizeof(client->character_name));
	ReadInt(client->character_group_id);
	ReadString(client->character_group, sizeof(client->character_group));
	ReadInt(client->character_level);
	ReadInt(client->character_exp);
	ReadInt(client->fcm_online_minutes);
	ReadInt(client->fcm_offline_minutes);
	ReadInt(client->top);
	ReadInt(client->fightnum);
	ReadFloat(client->win_rate);
}

// update channel status
void ProxyConnection::UpdateChannelStatus()
{
	double time = Event::GetTime();
	if (time - status_update_time > 1)
	{
		const int ping_status_count = sizeof(client_ping_status)/sizeof(client_ping_status[0]);
		const int resend_status_count = sizeof(client_resend_status)/sizeof(client_resend_status[0]);

		static const int ping_range[ping_status_count] =
		{
			30, 60, 90, 120, 150, 180, 210, 240, 270, 300,
		};

		static const double resend_range[resend_status_count] =
		{
			0.0, 0.01, 0.03, 0.05, 0.07, 0.09, 0.1, 0.2, 0.5, 1.0
		};

		// ping and resend status
		memset(client_ping_status, 0, sizeof(client_ping_status));
		memset(client_resend_status, 0, sizeof(client_resend_status));

		for (Client *client = server.client_pool.Begin(); client < server.client_pool.End(); client++)
		{
			if (client->udp_connection.IsConnected())
			{
				int delay = (int)(client->udp_connection.delay_time * 1000);
				double retry = (double)client->udp_connection.num_packets_retry / (double)client->udp_connection.num_packets_send;
				int index = 0;
				
				for (index = 0; index < ping_status_count; index++)
					if (ping_range[index] >= delay)
						break;

				client_ping_status[index] ++;

				
				for (index = 0; index < resend_status_count; index++)
					if (resend_range[index] >= retry)
						break;

				client_resend_status[index] ++;
			}
		}

		// room count and game count.
		int room_count = 0;
		int game_count = 0;
		int game_client_count = 0;

		for (Room *room = server.room_pool.Begin(); room < server.room_pool.End(); room++)
		{
			if (room->state > Room::RS_Idle)
			{
				room_count++;

				if (room->state == Room::RS_Playing)
				{
					game_count++;

					for (int slot = 0; slot < max_room_client_count; slot++)
					{
						Client * client = room->GetClient(slot);
						if (client && client->state > Client::CS_InRoom)
							game_client_count ++;
					}
				}
			}
		}

		// send data
		BeginWrite();
		WriteByte(CM_UpdateChannelStatus);
		Write(client_ping_status, sizeof(client_ping_status));
		Write(client_resend_status, sizeof(client_resend_status));
		WriteInt(room_count);
		WriteInt(game_count);
		WriteInt(game_client_count);
		EndWrite();

		status_update_time = time;
	}
}

void ProxyConnection::OnRequestHageBattleRoomCreate()
{
	uint dwBattleUid = 0;
	ReadInt(dwBattleUid);
	uint dwClientUid = 0;
	ReadInt(dwClientUid);
	RoomOption oRoomOp;
	ReadRoomOption(*this, oRoomOp);
	
	Client* pClient = server.GetClient(dwClientUid);
	if (!pClient)
	{
		BeginWrite();
		WriteByte(CM_ResponseHageBattleRoomCreate);
		WriteInt(kErrorChannelRoomCreate);
		WriteInt(dwBattleUid);
		WriteInt(dwClientUid);
		WriteInt(0);
		EndWrite();
		return;
	}
	
	Room* room = server.GetRoomFree();
	
	switch(oRoomOp.game_type)
	{
	case kTeam:
		{
			oRoomOp.rule_value = appcfg.hage_battle_team_rule_value;
			oRoomOp.special_mode = RoomOption::kNormal;
			oRoomOp.is_knife = false;
		}
		break;
	case kHoldPoint:
		{
			oRoomOp.rule_value = 2;
			oRoomOp.special_mode = RoomOption::kNormal;
			oRoomOp.is_knife = false;
		}
	case kPushVehicle:
		{
			oRoomOp.rule_value = 2;
			oRoomOp.special_mode = RoomOption::kNormal;
			oRoomOp.is_knife = false;
		}
		break;
	case kNovice:
	case kTeamDeathMatch:
		{
			oRoomOp.rule_value = 3;
			oRoomOp.special_mode = RoomOption::kNormal;
			oRoomOp.is_knife = false;
		}
		break;
	case kBossMode:
	case kKnifeMode:
		{
			oRoomOp.rule_value = appcfg.hage_battle_knife_rule_value;
			oRoomOp.special_mode = RoomOption::kKnife;
			oRoomOp.is_knife = true;
		}
		break;
		break;
	case kBombMode:
	case kStreetBoyMode:
	case kZombieMode:
	case kBossPVE:
	case kCommonZombieMode:
	case kBossMode2:
	case kItemMode:
	case kEditMode:
	case kTDMode:
	case KMoonMode:
	case kAdvenceMode:
	case kSurvivalMode:
	case kGameTypeCount:
		{
			oRoomOp.rule_value = 999;
		}
		break;
	default:
		{
			oRoomOp.rule_value = 999;
		}
		break;
	}

	
	int result = kErrorChannelRoomCreate;

	if (oRoomOp.is_gm == 1 && pClient->is_gm == 0)
	{
		result = kErrorChannelRoomCreateByGmError;
	}
	else
	{
		if (room)
			result = room->DoRoomCreate(*pClient, oRoomOp);
		else
			result = kErrorChannelRoomCreateFullRoom;
	}

	BeginWrite();
	WriteByte(CM_ResponseHageBattleRoomCreate);
	WriteInt(result);
	WriteInt(dwBattleUid);
	WriteInt(pClient->uid_in_proxy);
	WriteInt(room->id);
	EndWrite();
	
	pClient->ResponseRoomCreate(result, room);
	
	log_write(LOG_DEBUG1, "%s, %s mode : %d, client uid : %d, battle uid : %d, name : %s, result : %d, room id : %d",
		__FILE__, __FUNCTION__, oRoomOp.game_type, dwClientUid, dwBattleUid, pClient->character_name, result, room->id);
	//log_write(LOG_DEBUG1, "%s, %s, game mode : %d, client uid : %d, cid : %d, name : %s, in_room_id : %d, create room,  result : %d, room_id : %d, is matching room : %d", __FILE__, __FUNCTION__, op.game_type, uid, character_id, character_name, uid_in_room, result, room? room_id: -1, room ? room->option.m_bIsMatchingRoom : 0);
}

void ProxyConnection::OnRequestHageBattleRoomEnter()
{
	log_write(LOG_DEBUG1, "%s, %s", __FILE__, __FUNCTION__);
	uint dwClientUid = 0;
	uint dwRoomId = 0;
	ReadInt(dwClientUid);
	ReadInt(dwRoomId);
	
	Client* pClient = server.GetClient(dwClientUid);
	if (!pClient)
	{
		return;
	}
	
	Room* pRoom = server.GetRoom(dwRoomId);
	if (!pRoom)
	{
		return;
	}
	
	uint dwResult = pRoom->DoRoomClientEnter(*pClient, "sdfgde", NULL, 0);
	pClient->ResponseRoomEnter(dwResult, pRoom);
	log_write(LOG_DEBUG1, "%s, %s, DoRoomClientEnter result %d, name : %s", __FILE__, __FUNCTION__, dwResult, pClient->character_name);
	
	dwResult = pRoom->DoClientChangeTeam(*pClient, 0);
	log_write(LOG_DEBUG1, "%s, %s, DoClientChangeTeam result %d, name : %s", __FILE__, __FUNCTION__, dwResult, pClient->character_name);
	
	dwResult = pRoom->DoClientReady(*pClient, true);
	log_write(LOG_DEBUG1, "%s, %s, DoClientReady result %d, name : %s", __FILE__, __FUNCTION__, dwResult, pClient->character_name);
}

void ProxyConnection::OnRequestHageBattleHappyJumpEnter()
{
	uint dwClientUid = 0;
	uint dwRoomCount = 0;
	RoomOption oRoomOption;
	
	ReadInt(dwClientUid);
	ReadRoomOption(*this, oRoomOption);
	ReadInt(dwRoomCount);
	
	oRoomOption.can_join_on_playing = true;
	
	std::vector<uint> vecRoomIds;
	for (uint i = 0; i < dwRoomCount; ++i)
	{
		uint dwRoomId = 0;
		ReadInt(dwRoomId);
		vecRoomIds.push_back(dwRoomId);
	}
	
	Client* pClient = server.GetClient(dwClientUid);
	if (!pClient)
	{
		log_write(LOG_DEBUG1, "can not find client uid : %d", dwClientUid);
		return;
	}
	
	if (pClient->room_id != 0)
	{
		log_write(LOG_DEBUG1, "%s, %s, ready in room : %d, name : %s", __FILE__, __FUNCTION__, pClient->room_id, pClient->character_name);
		return;
	}
	
	std::vector<uint> vecNewRoom;
	std::vector<uint> vecEraseRoom;
	
	int dwResult = kErrorChannelRoomCreate;
	if (dwRoomCount == 0)
	{
		Room* pRoom = server.GetRoomFree();
		if (pRoom)
		{
			dwResult = pRoom->DoRoomCreate(*pClient, oRoomOption);
			pClient->ResponseRoomCreate(dwResult, pRoom);
		}
		if (dwResult == kErrorNone)
		{
			dwResult = pRoom->DoGameStart();
			if (dwResult == kErrorNone)
			{
				pClient->ResponseGameStart(dwResult);
				vecNewRoom.push_back(pRoom->id);
			}
		}
	}
	else
	{
		uint dwCurrentTime = Event::GetTime();
		for (uint i = 0; i < dwRoomCount; ++i)
		{
			Room* pRoom = server.GetRoom(vecRoomIds[i]);
			if (pRoom)
			{
				if (dwCurrentTime - pRoom->m_dwCreateTime < 60)
				{
					dwResult = pRoom->DoRoomClientEnter(*pClient, NULL, NULL, 0);
					if (dwResult == kErrorNone)
					{
						pClient->ResponseRoomEnter(dwResult, pRoom);
						dwResult = pRoom->DoClientReady(*pClient, true);
						if (dwResult != kErrorNone)
						{
							log_write(LOG_DEBUG1, "%s, %s, enter room but ready error result : %d, name : %s", __FILE__, __FUNCTION__, dwResult, pClient->character_name);
						}
						dwResult = pRoom->DoGameClientEnter(*pClient);
						if (dwResult != kErrorNone)
						{
							log_write(LOG_DEBUG1, "%s, %s, enter room but start game error result : %d, name : %s", __FILE__, __FUNCTION__, dwResult, pClient->character_name);
						}
						log_write(LOG_DEBUG1, "%s, %s, enter room result : %d, name : %s", __FILE__, __FUNCTION__, dwResult, pClient->character_name);
						break;
					}
				}
				log_write(LOG_DEBUG1, "%s, %s, can not enter room result : %d", __FILE__, __FUNCTION__, dwResult);
				vecEraseRoom.push_back(vecRoomIds[i]);
			}
		}
		
		if (dwResult != kErrorNone)
		{
			Room* pRoom = server.GetRoomFree();
			if (pRoom)
			{
				dwResult = pRoom->DoRoomCreate(*pClient, oRoomOption);
				pClient->ResponseRoomCreate(dwResult, pRoom);
			}
			if (dwResult == kErrorNone)
			{
				dwResult = pRoom->DoGameStart();
				if (dwResult == kErrorNone)
				{
					pClient->ResponseGameStart(dwResult);
					vecNewRoom.push_back(pRoom->id);
				}
			}
		}
	}
	
	BeginWrite();
	WriteByte(CM_ResponseHageBattleHappyJumpEnter);
	WriteInt((uint)vecNewRoom.size());
	for (uint i = 0; i < vecNewRoom.size(); ++i)
	{
		WriteInt(vecNewRoom[i]);
	}
	WriteInt((uint)vecEraseRoom.size());
	for (uint i = 0; i < vecEraseRoom.size(); ++i)
	{
		WriteInt(vecEraseRoom[i]);
	}
	EndWrite();
}
