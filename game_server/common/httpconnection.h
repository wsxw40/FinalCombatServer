#pragma once
#include "event.h"


// http connection
class HttpConnection
{
public:
	enum RequestPool
	{
		kServerPool,
		kClientPool,
		kLoginPool,
		kLastPool,
	};

public:
	// constructor
	HttpConnection();

	// destructor
	virtual ~HttpConnection();

	// release
	virtual void Release();

	// write post
	void Get(const char * url);
	void BeginTextPost(const char * url);
	void BeginBinaryPost(const char * url);

	void WriteParameter(const char * key, const char * data, int size);
	void WriteParameterf(const char * key, const char * format, ...);

	void WriteData(const void* data, int size);
	void WriteIntArray(int* data, int count);
	void WriteInt(int data);
	void WriteInt(uint data);
	void WriteByte(byte data);
	void WriteShort(short data);
	void WriteShort(ushort data);
	void WriteFloatArray(float* data, int count);
	void WriteDoubleArray(double* data, int count);
	void WriteFloat(float data);
	void WriteDouble(double data);
	void WriteVector3(const Vector3 & data);
	void WriteQuaternion(const Quaternion & data);
	void WriteString(const char * data);
	void WriteStringf(const char * format, ...);

	// end post
	void EndPost();

	// read
	void Read(void* data, int size);
	void ReadInt(int& data);
	void ReadInt(uint& data);
	void ReadByte(byte& data);
	void ReadShort(short& data);
	void ReadShort(ushort& data);
	void ReadFloatArray(float* data, int count);
	void ReadFloat(float& data);
	void ReadVector3(Vector3 & data);
	void ReadQuaternion(Quaternion & data);
	int  ReadString(char* s, int size);

public:
	// initialize
	static bool Initialize(int connections, sockaddr_in address[], int address_count);

	// terminate
	static void Terminate();

private:
	void Write(const char * data);
	void Writef(const char * format, ...);
	void WriteURL(const char * data, size_t len);
	void Send();

public:
	// on response
	virtual void OnResponse();

public:
	char request_url[256];
	char request_buffer[1024 * 4];
	char response_buffer[1024 * 16];
	char* request_position;
	char* request_end;
	char* response_position;
	char* response_end;

	uint error;
	bool cancel;

	double process_time;
	double response_time;

	RequestPool pool;
};

