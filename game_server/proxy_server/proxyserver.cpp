#include "pch.h"
#include <netdb.h>
#include <time.h>
#include <set>

#define BILL_BOARD_SIZE_LIMIT 5


// client listener
void ClientListener::OnClientConnected(int connected_socket, sockaddr_in addr)
{
	ClientConnection * client = server.client_pool.Allocate();

	if (client == NULL)
	{
		log_write_sys(LOG_DEBUG1, "can't allocate more client connections.");
		close(connected_socket);
		return;
	}

	client->client_ip = addr.sin_addr;
	client->Connect(connected_socket, addr);
}

// channel server listener
void ChannelListener::OnClientConnected(int connected_socket, sockaddr_in addr)
{
	ChannelConnection * conn = server.channel_pool.Allocate();

	if (conn == NULL)
	{
		log_write_sys(LOG_DEBUG1, "can't allocate more channel connections.");
		close(connected_socket);
		return;
	}

	conn->Connect(connected_socket, addr);
}

// info server listener
void GmListener::OnClientConnected(int connected_socket, sockaddr_in addr)
{
	GmConnection * conn = server.gm_pool.Allocate();

	if (conn == NULL)
	{
		log_write_sys(LOG_DEBUG1, "can't allocate more info connections.");
		close(connected_socket);
		return;
	}

	conn->client_ip = addr.sin_addr;
	conn->Connect(connected_socket, addr);
}

static void ReconnectLoginServer(LoginServerConnection * conn)
{
	conn->Connect(conn->addr);
}


LoginServerConnection::LoginServerConnection()
: BinaryStream(16 * 1024)
{
	stream = this;
	connection = this;
}

LoginServerConnection::LoginServerConnection(const sockaddr_in& _addr)
: BinaryStream(16 * 1024)
{
	stream = this;
	connection = this;
	addr = _addr;
}

LoginServerConnection::~LoginServerConnection()
{

}

void LoginServerConnection::OnMessage()
{
	log_write(LOG_DEBUG3, "LoginServerConnection Message");
	
	uint _uid;
	byte is_success;
	char account[user_name_length];
	short account_type;
	short xl_vip_account_level;
	short xl_bar_level;
	
	ClientConnection *client = NULL;
	
	try
	{
		ReadInt(_uid);
		ReadByte(is_success);
		ReadString(account,sizeof(account));
		ReadShort(account_type);
		ReadShort(xl_vip_account_level);
		ReadShort(xl_bar_level);
		
		if(_uid == 0)
		{
			log_write(LOG_DEBUG3, "LoginServerConnection Response Failed");
			return;
		}

		client = server.GetClient(_uid);
		if(client)
		{
			if(is_success != 0)
			{
				snprintf(client->xlinfo.xl_vip_account_level, 3, "%d", xl_vip_account_level);
				client->xlinfo.xl_vip_account_level[2] = '\0';

				snprintf(client->xlinfo.xl_bar_level, 3, "%d", xl_bar_level);
				client->xlinfo.xl_bar_level[2] = '\0';
				client->xlinfo.account_type = account_type;
				
				log_write(LOG_DEBUG3, "LoginServerConnection %s %d %d %d",account, account_type, xl_vip_account_level, xl_bar_level);
				log_write(LOG_DEBUG3, "LoginServerConnection Converted: %s %s", client->xlinfo.xl_bar_level, client->xlinfo.xl_vip_account_level);

				client->OnResponseXLConnection(account);
			}
			else
				client->OnLoginFailed("server_timeout");
		}
		else
			log_write(LOG_DEBUG1, "No Player Found");
	}
	catch (...)
	{
		log_write(LOG_ERROR, "LoginServerConnection::OnMessage() exp.");
		
		if (client)
			client->OnLoginFailed("server_timeout");
	}
}

void LoginServerConnection::OnConnected()
{
	
}

void LoginServerConnection::OnDisconnected()
{
	Event::AddTimer((EventHandler)&ReconnectLoginServer, this, 1);
}

void LoginServerConnection::RequestLoginInfo(uint _uid, const char * login_info)
{
	log_write(LOG_DEBUG3, "RequestLoginInfo %d %s", _uid, login_info);
	BeginWrite();
	WriteInt(_uid);
	WriteString(login_info);
	EndWrite();
}

// global server instance
ProxyServer server;

// proxy server
ProxyServer::ProxyServer()
{
	// clean channel table
	servers.clear();

	set_sockaddr(client_listener.addr, NULL, 9000);
	set_sockaddr(channel_listener.addr, NULL, 9001);
	set_sockaddr(gm_listener.addr, NULL, 9002);
	set_sockaddr(apex_connection.addr, "127.0.0.1", 9003);

	{
		char ipstr[16] ={0};
		struct hostent *answer;

		answer = gethostbyname("svr.fc.xunlei.com");
		if (answer != NULL) 
		{
			for (int i = 0; (answer->h_addr_list)[i] != NULL; i++) 
			{
				inet_ntop(AF_INET, (answer->h_addr_list)[i], ipstr, 16);
			}

			set_sockaddr(xl_connection[0][0].addr, ipstr, 58633);
		}
	}

	{
		char ipstr[16] ={0};
		struct hostent *answer;

		answer = gethostbyname("svr2.fc.xunlei.com");
		if (answer != NULL) 
		{
			for (int i = 0; (answer->h_addr_list)[i] != NULL; i++) 
			{
				inet_ntop(AF_INET, (answer->h_addr_list)[i], ipstr, 16);
			}

			set_sockaddr(xl_connection[1][0].addr, ipstr, 58633);
		}
	}
	abroad_address_vec_initialized = false;
}

ProxyServer::~ProxyServer()
{
	xl_fcm_connection.Disconnect();
	for (int i = 0; i < xl_connection_num; i++)
	{
		xl_connection[0][i].Disconnect();
		xl_connection[1][i].Disconnect();
	}
	for (int i = 0; i< loginserverconnection_vec.size(); i++)
	{
		loginserverconnection_vec[i]->Disconnect();
		delete loginserverconnection_vec[i];
	}
	loginserverconnection_vec.clear();
}

// update config
void ProxyServer::OnKeywordsChanged(RpcRequest * request)
{
	try
	{
		// parse keywords
		int keyword_count = 0;
		request->ReadInt(keyword_count);
		filter_keywords.clear();
		filter_keywords.reserve(keyword_count);

		for (int i = 0; i < keyword_count; i++)
		{
			char keyword[256] = {0};
			request->ReadString(keyword, sizeof(keyword));
			filter_keywords.push_back(keyword);
		}

		// build keywords
		DictMatch::Clear();
		for (std::vector<std::string>::iterator i = filter_keywords.begin(); i < filter_keywords.end(); ++i)
			DictMatch::AddKeyword((*i).c_str());
		DictMatch::Build();

		log_write(LOG_INFO, "keywords updated.");
	}
	catch (...)
	{
		log_write(LOG_ERROR, "keyword parse error.");
		filter_keywords.clear();
		DictMatch::Clear();
		DictMatch::Build();
	}

	// boradcast to channels.
	for (ChannelConnection * channel = channel_pool.Begin(); channel < channel_pool.End(); channel++)
	{
		if (channel->IsReady())
		{
			channel->UpdateFilterKeywords();
		}
	}
}

// on level list changed
void ProxyServer::OnLevelListChanged()
{
	for (ClientConnection * client = client_pool.Begin(); client < client_pool.End(); client ++)
	{
		if (client->IsOnline())
		{
			client->NotifyUpdateLevelList();
		}
	}
	
	// boradcast to channels.
	for (ChannelConnection * channel = channel_pool.Begin(); channel < channel_pool.End(); channel++)
	{
		if (channel->IsReady())
		{
			channel->UpdateLevelList();
		}
	}

	// ƥ�����ȡ��ͼ�б�
	matching_connection.OnUpdateLevelList(level_list);
}

// on notice list changed
void ProxyServer::OnNoticeListChanged(RpcRequest * request)
{
	try
	{
		// parse notice_list
		int count = 0;
		request->ReadInt(count);
		
		notice_list.clear();
		notice_list.reserve(count);
		
		uint cur_time = time(NULL);
		for (int i = 0; i < count; i++)
		{
			char msg[256] = {0};
			Notice notice;
			
			request->ReadInt(notice.id);
			if (notice.id)
			{
				request->ReadByte(notice.type);
				request->ReadString(msg, sizeof(msg));
				notice.msg = msg;
				if (notice.type == Notice::ONE_TIME_NOTICE)
				{
					request->ReadInt(notice.start_time);
					
					if (cur_time > notice.start_time)
					{
						log_write(LOG_DEBUG3, 
							"Notice(ONE_TIME_NOTICE) outdate, cur_time : %d, notice.start_time : %d", 
							cur_time, notice.start_time);
						
						continue;
					}
					
				}
				else if (notice.type == Notice::LOOP_NOTICE)
				{
					request->ReadInt(notice.start_time);
					request->ReadInt(notice.end_time);
					request->ReadInt(notice.interval);
					
					if (cur_time > notice.end_time)
					{
						log_write(LOG_DEBUG3, 
							"Notice(LOOP_NOTICE) outdate, cur_time : %d, notice.end_time : %d", 
							cur_time, notice.end_time);
						
						continue;
					}
				}
				else
				{
					log_write(LOG_ERROR, "unknow notice type.");
					
					throw notice.type;
				}
				
				notice_list.push_back(notice);
			}
		}

		log_write(LOG_INFO, "notice_list updated, count : %d.", count);
	}
	catch (...)
	{
		log_write(LOG_ERROR, "notice_list parse error.");
		notice_list.clear();
	}
}

// on sysconfig changed
void ProxyServer::OnSysConfigChanged(RpcRequest * info)
{
	try
	{
		uint cur_time = time(NULL);
		
		server.sys_config.activity_list.clear();

		char buffer[4096];
		int length;

		// parse sysconfig
		info->ReadByte(server.sys_config.fcm_on);
		info->ReadByte(server.sys_config.ads_on);
		info->ReadByte(server.sys_config.luck_on);
		info->ReadByte(server.sys_config.luck_interval);
		info->ReadByte(server.sys_config.luck_num);
		
		server.sys_config.cur_luck_interval = server.sys_config.luck_interval * 60;
		
		int activity_list_size = 0;
		info->ReadInt(activity_list_size);
		for (int i = 0; i < activity_list_size; i++)
		{
			SysConfig::Activity activity_tmp;
			
			activity_tmp.done = false;
			
			info->ReadInt(activity_tmp.id);
			info->ReadInt(activity_tmp.start_time);
			
			if (cur_time > activity_tmp.start_time)
			{
				log_write(LOG_DEBUG3, 
					"Activity outdate, cur_time : %d, activity.start_time : %d", 
					cur_time, activity_tmp.start_time);
				
				continue;
			}
			
			server.sys_config.activity_list.push_back(activity_tmp);
			
			log_write(LOG_DEBUG5, "Activity, id : %d, start_time : %d", activity_tmp.id, activity_tmp.start_time);
		}

		log_write(LOG_INFO, "sysconfig updated, fcm_on : %d, ads_on : %d, luck_on : %d, luck_interval : %d, luck_num : %d, activity_list_size : %d", 
				server.sys_config.fcm_on, server.sys_config.ads_on, server.sys_config.luck_on, server.sys_config.luck_interval, server.sys_config.luck_num, 
				server.sys_config.activity_list.size());

		

		info->ReadString(buffer,4096);
		http_config.http_xl_logo = buffer;
		log_write(LOG_DEBUG1, "http_config.http_xl_logo %s", http_config.http_xl_logo.c_str());

		info->ReadString(buffer,4096);
		http_config.http_xl_report = buffer;
		log_write(LOG_DEBUG1, "http_config.http_xl_report %s", http_config.http_xl_report.c_str());

		info->ReadString(buffer,4096);
		http_config.http_xl_info = buffer;
		log_write(LOG_DEBUG1, "http_config.http_xl_info %s", http_config.http_xl_info.c_str());

		info->ReadString(buffer,4096);
		http_config.http_fcm = buffer;
		log_write(LOG_DEBUG1, "http_config.http_fcm %s", http_config.http_fcm.c_str());

		info->ReadString(buffer,4096);
		http_config.http_gw = buffer;
		log_write(LOG_DEBUG1, "http_config.http_gw %s", http_config.http_gw.c_str());

		info->ReadString(buffer,4096);
		http_config.http_advertising_v = buffer;
		log_write(LOG_DEBUG1, "http_config.http_advertising_v %s", http_config.http_advertising_v.c_str());

		info->ReadString(buffer,4096);
		http_config.http_advertising_h = buffer;
		log_write(LOG_DEBUG1, "http_config.http_advertising_h %s", http_config.http_advertising_h.c_str());

		info->ReadString(buffer,4096);
		http_config.http_paydraw = buffer;
		log_write(LOG_DEBUG1, "http_config.http_paydraw %s", http_config.http_paydraw.c_str());

		info->ReadInt(length);
		log_write(LOG_DEBUG1, "http_config.http_pay_map length %d", length);
		for (int i = 0; i < length; i++)
		{
			std::string temp;
			std::string temp1;
			info->ReadString(buffer,4096);
			temp = buffer;
			
			info->ReadString(buffer,4096);
			temp1 = buffer;
			
			if(temp1 != "" && temp != "")
			{
				int key = atoi(temp.c_str());
				http_config.http_pay_map[key] = temp1;
				log_write(LOG_DEBUG1, "http_config.http_pay_map key: %d value: %s", key, temp1.c_str());
			}
		}
		info->ReadString(buffer,4096);
		std::string temp_str = buffer;
		if(!abroad_address_vec_initialized)
		{
			http_config.abroad_address_vec.reserve(32);
			SpltCsv(buffer, http_config.abroad_address_vec, ',');


			abroad_address_vec_initialized = true;

			for (uint i = 0 ; i < http_config.abroad_address_vec.size(); i++)
			{
				log_write(LOG_DEBUG3, "abroad login server address %s", http_config.abroad_address_vec[i].c_str());
			}

			InitializeAbroadServerConnection();
		}

		info->ReadString(buffer,4096);
		http_config.achievement_list = buffer;

		// text length limit--------------------
		info->ReadByte(http_config.nick_name_length_min);
		info->ReadByte(http_config.nick_name_length_max);

		info->ReadByte(http_config.battle_group_name_length_min);
		info->ReadByte(http_config.battle_group_name_length_max);
		info->ReadByte(http_config.room_name_length_min);
		info->ReadByte(http_config.room_name_length_max);
		info->ReadByte(http_config.time_sell_start);
		info->ReadByte(http_config.time_sell_start_date);
		info->ReadByte(http_config.time_sell_end);
		info->ReadByte(http_config.time_sell_end_date);
		info->ReadByte(http_config.time_sell_giftH);
		info->ReadByte(http_config.time_sell_giftM);
		info->ReadByte(http_config.time_sell_date);

		info->ReadString(buffer, 4096);
		http_config.buffversion = buffer;

		info->ReadString(buffer, 4096);
		http_config.quick_sell_data = buffer;

		info->ReadString(buffer, 4096);
		http_config.time_sell_gold = buffer;

		info->ReadString(buffer, 4096);
		http_config.time_sell_silver = buffer;

		info->ReadString(buffer, 4096);
		http_config.time_sell_cooper = buffer;

		info->ReadString(buffer,4096);
		http_config.UISystemFlag = buffer;
		// -------------------------------------

		// xl exe ------------------------------
		info->ReadByte(http_config.xl_exe_open);

		
		// -------------------------------------

		
	}
	catch (...)
	{
		log_write(LOG_ERROR, "sysconfig parse error.");
		server.sys_config.activity_list.clear();
	}
}

void ProxyServer::InitializeAbroadServerConnection()
{
	if(abroad_address_vec_initialized)
	{
		for (uint i = 0; i < http_config.abroad_address_vec.size(); i++)
		{
			sockaddr_in addr;
			if(parse_sockaddr(addr, http_config.abroad_address_vec[i].c_str()))
			{
				LoginServerConnection * connection = new LoginServerConnection(addr);
				connection->Connect(addr);
				loginserverconnection_vec.push_back(connection);
			}
		}
	}
}

bool ProxyServer::RequestAbroadLoginInfo(ClientConnection* c, const char * login_info)
{
	uint retry_time = 5;
	for (uint i = 0 ; i < retry_time; i++)
	{
		bool is_find = false;
		int rand_id = rand() % loginserverconnection_vec.size();
		LoginServerConnection* conn = loginserverconnection_vec[rand_id];
		if(conn->IsConnected())
		{
			conn->RequestLoginInfo(c->uid, login_info);
			return true;
		}
	}
	return false;
}

// on blacklist changed
void ProxyServer::OnBlackListChanged(RpcRequest * info)
{
	try
	{
		uint cur_time = time(NULL);
		
		banned_userid.clear();
		
		// parse blacklist
		int blacklist_size = 0;
		info->ReadInt(blacklist_size);
		for (int i = 0; i < blacklist_size; i++)
		{
			int tmp_user_id;
			
			info->ReadInt(tmp_user_id);
			
			banned_userid.insert(tmp_user_id);
		}
		
		log_write(LOG_INFO, "blacklist updated, blacklist_size : %d", blacklist_size);
		
		// boradcast to channels.
		for (ChannelConnection * channel = channel_pool.Begin(); channel < channel_pool.End(); channel++)
		{
			if (channel->IsReady())
			{
				channel->UpdateBlackList();
			}
		}
	}
	catch (...)
	{
		log_write(LOG_ERROR, "blacklist parse error.");
		banned_userid.clear();
	}
}

static void UpdateLevelListCallback(RpcRequest * request)
{
	if (request && request->error == 0)
	{
		server.ParseLevelList(request);
		server.OnLevelListChanged();
		log_write(LOG_INFO, "level list updated.");
	}
}

// update keyword callback
static void UpdateKeywordCallback(RpcRequest * request)
{
	if (request && request->error == 0)
		server.OnKeywordsChanged(request);
}

// update server list callback
static void UpdateServerListCallback(RpcRequest * request)
{
	if (request && request->error == 0)
		server.OnServerListChanged(request);
}

// update notice list callback
static void UpdateNoticeListCallback(RpcRequest * request)
{
	if (request && request->error == 0)
		server.OnNoticeListChanged(request);
}

// update sysconfig callback
static void UpdateSysConfigCallback(RpcRequest * request)
{
	if (request && request->error == 0)
		server.OnSysConfigChanged(request);
}

// update sysconfig callback
static void UpdateBlackListCallback(RpcRequest * request)
{
	if (request && request->error == 0)
		server.OnBlackListChanged(request);
}

// update level list
void ProxyServer::UpdateLevelList()
{
	RpcRequest * request = server.rpc.Allocate(RpcQueue::kServerQueue);

	if (request)
	{
		request->BeginWrite();
		request->WriteBinaryRPC("s_get_level_list", &UpdateLevelListCallback);
		request->EndWrite();
	}
}

// update keywords
void ProxyServer::UpdateKeywords()
{
	RpcRequest * request = server.rpc.Allocate(RpcQueue::kServerQueue);

	if (request)
	{
		request->BeginWrite();
		request->WriteBinaryRPC("s_keywords", &UpdateKeywordCallback);
		request->EndWrite();
	}
}

// update server list
void ProxyServer::UpdateServerList()
{
	RpcRequest * request = server.rpc.Allocate(RpcQueue::kServerQueue);

	if (request)
	{
		request->BeginWrite();
		request->WriteBinaryRPC("s_server_list", &UpdateServerListCallback);
		request->EndWrite();
	}
}

// update notice list
void ProxyServer::UpdateNoticeList()
{
	RpcRequest * request = server.rpc.Allocate(RpcQueue::kServerQueue);

	if (request)
	{
		request->BeginWrite();
		request->WriteBinaryRPC("s_notice_list", &UpdateNoticeListCallback);
		request->EndWrite();
	}
}

// update sys config 
void ProxyServer::UpdateSysConfig()
{
	RpcRequest * request = server.rpc.Allocate(RpcQueue::kServerQueue);

	if (request)
	{
		request->BeginWrite();
		request->WriteBinaryRPC("s_sys_config", &UpdateSysConfigCallback);
		request->EndWrite();
	}
}

// update blacklist
void ProxyServer::UpdateBlackList()
{
	RpcRequest * request = server.rpc.Allocate(RpcQueue::kServerQueue);

	if (request)
	{
		request->BeginWrite();
		request->WriteBinaryRPC("s_get_unspeaklist", &UpdateBlackListCallback);
		request->EndWrite();
	}
}

// parse level list
void ProxyServer::ParseLevelList(RpcRequest * request)
{
	if (request && request->error == 0)
	{
		try
		{
			int level_count = 0;
			request->ReadInt(level_count);

			level_list.clear();
			if (level_count > 0)
			{
				level_list.resize(level_count);

				for (int i = 0; i < level_count; i++)
				{
					request->ReadInt(level_list[i].level_id);
					request->ReadString(level_list[i].name, sizeof(level_list[i].name));
					request->ReadByte(level_list[i].type);

					request->ReadString(level_list[i].show_name, sizeof(level_list[i].show_name));
					char str[1024] = { 0 };
					request->ReadString(str, sizeof(str));
					level_list[i].description = str;
					
					request->ReadByte(level_list[i].is_vip);
					request->ReadByte(level_list[i].is_new);
					request->ReadInt(level_list[i].gai_lv);
					request->ReadByte(level_list[i].is_gm);
					
				}
			}
		}
		catch (...)
		{
			level_list.clear();
			log_write(LOG_ERROR, "parse level list error");
		}
	}
}

// on server list changed
void ProxyServer::OnServerListChanged(RpcRequest * request)
{
	// mark all servers.
	servers.clear();

	try
	{
		char buffer[512];
		int server_count;
		
		// read servers
		request->ReadInt(server_count);
		for (int i = 0; i < server_count; i++)
		{
			int server_id;
			int channel_count;

			request->ReadInt(server_id);

			ServerInfo server_tmp;

			server_tmp.id = server_id;
			request->ReadString(server_tmp.name, sizeof(server_tmp.name));
			request->ReadInt(server_tmp.min_character_level);
			request->ReadInt(server_tmp.max_character_level);
			request->ReadInt(server_tmp.max_online_characters);
			request->ReadByte(server_tmp.isnovice);
			request->ReadInt(server_tmp.min_fightnum);
			
			request->ReadString(buffer, sizeof(buffer));
			server_tmp.gametype_limit = buffer;
			
			uint dwServerType = 0;
			request->ReadInt(dwServerType);
			server_tmp.m_eServerType = (ServerType)dwServerType;

			request->ReadInt(channel_count);
			for (int j = 0; j < channel_count; j++)
			{
				int channel_id;
				ChannelInfo channel_tmp;

				request->ReadInt(channel_id);

				channel_tmp.id = channel_id;
				request->ReadString(channel_tmp.name, sizeof(channel_tmp.name));
				request->ReadInt(channel_tmp.min_character_level);
				request->ReadInt(channel_tmp.max_character_level);
				request->ReadInt(channel_tmp.min_level);
				request->ReadInt(channel_tmp.max_level);
				request->ReadInt(channel_tmp.max_online_characters);
				request->ReadByte(channel_tmp.istcp);
				channel_tmp.isnovice = server_tmp.isnovice;

				if (channel_id > 0/*  && channel_id <= appcfg.max_channel_count */)
				{
					// search connected channelconnection
					for (ChannelConnection * c = channel_pool.Begin(); c < channel_pool.End(); c++)
					{
						if (c && c->IsReady() && 
							server_id == c->server_id && channel_id == c->channel_id)
						{
							c->SetClientConnectMode(channel_tmp.istcp);
							c->SetServerNovice(channel_tmp.isnovice);
							channel_tmp.connection = c;
							
							log_write(LOG_DEBUG1, "find a connected channel : server_id[%d], channel_id[%d].", 
									channel_tmp.connection->server_id, channel_tmp.connection->channel_id);
							
							break;
						}
						else
						{
							m_mapClientCount[server_id][channel_id] = 0;
						}
					}
				
					server_tmp.channels.insert(std::pair<uint, ChannelInfo>(channel_id, channel_tmp));
				}
			}
			
			if (server_id > 0/*  && server_id <= appcfg.max_server_count */)
			{
				// search connected client
				for (ClientConnection * c = client_pool.Begin(); c < client_pool.End(); c++)
				{
					if (c && c->IsOnline() && c->character_server_id == server_id)
					{
						server_tmp.online_count++;
					}
				}
				
				servers.insert(std::pair<uint, ServerInfo>(server_id, server_tmp));
			}
		}
		
		UpdateServerInfo();

		log_write(LOG_INFO, "server list updated.");
	}
	catch (...)
	{
		servers.clear();

		log_write(LOG_ERROR, "server list updated error.");
	}
}

// update server info
void ProxyServer::UpdateServerInfo()
{
	for (std::map<uint, ServerInfo>::iterator itr1 = servers.begin(); 
		itr1 != servers.end(); itr1++)
	{
		uint max_server_characters = 0;

		ServerInfo & s = itr1->second;

		for (std::map<uint, ChannelInfo>::iterator itr2 = s.channels.begin(); 
			itr2 != s.channels.end(); itr2++)
		{
			ChannelInfo & c = itr2->second;

			if (c.id)
			{
				c.online_max = c.max_online_characters;

				if (c.connection)
				{
					// if (c.max_online_characters == 0)
					// {
						// c.online_max = c.connection->client_max;
					// }
					// else
					{
						if (c.connection->client_max < c.max_online_characters)
							c.online_max = c.connection->client_max;
					}
				}
				else
				{
					c.online_max = 0;
				}

				max_server_characters += c.online_max;
				
				log_write(LOG_DEBUG5, "UpdateServerInfo(ChannelInfo), name : %s, online_max : %d, max_online_characters : %d", 
						c.name, c.online_max, c.max_online_characters);
			}
		}

		if (s.id)
		{
			// if (s.max_online_characters == 0)
				// s.online_max = max_server_characters;
			// else
				s.online_max = s.max_online_characters;
				
			log_write(LOG_DEBUG5, "UpdateServerInfo(ServerInfo), name : %s, online_max : %d, max_online_characters : %d", 
					s.name, s.online_max, s.max_online_characters);
		}
	}
}

void ProxyServer::UpdateBillBoardList(const std::string& str)
{
	bill_board_list.push_back(str);
	bool flag = false;
	if(bill_board_list.size() > BILL_BOARD_SIZE_LIMIT)
	{
		bill_board_list.erase(bill_board_list.begin());
		flag = true;
	}

	for (ClientConnection * c = client_pool.Begin(); c < client_pool.End(); c++)
	{
		c->NotifyUpdateBillBoardInfo(str, flag);
	}
}

// get server
ServerInfo * ProxyServer::GetServerInfo(int server_id)
{
	std::map<uint, ServerInfo>::iterator itr = servers.find(server_id);
	if (itr != servers.end() && itr->second.id)
	{
		return &itr->second;
	}
	
	return NULL;
}

// get channel
ChannelInfo * ProxyServer::GetChannelInfo(int server_id, int channel_id)
{
	ServerInfo * server = GetServerInfo(server_id);
	if (server)
	{
		std::map<uint, ChannelInfo>::iterator itr = server->channels.find(channel_id);
		if (itr != server->channels.end())
		{
			return &itr->second;
		}
	}

	return NULL;
}

// get channel connection
ChannelConnection * ProxyServer::GetChannel(int server_id, int channel_id)
{
	ChannelInfo * channel_info = GetChannelInfo(server_id, channel_id);

	if (channel_info)
		return channel_info->connection;
	else
		return NULL;
}

// get client
ClientConnection * ProxyServer::GetClient(uint uid)
{
	return client_pool.Get(uid);
}

// get by name
ClientConnection * ProxyServer::GetClientByName(const char * name)
{
	ClientNameMap::const_iterator it = server.online_client_map.find(name);

	if (it != server.online_client_map.end())
		return it->second;

	return NULL;
}

// get by character id
ClientConnection * ProxyServer::GetClientById(uint user_id)
{
/*
	// TODO: optimize
	for (ClientConnection * client = client_pool.Begin(); client < client_pool.End(); client++)
	{
		if (client->IsOnline() && client->user_id == user_id)
			return client;
	}
*/
	__gnu_cxx::hash_map<uint, ClientConnection*>::const_iterator it = server.online_account_map.find(user_id);

	if (it != server.online_account_map.end())
		return it->second;

	return NULL;
}

// get chatgroup by id
ChatGroup * ProxyServer::GetChatGroup(uint uid)
{
	return chatgroup_pool.Get(uid);
}

// on info server ready
void ProxyServer::OnInfoConnectionUp()
{
	uint count = 0;
	for (InfoConnection * conn = info_pool.Begin(); conn < info_pool.End(); conn++)
	{
		if (conn->IsConnected())
			count++;
	}

	if (!server_running && count > 0)
	{
		// update config
		UpdateKeywords();

		// update server list
		UpdateServerList();

		// update level list
		UpdateLevelList();

		// update notice list
		UpdateNoticeList();
		
		// update sys config 
		UpdateSysConfig();
		
		// update black list
		UpdateBlackList();
		
		server_running = true;
	}
}

// on info server down
void ProxyServer::OnInfoConnectionDown()
{
	/* Force disconnect all client is nolonger needed when all info is down,
	 * because rpc requests is send into a queue in proxyserver. and that request
	 * will be send to info when then info is reconnected.

	uint count = 0;
	for (InfoConnection * conn = info_pool.Begin(); conn < info_pool.End(); conn++)
	{
		if (conn->IsConnected())
			count++;
	}

	if (server_running && count == 0)
	{
		server_running = false;
		
		// disconnect all clients
		for (ClientConnection * client = client_pool.Begin(); client < client_pool.End(); client++)
			client->ForceDisconnect("server_shutdown");
	}
	*/
}

// update status
static void UpdateStatus(void * data = NULL)
{
	status.Update();
	Event::AddTimer(&UpdateStatus, data, 1);
}

static void UpdateCCU(void * data = NULL)
{
	MatchingConnection::UpdateCCU(data);
	Event::AddTimer(&UpdateCCU, data, 1);
}

// update
static void UpdateClients(void * data = NULL)
{
	static double update_time = Event::GetTime();
	double time = Event::GetTime();
	double frame_time = time - update_time;
	update_time = time;

	for (ClientConnection * client = server.client_pool.Begin(); client < server.client_pool.End(); client++)
	{
		if (client->IsOnline())
		{
			client->OnUpdate(frame_time);
		}
	}

	Event::AddTimer(&UpdateClients, data, 0.2);
}

// update
static void UpdateRPC(void * data = NULL)
{
	static double update_time = Event::GetTime();
	double time = Event::GetTime();
	double frame_time = time - update_time;
	update_time = time;
	
	server.rpc.OnUpdate(frame_time);

	for (InfoConnection *info = server.info_pool.Begin(); info < server.info_pool.End(); info++)
	{
		if (!info->IsConnected())
			continue;

		info->Update();
	}

	Event::AddTimer(&UpdateRPC, data, 1.0);
}

// update
static void UpdateNotices(void * data = NULL)
{
	static double update_time = Event::GetTime();
	double time = Event::GetTime();
	double frame_time = time - update_time;
	update_time = time;

	for (uint i = 0; i < server.notice_list.size(); i++)
	{
		Notice *pNotice = NULL;
		uint cur_time = ::time(NULL);
		
		if (server.notice_list[i].type == Notice::ONE_TIME_NOTICE)
		{
			if (server.notice_list[i].is_done == false && server.notice_list[i].start_time <= cur_time)
			{
				pNotice = &server.notice_list[i];
				server.notice_list[i].is_done = true;
			}
		}
		else if (server.notice_list[i].type == Notice::LOOP_NOTICE)
		{
			if (server.notice_list[i].start_time <= cur_time && server.notice_list[i].end_time >= cur_time)
			{
				server.notice_list[i].cur_interval -= frame_time;
				if (server.notice_list[i].cur_interval <= 0)
				{
					pNotice = &server.notice_list[i];
					server.notice_list[i].cur_interval += server.notice_list[i].interval * 60;
				}
			}
		}

		if (pNotice)
		{
			for (ClientConnection * client = server.client_pool.Begin(); client < server.client_pool.End(); client++)
			{
				if (client->IsOnline())
				{
					client->NotifyChat("/notice", "", pNotice->msg.c_str());
				}
			}
			
			log_write(LOG_DEBUG5, "notice.type = %d, notice.msg = %s", pNotice->type, pNotice->msg.c_str());
		}
	}

	Event::AddTimer(&UpdateNotices, data, 1 * 60);
}

static void UpdateBattleGroups(void * data = NULL)
{
	static double update_time = Event::GetTime();
	double time = Event::GetTime();
	double frame_time = time - update_time;
	update_time = time;
	
	for (std::set<uint>::iterator itr = BattleGroup::battlegroup_list.begin(); 
		itr != BattleGroup::battlegroup_list.end(); itr++)
	{
		BattleGroup *battlegroup = server.battlegroup_pool.Get(*itr);
		if (battlegroup)
		{
			battlegroup->OnUpdate(frame_time);
		}
	}
	
	Event::AddTimer(&UpdateBattleGroups, data, 1);
}

// notify activity
static void NotifyActivity(RpcRequest * request, uint id)
{
	static const uint max_list_num = 10000;

	std::list<uint> character_id_list;
	uint batch_num = 0;
	
	for (ClientConnection * c = server.client_pool.Begin(); c < server.client_pool.End(); c++)
	{
		if (c->IsOnline())
		{
			if (character_id_list.size() >= max_list_num)
			{
				request->BeginWrite();
				request->WriteBinaryRPC("s_login_activity");
				request->EndRPCUserdata();
				request->WriteInt(id);
				request->WriteInt((uint)character_id_list.size());
				for (std::list<uint>::const_iterator itr = character_id_list.begin(); 
					itr != character_id_list.end(); itr++)
				{
					request->WriteInt(*itr);
				}
				request->EndWrite();
				
				character_id_list.clear();
				
				batch_num++;
			}
			
			character_id_list.push_back(c->character_id);
		}
	}
	
	if (character_id_list.size() > 0)
	{
		request->BeginWrite();
		request->WriteBinaryRPC("s_login_activity", NULL);
		request->WriteInt(id);
		request->WriteInt((uint)character_id_list.size());
		for (std::list<uint>::const_iterator itr = character_id_list.begin(); 
			itr != character_id_list.end(); itr++)
		{
			request->WriteInt(*itr);
		}
		request->EndWrite();
		
		character_id_list.clear();
		
		batch_num++;
	}
		
	log_write(LOG_DEBUG5, "NotifyActivity, id : %d, batch : %d", id, batch_num);
}

// send rand luck character
static void SendRandLuckCharacter(RpcRequest *request, std::vector<uint> & rand_client)
{
	request->BeginWrite();
	request->WriteBinaryRPC("s_random_player");
	request->EndRPCUserdata();
	request->WriteInt((uint)rand_client.size());
	for (int i = 0; i < rand_client.size(); i++)
	{
		request->WriteInt(rand_client[i]);
	}
	request->EndWrite();
	
	log_write(LOG_DEBUG5, "SendRandLuckCharacter, size : %d", rand_client.size());
}

static uint RandomInt(uint x, uint y)
{
	float r = (float)rand() / RAND_MAX;
	float num = x + (y - x) * r;
	
	return (uint)num; 
}

static void RandLuckClient(void * data = NULL)
{
	static double update_time = Event::GetTime();
	double time = Event::GetTime();
	double frame_time = time - update_time;
	update_time = time;
	
	if (server.sys_config.luck_on)
	{
		server.sys_config.cur_luck_interval -= frame_time;
		if (server.sys_config.cur_luck_interval <= 0)
		{
			server.sys_config.cur_luck_interval += server.sys_config.luck_interval * 60;
			
			int luck_num = Min((int)server.sys_config.luck_num, (int)server.online_client_map.size());
			
			// gen rand set
			std::set<uint> rand_set;
			for (int i = 0; i < luck_num; )
			{
				uint rand_pos = RandomInt(0, server.online_client_map.size());
				if (rand_set.find(rand_pos) == rand_set.end())
				{
					rand_set.insert(rand_pos);
					i++;
				}
			}
			
			// rand client
			std::vector<uint> rand_client;
			rand_client.reserve(rand_set.size());
			for (std::set<uint>::iterator rand_itr = rand_set.begin(); 
				rand_itr != rand_set.end(); rand_itr++)
			{
				uint pos = 0;
				
				for (ClientNameMap::iterator itr = server.online_client_map.begin(); 
					itr != server.online_client_map.end(); itr++)
				{
					if (*rand_itr == pos)
					{
						rand_client.push_back(itr->second->character_id);
						
						log_write(LOG_DEBUG5, "RandLuckClient : %d", itr->second->character_id);
						
						break;
					}
					
					pos++;
				}
			}
			
			//send
			RpcRequest * request = server.rpc.Allocate(RpcQueue::kServerQueue);
			if (request && rand_client.size() > 0)
			{
				SendRandLuckCharacter(request, rand_client);
			}
		}
	}
	
	uint cur_time = ::time(NULL);
	for (int i = 0; i < server.sys_config.activity_list.size(); i++)
	{
		if (server.sys_config.activity_list[i].done == false && 
			server.sys_config.activity_list[i].start_time <= cur_time)
		{
			RpcRequest * request = server.rpc.Allocate(RpcQueue::kServerQueue);
			if (request)
			{
				NotifyActivity(request, server.sys_config.activity_list[i].id);
				server.sys_config.activity_list[i].done = true;
			}
		}
	}
	
	Event::AddTimer(&RandLuckClient, data, 1 * 60);
}

static void RequestLeagueGameInfo(void* pData);
static void LeagueGameInfoCallBack(RpcRequest * request)
{
	log_write(LOG_DEBUG1, "%s, %s, request error : %d", __FILE__, __FUNCTION__, request->error);
	if (request && request->error == 0)
	{
		uint dwNextTime = 0;
		request->ReadInt(dwNextTime);
		Event::AddTimer((EventHandler)&RequestLeagueGameInfo, NULL, dwNextTime);

		uint dwTime = 0;
		request->ReadInt(dwTime);

		if (dwTime == 0)
		{
			return;
		}

		Event::AddTimer((EventHandler)HageBattleGroup::DelayStart, NULL, dwTime);
		HageBattleGroup::Initialize(*request);
	}
	else
	{
		RequestLeagueGameInfo(NULL);
	}
}

static void RequestLeagueGameInfo(void* pData)
{
	RpcRequest * request = server.rpc.Allocate(RpcQueue::kServerQueue);

	if (request)
	{
		request->BeginWrite();
		request->WriteBinaryRPC("s_league_game_info", &LeagueGameInfoCallBack);
		request->EndWrite();
	}
	log_write(LOG_DEBUG1, "%s, %s", __FILE__, __FUNCTION__);
}

static void RequestLeagueGameInfoTime(void*);
static void LeagueGameInfoTimeCallback(RpcRequest * request)
{
	if (request && request->error == 0)
	{
		uint dwTime = 0;
		request->ReadInt(dwTime);
	
		Event::AddTimer((EventHandler)&RequestLeagueGameInfo, NULL, (double)dwTime);
		Event::AddTimer((EventHandler)&RequestLeagueGameInfoTime, NULL, (double)dwTime);
	}
}

void RequestLeagueGameInfoTime(void* pData)
{
	RpcRequest * request = server.rpc.Allocate(RpcQueue::kServerQueue);

	if (request)
	{
		request->BeginWrite();
		request->WriteBinaryRPC("s_league_beg_time", &LeagueGameInfoTimeCallback);
		request->EndWrite();
	}
	log_write(LOG_DEBUG1, "%s, %s", __FILE__, __FUNCTION__);
}

// run
int ProxyServer::Run()
{
	time(&start_time);
	srand(time(NULL));
	
	log_write_sys(LOG_INFO, "intialize proxy server %d, apexserver=%s", 
				ntohs(client_listener.addr.sin_port), sockaddr_ntoa(apex_connection.addr));

	if (info_servers.size() == 0)
	{
		log_write_sys(LOG_ERROR, "no info server.");
		return 1;
	}

	if (!client_pool.Initialize(appcfg.max_client_count))
	{
		log_write_sys(LOG_ERROR, "not enough memory!");
		return 1;
	}
	
	if (!channel_pool.Initialize(appcfg.max_channel_count * appcfg.max_server_count))
	{
		log_write_sys(LOG_ERROR, "not enough memory!");
		return 1;
	}
	
	if (!gm_pool.Initialize(appcfg.max_gm_count))
	{
		log_write_sys(LOG_ERROR, "not enough memory!");
		return 1;
	}

	if (!info_pool.Initialize(info_servers.size()))
	{
		log_write_sys(LOG_ERROR, "not enough memory!");
		return 1;
	}

	if (!chatgroup_pool.Initialize(appcfg.max_chatgroup_count))
	{
		log_write_sys(LOG_ERROR, "not enough memory!");
		return 1;
	}
	
	if (!battlegroup_pool.Initialize(appcfg.max_battlegroup_count))
	{
		log_write_sys(LOG_ERROR, "not enough memory!");
		return 1;
	}

	if (!m_pollMatchingTeamGroups.Initialize(appcfg.max_matching_group_count))
	{
		log_write_sys(LOG_ERROR, "not enough memory!");
		return 1;
	}

	if (!m_pollHageBattleGroup.Initialize(appcfg.max_hagebattlegroup_count))
	{
		log_write_sys(LOG_ERROR, "not enough memory!");
		return 1;
	}

	if (!rpc.Initialize())
	{
		log_write_sys(LOG_ERROR, "not enough memory!");
		return 1;
	}
	
	/*
	if (!HttpConnection::Initialize(64, info_server_addr, info_server_count))
	{
		log_write_sys(LOG_ERROR, "can not initialize http pool, not enouth memory!");
		return 1;
	}*/
	
	for (int i = 0; i < xl_connection_num; i++)
	{
		xl_connection[0][i].Connect(xl_connection[0][0].addr);
		xl_connection[1][i].Connect(xl_connection[1][0].addr);
	}

	{
		char ipstr[16] ={0};
		struct hostent *answer;

		

		answer = gethostbyname(appcfg.fcm_address);
		if (answer != NULL) 
		{
			for (int i = 0; (answer->h_addr_list)[i] != NULL; i++) 
			{
				inet_ntop(AF_INET, (answer->h_addr_list)[i], ipstr, 16);
			}

			log_write(LOG_INFO, "fcm address : %s, port :%d", ipstr, appcfg.fcm_port);
			set_sockaddr(xl_fcm_connection.addr, ipstr, appcfg.fcm_port);
		}
	}

	log_write_sys(LOG_INFO, "try to connect fcm server");
	
	xl_fcm_connection.Connect(xl_fcm_connection.addr);

	if (!client_listener.Initialize())
		return 1;

	if (!channel_listener.Initialize())
		return 1;

	if (!gm_listener.Initialize())
		return 1;

	// connect info server
	for (int i = 0; i < info_servers.size(); i ++)
	{
		InfoConnection * conn = info_pool.Begin() + i;
		conn->addr = info_servers[i];
		conn->Connect(conn->addr);
	}
	
	// connect apex server
	apex_connection.Connect(apex_connection.addr);

	matching_connection.Connect(matching_connection.addr);

	// update status
	UpdateStatus();

	UpdateCCU();

	// update clients
	UpdateClients();
	
	// update infos
	UpdateRPC();
	
	// update notices
	UpdateNotices();
	
	// update battlegroups
	UpdateBattleGroups();
	
	// rand luck client
	RandLuckClient();
	
	/* Local stub: skip league game info RPC.
	RequestLeagueGameInfo(NULL);
	*/

	// dispatch events.
	Event::Dispatch();

	// terminate
	client_listener.Terminate();
	channel_listener.Terminate();
	gm_listener.Terminate();

	client_pool.Terminate();
	channel_pool.Terminate();
	gm_pool.Terminate();
	info_pool.Terminate();

	Event::Terminate();

	return 0;
}

void ProxyServer::UpdateGuildTeamMatchingEventTime()
{
	RpcRequest * request = server.rpc.Allocate(RpcQueue::kServerQueue);

	if (request)
	{
		request->BeginWrite();
		request->WriteBinaryRPC("s_guild_team_notice", &UpdateGuildTeamEventTimeCallback);
		request->EndWrite();
	}
}

void ProxyServer::UpdateGuildTeamEventTimeCallback( RpcRequest * request )
{
	if (request && request->error == 0)
	{
		server.matching_connection.OnUpdateGuildTeamEventTimeCallback(request->read_position, request->read_end - request->read_position);
	}
}

