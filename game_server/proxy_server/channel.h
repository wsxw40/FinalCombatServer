#pragma once
class ClientConnection;

class ChannelConnection : public TcpConnection, public BinaryStream
{
public:
	// constructor
	ChannelConnection();

	// destructor
	~ChannelConnection();
	
	// on client connected
	void OnConnected();

	// on close
	void OnDisconnected();

	// on message
	void OnMessage();

	// on initialize
	void OnInitialize();

	// on sync data
	void OnSyncData();

	// on request rcp
	void OnRequestRPC();

	// on request channel enter
	void OnRequestEnterChannel();

	// on notify leave channel
	void OnNotifyLeaveChannel();

	// on notify client room changed
	void OnNotifyClientRoomChanged();

	// on notify client status changed
	void OnNotifyClientStatusChagned();

	// on notify room preserve slot
	void OnNotifyRoomPreserveSlot();

	// on notify room cancel preserve slot
	void OnNotifyRoomCancelPreserveSlot();

	// on response search room
	void OnResponseSearchRoom();
	
	// on update channel status
	void OnUpdateChannelStatus();

	// [2015/10/15 dengxiaobo]
	void OnReponseMatchingRoomEnter();
	void OnRequestMatchingClient();
	void ResponseMatchingClient(uint dwRoomId, byte cRet, uint arrMatchingIds1[], uint arrMatchingIds2[]);
	void OnRequestIntoTargetRoom();
	// end
	
	void OnResponseHageBattleRoomCreate();
	void OnResponseHageBattleHappyJumpEnter();

public:
	// is ready
	bool IsReady();

	// broadcast chat message
	void BroadcastChatMessage(const char * to, const char * name, const char * msg);

	// update keywords
	void UpdateFilterKeywords();
	
	// update blacklist
	void UpdateBlackList();
	
	// update level list
	void UpdateLevelList();
	
	// set client connect mode
	void SetClientConnectMode(byte is_tcp);

	void SetServerNovice(byte novice);

	void SetServerType(int eServerType);

	// request client leave
	void RequestClientLeave(uint uid_in_proxy, uint uid_in_channel);
	
	// request sync data
	void RequestSyncData();

	// update character data
	void UpdateCharacterData(ClientConnection * client);

	// notify character refuse preserve
	void NotifyCharacterRefusePreserve(uint room_id, uint slot_id);

	// search room
	void RequestSearchRoom(uint client_uid, SearchRoomOptions & options);

	void RequestMatchingRoomEnter(byte cGameMode, int nLevelId, uint dwMatchingId1, uint dwMatchingId2);

	bool IsMatchingChannel();
	
	void RequestHageBattleRoomCreate(uint dwBattleUid, uint dwClientUid, RoomOption& refRoomOp);
	void RequestHageBattleRoomEnter(uint dwClientUid, uint dwRoomId);
	void RequestHageBattleHappyJumpEnter(uint dwClientUid, std::set<uint>& refsetRoomIds, RoomOption& refRoomOption);
	
public:
	// debug
	void RequestDebugCmd(const std::vector<std::string> & cmd_list);

protected:
	// write character data
	void WriteCharacterData(ClientConnection * client);

public:
	uint uid;
	uint server_id;
	uint channel_id;
	int client_count;
	int client_max;
	int state;
	int room_count;
	int game_count;
	int game_client_count;

	char name[64];
	char address[512];
	ushort client_port;
};

