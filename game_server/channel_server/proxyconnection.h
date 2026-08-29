#pragma once

class Game;

class ProxyConnection : public TcpConnection, public BinaryStream
{
public:
	// constructor
	ProxyConnection();

	// destructor
	~ProxyConnection();

	// on connected
	void OnConnected();

	// on close
	void OnDisconnected();

	// on message
	void OnMessage();
	
	// on response rpc
	void OnResponseRPC();

	// response game rpc
	void OnResponseGameRPC(uint error);

	// response level info
	void OnResponseLevelInfo(uint error);

	// response character info
	void OnResponseCharacterInfo(uint error);

	// response stage clear
	void OnResponseStageClear();

	// response stage quit
	void OnResponseStageQuit();

	// broadcast chat
	void OnBroadcastChat();

	// update filter keywords
	void OnUpdateFilterKeywords();

	// update blacklist
	void OnUpdateBlackList();
	
	// update levellist
	void OnUpdateLevelList();
	
	// on channel enter
	void OnResponseEnterChannel();

	// on request client leave
	void OnRequestClientLeave();

	// on update character data
	void OnUpdateCharacterData();

	// on notify character refuse preserve
	void OnNotifyCharacterRefusePreserve();

	// on request search room
	void OnRequestSearchRoom();
	
	// on set client connect mode
	void OnSetClientConnectMode();

	void OnSetServerNovice();
	void OnSetServerType();
	
	// [2015/10/15 dengxiaobo]
	void OnRequestMatchingRoomEnter();
	void RequestMatchingClient(uint room_id, uint client_level, byte team_type, byte game_mode,
		int dwLevelId, byte team0_client_count, byte team1_client_count);
	void OnResponseMatchingClient();
	// end
	
	void OnRequestHageBattleRoomCreate();
	void OnRequestHageBattleRoomEnter();
	void OnRequestHageBattleHappyJumpEnter();

public:
	// debug
	void OnRequestDebugCmd();

public:
	// sync data
	void SyncData();

	// request channel enter
	void RequestEnterChannel(uint uid_in_channel, uint uid_in_proxy, uint character_id);

	// notify channel leave
	void NotifyLeaveChannel(uint uid_in_channel, uint uid_in_proxy, uint character_id);
	
	// request character info
	void RequestCharacterInfo(Client & client, int level_id, byte character_id, byte is_knife);

	// begin request rpc
	void BeginRPCHead(const char * url);

	// begin rpc param
	void EndRPCHead();

	// forward binary rpc
	void ForwardBinaryRPC(Game & game);

	// request level list
	void RequestLevelList(int client_id);

	// request level info
	void RequestLevelInfo(const Room & room);

	// notify client room changed
	void NotifyClientRoomChanged(uint uid_in_proxy, int room_id);

	// notify client status changed
	void NotifyClientStatusChanged(uint uid_in_proxy, int status);

	// notify room preserve
	void NotifyRoomPreserveSlot(uint room_id, byte slot_id, const char * character_name, const char * invite_name);

	// notify room cancel preserve slot
	void NotifyRoomCancelPreserveSlot(uint room_id, byte slot_id, const char * character_name);
	
	// update channel status
	void UpdateChannelStatus();

protected:
	// read character data
	void ReadCharacterData(Client * client);

public:
	int status_client_count;
	char * rpc_head_position;
	
private:
	int client_ping_status[10];
	int client_resend_status[10];
	double status_update_time;
	int request_client_count;
};
