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
ApexConnection::ApexConnection()
	:BinaryStream(32 * 1024)
{
	stream = this;
	connection = this;
}

// on connected
void ApexConnection::OnConnected()
{
	log_write_sys(LOG_INFO, "apex server connected: %s:%d", inet_ntoa(addr.sin_addr), ntohs(addr.sin_port));
}

static void ReconnectSdo(ApexConnection * conn)
{
	conn->Connect(conn->addr);
}

// on disconnected
void ApexConnection::OnDisconnected()
{
	if (!connecting)
	{
		log_write_sys(LOG_INFO, "apex server disconnected: %s:%d.", inet_ntoa(addr.sin_addr), ntohs(addr.sin_port));
	}

	Event::AddTimer((EventHandler)&ReconnectSdo, this, 5);
}

// on message
void ApexConnection::OnMessage()
{
	byte msg = 255;
	
	try
	{
		ReadByte(msg);

		switch (msg)
		{
		case SM_ServerDataUpdate:	OnServerDataUpdate(); break;
		case SM_UserKill:			OnUserKill(); break;
		}
	}
	catch (...)
	{
		log_write(LOG_ERROR, "ApexConnection::OnMessage() exp. msg = %d", msg);
	}
}

// client login
void ApexConnection::ClientLogIn(uint user_id, uint ip, const char * user_name)
{
	if (user_name)
	{
		const uint packet_size = BinaryStream::HEAD_SIZE + 1 + 4 + 4 + 4;
		if (!CheckConnectionSendBuffer(packet_size + strlen(user_name)))
		{
			log_write(LOG_INFO, "ApexConnection::ClientLogIn() : size too big");
			
			return;
		}
		
		BeginWrite();
		WriteByte(CM_UserLogIn);
		WriteInt(user_id);
		WriteInt(ip);
		WriteString(user_name);
		EndWrite();
	}
}

// client logout
void ApexConnection::ClientLogOut(uint user_id, uint ip, const char * user_name)
{
	if (user_name)
	{
		const uint packet_size = BinaryStream::HEAD_SIZE + 1 + 4 + 4 + 4;
		if (!CheckConnectionSendBuffer(packet_size + strlen(user_name)))
		{
			log_write(LOG_INFO, "ApexConnection::ClientLogOut() : size too big");
			
			return;
		}
		
		BeginWrite();
		WriteByte(CM_UserLogOut);
		WriteInt(user_id);
		WriteInt(ip);
		WriteString(user_name);
		EndWrite();
	}
}

// client dataupdate
void ApexConnection::ClientDataUpdate(uint user_id, const char * data, uint size)
{
	if (data)
	{
		const uint packet_size = BinaryStream::HEAD_SIZE + 1 + 4 + 4;
		if (!CheckConnectionSendBuffer(packet_size + size))
		{
			log_write(LOG_INFO, "ApexConnection::ClientDataUpdate() : size too big");
			
			return;
		}
		
		BeginWrite();
		WriteByte(CM_UserDataUpdate);
		WriteInt(user_id);
		WriteInt(size);
		Write(data, size);
		EndWrite();
	}
}

// on server data update.
void ApexConnection::OnServerDataUpdate()
{
	uint userid;
	uint data_size;

	ReadInt(userid);
	ReadInt(data_size);
}

// on kill user.
void ApexConnection::OnUserKill()
{
	uint user_id;
	int Action;

	ReadInt(user_id);
	ReadInt(Action);
}

bool ApexConnection::CheckConnectionSendBuffer(uint size)
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
