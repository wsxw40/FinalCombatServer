#include "pch.h"
#include "inifile.h"

#define default_max_proxy_count			10

#define default_server_buffersize		32 * 1024

#define SECTION_APPCFG				"LoginServer"

AppCfg::AppCfg()
	:max_proxy_count(default_max_proxy_count)
	
	,server_buffersize(default_server_buffersize)
{
	memset(file_name, 0, sizeof(file_name));
}

void AppCfg::LoadConfigFile(const char * file)
{
	if (file)
	{
		max_proxy_count = read_profile_int(SECTION_APPCFG, "max_proxy_count", default_max_proxy_count, file);
		
		server_buffersize = read_profile_int(SECTION_APPCFG, "server_buffersize", default_server_buffersize, file);
		
		strcpy(file_name, file);
	}
}

void AppCfg::ReloadConfigFile()
{
	if (file_name[0])
	{
		server_buffersize = read_profile_int(SECTION_APPCFG, "server_buffersize", default_server_buffersize, file_name);
	}
	
	PrintAppCfg();
}

void AppCfg::PrintAppCfg()
{
	log_write_sys(LOG_INFO, "---PrintAppCfg Start---");
	
	log_write_sys(LOG_INFO, "max_proxy_count = %d", max_proxy_count);

	log_write_sys(LOG_INFO, "server_buffersize = %d", server_buffersize);
	
	log_write_sys(LOG_INFO, "---PrintAppCfg End---");
}

AppCfg appcfg;
