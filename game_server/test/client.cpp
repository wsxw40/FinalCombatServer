#include "common.h"
#include "tcpconnection.h"
#include "tcplistener.h"
#include "udpconnection.h"
#include "udplistener.h"
#include "log.h"

struct ClientConnection : public BinaryStream, public UdpConnection
{
	// constructor
	ClientConnection()
	: BinaryStream(64 * 1024)
	{
		stream = this;
		connection = this;
	}

	// on connected
	void OnConnected()
	{
		seq = 0;
		check = 0;

		BinaryStream::OnConnected();
		log_write(LOG_DEBUG1, "server connected.");
	}

	// on close
	void OnDisconnected()
	{
		BinaryStream::OnDisconnected();
		log_write(LOG_DEBUG1, "server disconnected.");
		Event::Quit();
	}

	// on message
	void OnMessage()
	{
		uint data;
		ReadInt(data);
		log_write(LOG_DEBUG1, "%d\t%d\n", uint(delay_time * 1000), data);

		if (data != check)
			log_write(LOG_ERROR, "data error: data=%d, seq=%d", data, check);

		check++;
	}

	uint seq;
	uint check;
} conn;

static void UpdateCallback(void * data)
{
	Event::AddTimer(&UpdateCallback, data, 0.05);

	if (!conn.IsIdle())
	{
		conn.ReceiveMessages();
		if (conn.IsConnected())
		{
			if (conn.send_offset + 256 < conn.send_buffer_size)
			{
				conn.BeginWrite();
				conn.WriteInt(conn.seq++);
				conn.EndWrite();
			}
		}
		conn.SendMessages();
	}
}

int main(int argc, char** argv)
{
	if (argc != 3)
	{
		puts("Usage: testclient addr port");
		return 1;
	}

	sockaddr_in addr;
	set_sockaddr(addr, argv[1], atoi(argv[2]));

	if (!Event::Initialize())
		return 1;

	UpdateCallback(NULL);

	conn.Connect(addr);

	Event::Dispatch();
	Event::Terminate();

	return 0;
}
