#include "pch.h"

// client listener
void ClientUdpListener::OnClientConnected(int connected_socket, sockaddr_in addr)
{
	if (server.is_tcp)
	{
		close(connected_socket);
		return;
	}
	
	Client * client = server.client_pool.Allocate();
	if (client == NULL)
	{
		close(connected_socket);
		return;
	}

	client->client_address = addr;
	client->tcp_connection.stream = NULL;
	client->udp_connection.stream = client;
	client->connection = &client->udp_connection;
	client->udp_connection.Connect(connected_socket, addr);
}

// client listener
void ClientTcpListener::OnClientConnected(int connected_socket, sockaddr_in addr)
{
	if (!server.is_tcp)
	{
		close(connected_socket);
		return;
	}
	
	Client * client = server.client_pool.Allocate();
	if (client == NULL)
	{
		close(connected_socket);
		return;
	}

	client->client_address = addr;
	client->tcp_connection.stream = client;
	client->udp_connection.stream = NULL;
	client->connection = &client->tcp_connection;
	client->tcp_connection.Connect(connected_socket, addr);
}

// -----------------------------------------------------------------
// class channel server
// -----------------------------------------------------------------
ChannelServer server;

ChannelServer::ChannelServer()
	: is_tcp(0)
	, novice(0)
	, room_count(0)
	, channel_id(1)
	, server_id(1)
	, update_time(0)
	, cServerType(0)
{
	snprintf(game_server_path, sizeof(game_server_path), "%s/gameserver", getexepath());
	set_sockaddr(proxy_addr, "127.0.0.1", 9001);
	set_sockaddr(client_listener.addr, "192.168.1.16", 9011);
}

ChannelServer::~ChannelServer()
{
}

// on statuc change
void ChannelServer::OnStatusChanged()
{
	proxy_connection.SyncData();
}

// get client
Client* ChannelServer::GetClient(uint uid)
{
	return client_pool.Get(uid);
}

// get room
Room* ChannelServer::GetRoom(uint id)
{
	if (id > 0 && id < room_pool.Size())
		return room_pool + id;
	else
		return NULL;
}

// room free
Room* ChannelServer::GetRoomFree()
{
	for (Room * room = room_pool.Begin() + 1; room < room_pool.End(); room ++)
	{
		if (!room->IsReady())
			return room;
	}

	return NULL;
}

Room* ChannelServer::GetRoomByName( char* szRoomName )
{
	for (Room * room = room_pool.Begin() + 1; room < room_pool.End(); room ++)
	{
		if (strcmp(szRoomName, room->name) == 0)
		{
			return room;
		}
	}

	return NULL;
}

// notify room create
void ChannelServer::NotifyRoomCreate(Room & room)
{
	Notify(&Client::NotifyRoomCreate, room);
}

// noitfy room close
void ChannelServer::NotifyRoomClose(Room & room)
{
	Notify(&Client::NotifyRoomClose, room);
}

// notify room option changed
void ChannelServer::NotifyRoomOptionChanged(Room & room)
{
	Notify(&Client::NotifyRoomChangeOption, room);
}

// notify room host changed
void ChannelServer::NotifyRoomHostChanged(Room & room)
{
	Notify(&Client::NotifyRoomHostChanged, room);
}

// notify room client count changed
void ChannelServer::NotifyRoomClientCountChanged(Room & room)
{
	Notify(&Client::NotifyRoomClientCountChanged, room);	
}

// notify room state changed
void ChannelServer::NotifyRoomStateChanged(Room & room)
{
	Notify(&Client::NotifyRoomStateChanged, room);
}

// notify
void ChannelServer::Notify(void (Client::*func)())
{
	for (Client * client = client_pool.Begin(); client < client_pool.End(); client++)
	{
		if (client->state == Client::CS_InChannel)
			(client->*func)();
	}
}

// notify
template<class T>
void ChannelServer::Notify(void (Client::*func)(T&), T & t)
{
	for (Client * client = client_pool.Begin(); client < client_pool.End(); client++)
	{
		if (client && client->state == Client::CS_InChannel)
			(client->*func)(t);
	}
}

static void UpdateCallback(void * data)
{
	Event::AddTimer(&UpdateCallback, data, 0.01);

	server.Update();

	// send messages
	for (Client * client = server.client_pool.Begin(); client < server.client_pool.End(); client++)
	{
		client->tcp_connection.SendMessages();
		client->udp_connection.SendMessages();
	}
}

// update
void ChannelServer::Update()
{
	double time = Event::GetTime();
	double frame_time = time - update_time;
	update_time = time;

	// update room
	for (Room * room = room_pool.Begin(); room < room_pool.End(); room++)
	{
		if (room->state > Room::RS_Idle)
		{
			room->OnUpdate(frame_time);
		}
	}

	// update client
	for (Client * client = client_pool.Begin(); client < client_pool.End(); client++)
	{
		if (client->state >= Client::CS_InChannel)
		{
			client->OnUpdate(frame_time);
		}
	}
	
	if (server.proxy_connection.IsConnected())
		server.proxy_connection.UpdateChannelStatus();
}

// run
int ChannelServer::Run()
{
	log_write_sys(LOG_INFO, "channel server initialize, address=%s:%d, proxy=%s:%d.",
			inet_ntoa(client_listener.addr.sin_addr), ntohs(client_listener.addr.sin_port),
			inet_ntoa(proxy_addr.sin_addr), ntohs(proxy_addr.sin_port));

	// create clients
	if (!client_pool.Initialize(appcfg.max_client_count + 100))
	{
		log_write_sys(LOG_ERROR, "not enough memory!");
		return 1;
	}

	// create room
	if (!room_pool.Initialize(appcfg.max_room_count))
	{
		log_write_sys(LOG_ERROR, "not enough memory!");
		return 1;
	}

	for (Room* room = room_pool.Begin(); room < room_pool.End(); room++)
	{
		room->id = room - room_pool.Begin();
	}

	// initialize client tcp listener
	client_tcp_listener.addr = client_listener.addr;
	if (!client_tcp_listener.Initialize())
		return 1;

	// initialize client udp listener
	if (!client_listener.Initialize())
		return 1;

	// connect server
	log_write_sys(LOG_INFO, "connecting proxyserver: %s", sockaddr_ntoa(proxy_addr));
	proxy_connection.Connect(proxy_addr);

	// update task
	UpdateCallback(NULL);

	// dispatch events
	Event::Dispatch();

	// free clients
	client_pool.Terminate();

	// free rooms
	room_pool.Terminate();

	// terminate events
	Event::Terminate();

	return 0;
}

