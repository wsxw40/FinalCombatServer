#include "get_log_handler.h"

#include <sys/socket.h>
#include <string.h>
#include <stdio.h>
#include <fcntl.h>
#include <netinet/in.h>
#include <netinet/tcp.h>
#include <errno.h>

#include "log_server.h"

GetLogHandler::GetLogHandler(int fd) : fd_(fd) {

}

GetLogHandler::~GetLogHandler() {

}

void GetLogHandler::Init(const struct sockaddr_in& address) {
  if (ConfigSocket(fd_) < 0)
    Ruin();

  io_.set<GetLogHandler, &GetLogHandler::HandleCallBack> (this);
  io_.start(fd_, ev::READ);

  if (read_buffer_.Init())
    Ruin();

  if (write_buffer_.Init())
    Ruin();

  LogServer::Static()->RegisterGetLog(this);
}

void GetLogHandler::HandleCallBack(ev::io &watcher, int revents) {
  if (revents & EV_ERROR)
    return;
  if (revents & EV_READ)
    HandleInput(watcher);
  if (revents & EV_WRITE)
    HandleOutput(watcher);

  if (write_buffer_.DataAmount() == 0) {
    io_.set(ev::READ);
  } else {
    io_.set(ev::READ | ev::WRITE);
  }
}

void GetLogHandler::HandleInput(ev::io& watcher) {
  //printf("GetLogHandler::HandleInput() ...\n");
  void* write_ptr_ = read_buffer_.WritePoint();
  while (true) {
    int write_surplus = read_buffer_.WriteSurplus();
    int count = recv(fd_, write_ptr_, write_surplus, 0);
    if (count > 0) {
      write_ptr_ = read_buffer_.WriteMove(count);
    } else if (count == 0) {
      if (write_surplus != 0)
        Ruin();
      break;
    } else {
      if (errno != EAGAIN && errno != EINTR && errno != EWOULDBLOCK)
        Ruin();
      break;
    }
  }
  ParsePackage();
}

void GetLogHandler::HandleOutput(ev::io& watcher) {
  //printf("GetLogHandler::HandleOutput() ...\n");
  void* read_ptr_ = write_buffer_.ReadPoint();
  while (true) {
    int data_amount = write_buffer_.DataAmount();
    if (data_amount == 0) {
      io_.set(ev::READ);
      return;
    }
    int count = send(fd_, read_ptr_, data_amount, 0);
    if (count > 0) {
      read_ptr_ = write_buffer_.ReadMove(count);
    } else {
      if (errno != EAGAIN && errno != EINTR && errno != EWOULDBLOCK)
        Ruin();
      break;
    }
  }
}

void GetLogHandler::Ruin() {
  printf("client fd %d disconnect\n", fd_);

  LogServer::Static()->CanceledGetLog(this);

  read_buffer_.Ruin();
  write_buffer_.Ruin();

  io_.stop();
  close(fd_);
}

int GetLogHandler::ConfigSocket(int fd) {
  int flags = fcntl(fd_, F_GETFL);
  if (flags < 0)
    return -1;
  if (fcntl(fd_, F_SETFL, flags | O_NONBLOCK) < 0)
    return -1;

  int nodelay = 1;
  if (setsockopt(fd_, IPPROTO_TCP, TCP_NODELAY, &nodelay, sizeof(nodelay)) < 0)
    return -1;

  linger lgr;
  lgr.l_onoff = 0;
  lgr.l_linger = 0;
  if (setsockopt(fd_, SOL_SOCKET, SO_LINGER, &lgr, sizeof(lgr)) < 0)
    return -1;

  int keep_alive = 1;
  if (setsockopt(fd, SOL_SOCKET, SO_KEEPALIVE, &keep_alive, sizeof(keep_alive)) < 0)
    return -1;

  int keep_idle = 10;
  if (setsockopt(fd_, SOL_TCP, TCP_KEEPIDLE, &keep_idle, sizeof(keep_idle)) < 0)
    return -1;

  int keep_interval = 5;
  if (setsockopt(fd_, SOL_TCP, TCP_KEEPINTVL, &keep_interval, sizeof(keep_interval)) < 0)
    return -1;

  int keep_count = 2;
  if (setsockopt(fd_, SOL_TCP, TCP_KEEPCNT, &keep_count, sizeof(keep_count)) < 0)
    return -1;

  return 0;
}

int GetLogHandler::ParsePackage() {
  while (true) {
    void* package = read_buffer_.ReadOnePackage();
    if (package != NULL) {
      LogServer::Static()->GetLogManager()->LogControl(this, package);
    } else {
      break;
    }
  }
  return 0;
}

GetLogHandler* GetLogHandler::ConstructHandler(int fd) {
  return LogServer::Static()->ConstructGetLogHandler(fd);
}
