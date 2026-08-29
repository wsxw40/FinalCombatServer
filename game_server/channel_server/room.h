#pragma once

struct RoomSlot
{
	enum Status
	{
		kClosed,
		kOpen,
		kView,
	};
public:
	bool IsEmpty() { return preserve_character_name[0] == 0 && client == NULL; }

public:
	byte id;
	byte team;
	uint status;
	char preserve_character_name[32];
	Client * client;
	double preserve_time;
};

class Room : public IEvent
{
public:
	enum ERoomState
	{
		RS_Idle,
		RS_Waiting,
		RS_Playing,
	};

public:
	// constructor
	Room();

	// destructor
	~Room();

	// on create
	void OnCreate();

	// on read
	void OnRead();

	// on write
	void OnWrite() {};

	// on close
	void OnClose();

	// on game connected
	void OnGameConnected(SOCKET s);

	// on  update
	void OnUpdate(double frame_time);

	// is ready
	bool IsReady();

	// is waiting
	bool IsWaiting();

	// is playing
	bool IsPlaying();

	// do create
	int DoRoomCreate(Client & client, RoomOption & op);

	// join in 
	int DoRoomClientEnter(Client & client, const char* password, const char ** members, int member_count);

	// room leave
	void DoRoomClientLeave(Client & client);

	// change option
	int DoChangeOption(Client & client, RoomOption & op, bool is_create);
	
	// change td option
	int DoChangeTDOption(Client & client, int level_id, int map_level, int res_value, const char * rand_key);

	// change host
	void DoChangeHost(Client & client);

	// client change team
	int DoClientChangeTeam(Client & client, byte team);

	// client ready
	int DoClientReady(Client & client, bool ready);

	// verify password
	bool DoVerifyPassword(const char* ps);

	// slot check game balance
	bool ChangeSlotCheckBalance(Client & client, const RoomSlot * old_slot, const RoomSlot * new_slot);

	// check game start
	int CheckGameStart();
	
	// 
	void DoTDGamePrepare();

	// game start
	int DoGameStart();

	// game end
	bool DoGameEnd();

	// game enter
	int DoGameClientEnter(Client & client, bool game_start = false);

	// do room kick client
	int DoRoomKickClient(Client & client, byte id_in_room);

	// do room kick client
	int DoRoomKickClient(byte id_in_room);

	// client change slot
	int DoClientChangeSlot(Client & client, byte slot_id);

	// change slot status
	int DoChangeSlotStatus(Client & client, byte slot_id, RoomSlot::Status status);

	// do preserve slot
	int DoPreserveSlot(byte slot_id, const char * character_name, const char * invite_name);

	// do cancel preserve
	byte DoCancelPreserveSlot(byte slot_id);

	// request chararcter info
	void RequestCharacterInfo(Client & client, int level_id, byte character_id, byte is_knife);

	// response character info
	void ResponseCharacterInfo(Client & client, void * buffer, int size);

	// response level info
	void ResponseLevelInfo();

	// game client leave
	void DoGameClientLeave(Client & client);

	// notify
	void Notify(void (Client::*func)());

	// notify
	template<class T>
	void Notify(void (Client::*func)(T&), T & t);

	// update character data
	void UpdateCharacterData(Client & client);

	// get slot
	RoomSlot * GetSlot(uint id);

	// get client
	Client * GetClient(uint slot_id);

	// get client slot
	uint GetClientSlot(Client & client);

	// update client count
	void UpdateClientCount();

	void CheckMatchingCallBack(float fDelta);

public:
	uint uid;
	char name[room_name_length];
	uint host_id;
	char host_name[user_name_length];
	uint host_group_id;
	char host_group[group_name_length];
	RoomOption option;
	short id;
	byte state;
	char client_count;
	Client* host_client;
	int game_fd;
	int rpc_index;
	bool requesting_level_info;
	double autostart_begin_time;
	double ping_time;

	bool m_bWaitForMatchingCallBack;
	float m_fWaitForMatchingCallBackTime;
	int m_dwMatchingLevel;
	
	uint m_dwCreateTime;
	
	std::map<uint, float> kick_clients;

	Game game;

	RoomSlot slots[max_room_client_count];
	
	bool m_bMatchingRequestClient;
};

