#include "pch.h"

// -----------------------------------------------------------------
// class room
// -----------------------------------------------------------------
Room::Room()
	: state(RS_Idle)
	, id(0)
	, host_id(0)
	, host_client(NULL)
	, client_count(0)
	, game_fd(INVALID_SOCKET)
	, rpc_index(0)
	, requesting_level_info(false)
	, autostart_begin_time(-1)
	, m_bWaitForMatchingCallBack(false)
	, m_fWaitForMatchingCallBackTime(0.0f)
	, m_dwMatchingLevel(0)
	, m_bMatchingRequestClient(true)
{
	game.room = this;
}

Room::~Room()
{
}

// is ready
bool Room::IsReady()
{
	return state != RS_Idle;
}

// is waiting
bool Room::IsWaiting()
{
	return state == RS_Waiting;
}

// is playing
bool Room::IsPlaying()
{
	return state == RS_Playing;
}

// on create
void Room::OnCreate()
{
	if (id > 0)
		server.room_count++;
	state = RS_Waiting;
	game_fd = INVALID_SOCKET;
	requesting_level_info = false;
	autostart_begin_time = -1;
	// initialize slot change
	for (uint i = 0; i < elementsof(slots); i ++)
	{
		slots[i].id = i + 1;
		slots[i].team = i < elementsof(slots) / 2 ? 0 : 1;
		slots[i].client = NULL;
		slots[i].status = RoomSlot::kClosed;
		slots[i].preserve_character_name[0] = 0;
	}
	
	kick_clients.clear();
	
	m_dwCreateTime = Event::GetTime();
}

// on close
void Room::OnClose()
{
	if (state != RS_Idle)
	{
		for (RoomSlot * slot = slots; slot < endof(slots); slot++)
		{
			if (slot->client)
			{
				slot->client->OnRoomLeave();
				slot->client = NULL;
			}
		}

		state = RS_Idle;
		memset(name, 0, 64);
		host_id = 0;
		host_client = NULL;
		memset(host_name, 0, 64);
		option.game_type = 0;
		option.can_join_on_playing = true;
		option.use_password = false;
		client_count = 0;
		if (id > 0)
			server.room_count--;

		log_write(LOG_DEBUG1, "room close, room id : %d, room count: %d", id, server.room_count);

		//notify
		server.NotifyRoomClose(*this);

		// disconnect game server
		game.Disconnect();

		if (game_fd)
			close(game_fd);
		
		game_fd = INVALID_SOCKET;
		requesting_level_info = false;
	}

	// ������ô�� ���ð��ǲ���ƥ��ı�־�û�ȥ
	option.m_bIsMatchingRoom = false;
	log_write(LOG_DEBUG1, "%s, %s, room id : %d, is matching room %d", __FILE__, __FUNCTION__, id, option.m_bIsMatchingRoom);
	m_fWaitForMatchingCallBackTime = 0.0f;
	m_dwMatchingLevel = 0;
	
	m_dwCreateTime = 0;
}

// on  update
void Room::OnUpdate(double frame_time)
{
	if (state != RS_Idle)
	{
		for (RoomSlot * slot = slots; slot < endof(slots); slot++)
		{
			if (slot->preserve_character_name[0])
			{
				slot->preserve_time -= frame_time;
				if (slot->preserve_time <= 0)
				{
					DoCancelPreserveSlot(slot->id);
				}
			}
		}
	}
	
	if (state == RS_Playing)
	{
		double time = Event::GetTime();

		if (time - ping_time > 1)
		{
			game.UpdateCharacterPing();
			ping_time = time;
		}
	}
	
	std::list<uint> erase_clients;
	for (std::map<uint, float>::iterator itr = kick_clients.begin(); 
		itr != kick_clients.end(); itr++)
	{
		itr->second -= frame_time;
		if (itr->second <= 0.f)
		{
			erase_clients.push_back(itr->first);
		}
	}
	for (std::list<uint>::iterator itr = erase_clients.begin(); 
		itr != erase_clients.end(); itr++)
	{
		kick_clients.erase(*itr);
	}

	if (m_bMatchingRequestClient)
	{
		CheckMatchingCallBack(frame_time);
	}
}

// do create
int Room::DoRoomCreate(Client & client, RoomOption & op)
{
	if (op.client_count > 16 || op.client_count < 2)
		return kErrorChannelRoomCreateClientCount;

	int error_code = kErrorChannelRoomCreate;

	if (state == RS_Idle)
	{
		OnCreate();

		error_code = DoChangeOption(client, op, true);
		if (error_code == kErrorNone)
		{
			// TODO: initialize room slots
			for (int i = 0; i < op.client_count; i++)
			{
				int slot_id = (i / 2) + 8 * (i % 2);
				slots[slot_id].status = RoomSlot::kOpen;
			}
			
			error_code = DoRoomClientEnter(client, op.password, NULL, 0);
			log_write(LOG_DEBUG1, "host enter room ret : %d", error_code);

			if (error_code == kErrorNone)
			{
				DoChangeHost(client);

				// notify
				server.NotifyRoomCreate(*this);

				return kErrorNone;
			}
		}

		OnClose();
	}
	return error_code;
}

// do join in
int Room::DoRoomClientEnter(Client & leader, const char * password, const char ** member_array, int member_array_size)
{
	if (!IsReady())
		return kErrorChannelRoomEnter;

	if (IsPlaying() && !option.can_join_on_playing)
		return kErrorChannelRoomEnterJoinOnPlaying;

	if (member_array_size > 4)
		return kErrorChannelRoomEnterMemberSize;
		
	if (kick_clients.find(leader.character_id) != kick_clients.end())
		return kErrorChannelRoomEnterKickedClient;

	const char * members[4] = {0};
	uint member_count = 0;

	// find members needed to be preserved.
	for (int i = 0; i < member_array_size; i ++)
	{
		if (member_array[i] && member_array[i][0])
		{
			bool member_preserved = false;
			for (RoomSlot * slot = slots; slot < endof(slots); slot++)
			{
				if (strcmp(slot->preserve_character_name, member_array[i]) == 0)
				{
					member_preserved = true;
					break;
				}
			}

			if (!member_preserved)
			{
				members[member_count++] = member_array[i];
			}
		}
	}

	uint position = 0;

	// check if is preserved
	for (RoomSlot * slot = slots; slot < endof(slots); slot++)
	{
		if (strcmp(slot->preserve_character_name, leader.character_name) == 0)
		{
			position = slot - slots + 1;
			break;
		}
	}

	// check password
	if (position == 0)
	{
		// check if room has enouth empty slots.
		if (client_count + 1 + member_count > option.client_count)
			return kErrorChannelRoomEnterEnoughSlots;

		if (!DoVerifyPassword(password))
			return kErrorChannelRoomEnterPassword;
	}
	else
	{
		// check if room has enouth empty slots.
		if (client_count + member_count > option.client_count)
			return kErrorChannelRoomEnterEnoughSlots;
	}
	
	// find slots
	int team_slot_count[2] = {0, 0};
	int team_slot_array[2][8];

	// find empty slots
	for (RoomSlot * slot = slots; slot < endof(slots); slot++)
	{
		if (slot->status > RoomSlot::kClosed)
		{
			// if this slot is empty or is preserved for leader.
			if (slot->IsEmpty() || slot->id == position)
			{
				int & slot_count = team_slot_count[slot->team];
				int * slot_array = team_slot_array[slot->team];

				// move leader preserve slot to first.
				if (slot->id == position && slot_count != 0)
				{
					slot_array[slot_count] = slot_array[0];
					slot_array[0] = slot->id;
				}
				else
				{
					slot_array[slot_count] = slot->id;
				}

				slot_count++;
			}
		}
	}

	// team id
	int team_id = 0;

	if (position)
	{
		team_id = GetSlot(position)->team;
	}
	else
	{
		if (team_slot_count[1] == team_slot_count[0])
		{
			if (team_slot_count[1] + team_slot_count[0] == option.client_count)
				team_id = 0;
			else
				team_id = rand() % 2;
				
			log_write(LOG_DEBUG5, "team_slot_count[0] : %d, team_slot_count[1] : %d, team_id : %d", 
					team_slot_count[0], team_slot_count[1], team_id);
		}
		else if (team_slot_count[1] > team_slot_count[0])
		{
			team_id = 1;
		}
		else
		{
			team_id = 0;
		}

		//team_id = team_slot_count[1] > team_slot_count[0] ? 1 : 0;

		// the first slot is for leader.
		position = team_slot_array[team_id][0];
	}

	// not enough slot.
	if (team_slot_count[team_id] < member_count + 1)
	{
		return kErrorChannelRoomEnterEnoughSlots;
	}

	for (int i = 0; i < member_count; i ++)
	{
		DoPreserveSlot(team_slot_array[team_id][i + 1], members[i], leader.character_name);
	}

	// notify leader enter room
	RoomSlot * slot = GetSlot(position);
	if (!slot)
	{
		return kErrorChannelRoomEnterEnoughSlots;
	}

	slot->preserve_character_name[0] = 0;
	slot->client = &leader;

	leader.uid_in_room = position;
	leader.room_id = id;
	leader.team = slot->team;
	leader.OnRoomEnter();

	Notify(&Client::NotifyRoomClientEnter, leader);

	// update client count
	UpdateClientCount();
	return kErrorNone;
}

// room leave
void Room::DoRoomClientLeave(Client & client)
{
	log_write(LOG_DEBUG1, "%s, %s Room Client Leave client name : %s", __FILE__, __FUNCTION__, client.character_name);
	
	Client *tmp_client = GetClient(client.uid_in_room);
	if (tmp_client && tmp_client == &client)
	{
		// notify game client leave
		if (client.state > Client::CS_InRoom)
		{
			if (game.IsConnected())
			{
				game.RequestClientLeave(client);
			}
		}

		// reset client status
		slots[client.uid_in_room - 1].client = NULL;
		client.room_id = 0;
		client.uid_in_room = 0;
		client.ready = false;
		client.OnRoomLeave();

		// change host to another client.
		if (client.character_id == host_id)
		{
			for (RoomSlot * slot = slots; slot < endof(slots); slot++)
			{
				if (slot->client)
				{
					DoChangeHost(*slot->client);
					break;
				}
			}
		}

		// update client count
		UpdateClientCount();

		// notify
		server.NotifyRoomClientCountChanged(*this);
		Notify(&Client::NotifyRoomClientLeave, client);
		
		if (client_count == 0)
			OnClose();
	}
	else
	{
		log_write(LOG_ERROR, "Room Client Leave error, %s, %d", client.character_name, client.uid_in_room);
	}
}

static int CheckOption(Client & client, const RoomOption & op)
{
	if (op.client_count > max_room_client_count 
		|| op.viewer_count > max_room_client_count)
		return kErrorChannelOptionRoomError;
		
	if (op.use_password == true && strlen(op.password) == 0)
		return kErrorChannelOptionRoomError;

	if (op.game_type != RoomOption::kTDMode)
	{
		if (op.level_id == 0)
			return kErrorChannelOptionLevelIdInvalid;

		if (op.rule_value < 0)
			return kErrorChannelOptionRuleValue;
	}
	
	if (op.round_rebirth_time_max < 0)
		return kErrorChannelOptionSpawnTime;

	if (op.special_mode >= RoomOption::kSpecialModeCount)
		return kErrorChannelOptionSpecialMode;

	if (op.dead_view_mode >= RoomOption::kViewModeCount)
		return kErrorChannelOptionDeadViewMode;

	char cType = op.game_type;
	switch (cType)
	{
	case RoomOption::kRandom:
		{
			if (server.cServerType != SvrType_Match && server.cServerType != SvrType_MatchFighting)
			{
				log_write(LOG_DEBUG1, "%s, %s, error server type : %d", __FILE__, __FUNCTION__, server.cServerType);
				return kErrorChannelOptionGameType;
			}
		}
		break;
	case RoomOption::kTeam:
	case RoomOption::kItemMode:
		{
			if (op.rule_value > 150 )
				return kErrorChannelOptionRuleValue;
		}
		break;
	case RoomOption::kKnifeMode:
		{
			if(op.rule_value > 150)
				return kErrorChannelOptionRuleValue;
		}
		break;
	case RoomOption::kBombMode:
		{
			if(op.rule_value > 20)
				return kErrorChannelOptionRuleValue;
		}
		break;
	case RoomOption::kHoldPoint:
		{
			if (op.rule_value > 20)
				return kErrorChannelOptionRuleValue;
		}
		break;

	case RoomOption::kPushVehicle:
		{
			if (op.rule_value > 20)
				return kErrorChannelOptionRuleValue;
		}
		break;
	case RoomOption::kNovice:
		{
			if (op.rule_value > 99)
				return kErrorChannelOptionRuleValue;
		}
		break;
	case RoomOption::kTeamDeathMatch:
		{
			if(op.rule_value > 20)
				return kErrorChannelOptionRuleValue;
		}
		break;

	case RoomOption::kBossMode:
		{
			if(op.rule_value > 20)
				return kErrorChannelOptionRuleValue;
		}
		break;
	case RoomOption::kStreetBoyMode:
		{
			if(op.rule_value > 20)
				return kErrorChannelOptionRuleValue;
		}
		break;
	case RoomOption::kZombieMode:
		{
			if(op.rule_value > 20)
				return kErrorChannelOptionRuleValue;
		}
		break;
	case RoomOption::kBossPVE:
		{
			if(op.rule_value > 20)
				return kErrorChannelOptionRuleValue;
		}
		break;
	case RoomOption::kCommonZombieMode:
		{
			if (op.rule_value > 20)
				return kErrorChannelOptionRuleValue;	
		}
		break;
	case RoomOption::kBossMode2:
		{
		}
		break;
	case RoomOption::kEditMode:
	case RoomOption::kTDMode:
	case RoomOption::KMoonMode:
	case RoomOption::kAdvenceMode:
	case RoomOption::kSurvivalMode:
		{
		}
		break;
	default:
		{
			log_write(LOG_DEBUG1, "%s, %s, error game type : %d", __FILE__, __FUNCTION__, op.game_type);
			return kErrorChannelOptionGameType;
		}
	}

	if (op.group_match && client.character_group[0] == 0)
		return kErrorChannelOptionNoGroup;
	
	return kErrorNone;
}

// change option
int Room::DoChangeOption(Client & client, RoomOption & op, bool is_create)
{
	int error_code = kErrorChannelOption;

	if (IsWaiting())
	{
		error_code = CheckOption(client, op);
		if (error_code == kErrorNone)
		{
			if (is_create == false)
			{
				int client_count_diff = op.client_count - option.client_count;
				if (client_count_diff < 0)
					return kErrorChannelOptionRoomError;
				
				for (RoomSlot * slot = slots; slot < endof(slots) && client_count_diff > 0; slot++)
				{
					if (slot->status == RoomSlot::kClosed)
					{
						DoChangeSlotStatus(client, slot->id, RoomSlot::kOpen);
						client_count_diff--;
					}
				}
			}
			
			// if (op.group_match)
				// op.can_join_on_playing = false;
			
			option = op;
			option.m_bIsMatchingRoom = op.m_bIsMatchingRoom;

			// filter room name keywords
			DictMatch::Replace(option.name);

			// notify
			Notify(&Client::NotifyRoomChangeOption, *this);
			server.NotifyRoomOptionChanged(*this);
			return kErrorNone;
		}
	}

	return error_code;
}

// change td option
int Room::DoChangeTDOption(Client & client, int level_id, int map_level, int res_value, const char * rand_key)
{
	//int error_code = kErrorChannelOption;
	
	//if (IsWaiting())
	//{
		option.level_id = level_id;
		option.map_level = map_level;
		option.rule_value = res_value;
		strcpy(option.rand_key, rand_key);
		
		log_write(LOG_DEBUG1, "DoChangeTDOption %d %d %d %s", option.level_id, level_id, map_level, rand_key);
		
		// notify
		Notify(&Client::NotifyRoomChangeOption, *this);
		server.NotifyRoomOptionChanged(*this);
		
		return kErrorNone;
	//}
	
	//log_write(LOG_DEBUG1, "DoChangeTDOption failed %d %d %d %s", option.level_id, level_id, map_level, rand_key);
	
	//return error_code;
}

// change host
void Room::DoChangeHost(Client & client)
{
	// clear room item
	if (host_id != 0 && option.item_id > 0)
	{
		option.item_id = 0;
		memset(option.item_resource, 0, sizeof(option.item_resource));
		memset(option.item_name, 0, sizeof(option.item_name));
		Notify(&Client::NotifyRoomChangeOption, *this);
		server.NotifyRoomOptionChanged(*this);
	}

	host_id = client.character_id;
	strcpy(host_name, client.character_name);
	host_group_id = client.character_group_id;
	strcpy(host_group, client.character_group);
	host_client = &client;
	client.ready = true;

	Notify(&Client::NotifyRoomHostChanged, *this);
	server.NotifyRoomHostChanged(*this);
}

bool Room::ChangeSlotCheckBalance(Client & client, const RoomSlot * old_slot, const RoomSlot * new_slot)
{
	if (option.check_game_balance)
	{
		if (old_slot && new_slot && old_slot != new_slot && client.ready)
		{
			if (old_slot->team == new_slot->team && old_slot->status == new_slot->status)
				return true;

			int team_clients[] = {0, 0};
			for (RoomSlot * slot = slots; slot < endof(slots); slot++)
			{
				if (slot->status == RoomSlot::kOpen)
				{
					if (slot->client && slot->client->ready)
						team_clients[slot->team]++;
				}
			}

			if (old_slot->status == RoomSlot::kOpen)
				team_clients[old_slot->team]--;

			if (new_slot->status == RoomSlot::kOpen)
				team_clients[new_slot->team]++;

			switch (option.game_type)
			{
			default:
				{
					if (team_clients[0] == 0)
						return false;

					if (team_clients[1] == 0)
						return false;

					int team_clients_diff = team_clients[0] - team_clients[1];
					if (abs(team_clients_diff) > 2)
					{
						if ((team_clients_diff > 0 && new_slot->team == 0) || 
							(team_clients_diff < 0 && new_slot->team == 1))
						{
							return false;
						}
					}
				}
				break;
			}
		}
	}

	return true;
}

// client change team
int Room::DoClientChangeTeam(Client & client, byte team)
{
	if (team >= 2)
		return kErrorChannelRoomChangeTeamTeamError;

	if (client.team == team)
		return kErrorChannelRoomChangeTeamSameTeam;

	if (client.uid_in_room == 0 || client.uid_in_room > max_room_client_count)
		return kErrorChannelRoomChangeTeamIdError;

	if (host_id != client.character_id && client.ready)
		return kErrorChannelRoomChangeTeamIsReady;

	if (IsReady())
	{
		int index = 0;
		for (RoomSlot * slot = slots; slot < endof(slots); slot ++)
		{
			if (slot->team == team && slot->IsEmpty() && slot->status != RoomSlot::kClosed)
			{
				bool balance_check = true;
				//if (client.ready)
				//{
				//	RoomSlot * oldslot = GetSlot(client.uid_in_room);
				//	balance_check = ChangeSlotCheckBalance(client, oldslot, slot);
				//}

				if (balance_check)
				{
					// remove client from old slot
					slots[client.uid_in_room - 1].client = NULL;

					// move client to new slot
					slot->client = &client;
					client.uid_in_room = slot->id;
					client.team = team;

					// notify
					Notify(&Client::NotifyClientChangeTeam, client);
					return kErrorNone;
				}
				else 
					return kErrorChannelRoomChangeTeamBalance;
			}
		}
	}
	return kErrorChannelRoomChangeTeam;
}

// client change slot
int Room::DoClientChangeSlot(Client & client, byte slot_id)
{
	RoomSlot * oldslot = GetSlot(client.uid_in_room);
	RoomSlot * slot = GetSlot(slot_id);

	if (!slot || !oldslot)
		return kErrorChannelChangeSlotNotExist;

	if (slot == oldslot)
		return kErrorChannelChangeSlotEqual;

	if (slot->client != NULL)
		return kErrorChannelChangeSlotHasClient;

	if (slot->preserve_character_name[0])
		if (strcmp(slot->preserve_character_name, client.character_name) != 0)
			return kErrorChannelChangeSlotPreserveForOther;

	if (slot->status == RoomSlot::kClosed)
		return kErrorChannelChangeSlotClose;

	if (host_id != client.character_id && client.ready)
		return kErrorChannelChangeSlotReady;

	if (IsReady())
	{
		bool balance_check = true;
		//if (client.ready)
		//	balance_check = ChangeSlotCheckBalance(client, oldslot, slot);

		if (balance_check)
		{
			// remove client from old slot
			oldslot->client = NULL;

			// move client to new slot
			slot->client = &client;
			client.uid_in_room = slot->id;
			client.team = slot->team;

			// notify
			Notify(&Client::NotifyClientChangeSlot, client);
			return kErrorNone;
		}
		else
			return kErrorChannelChangeSlotBalance;
	}

	return kErrorChannelChangeSlot;
}

// change slot status
int Room::DoChangeSlotStatus(Client & client, byte slot_id, RoomSlot::Status status)
{
	if (!IsReady())
		return kErrorChannelChangeSlotStatus;

	if (host_id != client.character_id)
		return kErrorChannelChangeSlotStatusNotHost;

	RoomSlot * slot = GetSlot(slot_id);

	if (!slot)
		return kErrorChannelChangeSlotStatusNotExist;

	if (slot->client && slot->client->state != Client::CS_InRoom)
		return kErrorChannelChangeSlotStatusStateError;

	if (slot->status == status)
		return kErrorChannelChangeSlotStatusEqual;

	if (status == RoomSlot::kClosed)
	{
		// kick client
		if (slot->client)
		{
			Notify(&Client::NotifyRoomKickClient, *slot->client);
			slot->client->RequestRoomLeave();
		}

		// kick a preserved character
		else if (slot->preserve_character_name[0])
		{
			// cancel preserve for character
			DoCancelPreserveSlot(slot_id);
		}
	}

	// update status
	slot->status = status;
	Notify(&Client::NotifyRoomChangeSlotStatus, *slot);

	// update client count
	UpdateClientCount();

	return kErrorNone;
}

// do preserve slot
int Room::DoPreserveSlot(byte slot_id, const char * character_name, const char * invite_name)
{
	RoomSlot * slot = GetSlot(slot_id);

	if (!IsReady())
		return kErrorChannelPreserveSlot;

	if (slot == NULL || slot->client != NULL || slot->status == RoomSlot::kClosed)
		return kErrorChannelPreserveSlotCannot;

	// check duplicated preserve
	for (RoomSlot * s = slots; s < endof(slots); s++)
	{
		if (strcmp(s->preserve_character_name, character_name) == 0)
			return kErrorChannelPreserveSlotAlreadyPreserved;

		if (s->client && strcmp(s->client->character_name, character_name) == 0)
			return kErrorChannelPreserveSlotAlreadyInRoom;
	}

	// cancel preserve
	if (slot->preserve_character_name[0])
	{
		// notify room preserve for character
		server.proxy_connection.NotifyRoomCancelPreserveSlot(id, slot_id, slot->preserve_character_name);
	}

	// preserve for character
	strncpy(slot->preserve_character_name, character_name, sizeof(slot->preserve_character_name));

	// notify slot status change
	Notify(&Client::NotifyRoomChangeSlotStatus, *slot);

	// notify room preserve for character
	server.proxy_connection.NotifyRoomPreserveSlot(id, slot_id, character_name, invite_name);

	slot->preserve_time = 10;

	return kErrorNone;
}

// do cancel preserve
byte Room::DoCancelPreserveSlot(byte slot_id)
{
	RoomSlot * slot = GetSlot(slot_id);

	if (slot)
	{
		if (slot->preserve_character_name[0])
		{
			// notify room preserve for character
			server.proxy_connection.NotifyRoomCancelPreserveSlot(id, slot_id, slot->preserve_character_name);

			// preserve for character
			slot->preserve_character_name[0] = 0;

			// notify slot status change
			Notify(&Client::NotifyRoomChangeSlotStatus, *slot);

			slot->preserve_time = 0;

			return 0;
		}

		return 1;
	}

	return 2;
}

// client ready
int Room::DoClientReady(Client & client, bool ready)
{
	if (client.ready == ready)
		return kErrorChannelClientReadyAlready;

	if (client.team >= 2)
		return kErrorChannelClientReadyTeam;

	if (host_id == client.character_id && !ready)
		return kErrorChannelClientReadyHost;

	if (IsReady())
	{
		if (ready && option.check_game_balance && !option.group_match)
		{
			int team_clients[] = {0, 0};
			for (RoomSlot * slot = slots; slot < endof(slots); slot++)
			{
				if (slot->status == RoomSlot::kOpen)
				{
					if (slot->client && slot->client->ready)
						team_clients[slot->team]++;

					if (slot->client == &client)
						team_clients[slot->team]++;
				}
			}

			switch (option.game_type)
			{
		
			default:
				{
					int team_clients_diff = team_clients[0] - team_clients[1];
					if (abs(team_clients_diff) > 2)
					{
						if ((team_clients_diff > 0 && client.team == 0) || 
							(team_clients_diff < 0 && client.team == 1))
						{
							return kErrorChannelClientReadyBalance;
						}
					}
				}
				break;
			}
		}
		
		client.ready = ready;
		// notify
		Notify(&Client::NotifyClientReady, client);
		if (option.auto_start && ready)
		{
			int Count_Ready = 0;
			for (RoomSlot * slot = slots; slot < endof(slots); slot++)
			{
				if (slot->status == RoomSlot::kOpen)
				{
					if (slot->client && slot->client->ready)
						Count_Ready++;
				}
			}
			if (Count_Ready > 6 )
			{
				if (autostart_begin_time < 0)
					autostart_begin_time = Event::GetTime();
				Notify(&Client::NotifyClientAutoStart, client);
			}
		}
		return kErrorNone;
	}
	return kErrorChannelClientReady;
}

// verify password
bool Room::DoVerifyPassword(const char* ps)
{
	return !option.use_password || strcmp(option.password, ps) == 0;
}

// check game start
int Room::CheckGameStart()
{
	int td_att_count = 0;
	
	int ready_count[] = {0, 0};
	for (RoomSlot * slot = slots; slot < endof(slots); slot++)
	{
		if (slot->status == RoomSlot::kOpen && slot->client && slot->client->ready)
			ready_count[slot->team]++;
		
		if (option.game_type == RoomOption::kTDMode)
		{
			if (slot->status == RoomSlot::kOpen && slot->client && slot->client->ready && 
				slot->client->character_group_id == host_group_id)
				td_att_count++;
		}
	}

	if (option.group_match == 1 && server.novice != 2)
		return kErrorChannelAdvServerError;

	
	if (option.game_type == RoomOption::kTDMode && td_att_count > 6)
		return kErrorChannelGameStartBalance;

	// check balance
	if (ready_count[0] == 0 && ready_count[1] == 0)
	{
		return kErrorChannelGameStartNoOne;
	}

	if (option.check_game_balance && !option.group_match)
	{
		switch (option.game_type)
		{
		default:
			{
				if (ready_count[0] == 0)
					return kErrorChannelGameStartBalance;

				if (ready_count[1] == 0)
					return kErrorChannelGameStartBalance;

				if (abs(ready_count[0] - ready_count[1]) > 2)
					return kErrorChannelGameStartBalance;
			}
			break;
		}
	}
	
	switch (option.game_type)
	{
	case RoomOption::kTeamDeathMatch:
		if ((ready_count[0] == 0 || ready_count[1] == 0) && !option.group_match && !option.m_bIsMatchingRoom && server.cServerType != SvrType_HageBattle)
		{
			return kErrorChannelDeathMatchPlayerNumberError;
		}
		break;
	case RoomOption::kBossMode:
		if(ready_count[0] + ready_count[1] < appcfg.boss_mode_player_count && !option.m_bIsMatchingRoom)
		{
			return kErrorChannelBossPlayerNumberError;
		}
		break;
	case RoomOption::kBossMode2:
		if(ready_count[0] + ready_count[1] < appcfg.boss_mode2_player_count && !option.m_bIsMatchingRoom)
		{
			return kErrorChannelBoss2PlayerNumberError;
		}
		break;
	case RoomOption::kBossPVE:
		if(ready_count[0] + ready_count[1] < appcfg.bosspve_mode_player_count && !option.m_bIsMatchingRoom)
		{
			return kErrorChannelBossPlayerNumberError;
		}
		break;
	case RoomOption::kZombieMode:
		if(ready_count[0] + ready_count[1] < appcfg.zombie_mode_player_count && !option.m_bIsMatchingRoom)
		{
			return kErrorChannelZombiePlayerNumberError;
		}
		break;
	case RoomOption::kStreetBoyMode:
		if((ready_count[0] < appcfg.street_boy_each_side_player_count || ready_count[1] < appcfg.street_boy_each_side_player_count && !option.m_bIsMatchingRoom) 
			&& !option.group_match)
		{
			return kErrorChannelStreetBoyPlayerNumberError;
		}
		break;
	case RoomOption::kBombMode:
		if((ready_count[0] < 1 || ready_count[1] < 1) && !option.group_match && !option.m_bIsMatchingRoom)
		{
			return kErrorChannelBombPlayerNumberError;
		}
	case RoomOption::kCommonZombieMode:
		if (ready_count[0] + ready_count[1] < 2 && !option.m_bIsMatchingRoom)
		{
			return kErrorChannelCommonZombiePlayerNumberError;
		}
		break;
	case RoomOption::kAdvenceMode:
		{
			if (server.novice != 4 && server.cServerType != SvrType_HageBattle)
			{
				return kErrorChannelAdvServerError;
			}
			if (ready_count[0] + ready_count[1] != 10 && server.cServerType != SvrType_HageBattle)
			{
				return kErrorChannelAdvPlayerNumberError;
			}
		}
		break;
	case  RoomOption::kSurvivalMode:
		{
			//if (server.novice != 4)
			//{
			//	return kErrorChannelAdvServerError;
			//}
			if (ready_count[0] + ready_count[1] < 4 && !option.m_bIsMatchingRoom)
			{
				return kErrorChannelZombiePlayerNumberError;
			}
		}
		break;
	}

	// check group match
	// if (option.group_match)
	// {
		// char group_name[2][group_name_length] = { 0 };
		// int group_member_num[2] = { 0 };

		// for (RoomSlot * slot = slots; slot < endof(slots); slot++)
		// {
			// if (slot->status == RoomSlot::kOpen && slot->client && slot->client->ready)
			// {
				// if (slot->client->character_group[0] == 0)
					// return kErrorChannelGameStartGroupMatchNoGroup;
				
				// bool find_match = false;
				// for (int i = 0; i < 2; i++)
				// {
					// if (group_name[i][0] == 0)
					// {
						// strcpy(group_name[i], slot->client->character_group);
						
						// slot->client->team = i;
						// group_member_num[i]++;
						// find_match = true;
					// }
					// else if (strcmp(slot->client->character_group, group_name[i]) == 0)
					// {
						// slot->client->team = i;
						// group_member_num[i]++;
						// find_match = true;
					// }
				// }
				
				// if (!find_match)
					// return kErrorChannelGameStartGroupMatchGroupError;
			// }
		// }
		
		// if (group_member_num[0] != group_member_num[1])
			// return kErrorChannelGameStartGroupMatchGroupError;

		// if (strcmp(group_name[0], group_name[1]) == 0)
			// return kErrorChannelGameStartGroupMatchGroupEqual;
	// }

	return kErrorNone;
}

// 
void Room::DoTDGamePrepare()
{
	if (option.game_type == RoomOption::kTDMode)
	{
		int supposed_open_slots = 0;
		
		for (RoomSlot * slot = slots; slot < endof(slots); slot++)
		{
			if (slot->status != RoomSlot::kOpen)
				DoChangeSlotStatus(*host_client, slot->id, RoomSlot::kOpen);
				
			if (slot->client)
				supposed_open_slots++;
		}
		
		switch (option.map_level)
		{
			// case 1:
				// supposed_open_slots += 4;
				// break;
			// case 2:
				// supposed_open_slots += 5;
				// break;
			// case 3:
				// supposed_open_slots += 6;
				// break;
			
			default:
				supposed_open_slots += 4;
				//log_write(LOG_WARNING, "TDMode : unknow map_level, room id : %d, map_level : %d", id, option.map_level);
				break;
		}
		
		int need_close_slots = 16 - supposed_open_slots;
		
		if (need_close_slots > 0)
		{
			for (RoomSlot * slot = slots; slot < endof(slots); slot++)
			{
				if (slot->status == RoomSlot::kOpen && !slot->client)
				{
					DoChangeSlotStatus(*host_client, slot->id, RoomSlot::kClosed);
					
					need_close_slots--;
					if (need_close_slots <= 0)
						break;
				}
			}
		}
		
		if (need_close_slots != 0)
			log_write(LOG_WARNING, "TDMode : need_close_slots : %d", need_close_slots);
	}
}

// game start
int Room::DoGameStart()
{
	int error_code = kErrorChannelGameStart;

	if (IsWaiting())
	{
		if (server.cServerType == (byte)SvrType_Match || server.cServerType == (byte)SvrType_MatchFighting)
		{
			if (!option.m_bIsMatchingRoom)
			{
				return error_code;
			}
		}
		if (option.game_type == RoomOption::kTDMode)
			DoTDGamePrepare();
		
		error_code = CheckGameStart();

		// check game start
		if (error_code != kErrorNone)
		{
			return error_code;
		}

		int fd[2];
		if (socketpair(AF_LOCAL, SOCK_STREAM, 0, fd))
		{
			log_write(LOG_ERROR, "room id : %d, game start socketpair error", id);
			return kErrorChannelGameStartGameServerError;
		}

		// start game server
		char server_id[8];
		char channel_id[8];
		char room_id[8];
		char sock[8];
		sprintf(server_id, "%d", server.server_id);
		sprintf(channel_id, "%d", server.channel_id);
		sprintf(room_id, "%d", id);
		sprintf(sock, "%d", fd[1]);
		
		char cmd_debug[] = "--debug";
		char cmd_log[] = "--log";
		char cmd_cfg[] = "--config";
		
		char debug_level[8];
		sprintf(debug_level, "%d", log_get_output_level());
		
		char *arg[] = { server.game_server_path, cmd_debug, debug_level, cmd_log, (char*)log_get_output(), 
						cmd_cfg, (char*)appcfg.GetCfgFileName(), server_id, channel_id, room_id, sock, 0 };

		// igorn signal
		signal(SIGCLD, SIG_IGN);
		signal(SIGPIPE, SIG_IGN);
		fcntl(fd[1], F_SETFD, 0);

		game_fd = fd[0];

		// game start
		state = RS_Playing;
		ping_time = 0;

		int pid = vfork();

		if (pid == 0)
		{
			execv(arg[0], arg);
			
			log_write(LOG_ERROR, "room id : %d, game start exec error!", id);
			close(fd[1]);
			_exit(0);
        }
		else
		{
			// request level info
			rpc_index++;
			requesting_level_info = true;
			server.proxy_connection.RequestLevelInfo(*this);
			close(fd[1]);
			log_write(LOG_DEBUG3, "room id : %d, create game process pid=%d", id, pid);
		}
		autostart_begin_time = -1;
		return kErrorNone;
	}
	else
	{
		log_write(LOG_DEBUG1, "%s, %s, room id : %d, state : %d", __FILE__, __FUNCTION__, id, state);
	}

	return error_code;
}

// game end
bool Room::DoGameEnd()
{
	if (IsPlaying())
	{
		for (RoomSlot * slot = slots; slot < endof(slots); slot++)
		{
			if (slot->client && (slot->client->state == Client::CS_InGame || slot->client->state == Client::CS_GameConnecting))
			{
				DoGameClientLeave(*slot->client);
			}
		}

		if (IsReady())
			state = RS_Waiting;

		Notify(&Client::NotifyGameEnd);
		Notify(&Client::NotifyRoomStateChanged, *this);
		server.NotifyRoomStateChanged(*this);

		// clear room item
		if (option.item_id > 0)
		{
			option.item_id = 0;
			memset(option.item_resource, 0, sizeof(option.item_resource));
			memset(option.item_name, 0, sizeof(option.item_name));
			Notify(&Client::NotifyRoomChangeOption, *this);
			server.NotifyRoomOptionChanged(*this);
		}

		game_fd = INVALID_SOCKET;
		requesting_level_info = false;
	}
	return true;
}

// game enter
int Room::DoGameClientEnter(Client & client, bool game_start)
{
	if (!IsPlaying())
	{
		return kErrorChannelGameEnter;
	}

	if (requesting_level_info)
	{
		return kErrorChannelGameEnter;
	}

	if (!game_start)
	{
		if (!option.can_join_on_playing)
			return kErrorChannelGameEnterJoinOnPlaying;

		switch (game.level_info.room_item.type)
		{
		case kRoomItemGroupPK:
		case kRoomItemGroupChallenge:
		case kRoomItemPersonalPK:
		case kRoomItemPersonalChallenge:
			return kErrorChannelGameEnterJoinPK;
		}
	}

	if (client.room_id != id)
		return kErrorChannelGameEnterRoomIdError;

	// check game balance
	if (option.check_game_balance)
	{
		if (game_start == false)
		{
			RoomSlot* client_slot = GetSlot(GetClientSlot(client));

			if (client_slot && client_slot->status == RoomSlot::kOpen)
			{
				switch (option.game_type)
				{
				default:
					{	
						int game_client_count[] = {0, 0};
						for (RoomSlot * slot = slots; slot < endof(slots); slot++)
						{
							if (slot->status == RoomSlot::kOpen 
								&& slot->client 
								&& slot->client->state > Client::CS_InRoom)
								game_client_count[slot->team]++;
						}

						game_client_count[client_slot->team]++;

						if (game_client_count[client_slot->team] - game_client_count[(client_slot->team + 1) % 2] > 2)
							return kErrorChannelGameStartBalance;
					}
					break;
				}
			}
		}
	}

	RequestCharacterInfo(client, option.level_id, option.character_id, option.is_knife);
	return kErrorNone;
}

// do room kick client
int Room::DoRoomKickClient(Client & client, byte id_in_room)
{
	if (!IsReady())
		return kErrorChannelKickClient;
	if (client.character_id != host_id)
		return kErrorChannelKickClientNotHost;
	if (client.uid_in_room == id_in_room)
		return kErrorChannelKickClientIdError;
	if (id_in_room >= max_room_client_count)
		return kErrorChannelKickClientIdError;
	RoomSlot * slot = GetSlot(id_in_room);
	
	if (slot)
	{
		if (slot->client && slot->client->state == Client::CS_InRoom)
		{
			Notify(&Client::NotifyRoomKickClient, *slot->client);
			slot->client->RequestRoomLeave();
			return kErrorNone;
		}

		if (slot->preserve_character_name[0])
		{

			if (0 == DoCancelPreserveSlot(id_in_room))
			{
				UpdateClientCount();
			}
			return kErrorNone;
		}
	}

	return kErrorChannelKickClient;
}
// do room kick client
int Room::DoRoomKickClient(byte id_in_room)
{
	if (!IsReady())
		return kErrorChannelKickClient;
	
	if (id_in_room >= max_room_client_count)
		return kErrorChannelKickClientIdError;
	RoomSlot * slot = GetSlot(id_in_room);

	if (slot)
	{
		if (slot->client && slot->client->state == Client::CS_InRoom)
		{
			Notify(&Client::NotifyRoomKickClient, *slot->client);
			slot->client->RequestRoomLeave();
			return kErrorNone;
		}
	}

	return kErrorChannelKickClient;
}
// request chararcter info
void Room::RequestCharacterInfo(Client & client, int level_id, byte character_id, byte is_knife)
{
	server.proxy_connection.RequestCharacterInfo(client, level_id, character_id, is_knife);
}

// response character info
void Room::ResponseCharacterInfo(Client & client, void * data, int size)
{
	RoomSlot* slot = GetSlot(GetClientSlot(client));
	if (IsPlaying() && requesting_level_info == false && slot)
	{
		// on game enter before
		client.OnGameEnterBefore();
		game.RequestClientEnter(client, data, size, slot->status == RoomSlot::kView ? true : false);
		game.UpdateCharacterData(client);
	}
	else
	{
		client.ResponseGameEnter(kErrorChannelGameEnter);
	}
}

// response level info
void Room::ResponseLevelInfo()
{
	if (IsPlaying() && requesting_level_info)
	{
		requesting_level_info = false;
		OnGameConnected(game_fd);
	}
}

// game client leave
void Room::DoGameClientLeave(Client & client)
{
	if (IsPlaying() && requesting_level_info == false)
	{
		if (GetClientSlot(client))
		{
			if (client.state == Client::CS_GameConnecting)
				client.OnGameEnterFailed(kErrorChannelGameEnterLeaveWhenConnecting);
			else
				client.OnGameLeave();

			// notify
			Notify(&Client::NotifyGameClientLeave, client);
		}
	}
}

// on read
void Room::OnRead()
{
}

// on game connected
void Room::OnGameConnected(SOCKET s)
{
	log_write(LOG_DEBUG1, "%s, %s, %d", __FILE__, __FUNCTION__, __LINE__);
	state = RS_Playing;

	Notify(&Client::NotifyRoomStateChanged, *this);
	server.NotifyRoomStateChanged(*this);

	game.Connect(s, sockaddr_in());

	Notify(&Client::NotifyGameStart);
}

// notify
void Room::Notify(void (Client::*func)())
{
	for (RoomSlot * slot = slots; slot < endof(slots); slot++)
	{
		if (slot->client)
			(slot->client->*func)();
	}
}

// notify
template<class T>
void Room::Notify(void (Client::*func)(T&), T & t)
{
	for (RoomSlot * slot = slots; slot < endof(slots); slot++)
	{
		if (slot->client)
			(slot->client->*func)(t);
	}
}

// update character data
void Room::UpdateCharacterData(Client & client)
{
	game.UpdateCharacterData(client);
	
	Notify(&Client::NotifyRoomClientUpdate, client);
}

// get slot
RoomSlot * Room::GetSlot(uint id)
{
	if (id > 0 && id < elementsof(slots) + 1)
		return &slots[id - 1];
	else
		return NULL;
}

// get client
Client * Room::GetClient(uint slot_id)
{
	if (slot_id > 0 && slot_id < elementsof(slots) + 1)
		return slots[slot_id - 1].client;
	else
		return NULL;
}

// get client slot
uint Room::GetClientSlot(Client & client)
{
	if (GetClient(client.uid_in_room) == &client)
		return client.uid_in_room;

	return 0;
}

// calculate client count
void Room::UpdateClientCount()
{
	uint room_client_count = 0;
	uint room_viewer_count = 0;
	uint cur_client_count = 0;

	for (RoomSlot * slot = slots; slot < endof(slots); slot++)
	{
		switch (slot->status)
		{
		case RoomSlot::kOpen:
			room_client_count++;
			break;

		case RoomSlot::kView:
			room_client_count++;
			room_viewer_count++;
			break;
		}

		if (slot->client != NULL || slot->preserve_character_name[0])
			cur_client_count++;
	}

	// update room max client count and viewer count
	if (room_client_count != option.client_count ||
		room_viewer_count != option.viewer_count )
	{
		option.client_count = room_client_count;
		option.viewer_count = room_viewer_count;

		// notify
		Notify(&Client::NotifyRoomChangeOption, *this);
		server.NotifyRoomOptionChanged(*this);
	}

	// update inroom client count
	if (cur_client_count != client_count)
	{
		client_count = cur_client_count;

		// notify
		if (client_count > 0)
			server.NotifyRoomClientCountChanged(*this);
	}
}

void Room::CheckMatchingCallBack( float fDelta )
{
	if (!option.m_bIsMatchingRoom)
	{
		return;
	}
	if (state != RS_Playing)
	{
		return;
	}

	int arrTeamCount[2] = {max_room_client_count/ 2, max_room_client_count / 2};
	for (int i = 0; i < elementsof(slots); ++i)
	{
		if (slots[i].client)
		{
			arrTeamCount[slots[i].team] -= 1;
			if (m_fWaitForMatchingCallBackTime <= 0)
			{
				log_write(LOG_DEBUG1, "client name : %s, team : %d", slots[i].client->character_name, slots[i].team);
			}
		}
	}

	if (arrTeamCount[0] | arrTeamCount[1])
	{
		if (m_bWaitForMatchingCallBack)
		{
			// ȱ�� �����ڵȻذ�
			return;
		}
		else
		{
			//ȱ�� ����û�ڵȻذ�
			if (m_fWaitForMatchingCallBackTime <= 0)
			{
				if (arrTeamCount[0] > arrTeamCount[1])
				{
					arrTeamCount[0] = 1;//(arrTeamCount[0] - arrTeamCount[1]);
					arrTeamCount[1] = 0;
				}
				else if (arrTeamCount[0] < arrTeamCount[1])
				{
					arrTeamCount[1] = 1;//(arrTeamCount[1] - arrTeamCount[0]);
					arrTeamCount[0] = 0;
				}
				else
				{
					// ��һ��һ���ļӺ��� Ҫ���̫��
					arrTeamCount[0] = arrTeamCount[1] = 1;
				}
				server.proxy_connection.RequestMatchingClient(id, m_dwMatchingLevel, 0, option.game_type, option.level_id, arrTeamCount[0], arrTeamCount[1]);
				m_fWaitForMatchingCallBackTime = 5.0f;	// ÿ�ε�5��
				m_bWaitForMatchingCallBack = true;
			}
			else
			{
				m_fWaitForMatchingCallBackTime -= fDelta;
			}
		}
	}
}

template void Room::Notify<Client>(void (Client::*func)(Client&), Client & t);
