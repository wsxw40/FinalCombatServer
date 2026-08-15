#include "proxy.h"
#include "common.h"
#include "matchingserver.h"
#include "log.h"
#include "pch.h"

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
	CM_MatchingCompleted,
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

ProxyConnection::ProxyConnection()
:BinaryStream(appcfg.m_dwServerBufferSize)
{
	connection = this;
	stream = this;
}

void ProxyConnection::OnMessage()
{
	byte opcode;
	ReadByte(opcode);
	switch (opcode)
	{
		case CM_RequestMatching:
			OnRequestMatching();
			break;
		case CM_RequestCancelMatching:
			OnCancelMatching();
			break;
		case CM_RequestMatchingRoomClient:
			OnRequestMatchingRoomClient();
			break;
		case CM_RequestMatchingProgress:
			OnRequestMatchingProgress();
			break;
		case CM_CCU:
			OnUpdateCCU();
			break;
		case CM_ResponseMatchingEventTime:
			OnResponseMatchingEventTime();
			break;
	}
}
void ProxyConnection::OnRequestMatching()
{
	uint server_id;
	uint matching_id;
	byte matching_level;
	byte client_count;
	char game_mode;
	byte team_type;
	int nLevelId;


	ReadInt(server_id);
	ReadInt(matching_id);
	ReadByte(matching_level);
	ReadByte(client_count);
	ReadByte(game_mode);
	ReadByte(team_type);
	ReadInt(nLevelId);

	//if (team_type == Matching::TYPE_GUILD)
	//{
	//	if (!server.IsEventTime(Event::GetTime()))
	//	{
	//		BeginWrite();
	//		WriteByte(Matching::SM_ResponseMatching);
	//		WriteInt(kErrorProxyMatchingGuildTeamNotStarted);
	//		WriteInt(matching_id);
	//		WriteByte(server.GetMatchingTeamCount(team_type) * 2);
	//		EndWrite();
	//		return;
	//	}
	//}

	if (matching_level < MatchingGroup::MIN_LEVEL || matching_level > MatchingGroup::MAX_LEVEL)
	{
		log_write(LOG_ERROR, "matching_level = %u invalid, clamp it", matching_level);
		matching_level = Clamp(matching_level, static_cast<byte>(MatchingGroup::MIN_LEVEL), static_cast<byte>(MatchingGroup::MAX_LEVEL));
	}


	log_write(LOG_DEBUG1, "%s, %s, matching_id = %llu, matching_level = %u, client_count = %u, game_mode = %d, level_id = %d", __FILE__, __FUNCTION__, matching_id, matching_level, client_count, game_mode, nLevelId);

	if (server.AddMatching(server_id, matching_id, team_type, client_count, matching_level, game_mode, nLevelId))
	{
		BeginWrite();
		WriteByte(SM_ResponseMatching);
		WriteInt(kErrorNone);
		WriteInt(matching_id);
		WriteByte(server.GetMatchingTeamCount(team_type) * 2);
		EndWrite();
	}
	else
	{
		log_write(LOG_ERROR, "Add matching error, matching_id = %lld, client_count = %d", matching_id, client_count);
	}
}

void ProxyConnection::OnCancelMatching()
{
	uint matching_id;
	byte   no_return;
	bool succ;

	ReadInt(matching_id);
	ReadByte(no_return);

	log_write(LOG_DEBUG1, "Cancel matching: matching_id = %d", matching_id);

	if (server.RemoveMatching(matching_id))
		succ = true;
	else
	{
		log_write(LOG_WARNING, "Remove matching error, matching_id = %d", matching_id);
		succ = false;
	}

	if (!no_return)
	{
		BeginWrite();
		WriteByte(SM_ResponseCancelMatching);
		WriteInt(kErrorNone);
		WriteInt(matching_id);
		WriteByte(succ);
		EndWrite();
	}
}

void ProxyConnection::MatchingCompleted( const uint server_id, const uint team_type, const uint game_mode, const uint matching_clients[] )
{
	int n = 0;                                                                                                          
	byte *count = NULL;                                                                                                 

	BeginWrite();                                                                                                       
	WriteByte(CM_MatchingCompleted);
	WriteInt(server_id);
	WriteByte(team_type);
	WriteByte(game_mode);
	for (int j = 0; j < 2; ++j)
	{
		n = 0;
		count = reinterpret_cast<byte*>(write_position);
		WriteByte(0);
		for (int i = 0 + (j * max_room_client_count / 2); i < max_room_client_count / 2 + (j * max_room_client_count / 2); ++i)
		{
			if (matching_clients[i] != 0)
			{
				log_write(LOG_DEBUG1, "Matching completed: team = %d, matching_id = %d", j, matching_clients[i]);
				WriteInt(matching_clients[i]);
				n++;
			}
			else
			{
				break;
			}
		}
		*count = n;
	}
	EndWrite();
}
void ProxyConnection::OnRequestMatchingRoomClient()
{
	uint dwServerId = 0;
	uint dwChannelId = 0;
	uint matching_room_id;
	int team_type = 0;
	int game_mode = 0;
	int dwLevelId = 0;
	int matching_level;
	int team_count[2] = {0};
	uint matching_clients[max_room_client_count] = {0};

	char cGameMode = 0;
	ReadInt(dwServerId);
	ReadInt(dwChannelId);
	ReadInt(matching_room_id);
	ReadInt(dwLevelId);
	ReadInt(matching_level);
	ReadByte((byte &)team_type);
	ReadByte(cGameMode);
	game_mode = cGameMode;
	ReadByte((byte &)team_count[0]);
	ReadByte((byte &)team_count[1]);

	log_write(LOG_DEBUG1, "MatchingRoom Request client: matching_room_id = %u, matching_level = %u, game_mode = %u, team_0 = %u, team_1 = %u",
			matching_room_id,
			matching_level,
			game_mode,
			team_count[0],
			team_count[1]
			);

	if (matching_level < MatchingGroup::MIN_LEVEL || matching_level > MatchingGroup::MAX_LEVEL)
	{
		log_write(LOG_ERROR, "matching_level = %u invalid, clamp it", matching_level);
		int max_level = MatchingGroup::MAX_LEVEL;
		int min_level = MatchingGroup::MIN_LEVEL;
		matching_level = Clamp(matching_level, min_level, max_level);
	}

	if (team_count[0] > max_room_client_count / 2 || team_count[1] >  max_room_client_count / 2)
	{
		log_write(LOG_ERROR, "team count invalid, clamp it");
		team_count[0] = team_count[0] > max_room_client_count / 2 ? max_room_client_count / 2 : team_count[0];
		team_count[1] = team_count[1] > max_room_client_count / 2 ? max_room_client_count / 2 : team_count[1];
	}

	if (server.Matching(dwServerId, team_type, game_mode, dwLevelId, matching_clients, matching_level, team_count[0], team_count[1]))
	{
		BeginWrite();
		WriteByte(SM_ResponseMatchingRoomClient);
		WriteInt(dwServerId);
		WriteInt(dwChannelId);
		WriteInt(matching_room_id);
		WriteByte(true);

		int n = 0;
		byte *count = NULL;

		for (int j = 0; j < 2; ++j)
		{
			n = 0;
			count = reinterpret_cast<byte*>(write_position);
			WriteByte(0);
			for (int i = 0 + (j * max_room_client_count / 2); i < max_room_client_count / 2 + (j * max_room_client_count / 2); ++i)
			{
				if (matching_clients[i] != 0)
				{
					log_write(LOG_DEBUG1, "%s, %s, completed team = %d, matching_id = %d", __FILE__, __FUNCTION__, j, matching_clients[i]);
					WriteInt(matching_clients[i]);
					n++;
				}
				else
				{
					break;
				}
			}
			*count = n;
		}

		EndWrite();
	}
	else
	{
		BeginWrite();
		WriteByte(SM_ResponseMatchingRoomClient);
		WriteInt(dwServerId);
		WriteInt(dwChannelId);
		WriteInt(matching_room_id);
		WriteByte(false);
		EndWrite();
	}
}
void ProxyConnection::OnRequestMatchingProgress()
{
	uint matching_id;
	uint client_uid;

	ReadInt(matching_id);
	ReadInt(client_uid);

	if (const MatchingGroup *matching_group = server.GetMatchingGroup(matching_id))
	{
		BeginWrite();
		WriteByte(SM_ResponseMatchingProgress);
		WriteInt(matching_id);
		WriteInt(client_uid);
		WriteByte(matching_group->GetCommitedProgress() == 0 ? matching_group->GetCount() : matching_group->GetCommitedProgress());
		WriteByte(server.GetMatchingTeamCount(matching_group->GetType()) * 2);
		EndWrite();
	}
}
void ProxyConnection::OnUpdateCCU()
{
	int ccu = 0;
	ReadInt(ccu);
	if (ccu != last_ccu)
	{
		last_ccu = ccu;
		//TODO
	}
}

void ProxyConnection::OnResponseMatchingEventTime()
{
	server.OnUpdateEventTime(this);
}

void ProxyConnection::ResponseCancelMatching( uint dwReason, uint dwMatchingId, bool bRet )
{
	BeginWrite();
	WriteByte(SM_ResponseCancelMatching);
	WriteInt(dwReason);
	WriteInt(dwMatchingId);
	WriteByte(bRet);
	EndWrite();
}
