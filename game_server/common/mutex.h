#pragma once

#include <pthread.h>

// Mutex
class Mutex
{
	public:
		Mutex();
		~Mutex();
		
	public:
		void Enter();
		void Leave();
	
	private:
		pthread_mutex_t m_Mutex;
};

// AutoLock
class AutoLock
{
	public:
		AutoLock(Mutex &lock);
		~AutoLock();
		
	private:
		Mutex &m_Lock;
};
