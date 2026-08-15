#pragma once

class ServerConnection : public TcpConnection, public BinaryStream
{
public:
	// constructor
	ServerConnection();

	// on connected
	void OnConnected();

	// on close
	void OnDisconnected();

	// on message
	void OnMessage();

public:
	uint uid;
	in_addr client_ip;
};

