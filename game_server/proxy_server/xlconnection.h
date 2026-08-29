#pragma once




struct XLInfo
{
	char account[21];
	byte fcmflag;
	byte mbkflag;
	
	
	char xlaccount_level[3];
	char xlaccount_time_yyddmm[11];
	char xlaccount_lefttime[6];
	
	char xl_vip_account_level[3];
	char xl_title_account_level[3];
	byte xl_gender;
	int	 xl_point_count;
	int  xl_exp;
	char xl_bar_level[3];
	short account_type;



	XLInfo()
	{
		memset(account,0,sizeof(account));
		fcmflag = 0;
		mbkflag = 0;

		memset(xlaccount_level,0,sizeof(xlaccount_level));
		memset(xlaccount_time_yyddmm,0,sizeof(xlaccount_time_yyddmm));
		memset(xlaccount_lefttime,0,sizeof(xlaccount_lefttime));

		memset(xl_vip_account_level,0,sizeof(xl_vip_account_level));
		memset(xl_title_account_level,0,sizeof(xl_title_account_level));
		xl_gender = 0;
		xl_point_count = 0;
		xl_exp = 0;
		memset(xl_bar_level,0,sizeof(xl_bar_level));
		account_type = 0;
	}
};

class XLConnection : public TcpConnection, public XLNetworkStream
{
public:
	// constructor
	XLConnection();

	// on connected
	void OnConnected();

	// on close
	void OnDisconnected();

	// on message
	void OnMessage();

	// on response real name auth.
	void OnResponseRealNameAuth();

	unsigned short GetRandomID()
	{
		return random_id++;
	}

public:
	//// update fcm
	//void UpdateFCM(bool online, const char * userid, const char * roleid, const char * ipaddr);

	//// request realname auth
	//void RequestRealNameAuth(uint user_id, const char * account_id);

public:
	sockaddr_in addr;

	char login_conmand[11];

	// online accounts
	__gnu_cxx::hash_map<unsigned short,uint> clientconnection_map;

private:
	unsigned short random_id;


};

class XLFCMConnection : public TcpConnection, public XLFCMNetworkStream
{
public:
	// constructor
	XLFCMConnection();

	// on connected
	void OnConnected();

	// on close
	void OnDisconnected();

	// on message
	void OnMessage();

	void RequestHeatBeat(ClientConnection* client, bool online = true);

	bool OnResponseHeatBeat();

	unsigned int GetRandomID()
	{
		return random_id++;
	}

public:
	sockaddr_in addr;

	unsigned int random_id;

	// online accounts
	__gnu_cxx::hash_map<unsigned int,uint> clientconnection_map;
};




