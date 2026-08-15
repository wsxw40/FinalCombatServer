#pragma once

class AppCfg
{
public:
	AppCfg();
	
	void LoadConfigFile(const char * file);
	
	void ReloadConfigFile();
	
	void PrintAppCfg();
	
public:
	float network_delay;
	
	int nMatchEndTime;			// 多久之后 不再拉人
	int nMatchKillNum;			// 杀人多少之后 不再拉人
	
	float checkcheat_movespeed_multiple;
	float checkcheat_cleantime;
	int max_checkcheat_num;
	
	int game_buffersize;
	int client_buffersize;
	
	// debug用
	int add_debugserverscript;
	char debug_serverscript[64];
	char debug_username[64];
	int test_weaponattr;
	int add_debugeffect;
	double debugeffect_duration;
	double debugeffect_interval;
	short debugeffect_type;
	float debugeffect_value;
	
private:
	char file_name[256];
};

// global status instance;
extern AppCfg appcfg;
