#ifndef CONNECTOR_H_
#define CONNECTOR_H_

#include <sys/socket.h>
#include <netinet/in.h>

#include <ev++.h>

template<typename Handler>
class Connector {
public:
  Connector();
  ~Connector();

public:
  int Init(int max_fd);

  int Connect(const char* ip, in_port_t port);
  int Connect(const sockaddr_in& address);

  void ConnectCallBack(ev::io &watcher, int revents);

private:
  int fd_;
  ev::io io_;

  int max_fd_;
};

#include "connector-inl.h"

#endif // CONNECTOR_H_
