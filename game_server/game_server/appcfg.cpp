#include "pch.h"
#include "inifile.h"

#define default_network_delay			0.1f

#define default_checkcheat_movespeed_multiple	2.0f
#define default_checkcheat_cleantime			3.0f
#define default_max_checkcheat_num				10

#define default_game_buffersize		128 * 1024
#define default_client_buffersize	32 * 1024

#define default_match_end_time 10 * 60
#define default_match_kill_num 10

// debug用
#define default_debug_serverscript		"debug_serverscript.txt"
#define default_debug_username			"debug_user"
#define default_add_debugserverscript	0
#define default_test_weaponattr			0
#define default_add_debugeffect			0
#define default_debugeffect_duration	0
#define default_debugeffect_interval	0
#define default_debugeffect_type		0
#define default_debugeffect_value		0

#define SECTION_APPCFG				"GameServer"

AppCfg::AppCfg()
	:network_delay(default_network_delay)
	,nMatchEndTime(default_match_end_time)
	,nMatchKillNum(default_match_kill_num)
	
	,checkcheat_movespeed_multiple(default_checkcheat_movespeed_multiple)
	,checkcheat_cleantime(default_checkcheat_cleantime)
	,max_checkcheat_num(default_max_checkcheat_num)
	
	,game_buffersize(default_game_buffersize)
	,client_buffersize(default_client_buffersize)
	
	// debug用
	,add_debugserverscript(default_add_debugserverscript)
	,test_weaponattr(default_test_weaponattr)
	,add_debugeffect(default_add_debugeffect)
	,debugeffect_duration(default_debugeffect_duration)
	,debugeffect_interval(default_debugeffect_interval)
	,debugeffect_type(default_debugeffect_type)
	,debugeffect_value(default_debugeffect_value)
{
	memset(debug_serverscript, 0, sizeof(debug_serverscript));
	memset(debug_username, 0, sizeof(debug_username));
	
	memset(file_name, 0, sizeof(file_name));
}

void AppCfg::LoadConfigFile(const char * file)
{
	if (file)
	{
		network_delay = read_profile_float(SECTION_APPCFG, "network_delay", default_network_delay, file);
		nMatchEndTime = read_profile_float(SECTION_APPCFG, "match_end_time", default_match_end_time, file);
		nMatchKillNum = read_profile_float(SECTION_APPCFG, "match_kill_num", default_match_kill_num, file);
		
		checkcheat_movespeed_multiple = read_profile_float(SECTION_APPCFG, "checkcheat_movespeed_multiple", default_checkcheat_movespeed_multiple, file);
		checkcheat_cleantime = read_profile_float(SECTION_APPCFG, "checkcheat_cleantime", default_checkcheat_cleantime, file);
		max_checkcheat_num = read_profile_int(SECTION_APPCFG, "max_checkcheat_num", default_max_checkcheat_num, file);
		
		game_buffersize = read_profile_int(SECTION_APPCFG, "game_buffersize", default_game_buffersize, file);
		client_buffersize = read_profile_int(SECTION_APPCFG, "client_buffersize", default_client_buffersize, file);
		
		// debug用
		read_profile_string(SECTION_APPCFG, "debug_serverscript", debug_serverscript, sizeof(debug_serverscript), default_debug_serverscript, file);
		add_debugserverscript = read_profile_int(SECTION_APPCFG, "add_debugserverscript", default_add_debugserverscript, file);
		read_profile_string(SECTION_APPCFG, "debug_username", debug_username, sizeof(debug_username), default_debug_username, file);
		test_weaponattr = read_profile_int(SECTION_APPCFG, "test_weaponattr", default_test_weaponattr, file);
		add_debugeffect = read_profile_int(SECTION_APPCFG, "add_debugeffect", default_add_debugeffect, file);
		debugeffect_duration = read_profile_float(SECTION_APPCFG, "debugeffect_duration", default_debugeffect_duration, file);
		debugeffect_interval = read_profile_float(SECTION_APPCFG, "debugeffect_interval", default_debugeffect_interval, file);
		debugeffect_type = read_profile_int(SECTION_APPCFG, "debugeffect_type", default_debugeffect_type, file);
		debugeffect_value = read_profile_float(SECTION_APPCFG, "debugeffect_value", default_debugeffect_value, file);
		
		strcpy(file_name, file);
	}
}

void AppCfg::ReloadConfigFile()
{
	if (file_name[0])
	{
		network_delay = read_profile_float(SECTION_APPCFG, "network_delay", default_network_delay, file_name);
		
		checkcheat_movespeed_multiple = read_profile_float(SECTION_APPCFG, "checkcheat_movespeed_multiple", default_checkcheat_movespeed_multiple, file_name);
		checkcheat_cleantime = read_profile_float(SECTION_APPCFG, "checkcheat_cleantime", default_checkcheat_cleantime, file_name);
		max_checkcheat_num = read_profile_int(SECTION_APPCFG, "max_checkcheat_num", default_max_checkcheat_num, file_name);
		
		game_buffersize = read_profile_int(SECTION_APPCFG, "game_buffersize", default_game_buffersize, file_name);
		client_buffersize = read_profile_int(SECTION_APPCFG, "client_buffersize", default_client_buffersize, file_name);
		
		// debug用
		read_profile_string(SECTION_APPCFG, "debug_serverscript", debug_serverscript, sizeof(debug_serverscript), default_debug_serverscript, file_name);
		add_debugserverscript = read_profile_int(SECTION_APPCFG, "add_debugserverscript", default_add_debugserverscript, file_name);
		read_profile_string(SECTION_APPCFG, "debug_username", debug_username, sizeof(debug_username), default_debug_username, file_name);
		test_weaponattr = read_profile_int(SECTION_APPCFG, "test_weaponattr", default_test_weaponattr, file_name);
		add_debugeffect = read_profile_int(SECTION_APPCFG, "add_debugeffect", default_add_debugeffect, file_name);
		debugeffect_duration = read_profile_float(SECTION_APPCFG, "debugeffect_duration", default_debugeffect_duration, file_name);
		debugeffect_interval = read_profile_float(SECTION_APPCFG, "debugeffect_interval", default_debugeffect_interval, file_name);
		debugeffect_type = read_profile_int(SECTION_APPCFG, "debugeffect_type", default_debugeffect_type, file_name);
		debugeffect_value = read_profile_float(SECTION_APPCFG, "debugeffect_value", default_debugeffect_value, file_name);
	}
	
	PrintAppCfg();
}

void AppCfg::PrintAppCfg()
{
	log_write_sys(LOG_INFO, "---PrintAppCfg Start---");
	
	log_write_sys(LOG_INFO, "network_delay = %f", network_delay);
	
	log_write_sys(LOG_INFO, "checkcheat_movespeed_multiple = %f", checkcheat_movespeed_multiple);
	log_write_sys(LOG_INFO, "checkcheat_cleantime = %f", checkcheat_cleantime);
	log_write_sys(LOG_INFO, "max_checkcheat_num = %d", max_checkcheat_num);

	log_write_sys(LOG_INFO, "game_buffersize = %d", game_buffersize);
	log_write_sys(LOG_INFO, "client_buffersize = %d", client_buffersize);
	
	// debug用
	log_write_sys(LOG_INFO, "debug_serverscript = %s", debug_serverscript);
	log_write_sys(LOG_INFO, "add_debugserverscript = %d", add_debugserverscript);
	log_write_sys(LOG_INFO, "debug_username = %s", debug_username);
	log_write_sys(LOG_INFO, "test_weaponattr = %d", test_weaponattr);
	log_write_sys(LOG_INFO, "add_debugeffect = %d", add_debugeffect);
	log_write_sys(LOG_INFO, "debugeffect_duration = %f", debugeffect_duration);
	log_write_sys(LOG_INFO, "debugeffect_interval = %f", debugeffect_interval);
	log_write_sys(LOG_INFO, "debugeffect_type = %d", debugeffect_type);
	log_write_sys(LOG_INFO, "debugeffect_value = %f", debugeffect_value);
	
	log_write_sys(LOG_INFO, "---PrintAppCfg End---");
}

AppCfg appcfg;
