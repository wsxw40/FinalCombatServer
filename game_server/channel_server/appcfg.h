#pragma once

class AppCfg
{
public:
	AppCfg();
	
	void LoadConfigFile(const char * file);
	
	void ReloadConfigFile();
	
	void PrintAppCfg();
	
	const char* GetCfgFileName();
	
public:
	int max_client_count;
	int max_room_count;

	int boss_mode_player_count;
	int boss_mode2_player_count;
	int street_boy_each_side_player_count;
	int zombie_mode_player_count;
	int bosspve_mode_player_count;
	
	float connection_reporttime;
	
	double connection_checktime_min;
	double connection_checktime_max;
	int connection_checkerror_max;
	
	int proxy_buffersize;
	int game_buffersize;
	int client_buffersize;
	int idle_kick_open;
	int match_rule_value;
	
	int hage_battle_team_rule_value;
	int hage_battle_knife_rule_value;
	
private:
	char file_name[256];
};

// global status instance;
extern AppCfg appcfg;
