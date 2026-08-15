#pragma once

#include <vector>
#include <string>

#include "characterinfo.h"


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

struct Transform
{
	Vector3 position;
	float	rotation;
};

struct PointInfo
{
	byte		id;
	Transform	transform;
};

struct AABBPointInfo
{
	Transform	transform;
	Vector3		dimension;

};

struct PushVehicleInfo
{
	Vector3 position;
	Vector3 dir;
	int sliding;
	int player_count;
	float total_length;
	PushVehicleInfo()
	{
		total_length = 0.f;
		sliding = 0;
		player_count = 0;
	}
};

struct BossActionInfo
{
	int lineid;
	int move_timer;
	Vector3 position;
	float career_id;
	float action_timer;
	float rotangle;
	int action_id;
	BossActionInfo()
	{
		lineid = -1;
		move_timer = -1;
		rotangle = -1;
		action_id = -1;
	}
};

struct KickInfo
{
	float kick_time_interval;
	float kick_factor;
	Quaternion dir;
	float on_static_kick_y_offset;
	KickInfo()
	{
		kick_factor = 1.f;
		kick_time_interval = 1.f;
		on_static_kick_y_offset = 0.f;
	}
};



struct BannerPointInfo
{
	byte			id;
	AABBPointInfo	aabb;
};

enum SupplyType
{
	kSupplyNone 	= 0,
	kSupplyHp	 	= 1,
	kSupplyAmmo		= 2,
	kSupplyMedkit	= 3,
	kSupplyAllTools	= 4,
	kSupplyVehicle	= 5,
	kSupplyNovice	= 6,
	kSupplyGold		= 7,
	kSupplyDropWeapon = 8,
	kSupplyGoldWithForce = 9,
	kSupplyZombieGold = 10,
	kSupplyDropItem = 11,
	kSupplyCommonZombie = 12,
	kSupplyCommonZombie2 = 13,
	kSupplyMoonModeWin = 14,
	kSupplyCCoin = 15,
	kSupplyMedal = 16,
	kSupplyWrench = 17,
	kSupplyRandomBoxA = 18,
	kSupplyRandomBoxB = 19,
	kSupplyRandomBoxC = 20,
	kSupplyBoxA = 21,
	kSupplyBoxB = 22,
	kSupplyBoxC = 23,
	kSupplyBoxD = 24,
	kSupplySurvivalItemStart,
	kSupplySurvivalItemHp,
	kSupplySurvivalItemAmmo,
	kSupplySurvivalItemTrapAmmo,
	kSupplySurvivalItemTrapHP,
	kSupplySurvivalItemTrapExpose,
	kSupplySurvivalItemTrapBomb,
	kSupplySurvivalItemTrapDebuff,
	kSupplySurvivalItemRandom,
	kSupplySurvivalItemGhostFire,
	kSupplySurvivalIteminitiative,
	kSupplySurvivalItemEnd,
};

enum RecoverType
{
	kRecoverNone 		= 0,
	kRecoverSupplyHp	= 1,
	kRecoverCuregun		= 2,
	kRecoverSelf		= 3,
	kSupplyAmmo_1		= 4,
	kSupplyAmmo_2		= 5,
	kRecoverMinusMax	= 6,
	kRecoverVehicle		= 7,
	kRecoverNovice		= 8,
	kRecoverBaseHp      = 9,
	kRecoverWaitAddBlood = 10,
};

enum ItemEffectType
{
	kItemEffectNone = 0,
	kItemEffectPowerUp = 1,
};

enum SkillType
{
	kSkillNone = 0,
	kSkillSpeedUp = 1,
	kSkillControlReverse,
	kSkillCureGun,
	kSkillInvincible,
	kSkillRadar,
	kSkillTeleport,
	kSkillControlStickBomb,
	kSkillWindReverse,
	kSkillZombie,
	kSkillZombieCharge,
	kSkillHunterCharge,
	kSkillActiveSlot1,
	kSkillBoss2_S1,
	kSkillBoss2_S2,
	kSillCharacter_KC6,
};

enum RoomItemType
{
	kRoomItemNone = 0,
	kRoomItemGP = 9,
	kRoomItemExp = 10,
	kRoomItemGroupPK = 11,
	kRoomItemGroupChallenge = 12,
	kRoomItemPersonalPK = 13,
	kRoomItemPersonalChallenge = 14,
};

struct Supply
{
	Vector3			position;
	byte			type;
	std::string		name;
	int				value;
	float			random;
	float			skilltime;
};

struct SkillInfo
{
	SkillInfo()
		: type(kSkillNone)
		, once(false)
	{
	}

	byte	type;
	float	effect_value[4];
	float	effect_time[4];
	float	cooldown[4];
	float	hurt_addition[4];
	bool	once;
};


struct RoomItem
{
	byte 	type;
	int		value;
	std::string comment;
	std::string comment2;
};

struct DieBuffData
{
	float				duration_timer;		//持续时间计时器（s）
	float				interval;			//间隔时间（s）
	int					type;				//参照EEffect
	float				value;				//Effect参数
	int				rate;					//概率
	char res_key[res_key_length];
	char res_desc[res_key_length];
};

struct LevelInfo
{
	LevelInfo()
		: level_id(0)
		, type(0)
		, is_use_normal_weapon(1)
		, requested(false)
	{
		name[0] = 0;
		show_name[0] = 0;
	}

	int						level_id;
	char					name[64];
	byte					type;

	char					show_name[64];
	std::string				description;

	byte					is_use_normal_weapon;

	float					dead_height;
	float					snatch_speed_base;
	int						vehicle_recover;
	Vector3					vehicle_dim;

	std::vector<PointInfo>			rebirth_point;
	std::vector<AABBPointInfo>		hold_point;
	std::vector<PushVehicleInfo>	push_vehicle_point[2];
	float							push_vehicle_total_length[2];//do not read this!!!
	std::vector<BannerPointInfo>	banner_point;
	std::vector<PointInfo>			weapon_point;

	std::vector<Weapon>		weapon_set;
	std::vector<Supply>		supply_set;

	RoomItem				room_item;
	
	byte					health_scale;
	
	byte					is_rushgold;
	std::vector<Vector3>	rushgold_set;
	
	byte					is_moneyreward;

	SingleCharacterInfo		boss_info;
	SingleCharacterInfo		Zombie_info;
	SingleCharacterInfo		KingZombie_info;
	SingleCharacterInfo		SuperZombie_info;
	std::vector<SingleCharacterInfo>	buff_zombie_info;

	std::vector<BossActionInfo>		push_boss_point;
	std::vector<BossActionInfo>		push_boss_points[4];
	
	byte					bosspve_count;
	SingleCharacterInfo		bosspve_info[4];
	std::vector<CharacterDropItem>	bosspve_dropitems[4];

	bool					requested;

	std::vector<ServerDummyCreateWithStepFatherInfo> stepfather_dummy_info;
	std::vector<DieBuffData> die_buff_data;
};

extern void WriteLevelInfo(BinaryStream & stream, const LevelInfo & info);
extern void ReadLevelInfo(BinaryStream & stream, LevelInfo & info);
extern void WriteSupply(BinaryStream & stream, const Supply & supply);
extern void WriteKickInfo(BinaryStream & stream, const KickInfo & info);
extern void ReadKickInfo(BinaryStream & stream, KickInfo & info);
