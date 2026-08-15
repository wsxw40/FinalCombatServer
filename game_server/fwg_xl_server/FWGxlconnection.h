#pragma once

class FWGXLConnection : public TcpConnection, public XLFWGStream
{
public:
	// constructor
	FWGXLConnection();

	virtual ~FWGXLConnection();

	// on connected
	void OnConnected();

	// on close
	void OnDisconnected();

	// on message
	void OnMessage();

public:
	// client login
	void ClientLogIn( std::string strGameId, std::string strServerId, uint dwRoleId, std::string strCustomerId);

	// client logout
	void ClientLogOut( std::string strGameId, std::string strServerId, uint dwRoleId, std::string strCustomerId);
	
private:
	bool CheckConnectionSendBuffer(uint size);

public:
	sockaddr_in& GetSockaddr(){return addr;}

public:
	sockaddr_in addr;
};

