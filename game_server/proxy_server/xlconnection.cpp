#include "pch.h"
#include "string.h"

// constructor
XLConnection::XLConnection()
{
	stream = this;
	connection = this;
	memset(login_conmand,0,sizeof(login_conmand));
	strcpy(login_conmand,"LOGIN");
	random_id = rand() % 65536;
}

// on connected
void XLConnection::OnConnected()
{
	//log_write_sys(LOG_INFO, "xl server connected: %s:%d", inet_ntoa(addr.sin_addr), ntohs(addr.sin_port));
}

static void ReconnectXL(XLConnection * conn)
{
	conn->Connect(conn->addr);
}

// on disconnected
void XLConnection::OnDisconnected()
{
	for (__gnu_cxx::hash_map<unsigned short,uint>::iterator itr = clientconnection_map.begin(); 
		itr != clientconnection_map.end(); itr++)
	{
		ClientConnection *client = server.GetClient(itr->second);
		if (client)
			client->OnLoginFailed("server_timeout");
	}
	clientconnection_map.clear();

	if (!connecting)
	{
		//log_write_sys(LOG_INFO, "xl server disconnected: %s:%d.", inet_ntoa(addr.sin_addr), ntohs(addr.sin_port));
	}

	Event::AddTimer((EventHandler)&ReconnectXL, this, 1);
}

// on message
void XLConnection::OnMessage()
{
	log_write(LOG_DEBUG3, "xl message.");
	
	OnResponseRealNameAuth();
}

// response real name auth.
void XLConnection::OnResponseRealNameAuth()
{
	char returncode[16] = {0};
	unsigned short random_id = 0; 

	char account[21] = {0};

	

	ReadString(returncode,16,4);
	ReadShort(random_id);

	ReadString(account,21,20);

	__gnu_cxx::hash_map<unsigned short,uint>::iterator itr = clientconnection_map.find(random_id);
	if(itr == clientconnection_map.end())
	{
		log_write(LOG_DEBUG3, "OnResponseRealNameAuth == 0, %d", random_id);
		return;
	}
	
	uint uid = itr->second;
	
	clientconnection_map.erase(random_id);

	if(strcmp(returncode, "0000") == 0)
	{	
		log_write(LOG_DEBUG3, "returncode success %d", uid);
		ClientConnection * client = server.GetClient(uid);
		if(client)
		{
			memcpy(client->xlinfo.account, account, sizeof(account));

			ReadByte(client->xlinfo.fcmflag);
			ReadByte(client->xlinfo.mbkflag);

			ReadString(client->xlinfo.xlaccount_level, 3, 2);
			ReadString(client->xlinfo.xlaccount_time_yyddmm, 11, 10);
			ReadString(client->xlinfo.xlaccount_lefttime, 6, 5);

			ReadString(client->xlinfo.xl_vip_account_level, 3, 2);
			ReadString(client->xlinfo.xl_title_account_level, 3, 2);

			ReadByte(client->xlinfo.xl_gender);
			ReadInt(client->xlinfo.xl_point_count);
			ReadInt(client->xlinfo.xl_exp);
			ReadString(client->xlinfo.xl_bar_level, 3, 2);
			ReadShort(client->xlinfo.account_type);

			client->OnResponseXLConnection(account);
			
			//xl_account_info  ttdcf0000000010      0  0                         0   1   0  0  0
			log_write(LOG_INFO, "xl_account_info  %s %d  %d  %s  %s  %s  %s  %s  %d  %d  %d %s %d", account, client->xlinfo.fcmflag, client->xlinfo.mbkflag, 
																							client->xlinfo.xlaccount_level, client->xlinfo.xlaccount_time_yyddmm,
																							client->xlinfo.xlaccount_lefttime, client->xlinfo.xl_vip_account_level, 
																							client->xlinfo.xl_title_account_level,
																							client->xlinfo.xl_gender, client->xlinfo.xl_point_count, client->xlinfo.xl_exp,
																							client->xlinfo.xl_bar_level, client->xlinfo.account_type);

			log_write(LOG_DEBUG3, "xl_fcm flag %d", client->xlinfo.fcmflag);
			log_write(LOG_DEBUG3, "xl_bar flag %s", client->xlinfo.xl_bar_level);
			log_write(LOG_DEBUG3, "account_type %d", client->xlinfo.account_type);
		}
		else
		{
			log_write(LOG_DEBUG1, "client not found");
			//client->Disconnect();
		}
	}

	log_write(LOG_DEBUG3, "OnResponseRealNameAuth1 %s           %d             %s", returncode,random_id,account);
}



///xl fcm
XLFCMConnection::XLFCMConnection()
{
	stream = this;
	connection = this;
	random_id = 10;
}


// on connected
void XLFCMConnection::OnConnected()
{
	log_write_sys(LOG_INFO, "xl fcm server connected: %s:%d", inet_ntoa(addr.sin_addr), ntohs(addr.sin_port));
	write_position = NULL;
}

static void ReconnectXL_FCM(XLFCMConnection * conn)
{
	conn->Connect(conn->addr);
}

// on disconnected
void XLFCMConnection::OnDisconnected()
{
	if (!connecting)
	{
		log_write_sys(LOG_INFO, "xl fcm server disconnected: %s:%d.", inet_ntoa(addr.sin_addr), ntohs(addr.sin_port));
	}

	Event::AddTimer((EventHandler)&ReconnectXL_FCM, this, 5);
}

// on message
void XLFCMConnection::OnMessage()
{
	log_write(LOG_DEBUG3, "xl fcm message.");

	OnResponseHeatBeat();
}

void XLFCMConnection::RequestHeatBeat(ClientConnection* client, bool online)
{
	if(!client)
	{
		log_write(LOG_DEBUG1, "xl fcm client is not found");
		return;
	}

	log_write(LOG_DEBUG1, "xl fcm heatbeat request uid: %s cid: %s", client->user_name, client->character_name);

	client->fcm_refresh_time = 0.f;
	
	BeginWrite();
	WriteInt(1001);
	unsigned int random_id = GetRandomID();
	clientconnection_map[random_id] = client->uid;
	WriteInt(random_id);
	WriteInt(10001);
	WriteString("00045");
	WriteByte(1);
	WriteString(client->user_name);
	WriteByte(online ? 1 : 0);
	EndWrite();
}

bool XLFCMConnection::OnResponseHeatBeat()
{
	int version = 0;
	unsigned int random_id = 0;
	int command = 0;
	
	ReadInt(version);
	ReadInt(random_id);
	
	log_write(LOG_DEBUG3, "xl fcm OnResponseHeatBeat %d, %d", version, random_id);

	uint uid = clientconnection_map[random_id];

	if(uid == 0)
	{
		log_write(LOG_DEBUG3, "xl fcm OnResponseHeatBeat == 0, %d", random_id);
		return false;

	}
	clientconnection_map.erase(random_id);

	ReadInt(command);

	if(command == 20001)
	{
		byte issuccess = 0;
		ReadByte(issuccess);
		if(issuccess == 0)
		{
			int left_time;
			ReadInt(left_time);
			
			log_write(LOG_DEBUG1, " fcm returncode success %d %d", uid, left_time);
			ClientConnection * client = server.GetClient(uid);
			if(client)
			{
				log_write(LOG_DEBUG3, " fcm success");
				client->UpdateFCMTime(left_time);
			}
		}
		else
		{
			ClientConnection * client = server.GetClient(uid);
			if(client)
			{
				log_write(LOG_DEBUG1, " fcm returncode failed %s", client->user_name);
				client->Disconnect();
			}
		}
		
	}
	else
	{
		log_write(LOG_DEBUG1, "xl fcm command error");
	}

	return true;
}

