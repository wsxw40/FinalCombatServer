#pragma once

class GmConnection : public TcpConnection, public TextStream
{
public:
	// constructor
	GmConnection();

	// destructor
	~GmConnection();
	
public:
	// on client connected
	void OnConnected();

	// on disconnected
	void OnDisconnected();

	// on message
	void OnMessage();

public:
	uint uid;
	in_addr client_ip;
};

