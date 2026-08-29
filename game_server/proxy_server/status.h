#pragma once

struct StatusWriter
{
	virtual void Write(const char * key, const char * format, ...) = 0;
};

class Status
{
public:
	struct RPCStatus
	{
		// num request
		uint num_allocates_total;
		uint num_allocates_per_second;

		// num responses.
		uint num_responses_total;
		uint num_responses_per_second;

		// num timeouts.
		uint num_timeouts_total;
		uint num_timeouts_per_second;

		// response time
		double response_time_total;
		double response_time_average;

		// process time
		double process_time_total;
		double process_time_average;
		double process_time_max;
		double process_time_min;
		double process_time_max_current;
		double process_time_min_current;

		// queue size
		uint queue_size;

		// rpc pool usage
		uint request_used;
		uint request_total;
	};

public:
	// constructor.
	Status();

	// write
	void Write(StatusWriter & writer);

	// update
	void Update();

private:
	// update time
	double update_time;

public:
	// total online client numbers.
	uint active_connections;

	// total online character numbers.
	uint online_characters;

	// rpc status
	RPCStatus rpc[RpcQueue::kQueueCount];

	uint rpc_responses_per_second;
	double rpc_average_response_time;

	// total clients ping stauts
	uint client_ping_status[10];
	uint client_resend_status[10];

public:
	// total bytes send and received per second with client..
	uint total_client_send_bps;
	uint total_client_recv_bps;

	// total bytes send and received per second with info server.
	uint total_info_send_bps;
	uint total_info_recv_bps;
	
	// client ping and resend status
	uint client_ping_sum[10];
	uint client_resend_sum[10];

	// total room count
	uint total_channel_count;
	uint total_room_count;
	uint total_game_count;
	uint total_ingame_client_count;
};

// global status instance;
extern Status status;
