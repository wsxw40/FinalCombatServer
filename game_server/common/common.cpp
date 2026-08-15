#include "common.h"

void set_sockaddr(sockaddr_in & addr, const char * ip, uint port)
{
	bzero(&addr, sizeof(addr));
	addr.sin_family = AF_INET;
	
	if (ip)
		addr.sin_addr.s_addr = inet_addr(ip);
	
	addr.sin_port = htons(port);
}

bool set_sockaddr(hostent* pHostent , uint dwPort, sockaddr_in & oAddr)
{
	if (!pHostent)
	{
		return false;
	}

	bzero(&oAddr, sizeof(oAddr));
	oAddr.sin_family = AF_INET;

	char strIp[16] ={0};
	
	for (int i = 0; (pHostent->h_addr_list)[i] != NULL; i++) 
	{
		inet_ntop(AF_INET, (pHostent->h_addr_list)[i], strIp, 16);
	}

	set_sockaddr(oAddr, strIp, dwPort);
	return false;
}


bool parse_sockaddr(sockaddr_in & addr, const char * str)
{
	if (str)
	{
		char address[256];
		uint port;

		const char * port_start = strchr(str, ':');
		if (port_start)
		{
			memcpy(address, str, port_start - str);
			address[port_start - str] = 0;
			port = atoi(port_start + 1);

			bzero(&addr, sizeof(addr));
			addr.sin_family = AF_INET;
			addr.sin_port = htons(port);
			if (address[0])
				addr.sin_addr.s_addr = inet_addr(address);
			return true;
		}
	}

	return false;
}

const char * sockaddr_ntoa(sockaddr_in & addr)
{
	static char buffer[256];
	snprintf(buffer, sizeof(buffer), "%s:%d", inet_ntoa(addr.sin_addr), ntohs(addr.sin_port));
	return buffer;
}

bool run_as_daemon()
{
	// ignore signals
	signal(SIGTTOU, SIG_IGN);
	signal(SIGTTIN, SIG_IGN);
	signal(SIGTSTP, SIG_IGN); 
	signal(SIGHUP, SIG_IGN);
	
	return daemon(1, 0) == 0;
}

const char * getexepath()
{
	static char buf[1024];
	int count; 

	count = readlink("/proc/self/exe", buf, sizeof(buf));

	if (count < 0 || count >= sizeof(buf))
		return "";

	char * end = strrchr(buf, '/');
	if (end)
		end[0] = 0;

	return buf;
}

void SpltCsv(const char *pBuffer, std::vector<std::string> &DataList, char cSplt)
{
	char szBuffer[256];

	if (!pBuffer)
	{
		return;
	}

	const char* pPosStart = pBuffer;
	const char* pPosEnd = strchr(pPosStart, cSplt);

	while (pPosEnd)
	{
		int Len = pPosEnd - pPosStart;
		memcpy(szBuffer, pPosStart, Len);
		szBuffer[Len] = 0;

		std::string tmp(szBuffer);

		if (!tmp.empty())
			DataList.push_back(tmp);

		pPosStart = pPosEnd + 1;
		pPosEnd = strchr(pPosStart, cSplt);
	}

	//last part
	std::string tmp(pPosStart);

	if (!tmp.empty())
		DataList.push_back(tmp);
}
