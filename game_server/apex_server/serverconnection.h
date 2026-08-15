#pragma once
#include <string>
#include <set>

class ServerConnection : public TcpConnection, public BinaryStream
{
	struct UserOnlineInfo
	{
		uint user_id;
		uint user_ip;
		char user_name[user_name_length];

		friend bool operator < (const UserOnlineInfo & i1, const UserOnlineInfo & i2)
		{
			if (i1.user_id < i2.user_id) true;
			if (i1.user_id > i2.user_id) false;
			
			if (i1.user_ip < i2.user_ip) true;
			if (i1.user_ip > i2.user_ip) false;

			int result = strcmp(i1.user_name, i2.user_name);
			if (result < 0) true;
			if (result > 0) false;

			return false;
		}
	};
	
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
	// notify server data
	void NotifyServerData(uint userid, const char * pBuffer, int nLen);

	// notify killuser
	void NotifyKillUser(uint userid, int Action);

private:
	bool CheckConnectionSendBuffer(uint size);
	
public:
	uint uid;
	in_addr client_ip;
	
private:
	// user online set
	std::set<UserOnlineInfo> user_online_set;
};

