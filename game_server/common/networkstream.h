#pragma once
#include "event.h"
#include "aes.h"

// network connection 
struct NetworkConnection : public IEvent
{
public:
	// constructor
	NetworkConnection();

	// destructor
	~NetworkConnection();

public:
	// send messages
	virtual void SendMessages() = 0;

	// recieve messages 
	virtual void ReceiveMessages() = 0;
	
	// send messages
	virtual void ForcedSendMessages();

public:
	class NetworkStream * stream;
};

// network encoder
struct NetworkEncoder
{
	// encode
	virtual void Encode(byte * data, uint size) = 0;

	// decode
	virtual void Decode(byte * data, uint size) = 0;
	
	// reset
	virtual void Reset() = 0;
};

// network compressor
struct NetworkCompressor
{
	// encode
	virtual uint Compress(byte * dst, uint dst_size, const byte * src, uint src_size) = 0;

	// decode
	virtual uint Decompress(byte * dst, uint dst_size, const byte * src, uint src_size) = 0;
	
	// reset
	virtual void Reset() = 0;
};

// base network stream
class NetworkStream
{
public:
	// constructor
	NetworkStream(int _buffer_size);

	// destructor
	~NetworkStream();

public:
	// on connected
	virtual void OnConnected();

	// on disconnected
	virtual void OnDisconnected();

	// on parse message
	virtual void OnParseMessage();

public:
	// resize send buffer
	bool SendBufferResize(int new_size);

	// resize recv buffer
	bool RecvBufferResize(int new_size);
	
	// dump send buffer
	void DumpSendBuffer(uint offset);
	
	// dump recv buffer
	void DumpRecvBuffer(uint offset);
	
public:
	static const int MAX_SEND_BUFFER_SIZE = 2 * 1024 * 1024;
	static const int MAX_RECV_BUFFER_SIZE = 2 * 1024 * 1024;
	
public:
	// buffer
	int send_buffer_size;
	int recv_buffer_size;
	char *send_buffer;
	char *recv_buffer;
	uint send_offset;
	uint recv_offset;

	// flags
	bool no_delay;
	bool writeerr;

	NetworkConnection * connection;
};

// packet based binary stream
class BinaryStream : public NetworkStream
{
public:
	// read
	void Read(void* data, int size);
	void ReadIntArray(int* data, int count);
	void ReadInt(int& data);
	void ReadInt(uint& data);
	void ReadByte(byte& data);
	void ReadByte(char& data);
	void ReadShort(short& data);
	void ReadShort(ushort& data);
	void ReadFloatArray(float* data, int count);
	void ReadFloat(float& data);
	void ReadVector2(Vector2 & data);
	void ReadVector3(Vector3 & data);
	void ReadQuaternion(Quaternion & data);
	int  ReadString(char* s, int size);
	void * ReadData(int size);

	// write
	void BeginWrite();
	void Write(const void* data, int size);
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
	void WriteVector2(const Vector2 & data);
	void WriteVector3(const Vector3 & data);
	void WriteQuaternion(const Quaternion & data);
	void WriteString(const char * data);
	void WriteStringf(const char * format, ...);
	void WriteStringArgs(const char * format, va_list args);
	void EndWrite();
	
	void CancelWrite();

public:
	// constructor
	BinaryStream(int _buffer_size);

	// destructor
	~BinaryStream();

	// on message
	virtual void OnMessage();

	// on parse message
	virtual void OnParseMessage();
	
public:
	static const int MAGICNUM_SIZE = 2;
	static const int PKGLENGHT_SIZE = 2;
	static const int HEAD_SIZE = MAGICNUM_SIZE + PKGLENGHT_SIZE;
	static const ushort MAGICNUM_MASK = 0x7FFF;
	static const ushort MAGICNUM = (('B') | (('S') << 8)) & MAGICNUM_MASK;
	
	// 这个跟默认缓存大小相同 不修改默认缓存大小 这个也不改变
	static const int MAX_PKGSIZE = 64 * 1024;

public:
	char* write_position;
	char* write_end;
	char* read_position;
	char* read_end;

	NetworkEncoder * encoder;
	NetworkCompressor * compressor;
};

// normal text stream
class TextStream : public NetworkStream
{
public:
	// write
	void BeginWrite();
	void WriteString(const char * data);
	void WriteStringf(const char * format, ...);
	void EndWrite();

	// read
	char* ReadLine();

public:
	// constructor
	TextStream();

	// on message
	virtual void OnMessage();

private:
	// on parse message
	void OnParseMessage();

public:
	char* write_position;
	char* write_end;
	char* read_position;
	char* read_end;
};

#define XL_UNCOMPRESSBUFFER_SIZE 512
class XLNetworkStream : public NetworkStream
{
public:
	// write
	void BeginWrite();

	// for login connection
	void WriteString(const char * data, int len);
	// for xl fcm connection
	void WriteString(const char* data);
	
	void WriteShort(short data);
	void WriteShort(unsigned short data);

	void WriteInt(int data);
	void WriteInt(uint data);

	void WriteByte(byte data);

	void WriteHead(const char * data);
	void WriteVersion(const char * data);

	void Write(const void* data, int size);

	void Read(void* data, int size);
	void ReadString(char* s, int sl, int l);

	void ReadInt(int& data);
	void ReadInt(uint& data);
	void ReadByte(byte& data);
	void ReadByte(char& data);
	void ReadShort(short& data);
	void ReadShort(ushort& data);

	void * ReadData(int size);
	
	void EndWrite();

	void BeginAesCompress();
	void EndAesCompress();

	void AesUnCompressStart();

	void AesUnCompressEnd();

	// on message
	virtual void OnMessage();



	// constructor
	XLNetworkStream();

private:
	// on parse message
	void OnParseMessage();

public:
	char* write_position;
	char* write_compress;

	char* read_position;
	char* read_end;


	char uncompress_buffer[XL_UNCOMPRESSBUFFER_SIZE];
	int	frame_length;
	char* uncompress_position;

	bool uncompress_flag;

	aes_ctx  aes;
};


class XLFCMNetworkStream : public NetworkStream
{
public:
	// write
	void BeginWrite();

	// for login connection
	void WriteString(const char * data, int len);
	// for xl fcm connection
	void WriteString(const char* data);

	void WriteShort(short data);
	void WriteShort(unsigned short data);

	void WriteInt(int data);
	void WriteInt(uint data);

	void WriteByte(byte data);

	void WriteHead(const char * data);
	void WriteVersion(const char * data);

	void Write(const void* data, int size);

	void Read(void* data, int size);
	void ReadString(char* s, int sl, int l);

	void ReadInt(int& data);
	void ReadInt(uint& data);
	void ReadByte(byte& data);
	void ReadByte(char& data);
	void ReadShort(short& data);
	void ReadShort(ushort& data);

	void * ReadData(int size);

	void EndWrite();

	// on message
	virtual void OnMessage();



	// constructor
	XLFCMNetworkStream();

private:
	// on parse message
	void OnParseMessage();

public:
	char* write_position;

	char* read_position;
	char* read_end;

	aes_ctx  aes;
};

// packet based fwg stream
class XLFWGStream : public NetworkStream
{
public:
	// read
	void Read(void* data, int size);
	void ReadInt(int& data);
	void ReadInt(uint& data);
	int  ReadString(char* s, int size);
	void * ReadData(int size);

	// write
	void BeginWrite();
	void Write(const void* data, int size);
	void WriteInt(int data);
	void WriteInt(uint data);
	void WriteString(const char * data);
	void WriteStringf(const char * format, ...);
	void WriteStringArgs(const char * format, va_list args);
	void EndWrite();

	void CancelWrite();

public:
	// constructor
	XLFWGStream();

	// destructor
	~XLFWGStream();

	// on message
	virtual void OnMessage();

	// on parse message
	virtual void OnParseMessage();

public:
	char* write_position;
	char* write_end;
	char* read_position;
	char* read_end;

	NetworkEncoder * encoder;
	NetworkCompressor * compressor;
};
