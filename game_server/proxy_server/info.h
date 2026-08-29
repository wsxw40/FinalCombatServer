#pragma once

class InfoConnection : public TcpConnection, public BinaryStream
{
public:
	// constructor
	InfoConnection();

	// destructor
	~InfoConnection();
	
	// on client connected
	void OnConnected();

	// on disconnected
	void OnDisconnected();

	// on message
	void OnMessage();

	// response character info
	void ResponseCharacterInfo(uint client_id);

	// notify character online
	void NotifyCharacterOnline(const char * userid, const char * characterid, const char * ip);

	// notify character online
	void NotifyCharacterOffline(const char * userid, const char * characterid, const char * ip);

	// cancel rpc
	void CancelRPC(uint queue_id, uint uid);

	// request rpc
	void RequestRPC(uint queue_id, RpcRequest * request);
	
	// sync queue
	void SyncRPCQueue(uint queue_id);

	// on update
	void Update();
	
	void RequestLeagueGameInfo();
	void RequestLeagueGameInfoTime();

public:
	void WriteResponseHeader();

protected:
	// on response rpc
	void OnResponseRPC();

	// send chat
	void OnSendChat();

	// broadcast proxy chat
	void OnBroadcastProxyChat();

	// broadcast server chat
	void OnBroadcastServerChat();

	// broadcast channel chat
	void OnBroadcastChannelChat();

	// on request online info
	void OnRequestOnlineInfo();

	// on request status
	void OnRequestStatus();

	// on notify client
	void OnNotifyClient();

	// on update character info
	void OnUpdateCharacterInfo();
	
	// on request keywords
	void OnRequestKeywords();

	// on request serverlist
	void OnRequestServerList();
	
	// on request levellist
	void OnRequestLevelList();
	
	// on request notice
	void OnRequestNotice();
	
	// on request blacklist
	void OnRequestBlackList();
	
	// on kick player
	void OnKickPlayer();
	
	// on request sysconfig
	void OnRequestSysConfig();

	void OnBroadcastLoopMsg();

	// on sync rpc queue
	void OnSyncRPCQueue();

	void OnUpdateBillBoardList();
	
	void OnLeagueGameInfo();
	void OnResponseLeagueGameInfoTime();

public:
	struct RpcStatus
	{
		int queue_size;
		uint request_count_total;
		uint request_count_per_second;
	};

	uint uid;
	sockaddr_in addr;
	bool will_disconnect;

	bool online;

	double rpc_queue_sync_time;
	double rpc_quene_disconnect_time;

	RpcStatus rpc_status[RpcQueue::kQueueCount];
};

