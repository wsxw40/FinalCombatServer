#pragma once

#include "common.h"
#include "servererror.h"
#include "event.h"
#include "tcplistener.h"
#include "tcpconnection.h"
#include "udplistener.h"
#include "udpconnection.h"
#include "log.h"
#include "objectpool.h"
#include "dictmatch.h"
#include "levelinfo.h"
#include "roomoption.h"
#include "gag.h"
#include "compress.h"
#include "encoder.h"
#include "desencoder.h"
#include "aes.h"
#include "base64.h"
#include "matching.h"

#include <vector>
#include <list>
#include <string>
#include <set>
#include <map>
#include <ext/hash_map>
#include <deque>

#define max_room_client_count 16

static const int xl_connection_num = 10;

struct eqstr
{
	bool operator ()(const char * s1, const char * s2) const
	{
		return strcmp(s1, s2) == 0;
	}
};

struct lessstr
{
	bool operator ()(const char * s1, const char * s2) const
	{
		return strcmp(s1, s2) < 0;
	}
};

struct SearchRoomOptions
{
	bool all_channels;
	uint game_type;
	uint level_id;
	byte num_clients;
	byte playing;
	byte waiting;
};

#include "appcfg.h"
#include "rpc.h"
#include "info.h"
#include "gm.h"
#include "chatgroup.h"
#include "battlegroup.h"
#include "apexconnection.h"
#include "xlconnection.h"
#include "channel.h"
#include "client.h"
#include "proxyserver.h"
#include "status.h"

extern AppCfg appcfg;
