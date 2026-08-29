#pragma once

// client connection
class ClientConnection : public TcpConnection, public BinaryStream
{
public:
	// constructor
	ClientConnection();

	// destructor
	~ClientConnection();
	
	// on connected
	void OnConnected();

	// on close
	void OnDisconnected();

	// on message
	void OnMessage();

	// on authencation
	void OnMessageConnected();

	bool RequestRealNameAuth(const char* in, int len, uint uid);

	void OnMessageNickName();

	void OnResponseXLConnection(const char* ac);

	void OnResponseAbroadLoginInfoConnection(const char* ac);

	// on message login
	void OnMessageLogin();

	// on message lobby
	void OnMessageLobby();

	// on update
	void OnUpdate(double time);

	// on login
	void OnLoginSuccess();

	void OnLoginRequestNickName();

	// on login failed
	void OnLoginFailed(const char * error);

	void OnNickNameFailed(const char * error);

	// request
	void OnRequestRPC();

	// request chat
	void OnRequestChat();

	// on enter lobby
	void OnRequestEnterLobby();

	// on leave lobby
	void OnRequestLeaveLobby();

	// on request channel connect
	void OnRequestChannelConnect();

	// on enter lobby
	void OnEnterLobby();

	// on leave lobby
	void OnLeaveLobby();

	// on team invite
	void OnRequestTeamInvite();

	// on team join
	void OnRequestTeamJoin();

	// on team leave
	void OnRequestTeamLeave();

	// on team kick
	void OnRequestTeamKick();

	// on team change leader
	void OnRequestTeamChangeLeader();

	// on request team refuse
	void OnRequestTeamRefuse();

	// on request team call
	void OnRequestTeamCall();

	// on request search room
	void OnRequestSearchRoom();

	// on request cancel search room
	void OnRequestCancelSearchRoom();

	// on request refuse preserve
	void OnRequestRefusePreserve();

	// on character info changed
	void OnCharacterInfoChanged();

	// on request enter server
	void OnRequestEnterServer();

	// on request enter channel
	void OnRequestEnterChannel();

	// on request refresh server list
	void OnRequestRefreshServerList();

	// on request refresh channel list
	void OnRequestRefreshChannelList();

	// on request leave server
	void OnRequestLeaveServer();

	// on request character adress
	void OnRequestCharacterAddress();

	// on save profile user
	void OnSaveUserProfile();

	// on save profile character
	void OnSaveCharacterProfile();

	// on request chatgroup create
	void OnRequestChatGroupCreate();
	
	// on request chatgroup invite
	void OnRequestChatGroupInvite();
	
	// on request chatgroup join
	void OnRequestChatGroupJoin();
	
	// on request chatgroup leave
	void OnRequestChatGroupLeave();
	
	// on request chatgroup call
	void OnRequestChatGroupCall();

	// on request chatgroup member
	void OnRequestChatGroupMember();
	
	// on notify multichat
	void OnNotifyMultiChat();
	
	// on request apex
	void OnRequestApex();
	
	// on request battlegroups
	void OnRequestBattleGroups();
	
	// on request battlegroup create
	void OnRequestBattleGroupCreate();
	
	// on request battlegroup invite
	void OnRequestBattleGroupInvite();
	
	// on request battlegroup join
	void OnRequestBattleGroupJoin();
	
	// on request battlegroup leave
	void OnRequestBattleGroupLeave();
	
	// on request battlegroup info
	void OnRequestBattleGroupInfo();
	
	// on battlegroup ready
	void OnRequestBattleGroupReady();
	
	// on battlegroup start search
	void OnRequestBattleGroupStartSearch();
	
	// on battlegroup challenge
	void OnRequestBattleGroupChallenge();
	
public:
	// change status
	void ChangeStatus(uint status);

	// change address
	void ChangeAddress(uint server_id, uint channel_id, uint room_id);

	// notify fcm
	void NotifyFCM();

	// chat
	void NotifyChat(const char * to, const char * name, const char * msg);

	void NotifyLoopMsg(InfoConnection * info);

	// is online
	bool IsOnline();

	// ForceDisconnect
	void ForceDisconnect(const char * error_message);

	// team member join
	int TeamMemberJoin(ClientConnection * leader);

	// team member leave
	bool TeamMemberLeave(ClientConnection * member);

	// team member info change
	bool TeamMemberInfoChange(ClientConnection * member);

	// team change leader
	bool TeamChangeLeader(ClientConnection * leader);

	// write team member info
	void WriteTeamMemberInfo(ClientConnection * member);

	// notify room preserve
	void NotifyRoomPreserve(uint channel_id, ushort room_id, byte slot_id, const char * invite_name);

	// notify room cancel preserve
	void NotifyRoomCancelPreserve(uint channel_id, ushort room_id, byte slot_id);

	// continue search room
	void ContinueSearchRoom();

	// on continue search room
	void FinishSearchRoom(uint server_id, uint channel_id, uint room_id);

	// notify update level list
	void NotifyUpdateLevelList();

	// notify rpc message
	void NotifyRPCMessage(int size, const char * data);

	// notify chatgroup leave
	void NotifyChatGroupLeave(uint chatgroup_uid);
	
	// notify chatgroup call
	void NotifyChatGroupCall(uint chatgroup_uid, const char *name, const char *msg);
	
	// notify apex client
	void NotifyApexClient(const char *pBuf, int nBufLen);
	
	// notify battlegroupinfo
	void NotifyBattleGroupInfo(BattleGroup *battlegroup);

	// notify billboard info
	void NotifyUpdateBillBoardInfo(const std::string & str, bool flag);
	
	// notify battlegroup leave
	void NotifyBattleGroupLeave(uint battlegroup_uid);
	
	// notify battlegroup kick
	void NotifyBattleGroupKick(uint battlegroup_uid);
	
	// notify battlegroup searching
	static void NotifyBattleGroupSearching(uint battlegroup_uid);
	
	// notify battlegroup game start
	static void NotifyBattleGroupGameStart(uint battlegroup_uid, uint server_id, uint channel_id, 
											uint room_id, byte change_room, byte is_challenge, uint group_id1, uint group_id2);
	static void NotifyHageBattleGroupGameStart(uint battlegroup_uid, uint server_id, uint channel_id, 
											uint room_id, byte change_room, byte is_challenge, uint group_id1, uint group_id2);
	
	// leave channel
	void LeaveChannel();

	// leave server
	void LeaveServer();
	
	// response refresh server list
	void ResponseRefreshServerList();

	// response refresh server list
	void ResponseRefreshChannelList();

	void UpdateFCMTime(int left_time);

	// [2015/10/9 dengxiaobo]
	void OnRequestMatching();
	void OnRequestCancelMatching(bool no_return = false);
	void ResponseMatching(uint error, bool matching, byte total_progress = 0);
	void ResponseCancelMatching(uint error, bool matching);

	void OnRequestMatchingTeamCreate();
	void OnRequestMatchingTeamJoin();
	void OnRequestMatchingTeamInvite();
	void OnRequestMatchingTeamLeave();
	void OnRequestMatchingTeamKick();
	void OnRequestMatchingTeamChangeLeader();

	void OnRequestLestPersonChannel();
	void OnRequestIntoMatchingTeam();

	void NotifyMatchingTeamLeave(int dwMatchingTeamId);
	void NotifyMatchingTeamKick(uint dwMatchingTeamId);

	void SetMatching(bool matching, uint matching_time = 0, bool matching_team = false, bool matching_random = false);
	// response matching progress
	void ResponseMatchingProgress(byte progress, byte total_progress);

	void OnRequestChangeMatchMap();
	void OnRequestChangeMatchGameType();

	// [2015/10/16 dengxiaobo]
	void NotifyGoToMetchingRoom(uint dwServerId, uint dwChannelId, uint dwRoomId, uint dwSlotId = 0);
	// end
	
	void OnRequestHageBattleGroupJoin();
	void OnRequestHageBattleGroupLeave();
	void OnRequestHageBattleGroups();
	void OnRequestHageBattleHappyJumpJoin();

public:
	// write clientbaseinfo
	void WriteClientBaseInfo(BinaryStream &stream);

public:
	uint uid;
	uint uid_in_channel;
	uint user_id;
	char user_name[user_name_length];
	char character_name[character_name_length];
	char character_group[group_name_length];
	uint character_group_id;
	uint character_group_level;
	uint character_id;
	uint character_server_id;
	uint character_channel_id;
	uint character_room_id;
	byte character_gender;
	uint character_level;
	int  character_exp;
	uint character_status;
	uint team_leader_uid;
	uint team_members[5];
	int state;
	uint rpc_call_id;
	uint fcm_online_minutes;
	uint fcm_offline_minutes;
	
	char head_icon[16];
	
	uint joined_battlegroup;
	byte battlegroup_state;// 0, 1, 2

	// [2015/10/9 dengxiaobo]
	uint m_dwMatchingTeamGroupId;
	bool matching;
	uint matching_level;	//p值
	uint matching_hege_level;		// 争霸赛p值
	uint matching_fighting_level;	// 普通匹配P值
	bool matching_random;
	bool matching_team;
	uint matching_time;
	// end

	uint created_chatgroup;
	std::set<uint> chatgroups;
	
	in_addr client_ip;
	char client_version[64];

	Gag gag;
	uint search_server_id;
	uint search_channel_id;
	SearchRoomOptions search_room_options;

	float list_refresh_time;

	float fcm_refresh_time;
	int	  fcm_left_time;

	std::string user_profile;
	std::string character_profile;

	char account[64];

	XLInfo xlinfo;
	byte first_game;
	byte is_vip;
	byte is_gm;
	byte is_PlayerCheckToday;
	byte net_bar_level;
	byte business_card;
	
	uint top;
	int fightnum;
	float win_rate;
	
private:
	XORNetworkEncoder xor_encoder;
	DesNetworkEncoder des_encoder;
};

// map for search client by name.
typedef __gnu_cxx::hash_map<const char *, ClientConnection *, __gnu_cxx::hash<const char *>, eqstr> ClientNameMap;

// group set
typedef std::multimap<const char *, ClientConnection *, lessstr> ClientGroupMap;
