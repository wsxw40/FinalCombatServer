#ifndef CONNECTOR_INL_H_
#define CONNECTOR_INL_H_

#include <arpa/inet.h>
#include <string.h>
#include <unistd.h>
#include <fcntl.h>
#include <assert.h>
#include <errno.h>

template<typename Handler>
Connector<Handler>::Connector() : max_fd_(0) {

}

template<typename Handler>
Connector<Handler>::~Connector() {
}

template<typename Handler>
int Connector<Handler>::Init(int max_fd) {
  max_fd_ = max_fd;
  return 0;
}

template<typename Handler>
int Connector<Handler>::Connect(const char* ip, in_port_t port) {
  assert(ip);

  sockaddr_in address;
  memset(&address, 0, sizeof(address));
  address.sin_family = AF_INET;
  address.sin_port = htons(port);
  if (inet_pton(AF_INET, ip, &address.sin_addr) != 1)
    return -1;

  return Connect(address);
}

template<typename Handler>
int Connector<Handler>::Connect(const sockaddr_in& address) {
  fd_ = socket(AF_INET, SOCK_STREAM, 0);
  if (fd_ < 0) {
    return -1;
  } else if (fd_ > max_fd_) {
    close(fd_);
    return -1;
  }

//  int flags = fcntl(fd_, F_GETFL);
//  if (flags < 0)
//    return -1;
//  if (fcntl(fd_, F_SETFL, flags | O_NONBLOCK) < 0)
//    return -1;
  int result = connect(fd_, (sockaddr*)&address, sizeof(address));
  if (result < 0) {
    return -1;
  } else {
    ConnectCallBack(io_, ev::WRITE);
    return fd_;
  }
//  if (result < 0) {
//    if (errno == EINPROGRESS) {
//      io_.set<Connector<Handler>, &Connector<Handler>::ConnectCallBack>(this);
//      io_.start(fd_, ev::READ);
//    } else {
//      return -1;
//    }
//  } else if (result == 0){
//    ConnectCallBack(io_, ev::WRITE);
//  } else {
//    return -1;
//  }

  return 0;
}

template<typename Handler>
void Connector<Handler>::ConnectCallBack(ev::io &watcher, int revents) {
  if (EV_ERROR & revents)
      return;

  if (revents & EV_WRITE) {
    Handler* handler = Handler::ConstructHandler(fd_);
    if (handler)
      handler->Init();
  }
}

#endif // CONNECTOR_INL_H_
