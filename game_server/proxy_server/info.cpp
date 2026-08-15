#include "pch.h"
#include <stdarg.h>

enum EServerMessage
{
	SM_ResponseTextRPC,
	SM_ResponseBinaryRPC,
	SM_SendChat,
	SM_BroadcastProxyChat,
	SM_BroadcastChannelChat,
	SM_RequestStatus,
	SM_RequestOnlineInfo,
	SM_NotifyClient,
	SM_UpdateCharacterInfo,
	SM_BroadcastServerChat,
	
	SM_RequestKeywords,
	SM_RequestServerList,
	SM_RequestLevelList,
	SM_RequestNotice,
	SM_KickPlayer,
	SM_RequestSysConfig,
	SM_BroadcastLoopMsg,
	SM_SyncRPCQueue,
	SM_RequestBlackList,
	SM_UpdateBillBoardList,
	
	SM_LeagueGameInfo,
	SM_ResponseLeagueGameInfoTime,
};

enum EClientMessage
{
	CM_RequestTextRPC,
	CM_RequestBinaryRPC,
	CM_ResponseStatus,
	CM_ResponseOnlineInfo,
	CM_UpdateServerInfo,
	CM_CancelRPC,
	CM_SyncRPCQueue,
	CM_RequestLeagueGameInfo,
	CM_RequestLeagueGameInfoTime,
};

// -----------------------------------------------------------------
// class info connection
// -----------------------------------------------------------------
static void ReconnectInfoServer(InfoConnection * conn)
{
	conn->Connect(conn->addr);
}

InfoConnection::InfoConnection()
	:BinaryStream(appcfg.info_buffersize)
{
	connection = this;
	stream = this;
	rpc_queue_sync_time = 0;
	rpc_quene_disconnect_time = 0;
}

InfoConnection::~InfoConnection()
{
}

// on client connected
void InfoConnection::OnConnected()
{
	online = true;
	will_disconnect = false;
	
	// initialize rpc queue
	for (int i = 0; i < RpcQueue::kQueueCount; i++)
	{
		// initialize status
		rpc_status[i].queue_size = 0;
		rpc_status[i].request_count_total = 0;
		rpc_status[i].request_count_per_second = 0;
	}

	log_write_sys(LOG_INFO, "info server connected %s:%d.", inet_ntoa(addr.sin_addr), ntohs(addr.sin_port));
	server.OnInfoConnectionUp();

	// update server info.
	BeginWrite();
	WriteByte(CM_UpdateServerInfo);
	WriteInt(0); // queue_id
	WriteInt(0); // rpc_id
	WriteString(""); // url
	WriteInt(0); // userdata
	WriteString(inet_ntoa(server.client_listener.addr.sin_addr));
	WriteShort(ntohs(server.client_listener.addr.sin_port));
	EndWrite();

	for (int i = 0; i < RpcQueue::kQueueCount; i++)
		SyncRPCQueue(i);
}

void InfoConnection::OnDisconnected()
{
	if (online)
	{
		online = false;
		log_write_sys(LOG_INFO, "info server disconnected %s:%d.", inet_ntoa(addr.sin_addr), ntohs(addr.sin_port));
		server.OnInfoConnectionDown();
	}

	Event::AddTimer((EventHandler)&ReconnectInfoServer, this, 5);
}

// receive message
void InfoConnection::OnMessage()
{
	byte msg = 255;
	
	try
	{
		ReadByte(msg);

		switch (msg)
		{
		case SM_ResponseTextRPC:		OnResponseRPC(); break;
		case SM_ResponseBinaryRPC:		OnResponseRPC(); break;
		case SM_SendChat:				OnSendChat();	break;
		case SM_BroadcastProxyChat:		OnBroadcastProxyChat(); break;
		case SM_BroadcastServerChat:	OnBroadcastServerChat(); break;
		case SM_BroadcastChannelChat:	OnBroadcastChannelChat(); break;
		case SM_RequestStatus:			OnRequestStatus(); break;
		case SM_RequestOnlineInfo:		OnRequestOnlineInfo(); break;
		case SM_NotifyClient:			OnNotifyClient(); break;
		case SM_UpdateCharacterInfo:	OnUpdateCharacterInfo(); break;
		
		case SM_RequestKeywords:		OnRequestKeywords(); break;
		case SM_RequestServerList:		OnRequestServerList(); break;
		case SM_RequestLevelList:		OnRequestLevelList(); break;
		case SM_RequestNotice:			OnRequestNotice(); break;
		case SM_RequestBlackList:		OnRequestBlackList(); break;
		case SM_KickPlayer:				OnKickPlayer(); break;
		case SM_RequestSysConfig:		OnRequestSysConfig(); break;
		case SM_BroadcastLoopMsg:		OnBroadcastLoopMsg(); break;
		case SM_SyncRPCQueue:			OnSyncRPCQueue(); break;
		case SM_UpdateBillBoardList:	OnUpdateBillBoardList();break;
		case SM_LeagueGameInfo:	OnLeagueGameInfo(); break;
		}
	}
	catch (...)
	{
		will_disconnect = true;
		log_write(LOG_ERROR, "InfoConnection::OnMessage() exp. msg = %d, info=%s", msg, sockaddr_ntoa(addr));
	}
}


// on response rpc
void InfoConnection::OnResponseRPC()
{
	uint queue_id;
	uint queue_size;
	uint rpc_id;
	uint error;

	ReadInt(queue_id);
	ReadInt(queue_size);
	ReadInt(rpc_id);
	ReadInt(error);

	if (queue_id < 0 || queue_id >= RpcQueue::kQueueCount)
	{
		log_write(LOG_DEBUG1, "queue_id large than max: %d", queue_id);
		return;
	}


	rpc_status[queue_id].queue_size++;
	status.rpc[queue_id].num_responses_total++;

	RpcRequest * rpc = server.rpc.Get(queue_id, rpc_id);

	if (rpc)
	{
		double current_time = Event::GetTime();
		double response_time = current_time - rpc->request_time;
		double process_time = current_time - rpc->process_time;

		status.rpc[queue_id].response_time_total += response_time;
		status.rpc[queue_id].process_time_total += process_time;

		if (process_time > status.rpc[queue_id].process_time_max_current)
			status.rpc[queue_id].process_time_max_current = process_time;

		if (process_time < status.rpc[queue_id].process_time_min_current)
			status.rpc[queue_id].process_time_min_current = process_time;

		log_write(LOG_DEBUG4, "rpc ret: queue=%d, id=%x, result=%d, size=%d, time=%g, info=%s",
				queue_id, rpc_id, error, read_end - read_position, response_time * 1000, sockaddr_ntoa(addr));

		rpc->error = error;
		bool result = rpc->OnResponse(read_position, read_end - read_position);

		server.rpc.Free(queue_id, rpc_id);

		if (!result)
		{
			log_write(LOG_DEBUG1, "rpc error: queue=%d, id=%x, result=%d, size=%d, url=%s, info=%s", queue_id, rpc_id, error,
					read_end - read_position, rpc->url, sockaddr_ntoa(addr));
			throw;
		}
	}
	else
	{
		log_write(LOG_DEBUG4, "rpc ret for unknown rpc: queue=%d, id=%x",
			queue_id, rpc_id);
	}


	// dispatch requests
	//server.rpc.DispatchRequests(queue_id);
	server.rpc.DispatchRequests();
}

// send chat
void InfoConnection::OnSendChat()
{
	char receiver[character_name_length];
	char to[character_name_length];
	char name[character_name_length];
	char msg[chat_length];
	ReadString(receiver, sizeof(receiver));
	ReadString(to, sizeof(to));
	ReadString(name, sizeof(name));
	ReadString(msg, sizeof(msg));

	ClientConnection * c = server.GetClientByName(receiver);
	if (c)
	{
		c->NotifyChat(to, name, msg);
	}
}

// notify chat
void InfoConnection::OnBroadcastProxyChat()
{
	char to[character_name_length];
	char name[character_name_length];
	char msg[chat_length];
	ReadString(to, sizeof(to));
	ReadString(name, sizeof(name));
	ReadString(msg, sizeof(msg));

	// borading message to online users
	for (ClientConnection * c = server.client_pool.Begin(); c < server.client_pool.End(); c++)
	{
		if (c->IsOnline())
		{
			c->NotifyChat(to, name, msg);
		}
	}

}

// notify chat
void InfoConnection::OnBroadcastServerChat()
{
	int server_id;

	char to[character_name_length];
	char name[character_name_length];
	char msg[chat_length];

	ReadInt(server_id);
	ReadString(to, sizeof(to));
	ReadString(name, sizeof(name));
	ReadString(msg, sizeof(msg));

	// borading message to server online users
	for (ClientConnection * c = server.client_pool.Begin(); c < server.client_pool.End(); c++)
	{
		if (c->IsOnline() && c->character_server_id == server_id)
		{
			c->NotifyChat(to, name, msg);
		}
	}

}

void InfoConnection::OnBroadcastChannelChat()
{
	int server_id;
	int channel_id;
	char to[character_name_length];
	char name[character_name_length];
	char msg[chat_length];

	ReadInt(server_id);
	ReadInt(channel_id);
	ReadString(to, sizeof(to));
	ReadString(name, sizeof(name));
	ReadString(msg, sizeof(msg));

	ChannelConnection * channel = server.GetChannel(server_id, channel_id);
	if (channel)
	{
		channel->BroadcastChatMessage(to, name, msg);
	}
}

void InfoConnection::WriteResponseHeader()
{
	uint queue_id;
	uint queue_size;
	uint rpc_id;
	uint userdata_size;
	char url[256];

	ReadInt(queue_id);
	ReadInt(queue_size);
	ReadInt(rpc_id);
	ReadString(url, sizeof(url));
	ReadInt(userdata_size);

	WriteInt(queue_id);
	WriteInt(rpc_id);
	WriteString(url);
	WriteInt(userdata_size);
	if (userdata_size > 0)
		Write(ReadData(userdata_size), userdata_size);
}

struct InfoStatusWriter : StatusWriter
{
	InfoStatusWriter(BinaryStream * s) : stream(s)
	{
		write_count = (uint*)(stream->write_position);
		stream->WriteInt(0);
	}

	void Write(const char * key, const char * format, ...)
	{
		++(*write_count);

		stream->WriteString(key);
		va_list args;
		va_start(args, format);
		stream->WriteStringArgs(format, args);
		va_end(args);
	}

private:
	uint * write_count;
	BinaryStream * stream;
};

void InfoConnection::OnRequestStatus()
{
	BeginWrite();
	WriteByte(CM_ResponseStatus);
	WriteResponseHeader();
	{
		InfoStatusWriter writer(this);
		status.Write(writer);
	}
	EndWrite();
}

void InfoConnection::OnRequestOnlineInfo()
{
	BeginWrite();
	WriteByte(CM_ResponseOnlineInfo);
	WriteResponseHeader();
	WriteInt(status.active_connections);
	WriteInt(status.online_characters);

	for (std::map<uint, ServerInfo>::iterator itr1 = server.servers.begin(); 
		itr1 != server.servers.end(); itr1++)
	{
		ServerInfo & s = itr1->second;
		
		for (std::map<uint, ChannelInfo>::iterator itr2 = s.channels.begin(); 
			itr2 != s.channels.end(); itr2++)
		{
			ChannelConnection * channel = itr2->second.connection;

			if (channel && channel->IsReady())
			{
				WriteInt(channel->server_id);
				WriteInt(channel->channel_id);
				WriteInt(channel->client_count);
			}
		}
	}
	WriteInt(0);
	WriteInt(0);
	WriteInt(0);
	EndWrite();
}

void InfoConnection::OnNotifyClient()
{
	char client_name[character_name_length];
	uint data_size;

	ReadString(client_name, sizeof(client_name));
	ReadInt(data_size);

	if (client_name[0])
	{
		ClientConnection * client = server.GetClientByName(client_name);

		if (client)
		{
			client->NotifyRPCMessage(data_size, read_position);
		}
	}
	else
	{
		for (ClientConnection * client = server.client_pool.Begin(); client < server.client_pool.End(); client++)
		{
			if (client->IsOnline())
			{
				client->NotifyRPCMessage(data_size, read_position);
			}
		}
	}
}

// request rpc
void InfoConnection::RequestRPC(uint queue_id, RpcRequest * request)
{
	byte command = 0;
	switch (request->type)
	{
	case RpcRequest::kBinary:	command = CM_RequestBinaryRPC; break;
	case RpcRequest::kText:		command = CM_RequestTextRPC; break;
	default: return;
	}

	BeginWrite();
	WriteByte(command);
	WriteInt(queue_id);
	WriteInt(request->uid);
	WriteString(request->url);
	WriteInt(0); // userdata, not used anymore.
	Write(request->send_buffer + request->data_offset, request->data_size);
	EndWrite();

	log_write(LOG_DEBUG1, "send rpc request: id=%x, queue=%d, info=%s url : %s",
			request->uid, queue_id, sockaddr_ntoa(addr), request->url);

	rpc_status[queue_id].queue_size--;
	rpc_status[queue_id].request_count_total++;
}

// cancel rpc
void InfoConnection::CancelRPC(uint queue_id, uint rpc_id)
{
	// don't need cancel anymore.
	return;

	BeginWrite();
	WriteByte(CM_CancelRPC);
	WriteInt(queue_id);
	WriteInt(rpc_id);
	WriteString("");
	WriteInt(0);
	EndWrite();
}

// sync queue
void InfoConnection::SyncRPCQueue(uint queue_id)
{
	if (queue_id < RpcQueue::kQueueCount)
	{
		BeginWrite();
		WriteByte(CM_SyncRPCQueue);
		WriteInt(queue_id);
		WriteInt(rpc_status[queue_id].queue_size);
		EndWrite();
	}
}

// on update character info
void InfoConnection::OnUpdateCharacterInfo()
{
	char client_name[character_name_length];

	ReadString(client_name, sizeof(client_name));

	ClientConnection * client = server.GetClientByName(client_name);

	if (client)
	{
		ReadInt(client->character_id);
		ReadInt(client->character_level);
		ReadInt(client->character_exp);
		
		ReadByte(client->is_vip);
		ReadByte(client->business_card);
		ReadString(client->head_icon, sizeof(client->head_icon));
		ReadByte(client->is_PlayerCheckToday);
		ReadInt(client->top);
		ReadInt(client->fightnum);
		ReadFloat(client->win_rate);

		uint character_group_id_old = client->character_group_id;
		ReadInt(client->character_group_id);
		if (character_group_id_old != client->character_group_id)
		{
			client->NotifyBattleGroupLeave(client->joined_battlegroup);
		}
		
		char character_group[group_name_length];
		ReadString(character_group, sizeof(character_group));
		if (strcmp(character_group, client->character_group) != 0)
		{
			if (client->character_group[0])
			{
				ClientGroupMap::iterator it = server.online_client_group_map.lower_bound(client->character_group);
				ClientGroupMap::iterator end = server.online_client_group_map.upper_bound(client->character_group);

				while (it != end)
				{
					if (it->second == client)
					{
						server.online_client_group_map.erase(it);
						break;
					}
					++it;
				}
			}

			strcpy(client->character_group, character_group);

			if (character_group[0])
				server.online_client_group_map.insert(ClientGroupMap::value_type(client->character_group, client));
		}
		ReadInt(client->character_group_level);
		ReadInt(client->matching_hege_level);
		ReadInt(client->matching_fighting_level);
		if (client->matching_hege_level < 1)
		{
			client->matching_hege_level = 1;
		}
		if (client->matching_fighting_level < 1)
		{
			client->matching_fighting_level = 1;
		}
		
		//log_write(LOG_DEBUG1, "%s, %s, matching level : %d, client name : %s", __FILE__, __FUNCTION__, client->matching_level, client->character_name);

		client->OnCharacterInfoChanged();
	}
}

// on request keywords
void InfoConnection::OnRequestKeywords()
{
	server.UpdateKeywords();
}

// on request serverlist
void InfoConnection::OnRequestServerList()
{
	server.UpdateServerList();
}

// on request levellist
void InfoConnection::OnRequestLevelList()
{
	server.UpdateLevelList();
}

// on request notice
void InfoConnection::OnRequestNotice()
{
	server.UpdateNoticeList();
}

// on request blacklist
void InfoConnection::OnRequestBlackList()
{
	server.UpdateBlackList();
}

// on kick player
void InfoConnection::OnKickPlayer()
{
	uint user_id = 0;
	
	ReadInt(user_id);

	char strErr[256] = {0};
	ReadString(strErr, sizeof(strErr) / sizeof(char));
	
	ClientConnection * client = server.GetClientById(user_id);
	if (client)
	{
		client->ForceDisconnect(strErr);
	}
}

// on request sysconfig
void InfoConnection::OnRequestSysConfig()
{
	server.UpdateSysConfig();
}

void InfoConnection::OnBroadcastLoopMsg()
{
	log_write(LOG_DEBUG2, "OnBroadcastLoopMsg");
	char receiver[character_name_length];
	ReadString(receiver, sizeof(receiver));	
	
	ClientConnection * c = server.GetClientByName(receiver);
	if (c)
	{
		log_write(LOG_DEBUG2, "OnBroadcastLoopMsg c is exist");
		c->NotifyLoopMsg(this);
	}
}

void InfoConnection::OnUpdateBillBoardList()
{
	char receiver[201];
	ReadString(receiver, sizeof(receiver));	
	server.UpdateBillBoardList(receiver);
}

void InfoConnection::OnSyncRPCQueue()
{
	uint queue_id;
	int queue_size_difference;
	int queue_size;
	
	ReadInt(queue_id);
	ReadInt(queue_size_difference);
	ReadInt(queue_size);
	
	if (queue_id < RpcQueue::kQueueCount)
	{
		int queue_size_old = rpc_status[queue_id].queue_size;
		rpc_status[queue_id].queue_size += queue_size_difference;
		
		if (rpc_status[queue_id].queue_size < 0)
		{
			log_write(LOG_WARNING, "OnSyncRPCQueue() queue_id : %d, queue_size_old : %d, queue_size_difference : %d", 
					queue_id, queue_size_old, queue_size_difference);
			
			//rpc_status[queue_id].queue_size = 0;
		}
	}
}

// on update
void InfoConnection::Update()
{
	double time = Event::GetTime();
	if (time - rpc_queue_sync_time > appcfg.rpc_update_interval)
	{
		for (int queue = 0; queue < RpcQueue::kQueueCount; queue++)
		{
			SyncRPCQueue(queue);
		}

		rpc_queue_sync_time = time;
	}

	if (time - rpc_quene_disconnect_time > appcfg.rpc__disconnect_interval)
	{
		if (will_disconnect)
		{
			bool really_disconnect = true;

			for (int queue = 0; really_disconnect && queue < RpcQueue::kQueueCount; queue++)
			{
				for (RpcRequest * request = server.rpc.request_pool[queue].Begin();
					request < server.rpc.request_pool[queue].End(); request++)
				{
					if (request->type != RpcRequest::kNone &&
						request->processing_info == this)
					{
						really_disconnect = false;
						break;
					}
				}
			}

			if (really_disconnect)
			{
				log_write(LOG_INFO, "info server will really disconnted, wahaha !!!");
				Disconnect();
				return;
			}
		}

		rpc_quene_disconnect_time = time;
	}
}

void InfoConnection::OnLeagueGameInfo()
{
	//uint dwTime = 0;
	//ReadInt(dwTime);
	//
	//Event::AddTimer((EventHandler)HageBattleGroup::StartPlay, NULL, dwTime);
	//
	//HageBattleGroup::Initialize(*this);
}

void InfoConnection::RequestLeagueGameInfo()
{
	BeginWrite();
	WriteByte(CM_RequestLeagueGameInfo);
	EndWrite();
}

void InfoConnection::RequestLeagueGameInfoTime()
{
	BeginWrite();
	WriteByte(CM_RequestLeagueGameInfoTime);
	EndWrite();
}

void InfoConnection::OnResponseLeagueGameInfoTime()
{
}



