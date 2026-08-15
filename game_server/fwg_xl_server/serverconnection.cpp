#include "pch.h"

enum EServerMessage
{
	SM_ServerDataUpdate,
	SM_UserKill,
};

enum EClientMessage
{
	CM_UserLogIn,
	CM_UserLogOut,
};

// constructor
ServerConnection::ServerConnection()
	:BinaryStream(appcfg.server_buffersize)
{
	connection = this;
	stream = this;
}

// on connected
void ServerConnection::OnConnected()
{
	log_write(LOG_INFO, "client connected: %s", inet_ntoa(client_ip));
}

// on disconnected
void ServerConnection::OnDisconnected()
{
	log_write(LOG_INFO, "client disconnected: %s", inet_ntoa(client_ip));

	// noitfy all user offline
	for (std::set<UserOnlineInfo>::const_iterator i = user_online_set.begin(); i != user_online_set.end(); ++i)
		server.NoticeXLFWGUserLogout(i->m_strGameId, i->m_strServerId, i->m_dwRoleId, i->m_strCustomerid);
		
	user_online_set.clear();
	
	server.connection_pool.Free(uid);
}

// on message
void ServerConnection::OnMessage()
{
	byte msg = 255;
	
	try
	{
		ReadByte(msg);

		switch (msg)
		{
		case CM_UserLogIn:
			{
				UserOnlineInfo info = {0};
				ReadString(info.m_strServerId, 64);
				ReadInt(info.m_dwRoleId);
				ReadString(info.m_strCustomerid, 64);
				
				user_online_set.insert(info);
				server.NoticeXLFWGUserLogin(std::string(info.m_strGameId), std::string(info.m_strServerId), info.m_dwRoleId, std::string(info.m_strCustomerid));
			}
			break;

		case CM_UserLogOut:
			{
				UserOnlineInfo info = {0};
				ReadString(info.m_strServerId, 64);
				ReadInt(info.m_dwRoleId);
				ReadString(info.m_strCustomerid, 64);
				
				user_online_set.erase(info);
				server.NoticeXLFWGUserLogout(std::string(info.m_strGameId), std::string(info.m_strServerId), info.m_dwRoleId, std::string(info.m_strCustomerid));
			}
			break;
		}
	}
	catch (...)
	{
		log_write(LOG_ERROR, "ServerConnection::OnMessage() exp. msg = %d", msg);
	}
}

// notify killuser
void ServerConnection::NotifyKillUser(uint userid, int Action)
{
	const uint packet_size = BinaryStream::HEAD_SIZE + 1 + 4 + 4;
	if (!CheckConnectionSendBuffer(packet_size))
	{
		log_write(LOG_INFO, "ServerConnection::NotifyKillUser() : size too big");
		
		return;
	}
		
	BeginWrite();
	WriteByte(SM_UserKill);
	WriteInt(userid);
	WriteInt(Action);
	EndWrite();
}

bool ServerConnection::CheckConnectionSendBuffer(uint size)
{
	uint left = NetworkStream::MAX_SEND_BUFFER_SIZE - send_offset;
	
	if (size >= BinaryStream::MAX_PKGSIZE)
	{
		log_write(LOG_INFO, "CheckConnectionSendBuffer() : size big than MAX_PKGSIZE");
		
		return false;
	}
	else if (size >= left)
	{
		ForcedSendMessages();
		
		log_write(LOG_INFO, "CheckConnectionSendBuffer() : try ForcedSendMessages()");
	}
	
	return true;
}
