#include "common.h"
#include "udpconnection.h"
#include "log.h"
#include <stdarg.h>

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


// -----------------------------------------------------------------
// class client
// -----------------------------------------------------------------
UdpConnection::UdpConnection()
	: writable(false)
	, connected_socket(INVALID_SOCKET)
	, num_bytes_send(0)
	, num_bytes_received(0)
	, status(ST_IDLE)
	, send_time(0)
	, send_frequency(0.02)
	, num_packets_send(0)
	, num_packets_retry(0)
	, ack_last(0)
	, syn_last(0)
	, send_data_frequency(1)
{
}

UdpConnection::~UdpConnection()
{
	if (connected_socket != INVALID_SOCKET)
		close(connected_socket);
}

// is idle
bool UdpConnection::IsIdle()
{
	return status == ST_IDLE;
}

// is connected
bool UdpConnection::IsConnected()
{
	return status == ST_ESTABLISHED;
}

// is connecting
bool UdpConnection::IsConnecting()
{
	return status == ST_SYN_SEND || status == ST_SYN_RECV || status == ST_SYN_RECV_WAIT;
}

// connect
void UdpConnection::Connect(SOCKET socket, const sockaddr_in & addr)
{
	connected_socket = socket;

	if (connected_socket == INVALID_SOCKET)
	{
		log_write(LOG_ERROR, "connect: invalid socket.");
		return;
	}

	fcntl(connected_socket, F_SETFL, fcntl(connected_socket, F_GETFL) | O_NONBLOCK);
	fcntl(connected_socket, F_SETFD, FD_CLOEXEC);

	// set ttl
	int ttl = 128;
	setsockopt(socket, IPPROTO_IP, IP_TTL, &ttl, sizeof(ttl));

	// set recv buffer size and send buffer size
	int buf_size = 256 * 1024;
	setsockopt(socket, SOL_SOCKET, SO_RCVBUF, &buf_size, sizeof(buf_size));
	setsockopt(socket, SOL_SOCKET, SO_SNDBUF, &buf_size, sizeof(buf_size));

	// connect
	if (connect(connected_socket, (sockaddr*)&addr, sizeof(addr)))
	{
		log_write(LOG_ERROR, "udp connect error.(%m)");
		Disconnect();
		return;
	}

	if (!Event::AddSocket(connected_socket, this, true, true))
	{
		Disconnect();
		return;
	}

	if (stream)
	{
		stream->send_offset = 0;
		stream->recv_offset = 0;
	}

	status = ST_SYN_RECV;
	delay_time = 0;
	delay_average = 3 * send_frequency;
	retry_time = delay_time + 2 * delay_average;
	send_time = 0;
	ack_recv_time = Event::GetTime();
	ack_timeout_retry = 1;
	ack_same_count = 0;
	quick_retry = false;
	send_data_time = 0;

	ack_last = 0;
	syn_last = 0;
	send_ack = false;
	send_window_control = 1;
	send_window_threshhold = send_window.window_size;

	readable = false;
	writable = false;

	// clear sliding window buffer
	recv_window.ClearBuffer();
	send_window.ClearBuffer();

	// initialize send window
	send_window.begin = 1;
	send_window.end = send_window.begin;

	// initialize recv window
	recv_window.begin = 1;
	recv_window.end = recv_window.begin + recv_window.window_size;
}

void UdpConnection::Connect(in_addr_t ip, ushort port)
{
	sockaddr_in addr;
	bzero(&addr, sizeof(addr));
	addr.sin_family = AF_INET;
	addr.sin_port = htons(port);
	addr.sin_addr.s_addr = ip;

	Connect(addr);
}

void UdpConnection::Connect(const sockaddr_in & addr)
{
	if (IsConnected() || IsConnecting())
		return;

	Connect(socket(AF_INET, SOCK_DGRAM, 0), addr);

	if (connected_socket != INVALID_SOCKET)
	{
		status = ST_SYN_SEND;
	}
}

// close
void UdpConnection::Disconnect(bool close_socket)
{
	if (connected_socket != INVALID_SOCKET)
	{
		if (status == ST_ESTABLISHED)
		{
			// flush unsend messages
			status = ST_FIN_WAIT_1;
			send_time = 0;
			send_ack = true;
			SendMessages();
		}

		Event::RemoveSocket(connected_socket);
		if (close_socket)
			close(connected_socket);
		connected_socket = INVALID_SOCKET;

		num_bytes_send = 0;
		num_bytes_received = 0;
		status = ST_IDLE;

		if (stream)
		{
			stream->send_offset = 0;
			stream->recv_offset = 0;
			stream->OnDisconnected();
		}
	}
}

// on read
void UdpConnection::OnRead()
{
	readable = true;

	if (stream)
		ReceiveMessages();
}

void UdpConnection::OnWrite()
{
	writable = true;

	if (stream && stream->no_delay)
		SendMessages();
}

void UdpConnection::OnClose()
{
	Disconnect();
}

void UdpConnection::SendMessages()
{
	if (stream == NULL)
		return;

	if (status == ST_IDLE)
		return;

	double time = Event::GetTime();

	// check ack received time
	if (time - ack_recv_time > 5)
	{		
		ack_recv_time = time;

		if (--ack_timeout_retry <= 0)
		{
			log_write(LOG_DEBUG1, "connection timeout.");
			OnClose();
			return;
		}
	}

	if (time < send_time)
		return;

	if (status == ST_SYN_RECV)
		return;

	bool force_retry = false;

	if (status == ST_ESTABLISHED 
			|| status == ST_FIN_WAIT_1)
	{
		// enter quick retry when get 3 same ack
		if (ack_same_count > 3)
		{
			if (quick_retry == false)
			{
				quick_retry = true;
				force_retry = true;

				send_window_threshhold = send_window_control / 2;
				if (send_window_threshhold < 2) send_window_threshhold = 2;
				send_window_control = send_window_threshhold + ack_same_count - 1;
				if (send_window_control > send_window.window_size)
					send_window_control = send_window.window_size;
			}
			else
			{
				// in quick retry
				// send_window_control increase 1 when get same ack 
				send_window_control += 1;
				if (send_window_control > send_window.window_size)
					send_window_control = send_window.window_size;
			}
		}
		else
		{
			// quick retry finished when get new ack
			if (quick_retry == true)
			{
				send_window_control = send_window_threshhold;
				quick_retry = false;
			}
		}

		// enter slow start when send data timeout 
		for (byte i = send_window.begin; i != send_window.end; i++)
		{
			byte id = i % send_window.window_size;
			ushort size = send_window.seq_size[id];

			if (send_window.seq_retry_count[id] > 0 
					&& time >= send_window.seq_retry[id])
			{
				send_window_threshhold = send_window_control / 2;
				if (send_window_threshhold < 2) send_window_threshhold = 2;
				//send_window_control = 1;
				send_window_control = send_window_threshhold;
				//break;

				quick_retry = false;
				ack_same_count = 0;
				break;
			}
		}

		uint offset = 0;
		uint size = stream->send_offset;
		char * send_buffer = stream->send_buffer;

		// put buffer to send window
		while ((send_window.free_buffer_id < send_window.window_size) &&	// there is a free buffer
				(size > 0))
		{
			// if send window more than send_window_control, break
			if (send_window.end - send_window.begin > send_window_control)
				break;

			byte id = send_window.end % send_window.window_size;

			// allocate buffer
			byte buffer_id = send_window.free_buffer_id;
			send_window.free_buffer_id = send_window.buffer[buffer_id][0];

			// send window buffer
			byte * buffer = send_window.buffer[buffer_id];

			// packet header
			PacketHeader & packet = *(PacketHeader*)buffer;
			packet.status = status;
			packet.syn = send_window.end;
			packet.ack = recv_window.begin - 1;

			// copy data
			uint copy_offset = sizeof(packet);
			uint copy_size = send_window.buffer_size - copy_offset;
			if (copy_size > size)
				copy_size = size;

			if (copy_size > 0)
			{
				memcpy(buffer + copy_offset, send_buffer + offset, copy_size);

				size -= copy_size;
				offset += copy_size;
			}

			// add to send window
			send_window.seq_buffer_id[id] = buffer_id;
			send_window.seq_size[id] = copy_size + copy_offset;
			send_window.seq_time[id] = time;
			send_window.seq_retry[id] = time;
			send_window.seq_retry_time[id] = retry_time;
			send_window.seq_retry_count[id] = 0;
			send_window.end++;
		}

		// remove data from send buffer.
		if (offset > 0)
		{
			memmove(send_buffer, send_buffer + offset, size);
			stream->send_offset = size;
		}
	}

	// if there is no data to send, make an empty one
	if (send_window.begin == send_window.end)		
	{
		if (time >= send_data_time)
		{
			if (send_window.free_buffer_id < send_window.window_size)
			{
				byte id = send_window.end % send_window.window_size;

				// allocate buffer
				byte buffer_id = send_window.free_buffer_id;
				send_window.free_buffer_id = send_window.buffer[buffer_id][0];

				// send window buffer
				byte * buffer = send_window.buffer[buffer_id];

				// packet header
				PacketHeader & packet = *(PacketHeader*)buffer;
				packet.status = status;
				packet.syn = send_window.end;
				packet.ack = recv_window.begin - 1;

				// add to send window
				send_window.seq_buffer_id[id] = buffer_id;
				send_window.seq_size[id] = sizeof(packet);
				send_window.seq_time[id] = time;
				send_window.seq_retry[id] = time;
				send_window.seq_retry_time[id] = retry_time;
				send_window.seq_retry_count[id] = 0;
				send_window.end++;
			}
		}
	}
	else
		send_data_time = time + send_data_frequency;

	// send packets
	for (byte i = send_window.begin; i != send_window.end; i++)
	{
		// if send packets more than send_window_control, break
		if (i - send_window.begin >= send_window_control)
			break;

		byte id = i % send_window.window_size;
		ushort size = send_window.seq_size[id];

		// send packet
		if (time >= send_window.seq_retry[id] || force_retry)
		{
			force_retry = false;

			byte* buffer = send_window.buffer[send_window.seq_buffer_id[id]];

			// packet header
			PacketHeader & packet = *(PacketHeader*)buffer;
			packet.status = status;
			packet.syn = i;
			packet.ack = recv_window.begin - 1;

			int n = send(connected_socket, buffer, size, 0);

			if (n < 0)
			{
				log_write(LOG_DEBUG1, "send error(%m)");
				break;
			}
			else
				num_bytes_send += n + 28;

			// num send
			num_packets_send++;

			// num retry send
			if (time != send_window.seq_time[id])
				num_packets_retry++;

			send_time = time + send_frequency;
			send_data_time = time + send_data_frequency;
			send_ack = false;

			send_window.seq_retry_count[id]++;
			//send_window.seq_retry_time[id] *= 2;
			send_window.seq_retry_time[id] = 1.5 * retry_time;
			if (send_window.seq_retry_time[id] > 0.2) send_window.seq_retry_time[id] = 0.2;
			send_window.seq_retry[id] = time + send_window.seq_retry_time[id];
		}
	}

	// send ack
	if (send_ack)
	{
		PacketHeader packet;
		packet.status = status;
		packet.syn = send_window.begin - 1;
		packet.ack = recv_window.begin - 1;

		int n = send(connected_socket, &packet, sizeof(packet), 0);

		if (n < 0)
			log_write(LOG_DEBUG1, "send error(%m)");

		send_time = time + send_frequency;
		send_ack = false;
	}
}

void UdpConnection::ReceiveMessages()
{
	if (stream == NULL)
		return;

	if (status == ST_IDLE)
		return;

	// current time
	double time = Event::GetTime();

	// packet received
	bool packet_received = false;
	bool close_connection = false;

	// receive packets
	while (readable)
	{
		// allocate buffer
		byte buffer_id = recv_window.free_buffer_id;
		byte * buffer = recv_window.buffer[buffer_id];
		recv_window.free_buffer_id = buffer[0];

		// can't allocate buffer, disconnect.
		if (buffer_id >= recv_window.window_size)
		{
			Disconnect();
			return;
		}

		// receive packet
		int n = recv(connected_socket, buffer, recv_window.buffer_size, 0);

		if (n == 0)
		{
			Disconnect();
			return;
		}
		else if (n < (int)sizeof(PacketHeader))
		{
			// free buffer
			buffer[0] = recv_window.free_buffer_id;
			recv_window.free_buffer_id = buffer_id;

			if (n < 0)
				readable = false;

			continue;
		}

		// num bytes received
		num_bytes_received += n + 28;

		// packet header
		PacketHeader & packet = *(PacketHeader*)buffer;
		
		bool connect_success = false;
		if (status == ST_SYN_RECV)
		{
			// recv side wait for syn_send, send ack and syn_recv_wait
			if (packet.status == ST_SYN_SEND)
			{
				if (packet.ack == send_window.begin - 1)
				{
					// initialize recv window
					for (byte i = recv_window.begin; i != recv_window.end; i++)
					{
						byte id = i % recv_window.window_size;
						recv_window.seq_buffer_id[id] = recv_window.window_size;
						recv_window.seq_size[id] = 0;
						recv_window.seq_time[id] = 0;
						recv_window.seq_retry[id] = 0;
						recv_window.seq_retry_count[id] = 0;
					}

					status = ST_SYN_RECV_WAIT;
				}
				else
					continue;
			}
			else
				continue;
		}
		else if (status == ST_SYN_SEND)
		{
			// send side wait for syn_recv_wait, send ack
			if (packet.status == ST_SYN_RECV_WAIT)
			{
				if (packet.ack == send_window.begin)
				{
					connect_success = true;
				}
				else
					continue;
			}
			else
				continue;
		}
		else if (status == ST_SYN_RECV_WAIT)
		{
			// recv side wait for ack
			if (packet.status == ST_ESTABLISHED)
			{
				if (packet.ack == send_window.begin)
				{
					connect_success = true;
				}
				else
					continue;
			}
			else
				continue;
		}

		if (connect_success)
		{
			for (byte i = recv_window.begin; i != recv_window.end; i++)
			{
				byte id = i % recv_window.window_size;
				recv_window.seq_buffer_id[id] = recv_window.window_size;
				recv_window.seq_size[id] = 0;
				recv_window.seq_time[id] = 0;
				recv_window.seq_retry[id] = 0;
				recv_window.seq_retry_count[id] = 0;
			}

			status = ST_ESTABLISHED;

			// on connected
			stream->OnConnected();
		}

		// close connection
		if (packet.status == ST_FIN_WAIT_1)
		{
			if (status == ST_ESTABLISHED)
				status = ST_FIN_WAIT_2;

			close_connection = true;
		}

		if (status == ST_ESTABLISHED 
				|| status == ST_SYN_RECV_WAIT 
				|| status == ST_FIN_WAIT_1)
		{
			// receive ack, process send buffer.
			if (send_window.IsValidIndex(packet.ack))
			{
				// got a valid packet
				ack_recv_time = time;
				ack_timeout_retry = 3;

				// static value for calculate delay
				static const double err_factor = 0.125;
				static const double average_factor = 0.25;
				static const double retry_factor = 2;

				double rtt = delay_time;
				double err_time = 0;

				// send_window_control not more than double send_window_control 
				double send_window_control_max = send_window_control * 2;
				if (send_window_control_max > send_window.window_size)
					send_window_control_max = send_window.window_size;

				while (send_window.begin != (byte)(packet.ack + 1))
				{
					byte id = send_window.begin % send_window.window_size;
					byte buffer_id = send_window.seq_buffer_id[id];

					// calculate delay only use no retry packet
					if (send_window.seq_retry_count[id] == 1)
					{
						// rtt(packet delay)
						rtt = time - send_window.seq_time[id];
						// err_time(difference between rtt and delay_time)
						err_time = rtt - delay_time;
						// revise delay_time with err_time 
						delay_time = delay_time + err_factor * err_time;
						// revise delay_average with err_time
						delay_average = delay_average + average_factor * (fabs(err_time) - delay_average);
					}

					// free buffer
					send_window.buffer[buffer_id][0] = send_window.free_buffer_id;
					send_window.free_buffer_id = buffer_id;
					send_window.begin ++;

					// get new ack
					// if send_window_control more than send_window_threshhold in congestion avoidance,
					// else in slow start
					// in congestion avoidance send_window_control increase 1
					// in slow start send_window_control increase 1 when get send_window_control count ack
					if (send_window_control <= send_window_threshhold)
						send_window_control += 1;
					else
						send_window_control += 1 / send_window_control;

					if (send_window_control > send_window_control_max)
						send_window_control = send_window_control_max;
				}

				// calculate retry with delay_time and delay_average
				retry_time = delay_time + retry_factor * delay_average;
				if (retry_time < send_frequency) retry_time = send_frequency;
			}

			// get same ack
			if (ack_last == send_window.begin - 1)
				ack_same_count++;
			else
				ack_same_count = 0;

			// packet is valid
			if (recv_window.IsValidIndex(packet.syn))
			{
				byte id = packet.syn % recv_window.window_size;

				if (recv_window.seq_buffer_id[id] >= recv_window.window_size)
				{
					recv_window.seq_buffer_id[id] = buffer_id;
					recv_window.seq_size[id] = n;
					packet_received = true;

					// no more buffer, try parse first.
					if (recv_window.free_buffer_id >= recv_window.window_size)
						break;
					else
						continue;
				}
			}
		}

		// free buffer.
		buffer[0] = recv_window.free_buffer_id;
		recv_window.free_buffer_id = buffer_id;
	}

	if (send_window.begin == send_window.end)
		ack_same_count = 0;

	// record ack last
	ack_last = send_window.begin - 1;

	// update recv window
	if (packet_received)
	{
		byte last_ack = recv_window.begin - 1;
		byte new_ack = last_ack;
		bool parse_message = false;

		// calculate new ack
		for (byte i = recv_window.begin; i != recv_window.end; i++)
		{
			// recv buffer is invalid
			if (recv_window.seq_buffer_id[i % recv_window.window_size] >= recv_window.window_size)
				break;

			new_ack = i;
		}

		// ack changed
		if (new_ack != last_ack)
		{
			while (recv_window.begin != (byte)(new_ack + 1))
			{
				const byte head_size = sizeof(PacketHeader);
				byte id = recv_window.begin % recv_window.window_size;
				byte buffer_id = recv_window.seq_buffer_id[id];
				byte * buffer = recv_window.buffer[buffer_id] + head_size;
				ushort size = recv_window.seq_size[id] - head_size;

				// copy buffer
				if (stream->recv_offset + size < stream->recv_buffer_size)
				{
					// add data to receive buffer
					memcpy(stream->recv_buffer + stream->recv_offset, buffer, size);
					stream->recv_offset += size;

					// free buffer
					recv_window.buffer[buffer_id][0] = recv_window.free_buffer_id;
					recv_window.free_buffer_id = buffer_id;

					// remove sequence
					recv_window.seq_size[id] = 0;
					recv_window.seq_buffer_id[id] = recv_window.window_size;
					recv_window.begin++;
					recv_window.end ++;

					// mark for parse message
					parse_message = true;

					// send ack when get packet
					send_ack = true;
				}
				else
				{
					if (stream->RecvBufferResize(2 * stream->recv_buffer_size) == false)
						break;
				}
			}
		}

		// record receive syn last
		syn_last = recv_window.begin - 1;

		// parse message
		if (parse_message)
		{
			try
			{
				stream->OnParseMessage();
			}
			catch (...)
			{
				close_connection = true;
			}
		}
	}

	if (close_connection)
		OnClose();
}
