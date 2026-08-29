#include "pch.h"
#include <stdarg.h>

// -----------------------------------------------------------------
//  rpc request
// -----------------------------------------------------------------
RpcRequest::RpcRequest()
	: BinaryStream(64 * 1024)
	, type(kNone)
	, processing_info(NULL)
{
}

// write text rpc
void RpcRequest::WriteTextRPC(const char * url, RpcCallback cb)
{
	type = kText;
	callback = cb;
	request_time = Event::GetTime();
	strncpy(this->url, url, sizeof(this->url));

	userdata_offset = write_position - send_buffer;
	userdata_size = 0;

	log_write(LOG_DEBUG4, "text rpc call: %s, id=%x, queue=%d", url, uid, queue);
}

void RpcRequest::WriteBinaryRPC(const char * url, RpcCallback cb)
{
	type = kBinary;
	callback = cb;
	request_time = Event::GetTime();
	strncpy(this->url, url, sizeof(this->url));

	userdata_offset = write_position - send_buffer;
	userdata_size = 0;

	log_write(LOG_DEBUG4, "bin rpc call: %s, id=%x, queue=%d", url, uid, queue);
}

void RpcRequest::EndRPCUserdata()
{
	userdata_size = write_position - send_buffer - userdata_offset;
}

void RpcRequest::WriteParameter(const char * key, const char * data, int size)
{
	WriteString(key);
	WriteInt(size);
	Write(data, size);
}

void RpcRequest::WriteParameter(const char * key, const char * data)
{
	WriteString(key);
	WriteString(data);
}

void RpcRequest::WriteParameterf(const char * key, const char * format, ...)
{
	WriteString(key);

	if (write_position)
	{
		int size;
		int buff_size = send_buffer + send_buffer_size - write_position - 4; 

		va_list args;
		va_start(args, format);
		size = vsnprintf(write_position + 4, buff_size, format, args);
		va_end(args);

		if (size < buff_size)
		{
			memcpy(write_position, &size, 4);
			write_position += size + 4;
		}
		else
		{
			write_position = NULL;
		}
	}
}

void RpcRequest::BeginWrite()
{
	send_offset = 0;
	BinaryStream::BeginWrite();
}

void RpcRequest::EndWrite()
{
	data_offset = userdata_offset + userdata_size;
	data_size = write_position - send_buffer - userdata_offset - userdata_size;
	BinaryStream::EndWrite();
	server.rpc.DispatchRequests();
	//server.rpc.DispatchRequests(queue);
}

bool RpcRequest::OnResponse(char * data, int size)
{
	// prepare response buffer.
	memcpy(recv_buffer, send_buffer + userdata_offset, userdata_size);
	memcpy(recv_buffer + userdata_size, data, size);
	read_position = recv_buffer;
	read_end = read_position + userdata_size + size;
	recv_offset = userdata_size + size;

	// status
	double 
	time = Event::GetTime() - time;

	bool result = true;

	if (callback)
	{
		try
		{
			callback(this);
		}
		catch (...)
		{
			log_write(LOG_ERROR, "rpc parse error: id=%x", uid);
			result = false;
		}
	}

	type = kNone;
	return result;
}

// -----------------------------------------------------------------
//  rpc queue
// -----------------------------------------------------------------
RpcQueue::RpcQueue()
{
}

// initialize
bool RpcQueue::Initialize()
{
	if (!request_pool[kClientQueue].Initialize(50) ||
		!request_pool[kCreateQueue].Initialize(10) ||
		!request_pool[kLoginQueue].Initialize(50) ||
		!request_pool[kServerQueue].Initialize(10) ||
		!request_pool[kGameQueue].Initialize(10))
		return false;

	return true;
}

// update
void RpcQueue::OnUpdate(double time)
{
	for (int queue = 0; queue < kQueueCount; queue++)
	{
		// process canceled requests.
		for (;!request_queue[queue].empty(); request_queue[queue].pop_front())
		{
			uint uid = request_queue[queue].front();

			RpcRequest* request = request_pool[queue].Get(uid);

			// maybe this request is canceled.
			if (request == NULL)
				continue;

			break;
		}

		// process timeout
		for (RpcRequest * rpc = request_pool[queue].Begin();
			rpc < request_pool[queue].End(); rpc++)
		{
			if (rpc->type != RpcRequest::kNone)
			{
				bool cancel_rpc = false;
				double timeout = Event::GetTime() - rpc->timeout;

				// the request has been send to info server.
				if (rpc->processing_info)
				{
					if (rpc->process_time < timeout)
					{
						status.rpc[queue].num_timeouts_total++;
						log_write(LOG_DEBUG1, "rpc process timeout : queue=%d, id=%x, url=%s", queue, rpc->uid, rpc->url);
						rpc->error = 504;
						rpc->OnResponse(NULL, 0);

						CancelRPC(queue, rpc->uid);
					}
				}
				else
				{
					if (rpc->request_time < timeout)
					{
						status.rpc[queue].num_timeouts_total++;
						log_write(LOG_DEBUG1, "rpc request timeout : queue=%d, id=%x, url=%s", queue, rpc->uid, rpc->url);
						rpc->error = 504;
						rpc->OnResponse(NULL, 0);

						CancelRPC(queue, rpc->uid);
					}
				}
			}
		}
	}

	// dispatchs.
	DispatchRequests();
}

RpcRequest* RpcQueue::Allocate(uint queue_id)
{
	RpcRequest * request = NULL;
	if (queue_id < kQueueCount)
	{
		request = request_pool[queue_id].Allocate();
		if (request)
		{
			request->timeout = appcfg.rpc_timeout;
			request->queue = queue_id;
			request->processing_info = NULL;
			request_queue[queue_id].push_back(request->uid);
		}

		status.rpc[queue_id].num_allocates_total++;
	}


	return request;
}

RpcRequest* RpcQueue::Get(uint queue_id, uint rpc_id)
{
	if (queue_id < kQueueCount)
	{
		return request_pool[queue_id].Get(rpc_id);
	}

	return NULL;
}

// free rpc
void RpcQueue::Free(uint queue_id, uint rpc_id)
{
	if (queue_id >= kQueueCount)
		return;

	request_pool[queue_id].Free(rpc_id);
}

void RpcQueue::CancelRPC(uint queue_id, uint rpc_id)
{
	if (queue_id >= kQueueCount)
		return;

	RpcRequest * rpc = request_pool[queue_id].Get(rpc_id);
	if (rpc)
	{
		log_write(LOG_DEBUG1, "CancelRPC() : queue=%d, id=%x", queue_id, rpc_id);
		
		if (rpc->processing_info)
		{
			rpc->processing_info->CancelRPC(queue_id, rpc_id);
			rpc->processing_info = NULL;
		}
		rpc->type = RpcRequest::kNone;
		request_pool[queue_id].Free(rpc_id);
	}
}

	// process
void RpcQueue::DispatchRequests()
{
	for (int queue = 0; queue < kQueueCount; queue++)
	{
		DispatchRequests(queue);
	}
}

void RpcQueue::DispatchRequests(uint queue)
{
	if (queue >= kQueueCount)
		return;

	// process canceled requests.
	for (;!request_queue[queue].empty(); request_queue[queue].pop_front())
	{
		uint uid = request_queue[queue].front();

		RpcRequest* request = request_pool[queue].Get(uid);

		// maybe this request is canceled.
		if (request == NULL)
			continue;

		// broken request
		if (request->send_offset == 0)
		{
			CancelRPC(queue, request->uid);
			continue;
		}

		// find best info connection to send this request
		InfoConnection * best_info = NULL;
		int best_info_queue_size = 0;

		for (InfoConnection * info = server.info_pool.Begin();
				info < server.info_pool.End(); ++info)
		{
			if (!info->online)
				continue;
			
			if (info->will_disconnect)
				continue;

			if (info->rpc_status[queue].queue_size > best_info_queue_size)
			{
				best_info = info;
				best_info_queue_size = info->rpc_status[queue].queue_size;
			}
		}

		// no free info found.
		if (best_info == NULL)
			break;

		request->process_time = Event::GetTime();
		request->processing_info = best_info;
		best_info->RequestRPC(queue, request);
	}
}
