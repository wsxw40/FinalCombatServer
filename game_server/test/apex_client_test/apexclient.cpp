#include <stdlib.h>
#include "pch.h"

ApexClient server;

// update
static void UpdateApexClient(void * data = NULL)
{
	static double update_time = Event::GetTime();
	double time = Event::GetTime();
	double frame_time = time - update_time;
	update_time = time;
	
	server.OnUpdate(frame_time);
	
	Event::AddTimer(&UpdateApexClient, data, 0.01);
}

// constructor
ApexClient::ApexClient()
{
	set_sockaddr(apex_connection.addr, "127.0.0.1", 9003);
}

ApexClient::~ApexClient()
{
}

// run
int ApexClient::Run()
{
	if (!Event::Initialize())
		return 1;
	
	// connect apex server
	apex_connection.Connect(apex_connection.addr);

	// UpdateApexClient
	UpdateApexClient();

	// dispatch events.
	Event::Dispatch();

	// terminate event system.
	Event::Terminate();

	return 0;
}

void ApexClient::OnUpdate(double frame_time)
{
	char dummy_buffer[1024];
	if (apex_connection.IsConnected())
	{
		for (int i = 0; i < 500; i++)
		{
			apex_connection.ClientDataUpdate(rand(), dummy_buffer, sizeof(dummy_buffer));
		}
	}
}
