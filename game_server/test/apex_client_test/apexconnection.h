#pragma once

class ApexConnection : public TcpConnection, public BinaryStream
{
public:
	// constructor
	ApexConnection();

	// on connected
	void OnConnected();

	// on close
	void OnDisconnected();

	// on message
	void OnMessage();

	// on server data update
	void OnServerDataUpdate();

	// on kill user
	void OnUserKill();

public:
	// client login
	void ClientLogIn(uint user_id, uint ip, const char * user_name);

	// client logout
	void ClientLogOut(uint user_id, uint ip, const char * user_name);
	
	// client dataupdate
	void ClientDataUpdate(uint user_id, const char * data, uint size);
	
private:
	bool CheckConnectionSendBuffer(uint size);

public:
	sockaddr_in addr;
};

