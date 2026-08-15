#pragma once
#include "networkstream.h"

// sliding window uses in reliable udp transfer.
class SlidingWindow
{
public:
	// count
	inline byte Count()
	{
		return end - begin;
	}

	// is valid id
	inline bool IsValidIndex(byte id)
	{
		byte pos = id - begin;
		byte count = end - begin;
		return pos < count;
	}
	
	// clear
	inline void ClearBuffer()
	{
		// link buffers
		free_buffer_id = 0;
		for (int i = 0; i < window_size; i++)
			buffer[i][0] = i + 1;
	}

public:
	static const uint buffer_size = 512;
	static const uint window_size = 32;

	// temp buffer
	byte buffer[window_size][buffer_size];
	byte free_buffer_id;

	byte seq_buffer_id[window_size];
	ushort seq_size[window_size];
	double seq_time[window_size];
	double seq_retry[window_size];
	double seq_retry_time[window_size];
	uint seq_retry_count[window_size];

	byte begin;	// begin position of sliding window
	byte end;	// end position of sliding window
};

// reliable udp connection
class UdpConnection : public NetworkConnection
{
public:
	// constructor
	UdpConnection();

	// destructor
	~UdpConnection();
	
	// is idle
	bool IsIdle();

	// is connected
	bool IsConnected();

	// is closed
	bool IsConnecting();

	// connect
	void Connect(in_addr_t ip, ushort port);

	// connect
	void Connect(SOCKET socket, const sockaddr_in & addr);

	// connect
	void Connect(const sockaddr_in & addr);

	// exit
	void Disconnect(bool close_socket = true);

public:
	// on read
	virtual void OnRead();

	// on write
	virtual void OnWrite();

	// on close
	virtual void OnClose();

public:
	// send messages
	void SendMessages();

	// recieve messages 
	void ReceiveMessages();

public:
	// connected socket
	SOCKET connected_socket;

	bool readable;
	bool writable;

	// bytes send and recieved
	uint num_bytes_send;
	uint num_bytes_received;

	// network delay time.
	double delay_time;
	double delay_average;
	double retry_time;
	double send_time;
	double send_frequency;
	double send_window_control;
	double send_window_threshhold;
	double send_data_time;
	double send_data_frequency;

	SlidingWindow recv_window;
	SlidingWindow send_window;

	// packets count send and retry
	uint num_packets_send;
	uint num_packets_retry;


private:
	double ack_recv_time;
	int ack_timeout_retry;
	uint status;

	int ack_same_count;
	bool quick_retry;
	bool send_ack;
	byte ack_last;
	byte syn_last;
};
