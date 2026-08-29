#include <string.h>
#include <wchar.h>
#include "dictmatch.h"

struct TrieNode
{
public:
	TrieNode()
	{
		c = 0;	
		count = 0;
		failure = NULL;
		memset(arraynode, 0, sizeof(arraynode));
	}

	~TrieNode()
	{
		for (int i = 0; i < 256; i ++)
		{
			if (arraynode[i])
				delete arraynode[i];
		}
	}

	int GetNodeCount()
	{
		int count = 1;

		for (int i = 0; i < 256; i ++)
		{
			if (arraynode[i])
				count += arraynode[i]->GetNodeCount();
		}

		return count;
	}

	char c;						
	struct TrieNode * failure;
	struct TrieNode * arraynode[256];
	int count;
};

static TrieNode * root = NULL;

// clear all data
void DictMatch::Clear()
{
	if (root)
		delete root;

	root = NULL;
}

// add keyword
void DictMatch::AddKeyword(const char * keyword)
{
	if (!root)
		root = new TrieNode;

	struct TrieNode * p = root; 
	int i = 0;
	while (keyword[i])
	{
		unsigned char nTemp = (unsigned char)keyword[i];
		struct TrieNode * ndNew = NULL;
		if (p->arraynode[nTemp] != NULL && p->arraynode[nTemp]->c == keyword[i])
			ndNew = p->arraynode[nTemp];
		if (ndNew == NULL)
		{
			ndNew = new TrieNode();
			ndNew->c = keyword[i];
			p->arraynode[nTemp] = ndNew;
		}
		p = ndNew;
		i++;
	}
	p->count = i;
}

// after add all keywords,you must call this funtion for build ac_trie.
void DictMatch::Build()
{
	int i;
	int head = 0;
	int tail = 0;

	if (!root)
		return;

	root->failure = NULL; 
	
	// count nodes
	int node_count = root->GetNodeCount();
	TrieNode **q = new TrieNode*[node_count];
	memset(q, 0, node_count * sizeof(TrieNode*));

	q[head++] = root; 
	while (head != tail)
	{ 
		struct TrieNode * temp = q[tail++]; 
		struct TrieNode * p = NULL; 
		for (i = 0; i < 256; ++i)
		{
			if (temp->arraynode[i] != NULL)
			{
				if (temp == root)
				{
					temp->arraynode[i]->failure = root;
				}
				else
				{
					p = temp->failure;
					while (p != NULL)
					{
						if (p->arraynode[i] != NULL)
						{
							temp->arraynode[i]->failure = p->arraynode[i]; 
							break;
						}
						p = p->failure; 
					}
					if (p == NULL)
					{
						temp->arraynode[i]->failure = root;
					}
				}
				q[head++] = temp->arraynode[i]; 
			}
		}
	}

	delete [] q;
}

// replace
void DictMatch::Replace(char * str)
{
	int j; 
	int cntend = 0;
	struct TrieNode * p;
	struct TrieNode * temp;
	unsigned char cTemp;
	int dbcs_end = 0;

	if (!root)
		return;

	p = root;
	while (str[cntend])
	{
		cTemp = (unsigned char)str[cntend];
		while (p->arraynode[cTemp] == NULL && p != root) 
			p = p->failure;
		p = p->arraynode[cTemp];
		p = (p == NULL) ? root : p;
		temp = p;
		while (temp != root)
		{
			if (temp->count > 1)
			{
				int match_end = cntend + 1;
				int match_start = match_end - temp->count;
				
				while (dbcs_end < match_end)
					dbcs_end += btowc(str[dbcs_end]) == WEOF ? 2 : 1;

				if (dbcs_end == match_end)
				{
					int dbcs_start = dbcs_end;

					while (dbcs_start > match_start)
						dbcs_start -= (dbcs_start > 1 && btowc(str[dbcs_start - 2]) == WEOF) ? 2 : 1;

					for (j = match_start; j < match_end; ++j)
						str[j] = '*';
				}

				break;
			}
			temp = temp->failure;
		}
		cntend++;
	}
}

