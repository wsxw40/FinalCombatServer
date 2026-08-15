#pragma once
#include "common.h"

struct IEvent
{
	virtual void OnRead() = 0;
	virtual void OnWrite() = 0;
	virtual void OnClose() = 0;
};

// event handler.
typedef void (*EventHandler)(void * userdata);

// event system
struct Event
{
	// initialize event system.
	static bool Initialize();

	// terminate event system
	static void Terminate();
	
	// dispatch events
	static void Dispatch();

	// add timer
	static bool AddTimer(EventHandler handler, void * userdata, double time);

	// add socket
	static bool AddSocket(SOCKET socket, IEvent * handler, bool read, bool write);

	// modify socket
	static bool ModifySocket(SOCKET socket, IEvent * handler, bool read, bool write);

	// remove socket
	static bool RemoveSocket(SOCKET socket);

	// quit
	static void Quit();

	// get time
	static double GetTime();
};


