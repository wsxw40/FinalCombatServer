#pragma once
#include "networkstream.h"

class TcpConnection : public NetworkConnection
{
public:
	// constructor
	TcpConnection();

	// destructor
	~TcpConnection();
	
	// is connected
	bool IsConnected();

	// is closed
	bool IsConnecting();

	// is idle
	bool IsIdle();

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
	void OnRead();

	// on write
	void OnWrite();

	// on close
	void OnClose();

public:
	// send messages
	virtual void SendMessages();

	// recieve messages 
	virtual void ReceiveMessages();
	
	// send messages
	virtual void ForcedSendMessages();

public:
	// connected socket
	SOCKET connected_socket;

	bool readable;
	bool writable;
	bool connecting;

	// bytes send and recieved
	uint num_bytes_send;
	uint num_bytes_received;
	double delay_time;
};


