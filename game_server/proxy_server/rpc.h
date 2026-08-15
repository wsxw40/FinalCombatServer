#pragma once

class InfoConnection;

class RpcRequest : public BinaryStream
{
public:
	enum Type
	{
		kNone,
		kText,
		kBinary,
	};
	typedef void (*RpcCallback)(RpcRequest * request);

public:
	RpcRequest();

public:
	// write text rpc
	void WriteTextRPC(const char * url, RpcCallback callback = NULL);
	void WriteBinaryRPC(const char * url, RpcCallback callback = NULL);
	void EndRPCUserdata();

	void WriteParameter(const char * key, const char * data, int size);
	void WriteParameter(const char * key, const char * data);
	void WriteParameterf(const char * key, const char * format, ...);

	void BeginWrite();
	void EndWrite();

public:
	bool OnResponse(char * data, int size);

public:
	uint uid;
	Type type;
	uint queue;
	double timeout;
	double request_time;
	double process_time;
	RpcCallback callback;
	char url[256];
	uint error;
	InfoConnection *processing_info;

public:
	int userdata_offset;
	int userdata_size;
	int data_offset;
	int data_size;
};

class RpcQueue
{
public:
	enum QueueId
	{
		kClientQueue,
		kCreateQueue,
		kLoginQueue,
		kServerQueue,
		kGameQueue,
		
		kQueueCount,
	};
public:
	RpcQueue();

	// initialize
	bool Initialize();

	// update
	void OnUpdate(double time);

	// dispatch
	void DispatchRequests();

	// dispatch
	void DispatchRequests(uint queue_id);

	// cancel
	void CancelRPC(uint queue_id, uint rpc_id);

	// get
	RpcRequest* Get(uint queue_id, uint rpc_id);

	// allocate
	RpcRequest* Allocate(uint queue_id);

	// free rpc
	void Free(uint queue_id, uint rpc_id);

public:
	ObjectPool<RpcRequest> request_pool[kQueueCount];
	std::deque<uint> request_queue[kQueueCount];
};
