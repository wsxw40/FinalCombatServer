#pragma once

#include <list>
#include "mutex.h"

struct ApexReq
{
	enum ApexReqType
	{
		Type_None,
		Type_Action,
		Type_Send,
	};
	
	uint uid;
	
	uint type;
	uint user_id;
	struct ReqData
	{
		struct
		{
			int Action;
		};
		
		struct
		{
			char *pBuf;
			int nBufLen;
			int nAllocBufLen;
		};
	}req_data;
	
	ApexReq()
		: type(ApexReq::Type_None)
		, user_id(0)
	{
		memset(&req_data, 0, sizeof(req_data));
	}
	
	~ApexReq()
	{
		delete [] req_data.pBuf;
		req_data.pBuf = NULL;
		req_data.nBufLen = 0;
		req_data.nAllocBufLen = 0;
	}
};

class ApexReqPool
{
public:
	// constructor
	ApexReqPool();
	
	// destructor
	~ApexReqPool();
	
public:
	// initialize
	bool Initialize(uint max_size);

public:
	// add request
	bool AddRequest(uint userid, const char * pBuffer, int nLen);

	// add request
	bool AddRequest(uint userid, int Action);
	
	// get request
	void GetRequest(std::list<ApexReq*> &reqs);
	
	// remove request
	void RemoveRequest(std::list<ApexReq*> &reqs);

private:
	// req list
	std::list<ApexReq*> req_list;
	
	// req pool
	ObjectPool<ApexReq> reg_pool;
	
	// mutex
	Mutex mutex;
};

