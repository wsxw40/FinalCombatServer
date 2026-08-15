#include "pch.h"

//////////////////////////////////////////////////////////
#define MULTIPLIER	0x015a4e35L 
#define INCREMENT	1 

RandFun::RandFun()
	:m_seed(1)
{
}

void RandFun::Srand(unsigned int seed)
{
	m_seed = seed; 
}

int RandFun::Rand()
{
	m_seed = MULTIPLIER * m_seed + INCREMENT;
	
	return((int)(m_seed >> 16) & 0x7fff);
}

int RandFun::GetRandMax()
{
	return 0x7fff;
}

int RandFun::GetSeed()
{
	return ((int)(m_seed >> 16) & 0x7fff);
}

//////////////////////////////////////////////////////////
float GetGaiLv(int num, int max)
{
	float gailv = float(num) / float(max);
	
	return gailv;
}
