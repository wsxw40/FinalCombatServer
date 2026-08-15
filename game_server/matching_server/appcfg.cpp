#include "pch.h"
#include "inifile.h"

#define default_server_buffer_size 64*1024

#define SECTION_APPCFG				"MatchingServer"

AppCfg::AppCfg()
:m_dwServerBufferSize(default_server_buffer_size)
{
	memset(file_name, 0, sizeof(file_name));
}

void AppCfg::LoadConfigFile(const char * file)
{
	if (file)
	{
		m_dwServerBufferSize = read_profile_int(SECTION_APPCFG, "server_buffer_size", default_server_buffer_size, file);

		strcpy(file_name, file);
		
	}
}

void AppCfg::ReloadConfigFile()
{
	if (file_name[0])
	{
		m_dwServerBufferSize = read_profile_int(SECTION_APPCFG, "server_buffer_size", default_server_buffer_size, file_name);

	}
	
	PrintAppCfg();
}

void AppCfg::PrintAppCfg()
{
	log_write_sys(LOG_INFO, "---PrintAppCfg Start---");
	
	log_write_sys(LOG_INFO, "server_buffer_size = %d", m_dwServerBufferSize);
	//log_write_sys(LOG_INFO, "max_server_count = %d", max_server_count);
}

AppCfg appcfg;
