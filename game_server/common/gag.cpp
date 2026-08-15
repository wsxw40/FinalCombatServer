#include "common.h"
#include "gag.h"
#include "log.h"

static double gag_duration = 10;
static double send_frequency = 1;

// get time
static double GetTime()
{
	timeval tv;
	gettimeofday(&tv, NULL);

	return tv.tv_sec + (double)tv.tv_usec * 0.000001;
}

// constructor
Gag::Gag()
{
	gag_time = 0;
	bzero(&send_time, sizeof(send_time));
}


// send
bool Gag::Send()
{
	double time = GetTime();
	isenable = false;
	// is gaged.
	if (time - gag_time < gag_duration)
		return false;

	double f1 = send_time[1] - send_time[0];
	double f2 = send_time[2] - send_time[1];
	double f3 = time - send_time[2];

	send_time[0] = send_time[1];
	send_time[1] = send_time[2];
	send_time[2] = time;

	if (f1 + f2 + f3 < send_frequency * 3)
	{
		gag_time = time;
		bzero(send_time, sizeof(send_time));
		return false;
	}
	isenable = true;
	return true;
}

bool Gag::IsEnable()
{
	/*double time = GetTime();
	if (time - gag_time < gag_duration)
		return false;
	double f1 = send_time[1] - send_time[0];
	double f2 = send_time[2] - send_time[1];
	double f3 = time - send_time[2];
	if (f1 + f2 + f3 < send_frequency * 3)
	{
		return false;
	}
	return true;*/
	return isenable;
}
