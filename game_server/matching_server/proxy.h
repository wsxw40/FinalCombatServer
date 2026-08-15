#pragma once

#include "tcpconnection.h"
#include "networkstream.h"
#include <algorithm>

class ProxyConnection: public TcpConnection, public BinaryStream
{
	public:
		ProxyConnection();

		void MatchingCompleted(const uint server_id, const uint team_type, const uint game_mode, const uint matching_clients[]);

		int GetCCU() { return std::max(last_ccu, 1); }

		void ResponseCancelMatching(uint dwReason, uint dwMatchingId, bool bRet);

	private:
		void OnMessage();

		void OnConnected() { last_ccu = 0; }

		void OnRequestMatching();
		void OnCancelMatching();

		void OnRequestMatchingRoomClient();

		void OnRequestMatchingProgress();

		void OnUpdateCCU();

		void OnResponseMatchingEventTime();

	private:
		int last_ccu;
};
