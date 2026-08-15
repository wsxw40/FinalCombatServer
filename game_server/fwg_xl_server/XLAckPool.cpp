#include "pch.h"

// constructor
FWGXLAckPool::FWGXLAckPool()
{
}

// destructor
FWGXLAckPool::~FWGXLAckPool()
{
}

// initialize
bool FWGXLAckPool::Initialize(uint max_size)
{
	// initialize req pool.
	if (!ack_pool.Initialize(max_size))
	{
		log_write_sys(LOG_ERROR, "cannot initialize reg pool.");
		
		return false;
	}
	
	return true;
}

// add request
bool FWGXLAckPool::AddAck(const char * pBuffer, int nLen)
{
	AutoLock auto_lock(mutex);
	
	ApexXLAck *req = ack_pool.Allocate();
	if (req)
	{
		if (!req->ack_data.pBuf || req->ack_data.nAllocBufLen < nLen)
		{
			delete [] req->ack_data.pBuf;
			
			req->ack_data.pBuf = new char[nLen];
			req->ack_data.nAllocBufLen = nLen;
		}
		
		memcpy(req->ack_data.pBuf, pBuffer, nLen);
		req->ack_data.nBufLen = nLen;
		
		ack_list.push_back(req);
		
		return true;
	}
	
	log_write_sys(LOG_WARNING, "can't allocate more ApexXLAck. ack_list.size() : %d", ack_list.size());
	
	return false;
}

// get request
void FWGXLAckPool::GetAck(std::list<ApexXLAck*> &listAcks)
{
	listAcks.clear();
	
	{
		AutoLock auto_lock(mutex);
		
		ack_list.swap(listAcks);
	}
	
	return;
}

// remove request
void FWGXLAckPool::RemoveAck(std::list<ApexXLAck*> &listAcks)
{
	AutoLock auto_lock(mutex);
	
	for (std::list<ApexXLAck*>::const_iterator itr = listAcks.begin(); itr != listAcks.end(); itr++)
	{
		ack_pool.Free((*itr)->uid);
	}
	
	listAcks.clear();
	
	return;
}
