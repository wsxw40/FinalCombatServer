#pragma once

class AppCfg
{
public:
	AppCfg();
	
	void LoadConfigFile(const char * file);
	
	void ReloadConfigFile();
	
	void PrintAppCfg();
	
public:
	int max_proxy_count;
	int max_apexreqpool_size;
	
	int server_buffersize;
	
private:
	char file_name[256];
};

// global status instance;
extern AppCfg appcfg;
