#pragma once

struct ServerListener : public TcpListener
{
	virtual void OnClientConnected(int socket, sockaddr_in addr);
};

class ApexServer
{
public:
	// constructor
	ApexServer();

	// destructor
	~ApexServer();

	// run
	int Run();
	
public:
	// update
	void OnUpdate(double frame_time);

public:
	// 玩家进入
	int NoticeApexProxy_UserLogin(uint user_id, const char *user_name, uint user_ip);

	// 玩家离开
	int NoticeApexProxy_UserLogout(uint user_id, const char *user_name);

	// 有数据到达
	int NoticeApexProxy_UserData(uint user_id, const char *pBuf, int nBufLen);

public:
	ServerListener listener;
	ObjectPool<ServerConnection> connection_pool;
	
	ApexReqPool apex_reqpool;
};

extern ApexServer server;
