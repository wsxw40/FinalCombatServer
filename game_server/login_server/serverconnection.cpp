#include "pch.h"

static unsigned char s_EncodeKey[16] = {49,106,-96,51,23,-12,-97,54,90,-22,-16,-6,-16,96,62,-106};

/// Decode function
int Decode(const char *in, int in_size, char *out)
{
	aes_ctx aes;
	unsigned char buffer[4096] = {0};

	aes_set_key(&aes, s_EncodeKey, 16);

	int bin_size = Base64::calculateBinLen(in_size);
	if (bin_size > 4096)
		return -1;

	Base64::base64ToBin(in, buffer, 4096);

	int out_size = ((bin_size + 15) / 16) * 16 + 4;

	if (AesDecrypt(&aes, buffer, (unsigned char*)out) != 0)
		return -1;

	return out_size;
}

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
	
	server.connection_pool.Free(uid);
}

// on message
void ServerConnection::OnMessage()
{
	uint _uid = 0;
	char login_info[4096] = {0};
	char login_data[4096] = {0};
	
	try
	{
		char account[4096] = {0};
		int level = 0;
		
		bool is_ok = false;
		
		ReadInt(_uid);
		ReadString(login_info, sizeof(login_info));
		
		int ret = Decode(login_info, strlen(login_info), login_data);
		if (ret > 0)
		{
			log_write(LOG_DEBUG4, "Decode(%s) = %d. _uid = %d", login_info, ret, _uid);
			
			if (ret > 4 + 4 + 4)
			{
				char *read = &login_data[0];
				if (*(int*)read == 0x43211234)
				{
					read += sizeof(int);
					
					int account_size = *(int*)read;
					read += sizeof(int);
					
					memcpy(account, read, account_size);
					account[account_size] = '\0';
					read += account_size;
					
					level = *(int*)read;
					
					is_ok = true;
				}
			}
		}
		
		BeginWrite();
		WriteInt(_uid);
		if (!is_ok)
		{
			WriteByte((byte)0);
			WriteString("");
			WriteShort((ushort)0);
			WriteShort((ushort)0);
			WriteShort((ushort)0);
		}
		else
		{
			WriteByte((byte)1);
			WriteString(account);
			WriteShort((ushort)0);
			WriteShort((ushort)0);
			WriteShort((ushort)level);
		}
		EndWrite();
	}
	catch (...)
	{
		log_write(LOG_ERROR, "ServerConnection::OnMessage() exp. _uid = %d", _uid);
	}
}
