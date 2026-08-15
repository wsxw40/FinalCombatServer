#pragma once
#include <vector>

const byte InvalidTeam = (byte)-1;

const float ZOMBIE_MODE_SAVE_DYING_TIME = 3.f;

const byte DUMMY_SERVER = 100;

const byte TD_RESBUILDING_SUBTYPE = 4;

uint GenerateDummyIndex();

struct KillInfo
{
	Vector3 position;

	byte mapid;
	int time;

	uint deadid;
	byte deadcareerid;

	uint killerid;
	byte killercareerid;
	int	 killerweaponid;

	static void DebugPrint(std::vector<KillInfo> &kill_infos)
	{
		if (log_get_output_level() < 4)
		{
			return;
		}

		log_write(LOG_DEBUG4, "KillInfo::DebugPrint() Start");

		for (uint i = 0; i < kill_infos.size(); i++)
		{
			log_write(LOG_DEBUG4, "\tkill_infos[%d].position : Vector3(%f, %f, %f)",
				i, kill_infos[i].position.x, kill_infos[i].position.y, kill_infos[i].position.z);
			log_write(LOG_DEBUG4, "\tkill_infos[%d].mapid : %d", i, kill_infos[i].mapid);
			log_write(LOG_DEBUG4, "\tkill_infos[%d].time : %d", i, kill_infos[i].time);
			log_write(LOG_DEBUG4, "\tkill_infos[%d].deadid : %d", i, kill_infos[i].deadid);
			log_write(LOG_DEBUG4, "\tkill_infos[%d].deadcareerid : %d", i, kill_infos[i].deadcareerid);
			log_write(LOG_DEBUG4, "\tkill_infos[%d].killerid : %d", i, kill_infos[i].killerid);
			log_write(LOG_DEBUG4, "\tkill_infos[%d].killercareerid : %d", i, kill_infos[i].killercareerid);
			log_write(LOG_DEBUG4, "\tkill_infos[%d].killerweaponid : %d", i, kill_infos[i].killerweaponid);
		}

		log_write(LOG_DEBUG4, "KillInfo::DebugPrint() End");
	}
};

struct HoldPointInfo
{
	byte owner_team;
	byte snatch_team;
	float snatching_timer;
	AxisAlignedBox aabb;
};
enum StreetKingState
{
	StateNone = 0,
	StateKiller,
	StateRandom,
};

struct StreetKingInfo
{
	byte last_street_king_uid;
	byte current_street_king_uid;
	byte next_round_street_king;
	Vector3 street_king_last_position;
	int  select_state;

	StreetKingInfo()
	{
		current_street_king_uid = 0;
		next_round_street_king = 0;
		last_street_king_uid = 0;
		select_state = StateNone;
	}
	
	void Clear()
	{
		current_street_king_uid = 0;
		next_round_street_king = 0;
		last_street_king_uid = 0;
		select_state = StateNone;
	}

};

struct TrapInfo
{
	Vector3 position;
	byte type;
	byte uid;
	byte team;
	double time;
};

uint GenerateDummyIndex();

static const float CHECK_GAME_END_INTERVAL = 10.f;

typedef void (*ClientNotifyHandler)(Client &client, const void *userdata);

class Game : public TcpConnection, public BinaryStream
{
public:
	enum GameType
	{
		kRandom = -1,
		kTeam = 0,
		kHoldPoint,
		kPushVehicle,
		kNovice,
		kTeamDeathMatch,
		kBossMode,
		kKnifeMode,
		kBombMode,
		kStreetBoyMode,
		kZombieMode,
		kBossPVE,
		kCommonZombieMode,
		kBossMode2,
		kItemMode,
		kEditMode,
		kTDMode,
		KMoonMode,
		kAdvenceMode,
		kSurvivalMode,
		kGameTypeCount,
	};

	enum VictoryRule
	{
		kKillNum = 0,
		kPlayTime,
		kPlayRound,
		kVictoryRuleCount,
	};

	enum ScoreType
	{
		kKills,
		kRounds,
	};

	enum SpecialMode
	{
		kNormal = 0,
		kSpecialModeCount,
	};

	enum SlotStatus
	{
		kClosed,
		kOpen,
		kView,
	};

public:
	// constructor
	Game();

	// destructor
	~Game();

	// start network
	int Run();

	// on message
	void OnMessage();

	// on connected
	void OnConnected();

	// on close
	void OnDisconnected();

	// notify client leave
	void NotifyClientLeave(Client & client);

	// get client
	Client* GetClient(byte uid);

	// get client by id
	Client* GetClientById(uint id);
	
	// get client by pid
	Client* GetClientByPId(uint pid);

	// get bot by index
	Client* GetBotByIndex(int index);

	// get bot by cid
	Client* GetBotByCid(int cid);

	uint GetAliveBossCid(int index);

	// create bot
	Client* CreateBot(CharacterInfo &charinfo);

	// get score type
	byte GetScoreType();
	
	// 
	void BuildServerScriptList(const std::string &text);
	
	// 
	void SetServerScriptStringValue(const std::string &key, const std::string &value);
	
	// 
	bool GetServerScriptStringValue(const std::string &key, std::string &value);
	
	// 
	void SetServerScriptNumberValue(const std::string &key,float value);
	
	// 
	bool GetServerScriptNumberValue(const std::string &key, float &value);

public:
	// request update keywords
	void RequestUpdateKeywords();

	// request client enter
	void RequestClientEnter();

	// request stage clear
	void RequestStageClear(byte team);

	// request stage clear
	void RequestStageQuit(Client & client);

	// request save map
	void RequestSaveMap(MapInfo & oMapInfo);

	// request end novice
	void RequestEndNovice(Client & client, bool issuccess);

	// response stage quit
	void ResponseStageQuit();

	// response save map
	void ResponseSaveMap();

	// update kill info
	void UpdateKillInfo();

public:
	// write binary rpc
	void WriteBinaryRPC(const char * url);

	// end RPC userdata
	void EndRPCUserdata();

	// response stage clear
	void ResponseStageClear();

	// response binary rpc
	void ResponseBinaryRPC();

	// forward client message
	void ForwardClientMessage(Client & client);

	// forword all message
	void ForwordAllMessage();

	// broadcast clients
	void BroadcastClients(ClientNotifyHandler handler, void *userdata, Client *client_skip);
	
	// on forward client message
	void OnForwardClientMessage();

	// request for disconnect
	void RequestDisconnect();

	// on response disconnect
	void OnResponseDisconnect();

	// on update character ping
	void OnUpdateCharacterPing();

private:
	// rpc userdata position
	char * rpc_userdata_position;

public:
	// on client state changed
	void OnClientStateChanged();

	// on play time changed
	void OnPlayTimeChanged();

	// on team kills changed
	void OnTeamKillsChanged(int team);

	// on team alive changed
	void OnTeamAliveChanged(int team, bool round_start = false);

	// on round start
	void OnRoundStart(bool first_round = false);

	// on round end
	void OnRoundEnd(bool timeout, byte team);

	// on game start
	void OnGameStart();

	// write die buff
	void WriteDieBuff();

	// on game end
	void OnGameEnd(int team);

	// update
	void Update(float delta);

	// on update character data
	void OnUpdateCharacterData();

	// on request client leave
	void OnRequestClientLeave();

	// on kick client start
	void OnKickClientStart(byte start_uid, byte kicked_uid, byte kicked_reason);

	// on kick client end
	void OnKickClientEnd();

	void OnPushVehicleUpdate(float delta);

	float GetCarVelocity(int player_count);

	PushVehicleInfo GetNextPushVehicleTransform(int team);

	PushVehicleInfo GetPrevPushVehicleTransform(int team);

	bool IsAtPushVehicleGoal(int team);

	void CheckRoundEnd();

	void OnGameEndTimer(int team);

	void OnKickPerson(int person_id, int room_id, float time = 180.f);

public:
	// supply ready
	void SupplyReady();

	void SetBossAliveNumber(int num);

	int GetBossAliveNumber();

	void NotifyBossAliveChanged();

	void AddDropGoldWithSpeed(const Vector3& to,const Vector3& from, int type, int value, byte owner_id, short team);

	bool AddZombieDropGold(const Vector3& vDir);

	void CheckAllCure();

	void ClearPlantState();

	void ClearDefusingState();

	void StartDefusing(byte uid, float t);

	bool CheckIsInBombArea(const Vector3& pos);

	bool CheckIsInZombieArea(const Vector3& pos);

	void UpdateKingPosition();

	void UpdateRoundStartTimer(float delta);

	bool StreetKingSelect();

	bool CommonZombieSelect();
	
	bool BossMode2Select();

	void UpdateZombieModeSaveDying(float delta);

	bool ZombieSelect();

	void NotifyZombieModeStepTwo();

	void NotifyZombieModeGameStart();

	bool CheckSupplyType(int type);

	bool CheckAliveBossInfo();

	int GetNowPhaseBossNum();

	void DestoryDummy(uint dummy_id);

	void DestoryAllDummy();

	void DestoryDummyByOwner(byte owner_id, bool need_end_stepfather = false);
	
	void ServerDummyCreate(const ServerDummyCreateInfo& dummyinfo);

	void ServerDummyCreateStepFather(const ServerDummyCreateWithStepFatherInfo& dummyinfo, byte team);

	void ChangeStepFatherDummyOwner(byte owner_id);

	void WriteLogClient(const char * format, ...);

	void picked_count_add();

	int get_picked_count(){return picked_count;}

	void RandSupplyType(Supply *supply);

	void TrapTrigger(int index, byte uid);
	
	void EndRequestMatchClient();
	
private:
	// write stage clear info
	void WriteStageClear(byte winner);

public:
	SOCKET listening_socket;

	Client* clients;
	Client* bot_clients;

	std::vector<int> team_ids[2];

	int state;
	ServerConfig config;
	std::vector<KillInfo> kill_infos;

public:
	ObjectPool<DroppedWeapon> dropped_weapon;
	ObjectPool<SupplyObject> supply_list;
	ObjectPool<SupplyObject> common_zombie_supply_list;
	ObjectPool<SupplyObject> common_zombie_supply_list2;
	ObjectPool<DroppedSupply> dropped_supply;

public:
	int room_id;
	int server_id;
	int channel_id;
	int channel_socket;
	uint host_character_id;
	char server_address[16];

	RoomOption room_option;
	LevelInfo level_info;
	byte game_type;
	int rule_value;
	byte special_mode;
	short round_rebirth_time_max;
	bool team_hurt;
	float end_time;
	int idle_kick_open;

	// [2015/10/20 dengxiaobo]
	byte m_bIsMatching;
	// end

	bool check_game_balance;

	std::vector<Transform>	start_point[2];
	std::vector<Transform>	boss2_rebirth_point[4];
	std::vector<Transform>  zombie_step_two_point;
	
	int boss2_boss_rand_start;
	int boss2_rebirth_rand_start[4];
	
	int cur_holdpoint;
	int cur_holdpoint_diffnum;
	std::vector<HoldPointInfo> hold_points;

	int team_kills[2];
	int team_rounds[2];
	float team_timer[2];

	AxisAlignedBox vehicle_aabb[2];
	//AxisAlignedBox vehicle_kick_aabb[2];
	PushVehicleInfo vehicle_current_info[2];

	int			   current_vehicle_id[2];
	float			vehicle_timer;
	float			add_up_delta;
	float			vehicle_timer_interval;

	double play_time;
	double play_time_old;
	double cure_time;
	float time_max;
	bool time_out_finished;
	
	bool boss2_showtime;

	int round;
	int round_time;
	float round_end_time;
	bool round_playing	: 1;
	float round_start_time;
	float round_start_total_time;

	bool playing;
	bool waiting;
	bool loading;
	bool stage_cleared;

	float waiting_time;
	float shutdown_timer;
	int client_connected_count[2];
	int client_ready_count[2];

	double update_time;

	uint first_kill;
	uint first_died;

	int debug_level;

	// vote
	bool is_voting;
	std::vector<short> vote_clients;
	byte kick_sponsor_uid;
	byte kick_client_uid;

	float vote_timer;

	int game_start_time;

	bool novice_isupdate;

	float gold_time;
	
private:
	int team_holdpoint_num[2];

	float vehicle_update_timer;
	
	uint group_id_t1;
	uint group_id_t2;

public:
	bool write_log_to_client;

	float special_person_start_timer;

	int	  special_person_start_time_count;

	bool  special_person_flashed;

	float game_end_timer;

	int   game_end_team;

	float check_game_end_timer;

	float item_update_timer;
	float item_update_timer2;

	// boss gold
	int boss_life_gold_count;

	// bomb mode
	bool bomb_planting;
	float bomb_plant_timer;

	bool bomb_planted;
	float bomb_explode_timer;
	byte bomb_owner_id;
	Vector3 bomb_planted_pos;
	
	bool bomb_defusing;
	float bomb_defusing_timer;
	byte bomb_defusing_uid;

	// for common zombie
	bool can_super;
	bool human_super;
	bool zombie_super;
	std::map<uint, Vector3> somg_area;

	std::vector<TrapInfo> trap_info;

	std::vector<AxisAlignedBox> bomb_aabb_area;

	// street boy mode
	StreetKingInfo street_king_info[2];

	float sync_street_king_timer;

	//zombie mode
	bool zombie_step_two_flag;
	std::vector<AxisAlignedBox> zombie_target_area;

	int zombie_target_area_id;
	
	// ServerScript(请勿直接使用！！！)
	std::list<ServerScript> server_script_list;
	
	// ServerScriptValue(请勿直接使用！！！)
	std::map<std::string, std::string> server_script_stringvalue;
	std::set<std::string> server_script_stringvalue_dirty;
	
	std::map<std::string, float> server_script_numbervalue;
	std::set<std::string> server_script_numbervalue_dirty;

	std::map<uint, DummyBaseInfo> dummy_object_map;
	std::vector<DummyCreateAction> dummy_create_vector;

	// item mode
	bool is_hahatime;
	bool start_hahatime;
	
	// td mode
	int max_reshp;
	int cur_reshp;

	byte cServerType;
	
	bool m_bEndMatchClient;


private:
	int boss_mode_human_alive_number;
	
	bool is_request_disconnect;
	float request_disconnect_timeout;

	// for boss pve
	float boss_revive_time;
	bool isnewphase;
	int now_phase;
	std::vector<BossActionInfo> now_Alive_boss[4];
	bool bosspve_end_game;
	int picked_count;
};

extern Game server;
