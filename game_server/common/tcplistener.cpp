#include "tcplistener.h"
#include "log.h"

// constructor
TcpListener::TcpListener()
	: listen_socket(-1)
{
	bzero(&addr, sizeof(addr));
	addr.sin_family = AF_INET;
}

// destructor
TcpListener::~TcpListener()
{
	Terminate();
}

// initialize
bool TcpListener::Initialize()
{
	// create socket
	listen_socket = socket(AF_INET, SOCK_STREAM, 0);
	if (listen_socket == -1)
	{
		log_write(LOG_ERROR, "socket create failed(%d)", errno);
		return false;
	}

	fcntl(listen_socket, F_SETFD, FD_CLOEXEC);

	// set nonblock
	if (fcntl(listen_socket, F_SETFL, fcntl(listen_socket, F_GETFL) | O_NONBLOCK))
	{
		log_write(LOG_ERROR, "fcntl failed(%d).", errno);
		return false;
	}

	// set reuseaddr
	int yes = 1;
	if (setsockopt(listen_socket, SOL_SOCKET, SO_REUSEADDR, &yes, sizeof(yes)))
	{
		log_write(LOG_ERROR, "setsockopt failed(%d).", errno);
		return false;
	}

	// bind
	if (bind(listen_socket, (sockaddr*)&addr, sizeof(addr)))
	{
		log_write(LOG_ERROR, "bind failed on %s:%d(%p).", inet_ntoa(addr.sin_addr), (int)ntohs(addr.sin_port), errno);
		return false;
	}


	// listen
	if (listen(listen_socket, 128))
	{
		log_write(LOG_ERROR, "listen failed(%d).", errno);
		return false;
	}

	return Event::AddSocket(listen_socket, this, true, false);
}

// terminate
void TcpListener::Terminate()
{
	if (listen_socket != INVALID_SOCKET)
	{
		Event::RemoveSocket(listen_socket);
		close(listen_socket);
		listen_socket = INVALID_SOCKET;
	}
}

// on read
void TcpListener::OnRead()
{
	sockaddr_in address;
	socklen_t addr_len = sizeof(sockaddr);

	for (;;)
	{
		// accpet connection
		int connected_socket = accept(listen_socket, (sockaddr*)&address, &addr_len);

		if (connected_socket < 0)
		{
			if (errno == EINTR)
				continue;

			if (errno != EAGAIN && errno != EWOULDBLOCK)
			{
				log_write(LOG_ERROR, "error accept(%d).", errno);
			}

			return;
		}

		OnClientConnected(connected_socket, address);
	}
}

// on write
void TcpListener::OnWrite()
{
}

// on close
void TcpListener::OnClose()
{
}
