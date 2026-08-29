#pragma once

class AppCfg
{
public:
	AppCfg();
	
	void LoadConfigFile(const char * file);
	
	void ReloadConfigFile();
	
	void PrintAppCfg();
	
public:
	int max_client_count;
	int max_server_count;
	int max_channel_count;
	int max_gm_count;
	int max_chatgroup_count;
	int max_battlegroup_count;
	int max_hagebattlegroup_count;
	
	int chatgroup_character_limit;
	int battlegroup_character_limit;
	float battlegroup_searching_interval;
	int rpc_timeout;

	int max_matching_group_count;
	int matching_group_character_limit;
	
	int info_buffersize;
	int channel_buffersize;
	int client_buffersize;
	int matching_buffersize;
	int fcm_time_interval;

	char fcm_address[256];
	int  fcm_port;

	int rpc_update_interval;
	int rpc__disconnect_interval;

	int max_client_current_count;
	
	int happy_jump_battle_join_time;
	
private:
	char file_name[256];
};

// global status instance;
extern AppCfg appcfg;
