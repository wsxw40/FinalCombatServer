#include "networkstream.h"
#include "log.h"
#include <stdarg.h>
#include <time.h>

// -----------------------------------------------------------------
//  network connection
// -----------------------------------------------------------------
// constructor
NetworkConnection::NetworkConnection()
	: stream(NULL)
{
}

// destructor
NetworkConnection::~NetworkConnection()
{
	if (stream)
		stream->connection = NULL;
}

// ForcedSendMessages
void NetworkConnection::ForcedSendMessages()
{
	SendMessages();
}


// -----------------------------------------------------------------
//  network stream
// -----------------------------------------------------------------
	// constructor
NetworkStream::NetworkStream(int _buffer_size)
	: send_buffer_size(_buffer_size)
	, recv_buffer_size(_buffer_size)
	, send_buffer(NULL)
	, recv_buffer(NULL)
	, send_offset(0)
	, recv_offset(0)
	, no_delay(true)
	, writeerr(false)
	, connection(NULL)
{
	if (send_buffer_size > 0)
		send_buffer = new char[send_buffer_size];
	
	if (recv_buffer_size > 0)
		recv_buffer = new char[recv_buffer_size];
}

	// destructor
NetworkStream::~NetworkStream()
{
	if (send_buffer)
	{
		delete [] send_buffer;
		send_buffer = NULL;
	}
	
	if (recv_buffer)
	{
		delete [] recv_buffer;
		recv_buffer = NULL;
	}
	
	if (connection)
		connection->stream = NULL;
}

// on connected
void NetworkStream::OnConnected()
{
}

// on disconnected
void NetworkStream::OnDisconnected()
{
}

// on parse message
void NetworkStream::OnParseMessage()
{
	recv_offset = 0;
}

// resize send buffer
bool NetworkStream::SendBufferResize(int new_size)
{
	if (new_size <= 0)
		new_size = 1024;
		
	if (new_size > MAX_SEND_BUFFER_SIZE)
		new_size = MAX_SEND_BUFFER_SIZE;
		
	log_write(LOG_INFO, "send buffer resize: old_size = %d, new_size = %d", send_buffer_size, new_size);

	if (send_buffer_size < new_size)
	{
		char* buffer = new char[new_size];

		if (buffer == NULL)
			return false;

		log_write(LOG_INFO, "send buffer resize succeed");
			
		memcpy(buffer, send_buffer, send_buffer_size);
		delete[] send_buffer;
		send_buffer = buffer;
		send_buffer_size = new_size;
		
		return true;
	}
	
	log_write(LOG_INFO, "send buffer resize failed");

	return false;
}

// resize recv buffer
bool NetworkStream::RecvBufferResize(int new_size)
{
	if (new_size <= 0)
		new_size = 1024;

	if (new_size > MAX_SEND_BUFFER_SIZE)
		new_size = MAX_SEND_BUFFER_SIZE;
		
	log_write(LOG_INFO, "recv buffer resize: old_size = %d, new_size = %d", recv_buffer_size, new_size);

	if (recv_buffer_size < new_size)
	{
		char* buffer = new char[new_size];

		if (buffer == NULL)
			return false;

		log_write(LOG_INFO, "recv buffer resize succeed");
			
		memcpy(buffer, recv_buffer, recv_buffer_size);
		delete[] recv_buffer;
		recv_buffer = buffer;
		recv_buffer_size = new_size;
		
		return true;
	}
	
	log_write(LOG_INFO, "recv buffer resize failed");

	return false;
}

// dump send buffer
void NetworkStream::DumpSendBuffer(uint offset)
{
	char szBuffer[64];
	const char *ident = log_get_ident();
	
	snprintf(szBuffer, sizeof(szBuffer), "%s-BS_Send(%d)", ident ? ident : "Unknow", offset);
	szBuffer[sizeof(szBuffer) - 1] = 0;
	DumpBuffer(szBuffer, send_buffer, send_buffer_size);
}

// dump recv buffer
void NetworkStream::DumpRecvBuffer(uint offset)
{
	char szBuffer[64];
	const char *ident = log_get_ident();
	
	snprintf(szBuffer, sizeof(szBuffer), "%s-BS_Recv(%d)", ident ? ident : "Unknow", offset);
	szBuffer[sizeof(szBuffer) - 1] = 0;
	DumpBuffer(szBuffer, recv_buffer, recv_offset);
}

// -----------------------------------------------------------------
// BinaryStream reading functions
// -----------------------------------------------------------------
void BinaryStream::Read(void* data, int size)
{
	if (read_position + size > read_end)
	{
		log_write(LOG_ERROR, "%s, %s, read_position : %d, read_end : %d, size : %d", __FILE__, __FUNCTION__, read_position, read_end, size);
		assert(0);
		throw ERR_Read;
	}

	memcpy(data, read_position, size);
	read_position += size;
}

void * BinaryStream::ReadData(int size)
{
	if (read_position + size > read_end)
	{
		log_write(LOG_ERROR, "%s, %s, read_position : %d, read_end : %d, size : %d", __FILE__, __FUNCTION__, read_position, read_end, size);
		assert(0);
		throw ERR_Read;
	}

	read_position += size;

	return read_position - size;
}

void BinaryStream::ReadIntArray(int* data, int count)
{
	Read(data, sizeof(int) * count);
}

void BinaryStream::ReadInt(int& data)
{
	Read(&data, sizeof(int));
}

void BinaryStream::ReadInt(uint& data)
{
	Read(&data, sizeof(uint));
}

void BinaryStream::ReadByte(byte& data)
{
	Read(&data, sizeof(byte));
}

void BinaryStream::ReadByte(char& data)
{
	Read(&data, sizeof(char));
}

void BinaryStream::ReadShort(short& data)
{
	Read(&data, 2);
}

void BinaryStream::ReadShort(ushort& data)
{
	Read(&data, 2);
}

void BinaryStream::ReadVector2(Vector2 & data)
{
	Read(&data, sizeof(float) * 2);
}

void BinaryStream::ReadVector3(Vector3 & data)
{
	Read(&data, sizeof(float) * 3);
}

void BinaryStream::ReadQuaternion(Quaternion & data)
{
	Read(&data, sizeof(float) * 4);
}
void BinaryStream::ReadFloatArray(float* data, int count)
{
	Read(data, sizeof(float) * count);
}

void BinaryStream::ReadFloat(float& data)
{
	ReadFloatArray(&data, 1);
}

int BinaryStream::ReadString(char* s, int size)
{
	uint l;
	ReadInt(l);

	if (size <= l)
	{
		log_write(LOG_ERROR, "read string len error: len = %d, size = %d", l, size);
		assert(0);
		throw ERR_Read;
	}

	s[l] = 0;
	Read(s, l);
	return l;
}

// -----------------------------------------------------------------
// BinaryStream writing functions
// -----------------------------------------------------------------
void BinaryStream::BeginWrite()
{
	if (write_position)
	{
		log_write(LOG_ERROR, "BinaryStream::BeginWrite() : write_position is not null");
		
		CancelWrite();
	}
	
	if (send_offset + HEAD_SIZE >= send_buffer_size)
		SendBufferResize(2 * send_buffer_size);
	
	if (send_offset + HEAD_SIZE < send_buffer_size)
	{
		write_position = send_buffer + send_offset + HEAD_SIZE;
	}
	else
	{
		write_position = NULL;
		log_write(LOG_ERROR, "BinaryStream::BeginWrite() : buffer overflow[%d, %d]", send_offset, send_buffer_size);
		assert(0);
	}
	
	return;
}

void BinaryStream::EndWrite()
{
	if (write_position)
	{
		int size = write_position - send_buffer - send_offset;

		if (size <= 0)
		{
			log_write(LOG_ERROR, "BinaryStream::EndWrite(), pkg size is zero!!!, size[%d]", size);
			
			write_position = NULL;
			writeerr = true;
			if (connection)
				connection->OnClose();
			
			return;
		}
		
		if (size >= MAX_PKGSIZE)
		{
			log_write(LOG_ERROR, "BinaryStream::EndWrite(), pkg too big!!!, size[%d]", size);
			
			write_position = NULL;
			writeerr = true;
			if (connection)
				connection->OnClose();
			
			return;
		}

		if (compressor)
		{
			char * src = send_buffer + send_offset + HEAD_SIZE;
			char * dst = write_position;
			uint dst_size = send_buffer + send_buffer_size - dst;
			size = compressor->Compress((byte*)dst, dst_size, (byte*)src, size - HEAD_SIZE);

			// no buffer to compress
			if (size == 0)
			{
				writeerr = true;
				if (connection)
					connection->OnClose();
				
				return;
			}
			
			size += HEAD_SIZE;

			// copy compressed buffer to original buffer
			memmove(src, dst, size); 
		}

		if (encoder)
			encoder->Encode((byte*)send_buffer + send_offset + HEAD_SIZE, size - HEAD_SIZE);

		*(ushort*)(send_buffer + send_offset) = MAGICNUM;
		*(ushort*)(send_buffer + send_offset + MAGICNUM_SIZE) = size;
		send_offset += size;
		write_position = NULL;

		if (no_delay && connection)
			connection->SendMessages();
	}
	else
	{
		log_write(LOG_ERROR, "BinaryStream::EndWrite() : write_position is null");
		
		writeerr = true;
		if (connection)
			connection->OnClose();
	}
}

void BinaryStream::CancelWrite()
{
	if (write_position)
	{
		log_write(LOG_DEBUG1, "BinaryStream::CancelWrite()");
		
		write_position = NULL;
	}
}

void BinaryStream::Write(const void* data, int size)
{
	if (write_position)
	{
		if (write_position + size >= send_buffer + send_buffer_size)
		{
			int size_need = write_position + size - send_buffer - send_buffer_size;
			int grow = 2 + size_need / send_buffer_size;

			int write_offset = write_position - send_buffer;
			SendBufferResize(grow * send_buffer_size);
			write_position = send_buffer + write_offset;
		}
	
		if (write_position + size < send_buffer + send_buffer_size)
		{
			memcpy(write_position, data, size);
			write_position += size;
		}
		else
		{
			log_write(LOG_ERROR, "BinaryStream::Write() : buffer overflow[%d, %d, %d]", 
				send_buffer + send_buffer_size - write_position, size, send_buffer_size);
			write_position = NULL;
			assert(0);
		}
		
		return;
	}
	
	log_write(LOG_ERROR, "BinaryStream::Write() : write_position is null");
}

void BinaryStream::WriteIntArray(int* data, int count)
{
	Write(data, sizeof(int) * count);
}

void BinaryStream::WriteInt(int data)
{
	Write(&data, sizeof(int));
}

void BinaryStream::WriteInt(uint data)
{
	Write(&data, sizeof(uint));
}

void BinaryStream::WriteByte(byte data)
{
	Write(&data, 1);
}

void BinaryStream::WriteShort(short data)
{
	Write(&data, 2);
}

void BinaryStream::WriteShort(ushort data)
{
	Write(&data, 2);
}

void BinaryStream::WriteVector2(const Vector2 & data)
{
	Write(&data, sizeof(float) * 2);
}

void BinaryStream::WriteVector3(const Vector3 & data)
{
	Write(&data, sizeof(float) * 3);
}

void BinaryStream::WriteQuaternion(const Quaternion & data)
{
	Write(&data, sizeof(float) * 4);
}

void BinaryStream::WriteFloatArray(float* data, int count)
{
	Write(data, sizeof(float) * count);
}

void BinaryStream::WriteFloat(float data)
{
	WriteFloatArray(&data, 1);
}

void BinaryStream::WriteDoubleArray(double* data, int count)
{
	Write(data, sizeof(double) * count);
}

void BinaryStream::WriteDouble(double data)
{
	WriteDoubleArray(&data, 1);
}

void BinaryStream::WriteString(const char* data)
{
	if (data)
	{
		int len = strlen(data);
		WriteInt(len);
		Write(data, len);
	}
	else
	{
		WriteInt(0);
	}
}

void BinaryStream::WriteStringArgs(const char * format, va_list args)
{
	if (write_position)
	{
		int size;
		int buff_size = send_buffer + send_buffer_size - write_position - 4;

		size = vsnprintf(write_position + 4, buff_size, format, args);

		if (size >= buff_size)
		{
			int write_offset = write_position - send_buffer;
			SendBufferResize(2 * send_buffer_size);
			write_position = send_buffer + write_offset;
		}
		
		if (size < buff_size)
		{
			memcpy(write_position, &size, 4);
			write_position += size + 4;
		}
		else
		{
			write_position = NULL;
			log_write(LOG_ERROR, "BinaryStream::WriteStringArgs() : buffer overflow");
		}
		
		return;
	}
	
	log_write(LOG_ERROR, "BinaryStream::WriteStringArgs() : write_position is null");
}

void BinaryStream::WriteStringf(const char * format, ...)
{
	va_list args;
	va_start(args, format);
	WriteStringArgs(format, args);
	va_end(args);
}

// -----------------------------------------------------------------
// BinaryStream 
// -----------------------------------------------------------------
// constructor
BinaryStream::BinaryStream(int _buffer_size)
	: NetworkStream(_buffer_size)
	, write_position(NULL)
	, write_end(NULL)
	, read_position(NULL)
	, read_end(NULL)
	, encoder(NULL)
	, compressor(NULL)
{
}

// destructor
BinaryStream::~BinaryStream()
{
}

// on parse message
void BinaryStream::OnParseMessage()
{
	char * pos = recv_buffer;

	while (pos + HEAD_SIZE < recv_buffer + recv_offset)
	{
		ushort magic_num = *(ushort*)pos;
		ushort sz = *(ushort*)(pos + MAGICNUM_SIZE);
		
		if (sz < HEAD_SIZE)
		{
			log_write(LOG_ERROR, "binary stream packet errror.");
			
			throw ERR_Parse;
		}
		
		//check magic num
		if ((magic_num & MAGICNUM_MASK) != MAGICNUM)
		{
			log_write(LOG_ERROR, "BinaryStream::OnParseMessage() : magic_num[%d, %d] is incorrect!!!", magic_num, MAGICNUM);
			
			if (log_get_output_level() > LOG_INFO)
				DumpRecvBuffer(pos - recv_buffer);
			
			throw ERR_Parse;
		}

		// not enough buffer
		if (pos + sz > recv_buffer + recv_offset)
		{
			log_write(LOG_DEBUG3, "BinaryStream::OnParseMessage() : not enough buffer[%d]", (int)sz);
			
			break;
		}

		// set read position and read end
		read_position = pos + HEAD_SIZE;
		read_end = pos + sz;
		pos = read_end;

		// decode
		if (encoder)
			encoder->Decode((byte*)read_position, read_end - read_position);

		// decompress data
		if (compressor)
		{
			char * dst = recv_buffer + recv_offset;
			uint dst_size = recv_buffer_size - recv_offset;
			uint size = compressor->Decompress((byte*)dst, dst_size, (byte*)read_position, read_end - read_position);

			read_position = dst;
			read_end = dst + size;
		}

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

// on message
void BinaryStream::OnMessage()
{
}

// -----------------------------------------------------------------
// TextStream
// -----------------------------------------------------------------
// constructor
TextStream::TextStream()
	: NetworkStream(32 * 1024)
	, read_position(recv_buffer)
	, read_end(recv_buffer)
	, write_position(NULL)
	, write_end(NULL)
{
}

// begin write
void TextStream::BeginWrite()
{
	if (write_position)
		writeerr = true; 

	write_position = send_buffer + send_offset;
	write_end = send_buffer + send_buffer_size;
}

// end write
void TextStream::EndWrite()
{
	if (write_position)
	{
		if (write_position > send_buffer + send_offset)
		{
			send_offset = write_position - send_buffer;

			write_position = NULL;
			write_end = NULL;

			if (no_delay && connection)
				connection->SendMessages();
		}
	}
}

// write
void TextStream::WriteString(const char * data)
{
	if (write_position)
	{
		int len = strlen(data);

		if (write_position + len < write_end)
		{
			memcpy(write_position, data, len);
			write_position += len;
		}
		else
		{
			writeerr = true;
			write_position = NULL;
			write_end = NULL;
		}
	}
}

void TextStream::WriteStringf(const char * format, ...)
{
	if (write_position)
	{
		int size;
		va_list args;
		va_start(args, format);
		size = vsnprintf(write_position, write_end - write_position, format, args);
		va_end(args);

		if (write_position + size < write_end)
		{
			write_position += size;
		}
		else
		{
			writeerr = true;
			write_position = NULL;
			write_end = NULL;
		}
	}
}

static inline bool islineend(char ch)
{
	return (ch == '\n' || ch == '\r' || ch == '\0');
}

// read line
char * TextStream::ReadLine()
{
	if (read_position < read_end)
	{
		char * line_start = read_position;
		char * line_end;

		// search for line end
		for (line_end = line_start; line_end < read_end && !islineend(*line_end); line_end++);

		// parse line
		if (islineend(*line_end))
		{
			// close string
			*line_end = '\0';
			
			// to next line
			for (;line_end < read_end && islineend(*line_end); line_end++);

			// point to next line start
			read_position = line_end;

			return line_start;
		}
	}

	return NULL;
}

// on parse message
void TextStream::OnParseMessage()
{
	read_position = recv_buffer;
	read_end = recv_buffer + recv_offset;

	if (read_end > read_position)
		OnMessage();

	if (read_position > recv_buffer)
	{
		recv_offset = read_end - read_position;

		if (recv_offset > 0)
			memmove(recv_buffer, read_position, recv_offset);
	}

	read_position = recv_buffer;
	read_end = recv_buffer + recv_offset;
}

// on message
void TextStream::OnMessage()
{
}










static unsigned char XL_key[16] = {49,106,-96,51,23,-12,-97,54,90,-22,-16,-6,-16,96,62,-106};

// on message
void XLNetworkStream::OnMessage()
{

}

void XLNetworkStream::OnParseMessage()
{
	log_write(LOG_DEBUG1,"XL OnParseMessage");

	while (4 < recv_offset)
	{
		read_position = recv_buffer;
		read_end = recv_buffer + recv_offset;

		ReadInt(frame_length);

		log_write(LOG_DEBUG1,"XL frame_length %d   %d", frame_length, recv_offset);
		if (frame_length <= recv_offset - 4)
		{
			char version[6] = {0};
			char game_id[6] = {0};

			ReadString(version,6,5);

			ReadString(game_id,6,5);

			log_write(LOG_DEBUG1,"XL Read Start");
			AesUnCompressStart();

			OnMessage();

			AesUnCompressEnd();

			log_write(LOG_DEBUG1,"XL Read End");

			recv_offset = recv_offset - 4 - frame_length;
			
			log_write(LOG_DEBUG1,"XL Read OffSet %d", recv_offset);
			
			if(recv_offset > 0)
				memmove(recv_buffer, recv_buffer + 4 + frame_length, recv_offset);
			
			log_write(LOG_DEBUG1,"XL OnMessage Left Buffer %d", recv_offset);
		}
		else
		{
			log_write(LOG_ERROR,"XL portion buffer");
			
			if (connection)
				connection->OnClose();
		
			return;
		}
	}
	
}

// constructor
XLNetworkStream::XLNetworkStream()
: NetworkStream(32 * 1024)
, read_position(recv_buffer)
, read_end(recv_buffer)
, write_position(NULL)
, write_compress(NULL)
, uncompress_position(NULL)
, frame_length(0)
, uncompress_flag(false)
{
	gen_tabs();

	aes_set_key(&aes, (const u8 *)XL_key , 16);
}

void XLNetworkStream::AesUnCompressStart()
{
	if(uncompress_flag)
	{
		log_write(LOG_ERROR,"xl uncompress start failed.");
		writeerr = true;
		return;
	}
	int l = frame_length;

	l = (l + 15)/16 * 16 + 4;

	if(l >= XL_UNCOMPRESSBUFFER_SIZE)
	{
		log_write(LOG_ERROR,"xl uncompress buffer not enough long");
		writeerr = true; 
		return;

	}

	memcpy(uncompress_buffer,read_position,l);

	uncompress_flag = true;

	if(1 == AesDecrypt(&aes, (unsigned char*)uncompress_buffer, (unsigned char*)read_position))
	{
		log_write(LOG_ERROR,"AesDecrypt failed");
	}
	
	uncompress_position = read_position;
}

void XLNetworkStream::AesUnCompressEnd()
{
	if(!uncompress_flag)
	{
		log_write(LOG_ERROR,"xl uncompress end failed.");
		writeerr = true;
		return;
	}
	uncompress_flag = false;

	read_position = uncompress_position + frame_length; 


	int l = frame_length;

	l = (l + 15)/16 * 16 + 4;

	memcpy(uncompress_buffer,uncompress_position,l);
}

void XLNetworkStream::BeginAesCompress()
{	
	if(write_compress)
	{
		log_write(LOG_ERROR, "write_compress not null");
		writeerr = true; 
	}

	write_compress = write_position;
}

void XLNetworkStream::EndAesCompress()
{
	if (writeerr)
	{
		log_write(LOG_ERROR, "write error~~~");
		return;
	}
	
	if (!write_position)
	{
		log_write(LOG_ERROR, "no write position");
		writeerr = true;
		return;
	}
	
	if(!write_compress)
	{
		log_write(LOG_ERROR, "no write_compress");
		writeerr = true; 
		return;
	}
	
	int size = write_position - write_compress;
	
	static unsigned char buffer[MAX_RECV_BUFFER_SIZE] = {0};
	memset(buffer, 0, sizeof(buffer));
	
	int size1 = ((size + 15)/16) * 16 + 4;

	memcpy(buffer, write_compress,size);

	AesEncrypt(&aes,(unsigned char*)buffer,(unsigned char*)write_compress,size1-4);
	
	write_position = write_compress + size1;

	write_compress = NULL;
}

void XLNetworkStream::BeginWrite()
{
	if (!write_position)
	{
		
		if (send_offset + 4 < send_buffer_size)
		{
			write_position = send_buffer + send_offset +4;
		}
		else
		{
			write_position = NULL;
			log_write(LOG_ERROR, "XLStream::BeginWrite() : buffer overflow[%d, %d]", send_offset, send_buffer_size);
			assert(0);
		}

		return;
	}

	log_write(LOG_ERROR, "XLStream::BeginWrite() : write_position is not null");
}

void XLNetworkStream::Read(void* data, int size)
{
	if (read_position + size > read_end)
	{
		log_write(LOG_ERROR, "%s, %s, read_position : %d, read_end : %d, size : %d", __FILE__, __FUNCTION__, read_position, read_end, size);
		assert(0);
		throw ERR_Read;
	}

	memcpy(data, read_position, size);
	read_position += size;
}

void XLNetworkStream::Write(const void* data, int size)
{
	WriteString((char*)data,size);
}

void XLNetworkStream::ReadString(char* s, int sl, int l)
{
	if (sl <= l)
	{
		log_write(LOG_ERROR, "read string len error: len1 = %d, size = %d", l, sl);
		//assert(0);
		throw ERR_Read;
	}

	s[l] = 0;
	Read(s, l);
}

void XLNetworkStream::ReadInt(int& data)
{
	Read(&data, sizeof(int));
	data = ntohl(data);
}

void XLNetworkStream::ReadInt(uint& data)
{
	Read(&data, sizeof(uint));
	data = ntohl(data);
}

void XLNetworkStream::ReadByte(byte& data)
{
	Read(&data, sizeof(byte));
}

void XLNetworkStream::ReadByte(char& data)
{
	Read(&data, sizeof(char));
}

void XLNetworkStream::ReadShort(short& data)
{
	Read(&data, 2);
	data = ntohs(data);
}

void XLNetworkStream::ReadShort(ushort& data)
{
	Read(&data, 2);
	data = ntohs(data);
}

void * XLNetworkStream::ReadData(int size)
{
	if (read_position + size > read_end)
	{
		log_write(LOG_ERROR, "%s, %s, read_position : %d, read_end : %d, size : %d", __FILE__, __FUNCTION__, read_position, read_end, size);
		assert(0);
		throw ERR_Read;
	}

	read_position += size;

	return read_position - size;
}

void XLNetworkStream::WriteString(const char * data, int len)
{
	if (write_position)
	{
		if (write_position + len <  send_buffer + send_buffer_size)
		{
			memcpy(write_position, data, len);
			write_position += len;
		}
		else
		{
			writeerr = true;
			write_position = NULL;
			log_write(LOG_ERROR, "write_position : %d, send_buffer : %d, send_buffer_size : %d, date : %s", write_position, send_buffer, send_buffer_size, data);
		}
	}
}

void XLNetworkStream::WriteString(const char* data)
{
	if (data)
	{
		int len = strlen(data);
		WriteInt(len);
		Write(data, len);
	}
	else
	{
		WriteInt(0);
	}
}

void XLNetworkStream::WriteHead(const char * data)
{
	if(data == NULL || strlen(data) < 5)
	{
		log_write(LOG_ERROR, "~~~~~");
		writeerr = true; 
	}
	else
	{
		WriteString(data,5);
	}
}

void XLNetworkStream::WriteVersion(const char * data)
{
	if(data == NULL || strlen(data) < 5)
	{
		log_write(LOG_ERROR, "~~~~~");
		writeerr = true; 
	}
	else
	{
		WriteString(data,5);
	}
}

void XLNetworkStream::WriteShort(short data)
{
	short t = htons(data);
	Write(&t, 2);
}

void XLNetworkStream::WriteShort(ushort data)
{
	short t = htons(data);
	Write(&t, 2);
}

void XLNetworkStream::WriteInt(int data)
{
	int t = htonl(data);
	Write(&t, sizeof(int));
}

void XLNetworkStream::WriteInt(uint data)
{
	int t = htonl(data);
	Write(&t, sizeof(uint));
}

void XLNetworkStream::WriteByte(byte data)
{
	Write(&data, 1);
}

void XLNetworkStream::EndWrite()
{
	if(writeerr)
	{
		write_position = NULL;
		write_compress = NULL;
		//send_offset = 0;
		log_write(LOG_ERROR, "XLNetworkStream write error and not reset buf!");
		writeerr = false;
		return;
	}
	if (write_position)
	{
		int offset = write_position - send_buffer - send_offset;
		int size = offset - 4;

		*(int*)(send_buffer + send_offset) = htonl(size);

		write_position = NULL;
		send_offset += offset;

		if (no_delay&&connection)
			connection->SendMessages();
	}
}












// on message
void XLFCMNetworkStream::OnMessage()
{

}

void XLFCMNetworkStream::OnParseMessage()
{
	log_write(LOG_DEBUG1,"XL Fcm OnParseMessage");

	while (16 < recv_offset)
	{
		read_position = recv_buffer;
		read_end = recv_buffer + recv_offset;
		
		int size = 0;
		ReadInt(size);

		log_write(LOG_DEBUG1,"XL  Fcm frame_length %d   %d", size, recv_offset);
		if (size <= recv_offset - 16)
		{
			OnMessage();

			recv_offset = recv_offset - 16 - size;

			log_write(LOG_DEBUG1,"XL Fcm Read OffSet %d", recv_offset);

			if(recv_offset > 0)
				memmove(recv_buffer, recv_buffer + 16 + size, recv_offset);

			log_write(LOG_DEBUG1,"XL Fcm OnMessage Left Buffer %d", recv_offset);
		}
		else
		{
			log_write(LOG_ERROR,"XL Fcm portion buffer");
			
			if (connection)
				connection->OnClose();
		}
	}
}

// constructor
XLFCMNetworkStream::XLFCMNetworkStream()
: NetworkStream(32 * 1024)
, read_position(recv_buffer)
, read_end(recv_buffer)
, write_position(NULL)
{
	gen_tabs();

	aes_set_key(&aes, (const u8 *)XL_key , 16);
}



void XLFCMNetworkStream::BeginWrite()
{
	if (!write_position)
	{
		if (send_offset + 4 < send_buffer_size)
		{
			write_position = send_buffer + send_offset + 4;
		}
		else
		{
			write_position = NULL;
			log_write(LOG_INFO, "FCM send_buffer is not enough");
		}
	}
	else
	{
		log_write(LOG_DEBUG1, "FCM write position is null");
	}
}

void XLFCMNetworkStream::Read(void* data, int size)
{
	if (read_position + size > read_end)
	{
		log_write(LOG_ERROR, "%s, %s, read_position : %d, read_end : %d, size : %d", __FILE__, __FUNCTION__, read_position, read_end, size);
		assert(0);
		throw ERR_Read;
	}

	memcpy(data, read_position, size);
	read_position += size;
}

void XLFCMNetworkStream::Write(const void* data, int size)
{
	WriteString((char*)data,size);
}

void XLFCMNetworkStream::ReadString(char* s, int sl, int l)
{
	if (sl <= l)
	{
		log_write(LOG_ERROR, "fcm read string len error: len1 = %d, size = %d", l, sl);
		//assert(0);
		throw ERR_Read;
	}

	s[l] = 0;
	Read(s, l);
}

void XLFCMNetworkStream::ReadInt(int& data)
{
	Read(&data, sizeof(int));
	data = ntohl(data);
}

void XLFCMNetworkStream::ReadInt(uint& data)
{
	Read(&data, sizeof(uint));
	data = ntohl(data);
}

void XLFCMNetworkStream::ReadByte(byte& data)
{
	Read(&data, sizeof(byte));
}

void XLFCMNetworkStream::ReadByte(char& data)
{
	Read(&data, sizeof(char));
}

void XLFCMNetworkStream::ReadShort(short& data)
{
	Read(&data, 2);
}

void XLFCMNetworkStream::ReadShort(ushort& data)
{
	Read(&data, 2);
}

void * XLFCMNetworkStream::ReadData(int size)
{
	if (read_position + size > read_end)
	{
		log_write(LOG_ERROR, "%s, %s, read_position : %d, read_end : %d, size : %d", __FILE__, __FUNCTION__, read_position, read_end, size);
		assert(0);
		throw ERR_Read;
	}

	read_position += size;

	return read_position - size;
}

void XLFCMNetworkStream::WriteString(const char * data, int len)
{
	if (write_position)
	{
		if (write_position + len <  send_buffer + send_buffer_size)
		{
			memcpy(write_position, data, len);
			write_position += len;
		}
		else
		{
			writeerr = true;
			write_position = NULL;
		}
	}
}

void XLFCMNetworkStream::WriteString(const char* data)
{
	if (data)
	{
		int len = strlen(data);
		WriteInt(len);
		Write(data, len);
	}
	else
	{
		WriteInt(0);
	}
}

void XLFCMNetworkStream::WriteHead(const char * data)
{
	if(data == NULL || strlen(data) < 5)
	{
		writeerr = true; 
	}
	else
	{
		WriteString(data,5);
	}
}

void XLFCMNetworkStream::WriteVersion(const char * data)
{
	if(data == NULL || strlen(data) < 5)
	{
		writeerr = true; 
	}
	else
	{
		WriteString(data,5);
	}
}

void XLFCMNetworkStream::WriteShort(short data)
{
	Write(&data, 2);
}

void XLFCMNetworkStream::WriteShort(ushort data)
{
	Write(&data, 2);
}

void XLFCMNetworkStream::WriteInt(int data)
{
	int t = htonl(data);
	Write(&t, sizeof(int));
}

void XLFCMNetworkStream::WriteInt(uint data)
{
	int t = htonl(data);
	Write(&t, sizeof(uint));
}

void XLFCMNetworkStream::WriteByte(byte data)
{
	Write(&data, 1);
}

void XLFCMNetworkStream::EndWrite()
{
	if(writeerr)
	{
		write_position = NULL;
		send_offset = 0;
		log_write(LOG_ERROR, "FCM writeerror and reset buf!");
		writeerr = false;
		return;
	}


	if (write_position)
	{
		log_write(LOG_DEBUG4, "FCM End Write");
		int offset = write_position - send_buffer - send_offset;
		int size = offset - 16;

		*(int*)(send_buffer + send_offset) = htonl(size);

		write_position = NULL;
		send_offset += offset;

		if (connection)
		{
			connection->SendMessages();
			log_write(LOG_DEBUG4, "FCM SendMessages");
		}
	}
}

//////////////////////////////////////////////////////////////////////////

XLFWGStream::XLFWGStream()
: NetworkStream(32 * 1024)
, write_position(NULL)
, write_end(NULL)
, read_position(NULL)
, read_end(NULL)
{

}

XLFWGStream::~XLFWGStream()
{

}

void XLFWGStream::OnMessage()
{

}

void XLFWGStream::OnParseMessage()
{
	if (32 * 1024 < recv_offset)
	{
		//不用存在这种情况
		assert(0);
	}
	
	read_position = recv_buffer;
	read_end = recv_buffer + recv_offset;

	char* tempPosition = read_position;
	try
	{
		while (read_end > read_position)
		{
			tempPosition = read_position;
			OnMessage();
		}
	}
	catch (...)
	{
		// 貌似迅雷有时候一个包发不完 这个时候 就把包断掉
		read_position = tempPosition;
	}

	if (read_position > recv_buffer)
	{
		recv_offset = read_end - read_position;

		if (recv_offset > 0)
			memmove(recv_buffer, read_position, recv_offset);
	}

	read_position = recv_buffer;
	read_end = recv_buffer + recv_offset;
}

void XLFWGStream::Read( void* data, int size )
{
	if (read_position + size > read_end)
	{
		log_write(LOG_ERROR, "%s, %s, read_position : %d, read_end : %d, size : %d", __FILE__, __FUNCTION__, read_position, read_end, size);
		//assert(0);
		throw ERR_Read;
	}

	memcpy(data, read_position, size);
	read_position += size;
}

void XLFWGStream::ReadInt( int& data )
{
	Read(&data, sizeof(int));
	data = ntohl(data);
}

void XLFWGStream::ReadInt( uint& data )
{
	Read(&data, sizeof(uint));
	data = ntohl(data);
}

int XLFWGStream::ReadString( char* s, int size )
{
	uint l;
	ReadInt(l);

	if (size <= l)
	{
		log_write(LOG_ERROR, "read string len error: len = %d, size = %d", l, size);
		//assert(0);
		throw ERR_Read;
	}

	s[l] = 0;
	Read(s, l);
	return l;
}

void * XLFWGStream::ReadData( int size )
{
	if (read_position + size > read_end)
	{
		log_write(LOG_ERROR, "%s, %s, read_position : %d, read_end : %d, size : %d", __FILE__, __FUNCTION__, read_position, read_end, size);
		//assert(0);
		throw ERR_Read;
	}

	read_position += size;

	return read_position - size;
}

void XLFWGStream::BeginWrite()
{
	if (write_position)
	{
		log_write(LOG_ERROR, "BinaryStream::BeginWrite() : write_position is not null");

		CancelWrite();
	}

	if (send_offset >= send_buffer_size)
		SendBufferResize(2 * send_buffer_size);

	if (send_offset < send_buffer_size)
	{
		write_position = send_buffer + send_offset;
	}
	else
	{
		write_position = NULL;
		log_write(LOG_ERROR, "BinaryStream::BeginWrite() : buffer overflow[%d, %d]", send_offset, send_buffer_size);
		assert(0);
	}

	return;
}

void XLFWGStream::EndWrite()
{
	if (write_position)
	{
		int size = write_position - send_buffer - send_offset;

		if (size <= 0)
		{
			log_write(LOG_ERROR, "BinaryStream::EndWrite(), pkg size is zero!!!, size[%d]", size);

			write_position = NULL;
			writeerr = true;
			if (connection)
				connection->OnClose();

			return;
		}

		send_offset += size;
		write_position = NULL;

		if (no_delay && connection)
			connection->SendMessages();
	}
	else
	{
		log_write(LOG_ERROR, "BinaryStream::EndWrite() : write_position is null");

		writeerr = true;
		if (connection)
			connection->OnClose();
	}
}

void XLFWGStream::Write( const void* data, int size )
{
	if (write_position)
	{
		if (write_position + size >= send_buffer + send_buffer_size)
		{
			int size_need = write_position + size - send_buffer - send_buffer_size;
			int grow = 2 + size_need / send_buffer_size;

			int write_offset = write_position - send_buffer;
			SendBufferResize(grow * send_buffer_size);
			write_position = send_buffer + write_offset;
		}

		if (write_position + size < send_buffer + send_buffer_size)
		{
			memcpy(write_position, data, size);
			write_position += size;
		}
		else
		{
			log_write(LOG_ERROR, "XLFWGStream::Write() : buffer overflow[%d, %d, %d]", 
				send_buffer + send_buffer_size - write_position, size, send_buffer_size);
			write_position = NULL;
			assert(0);
		}

		return;
	}

	log_write(LOG_ERROR, "XLFWGStream::Write() : write_position is null");
}

void XLFWGStream::WriteInt( int data )
{
	int t = htonl(data);
	Write(&t, sizeof(int));
}

void XLFWGStream::WriteInt( uint data )
{
	int t = htonl(data);
	Write(&t, sizeof(uint));
}

void XLFWGStream::WriteString( const char * data )
{
	if (data)
	{
		int len = strlen(data);
		WriteInt(len);
		Write(data, len);
	}
	else
	{
		WriteInt(0);
	}
}

void XLFWGStream::WriteStringf( const char * format, ... )
{
	va_list args;
	va_start(args, format);
	WriteStringArgs(format, args);
	va_end(args);
}

void XLFWGStream::WriteStringArgs( const char * format, va_list args )
{
	if (write_position)
	{
		int size;
		int buff_size = send_buffer + send_buffer_size - write_position - 4;

		size = vsnprintf(write_position + 4, buff_size, format, args);

		if (size >= buff_size)
		{
			int write_offset = write_position - send_buffer;
			SendBufferResize(2 * send_buffer_size);
			write_position = send_buffer + write_offset;
		}

		if (size < buff_size)
		{
			memcpy(write_position, &size, 4);
			write_position += size + 4;
		}
		else
		{
			write_position = NULL;
			log_write(LOG_ERROR, "XLFWGStream::WriteStringArgs() : buffer overflow");
		}

		return;
	}

	log_write(LOG_ERROR, "XLFWGStream::WriteStringArgs() : write_position is null");
}

void XLFWGStream::CancelWrite()
{
	if (write_position)
	{
		log_write(LOG_DEBUG1, "XLFWGStream::CancelWrite()");

		write_position = NULL;
	}
}
