#pragma once 

#include "tcplistener.h"
#include "proxy.h"
#include "objectpool.h"
#include "matchingtree.h"

#include <map>
#include <set>
#include <string>
#include <bitset>

class MatchingGroup
{
	public:
		class CompareDes
		{
			public:
				bool operator() (const MatchingGroup* l, const MatchingGroup* r) const
				{
					return l->GetWeights() > r->GetWeights();
				}

		};
	public:
		MatchingGroup(): extend_flag(0), extend_times(0), extend_time_out(0), upweight_time_out(0), type(0) { }
		uint uid;
		uint GetId() const { return id; }
		bool Extend(); 

		void Initialize(uint server_id, uint _id, uint _type, uint _count, uint _level, int _mode, uint _dwLevelId);
		void Reset() { id = count = weights = org_level = min_level = max_level = game_mode = extend_flag = 0; extend_times = 0; extend_time_out = 0; upweight_time_out = 0; commited_progress = 0; progress = 0; type = 0; m_dwLevelId = 0; }

		void UpWeights() { weights++; }

		uint GetWeights() const { return weights; }

		int GetGameMode() const { return game_mode; }

		uint GetType() const { return type; }

		bool Matching(const MatchingGroup& matching_group);

		bool Update(double diff);

		uint GetCount() const { return count; }

		uint GetExtendFlag() const { return extend_flag; }

		void SetProgress(byte n) const { progress = n; }
		byte GetProgress() const { return progress; }
		void CommitProgress() const { commited_progress = progress; }
		byte GetCommitedProgress() const { return commited_progress; }

		uint GetMinLevel() const {return min_level;}
		uint GetMaxLevel() const {return max_level;}
		uint GetLevelId() const {return m_dwLevelId;}
		uint GetServerId() const {return m_dwServerId;}

	private:
		uint id;
		uint count;
		uint weights;
		uint org_level;
		uint min_level;
		uint max_level;
		int game_mode;
		uint extend_flag;
		uint extend_times;
		uint type;
		uint m_dwLevelId;
		uint m_dwServerId;

		mutable byte progress;
		mutable byte commited_progress;

		float extend_time_out;
		float upweight_time_out;

	public:
		static const int MIN_LEVEL = 1;
		// ����������ܻ����� �������ֵ���̶ܹ�
		static const int MAX_LEVEL = 120;
};

// proxy listener
class ProxyListener : public TcpListener
{
	// on client connected
	virtual void OnClientConnected(int socket, sockaddr_in addr);
};

// social server;
class MatchingServer : public TcpConnection, public BinaryStream
{
public:

	struct Time
	{
		Time(): dow(0), hour(0), minute(0) {}
		byte dow;
		byte hour;
		byte minute;
	};

	struct EventTime
	{
		Time start;
		Time end;
	};

	typedef std::multiset<MatchingGroup*, MatchingGroup::CompareDes> MatchingQueue;
	typedef std::map<uint, MatchingQueue::iterator> MatchingMap;
	typedef TreeNode<byte> MatchingTreeNode;
	typedef std::set<uint> MatchingFilter;

	class MatchingStack
	{
		public:
			MatchingStack(uint server_id, int _team_type, int _game_mode, uint dwLevelId, uint level = 0): m_dwServerId(server_id), m_team_type(_team_type), m_game_mode(_game_mode), m_dwLevelId(dwLevelId), m_size(0) 
			{
				m_dwMatchingLevel = level;
				if (level == 0)
					level_flag = ~0llu;
				else
					level_flag = 1llu << (level - 1);
			}
			bool matching(MatchingGroup *matching_group);
			bool empty() const  { return stack.empty(); }
			const MatchingGroup* top() const { return stack.back(); }
			void push(const MatchingGroup*); 
			void pop();
			size_t size() { return stack.size(); }
			void clear() { stack.clear(); }
			int game_mode() { return m_game_mode; }
			int team_type() { return m_team_type; }
			uint level_id(){return m_dwLevelId;}
		private:
			std::vector<const MatchingGroup*> stack;
			int m_game_mode;
			int m_team_type;
			uint level_flag;

			uint m_dwMatchingLevel;
			byte m_size;
			uint m_dwLevelId;
			uint m_dwServerId;
	};
	

	// constructor
	MatchingServer();

	// destructor
	~MatchingServer();

	// proxy listener
	ProxyListener proxy_listener;

	// proxy connection
	ProxyConnection proxy_connection;

	// run
	int Run();

	// update
	void Update();

	bool AddMatching(uint server_id, uint matching_id, uint type, uint count, uint matching_level, char game_mode, int nLevelId = 0);
	bool RemoveMatching(uint matching_id);

	const MatchingGroup *GetMatchingGroup(uint id);

	bool Matching(uint server_id, int &team_type, int &game_mode, int& dwLevelId, uint matching_clients[], byte matching_level = 0, int rteam_count = 0, int bteam_count = 0);

	void Status();

	int GetMatchingTeamCount(int game_mode);

	void CannelMatching(int type);

	bool IsEventTime(time_t now);

	bool OnUpdateEventTime(ProxyConnection* conn);

private:

	void DecTeamCount(int type, int count);
	void IncTeamCount(int type, int count);

	const MatchingTreeNode * GetMatchingTree(int n) { return n > 0 && n <= 8? &matching_tree[n-1] : NULL; }
	const MatchingTreeNode * GetMatchingTree(const MatchingStack &, const MatchingGroup*);

	bool Matching(const MatchingTreeNode *node, MatchingStack &stack, const MatchingFilter &filter, MatchingQueue::iterator begin, MatchingQueue::iterator end);

	int DumpStackMatchingClient(MatchingStack& stack, size_t stack_bottom, int &team_type, int &game_mode, uint matching_clients[]);

	bool BuildMatchingTree();
	bool _BuildMatchingTree(int i, MatchingTreeNode &matching_tree);

	ObjectPool<MatchingGroup> matching_pool;
	MatchingQueue matching_queue;
	MatchingMap matching_map;
	MatchingTreeNode matching_tree[8];		//д��8�� Ҫ�ĵ�ʱ��ע��

	int max_team_count;
	int team_count[8];		//���� д��8�� Ҫ�ĵ�ʱ�� Ҫ�ǵ�

	std::vector<EventTime> event_time;

};

// global social server
extern MatchingServer server;
