#pragma once

// client listener
class ClientListener : public TcpListener
{
	virtual void OnClientConnected(int socket, sockaddr_in addr);
};

// channel listener
class ChannelListener : public TcpListener
{
	virtual void OnClientConnected(int socket, sockaddr_in addr);
};

// gm listener
class GmListener : public TcpListener
{
	virtual void OnClientConnected(int socket, sockaddr_in addr);
};

// level
struct Level
{
	int				level_id;
	char			name[64];
	char			show_name[64];
	byte			type;				// 100以上为匹配 不转发给客户端 也不发给channel
	std::string		description;
	byte			is_vip;
	byte			is_new;
	int				gai_lv;
	byte			is_gm;
};

struct ChannelInfo
{
	uint id;
	char name[32];
	int min_character_level;
	int max_character_level;
	int min_level;
	int max_level;
	int online_max;
	int max_online_characters;
	byte istcp;
	byte isnovice;
	ChannelConnection * connection;
	
	ChannelInfo()
		:id(0)
		,min_character_level(0)
		,max_character_level(0)
		,min_level(0)
		,max_level(0)
		,online_max(0)
		,max_online_characters(0)
		,istcp(0)
		,isnovice(0)
		,connection(NULL)
	{
		memset(name, 0, sizeof(name));
	}
};

enum ServerType
{
	SvrType_None = 0,				// 
	SvrType_NewComer = 1,			// 新手
	SvrType_Improve = 2,			// 进阶
	SvrType_PastMaster = 3,			// 高手
	SvrType_StriveForResource = 4,	// 资源争夺战
	SvrType_HappyJump = 5,			// 跳跳乐
	SvrType_Match = 6,				// 匹配 争霸赛
	SvrType_MatchFighting = 7,				// 自动匹配
	SvrType_HageBattle = 8,				// 战队联赛
};

struct ServerInfo
{
	uint id;
	char name[32];
	int min_character_level;
	int max_character_level;
	int online_count;
	int online_max;
	int max_online_characters;
	int min_fightnum;
	std::string gametype_limit;
	byte isnovice;
	std::map<uint, ChannelInfo> channels;
	ServerType m_eServerType;
	
	ServerInfo()
		:id(0)
		,min_character_level(0)
		,max_character_level(0)
		,online_count(0)
		,online_max(0)
		,max_online_characters(0)
		,min_fightnum(0)
		,isnovice(0)
		,m_eServerType(SvrType_None)
	{
		memset(name, 0, sizeof(name));
	}
};

struct Notice
{
	uint		id;
	byte		type;		//1:one time notice2：loop notice
	std::string	msg;
	uint		start_time;	//sec
	uint		end_time;	//sec
	uint		interval;	//min
	
	double		cur_interval;//sec
	bool		is_done;
	
	Notice()
		: id(0)
		, type(0)
		, start_time(0)
		, end_time(0)
		, interval(0)
		, cur_interval(0)
		, is_done(false)
	{
	}
	
	static const byte ONE_TIME_NOTICE = 1;
	static const byte LOOP_NOTICE = 2;
};

struct SysConfig
{
	struct Activity
	{
		uint id;
		uint start_time;	//sec
		
		bool done;
	};
	
	byte	fcm_on;
	byte	ads_on;
	byte	luck_on;
	byte	luck_num;
	byte	luck_interval;
	
	double cur_luck_interval;
	
	std::vector<Activity> activity_list;
	
	SysConfig()
		: fcm_on(1)
		, ads_on(1)
		, luck_on(0)
		, luck_num(1)
		, luck_interval(1) //min
		, cur_luck_interval(0) //sec
	{
	}
};

struct HttpConfig
{
	std::string	 http_xl_logo;
	std::string  http_xl_report;
	std::string  http_xl_info;
	std::string  http_fcm;
	std::string  http_gw;
	std::string  http_advertising_v;
	std::string  http_advertising_h;
	std::string  http_paydraw;
	std::map<int,std::string> http_pay_map;
	std::vector<std::string> abroad_address_vec;

	std::string achievement_list;
	byte nick_name_length_min;
	byte nick_name_length_max;
	byte battle_group_name_length_min;
	byte battle_group_name_length_max;
	byte room_name_length_min;
	byte room_name_length_max;
	byte time_sell_date;
	byte time_sell_start;
	byte time_sell_start_date;
	byte time_sell_end;
	byte time_sell_end_date;
	byte time_sell_giftH;
	byte time_sell_giftM;
	std::string buffversion;
	std::string quick_sell_data;
	std::string time_sell_gold;
	std::string time_sell_silver;
	std::string time_sell_cooper;

	std::string UISystemFlag;
	byte xl_exe_open;

	
};
// client connection
class LoginServerConnection : public TcpConnection, public BinaryStream
{
public:
	// constructor
	LoginServerConnection();

	LoginServerConnection(const sockaddr_in& _addr);

	// destructor
	~LoginServerConnection();

	// on connected
	void OnConnected();

	// on close
	void OnDisconnected();

	// on message
	void OnMessage();

	// request login info
	void RequestLoginInfo(uint _uid, const char * login_info);
public:
	sockaddr_in addr;
};

class ProxyServer
{
public:
	// constructor
	ProxyServer();

	// destructor
	~ProxyServer();

	// run
	int Run();

	// on info server ready
	void OnInfoConnectionUp();

	// on info server down
	void OnInfoConnectionDown();

	// on level list changed
	void OnLevelListChanged();

	// on notice list changed
	void OnNoticeListChanged(RpcRequest * request);
	
	// on sysconfig changed
	void OnSysConfigChanged(RpcRequest * request);
	
	// on blacklist changed
	void OnBlackListChanged(RpcRequest * request);
	
	// update level list
	void UpdateLevelList();

	// update keywords
	void UpdateKeywords();

	// update server list
	void UpdateServerList();

	// update notice list
	void UpdateNoticeList();
	
	// update sys config 
	void UpdateSysConfig();
	
	// update blacklist
	void UpdateBlackList();
	
	// on keywords changed
	void OnKeywordsChanged(RpcRequest * conn);

	// parse level list
	void ParseLevelList(RpcRequest * conn);

	// on server list changed
	void OnServerListChanged(RpcRequest * conn);

	// [2015/10/9 dengxiaobo] 匹配
	void UpdateGuildTeamMatchingEventTime();
	static void UpdateGuildTeamEventTimeCallback(RpcRequest * request);
	// end
	
public:
	// get server
	ServerInfo * GetServerInfo(int server_id);

	// get channel
	ChannelInfo * GetChannelInfo(int server_id, int channel_id);

	// get channel connection
	ChannelConnection * GetChannel(int server_id, int channel_id);

	// get client
	ClientConnection * GetClient(uint uid);

	// get by name
	ClientConnection * GetClientByName(const char * name);

	// get by character id
	ClientConnection * GetClientById(uint user_id);

	// get balanced info connection
	InfoConnection * GetInfoConnection();

	// get chatgroup by id
	ChatGroup * GetChatGroup(uint uid);
	
	// update server info
	void UpdateServerInfo();

	void UpdateBillBoardList(const std::string& str);

	void InitializeAbroadServerConnection();

	bool RequestAbroadLoginInfo(ClientConnection* c, const char * login_info);

public:
	ClientListener client_listener;
	ChannelListener channel_listener;
	GmListener gm_listener;
	
	ApexConnection apex_connection;

	XLConnection  xl_connection[2][xl_connection_num];

	XLFCMConnection xl_fcm_connection;

	MatchingConnection matching_connection;
	
	std::vector<sockaddr_in> info_servers;

	ObjectPool<ClientConnection> client_pool;
	ObjectPool<ChannelConnection> channel_pool;
	ObjectPool<GmConnection> gm_pool;
	ObjectPool<InfoConnection> info_pool;
	
	ObjectPool<ChatGroup> chatgroup_pool;
	ObjectPool<BattleGroup> battlegroup_pool;
	ObjectPool<MatchingTeamGroup> m_pollMatchingTeamGroups;
	ObjectPool<HageBattleGroup> m_pollHageBattleGroup;

	std::map<uint, ServerInfo> servers;

	bool server_running;

	// online client map
	ClientNameMap online_client_map;

	// online client group set
	ClientGroupMap online_client_group_map;

	// online accounts
	__gnu_cxx::hash_map<uint, ClientConnection*> online_account_map;
	
	// banned userid
	std::set<uint> banned_userid;

	// level list
	std::vector<Level> level_list;

	// filter keywords
	std::vector<std::string> filter_keywords;

	// notice list
	std::vector<Notice> notice_list;

	std::vector<LoginServerConnection*> loginserverconnection_vec;
	
	// sys config
	SysConfig sys_config;

	HttpConfig http_config;

	// rpc queue
	RpcQueue rpc;

	// starting time
	time_t start_time;

	std::vector<std::string> bill_board_list;

	bool abroad_address_vec_initialized;

	std::map<uint, std::map<uint, uint> > m_mapClientCount;	//map<servertype map<channelid, count> >
};

extern ProxyServer server;
