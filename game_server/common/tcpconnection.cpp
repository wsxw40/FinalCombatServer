#include "common.h"
#include "tcpconnection.h"
#include "log.h"
#include <stdarg.h>

static const char* EErrorString[] = 
{
	"ERR_Read",
	"ERR_Write",
	"ERR_Auth",
	"ERR_Closed",
	"ERR_Socket",
	"ERR_Parse",
};

static void init_socket(SOCKET socket)
{
	// non-blocking
	fcntl(socket, F_SETFL, fcntl(socket, F_GETFL) | O_NONBLOCK);
	fcntl(socket, F_SETFD, FD_CLOEXEC);

	// tcp nodelay
	int nodelay = 1;
	setsockopt(socket, IPPROTO_TCP, TCP_NODELAY, (char*)&nodelay, sizeof(nodelay));

	// linger
	linger l;
	l.l_onoff = 0;
	l.l_linger = 0;
	setsockopt(socket, SOL_SOCKET, SO_LINGER, &l, sizeof(l));

	// tcp keep-alive
	int keep_alive = 1;
	int keep_idle = 10;
	int keep_interval = 5;
	int keep_count = 2;
	setsockopt(socket, SOL_SOCKET, SO_KEEPALIVE, &keep_alive, sizeof(keep_alive));
	setsockopt(socket, SOL_TCP, TCP_KEEPIDLE, &keep_idle, sizeof(keep_idle));
	setsockopt(socket, SOL_TCP, TCP_KEEPINTVL, &keep_interval, sizeof(keep_interval));
	setsockopt(socket, SOL_TCP, TCP_KEEPCNT, &keep_count, sizeof(keep_count));
}

// -----------------------------------------------------------------
// class client
// -----------------------------------------------------------------
TcpConnection::TcpConnection()
	: connected_socket(INVALID_SOCKET)
	, readable(false)
	, writable(false)
	, connecting(false)
	, num_bytes_send(0)
	, num_bytes_received(0)
	, delay_time(0)
{
}

TcpConnection::~TcpConnection()
{
	if (connected_socket != INVALID_SOCKET)
		close(connected_socket);
}

// is idle
bool TcpConnection::IsIdle()
{
	return connected_socket == INVALID_SOCKET;
}

// is connected
bool TcpConnection::IsConnected()
{
	return !connecting && connected_socket != INVALID_SOCKET;
}

// is connecting
bool TcpConnection::IsConnecting()
{
	return connecting && connected_socket != INVALID_SOCKET;
}

// connect
void TcpConnection::Connect(SOCKET socket, const sockaddr_in & addr)
{
	this->connected_socket = socket;

	// init socket
	init_socket(connected_socket);

	if (!Event::AddSocket(socket, this, true, true))
	{
		Disconnect();
		return;
	}

	readable = false;
	writable = false;
	connecting = false;

	if (stream)
	{
		stream->send_offset = 0;
		stream->recv_offset = 0;
		stream->OnConnected();
	}
}

void TcpConnection::Connect(in_addr_t ip, ushort port)
{
	sockaddr_in addr;
	bzero(&addr, sizeof(addr));
	addr.sin_family = AF_INET;
	addr.sin_port = htons(port);
	addr.sin_addr.s_addr = ip;

	Connect(addr);
}

void TcpConnection::Connect(const sockaddr_in & addr)
{
	if (IsConnected() || IsConnecting())
		return;

	// create socket
	connected_socket = socket(AF_INET, SOCK_STREAM, 0);

	// init socket
	init_socket(connected_socket);

	// connect
	connect(connected_socket, (sockaddr*)&addr, sizeof(addr));

	if (EINPROGRESS == errno)
	{
		connecting = true;

		if (stream)
		{
			stream->send_offset = 0;
			stream->recv_offset = 0;
		}

		if (!Event::AddSocket(connected_socket, this, true, true))
		{
			Disconnect();
		}
	}
	else
	{
		Disconnect();
	}
}

// close
void TcpConnection::Disconnect(bool close_socket)
{
	if (connected_socket != INVALID_SOCKET)
	{
		// flush unsend messages
		SendMessages();

		Event::RemoveSocket(connected_socket);
		if (close_socket)
			close(connected_socket);
		connected_socket = INVALID_SOCKET;

		num_bytes_send = 0;
		num_bytes_received = 0;

		if (stream)
		{
			stream->recv_offset = 0;
			stream->send_offset = 0;
			
			stream->OnDisconnected();
		}
	}
}

// on read
void TcpConnection::OnRead()
{
	readable = true;

	ReceiveMessages();
}

void TcpConnection::OnWrite()
{
	if (connecting)
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
				writable = true;
				readable = false;
				connecting = false;

				if (stream)
					stream->OnConnected();
			}
			else
			{
				OnClose();
				return;
			}
		}
	}

	writable = true;

	if (stream && stream->no_delay)
		SendMessages();
}

void TcpConnection::OnClose()
{
	Disconnect();
}

void TcpConnection::ForcedSendMessages()
{
	if (!stream)
		return;

	// blocking
	fcntl(connected_socket, F_SETFL, fcntl(connected_socket, F_GETFL) & (~O_NONBLOCK));
	
	SendMessages();
	
	// non-blocking
	fcntl(connected_socket, F_SETFL, fcntl(connected_socket, F_GETFL) | O_NONBLOCK);
}

void TcpConnection::SendMessages()
{
	if (!stream || !stream->send_buffer)
		return;

	while (writable && stream->send_offset > 0)
	{
		// bytes total send in this loop.
		int count = 0;

		while (count < stream->send_offset)
		{
			// send as much data as we can.
			int n = send(connected_socket, stream->send_buffer + count, stream->send_offset - count, 0);

			if (n == -1)
			{
				int err = errno;
				writable = false;

				if (err == EAGAIN)
					break;
				else
				{
					log_write(LOG_DEBUG1, "TcpConnection::SendMessages() error, errno : %d, %s", errno, strerror(errno));
					OnClose();
					return;
				}
			}

			if (n == 0)
				break;

			count += n;
			num_bytes_send += n;
		}

		// move unsend data to from of the send buffer.
		if (count < stream->send_offset && count > 0)
			memcpy(stream->send_buffer, stream->send_buffer + count, stream->send_offset - count);

		stream->send_offset -= count;
	}
}

void TcpConnection::ReceiveMessages()
{
	if (!stream || !stream->recv_buffer)
		return;

	int n;
	int err;

	try
	{
		while (readable)
		{
			if ((stream->recv_buffer_size - stream->recv_offset) == 0)
			{
				stream->RecvBufferResize(2 * stream->recv_buffer_size);
				log_write(LOG_DEBUG1, "Resize Buffer");
			}
			

			n = recv(connected_socket, stream->recv_buffer + stream->recv_offset, stream->recv_buffer_size - stream->recv_offset, 0);

			if (n == -1)
			{
				readable = false;
				err = errno;

				if (err != EAGAIN && err != EINTR)
					throw ERR_Socket;
			}
			else if (n == 0)
			{
				readable = false;
				throw ERR_Closed;
			}
			else
			{
				num_bytes_received += n;
				stream->recv_offset += n;

				stream->OnParseMessage();
			}
		}
	}
	catch (EError &exp)
	{
		int index = Clamp(exp, ERR_Read, ERR_Parse);
		log_write(LOG_DEBUG1, "TcpConnection::ReceiveMessages() error, exp : %s, errno : %d, %s", EErrorString[index], errno, strerror(errno));
		OnClose();
	}
	catch (...)
	{
		log_write(LOG_DEBUG1, "TcpConnection::ReceiveMessages() unknow error");
		OnClose();
		
		assert(0);
	}
}

