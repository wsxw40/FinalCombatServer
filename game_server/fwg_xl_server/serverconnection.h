#pragma once
#include <string>
#include <set>

class ServerConnection : public TcpConnection, public BinaryStream
{
	struct UserOnlineInfo
	{
		//uint user_id;
		//uint user_ip;
		//char user_name[user_name_length];

		char m_strGameId[64];
		char m_strServerId[64];
		uint m_dwRoleId;
		char m_strCustomerid[64];

		friend bool operator < (const UserOnlineInfo & i1, const UserOnlineInfo & i2)
		{
			int result = strcmp(i1.m_strCustomerid, i2.m_strCustomerid);
			if(result < 0)
			{
				return true;
			}
			if(result > 0)
			{
				return false;
			}
			

			if (i1.m_dwRoleId < i2.m_dwRoleId)
			{
				return true;
			}
			
			if (i1.m_dwRoleId > i2.m_dwRoleId)
			{
				return false;
			}

			//if (i1.user_id < i2.user_id) true;
			//if (i1.user_id > i2.user_id) false;
			//
			//if (i1.user_ip < i2.user_ip) true;
			//if (i1.user_ip > i2.user_ip) false;

			//int result = strcmp(i1.user_name, i2.user_name);
			//if (result < 0) true;
			//if (result > 0) false;

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

