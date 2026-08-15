#pragma once

enum CharacterPart
{
	kCharacterPartTorso,
	kCharacterPartBack,
	kCharacterPartChest,
	kCharacterPartHead,

	kCharacterPartShoulderL,
	kCharacterPartArmL,
	kCharacterPartElbowL,
	kCharacterPartWristL,

	kCharacterPartShoulderR,
	kCharacterPartArmR,
	kCharacterPartElbowR,
	kCharacterPartWristR,

	kCharacterPartLegL,
	kCharacterPartKneeL,
	kCharacterPartAnkleL,

	kCharacterPartLegR,
	kCharacterPartKneeR,
	kCharacterPartAnkleR,

	kCharacterPartCount,
};

enum ScoreDataType
{
	kScoreNormal,
	kScoreHeadShot,
	kScoreContinuousKill,
	kScoreTreatment,
	kScoreDamage,
	kScoreLiveTime,
	kScoreKnifeKills,
	kScoreAssistKill,
	kScoreBoostKill,
	kScoreSustainKill,
	kScoreControlKill,
	kScoreRevengeKill,

	kScoreTypeCount,
};

class Client;

class ScoreData
{
public:
	struct ScoreDataPlayer
	{
		uint career_id;
		int max_num;
		int cur_num;

		void Add(uint career, int value)
		{
			cur_num += value;
			if (cur_num > max_num)
			{
				max_num = cur_num;
				career_id = career;
			}
		}

		void Reset()
		{
			cur_num = 0;
		}
	};

	struct ScoreDataCareer
	{
		short kill;
		short dead;
		short control_num;
		short revenge_num;
		short assist_num;
		short grenade_kill;
		short knife_kill;
		short used_account;
		short shoothead;
		short maxdamage;
		short boostkill;
		short sustainkill;
		int treatment;
	};

public:
	// initialize
	void Initialize(Client &client);

	// clear
	void Clear();

	// score data
	void ScoreDataHit(const SingleCharacterInfo & career, int weapon_type, int damage, int part);
	
	// score data
	void ScoreDataHealth(const SingleCharacterInfo & career, int health);

	// score data
	void ScoreDataRebirth(uint career, bool add);

	// score data
	void ScoreDataKill(const SingleCharacterInfo & career, const SingleCharacterInfo & targetcareer,
						int weapon_type, int part, int level_diff, int userdata);

	// score data
	void ScoreDataKillByAssist(const SingleCharacterInfo & career, const SingleCharacterInfo & targetcareer,
								int weapon_type, int level_diff);

	// score data
	void ScoreDataDied(const SingleCharacterInfo & career, int time, bool add_died);

	// score data
	void ScoreDataAdd(float v, bool syn = false);

	// get score
	int GetScore();

	// get calc score
	int GetCalcScore(byte game_type, int time);

	// get score playerdata
	ScoreDataPlayer& GetScorePlayerData(ScoreDataType type);

	// get score careerdatas
	std::map<uint, ScoreDataCareer>& GetScoreCareerDatas();

	// get target datas
	std::map<uint, uint>& GetTargetData();

	// debug
	void DebugPrint(const char *name);

private:
	// 不要直接修改score，请使用ScoreDataAdd
	float score;
	ScoreDataPlayer player_datas[kScoreTypeCount];
	std::map<uint, ScoreDataCareer> career_datas;//career_id, ScoreDataCareer
	std::map<uint, uint> target_datas;
	Client *pClient;
};

struct ServerConfig
{
	float part_damages[kCharacterPartCount];
	int part_hit_score[kCharacterPartCount];
	int hold_point_score_snatch;
	int hold_point_score;
	int vehicle_score_push;
	int vehicle_score;

	// game wait max time
	float shutdown_time_max;
	float wait_time;
	float end_time;

	float vote_time;
	int kick_client_max;

	// normal mode
	float gun_destroy_time;

	// round time
	int round_time_holdpoint;
	float round_end_time_max;
	float round_end_special_time_max;
	float round_time_max_holdpoint;
	float round_time_max_vehicle;
	float round_time_max_normal;
	float round_time_max_boss;
	float round_time_max_boss2;
	float round_time_max_bomb;
	float round_time_max_advence;

	float round_time_max_knife;
	float round_time_max_deathmatch;
	float round_time_max_streetboy;
	float round_time_max_commonzombie;
	float round_time_max_zombie;
	float round_time_max_bosspve;
	float round_time_max_td;

	float round_time_stepone_zombie;

	// lock time
	int holdpoint_locktime;

	// spawn time
	short spawn_time_adjust;
	short spawn_time;

	// treasure mode
	float treasure_supply_start_time;
	float treasure_supply_time;

	// scores
	float score_scale[kScoreTypeCount];
	float score_scale_higher[10];
	float score_scale_lower[10];
	float match_score_scale[kScoreTypeCount];

	float kill_boss_score;
	
	float boss2_humanwin_score;
	float boss2_bosswin_score;
	
	float knock_down_human_score;
	float knock_down_human_killer_score;
	float kill_human_score;
	float kill_human_killer_score;
	float kill_human_assist_score;
	
	float kill_zombie_score;

	float kill_common_human_score;
	float kill_common_human_super_score;

	float kill_common_zombie_score;
	float kill_common_king_zombie_score;
	float kill_common_zombie_super_score;
	float assist_common_zombie_score;
	float assist_common_king_zombie_score;
	float assist_common_zombie_super_score;
	float common_zombie_win;

	float kill_boss_pve_score;

	float save_dying_score;
	float runaway_human_alive_score_base;
	float runaway_human_died_score_base;

	//Vector3 push_vehicle_dimension;

	//Vector3 kick_vehicle_dimension;

	float push_vehicle_velocity;

	//bossgold
	int life_each_boss_gold;
	
	// boss2
	float boss2_human_energy_max_init;
	float boss2_human_energy_max_add;
	float boss2_human_energy_up_speed;
	float boss2_defence_energy_max_init;
	float boss2_defence_energy_max_add;
	float boss2_defence_energy_kill_add;
	float boss2_showtime;
	
	// item mode
	float itemmode_energy_max_init;
	float itemmode_energy_up_speed;

	// bomb
	int bomb_explode_time; 
	Weapon bomb;

	int zombie_mode_human_number[max_client_count];

	float zombie_mode__resistance_all[max_client_count];

	float moonmode_time_max;


public:
	void RestoreDefault();
};

float GetScale(byte scale);
