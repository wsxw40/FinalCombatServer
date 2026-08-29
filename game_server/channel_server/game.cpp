#include "pch.h"

enum EGameState
{
	GS_Close,
	GS_Connected,
};

enum ERoomMessage
{
	RM_UpdateKeywords,
	RM_RequestClientEnter,
	RM_UpdateCharacterData,
	RM_RequestClientLeave,
	RM_ResponseBinaryRPC,
	RM_ForwardClientMessage,
	RM_ResponseDisconnect,
	RM_UpdateCharacterPing,
};

enum EGameMessage
{
	GM_ResponseClientEnter,
	GM_NotifyClientLeave,
	GM_RequestBinaryRPC,
	GM_ForwardClientMessage,
	GM_RequestDisconnect,
	GM_KickPerson,
	GM_EndRequestMatchClient,
};

// -----------------------------------------------------------------
// class server connection
// -----------------------------------------------------------------
Game::Game()
	: BinaryStream(appcfg.game_buffersize)
	, state(GS_Close)
	, room(NULL)
{
	connection = this;
	stream = this;
}

Game::~Game()
{
}

// is close
bool Game::IsClose()
{
	return state == GS_Close;
}

// on client connected
void Game::OnConnected()
{
	if (connected_socket != INVALID_SOCKET)
	{
		linger l;
		l.l_onoff = 1;
		l.l_linger = 1;
		setsockopt(connected_socket, SOL_SOCKET, SO_LINGER, &l, sizeof(l));
	}

	state = GS_Connected;

	if (room)
	{
		BeginWrite();
		WriteInt(server.server_id);
		WriteInt(server.channel_id);
		WriteInt(room->host_id);
		WriteByte(server.cServerType);

		// room_option
		WriteRoomOption(*this, room->option);
		
		// old code
		WriteInt(room->option.level_id);
		WriteByte(room->option.game_type);
		// [2015/10/20 dengxiaobo]
		WriteByte(room->option.m_bIsMatchingRoom);
		log_write(LOG_DEBUG1, "%s, %s, room id : %d, is matching room %d", __FILE__, __FUNCTION__, room->id, room->option.m_bIsMatchingRoom);
		// end
		WriteInt(room->option.rule_value);
		WriteByte(room->option.special_mode);
		WriteShort(room->option.round_rebirth_time_max);
		WriteByte(room->option.team_hurt);
		WriteByte(room->option.group_match);
		WriteByte(room->option.check_game_balance);

		WriteLevelInfo(*this, level_info);
		WriteInt(appcfg.idle_kick_open);
		EndWrite();
		
		// keywords
		BeginWrite();
		WriteByte(RM_UpdateKeywords);
		
		int keywords = 0;
		WriteInt((int)server.filter_keywords.size());
		for (std::vector<std::string>::iterator i = server.filter_keywords.begin(); i < server.filter_keywords.end(); ++i)
		{
			WriteString(i->c_str());
			keywords += strlen(i->c_str());
		}
		log_write(LOG_DEBUG1, "keywords %d, %d", keywords, server.filter_keywords.size() * 4);
		
		EndWrite();
	}
}

// on close
void Game::OnDisconnected()
{
	state = GS_Close;
	level_info.level_id = 0;

	log_write(LOG_DEBUG2, "room id : %d, game server close", room->id);
	room->DoGameEnd();
}

// on message
void Game::OnMessage()
{
	if (IsConnected())
	{
		byte msg;
		ReadByte(msg);

		switch (msg)
		{
		case GM_ResponseClientEnter:	OnResponseClientEnter(); break; 
		case GM_NotifyClientLeave: 		OnNotifyClientLeave(); break; 
		case GM_RequestBinaryRPC:		OnRequestBinaryRPC();	break;
		case GM_ForwardClientMessage:	OnForwardClientMessage(); break;
		case GM_RequestDisconnect:		OnReqeustDisconnect();	break;
		case GM_KickPerson:				OnKickPerson();	break;
		case GM_EndRequestMatchClient:	OnEndRequestMatchClient(); break;
		}
	}
}

// request client enter
bool Game::RequestClientEnter(const Client & client, void * client_info, int size, bool viewer)
{
	if (IsConnected())
	{
		log_write(LOG_DEBUG3,"size %d", size);
		if (room->GetClient(client.uid_in_room) == &client && client.state == Client::CS_GameConnecting)
		{
			BeginWrite();
			WriteByte(RM_RequestClientEnter);
			WriteInt(client.uid_in_room);
			WriteInt(client.uid);
			if (room->option.game_type == RoomOption::kBossMode || 
				room->option.game_type == RoomOption::kBossMode2 || 
				room->option.game_type == RoomOption::kZombieMode || 
				room->option.game_type == RoomOption::kBossPVE ||
				room->option.game_type == RoomOption::kCommonZombieMode ||
				room->option.game_type == RoomOption::kAdvenceMode)
			{
				WriteByte(viewer? 2: 0);
			}
			else if (room->option.game_type == RoomOption::kEditMode)
			{
				WriteByte(viewer? 2: 1);
			}
			else if (room->option.game_type == RoomOption::kTDMode)
			{
				WriteByte(viewer? 2: (room->host_group_id == client.character_group_id ? 0 : 1));
			}
			else
			{
				WriteByte(viewer? 2: client.team);
			}
			WriteString(client.head_icon);
			WriteByte(client.is_vip);
			WriteByte(client.business_card);
			WriteByte(client.is_gm);
			WriteInt(client.character_level);
			WriteInt(client.character_group_id);
			//WriteInt(client.fcm_online_minutes);
			//WriteInt(client.fcm_offline_minutes);
			Write(client_info, size);
			EndWrite();
			return true;
		}
	}
	return false;
}

// response client enter
void Game::OnResponseClientEnter()
{
	int uid;
	int error_code;
	ReadInt(uid);
	ReadInt(error_code);
	
	log_write(LOG_DEBUG3, "room id : %d, client enter", uid);

	Client* client = room->GetClient(uid);
	if (client)
	{
		if (error_code == kErrorNone)
		{
			client->OnGameEnter();

			log_write(LOG_DEBUG4, "GameEnterSuccess");
			//notify
			room->Notify(&Client::NotifyGameClientEnter, *client);
		}
		else
		{
			client->OnGameEnterFailed(error_code);
		}
	}
}

// notify client leave
void Game::OnNotifyClientLeave()
{
	uint inroom_id;
	uint uid_in_channel;

	
	ReadInt(inroom_id);
	ReadInt(uid_in_channel);

	Client * client = server.GetClient(uid_in_channel);

	if (client && client->state == Client::CS_InGame)
	{
		room->DoGameClientLeave(*client);
	}
}

// request binary rpc
void Game::OnRequestBinaryRPC()
{
	server.proxy_connection.ForwardBinaryRPC(*this);
}

// response binary rpc
void Game::ResponseBinaryRPC(uint result, const void * data, int size)
{
	BeginWrite();
	WriteByte(RM_ResponseBinaryRPC);
	WriteInt(result);
	Write(data, size);
	EndWrite();
}

// forward client message
void Game::ForwardClientMessage(int inroom_id, byte msg, char * buffer, uint size)
{
	BeginWrite();
	WriteByte(RM_ForwardClientMessage);
	WriteInt(inroom_id);
	WriteByte(msg);
	Write(buffer, size);
	EndWrite();
}


// on forward client message
void Game::OnForwardClientMessage()
{
	uint client_uid;
	ReadInt(client_uid);

	Client * client = server.GetClient(client_uid);

	if (client)
	{
		while (read_position < read_end)
		{
			ushort magic_num;
			ushort size;
			
			ReadShort(magic_num);
			
			if ((magic_num & BinaryStream::MAGICNUM_MASK) != BinaryStream::MAGICNUM)
			{
				log_write(LOG_ERROR, "Game::OnForwardClientMessage() : magic_num is incorrect!!!");
				
				Disconnect();
				
				return;
			}
			
			ReadShort(size);
			
			int pkg_size = size - BinaryStream::HEAD_SIZE;
			
			client->BeginWrite();
			client->Write(ReadData(pkg_size), pkg_size);
			client->EndWrite();
		}
	}

	//log_write(LOG_DEBUG1, "%s, %s, client uid : %d, client point : %d", __FILE__, __FUNCTION__, client_uid, client);
}

// on request disconnect
void Game::OnReqeustDisconnect()
{
	BeginWrite();
	WriteByte(RM_ResponseDisconnect);
	EndWrite();

	Disconnect();
}

// on kick person
void Game::OnKickPerson()
{
	uint room_id;
	uint character_id;
	float kick_time;
	
	ReadInt(room_id);
	ReadInt(character_id);
	ReadFloat(kick_time);
	
	if (room)
	{
		std::map<uint, float>::iterator itr = room->kick_clients.find(character_id);
		if (itr == room->kick_clients.end())
		{
			room->kick_clients.insert(std::pair<uint, float>(character_id, kick_time));
		}
		else
		{
			itr->second = kick_time;
		}
	}
}

// update character data
void Game::UpdateCharacterData(Client & client)
{
	if (IsConnected())
	{
		if (room->GetClient(client.uid_in_room) == &client)
		{
			BeginWrite();
			WriteByte(RM_UpdateCharacterData);
			WriteInt(client.uid);
			WriteInt(client.uid_in_room);

			WriteInt(client.character_id);
			WriteString(client.character_name);
			WriteString(client.character_group);
			WriteInt(client.character_level);
			WriteInt(client.fcm_online_minutes);
			WriteInt(client.fcm_offline_minutes);
			EndWrite();
		}
	}
}

// update character ping
void Game::UpdateCharacterPing()
{
	if (IsConnected() && room)
	{
		BeginWrite();
		WriteByte(RM_UpdateCharacterPing);

		for (RoomSlot * slot = room->slots; slot < endof(room->slots); slot++)
		{
			uint ping = 0;

			if (slot->client)
				ping = (uint)(slot->client->udp_connection.delay_time * 1000);

			WriteInt(ping);
		}

		EndWrite();
	}
}

// request client leave
void Game::RequestClientLeave(Client & client)
{
	if (IsConnected())
	{
		if (room->GetClient(client.uid_in_room) == &client)
		{
			BeginWrite();
			WriteByte(RM_RequestClientLeave);
			WriteInt(client.uid);
			WriteInt(client.uid_in_room);
			EndWrite();
		}
	}
}

void Game::OnEndRequestMatchClient()
{
	room->m_bMatchingRequestClient = false;
}
