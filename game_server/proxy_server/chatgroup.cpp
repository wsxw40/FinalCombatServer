#include "pch.h"

ChatGroup::ChatGroup()
{
	memset(creater_name, 0, sizeof(creater_name));
}

ChatGroup::~ChatGroup()
{
}

bool ChatGroup::Init(char *name)
{
	if (name)
	{
		strcpy(creater_name, name);
		characters_uid.clear();
		
		return true;
	}
	
	return false;
}

bool ChatGroup::AddCharacter(uint uid)
{
	if (characters_uid.size() < appcfg.chatgroup_character_limit && 
		characters_uid.find(uid) == characters_uid.end())
	{
		characters_uid.insert(uid);
		
		return true;
	}
	
	return false;
}

bool ChatGroup::DelCharacter(uint uid)
{
	std::set<uint>::iterator it = characters_uid.find(uid);
	if (it != characters_uid.end())
	{
		characters_uid.erase(it);
		
		return true;
	}
	
	return false;
}
