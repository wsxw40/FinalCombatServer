#pragma once
#include "networkstream.h"

class LocalConnection : public NetworkConnection
{
public:
	// constructor
	LocalConnection();
	
	// destructor
	~LocalConnection();

	// is connected
	bool IsConnected();

	// connect
	void Connect(SOCKET socket);

	// exit
	void Disconnect();

	// do write
	void SendMessages();

	// recieve messages 
	void ReceiveMessages();

	// read
	void ReadFD(int& fd);

	// write
	void WriteFD(int fd);

protected:
	// on read
	void OnRead();

	// on write
	void OnWrite();

	// on close
	void OnClose();

public:
	// connected socket
	SOCKET connected_socket;

	bool readable;
	bool writable;

	static const uint fd_buffer_size = 32;
	uint fd_send_buffer[fd_buffer_size];
	uint fd_recv_buffer[fd_buffer_size];
	uint fd_send_offset;
	uint fd_recv_offset;

	// bytes send and recieved
	uint num_bytes_send;
	uint num_bytes_received;
};
