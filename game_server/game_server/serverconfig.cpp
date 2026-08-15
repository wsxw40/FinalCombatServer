#include "pch.h"
#include <math.h>

// -----------------------------------------------------------------
// score data
// -----------------------------------------------------------------
// initialize data
void ScoreData::Initialize(Client &client)
{
	Clear();

	pClient = &client;
	
	for (int i = 0; i < elementsof(player_datas); i++)
	{
		player_datas[i].career_id = client.GetDefaultCharInfo().career_id;
	}

	ScoreDataCareer tmp_data;
	bzero(&tmp_data, sizeof(tmp_data));

	for (SingleCharacterInfoMap::const_iterator itr = client.GetCharacterInfo().singlecharacter_set.begin();
		itr != client.GetCharacterInfo().singlecharacter_set.end(); itr++)
	{
		career_datas.insert(std::pair<uint, ScoreDataCareer>(itr->second.career_id, tmp_data));
	}
}

// initialize data
void ScoreData::Clear()
{
	score = 0;

	bzero(player_datas, sizeof(player_datas));
	career_datas.clear();
	target_datas.clear();
}

// score data increase
void ScoreData::ScoreDataHit(const SingleCharacterInfo & career, int weapon_type, int damage, int part)
{
	uint career_id = career.career_id;

	int damage_add = career.can_select ? 1 : 0;

	player_datas[kScoreDamage].Add(career_id, damage_add * damage);

	if(career_datas[career_id].maxdamage < damage)
	{
		career_datas[career_id].maxdamage = damage;
	}
}

// score data increase
void ScoreData::ScoreDataHealth(const SingleCharacterInfo & career, int health)
{
	uint need_add = career.can_select ? 1 : 0;
	health *= need_add;
	
	uint career_id = career.career_id;
	
	career_datas[career_id].treatment += health;
	player_datas[kScoreTreatment].Add(career_id, health);

	if (server.cServerType == (byte)SvrType_Match)
	{
	}
	else
	{
		ScoreDataAdd(server.config.score_scale[kScoreTreatment] * health);
	}
}

// score data increase
void ScoreData::ScoreDataRebirth(uint career, bool add)
{
	if (add)
		career_datas[career].used_account++;
		
	for (int i = 0; i < elementsof(player_datas); i++)
	{
		player_datas[i].Reset();
	}
}

// score data increase
void ScoreData::ScoreDataKill(const SingleCharacterInfo & career, const SingleCharacterInfo & targetcareer,
								int weapon_type, int part, int level_diff, int userdata)
{
	//float score_calc = 0;
	//float score_scale = 1;

	//score_calc = server.config.part_hit_score[part];

	//等级差距获取不同积分暂时去掉，等以后有了等级再说
	/*if (level_diff > 0)
	{
		int level_diff_max = elementsof(server.config.score_scale_lower);
		if (level_diff > level_diff_max)
			level_diff = level_diff_max;

		score_scale = server.config.score_scale_lower[level_diff - 1];
	}
	else if (level_diff < 0)
	{
		int level_diff_max = elementsof(server.config.score_scale_higher);
		if (level_diff < -level_diff_max)
			level_diff = -level_diff_max;

		score_scale = server.config.score_scale_higher[-level_diff - 1];
	}*/

	//ScoreDataAdd(score_calc * score_scale);


	//普通击杀 +
	//			暴击击杀
	//			连续击杀x2
	//			连续击杀x3
	//			连续击杀x4
	//			连续击杀x5
	//			爆头击杀
	//			持续伤害击杀
	//			近身击杀
	//			复仇击杀
	//			护士治疗
	//			控制击杀

	uint career_id = career.career_id;
	uint targetcareer_id = targetcareer.career_id;

	uint need_add = career.can_select ? 1 : 0;

	float score_add = (server.cServerType == (byte)SvrType_Match) ? server.config.match_score_scale[kScoreNormal] : server.config.score_scale[kScoreNormal];

	//手雷击杀
	if (weapon_type == kWeaponTypeGrenade)
	{
		career_datas[career_id].grenade_kill++;
	}
	//近身击杀
	if (weapon_type == kWeaponTypeKnife)
	{
		career_datas[career_id].knife_kill++;
		player_datas[kScoreKnifeKills].Add(career_id, need_add);
		if (server.cServerType == (byte)SvrType_Match)
		{
			score_add += server.config.match_score_scale[kScoreKnifeKills];
		}
		else
		{
			score_add += server.config.score_scale[kScoreKnifeKills];
		}
	}
	//爆头击杀
	if (part == kCharacterPartHead)
	{
		career_datas[career_id].shoothead++;
		player_datas[kScoreHeadShot].Add(career_id, need_add);
		if (server.cServerType == (byte)SvrType_Match)
		{
			score_add += server.config.match_score_scale[kScoreHeadShot];
		}
		else
		{
			score_add += server.config.score_scale[kScoreHeadShot];
		}
	}
	//连续击杀
	player_datas[kScoreContinuousKill].Add(career_id, need_add);
	if (need_add)
	{
		if (server.cServerType == (byte)SvrType_Match)
		{
			if (player_datas[kScoreContinuousKill].cur_num > 1)
			{
				if (pClient && pClient->m_vecContinueKill.size() > 0)
				{
					if (*pClient->m_vecContinueKill.rbegin() == targetcareer.player_id)
					{
						int num = 0;
						for (std::vector<uint>::const_reverse_iterator it = pClient->m_vecContinueKill.rbegin(); it!= pClient->m_vecContinueKill.rend(); ++it)
						{
							if (*it == targetcareer.player_id)
							{
								++num;
							}
							else
							{
								break;
							}
						}

						if (num > 1)
						{
							num = num - 1;
						}
						else
						{
							num = 0;
						}

						float score = pow(0.75f, num) * server.config.match_score_scale[kScoreContinuousKill];

						int temp = floor(score);
						score_add += temp;
						//log_write(LOG_DEBUG1, "%s, %s, add : %f, num : %d, temp : %d", __FILE__, __FUNCTION__, score, num, temp);
					}
					else
					{
						// 策划大人说 换了一个人击杀 要从2分开始算
						score_add += server.config.match_score_scale[kScoreNormal];
					}
				}
				else
				{
					score_add += server.config.match_score_scale[kScoreContinuousKill];
				}

				score_add -= server.config.match_score_scale[kScoreNormal];
				//float fAdd = 1.0f;
				//for (int i = 0; i < player_datas[kScoreContinuousKill].cur_num - 2; ++ i)
				//{
				//	fAdd *= 0.75f;
				//}
				//score_add += fAdd * server.config.match_score_scale[kScoreContinuousKill];
			}
		}
		else
		{
			score_add += server.config.score_scale[kScoreContinuousKill] * (player_datas[kScoreContinuousKill].cur_num - 1);
		}
	}
	
	if (userdata & (1 << kScoreBoostKill))
	{
		career_datas[career_id].boostkill++;
		player_datas[kScoreBoostKill].Add(career_id, need_add);
		if (server.cServerType == (byte)SvrType_Match)
		{
			score_add += server.config.match_score_scale[kScoreBoostKill];
		}
		else
		{
			score_add += server.config.score_scale[kScoreBoostKill];
		}
	}
	if (userdata & (1 << kScoreSustainKill))
	{
		career_datas[career_id].sustainkill++;
		player_datas[kScoreSustainKill].Add(career_id, need_add);
		if (server.cServerType == (byte)SvrType_Match)
		{
			score_add += server.config.match_score_scale[kScoreSustainKill];
		}
		else
		{
			score_add += server.config.score_scale[kScoreSustainKill];
		}
	}
	if (userdata & (1 << kScoreControlKill))
	{
		career_datas[career_id].control_num++;
		player_datas[kScoreControlKill].Add(career_id, need_add);
		if (server.cServerType == (byte)SvrType_Match)
		{
			score_add += server.config.match_score_scale[kScoreControlKill];
		}
		else
		{
			score_add += server.config.score_scale[kScoreControlKill];
		}
	}
	if (userdata & (1 << kScoreRevengeKill))
	{
		career_datas[career_id].revenge_num++;
		player_datas[kScoreRevengeKill].Add(career_id, need_add);
		if (server.cServerType == (byte)SvrType_Match)
		{
			score_add += server.config.match_score_scale[kScoreRevengeKill];
		}
		else
		{
			score_add += server.config.score_scale[kScoreRevengeKill];
		}
	}
	career_datas[career_id].kill++;

	{
		std::map<uint, uint>::iterator itr = target_datas.find(targetcareer_id);
		if (itr == target_datas.end())
		{
			target_datas.insert(std::pair<uint, uint>(targetcareer_id, 1));
		}
		else
		{
			itr->second++;
		}
	}
	if(server.game_type != Game::kZombieMode && server.game_type != Game::kCommonZombieMode)
	{
		ScoreDataAdd(score_add * GetScale(targetcareer.score_scale));
	}

	if (pClient)
	{
		pClient->m_vecContinueKill.push_back(targetcareer.player_id);
	}
}

// score data increase by assistance
void ScoreData::ScoreDataKillByAssist(const SingleCharacterInfo & career, const SingleCharacterInfo & targetcareer,
										int weapon_type, int level_diff)
{
	uint career_id = career.career_id;

	uint need_add = career.can_select ? 1 : 0;

	//			协力击杀
	float score_add = server.config.score_scale[kScoreAssistKill];
	player_datas[kScoreAssistKill].Add(career_id, need_add);
	career_datas[career_id].assist_num++;
	if(server.game_type != Game::kCommonZombieMode)
	{
		if (server.cServerType == (byte)SvrType_Match)
		{
			ScoreDataAdd(1, true);
		}
		else
		{
			ScoreDataAdd(score_add * GetScale(targetcareer.score_scale));
		}
	}
}

// score data increase
void ScoreData::ScoreDataDied(const SingleCharacterInfo & career, int time, bool add_died)
{
	uint career_id = career.career_id;

	int time_add = career.can_select ? time : 0;

	player_datas[kScoreLiveTime].Add(career_id, time_add);

	if (add_died)
	{
		for (int i = 0; i < elementsof(player_datas); i++)
		{
			player_datas[i].Reset();
		}
		
		career_datas[career_id].dead++;
	}
}

// score data increase
void ScoreData::ScoreDataAdd(float v, bool syn)
{
	if (pClient)
	{
		float value = 1;
		
		pClient->GetSkillEffect().SumEffect(kEffect_Infect_Score, value);
		if (server.cServerType == (byte)SvrType_Match)
		{
		}
		else
		{
			v = fmaxf(v * value, 0);
		}
		
		pClient->OnAction();
	}
	
	score += v;
	
	if (pClient && syn)
	{
		for (uint i = 0; i < max_client_count; i++)
		{
			Client * c = server.clients + i;
			
			if (c->IsReady())
			{
				c->BeginWrite();
				c->WriteByte(SM_SynScore);
				c->WriteByte(pClient->uid);
				c->WriteInt(GetScore());
				c->EndWrite();
			}
		}
	}
}

// get score
int ScoreData::GetScore()
{
	return (int)score;
}

// get calc score
int ScoreData::GetCalcScore(byte game_type, int time)
{
	if(game_type >= Game::kGameTypeCount)
	{
		log_write_sys(LOG_ERROR, "ScoreData::GetCalcScore index length error");
		return 0;
	}

	int var07, var08;

	switch(game_type)
	{
	case kHoldPoint:
		{
			var07 = 9;
		}
		break;
	case kNovice:
		{
			var07 = 1;
		}
		break;
	default:
		{
			var07 = 7;
		}
	}

	switch(game_type)
	{
	case kNovice:
		{
			var08 = 1;
		}
		break;
	default:
		{
			var08 = 150;
		}
	}
	float base_score = score;
	float calc_score = var08 * (time / var07);

	return (int)Min(base_score, calc_score);
}

// get score playerdata
ScoreData::ScoreDataPlayer& ScoreData::GetScorePlayerData(ScoreDataType type)
{
	return player_datas[type];
}

// get score
std::map<uint, ScoreData::ScoreDataCareer>& ScoreData::GetScoreCareerDatas()
{
	return career_datas;
}

// get target datas
std::map<uint, uint>& ScoreData::GetTargetData()
{
	return target_datas;
}

// debug
void ScoreData::DebugPrint(const char *name)
{
	static const char* ScoreDataTypeString[] =
	{
		"kScoreNormal",
		"kScoreHeadShot",
		"kScoreContinuousKill",
		"kScoreTreatment",
		"kScoreDamage",
		"kScoreLiveTime",
		"kScoreKnifeKills",
		"kScoreAssistKill",
		"kScoreBoostKill",
		"kScoreSustainKill",
		"kScoreControlKill",
		"kScoreRevengeKill",
	};

	if (log_get_output_level() < LOG_DEBUG4)
	{
		return;
	}

	log_write(LOG_DEBUG4, "ScoreData::DebugPrint() Start");

	log_write(LOG_DEBUG4, "\tname : %s", name);
	log_write(LOG_DEBUG4, "\tscore : %f", score);

	int i = 0;
	for (i = 0; i < kScoreTypeCount; i++)
	{
		log_write(LOG_DEBUG4, "\tplayer_datas[%s].career_id : %u", ScoreDataTypeString[i], player_datas[i].career_id);
		log_write(LOG_DEBUG4, "\tplayer_datas[%s].max_num : %d", ScoreDataTypeString[i], player_datas[i].max_num);
		log_write(LOG_DEBUG4, "\tplayer_datas[%s].cur_num : %d", ScoreDataTypeString[i], player_datas[i].cur_num);
	}

	log_write(LOG_DEBUG4, "\tcareer_datas.size() : %u", career_datas.size());
	i = 0;
	for (std::map<uint, ScoreData::ScoreDataCareer>::const_iterator itr = career_datas.begin();
		itr != career_datas.end(); itr++)
	{
		log_write(LOG_DEBUG4, "\tcareer_datas[%d].career_id : %u", i, itr->first);
		log_write(LOG_DEBUG4, "\tcareer_datas[%d].kill : %hd", i, itr->second.kill);
		log_write(LOG_DEBUG4, "\tcareer_datas[%d].dead : %hd", i, itr->second.dead);
		log_write(LOG_DEBUG4, "\tcareer_datas[%d].control_num : %hd", i, itr->second.control_num);
		log_write(LOG_DEBUG4, "\tcareer_datas[%d].revenge_num : %hd", i, itr->second.revenge_num);
		log_write(LOG_DEBUG4, "\tcareer_datas[%d].assist_num : %hd", i, itr->second.assist_num);
		log_write(LOG_DEBUG4, "\tcareer_datas[%d].grenade_kill : %hd", i, itr->second.grenade_kill);
		log_write(LOG_DEBUG4, "\tcareer_datas[%d].knife_kill : %hd", i, itr->second.knife_kill);
		log_write(LOG_DEBUG4, "\tcareer_datas[%d].used_account : %hd", i, itr->second.used_account);
		log_write(LOG_DEBUG4, "\tcareer_datas[%d].shoothead : %hd", i, itr->second.shoothead);
		log_write(LOG_DEBUG4, "\tcareer_datas[%d].maxdamage : %hd", i, itr->second.maxdamage);
		log_write(LOG_DEBUG4, "\tcareer_datas[%d].boostkill : %hd", i, itr->second.boostkill);
		log_write(LOG_DEBUG4, "\tcareer_datas[%d].sustainkill : %hd", i, itr->second.sustainkill);
		log_write(LOG_DEBUG4, "\tcareer_datas[%d].treatment : %d", i, itr->second.treatment);

		i++;
	}

	log_write(LOG_DEBUG4, "\ttarget_datas.size() : %u", target_datas.size());
	i = 0;
	for (std::map<uint, uint>::const_iterator itr = target_datas.begin();
		itr != target_datas.end(); itr++)
	{
		log_write(LOG_DEBUG4, "\ttarget_datas[%d].career_id : %u", i, itr->first);
		log_write(LOG_DEBUG4, "\ttarget_datas[%d].kill : %u", i, itr->second);

		i++;
	}
	if (target_datas.size() != i)
		log_write(LOG_DEBUG4, "\tWARN : target_data`s size is error");

	log_write(LOG_DEBUG4, "ScoreData::DebugPrint() End");
}



void ServerConfig::RestoreDefault()
{
	part_damages[kCharacterPartTorso] 		= 1.0f;
	part_damages[kCharacterPartBack]		= 1.0f;
	part_damages[kCharacterPartChest]		= 1.0f;
	part_damages[kCharacterPartHead]		= 1.0f;

	part_damages[kCharacterPartShoulderL]	= 1.0f;
	part_damages[kCharacterPartArmL]		= 1.0f;
	part_damages[kCharacterPartElbowL]		= 1.0f;
	part_damages[kCharacterPartWristL]		= 1.0f;

	part_damages[kCharacterPartShoulderR]	= 1.0f;
	part_damages[kCharacterPartArmR]		= 1.0f;
	part_damages[kCharacterPartElbowR]		= 1.0f;
	part_damages[kCharacterPartWristR]		= 1.0f;

	part_damages[kCharacterPartLegL]		= 1.0f;
	part_damages[kCharacterPartKneeL]		= 1.0f;
	part_damages[kCharacterPartAnkleL]		= 1.0f;

	part_damages[kCharacterPartLegR]		= 1.0f;
	part_damages[kCharacterPartKneeR]		= 1.0f;
	part_damages[kCharacterPartAnkleR]		= 1.0f;

	part_hit_score[kCharacterPartTorso] 	= 135;
	part_hit_score[kCharacterPartBack]		= 135;
	part_hit_score[kCharacterPartChest]		= 135;
	part_hit_score[kCharacterPartHead]		= 135;

	part_hit_score[kCharacterPartShoulderL]	= 135;
	part_hit_score[kCharacterPartArmL]		= 135;
	part_hit_score[kCharacterPartElbowL]	= 135;
	part_hit_score[kCharacterPartWristL]	= 135;

	part_hit_score[kCharacterPartShoulderR]	= 135;
	part_hit_score[kCharacterPartArmR]		= 135;
	part_hit_score[kCharacterPartElbowR]	= 135;
	part_hit_score[kCharacterPartWristR]	= 135;

	part_hit_score[kCharacterPartLegL]		= 135;
	part_hit_score[kCharacterPartKneeL]		= 135;
	part_hit_score[kCharacterPartAnkleL]	= 135;

	part_hit_score[kCharacterPartLegR]		= 135;
	part_hit_score[kCharacterPartKneeR]		= 135;
	part_hit_score[kCharacterPartAnkleR]	= 135;

	hold_point_score_snatch = 20;
	hold_point_score = 135;
	vehicle_score_push = 2;
	vehicle_score = 270;

	shutdown_time_max = 20;
	wait_time = 30;
	end_time = 10;

	vote_time = 10;
	kick_client_max = 1;

	gun_destroy_time = 30.f;

	life_each_boss_gold = 4000;
	
	boss2_human_energy_max_init = 100;
	boss2_human_energy_max_add = 10;
	boss2_human_energy_up_speed = 0.067f;
	boss2_defence_energy_max_init = 1000;
	boss2_defence_energy_max_add = 200;
	boss2_defence_energy_kill_add = 75;
	boss2_showtime = 1;
	
	itemmode_energy_max_init = 500;
	itemmode_energy_up_speed = 1.0f;

	round_time_holdpoint = 3 * 60 + 30;
	round_end_time_max = 4;
	round_end_special_time_max = 15;
	round_time_max_holdpoint = 20 * 60;
	round_time_max_vehicle = 30 * 60;
	round_time_max_normal = 20 * 60;
	round_time_max_knife = 30 * 60;
	round_time_max_deathmatch = 10 * 60;
	round_time_max_streetboy = 5 * 60 + 5;
	round_time_max_commonzombie = 2 * 60 + 80;

	round_time_max_boss = 5 * 60 + 5;
	round_time_max_boss2 = 5 * 60 + 5 + boss2_showtime;
	round_time_max_bomb = 150;

	round_time_max_advence = 60 * 4;

	round_time_stepone_zombie = 3 * 60 + 30 + rand() % 20;

	//round_time_stepone_zombie = 30;

	round_time_max_zombie = round_time_stepone_zombie + 3 * 60;
	round_time_max_bosspve = 60 * 60;
	round_time_max_td = 5 * 60;

	holdpoint_locktime = 10;

	spawn_time_adjust = 2;
	spawn_time = 8;

	treasure_supply_start_time = 3;
	treasure_supply_time = 20;

	score_scale[kScoreNormal] = 135;
	score_scale[kScoreHeadShot] = 25;
	score_scale[kScoreContinuousKill] = 5;
	score_scale[kScoreTreatment] = 0.375;
	score_scale[kScoreDamage] = 0;
	score_scale[kScoreLiveTime] = 0;
	score_scale[kScoreKnifeKills] = 5;
	score_scale[kScoreAssistKill] = 70;
	score_scale[kScoreBoostKill] = 15;
	score_scale[kScoreSustainKill] = 5;
	score_scale[kScoreControlKill] = 120;
	score_scale[kScoreRevengeKill] = 35;

	match_score_scale[kScoreNormal] = 2;
	match_score_scale[kScoreHeadShot] = 0;
	match_score_scale[kScoreContinuousKill] = 4;
	match_score_scale[kScoreTreatment] = 0.375;
	match_score_scale[kScoreDamage] = 0;
	match_score_scale[kScoreLiveTime] = 0;
	match_score_scale[kScoreKnifeKills] = 0;
	match_score_scale[kScoreAssistKill] = 1;
	match_score_scale[kScoreBoostKill] = 0;
	match_score_scale[kScoreSustainKill] = 0;
	match_score_scale[kScoreControlKill] = 0;
	match_score_scale[kScoreRevengeKill] = 2;

	for (int i = 0; i < elementsof(score_scale_higher); i ++)
		score_scale_higher[i] = 1.0f + i * 0.1f;

	for (int i = 0; i < elementsof(score_scale_lower); i ++)
		score_scale_lower[i] = 1.0f - i * 0.1f;

	kill_boss_score = 2000.f;
	
	boss2_humanwin_score = 500.f;
	boss2_bosswin_score = 100.f;
	
	knock_down_human_score = 60.f;
	knock_down_human_killer_score = 120;
	kill_human_score = 100.f;
	kill_human_killer_score = 200.f;
	kill_human_assist_score = 150.f;
	save_dying_score = 20.f;
	kill_zombie_score = 30.f;

	kill_common_human_score = 270.f;
	kill_common_human_super_score = 1080.f;

	kill_common_zombie_score = 270.f;
	kill_common_king_zombie_score = 540.f;
	kill_common_zombie_super_score = 1080.f;
	assist_common_zombie_score = 135.f;
	assist_common_king_zombie_score = 270.f;
	assist_common_zombie_super_score = 540.f;
	common_zombie_win = 945.f;

	runaway_human_alive_score_base = 500.f;
	runaway_human_died_score_base = 300.f;

	kill_boss_pve_score = 2000.f;

	//push_vehicle_dimension = Vector3(4, 4, 4);

	//kick_vehicle_dimension = Vector3(1.2f, 2, 1.2f);

	push_vehicle_velocity = 1.f;

	
	// bomb
	bomb_explode_time = 45;
	bomb.base_info.sid = 1;
	strcpy(bomb.base_info.name, "c4");
	bomb.base_info.part_count = 1;
	strcpy(bomb.base_info.part_key[0], "c4/c4");
	
	bomb.weapon_data.bomb.type = kWeaponTypeBomb;
	bomb.weapon_data.bomb.change_in_time = 0.58f;
	bomb.weapon_data.bomb.move_speed_offset = 0;
	bomb.weapon_data.bomb.cross_offset = 8;
	bomb.weapon_data.bomb.cross_length_base = 7;
	bomb.weapon_data.bomb.cross_length_factor = 80;
	bomb.weapon_data.bomb.cross_distance_base = 3;
	bomb.weapon_data.bomb.cross_distance_factor = 400;
	bomb.weapon_data.bomb.damage = 580;
	bomb.weapon_data.bomb.range = 50;
	bomb.weapon_data.bomb.plant_time = 5.f;
	bomb.weapon_data.bomb.defuse_time = 10.f;
	bomb.weapon_data.bomb.defuse_with_item_time = 5.f;

	zombie_mode_human_number[0] = 1;
	zombie_mode_human_number[1] = 1;
	zombie_mode_human_number[2] = 1;
	zombie_mode_human_number[3] = 1;
	
	zombie_mode_human_number[4] = 2;
	zombie_mode_human_number[5] = 2;
	zombie_mode_human_number[6] = 2;
	zombie_mode_human_number[7] = 2;
	
	zombie_mode_human_number[8] = 3;
	zombie_mode_human_number[9] = 3;
	zombie_mode_human_number[10] = 3;
	zombie_mode_human_number[11] = 3;
	
	zombie_mode_human_number[12] = 4;
	zombie_mode_human_number[13] = 4;
	zombie_mode_human_number[14] = 4;
	zombie_mode_human_number[15] = 4;


	zombie_mode__resistance_all[0] = 0.0f;
	zombie_mode__resistance_all[1] = 0.0f;
	zombie_mode__resistance_all[2] = 0.0f;
	zombie_mode__resistance_all[3] = 0.0f;

	zombie_mode__resistance_all[4] = -0.2f;
	zombie_mode__resistance_all[5] = -0.3f;
	zombie_mode__resistance_all[6] = 0.2f;
	zombie_mode__resistance_all[7] = 0.0f;

	zombie_mode__resistance_all[8] = -0.2f;
	zombie_mode__resistance_all[9] = -0.3f;
	zombie_mode__resistance_all[10] = 0.2f;
	zombie_mode__resistance_all[11] = 0.0f;

	zombie_mode__resistance_all[12] = -0.2f;
	zombie_mode__resistance_all[13] = -0.3f;
	zombie_mode__resistance_all[14] =  0.2f;
	zombie_mode__resistance_all[15] =  0.0f;

	moonmode_time_max = 8 * 60.f;


}

float GetScale(byte scale)
{
	return scale;
}
