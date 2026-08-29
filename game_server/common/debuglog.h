#pragma once

#include <string>

void debuglog_write(const char * format, ...);

class DebugFunLog
{
public:
	DebugFunLog(const char *file_name, const char *fun_name, int line);
	~DebugFunLog();

private:
	std::string file_name_;
	std::string fun_name_;
	int line_;
};

#ifdef DEBUG_LOG
	#define DEBUGLOG_WRITE(format, ...)	debuglog_write(format, __VA_ARGS__)
	#define DEBUG_FUNLOG				DebugFunLog debugfun_log(__FILE__, __PRETTY_FUNCTION__, __LINE__)
#else
	#define DEBUGLOG_WRITE(format, ...)	
	#define DEBUG_FUNLOG				
#endif
