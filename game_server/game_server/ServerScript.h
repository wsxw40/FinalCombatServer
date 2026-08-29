#pragma once

class ServerScript
{
	enum OpType
	{
		kSetStringValue,
		kSetNumberValue,
	};
public:
	// ServerScript
	ServerScript();
	
	~ServerScript();
	
public:
	void Reset();
	
	void Update(float delta);
	
public:
	static bool BuildServerScript(const std::string &text, ServerScript &script);

private:
	double		m_TriggerTime;
	bool		m_IsDone;
	OpType		m_Op;
	std::string m_Key;
	std::string m_Value;
};
