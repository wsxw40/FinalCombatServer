#pragma once

class ApexClient
{
public:
	// constructor
	ApexClient();

	// destructor
	~ApexClient();
	
	// run
	int Run();
	
public:
	// update
	void OnUpdate(double frame_time);
	
public:
	ApexConnection apex_connection;
};

extern ApexClient server;
