#pragma once

struct DictMatch
{
	// add keyword
	static void AddKeyword(const char * keyword);

	// after add all keywords,you must call this funtion for build ac_trie.
	static void Build();

	// clear data
	static void Clear();

	// replace
	static void Replace(char * str);
};
