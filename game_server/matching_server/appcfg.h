#pragma once

class AppCfg
{
public:
	AppCfg();
	
	void LoadConfigFile(const char * file);
	
	void ReloadConfigFile();
	
	void PrintAppCfg();
	
public:

	uint m_dwServerBufferSize;
	
private:
	char file_name[256];
};

// global status instance;
extern AppCfg appcfg;
