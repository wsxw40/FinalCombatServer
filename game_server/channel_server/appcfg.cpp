#include "pch.h"
#include "inifile.h"

#define default_max_client_count 500
#define default_max_room_count 100

#define default_connection_reporttime		15 * 60

#define default_connection_checktime_min	2.5f
#define default_connection_checktime_max	10.0f
#define default_connection_checkerror_max	2

#define default_proxy_buffersize	64 * 1024
#define default_game_buffersize		128 * 1024
#define default_client_buffersize	32 * 1024

#define default_boss_mode_player_count 10
#define default_boss_mode2_player_count 2
#define default_bosspve_mode_player_count 1
#define default_street_boy_each_side_player_count 1
#define default_zombie_mode_player_count 1
#define default_idle_kick_open 1

#define default_match_rule_value 100
#define default_hage_battle_team_rule_value 100
#define default_hage_battle_knife_rule_value 100

#define SECTION_APPCFG				"ChannelServer"

AppCfg::AppCfg()
	:max_client_count(default_max_client_count)
	,max_room_count(default_max_room_count)
		
	,connection_reporttime(default_connection_reporttime)
	,connection_checktime_min(default_connection_checktime_min)
	,connection_checktime_max(default_connection_checktime_max)
	,connection_checkerror_max(default_connection_checkerror_max)
	
	,proxy_buffersize(default_proxy_buffersize)
	,game_buffersize(default_game_buffersize)
	,client_buffersize(default_client_buffersize)
	,boss_mode_player_count(default_boss_mode_player_count)
	,boss_mode2_player_count(default_boss_mode2_player_count)
	,street_boy_each_side_player_count(default_street_boy_each_side_player_count)
	,zombie_mode_player_count(default_zombie_mode_player_count)
	,bosspve_mode_player_count(default_bosspve_mode_player_count)
	,idle_kick_open(default_idle_kick_open)
	,match_rule_value(default_match_rule_value)
	,hage_battle_team_rule_value(default_hage_battle_team_rule_value)
	,hage_battle_knife_rule_value(default_hage_battle_knife_rule_value)
{
	memset(file_name, 0, sizeof(file_name));
}

void AppCfg::LoadConfigFile(const char * file)
{
	if (file)
	{
		max_client_count = read_profile_int(SECTION_APPCFG, "max_client_count", default_max_client_count, file);
		max_room_count = read_profile_int(SECTION_APPCFG, "max_room_count", default_max_room_count, file);
		
		connection_reporttime = read_profile_float(SECTION_APPCFG, "connection_reporttime", default_connection_reporttime, file);
		
		connection_checktime_min = read_profile_float(SECTION_APPCFG, "connection_checktime_min", default_connection_checktime_min, file);
		connection_checktime_max = read_profile_float(SECTION_APPCFG, "connection_checktime_max", default_connection_checktime_max, file);
		connection_checkerror_max = read_profile_int(SECTION_APPCFG, "connection_checkerror_max", default_connection_checkerror_max, file);
		
		proxy_buffersize = read_profile_int(SECTION_APPCFG, "proxy_buffersize", default_proxy_buffersize, file);
		game_buffersize = read_profile_int(SECTION_APPCFG, "game_buffersize", default_game_buffersize, file);
		client_buffersize = read_profile_int(SECTION_APPCFG, "client_buffersize", default_client_buffersize, file);

		boss_mode_player_count = read_profile_int(SECTION_APPCFG, "boss_mode_player_count", default_boss_mode_player_count, file);
		boss_mode2_player_count = read_profile_int(SECTION_APPCFG, "boss_mode2_player_count", default_boss_mode2_player_count, file);
		street_boy_each_side_player_count = read_profile_int(SECTION_APPCFG, "street_boy_each_side_count", default_street_boy_each_side_player_count, file);
		zombie_mode_player_count = read_profile_int(SECTION_APPCFG, "zombie_mode_player_count", default_zombie_mode_player_count, file);
		bosspve_mode_player_count = read_profile_int(SECTION_APPCFG, "bosspve_mode_player_count", default_bosspve_mode_player_count, file);
		match_rule_value = read_profile_int(SECTION_APPCFG, "match_rule_value", default_match_rule_value, file);
		hage_battle_team_rule_value = read_profile_int(SECTION_APPCFG, "hage_battle_team_rule_value", default_hage_battle_team_rule_value, file);
		hage_battle_knife_rule_value = read_profile_int(SECTION_APPCFG, "hage_battle_knife_rule_value", default_hage_battle_knife_rule_value, file);

		idle_kick_open = read_profile_int(SECTION_APPCFG, "idle_kick_open", default_idle_kick_open, file);

		strcpy(file_name, file);
	}
}

void AppCfg::ReloadConfigFile()
{
	if (file_name[0])
	{
		connection_reporttime = read_profile_float(SECTION_APPCFG, "connection_reporttime", default_connection_reporttime, file_name);
		
		connection_checktime_min = read_profile_float(SECTION_APPCFG, "connection_checktime_min", default_connection_checktime_min, file_name);
		connection_checktime_max = read_profile_float(SECTION_APPCFG, "connection_checktime_max", default_connection_checktime_max, file_name);
		connection_checkerror_max = read_profile_int(SECTION_APPCFG, "connection_checkerror_max", default_connection_checkerror_max, file_name);
		
		proxy_buffersize = read_profile_int(SECTION_APPCFG, "proxy_buffersize", default_proxy_buffersize, file_name);
		game_buffersize = read_profile_int(SECTION_APPCFG, "game_buffersize", default_game_buffersize, file_name);
		client_buffersize = read_profile_int(SECTION_APPCFG, "client_buffersize", default_client_buffersize, file_name);

		boss_mode_player_count = read_profile_int(SECTION_APPCFG, "boss_mode_player_count", default_boss_mode_player_count, file_name);
		boss_mode2_player_count = read_profile_int(SECTION_APPCFG, "boss_mode2_player_count", default_boss_mode2_player_count, file_name);
		street_boy_each_side_player_count = read_profile_int(SECTION_APPCFG, "street_boy_each_side_count", default_street_boy_each_side_player_count, file_name);
		zombie_mode_player_count = read_profile_int(SECTION_APPCFG, "zombie_mode_player_count", default_zombie_mode_player_count, file_name);
		bosspve_mode_player_count = read_profile_int(SECTION_APPCFG, "bosspve_mode_player_count", default_bosspve_mode_player_count, file_name);
		match_rule_value = read_profile_int(SECTION_APPCFG, "match_rule_value", default_match_rule_value, file_name);
		hage_battle_team_rule_value = read_profile_int(SECTION_APPCFG, "hage_battle_team_rule_value", default_hage_battle_team_rule_value, file_name);
		hage_battle_knife_rule_value = read_profile_int(SECTION_APPCFG, "hage_battle_knife_rule_value", default_hage_battle_knife_rule_value, file_name);

		idle_kick_open = read_profile_int(SECTION_APPCFG, "idle_kick_open", default_idle_kick_open, file_name);
	}
	
	PrintAppCfg();
}

void AppCfg::PrintAppCfg()
{
	log_write_sys(LOG_INFO, "---PrintAppCfg Start---");
	
	log_write_sys(LOG_INFO, "max_client_count = %d", max_client_count);
	log_write_sys(LOG_INFO, "max_room_count = %d", max_room_count);
	
	log_write_sys(LOG_INFO, "connection_reporttime = %f", connection_reporttime);
	
	log_write_sys(LOG_INFO, "connection_checktime_min = %f", (float)connection_checktime_min);
	log_write_sys(LOG_INFO, "connection_checktime_max = %f", (float)connection_checktime_max);
	log_write_sys(LOG_INFO, "connection_checkerror_max = %d", connection_checkerror_max);
	
	log_write_sys(LOG_INFO, "proxy_buffersize = %d", proxy_buffersize);
	log_write_sys(LOG_INFO, "game_buffersize = %d", game_buffersize);
	log_write_sys(LOG_INFO, "client_buffersize = %d", client_buffersize);

	log_write_sys(LOG_INFO, "boss_mode_player_count = %d", boss_mode_player_count);
	log_write_sys(LOG_INFO, "street_boy_each_side_player_count = %d", street_boy_each_side_player_count);
	log_write_sys(LOG_INFO, "zombie_mode_player_count = %d", zombie_mode_player_count);
	log_write_sys(LOG_INFO, "bosspve_mode_player_count = %d", bosspve_mode_player_count);
	log_write_sys(LOG_INFO, "match_rule_value = %d", match_rule_value);

	log_write_sys(LOG_INFO, "idle_kick_open = %d", idle_kick_open);

	log_write_sys(LOG_INFO, "---PrintAppCfg End---");
}

const char* AppCfg::GetCfgFileName()
{
	return file_name;
}

AppCfg appcfg;
