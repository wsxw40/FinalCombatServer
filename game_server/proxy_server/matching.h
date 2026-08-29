#pragma once

#include "tcpconnection.h"
#include "networkstream.h"

class InfoConnection;
struct Level;

class MatchingConnection: public TcpConnection, public BinaryStream
{
	public:
		MatchingConnection();
		void OnConnected();
		void OnDisconnected();

		// level id 为0时 为随机模式
		void RequestMatching(uint server_id, uint matching_id, byte matching_level, byte client_count, byte game_mode, byte team_type, int nLevelId = 0);
		void RequestCancelMatching(uint matching_id, bool no_return);
		void RequestMatchingProgress(uint matching_id, uint client_uid);

		void OnUpdateLevelList(const std::vector<Level>& level_list);
		uint GetGameModeByRandom();
		bool IsGameModeValid(int game_mode) { return matching_mode_count.find(game_mode) != matching_mode_count.end(); }

		void OnUpdateGuildTeamEventTimeCallback( void* pData, uint dwLen );

		void RequestMatchingClient(int dwServerId, int dwChannelId, int dwRoomId, int dwLevelId, byte cGameMode, uint dwMatchingLevel, byte cTeamType, byte dwTeam1Count, byte dwTeam2Count);

		static void UpdateCCU(void* parg);
	public:
		sockaddr_in addr;

	private:
		void OnMessage();
		void OnMatchingCompleted();
		void OnResponseMatching();
		void OnResponseCancelMatching();
		void OnResponseMatchingRoomClient();
		void OnResponseMatchingProgress();
	private:
		bool connected;
		//std::map<uint, uint> matching_mode_count;
		std::map<uint, std::list<uint> > matching_mode_count;
};
