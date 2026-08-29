#pragma once

class ClientConnection;

class BattleGroup
{
public:
	// constructor
	BattleGroup();
	
	// destructor
	~BattleGroup();
	
public:
	bool Initialize(ClientConnection *client, const RoomOption &op);
	
	bool AddCharacter(ClientConnection *client);
	
	bool DelCharacter(ClientConnection *client);
	
	void OnUpdate(double time);
	
public:
	bool IsEmpty();
	
	bool IsPlaying();
	
	bool IsSearching();
	
	bool CanStartSearch();
	
	bool StartPlay(uint battlegroup_uid, bool is_challenge);
	
private:
	void Terminate();
	
	void ChangeOwner(ClientConnection *client);
	
public:
	static void WriteBattleGroupInfo(BattleGroup *battlegroup, BinaryStream &stream);
	
public:
	uint uid;//do not change this!!!
	
	uint server_id;
	uint channel_id;
	uint room_id;
	
	RoomOption roomop;
	
	uint ownerclient_uid;
	uint group_level;
	uint group_id;
	char group_name[group_name_length];
	
	char vs_group_name[group_name_length];
	
	bool is_searching;
	float searching_interval;
	
	std::set<uint> characters_uid;
	
	static std::set<uint> battlegroup_list;
};

class MatchingTeamGroup
{
public:
	MatchingTeamGroup();

	~MatchingTeamGroup();
public:
	bool Initialize(ClientConnection *client, const RoomOption &op);
	
	bool AddCharacter(ClientConnection *client);
	
	bool DelCharacter(ClientConnection *client, bool bCancleMatch = true);
	
public:
	bool IsEmpty();

	bool IsMatching();

	void SetMatching(bool matching){is_matching = matching;}
	
private:
	void Terminate();

	void ChangeOwner(ClientConnection *client);

public:
	uint uid;//do not change this!!!

	uint server_id;
	uint channel_id;
	uint room_id;

	bool is_matching;

	RoomOption roomop;

	uint ownerclient_uid;

	std::set<uint> characters_uid;
protected:
private:
};

class HappyJumpBattle
{
public:
	HappyJumpBattle();
	~HappyJumpBattle();

	void Reset();
	void JoinGame(ClientConnection* pClient, RoomOption& op);
	
	std::set<uint> m_setParticipator;
	std::set<uint> m_setRoomIds;
	
	static bool s_bReadyTime;
	static void EndTime(void* pArg);
};
class HageBattleGroup
{
public:
	// constructor
	HageBattleGroup();
	
	// destructor
	~HageBattleGroup();
	
public:
	static bool Initialize(BinaryStream& refStream);
	
	bool AddCharacter(ClientConnection *pClient, RoomOption& op);
	
	bool DelCharacter(ClientConnection* pClient);
	
	void OnUpdate(double time);
	
	void OnRoomCreate(uint dwClientId, uint dwCreatedRoomId);
	
public:
	bool IsEmpty();
	
	bool IsPlaying();
	
	bool IsSearching();
	
	bool CanStartSearch();
	
	static void DelayStart(void* pArgs);
	
	static void StartPlay(void* pArgs);
	
private:
	void Terminate();
	
	void ChangeOwner(ClientConnection *client);
	
public:
	static void WriteHageBattleGroupInfo(HageBattleGroup *pBattleGroup, BinaryStream& refStream);
	
public:
	uint uid;//do not change this!!!
	
	uint dwServerId;
	uint dwChannelId;
	uint dwRoomId;
	
	RoomOption oRoomOp;
	
	uint ownerclient_uid;
	uint dwGroupLevel;
	uint dwGroupId;
	uint dwGameType;
	uint dwMatchId;
	char szGroupName[group_name_length];
	
	char szVsGroupName[group_name_length];
	
	std::map<uint, bool> mapCharactersUid;		//<玩家user_id, 是否进入房间>
	
	static std::map<uint, std::map<uint, uint> > s_mapBattleGroupList;		//<战队id, <gametype, battleid> >
	static std::map<uint, std::pair<uint, uint> > s_mapMatchList;							//<dwMatchId, [uid1, uid2]>
	static std::map<uint, std::list<uint> > s_mapLevelList;
	
	static bool s_bReadyTime;												// 可以进入游戏了
	static HappyJumpBattle s_HappyJumpBattle;
};


