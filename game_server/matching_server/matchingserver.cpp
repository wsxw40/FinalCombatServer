#include "matchingserver.h"
#include "pch.h"

#include "tcpconnection.h"
#include "tcplistener.h"
#include "log.h"
#include "utils.h"
//#include "properties.h"
//#include "protocol/matching.h"
#include "servererror.h"

#include <sys/time.h>
#include <ctime>

inline float GetExtendTimeOut(int ccu, int times)
{
	//return 7.2;
	return 5.0f;
}

static void reload_props(int)
{
	log_write(LOG_INFO, "reload properties...");
	appcfg.ReloadConfigFile();
}

// global login server
MatchingServer server;

// proxy server listener
void ProxyListener::OnClientConnected(int connected_socket, sockaddr_in addr)
{
	ProxyConnection &conn = server.proxy_connection;
	if (conn.IsConnected())
	{
		conn.Disconnect();
		log_write(LOG_ERROR, "Proxy already connected, disconnect it first.");
	}
	conn.Connect(connected_socket, addr);
}

// constructor
MatchingServer::MatchingServer()
:BinaryStream(appcfg.m_dwServerBufferSize)
{
	max_team_count = 1;
	memset(team_count, 0, sizeof(team_count));
}

// destructor
MatchingServer::~MatchingServer()
{
}

static void UpdateMatching(void * data = NULL)
{
	server.Update();
	Event::AddTimer(UpdateMatching, data, 0.1);
}
static void UpdateStatus(void * data = NULL)
{
	server.Status();
	Event::AddTimer(UpdateStatus, data, 1);
}
// run
int MatchingServer::Run()
{

	log_write(LOG_INFO, "match server initialize, proxy_listen=%s:%d",
		inet_ntoa(proxy_listener.addr.sin_addr), ntohs(proxy_listener.addr.sin_port));

	if (proxy_listener.addr.sin_port == 0)
	{
		log_write(LOG_ERROR, "Proxy listen port == 0");
		return 1;
	}

	matching_pool.Initialize(65535);

	BuildMatchingTree();

	if (!proxy_listener.Initialize())
		return 1;

	UpdateMatching();

	UpdateStatus();

	signal(SIGUSR1, &reload_props);

	Event::Dispatch();

	Event::Terminate();
}

void MatchingServer::Update()
{
	static double time = Event::GetTime(), diffs = 0;
	double diff = Event::GetTime() - time;
	time = Event::GetTime();

	diffs += diff;
	if (diffs > 1.0f)
	{
		static bool last_event_time = false;

		// todo û��ս����ƥ���� ��������ò�����
		if (IsEventTime(time))
			last_event_time = true;
		else
		{
			if (last_event_time)
			{
				last_event_time = false;
				//CannelMatching(0);
			}
		}
		diffs = 0;
	}


	MatchingQueue matching_queue2 = matching_queue;
	matching_queue.clear();

	MatchingQueue::iterator iter = matching_queue2.begin();
	while (iter != matching_queue2.end())
	{
		if (MatchingGroup *matching_group = *iter)
		{
			matching_group->Update(diff);
			MatchingMap::iterator it = matching_map.find(matching_group->GetId()); 
			if (it != matching_map.end())                                          
			{                                                                       
				it->second = matching_queue.insert(matching_group);
			}
		}
		++iter;
	}

	if (!server.proxy_connection.IsConnected())
	{
		return;
	}

	if (!matching_queue.empty())
	{
		bool ret = true;

		double start_matching_time = Event::GetTime();
		while (ret)
		{
			uint matching_clients [max_room_client_count] = {0};

			int game_mode = -1;
			int team_type = -1;
			int dwLevelId = -1;
			int server_id = -1;
			if (ret = Matching(server_id, team_type, game_mode, dwLevelId, matching_clients))
			{
				server.proxy_connection.MatchingCompleted(server_id, team_type, game_mode, matching_clients);

				if (Event::GetTime() - start_matching_time > 0.1)
					break;
			}
		}
	}
}

bool MatchingGroup::Extend()
{
	if (min_level <= MIN_LEVEL && max_level >= MAX_LEVEL)
		return false;

	if (min_level > MIN_LEVEL)
	{
		min_level--;
		extend_flag |= 1llu << (min_level - 1);
	}

	if (max_level < MAX_LEVEL)
	{
		max_level++;
		extend_flag |= 1llu << (max_level - 1);
	}

	return true;
}

void MatchingGroup::Initialize( uint server_id, uint _id, uint _type, uint _count, uint _level, int _mode, uint _dwLevelId )
	{
	m_dwServerId = server_id;
	id = _id;
	count = weights = _count;
	org_level = min_level = max_level = _level;
	game_mode = _mode;
	type = _type;
	extend_flag |= 1llu << (org_level - 1);

	m_dwLevelId = _dwLevelId;
}

bool MatchingGroup::Matching(const MatchingGroup& matching_group)
{
	if ((game_mode != -1 && matching_group.game_mode != -1) && game_mode != matching_group.game_mode)
		return false;

	if (m_dwLevelId != matching_group.m_dwLevelId)
	{
		return false;
	}
	
	if (min_level < matching_group.min_level && max_level < matching_group.min_level)
		return false;

	if (min_level > matching_group.max_level && max_level > matching_group.max_level)
		return false;

	return true;
}
bool MatchingGroup::Update(double diff)
{

	upweight_time_out += diff;
	if (upweight_time_out >= 1)
	{
		weights++;
		upweight_time_out = 0;
	}

	extend_time_out += diff;

	double time_out = 0.0;

	time_out = GetExtendTimeOut(server.proxy_connection.GetCCU(), extend_times);

	if (extend_time_out >= time_out)
	{
		if (Extend()) 
		{
			extend_times++;
		}
		extend_time_out = 0;
	}
	return true;
}

bool MatchingServer::AddMatching( uint server_id, uint matching_id, uint type, uint count, uint matching_level, char game_mode, int nLevelId /*= 0*/ )
{
	MatchingMap::iterator iter = matching_map.find(matching_id);
	if (iter == matching_map.end())
	{
		if (MatchingGroup *matching_group = matching_pool.Allocate())
		{
			IncTeamCount(type, count);
			matching_group->Initialize(server_id, matching_id, type, count, matching_level, game_mode, nLevelId);
			MatchingQueue::iterator it = matching_queue.insert(matching_group);
			matching_map.insert(std::make_pair(matching_id, it));
			return true;
		}
	}
	return false;
}

bool MatchingServer::RemoveMatching(uint matching_id)
{
	MatchingMap::iterator iter = matching_map.find(matching_id);
	if (iter != matching_map.end())
	{
		DecTeamCount((*iter->second)->GetType(), (*iter->second)->GetCount());
		(*iter->second)->Reset();
		matching_pool.Free((*iter->second)->uid);
		matching_queue.erase(iter->second);
		matching_map.erase(iter);
		return true;
	}
	return false;
}
const MatchingGroup *MatchingServer::GetMatchingGroup(uint matching_id)
{
	MatchingMap::iterator iter = matching_map.find(matching_id);
	if (iter != matching_map.end())
	{
		return *iter->second;
	}
	return NULL;
}

bool MatchingServer::BuildMatchingTree()
{
	for (int i = 1; i <= max_room_client_count / 2; ++i)
	{
		_BuildMatchingTree(i, matching_tree[i-1]);
	}
	return true;
}
bool MatchingServer::_BuildMatchingTree(int count, MatchingTreeNode &matching_tree)
{
	typedef FixedArray<byte, max_room_client_count / 2> TeamFixedArray;
	typedef std::vector< TeamFixedArray > TeamFixedArrayVec;
	TeamFixedArrayVec team_expr, all_team_expr;

	split_n(count, 1, team_expr, true);

	for (TeamFixedArrayVec::iterator iter = team_expr.begin(); iter != team_expr.end(); ++iter)
	{
		array_u(*iter, iter->GetSize(), 1, all_team_expr);
	}

	for (TeamFixedArrayVec::iterator iter = all_team_expr.begin(); iter != all_team_expr.end(); ++iter)
	{
		MatchingTreeNode * node = &matching_tree;

		TeamFixedArray &fixed_array = *iter;
		for (int i = 0; i < fixed_array.GetSize(); ++i)
		{
			MatchingTreeNode * child_node = node->GetChildNode(fixed_array[i]);
			if (child_node)
				node = child_node;
			else
				node = node->AddChildNode(fixed_array[i]);
		}
	}
	return true;
}

bool MatchingServer::MatchingStack::matching(MatchingGroup *matching_group)
{
	//if (m_team_type != -1 && matching_group->GetType() !=  m_team_type)
	//{
	//	return false;
	//}
	if (m_game_mode != -1 && matching_group->GetGameMode() != m_game_mode) 
	{
		return false;
	}
	
	if (m_dwServerId != -1 && m_dwServerId != matching_group->GetServerId())
	{
		return false;
	}
	
	
	//if (!(level_flag & matching_group->GetExtendFlag()))
	//{
	//	return false;
	//}
	if (m_dwMatchingLevel)
	{
		if (m_dwMatchingLevel > matching_group->GetMaxLevel() || m_dwMatchingLevel < matching_group->GetMinLevel())
		{
			return false;
		}
	}
	
	if ((int)m_dwLevelId != -1 && m_dwLevelId != matching_group->GetLevelId() && (int)(matching_group->GetLevelId()) != -1)
	{
		// Ϊ0 ʱ Ϊ�����ͼ Ҫ�ӽ���
		if (matching_group->GetLevelId())
		{
			return false;
		}
	}
	
	std::vector< const MatchingGroup* >::iterator iter = stack.begin();
	while (iter != stack.end())
	{
		if (matching_group->GetId() == (*iter)->GetId())
		{
			return false;
		}
		//if (!((*iter)->GetExtendFlag() & matching_group->GetExtendFlag()))
		//{
		//	return false;
		//}
		if ((*iter)->GetMinLevel() > matching_group->GetMaxLevel() || (*iter)->GetMaxLevel() < matching_group->GetMinLevel())
		{
			return false;
		}
		
		if ((*iter)->GetType() != matching_group->GetType())
		{
			return false;
		}
		if (matching_group->GetGameMode() != -1 && (*iter)->GetGameMode() != -1 && matching_group->GetGameMode() != (*iter)->GetGameMode())
		{
			return false;
		}
		if ((int)(matching_group->GetLevelId()) != -1 && (*iter)->GetLevelId() != matching_group->GetLevelId() && (int)((*iter)->GetLevelId()) != -1)
		{
			// Ϊ0 ʱ Ϊ�����ͼ Ҫ�ӽ���
			if (matching_group->GetLevelId())
			{
				return false;
			}
		}
		
		if (matching_group->GetServerId() != (*iter)->GetServerId())
		{
			return false;
		}
		
		++iter;
	}
	return true;
}

void MatchingServer::MatchingStack::pop()
{
	if (!stack.empty())
	{
		m_size -= stack.back()->GetCount();
		stack.pop_back();
	}
}

void MatchingServer::MatchingStack::push(const MatchingGroup* matching_group)
{ 
	stack.push_back(matching_group); 
	m_size += matching_group->GetCount();
	std::vector< const MatchingGroup* >::iterator iter = stack.begin();
	while (iter != stack.end())
	{
		if ((*iter)->GetProgress() < m_size)
		{
			(*iter)->SetProgress(m_size);
		}
		++iter;
	}
}

bool MatchingServer::Matching(const MatchingTreeNode *node, MatchingStack &stack, const MatchingFilter &filter, MatchingQueue::iterator begin, MatchingQueue::iterator end)
{
	MatchingQueue::iterator iter = begin;
	while (iter != end)
	{
		if (filter.find((*iter)->GetId()) == filter.end())
		{
			const MatchingTreeNode *n = node == NULL ? GetMatchingTree(stack, *iter) : node;
			if (n) 
			{
				if (n = n->GetChildNode((*iter)->GetCount()))
				{
					if (stack.matching(*iter))
					{
						stack.push(*iter);

						if (n->IsLeafNode())
							return true;

						if (Matching(n, stack, filter, ++MatchingQueue::iterator(iter), end))
							return true;

						stack.pop();
					}
				}
			}
		}
		++iter;
	}
	return false;
}

bool MatchingServer::Matching(uint server_id, int &team_type, int &game_mode, int& dwLevelId, uint matching_clients[], byte matching_level, int rteam_count, int bteam_count)
{
	bool ret = true;

	MatchingFilter filter;
	MatchingStack stack(server_id, team_type, game_mode, dwLevelId, matching_level);

	size_t stack_bottom = 0;
	const MatchingTreeNode *node = game_mode == -1 ? NULL : GetMatchingTree(rteam_count);
	while ((game_mode != -1 && rteam_count == 0) || (ret = Matching(node, stack, filter, matching_queue.begin(), matching_queue.end())))
	{
		stack_bottom = stack.size();
		node = game_mode == -1 ? NULL : GetMatchingTree(bteam_count);
		if (((game_mode == -1 && bteam_count == 0) || game_mode != -1 && bteam_count != 0)
				&& !(ret &= Matching(node, stack, filter, matching_queue.begin(), matching_queue.end())) && stack.size() != 0)
		{
			while (!stack.empty())
			{
				const MatchingGroup* matching_group = stack.top();
				filter.insert(matching_group->GetId());
				stack.pop();
			}
		}
		else
			break;
	}

	if (ret)
	{
		DumpStackMatchingClient(stack, stack_bottom, team_type, game_mode, &matching_clients[max_room_client_count / 2]);
		DumpStackMatchingClient(stack, 0, team_type, game_mode, &matching_clients[0]);
		if (game_mode == -1)
		{
			game_mode = (char)-1;
		}
	}

	MatchingQueue::iterator iter = matching_queue.begin();
	while (iter != matching_queue.end())
	{
		if (!ret && game_mode == -1)
		{
			(*iter)->CommitProgress();
		}
		(*iter)->SetProgress(0);
		iter++;
	}

	return ret;
}

int MatchingServer::DumpStackMatchingClient(MatchingServer::MatchingStack& stack, size_t stack_bottom, int &team_type, int &game_mode, uint matching_clients[])
{
	int count = 0;
	while (stack.size() != stack_bottom)
	{
		const MatchingGroup * matching_group = stack.top();
		if (game_mode == -1)
		{
			game_mode = matching_group->GetGameMode();
		}
		if (team_type == -1)
		{
			team_type = matching_group->GetType();
		}
		matching_clients[ count ] = matching_group->GetId();
		RemoveMatching(matching_group->GetId());
		stack.pop();
		count++;
	}
	
	return count;
}

void MatchingServer::Status()
{
	static int n = -1;
	if (n != matching_queue.size())
	{
		log_write(LOG_DEBUG1, "Matching queue count = %d", matching_queue.size());
		n = matching_queue.size();
	}
}

const MatchingServer::MatchingTreeNode * MatchingServer::GetMatchingTree(const MatchingStack &stack, const MatchingGroup* matching_group)
{
	if (!stack.empty())
	{
		return GetMatchingTree(GetMatchingTeamCount(stack.top()->GetType()));
	}
	return GetMatchingTree(GetMatchingTeamCount(matching_group->GetType()));
}

int MatchingServer::GetMatchingTeamCount(int team_mode)
{
	int count = 0;
	int ccu = server.proxy_connection.GetCCU();

	if (max_team_count > ccu / 2)
	{
		ccu -= max_team_count;
	}
	
	if (ccu > 0 && ccu <= 6)
	{
		count = std::max(1, std::min(max_team_count, ccu / 2));
	}
	else if (ccu > 6 && ccu <= 12)
	{
		count = std::max(2, std::min(max_team_count, ccu / 2));
	}
	else if (ccu > 12 && ccu <= 50)
	{
		count = std::max(3, std::min(max_team_count, ccu / 2));
	}
	else if (ccu > 50 && ccu <= 200)
	{
		count = std::max(4, std::min(max_team_count, ccu / 2));
	}
	else
	{
		count = 8;
	}

	// ÿ�����8��
	if (count > 8)
	{
		count = 8;
	}

	if (count > ccu / 2)
	{
		count = ccu / 2;
	}
	
	return count;
	

	//int temp_team_count = 1;
	//if (temp_team_count < max_team_count / 2)
	//{
	//	temp_team_count = max_team_count / 2;
	//}

	//int ccu = server.proxy_connection.GetCCU();
	//if (ccu > 0 && ccu <= 6)
	//	return std::min(1, temp_team_count);
	//else if (ccu > 6 && ccu <= 12)
	//	return std::min(2, temp_team_count);
	//else if (ccu > 12 && ccu <= 50)
	//	return std::min(3, temp_team_count);
	//else if (ccu > 50 && ccu <= 200)
	//	return std::min(4, temp_team_count);
	//else
	//	return std::min(8, temp_team_count);
}
void MatchingServer::DecTeamCount(int type, int count)
{
	team_count[count - 1]--;
	if (team_count[count - 1] == 0 && count == max_team_count)
	{
		if (count <= 1)
			max_team_count = 1;
		else
		{
			for (int i = count - 2; i >= 0; --i)
			{
				if (team_count[i] != 0)
				{
					max_team_count = i + 1;
					break;
				}
			}
			if (count == max_team_count)
				max_team_count = 1;
		}
	}
}
void MatchingServer::IncTeamCount(int type, int count)
{
	team_count[count - 1]++;
	if (count > max_team_count)
	{
		max_team_count = count;
	}
}

void MatchingServer::CannelMatching(int type)
{
	MatchingQueue::iterator iter = matching_queue.begin();
	while (iter != matching_queue.end())
	{
		MatchingQueue::iterator it = iter++;
		if (MatchingGroup *matching_group = *it)
		{
			if (matching_group->GetType() == type)
			{
				uint matching_id = matching_group->GetId();
				if (RemoveMatching(matching_id))
				{
					server.proxy_connection.ResponseCancelMatching(kErrorProxyMatchingGuildTeamIsStopped, matching_id, true);
				}
			}
		}
	}
}
bool MatchingServer::IsEventTime(time_t now)
{
	struct tm * tm = localtime(&now);
	for (int i = 0; i < event_time.size(); ++i)
	{
		struct tm tm_s = *tm;
		struct tm tm_e = *tm;

		tm_s.tm_sec = tm_e.tm_sec = 0;

		tm->tm_wday = tm->tm_wday ? tm->tm_wday : 7;

		tm_s.tm_mday += event_time[i].start.dow - tm->tm_wday;
		tm_e.tm_mday += event_time[i].end.dow - tm->tm_wday;

		tm_s.tm_hour = event_time[i].start.hour;
		tm_e.tm_hour = event_time[i].end.hour;

		tm_s.tm_min = event_time[i].start.minute;
		tm_e.tm_min = event_time[i].end.minute;

		//the values supplied by the caller in the tm_wday and tm_yday fields.
		const time_t start = mktime(&tm_s);
		const time_t end = mktime(&tm_e);
		if (now >= start && now <= end)
		{
			return true;
		}
	}
	return false;
}
bool MatchingServer::OnUpdateEventTime(ProxyConnection* conn)
{
	event_time.clear();

	int count;
	conn->ReadInt(count);
	for (int i = 0; i < count; ++i)
	{
		EventTime et;
		conn->ReadByte(et.start.dow);
		conn->ReadByte(et.start.hour);
		conn->ReadByte(et.start.minute);
		conn->ReadByte(et.end.dow);
		conn->ReadByte(et.end.hour);
		conn->ReadByte(et.end.minute);
		event_time.push_back(et);
	}
	return true;
}
