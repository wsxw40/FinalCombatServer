#include "common.h"
#include "httpconnection.h"
#include "objectpool.h"
#include "log.h"
#include "event.h"

#include <stdio.h>
#include <stdarg.h>
#include <deque>
#include <assert.h>

// -----------------------------------------------------------------
// Reading functions
// -----------------------------------------------------------------
void HttpConnection::Read(void* data, int size)
{
	if (response_position < response_buffer || response_position + size > response_end)
		throw ERR_Read;

	memcpy(data, response_position, size);
	response_position += size;
}

void HttpConnection::ReadInt(int& data)
{
	Read(&data, sizeof(int));
}

void HttpConnection::ReadInt(uint& data)
{
	Read(&data, sizeof(uint));
}

void HttpConnection::ReadByte(byte& data)
{
	Read(&data, sizeof(byte));
}

void HttpConnection::ReadShort(short& data)
{
	Read(&data, 2);
}

void HttpConnection::ReadShort(ushort& data)
{
	Read(&data, 2);
}

void HttpConnection::ReadVector3(Vector3 & data)
{
	Read(&data, sizeof(float) * 3);
}

void HttpConnection::ReadQuaternion(Quaternion & data)
{
	Read(&data, sizeof(float) * 4);
}

void HttpConnection::ReadFloat(float& data)
{
	Read(&data, sizeof(float));
}

int HttpConnection::ReadString(char* s, int size)
{
	int l;
	ReadInt(l);

	if (size <= l)
		throw ERR_Read;

	s[l] = 0;
	Read(s, l);
	return l;
}

// -----------------------------------------------------------------
// Writing functions
// -----------------------------------------------------------------
void HttpConnection::WriteData(const void * data, int size)
{
	if (request_end)
	{
		if (request_end + size < request_buffer + sizeof(request_buffer))
		{
			memcpy(request_end, data, size);
			request_end += size;
		}
		else
		{
			request_end = NULL;
		}
	}
}

void HttpConnection::WriteIntArray(int* data, int count)
{
	WriteData(data, sizeof(int) * count);
}

void HttpConnection::WriteInt(int data)
{
	WriteData(&data, sizeof(int));
}

void HttpConnection::WriteInt(uint data)
{
	WriteData(&data, sizeof(uint));
}

void HttpConnection::WriteByte(byte data)
{
	WriteData(&data, 1);
}

void HttpConnection::WriteShort(short data)
{
	WriteData(&data, 2);
}

void HttpConnection::WriteShort(ushort data)
{
	WriteData(&data, 2);
}

void HttpConnection::WriteVector3(const Vector3 & data)
{
	WriteData(&data, sizeof(float) * 3);
}

void HttpConnection::WriteQuaternion(const Quaternion & data)
{
	WriteData(&data, sizeof(float) * 4);
}

void HttpConnection::WriteFloatArray(float* data, int count)
{
	WriteData(data, sizeof(float) * count);
}

void HttpConnection::WriteDoubleArray(double* data, int count)
{
	WriteData(data, sizeof(double) * count);
}

void HttpConnection::WriteFloat(float data)
{
	WriteFloatArray(&data, 1);
}

void HttpConnection::WriteDouble(double data)
{
	WriteDoubleArray(&data, 1);
}


void HttpConnection::WriteString(const char* data)
{
	int len = strlen(data);
	WriteInt(len);
	WriteData(data, len);
}

void HttpConnection::WriteStringf(const char * format, ...)
{
	if (request_end)
	{
		va_list args;
		va_start(args, format);
		int left = request_buffer + sizeof(request_buffer) - request_end - 4;
		int size = vsnprintf(request_end + 4, left, format, args);
		va_end(args);

		if (size < left)
		{
			memcpy(request_end, &size, sizeof(int));
			request_end += 4 + size;
		}
		else
		{
			request_end = NULL;
		}
	}
}

// write
void HttpConnection::Write(const char * data)
{
	WriteData(data, strlen(data));
}

// write format
void HttpConnection::Writef(const char * format, ...)
{
	if (request_end)
	{
		va_list args;
		va_start(args, format);
		int left = request_buffer + sizeof(request_buffer) - request_end; 
		int len = vsnprintf(request_end, left, format, args);

		if (len < left)
		{
			request_end += len;
		}
		else
		{
			request_end = NULL;
		}
	}
}

static u_char   hex[] = "0123456789abcdef";

/* " ", "#", "%", "?", %00-%1F, %7F-%FF */

static uint32_t   uri[] = {
	0xffffffff, /* 1111 1111 1111 1111  1111 1111 1111 1111 */

				/* ?>=< ;:98 7654 3210  /.-, +*)( '&%$ #"!  */
	0xfc009fff, /* 1111 1100 0000 0000  1001 1111 1111 1111 */

				/* _^]\ [ZYX WVUT SRQP  ONML KJIH GFED CBA@ */
	0x78000001, /* 0111 1000 0000 0000  0000 0000 0000 0001 */

				/*  ~}| {zyx wvut srqp  onml kjih gfed cba` */
	0xB0000001, /* 1011 1000 0000 0000  0000 0000 0000 0001 */

	0xffffffff, /* 1111 1111 1111 1111  1111 1111 1111 1111 */
	0xffffffff, /* 1111 1111 1111 1111  1111 1111 1111 1111 */
	0xffffffff, /* 1111 1111 1111 1111  1111 1111 1111 1111 */
	0xffffffff  /* 1111 1111 1111 1111  1111 1111 1111 1111 */
};

void HttpConnection::WriteURL(const char * data, size_t len)
{
	if (request_end)
	{
		for (size_t i = 0; i < len; i ++)
		{
			byte src = (byte)*data;
			if (uri[src >> 5] & (1 << (src & 0x1f)))
			{
				if (request_end + 3 >= request_buffer + sizeof(request_buffer))
				{
					request_end = NULL;
					return;
				}

				*request_end++ = '%';
				*request_end++ = hex[src >> 4];
				*request_end++ = hex[src & 0xf];
				data++;
			}
			else
			{
				if (request_end + 1 >= request_buffer + sizeof(request_buffer))
				{
					request_end = NULL;
					return;
				}

				*request_end++ = *data++;
			}
		}
	}
}


void HttpConnection::WriteParameter(const char * key, const char * data, int size)
{
	if (request_end)
	{
		if (request_end > request_position)
			WriteByte('&');

		WriteURL(key, strlen(key));
		WriteByte('=');
		WriteURL(data, size);
	}
}

void HttpConnection::WriteParameterf(const char * key, const char * format, ...)
{
	char buff[4096];
	va_list args;
	va_start(args, format);
	int len = vsnprintf(buff, 4906, format, args);
	va_end(args);
	WriteParameter(key, buff, len);
}

// -----------------------------------------------------------------
// Http connection
// -----------------------------------------------------------------
// constructor
HttpConnection::HttpConnection()
	: cancel(false)
	, pool(kServerPool)
{
	assert(pool < kLastPool);

	request_position = NULL;
	request_end = NULL;
	response_position = response_buffer;
	response_end = response_buffer;

	error = 0;
}

// destructor
HttpConnection::~HttpConnection()
{
}

// release
void HttpConnection::Release()
{
	delete this;
}

void HttpConnection::Get(const char * url)
{
	strncpy(request_url, url, sizeof(request_url));
	log_write(LOG_DEBUG5, "http get : %s", url);

	request_end = request_buffer + snprintf(request_buffer, sizeof(request_buffer),
			"GET %s HTTP/1.1\r\n"
			"Host: 127.0.0.1\r\n"
			"\r\n", url);

	Send();
}

void HttpConnection::BeginTextPost(const char * url)
{
	strncpy(request_url, url, sizeof(request_url));
	log_write(LOG_DEBUG5, "http post : %s", url);

	request_end = request_buffer + snprintf(request_buffer, sizeof(request_buffer),
			"POST %s HTTP/1.1\r\n"
			"Host: 127.0.0.1\r\n"
			"Content-Type: application/x-www-form-urlencoded\r\n"
			"Content-Length:       \r\n\r\n", url);

	request_position = request_end;
}

void HttpConnection::BeginBinaryPost(const char * url)
{
	strncpy(request_url, url, sizeof(request_url));
	log_write(LOG_DEBUG5, "http post : %s", url);

	request_end = request_buffer + snprintf(request_buffer, sizeof(request_buffer),
			"POST %s HTTP/1.1\r\n"
			"Host: 127.0.0.1\r\n"
			"Content-Type: application/octet-stream\r\n"
			"Content-Length:       \r\n\r\n", url);

	request_position = request_end;
}

// connect
void HttpConnection::EndPost()
{
	// write content length
	int size = snprintf(request_position - 10, 6, "%d", request_end - request_position);
	request_position[size - 10] = ' ';

	// send 
	Send();
}

void HttpConnection::OnResponse()
{
}



//------------------------------------------------------------------
// HttpConnectionManager
//------------------------------------------------------------------
class HttpConnectionManager;

class HttpConnectionCache : public IEvent
{
public:
	enum State
	{
		kIdle,
		kConnecting,
		kWorking,
	};

	enum ParseState
	{
		kParseBegin,
		kParseHeader,
		kParseContent,
		kParseEnd,
	};

public:
	// constructor
	HttpConnectionCache();

	// on read
	void OnRead();

	// on write
	void OnWrite();

	// on close
	void OnClose();

	// parse response
	void OnParseResponse();

	// response
	void OnResponse();

	// process
	void Process(HttpConnection * http);

public:
	uint uid;
	HttpConnection * request;

	sockaddr_in address;
	SOCKET connected_socket;

	char* request_position;
	char* request_end;
	char* response_position;
	char* response_end;
	char* parse_position;

	char* write_start;
	char* write_position;

	bool writable;
	uint state;
	uint error;
	uint parse_state;

	bool content_chunked;
	int content_length;

	HttpConnectionManager * manager;
};

// constructor
HttpConnectionCache::HttpConnectionCache()
	: request(NULL)
	, connected_socket(INVALID_SOCKET)
	, writable(false)
	, state(kIdle)
	, error(0)
	, parse_state(kParseEnd)
{
}

// on read
void HttpConnectionCache::OnRead()
{
	int n;
	int err;

	try
	{
		while (true)
		{
			n = recv(connected_socket, response_position, response_end - response_position, 0);

			if (n == -1)
			{
				err = errno;

				if (err != EAGAIN && err != EINTR)
					throw ERR_Socket;

				break;
			}
			else if (n == 0)
			{
				throw ERR_Closed;
			}
			else
			{
				response_position += n;
				OnParseResponse();
			}
		}
	}
	catch (...)
	{
		OnClose();
	}
}

// on write
void HttpConnectionCache::OnWrite()
{
	if (state == kConnecting)
	{
		int err = 0;
		socklen_t len = sizeof(err);
		if (getsockopt(connected_socket, SOL_SOCKET, SO_ERROR, &err, &len))
		{
			OnClose();
			return;
		}
		else
		{
			if (err == 0)
			{
				state = kWorking;
			}
			else
			{
				OnClose();
				return;
			}
		}
	}

	writable = true;

	try
	{
		while (writable && request_position < request_end)
		{
			// send as much data as we can.
			int n = send(connected_socket, request_position, request_end - request_position, 0);

			if (n == -1)
			{
				int err = errno;

				if (err == EAGAIN)
				{
					writable = false;
					break;
				}
				else
				{
					throw ERR_Socket;
				}
			}

			if (n == 0)
				break;

			request_position += n;
		}
	}
	catch (...)
	{
		OnClose();
	}
}

// on close
void HttpConnectionCache::OnClose()
{
	if (connected_socket != INVALID_SOCKET)
	{
		log_write(LOG_DEBUG5, "http connection close");
		Event::RemoveSocket(connected_socket);
		close(connected_socket);
		connected_socket = INVALID_SOCKET;
	}

	OnParseResponse();
	OnResponse();
}

// parse response
void HttpConnectionCache::OnParseResponse()
{
	if (response_position <= parse_position)
		return;

	switch (parse_state)
	{
	case kParseBegin:
		{
			// scan http version
			sscanf(parse_position, "HTTP/1.1 %d", &error);

			// HTTP 200 OK
			if (error != 200)
			{ 
				if (error == 0)
					error = 1;

				OnResponse();
				return;
			}
			else
			{
				error = 0;
				content_chunked = false;
				content_length = -1;
				parse_state = kParseHeader;
			}
		}

	case kParseHeader:
		for(;;)
		{
			// parse http response
			char * line_start = parse_position;
			char * line_end = line_start;

			// search line end
			for (;;)
			{
				if (line_end + 1 >= response_position)
					return;

				if (line_end[0] == '\r' && line_end[1] == '\n')
					break;

				line_end ++;
			}

			// empty line, header end
			if (line_end == line_start)
			{
				parse_position = line_end + 2;
				parse_state = kParseContent;
				write_position = write_start;
				break;
			}
			else
			{
				// finishe string.
				line_end[0] = 0;

				// split into key-value pair
				char * value_start = strchr(line_start, ':');

				if (value_start)
				{
					value_start[0] = 0;
					value_start++;
					while (value_start < line_end && value_start[0] == ' ')
						value_start++;

					if (strcasecmp(line_start, "transfer-encoding") == 0)
					{
						if (strcasecmp(value_start, "chunked") == 0)
							content_chunked = true;
					}
					else if (strcasecmp(line_start, "content-length") == 0)
					{
						content_length = atoi(value_start);
					}
				}

				// next line
				parse_position = line_end + 2;
			}
		}

	case kParseContent:
		if (content_chunked)
		{
			for(;;)
			{
				char * rpos = parse_position;
				char * wpos = write_position;

				uint len = 0;

				// parse chunk size
				for(;;)
				{
					if  (rpos + 1 >= response_position)
						return;

					if (rpos[0] == '\r' && rpos[1] == '\n')
						break;

					switch (rpos[0])
					{
					case '0'...'9':	len = (len << 4) | (rpos[0] - '0'); break;
					case 'a'...'f': len = (len << 4) | (rpos[0] - 'a' + 10); break;
					case 'A'...'F': len = (len << 4) | (rpos[0] - 'A' + 10); break;
					}

					rpos ++;
				}

				// skip line end
				rpos += 2;

				// content end
				if (len == 0)
				{
					parse_state = kParseEnd;
					OnResponse();
					return;
				}

				// continue parse
				if (rpos + len < response_position - 2)
				{
					memcpy(wpos, rpos, len);
					parse_position = rpos + len + 2;
					write_position = wpos + len;
					continue;
				}

				// data imcomplete, parse later.
				return;
			}
		}
		else
		{
			int size = response_position - parse_position;

			if (size > 0)
			{
				memmove(write_position, parse_position, size);
				write_position += size;
				parse_position += size;
			}

			if (content_length >= 0)
			{
				if (write_position - write_start >= content_length)
				{
					parse_state = kParseEnd;
					OnResponse();
					return;
				}
			}
		}
	}
}

// process
void HttpConnectionCache::Process(HttpConnection * http)
{
	if (request)
	{
		log_write(LOG_ERROR, "http process error.");
		return;
	}

	http->response_time = Event::GetTime();

	request = http;
	request_position = http->request_buffer;
	request_end = http->request_end;
	response_position = http->response_buffer;
	response_end = http->response_buffer + sizeof(http->response_buffer);
	parse_position = http->response_buffer;
	write_start = http->response_buffer;
	write_position = http->response_buffer;

	error = 1;
	parse_state = kParseBegin;

	if (connected_socket == INVALID_SOCKET)
	{
		log_write(LOG_DEBUG5, "http connection connect");
		connected_socket = socket(AF_INET, SOCK_STREAM, 0);
		fcntl(connected_socket, F_SETFL, fcntl(connected_socket, F_GETFL) | O_NONBLOCK);

		connect(connected_socket, (sockaddr*)&address, sizeof(address));

		if (EINPROGRESS == errno)
		{
			state = kConnecting;

			if (!Event::AddSocket(connected_socket, this, true, true))
			{
				OnClose();
			}
		}
		else
		{
			OnClose();
		}
	}
	else
	{
		if (writable)
			OnWrite();
	}
}

//------------------------------------------------------------------
// HttpConnectionManager
//------------------------------------------------------------------
class HttpConnectionManager
{
public:
	// update
	void Update();

	// finish request
	void ProcessFinished(HttpConnectionCache * cache, HttpConnection * request);

public:
	// waiting queue.
	std::deque<HttpConnection*> waiting_queue;

	// connection pool
	ObjectPool<HttpConnectionCache> connection_pool;
};

// global connection manager
HttpConnectionManager managers[HttpConnection::kLastPool];

// add request
void HttpConnectionManager::Update()
{
	while (!waiting_queue.empty())
	{
		HttpConnection * request = waiting_queue.front();

		if (request->cancel)
		{
			waiting_queue.pop_front();
			continue;
		}

		HttpConnectionCache * cache = connection_pool.Allocate();

		if (!cache)
			return;

		waiting_queue.pop_front();
		cache->Process(request);
	}
}

// finish request
void HttpConnectionManager::ProcessFinished(HttpConnectionCache * cache, HttpConnection * request)
{
	connection_pool.Free(cache->uid);
	try
	{
		log_write(request->error ? LOG_ERROR : LOG_DEBUG5, "http ret : %s, result: %d, size=%d, time=%dms", 
				request->request_url, request->error, request->response_end - request->response_position, (int)(request->process_time * 1000));
		request->OnResponse();
	}
	catch (...)
	{
		log_write(LOG_ERROR, "http parse error.");
	}
	request->Release();
	Update();
}

// response
void HttpConnectionCache::OnResponse()
{
	if (request)
	{
		request->error = error;

		if (error)
		{
			request->response_position = request->response_buffer;
			request->response_end = request->response_buffer;
		}
		else
		{
			request->response_position = write_start;
			request->response_end = write_position;
		}

		parse_state = kParseEnd;

		request_position = 0;
		request_end = 0;
		response_position = 0;
		response_end = 0;
		parse_position = 0;
		write_start = 0;
		write_position = 0;

		HttpConnection * http = request;
		request = NULL;

		double time = Event::GetTime();
		http->process_time = time - http->process_time;
		http->response_time = time - http->response_time;

		manager->ProcessFinished(this, http);
	}
}

//------------------------------------------------------------------
// HttpConnection
//------------------------------------------------------------------
// initialize
bool HttpConnection::Initialize(int connections, sockaddr_in address[], int address_count)
{
	for (int pool = 0; pool < kLastPool; pool++)
	{
		if (!managers[pool].connection_pool.Initialize(connections))
			return false;

		for (int i = 0; i < connections; i ++)
		{
			HttpConnectionCache & cache = managers[pool].connection_pool[i];
			cache.address = address[i % address_count];
			cache.manager = &managers[pool];
		}
	}

	return true;
}

// terminate
void HttpConnection::Terminate()
{
	for (int i = 0; i < kLastPool; i++)
	{
		HttpConnectionManager & manager = managers[i];

		for (std::deque<HttpConnection*>::iterator i = manager.waiting_queue.begin(); i != manager.waiting_queue.end(); ++i)
			(*i)->Release();

		manager.waiting_queue.clear();
		manager.connection_pool.Terminate();
	}
}

void HttpConnection::Send()
{
	request_position = request_buffer;
	response_position = response_buffer;
	response_end = response_position;
	response_buffer[0] = 0;

	process_time = Event::GetTime();

	managers[pool].waiting_queue.push_back(this);
	managers[pool].Update();
}

