#ifndef ACCEPTOR_H_
#define ACCEPTOR_H_

#include <sys/socket.h>
#include <netinet/in.h>

#include <ev++.h>

template<typename Handler>
class Acceptor {
public:
  Acceptor();
  ~Acceptor();

public:
  int Init(int max_fd);

  int Accept(const char* ip, in_port_t port);
  int Accept(in_port_t port);
  int Accept(const sockaddr_in& address);

  void AcceptCallBack(ev::io &watcher, int revents);

private:
  int listen_;
  ev::io io_;

  int max_fd_;
};

#include "acceptor-inl.h"

#endif // ACCEPTOR_H_
