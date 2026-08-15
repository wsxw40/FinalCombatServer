#pragma once

#include <list>
#include "mutex.h"

struct ApexXLAck
{
	uint uid;
	struct AckData
	{
		struct
		{
			char *pBuf;
			int nBufLen;
			int nAllocBufLen;
		};
	}ack_data;
	
	ApexXLAck()
	{
		memset(&ack_data, 0, sizeof(ack_data));
	}
	
	~ApexXLAck()
	{
		delete [] ack_data.pBuf;
		ack_data.pBuf = NULL;
		ack_data.nBufLen = 0;
		ack_data.nAllocBufLen = 0;
	}
};

class FWGXLAckPool
{
public:
	// constructor
	FWGXLAckPool();
	
	// destructor
	~FWGXLAckPool();
	
public:
	// initialize
	bool Initialize(uint max_size);

public:
	// add request
	bool AddAck(const char * pBuffer, int nLen);

	// get request
	void GetAck(std::list<ApexXLAck*> &listAcks);
	
	// remove request
	void RemoveAck(std::list<ApexXLAck*> &listAcks);

private:
	// req list
	std::list<ApexXLAck*> ack_list;
	
	// req pool
	ObjectPool<ApexXLAck> ack_pool;
	
	// mutex
	Mutex mutex;
};

