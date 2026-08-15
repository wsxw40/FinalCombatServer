#include "pch.h"

//////////////////////////////////////////////////////////
ServerScript::ServerScript()
	: m_TriggerTime(0)
	, m_IsDone(false)
{
}

ServerScript::~ServerScript()
{
}

void ServerScript::Reset()
{
	m_IsDone = false;
}

void ServerScript::Update(float delta)
{
	if (!m_IsDone)
	{
		if (server.play_time >= m_TriggerTime)
		{
			switch (m_Op)
			{
				case kSetStringValue:
					server.SetServerScriptStringValue(m_Key, m_Value);
					break;
					
				case kSetNumberValue:
					server.SetServerScriptNumberValue(m_Key, atof(m_Value.c_str()));
					break;
				
				default:
					break;
			}
			
			m_IsDone = true;
		}
	}
}

bool ServerScript::BuildServerScript(const std::string &text, ServerScript &script)
{
	//example : time,op,key,value
	
	std::vector<std::string> DataList;
	
	DataList.reserve(4);
	SpltCsv(text.c_str(), DataList, ',');
	
	if (DataList.size() == 4)
	{
		script.Reset();
		
		script.m_TriggerTime = atof(DataList[0].c_str());
		if (DataList[1] == "SS")
		{
			script.m_Op = kSetStringValue;
		}
		else if (DataList[1] == "SN")
		{
			script.m_Op = kSetNumberValue;
		}
		else
		{
			DEBUGLOG_WRITE("BuildServerScript failed : unknow op[%s]", DataList[1].c_str());
			
			return false;
		}
		script.m_Key = DataList[2];
		script.m_Value = DataList[3];
		
		return true;
	}
	
	DEBUGLOG_WRITE("BuildServerScript failed : DataList.size() : %u", DataList.size());
	
	return false;
}
