#pragma once

class ChatGroup
{
public:
	// constructor
	ChatGroup();
	
	// destructor
	~ChatGroup();
	
public:
	bool Init(char *name);
	
	bool AddCharacter(uint uid);
	
	bool DelCharacter(uint uid);

public:
	uint uid;//do not change this!!!
	
	char creater_name[user_name_length];
	std::set<uint> characters_uid;
};
