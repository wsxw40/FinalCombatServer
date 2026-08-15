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
	CM_UserDataUpdate,
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
		server.NoticeApexProxy_UserLogout(i->user_id, i->user_name);
		
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
				ReadInt(info.user_id);
				ReadInt(info.user_ip);
				ReadString(info.user_name, sizeof(info.user_name));
				
				user_online_set.insert(info);
				server.NoticeApexProxy_UserLogin(info.user_id, info.user_name, info.user_ip);
			}
			break;

		case CM_UserLogOut:
			{
				UserOnlineInfo info = {0};
				ReadInt(info.user_id);
				ReadInt(info.user_ip);
				ReadString(info.user_name, sizeof(info.user_name));
				
				user_online_set.erase(info);
				server.NoticeApexProxy_UserLogout(info.user_id, info.user_name);
			}
			break;
			
		case CM_UserDataUpdate:
			{
				uint userid;
				uint data_size;

				ReadInt(userid);
				ReadInt(data_size);

				server.NoticeApexProxy_UserData(userid, (const char *)ReadData(data_size), data_size);
			}
			break;
		}
	}
	catch (...)
	{
		log_write(LOG_ERROR, "ServerConnection::OnMessage() exp. msg = %d", msg);
	}
}

// notify server data
void ServerConnection::NotifyServerData(uint userid, const char * pBuffer, int nLen)
{
	if (pBuffer)
	{
		const uint packet_size = BinaryStream::HEAD_SIZE + 1 + 4 + 4;
		if (!CheckConnectionSendBuffer(packet_size + nLen))
		{
			log_write(LOG_INFO, "ServerConnection::NotifyServerData() : size too big");
			
			return;
		}
	
		BeginWrite();
		WriteByte(SM_ServerDataUpdate);
		WriteInt(userid);
		WriteInt(nLen);
		Write(pBuffer, nLen);
		EndWrite();
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
