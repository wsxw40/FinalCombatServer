#pragma once 

#include "common.h"
#include "servererror.h"
#include "log.h"
#include "tcpconnection.h"
#include "tcplistener.h"
#include "udpconnection.h"
#include "udplistener.h"
#include "dictmatch.h"
#include "objectpool.h"
#include "gag.h"
#include "matchingserver.h"
#include "appcfg.h"

#include <vector>
#include <string>
#include <set>
#include <map>
#include <algorithm>
#include <tr1/unordered_map>
#include <tr1/unordered_set>


#define UNORDERED_MAP std::tr1::unordered_map
#define UNORDERED_SET std::tr1::unordered_set

#define max_room_client_count 16

struct eqstr
{
	bool operator ()(const char * s1, const char * s2) const
	{
		return strcmp(s1, s2) == 0;
	}
};


struct hash
{
	template <typename T>
		size_t operator()(const T* p) const
		{
			return std::tr1::hash<T>()(*p);
		}
};

extern AppCfg appcfg;
