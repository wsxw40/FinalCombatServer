#include "pch.h"

//-----------------------------------------------------------------
// apex Server 
//-----------------------------------------------------------------
void ServerListener::OnClientConnected(int socket, sockaddr_in addr)
{
	ServerConnection * conn = server.connection_pool.Allocate();

	if (conn == NULL)
	{
		log_write_sys(LOG_INFO, "maximum connected reachd, client dropped : %s", inet_ntoa(addr.sin_addr));
		close(socket);
		return;
	}

	conn->client_ip = addr.sin_addr;
	conn->Connect(socket, addr);
}

LoginServer server;

// update
static void UpdateLoginServer(void * data = NULL)
{
	static double update_time = Event::GetTime();
	double time = Event::GetTime();
	double frame_time = time - update_time;
	update_time = time;
	
	server.OnUpdate(frame_time);
	
	Event::AddTimer(&UpdateLoginServer, data, 0.05);
}

// constructor
LoginServer::LoginServer()
{
	set_sockaddr(listener.addr, NULL, 19004);
}

LoginServer::~LoginServer()
{
}

// run
int LoginServer::Run()
{
	// gen_tabs
	gen_tabs();
	
	// initialize connection pool.
	if (!connection_pool.Initialize(appcfg.max_proxy_count))
	{
		log_write_sys(LOG_ERROR, "cannot initialize connection pool.");
		return 1;
	}

	// initialize listener
	if (!listener.Initialize())
		return 1;
	
	// UpdateLoginServer
	UpdateLoginServer();

	// dispatch events.
	Event::Dispatch();

	// disconnect all connections
	for (ServerConnection * c = connection_pool.Begin(); c < connection_pool.End(); c++)
		c->Disconnect();
		
	// terminate event system.
	Event::Terminate();

	return 0;
}

void LoginServer::OnUpdate(double frame_time)
{
}
