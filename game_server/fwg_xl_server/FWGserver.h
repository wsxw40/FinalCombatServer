#pragma once

struct ServerListener : public TcpListener
{
	virtual void OnClientConnected(int socket, sockaddr_in addr);
};

class FWGxlServer
{
public:
	// constructor
	FWGxlServer();

	// destructor
	~FWGxlServer();

	// run
	int Run();
	
public:
	// update
	void OnUpdate(double frame_time);

public:
	// 玩家进入
	int NoticeXLFWGUserLogin( std::string strGameId, std::string strServerId, uint dwRoleId, std::string strCustomerId);

	// 玩家离开
	int NoticeXLFWGUserLogout( std::string strGameId, std::string strServerId, uint dwRoleId, std::string strCustomerId );

public:
	ServerListener listener;
	FWGXLConnection m_oFWGXLConnection;
	ObjectPool<ServerConnection> connection_pool;
	
	FWGXLAckPool m_oFWGXLAckPool;
};

extern FWGxlServer server;

