#pragma once
#include "event.h"
#include "objectpool.h"

class UdpListener : public IEvent
{
public:
	// constructor
	UdpListener();

	// destructor
	~UdpListener();

	// initialize
	bool Initialize();
	
	// terminate
	void Terminate();

protected:
	// on client connected
	virtual void OnClientConnected(int socket, sockaddr_in ip) = 0;

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

private:
	struct AcceptReq
	{
		AcceptReq* next;
		sockaddr_in addr;
		SOCKET accept_socket;
		uint uid;
	};

	int accept_queue_len;

	static const uint UDP_ACCEPT_HASH_SIZE = 64;
	static const uint UDP_ACCEPT_MAX_SIZE = 2048;

	AcceptReq* accept_queue[UDP_ACCEPT_HASH_SIZE];
	ObjectPool<AcceptReq> accept_pool;

private:
	// generate accept hash
	uint GenerateAcceptHash(const sockaddr_in & addr);

	// get accept req
	AcceptReq* GetAcceptReq(const sockaddr_in & addr);

	// add accept req
	void AddAcceptReq(AcceptReq* req);

	// remove accept req
	void RemoveAcceptReq(const sockaddr_in & addr);
};
