#include "pch.h"

#define USE_COMPRESSOR 0
#define USE_ENCODER 1
#define USE_ROOM_WAITTIME 300

enum EServerMessage
{
	SM_NotifyChannelClientEnter,
	SM_NotifyChannelClientLeave,
	SM_NotifyRoomCreate,
	SM_NotifyRoomClose,
	SM_NotifyRoomClientCountChanged,
	SM_NotifyRoomClientEnter,
	SM_NotifyRoomClientLeave,
	SM_NotifyRoomClientUpdate,
	SM_NotifyRoomList,
	SM_NotifyRoomHostChanged,
	SM_NotifyRoomChangeOption,
	SM_NotifyRoomStateChanged,
	SM_NotifyRoomKickClient,
	SM_NotifyClientChangeTeam,
	SM_NotifyClientReady,
	SM_NotifyClientAutoStart,
	SM_NotifyClientAutoStartCancel,
	SM_NotifyGameStart,
	SM_NotifyGameClientEnter,
	SM_NotifyGameClientLeave,
	SM_NotifyGameEnd,
	SM_NotifyGameLeave,

	SM_ResponseChannelEnter,

	SM_ResponseRoomList,
	SM_ResponseRoomCreate,
	SM_ResponseRoomEnter,
	SM_ResponseRoomLeave,
	SM_ResponseRoomClientList,
	SM_ResponseRoomChangeOption,
	SM_ResponseRoomChangeTeam,
	SM_ResponseRoomReady,
	SM_ResponseGameStart,
	SM_ResponseRoomChangeSlotStatus,
	SM_NotifyRoomChangeSlotStatus,
	SM_ResponseRoomChangeSlot,
	SM_NotifyClientChangeSlot,
	SM_ResponseRoomPreserveSlot,
	SM_ResponseClientList,
	SM_ResponseTDData,

	SM_ResponseGameEnter = 100,
	SM_NotifyChat,
};

enum EClientMessage
{
	CM_RequestChannelEnter,

	CM_RequestRoomList,
	CM_RequestRoomCreate,
	CM_RequestRoomEnter,
	CM_RequestRoomLeave,
	CM_RequestRoomClientList,
	CM_RequestRoomChangeOption,
	CM_RequestRoomChangeTeam,
	CM_RequestRoomReady,
	CM_RequestRoomKickClient,
	CM_RequestGameStart,
	CM_RequestRoomChangeSlotStatus,
	CM_RequestRoomChangeSlot,
	CM_RequestRoomPreserveSlot,
	CM_RequestRoomTeamEnter,
	CM_RequestClientList,
	CM_RequestNovice,
	CM_RequestTDData,
	
	CM_ConnectionCheck,

	CM_RequestRoomEnterWithSlotId,

	CM_RequestGameEnter = 100,
	CM_RequestChat,
};

// -----------------------------------------------------------------
// class client
// -----------------------------------------------------------------
Client::Client()
	: BinaryStream(appcfg.client_buffersize)
	, state(CS_Idle)
	, uid(0)
	, room_id(0)
	, uid_in_room(0)
	, ready(false)
	, character_id(0)
	, fcm_online_minutes(0)
	, room_novice(NULL)
{
	no_delay = false;
	udp_connection.send_frequency = 0.05;
	
	is_vip = 0;
	net_bar_level = 0;
	business_card = 0;
	is_gm = 0;
	
	memset(head_icon, 0, sizeof(head_icon));
	
	top = 0;
	fightnum = 0;
	win_rate = 0;
}

Client::~Client()
{
	if (room_novice)
	{
		delete room_novice;
		room_novice = NULL;
	}
}

// disconnect
void Client::Disconnect()
{
	tcp_connection.Disconnect();
	udp_connection.Disconnect();
}

// on client connected
void Client::OnConnected()
{
	state = CS_Connected;
	
	connection_reporttime = appcfg.connection_reporttime / 2;
	
	connection_check_time = 0;
	connection_check_error_count = 0;
	
#if USE_COMPRESSOR
	huffman_compressor.Reset();
	compressor = &huffman_compressor;
#endif

#if USE_ENCODER
	xor_encoder.Reset();
	encoder = &xor_encoder;
#endif

	log_write(LOG_DEBUG3, "client uid : %d, client connected", uid);
}

void Client::OnDisconnected()
{
	// notify character leave channel
	if (uid_in_proxy)
	{
		server.proxy_connection.NotifyLeaveChannel(uid, uid_in_proxy, character_id);
	}

	if (state >= CS_InRoom)
	{
		Room* room = server.GetRoom(room_id);
		if (room)
			room->DoRoomClientLeave(*this);
	}

	if (room_novice)
	{
		room_novice->DoRoomClientLeave(*this);
		delete room_novice;
		room_novice = NULL;
	}

	state = CS_Idle;

	server.online_client_set.erase(this);
	server.client_pool.Free(uid);

	room_id = 0;
	character_id = 0;
	ready = false;
	fcm_online_minutes = 0;
	fcm_offline_minutes = 0;
	character_name[0] = 0;
	character_group_id = 0;
	character_group[0] = 0;
	character_level = 0;
	character_exp = 0;
	
	top = 0;
	fightnum = 0;
	win_rate = 0;
	
	tcp_connection.stream = NULL;
	udp_connection.stream = NULL;
	connection = NULL;
	encoder = NULL;

	server.OnStatusChanged();
}

// receive message
void Client::OnMessage()
{
	byte msg;
	ReadByte(msg);

	switch (state)
	{
	case CS_Connected:
		OnMessageConnected(msg);
		break;

	case CS_InChannel:
		OnMessageInChannel(msg);
		break;

	case CS_InRoom:
		OnMessageInRoom(msg);
		break;

	case CS_InGame:
		OnMessageInGame(msg);
		break;

	default:
		log_write(LOG_DEBUG1, "client uid: %d, cid: %d, name: %s, on message warning, state: %d, msg: %d", uid, character_id, character_name, state, msg);
		break;
	}
}

// Request Channel Enter 
void Client::RequestChannelEnter()
{
	uint lobby_uid;
	uint character_id;

	ReadInt(lobby_uid);
	ReadInt(character_id);

	server.proxy_connection.RequestEnterChannel(uid, lobby_uid, character_id);
}

void Client::ResponseChannelEnter(uint channel_id, const char * channel_name, int result)
{
	log_write(LOG_DEBUG1, "%s, %s, client uid : %d, cid : %d , name : %s, client enter channel result(%d)", __FILE__, __FUNCTION__, uid, character_id, character_name, result);

	BeginWrite();
	WriteByte(SM_ResponseChannelEnter);
	WriteInt(result);
	WriteInt(channel_id);
	WriteString(channel_name);
	EndWrite();

	if (result == kErrorNone)
	{
		state = CS_InChannel;
		// insert this to online character list
		server.online_client_set.insert(this);

		refresh_clientlist_time = 0;
		ResponseClientList(0);

		NotifyRoomList();

		// server status changed
		server.OnStatusChanged();
	}
}

// request chat
void Client::RequestChat()
{
	char channel[character_name_length];
	char msg[chat_length];

	ReadString(channel, sizeof(channel));
	ReadString(msg, sizeof(msg));
	
	bool can_talk = gag.Send();
	bool is_banned = server.banned_userid.find(character_id) != server.banned_userid.end();

	if (can_talk && !is_banned)
	{
		DictMatch::Replace(msg);

		for (Client * c = server.client_pool.Begin(); c < server.client_pool.End(); c++)
		{
			if (c->state == state)
			{
				if (state == Client::CS_InRoom && c->room_id != room_id)
					continue;

				c->NotifyChat(channel, character_name, msg);
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

// request room list
void Client::RequestRoomList()
{
	log_write(LOG_DEBUG3, "client uid : %d, cid : %d , name : %s, client request room list", uid, character_id, character_name);

	BeginWrite();
	WriteByte(SM_ResponseRoomList);
	WriteShort(server.room_count);
	int count = 0;

	for (Room * room = server.room_pool.Begin() + 1; room < server.room_pool.End(); room++)
	{
		if (room->IsReady())
		{
			WriteRoomInfo(*room);
			
			count++;
		}
	}
	if (count != server.room_count)
	{
		log_write(LOG_ERROR, "client uid : %d, cid : %d, name : %s, reqeust room list count error\n", uid, character_id, character_name);
	}

	EndWrite();

	log_write(LOG_DEBUG1, "%s, %s, room_count : %d, client uid : %d, cid : %d, name : %s", __FILE__, __FUNCTION__, count, uid, character_id, character_name);
}

// request room create
void Client::RequestRoomCreate()
{
	RoomOption option;
	ReadRoomOption(*this, option);

	Room* room = server.GetRoomFree();

	if (server.cServerType == SvrType_HageBattle)
	{
		if (option.game_type != kAdvenceMode)
		{
			ResponseRoomCreate(kErrorChannelRoomCreate, room);
			return;
		}
	}
	ResponseRoomCreate(room, option);
}

void Client::ResponseRoomCreate(Room * room, RoomOption & op)
{
	int result = kErrorChannelRoomCreate;

	if (op.is_gm == 1 && is_gm == 0)
	{
		result = kErrorChannelRoomCreateByGmError;
	}
	else
	{
		if (room)
			result = room->DoRoomCreate(*this, op);
		else
			result = kErrorChannelRoomCreateFullRoom;
	}

	BeginWrite();
	WriteByte(SM_ResponseRoomCreate);
	WriteInt(result);
	if (result == kErrorNone && room)
	{
		WriteRoomInfo(*room);

		for (RoomSlot * slot = room->slots; slot < endof(room->slots); slot++)
		{
			WriteByte(slot->id);
			WriteByte(slot->team);
			WriteInt(slot->status);
			WriteString(slot->preserve_character_name);
		}
	}
	EndWrite();
	log_write(LOG_DEBUG1, "%s, %s, game mode : %d, client uid : %d, cid : %d, name : %s, in_room_id : %d, create room,  result : %d, room_id : %d, is matching room : %d", __FILE__, __FUNCTION__, op.game_type, uid, character_id, character_name, uid_in_room, result, room? room_id: -1, room ? room->option.m_bIsMatchingRoom : 0);
}

void Client::ResponseRoomCreate(int dwResult, Room* pRoom)
{
	BeginWrite();
	WriteByte(SM_ResponseRoomCreate);
	WriteInt(dwResult);
	if (dwResult == kErrorNone && pRoom)
	{
		WriteRoomInfo(*pRoom);

		for (RoomSlot * slot = pRoom->slots; slot < endof(pRoom->slots); slot++)
		{
			WriteByte(slot->id);
			WriteByte(slot->team);
			WriteInt(slot->status);
			WriteString(slot->preserve_character_name);
		}
	}
	EndWrite();
	log_write(LOG_DEBUG3, "client uid : %d, cid : %d, name : %s, in_room_id : %d, create room,  result : %d, room_id : %d", uid, character_id, character_name, uid_in_room, dwResult, pRoom? room_id: -1);
}

// request room enter
void Client::RequestRoomEnter(bool team_enter)
{
	uint room_id;
	char password[64];

	ReadInt(room_id);
	ReadString(password, sizeof(password));

	int result = kErrorChannelRoomEnter;
	Room* room = server.GetRoom(room_id);

	if (room)
	{
		if (team_enter)
		{
			byte member_count = 0;

			ReadByte(member_count);

			if (member_count < 5)
			{
				char member_names[5][character_name_length];
				const char * members[5] = {0};

				for (int i = 0; i < member_count; i ++)
				{
					ReadString(member_names[i], sizeof(member_names[i]));
					members[i] = member_names[i];
				}
				
				result = room->DoRoomClientEnter(*this, password, members, member_count);
			}
			else
				result = kErrorChannelRoomEnterMemberSize;
		}
		else
		{
			if (room->option.game_type == RoomOption::kTDMode && 
				room->IsPlaying() && 
				room->host_group_id == character_group_id)
				result = kErrorChannelRoomEnterRoomError;
			else
				result = room->DoRoomClientEnter(*this, password, NULL, 0);
		}
		
		if (server.cServerType == SvrType_HageBattle)
		{
			Client* pLeader = room->host_client;
			if (pLeader->character_group_id == character_group_id)
			{
				room->DoClientChangeTeam(*this, 0);
			}
			else
			{
				room->DoClientChangeTeam(*this, 1);
				room->DoClientReady(*this, true);
				if (room->IsPlaying())
				{
					room->DoGameClientEnter(*this, true);
				}
			}
			
		}
	}
	else
		result = kErrorChannelRoomEnterRoomError;
		

	log_write(LOG_DEBUG1, "%s, %s, client uid : %d, cid : %d, name : %s, in_room_id : %d, enter room, result : %d, room_id : %d", __FILE__, __FUNCTION__, uid, character_id, character_name, uid_in_room, result, room_id);

	BeginWrite();
	WriteByte(SM_ResponseRoomEnter);
	WriteInt(result);
	if (result == kErrorNone && room)
	{
		// write room info
		WriteRoomInfo(*room);

		for (RoomSlot * slot = room->slots; slot < endof(room->slots); slot++)
		{
			WriteByte(slot->id);
			WriteByte(slot->team);
			WriteInt(slot->status);
			WriteString(slot->preserve_character_name);
		}
	}
	EndWrite();
}

void Client::ResponseRoomEnter(int dwResult, Room* pRoom)
{
	BeginWrite();
	WriteByte(SM_ResponseRoomEnter);
	WriteInt(dwResult);
	if (dwResult == kErrorNone && pRoom)
	{
		// write room info
		WriteRoomInfo(*pRoom);

		for (RoomSlot * slot = pRoom->slots; slot < endof(pRoom->slots); slot++)
		{
			WriteByte(slot->id);
			WriteByte(slot->team);
			WriteInt(slot->status);
			WriteString(slot->preserve_character_name);
		}
	}
	EndWrite();
}

// request room leave
void Client::RequestRoomLeave()
{
	log_write(LOG_DEBUG3, "client uid : %d, cid : %d, name : %s, in_room_id : %d, leave room, room_id : %d", uid, character_id, character_name, uid_in_room, room_id);

	Room* room = server.GetRoom(room_id);
	if (room)
	{
		ResponseRoomLeave(room);
	}
	else if (room_novice)
	{
		ResponseRoomLeave(room_novice);
	}
}

// response room leave
void Client::ResponseRoomLeave(Room * room)
{
	if (room)
	{
		BeginWrite();
		WriteByte(SM_ResponseRoomLeave);
		EndWrite();
		room->DoRoomClientLeave(*this);
	}
}

// request room client list
void Client::RequestRoomClientList()
{
	Room* room = server.GetRoom(room_id);
	if (room)
	{
		BeginWrite();
		WriteByte(SM_ResponseRoomClientList);

		int count = 0;

		for (RoomSlot * slot = room->slots; slot < endof(room->slots); slot++)
		{
			if (slot->client)
				count++;
		}

		WriteInt(count);

		for (RoomSlot * slot = room->slots; slot < endof(room->slots); slot++)
		{
			if (slot->client)
			{
				WriteClientInfo(*slot->client);
			}
		}

		EndWrite();
	}
	log_write(LOG_DEBUG1, "client uid : %d, cid : %d, name : %s, in_room_id : %d, reuqest room client list, room_id : %d", uid, character_id, character_name, uid_in_room, room_id);
}

// request room change option
void Client::RequestRoomChangeOption()
{
	RoomOption option;
	ReadRoomOption(*this, option);
	Room* room = server.GetRoom(room_id);
	int result = kErrorChannelOption;

	if (server.cServerType == (byte)SvrType_Match)
	{
		log_write(LOG_DEBUG1, "%s, %s, in match channel but client request change room option improper name : %s", __FILE__, __FUNCTION__, character_name);
	}
	else
	{
		if (room)
		{
			if (room->host_id == character_id)
			{
				//option.client_count = room->option.client_count;
				//option.viewer_count = room->option.viewer_count;
				
				result = room->DoChangeOption(*this, option, false);
			}
			else
				result = kErrorChannelOptionNotHost;
		}
		else
			result = kErrorChannelOptionRoomError;
	}

	log_write(LOG_DEBUG4, "client uid : %d, cid : %d, name : %s, in_room_id : %d, change room option, result : %d, room_id : %d\n", uid, character_id, character_name, uid_in_room, result, room_id);

	BeginWrite();
	WriteByte(SM_ResponseRoomChangeOption);
	WriteInt(result);
	EndWrite();
}


// request room change team
void Client::RequestRoomChangeTeam()
{
	byte t;
	ReadByte(t);
	Room* room = server.GetRoom(room_id);
	int result = kErrorChannelRoomChangeTeam;

	if (room)
		result = room->DoClientChangeTeam(*this, t);

	log_write(LOG_DEBUG4, "client uid : %d, cid : %d, name : %s, in_room_id : %d, change team, result : %d, room_id : %d, team : %d\n", uid, character_id, character_name, uid_in_room, result, room_id, t);

	BeginWrite();
	WriteByte(SM_ResponseRoomChangeTeam);
	WriteInt(result);
	EndWrite();
}

// request room change slot
void Client::RequestRoomChangeSlot()
{
	byte slot_id;
	ReadByte(slot_id);

	int result = kErrorChannelChangeSlot;

	Room * room = server.GetRoom(room_id);
	if (room)
		result = room->DoClientChangeSlot(*this, slot_id);
	else
		result = kErrorChannelChangeSlotRoomError;

	log_write(LOG_DEBUG4, "client change slot. cid : %d, room_id : %d, slot_id : %d, result : %d", character_id, room_id, slot_id, result);

	BeginWrite();
	WriteByte(SM_ResponseRoomChangeSlot);
	WriteInt(result);
	EndWrite();
}

// request room change slot status
void Client::RequestRoomChangeSlotStatus()
{
	byte slot_id;
	uint status;
	int result = kErrorChannelChangeSlotStatus;

	ReadByte(slot_id);
	ReadInt(status);

	Room * room = server.GetRoom(room_id);
	if (room)
		result = room->DoChangeSlotStatus(*this, slot_id, (RoomSlot::Status)status);
	else
		result = kErrorChannelChangeSlotStatusRoomError;

	log_write(LOG_DEBUG1, "change slot status. cid : %d, room_id : %d, slot_id : %d, status : %d, result : %d", character_id, room_id, slot_id, status, result);

	BeginWrite();
	WriteByte(SM_ResponseRoomChangeSlotStatus);
	WriteInt(result);
	EndWrite();
}

// request room preserve slot.
void Client::RequestRoomPreserveSlot()
{
	int result = kErrorChannelPreserveSlot;
	byte slot_id;
	char name[character_name_length];
	
	ReadByte(slot_id);
	ReadString(name, sizeof(name));

	Room * room = server.GetRoom(room_id);

	if (room)
	{
		if (room->host_id == character_id)
		{
			result = room->DoPreserveSlot(slot_id, name, character_name);

			if (result == 0)
				room->UpdateClientCount();
		}
	}

	log_write(LOG_DEBUG4, "preserve slot. cid : %d, room_id : %d, slot_id : %d, name : %s, result : %d", character_id, room_id, slot_id, name, result);

	BeginWrite();
	WriteByte(SM_ResponseRoomPreserveSlot);
	WriteInt(result);
	EndWrite();
}

// request client list
void Client::RequestClientList()
{
	// TODO: limit request frequency.
	uint start;
	ReadInt(start);
	ResponseClientList(start);
}

// response client list
void Client::ResponseClientList(uint start)
{
	if (state < CS_InChannel || state > CS_InRoom)
		return;

	BeginWrite();
	WriteByte(SM_ResponseClientList);

	uint id = 0;
	uint size = 0;
	uint count = server.online_client_set.size();

	for (ChannelServer::ClientSet::const_iterator i = server.online_client_set.begin(); i != server.online_client_set.end(); ++i)
	{
		Client * client = *i;

		if (client)
		{
			if (id >= start)
			{
				if (++size >= 20)
					break;
			}
			id++;
		}
	}

	WriteInt(count);
	WriteInt(start);
	WriteInt(size);
	
	id = 0;
	for (ChannelServer::ClientSet::const_iterator i = server.online_client_set.begin(); i != server.online_client_set.end(); ++i)
	{
		Client * client = *i;

		if (client)
		{
			if (id >= start)
			{
				WriteInt(client->character_id);
				WriteString(client->character_name);
				WriteString(client->character_group);
				WriteString(client->head_icon);
				WriteInt(client->character_level);
				WriteByte(client->is_vip);
				WriteByte(client->net_bar_level);
				WriteByte(client->business_card);
				WriteByte(client->is_gm);
				
				if (log_get_output_level() >= LOG_DEBUG5)
				{
					log_write(LOG_DEBUG5, "ResponseClientList, character_name : %s, client->character_name : %s", 
							character_name, client->character_name);
				}

				if (--size == 0)
					break;
			}
			id++;
		}
	}

	EndWrite();
	refresh_clientlist_time = 5;
}

// request room ready
void Client::RequestRoomReady()
{
	byte r;
	ReadByte(r);
	Room* room = server.GetRoom(room_id);
	int result = kErrorChannelClientReady;

	if (room)
		result = room->DoClientReady(*this, r);
	if(r)
	{
		roomwait_time = -1;
	}
	else
	{
		roomwait_time = USE_ROOM_WAITTIME;
	}

	log_write(LOG_DEBUG4, "client uid : %d, cid : %d, name : %s, in_room_id : %d, room ready, result : %d, room_id : %d, ready : %d", uid, character_id, character_name, uid_in_room, result, room_id, r);
	
	BeginWrite();
	WriteByte(SM_ResponseRoomReady);
	WriteInt(result);
	EndWrite();
}

bool Client::RoomInfoCheck(Room * room)
{
	if (room)
	{
		if (room->option.game_type == RoomOption::kEditMode || 
			room->option.game_type == RoomOption::kTDMode)
		{
			if (room->option.round_rebirth_time_max > 15)
				room->option.round_rebirth_time_max = 15;
			
			return true;
		}
		
		for (int i = 0; i < server.level_list.size(); ++i)
		{
			if (server.level_list[i].level_id == room->option.level_id && server.level_list[i].type == room->option.game_type)
			{
				switch (room->option.game_type)
				{
				case RoomOption::kTeam:
				case RoomOption::kItemMode:
					{
						if (room->option.rule_value > 150)
							room->option.rule_value = 150;
						if (room->option.round_rebirth_time_max > 15)
							room->option.rule_value = 10;
					}
					break;
				case RoomOption::kHoldPoint:
				case RoomOption::kPushVehicle:
				case RoomOption::kBossMode:
				case RoomOption::KMoonMode:
					{
						if (room->option.rule_value > 5)
							room->option.rule_value = 3;
					}
					break;
				case RoomOption::kBombMode:
				case RoomOption::kTeamDeathMatch:
				case RoomOption::kStreetBoyMode:
				case RoomOption::kZombieMode:
					{
						if (room->option.rule_value > 9)
							room->option.rule_value = 5;
					}
					break;
				case RoomOption::kCommonZombieMode:
					{
					}
					break;
				case RoomOption::kAdvenceMode:
					{
						if (room->option.rule_value > 3)
							room->option.rule_value = 1;
					}
					break;
				case RoomOption::kSurvivalMode:
					{

					}
					break;
				}
				return true;
			}
		}
	}
	
	log_write(LOG_DEBUG1, "%s, %s, level id : %d, game type : %d", __FILE__, __FUNCTION__, room->option.level_id, room->option.game_type);
	return false;
}

// request game start
void Client::RequestGameStart()
{
	Room* room = server.GetRoom(room_id);
	int result = kErrorChannelGameStart;

	if (server.cServerType == (byte)SvrType_Match || server.cServerType == (byte)SvrType_MatchFighting)
	{
		log_write(LOG_DEBUG1, "%s, %s, in match channel but client request game start improper name : %s", __FILE__, __FUNCTION__, character_name);
	}
	else
	{
		if (is_gm == 0 && room->option.is_gm == 1)
		{
			result = kErrorChannelRoomCreateByGmError;
		}
		else
		{
			if (room && character_id == room->host_id && RoomInfoCheck(room))
				result = room->DoGameStart();
			roomwait_time = -1;
		}
	}

	log_write(LOG_DEBUG3, "RequestGameStart result : %d", result);

	if (server.cServerType == SvrType_HageBattle && result != kErrorNone)
	{
	}
	else
	{
		BeginWrite();
		WriteByte(SM_ResponseGameStart);
		WriteInt(result);
		EndWrite();
	}
	
	if (room && result != 0)
	{
		room->autostart_begin_time = -1;
		room->Notify(&Client::NotifyClientAutoStartCancel, *this);
	}
}

void Client::RequestNovice()
{
	if (!room_novice)
		room_novice = new Room;

	if (room_novice)
	{
		RoomOption option;
		strcpy(option.name,"ÐÂÊÖÑµÁ·");
		strcpy(option.map_name, "level_train");
		option.level_id = RoomOption::TRAIN_ID;
		option.game_type = RoomOption::kNovice;
		option.rule_value = 0;
		ResponseRoomCreate(room_novice, option);
		room_novice->DoGameStart();

		BeginWrite();
		WriteByte(SM_ResponseGameStart);
		WriteInt(kErrorNone);
		EndWrite();
	}
}

void Client::RequestTDData()
{
	int level_id;
	int map_level;
	int res_value;
	char rand_key[64];
	
	ReadInt(level_id);
	ReadInt(map_level);
	ReadInt(res_value);
	ReadString(rand_key, sizeof(rand_key));
	
	Room* room = server.GetRoom(room_id);
	int result = kErrorChannelOption;
	
	if (room && character_id == room->host_id && 
		(room->option.game_type == RoomOption::kTDMode || room->option.game_type == RoomOption::kEditMode))
		result = room->DoChangeTDOption(*this, level_id, map_level, res_value, rand_key);
	
	BeginWrite();
	WriteByte(SM_ResponseTDData);
	WriteInt(result);
	EndWrite();
}

// notify game start
void Client::NotifyGameStart()
{
	if (state == CS_InRoom && ready)
	{
		if (ready)
		{
			Room* room = server.GetRoom(room_id);
			int result = kErrorChannelGameEnter;

			if (room == NULL)
				room = room_novice;

			if (room)
				result = room->DoGameClientEnter(*this, true);

			log_write(LOG_DEBUG1, "NotifyGameStart client uid : %d, cid : %d, name : %s, in_room_id : %d, enter game, result : %d, room_id : %d", uid, character_id, character_name, uid_in_room, result, room_id);

			// on game enter
			if (result != kErrorNone)
			{
				log_write(LOG_DEBUG3, "NotifyGameStart Failed");
				OnGameLeave();
				ResponseGameEnter(result);
			}
		}
		else
		{
			BeginWrite();
			WriteByte(SM_NotifyGameStart);
			EndWrite();
		}
	}
	else
	{
		log_write(LOG_DEBUG1, "state : %d, ready : %d, character name : %s", state, (int)ready, character_name);
	}
}

// response game end
void Client::NotifyGameEnd()
{
	if (state == CS_InRoom)
	{
		BeginWrite();
		WriteByte(SM_NotifyGameEnd);
		EndWrite();
	}
}

// request game enter
void Client::RequestGameEnter()
{
	if (state == CS_InRoom)
	{
		if (server.cServerType == (byte)SvrType_Match || server.cServerType == (byte)SvrType_MatchFighting)
		{
			log_write(LOG_DEBUG1, "%s, %s, in match channel but client request game start improper name : %s", __FILE__, __FUNCTION__, character_name);
			OnGameLeave();
			ResponseGameEnter(kErrorChannelGameEnter);
			return;
		}
		
		Room* room = server.GetRoom(room_id);
		int result = kErrorChannelGameEnter;
		if (room)
			result = room->DoGameClientEnter(*this);

		log_write(LOG_DEBUG3,
			"RequestGameEnter client uid : %d, cid : %d, name : %s, in_room_id : %d, enter game, result : %d, room_id : %d",
			uid, character_id, character_name, uid_in_room, result, room_id);
		
		// on game enter
		if (result != kErrorNone)
		{
			OnGameLeave();
			ResponseGameEnter(result);
		}
	}
}

// request room kick client
void Client::RequestRoomKickClient()
{
	byte id;
	ReadByte(id);

	if (state == CS_InRoom)
	{
		Room* room = server.GetRoom(room_id);
		if (room)
			room->DoRoomKickClient(*this, id);
	}
}

// notify room kick client
void Client::NotifyRoomKickClient(Client & client)
{
	if (character_id == client.character_id || state == CS_InRoom )
	{
		BeginWrite();
		WriteByte(SM_NotifyRoomKickClient);
		WriteInt(client.character_id);
		EndWrite();
	}
}

// response game enter
void Client::ResponseGameEnter(int result)
{
	BeginWrite();
	WriteByte(SM_ResponseGameEnter);
	WriteInt(result);
	EndWrite();
}

// on message connected
void Client::OnMessageConnected(byte msg)
{
	switch (msg)
	{
	case CM_RequestChannelEnter:
		RequestChannelEnter();
		break;

	//case CM_ConnectionCheck:
	//	ConnectionCheck();
	//	break;
		
	default:
		log_write(LOG_DEBUG1, "client uid: %d, cid: %d, name: %s, on message connected warning, state: %d, msg: %d", uid, character_id, character_name, state, msg);
		Disconnect();
		break;
	}
}

// on message in channel
void Client::OnMessageInChannel(byte msg)
{
	switch (msg)
	{
	case CM_RequestChat:
		RequestChat();
		break;

	case CM_RequestRoomList:
		RequestRoomList();
		break;

	case CM_RequestRoomCreate:
		RequestRoomCreate();
		break;

	case CM_RequestRoomEnter:
		RequestRoomEnter(false);
		break;

	case CM_RequestRoomTeamEnter:
		RequestRoomEnter(true);
		break;

	case CM_RequestClientList:
		RequestClientList();
		break;

	case CM_RequestNovice:
		RequestNovice();
		break;

	case CM_RequestRoomEnterWithSlotId:
		OnRequestEnterRoomWithSlotId();
		break;
		
	//case CM_ConnectionCheck:
	//	ConnectionCheck();
	//	break;

	default:
		log_write(LOG_DEBUG1, "client uid: %d, cid: %d, name: %s, on message in channel warning, state: %d, msg: %d", uid, character_id, character_name, state, msg);
		break;
	}
}

// on message in room
void Client::OnMessageInRoom(byte msg)
{
	switch (msg)
	{
	case CM_RequestChat:
		RequestChat();
		break;

	case CM_RequestClientList:
		RequestClientList();
		break;

	case CM_RequestRoomLeave:
		RequestRoomLeave();
		break;

	case CM_RequestRoomClientList:
		RequestRoomClientList();
		break;

	case CM_RequestRoomChangeOption:
		RequestRoomChangeOption();
		break;

	case CM_RequestRoomChangeTeam:
		RequestRoomChangeTeam();
		break;

	case CM_RequestRoomChangeSlot:
		RequestRoomChangeSlot();
		break;

	case CM_RequestRoomPreserveSlot:
		RequestRoomPreserveSlot();
		break;

	case CM_RequestRoomChangeSlotStatus:
		RequestRoomChangeSlotStatus();
		break;

	case CM_RequestRoomReady:
		RequestRoomReady();
		break;

	case CM_RequestGameStart:
		RequestGameStart();
		break;

	case CM_RequestGameEnter:
		RequestGameEnter();
		break;

	case CM_RequestRoomKickClient:
		RequestRoomKickClient();
		break;
		
	case CM_RequestTDData:
		RequestTDData();
		break;
		
	case CM_ConnectionCheck:
		ConnectionCheck();
		break;

	case CM_RequestRoomEnterWithSlotId:
		OnRequestEnterRoomWithSlotId();
		break;

	default:
		log_write(LOG_DEBUG1, "client uid: %d, cid: %d, name: %s, on message in room warning, state: %d, msg: %d", uid, character_id, character_name, state, msg);
		break;
	}
}

// on message in game
void Client::OnMessageInGame(byte msg)
{
	Room * room = server.GetRoom(room_id);

	if (room == NULL)
		room = room_novice;

	if (room)
	{
		if (room->game.IsConnected())
		{
			if (msg == CM_ConnectionCheck)
			{
				ConnectionCheck();
				return;
			}
			else
			{
				if (msg == CM_RequestRoomClientList)
				{
					RequestRoomClientList();
					return;
				}
			}
			
			room->game.ForwardClientMessage(uid_in_room, msg, read_position, read_end - read_position);
		}
	}
}

// write room info
void Client::WriteRoomInfo(Room & room)
{
	WriteShort(room.id);
	WriteInt(room.host_id);
	WriteString(room.host_name);
	WriteByte(room.host_client ? room.host_client->is_vip : 0);
	WriteByte(room.host_client ? room.host_client->net_bar_level : 0);
	WriteInt(room.host_client ? room.host_client->character_level : 0);
	WriteByte(room.state);
	WriteByte(room.client_count);
	WriteRoomOption(*this, room.option);
}

// write client info
void Client::WriteClientInfo(Client & client)
{
	Room * room = server.GetRoom(client.room_id);
	byte is_host = room ? client.character_id == room->host_id : false;
	byte in_game = client.state == CS_InGame;
	
	WriteInt(client.uid_in_room);
	WriteInt(client.character_id);
	WriteString(client.character_name);
	WriteString(client.character_group);
	WriteString(client.head_icon);
	WriteInt(client.character_level);
	WriteInt(client.character_exp);
	WriteByte(client.team);
	WriteByte(client.is_vip);
	WriteByte(client.net_bar_level);
	WriteByte(client.business_card);
	WriteByte(client.is_gm);
	WriteByte(client.ready);
	WriteInt(client.top);
	WriteInt(client.fightnum);
	WriteFloat(client.win_rate);
	WriteByte(is_host);
	WriteByte(in_game);
}

// on room enter
void Client::OnRoomEnter()
{
	if (state == CS_InChannel)
	{
		state = CS_InRoom;

		server.proxy_connection.NotifyClientRoomChanged(uid_in_proxy, room_id);

		roomwait_time = USE_ROOM_WAITTIME;

		log_write(LOG_DEBUG4, "client uid : %d, cid : %d, name : %s, state room enter", uid, character_id, character_name);
	}
}

// on room leave
void Client::OnRoomLeave()
{
	if (state == CS_InRoom)
	{
		state = CS_InChannel;

		server.proxy_connection.NotifyClientRoomChanged(uid_in_proxy, 0);

		roomwait_time = -1;
		log_write(LOG_DEBUG4, "client uid : %d, cid : %d, name : %s, state room leave", uid, character_id, character_name);

		NotifyRoomList();
	}
}

// on enter game
void Client::OnGameEnter()
{
	if (state == CS_GameConnecting)
	{
		state = CS_InGame;

		server.proxy_connection.NotifyClientStatusChanged(uid_in_proxy, state);

		roomwait_time = -1;
		log_write(LOG_DEBUG4, "client uid : %d, cid : %d, name : %s, state game enter", uid, character_id, character_name);
	}
}

// on enter game failed
void Client::OnGameEnterFailed(int result)
{
	if (state == CS_GameConnecting || state == CS_InRoom)
	{
		OnGameLeave();
		ResponseGameEnter(result);

		server.proxy_connection.NotifyClientStatusChanged(uid_in_proxy, state);

		log_write(LOG_DEBUG3, "client uid : %d, cid : %d, name : %s, state game enter failed", uid, character_id, character_name);
	}
}

// on enter game
void Client::OnGameEnterBefore()
{
	//Event::ModifySocket(connected_socket, this, false, false);
	//Event::RemoveSocket(connected_socket);

	state = CS_GameConnecting;
	log_write(LOG_DEBUG4, "client uid : %d, cid : %d, name : %s, state game connecting", uid, character_id, character_name);
}

// on leave game
void Client::OnGameLeave()
{
	if (state == CS_InGame || state == CS_GameConnecting)
	{
		state = CS_InRoom;

		server.proxy_connection.NotifyClientStatusChanged(uid_in_proxy, state);

		Room* room = server.GetRoom(room_id);
		if (room && room->host_id != character_id)
			room->DoClientReady(*this, false);

		//Event::ModifySocket(connected_socket, this, true, true);
		log_write(LOG_DEBUG1, "%s, %s, client uid : %d, cid : %d, name : %s, state game leave", __FILE__, __FUNCTION__, uid, character_id, character_name);
		roomwait_time = USE_ROOM_WAITTIME;
		BeginWrite();
		WriteByte(SM_NotifyGameLeave);
		EndWrite();
	}
}

// notify channel client enter
void Client::NotifyChannelClientEnter(Client & client)
{
	if (character_id != client.character_id && state == CS_InChannel)
	{
		BeginWrite();
		WriteByte(SM_NotifyChannelClientEnter);
		EndWrite();
	}
}

// notify channel client leave
void Client::NotifyChannelClientLeave(Client & client)
{
	if (character_id != client.character_id && state == CS_InChannel)
	{
		BeginWrite();
		WriteByte(SM_NotifyChannelClientLeave);
		EndWrite();
	}
}

// notify room create
void Client::NotifyRoomCreate(Room & room)
{
	if (state == CS_InChannel)
	{
		BeginWrite();
		WriteByte(SM_NotifyRoomCreate);
		WriteRoomInfo(room);
		EndWrite();
	}
}

// notify room close
void Client::NotifyRoomClose(Room & room)
{
	if (state == CS_InChannel)
	{
		BeginWrite();
		WriteByte(SM_NotifyRoomClose);
		WriteInt(room.id);
		EndWrite();
	}
}

// notify room list
void Client::NotifyRoomList()
{
	int count = 0;
	if (state == CS_InChannel)
	{
		BeginWrite();
		WriteByte(SM_NotifyRoomList);
		WriteShort(server.room_count);

		for (Room * room = server.room_pool.Begin() + 1; room < server.room_pool.End(); room++)
		{
			if (room->IsReady())
			{
				WriteRoomInfo(*room);
				
				count++;
			}
		}

		if (count != server.room_count)
			log_write(LOG_ERROR, "client uid : %d, cid : %d, name : %s, notify room list count error\n", uid, character_id, character_name);

		EndWrite();
	}
	log_write(LOG_DEBUG1, "%s, %s, client uid : %d, cid : %d , name : %s, notify room list character state : %d, count : %d", __FILE__, __FUNCTION__, uid, character_id, character_name, state, count);
}

// notify room change option
void Client::NotifyRoomChangeOption(Room & room)
{
	if (state != CS_InChannel && state != CS_InRoom)
		return;

	if (state == CS_InRoom && room.id != room_id)
		return;

	BeginWrite();
	WriteByte(SM_NotifyRoomChangeOption);
	WriteInt(room.id);
	WriteRoomOption(*this, room.option);
	EndWrite();
}

// notify room change option
void Client::NotifyRoomClientCountChanged(Room & room)
{
	if (state != CS_InChannel)
		return;

	BeginWrite();
	WriteByte(SM_NotifyRoomClientCountChanged);
	WriteInt(room.id);
	WriteInt(room.client_count);
	EndWrite();
}

// notify game leave
void Client::NotifyGameLeave()
{
	OnGameLeave();
}

// notify room client enter
void Client::NotifyRoomClientEnter(Client & client)
{
	if (client.character_id == character_id || state != CS_InRoom)
		return;

	BeginWrite();
	WriteByte(SM_NotifyRoomClientEnter);
	WriteClientInfo(client);
	EndWrite();
}

// notify room client leave
void Client::NotifyRoomClientLeave(Client & client)
{
	if (client.character_id == character_id || state != CS_InRoom)
		return;

	BeginWrite();
	WriteByte(SM_NotifyRoomClientLeave);
	WriteInt(client.character_id);
	EndWrite();
}

// notify room client update
void Client::NotifyRoomClientUpdate(Client & client)
{
	if (/* client.character_id == character_id ||  */state != CS_InRoom)
		return;

	BeginWrite();
	WriteByte(SM_NotifyRoomClientUpdate);
	WriteClientInfo(client);
	EndWrite();
}

// notify room host changed
void Client::NotifyRoomHostChanged(Room & room)
{
	if (state != CS_InChannel 
		&& state != CS_InRoom 
		&& state != CS_InGame)
		return;

	if (state == CS_InRoom && room.id != room_id)
		return;
	if(room.host_id == character_id)
		roomwait_time = USE_ROOM_WAITTIME;
	BeginWrite();
	WriteByte(SM_NotifyRoomHostChanged);
	WriteShort(room.id);
	WriteInt(room.host_id);
	WriteByte(room.host_client ? room.host_client->is_vip : 0);
	WriteByte(room.host_client ? room.host_client->net_bar_level : 0);
	WriteInt(room.host_client ? room.host_client->character_level : 0);
	WriteString(room.host_name);
	EndWrite();
}

// notify room state changed
void Client::NotifyRoomStateChanged(Room & room)
{
	if (state != CS_InChannel && state != CS_InRoom)
		return;

	if (state == CS_InRoom && room.id != room_id)
		return;

	BeginWrite();
	WriteByte(SM_NotifyRoomStateChanged);
	WriteShort(room.id);
	WriteByte(room.state);
	EndWrite();
}

// notify client change team
void Client::NotifyClientChangeTeam(Client & client)
{
	if (state != CS_InRoom)
		return;

	BeginWrite();
	WriteByte(SM_NotifyClientChangeTeam);
	WriteInt(client.character_id);
	WriteInt(client.uid_in_room);
	WriteByte(client.team);
	EndWrite();
}

// notify client chagne slot
void Client::NotifyClientChangeSlot(Client & client)
{
	if (state != CS_InRoom)
		return;

	BeginWrite();
	WriteByte(SM_NotifyClientChangeSlot);
	WriteInt(client.character_id);
	WriteByte(client.uid_in_room);
	WriteByte(client.team);
	EndWrite();
}

// notify client chagne slot
void Client::NotifyRoomChangeSlotStatus(RoomSlot & slot)
{
	if (state != CS_InRoom)
		return;

	BeginWrite();
	WriteByte(SM_NotifyRoomChangeSlotStatus);
	WriteByte(slot.id);
	WriteInt(slot.status);
	EndWrite();
}

// notify client ready
void Client::NotifyClientReady(Client & client)
{
	if (state != CS_InRoom)
		return;

	BeginWrite();
	WriteByte(SM_NotifyClientReady);
	WriteInt(client.character_id);
	WriteByte(client.ready);
	EndWrite();
}

// notify client AutoStart
void Client::NotifyClientAutoStart(Client & client)
{
	if (state != CS_InRoom)
		return;
	Room* room = server.GetRoom(client.room_id);
	if (room)
	{
		double time = room->autostart_begin_time;
		double f_temp;
		f_temp = Event::GetTime();
		f_temp -= time;

		BeginWrite();
		WriteByte(SM_NotifyClientAutoStart);
		WriteDouble(10 - f_temp);
		EndWrite();
	}
}
// notify client AutoStartCancel
void Client::NotifyClientAutoStartCancel(Client & client)
{
	if (state != CS_InRoom)
		return;
	BeginWrite();
	WriteByte(SM_NotifyClientAutoStartCancel);
	EndWrite();
}

// notify game client enter
void Client::NotifyGameClientEnter(Client & client)
{
	log_write(LOG_DEBUG4, "character id %d  client.character_id %d state %d ", character_id, client.character_id, state);
	if (client.character_id == character_id || state != CS_InRoom)
		return;

	
	log_write(LOG_DEBUG4, "SM_NotifyGameClientEnter");

	BeginWrite();
	WriteByte(SM_NotifyGameClientEnter);
	WriteInt(client.character_id);
	EndWrite();
}

// notify game client leave
void Client::NotifyGameClientLeave(Client & client)
{
	if (client.character_id == character_id || state != CS_InRoom)
		return;

	BeginWrite();
	WriteByte(SM_NotifyGameClientLeave);
	WriteInt(client.character_id);
	EndWrite();
}


// notify chat
void Client::NotifyChat(const char * to, const char * name, const char * msg)
{
	BeginWrite();
	WriteByte(SM_NotifyChat);
	WriteString(to);
	WriteString(name);
	WriteString(msg);
	EndWrite();
}


// connection check
void Client::ConnectionCheck()
{
	if (connection_check_time < appcfg.connection_checktime_min || 
		connection_check_time > appcfg.connection_checktime_max)
		connection_check_error_count++;
	else
		connection_check_error_count = 0;

	connection_check_time = 0;
}


// on update
void Client::OnUpdate(double frame_time)
{
	if (udp_connection.IsConnected())
	{
		connection_reporttime -= frame_time;
		if (connection_reporttime <= 0)
		{
			connection_reporttime += appcfg.connection_reporttime;
			
			log_write(LOG_INFO, "Ping, %s, %f", inet_ntoa(client_address.sin_addr), udp_connection.delay_time * 1000);
		}
	}
	
	// if (state >= CS_InChannel && state <= CS_InRoom)
	// {
		// refresh_clientlist_time -= frame_time;

		// if (refresh_clientlist_time <= 0)
		// {
			// ResponseClientList(0);
		// }
	// }

	if (state == CS_InGame)
	{
		connection_check_time += frame_time;

		if (connection_check_error_count > appcfg.connection_checkerror_max)
		{
			log_write(LOG_DEBUG1, "connection check : client character_id : %d, client name : %s", character_id, character_name);
			Disconnect();
		}
	}
	
	if (room_novice)
	{
		room_novice->OnUpdate(frame_time);

		if (state < CS_InRoom)
		{
			delete room_novice;
			room_novice = NULL;
		}
	}
	
	if (roomwait_time > 0 && appcfg.idle_kick_open)
	{
		roomwait_time -= frame_time;
		if (roomwait_time <= 0)
		{
			roomwait_time = 0;
			Room* room = server.GetRoom(room_id);
			if (room && room->option.group_match == false && room->option.game_type != RoomOption::kTDMode)
			{
				if (!room->option.m_bIsMatchingRoom)
				{
					room->DoRoomKickClient(uid_in_room);
				}
			}
		}
	}
}

void Client::ResponseGameStart( int dwResult )
{
	BeginWrite();
	WriteByte(SM_ResponseGameStart);
	WriteInt(dwResult);
	EndWrite();
}

void Client::OnRequestEnterRoomWithSlotId()
{
	uint dwRoomId = 0;
	uint dwSlotId = 0;

	ReadInt(dwRoomId);
	ReadInt(dwSlotId);

	Room* pOldRoom = server.GetRoom(room_id);
	if (pOldRoom)
	{
		pOldRoom->DoRoomClientLeave(*this);
		ResponseRoomLeave(pOldRoom);
	}

	Room* pRoom = server.GetRoom(dwRoomId);

	int result = kErrorChannelRoomEnterRoomError;
	if (pRoom)
	{
		result = pRoom->DoRoomClientEnter(*this, NULL, NULL, 0);
		ResponseRoomEnter(result, pRoom);
		if (result == kErrorNone && dwSlotId)
		{
			result = pRoom->DoClientChangeSlot(*this, dwSlotId);
			if (result != kErrorNone)
			{
				result = pRoom->DoClientChangeTeam(*this, (byte)(dwSlotId > max_room_client_count / 2));
			}
			pRoom->DoClientReady(*this, true);
		}
	}

	log_write(LOG_DEBUG1, "%s, %s client uid : %d, cid : %d, name : %s, in_room_id : %d, enter room, result : %d, target room id : %d, room_id : %d, target slog id : %d, result solt id : %d", __FILE__, __FUNCTION__, uid, character_id, character_name, uid_in_room, result, dwRoomId, room_id, dwSlotId, pRoom->GetClientSlot(*this));

	BeginWrite();
	WriteByte(SM_ResponseRoomEnter);
	WriteInt(result);
	if (result == kErrorNone && pRoom)
	{
		// write room info
		WriteRoomInfo(*pRoom);

		for (RoomSlot * slot = pRoom->slots; slot < endof(pRoom->slots); slot++)
		{
			WriteByte(slot->id);
			WriteByte(slot->team);
			WriteInt(slot->status);
			WriteString(slot->preserve_character_name);
		}
	}
	EndWrite();


	if (state == CS_InRoom)
	{
		result = kErrorChannelGameEnter;
		if (pRoom)
			result = pRoom->DoGameClientEnter(*this);

		log_write(LOG_DEBUG1, "%s, %s, RequestGameEnter client uid : %d, cid : %d, name : %s, in_room_id : %d, enter game, result : %d, room_id : %d", __FILE__, __FUNCTION__, uid, character_id, character_name, uid_in_room, result, room_id);

		// on game enter
		if (result != kErrorNone)
		{
			OnGameLeave();
			ResponseGameEnter(result);
		}
	}
}
