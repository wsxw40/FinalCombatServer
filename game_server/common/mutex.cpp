#include "mutex.h"

// Mutex
Mutex::Mutex()
{
	pthread_mutex_init(&m_Mutex, NULL);
}

Mutex::~Mutex()
{
	pthread_mutex_destroy(&m_Mutex);
}

void Mutex::Enter()
{
	pthread_mutex_lock(&m_Mutex);
}

void Mutex::Leave()
{
	pthread_mutex_unlock(&m_Mutex);
}

// AutoLock
AutoLock::AutoLock(Mutex &lock)
	: m_Lock(lock)
{
	m_Lock.Enter();
}

AutoLock::~AutoLock()
{
	m_Lock.Leave();
}
