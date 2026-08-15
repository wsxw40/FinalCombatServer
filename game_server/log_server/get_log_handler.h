#ifndef Get_LOG_HANDLER_H_
#define Get_LOG_HANDLER_H_

#include <ev++.h>

#include "buffer.h"

class GetLogHandler {
public:
  explicit GetLogHandler(int fd);
  ~GetLogHandler();

public:
  void Init(const struct sockaddr_in& address);
  void Ruin();

  void HandleCallBack(ev::io &watcher, int revents);

  int GetFd() const;

  inline int Send(void* data, int size);
  inline int SendPackage(void* data);

  inline Buffer* GetReadBuffer();
  inline Buffer* GetWriteBuffer();

  static GetLogHandler* ConstructHandler(int fd);

private:
  void HandleInput(ev::io& watcher);
  void HandleOutput(ev::io& watcher);
  int ConfigSocket(int fd);
  int ParsePackage();

private:
  int fd_;
  ev::io io_;

  Buffer read_buffer_;
  Buffer write_buffer_;
};

inline int GetLogHandler::GetFd() const {
  return fd_;
}

inline int GetLogHandler::Send(void* data, int size) {
  io_.set(ev::READ | ev::WRITE);
  return write_buffer_.Write(data, size);
}

inline int GetLogHandler::SendPackage(void* data) {
  int size = *(static_cast<int*>(data)) + sizeof(size);
  return Send(data, size);
}

inline Buffer* GetLogHandler::GetReadBuffer() {
  return &read_buffer_;
}

inline Buffer* GetLogHandler::GetWriteBuffer() {
  return &write_buffer_;
}

#endif // Get_LOG_HANDLER_H_
