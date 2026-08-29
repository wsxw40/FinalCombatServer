#include "pch.h"

static _FUNC_S_REC pApexSendToApexProxy = NULL;

static long ApexKillUser(signed int nId, int Action)
{
	server.apex_reqpool.AddRequest(nId, Action);
	
	log_write(LOG_DEBUG3, "ApexKillUser(), nId : %d", nId);
	
	return 0;
}

static long ApexSendToGameClient(signed int nId, const char * pBuffer, int nLen)
{
	server.apex_reqpool.AddRequest(nId, pBuffer, nLen);
	
	//log_write(LOG_DEBUG4, "ApexSendToGameClient(), nId : %d, nLen : %d", nId, nLen);
	
	return 0;
}

static long ApexSendToApexProxy(char cMsgId, signed int nId, const char * pBuffer, int nLen)
{
	if (pApexSendToApexProxy)
	{
		//log_write(LOG_DEBUG4, "ApexSendToApexProxy(cMsgId : %c, nId : %d, nLen : %d)", cMsgId, nId, nLen);
		
		return pApexSendToApexProxy(cMsgId, nId, pBuffer, nLen);
	}
	
	log_write(LOG_DEBUG3, "ApexSendToApexProxy(cMsgId : %c, nId : %d, nLen : %d), pApexSendToApexProxy is null", cMsgId, nId, nLen);
	
	return 0;
}


// 启动ApexProxy
static int StartApexProxy()
{
	pApexSendToApexProxy = NULL;
	
	CHSStart(&ApexSendToGameClient, pApexSendToApexProxy);
	CHSSetFunc((void*)&ApexKillUser, FLAG_KILLUSER);
	
	return 1;
}

// 停止ApexProxy
static int StopApexProxy()
{
	CHSEnd();
	
	pApexSendToApexProxy = NULL;
	
	return 1;
}

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

ApexServer server;

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
ApexServer::ApexServer()
{
	set_sockaddr(listener.addr, NULL, 9003);
}

ApexServer::~ApexServer()
{
}

// 玩家进入
int ApexServer::NoticeApexProxy_UserLogin(uint user_id, const char *user_name, uint user_ip)
{
	if (user_name)
	{
		#define ClientIpFlag  0x01
		
		struct nUserIp_st 
		{
			char	Cmd_UserIpFlag;
			uint	nClientIp;
		}__attribute__ ((packed));
		
		nUserIp_st ip;
		ip.Cmd_UserIpFlag = ClientIpFlag;
		ip.nClientIp = user_ip;
		
		ApexSendToApexProxy('L', user_id, user_name, strlen(user_name));
		
		ApexSendToApexProxy('S', user_id, (const char *)&ip, sizeof(ip));
		
		return 1;
	}
	
	return 0;
}

// 玩家离开
int ApexServer::NoticeApexProxy_UserLogout(uint user_id, const char *user_name)
{
	if (user_name)
	{
		ApexSendToApexProxy('G', user_id, user_name, strlen(user_name));
		
		return 1;
	}
	
	return 0;
}

// 有数据到达
int ApexServer::NoticeApexProxy_UserData(uint user_id, const char *pBuf, int nBufLen)
{
	if (pBuf && nBufLen > 0)
	{
		ApexSendToApexProxy('T', user_id, pBuf, nBufLen);
		
		return 1;
	}
	
	return 0;
}

// run
int ApexServer::Run()
{
	// initialize apex apexproxy
	int ret = StartApexProxy();
	if (ret < 0)
	{
		log_write_sys(LOG_ERROR, "start apexproxy failed.");
		return 1;
	}

	// initialize connection pool.
	if (!connection_pool.Initialize(appcfg.max_proxy_count))
	{
		log_write_sys(LOG_ERROR, "cannot initialize connection pool.");
		return 1;
	}
	
	// initialize ApexReqPool.
	if (!apex_reqpool.Initialize(appcfg.max_apexreqpool_size))
	{
		log_write_sys(LOG_ERROR, "cannot initialize ApexReqPool pool.");
		return 1;
	}

	// initialize listener
	if (!listener.Initialize())
		return 1;
	
	// UpdateApexServer
	UpdateApexServer();

	// dispatch events.
	Event::Dispatch();

	// disconnect all connections
	for (ServerConnection * c = connection_pool.Begin(); c < connection_pool.End(); c++)
		c->Disconnect();
		
	// stop apex apexproxy
	StopApexProxy();

	// terminate event system.
	Event::Terminate();

	return 0;
}

void ApexServer::OnUpdate(double frame_time)
{
	std::list<ApexReq*> req_list;
	
	apex_reqpool.GetRequest(req_list);
	
	for (ServerConnection * conn = server.connection_pool.Begin(); conn < server.connection_pool.End(); conn++)
	{
		if (conn && conn->IsConnected())
		{
			for (std::list<ApexReq*>::const_iterator itr = req_list.begin(); itr != req_list.end(); itr++)
			{
				switch ((*itr)->type)
				{
					case ApexReq::Type_Action:
						conn->NotifyKillUser((*itr)->user_id, (*itr)->req_data.Action);
						break;
					case ApexReq::Type_Send:
						conn->NotifyServerData((*itr)->user_id, (*itr)->req_data.pBuf, (*itr)->req_data.nBufLen);
						break;
					default:
						log_write(LOG_NOTICE, "Unknow ApexReq type : %d", (*itr)->type);
						break;
				}
			}
		}
	}
	
	if (log_get_output_level() >= LOG_DEBUG5 && req_list.size() > 0)
		log_write(LOG_DEBUG5, "req_list.size() : %d", req_list.size());
	
	apex_reqpool.RemoveRequest(req_list);
}
