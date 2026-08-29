#pragma once

// client less
struct ClientLess
{
	bool operator ()(Client * client1, Client * client2)
	{
		if (client1->character_level > client2->character_level)
			return true;

		if (client1->character_level < client2->character_level)
			return false;

		return strcasecmp(client1->character_name, client2->character_name) < 0;
	}
};

// client listener
class ClientUdpListener : public UdpListener
{
	virtual void OnClientConnected(int connected_socket, sockaddr_in addr);
};

class ClientTcpListener : public TcpListener
{
	virtual void OnClientConnected(int connected_socket, sockaddr_in addr);
};

// level
struct Level
{
	int				level_id;
	char			name[64];
	char			show_name[64];
	byte			type;
	std::string		description;
	byte			is_vip;
	byte			is_new;
	byte			is_gm;
};

// channel server
class ChannelServer
{
public:
	typedef std::set<Client*/*, ClientLess*/> ClientSet;

public:
	// constructor
	ChannelServer();

	// destructor
	~ChannelServer();

	// run
	int Run();

	// on status changed
	void OnStatusChanged();

	// get client
	Client* GetClient(uint uid);

	// get room free
	Room* GetRoomFree();

	// get room
	Room* GetRoom(uint id);
	
	Room* GetRoomByName(char* szRoomName);

	// notify room create
	void NotifyRoomCreate(Room & room);

	// noitfy room close
	void NotifyRoomClose(Room & room);

	// notify room client count changed
	void NotifyRoomClientCountChanged(Room & room);

	// notify room option changed
	void NotifyRoomOptionChanged(Room & room);

	// notify room host changed
	void NotifyRoomHostChanged(Room & room);

	// notify room state changed
	void NotifyRoomStateChanged(Room & room);

	// notify
	void Notify(void (Client::*func)());

	// notify
	template<class T>
	void Notify(void (Client::*func)(T&), T & t);

	// update
	void Update();

public:
	ObjectPool<Client> client_pool;
	ObjectPool<Room> room_pool;

	ClientUdpListener client_listener;
	ClientTcpListener client_tcp_listener;
	ProxyConnection proxy_connection;
	
	std::string m_strDomainName;

	// filter kelywords
	std::vector<std::string> filter_keywords;
	
	// banned userid
	std::set<uint> banned_userid;
	
	// level list
	std::vector<Level> level_list;

	// online client set
	ClientSet online_client_set;

public:
	byte is_tcp;

	byte novice;
	
	int channel_id;
	int server_id;
	short room_count;
	double update_time;

	char game_server_path[256];

	sockaddr_in proxy_addr;

	byte cServerType;
};

extern ChannelServer server;
