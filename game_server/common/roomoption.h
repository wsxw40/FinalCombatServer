#pragma once

#include "networkstream.h"

struct RoomOption
{
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

	static const int TRAIN_ID = 50;

	enum SpecialMode
	{
		kNormal = 0,
		kSniper = 1,
		kPistol,
		kKnife,

		kSpecialModeCount,
	};

	enum ViewMode
	{
		kFirstView = 0,
		kThirdView,

		kViewModeCount,
	};

	// constructor
	RoomOption()
		: client_count(16)
		, viewer_count(0)

		, level_id(0)
		, map_level(0)
		, character_id(0)
		, is_knife(0)
		, is_gm(0)

		, use_random_map(false)

		, game_type(kTeam)
		, rule_value(100)
		, special_mode(kNormal)

		, round_rebirth_time_max(0)
		, dead_view_mode(kFirstView)
		, can_join_on_playing(true)
		, check_game_balance(false)
		, team_hurt(false)
		, group_match(false)
		, auto_start(false)

		, use_password(false)
		, item_id(0)
		, m_bIsMatchingRoom(false)
	{
	}

	char	name[128];
	int		level_id;
	int		map_level;
	char	rand_key[64];
	int     character_id;
	byte	is_knife;
	byte	is_gm;
	char	map_name[256];

	bool	use_random_map;

	char	client_count;
	char	viewer_count;

	byte	game_type;
	int		rule_value;
	byte	special_mode;

	short	round_rebirth_time_max;
	byte	dead_view_mode;
	bool	can_join_on_playing;
	bool	check_game_balance;
	bool	team_hurt;
	bool	group_match;
	bool	auto_start;

	bool	use_password;
	char	password[64];

	// room itme
	uint	item_id;
	char	item_resource[64];
	char	item_name[64];

	byte	m_bIsMatchingRoom;
};

extern void ReadRoomOption(BinaryStream & stream, RoomOption & option);
extern void WriteRoomOption(BinaryStream & stream, const RoomOption & option);
