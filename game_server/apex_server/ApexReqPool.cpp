#include "pch.h"

// constructor
ApexReqPool::ApexReqPool()
{
}

// destructor
ApexReqPool::~ApexReqPool()
{
}

// initialize
bool ApexReqPool::Initialize(uint max_size)
{
	// initialize req pool.
	if (!reg_pool.Initialize(max_size))
	{
		log_write_sys(LOG_ERROR, "cannot initialize reg pool.");
		
		return false;
	}
	
	return true;
}

// add request
bool ApexReqPool::AddRequest(uint userid, const char * pBuffer, int nLen)
{
	AutoLock auto_lock(mutex);
	
	ApexReq *req = reg_pool.Allocate();
	if (req)
	{
		req->type = ApexReq::Type_Send;
		req->user_id = userid;
		
		if (!req->req_data.pBuf || req->req_data.nAllocBufLen < nLen)
		{
			delete [] req->req_data.pBuf;
			
			req->req_data.pBuf = new char[nLen];
			req->req_data.nAllocBufLen = nLen;
		}
		
		memcpy(req->req_data.pBuf, pBuffer, nLen);
		req->req_data.nBufLen = nLen;
		
		req_list.push_back(req);
		
		return true;
	}
	
	log_write_sys(LOG_WARNING, "can't allocate more ApexReq. req_list.size() : %d", req_list.size());
	
	return false;
}

// add request
bool ApexReqPool::AddRequest(uint userid, int Action)
{
	AutoLock auto_lock(mutex);
	
	ApexReq *req = reg_pool.Allocate();
	if (req)
	{
		req->type = ApexReq::Type_Action;
		req->user_id = userid;
		req->req_data.Action = Action;
		
		req_list.push_back(req);
		
		return true;
	}
	
	log_write_sys(LOG_WARNING, "can't allocate more ApexReq. req_list.size() : %d", req_list.size());
	
	return false;
}

// get request
void ApexReqPool::GetRequest(std::list<ApexReq*> &reqs)
{
	reqs.clear();
	
	{
		AutoLock auto_lock(mutex);
		
		req_list.swap(reqs);
	}
	
	return;
}

// remove request
void ApexReqPool::RemoveRequest(std::list<ApexReq*> &reqs)
{
	AutoLock auto_lock(mutex);
	
	for (std::list<ApexReq*>::const_iterator itr = reqs.begin(); itr != reqs.end(); itr++)
	{
		reg_pool.Free((*itr)->uid);
	}
	
	reqs.clear();
	
	return;
}
