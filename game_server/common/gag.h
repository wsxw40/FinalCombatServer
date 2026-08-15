#pragma once

class Gag
{
public:
	// constructor
	Gag();

	// send
	bool Send();
	bool IsEnable();
private:
	double gag_time;
	double send_time[3];
	bool isenable;
};
