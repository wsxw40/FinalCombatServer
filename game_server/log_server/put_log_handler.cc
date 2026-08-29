#include "put_log_handler.h"

#include <sys/socket.h>
#include <string.h>
#include <stdio.h>
#include <fcntl.h>
#include <netinet/in.h>
#include <netinet/tcp.h>
#include <errno.h>

#include "log_server.h"
#include "package.h"

PutLogHandler::PutLogHandler(int fd) : fd_(fd) {

}

PutLogHandler::~PutLogHandler() {

}

void PutLogHandler::Init(const struct sockaddr_in& address) {
  if (ConfigSocket(fd_) < 0)
    Ruin();

  io_.set<PutLogHandler, &PutLogHandler::HandleCallBack> (this);
  io_.start(fd_, ev::READ);

  if (read_buffer_.Init())
    Ruin();

  if (write_buffer_.Init())
    Ruin();

  char ip[32];
  if (inet_ntop(AF_INET, &address.sin_addr, ip, sizeof(ip)) != NULL) {
    snprintf(server_address_, sizeof(server_address_), "%s:%05d", ip, address.sin_port);
    strncpy(server_name_, server_address_, sizeof(server_name_));
  } else {
    Ruin();
  }
  LogServer::Static()->RegisterPutLog(this);
  int all_level = LogServer::Static()->GetLogManager()->GetAllLogLevel();
  if (all_level != -1)
    SetClientLogLevel(all_level);
}

void PutLogHandler::HandleCallBack(ev::io &watcher, int revents) {
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

void PutLogHandler::HandleInput(ev::io& watcher) {
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

void PutLogHandler::HandleOutput(ev::io& watcher) {
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

void PutLogHandler::Ruin() {
  LogServer::Static()->CanceledPutLog(this);
  read_buffer_.Ruin();
  write_buffer_.Ruin();
  io_.stop();
  close(fd_);
}

int PutLogHandler::ConfigSocket(int fd) {
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
  if (setsockopt(fd_, SOL_TCP, TCP_KEEPINTVL, &keep_interval, sizeof(keep_interval))< 0)
    return -1;

  int keep_count = 2;
  if (setsockopt(fd_, SOL_TCP, TCP_KEEPCNT, &keep_count, sizeof(keep_count)) < 0)
    return -1;

  return 0;
}

int PutLogHandler::ParsePackage() {
  while (true) {
    void* package = read_buffer_.ReadOnePackage();
    if (package != NULL) {
      Package _package(package);
      int message_type = _package.ReadInt32();
	  if (message_type == CM_Log) {
        LogServer::Static()->GetLogManager()->LogProcess(this, package);
	  } else if (message_type == CM_Ident) {
        _package.ReadString(server_name_, sizeof(server_name_));
        int log_level = LogServer::Static()->GetLogManager()->GetLogLevelByName(server_name_);
		if (log_level != -1)
			SetClientLogLevel(log_level);
	  }
    } else {
	  break;
    }
  }
  return 0;
}

void PutLogHandler::SetClientLogLevel(int new_level) {
  int32_t package_size = sizeof(int32_t) * 3;
  int32_t message_type = SM_SetLevel;
  int32_t level = new_level;
  Send(&package_size, sizeof(package_size));
  Send(&message_type, sizeof(message_type));
  Send(&level, sizeof(level));
}

PutLogHandler* PutLogHandler::ConstructHandler(int fd) {
  return LogServer::Static()->ConstructPutLogHandler(fd);
}
