#include "pch.h"

std::set<uint> BattleGroup::battlegroup_list;

BattleGroup::BattleGroup()
	: server_id(0)
	, channel_id(0)
	, room_id(0)
	
	, ownerclient_uid(0)
	, group_id(0)
	, group_level(0)
	
	, is_searching(false)
	, searching_interval(0)
{
	memset(group_name, 0, sizeof(group_name));
	
	memset(vs_group_name, 0, sizeof(vs_group_name));
}

BattleGroup::~BattleGroup()
{
}

bool BattleGroup::Initialize(ClientConnection *client, const RoomOption &op)
{
	bool ret = false;
	if (client && strlen(client->character_group) > 0)
	{
		server_id = client->character_server_id;
		channel_id = client->character_channel_id;
		room_id = client->character_room_id;
		
		roomop = op;
		
		ownerclient_uid = client->uid;
		group_id = client->character_group_id;
		group_level = client->character_group_level;
		strcpy(group_name, client->character_group);
		
		memset(vs_group_name, 0, sizeof(vs_group_name));
		
		is_searching = false;
		searching_interval = 0;
		
		characters_uid.clear();
		
		ret = AddCharacter(client);
	}
	
	if (ret)
	{
		battlegroup_list.insert(uid);
		
		client->battlegroup_state = 1;
	}
	
	return ret;
}

bool BattleGroup::AddCharacter(ClientConnection *client)
{
	uint client_uid = client->uid;
	
	if (strcmp(group_name, client->character_group) != 0)
		return false;
	
	if (characters_uid.size() < appcfg.battlegroup_character_limit && 
		characters_uid.find(client_uid) == characters_uid.end() && 
		client->joined_battlegroup == 0)
	{
		characters_uid.insert(client_uid);
		
		client->joined_battlegroup = uid;
		client->battlegroup_state = 0;
		
		return true;
	}
	
	return false;
}

bool BattleGroup::DelCharacter(ClientConnection *client)
{
	uint client_uid = client->uid;
	
	std::set<uint>::iterator itr = characters_uid.find(client_uid);
	if (itr != characters_uid.end())
	{
		//ChangeOwner(client);
		bool is_owner = (client_uid == ownerclient_uid);
		
		client->joined_battlegroup = 0;
		client->battlegroup_state = 0;
		
		characters_uid.erase(itr);
		
		if (!IsPlaying() && is_owner)
		{
			for (std::set<uint>::const_iterator itr = characters_uid.begin(); 
				itr != characters_uid.end(); itr++)
			{
				ClientConnection *c = server.GetClient(*itr);
				if (c)
				{
					c->NotifyBattleGroupKick(uid);
					
					c->joined_battlegroup = 0;
					c->battlegroup_state = 0;
				}
			}
			
			characters_uid.clear();
		}
		
		if (is_searching)
		{
			is_searching = false;
			searching_interval = 0;
			
			ClientConnection::NotifyBattleGroupSearching(uid);
		}
		
		if (IsEmpty())
		{
			Terminate();
		}
		
		return true;
	}
	
	return false;
}

void BattleGroup::OnUpdate(double time)
{
	if (IsSearching() && !IsPlaying())
	{
		searching_interval -= time;
		if (searching_interval <= 0)
		{
			searching_interval += appcfg.battlegroup_searching_interval;
			
			// uint match_group_level_min = group_level < 2 ? 0 : group_level - 2;
			// uint match_group_level_max = group_level + 2;
			
			static const int max_match_battlegroups = 100;
			
			uint match_battlegroup_num = 0;
			uint match_battlegroups[max_match_battlegroups];
			
			for (std::set<uint>::const_iterator itr = battlegroup_list.begin(); 
				itr != battlegroup_list.end(); itr++)
			{
				BattleGroup *battlegroup = server.battlegroup_pool.Get(*itr);
				if (battlegroup && 
					battlegroup != this && 
					strcmp(battlegroup->group_name, group_name) != 0 && 
					!battlegroup->IsPlaying() && 
					battlegroup->IsSearching() && 
					(battlegroup->server_id == server_id && battlegroup->channel_id == channel_id)
					/* && (battlegroup->group_level >= match_group_level_min && battlegroup->group_level <= match_group_level_max)*/)
				{
					match_battlegroups[match_battlegroup_num++] = *itr;
					if (match_battlegroup_num >= max_match_battlegroups)
						break;
				}
			}
			
			if (match_battlegroup_num == 0)
				return;
			
			uint match_battlegroup_uid = match_battlegroups[rand() % match_battlegroup_num];
			StartPlay(match_battlegroup_uid, false);
		}
	}
}

bool BattleGroup::IsEmpty()
{
	return characters_uid.size() == 0;
}

bool BattleGroup::IsPlaying()
{
	for (std::set<uint>::const_iterator itr = characters_uid.begin(); 
		itr != characters_uid.end(); itr++)
	{
		ClientConnection *c = server.GetClient(*itr);
		if (c && c->battlegroup_state != 2)
		{
			return false;
		}
	}
	
	return true;
}

bool BattleGroup::IsSearching()
{
	return is_searching;
}

bool BattleGroup::CanStartSearch()
{
	if (IsSearching())
		return false;
	
	if (characters_uid.size() != appcfg.battlegroup_character_limit)
		return false;
	
	for (std::set<uint>::const_iterator itr = characters_uid.begin(); 
		itr != characters_uid.end(); itr++)
	{
		ClientConnection *c = server.GetClient(*itr);
		if (c && c->battlegroup_state != 1)
		{
			return false;
		}
	}
	
	return true;
}

bool BattleGroup::StartPlay(uint battlegroup_uid, bool is_challenge)
{
	BattleGroup *battlegroup = server.battlegroup_pool.Get(battlegroup_uid);
	if (battlegroup && battlegroup != this)
	{
		if (!battlegroup->IsSearching())
			return false;
		
		if (battlegroup->IsPlaying())
			return false;
	
		if (strcmp(battlegroup->group_name, group_name) == 0)
			return false;
		
		BattleGroup *battlegroups[2] = {this, battlegroup};
		byte which_one = rand() % 2;
		
		for (int i = 0; i < 2; i++)
		{
			for (std::set<uint>::iterator itr = battlegroups[i]->characters_uid.begin(); 
				itr != battlegroups[i]->characters_uid.end(); itr++)
			{
				ClientConnection *c = server.GetClient(*itr);
				if (c)
				{
					c->battlegroup_state = 2;
				}
			}
			
			if (i != which_one)
			{
				battlegroups[i]->roomop = battlegroups[which_one]->roomop;
			}
			
			battlegroups[i]->is_searching = false;
			
			strcpy(battlegroups[i]->vs_group_name, battlegroups[i == 0 ? 1 : 0]->group_name);
		
			ClientConnection::NotifyBattleGroupGameStart(battlegroups[i]->uid, 
										battlegroups[which_one]->server_id, battlegroups[which_one]->channel_id, battlegroups[which_one]->room_id, battlegroups[which_one] != battlegroups[i], is_challenge, battlegroups[0]->group_id, battlegroups[1]->group_id);
		}
		
		return true;
	}

	return false;
}

void BattleGroup::Terminate()
{
	battlegroup_list.erase(uid);
	server.battlegroup_pool.Free(uid);
	
	server_id = 0;
	channel_id = 0;
	room_id = 0;
	
	ownerclient_uid = 0;
	group_id = 0;
	group_level = 0;
	
	memset(group_name, 0, sizeof(group_name));
	
	memset(vs_group_name, 0, sizeof(vs_group_name));
	
	is_searching = false;
	searching_interval = 0;
	
	characters_uid.clear();
}

void BattleGroup::ChangeOwner(ClientConnection *client)
{
	if (client->uid == ownerclient_uid)
	{
		for (std::set<uint>::const_iterator itr = characters_uid.begin(); 
			itr != characters_uid.end(); itr++)
		{
			ClientConnection *c = server.GetClient(*itr);
			if (c && *itr != ownerclient_uid)
			{
				ownerclient_uid = *itr;
				
				c->joined_battlegroup = uid;
				c->battlegroup_state = 1;
				
				return;
			}
		}
	}
}

void BattleGroup::WriteBattleGroupInfo(BattleGroup *battlegroup, BinaryStream &stream)
{
	if (battlegroup)
	{
		stream.WriteInt(battlegroup->uid);
		
		stream.WriteInt(battlegroup->server_id);
		stream.WriteInt(battlegroup->channel_id);
		stream.WriteInt(battlegroup->room_id);
		
		WriteRoomOption(stream, battlegroup->roomop);
		
		stream.WriteInt(battlegroup->ownerclient_uid);
		stream.WriteInt(battlegroup->group_level);
		stream.WriteInt(battlegroup->group_id);
		stream.WriteString(battlegroup->group_name);
		
		stream.WriteString(battlegroup->vs_group_name);
		
		stream.WriteInt((uint)battlegroup->characters_uid.size());
		for (std::set<uint>::const_iterator itr = battlegroup->characters_uid.begin(); 
			itr != battlegroup->characters_uid.end(); itr++)
		{
			ClientConnection *c = server.GetClient(*itr);
			if (c)
			{
				c->WriteClientBaseInfo(stream);
				stream.WriteByte(c->battlegroup_state);
			}
		}
	}
}

//////////////////////////////////////////////////////////////////////////

MatchingTeamGroup::MatchingTeamGroup()
: server_id(0)
, channel_id(0)
, room_id(0)
, is_matching(false)
, ownerclient_uid(0)
{

}

MatchingTeamGroup::~MatchingTeamGroup()
{

}

bool MatchingTeamGroup::Initialize( ClientConnection *client, const RoomOption &op )
{
	bool ret = false;
	if (client != NULL)
	{
		server_id = client->character_server_id;
		channel_id = client->character_channel_id;
		room_id = client->character_room_id;
		
		roomop = op;
		
		ownerclient_uid = client->uid;
		//group_id = client->character_group_id;
		//group_level = client->character_group_level;
		//strcpy(group_name, client->character_group);
		//
		//memset(vs_group_name, 0, sizeof(vs_group_name));
		//
		//is_searching = false;
		//searching_interval = 0;
		
		characters_uid.clear();
		
		ret = AddCharacter(client);
	}
	
	if (ret)
	{
		SetMatching(false);
		//battlegroup_list.insert(uid);
		
		//client->battlegroup_state = 1;
	}
	
	return ret;

}

bool MatchingTeamGroup::AddCharacter( ClientConnection *client )
{
	uint client_uid = client->uid;
	
	//if (strcmp(group_name, client->character_group) != 0)
	//	return false;
	
	if (characters_uid.size() >= appcfg.matching_group_character_limit)
	{
		log_write(LOG_DEBUG1, "%s, %s, characters_uid size : %d, big than matching_group_character_limit name : %s", __FILE__, __FUNCTION__, characters_uid.size(), client->character_name);
		return false;
	}
	
	if (characters_uid.find(client_uid) != characters_uid.end())
	{
		log_write(LOG_DEBUG1, "%s, %s, already in group name : %s", __FILE__, __FUNCTION__, client->character_name);
		return false;
	}
	
	if (client->m_dwMatchingTeamGroupId != 0)
	{
		log_write(LOG_DEBUG1, "%s, %s, already in other group : %d, name : %s", __FILE__, __FUNCTION__, client->m_dwMatchingTeamGroupId, client->character_name);
		return false;
	}
	
	
	//if (characters_uid.size() < appcfg.matching_group_character_limit && 
	//	characters_uid.find(client_uid) == characters_uid.end() && 
	//	client->m_dwMatchingTeamGroupId == 0)
	{
		characters_uid.insert(client_uid);
		
		client->m_dwMatchingTeamGroupId = uid;
		
		ServerInfo* pInfo = server.GetServerInfo(server_id);
		if (pInfo == NULL)
		{
			log_write(LOG_ERROR, "error info");
		}
		
		if (pInfo->m_eServerType == SvrType_Match)
		{
			client->matching_level = client->matching_hege_level;
		}
		else
		{
			client->matching_level = client->matching_fighting_level;
		}

		log_write(LOG_DEBUG1, "%s, %s, name %s, id : %d", __FILE__, __FUNCTION__, client->character_name, client->m_dwMatchingTeamGroupId);
		//client->battlegroup_state = 0;
		
		return true;
	}
	
	return false;

}

bool MatchingTeamGroup::DelCharacter( ClientConnection *client, bool bCancleMatch /*= true*/ )
{
	uint client_uid = client->uid;
	
	std::set<uint>::iterator itr = characters_uid.find(client_uid);
	if (itr != characters_uid.end())
	{
		//ChangeOwner(client);
		bool is_owner = (client_uid == ownerclient_uid);
		client->m_dwMatchingTeamGroupId = 0;
		characters_uid.erase(itr);
		
		client->matching_level = 0xffffffff;

		log_write(LOG_DEBUG1, "%s, %s, name : %s, id : %d", __FILE__, __FUNCTION__, client->character_name, client->m_dwMatchingTeamGroupId);

		if (IsMatching() && bCancleMatch)
		{
			server.matching_connection.RequestCancelMatching(uid, false);
		}
		

		if (is_owner)
		{
			//for (std::set<uint>::const_iterator itr = characters_uid.begin();
			//	itr != characters_uid.end(); itr++)
			//{
			//	ClientConnection *c = server.GetClient(*itr);
			//	if (c)
			//	{
			//		c->NotifyMatchingTeamKick(uid);
			//		
			//		c->m_dwMatchingTeamGroupId = 0;
			//	}
			//}
			//
			//characters_uid.clear();
		}

		if (is_owner)
		{
			if (characters_uid.size())
			{
				ownerclient_uid = *(characters_uid.begin());
			}
		}
		
		if (IsEmpty())
		{
			Terminate();
		}
		return true;
	}
	
	return false;

}


bool MatchingTeamGroup::IsEmpty()
{
	return characters_uid.size() == 0;

}


void MatchingTeamGroup::Terminate()
{
	server.m_pollMatchingTeamGroups.Free(uid);

	server_id = 0;
	channel_id = 0;
	room_id = 0;

	ownerclient_uid = 0;

	//memset(group_name, 0, sizeof(group_name));

	//memset(vs_group_name, 0, sizeof(vs_group_name));

	characters_uid.clear();

}

void MatchingTeamGroup::ChangeOwner( ClientConnection *client )
{
	if (client->uid == ownerclient_uid)
	{
		for (std::set<uint>::const_iterator itr = characters_uid.begin(); 
			itr != characters_uid.end(); itr++)
		{
			ClientConnection *c = server.GetClient(*itr);
			if (c && *itr != ownerclient_uid)
			{
				ownerclient_uid = *itr;

				c->m_dwMatchingTeamGroupId = uid;

				return;
			}
		}
	}
}

bool MatchingTeamGroup::IsMatching()
{
	return is_matching;
}

std::map<uint, std::map<uint, uint> > HageBattleGroup::s_mapBattleGroupList;
std::map<uint, std::pair<uint, uint> > HageBattleGroup::s_mapMatchList;
std::map<uint, std::list<uint> > HageBattleGroup::s_mapLevelList;
bool HageBattleGroup::s_bReadyTime = false;
HappyJumpBattle HageBattleGroup::s_HappyJumpBattle;

HageBattleGroup::HageBattleGroup()
	: dwServerId(0)
	, dwChannelId(0)
	, dwRoomId(0)
	
	, dwGroupId(0)
	, dwGroupLevel(0)
	, dwMatchId(0)
	
{
	memset(szGroupName, 0, sizeof(szGroupName));
	
	memset(szVsGroupName, 0, sizeof(szVsGroupName));
}

HageBattleGroup::~HageBattleGroup()
{

}

bool HageBattleGroup::Initialize( BinaryStream& refStream )
{
	// ��ʼ������Ϸ
	s_HappyJumpBattle.Reset();
	
	s_mapLevelList.clear();
	uint dwGameTypeCount = 0;
	refStream.ReadInt(dwGameTypeCount);
	for (uint i = 0; i < dwGameTypeCount; ++i)
	{
		uint dwGameType = 0;
		refStream.ReadInt(dwGameType);
		uint dwLevelCount = 0;
		refStream.ReadInt(dwLevelCount);
		for (uint j = 0; j < dwLevelCount; ++j)
		{
			uint dwLevelId = 0;
			refStream.ReadInt(dwLevelId);
			s_mapLevelList[dwGameType].push_back(dwLevelId);
			
			log_write(LOG_DEBUG1, "%s, %s, game type : %d, levelid : %d", __FILE__, __FUNCTION__, dwGameType, dwLevelId);
		}
	}
	
	for (std::map<uint, std::map<uint, uint> >::iterator it = s_mapBattleGroupList.begin(); it != s_mapBattleGroupList.end(); ++it)
	{
		for (std::map<uint, uint>::iterator iit = (it->second).begin(); iit != (it->second).end(); ++iit)
		{
			HageBattleGroup* pHageBattleGroup = server.m_pollHageBattleGroup.Get(iit->second);
			if (pHageBattleGroup)
			{
				pHageBattleGroup->Terminate();
			}
		}
	}
	s_mapBattleGroupList.clear();
	s_mapMatchList.clear();
	
	uint dwCount = 0;
	refStream.ReadInt(dwCount);
	
	for (uint i = 0; i < dwCount; ++i)
	{
		HageBattleGroup* pHageBattleGroup = server.m_pollHageBattleGroup.Allocate();
		if (!pHageBattleGroup)
		{
			return false;
		}
		
		refStream.ReadInt(pHageBattleGroup->dwMatchId);
		if (s_mapMatchList.find(pHageBattleGroup->dwMatchId) == s_mapMatchList.end())
		{
			s_mapMatchList[pHageBattleGroup->dwMatchId].first = pHageBattleGroup->uid;
		}
		else
		{
			s_mapMatchList[pHageBattleGroup->dwMatchId].second = pHageBattleGroup->uid;
		}
		
		refStream.ReadInt(pHageBattleGroup->dwGroupId);
		refStream.ReadInt(pHageBattleGroup->dwGroupLevel);
		refStream.ReadInt(pHageBattleGroup->dwGameType);
		
		log_write(LOG_DEBUG1, "%s, %s, group id : %d, group level : %d, game type : %d, matchid : %d", __FILE__, __FUNCTION__, pHageBattleGroup->dwGroupId, pHageBattleGroup->dwGroupLevel, pHageBattleGroup->dwGameType, pHageBattleGroup->dwMatchId);
		
		uint dwRoleCount = 0;
		refStream.ReadInt(dwRoleCount);
		pHageBattleGroup->mapCharactersUid.clear();
		for (uint i = 0; i < dwRoleCount; ++i)
		{
			uint dwUid = 0;
			refStream.ReadInt(dwUid);
			pHageBattleGroup->mapCharactersUid[dwUid] = false;
		}
		
		pHageBattleGroup->oRoomOp.game_type = pHageBattleGroup->dwGameType;
		if (s_mapLevelList.find(pHageBattleGroup->oRoomOp.game_type) == s_mapLevelList.end())
		{
			pHageBattleGroup->oRoomOp.level_id = 0;
		}
		else
		{
			pHageBattleGroup->oRoomOp.level_id = *(s_mapLevelList[pHageBattleGroup->oRoomOp.game_type].begin());
			s_mapLevelList[pHageBattleGroup->oRoomOp.game_type].pop_front();
			s_mapLevelList[pHageBattleGroup->oRoomOp.game_type].push_back(pHageBattleGroup->oRoomOp.level_id);
		}
		pHageBattleGroup->oRoomOp.client_count = 16;
		pHageBattleGroup->oRoomOp.use_password = true;
		sprintf(pHageBattleGroup->oRoomOp.password, "%s", "sdfgde");
		s_mapBattleGroupList[pHageBattleGroup->dwGroupId][pHageBattleGroup->dwGameType] = pHageBattleGroup->uid;
	}
}

bool HageBattleGroup::AddCharacter( ClientConnection *pClient, RoomOption& op )
{
	//if (strcmp(szGroupName, pClient->character_group) != 0)
	//{
	//	return false;
	//}
	
	if (pClient->character_server_id == 0 || pClient->character_channel_id == 0)
	{
		log_write(LOG_DEBUG1, "%s, %s, server id : %d, channel id : %d, name : %s", __FILE__, __FUNCTION__, pClient->character_server_id, pClient->character_channel_id, pClient->character_name);
		return false;
	}
	
	ServerInfo* pServerInfo = server.GetServerInfo(pClient->character_server_id);
	if (!pServerInfo)
	{
		return false;
	}
	
	if (pServerInfo->m_eServerType != SvrType_HageBattle)
	{
		log_write(LOG_DEBUG1, "%s, %s, server type error : %d, name : %s", __FILE__, __FUNCTION__, pServerInfo->m_eServerType, pClient->character_name);
		return false;
	}
	
	if (dwGroupId != pClient->character_group_id)
	{
		log_write(LOG_DEBUG1, "%s, %s, group id hage battle : %d, client : %d, name : %s", __FILE__, __FUNCTION__, dwGroupId, pClient->character_group_id, pClient->character_name);
		return false;
	}
	
	if (mapCharactersUid.find(pClient->user_id) == mapCharactersUid.end())
	{
		log_write(LOG_DEBUG1, "%s, %s, uid client : %d, name : %s", __FILE__, __FUNCTION__, pClient->user_id, pClient->character_name);
		return false;
	}
	
	if (dwServerId == 0)
	{
		dwServerId = pClient->character_server_id;
	}
	
	if (dwServerId != pClient->character_server_id)
	{
		log_write(LOG_DEBUG1, "%s, %s, server id : %d, client : %d, name : %s", __FILE__, __FUNCTION__, dwServerId, pClient->character_server_id, pClient->character_name);
		return false;
	}
	
	if (dwChannelId == 0)
	{
		dwChannelId = pClient->character_channel_id;
	}
	
	if (pClient->character_channel_id != dwChannelId)
	{
		log_write(LOG_DEBUG1, "%s, %s, channel id : %d, client : %d, name : %s", __FILE__, __FUNCTION__, dwChannelId, pClient->character_channel_id, pClient->character_name);
		return false;
	}
	
	// ��ȡ���뷿�������
	uint dwCount = 0;
	for (std::map<uint, bool>::const_iterator cit = mapCharactersUid.begin(); cit != mapCharactersUid.end(); ++cit)
	{
		if (cit->second)
		{
			++dwCount;
		}
	}
	
	op.game_type = oRoomOp.game_type;
	op.level_id = oRoomOp.level_id;
	op.special_mode = oRoomOp.special_mode;
	op.is_knife = oRoomOp.is_knife;
	op.rule_value = oRoomOp.rule_value;
	op.use_password = oRoomOp.use_password;
	memcpy(op.password, oRoomOp.password, sizeof(oRoomOp.password));
	
	oRoomOp = op;
	
	if (dwRoomId == 0 && dwCount == 0)
	{
		// ���ʱ���� ��һ�����˽��� ��û�п����� Ӧ�÷���Ϣ��channel �ѷ��俪��
		ChannelConnection* pChannelConnection = server.GetChannel(dwServerId, dwChannelId);
		if (!pChannelConnection)
		{
			log_write(LOG_DEBUG1, "%s, %s, channel not found id : %d, client : %d, name : %s", __FILE__, __FUNCTION__, dwChannelId, pClient->character_channel_id, pClient->character_name);
			return false;
		}
		pChannelConnection->RequestHageBattleRoomCreate(uid, pClient->uid_in_channel, oRoomOp);
		mapCharactersUid[pClient->user_id] = true;
		return true;
	}
	if (dwRoomId != 0)
	{
		// ���ʱ�� �����Ѿ������� ����
		if (!mapCharactersUid[pClient->user_id])
		{
			ChannelConnection* pChannelConnection = server.GetChannel(dwServerId, dwChannelId);
			if (!pChannelConnection)
			{
				log_write(LOG_DEBUG1, "%s, %s, channel not found id : %d, client : %d, name : %s", __FILE__, __FUNCTION__, dwChannelId, pClient->character_channel_id, pClient->character_name);
				return false;
			}
			pChannelConnection->RequestHageBattleRoomEnter(pClient->uid_in_channel, dwRoomId);
		}
		
		mapCharactersUid[pClient->user_id] = true;
		return true;
	}
	mapCharactersUid[pClient->user_id] = true;
}

bool HageBattleGroup::DelCharacter( ClientConnection* pClient )
{
	//if (strcmp(szGroupName, pClient->character_group) != 0)
	//{
	//	return false;
	//}
	if (dwGroupId != pClient->character_group_id)
	{
		log_write(LOG_DEBUG1, "%s, %s, group id hage battle : %d, client : %d, name : %s", __FILE__, __FUNCTION__, dwGroupId, pClient->character_group_id, pClient->character_name);
		return false;
	}
	if (mapCharactersUid.find(pClient->user_id) == mapCharactersUid.end())
	{
		log_write(LOG_DEBUG1, "%s, %s, uid client : %d, name : %s", __FILE__, __FUNCTION__, pClient->user_id, pClient->character_name);
		return false;
	}
	
	if (mapCharactersUid[pClient->user_id] == false)
	{
		log_write(LOG_DEBUG1, "%s, %s, not join battle uid client : %d, name : %s", __FILE__, __FUNCTION__, pClient->user_id, pClient->character_name);
		return false;
	}
	
	mapCharactersUid[pClient->user_id] = false;
	
	//if (dwRoomId != 0)
	//{
	//	// ��channel������Ϣ �뿪����
	//}
	
	// ��ȡ���뷿�������
	uint dwCount = 0;
	for (std::map<uint, bool>::const_iterator cit = mapCharactersUid.begin(); cit != mapCharactersUid.end(); ++cit)
	{
		if (cit->second)
		{
			++dwCount;
		}
	}
	
	if (dwCount == 0)
	{
		dwRoomId = 0;
		dwChannelId = 0;
		dwServerId = 0;
	}
}

void HageBattleGroup::OnUpdate( double time )
{

}

bool HageBattleGroup::IsEmpty()
{

}

bool HageBattleGroup::IsPlaying()
{

}

bool HageBattleGroup::IsSearching()
{

}

bool HageBattleGroup::CanStartSearch()
{

}

void HageBattleGroup::StartPlay( void* pArgs )
{
	if (!s_bReadyTime)
	{
		log_write(LOG_DEBUG1, "%s, %s started!!!!!!!!!!!!!!!!!!", __FILE__, __FUNCTION__);
		return;
	}
	
	log_write(LOG_DEBUG1, "%s, %s", __FILE__, __FUNCTION__);
	s_bReadyTime = false;
	for (std::map<uint, std::pair<uint, uint> >::iterator it = s_mapMatchList.begin(); it != s_mapMatchList.end(); ++it)
	{
		HageBattleGroup* pGroup1 = server.m_pollHageBattleGroup.Get(it->second.first);
		HageBattleGroup* pGroup2 = server.m_pollHageBattleGroup.Get(it->second.second);
		
		HageBattleGroup* pSwitch = pGroup1;
		if (!pGroup1 || pGroup1->dwRoomId == 0)
		{
			pSwitch = pGroup2;
		}
		if (!pSwitch)
		{
			continue;
		}
		if (pSwitch->dwRoomId == 0)
		{
			continue;
		}
		
		uint dwGroup1 = 0 ;
		uint dwGroup2 = 0;
		if (pGroup1)
		{
			dwGroup1 = pGroup1->dwGroupId;
		}
		if (pGroup2)
		{
			dwGroup2 = pGroup2->dwGroupId;
		}
		
		if (pGroup1)
		{
			ClientConnection::NotifyHageBattleGroupGameStart(pGroup1->uid, pSwitch->dwServerId, pSwitch->dwChannelId,
				pSwitch->dwRoomId, pSwitch != pGroup1, false, dwGroup1, dwGroup2);
		}
		if (pGroup2)
		{
			ClientConnection::NotifyHageBattleGroupGameStart(pGroup2->uid, pSwitch->dwServerId, pSwitch->dwChannelId,
				pSwitch->dwRoomId, pSwitch != pGroup2, false, dwGroup1, dwGroup2);
		}
		log_write(LOG_DEBUG1, "%s, %s, server id : %d, channel id : %d, group : %d, and group : %d", __FILE__, __FUNCTION__, pSwitch->dwServerId, pSwitch->dwChannelId, dwGroup1, dwGroup2);
	}
}

void HageBattleGroup::Terminate()
{
	server.m_pollHageBattleGroup.Free(uid);
	
	dwServerId = 0;
	dwChannelId = 0;
	dwRoomId = 0;
	
	dwGroupId = 0;
	dwGroupLevel = 0;
	
	dwMatchId = 0;
	
	memset(szGroupName, 0, sizeof(szGroupName));
	
	memset(szVsGroupName, 0, sizeof(szVsGroupName));
	
	mapCharactersUid.clear();
}

void HageBattleGroup::ChangeOwner( ClientConnection *client )
{

}

void HageBattleGroup::WriteHageBattleGroupInfo( HageBattleGroup *pBattleGroup, BinaryStream& refStream )
{
	if (pBattleGroup)
	{
		refStream.WriteInt(pBattleGroup->uid);

		refStream.WriteInt(pBattleGroup->dwServerId);
		refStream.WriteInt(pBattleGroup->dwChannelId);
		refStream.WriteInt(pBattleGroup->dwRoomId);

		WriteRoomOption(refStream, pBattleGroup->oRoomOp);

		refStream.WriteInt(pBattleGroup->ownerclient_uid);
		refStream.WriteInt(pBattleGroup->dwGroupLevel);
		refStream.WriteInt(pBattleGroup->dwGroupId);
		refStream.WriteString(pBattleGroup->szGroupName);

		refStream.WriteString(pBattleGroup->szVsGroupName);

		uint* pdwRoleCount = reinterpret_cast<uint*>(refStream.write_position);
		refStream.WriteInt(0);
		for (std::map<uint, bool>::const_iterator itr = pBattleGroup->mapCharactersUid.begin(); 
			itr != pBattleGroup->mapCharactersUid.end(); itr++)
		{
			if (itr->second)
			{
				ClientConnection *c = server.GetClientById(itr->first);
				if (c)
				{
					c->WriteClientBaseInfo(refStream);
					refStream.WriteByte(0);
					++ *pdwRoleCount;
				}
			}
			
		}
	}
}

void HageBattleGroup::OnRoomCreate( uint dwClientId, uint dwCreatedRoomId )
{
	dwRoomId = dwCreatedRoomId;
	ChannelConnection* pChannelConnection = server.GetChannel(dwServerId, dwChannelId);
	if (!pChannelConnection)
	{
		return;
	}
	
	uint dwUserId = 0;
	ClientConnection* pClient = server.GetClient(dwClientId);
	if (!pClient)
	{
		log_write(LOG_DEBUG1, "%s, %s, get client error id : %d", __FILE__, __FUNCTION__, dwClientId);
	}
	else
	{
		dwUserId = pClient->user_id;
	}
	
	for (std::map<uint, bool>::iterator it = mapCharactersUid.begin(); it != mapCharactersUid.end(); ++it)
	{
		if (!it->second)
		{
			log_write(LOG_DEBUG1, "%s, %s, uid : %d, not in hage battle", __FILE__, __FUNCTION__, dwClientId);
			return;
		}
		ClientConnection* pClient = server.GetClientById(it->first);
		if (pClient)
		{
			log_write(LOG_DEBUG1, "%s, %s, client : %s, hage room enter", __FILE__, __FUNCTION__, pClient->character_name);
		}
		
		if (it->first == dwUserId)
		{
			continue;
		}
		if (pClient)
		{
			// �����˽��뷿�������
			pChannelConnection->RequestHageBattleRoomEnter(pClient->uid_in_channel, dwRoomId);
		}
	}
}

void HageBattleGroup::DelayStart( void* pArgs )
{
	s_bReadyTime = true;
	HappyJumpBattle::s_bReadyTime = true;
	Event::AddTimer((EventHandler)HageBattleGroup::StartPlay, NULL, 60);
	Event::AddTimer((EventHandler)HappyJumpBattle::EndTime, NULL, appcfg.happy_jump_battle_join_time);
}

bool HappyJumpBattle::s_bReadyTime = false;
HappyJumpBattle::HappyJumpBattle()
{

}

HappyJumpBattle::~HappyJumpBattle()
{

}

void HappyJumpBattle::Reset()
{
	m_setParticipator.clear();
	m_setRoomIds.clear();
}

void HappyJumpBattle::JoinGame( ClientConnection* pClient, RoomOption& op )
{
	op.game_type = kAdvenceMode;
	op.client_count = 10;
	op.use_password = false;
	op.can_join_on_playing = true;
	op.level_id = (HageBattleGroup::s_mapLevelList[kAdvenceMode].front());
	if (pClient->character_channel_id == 0)
	{
		return;
	}
	
	ServerInfo* pInfo = server.GetServerInfo(pClient->character_server_id);
	if(!pInfo)
	{
		return;
	}
	if (pInfo->m_eServerType != SvrType_HageBattle)
	{
		return;
	}
	
	if (m_setParticipator.find(pClient->user_id) != m_setParticipator.end())
	{
		return;
	}
	
	//m_setRoomIds;
	ChannelConnection* pChannel = server.GetChannel(pClient->character_server_id, pClient->character_channel_id);
	pChannel->RequestHageBattleHappyJumpEnter(pClient->uid_in_channel, m_setRoomIds, op);
	
	m_setParticipator.insert(pClient->user_id);
}

void HappyJumpBattle::EndTime( void* pArg )
{
	s_bReadyTime = false;
}


