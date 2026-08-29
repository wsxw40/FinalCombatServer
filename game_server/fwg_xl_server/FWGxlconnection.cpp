#include "pch.h"
#include <sstream>

// constructor
FWGXLConnection::FWGXLConnection()
{
	stream = this;
	connection = this;
}

FWGXLConnection::~FWGXLConnection()
{

}

// on connected
void FWGXLConnection::OnConnected()
{
	// 链接迅雷的反外挂服务器
	log_write(LOG_INFO, "apexXL server connected: %s:%d", inet_ntoa(addr.sin_addr), ntohs(addr.sin_port));
}

static void ReconnectSdo(FWGXLConnection * conn)
{
	conn->Connect(conn->GetSockaddr());
}

// on disconnected
void FWGXLConnection::OnDisconnected()
{
	if (!connecting)
	{
		log_write(LOG_INFO, "apex server disconnected: %s:%d.", inet_ntoa(addr.sin_addr), ntohs(addr.sin_port));
	}

	Event::AddTimer((EventHandler)&ReconnectSdo, this, 5);
}

// on message
void FWGXLConnection::OnMessage()
{
	// 从迅雷那边得到的消息
	char strContent[1024] = {0};
	uint dwLen = ReadString(strContent, sizeof(strContent));
	log_write(LOG_DEBUG3, "message from xl : %s", strContent);
	server.m_oFWGXLAckPool.AddAck(strContent, dwLen);
}

// client login
void FWGXLConnection::ClientLogIn( std::string strGameId, std::string strServerId, uint dwRoleId, std::string strCustomerId)
{
	/*
	if(!connecting)
	{
		return;
	}
	*/
	Json::Value jValue;
	
	std::stringstream oStream;
	oStream << strGameId;
	std::string strCon = oStream.str();
	jValue["gameid"] = strCon;
	oStream.str("");
	oStream.clear();
	oStream << strServerId;
	strCon = oStream.str();
	jValue["serverid"] = strCon;
	oStream.str("");
	oStream.clear();
	oStream << dwRoleId;
	strCon = oStream.str();
	jValue["roleid"] = strCon;
	oStream.str("");
	oStream.clear();
	oStream << strCustomerId;
	strCon = oStream.str();
	jValue["customerid"] = strCon;
	jValue["sn"] = "0";

	jValue["gameid"] = "00045";
	jValue["sn"] = "0001";

	Json::FastWriter jWrite;
	std::string strValue = jWrite.write(jValue);
	log_write(LOG_DEBUG3, "send to xl cmd : %s len : %d content : %s", "201", strValue.size(), strValue.c_str());
	BeginWrite();
	WriteString("201");
	//WriteInt((int)strValue.size());
	WriteString(strValue.c_str());
	EndWrite();
}

// client logout
void FWGXLConnection::ClientLogOut( std::string strGameId, std::string strServerId, uint dwRoleId, std::string strCustomerId)
{
	/*
	if(!connecting)
	{
		return;
	}
	*/
	Json::Value jValue;

	std::stringstream oStream;
	oStream << strGameId;
	std::string strCon = oStream.str();
	jValue["gameid"] = strCon;
	oStream.str("");
	oStream.clear();
	oStream << strServerId;
	strCon = oStream.str();
	jValue["serverid"] = strCon;
	oStream.str("");
	oStream.clear();
	oStream << dwRoleId;
	strCon = oStream.str();
	jValue["roleid"] = strCon;
	oStream.str("");
	oStream.clear();
	oStream << strCustomerId;
	strCon = oStream.str();
	jValue["customerid"] = strCon;
	jValue["sn"] = "0";

	jValue["gameid"] = "00045";
	jValue["sn"] = "0001";

	Json::FastWriter jWrite;
	std::string strValue = jWrite.write(jValue);
	log_write(LOG_DEBUG3, "send to xl cmd : %s len : %d content : %s", "202", strValue.size(), strValue.c_str());
	BeginWrite();
	WriteString("202");
	//WriteInt((int)strValue.size());
	WriteString(strValue.c_str());
	EndWrite();
}

bool FWGXLConnection::CheckConnectionSendBuffer(uint size)
{
	uint left = NetworkStream::MAX_SEND_BUFFER_SIZE - send_offset;
	
	if (size >= BinaryStream::MAX_PKGSIZE)
	{
		log_write(LOG_INFO, "CheckConnectionSendBuffer() : size big than MAX_PKGSIZE");
		
		return false;
	}
	else if (size >= left)
	{
		ForcedSendMessages();
		
		log_write(LOG_INFO, "CheckConnectionSendBuffer() : try ForcedSendMessages()");
	}
	
	return true;
}
