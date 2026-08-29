#pragma once

class RoomSlot;

class Game : public TcpConnection, public BinaryStream
{
public:
	// constructor
	Game();

	// destructor
	~Game();

	//is close
	bool IsClose();

	// on connected
	void OnConnected();

	// on close
	void OnDisconnected();

	// on message
	void OnMessage();

	// request client enter
	bool RequestClientEnter(const Client & client, void * client_info, int size, bool viewer);

	// response client enter
	void OnResponseClientEnter();

	// on notify client leave
	void OnNotifyClientLeave();

	// notify client close
	void NotifyClientClose();

	// request client leave
	void RequestClientLeave(Client & client);

	// request binary rpc
	void OnRequestBinaryRPC();

	// on request disconnect
	void OnReqeustDisconnect();
	
	// on kick person
	void OnKickPerson();

	// response binary rpc
	void ResponseBinaryRPC(uint result, const void * data, int size);

	// forward client message
	void ForwardClientMessage(int inroom_id, byte msg, char * buffer, uint size);

	// on forward client message
	void OnForwardClientMessage();

	// update character data
	void UpdateCharacterData(Client & client);

	// update character ping
	void UpdateCharacterPing();
	
	void OnEndRequestMatchClient();
	
public:
	int state;
	Room* room;
	LevelInfo level_info;
};
