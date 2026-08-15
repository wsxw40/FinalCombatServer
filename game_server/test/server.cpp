#include "common.h"
#include "tcpconnection.h"
#include "udpconnection.h"
#include "udplistener.h"
#include "tcplistener.h"
#include "networkstream.h"
#include "log.h"

struct ServerConnection : public BinaryStream, public UdpConnection
{
	// constructor
	ServerConnection()
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
		log_write(LOG_DEBUG1, "client connected.");
	}

	// on close
	void OnDisconnected()
	{
		BinaryStream::OnDisconnected();
		log_write(LOG_DEBUG1, "client disconnected.");
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
};

ServerConnection connections[16];

struct ServerListener : public UdpListener
{
	void OnClientConnected(int connected_socket, sockaddr_in addr)
	{
		for (ServerConnection * conn = connections; conn < endof(connections); ++conn)
		{
			if (conn->IsIdle())
			{
				conn->no_delay = false;
				conn->Connect(connected_socket, addr);
				return;
			}
		}
	}
} listener;


static void UpdateCallback(void * data)
{
	Event::AddTimer(&UpdateCallback, data, 0.05);

	for (ServerConnection * conn = connections; conn < endof(connections); ++conn)
	{
		if (!conn->IsIdle())
		{
			conn->ReceiveMessages();
			if (conn->IsConnected())
			{
				if (conn->send_offset + 256 < conn->send_buffer_size)
				{
					conn->BeginWrite();
					conn->WriteInt(conn->seq++);
					conn->EndWrite();
				}
			}
			conn->SendMessages();
		}
	}
}

int main(int argc, char** argv)
{
	if (argc != 2)
	{
		puts("Usage: testserver port");
		return 1;
	}

	set_sockaddr(listener.addr, NULL, atoi(argv[1]));

	if (!Event::Initialize())
		return 1;

	if (!listener.Initialize())
		return 1;

	UpdateCallback(NULL);

	Event::Dispatch();
	Event::Terminate();

	return 0;
}
