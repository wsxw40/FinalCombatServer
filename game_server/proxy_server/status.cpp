#include "pch.h"
#include <stdarg.h>

// status writer
static inline void write_status(StatusWriter & writer, const char * key, uint data)
{
	writer.Write(key, "%d", data);
}
static inline void write_status(StatusWriter & writer, const char * key, double data)
{
	writer.Write(key, "%.4g", data);
}

// global status instance.
Status status;

// constructor.
Status::Status()
{
	bzero(this, sizeof(Status));
}

#define write_member(name) write_status(writer, #name, name)
#define foreach_queue_format(name) name "\t" name "\t" name "\t" name "\t" name
#define foreach_queue(name) rpc[0].name, rpc[1].name, rpc[2].name, rpc[3].name, rpc[4].name

// write
void Status::Write(StatusWriter & writer)
{
	time_t current_time;
	time_t curren_time;
	struct tm * t;
	time(&current_time);
	t = localtime(&current_time);
	writer.Write("time", "%04d-%02d-%02d %02d:%02d:%02d",
			t->tm_year + 1900,
			t->tm_mon + 1,
			t->tm_mday,
			t->tm_hour,
			t->tm_min,
			t->tm_sec);
	current_time -= server.start_time;
	writer.Write("running_time", "%d %02d:%02d:%02d",
			current_time / 3600 / 24,
			current_time / 60 / 60 % 24,
			current_time / 60 % 60,
			current_time % 60);

	write_member(active_connections);
	write_member(online_characters);
	write_member(rpc_responses_per_second);
	write_member(rpc_average_response_time);

	// network traffic
	write_member(total_client_send_bps);
	write_member(total_client_recv_bps);
	write_member(total_info_send_bps);
	write_member(total_info_recv_bps);

	// total room and game count
	write_member(total_channel_count);
	write_member(total_room_count);
	write_member(total_game_count);
	write_member(total_ingame_client_count);
	
	// network status
	writer.Write("#ping", "30ms\t60ms\t90ms\t120ms\t150ms\t180ms\t210ms\t240ms\t270ms\t300ms+");
	writer.Write("ping_status", "%d\t%d\t%d\t%d\t%d\t%d\t%d\t%d\t%d\t%d", 
					client_ping_status[0],
					client_ping_status[1],
					client_ping_status[2],
					client_ping_status[3],
					client_ping_status[4],
					client_ping_status[5],
					client_ping_status[6],
					client_ping_status[7],
					client_ping_status[8],
					client_ping_status[9]);

	writer.Write("#resend", "%s", "0%\t0.01%\t0.03%\t0.05%\t0.07%\t0.09%\t0.1%\t0.2%\t0.5%\t1.0%");
	writer.Write("resend_status", "%d\t%d\t%d\t%d\t%d\t%d\t%d\t%d\t%d\t%d", 
					client_resend_status[0],
					client_resend_status[1],
					client_resend_status[2],
					client_resend_status[3],
					client_resend_status[4],
					client_resend_status[5],
					client_resend_status[6],
					client_resend_status[7],
					client_resend_status[8],
					client_resend_status[9]);

	// rpc status
	writer.Write("#rpc queue", "client\t" "create\t" "login\t" "server\t" "game");
	writer.Write("num_allocates_per_second", foreach_queue_format("%d"), foreach_queue(num_allocates_per_second));
	writer.Write("num_responses_per_second", foreach_queue_format("%d"), foreach_queue(num_responses_per_second));
	writer.Write("num_timeouts_per_second", foreach_queue_format("%d"), foreach_queue(num_timeouts_per_second));
	writer.Write("response_time_average", foreach_queue_format("%.3f"), foreach_queue(response_time_average));
	writer.Write("process_time_average", foreach_queue_format("%.3f"), foreach_queue(process_time_average));
	writer.Write("process_time_max", foreach_queue_format("%.3f"), foreach_queue(process_time_max));
	writer.Write("process_time_min", foreach_queue_format("%.3f"), foreach_queue(process_time_min));
	writer.Write("queue_size", foreach_queue_format("%d"), foreach_queue(queue_size));
	writer.Write("request_pool_used", foreach_queue_format("%d"), foreach_queue(request_used));
	writer.Write("request_pool_size", foreach_queue_format("%d"), foreach_queue(request_total));


	writer.Write("#queue/requests", "client\tcreate\tlogin\tserver\tgame\t" "client\tcreate\tlogin\tserver\tgame\t");
	for (InfoConnection * info = server.info_pool.Begin();
		info < server.info_pool.End(); ++info)
	{
		if (!info->IsConnected())
			continue;

		char name[256];
		snprintf(name, sizeof(name), "info-%s", sockaddr_ntoa(info->addr));
		writer.Write(name, "%d\t%d\t%d\t%d\t%d\t" "%d\t%d\t%d\t%d\t%d\t",
			info->rpc_status[0].queue_size,
			info->rpc_status[1].queue_size,
			info->rpc_status[2].queue_size,
			info->rpc_status[3].queue_size,
			info->rpc_status[4].queue_size,
			info->rpc_status[0].request_count_per_second,
			info->rpc_status[1].request_count_per_second,
			info->rpc_status[2].request_count_per_second,
			info->rpc_status[3].request_count_per_second,
			info->rpc_status[4].request_count_per_second);
	}
}

static inline uint scale_value(double value, double duration)
{
	return (uint)(value / duration);
}

// update
void Status::Update()
{
	double time = Event::GetTime();
	double duration = time - update_time;

	if (duration > 1)
	{
		// update time.
		update_time = time;
		active_connections = 0;
		online_characters = 0;
		total_client_send_bps = 0;
		total_client_recv_bps = 0;

		// for each client
		for (ClientConnection * c = server.client_pool.Begin(); c < server.client_pool.End(); c++)
		{
			if (c->state > 0)
			{
				++active_connections;
				total_client_send_bps += c->num_bytes_send;
				total_client_recv_bps += c->num_bytes_received;

				c->num_bytes_send = 0;
				c->num_bytes_received = 0;
			}

			if (c->IsOnline())
				++online_characters;
		}

		total_client_send_bps = scale_value(total_client_send_bps, duration);
		total_client_recv_bps = scale_value(total_client_recv_bps, duration);

		// info connections.
		total_info_send_bps = 0;
		total_info_recv_bps = 0;

		for (InfoConnection * info = server.info_pool.Begin(); info < server.info_pool.End(); info++)
		{
			total_info_send_bps += info->num_bytes_send;
			total_info_recv_bps += info->num_bytes_received;

			info->num_bytes_send = 0;
			info->num_bytes_received = 0;
			for (int q = 0; q < RpcQueue::kQueueCount; q++)
			{
				info->rpc_status[q].request_count_per_second = scale_value(info->rpc_status[q].request_count_total, duration);
				info->rpc_status[q].request_count_total = 0;
			}
		}

		total_info_send_bps = scale_value(total_info_send_bps, duration);
		total_info_recv_bps = scale_value(total_info_recv_bps, duration);

		rpc_responses_per_second = 0;
		double rpc_call_total_time = 0;

		// update rpc call
		for (int q = 0; q < RpcQueue::kQueueCount; q++)
		{
			rpc_responses_per_second += rpc[q].num_responses_total;
			rpc_call_total_time += rpc[q].response_time_total;

			if (rpc[q].num_responses_total)
			{
				rpc[q].response_time_average = rpc[q].response_time_total / rpc[q].num_responses_total;
				rpc[q].process_time_average = rpc[q].process_time_total / rpc[q].num_responses_total;
			}
			rpc[q].response_time_total = 0;
			rpc[q].process_time_total = 0;

			if (rpc[q].num_responses_total)
			{
				rpc[q].process_time_max = rpc[q].process_time_max_current;
				rpc[q].process_time_min = rpc[q].process_time_min_current;
				rpc[q].process_time_max_current = 0;
				rpc[q].process_time_min_current = 100;
			}

			rpc[q].num_responses_per_second = scale_value(rpc[q].num_responses_total, duration);
			rpc[q].num_responses_total = 0;

			rpc[q].num_timeouts_per_second = scale_value(rpc[q].num_timeouts_total, duration);
			rpc[q].num_timeouts_total = 0;

			rpc[q].num_allocates_per_second = scale_value(rpc[q].num_allocates_total, duration);
			rpc[q].num_allocates_total = 0;

			rpc[q].queue_size = server.rpc.request_queue[q].size();

			rpc[q].request_total = server.rpc.request_pool[q].Size();
			rpc[q].request_used = 0;
			for (RpcRequest * request = server.rpc.request_pool[q].Begin();
					request < server.rpc.request_pool[q].End(); request++)
			{
				if (request->type != RpcRequest::kNone)
				{
					rpc[q].request_used++;
				}
			}
		}

		if (rpc_responses_per_second)
			rpc_average_response_time = rpc_call_total_time / rpc_responses_per_second;

		// update stauts
		for (int i = 0; i < 10; i ++)
		{
			client_ping_status[i] = client_ping_sum[i];
			client_ping_sum[i] = 0;
			client_resend_status[i] = client_resend_sum[i];
			client_resend_sum[i] = 0;
		}

		// update room and game count
		total_room_count = 0;
		total_game_count = 0;
		total_channel_count = 0;
		total_ingame_client_count = 0;
		for (ChannelConnection *conn = server.channel_pool.Begin(); conn < server.channel_pool.End(); conn++)
		{
			if (conn->IsConnected())
			{
				total_channel_count ++;
				total_room_count += conn->room_count;
				total_game_count += conn->game_count;
				total_ingame_client_count += conn->game_client_count;
			}
		}

	}
}
