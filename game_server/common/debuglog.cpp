#include <sys/types.h> 
#include <unistd.h> 
#include <stdarg.h>
#include <time.h>

#include "common.h"
#include "debuglog.h"

static FILE * debuglog_file = NULL;

void debuglog_write(const char * format, ...)
{
	if (!debuglog_file)
	{
		char szBuffer[256] = {0};
		sprintf(szBuffer, "%d.log", getpid());
		debuglog_file = fopen(szBuffer, "wb");
	}
	
	if (debuglog_file)
	{
		char buff[512];

		va_list args;
		va_start(args, format);
		vsnprintf(buff, sizeof(buff), format, args);
		va_end(args);
	
		time_t time_v;
		char time_buff[256];
		time(&time_v);

		strftime(time_buff, sizeof(time_buff), "%Y-%m-%d %H:%M:%S", localtime(&time_v));
		struct timeval time_value;
		if (!gettimeofday(&time_value, NULL))
			sprintf(time_buff, "%s.%03ld", time_buff, time_value.tv_usec/1000);

		fprintf(debuglog_file, "[%s] %s\n", time_buff, buff);
		fflush(debuglog_file);
	}
}

DebugFunLog::DebugFunLog(const char *file_name, const char *fun_name, int line)
	: file_name_(file_name)
	, fun_name_(fun_name)
	, line_(line)
{
	debuglog_write("%s:%d, [%s] In", file_name_.c_str(), line_, fun_name_.c_str());
}

DebugFunLog::~DebugFunLog()
{
	debuglog_write("%s:%d, [%s] Out", file_name_.c_str(), line_, fun_name_.c_str());
}
