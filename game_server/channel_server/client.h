#pragma once

struct RoomSlot;

class Client : public BinaryStream
{
public:
	enum EClientState
	{
		CS_Idle,
		CS_Connected,
		CS_InChannel,
		CS_InRoom,
		CS_GameConnecting,
		CS_InGame,
	};

public:
	// constructor
	Client();

	// destructor
	~Client();

	// disconnect
	void Disconnect();

	// on client connected
	void OnConnected();

	// on close
	void OnDisconnected();

	// receive message
	void OnMessage();

	// on message connected
	void OnMessageConnected(byte msg);

	// on message in channel
	void OnMessageInChannel(byte msg);

	// on message in room
	void OnMessageInRoom(byte msg);

	// on message in room
	void OnMessageInGame(byte msg);

	// on room enter
	void OnRoomEnter();

	// on room leave
	void OnRoomLeave();

	// on enter game before
	void OnGameEnterBefore();

	// on enter game failed
	void OnGameEnterFailed(int error_code);

	// on enter game
	void OnGameEnter();

	// on leave game
	void OnGameLeave();

	// on update
	void OnUpdate(double frame_time);

	// on response channel enter
	void ResponseChannelEnter(uint channel_id, const char * channel_name, int result);

	// request channel enter
	void RequestChannelEnter();

	// request chat
	void RequestChat();

	// Response room list
	void RequestRoomList();

	// request room create
	void RequestRoomCreate();

	// response room create
	void ResponseRoomCreate(Room * room, RoomOption & op);
	void ResponseRoomCreate(int dwResult, Room* pRoom);

	// request room enter
	void RequestRoomEnter(bool team_enter);

	void ResponseRoomEnter(int dwResult, Room* pRoom);

	// request room leave
	void RequestRoomLeave();

	// response room leave
	void ResponseRoomLeave(Room * room);

	// request room client list
	void RequestRoomClientList();

	// request room change option
	void RequestRoomChangeOption();

	// request room change team
	void RequestRoomChangeTeam();

	// request room ready
	void RequestRoomReady();

	// request game start
	void RequestGameStart();

	void ResponseGameStart(int dwResult);

	// request room kick client
	void RequestRoomKickClient();

	// request room change slot
	void RequestRoomChangeSlot();

	// request room change slot status
	void RequestRoomChangeSlotStatus();

	// request room preserve slot.
	void RequestRoomPreserveSlot();

	// request client list
	void RequestClientList();

	// notify room kick client
	void NotifyRoomKickClient(Client & client);

	// response game start
	void NotifyGameStart();

	// response game end
	void NotifyGameEnd();

	// request game enter
	void RequestGameEnter();

	// response game enter
	void ResponseGameEnter(int error_code);

	// notify channel client enter
	void NotifyChannelClientEnter(Client & client);

	// notify channel client leave
	void NotifyChannelClientLeave(Client & client);

	// notify room create
	void NotifyRoomCreate(Room & room);

	// notify room close
	void NotifyRoomClose(Room & room);

	// notify room list
	void NotifyRoomList();

	// notify room change option
	void NotifyRoomChangeOption(Room & room);

	// notify room change option
	void NotifyRoomClientCountChanged(Room & room);

	// notify room client enter
	void NotifyRoomClientEnter(Client & client);

	// notify room client leave
	void NotifyRoomClientLeave(Client & client);

	// notify room client update
	void NotifyRoomClientUpdate(Client & client);
	
	// notify room host changed
	void NotifyRoomHostChanged(Room & room);

	// notify room state changed
	void NotifyRoomStateChanged(Room & room);

	// notify client change team
	void NotifyClientChangeTeam(Client & client);

	// notify client chagne slot
	void NotifyClientChangeSlot(Client & client);

	// notify client chagne slot
	void NotifyRoomChangeSlotStatus(RoomSlot & slot);

	// notify client ready
	void NotifyClientReady(Client & client);

	// NotifyClientAutoStart
	void NotifyClientAutoStart(Client & client);

	// NotifyClientAutoStartCancel
	void NotifyClientAutoStartCancel(Client & client);

	// notify game client enter
	void NotifyGameClientEnter(Client & client);

	// notify game client leave
	void NotifyGameClientLeave(Client & client);

	// notify game leave
	void NotifyGameLeave();

	// notify chat
	void NotifyChat(const char * to, const char * name, const char * msg);

	// response client list
	void ResponseClientList(uint start);

	// request novice
	void RequestNovice();

	// request tddata
	void RequestTDData();
	
	// request connection check
	void RequestConnectionCheck(const Room * room);

	// connection check
	void ConnectionCheck();
	
	// roominfo check
	bool RoomInfoCheck(Room * room);

	void OnRequestEnterRoomWithSlotId();
	
	// write room info
	void WriteRoomInfo(Room & room);

protected:

	// write client info
	void WriteClientInfo(Client & client);
	
public:
	int state;
	uint uid;
	uint uid_in_room;
	uint uid_in_proxy;
	uint room_id;
	byte team;
	uint character_id;
	char character_name[character_name_length];
	uint character_group_id;
	char character_group[group_name_length];
	uint character_level;
	int character_exp;
	bool ready;
	
	byte is_vip;
	byte net_bar_level;
	byte business_card;
	byte is_gm;
	char head_icon[16];

	int fcm_online_minutes;
	int fcm_offline_minutes;
	
	uint top;
	uint fightnum;
	float win_rate;

	Gag gag;
	double refresh_clientlist_time;
	double roomwait_time;
	
	double connection_check_time;
	int connection_check_error_count;
	
	float connection_reporttime;

	sockaddr_in client_address;
	UdpConnection udp_connection;
	TcpConnection tcp_connection;
	
	HuffmanNetworkCompressor huffman_compressor;
	XORNetworkEncoder xor_encoder;
	Room* room_novice;
};
