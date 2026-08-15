#pragma once

struct ServerListener : public TcpListener
{
	virtual void OnClientConnected(int socket, sockaddr_in addr);
};

class LoginServer
{
public:
	// constructor
	LoginServer();

	// destructor
	~LoginServer();

	// run
	int Run();
	
public:
	// update
	void OnUpdate(double frame_time);

public:
	ServerListener listener;
	ObjectPool<ServerConnection> connection_pool;
};

extern LoginServer server;
