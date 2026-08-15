#include <stdarg.h>
#include <stdlib.h>
#include <stdio.h>
#include <time.h>
#include <string.h>
#include <sys/time.h>
#include <sys/syslog.h>
#include <cassert>

#include <execinfo.h>

#include "log.h"
#include "event.h"
#include "common.h"
#include "tcpconnection.h"
#include "networkstream.h"


enum LogMessageType {
	SM_Ident = 10000,
	SM_Log = 10001,
	SM_Level = 10002,
	CM_SetLevel = 10000 + 100,
};

static enum LogOutput {
	kStdout,
	kLogServer,
	kNone,
} log_output_mode = kNone;


static int log_output_level = LOG_INFO;
static char log_ident[256] = "";
static char log_output[256] = "";

static const char * log_level_names[] = {
    "emerg",
    "alert",
    "crit",
    "error",
    "warn",
    "notice",
    "info",
    "debug_lv1",
	"debug_lv2",
	"debug_lv3",
	"debug_lv4",
	"debug_lv5",
};

static int DumpStack(char *buffer, int max_buffersize, int start_stack);
static void send_ident_to_logserver(void*);

// -----------------------------------------------------------------
// class LogStream 
// -----------------------------------------------------------------
class LogStream : public NetworkStream
{
public:
	void Read(void* data, int size);
	void * ReadData(int size);

	void ReadInt(int& data);
	void ReadInt(uint& data);

	void ReadByte(byte& data);
	void ReadByte(char& data);

	void ReadShort(short& data);
	void ReadShort(ushort& data);

	int ReadString(char* s, int size);

	bool BeginWrite();

	bool WriteString(const char * data, int len);
	bool WriteString(const char* data);

	bool WriteInt(int data);
	bool WriteInt(uint data);

	bool WriteShort(short data);
	bool WriteShort(ushort data);

	bool WriteByte(byte data);

	bool Write(const void* data, int size);

	bool EndWrite();
	void CancelWrite(); 

	// on message
	virtual void OnMessage();

	// constructor
	LogStream(int _buffer_size);
	~LogStream();

private:
	// on parse message
	void OnParseMessage();

public:
	char* write_position;
	char* write_end;
	char* read_position;
	char* read_end;

	static const int LOG_MESSAGE_HEAD_SIZE = 4;
	static const int MAX_PKGSIZE = 64 * 1024;
};


// -----------------------------------------------------------------
// LogStream 
// -----------------------------------------------------------------
void LogStream::Read(void* data, int size)
{
	if (read_position + size > read_end)
		throw ERR_Read;

	memcpy(data, read_position, size);
	read_position += size;
}

void * LogStream::ReadData(int size)
{
	if (read_position + size > read_end)
		throw ERR_Read;

	read_position += size;

	return read_position - size;
}

void LogStream::ReadInt(int& data)
{
	Read(&data, sizeof(int));
}

void LogStream::ReadInt(uint& data)
{
	Read(&data, sizeof(uint));
}

void LogStream::ReadByte(byte& data)
{
	Read(&data, sizeof(byte));
}

void LogStream::ReadByte(char& data)
{
	Read(&data, sizeof(char));
}

void LogStream::ReadShort(short& data)
{
	Read(&data, 2);
}

void LogStream::ReadShort(ushort& data)
{
	Read(&data, 2);
}

int LogStream::ReadString(char* s, int size)
{
	uint l;
	ReadInt(l);

	if (size <= l)
	{
		log_write(LOG_ERROR, "read string len error: len = %d, size = %d", l, size);
		throw ERR_Read;
	}

	s[l] = 0;
	Read(s, l);
	return l;
}

bool LogStream::BeginWrite()
{
	if (write_position)
	{
		CancelWrite();
		return false;
	}

	if (send_offset + LOG_MESSAGE_HEAD_SIZE < send_buffer_size)
	{
		write_position = send_buffer + send_offset + LOG_MESSAGE_HEAD_SIZE;
		return true;
	}
	else
	{
		CancelWrite();
		return false;
	}

	return false;
}

bool LogStream::EndWrite()
{
	if (write_position)
	{
		int size = write_position - send_buffer - send_offset;

		if (size <= 0)
		{
			CancelWrite();
			return false;
		}

		if (size >= MAX_PKGSIZE)
		{
			CancelWrite();
			return false;
		}
		*(int*)(send_buffer + send_offset) = size;
		send_offset += size;
		write_position = NULL;

		if (no_delay && connection)
			connection->SendMessages();
		return true;
	}
	else
	{
		CancelWrite();
		return false;
	}
	return false;
}

void LogStream::CancelWrite()
{
	write_position = NULL;
}

bool LogStream::Write(const void* data, int size)
{
	if (write_position)
	{
		if (write_position + size < send_buffer + send_buffer_size)
		{
			memcpy(write_position, data, size);
			write_position += size;
			return true;
		 }
	}
	CancelWrite();
	return false;
}

bool LogStream::WriteInt(int data)
{
	return Write(&data, sizeof(int));
}

bool LogStream::WriteInt(uint data)
{
	return Write(&data, sizeof(uint));
}

bool LogStream::WriteByte(byte data)
{
	return Write(&data, 1);
}

bool LogStream::WriteShort(short data)
{
	return Write(&data, 2);
}

bool LogStream::WriteShort(ushort data)
{
	return Write(&data, 2);
}

bool LogStream::WriteString(const char* data)
{
	if (data)
	{
		int len = strlen(data);
		if (!WriteInt(len)) return false;
		return Write(data, len);
	}
	else
	{
		return WriteInt(0);
	}
}

// constructor
LogStream::LogStream(int size)
	: NetworkStream(size)
	, write_position(NULL)
	, write_end(NULL)
	, read_position(recv_buffer)
	, read_end(recv_buffer)
{
}

// destructor
LogStream::~LogStream()
{
}

// on parse message
void LogStream::OnParseMessage()
{
	try
	{
		char * pos = recv_buffer;

		while (pos + LOG_MESSAGE_HEAD_SIZE < recv_buffer + recv_offset)
		{
			int package_size = *(int*)pos;

			// TODO: stream checksum

			if (package_size < 4)
			{
				log_write(LOG_ERROR, "binary stream packet errror.");
				throw ERR_Parse;
			}

			// not enough buffer
			if (pos + package_size > recv_buffer + recv_offset)
				break;

			// set read position and read end
			read_position = pos + LOG_MESSAGE_HEAD_SIZE;
			read_end = pos + package_size;
			pos = read_end;

			// a complete message
			OnMessage();
		}

		// move unpared data
		if (pos > recv_buffer && pos <= recv_buffer + recv_offset)
		{
			uint size = recv_buffer + recv_offset - pos;

			if (size > 0)
				memmove(recv_buffer, pos, size);

			recv_offset = size;
		}
	}
	catch (...)
	{
		throw ERR_Parse;
	}
}

// on message
void LogStream::OnMessage()
{
}



// -----------------------------------------------------------------
// class LogConnection
// -----------------------------------------------------------------
class LogConnection : public TcpConnection, public LogStream
{
public:
	// constructor
	LogConnection();

	// on connected
	void OnConnected();

	// on close
	void OnDisconnected();

	// on message
	void OnMessage();

public:
	sockaddr_in addr;
};

// constructor
LogConnection::LogConnection()
	: LogStream(10 * 1024 * 1024)
{
	stream = this;
	connection = this;
}

// on connected
void LogConnection::OnConnected()
{
	//log_write_sys(LOG_INFO, "log server connected: %s:%d", inet_ntoa(addr.sin_addr), ntohs(addr.sin_port));
	
	send_ident_to_logserver(NULL);
}

static void ReconnectLog(LogConnection * conn)
{
	conn->Connect(conn->addr);
}

// on disconnected
void LogConnection::OnDisconnected()
{
	if (!connecting)
	{
		//log_write_sys(LOG_INFO, "log server disconnected: %s:%d.", inet_ntoa(addr.sin_addr), ntohs(addr.sin_port));
	}

	Event::AddTimer((EventHandler)&ReconnectLog, this, 2);
}

// on message
void LogConnection::OnMessage()
{
	log_write(LOG_DEBUG3, "logconnection on message.");
	
	int message_type = 0;
	try
	{
		ReadInt(message_type);
		switch(message_type)
		{
			case CM_SetLevel:
				{
					int level = 0;
					ReadInt(level);
					log_set_output_level(level);
				}
				break;
			defaule:
				break;
		}
	}
	catch (...)
	{
		log_write_sys(LOG_NOTICE, "LogConnection::OnMessage() error.");
	}
}


// -----------------------------------------------------------------
// log api
// -----------------------------------------------------------------
static LogConnection log_connection;

static void send_ident_to_logserver(void*)
{
	if (strcmp(log_ident, "") == 0)
		return;
	
	bool is_resend = false;
	if (!log_connection.BeginWrite()) is_resend = true;
	if (!log_connection.WriteInt(SM_Ident)) is_resend = true;
	if (!log_connection.WriteString(log_ident)) is_resend = true;
	if (!log_connection.EndWrite()) is_resend = true;
	
	if (is_resend)
		Event::AddTimer((EventHandler)&send_ident_to_logserver, NULL, 0.01);
}

// set log output file.
void log_set_output(const char * device)
{
	strcpy(log_output, "");

	if (device)
	{
		if (strcmp(device, "stdout") == 0)
		{
			log_output_mode = kStdout;
		}
		else if (strchr(device, ':') != NULL)
		{
			if (parse_sockaddr(log_connection.addr, device))
			{
				log_connection.Connect(log_connection.addr);
				log_connection.no_delay = true;
				
				log_output_mode = kLogServer;
			}
			else
			{
				log_write_sys(LOG_NOTICE, "log output device error.");
				
				log_output_mode = kNone;
			}
		}
		else if (strcmp(device, "none") == 0)
		{
			log_output_mode = kNone;
		}
		else
		{
			log_write_sys(LOG_NOTICE, "unknow log output.");
			
			log_output_mode = kNone;
		}
		
		strncpy(log_output, device, sizeof(log_output));
		
		log_write_sys(LOG_INFO, "log output %s.", log_output);
	}
}

// log get output
const char * log_get_output()
{
	return log_output;
}

// set log identifier
void log_set_ident(const char * format, ...)
{
	va_list args;
	va_start(args, format);
	vsnprintf(log_ident, sizeof(log_ident), format, args);
	va_end(args);
	
	send_ident_to_logserver(NULL);
}

// get log identifyer
const char* log_get_ident()
{
	return log_ident;
}

// set log output level
void log_set_output_level(int level)
{
	if (LOG_EMERG <= level && level <= LOG_DEBUG5) 	
		log_output_level = level;
		
	log_write_sys(LOG_INFO, "log_set_output_level %d.", log_output_level);
}

int log_get_output_level() 
{
	return log_output_level;
}

// write syslog
void log_write_sys(int level, const char * format, ...)
{
	if (LOG_EMERG <= level)
	{
		char buff[1024];

		va_list args;
		va_start(args, format);
		vsnprintf(buff, sizeof(buff), format, args);
		va_end(args);
		
		if (level <= LOG_ERROR)
			DumpStack(buff, sizeof(buff), 2);
		
		syslog(level, "[%s] %s", log_level_names[level], buff);
	}
}

// write log
void log_write(int level, const char * format, ...)
{
	if (LOG_EMERG <= level && level <= log_output_level)
	{
		switch(log_output_mode)
		{
			case kLogServer:
				{
					char* send_buffer = log_connection.send_buffer;
					int send_buffer_size = log_connection.send_buffer_size;
					int send_offset = log_connection.send_offset; 
					int head_size = log_connection.LOG_MESSAGE_HEAD_SIZE; 
					char* buffer = send_buffer + send_offset + head_size + sizeof(int) * 3;
					int buffer_size = send_buffer_size - send_offset - head_size - sizeof(int) * 3;
					if (buffer_size > 0) 
					{
						va_list args;
						va_start(args, format);
						int log_size = vsnprintf(buffer, buffer_size, format, args);
						va_end(args);
						if (log_size > 0 && log_size < buffer_size)
						{
							if (level <= LOG_ERROR)
							{
								char* stack_buffer = buffer + log_size;
								int stack_buffer_size = buffer_size - log_size;
								if (stack_buffer_size > 0)
								{
									int stack_string_size = DumpStack(stack_buffer, stack_buffer_size, 2);
									if (stack_string_size < buffer_size) {
										log_size += stack_string_size;
									}
								}
							}	
							int package_size = log_size + head_size + sizeof(int) * 3;
							*(int*)(send_buffer + send_offset) = package_size;
							*(int*)(send_buffer + send_offset + head_size) = SM_Log;
							*(int*)(send_buffer + send_offset + head_size + sizeof(int)) = level;
							*(int*)(send_buffer + send_offset + head_size + sizeof(int) * 2) = log_size;
							log_connection.send_offset += package_size;

							if (log_connection.no_delay && log_connection.connection)
								log_connection.SendMessages();
						}
					}
				}
				break;
			case kStdout:
				{
					char buff[1024];
					va_list args;
					va_start(args, format);
					vsnprintf(buff, sizeof(buff), format, args);
					va_end(args);

					if (level <= LOG_ERROR)
						DumpStack(buff, sizeof(buff), 2);

					char time_buff[64];
					struct timeval time_value;
					if (!gettimeofday(&time_value, NULL)) 
					{
						strftime(time_buff, sizeof(time_buff), "%Y-%m-%d %H:%M:%S", localtime(&time_value.tv_sec));
						sprintf(time_buff, "%s.%03ld", time_buff, time_value.tv_usec / 1000);
					} 
					else
					{
						strcpy(time_buff, "");
					}
					fprintf(stdout, "[%s][%s][%s] %s\n", time_buff, log_ident, log_level_names[level], buff);
				}
				break;
			case kNone:
			default:
				break;
		}
	}
}

// dump buffer
void DumpBuffer(const char *name, const void *buffer, int size)
{
	if (name && buffer && size > 0)
	{
		time_t time_v;
		char time_buff[128];
		char filename[256];
		struct timeval time_value;

		gettimeofday(&time_value, NULL);

		strftime(time_buff, sizeof(time_buff), "%Y-%m-%d %H:%M:%S", localtime(&time_value.tv_sec));
		snprintf(filename, sizeof(filename), "%s_%s_%ld.dump", name, time_buff, time_value.tv_usec);
		filename[sizeof(filename) - 1] = 0;

		FILE * fp = fopen(filename, "wb");
		if (fp)
		{
			fwrite(buffer, size, 1, fp);
			fclose(fp);
		}
	}

	return;
}

// dump stack
static int DumpStack(char *buffer, int max_buffersize, int start_stack)
{
	int size = 0;
	if (buffer)
	{
		void *stack_buffer[64];

		max_buffersize -= strlen(buffer);

		int nptrs = backtrace(stack_buffer, elementsof(stack_buffer));
		char **stack_strings = backtrace_symbols(stack_buffer, nptrs);
		if (stack_strings)
		{
			const char text[] = "\n----- DumpStack -----\n";

			strncat(buffer, text, max_buffersize);
			max_buffersize -= strlen(text);

			for (int i = start_stack; i < nptrs && max_buffersize > 0; i++)
			{
				strncat(buffer, stack_strings[i], max_buffersize);
				strncat(buffer, "\n", max_buffersize);
				int string_size = strlen(stack_strings[i]) + 1;	
				size += string_size;
				max_buffersize -= string_size; 
			}

			free(stack_strings);
		}
	}
	return size;
}

