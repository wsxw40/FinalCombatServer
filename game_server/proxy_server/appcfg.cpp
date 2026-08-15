#include "pch.h"
#include "inifile.h"

#define default_max_client_count 20000
#define default_max_server_count 10
#define default_max_channel_count 10
#define default_max_gm_count 10
#define default_max_chatgroup_count default_max_client_count
#define default_max_battlegroup_count default_max_client_count
#define default_max_hagebattlegroup_count default_max_client_count

#define default_chatgroup_character_limit	5
#define default_battlegroup_character_limit	6
#define default_battlegroup_searching_interval	5
#define default_rpctimeout					15

#define default_fcm_time_interval	30

#define default_info_buffersize		64 * 1024
#define default_channel_buffersize	64 * 1024
#define default_client_buffersize	32 * 1024
#define default_matching_buffersize	32 * 1024
#define default_fcm_port 9005
#define default_rpc_update_interval 60
#define default_max_client_current_count 20000
#define default_rpc_disconnect_interval 1

#define default_max_matching_group_count 1024
#define default_matching_group_character_limit 8

#define default_happy_jump_battle_join_time 3600


#define SECTION_APPCFG				"ProxyServer"

AppCfg::AppCfg()
	:max_client_count(default_max_client_count)
	,max_server_count(default_max_server_count)
	,max_channel_count(default_max_channel_count)
	,max_gm_count(default_max_gm_count)
	,max_chatgroup_count(default_max_chatgroup_count)
	,max_battlegroup_count(default_max_battlegroup_count)

	,max_matching_group_count(default_max_battlegroup_count)
	,matching_group_character_limit(default_matching_group_character_limit)
	
	,chatgroup_character_limit(default_chatgroup_character_limit)
	,battlegroup_character_limit(default_battlegroup_character_limit)
	,battlegroup_searching_interval(default_battlegroup_searching_interval)
	,rpc_timeout(default_rpctimeout)
	,fcm_time_interval(default_fcm_time_interval)
	
	,info_buffersize(default_info_buffersize)
	,channel_buffersize(default_channel_buffersize)
	,client_buffersize(default_client_buffersize)
	,matching_buffersize(default_matching_buffersize)
	,fcm_port(default_fcm_port)

	,rpc_update_interval(default_rpc_update_interval)
	,rpc__disconnect_interval(default_rpc_disconnect_interval)

	,max_client_current_count(default_max_client_current_count)
	,happy_jump_battle_join_time(default_happy_jump_battle_join_time)
{
	memset(file_name, 0, sizeof(file_name));
	memset(fcm_address, 0, sizeof(fcm_address));
}

void AppCfg::LoadConfigFile(const char * file)
{
	if (file)
	{
		max_client_count = read_profile_int(SECTION_APPCFG, "max_client_count", default_max_client_count, file);
		max_server_count = read_profile_int(SECTION_APPCFG, "max_server_count", default_max_server_count, file);
		max_channel_count = read_profile_int(SECTION_APPCFG, "max_channel_count", default_max_channel_count, file);
		max_gm_count = read_profile_int(SECTION_APPCFG, "max_gm_count", default_max_gm_count, file);
		max_chatgroup_count = read_profile_int(SECTION_APPCFG, "max_chatgroup_count", default_max_chatgroup_count, file);
		max_battlegroup_count = read_profile_int(SECTION_APPCFG, "max_battlegroup_count", default_max_battlegroup_count, file);
		max_hagebattlegroup_count = read_profile_int(SECTION_APPCFG, "max_hagebattlegroup_count", default_max_hagebattlegroup_count, file);
		
		chatgroup_character_limit = read_profile_int(SECTION_APPCFG, "chatgroup_character_limit", default_chatgroup_character_limit, file);
		battlegroup_character_limit = read_profile_int(SECTION_APPCFG, "battlegroup_character_limit", default_battlegroup_character_limit, file);
		battlegroup_searching_interval = read_profile_float(SECTION_APPCFG, "battlegroup_searching_interval", default_battlegroup_searching_interval, file);
		rpc_timeout = read_profile_int(SECTION_APPCFG, "rpc_timeout", default_rpctimeout, file);
		fcm_time_interval = read_profile_int(SECTION_APPCFG, "fcm_time_interval", default_fcm_time_interval, file);

		rpc_update_interval = read_profile_int(SECTION_APPCFG, "rpc_update_interval", default_rpc_update_interval, file);
		rpc__disconnect_interval = read_profile_int(SECTION_APPCFG, "rpc_disconnect_interval", default_rpc_disconnect_interval, file);

		info_buffersize = read_profile_int(SECTION_APPCFG, "info_buffersize", default_info_buffersize, file);
		channel_buffersize = read_profile_int(SECTION_APPCFG, "channel_buffersize", default_channel_buffersize, file);
		client_buffersize = read_profile_int(SECTION_APPCFG, "client_buffersize", default_client_buffersize, file);
		matching_buffersize = read_profile_int(SECTION_APPCFG, "matching_buffersize", default_matching_buffersize, file);

		int result = read_profile_string(SECTION_APPCFG, "fcm_address", fcm_address, sizeof(fcm_address), "indulge.fc.xunlei.com", file);
		
		max_client_current_count = read_profile_int(SECTION_APPCFG, "max_client_current_count", max_client_count, file);

		happy_jump_battle_join_time = read_profile_int(SECTION_APPCFG, "happy_jump_battle_join_time", default_happy_jump_battle_join_time, file);
		
		if(result == 0)
		{
			log_write(LOG_ERROR, "fcm_address error !!");
		}

		fcm_port = read_profile_int(SECTION_APPCFG, "fcm_port", default_fcm_port, file);

		strcpy(file_name, file);
		
	}
}

void AppCfg::ReloadConfigFile()
{
	if (file_name[0])
	{
		chatgroup_character_limit = read_profile_int(SECTION_APPCFG, "chatgroup_character_limit", default_chatgroup_character_limit, file_name);
		battlegroup_character_limit = read_profile_int(SECTION_APPCFG, "battlegroup_character_limit", default_battlegroup_character_limit, file_name);
		battlegroup_searching_interval = read_profile_float(SECTION_APPCFG, "battlegroup_searching_interval", default_battlegroup_searching_interval, file_name);
		rpc_timeout = read_profile_int(SECTION_APPCFG, "rpc_timeout", default_rpctimeout, file_name);
		fcm_time_interval = read_profile_int(SECTION_APPCFG, "fcm_time_interval", default_fcm_time_interval, file_name);

		rpc_update_interval = read_profile_int(SECTION_APPCFG, "rpc_update_interval", default_rpc_update_interval, file_name);
		rpc__disconnect_interval = read_profile_int(SECTION_APPCFG, "rpc_disconnect_interval", default_rpc_disconnect_interval, file_name);
		
		info_buffersize = read_profile_int(SECTION_APPCFG, "info_buffersize", default_info_buffersize, file_name);
		channel_buffersize = read_profile_int(SECTION_APPCFG, "channel_buffersize", default_channel_buffersize, file_name);
		client_buffersize = read_profile_int(SECTION_APPCFG, "client_buffersize", default_client_buffersize, file_name);

		max_client_current_count = read_profile_int(SECTION_APPCFG, "max_client_current_count", max_client_count, file_name);
		
		happy_jump_battle_join_time = read_profile_int(SECTION_APPCFG, "happy_jump_battle_join_time", default_happy_jump_battle_join_time, file_name);

		int result = read_profile_string(SECTION_APPCFG, "fcm_address", fcm_address, sizeof(fcm_address), "indulge.fc.xunlei.com", file_name);

		if(result == 0)
		{
			log_write(LOG_ERROR, "reload fcm_address error !!");
		}

		fcm_port = read_profile_int(SECTION_APPCFG, "fcm_port", default_fcm_port, file_name);

	}
	
	PrintAppCfg();
}

void AppCfg::PrintAppCfg()
{
	log_write_sys(LOG_INFO, "---PrintAppCfg Start---");
	
	log_write_sys(LOG_INFO, "max_client_count = %d", max_client_count);
	log_write_sys(LOG_INFO, "max_server_count = %d", max_server_count);
	log_write_sys(LOG_INFO, "max_channel_count = %d", max_channel_count);
	log_write_sys(LOG_INFO, "max_gm_count = %d", max_gm_count);
	log_write_sys(LOG_INFO, "max_chatgroup_count = %d", max_chatgroup_count);
	log_write_sys(LOG_INFO, "max_battlegroup_count = %d", max_battlegroup_count);
	
	log_write_sys(LOG_INFO, "max_client_current_count = %d", max_client_current_count);
	
	log_write_sys(LOG_INFO, "chatgroup_character_limit = %d", chatgroup_character_limit);
	log_write_sys(LOG_INFO, "battlegroup_character_limit = %d", battlegroup_character_limit);
	log_write_sys(LOG_INFO, "battlegroup_searching_interval = %f", battlegroup_searching_interval);
	log_write_sys(LOG_INFO, "rpc_timeout = %d", rpc_timeout);
	log_write_sys(LOG_INFO, "fcm_time_interval = %d", fcm_time_interval);

	log_write_sys(LOG_INFO, "rpc_update_interval = %d", rpc_update_interval);
	log_write_sys(LOG_INFO, "rpc_disconnect_interval = %d", rpc__disconnect_interval);

	
	log_write_sys(LOG_INFO, "info_buffersize = %d", info_buffersize);
	log_write_sys(LOG_INFO, "channel_buffersize = %d", channel_buffersize);
	log_write_sys(LOG_INFO, "client_buffersize = %d", client_buffersize);

	log_write_sys(LOG_INFO, "fcm_address = %s  fcm_port = %d", fcm_address,fcm_port);
	

	log_write_sys(LOG_INFO, "---PrintAppCfg End---");
}

AppCfg appcfg;
