#ifndef ACCEPTOR_INL_H_
#define ACCEPTOR_INL_H_

#include <arpa/inet.h>
#include <string.h>
#include <unistd.h>
#include <fcntl.h>
#include <assert.h>

template<typename Handler>
Acceptor<Handler>::Acceptor() {
}

template<typename Handler>
Acceptor<Handler>::~Acceptor() {
}

template<typename Handler>
int Acceptor<Handler>::Init(int max_fd) {
  max_fd_ = max_fd;
  return 0;
}

template<typename Handler>
int Acceptor<Handler>::Accept(in_port_t port) {
  sockaddr_in address;
  memset(&address, 0, sizeof(address));
  address.sin_family = AF_INET;
  address.sin_port = htons(port);
  address.sin_addr.s_addr = INADDR_ANY;

  return Accept(address);
}

template<typename Handler>
int Acceptor<Handler>::Accept(const char* ip, in_port_t port) {
  assert(ip);

  sockaddr_in address;
  memset(&address, 0, sizeof(address));
  address.sin_family = AF_INET;
  address.sin_port = htons(port);
  if (inet_pton(AF_INET, ip, &address.sin_addr) != 1)
    return -1;

  return Accept(address);
}

template<typename Handler>
int Acceptor<Handler>::Accept(const sockaddr_in& address) {
  listen_ = socket(AF_INET, SOCK_STREAM, 0);
  if (listen_ < 0)
    return -1;

  int flags = fcntl(listen_, F_GETFL);
  if (flags < 0)
    return -1;
  if (fcntl(listen_, F_SETFL, flags | O_NONBLOCK) < 0)
    return -1;

  int value = 1;
  if (setsockopt(listen_, SOL_SOCKET, SO_REUSEADDR, &value, sizeof(value)) < 0)
    return -1;

  if (bind(listen_, (sockaddr*) &address, sizeof(address)) < 0)
    return -1;

  if (listen(listen_, 128) < 0)
    return -1;

  io_.set<Acceptor<Handler>, &Acceptor<Handler>::AcceptCallBack>(this);
  io_.start(listen_, ev::READ);

  return 0;
}

template<typename Handler>
void Acceptor<Handler>::AcceptCallBack(ev::io &watcher, int revents) {
  if (EV_ERROR & revents)
    return;

  if (revents & EV_READ) {
    sockaddr_in client_address;
    socklen_t client_length = sizeof(client_address);

    int client_fd = accept(watcher.fd, (sockaddr*) &client_address, &client_length);

    if (client_fd >= max_fd_) {
      close(client_fd);
      return;
    }

    if (client_fd < 0)
      return;

    Handler* handler = Handler::ConstructHandler(client_fd);
    if (handler)
      handler->Init(client_address);
  }
}

#endif // ACCEPTOR_INL_H_
