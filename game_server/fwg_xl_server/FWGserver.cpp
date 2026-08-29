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

FWGxlServer server;

// update
static void UpdateApexServer(void * data = NULL)
{
	static double update_time = Event::GetTime();
	double time = Event::GetTime();
	double frame_time = time - update_time;
	update_time = time;
	
	server.OnUpdate(frame_time);
	
	Event::AddTimer(&UpdateApexServer, data, 0.05);
}

// constructor
FWGxlServer::FWGxlServer()
{
	set_sockaddr(listener.addr, NULL, 9003);
	if(!set_sockaddr(gethostbyname("antiserver.youxi.xunlei.com"), 12590, m_oFWGXLConnection.GetSockaddr()))
	{
		log_write_sys(LOG_ERROR, "connot initialize ApexXLConnection sockaddr.");
	}
	
	//set_sockaddr(m_oApexXLConnection.addr, "10.10.12.12", 9999);
	
	/*
	{
		char ipstr[16] ={0};
		struct hostent *answer;

		answer = gethostbyname("antiserver.youxi.xunlei.com");
		if (answer != NULL) 
		{
			for (int i = 0; (answer->h_addr_list)[i] != NULL; i++) 
			{
				inet_ntop(AF_INET, (answer->h_addr_list)[i], ipstr, 16);
			}
			set_sockaddr(m_oApexXLConnection.addr, ipstr, 12580);
		}
	}
	*/
}

FWGxlServer::~FWGxlServer()
{
}

// 玩家进入
int FWGxlServer::NoticeXLFWGUserLogin( std::string strGameId, std::string strServerId, uint dwRoleId, std::string strCustomerId )
{
	// 这里要给迅雷的反外挂发消息

	m_oFWGXLConnection.ClientLogIn(strGameId, strServerId, dwRoleId, strCustomerId);
	
	return 1;
}

// 玩家离开
int FWGxlServer::NoticeXLFWGUserLogout( std::string strGameId, std::string strServerId, uint dwRoleId, std::string strCustomerId )
{
	//给迅雷发消息

	m_oFWGXLConnection.ClientLogOut(strGameId, strServerId, dwRoleId, strCustomerId);
	return 1;
}

// run
int FWGxlServer::Run()
{
	// initialize connection pool.
	if (!connection_pool.Initialize(appcfg.max_proxy_count))
	{
		log_write_sys(LOG_ERROR, "cannot initialize connection pool.");
		return 21;
	}
	
	// initialize ApexReqPool.
	if (!m_oFWGXLAckPool.Initialize(appcfg.max_apexreqpool_size))
	{
		log_write_sys(LOG_ERROR, "cannot initialize ApexReqPool pool.");
		return 31;
	}

	// initialize listener
	if (!listener.Initialize())
		return 41;
	
	if(m_oFWGXLConnection.connecting)
	{
		return 51;
	}
	m_oFWGXLConnection.Connect(m_oFWGXLConnection.addr);
	if(!m_oFWGXLConnection.connecting)
	{
		return 71;
	}

	// UpdateApexServer
	UpdateApexServer();

	// dispatch events.
	Event::Dispatch();

	// disconnect all connections
	for (ServerConnection * c = connection_pool.Begin(); c < connection_pool.End(); c++)
		c->Disconnect();
		
	// terminate event system.
	Event::Terminate();

	return 0;
}

void FWGxlServer::OnUpdate(double frame_time)
{
	//m_oApexXLConnection.ClientLogIn(45,123,321,123456789);
	std::list<ApexXLAck*> listAck;
	m_oFWGXLAckPool.GetAck(listAck);
	for(std::list<ApexXLAck*>::const_iterator it = listAck.begin(); it != listAck.end(); ++it)
	{
		Json::Reader jReader;
		Json::Value jValue;
		if(jReader.parse((*it)->ack_data.pBuf, jValue))
		{
			Json::Value jCmd = jValue.get("cmdid", jValue);
			std::string strCmd = "";
			if(jCmd.isString())
			{
				strCmd = jCmd.asString();
				if(strCmd == "201")
				{
				}
				else if(strCmd == "202")
				{
				}
			}
		}
		else
		{}
	}
	m_oFWGXLAckPool.RemoveAck(listAck);
}

