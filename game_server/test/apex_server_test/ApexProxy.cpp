#include "ApexProxy.h"

#include <pthread.h>
#include <unistd.h>
#include <stdlib.h>

_FUNC_S_SEND s_pfSend = NULL;
_FUNC_S_KILLUSER s_pKillUser = NULL;

pthread_t thread_id = 0;
volatile bool thread_stop = false;

long Rec_Fun(char cMsgId,signed int nId,const char * pBuffer,int nLen)
{
	return 1;
}

void* thread_handle(void* arg)
{
	char dummy_buffer[1024];
	
	while (!thread_stop)
	{
		if (s_pfSend && s_pKillUser)
		{
			for (int i = 0; i < 50; i++)
			{
				s_pfSend(rand(), dummy_buffer, sizeof(dummy_buffer));
				s_pKillUser(rand(), 0);
			}
		}
		
		usleep(1 * 1000);
	}
	
	return NULL;
}

extern "C"  CH_RESULT   CHSStart( _FUNC_S_SEND pfSend , _FUNC_S_REC & pfRec)
{
	s_pfSend = pfSend;
	pfRec = &Rec_Fun;
	
	thread_stop = false;
	
	pthread_create(&thread_id, NULL, &thread_handle, NULL);
	
 	return 1;
}

extern "C" void   CHSEnd()
{
	thread_stop = true;
}

extern "C" void   CHSSetFunc(void * pFunc,int nFuncFlag)
{
	s_pKillUser = (_FUNC_S_KILLUSER)pFunc;
	
	return;
}

