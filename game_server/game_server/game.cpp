#include "pch.h"
#include <time.h>
#include <list>
#include <stdarg.h>

const float STREET_KING_UPDATE_POSITION_INTERVAL = 1.f;
const int ROUND_START_TIME = 10;
const int COMMON_ZOMBIE_ROUND_START_TIME = 25;
const int COMMON_BOSSMODE2_ROUND_START_TIME = 5;
const int GOLD_MAX_COUNT = 64;
const int DROPITEM_MAX_COUNT = 64;
const int SURVIVALITEMBAGSIZE = 6;

// SYS
static const std::string SYS_PLAYTIME_KEY = "SYS_PLAYTIME";

uint GenerateDummyIndex()
{
	static uint dummy_index_generator = 0;

	return dummy_index_generator ++;
}

enum EGameState
{
	GS_Idle,
	GS_Connecting,
	GS_Connected,
	GS_Initialized,
};

enum EGameRPC
{
	RPC_StageClear,
	RPC_StageQuit,
	RPC_KillInfo,
	RPC_SaveMap
};

Game server;

static void UpdateGame(void * data)
{
	Event::AddTimer(&UpdateGame, data, 0.05);

	Game * game = static_cast<Game*>(data);

	if (game)
	{
		double time = Event::GetTime();
		double delta = time - game->update_time;
		game->update_time = time;

		// update game
		game->Update(delta);

		game->ForwordAllMessage();
	}
}

Game::Game()
	: BinaryStream(128 * 1024)
	, state(GS_Idle)
	, debug_level(0)
	, gold_time(0)
	, cure_time(0)
	, bomb_planting(false)
	, bomb_planted(false)

	, bomb_plant_timer(0.f)
	, bomb_owner_id(0)
	, bomb_defusing(false)
	, bomb_defusing_timer(0.f)
	, bomb_explode_timer(0.f)
	, sync_street_king_timer(0.f)
	, zombie_step_two_flag(false)
	, zombie_target_area_id(0)
	, boss_revive_time(0)
	, isnewphase(true)
	, now_phase(0)
	, bosspve_end_game(false)
	, idle_kick_open(true)
	, can_super(false)
	, zombie_super(true)
	, human_super(true)
	, item_update_timer(-1.0f)
	, item_update_timer2(-1.0f)
	, write_log_to_client(false)
	, picked_count(0)
	, cServerType(0)
	, m_bEndMatchClient(false)
{
	clients = NULL;
	bot_clients = NULL;
	connection = this;
	stream = this;
	
	config.RestoreDefault();

	cur_holdpoint = 0;
	cur_holdpoint_diffnum = 0;

	team_kills[0] = 0;
	team_kills[1] = 0;

	check_game_balance = true;

	game_type = kTeam;
	rule_value = 100;
	special_mode = kNormal;
	round_rebirth_time_max = 8;
	team_hurt = false;
	end_time = config.end_time;
	waiting_time = config.wait_time;
	shutdown_timer = config.shutdown_time_max;

	m_bIsMatching = 0;

	client_connected_count[0] = client_connected_count[1] = 0;
	client_ready_count[0] = client_ready_count[1] = 0;

	loading = false;
	playing = false;
	waiting = false;

	play_time = 0;
	play_time_old = 0;
	time_out_finished = false;
	time_max = config.round_time_max_normal;
	
	boss2_showtime = false;

	is_voting = false;
	kick_sponsor_uid = 0;
	kick_client_uid = 0;

	team_rounds[0] = 0;
	team_rounds[1] = 0;

	team_timer[0] = 0;
	team_timer[1] = 0;

	team_holdpoint_num[0] = 0;
	team_holdpoint_num[1] = 0;

	round_end_time = 0;
	round_start_time = 0;
	round_start_total_time = 0;

	round_playing = false;

	room_id = 0;
	server_id = 0;
	channel_id = 0;
	host_character_id = 0;

	strcpy(server_address, "127.0.0.1");

	stage_cleared = false;
	first_kill = 0;
	first_died = 0;

	vehicle_timer = 0.f;

	add_up_delta = 0.f;
	vehicle_timer_interval = 0.5f;
	
	group_id_t1 = 0;
	group_id_t2 = 0;

	game_start_time = time(NULL);
	special_person_start_timer = -2.f;
	special_person_start_time_count = 0;
	novice_isupdate = true;
	special_person_flashed = false;

	game_end_timer = -1.f;
	game_end_team = -1;
	check_game_end_timer = CHECK_GAME_END_INTERVAL;

	boss_mode_human_alive_number = 0;

	vehicle_update_timer = 0.f;
	
	is_request_disconnect = false;
	request_disconnect_timeout = 10.f;

	boss_life_gold_count = 0;

	boss2_boss_rand_start = 0;
	boss2_rebirth_rand_start[0] = 0;
	boss2_rebirth_rand_start[1] = 0;
	boss2_rebirth_rand_start[2] = 0;
	boss2_rebirth_rand_start[3] = 0;
	
	is_hahatime = false;
	start_hahatime = false;
}

Game::~Game()
{
}

// on connected
void Game::OnConnected()
{
	if (connected_socket != INVALID_SOCKET)
	{
		linger l;
		l.l_onoff = 1;
		l.l_linger = 1;
		setsockopt(connected_socket, SOL_SOCKET, SO_LINGER, &l, sizeof(l));
	}

	state = GS_Connected;

	game_start_time = time(NULL);
	
	log_write(LOG_DEBUG1, "%s, %s, game start matching_level_check", __FILE__, __FUNCTION__);
}

static void RandomArray(int count, int* random_array)
{
	if (count > 0 && random_array != NULL)
	{
		for (int i = 0; i < count; i++)
			*(random_array + i) = i;

		for (int i = 0; i < count; i++)
		{
			int temp = *(random_array + i);
			int swap_pos = rand() % count;
			*(random_array + i) = *(random_array + swap_pos);
			*(random_array + swap_pos) = temp;
		}
	}
}

static float CalcSnatchSpeed(int num, float base_speed)
{
	float ret = 0;

	num = Min(num, 4);

	for (int i = 0; i < num; i++)
	{
		ret += base_speed / (i + 1);
	}

	return ret;
}

void Game::Update(float delta)
{
	if (is_request_disconnect)
	{
		request_disconnect_timeout -= delta;
		if (request_disconnect_timeout < 0.f)
		{
			Disconnect();
			is_request_disconnect = false;
			
			log_write(LOG_ERROR, "request disconnect time out");
			
			return;
		}
	}
	
	if (game_end_timer >= 0.f)
	{
		game_end_timer -= delta;
		if (game_end_timer < 0.f)
		{
			OnGameEnd(game_end_team);
		}
	}

	cure_time += delta;
	if (cure_time > 1)
	{
		cure_time = 0;
		CheckAllCure();
	}
	

/* 	int connected_client_num = 0;
	for (int i = 0; i < max_client_count; ++ i)
	{
		if (clients[i].IsConnected() && clients[i].team < 2)
		{
			connected_client_num++;
		}
	}
	if ((waiting || playing) &&
		(connected_client_num <= 0))
	{
		// no one is connected
		log_write(LOG_DEBUG1, "Update() : no one is connected[%d, %d]", waiting, playing);

		OnGameEnd(-1);
	} */

	if (loading)
	{
		int connected_count = client_connected_count[0] + client_connected_count[1];
		int ready_count = client_ready_count[0] + client_ready_count[1];

		if (waiting)
		{
			if (connected_count == 0 || waiting_time <= 0)
			{
				waiting = false;
				playing = true;
			}
			else
			{
				waiting_time -= delta;
			}
		}
		else
		{
			if (ready_count > 0)
			{
				if (connected_count > 0)
					waiting = true;
				else
				{
					playing = true;
					waiting = false;
				}
			}
		}

		if (playing)
		{
			loading = false;
			OnGameStart();
		}
		else
		{
			if (connected_count <= 0)
			{
				shutdown_timer -= delta;

				if (shutdown_timer <= 0)
				{
					loading = false;
					if (server.cServerType == (byte)SvrType_Match || server.cServerType == (byte)SvrType_MatchFighting)
					{
						log_write(LOG_ERROR, "%s, %s, no client", __FILE__, __FUNCTION__);
					}
					RequestDisconnect();
				}
			}
		}
	}
	else if (playing)
	{
		switch (game_type)
		{
		case kItemMode:
			{
				if (start_hahatime)
				{
					is_hahatime = true;
					start_hahatime = false;
					
					for (uint i = 0; i < max_client_count; i++)
					{
						Client * c = server.clients + i;
						
						if (c->IsReady())
						{
							Transform transform;
							transform.position = c->position;
							transform.rotation = c->rotation.GetZXY().y * RAD2DEG;
							
							c->Spawn(transform, NULL, false, false, true);
						}
					}
					
					for (SupplyObject* supply = supply_list.Begin(); supply < supply_list.End(); supply++)
					{
						if (supply->supply.type == kSupplyCommonZombie2 && supply->IsActive())
						{
							supply->Destroy();
							for (int j = 0; j < max_client_count; ++j)
							{
								if (server.clients[j].IsReady())
									server.clients[j].DestroySupplyObject(supply->uid);
							}
						}
					}

					for (SupplyObject* supply = supply_list.Begin(); supply < supply_list.End(); supply++)
					{
						if (supply->supply.type == kSupplyCommonZombie && supply->IsActive())
						{
							supply->Destroy();
							for (int j = 0; j < max_client_count; ++j)
							{
								if (server.clients[j].IsReady())
									server.clients[j].DestroySupplyObject(supply->uid);
							}
						}
					}
				}
			}
			break;
		case kBossMode:
			{
				if(!special_person_flashed)
				{
					UpdateRoundStartTimer(delta);

					//log_write(LOG_DEBUG5, "boss_timer: %f", special_person_start_timer);
					if (special_person_start_timer > ROUND_START_TIME)
					{
						special_person_start_timer = -2.f;
						// Notify Boss Flash
						int player_count = 0;
						for (int i = 0; i < max_client_count; ++ i)
						{
							if (clients[i].IsReady())
							{
								player_count++;
							}
						}

						if (player_count <= 1)
						{
							OnGameEnd(1);
							return;
						}
						int boss_pos = rand() % player_count;
						int boss_count = 0;
						bool boss_selected = false;
						for (int i = 0; i < max_client_count; ++i)
						{
							if (clients[i].IsReady())
							{
								if (boss_count == boss_pos)
								{
									log_write(LOG_DEBUG1, "NotifyBossFlash");
									clients[i].NotifyBossFlash();
									boss_selected = true;
								}
								boss_count++;
							}
						}
						if (!boss_selected)
						{
							log_write(LOG_DEBUG1, "NotifyBossFlash Failed %d  %d", boss_pos, boss_count);
						}
					}
				}
			}
			break;
		case kBossMode2:
			{
				if(!special_person_flashed)
				{
					UpdateRoundStartTimer(delta);

					if (special_person_start_timer > COMMON_BOSSMODE2_ROUND_START_TIME)
					{
						special_person_start_timer = -2.f;
						special_person_start_time_count = 0;
						BossMode2Select();
					}
				}
			}
			break;
		case kStreetBoyMode:
			{
				if(!special_person_flashed)
				{
					UpdateRoundStartTimer(delta);

					if (special_person_start_timer > ROUND_START_TIME)
					{
						special_person_start_timer = -2.f;
						// Notify Street King Flash
						StreetKingSelect();
					}
				}
				else
				{
					sync_street_king_timer += delta;
					if(sync_street_king_timer > STREET_KING_UPDATE_POSITION_INTERVAL)
					{
						sync_street_king_timer = 0.f;
						// sync position
						UpdateKingPosition();
	
					}
				}
			}
			break;
		case kZombieMode:
			{
				if(!special_person_flashed)
				{
					UpdateRoundStartTimer(delta);

					if (special_person_start_timer > ROUND_START_TIME)
					{
						special_person_start_timer = -2.f;
						// Notify Zombie Flash
						ZombieSelect();
						NotifyZombieModeGameStart();
					}
				}
				else
				{
					UpdateZombieModeSaveDying(delta);

					if(!zombie_step_two_flag)
					{
						if(play_time > config.round_time_stepone_zombie)
						{
							//notify step two
							zombie_step_two_flag = true;

							NotifyZombieModeStepTwo();
						}
					}
					else
					{
						if(play_time > config.round_time_stepone_zombie + 15.f)
						{
							int human_count = 0;
							int success_count = 0;
							for (int i = 0; i < max_client_count; ++ i)
							{
								Client& client = clients[i];
								if (client.IsReady() && client.IsAlive() && client.team == 0)
								{
									human_count ++;
									if(client.zombie_mode_dying_state)
										continue;
									else if(CheckIsInZombieArea(client.position))
									{
										success_count ++;
									}
								}
							}
							
							if(human_count != 0 && human_count == success_count)
							{
								OnRoundEnd(false, 0);
							}
						}
					}
				}
			}
			break;
		case kCommonZombieMode:
			{
				if(!special_person_flashed)
				{
					UpdateRoundStartTimer(delta);

					if (special_person_start_timer > COMMON_ZOMBIE_ROUND_START_TIME)
					{
						special_person_start_timer = -2.f;
						CommonZombieSelect();
					}
				}
			}
			break;
		case kBombMode:
			{
				if(round_playing)
				{
					if(bomb_planting && bomb_plant_timer)
					{
						bomb_plant_timer -= delta;
						if(bomb_plant_timer < 0.f)
						{
							for (int i = 0; i < max_client_count; ++ i)
							{
								Client& client = clients[i];
								if (client.IsReady() && client.IsAlive() && client.uid == bomb_owner_id)
								{
									bool is_in_area = CheckIsInBombArea(client.position);
									if(is_in_area)
									{
										server.bomb_planted_pos = client.position;
										

										client.PlantBombSuccess(server.config.bomb_explode_time);

										server.bomb_planted = true;
										server.bomb_planting = false;
										server.bomb_explode_timer = server.config.bomb_explode_time;
										server.bomb_owner_id = 0;
										if(play_time + server.config.bomb_explode_time >= time_max)
										{
											play_time -= server.config.bomb_explode_time;
										}
									}
								}
							}
						}
					}
					else if(bomb_planted && bomb_explode_timer)
					{
						bomb_explode_timer -= delta;
						if(bomb_explode_timer < 0.f)
						{
							// bomb exploded
							for (int i = 0; i < max_client_count; ++ i)
							{
								if (clients[i].IsReady())
								{
									clients[i].BombExploded();
								}
							}

							bomb_planted = false;
							ClearDefusingState();
							if(round_playing)
								OnRoundEnd(false,1);
						}
					}

					if(bomb_defusing_uid && bomb_defusing && bomb_planted)
					{
						bomb_defusing_timer -= delta;
						if(bomb_defusing_timer < 0.f)
						{
							//defusing success
							for (int i = 0; i < max_client_count; ++ i)
							{
								if (clients[i].IsReady())
								{
									clients[i].DefuseBombSuccess();
								}
							}
							ClearDefusingState();
							if(round_playing)
								OnRoundEnd(false,0);
						}
					}
				}	
			}
			break;
		case kBossPVE:
			{
				if(!special_person_flashed && !bosspve_end_game)
				{
					if (boss_revive_time > 0)
					{
						boss_revive_time -= delta;
					}
					else
					{ 
						UpdateRoundStartTimer(delta);

						if (special_person_start_timer > ROUND_START_TIME)
						{
							special_person_start_timer = -2.f;
							special_person_start_time_count = 0;

							//复活本阶段可能会复活的所有BOSS
							int bossnum = GetNowPhaseBossNum();
							for (int k = 0; k < bossnum;++k)
							{
								// cid由level_info.bosspve_info.career_id表示
								uint cid = GetAliveBossCid(k);
								Client* bot = GetBotByCid(cid);
								if (bot)
								{
									bot->SetCurCharInfo(cid, true);
									//第一个位置作为出生点
									bot->SetBossActionInfo(now_Alive_boss[k]);
									bot->OnBotSpawn(now_Alive_boss[k][0].position);
								}
							}
							int point_size[2];
							point_size[0] = start_point[0].size();
							point_size[1] = start_point[1].size();
							Transform transform;
							for (int i = 0; i < max_client_count; ++ i)
							{
								if (clients[i].IsReady())
								{
									if (clients[i].team < 2)
									{

										clients[i].zombie_rebirth_counter = 0;
										clients[i].zombie_mode_dying_state = false;
										clients[i].zombie_mode_saving_dying_uid = 0;
										clients[i].zombie_mode_saver_uid = 0;
										clients[i].zombie_mode_save_timer = 0.f;
										clients[i].human_rebirth_counter = 0;
										clients[i].zombie_rebirth_counter = 0;
										clients[i].SetTeam(0);

										if (point_size[clients[i].team] > 0)
											transform = start_point[clients[i].team][(clients[i].uid & 0x0f) % point_size[clients[i].team]];

										if (!clients[i].GetCurCharinfo().can_select)
										{
											clients[i].UseDefaultCharInfo();
										}	
										clients[i].Spawn(transform, NULL, false);
									}
								}
							}
							special_person_flashed = true;
						}
						else
						{
							//新阶段倒数计时时，需要将本阶段出场BOSS的信息全部加入到容器中
							if (isnewphase)
							{
								isnewphase = false;
								if (!CheckAliveBossInfo())
								{
									special_person_flashed = true;
									bosspve_end_game = true;
								}
							}
						}
					}
				}
				else if (!bosspve_end_game)
				{
					//检测本阶段是不是所有BOSS都死掉了,如果是更新至下个阶段，流程返回
					int bossnum = GetNowPhaseBossNum();
					int dienum = 0;
					for (int k = 0; k < bossnum;++k)
					{
						Client* bot = GetBotByCid(GetAliveBossCid(k));
						if (bot && bot->IsDied())
							dienum++;
					}
					if (dienum == bossnum)
					{
						isnewphase = true;
						now_phase++;
						SetServerScriptNumberValue("BOSSPVE_PHASE_INDEX", now_phase);
						special_person_flashed = false;
						boss_revive_time = 20.f;
					}
				}
			}
			break;
		}

		switch (game_type)
		{
		case kBossMode:
		case kBossMode2:
		case kZombieMode:
		case kCommonZombieMode:
		case kBossPVE:
			{
				if(special_person_flashed)
				{
					check_game_end_timer -= delta;
					if(check_game_end_timer < 0.f)
					{
						check_game_end_timer = CHECK_GAME_END_INTERVAL;
						CheckRoundEnd();
					}
				}
			}
			break;
		case kTeamDeathMatch:
		case kBombMode:
		case kStreetBoyMode:
		case kTDMode:
			{
				if(server.round_start_time  <= 0.f)
				{
					check_game_end_timer -= delta;
					if(check_game_end_timer < 0.f)
					{
						check_game_end_timer = CHECK_GAME_END_INTERVAL;
						CheckRoundEnd();
					}
				}

			}
			break;
		case KMoonMode:
		case kSurvivalMode:
			{
				CheckRoundEnd();
			}
			break;
		}

		if (round_end_time > 0)
		{
			round_end_time -= delta;

			if (round_end_time <= 0)
				OnRoundStart();
		}
		else if (round_start_time > 0)
		{
			round_start_time -= delta;

			if (round_start_time <= 0)
			{
				round_start_time = 0;

				for (int i = 0; i < max_client_count; ++ i)
				{
					if (clients[i].IsReady())
						clients[i].RoundStartPlay();
				}
			}
		}
		else
		{
			play_time += delta;
			
			if (play_time - play_time_old >= 1)
			{
				SetServerScriptNumberValue(SYS_PLAYTIME_KEY, play_time);
				
				play_time_old = play_time;
			}

			OnPlayTimeChanged();
		}
		
		switch(server.cServerType)
		{
			case SvrType_Match:
			case SvrType_MatchFighting:
			{
			
				if (m_bEndMatchClient)
				{
					for (int i = 0; i < 2; i++)
					{
						int total_count = client_ready_count[i] + client_connected_count[i];

						if (total_count < 1)
						{
							OnGameEnd((i + 1) % 2);
							break;
						}
					}
				}
		
				if (!m_bEndMatchClient)
				{
					if (play_time > appcfg.nMatchEndTime)
					{
						m_bEndMatchClient = true;
						EndRequestMatchClient();
					}
				}
			
			}
			break;
			case SvrType_HageBattle:
			{
				if (play_time > 120 && server.game_type != kAdvenceMode)
				{
					for (int i = 0; i < 2; i++)
					{
						int total_count = client_ready_count[i] + client_connected_count[i];

						if (total_count < 1)
						{
							OnGameEnd((i + 1) % 2);
							break;
						}
					}
				}
			}
			break;
			default:
			break;
		}
		
		// group_match and check game balance
		if ((check_game_balance) || (room_option.group_match && play_time >= 5))
		{
			switch (game_type)
			{
			default:
				{
					for (int i = 0; i < 2; i++)
					{
						int total_count = client_ready_count[i] + client_connected_count[i];

						if (total_count < 1)
						{
							OnGameEnd((i + 1) % 2);
							break;
						}
					}
				}
				break;
			}
		}
		
		if (!server_script_stringvalue_dirty.empty() || 
			!server_script_numbervalue_dirty.empty())
		{
			for (int i = 0; i < max_client_count; ++ i)
			{
				if (clients[i].IsConnected())
					clients[i].SyncServerScriptValue();
			}
			
			server_script_stringvalue_dirty.clear();
			server_script_numbervalue_dirty.clear();
		}
		
		for (std::list<ServerScript>::iterator itr = server_script_list.begin(); 
			itr != server_script_list.end(); itr++)
		{
			itr->Update(delta);
		}

		for (int i = 0; i < max_client_count; ++ i)
		{
			if (clients[i].IsConnected())
				clients[i].Update(delta);
		}

		for (int i = 0; i < max_botclient_count; ++ i)
		{
			if (bot_clients[i].IsConnected())
				bot_clients[i].BotUpdate(delta);
		}

		// update dropped gun
		for (DroppedWeapon* weapon = dropped_weapon.Begin(); weapon < dropped_weapon.End(); weapon++)
		{
			if (weapon && weapon->IsActive())
				weapon->Update(delta);
		}

		// update dropped supply
		//for (DroppedSupply* sup = dropped_supply.Begin(); sup < dropped_supply.End(); sup++)
		//{
		//	if (sup && sup->IsActive())
		//		sup->Update(delta);
		//}
		
		// update supply list
		for (SupplyObject* supply = supply_list.Begin(); supply < supply_list.End(); supply++)
		{
			if (supply && CheckSupplyType(supply->supply.type))
			{
				if (supply->createtime > supply->supply.skilltime && !supply->IsActive())
				{
					supply->Create();
					supply->createtime = 0;
					supply->position = supply->supply.position;
					supply->rotation = 0;
					for (int j = 0; j < max_client_count; ++j)
					{
						if (clients[j].IsReady())
							clients[j].AddSupplyObject(supply);
					}
				}
				else if (!supply->IsActive())
				{
					supply->createtime += delta;
				}
			}
		}
		
		static float server_dummy_sync = 1;
		
		// update server dummy
		if (server_dummy_sync > 0)
		{
			server_dummy_sync -= delta;
		}
		else
		{
			server_dummy_sync = 1;
			
			for (std::map<uint, DummyBaseInfo>::const_iterator itr = dummy_object_map.begin(); 
				itr != dummy_object_map.end(); itr++)
			{
				const DummyBaseInfo & info = itr->second;
				if (info.type == DUMMY_SERVER && info.owner_id == 0)
				{
					assert(info.buf_length == sizeof(ServerDummyCreateInfo));
					
					if (info.buf_length == sizeof(ServerDummyCreateInfo))
					{
						const ServerDummyCreateInfo& c_info = *(ServerDummyCreateInfo*)&info.buffer[0];
						
						ServerDummySyncData sync;
						
						sync.time = 0;
						sync.hp = c_info.hp;
						
						for (int j = 0; j < max_client_count; ++j)
						{
							if (clients[j].IsReady())
								clients[j].NotifyServerDummySyncUpdate(info.id, sync);
						}
					}
				}
			}
		}
		
		if (game_type == kCommonZombieMode || 
			game_type == kBossMode2 || 
			game_type == kItemMode)
		{
			if (item_update_timer > 0.0f)
			{
				if (item_update_timer < delta)
					item_update_timer = 0.0f;
				else
					item_update_timer -= delta;
			}
			else
			{
				for (SupplyObject* supply = supply_list.Begin(); supply < supply_list.End(); supply++)
				{
					if (supply->supply.type == kSupplyCommonZombie && supply->IsActive())
					{
						supply->Destroy();
						for (int j = 0; j < max_client_count; ++j)
						{
							if (server.clients[j].IsReady())
								server.clients[j].DestroySupplyObject(supply->uid);
						}
					}
				}
				int count = 6;
				if (game_type == kBossMode2)
				{
					int human = 0;
					for (int i = 0; i < max_client_count; ++ i)
					{
						if (clients[i].IsReady() && clients[i].team == 0)
							human++;
					}
					count = (int)ceilf((float)human / 1.5f);
					if (count == 0) count = 1;
				}
				else if (game_type == kItemMode)
				{
					int r = rand() % 3 + 9;
					count = Min(r, (int)common_zombie_supply_list.GetCount());
					
					if (is_hahatime)
						count = 0;
				}
				int sum = 0;
				while(count > 0)
				{
					if (sum > 5000)
					{
						log_write(LOG_DEBUG1, "Item_Create_error");
						break;
					}
					sum++;
					SupplyObject* supply1 = common_zombie_supply_list.Begin() + rand() % common_zombie_supply_list.GetCount();
					for (SupplyObject* supply = supply_list.Begin(); supply < supply_list.End(); supply++)
					{
						if (supply && supply1 && supply1->supply.position == supply->supply.position && !supply->IsActive())
						{
							supply->Create();
							supply->createtime = 0;
							supply->position = supply->supply.position;
							supply->rotation = 0;
							for (int j = 0; j < max_client_count; ++j)
							{
								if (clients[j].IsReady())
									clients[j].AddSupplyObject(supply);
							}
							count--;
							break;
						}
					}
				}
				if (game_type == kItemMode)
					item_update_timer = 45.0f;
				else
					item_update_timer = 30.0f;
			}
		}
		
		if (game_type == kItemMode)
		{
			if (item_update_timer2 > 0.0f)
			{
				if (item_update_timer2 < delta)
					item_update_timer2 = 0.0f;
				else
					item_update_timer2 -= delta;
			}
			else
			{
				for (SupplyObject* supply = supply_list.Begin(); supply < supply_list.End(); supply++)
				{
					if (supply->supply.type == kSupplyCommonZombie2 && supply->IsActive())
					{
						supply->Destroy();
						for (int j = 0; j < max_client_count; ++j)
						{
							if (server.clients[j].IsReady())
								server.clients[j].DestroySupplyObject(supply->uid);
						}
					}
				}
				int count = 1;
				int sum = 0;
				
				if (is_hahatime)
					count = 0;
				
				while(count > 0)
				{
					if (sum > 5000)
					{
						log_write(LOG_DEBUG1, "Item_Create_error");
						break;
					}
					sum++;
					SupplyObject* supply1 = common_zombie_supply_list2.Begin() + rand() % common_zombie_supply_list2.GetCount();
					for (SupplyObject* supply = supply_list.Begin(); supply < supply_list.End(); supply++)
					{
						if (supply && supply1 && supply1->supply.position == supply->supply.position && !supply->IsActive())
						{
							supply->Create();
							supply->createtime = 0;
							supply->position = supply->supply.position;
							supply->rotation = 0;
							for (int j = 0; j < max_client_count; ++j)
							{
								if (clients[j].IsReady())
									clients[j].AddSupplyObject(supply);
							}
							count--;
							break;
						}
					}
				}
				item_update_timer2 = 90.0f + rand() % 10;
			}
		}
		else if (game_type == kSurvivalMode)
		{
			int index = 1;
			for (std::vector<TrapInfo>::iterator itor = server.trap_info.begin();itor != server.trap_info.end(); itor++)
			{
				TrapInfo& trapinfo = *itor;
				bool flag = false;
				for (int i = 0; i < max_client_count; ++ i)
				{
					if (clients[i].IsReady())
					{
						if (Length(itor->position - clients[i].position) < 3.f && clients[i].team != itor->team && !clients[i].isghost)
						{
							trap_info.erase(itor);
							TrapTrigger(index, clients[i].uid);
							flag = true;
							switch(trapinfo.type)
							{
							case kSupplySurvivalItemTrapAmmo:
								{
									clients[i].AmmoDisappear();
								}
								break;
							case kSupplySurvivalItemTrapHP:
								{
									clients[i].HPItemDisappear();
								}
								break;
							case kSupplySurvivalItemTrapExpose:
								{
									clients[i].AddSkill(kEffect_Survival_Expose, 60.f);
								}
								break;
							case kSupplySurvivalItemTrapBomb:
								{
									clients[i].TakeSustainHurt(server.GetClientByPId(itor->uid), clients[i].health / 2, 0);
								}
								break;
							case kSupplySurvivalItemTrapDebuff:
								{
									clients[i].AddSkill(kEffect_Survival_Debuff, 10.f);
								}
								break;
							}
							break;
						}
					}
				}
				if (flag)
				{
					break;
				}
				index++;
			}
		}

		// update kick clien vote
		if (is_voting)
		{
			vote_timer += delta;
			if (vote_timer > config.vote_time)
			{
				OnKickClientEnd();
			}
		}

		switch (game_type)
		{
		case kHoldPoint:
			if (play_time >= config.holdpoint_locktime 
				&& round_playing && game_end_timer < 0.f)
			{
				int hold_point_team[2] = {0};
				bool client_in_points[max_client_count] = {false};

				HoldPointInfo &hold_point = hold_points[cur_holdpoint];
				for (int i = 0; i < max_client_count; ++ i)
				{
					if (clients[i].IsAlive() && clients[i].team < 2 &&
						hold_point.aabb.IsPointInside(clients[i].position))
					{
						hold_point_team[clients[i].team]++;
						client_in_points[i] = true;
					}
				}

				int diff_num = hold_point_team[0] - hold_point_team[1];
				cur_holdpoint_diffnum = Abs(diff_num);
				byte team_more = InvalidTeam;

				if (hold_point_team[0] > 0 && hold_point_team[1] > 0)
				{
					//do nothing
					cur_holdpoint_diffnum = 0;
				}
				else if (hold_point_team[0] == 0 && hold_point_team[1] == 0)
				{
					if (hold_point.snatch_team != InvalidTeam)
					{
						hold_point.snatching_timer -= level_info.snatch_speed_base * delta * 0.5f;
					}
				}
				else
				{
					if (hold_point_team[0] > 0)
						team_more = 0;
					else
						team_more = 1;

					if (hold_point.owner_team == team_more)
					{
						if (hold_point.snatch_team != InvalidTeam)
						{
							hold_point.snatching_timer -= level_info.snatch_speed_base * delta * 0.5f;
						}
						//else
						//{
							////do nothing
						//}
					}
					else
					{
						if (hold_point.snatch_team == team_more ||
							hold_point.snatch_team == InvalidTeam)
						{
							hold_point.snatching_timer += CalcSnatchSpeed(hold_point_team[team_more], level_info.snatch_speed_base) * delta;
							hold_point.snatch_team = team_more;

							for (int i = 0; i < max_client_count; i++)
							{
								if (client_in_points[i] && clients[i].team == team_more)
								{
									if (server.cServerType == (byte)SvrType_Match)
									{
									}
									else
									{
										clients[i].data.ScoreDataAdd(config.hold_point_score_snatch * delta);
									}
								}
							}
						}
						else
						{
							hold_point.snatching_timer -= level_info.snatch_speed_base * delta * 0.5f;
						}
					}
				}

				if (hold_point.snatching_timer > 100.f)
				{
					hold_point.snatching_timer = 0.f;

					if (hold_point.owner_team != InvalidTeam)
					{
						team_holdpoint_num[hold_point.owner_team]--;
					}

					if (team_more != InvalidTeam)
					{
						team_holdpoint_num[team_more]++;
					}

					hold_point.owner_team = team_more;
					hold_point.snatch_team = InvalidTeam;

					for (int i = 0; i < max_client_count; i++)
					{
						if (clients[i].IsReady() && clients[i].team == team_more)
						{
							if (server.cServerType == (byte)SvrType_Match)
							{
							}
							else
							{
								clients[i].data.ScoreDataAdd(config.hold_point_score);
							}
						}
					}

					//cur_holdpoint++;
				}
				else if (hold_point.snatching_timer < 0.f)
				{
					hold_point.snatching_timer = 0.f;
					hold_point.snatch_team = InvalidTeam;
				}

				for (int i = 0; i < 2; i++)
				{
					if (team_holdpoint_num[i] >= hold_points.size())
					{
						team_timer[i] -= delta;
						if (team_timer[i] <= 0.f)
						{
							team_timer[i] = 0.f;
							if ((diff_num == 0 || team_more == i) &&
								(hold_point.owner_team == i && hold_point.snatch_team == InvalidTeam))
							{
								if(round_playing)
								{
									OnRoundEnd(false, i);
								}

								break;
							}
						}
					}
				}
			}
			break;
		case kPushVehicle:
			{
				vehicle_timer -= delta;
				add_up_delta += delta;
				if(vehicle_timer < 0.f)
				{
					OnPushVehicleUpdate(add_up_delta);
					vehicle_timer += vehicle_timer_interval;
					add_up_delta = 0.f;
				}
			}
			break;
		case kNovice:
			{
				if (novice_isupdate)
				{
					vehicle_timer -= delta;
					add_up_delta += delta;
					if(vehicle_timer < 0.f)
					{
						OnPushVehicleUpdate(add_up_delta);
						vehicle_timer += vehicle_timer_interval;
						add_up_delta = 0.f;
					}
				}
			}
			break;
		}
	}
	else
	{
		end_time -= delta;
		if (end_time < 0)
		{
			if (end_time < -config.shutdown_time_max || stage_cleared)
			{
				for (int i = 0; i < max_client_count; ++ i)
				{
					if (clients[i].IsConnected())
						clients[i].LeaveGame(stage_cleared);
				}
				
				DEBUGLOG_WRITE("Game::Update(), end_time : %f, stage_cleared : %d", end_time, stage_cleared);

				stage_cleared = false;

				// update kill info
				UpdateKillInfo();

				RequestDisconnect();
			}

			if (end_time < - config.shutdown_time_max)
			{
				log_write(LOG_ERROR, "%s, %s, end_time < - config.shutdown_time_max", __FILE__, __FUNCTION__);
				Disconnect();
			}
		}
	}


	if((game_end_timer >= 0.f || round_end_time) && level_info.is_rushgold)
	{
		if (gold_time > 0)
		{
			gold_time -= delta;
		}
		else
		{
			// update supply list
			for (SupplyObject* supply = supply_list.Begin(); supply < supply_list.End(); supply++)
			{
				if (supply && supply->supply.type == kSupplyGold && !supply->IsActive())
				{
					gold_time = 0.35f;
					supply->Create();
					supply->position = supply->supply.position;
					supply->rotation = 0;
					for (int j = 0; j < max_client_count; ++j)
					{
						if (clients[j].IsReady())
							clients[j].AddSupplyObject(supply);
					}
					break;
				}
			}
		}
	}
}

int Game::GetNowPhaseBossNum()
{
	int num = 0;
	for (int k = 0; k < 4; ++k)
	{
		if (now_Alive_boss[k].size() > 0)
		{
			num++;
		}
	}
	return num;
}

bool Game::CheckAliveBossInfo()
{
	for (int k = 0; k < 4; ++k)
	{
		now_Alive_boss[k].clear();
	}
	if (now_phase < 4 && level_info.push_boss_points[now_phase].size() > 0)
	{
		int temp = 0;
		int bossid = level_info.push_boss_points[now_phase][0].career_id;
		for(int k = 0; k < level_info.push_boss_points[now_phase].size();++k)
		{
			if((int)level_info.push_boss_points[now_phase][k].career_id == bossid)
			{
				now_Alive_boss[temp].push_back(level_info.push_boss_points[now_phase][k]);
			}
			else
			{
				bossid = level_info.push_boss_points[now_phase][k].career_id;
				temp++;
				if (temp < 4)
				{
					now_Alive_boss[temp].push_back(level_info.push_boss_points[now_phase][k]);
				}
			}		
		}
		return true;
	}
	return false;
	//for (int k = 0; k < 4; ++k)
	//{
	//	WriteLogClient("level_info.push_boss_points[%d].size() = %d",k,level_info.push_boss_points[k].size()); 
	//}
	//for (int k = 0; k < 4; ++k)
	//{
	//	WriteLogClient("now_Alive_boss[%d].size() = %d",k,now_Alive_boss[k].size()); 
	//}
}

/// on client state changed
void Game::OnClientStateChanged()
{
	int ready_count[] = {0, 0};
	int connected_count[] = {0, 0};

	for (int i = 0; i < max_client_count; ++ i)
	{
		if (clients[i].IsConnected() && clients[i].team < 2)
		{
			if (clients[i].IsReady())
				ready_count[clients[i].team]++;
			else
				connected_count[clients[i].team]++;
		}
	}

	if (check_game_balance && playing)
	{
		switch (game_type)
		{
		default:
			{
				for (int i = 0; i < 2; i++)
				{
					if (ready_count[i] < client_ready_count[i] && ready_count[i] < 1)
					{
						OnGameEnd((i + 1) % 2);
						break;
					}

					int total_count_old = client_ready_count[i] + client_connected_count[i];
					int total_count_new = ready_count[i] + connected_count[i];

					if (total_count_new < total_count_old && total_count_new < 1)
					{
						OnGameEnd((i + 1) % 2);
						break;
					}
				}
			}
			break;
		}
	}

	switch (game_type)
	{
	default:
		break;
	}

	client_ready_count[0] = ready_count[0];
	client_ready_count[1] = ready_count[1];
	client_connected_count[0] = connected_count[0];
	client_connected_count[1] = connected_count[1];

	//CheckRoundEnd();
}

// on play time changed
void Game::OnPlayTimeChanged()
{
	if (game_type == kBossMode2 && play_time >= (time_max - config.boss2_showtime) && !time_out_finished )
	{
		if (boss2_showtime == false)
		{
			for (int i = 0; i < max_client_count; ++ i)
			{
				if (clients[i].IsConnected())
					clients[i].NotifyBoss2Showtime();
			}
			
			boss2_showtime = true;
		}
	}
	
	if (play_time >= time_max && !time_out_finished )
	{
		time_out_finished = true;
		switch (game_type)
		{
		case kTeam:
		case kKnifeMode:
		case kItemMode:
		case kEditMode:
			OnGameEnd(-1);
			break;
		case kTDMode:
			OnGameEnd(-1);
			break;
		case kBossMode:
		case kZombieMode:
		case kBossPVE:
			OnRoundEnd(true, 1);
			break;
		case kBombMode:
			OnRoundEnd(true, 0);
			break;
		case kHoldPoint:
		case kPushVehicle:
		case kTeamDeathMatch:
		case kStreetBoyMode:
		case KMoonMode:
			OnRoundEnd(true, 0);
			break;
		case kAdvenceMode:
			OnRoundEnd(true, 0);
		case kCommonZombieMode:
			OnRoundEnd(true, 0);
			break;
		case kBossMode2:
			OnRoundEnd(true, 1);
			break;
		case kSurvivalMode:
			OnRoundEnd(true, 1);
			break;
		default:
			break;
		}
	}
}

// on team kills changed
void Game::OnTeamKillsChanged(int team)
{
	if (game_type == kItemMode 
		&& is_hahatime == false
		&& team_kills[team] >= (rule_value - 15))
	{
		start_hahatime = true;
	}
	
	if ((game_type == kTeam || game_type == kKnifeMode || game_type == kItemMode)
		&& team < 2
		&& team_kills[team] >= rule_value)
	{
		OnGameEnd(team);
	}
	
	if (server.cServerType == (byte)SvrType_Match || server.cServerType == (byte)SvrType_MatchFighting)
	{
		if (!m_bEndMatchClient)
		{
			if (team_kills[team] >= appcfg.nMatchKillNum)
			{
				m_bEndMatchClient = true;
				EndRequestMatchClient();
			}
		}
	}
}

// on team alive changed
void Game::OnTeamAliveChanged(int team, bool round_start)
{
	if (round_start)
		return;

	if (team > 1)
		return;
	
	if(server.round_start_time <= 0.f)
	{
		if (game_type == kCommonZombieMode && special_person_flashed)
			CheckRoundEnd();
		else
			CheckRoundEnd();
	}
}

void Game::CheckRoundEnd()
{
	switch (game_type)
	{
	case kTDMode:
		{
			if(server.playing)
			{
				if (cur_reshp <= 0)
					OnGameEnd(0);
				else
				{
					byte team_connect_flag[2] = {0};
					
					for (uint i = 0; i < max_client_count; i++)
					{
						Client * c = clients + i;

						if(c->IsConnected() && c->team < 2)
						{
							team_connect_flag[c->team] ++;
						}
					}

					log_write(LOG_DEBUG1, "red_flag %d      blue_flag %d" , team_connect_flag[0], team_connect_flag[1]);
					
					if(team_connect_flag[0] == 0)
						OnGameEnd(1);
				}
			}
		}
		break;
	case kTeamDeathMatch:
		{
			byte team_alive_flag[2] = {0};

			if(server.round_playing)
			{
				for (uint i = 0; i < max_client_count; i++)
				{
					Client * c = clients + i;

					if(c->IsReady() && c->IsAlive() && c->team < 2)
					{
						team_alive_flag[c->team] ++;
					}
				}

				log_write(LOG_DEBUG1, "alive_flag %d      blue_flag %d" , team_alive_flag[0], team_alive_flag[1]);

				if(team_alive_flag[0] == 0)
				{
					OnRoundEnd(false, 1);
				}
				else if(team_alive_flag[1] == 0)
				{
					OnRoundEnd(false, 0);
				}
			}

		}
		break;
	case kBossMode:
		{
			if(special_person_flashed && round_playing)
			{
				byte team_member_flag[2] = {0};

				for (uint i = 0; i < max_client_count; i++)
				{
					Client * c = clients + i;

					if(c->IsReady() && c->team < 2)
					{
						if(c->team == 0 && c->spawn_time > NEVER_SPAWN + 1.f)
						{
							team_member_flag[c->team] ++;
						}
						else if(c->team == 1 && c->IsAlive())
						{
							team_member_flag[c->team] ++;
						}

					}
				}

				log_write(LOG_DEBUG1, "alive_flag %d      blue_flag %d" , team_member_flag[0], team_member_flag[1]);

				if(team_member_flag[0] == 0)
				{
					OnRoundEnd(false, 1);
				}
				else if(team_member_flag[1] == 0)
				{
					OnRoundEnd(false, 0);
				}
			}
		}
		break;
	case kBossMode2:
		{
			if(special_person_flashed && round_playing)
			{
				byte team_member_flag[2] = {0};

				for (uint i = 0; i < max_client_count; i++)
				{
					Client * c = clients + i;

					if(c->IsReady() && c->team < 2)
					{
						if(c->team == 0 && c->spawn_time > NEVER_SPAWN + 1.f)
						{
							team_member_flag[c->team] ++;
						}
						else if(c->team == 1 && c->IsAlive())
						{
							team_member_flag[c->team] ++;
						}

					}
				}

				log_write(LOG_DEBUG1, "alive_flag %d      blue_flag %d" , team_member_flag[0], team_member_flag[1]);

				if(team_member_flag[0] == 0)
				{
					OnRoundEnd(false, 1);
				}
				else if(team_member_flag[1] == 0)
				{
					OnRoundEnd(false, 0);
				}
			}
		}
		break;
	case kBombMode:
		{
			byte team_alive_flag[2] = {0};

			if(server.round_playing)
			{
				for (uint i = 0; i < max_client_count; i++)
				{
					Client * c = clients + i;

					if(c->IsReady() && c->IsAlive() && c->team < 2)
					{
						team_alive_flag[c->team] ++;
					}
				}

				log_write(LOG_DEBUG1, "alive_flag %d      blue_flag %d" , team_alive_flag[0], team_alive_flag[1]);

				if(team_alive_flag[0] == 0)
				{
					OnRoundEnd(false, 1);
				}
				else if(team_alive_flag[1] == 0 && !bomb_planted)
				{
					OnRoundEnd(false, 0);
				}
			}
		}
		break;
	case kStreetBoyMode:
		{
			if(server.round_playing && special_person_flashed)
			{
				for (uint t = 0; t < 2; t++)
				{
					bool flag = false;
					const StreetKingInfo& info = street_king_info[t];

					for (uint i = 0; i < max_client_count; i++)
					{
						Client * c = clients + i;

						if(c->IsReady() && c->IsAlive() && c->team == t && info.current_street_king_uid != 0 && c->uid == info.current_street_king_uid)
						{
							flag = true;
						}
					}
					if(!flag)
					{
						OnRoundEnd(false, t == 0 ? 1 : 0);
					}
				}
			}	
		}
		break;
	case kZombieMode:
		{
			byte team_alive_flag[2] = {0};

			if(server.round_playing && special_person_flashed)
			{
				bool flag = false;
				for (uint i = 0; i < max_client_count; i++)
				{
					Client * c = clients + i;

					if(c->IsReady() && c->IsAlive() && c->team == 0)
					{
						team_alive_flag[0] ++;
					}
					else if(c->IsReady() && c->team == 1)
					{
						team_alive_flag[1] ++;
					}
				}
				if(team_alive_flag[0] == 0)
				{
					OnRoundEnd(false, 1);
				}
				else if(team_alive_flag[1] == 0)
				{
					OnRoundEnd(false, 0);
				}
			}

		}
		break;
	case kBossPVE:
		{
			if(server.round_playing)
			{
				if(bosspve_end_game)
				{
					OnRoundEnd(false, 0);
				}
				else
				{
					byte team_alive_flag = 0;
					for (uint i = 0; i < max_client_count; i++)
					{
						Client * c = clients + i;

						if(c->IsReady() && c->IsAlive() && c->team == 0)
						{
							team_alive_flag ++;
						}
					}
					if(team_alive_flag == 0)
					{
						OnRoundEnd(false, 1);
					}
				}
			}
		}
		break;
	case kCommonZombieMode:
		{
			if(server.round_playing && special_person_flashed)
			{
				int human_count = 0;
				int human_pos = -1;
				int zombie_count = 0;
				int can_spawn_zombie_count = 0;
				int zombie_pos = -1;
				for (int i = 0; i < max_client_count; ++ i)
				{
					Client& client = clients[i];
					if (client.IsReady())
					{
						if (client.team == 0 && client.IsAlive())
						{
							human_count++;
							human_pos = i;
						}
						if (client.team == 1)
						{
							if (client.IsAlive())
							{
								zombie_count++;
							}

							if (client.can_spawn == true)
							{
								can_spawn_zombie_count++;
								zombie_pos = i;
							}
							if (!client.is_zombie_king)
							{
								can_super = true;
							}
						}
					}
				}
				if (human_count == 1 && human_pos != -1 )
				{
					Client& client = clients[human_pos];
					if (can_super && human_super)
					{
						human_super = false;
						client.NotifyCommonHumanSuper();
					}
				}

				if (human_count < 1)
				{
					OnRoundEnd(false, 1);
				}
				else if(can_spawn_zombie_count < 1 && zombie_count == 0)
				{
					OnRoundEnd(false, 0);
				}
			}	
		}
		break;
	case KMoonMode:
		{
			if (round_playing)
			{
				if (picked_count >= 5)
				{
					int sum0 = 0;
					int sum1 = 0;
					for (int i = 0; i < max_client_count; ++ i)
					{
						if (clients[i].team == 0)
							sum0 += clients[i].data.GetScore();
						else if (clients[i].team == 1)
						{
							sum1 += clients[i].data.GetScore();
						}
					}
					if (sum0 == sum1)
					{
						OnRoundEnd(false, rand() % 2);
					}
					else if (sum0 > sum1)
					{
						OnRoundEnd(false, 0);
					}
					else
					{
						OnRoundEnd(false, 1);
					}
				}
				
			}
		}
		break;
	case kSurvivalMode:
		{
			if (round_playing)
			{
				bool flag = true;
				for (int i = 0; i < max_client_count; ++ i)
				{
					if (clients[i].team == 0 && (!clients[i].isghost && clients[i].IsReady()))
					{
						flag = false;
						break;
					}
				}
				if (flag)
				{
					OnGameEnd(1);
				}
				else
				{
					flag = true;
					for (int i = 0; i < max_client_count; ++ i)
					{
						if (clients[i].team == 1 && (!clients[i].isghost && clients[i].IsReady()))
						{
							flag = false;
							break;
						}
					}
					if (flag)
					{
						OnGameEnd(0);
					}
				}
			}
		}
		break;
	}
}

// on round start
void Game::OnRoundStart(bool first_round)
{
	{
		srand(time(NULL));
		int rcont = rand() % 9;
		for (int i = 0; i < rcont; i++)
			rand();
		srand(rand());
	}

	time_out_finished = false;
	boss2_showtime = false;
	
	is_hahatime = false;
	start_hahatime = false;

	if(game_type == kStreetBoyMode)
	{
		round_start_time = 10.f;
		round_start_total_time = 10.f;
	}
	else if(game_type == kBossMode)
	{
		round_start_time = 1.f;
		round_start_total_time = 1.f;
	}
	else if(game_type == kBossMode2)
	{
		round_start_time = 1.f;
		round_start_total_time = 1.f;
		item_update_timer = 55.0f;
	}
	else if (game_type == kCommonZombieMode)
	{
		round_start_time = 5.f;
		round_start_total_time = 5.f;
		item_update_timer = 55.0f;
	}
	else if(game_type == kItemMode)
	{
		item_update_timer = 1.0f;
		item_update_timer2 = 50 + rand() % 10;
		round_start_time = 5.f;
		round_start_total_time = 5.f;
	}
	else
	{
		round_start_time = 5.f;
		round_start_total_time = 5.f;
	}
	
	SetServerScriptNumberValue(SYS_PLAYTIME_KEY, 0);

	round_playing = true;
	
	play_time = 0;
	play_time_old = 0;
	
	for (std::list<ServerScript>::iterator itr = server_script_list.begin(); 
		itr != server_script_list.end(); itr++)
	{
		itr->Reset();
	}

	DestoryAllDummy();

	if (game_type == kHoldPoint ||
		game_type == kPushVehicle ||
		game_type == kTeamDeathMatch ||
		game_type == kNovice ||
		game_type == kBossMode || 
		game_type == kBossMode2 || 
		game_type == kBombMode ||
		game_type == kKnifeMode || 
		game_type == kStreetBoyMode||
		game_type == kZombieMode ||
		game_type == kBossPVE ||
		game_type == kCommonZombieMode || 
		game_type == kTDMode ||
		game_type == KMoonMode||
		game_type == kAdvenceMode||
		game_type == kSurvivalMode
		)
	{
		round_end_time = 0;

		// clear dropped gun
		for (DroppedWeapon* weapon = dropped_weapon.Begin(); weapon < dropped_weapon.End(); weapon++)
		{
			if (weapon)
				weapon->Destroy();
		}

		// clear supply object
		for (SupplyObject* supply = supply_list.Begin(); supply < supply_list.End(); supply++)
		{
			if (supply)
			{
				supply->Destroy();
				supply->createtime = 0;
			}
		}

		for (DroppedSupply* sup = dropped_supply.Begin(); sup < dropped_supply.End(); sup++)
		{
			if (sup)
				sup->Destroy();
		}

		int point_size[2];
		point_size[0] = start_point[0].size();
		point_size[1] = start_point[1].size();

		for (int i = 0; i < 2; i++)
		{
			for (int pos = 0; pos < point_size[i]; pos++)
			{
				Transform temp = start_point[i][pos];
				int swap_pos = rand() % point_size[i];
				start_point[i][pos] = start_point[i][swap_pos];
				start_point[i][swap_pos] = temp;
			}
		}
		switch (game_type)
		{
		case kZombieMode:
			{
				zombie_target_area_id = 0;
				zombie_step_two_flag = false;

				Transform transform;
				for (int i = 0; i < max_client_count; ++ i)
				{
					if (clients[i].IsReady())
					{
						if (clients[i].team < 2)
						{

							clients[i].zombie_rebirth_counter = 0;
							clients[i].zombie_mode_dying_state = false;
							clients[i].zombie_mode_saving_dying_uid = 0;
							clients[i].zombie_mode_saver_uid = 0;
							clients[i].zombie_mode_save_timer = 0.f;
							clients[i].human_rebirth_counter = 0;
							clients[i].zombie_rebirth_counter = 0;

							clients[i].SetTeam(0);

							if (point_size[clients[i].team] > 0)
								transform = start_point[clients[i].team][(clients[i].uid & 0x0f) % point_size[clients[i].team]];

							if (!clients[i].GetCurCharinfo().can_select)
							{
								clients[i].UseDefaultCharInfo();
							}	
							clients[i].Spawn(transform, NULL, first_round == false);

						}
					}
				}
			}
			break;
		case kBombMode:
			{
				int bomb_count = 1;
				int bomb_pos = -1;
				int pos = -1;

				ClearPlantState();
				ClearDefusingState();

				uint client_count = 0;

				for (int i = 0; i < max_client_count; ++ i)
				{
					if (clients[i].IsReady() && clients[i].team == 1)
						client_count++;
				}

				if (client_count)
					bomb_pos = rand() % client_count;	// better use client_count * rand() / (RAND_MAX + 1) instead for more random


				// spawn
				Transform transform;
				for (int i = 0; i < max_client_count; ++ i)
				{
					if (clients[i].IsReady())
					{
						//Weapon* b = NULL;
						bool has_bomb = false;
						if (bomb_count > 0 && clients[i].team == 1)
						{
							pos++;

							if (pos == bomb_pos)
							{
							//	b = &config.bomb;
								has_bomb = true;
								bomb_owner_id = clients[i].uid;
								bomb_count--;
							}
						}

						if (clients[i].team < 2)
						{
							if (point_size[clients[i].team] > 0)
								transform = start_point[clients[i].team][(clients[i].uid & 0x0f) % point_size[clients[i].team]];

							if (!clients[i].GetCurCharinfo().can_select)
							{
								clients[i].SetTeam(0);
								clients[i].UseDefaultCharInfo();
							}
							clients[i].Spawn(transform, &config.bomb, first_round == false, has_bomb);	
						}
					}
				}
			}
			break;
		case kCommonZombieMode:
			{
				somg_area.clear();
				special_person_flashed = false;
				can_super = false;
				zombie_super = true;
				human_super = true;
				Transform transform;
				for (int i = 0; i < max_client_count; ++ i)
				{
					if (clients[i].IsReady())
					{
						if (clients[i].team < 2)
						{
							if (point_size[clients[i].team] > 0)
								transform = start_point[clients[i].team][(clients[i].uid & 0x0f) % point_size[clients[i].team]];

							if (!clients[i].GetCurCharinfo().can_select)
							{
								clients[i].SetTeam(0);
								clients[i].UseDefaultCharInfo();
							}	
							clients[i].common_zombie_level = 0;
							clients[i].common_zombie_energy = 0;
							clients[i].common_zombie_energy_percent = 0.0f;
							clients[i].is_zombie_king = false;
							clients[i].die_pos = Vector3(0, 0, 0);
							clients[i].SetTeam(0);
							clients[i].can_spawn = true;
							clients[i].zombie_unable_state = false;
							clients[i].zombie_unable_state_flag = true;
							clients[i].human_energy = 0;
							clients[i].unable_time = -1.0f;
							clients[i].is_zombie_super = false;
							clients[i].is_human_super = false;
							clients[i].super_human_skill_cd_time = -1.0f;
							clients[i].is_common_zombie_spawn = false;
							clients[i].Spawn(transform, NULL, first_round == false);	
						}
					}
				}
			}
			break;
		case kBossMode2:
			{
				special_person_flashed = false;
				
				Transform transform;
				for (int i = 0; i < max_client_count; ++ i)
				{
					if (clients[i].IsReady())
					{
						if (clients[i].team < 2)
						{
							if (!clients[i].GetCurCharinfo().can_select)
							{
								clients[i].SetTeam(0);
								clients[i].UseDefaultCharInfo();
							}
							
							if (point_size[clients[i].team] > 0)
								transform = start_point[clients[i].team][(clients[i].uid & 0x0f) % point_size[clients[i].team]];

							clients[i].Boss2Cleanup();
							clients[i].Spawn(transform, NULL, first_round == false);
						}
					}
				}
			}
			break;
		case KMoonMode:
			{
				picked_count = 0;
				Transform transform;
				for (int i = 0; i < max_client_count; ++ i)
				{
					if (clients[i].IsReady())
					{
						if (clients[i].team < 2)
						{
							if (point_size[clients[i].team] > 0)
								transform = start_point[clients[i].team][(clients[i].uid & 0x0f) % point_size[clients[i].team]];
							clients[i].Spawn(transform, NULL, first_round == false);
						}
					}
				}
			}
			break;
		default:
			{
				// spawn
				Transform transform;
				for (int i = 0; i < max_client_count; ++ i)
				{
					if (clients[i].IsReady())
					{
						if (clients[i].team < 2)
						{
							if (point_size[clients[i].team] > 0)
								transform = start_point[clients[i].team][(clients[i].uid & 0x0f) % point_size[clients[i].team]];

							if (!clients[i].GetCurCharinfo().can_select)
							{
								clients[i].SetTeam(0);
								clients[i].UseDefaultCharInfo();
							}	
							clients[i].Spawn(transform, NULL, first_round == false);
							
						}
					}
				}
			}
			break;

		}
		

		switch (game_type)
		{
		case kHoldPoint:
			{
				time_max = config.round_time_max_holdpoint;

				team_timer[0] = team_timer[1] = config.round_time_holdpoint;
				team_holdpoint_num[0] = team_holdpoint_num[1] = 0;

				cur_holdpoint = 0;
				cur_holdpoint_diffnum = 0;

				for (int i = 0; i < hold_points.size(); i++)
				{
					hold_points[i].owner_team = InvalidTeam;
					hold_points[i].snatch_team = InvalidTeam;
					hold_points[i].snatching_timer = 0.f;
				}
			}
			break;
		case kNovice:
		case kPushVehicle:
			{
				time_max = config.round_time_max_vehicle;

				team_timer[0] = team_timer[1] = 0;

				current_vehicle_id[0] = current_vehicle_id[1] = 0;

				Vector3 delta_pos_normalize[2];

				for (uint i = 0; i < 2; i++)
				{
					vehicle_current_info[i] = level_info.push_vehicle_point[i][0];

					delta_pos_normalize[i] = level_info.push_vehicle_point[i][current_vehicle_id[i] + 1].position - level_info.push_vehicle_point[i][current_vehicle_id[i]].position;

					float length_delta = Length(delta_pos_normalize[i]);

					delta_pos_normalize[i] = Vector3(delta_pos_normalize[i].x/length_delta, delta_pos_normalize[i].y/length_delta, delta_pos_normalize[i].z/length_delta);

					vehicle_current_info[i].dir = delta_pos_normalize[i];
				}

				for (int i = 0; i < max_client_count; ++ i)
				{
					if (clients[i].IsReady())
					{
						clients[i].InitializeVehicleInfo();
					}
				}
			}
			break;

		case kBossMode:
			{
				special_person_start_timer = 0.f;
				time_max = config.round_time_max_boss;
			}
			break;
		case kBossMode2:
			{
				special_person_start_timer = 0.f;
				time_max = config.round_time_max_boss2;
			}
			break;
		case kKnifeMode:
			{
				time_max = config.round_time_max_knife;
			}
			break;
		case kTeamDeathMatch:
			{
				time_max = config.round_time_max_deathmatch;
			}
			break;
		case kBombMode:
			{
				time_max = config.round_time_max_bomb;
			}
			break;
		case kZombieMode:
			{
				time_max = config.round_time_max_zombie;
			}
			break;
		case kStreetBoyMode:
			{
				special_person_start_timer = 0.f;
				sync_street_king_timer = 0.f;
				time_max = config.round_time_max_streetboy;
				street_king_info[0].last_street_king_uid = street_king_info[0].current_street_king_uid;
				street_king_info[1].last_street_king_uid = street_king_info[1].current_street_king_uid;
				street_king_info[0].current_street_king_uid = 0;
				street_king_info[1].current_street_king_uid = 0;
			}
			break;
		case  kCommonZombieMode:
			{
				special_person_start_timer = 0.f;
				time_max = config.round_time_max_commonzombie;
			}
			break;
		case kBossPVE:
			{
				time_max = config.round_time_max_bosspve;
				for (uint i = 0; i < 4; i++)
				{
					/*bosspve_info[i].position = level_info.push_vehicle_point[i].position;
					bosspve_info[i].phase = level_info.push_vehicle_point[i].dir.x;
					bosspve_info[i].speed = level_info.push_vehicle_point[i].dir.y;
					bosspve_info[i].bossID = level_info.push_vehicle_point[i].dir.z;
					bosspve_info[i].action = level_info.push_vehicle_point[i].sliding;*/
				}
			}
			break;
		case kTDMode:
			{
				time_max = config.round_time_max_td;
			}
			break;
		case KMoonMode:
			{
				time_max = config.moonmode_time_max;
			}
			break;
		case kAdvenceMode:
			{
				time_max = config.round_time_max_advence;
				for (int i = 0; i < max_client_count; ++ i)
				{
					if (clients[i].IsReady())
					{
						clients[i].CCoinCount = 0;
						clients[i].MedalCount = 0;
						clients[i].WrenchCount = 0;
						clients[i].BoxACount = 0;
						clients[i].BoxBCount = 0;
						clients[i].BoxCCount = 0;
						clients[i].BoxDCount = 0;
					}
				}
			}
			break;
		case kSurvivalMode:
			{
				trap_info.clear();
			}
			break;
		default:
			{
				time_max = config.round_time_max_normal;
			}
			break;
		}

		for (int i = 0; i < max_client_count; ++ i)
		{
			if (clients[i].IsReady())
			{
				clients[i].fu_huo_bi_use_count = 0;
			}
		}
	}
	else
	{
		time_max = config.round_time_max_normal;
	}

	//for bot test.
	//timeval tv;
	//gettimeofday(&tv, NULL);
	//srand(tv.tv_usec);
	//time_max -= 15 * 60 * (rand() / (RAND_MAX + 1.0));
	
	Client::boss2_defence_energy = 0;
	Client::boss2_defence_energy_max = config.boss2_defence_energy_max_init;
	Client::boss2_defence_energy_level = 0;

	if (round_start_time <= 0)
		round_start_time = 0;

	for (int i = 0; i < max_client_count; ++ i)
	{
		if (clients[i].IsReady())
		{
			clients[i].RoundStart(round_start_time);
			if (round_start_time == 0)
				clients[i].RoundStartPlay();
			clients[i].Boss2SyncData();
		}
	}
	
	boss_life_gold_count = 0;
	
	//create server dummy
	//{
	//	// test
	//	ServerDummyCreateInfo c_info;
	//	
	//	c_info.position = Vector3(1,1,1);
	//	c_info.rotation.SetZXY(Vector3(0,0,0));
	//	c_info.hp = c_info.maxhp = 100;
	//	strcpy(c_info.res_key, "/engineer01001_0/rv1");
	//	
	//	ServerDummyCreate(c_info);
	//}

	//create server dummy force with stepfather
	{

		for (uint i = 0; i < level_info.stepfather_dummy_info.size(); i++)
		{
			ServerDummyCreateStepFather(level_info.stepfather_dummy_info[i], 1);
		}
	}	
}

// on round end
void Game::OnRoundEnd(bool time_out, byte team)
{
	if (team < 2)
	{
		round_playing = false;
		switch (game_type)
		{
		case kTeam:
		case kKnifeMode:
		case kItemMode:
		case kEditMode:
		case kTDMode:
			break;
		case kHoldPoint:
			{
				if (time_out)
				{
					if (team_timer[0] < team_timer[1])
						team = 0;
					else if (team_timer[0] > team_timer[1])
						team = 1;
					else
					{
						int Scores[2] = {0};
						for (int i = 0; i < max_client_count; ++ i)
						{
							if (clients[i].IsReady() && clients[i].team < 2)
							{
								Scores[clients[i].team] += clients[i].data.GetScore();
							}
						}

						if (Scores[0] > Scores[1])
							team = 0;
						else if (Scores[0] < Scores[1])
							team = 1;
						/*else
							team = -1;*/
					}
				}

				if (team != InvalidTeam)
				{
					team_rounds[team]++;
				}

				if (team != InvalidTeam && team_rounds[team] >= rule_value)
				{
					for (int i = 0; i < max_client_count; ++ i)
					{
						if (clients[i].IsReady())
						{
							clients[i].RoundEnd(team);
						}
					}
					if (level_info.is_rushgold)
						OnGameEndTimer(team);
					else
						OnGameEnd(team);
				}
				else
				{
					round_end_time = config.round_end_special_time_max;
					gold_time = 1.5f;
					for (int i = 0; i < max_client_count; ++ i)
					{
						if (clients[i].IsReady())
						{
							clients[i].RoundEnd(team);
						}
					}
				}
			}
			break;
		case kPushVehicle:
			{
				if (time_out)
				{
					float vehicle[2] = {0};

					vehicle[0] = vehicle_current_info[0].total_length / level_info.push_vehicle_total_length[0];
					vehicle[1] = vehicle_current_info[1].total_length / level_info.push_vehicle_total_length[1];


					if (vehicle[0] > vehicle[1])
						team = 0;
					else if (vehicle[0] < vehicle[1])
						team = 1;
					else
					{
						int Scores[2] = {0};
						for (int i = 0; i < max_client_count; ++ i)
						{
							if (clients[i].IsReady() && clients[i].team < 2)
							{
								Scores[clients[i].team] += clients[i].data.GetScore();
							}
						}

						if (Scores[0] > Scores[1])
							team = 0;
						else if (Scores[0] < Scores[1])
							team = 1;
						/*else
							team = -1;*/
					}
				}

				if (team != InvalidTeam)
				{
					team_rounds[team]++;
				}

				if (team != InvalidTeam && team_rounds[team] >= rule_value)
				{
					for (int i = 0; i < max_client_count; ++ i)
					{
						if (clients[i].IsReady())
						{
							clients[i].RoundEnd(team);
						}
					}
					if (level_info.is_rushgold)
						OnGameEndTimer(team);
					else
						OnGameEnd(team);
				}
				else
				{
					round_end_time = config.round_end_special_time_max;
					gold_time = 1.5f;
					for (int i = 0; i < max_client_count; ++ i)
					{
						if (clients[i].IsReady())
						{
							clients[i].RoundEnd(team);
						}
					}

				}
			}
			break;
		case kTeamDeathMatch:
			{
				if (team != InvalidTeam)
				{
					team_rounds[team]++;
				}

				if (team != InvalidTeam && team_rounds[team] >= rule_value)
				{
					for (int i = 0; i < max_client_count; ++ i)
					{
						if (clients[i].IsReady())
						{
							clients[i].RoundEnd(team);
						}
					}
					OnGameEnd(team);
				}
				else
				{
					byte team_count[2] = {0};

					for (int i = 0; i < max_client_count; ++ i)
					{
						if (clients[i].IsReady() && clients[i].team < 2)
						{
							team_count[clients[i].team]++;
						}
					}
					if(team_count[0] == 0)
					{
						OnGameEnd(1);
					}
					else if(team_count[1] == 0)
					{
						OnGameEnd(0);
					}
					else
					{
						round_end_time = config.round_end_time_max;

						for (int i = 0; i < max_client_count; ++ i)
						{
							if (clients[i].IsReady())
							{
								clients[i].RoundEnd(team);
							}
						}
					}

				}

			}
			break;
		case kBossMode:
		case kZombieMode:
			{
				if (team != InvalidTeam)
				{
					team_rounds[team]++;

					if (game_type == kBossMode)
					{
						for (int i = 0; i < max_client_count; ++ i)
						{
							if (clients[i].IsReady() && clients[i].team == team && clients[i].team < 2)
							{
								if (server.cServerType == (byte)SvrType_Match)
								{
								}
								else
								{
									clients[i].data.ScoreDataAdd(config.kill_boss_score);
								}
							}
						}
					}
					else if (game_type == kZombieMode)
					{
						if (team == 0)
						{
							int human_alive = 0;
							int human_died = 0;
							
							for (int i = 0; i < max_client_count; ++ i)
							{
								if (clients[i].IsReady() && clients[i].team == team)
								{
									if (clients[i].IsAlive())
										human_alive++;
									else
										human_died++;
								}
							}
							
							float runaway_human_alive_score = config.runaway_human_alive_score_base * human_alive;
							float runaway_human_died_score = config.runaway_human_died_score_base * human_alive;
							for (int i = 0; i < max_client_count; ++ i)
							{
								if (clients[i].IsReady() && clients[i].team == team)
								{
									if (clients[i].IsAlive())
									{
										if (server.cServerType == (byte)SvrType_Match)
										{
										}
										else
										{
											clients[i].data.ScoreDataAdd(runaway_human_alive_score, true);
										}
									}
									else
									{
										if (server.cServerType == (byte)SvrType_Match)
										{
										}
										else
										{
											clients[i].data.ScoreDataAdd(runaway_human_died_score, true);
										}
									}
								}
							}
						}
					}
				}

				if (team != InvalidTeam && team_rounds[team] >= rule_value)
				{
					for (int i = 0; i < max_client_count; ++ i)
					{
						if (clients[i].IsReady())
						{
							clients[i].RoundEnd(team);
						}
					}
					OnGameEnd(team);
				}
				else
				{
					if(time_out)
					{
						for (int i = 0; i < max_client_count; ++ i)
						{
							if (clients[i].IsReady())
							{
								clients[i].RoundEnd(1);
							}
						}
					}
					else
					{
						byte team_count[2] = {0};

						for (int i = 0; i < max_client_count; ++ i)
						{
							if (clients[i].IsReady() && clients[i].team < 2)
							{
								team_count[clients[i].team]++;
							}
						}
						if(team_count[0] + team_count[1] <= 1)
						{
							if(team_count[0] == 0)
							{
								OnGameEnd(1);
							}
							else if(team_count[1] == 0)
							{
								OnGameEnd(0);
							}
						}
						else
						{
							for (int i = 0; i < max_client_count; ++ i)
							{
								if (clients[i].IsReady())
								{
									clients[i].RoundEnd(team);
								}
							}
						}


					}
				}

				special_person_start_timer = -2.f;
				special_person_flashed = false;
				round_end_time = config.round_end_time_max;
			}
			break;
		case kBossMode2:
			{
				if (team != InvalidTeam)
				{
					team_rounds[team]++;

					for (int i = 0; i < max_client_count; ++ i)
					{
						if (clients[i].IsReady() && clients[i].team == team && clients[i].team < 2)
						{
							if (team == 0)
							{
								if (server.cServerType == (byte)SvrType_Match)
								{
								}
								else
								{
									clients[i].data.ScoreDataAdd(config.boss2_humanwin_score);
								}
							}
							else if (team == 1)
							{
								if (server.cServerType == (byte)SvrType_Match)
								{
								}
								else
								{
									clients[i].data.ScoreDataAdd(config.boss2_bosswin_score);
								}
							}
						}
					}
				}

				if (team != InvalidTeam && team_rounds[team] >= rule_value)
				{
					for (int i = 0; i < max_client_count; ++ i)
					{
						if (clients[i].IsReady())
						{
							clients[i].RoundEnd(team);
						}
					}
					OnGameEnd(team);
				}
				else
				{
					if(time_out)
					{
						for (int i = 0; i < max_client_count; ++ i)
						{
							if (clients[i].IsReady())
							{
								clients[i].RoundEnd(1);
							}
						}
					}
					else
					{
						byte team_count[2] = {0};

						for (int i = 0; i < max_client_count; ++ i)
						{
							if (clients[i].IsReady() && clients[i].team < 2)
							{
								team_count[clients[i].team]++;
							}
						}
						if(team_count[0] + team_count[1] <= 1)
						{
							if(team_count[0] == 0)
							{
								OnGameEnd(1);
							}
							else if(team_count[1] == 0)
							{
								OnGameEnd(0);
							}
						}
						else
						{
							for (int i = 0; i < max_client_count; ++ i)
							{
								if (clients[i].IsReady())
								{
									clients[i].RoundEnd(team);
								}
							}
						}


					}
				}

				special_person_start_timer = -2.f;
				special_person_flashed = false;
				round_end_time = config.round_end_time_max;
			}
			break;
		case kBombMode:
			{
				if (team != InvalidTeam)
				{
					team_rounds[team]++;
				}

				if (team != InvalidTeam && team_rounds[team] >= rule_value)
				{
					for (int i = 0; i < max_client_count; ++ i)
					{
						if (clients[i].IsReady())
						{
							clients[i].RoundEnd(team);
						}
					}
					OnGameEnd(team);
				}
				else
				{

					byte team_count[2] = {0};

					for (int i = 0; i < max_client_count; ++ i)
					{
						if (clients[i].IsReady() && clients[i].team < 2)
						{
							team_count[clients[i].team]++;
						}
					}
					if(team_count[0] == 0)
					{
						OnGameEnd(1);
					}
					else if(team_count[1] == 0)
					{
						OnGameEnd(0);
					}
					else
					{
						round_end_time = config.round_end_time_max;

						for (int i = 0; i < max_client_count; ++ i)
						{
							if (clients[i].IsReady())
							{
								clients[i].RoundEnd(team);
							}
						}
					}

				}

			}
			break;
		case kStreetBoyMode:
			{
				if (team != InvalidTeam && !time_out)
				{
					team_rounds[team]++;
				}

				if (team != InvalidTeam && team_rounds[team] >= rule_value)
				{
					for (int i = 0; i < max_client_count; ++ i)
					{
						if (clients[i].IsReady())
						{
							clients[i].RoundEnd(team);
						}
					}
					OnGameEnd(team);
				}
				else
				{
					if(time_out)
					{
						
						byte t = 0;
						float p[2];
						for (int j = 0; j < 2; j++)
						{
							p[j] = 0.f;
							for (int i = 0; i < max_client_count; ++ i)
							{
								if(clients[i].IsReady() && clients[i].IsAlive() && street_king_info[j].current_street_king_uid == clients[i].uid)
								{
									p[j] = clients[i].health / clients[i].max_health;
									p[j] = Clamp(p[j], 0.f, 1.f);
								}
							}
						}
						
						if(abs(p[0] - p[1]) < 0.0001f)
						{
							t = 2;
						}
						else
						{
							t = p[0] > p[1] ? 0 : 1;
							team_rounds[team]++;
						}
						
						
			
						for (int i = 0; i < max_client_count; ++ i)
						{
							if (clients[i].IsReady())
							{
								clients[i].RoundEnd(t);
							}
						}
					}
					else
					{
						byte team_count[2] = {0};

						for (int i = 0; i < max_client_count; ++ i)
						{
							if (clients[i].IsReady() && clients[i].team < 2)
							{
								team_count[clients[i].team]++;
							}
						}
						if(team_count[0] == 0)
						{
							OnGameEnd(1);
						}
						else if(team_count[1] == 0)
						{
							OnGameEnd(0);
						}
						else
						{
							for (int i = 0; i < max_client_count; ++ i)
							{
								if (clients[i].IsReady())
								{
									clients[i].RoundEnd(team);
								}
							}
						}
					}
					

				}
				round_end_time = config.round_end_time_max;
				special_person_start_timer = -2.f;
				special_person_flashed = false;

			}
			break;
		case kBossPVE:
			{
				if (team != InvalidTeam)
				{
					team_rounds[team]++;
				}

				if (team != InvalidTeam && team_rounds[team] >= rule_value)
				{
					for (int i = 0; i < max_client_count; ++ i)
					{
						if (clients[i].IsReady())
						{
							clients[i].RoundEnd(team);
						}
					}
					OnGameEnd(team);
				}
				else
				{
					for (int i = 0; i < max_client_count; ++ i)
					{
						if (clients[i].IsReady())
						{
							clients[i].RoundEnd(team);
						}
					}
				}
				round_end_time = config.round_end_time_max;
				special_person_start_timer = -2.f;
				special_person_flashed = false;
			}
			break;
		case kCommonZombieMode:
			{
				if (team != InvalidTeam)
				{
					team_rounds[team]++;
				}

				for (int i = 0; i < max_client_count; ++ i)
				{
					if (clients[i].IsReady() && clients[i].team == team)
					{
						if (server.cServerType == (byte)SvrType_Match)
						{
						}
						else
						{
							clients[i].data.ScoreDataAdd(server.config.common_zombie_win, true);
						}
					}
				}

				if (team != InvalidTeam && team_rounds[team] >= rule_value)
				{
					for (int i = 0; i < max_client_count; ++ i)
					{
						if (clients[i].IsReady())
						{
							clients[i].RoundEnd(team);
						}
					}
					OnGameEnd(team);
				}
				else
				{
					if(time_out)
					{
						for (int i = 0; i < max_client_count; ++ i)
						{
							if (clients[i].IsReady())
							{
								clients[i].RoundEnd(0);
							}
						}
					}
					else
					{
						byte team_count[2] = {0};

						for (int i = 0; i < max_client_count; ++ i)
						{
							if (clients[i].IsReady() && clients[i].team < 2)
							{
								team_count[clients[i].team]++;
							}
						}
						if(team_count[0] + team_count[1] <= 1)
						{
							if(team_count[0] == 0)
							{
								OnGameEnd(1);
							}
							else if(team_count[1] == 0)
							{
								OnGameEnd(0);
							}
						}
						else
						{
							for (int i = 0; i < max_client_count; ++ i)
							{
								if (clients[i].IsReady())
								{
									clients[i].RoundEnd(team);
								}
							}
						}


					}
				}
				special_person_start_timer = -2.f;
				round_end_time = config.round_end_time_max;
			}
			break;
		case KMoonMode:
			{
				if(time_out)
				{
					int sum0 = 0;
					int sum1 = 0;
					for (int i = 0; i < max_client_count; ++ i)
					{
						if (clients[i].team == 0)
							sum0 += clients[i].data.GetScore();
						else if (clients[i].team == 1)
						{
							sum1 += clients[i].data.GetScore();
						}
					}
					if (sum0 == sum1)
					{
						team = rand() % 2;
					}
					else if (sum0 > sum1)
					{
						team = 0;
					}
					else
					{
						team = 1;
					}
					for (int i = 0; i < max_client_count; ++ i)
					{
						if (clients[i].IsReady())
						{
							clients[i].RoundEnd(team);
						}
					}
					OnGameEnd(team);
				}
				else
				{
					if (team != InvalidTeam)
					{
						team_rounds[team]++;
					}
					if (team != InvalidTeam && team_rounds[team] >= rule_value)
					{
						for (int i = 0; i < max_client_count; ++ i)
						{
							if (clients[i].IsReady())
							{
								clients[i].RoundEnd(team);
							}
						}
						OnGameEnd(team);
					}
				}
			}
			break;
		case kAdvenceMode:
			{
				OnGameEnd(team);
			}
		case kSurvivalMode:
			{
				OnGameEnd(team);
			}
			break;
		}
	}
}

void Game::WriteDieBuff()
{
	for (int i = 0; i < max_client_count; ++ i)
	{
		if (clients[i].IsReady())
		{
			if (clients[i].team < 2)
			{
				clients[i].BeginWrite();
				clients[i].WriteByte(SM_DIE_BUFF_DATA);
				const uint iCnt =(uint)level_info.die_buff_data.size();
				clients[i].WriteInt(iCnt);
				for(uint j =0; j<iCnt; ++j)
				{
					DieBuffData& dd =level_info.die_buff_data[j];
					clients[i].WriteFloat(dd.duration_timer);
					clients[i].WriteFloat(dd.interval);
					clients[i].WriteInt(dd.type);
					clients[i].WriteFloat(dd.value);
					clients[i].WriteString(dd.res_key);
					clients[i].WriteString(dd.res_desc);
					clients[i].WriteInt(dd.rate);
				}
	
				clients[i].EndWrite();
			}
		}
	}
}

// on game start
void Game::OnGameStart()
{
	if (server.cServerType == SvrType_Match)
	{
		log_write(LOG_DEBUG1, "*****SvrType_Match GameStart socket : %d",channel_socket);
	}
	if (server.cServerType == SvrType_MatchFighting)
	{
		log_write(LOG_DEBUG1, "*****SvrType_MatchFighting GameStart socket : %d",channel_socket);
	}


	OnRoundStart(true);
	WriteDieBuff();
	switch (game_type)
	{
	case kHoldPoint:
	case kPushVehicle:
	case kTeamDeathMatch:
	case kNovice:
	case kBossMode:
	case kBossMode2:
	case kBombMode:
	case kStreetBoyMode:
	case kZombieMode:
	case kCommonZombieMode:
	case kTDMode:
		break;
	case kBossPVE:
		{
			bosspve_end_game = false;
			now_phase = 0;
			SetServerScriptNumberValue("BOSSPVE_PHASE_INDEX", now_phase);
		}
		break;
		
	default:
		{
			int point_size[2];
			point_size[0] = start_point[0].size();
			point_size[1] = start_point[1].size();
			Transform transform;
			transform.position.x = transform.position.y = transform.position.z = 0;
			transform.rotation = 0;

			if (game_type == kAdvenceMode)
			{
				for (SupplyObject* supply = supply_list.Begin(); supply < supply_list.End(); supply++)
				{
					if (supply && supply->supply.type >= kSupplyCCoin && supply->supply.type <= kSupplyBoxD)
					{
						supply->Create();
						supply->createtime = 0;
						supply->position = supply->supply.position;
						supply->rotation = 0;
						for (int j = 0; j < max_client_count; ++j)
						{
							if (clients[j].IsReady())
								clients[j].AddSupplyObject(supply);
						}
					}
				}
			}
			else if (game_type == kSurvivalMode)
			{
				for (int j = 0; j < max_client_count; ++j)
				{
					if (clients[j].IsReady())
					{
						clients[j].beghostcount = 0;
						clients[j].ghostflag = false;
						clients[j].ghostfirecount = 0;
						clients[j].isghost = false;
					}
				}
				for (SupplyObject* supply = supply_list.Begin(); supply < supply_list.End(); supply++)
				{
					if (supply && supply->supply.type > kSupplySurvivalItemStart && supply->supply.type < kSupplySurvivalItemEnd)
					{
						supply->Create();
						supply->createtime = 0;
						supply->position = supply->supply.position;
						supply->rotation = 0;
						for (int j = 0; j < max_client_count; ++j)
						{
							if (clients[j].IsReady())
								clients[j].AddSupplyObject(supply);
						}
					}
				}
			}

			// spawn
			for (int i = 0; i < max_client_count; ++ i)
			{
				if (clients[i].IsReady())
				{
					if (clients[i].team < 2)
					{
						if (point_size[clients[i].team] > 0)
							transform = start_point[clients[i].team][(clients[i].uid & 0x0f) % point_size[clients[i].team]];
						clients[i].Spawn(transform, NULL, false);
					}
				}
			}
		}
		break;
	}
}

// on game end
void Game::OnGameEnd(int team)
{
	log_write(LOG_DEBUG1, "GAME: game end.");

	if (!playing)
	{
		return;
	}

	if (server.cServerType == SvrType_Match)
	{
		log_write(LOG_DEBUG1, "*****SvrType_Match GameEnd socket : %d",channel_socket);
	}
	if (server.cServerType == SvrType_MatchFighting)
	{
		log_write(LOG_DEBUG1, "*****SvrType_MatchFighting GameStart socket : %d",channel_socket);
	}

	playing = false;
	loading = false;
	

	if (team == -1)
	{
		switch (game_type)
		{
		case kTDMode:
			{
				float rate = (float)cur_reshp / (float)max_reshp;
				if (rate < 0.5f)
					team = 0;
				else
					team = 1;
			}
			break;
		case kTeam:
		case kKnifeMode:
		case kItemMode:
			{
				if (team_kills[0] > team_kills[1])
					team = 0;
				else if (team_kills[0] < team_kills[1])
					team = 1;
			}
			break;

		case kHoldPoint:
		case kPushVehicle:
		case kTeamDeathMatch:
		case kBombMode:
		case kStreetBoyMode:
		case kZombieMode:
		case kCommonZombieMode:
		case kBossMode:
		case kBossMode2:
		case kSurvivalMode:
			{
				if (team_rounds[0] > team_rounds[1])
					team = 0;
				else if (team_rounds[0] < team_rounds[1])
					team = 1;
			}
			break;
		case kAdvenceMode:
			{
				team = 0;
			}
		default:
			break;
		}
	}

	switch (game_type)
	{
	case kHoldPoint:
		for (int i = 0; i < max_client_count; ++ i)
		{
			if (clients[i].IsConnected())
				clients[i].SyncHoldPointInfo();
		}
		break;
	}

	RequestStageClear(team);

	for (int i = 0; i < max_client_count; ++ i)
	{
		if (clients[i].IsConnected())
			clients[i].GameEnd(team);
	}

	m_bIsMatching = false;
}

// get score type
byte Game::GetScoreType()
{
	switch (game_type)
	{
	case kTeam:
	case kKnifeMode:
	case kItemMode:
	
		return kKills;
		break;

	case kTeamDeathMatch:
	case kPushVehicle:
	case kHoldPoint:
	case kBossMode:
	case kBossMode2:
	case kStreetBoyMode:
	case kZombieMode:
	case kBossPVE:
	case kCommonZombieMode:
	case KMoonMode:
	case kAdvenceMode:
	case kSurvivalMode:
		return kRounds;
		break;

	default:
		break;
	}

	return kKills;
}

// 
void Game::BuildServerScriptList(const std::string &text)
{
	ServerScript TmpServerScript;
	std::vector<std::string> DataList;
	
	DataList.reserve(256);
	SpltCsv(text.c_str(), DataList, ';');
	
	for (size_t i = 0; i < DataList.size(); i++)
	{
		if (ServerScript::BuildServerScript(DataList[i], TmpServerScript))
		{
			server_script_list.push_back(TmpServerScript);
		}
		else
		{
			DEBUGLOG_WRITE("BuildServerScript failed : id : %u, text : %s", i, DataList[i].c_str());
		}
	}
}

// 
void Game::SetServerScriptStringValue(const std::string &key, const std::string &value)
{
	std::map<std::string, std::string>::iterator itr = server_script_stringvalue.find(key);
	if (itr != server_script_stringvalue.end())
	{
		if (itr->second != value)
		{
			itr->second = value;
			
			server_script_stringvalue_dirty.insert(key);
		}
	}
	else
	{
		server_script_stringvalue.insert(std::make_pair(key, value));
		
		server_script_stringvalue_dirty.insert(key);
	}
}

// 
bool Game::GetServerScriptStringValue(const std::string &key, std::string &value)
{
	std::map<std::string, std::string>::iterator itr = server_script_stringvalue.find(key);
	if (itr != server_script_stringvalue.end())
	{
		value = itr->second;
	
		return true;
	}
	
	return false;
}

// 
void Game::SetServerScriptNumberValue(const std::string &key, float value)
{
	std::map<std::string, float>::iterator itr = server_script_numbervalue.find(key);
	if (itr != server_script_numbervalue.end())
	{
		if (itr->second != value)
		{
			itr->second = value;
			
			server_script_numbervalue_dirty.insert(key);
		}
	}
	else
	{
		server_script_numbervalue.insert(std::make_pair(key, value));
		
		server_script_numbervalue_dirty.insert(key);
	}
}

// 
bool Game::GetServerScriptNumberValue(const std::string &key, float &value)
{
	std::map<std::string, float>::iterator itr = server_script_numbervalue.find(key);
	if (itr != server_script_numbervalue.end())
	{
		value = itr->second;
	
		return true;
	}
	
	return false;
}

// request update keywords
void Game::RequestUpdateKeywords()
{
	// read keywords
	int keyword_count;
	ReadInt(keyword_count);
	DictMatch::Clear();
	for (int i = 0; i < keyword_count; i++)
	{
		char keyword[256] = {0};
		ReadString(keyword, sizeof(keyword));
		DictMatch::AddKeyword(keyword);
	}
	DictMatch::Build();
}

// request client enter
void Game::RequestClientEnter()
{
	int uid_in_room;

	ReadInt(uid_in_room);

	Client* client = GetClientById(uid_in_room);

	int error_code = kErrorChannelGameEnter;
	if (client && !client->IsReady())
	{
		log_write(LOG_DEBUG1, "%s, %s, uid in room : %d, client enter name : %s", __FILE__, __FUNCTION__, uid_in_room, client->GetCharacterInfo().character_name);

		client->uid_in_room = uid_in_room;
		ReadInt(client->uid_in_channel);
		ReadByte(client->team);
		ReadString(client->head_icon, sizeof(client->head_icon));
		ReadByte(client->is_vip);
		ReadByte(client->business_card);
		ReadByte(client->is_gm);
		ReadInt(client->level);
		ReadInt(client->character_group_id);
		if (client->team == 0 && group_id_t1 == 0)
			group_id_t1 = client->character_group_id;
		if (client->team == 1 && group_id_t2 == 0)
			group_id_t2 = client->character_group_id;

		try
		{
			CharacterInfo info;
			
			log_write(LOG_DEBUG4, "try character info %d",read_end - read_position);
			ReadCharacterInfo(*this, info);
			ReadBagInfo(*this, info.bag);
			
			client->kick_client_count = info.kick_count;
			client->fu_huo_bi = info.fu_huo_bi;

			log_write(LOG_DEBUG1, "kick count %d ", client->kick_client_count);
			log_write(LOG_DEBUG1, "fu_huo_bi %d ", client->fu_huo_bi); 

			if(info.character_count > 0)
			{
				info.character_id = info.singlecharacter_set.begin()->second.player_id;
				
				client->SetCharacterInfo(info);

				switch (game_type)
				{
				case kBossMode:
					{
						if (client->AddSingleCharacterInfo(level_info.boss_info))
						{
							log_write(LOG_DEBUG4, "add boss_info");
						}
						else
						{
							log_write(LOG_DEBUG1, "client already has boss_info %d",
										level_info.boss_info.career_id);

							throw (int)kErrorChannelGameEnterCharacterInfoError;
						}
					}
					break;
				case kBossMode2:
					{
						if (client->AddSingleCharacterInfo(level_info.boss_info))
						{
							log_write(LOG_DEBUG4, "add boss2_info");
						}
						else
						{
							log_write(LOG_DEBUG1, "client already has boss2_info %d",
										level_info.boss_info.career_id);

							throw (int)kErrorChannelGameEnterCharacterInfoError;
						}
						
						for (int i = 0; i < 4; i++)
						{
							if (client->AddSingleCharacterInfo(level_info.bosspve_info[i]))
							{
								log_write(LOG_DEBUG4, "add boss2_info[%d]", i);
							}
							else
							{
								log_write(LOG_DEBUG1, "client already has boss2_info[%d] %d",
											i, level_info.bosspve_info[i].career_id);

								throw (int)kErrorChannelGameEnterCharacterInfoError;
							}
						}
					}
					break;
				case kBossPVE:
					{
						static bool bot_add = false;
						if (bot_add == false)
						{
							for (int i = 0; i < level_info.bosspve_count; i++)
							{
								info.character_id = level_info.bosspve_info[i].player_id;
								strcpy(info.character_name, level_info.bosspve_info[i].careername);
								Client* bot = CreateBot(info);
								if (bot)
								{
									bot_add = true;
									bot->SetBosspveCareerid((uint)level_info.bosspve_info[i].career_id);
									bot->AddSingleCharacterInfo(level_info.bosspve_info[i]);
								}
							}
						}
					}  
					break;
				case kCommonZombieMode:
					{
						if (client->AddSingleCharacterInfo(level_info.Zombie_info) && client->AddSingleCharacterInfo(level_info.KingZombie_info) && client->AddSingleCharacterInfo(level_info.SuperZombie_info))
						{
							log_write(LOG_DEBUG4, "add Zombie_info");
						}
						else
						{
							log_write(LOG_DEBUG1, "client already has boss_info %d",
								level_info.Zombie_info.career_id);

							throw (int)kErrorChannelGameEnterCharacterInfoError;
						}

						for (int i = 0; i < level_info.buff_zombie_info.size(); ++i)
						{
							log_write(LOG_DEBUG3, "buff_zombie_info = %d", level_info.buff_zombie_info[i].career_id);
							if (client->AddSingleCharacterInfo(level_info.buff_zombie_info[i]))
							{
								log_write(LOG_DEBUG3, "111111111111111buff_zombie_info = %d", level_info.buff_zombie_info[i].career_id);
							}
						}
						
					}
					break;
				case kItemMode:
				case KMoonMode:
					{
						if (client->AddSingleCharacterInfo(level_info.boss_info))
						{
							log_write(LOG_DEBUG4, "add ???_info");
						}
						else
						{
							log_write(LOG_DEBUG1, "client already has ???_info %d",
										level_info.boss_info.career_id);

							throw (int)kErrorChannelGameEnterCharacterInfoError;
						}
					}
					break;
				}
			}
			else
			{
				log_write(LOG_DEBUG1, "character count is zero @!!!!!");

				throw (int)kErrorChannelGameEnterCharacterInfoError;
			}

			log_write(LOG_DEBUG4, "read character success!!!!!!!");

			client->ApplyHackFix();
			client->Connect();
			
			client->SyncServerScriptValue(true);

			error_code = kErrorNone;
		}
		catch (int exp)
		{
			log_write(LOG_DEBUG1, "client_enter error[%d], uid_in_room : %d, uid_in_channel : %d", exp, uid_in_room, client->uid_in_channel);
			error_code = exp;
		}
		catch (...)
		{
			log_write(LOG_DEBUG1, "client_enter unknow error, uid_in_room : %d, uid_in_channel : %d", uid_in_room, client->uid_in_channel);
			error_code = kErrorChannelGameEnterCharacterInfoError;
		}
	}

	BeginWrite();
	WriteByte(GM_ResponseClientEnter);
	WriteInt(uid_in_room);
	WriteInt(error_code);
	EndWrite();
}

// on message
void Game::OnMessage()
{
	bool group_match;

	switch (state)
	{
	case GS_Connected:
		{
			int level_id = 0;
			
			ReadInt(server_id);
			ReadInt(channel_id);
			ReadInt(host_character_id);
			ReadByte(cServerType);

			// read room options
			ReadRoomOption(*this, room_option);
			
			// old code
			ReadInt(level_id);
			ReadByte(game_type);
			ReadByte(m_bIsMatching);
			ReadInt(rule_value);
			ReadByte(special_mode);
			ReadShort(round_rebirth_time_max);
			ReadByte((byte&)team_hurt);
			ReadByte((byte&)group_match);
			ReadByte((byte&)check_game_balance);

			log_write(LOG_DEBUG4, "server round_rebirth_time_max %d", (int)round_rebirth_time_max);
			log_write(LOG_DEBUG4, "server check_game_balance %d", (int)check_game_balance);

			// read level info
			log_write(LOG_DEBUG4, "GAME: connected ReadLevelInfo.");
			ReadLevelInfo(*this, level_info);
			ReadInt(idle_kick_open);

			if (level_info.level_id == 0 || level_info.level_id != level_id || 
				(game_type == kHoldPoint && level_info.hold_point.size() == 0) ||
				((game_type == kPushVehicle || game_type == kNovice) &&
					(level_info.push_vehicle_point[0].size() < 2 || level_info.push_vehicle_point[1].size() < 2)))
			{
				log_write(LOG_ERROR, "level info error. level_info.level_id : %d, level_id : %d", 
						level_info.level_id, level_id);
				RequestDisconnect();

				// 已经disconnected了 所以 不需要return 这样 就不会有报错的问题
				//return;
			}
			
			if (game_type == kBossPVE)
			{
				for (byte i = 0; i < level_info.bosspve_count; i++)
				{
					level_info.bosspve_info[i].dropitems = level_info.bosspve_dropitems[i];
					
					for (int j = 0; j < level_info.bosspve_dropitems[i].size(); j++)
					{
						log_write(LOG_DEBUG3, "BossPVE DropItem : [%s], [%d, %f, %f, %d]", 
								level_info.bosspve_info[i].careername, 
								level_info.bosspve_info[i].dropitems[j].itemid, 
								level_info.bosspve_info[i].dropitems[j].parameter1, 
								level_info.bosspve_info[i].dropitems[j].parameter2, 
								level_info.bosspve_info[i].dropitems[j].count);
					}
				}
			}

			start_point[0].clear();
			start_point[1].clear();

			for (std::vector<PointInfo>::const_iterator point = level_info.rebirth_point.begin(); point < level_info.rebirth_point.end(); point++)
			{
				if(point->id < 2)
				{
					start_point[point->id & 1].push_back(point->transform);
				}
				else
				{
					if (game_type == kZombieMode)
						zombie_step_two_point.push_back(point->transform);
					else if (game_type == kBossMode2)
					{
						switch (point->id)
						{
						case 2:
							boss2_rebirth_point[0].push_back(point->transform);
							break;
						case 3:
							boss2_rebirth_point[1].push_back(point->transform);
							break;
						case 4:
							boss2_rebirth_point[2].push_back(point->transform);
							break;
						case 5:
							boss2_rebirth_point[3].push_back(point->transform);
							break;
						}
					}
				}
			}
			
			if (game_type == kBossMode2)
			{
				boss2_boss_rand_start = rand();
				
				for (int i = 0; i < 4; i++)
					boss2_rebirth_rand_start[i] = rand();
			}
			
			/// create all supply
			for (std::vector<Supply>::iterator supply = level_info.supply_set.begin(); supply < level_info.supply_set.end(); supply++)
			{

				SupplyObject* supply_tmp = supply_list.Allocate();
				supply_tmp->supply = *supply;

				if (game_type == kAdvenceMode)
				{
					RandSupplyType(&supply_tmp->supply);
				}
				
				if (supply->type == kSupplyCommonZombie)
				{
					SupplyObject* supply_tmp = common_zombie_supply_list.Allocate();
					supply_tmp->supply = *supply;
				}
				else if (supply->type == kSupplyCommonZombie2)
				{
					SupplyObject* supply_tmp = common_zombie_supply_list2.Allocate();
					supply_tmp->supply = *supply;
				}
			}
			
			// server_script_list
			{
				server_script_list.clear();
				
				// debug only
				if (appcfg.add_debugserverscript)
				{
					std::ifstream debug_file(appcfg.debug_serverscript);
					std::string data;
					
					if (debug_file)
					{
						DEBUGLOG_WRITE("Add Debug ServerScript[%s]", appcfg.debug_serverscript);
						
						std::getline(debug_file, data);
						
						BuildServerScriptList(data);
					}
					
					debug_file.close();
				}
			}
			
			// sys value
			SetServerScriptNumberValue(SYS_PLAYTIME_KEY, 0);
			
			// stepfather_dummy_info
			max_reshp = 0;
			for (uint i = 0; i < level_info.stepfather_dummy_info.size(); i++)
			{
				ServerDummyCreateWithStepFatherInfo &info = level_info.stepfather_dummy_info[i];
				
				snprintf(info.create_info.key, sizeof(info.create_info.key), "%s_0/rv1", info.create_info.tower_key);
				
				if (info.sub_type == TD_RESBUILDING_SUBTYPE)
					max_reshp += info.create_info.max_hp;
			}
			cur_reshp = max_reshp;
			if (max_reshp == 0) max_reshp = 1;
			
			switch (game_type)
			{
			case kBossMode:
			case kZombieMode:
				{
					Supply gold_supply;

					gold_supply.type = kSupplyGoldWithForce;
					for (int i = 0; i < GOLD_MAX_COUNT; i++)
					{
						SupplyObject* supply_tmp = supply_list.Allocate();
						supply_tmp->supply = gold_supply;
					}
				}

			default:
				{
					Supply supply_drop;

					supply_drop.type = kSupplyDropItem;
					for (int i = 0; i < DROPITEM_MAX_COUNT; i++)
					{
						SupplyObject* supply_tmp = supply_list.Allocate();
						supply_tmp->supply = supply_drop;
					}
				}
				break;
			}
			

			state = GS_Initialized;

			log_write(LOG_DEBUG4, "GAME: connected success.");

			loading = true;

			switch (game_type)
			{
			case kHoldPoint:
				{
					team_timer[0] = team_timer[1] = config.round_time_holdpoint;
					team_holdpoint_num[0] = team_holdpoint_num[1] = 0;

					cur_holdpoint = 0;
					cur_holdpoint_diffnum = 0;

					hold_points.clear();
					hold_points.reserve(level_info.hold_point.size());

					HoldPointInfo hold_point;
					for (std::vector<AABBPointInfo>::const_iterator point = level_info.hold_point.begin(); point < level_info.hold_point.end(); point++)
					{
						hold_point.owner_team = InvalidTeam;
						hold_point.snatch_team = InvalidTeam;
						hold_point.snatching_timer = 0;

						hold_point.aabb.SetInvalid();
						hold_point.aabb.UnionWithVector(point->transform.position + point->dimension * Vector3(1, 0, 1));
						hold_point.aabb.UnionWithVector(point->transform.position + point->dimension * Vector3(1, 0, -1));
						hold_point.aabb.UnionWithVector(point->transform.position + point->dimension * Vector3(-1, 0, 1));
						hold_point.aabb.UnionWithVector(point->transform.position + point->dimension * Vector3(-1, 0, -1));
						hold_point.aabb.UnionWithVector(point->transform.position + point->dimension * Vector3(1, 1, 1));
						hold_point.aabb.UnionWithVector(point->transform.position + point->dimension * Vector3(1, 1, -1));
						hold_point.aabb.UnionWithVector(point->transform.position + point->dimension * Vector3(-1, 1, 1));
						hold_point.aabb.UnionWithVector(point->transform.position + point->dimension * Vector3(-1, 1, -1));

						hold_points.push_back(hold_point);
					}
				}
				break;
			case kBombMode:
				{
					bomb_aabb_area.clear();
					for (std::vector<AABBPointInfo>::const_iterator point = level_info.hold_point.begin(); point < level_info.hold_point.end(); point++)
					{
						AxisAlignedBox aabb;

						aabb.SetInvalid();
						aabb.UnionWithVector(point->transform.position + point->dimension * Vector3(1, -1, 1));
						aabb.UnionWithVector(point->transform.position + point->dimension * Vector3(1, -1, -1));
						aabb.UnionWithVector(point->transform.position + point->dimension * Vector3(-1, -1, 1));
						aabb.UnionWithVector(point->transform.position + point->dimension * Vector3(-1, -1, -1));
						aabb.UnionWithVector(point->transform.position + point->dimension * Vector3(1, 1, 1));
						aabb.UnionWithVector(point->transform.position + point->dimension * Vector3(1, 1, -1));
						aabb.UnionWithVector(point->transform.position + point->dimension * Vector3(-1, 1, 1));
						aabb.UnionWithVector(point->transform.position + point->dimension * Vector3(-1, 1, -1));

						bomb_aabb_area.push_back(aabb);
					}
				}
				break;
			case kZombieMode:
				{
					int length = level_info.hold_point.size();
					if(length > 0)
					{
						zombie_target_area.clear();
						for (std::vector<AABBPointInfo>::const_iterator point = level_info.hold_point.begin(); point < level_info.hold_point.end(); point++)
						{
							AxisAlignedBox aabb;

							aabb.SetInvalid();
							aabb.UnionWithVector(point->transform.position + point->dimension * Vector3(1, -1, 1));
							aabb.UnionWithVector(point->transform.position + point->dimension * Vector3(1, -1, -1));
							aabb.UnionWithVector(point->transform.position + point->dimension * Vector3(-1, -1, 1));
							aabb.UnionWithVector(point->transform.position + point->dimension * Vector3(-1, -1, -1));
							aabb.UnionWithVector(point->transform.position + point->dimension * Vector3(1, 1, 1));
							aabb.UnionWithVector(point->transform.position + point->dimension * Vector3(1, 1, -1));
							aabb.UnionWithVector(point->transform.position + point->dimension * Vector3(-1, 1, 1));
							aabb.UnionWithVector(point->transform.position + point->dimension * Vector3(-1, 1, -1));

							zombie_target_area.push_back(aabb);
							log_write(LOG_INFO, "zombie_aabb : x:%f y:%f z:%f",point->transform.position.x, point->transform.position.y, point->transform.position.z);
							
						}
					}
					else
					{
						log_write(LOG_ERROR, "level target point is empty level id : %d",level_info.level_id);
						RequestDisconnect();
					}
				}
				break;

			case kNovice:
			case kPushVehicle:
				{
					team_timer[0] = team_timer[1] = 0;

					current_vehicle_id[0] = current_vehicle_id[1] = 0;

					Vector3 delta_pos_normalize[2];

					for (uint i = 0; i < 2; i++)
					{
						vehicle_current_info[i] = level_info.push_vehicle_point[i][0];

						delta_pos_normalize[i] = level_info.push_vehicle_point[i][current_vehicle_id[i] + 1].position - level_info.push_vehicle_point[i][current_vehicle_id[i]].position;

						float length_delta = Length(delta_pos_normalize[i]);

						delta_pos_normalize[i] = Vector3(delta_pos_normalize[i].x/length_delta, delta_pos_normalize[i].y/length_delta, delta_pos_normalize[i].z/length_delta);

						vehicle_current_info[i].dir = delta_pos_normalize[i];
					}
				}
				break;
			}
		}
		break;

	case GS_Initialized:
		{
			byte msg;
			ReadByte(msg);

			switch (msg)
			{
			case RM_UpdateKeywords:
				RequestUpdateKeywords();
				break;
			
			case RM_RequestClientEnter:
				RequestClientEnter();
				break;

			case RM_UpdateCharacterData:
				OnUpdateCharacterData();
				break;

			case RM_RequestClientLeave:
				OnRequestClientLeave();
				break;

			case RM_ResponseBinaryRPC:
				ResponseBinaryRPC();
				break;

			case RM_ForwardClientMessage:
				OnForwardClientMessage();
				break;

			case RM_ResponseDisconnect:
				OnResponseDisconnect();
				break;

			case RM_UpdateCharacterPing:
				OnUpdateCharacterPing();
				break;

			default:
				break;
			}
		}
		break;

	default:
		break;
	}
}

// notify client leave
void Game::NotifyClientLeave(Client & client)
{
	if (IsConnected())
	{
		BeginWrite();
		WriteByte(GM_NotifyClientLeave);
		WriteInt(client.uid_in_room);
		WriteInt(client.uid_in_channel);
		EndWrite();
	}
}

void Game::NotifyZombieModeStepTwo()
{
	zombie_target_area_id = rand()%zombie_target_area.size();
	AxisAlignedBox aabb = zombie_target_area[zombie_target_area_id];

	// broadcast player shoot
	for (uint i = 0; i < max_client_count; i++)
	{
		Client* c = server.clients + i;

		if (c->IsReady())
		{
			c->BeginWrite();
			c->WriteByte(SM_Zombie_Mode_Step_Two);
			c->WriteInt(zombie_target_area_id);
			c->WriteVector3(aabb.Min);
			c->WriteVector3(aabb.Max);
			c->EndWrite();
		}
	}
}

void Game::NotifyZombieModeGameStart()
{
	// broadcast player shoot
	for (uint i = 0; i < max_client_count; i++)
	{
		Client* c = server.clients + i;

		if (c->IsReady())
		{
			c->BeginWrite();
			c->WriteByte(SM_Zombie_Mode_StartGame);
			c->EndWrite();
		}
	}
}


void Game::OnDisconnected()
{
	bomb_aabb_area.clear();

	if (clients)
	{
		for (int i = 0; i < max_client_count; ++ i)
		{
			if (clients[i].IsConnected())
				clients[i].LeaveGame(stage_cleared);
		}
	}

	playing = false;

	Event::Quit();

	log_write(LOG_DEBUG1, "game server close");
}

// get player
Client* Game::GetClient(byte uid)
{
	union
	{
		byte uid;
		struct
		{
			byte id : 6;
			byte magic : 2;
		};
	} dt;
	dt.uid = uid;

	if (dt.id < max_client_count)
	{
		if (clients[dt.id].uid == uid)
			return clients + dt.id;
	}
	else if (dt.id >= max_client_count && dt.id < max_client_count+max_botclient_count)
	{
		if (bot_clients[dt.id - max_client_count].uid == uid)
			return bot_clients + dt.id - max_client_count;
	}

	return NULL;
}

// get client by id
Client* Game::GetClientById(uint id)
{
	if (id > 0 && id < max_client_count + 1)
	{
		return clients + id - 1;
	}
	return NULL;
}

// get client by pid
Client* Game::GetClientByPId(uint pid)
{
	if (pid == 0)
		return NULL;
	
	for (int i = 0; i < max_client_count; i ++)
	{
		Client * client = clients + i;
		if (client->IsConnected() && client->GetCharacterInfo().character_id == pid)
		{
			return client;
		}
	}

	return NULL;
}

// create bot
Client* Game::CreateBot(CharacterInfo &charinfo)
{
	for (int i = 0; i < max_botclient_count; i ++)
	{
		Client * bot = bot_clients + i;
		if (bot->IsConnected() == false)
		{
			bot->OnBotCreate(charinfo);
			return bot;
		}
	}

	return NULL;
}

// get bot by index
Client* Game::GetBotByIndex(int index)
{
	if (index < max_botclient_count)
	{
		return bot_clients+index;
	}
	return NULL;
}

uint Game::GetAliveBossCid(int index)
{
	if (index >= 0 && index < 4 && now_Alive_boss[index].size() > 0)
	{
		return (uint)now_Alive_boss[index][0].career_id;
	}
	return 0;
}

Client* Game::GetBotByCid(int cid)
{
	for(int i = 0; i < max_botclient_count; ++i)
	{
		if (bot_clients[i].GetBosspveCareerid() == cid)
		{
			return bot_clients+i;
		}
	}
	return NULL;
}

void Game::WriteStageClear(byte winner)
{
	int game_end = time(NULL);
	int client_end = game_end;
	char sumHurt[1024] = "";

	WriteInt(room_id);
	WriteInt(server_id);
	WriteInt(channel_id);
	WriteInt(host_character_id);
	WriteByte(game_type);
	WriteInt(game_start_time);
	WriteInt(game_end);
	WriteInt(level_info.level_id);
	byte group_match = room_option.group_match;
	WriteByte(group_match);
	if (group_match)
	{
		WriteInt(group_id_t1);
		WriteInt(group_id_t2);
	}
	WriteByte(winner);
	
	switch (game_type)
	{
	case kTeam:
	case kKnifeMode:
	case kItemMode:
		WriteInt(team_kills[0]);
		WriteInt(team_kills[1]);
		break;

	case kHoldPoint:
	case kNovice:
	case kPushVehicle:
	case kTeamDeathMatch:
	case kBossMode:
	case kBossMode2:
	case kBombMode:
	case kStreetBoyMode:
	case kZombieMode:
	case kCommonZombieMode:
	case kBossPVE:
	case KMoonMode:
	case kAdvenceMode:
	case kSurvivalMode:
		WriteInt(team_rounds[0]);
		WriteInt(team_rounds[1]);
		break;
		
	case kTDMode:
		{
			float s = (float)cur_reshp/(float)max_reshp;
			
			WriteString(room_option.rand_key);
			WriteFloat(s);
		}
		break;

	default:
		WriteInt(0);
		WriteInt(0);
		break;
	}

	int player_count = 0;
	Client * mvp = NULL;

	for (int i = 0; i < max_client_count; i ++)
	{
		Client * client = clients + i;

		if (!client->IsConnected())
			continue;

		player_count++;

		client->data.ScoreDataDied(client->GetCurCharinfo(), (int)client->life_time, false);

		if (mvp == NULL)
			mvp = client;

		if (client->data.GetCalcScore(game_type, client_end - client->start_time) >
			mvp->data.GetCalcScore(game_type, client_end - mvp->start_time))
			mvp = client;
	}

	int game_time = game_end - game_start_time;
	
	if (mvp && player_count >=4 && game_time >= 2 * 60) 
		WriteInt(mvp->GetCharacterInfo().character_id);
	else
		WriteInt(0);

	//kill
	WriteInt(first_kill);
	WriteInt(first_died);

	// room item
	WriteByte(level_info.room_item.type);
	if (level_info.room_item.type != 0)
	{
		WriteInt(level_info.room_item.value);
		WriteString(level_info.room_item.comment.c_str());
		WriteString(level_info.room_item.comment2.c_str());

		log_write(LOG_DEBUG4, "write room_item");
	}

	WriteInt(player_count);
	for (int i = 0; i < max_client_count; i ++)
	{
		Client * client = clients + i;

		if (!client->IsConnected())
			continue;

		WriteInt(client->GetCharacterInfo().character_id);
		WriteString(client->GetCharacterInfo().character_name);
		WriteByte(client->team);

		if (game_type == kTDMode)
		{
			sprintf(sumHurt, "%s%s|%d|%d\r\n",sumHurt,client->GetCharacterInfo().character_name,client->GetCharacterInfo().character_id,client->alldamage);
			client->alldamage = 0;
		}

		WriteInt(client->fcm_online_minutes);
		WriteInt(client->fcm_offline_minutes);
		byte kick_count = (byte)(client->kick_client_count);
		WriteByte(kick_count);
		WriteInt(client->fu_huo_bi);
		
		log_write(LOG_DEBUG2, "write stage_clear  name: %s ,fcm : on %d off %d" , client->GetCharacterInfo().character_name, client->fcm_online_minutes, client->fcm_offline_minutes );

		if (client->start_time < game_start_time)
			client->start_time = game_start_time;

		WriteInt(client->start_time);
		WriteInt(client_end);

		WriteInt(client->data.GetCalcScore(game_type, client_end - client->start_time));

		WriteShort(client->num_kill);
		WriteShort(client->num_die);
		WriteShort(client->control_num);
		WriteShort(client->revenge_num);
		WriteShort(client->assist_num);
		WriteShort(client->knife_kill);

		WriteInt(client->data.GetScorePlayerData(kScoreHeadShot).max_num);
		WriteInt(client->data.GetScorePlayerData(kScoreHeadShot).career_id);

		WriteInt(client->data.GetScorePlayerData(kScoreContinuousKill).max_num);
		WriteInt(client->data.GetScorePlayerData(kScoreContinuousKill).career_id);

		if(client->data.GetScorePlayerData(kScoreTreatment).max_num > 1)
			WriteInt(client->data.GetScorePlayerData(kScoreTreatment).max_num);
		else
			WriteInt(0);
		WriteInt(client->data.GetScorePlayerData(kScoreTreatment).career_id);

		WriteInt(client->data.GetScorePlayerData(kScoreDamage).max_num);
		WriteInt(client->data.GetScorePlayerData(kScoreDamage).career_id);

		WriteInt(client->data.GetScorePlayerData(kScoreLiveTime).max_num);
		WriteInt(client->data.GetScorePlayerData(kScoreLiveTime).career_id);
		WriteShort(client->num_ammorecover);
		WriteShort(client->num_healthrecover);
		// write careerdatas
		const std::map<uint, ScoreData::ScoreDataCareer>& career_datas = client->data.GetScoreCareerDatas();
		WriteInt((int)career_datas.size());
		for (std::map<uint, ScoreData::ScoreDataCareer>::const_iterator itr = career_datas.begin();
			itr != career_datas.end(); itr++)
		{
			WriteInt(itr->first);
			WriteShort(itr->second.kill);
			WriteShort(itr->second.dead);
			WriteShort(itr->second.control_num);
			WriteShort(itr->second.revenge_num);
			WriteShort(itr->second.assist_num);
			WriteShort(itr->second.grenade_kill);
			WriteShort(itr->second.knife_kill);
			WriteShort(itr->second.used_account);
			WriteShort(itr->second.shoothead);
			WriteShort(itr->second.maxdamage);
			WriteShort(itr->second.boostkill);
			WriteShort(itr->second.sustainkill);
			WriteInt(itr->second.treatment);
		}
		// write target_datas
		const std::map<uint, uint>& target_datas = client->data.GetTargetData();
		WriteInt((int)target_datas.size());
		for (std::map<uint, uint>::const_iterator itr = target_datas.begin();
			itr != target_datas.end(); itr++)
		{
			WriteInt(itr->first);
			WriteInt(itr->second);
		}
		
		WriteInt((int)client->pickup_dropitems.size());
		for (std::map<int, int>::const_iterator itr = client->pickup_dropitems.begin();
			itr != client->pickup_dropitems.end(); itr++)
		{
			WriteInt(itr->first);
			WriteInt(itr->second);
		}
		
		WriteInt(now_phase);

		if (kTDMode == game_type)
		{
			ushort itemNum = client->GetCharacterInfo().bag.size();
			WriteShort(itemNum);
			ItemBag::const_iterator it = client->GetCharacterInfo().bag.begin();
			for (; it != client->GetCharacterInfo().bag.end(); ++it)
			{
				WriteInt(it->second.sid);
				WriteShort((ushort)(it->second.init_count - it->second.count));
			}
		}
		if (kAdvenceMode == game_type)
		{
			WriteInt(client->CCoinCount);
			WriteInt(client->MedalCount);
			WriteInt(client->WrenchCount);
			WriteInt(client->BoxACount);
			WriteInt(client->BoxBCount);
			WriteInt(client->BoxCCount);
			WriteInt(client->BoxDCount);
		}

		//debug
		client->data.DebugPrint(client->GetCharacterInfo().character_name);
	}

	WriteString(sumHurt);
}

// request stage quit
void Game::RequestStageQuit(Client & client)
{
	BeginWrite();
	WriteBinaryRPC("stage_quit");
	WriteByte(RPC_StageQuit);
	WriteByte(client.uid);
	EndRPCUserdata();
	int nServetType = (int)cServerType;
	WriteInt(nServetType);
	WriteInt(client.GetCharacterInfo().character_id);
	WriteStageClear(client.team == 0 ? 1 : 0);
	EndWrite();
}

// request end novice
void Game::RequestEndNovice(Client & client, bool is_success)
{
	log_write(LOG_DEBUG1, "%s, %s, result : %d", __FILE__, __FUNCTION__, is_success);
	int game_end = time(NULL);

	BeginWrite();
	WriteBinaryRPC("end_new");
	EndRPCUserdata();
	WriteInt(client.GetCharacterInfo().character_id);
	WriteInt(game_start_time);
	WriteInt(game_end);
	WriteInt(client.GetCurCharinfo().career_id);
	WriteInt(is_success ? 1 : 0);
	EndWrite();
}

// response stage quit
void Game::ResponseStageQuit()
{
	byte client_uid;
	ReadByte(client_uid);

	Client * client = GetClient(client_uid);

	if (client && client->IsConnected())
	{
		client->LeaveGame(false);
	}

}

// request stage clear
void Game::RequestStageClear(byte winner)
{
	//  [11/15/2013 chenjun!!!!!!!!!]
	if (stage_cleared)
		return;

	BeginWrite();
	WriteBinaryRPC("stage_clear");
	WriteByte(RPC_StageClear);
	EndRPCUserdata();
	int nServerType = (int)cServerType;
	WriteInt(nServerType);
	WriteStageClear(winner);
	EndWrite();
	stage_cleared = false;
	log_write(LOG_DEBUG1, "RequestStageClear");
	//DEBUGLOG_WRITE("RequestStageClear(), now_time : %d", time(NULL));
}

// response stage clear
void Game::ResponseStageClear()
{
	stage_cleared = true;
	
	log_write(LOG_DEBUG1, "ResponseStageClear()");
	//DEBUGLOG_WRITE("ResponseStageClear(), now_time : %d", time(NULL));
}


// request save map
void Game::RequestSaveMap(MapInfo & oMapInfo)
{
	BeginWrite();
	WriteBinaryRPC("save_map");
	WriteByte(RPC_SaveMap);
	EndRPCUserdata();
	//map info
	WriteInt(level_info.level_id);
	WriteInt(oMapInfo.character_id);
	const int iCnt =(int)oMapInfo.m_aItemInfo.size();
	WriteInt(iCnt);
	for(int i=0; i<iCnt; ++i)
	{
		MapItemInfo& oItemInfo =oMapInfo.m_aItemInfo[i];
		
		//WriteInt(oItemInfo.DummyObjectType);
		WriteInt(oItemInfo.SystemId);
		//WriteString(oItemInfo.ResKey);
		WriteFloatArray(oItemInfo.Position, 3);
		WriteFloatArray(oItemInfo.Rotation, 4);
	}

	EndWrite();
}
void Game::ResponseSaveMap()
{
	DEBUGLOG_WRITE("ResponseSaveMap(), now_time : %d", time(NULL));
}


// update kill info
void Game::UpdateKillInfo()
{
	if (kill_infos.empty())
		return;

	BeginWrite();
	WriteBinaryRPC("kill_info");
	WriteByte(RPC_KillInfo);
	EndRPCUserdata();

	static const int max_count = (BinaryStream::MAX_PKGSIZE - 1024)  / sizeof(KillInfo);
	int count = kill_infos.size();

	if (count > max_count)
	{
		count = max_count;

		log_write(LOG_WARNING, "Game::UpdateKillInfo() : count is overflow!!! count = %d, max_count = %d", count, max_count);
	}
	
	//bulid client kill info list
	std::map<uint, std::list<uint> > kill_info_map;
	for (int i = 0; i < count; i++)
	{
		KillInfo & info = kill_infos[i];
		
		std::map<uint, std::list<uint> >::iterator itr = kill_info_map.find(info.killerid);
		if (itr == kill_info_map.end())
		{
			std::list<uint> info_list;
			
			info_list.push_back(i);
			kill_info_map.insert(std::pair<uint, std::list<uint> >(info.killerid, info_list));
		}
		else
		{
			itr->second.push_back(i);
		}
	}
	
	WriteByte(server.level_info.level_id);
	WriteInt((uint)kill_info_map.size());
	for (std::map<uint, std::list<uint> >::const_iterator itr = kill_info_map.begin(); 
		itr != kill_info_map.end(); itr++)
	{
		WriteInt(itr->first);
		WriteInt((uint)itr->second.size());
		for (std::list<uint>::const_iterator itr2 = itr->second.begin();
			itr2 != itr->second.end(); itr2++)
		{
			KillInfo & info = kill_infos[*itr2];

			WriteVector3(info.position);

			//WriteByte(info.mapid);
			WriteInt(info.time);

			WriteInt(info.deadid);
			WriteByte(info.deadcareerid);

			//WriteInt(info.killerid);
			WriteByte(info.killercareerid);
			WriteInt(info.killerweaponid);
		}
	}
	EndWrite();

	KillInfo::DebugPrint(kill_infos);

	kill_infos.clear();

	log_write(LOG_DEBUG3, "Game::UpdateKillInfo() end");
}

// write binary rpc
void Game::WriteBinaryRPC(const char * url)
{
	WriteByte(GM_RequestBinaryRPC);
	WriteString(url);
	WriteInt(0);
	rpc_userdata_position = write_position;
}

// end RPC userdata
void Game::EndRPCUserdata()
{
	if (rpc_userdata_position)
	{
		int size = write_position - rpc_userdata_position;
		memcpy(rpc_userdata_position - 4, &size, sizeof(int));
		rpc_userdata_position = NULL;
	}
}

// on response rpc
void Game::ResponseBinaryRPC()
{
	byte msg;
	uint result;
	ReadInt(result);
	ReadByte(msg);

	log_write(LOG_DEBUG1, "%s, %s, msg : %d", __FILE__, __FUNCTION__, msg);

	try
	{
		switch (msg)
		{
		case RPC_StageClear:ResponseStageClear(); break;
		case RPC_StageQuit:	ResponseStageQuit(); break;
		case RPC_KillInfo: break;
		case RPC_SaveMap:	ResponseSaveMap(); break;
		}
	}
	catch (...)
	{
		log_write(LOG_ERROR, "rpc parse error.");
		throw;
	}
}

// forward client message
void Game::ForwardClientMessage(Client & client)
{
	if (client.IsConnected())
	{
		uint size = client.send_offset;
		uint left = send_buffer_size - send_offset;
		const uint packet_size = BinaryStream::HEAD_SIZE + 1 + 4;

		uint size_need = size + packet_size;

		if (size_need >= BinaryStream::MAX_PKGSIZE)
		{
			client.send_offset = 0;

			log_write(LOG_DEBUG1, "ForwardClientMessage() : client's send_offset too big");
		}
		else if (size > 0)
		{
			if (size_need >= left && (send_buffer_size * 2 < BinaryStream::MAX_SEND_BUFFER_SIZE))
			{
				SendBufferResize(2 * send_buffer_size);

				left = send_buffer_size - send_offset;

				log_write(LOG_DEBUG1, "ForwardClientMessage() : resize sendbuffer[%d]", send_buffer_size);
			}

			if (size_need < left)
			{
				BeginWrite();
				WriteByte(GM_ForwardClientMessage);
				WriteInt(client.uid_in_channel);
				Write(client.send_buffer, size);
				EndWrite();
				//log_write(LOG_INFO, "ForwardClientMessage() : size[%d]", size);
				client.send_offset = 0;
			}
		}
	}
}

// forword all message
void Game::ForwordAllMessage()
{
	// sync player data and forward messages
	for (Client * client = clients; client < clients + max_client_count; client++)
	{
		if (client->IsConnected())
		{
			ForwardClientMessage(*client);
		}
	}
}

// broadcast clients
void Game::BroadcastClients(ClientNotifyHandler handler, void *userdata, Client *client_skip)
{
	for (Client * client = clients; client < clients + max_client_count; client++)
	{
		if (client && client!= client_skip)
		{
			handler(*client, userdata);
		}
	}
}

float Game::GetCarVelocity(int player_count)
{
	switch (player_count)
	{
	case 0:
		{
			return 2.f;
		}
		break;
	case 1:
		{
			return 0.5f;
		}
		break;
	case 2:
		{
			return 0.75f;
		}
		break;
	default:
		return 1.f;
		break;
	}
	return config.push_vehicle_velocity;
}

void Game::OnPushVehicleUpdate(float delta)
{
	if(!round_playing)
		return;

	vehicle_update_timer +=delta;

	bool isDirty = false;
	Vector3 delta_pos_normalize[2];
	int		sliding[2];


	for (uint i = 0; i < 2; i++)
	{
		int team_member_count = 0;
		vehicle_current_info[i].sliding = 0;
		vehicle_current_info[i].player_count = 0;
		//dispatch message
		vehicle_aabb[i].SetInvalid();
		Vector3 vecdown = vehicle_current_info[i].position;
		vecdown.y -= 2.f;
		vehicle_aabb[i].UnionWithVector(vecdown + level_info.vehicle_dim * Vector3(1, 0, 1));
		vehicle_aabb[i].UnionWithVector(vecdown + level_info.vehicle_dim * Vector3(1, 0, -1));
		vehicle_aabb[i].UnionWithVector(vecdown + level_info.vehicle_dim * Vector3(-1, 0, 1));
		vehicle_aabb[i].UnionWithVector(vecdown + level_info.vehicle_dim * Vector3(-1, 0, -1));
		vehicle_aabb[i].UnionWithVector(vecdown + level_info.vehicle_dim * Vector3(1, 1, 1));
		vehicle_aabb[i].UnionWithVector(vecdown + level_info.vehicle_dim * Vector3(1, 1, -1));
		vehicle_aabb[i].UnionWithVector(vecdown + level_info.vehicle_dim * Vector3(-1, 1, 1));
		vehicle_aabb[i].UnionWithVector(vecdown + level_info.vehicle_dim * Vector3(-1, 1, -1));

		for (int j = 0; j < max_client_count; ++ j)
		{
			if (clients[j].IsAlive() && clients[j].team == i && vehicle_aabb[i].IsPointInside(clients[j].position))
			{
				//Supply
				clients[j].Recover((int)(level_info.vehicle_recover/*  * vehicle_timer_interval */), kRecoverVehicle);
				clients[j].AmmoRecover(kSupplyVehicle, 0);

				if (server.cServerType == (byte)SvrType_Match)
				{
				}
				else
				{
					clients[j].data.ScoreDataAdd(config.vehicle_score_push * delta);
				}
			}
		}

		for (int j = 0; j < max_client_count; ++ j)
		{
			if (clients[j].IsAlive() && clients[j].team == i && vehicle_aabb[i].IsPointInside(clients[j].position))
			{
				team_member_count ++;
			}
		}

		if(team_member_count > 0)
		{
			isDirty = true;

			if(IsAtPushVehicleGoal(i))
			{
				for (int j = 0; j < max_client_count; ++ j)
				{
					if (clients[j].IsReady() && clients[j].team == i)
					{
						if (server.cServerType == (byte)SvrType_Match)
						{
						}
						else
						{
							clients[j].data.ScoreDataAdd(config.vehicle_score);
						}
					}
				}

				//finish
				if (game_type == kPushVehicle)
				{
					OnRoundEnd(false, i);
				}
				else
				{
					novice_isupdate = false;
					for (int j = 0; j < max_client_count; ++ j)
					{
						if (clients[j].IsReady())
						{
							clients[j].ParseNoviceOperation(20);
						}
					}
				}
			}

			//push_car
			Vector3 current_pos = vehicle_current_info[i].position;
			float speed_delta = GetCarVelocity(team_member_count) * delta;

			vehicle_current_info[i].player_count = team_member_count;

			int next_id = current_vehicle_id[i] + 1;

			delta_pos_normalize[i] = level_info.push_vehicle_point[i][next_id].position - current_pos;

			float length_delta = 0.f;

			Normalize(delta_pos_normalize[i],delta_pos_normalize[i],length_delta);

			Vector3 delta_pos = delta_pos_normalize[i] * Vector3(speed_delta, speed_delta, speed_delta);
			//get next
			if(LengthSqr(delta_pos) > length_delta * length_delta)
			{
				// goto next point
				vehicle_current_info[i] = GetNextPushVehicleTransform(i);

				int next_id = current_vehicle_id[i] + 1;

				delta_pos_normalize[i] = level_info.push_vehicle_point[i][next_id].position - current_pos;

				float length_delta = 0.f;

				Normalize(delta_pos_normalize[i],delta_pos_normalize[i],length_delta);

				vehicle_current_info[i].dir = delta_pos_normalize[i];
			}
			else
			{
				vehicle_current_info[i].position = vehicle_current_info[i].position + delta_pos;
				vehicle_current_info[i].total_length += Length(delta_pos);
			}
		}
		else
		{
			PushVehicleInfo& info = level_info.push_vehicle_point[i][current_vehicle_id[i]];

			vehicle_current_info[i].sliding = info.sliding;

			if(info.sliding == 1)
			{
				isDirty = true;

				Vector3 current_pos = vehicle_current_info[i].position;
				float speed_delta = GetCarVelocity(0) * delta;

				int prev_id = current_vehicle_id[i];

				if(prev_id < 0)
					continue;

				delta_pos_normalize[i] = current_pos - level_info.push_vehicle_point[i][prev_id].position ;

				float length_delta = 0.f;

				log_write(LOG_DEBUG4, "delta_pos_normalize %f %f %f", delta_pos_normalize[i].x, delta_pos_normalize[i].y, delta_pos_normalize[i].z);
				float normalizesqr = LengthSqr(delta_pos_normalize[i]);
				if(normalizesqr < 0.0001f)
					continue;


				Normalize(delta_pos_normalize[i],delta_pos_normalize[i],length_delta);

				Vector3 delta_pos = delta_pos_normalize[i] * Vector3(speed_delta, speed_delta, speed_delta);
				
				log_write(LOG_DEBUG3, "delta pos %f %f %f", delta_pos_normalize[i].x, delta_pos_normalize[i].y, delta_pos_normalize[i].z);

				//get next
				if(LengthSqr(delta_pos) < length_delta * length_delta)
				{
					vehicle_current_info[i].position = vehicle_current_info[i].position - delta_pos;
					vehicle_current_info[i].total_length -= Length(delta_pos);
				}
				else
				{
					vehicle_current_info[i].position = level_info.push_vehicle_point[i][prev_id].position ;
					vehicle_current_info[i].total_length = level_info.push_vehicle_point[i][prev_id].total_length;
				}

			}
		}
	}

	// dispatch
	if(isDirty || vehicle_update_timer > 5.f)
	{
		vehicle_update_timer = 0.f;

		for (int j = 0; j < max_client_count; ++ j)
		{
			if (clients[j].IsReady())
			{
				clients[j].UpdateVehicleInfo();
			}
		}
	}

}
PushVehicleInfo Game::GetNextPushVehicleTransform(int team)
{
	current_vehicle_id[team]++;

	int pos = current_vehicle_id[team];
	return level_info.push_vehicle_point[team][pos];
}

PushVehicleInfo Game::GetPrevPushVehicleTransform(int team)
{
	current_vehicle_id[team]--;

	int pos = current_vehicle_id[team];
	return level_info.push_vehicle_point[team][pos];
}

bool Game::IsAtPushVehicleGoal(int team)
{
	if(level_info.push_vehicle_point[team].size() - current_vehicle_id[team] - 1 == 0)
	{
		return true;
	}
	return false;
}

// on forward client message
void Game::OnForwardClientMessage()
{
	uint inroom_id;

	ReadInt(inroom_id);

	Client* client = GetClientById(inroom_id);

	if (client && client->IsConnected())
	{
		try
		{
			uint size = read_end - read_position;

			if (size + BinaryStream::HEAD_SIZE <= client->recv_buffer_size - client->recv_offset)
			{
				ushort magic_num = BinaryStream::MAGICNUM;
				ushort pkg_size = size + BinaryStream::HEAD_SIZE;

				memcpy(client->recv_buffer + client->recv_offset, &magic_num, sizeof(magic_num));
				memcpy(client->recv_buffer + client->recv_offset + BinaryStream::MAGICNUM_SIZE, &pkg_size, sizeof(pkg_size));
				memcpy(client->recv_buffer + client->recv_offset + BinaryStream::HEAD_SIZE, read_position, size);

				client->recv_offset += pkg_size;
				client->OnParseMessage();
			}
			else
			{
				log_write(LOG_DEBUG1, "catch catch catch catch");
				client->Disconnect();
			}
		}
		catch (EError &exp)
		{
			log_write(LOG_ERROR, "forward client message error[%d].", (int)exp);
			client->Disconnect();
		}
		catch (...)
		{
			log_write(LOG_ERROR, "forward client message error.");
			client->Disconnect();
		}
	}
}

void Game::OnKickPerson(int person_id, int room_id, float time)
{
	BeginWrite();
	WriteByte(GM_KickPerson);
	WriteInt(room_id);
	WriteInt(person_id);
	WriteFloat(time);
	EndWrite();
}

// request for disconnect
void Game::RequestDisconnect()
{
	log_write(LOG_DEBUG1, "Game::RequestDisconnect().");
	
	if (server.cServerType == (byte)SvrType_Match || server.cServerType == (byte)SvrType_MatchFighting)
	{
		for (Client * client = clients; client < clients + max_client_count; client++)
		{
			if (client->connected)
			{
				log_write(LOG_DEBUG1, "%s, %s, matching_level_check name : %s, match level : %d, team : %d", __FILE__, __FUNCTION__, client->GetCharacterInfo().character_name, client->GetCharacterInfo().nMatchingLevel, client->team);
			}
		}
	}
	
	log_write(LOG_DEBUG1, "%s, %s, game end matching_level_check", __FILE__, __FUNCTION__);
	
	ForwordAllMessage();

	BeginWrite();
	WriteByte(GM_RequestDisconnect);
	EndWrite();

	playing = false;
	is_request_disconnect = true;
}

// on response disconnect
void Game::OnResponseDisconnect()
{
	log_write(LOG_DEBUG3, "Game::OnResponseDisconnect().");
	
	is_request_disconnect = false;

	Disconnect();
}

void Game::OnUpdateCharacterPing()
{
	for (Client * client = clients; client < clients + max_client_count; client++)
	{
		uint ping;
		ReadInt(ping);

		client->UpdatePlayerPing(ping);
	}
}

void Game::OnUpdateCharacterData()
{
	uint client_uid;
	uint uid_in_room;

	ReadInt(client_uid);
	ReadInt(uid_in_room);

	Client * client = GetClientById(uid_in_room);

	if (client && client->uid_in_channel == client_uid)
	{
		uint character_id;
		char character_name[256];
		char character_group[256];

		ReadInt(character_id);
		ReadString(character_name, sizeof(character_name));
		ReadString(character_group, sizeof(character_group));
		ReadInt(client->level);
		ReadInt(client->fcm_online_minutes);
		ReadInt(client->fcm_offline_minutes);

		log_write(LOG_DEBUG1, "update character data: name=%s fcm: %d", character_name, client->fcm_online_minutes);
	}
}

// on request client leave
void Game::OnRequestClientLeave()
{
	uint client_uid;
	uint uid_in_room;

	ReadInt(client_uid);
	ReadInt(uid_in_room);

	Client * client = GetClientById(uid_in_room);

	if (client && client->uid_in_channel == client_uid)
	{
		if (client->IsConnected())
		{
			client->Disconnect();
		}
	}
}


// on kick client start
void Game::OnKickClientStart(byte start_uid, byte kicked_uid, byte kicked_reason)
{
	vote_timer = 0;
	vote_clients.clear();
	vote_clients.reserve(max_client_count);
	kick_sponsor_uid = start_uid;
	kick_client_uid = kicked_uid;

	for (uint i = 0; i < max_client_count; i++)
	{
		Client * c = server.clients + i;

		if (c->IsReady())
		{
			c->KickClientStart(kick_sponsor_uid, kick_client_uid, kicked_reason);

			if (c->uid != kick_sponsor_uid && c->uid != kick_client_uid)
			{
				vote_clients.push_back(c->uid);
				c->is_voted = false;
			}
		}
	}

	is_voting = true;
}

// on kick client end
void Game::OnKickClientEnd()
{
	int vote_client_count = 1;
	int vote_accept_count = 1;
	Client* c = GetClient(kick_sponsor_uid);
	if (!c)
	{
		vote_timer = 0;
		vote_clients.clear();
		is_voting = false;
		return;
	}

	if (!c->IsConnected())
	{
		vote_timer = 0;
		vote_clients.clear();
		is_voting = false;
		return;
	}

	for (std::vector<short>::const_iterator i = server.vote_clients.begin(); i < server.vote_clients.end(); i++)
	{
		byte client_uid = (*i) & 0x00FF;
		Client* client = GetClient(client_uid);
		if (client && client->IsReady() && client->is_voted)
			vote_client_count++;

		if ((*i) & 0x0100)
			vote_accept_count++;
	}

	//bool result = vote_accept_count * 2 > vote_client_count;
	bool result = vote_accept_count * 2 > server.vote_clients.size();

	for (uint i = 0; i < max_client_count; i++)
	{
		Client * c = server.clients + i;

		if (c->IsReady())
			c->KickClientEnd(kick_sponsor_uid, kick_client_uid, result);
	}

	if (result)
	{
		Client* client = GetClient(kick_client_uid);
		if (client)
		{
			RequestStageQuit(*client);
			log_write(LOG_DEBUG1,"client = %d  room_id = %d",client->GetCharacterInfo().character_id, room_id);
			OnKickPerson(client->GetCharacterInfo().character_id,room_id);
		}
	}

	vote_timer = 0;
	vote_clients.clear();
	is_voting = false;
}

int Game::Run()
{
	if (server.cServerType == SvrType_Match)
	{
		log_write(LOG_DEBUG1, "*****SvrType_Match Run socket : %d",channel_socket);
	}
	if (server.cServerType == SvrType_MatchFighting)
	{
		log_write(LOG_DEBUG1, "*****SvrType_MatchFighting GameStart socket : %d",channel_socket);
	}

	srand(time(NULL));

	// allocate clients
	clients = new Client[max_client_count];
	for (uint i = 0; i < max_client_count; i++)
	{
		clients[i].id = i;
		clients[i].magic = 1;
	}

	bot_clients = new Client[max_botclient_count];
	for (uint i = 0; i < max_botclient_count; i++)
	{
		bot_clients[i].id = max_client_count+i;
		bot_clients[i].magic = 1;
	}

	// allocate dropped gun
	if (!dropped_weapon.Initialize(max_dropped_gun_count))
	{
		log_write_sys(LOG_ERROR, "not enough memory!");
		return 1;
	}

	// allocate level supply
	if (!supply_list.Initialize(max_level_supply_count))
	{
		log_write_sys(LOG_ERROR, "not enough memory!");
		return 1;
	}

	if (!common_zombie_supply_list.Initialize(max_common_zombie_supply))
	{
		log_write_sys(LOG_ERROR, "not enough memory!");
		return 1;
	}
	
	if (!common_zombie_supply_list2.Initialize(max_common_zombie_supply))
	{
		log_write_sys(LOG_ERROR, "not enough memory!");
		return 1;
	}

	// allocate dropped supply
	if (!dropped_supply.Initialize(max_dropped_supply_count))
	{
		log_write_sys(LOG_ERROR, "not enough memory!");
		return 1;
	}

	// connect channel server
	Connect(channel_socket, sockaddr_in());

	// update game
	update_time = Event::GetTime();
	Event::AddTimer(&UpdateGame, this, 0);

	// update
	Event::Dispatch();

	// terminate
	OnClose();

	if (clients)
		delete[] clients;

	if (bot_clients)
		delete[] bot_clients;

	dropped_weapon.Terminate();
	supply_list.Terminate();
	dropped_supply.Terminate();
	common_zombie_supply_list.Terminate();
	common_zombie_supply_list2.Terminate();

	log_write(LOG_DEBUG1, "socket : %d, game server exit", channel_socket);

	if (server.cServerType == SvrType_Match)
	{
		log_write(LOG_DEBUG1, "*****SvrType_Match Return socket : %d",channel_socket);
	}
	if (server.cServerType == SvrType_MatchFighting)
	{
		log_write(LOG_DEBUG1, "*****SvrType_MatchFighting GameStart socket : %d",channel_socket);
	}

	return 0;
}

void Game::OnGameEndTimer(int team)
{
	game_end_timer = config.round_end_special_time_max;
	gold_time = 1.5f;
	game_end_team = team;
}

void Game::SetBossAliveNumber(int num)
{
	boss_mode_human_alive_number = num;
}
void Game::NotifyBossAliveChanged()
{
	for (uint i = 0; i < max_client_count; i++)
	{
		Client * c = server.clients + i;

		if (c->IsReady())
			c->NotifyBossModeAliveNumber(boss_mode_human_alive_number);
	}
}

int Game::GetBossAliveNumber()
{
	return boss_mode_human_alive_number;
}

void Game::AddDropGoldWithSpeed(const Vector3& to,const Vector3& from, int type, int value, byte owner_id,short team)
{
	// drop gold
	Vector3 dir = to - from;
	float length = Length(dir);

	if (length > 0.00001f)
	{
		dir = Vector3(dir.x/length, dir.y/length, dir.z/length);
	}
	else
	{
		dir = Vector3(0,0,-1);
	}

	for (uint i = 0; i < 5; i++)
	{
		
		for (SupplyObject* supply = supply_list.Begin(); supply < supply_list.End(); supply++)
		{
			if (supply && !supply->IsActive() && supply->supply.type >= kSupplyGoldWithForce)
			{
				supply->Create();
				supply->position = dir;
				supply->rotation = PI * 0.4f * i;
				supply->supply.type = type;
				supply->supply.value = value;
				for (int j = 0; j < max_client_count; ++j)
				{
					if (clients[j].IsReady())
						clients[j].AddSupplyObject(supply,owner_id,team);
				}
				break;
			}
		}
	}
}
bool Game::AddZombieDropGold(const Vector3& position)
{
	for (SupplyObject* supply = supply_list.Begin(); supply < supply_list.End(); supply++)
	{
		if (supply && !supply->IsActive() && supply->supply.type >= kSupplyGoldWithForce)
		{
			supply->Create();
			supply->supply.type = kSupplyZombieGold;
			supply->supply.value = 10;
			supply->position = position;
			supply->rotation = 0;
			for (int j = 0; j < max_client_count; ++j)
			{
				if (clients[j].IsReady())
					clients[j].AddSupplyObject(supply);
			}
			return true;
		}
	}
	return false;
}


void Game::CheckAllCure()
{
	for (uint i = 0; i < max_client_count; i++)
	{
		Client * c = server.clients + i;
		if (c->IsReady())
		{
			c->CheckCure();
		}
	}
}

void Game::ClearPlantState()
{
	bomb_planting = false;
	bomb_planted = false;
	bomb_plant_timer = -1.f;
	bomb_explode_timer = -1.f;
}

void Game::ClearDefusingState()
{
	bomb_defusing_uid = 0;
	bomb_defusing_timer = -1.f;
	bomb_defusing = false;
}

void Game::StartDefusing(byte uid, float t)
{
	bomb_defusing = true;
	bomb_defusing_uid = uid;
	bomb_defusing_timer = t;

}

bool Game::CheckIsInBombArea(const Vector3& pos)
{
	for (std::vector<AxisAlignedBox>::iterator itor = server.bomb_aabb_area.begin();itor != server.bomb_aabb_area.end(); itor++)
	{
		AxisAlignedBox& aabb = *itor;
		if(aabb.IsPointInside(pos))
		{
			return true;
		}
	}
	//temp test
	return false;
	//return false;
}

bool Game::CheckIsInZombieArea(const Vector3& pos)
{
	if(zombie_target_area.size() > zombie_target_area_id)
	{
		if(zombie_target_area[zombie_target_area_id].IsPointInside(pos))
		{
			return true;
		}
	}

	return false;
}

void Game::UpdateKingPosition()
{
	for (int t = 0; t < 2; ++ t)
	{
		for (int i = 0; i < max_client_count; ++ i)
		{
			if (clients[i].IsReady() && clients[i].IsAlive())
			{	
				if(clients[i].uid == street_king_info[t].current_street_king_uid)
				{
					street_king_info[t].street_king_last_position = clients[i].position; 
				}
			}
		}
	}

}

void Game::UpdateRoundStartTimer(float delta)
{
	int totaltime;
	if (game_type == kCommonZombieMode)
	{
		totaltime = COMMON_ZOMBIE_ROUND_START_TIME;
	}
	else if (game_type == kBossMode2)
	{
		totaltime = COMMON_BOSSMODE2_ROUND_START_TIME;
	}
	else
	{
		totaltime = ROUND_START_TIME;
	}

	special_person_start_timer += delta;

	int temp_count = special_person_start_time_count;

	special_person_start_time_count = (int)special_person_start_timer;

	if(special_person_start_time_count > temp_count)
	{
		int t = totaltime - special_person_start_time_count;
		if(t > -2)
		{
			for (int i = 0; i < max_client_count; ++ i)
			{
				if (clients[i].IsReady())
				{
					clients[i].GameStartTimerNotify(t);
				}
			}
		}
	}
}

void Game::UpdateZombieModeSaveDying(float delta)
{
	for (int i = 0; i < max_client_count; ++ i)
	{
		Client * c = clients + i;
		if (c->IsReady() && c->team == 0 && c->IsAlive() && c->zombie_mode_saving_dying_uid)
		{
			for (int j = 0; j < max_client_count; ++ j)
			{
				Client * dying_c = clients + j;
				if(dying_c->IsReady() && dying_c->team == 0 && dying_c->zombie_mode_saver_uid == c->uid)
				{
					c->zombie_mode_save_timer += delta;
					if(c->zombie_mode_save_timer > ZOMBIE_MODE_SAVE_DYING_TIME)
					{
						c->zombie_mode_save_timer = 0.f;
						c->zombie_mode_saving_dying_uid = 0;
						if (server.cServerType == (byte)SvrType_Match)
						{
						}
						else
						{
							c->data.ScoreDataAdd(config.save_dying_score, true);
						}

						dying_c->ZombieModeRebirth(dying_c->zombie_mode_saver_uid);
						
						dying_c->zombie_mode_saver_uid = 0;
						dying_c->zombie_mode_dying_state = false;
						//rebirth
						
					}
				}
			}
		}
	}

}

bool Game::ZombieSelect()
{
	int player_count = 0;
	std::vector<byte> select_vec;
	
	for (int i = 0; i < max_client_count; ++ i)
	{
		Client * c = clients + i;
		if (c->IsReady() && c->team == 0)
		{
			player_count++;
			select_vec.push_back(c->uid);
		}
	}

	int human_count = config.zombie_mode_human_number[player_count == 0 ? 0 : player_count - 1];
	
	for (uint i = 0; i < human_count; i++)
	{
		int pos = rand() % select_vec.size();
		int count = 0;
		std::vector<byte>::iterator itor;
		for (itor = select_vec.begin();itor != select_vec.end(); itor++)
		{
			if(count == pos)
			{
				select_vec.erase(itor);
				break;
			}
			count ++;
		}
	}
	
	for (uint i = 0; i < select_vec.size(); i++)
	{
		byte player_id = select_vec[i];
		for (int j = 0; j < max_client_count; ++ j)
		{
			if (clients[j].IsReady() && player_id == clients[j].uid)
			{
				clients[j].NotifyZombieFlash(player_count,true);
			}
		}
	}

	special_person_flashed = true;

	return true;
}

bool Game::CommonZombieSelect()
{
	int player_count = 0;
	std::vector<byte> select_vec;
	std::vector<byte> select_zom;

	for (int i = 0; i < max_client_count; ++ i)
	{
		Client * c = clients + i;
		if (c->IsReady())
		{
			player_count++;
			select_vec.push_back(c->uid);
		}
	}

	int zombie_count = 0;

	if (player_count < 5)
		zombie_count = 1;
	else if (player_count < 9)
		zombie_count = 2;
	else if (player_count < 13)
		zombie_count = 3;
	else
		zombie_count = 4;

	for (uint i = 0; i < zombie_count; i++)
	{
		int pos = rand() % select_vec.size();
		int count = 0;
		std::vector<byte>::iterator itor;
		for (itor = select_vec.begin();itor != select_vec.end(); itor++)
		{
			if(count == pos)
			{
				select_zom.push_back(*itor);
				break;
			}
			count ++;
		}
	}

	for (uint i = 0; i < select_zom.size(); i++)
	{
		byte player_id = select_zom[i];
		for (int j = 0; j < max_client_count; ++ j)
		{
			if (clients[j].IsReady() && player_id == clients[j].uid)
			{
				clients[j].NotifyCommonKingZombieFlash(clients[j].position);
				break;
			}
		}
		server.DestoryDummyByOwner(player_id);
	}

	special_person_flashed = true;

	return true;
}

bool Game::BossMode2Select()
{
	int player_count = 0;
	std::vector<byte> select_vec;
	std::vector<byte> select_boss;

	for (int i = 0; i < max_client_count; ++ i)
	{
		Client * c = clients + i;
		if (c->IsReady())
		{
			player_count++;
			select_vec.push_back(c->uid);
		}
	}
	
	if (player_count < 1)
		return false;

	int boss_count = 0;

	if (player_count <= 5)
		boss_count = 1;
	else if (player_count <= 10)
		boss_count = 2;
	else if (player_count <= 13)
		boss_count = 3;
	else
		boss_count = 4;
		
	for (uint i = 0; i < boss_count; i++)
	{
		int size = select_vec.size();
		int pos = boss2_boss_rand_start % size;
		
		boss2_boss_rand_start = pos + 1;
		
		select_boss.push_back(select_vec[pos]);
	}
	
	static const int boss_team = 1;
	
	int blank_num = select_boss.size();
	int blank_size = start_point[boss_team].size() / blank_num;
	if (blank_size <= 0) blank_size = start_point[boss_team].size();
	
	for (uint i = 0; i < select_boss.size(); i++)
	{
		Client *c = GetClient(select_boss[i]);

		if (c && c->IsReady())
		{
			int r_pos = rand() % blank_size;
			c->NotifBoss2Flash((r_pos + blank_size * i) % start_point[boss_team].size());
		}
	}

	special_person_flashed = true;

	return true;
}


bool Game::StreetKingSelect()
{
	for (int t = 0; t < 2; ++ t)
	{
		int player_count = 0;

		StreetKingInfo& s_info = street_king_info[t];
		s_info.current_street_king_uid = 0;
		s_info.select_state = StateNone;

		bool king_selected = false;
		if(s_info.next_round_street_king != 0)
		{
			for (int i = 0; i < max_client_count; ++ i)
			{
				Client * c = clients + i;
				if (c->IsReady() && c->uid == s_info.next_round_street_king)
				{
					king_selected = true;
					s_info.current_street_king_uid = s_info.next_round_street_king;
					s_info.select_state = StateKiller;
					s_info.next_round_street_king = 0;
				}
			}
		}
		
		if(!king_selected)
		{
			for (int i = 0; i < max_client_count; ++ i)
			{
				Client * c = clients + i;
				if (c->IsReady() && c->IsAlive() && c->team == t)
				{
					player_count++;
				}
			}

			if(player_count > 0)
			{
				std::vector<byte> select_vec;
				int team_member_count = 0;
				for (int i = 0; i < max_client_count; ++i)
				{
					Client * c = clients + i;
					if (c->IsReady() && c->IsAlive() && c->team == t)
					{
						if(c->uid != s_info.last_street_king_uid)
						{
							select_vec.push_back(c->uid);
						}
						team_member_count++;
					}
				}
				if(select_vec.size())
				{
					int pos = rand() % select_vec.size();

					s_info.current_street_king_uid = select_vec[pos];
					s_info.select_state = StateRandom;

					select_vec.clear();	
				}
				else if(team_member_count > 0)
				{
					for (int i = 0; i < max_client_count; ++i)
					{
						Client * c = clients + i;
						if (c->IsReady() && c->IsAlive() && c->team == t)
						{
							s_info.current_street_king_uid = c->uid;
							s_info.select_state = StateRandom;
						}
					}
				}
				else
				{
					log_write(LOG_ERROR, "StreetKing Select Error Not Enough Player ");
					return false;
				}

			}
		}

		for (int i = 0; i < max_client_count; ++ i)
		{
			if (clients[i].IsReady() && s_info.current_street_king_uid == clients[i].uid)
			{
				log_write(LOG_DEBUG1, "NotifyStreetKingFlash %d", clients[i].uid);
				clients[i].NotifyStreetKingFlash();
			}
		}
	}

	server.special_person_flashed = true;
	
	return true;
	
}


bool Game::CheckSupplyType(int type)
{
	if (game_type == KMoonMode && type == kSupplyGold)
		return true;
	if (type == kSupplyGold || type == kSupplyGoldWithForce || 
		type == kSupplyZombieGold || type == kSupplyDropItem || 
		type == kSupplyNone || type == kSupplyCommonZombie || 
		type == kSupplyCommonZombie2 || (type >= kSupplyCCoin && type <= kSupplyBoxD))
		return false;

	return true;
}

void Game::WriteLogClient(const char * format, ...)
{
	for (uint i = 0; i < max_client_count; i++)
	{
		Client* c = clients + i;

		if (c->IsReady() && write_log_to_client)
		{
			char buffer[1024];

			va_list args;
			va_start(args, format);
			int log_size = vsnprintf(buffer, sizeof(buffer), format, args);
			va_end(args);

			c->BeginWrite();
			c->WriteByte(SM_MessageClient);
			c->WriteString(buffer);
			c->EndWrite();
		}
	}
}

void Game::picked_count_add()
{
	picked_count++;
}

void Game::ServerDummyCreate(const ServerDummyCreateInfo& dummyinfo)
{
	DummyBaseInfo dummy;
	
	dummy.type = DUMMY_SERVER;
	dummy.buf_length = sizeof(ServerDummyCreateInfo);
	memcpy(dummy.buffer, &dummyinfo, dummy.buf_length);
	dummy.owner_id = 0;
	dummy.id = GenerateDummyIndex();
	
	dummy_object_map[dummy.id] = dummy;

	DummyCreateAction action;
	action.action = 1;
	action.id = dummy.id;
	action.owner_id = 0;

	dummy_create_vector.push_back(action);
	WriteLogClient("Dummy Add %d", dummy_create_vector.size());

	for (uint i = 0; i < max_client_count; i++)
	{
		Client * c = clients + i;

		if (c->IsReady())
		{
			c->NotifyDummyCreate(dummy);
		}
	}
}

void Game::ServerDummyCreateStepFather(const ServerDummyCreateWithStepFatherInfo& dummyinfo, byte team)
{
	byte owner_id = 0;
	for (uint i = 0; i < max_client_count; i++)
	{
		Client* c = server.clients + i;

		if (c && c->IsReady())
		{
			owner_id = c->uid;
		}
	}

	if(owner_id != 0)
	{
		DummyBaseInfo dummy;

		dummy.type = dummyinfo.type;
		dummy.sub_type = dummyinfo.sub_type;
		dummy.buf_length = sizeof(ServerDummyCreateWithStepFatherInfo);
		memcpy(dummy.buffer, &dummyinfo, dummy.buf_length);
		dummy.owner_id = owner_id;
		dummy.id = GenerateDummyIndex();
		dummy.need_stepfather = 1;
		dummy.team = team;
		dummy.can_hurt = 1;

		dummy_object_map[dummy.id] = dummy;

		DummyCreateAction action;
		action.action = 1;
		action.id = dummy.id;
		action.owner_id = owner_id;
		action.stepfather = 1;

		dummy_create_vector.push_back(action);

		WriteLogClient("Dummy Add %d", dummy_create_vector.size());

		for (uint i = 0; i < max_client_count; i++)
		{
			Client * c = clients + i;

			if (c->IsReady())
			{
				c->NotifyDummyCreate(dummy);
			}
		}
	}

	
}

void Game::DestoryDummyByOwner(byte owner_id, bool need_end_stepfather)
{
	std::vector<DummyCreateAction>::iterator itor = dummy_create_vector.begin();
	for (;itor != dummy_create_vector.end();)
	{
		const DummyCreateAction& action = *itor;
		if(action.owner_id == owner_id && (!action.stepfather || need_end_stepfather))
		{
			for (uint i = 0; i < max_client_count; i++)
			{
				Client* c = clients + i;

				if (c->IsReady())
				{
					c->NotifyDummyDestory(action.id);
				}
			}

			dummy_object_map.erase(action.id);

			itor = dummy_create_vector.erase(itor);

		}
		else
		{
			itor ++;
		}
	}
}

void Game::ChangeStepFatherDummyOwner(byte owner_id)
{
	byte new_owner_uid = 0;
	for (uint i = 0; i < max_client_count; i++)
	{
		Client* c = clients + i;
		if(c->IsReady() && c->uid != owner_id)
		{
			new_owner_uid = c->uid;
			break;
		}
	}
	if(new_owner_uid)
	{
		std::vector<DummyCreateAction>::iterator itor = dummy_create_vector.begin();
		for (;itor != dummy_create_vector.end();)
		{
			DummyCreateAction& action = *itor;
			if(action.owner_id == owner_id && action.stepfather)
			{
				for (uint i = 0; i < max_client_count; i++)
				{
					Client* c = clients + i;

					if (c->IsReady())
					{
						c->NotifyDummyChangeOwner(action.id, new_owner_uid);
					}
				}

				std::map<uint, DummyBaseInfo>::iterator itor = dummy_object_map.find(action.id);
				if(itor != dummy_object_map.end())
				{
					DummyBaseInfo& info = itor->second;
					info.owner_id = new_owner_uid;
				}

				action.owner_id = new_owner_uid;

			}
			else
			{
				itor ++;
			}
		}
	}
	else
	{
		DestoryDummyByOwner(owner_id, true);
	}
}

void Game::DestoryDummy(uint dummy_id)
{
	std::vector<DummyCreateAction>::iterator itor = dummy_create_vector.begin();
	for (;itor != dummy_create_vector.end();)
	{
		const DummyCreateAction& action = *itor;
		if(action.id == dummy_id)
		{
			for (uint i = 0; i < max_client_count; i++)
			{
				Client* c = clients + i;

				if (c->IsReady())
				{
					c->NotifyDummyDestory(dummy_id);
				}
			}
			dummy_object_map.erase(dummy_id);

			itor = dummy_create_vector.erase(itor);
		}
		else
		{
			itor ++;
		}
	}
}

void Game::DestoryAllDummy()
{
	std::vector<DummyCreateAction>::iterator itor = dummy_create_vector.begin();
	for (;itor != dummy_create_vector.end(); itor++)
	{
		const DummyCreateAction& action = *itor;
		for (uint i = 0; i < max_client_count; i++)
		{
			Client* c = clients + i;

			if (c->IsReady())
			{
				c->NotifyDummyDestory(action.id);
			}
		}
	}
	dummy_create_vector.clear();
}

void Game::RandSupplyType(Supply *supply)
{
	if (supply->type == kSupplyRandomBoxA)
	{
		const int rand_type[10] = {15, 15, 15, 15, 15, 15, 16, 16, 17, 17};
		supply->type = rand_type[rand() % 10];
	}
	else if (supply->type == kSupplyRandomBoxB)
	{
		const int rand_type[10] = {15, 15, 15, 15, 15, 16, 16, 17, 17, 17};
		supply->type = rand_type[rand() % 10];
	}
	else if (supply->type == kSupplyRandomBoxC)
	{
		const int rand_type[10] = {15, 16, 16, 16, 16, 17, 17, 17, 17, 17};
		supply->type = rand_type[rand() % 10];
	}
}

void Game::TrapTrigger(int index, byte uid)
{
	for (uint i = 0; i < max_client_count; i++)
	{
		Client * c = clients + i;

		if (c->IsReady())
		{
			c->BeginWrite();
			c->WriteByte(SM_UseItem_Trap_Trigger);
			c->WriteByte(uid);
			c->WriteInt(index);
			c->EndWrite();
		}
	}
}

void Game::EndRequestMatchClient()
{
	BeginWrite();
	WriteByte(GM_EndRequestMatchClient);
	EndWrite();
}

