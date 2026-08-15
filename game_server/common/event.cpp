#include "common.h"
#include "event.h"
#include "log.h"
#include <map>

static const int max_timer_count = 1024;

struct Timer
{
	double time;
	EventHandler handler;
	void * userdata;
};

struct EventPipe : IEvent
{
public:
	void OnRead();
	void OnWrite() {} 
	void OnClose() {}
	bool WriteTimer(const Timer & timer);

public:
	SOCKET read_socket;
	SOCKET write_socket;
};

struct EventManager
{
public:
	typedef std::multimap<double, Timer> TimerQueue;

public:
	EventManager()
		: epoll(-1)
		, running(true)
	{
	}

public:
	// epoll
	EPOLL epoll;

	// timers
	TimerQueue timers;

	// running flag
	bool running;

	// pipe
	EventPipe pipe;
};

// event manager
static EventManager * mgr = NULL;


// internal message process
void EventPipe::OnRead()
{
    struct iovec iov[1];
    struct msghdr msg;
	Timer timer;

	union {
		struct cmsghdr cm;
		char space[CMSG_SPACE(sizeof(int))];
	} cmsg;

    iov[0].iov_base = &timer;
    iov[0].iov_len = sizeof(timer);
    
	msg.msg_name = NULL;
	msg.msg_namelen = 0;
    msg.msg_iov = iov;
    msg.msg_iovlen = 1;

    msg.msg_control = (caddr_t) &cmsg;
    msg.msg_controllen = sizeof(cmsg);

	while (recvmsg(read_socket, &msg, 0) == sizeof(Timer))
	{
		if (mgr)
		{
			// insert timer to time queue.
			mgr->timers.insert(std::pair<double, Timer>(timer.time, timer));
		}
	}
}

bool EventPipe::WriteTimer(const Timer & timer)
{
    struct iovec iov[1];
    struct msghdr msg;

	union {
		struct cmsghdr cm;
		char space[CMSG_SPACE(sizeof(int))];
	} cmsg;

	msg.msg_control = NULL;
	msg.msg_controllen = 0;

    iov[0].iov_base = (void*)&timer;
    iov[0].iov_len = sizeof(timer);

	msg.msg_flags = 0;
	msg.msg_name = NULL;
	msg.msg_namelen = 0;
    msg.msg_iov = iov;
    msg.msg_iovlen = 1;

	int ret = sendmsg(write_socket, &msg, 0);

	if (ret > 0)
	{
		return ret == sizeof(timer);
	}
	else
	{
		log_write_sys(LOG_ERROR, "write timer failed(%m).");
		return false;
	}
}

// term
static void term_signal(int)
{
	Event::Quit();
}

// initialize event system.
bool Event::Initialize()
{
	signal(SIGPIPE, SIG_IGN);
	signal(SIGTERM, &term_signal);
	
	if (mgr)
	{
		log_write_sys(LOG_ERROR, "event system already initialized.");
		return false;
	}

	// create epoll
	EPOLL epoll = epoll_create(20);

	if (epoll == -1)
	{
		log_write_sys(LOG_ERROR, "epoll create failed(%m)");
		return false;
	}

	fcntl(epoll, F_SETFD, FD_CLOEXEC);

	// create event pipe
	int pipe_fd[2];

	if (socketpair(AF_LOCAL, SOCK_STREAM, 0, pipe_fd))
	{
		log_write_sys(LOG_ERROR, "pipe create faidled(%m)");
		close(epoll);
		return false;
	}

	// set non block mode for pipe fd
	fcntl(pipe_fd[0], F_SETFL, fcntl(pipe_fd[0], F_GETFL) | O_NONBLOCK);
	fcntl(pipe_fd[1], F_SETFL, fcntl(pipe_fd[1], F_GETFL) | O_NONBLOCK);

	// create
	mgr = new EventManager;
	mgr->epoll = epoll;
	mgr->pipe.read_socket = pipe_fd[0];
	mgr->pipe.write_socket = pipe_fd[1];

	AddSocket(mgr->pipe.read_socket, &mgr->pipe, true, false);

	return true;
}

// terminate event system.
void Event::Terminate()
{
	if (!mgr)
		return;

	RemoveSocket(mgr->pipe.read_socket);

	close(mgr->pipe.read_socket);
	close(mgr->pipe.write_socket);
	close(mgr->epoll);

	delete mgr;
	mgr = NULL;
}

// get time
double Event::GetTime()
{
	timeval tv;
	gettimeofday(&tv, NULL);

	return tv.tv_sec + (double)tv.tv_usec * 0.000001;
}

// add timer
bool Event::AddTimer(EventHandler handler, void * userdata, double time)
{
	Timer timer;

	if (!mgr)
		return false;

	timer.time = GetTime() + time;
	timer.handler = handler;
	timer.userdata = userdata;

	return mgr->pipe.WriteTimer(timer);
}

// add socket
bool Event::AddSocket(SOCKET socket, IEvent * handler, bool read, bool write)
{
	if (!mgr)
		return false;

	if (socket == -1)
		return false;

	epoll_event e;
	e.events = EPOLLET;
	e.data.ptr = handler;

	if (read)	e.events |= EPOLLIN;
	if (write)	e.events |= EPOLLOUT;

	if (epoll_ctl(mgr->epoll, EPOLL_CTL_ADD, socket, &e))
	{
		log_write_sys(LOG_ERROR, "EPOLL_CTL_ADD failed (%m).");
		return false;
	}

	return true;
}

// modify socket
bool Event::ModifySocket(SOCKET socket, IEvent * handler, bool read, bool write)
{
	if (!mgr)
		return false;

	if (socket == -1)
		return false;

	epoll_event e;
	e.events = EPOLLET;
	e.data.ptr = handler;

	if (read)	e.events |= EPOLLIN;
	if (write)	e.events |= EPOLLOUT;

	if (epoll_ctl(mgr->epoll, EPOLL_CTL_MOD, socket, &e))
	{
		log_write_sys(LOG_ERROR, "EPOLL_CTL_MOD failed (%m).");
		return false;
	}

	return true;
}

// remove socket
bool Event::RemoveSocket(SOCKET socket)
{
	if (!mgr)
		return false;

	if (socket == -1)
		return false;

	epoll_event e;
	e.events = EPOLLET;
	e.data.ptr = NULL;

	if (epoll_ctl(mgr->epoll, EPOLL_CTL_DEL, socket, &e))
	{
		log_write_sys(LOG_DEBUG5, "EPOLL_CTL_DEL failed (%m).");
		return false;
	}

	return true;
}

// quit
void Event::Quit()
{
	if (mgr)
	{
		mgr->running = false;
	}
}

// dispatch
void Event::Dispatch()
{
	if (!mgr)
		return;
		
	static double dispatch_time_max = -10000;
	static double dispatch_time_min = 10000;
	static double dispatch_time_sum = 0;
	static double dispatch_count = 0;

	while (mgr->running)
	{
		int wait_delta = 10;
		
		double dispatch_time = GetTime();

		// process timers.
		for (;;)
		{
			if (mgr->timers.empty())
				break;

			// first timer position
			EventManager::TimerQueue::iterator i = mgr->timers.begin();

			double update_time = GetTime();
			Timer * timer = &i->second;

			if (timer->time > update_time)
			{
				wait_delta = static_cast<int>((timer->time - update_time) * 1000);
				break;
			}

			EventHandler handler = timer->handler;
			void * userdata = timer->userdata;

			// remove this timer
			mgr->timers.erase(i);

			// execute timer
			if (handler)
				(*handler)(userdata);
		}

		// process epoll events
		epoll_event event_list[20];

		// wait events.
		int n = epoll_wait(mgr->epoll, event_list, 20, wait_delta);
		
		switch (n)
		{
		// error
		case -1:
			log_write_sys(LOG_ERROR, "wait failed : (%m)");
			break;

		// time out
		case 0:
			break;

		// event
		default:
			{
				for (int i = 0; i < n; ++ i)
				{
					int revents = event_list[i].events;
					IEvent* event = (IEvent*)event_list[i].data.ptr;

					if (revents & (EPOLLERR | EPOLLHUP))
					{
						event->OnClose();
					}
					else
					{
						if (revents & EPOLLOUT)
							event->OnWrite();

						if (revents & EPOLLIN)
							event->OnRead();
					}
				}
			} break;
		}
		
		dispatch_time = GetTime() - dispatch_time;
		
		// status
		dispatch_time_sum += dispatch_time;
		dispatch_count += 1.0;
		
		if (dispatch_time > dispatch_time_max)
			dispatch_time_max = dispatch_time;
			
		if (dispatch_time < dispatch_time_min)
			dispatch_time_min = dispatch_time;
			
		if (dispatch_time_sum >= 30.0)
		{
			if (log_get_output_level() >= LOG_DEBUG2)
				log_write(LOG_DEBUG2, "dispatch_time_sum : %f, dispatch_count : %f, dispatch_time_max : %f, dispatch_time_min : %f", 
						dispatch_time_sum, dispatch_count, dispatch_time_max, dispatch_time_min);
			
			dispatch_time_max = -10000;
			dispatch_time_min = 10000;
			dispatch_time_sum = 0;
			dispatch_count = 0;
		}
	}
}
