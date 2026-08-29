#pragma once

class RandFun
{
public:
	// RandFun
	RandFun();
	
public:
	// Srand
	void Srand(unsigned int seed);

	// Rand
	int Rand();
	
public:
	// rand max
	int GetRandMax();
	int GetSeed();
private:
	unsigned int m_seed;
};

// float gailv = GetGaiLv(RandFun.Rand(), RandFun.GetRandMax())
// out 0-1
float GetGaiLv(int num, int max);
