#include "localconnection.h"
#include "log.h"

// -----------------------------------------------------------------
// read and write fd
// -----------------------------------------------------------------
void LocalConnection::ReadFD(int& fd)
{
	if (fd_recv_offset > 0)
	{
		fd = fd_recv_buffer[0];

		if (fd_recv_offset > 1)
			memmove(fd_recv_buffer, fd_recv_buffer + 1, (fd_recv_offset - 1) * sizeof(int));

		fd_recv_offset--;
	}
	else
	{
		throw ERR_Read;
	}
}

void LocalConnection::WriteFD(int fd)
{
	if (fd_send_offset < fd_buffer_size)
	{
		fd_send_buffer[fd_send_offset] = fd;
		fd_send_offset++;
	}
	else
	{
		log_write(LOG_ERROR, "fd buffer full");
	}
}

// -----------------------------------------------------------------
// local connection
// -----------------------------------------------------------------
// constructor
LocalConnection::LocalConnection()
	: connected_socket(INVALID_SOCKET)
	, fd_send_offset(0)
	, fd_recv_offset(0)
	, readable(false)
	, writable(false)
{
}

// destructor
LocalConnection::~LocalConnection()
{
	if (connected_socket != INVALID_SOCKET)
		close(connected_socket);
}

// is idle
bool LocalConnection::IsConnected()
{
	return connected_socket != INVALID_SOCKET;
}

// connect
void LocalConnection::Connect(SOCKET socket)
{
	connected_socket = socket;

	// non-blocking
	fcntl(socket, F_SETFL, fcntl(socket, F_GETFL) | O_NONBLOCK);
	fcntl(socket, F_SETFD, FD_CLOEXEC);

	// tcp nodelay
	int nodelay = 1;
	setsockopt(socket, IPPROTO_TCP, TCP_NODELAY, (char*)&nodelay, sizeof(nodelay));

	// linger
	linger l;
	l.l_onoff = 1;
	l.l_linger = 1;
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

	if (!Event::AddSocket(socket, this, true, true))
	{
		Disconnect();
		return;
	}

	readable = false;
	writable = true;

	if (stream)
		stream->OnConnected();
}

// close
void LocalConnection::Disconnect()
{
	if (connected_socket != INVALID_SOCKET)
	{
		// flush unsend messages
		SendMessages();

		Event::RemoveSocket(connected_socket);
		close(connected_socket);
		connected_socket = INVALID_SOCKET;

		num_bytes_send = 0;
		num_bytes_received = 0;
		fd_send_offset = 0;
		fd_recv_offset = 0;

		if (stream)
		{
			stream->recv_offset = 0;
			stream->send_offset = 0;

			stream->OnDisconnected();
		}
	}
}

// on read
void LocalConnection::OnRead()
{
	readable = true;

	if (stream && stream->no_delay)
		ReceiveMessages();
}

void LocalConnection::OnWrite()
{
	writable = true;

	if (stream && stream->no_delay)
		SendMessages();
}

void LocalConnection::OnClose()
{
	Disconnect();
}

void LocalConnection::SendMessages()
{
	if (!stream)
		return;

	while (writable && stream->send_offset > 0)
	{
		// bytes total send in this loop.
		int count = 0;

		while (count < stream->send_offset)
		{
			struct iovec iov[1];
			struct msghdr msg;

			union {
				struct cmsghdr cm;
				char space[CMSG_SPACE(sizeof(int) * fd_buffer_size)];
			} cmsg;

			// fd
			if (fd_send_offset > 0)
			{
				msg.msg_control = (caddr_t) &cmsg;
				msg.msg_controllen = CMSG_SPACE(sizeof(int) * fd_send_offset);

				cmsg.cm.cmsg_level = SOL_SOCKET;
				cmsg.cm.cmsg_type = SCM_RIGHTS;
				cmsg.cm.cmsg_len = CMSG_LEN(sizeof(int) * fd_send_offset);

				memcpy(CMSG_DATA(&cmsg.cm), fd_send_buffer, sizeof(int) * fd_send_offset);
			}
			else
			{
				msg.msg_control = NULL;
				msg.msg_controllen = 0;
			}

			// buffer
			iov[0].iov_base = stream->send_buffer + count;
			iov[0].iov_len = stream->send_offset - count;

			// limit send data size
			if (iov[0].iov_len > 1024)
				iov[0].iov_len = 1024;

			msg.msg_flags = 0;
			msg.msg_name = NULL;
			msg.msg_namelen = 0;
			msg.msg_iov = iov;
			msg.msg_iovlen = 1;

			// send message
			int n = sendmsg(connected_socket, &msg, 0);

			if (n == -1)
			{
				if (errno == EAGAIN)
				{
					writable = false;
					return;
				}
				else
				{
					OnClose();
					return;
				}
			}

			if (n == 0)
				break;

			count += n;
			fd_send_offset = 0;
		}

		// move unsend data to from of the send buffer.
		if (count < stream->send_offset && count > 0)
			memcpy(stream->send_buffer, stream->send_buffer + count, stream->send_offset - count);

		stream->send_offset -= count;
	}
}

void LocalConnection::ReceiveMessages()
{
	if (!stream)
		return;

	while (readable)
	{
		struct iovec iov[1];
		struct msghdr msg;

		union {
			struct cmsghdr cm;
			char space[CMSG_SPACE(sizeof(int) * fd_buffer_size)];
		} cmsg;

		iov[0].iov_base = stream->recv_buffer + stream->recv_offset;
		iov[0].iov_len = stream->recv_buffer_size - stream->recv_offset;

		msg.msg_name = NULL;
		msg.msg_namelen = 0;
		msg.msg_iov = iov;
		msg.msg_iovlen = 1;

		msg.msg_control = (caddr_t) &cmsg;
		msg.msg_controllen = sizeof(cmsg);

		// receive message
		int n = recvmsg(connected_socket, &msg, 0);

		if (n == -1)
		{
			readable = false;

			if (errno != EAGAIN && errno != EINTR)
			{
				OnClose();
				return;
			}

			break;
		}
		else if (n == 0)
		{
			OnClose();
			return;
		}
		else if (n > iov[0].iov_len)
		{
			log_write(LOG_ERROR, "recvmsg retured not enough data: %d", n);
			OnClose();
			return;
		}

		// receive fds
		if (cmsg.cm.cmsg_level == SOL_SOCKET && cmsg.cm.cmsg_type == SCM_RIGHTS)
		{
			uint nfds = (cmsg.cm.cmsg_len - CMSG_LEN(0)) / sizeof(int);

			if (nfds)
			{
				if (fd_recv_offset + nfds > fd_buffer_size)
				{
					log_write(LOG_ERROR, "no buffer to receive fds");
					OnClose();
					return;
				}

				int * fds = (int*)CMSG_DATA(&cmsg.cm);
				memcpy(fd_recv_buffer + fd_recv_offset, fds, nfds * sizeof(int));
				fd_recv_offset += nfds;
			}
		}

		num_bytes_received += n;
		stream->recv_offset += n;

		try
		{
			stream->OnParseMessage();
		}
		catch (...)
		{
			log_write(LOG_ERROR, "parse message error.");
			OnClose();
			return;
		}
	}
}

