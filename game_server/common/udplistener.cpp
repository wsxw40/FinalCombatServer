#include "udplistener.h"
#include "log.h"

enum ConnectionStatus
{
	ST_IDLE,
	ST_SYN_SEND,
	ST_SYN_RECV,
	ST_SYN_RECV_WAIT,
	ST_ESTABLISHED,
	ST_FIN_WAIT_1,
	ST_FIN_WAIT_2,
};

struct PacketHeader
{
	byte status;
	byte syn;
	byte ack;
};

// constructor
UdpListener::UdpListener()
	: listen_socket(-1)
{
	bzero(&addr, sizeof(addr));
	addr.sin_family = AF_INET;
}

// destructor
UdpListener::~UdpListener()
{
	Terminate();
}

// initialize
bool UdpListener::Initialize()
{
	// create socket
	listen_socket = socket(AF_INET, SOCK_DGRAM, 0);
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
		listen_socket = -1;
		return false;
	}

	// set reuseaddr
	int yes = 1;
	if (setsockopt(listen_socket, SOL_SOCKET, SO_REUSEADDR, &yes, sizeof(yes)))
	{
		log_write(LOG_ERROR, "setsockopt failed(%m).", errno);
		listen_socket = -1;
		return false;
	}

	// bind
	if (bind(listen_socket, (sockaddr*)&addr, sizeof(addr)))
	{
		log_write(LOG_ERROR, "bind failed on %s:%d(%d).", inet_ntoa(addr.sin_addr), (int)ntohs(addr.sin_port), errno);
		listen_socket = -1;
		return false;
	}

	// allocate accept queue
	if (!accept_pool.Initialize(UDP_ACCEPT_MAX_SIZE))
	{
		log_write(LOG_ERROR, "not enough memory");
		listen_socket = -1;
		return false;
	}

	memset(accept_queue, sizeof(accept_queue), 0);
	accept_queue_len = 0;

	return Event::AddSocket(listen_socket, this, true, false);
}

// terminate
void UdpListener::Terminate()
{
	if (listen_socket != INVALID_SOCKET)
	{
		Event::RemoveSocket(listen_socket);
		close(listen_socket);
		listen_socket = INVALID_SOCKET;
	}

	// terminate accept queue
	accept_pool.Terminate();
}

uint UdpListener::GenerateAcceptHash(const sockaddr_in & addr)
{
	unsigned h = addr.sin_addr.s_addr ^ addr.sin_port;
	h ^= h>>16;
	h ^=h>>8;
	return h & (UDP_ACCEPT_HASH_SIZE - 1);
}

// get accept req
UdpListener::AcceptReq* UdpListener::GetAcceptReq(const sockaddr_in & addr)
{
	for (AcceptReq *req = accept_queue[GenerateAcceptHash(addr)]; req != NULL; req = req->next)
	{
		if (req->addr.sin_addr.s_addr == addr.sin_addr.s_addr &&
			req->addr.sin_port == addr.sin_port)
			return req;
	}
	
	return NULL;
}

// add accept req
void UdpListener::AddAcceptReq(UdpListener::AcceptReq* req)
{
	if (req && req->next == NULL)
	{
		uint h = GenerateAcceptHash(req->addr);
		req->next = accept_queue[h];
		accept_queue[h] = req;
		accept_queue_len++;
	}
}

// remove accept req
void UdpListener::RemoveAcceptReq(const sockaddr_in & addr)
{
	AcceptReq* req = accept_queue[GenerateAcceptHash(addr)];
	AcceptReq* prev = NULL;

	while (req != NULL)
	{
		if (req->addr.sin_addr.s_addr == addr.sin_addr.s_addr &&
			req->addr.sin_port == addr.sin_port)
		{
			if (prev) prev->next = req->next;
			accept_pool.Free(req->uid);
			accept_queue_len--;
			break;
		}

		prev = req;
		req = req->next;
	}
}

// on read
void UdpListener::OnRead()
{
	for (;;)
	{
		PacketHeader header;
		sockaddr_in address;
		socklen_t addr_len = sizeof(sockaddr);

		// receive message
		int size = recvfrom(listen_socket, &header, sizeof(header), 0, (sockaddr*)&address, &addr_len);

		if (size == sizeof(header))
		{
			AcceptReq* req = GetAcceptReq(address);

			if (req != NULL)
				continue;

			if (header.status != ST_SYN_SEND)
				continue;

			if (header.syn != 1 || header.ack != 0)
				continue;

			req = accept_pool.Allocate();

			if (req == NULL)
			{
				log_write(LOG_WARNING, "can't allocate more udp accept request.");
				break;
			}

			req->next = NULL;
			req->addr = address;

			req->accept_socket = socket(AF_INET, SOCK_DGRAM, 0);
			if (req->accept_socket == -1)
			{
				log_write(LOG_ERROR, "create socket failed(%m).");
				accept_pool.Free(req->uid);
				continue;
			}

			// cloexec
			fcntl(req->accept_socket, F_SETFD, FD_CLOEXEC);

			// set reuseaddr
			int yes = 1;
			if (setsockopt(req->accept_socket, SOL_SOCKET, SO_REUSEADDR, &yes, sizeof(yes)))
			{
				log_write(LOG_ERROR, "setsockopt failed(%m).", errno);
				close(req->accept_socket);
				accept_pool.Free(req->uid);
				continue;
			}

			// bind
			if (bind(req->accept_socket, (sockaddr*)&addr, sizeof(addr)))
			{
				log_write(LOG_ERROR, "bind failed on %s:%d(%d).", inet_ntoa(addr.sin_addr), (int)ntohs(addr.sin_port), errno);
				close(req->accept_socket);
				accept_pool.Free(req->uid);
				continue;
			}

			// add accept req
			AddAcceptReq(req);

			// send back
			OnClientConnected(req->accept_socket, address);
		}
		else if (size < 0)
		{
			if (errno == EINTR)
				continue;

			if (errno != EAGAIN && errno != EWOULDBLOCK)
			{
				log_write(LOG_ERROR, "error accept(%m).");
			}

			break;
		}
	}

	// temp remove accept req at here
	if (accept_queue_len > 0)
	{
		AcceptReq *req;
		for (int i = 0; i < UDP_ACCEPT_HASH_SIZE; i++)
		{
			while ((req = accept_queue[i]) != NULL)
			{
				accept_queue[i] = req->next;

				accept_pool.Free(req->uid);
				accept_queue_len--;

				if (accept_queue_len == 0)
					return;
			}
		}
	}
}

// on write
void UdpListener::OnWrite()
{
}

// on close
void UdpListener::OnClose()
{
}
