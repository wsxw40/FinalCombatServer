#pragma once
#include "event.h"

class TcpListener : public IEvent
{
public:
	// constructor
	TcpListener();

	// destructor
	~TcpListener();

	// initialize
	bool Initialize();
	
	// terminate
	void Terminate();

protected:
	// on client connected
	virtual void OnClientConnected(int socket, sockaddr_in addr) = 0;

private:
	// on read
	virtual void OnRead();

	// on write
	virtual void OnWrite();

	// on close
	virtual void OnClose();

public:
	SOCKET listen_socket;
	sockaddr_in addr;
};
