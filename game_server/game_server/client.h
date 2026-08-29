#pragma once

static const float NEVER_SPAWN = -10000.f;

const int ITEM_REDBOTTLE = 39;
const int ITEM_AMMO = 40;
const int ITEM_CARRIER = 41;
const int ITEM_ATTACK = 43;
const int ITEM_ATTACK_SPEED = 44;
const int ITEM_BLOOD = 45;
const int ITEM_RESISTANCE = 46;
const int ITEM_MOVE_SPEED = 47;

struct MapItemInfo
{
	int DummyObjectType;
	int SystemId;
	char ResKey[res_key_length];
	float Position[3];
	float Rotation[4];
};

struct MapInfo
{
	uint character_id;
	std::vector<MapItemInfo> m_aItemInfo;
};

struct SyncBossAction
{
	Vector3 startpos;
	Vector3 endpos;
	int move_time;
	float total_time;
	float startrot;
	float endrot;
	int action_id;

	SyncBossAction()
	{
		action_id = -1;
	}
};

struct ZombieLevelUp
{
	float Resistance[3];
	float Recover[3];
	float Speed[3];
	float JumpHeight[3];
	float FireTime[3];

	float KingResistance[3];
	float KingRecover[3];
	float KingSpeed[3];
	float KingJumpHeight[3];
	float KingFireTime[3];

	ZombieLevelUp()
	{
		Resistance[0] = 0.0f;
		Resistance[1] = 0.05f;
		Resistance[2] = 0.1f;

		KingResistance[0] = 0.0f;
		KingResistance[1] = 0.1f;
		KingResistance[2] = 0.2f;

		Recover[0] = 100.0f;
		Recover[1] = 200.0f;
		Recover[2] = 300.0f;

		KingRecover[0] = 200.0f; 
		KingRecover[1] = 250.0f;
		KingRecover[2] = 300.0f;

		Speed[0] = 6.2f;
		Speed[1] = 6.3f;
		Speed[2] = 6.4f;

		KingSpeed[0] = 6.3f;
		KingSpeed[1] = 6.4f;
		KingSpeed[2] = 6.5f;

		JumpHeight[0] = 2.0f;
		JumpHeight[1] = 2.2f;
		JumpHeight[2] =	3.4f;

		KingJumpHeight[0] = 2.0f;
		KingJumpHeight[1] = 2.2f;
		KingJumpHeight[2] =	2.4f;

		FireTime[0] = 0.0f;
		FireTime[1] = (-2.0f) / 11.0f;
		FireTime[2] = (-4.0f) / 11.0f;

		KingFireTime[0] = 0.0f;
		KingFireTime[1] = (-1.0f) / 6.0f;
		KingFireTime[2] = (-1.0f) / 3.0f;
	}
};

class ServerDummySyncData;

class Client : public BinaryStream
{
	enum HitTargetType
	{
		Character = 0,
		Dummy,
	};

public:
	// constructor
	Client();

	// destructor
	~Client();

	// connect
	void Connect();

	// disconnect
	void Disconnect();

	// is connected
	bool IsConnected();

	// is ready
	bool IsReady();

	// is alive
	bool IsAlive();

	// is die
	bool IsDied();

	// can hurt
	bool CanHurt(Client* client, bool team_hurt);

	bool CanHurtStrange(Client* client, bool team_hurt);

	bool CanHurtDummy(byte owner_id, bool team_hurt);

	// on leave
	void OnLeave();

	// on position changed
	void OnPositionChanged();

	// on rotation changed
	void OnRotationChanged(const Quaternion & old_rot, const Quaternion & new_rot);

	// on hit
	void OnHit(int damage);

	// on action
	void OnAction();

	// update
	void Update(float delta);

	// on message
	void OnMessage();

	// get weapon damage
	float GetWeaponDamage(const Weapon & weapon, float distance, Client* hurt_client, bool *pboost, byte hurt_type = 0, bool back = false, float range_ratio = 1.f);

	// damage before resistance
	float DamageBeforeResistance(float damage, const Weapon & weapon, Client* hurt_client);
	
	// damage after resistance
	float DamageAfterResistance(float damage, int weapon_type, byte from_uid);

	// take damage
	bool TakeDamage(Client* from, int* damage, int part, const Weapon &weapon, bool boost = false, bool sustainhurt = false);

	// spawn
	void Spawn(const Transform & transform, Weapon* bomb = NULL, bool round_start = false, bool has_bomb = false, bool doit = false);

	// set team
	void SetTeam(byte team);

	// recover
	bool Recover(int hp, byte recover_type = kRecoverNone, byte from = 0, float userdata = 0);

	// sustain hurt
	bool TakeSustainHurt(Client *from, int damage, byte hurttype);

	// on connected
	void OnConnected();

	// on close
	void OnDisconnected();

	// update magic
	void UpdateMagic();

	// read client
	Client* ReadClient();

	DummyBaseInfo* ReadDummyBaseInfo();
	
	// get charinfo
	const CharacterInfo& GetCharacterInfo() const;

	bool IsSingleCharacterInfoExist(int career);

	// set charinfo
	void SetCharacterInfo(CharacterInfo &charinfo);
	
	// set charinfo
	bool AddSingleCharacterInfo(SingleCharacterInfo &info);

	// get cur charinfo
	SingleCharacterInfo& GetCurCharinfo();

	// set cur charinfo
	void SetCurCharInfo(uint career, bool can_use_all = false);

	// get default charinfo
	const SingleCharacterInfo& GetDefaultCharInfo() const;

	// use default charinfo
	void UseDefaultCharInfo();
	
	// get cur pack
	byte GetCurPack() const;
	
	// set cur pack
	void SetCurPack(byte val);
	
	// get cur pack info
	PackInfo& GetCurPackInfo();
	
	// get cur weapon
	byte GetCurWeapon() const;
	
	// set cur weapon
	void SetCurWeapon(byte val);
	
	// get cur weapon info
	Weapon& GetCurWeaponInfo();
	
	// get weapon info
	Weapon* GetWeaponInfo(byte val);
	
	// replace weapon
	void ReplaceWeapon(byte slot, Weapon &weapon);
	
	// remove weapon 
	bool RemoveWeapon(byte val);
	
	// get skilleffect
	SkillEffect& GetSkillEffect();

	// initialize
	void Initialize();

	// send character info
	void SendCharacterInfo(Client & client);

	// game end
	void GameEnd(byte team);

	// request leave game
	void RequestLeaveGame();

	// leave game
	void LeaveGame(bool cleared);

	// dropped gun destroy
	void DestroyDroppedWeapon(uint uid);

	// round start
	void RoundStart(float wait_time);

	// round start play
	void RoundStartPlay();

	// round end
	void RoundEnd(byte team);

	// 
	void GameStartTimerNotify(int i);

	// recover stop
	void RecoverStop();

	// drop gun
	void DropWeapon(byte weapon_id, const Vector3 & pos, float rot);

	//drop supply
	void AddZombieDropSupply(byte supply_type, const Vector3 & pos, int value);

	// add dropped supply
	void AddDropSupply(const DroppedSupply * dropped_supply);

	// add dropped weapon
	void AddDroppedWeapon(const DroppedWeapon * dropped_weapon);

	// add supply object
	void AddSupplyObject(const SupplyObject * supply, byte owner_id = 0, short team = -1);

	// destroy supply object
	void DestroySupplyObject(uint id);

	// on kill
	void OnKill(Client * kill, int &part, const Weapon & weapon, byte assistuid = 0, bool boost = false, bool sustainhurt= false);

	// on died
	void OnDied(Client * killer);
	
	// on skilleffect changed
	void OnSkillEffectChanged();
	
	// 
	void DoCharacterDropItem();

	// ammo recover
	bool AmmoRecover(int type, int count);

	// ammo disappear
	void AmmoDisappear();

	void HPItemDisappear();
	
	// armor recover
	void ArmorRecover(int type, int count);

	// kick client start
	void KickClientStart(byte start_uid, byte kicked_uid, byte kicked_reason);

	// kick client end
	void KickClientEnd(byte start_uid, byte kicked_uid, byte result);

	// suicide
	void Suicide();

	void SkillHurt(Client* from, int damage);

	// sync data
	void SyncPlayerData();

	// sync hold point info
	void SyncHoldPointInfo();

	// update vehicle point info
	void UpdateVehicleInfo();
	
	// sync server script value
	void SyncServerScriptValue(byte full_syn = 0);

	void InitializeVehicleInfo();

	void OnHitBack(const KickInfo& info);
	void NotifyBossModeAliveNumber(int num);
	
	void ParseNoviceOperation(int index);
	
	void ParseItemMode_ZiBao();

	void ParseSaveMap();

	void NotifyBossFlash();
	
	void NotifBoss2Flash(int spawn_slot);
	
	void NotifyBoss2Showtime();

	void NotifyStreetKingFlash();

	void NotifyZombieFlash(int player_count, bool is_first);

	void NotifyCommonZombieFlash(const Vector3 position);

	void NotifyCommonZombieHumanDie(byte from, byte to, byte part, Weapon weapon);

	void NotifyCommonKingZombieFlash(const Vector3 position);

	void NotifyCommonZombieLevelChange(byte is_up);

	void NotifyCommonZombieEnergyChange();

	void NotifyCommonZombieSuper(const Vector3 position);

	void NotifyCommonHumanSuper();

	void NotifyCommonKingZombieHalfFlash();

	void CommonZombieUnableState();

	void SetCommonZombieEnergy(float value);

	void SetCommonZombieLevel(int value);

	void SetHumanEnergy(int value);

	// update player ping
	void UpdatePlayerPing(uint value);
	
	void UpdatePlayerSpeed();

	void CheckCure();

	void ResetAllCure();

	void PlantBombSuccess(float t);

	void DefuseBombSuccess();

	void BombExploded();

	void CancelPlantBomb();
	
	float GetCurePower();
	
	void ModifyCurePower(float power);
	
	void ClearCurePower();

	void ZombieDyingState(byte from_id);

	void StartSaveDying(byte dying_uid);
	
	void CancelSaveDying(byte dying_uid);

	void ZombieModeRebirth(byte saver_uid);

	void ClearZombieSavingState();

	void HumanEnergyChange();

	void HumanPowerUp();
	
	void Boss2Cleanup();
	
	void Boss2DoPassiveSkill();
	
	void Boss2DoDefenceUp();
	
	void Boss2SyncData();
	
	void ItemModeSyncData();

	// HackFix
	void ApplyHackFix();

	// bot
	// on bot create
	void OnBotCreate(CharacterInfo &charinfo);

	// on bot destroy
	void OnBotDestroy();

	// on bot spawn
	void OnBotSpawn(const Vector3 & pos);

	// bot update
	void BotUpdate(float delta);
	
	// set boss cid
	void SetBosspveCareerid(uint cid);

	uint GetBosspveCareerid();

	void SetBossActionInfo(std::vector<BossActionInfo> & info);

	void NotifyDummyCreate(const DummyBaseInfo& dummy);

	void NotifyDummyDestory(uint uid);

	void DummyTakeDamage(int damage, const Weapon& w, DummyBaseInfo* dummy, bool isboost);
	
	void NotifyServerDummySyncUpdate(uint dummyid, const ServerDummySyncData& dummydata);

	void NotifyDummyChangeOwner(uint dummyid, byte new_owner);

	InGameItemInfo* GetBagItem(uint sid);

	bool	UseItem(uint sid, ushort count);

	bool RefreshBagItem(uint sid);		// sid=0刷新所有物品数据

	bool ItemIsCDTime(const InGameItemInfo* item_info, double now_time);

	bool ItemIsRedBottle(const InGameItemInfo* item_info);

	bool ItemIsAmmo(const InGameItemInfo* item_info);

	bool ItemIsCarrier(const InGameItemInfo* item_info);

	bool ItemIsBuffer(const InGameItemInfo* item_info, int buff_type);

	bool IsCarrierMode();

	void AddSkill(EEffect type, float timer);

	void ChangeGhost(Client *from, const Weapon* w = NULL, int part = 0, bool boost = false, bool sustainhurt = false);

	bool CanBeGhost();
	
private:
	void ParseSyncPlayerData();
	void ParseShoot();
	void ParseFlameShoot();
	void ParseHurt();
	void ParseGrenadeThrowIn();
	void ParseGrenadeThrowStop();
	void ParseGrenadeThrowOut();
	void ParseGrenadeHurt();
	void ParsePoke();
	void ParsePokeHurt();
	void ParseDummyPokeHurt();
	void ParseReload();
	void ParseReloadReady();
	void ParseDropWeapon();
	void ParseSelectWeapon();
	void ParsePickUpWeapon();
	void ParseChat();

	void ParseKickBack();
	void ParseFlashBright();
	void ParseCameraFovChanged();

	void ParsePickUpSupplyObject();
	void ParsePickUpSupplyObjectNew();
	void ParseUseSkill();
	void ParseUseItem_ItemMode();
	void ParseMoonBoss();
	void ParseUseSkillSuperMan();
	void ParseCancel_Invisible();
	void ParseUseSmog();
	void ParseSomgAreaCancel();
	void ParseSkillSuperManSuccess();
	void ParseChangePack();

	void ParseOpenMessageClient();

	void ParseKickClientStart();
	void ParseKickClientVote();

	void ParseSuicide();

	void ParseRadioReport();

	void ParseSpawnConfirm();

	void ParseActionOn();

	void ParseChangeCareer();

	void WriteVector3FP(const Vector3 & position);
	void ReadVector3FP(Vector3 & position);

	void WriteCharacterRotation(const Quaternion & rotation);
	void ReadCharacterRotation(Quaternion & rotation);

	//////////////////////////////////////New Edited Start/////////////////////////////////////////
	void ParseProjectedAmmoOut();
	void ParseNeedDieBuff();
	void ParseProjectedAmmoDestroy();
	void ParseProjectedAmmoUpdate();
	void ParseProjectedAmmoHurt();
	void ParseProjectedProdHurt();
	void ParsePlayerAnimationStart();
	void ParsePlayerAnimationEnd();
	void ParseCallDoctor();
	void ParseCureCharacter();
	void ParseStopBurn();
	void ParseNoviceOperation();
	void ParseDrumCheck();
	void ParseDrink();

	void ParseStartPlantBomb();
	void ParseCancelPlantBomb();
	void ParseStartDefuseBomb();
	void ParseCancelDefuseBomb();

	void ParseStartSaveDying();
	void ParseCancelSaveDying();
	
	void ParseSpray();
	void ParseChangeTeam();
	void ParseUseSpawnCoin();

	void ZombieSkill_Bomer();

	void ZombieBomerHurt(Client* owner);
	void ParseChargeSomething();
	void ParseSkillKickBack();
	
	void ParsePVEAmmoOut();
	void ParsePVEAmmoDestroy();
	void ParsePVEAmmoHitHurt();
	void ParsePVEAmmoExplodeHurt();
	void ParsePVEAmmoUpdate();
	void ParseCutHurt();
	void ParseDummySyncCreate();
	void ParseDummySyncDestory();
	void ParseDummySyncUpdate();
	void ParseGunTowerShoot();
	void ParseDummyProjectedAmmoHurt();
	void ParseDummyGrenadeHurt();
	void ParseDummyProjectedProdHurt();
	void ParseCharacterHeal();
	void ParseTeleport();
	void ParseForceSpawn();
	void ParseMoonForceSpawn();
	void ParseUseItem();
	void ParseUseItemSurvival();
	void ParseUseItemSurvivalByGhost();

	//////////////////////////////////////New Edited End/////////////////////////////////////////
public:
	// The id part of this player cache is the position of this player in the server.
	// when a player died or leaves, the magic is increased by one. means that this
	// is a new character.
	union
	{
		byte uid;
		struct
		{
			int id : 6;
			int magic : 2;
		};
	};

	uint uid_in_room;
	uint uid_in_channel;

	int fcm_online_minutes;
	int fcm_offline_minutes;
	
	bool ignore_checkcheat;
	int checkcheat_num;
	float checkcheat_delta;
	float checkcheat_movespeed;
	float checkcheat_cleantimer;

	// sync flags
	byte sync_flags;
	float sync_time;

	float sync_holdinfo_timer;

	// player status.
	ushort status;
	ushort ping;

	Vector3 position;
	Vector3 position_last;
	Quaternion rotation;

	byte team;
	short num_kill;
	short num_die;
	short num_healthrecover;
	short num_ammorecover;

	short control_num;
	short revenge_num;
	short assist_num;
	short knife_kill;

	// SetCurCharInfo时填充
	float max_health_unscale;
	float ex_health_unscale;
	float resistance_flame;
	float resistance_explode;
	float resistance_bullet;
	float resistance_close;
	
	// 未受Effect影响的值
	int max_health_base;
	int ex_health_base;
	
	// 实际使用值
	int health;
	int max_health;
	int ex_health;
	int armor;
	int max_armor;
	int level;

	uint target_career;

	int leavereason;

	//BuffItem spray_info;

	//int spray_count_used;
	//int spray_count;

private:
	// 不要直接使用这些变量！！！
	CharacterInfo m_character_info;
	SingleCharacterInfo m_default_career;
	SingleCharacterInfo m_current_career;
	byte m_curpack;
	byte m_curweapon;
	
	float m_cure_power;
	float charge_skill_time;
	float charge_skill_alltime;
	float charge_skill_cd;
	uint bosspve_career_id;
	float itemmode_skill_cd;
private:
	SkillEffect effect;

public:
	float spawn_time;
	bool spawn_timing;
	float died_camera_time;
	
	float selfrecover_interval;

	int state;

	int start_time;

	int play_time;
	float life_time;

	int round;
	bool playing;
	bool connected;

	int kick_client_count;
	int fu_huo_bi;
	int fu_huo_bi_use_count;
	bool can_use_spawn_coin;
	
	bool is_voted;
	byte is_vip;
	byte business_card;
	byte is_gm;
	char head_icon[16];
	uint character_group_id;

	ScoreData data;

	byte pack_used;
	bool weapon_used;

	// grenade param
	bool grenade_throw_in_ing;
	bool grenade_throw_ready;
	bool grenade_throw_out_ing;

	float grenade_throw_in_timer;
	float grenade_throw_out_timer;

	float spray_index;
	float main_weapon_ammo_rate;

	// spawn confirm
	bool spawn_confirm;

	Gag gag;
	
	Weapon projected_ammo_info;
	Weapon gun_tower_hand_info;
	std::map<ushort, Weapon> projected_ammo_set;

	std::map<int, int> cure_list;
	
	std::map<int, int> pickup_dropitems;

public:
	float cure_limit_timer;

	float input_limit_timer;
	
	float assist_timer[2];
	byte  assist_uid[2];

	float recover_assist_timer;
	byte recover_assist_uid;

	int  control_person_id;
	int  control_person_old_id;
	std::vector<int> reveng_person_id;

	bool has_defuse_bomb_item;

	int accumulate_damage;

	int drinkrecover;
	int drinkrecovercount;
	
	float newDamage;
	float newResistance;

	// zombie mode
	bool zombie_mode_dying_state;
	byte zombie_mode_saving_dying_uid;
	byte zombie_mode_saver_uid;
	float zombie_mode_save_timer;
	int	 human_rebirth_counter;
	int	 zombie_rebirth_counter;

	float zombie_skill_cd;

	uint zombie_default_career;
	
	//common zombie mode
	int common_zombie_level;
	int common_zombie_energy;
	float common_zombie_energy_percent;
	int human_energy;
	bool is_zombie_king;
	Vector3 die_pos;
	bool zombie_unable_state;
	bool zombie_unable_state_flag;
	float unable_time;
	bool can_spawn;
	bool is_zombie_super;
	bool is_human_super;
	float powup_time;
	bool is_powup;
	bool can_Invincible;
	float super_human_skill_cd_time;
	bool is_common_zombie_spawn;
	int itemlevel_a, itemlevel_b, itemlevel_c, itemlevel_d;
	int buff_zombie_id;
	float somg_hurt_interval_time;

	RandFun rand_fun;

	bool ischarge_skill;

	// boss2
	float boss2_human_energy;
	float boss2_human_energy_max;
	int boss2_human_energy_level;
	
	int boss2_passiveskill_level[4]; //0:移动速度，1:跳跃高度，2:抗性，3:武器威力
	
	int boss2_initiative_type; //0:无，1:无敌，2:回复，3:威力增加，4:射速&装弹速度

	int boss2_strange_spawn;//-1:无，0:固定火箭塔，1:固定炮塔，2:炮塔UAV，3:机枪UAV
	
	static float boss2_defence_energy;
	static float boss2_defence_energy_max;
	static int boss2_defence_energy_level;
	
	bool boss2_strange_spawn_use;
	
	// item mode
	float itemmode_energy;
	float itemmode_energy_max;
	//-1:无, 0:生命补给（大），1:生命补给（小），2:增加移动速度，3:无敌，4:自爆，5:隐身，6:范围内对方减速
	//7:范围内对方混乱（鼠标），8:范围内对方混乱（键盘），9:范围内对方中闪光弹，10:增加跳跃高度，11:范围内对方无法射击
	//12:范围内对方无法跳跃，13:随机出现一种效果，14:变身成为黑暗游侠
	//15:除自己外全体减速，16:除自己外全体混乱（鼠标），17:除自己外全体无法跳跃，18:除自己外全体无法开火，19:无敌+威力加强
	int itemmode_item_slot;
	
	bool itemmode_zibao;
	bool ismoonboss;
	bool ismoonbossflag;

	//for survival mode
	int revive_count;

public:
	std::vector<BossActionInfo> alive_boss_actioninfo;
	
	short nowactionindex;
	SyncBossAction sycn_boss_action;
	ZombieLevelUp zombie_level_up_info;
	float total_action_timer;
	float action_timer;
	float old_action_timer;
	bool is_pveboss;
	int die_counter;//死亡计数器，每死一次加一,杀掉一个敌人后清零
	int die_buff_counter;//添加死亡buff计数器
	int alldamage;//单局总伤害

	//for kAdvenceMode
	int CCoinCount;
	int MedalCount;
	int WrenchCount;
	int BoxACount;
	int BoxBCount;
	int BoxCCount;
	int BoxDCount;

	//for kSurvivalMode
	int ghostfirecount;
	int beghostcount;
	bool ghostflag;
	bool isghost;
	float spawntimebySurvival;

	std::vector<uint> m_vecContinueKill;
	
	bool m_bRequestLeaved;
};

