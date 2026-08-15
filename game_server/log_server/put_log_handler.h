#ifndef PUT_LOG_HANDLER_H_
#define PUT_LOG_HANDLER_H_

#include <string>
#include <ev++.h>

#include "buffer.h"
#include "log_manager.h"

class PutLogHandler {
public:
  explicit PutLogHandler(int fd);
  ~PutLogHandler();

public:
  void Init(const struct sockaddr_in& address);
  void Ruin();

  void HandleCallBack(ev::io &watcher, int revents);

  int GetFd() const;

  inline int Send(void* data, int size);
  inline int SendPackage(void* data);

  inline Buffer* GetReadBuffer();
  inline Buffer* GetWriteBuffer();

  static PutLogHandler* ConstructHandler(int fd);
  inline char* GetServerName();
  inline char* GetServerAddress();

  void SetClientLogLevel(int new_level); 

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

  char server_name_[256];
  char server_address_[256];
};

inline int PutLogHandler::GetFd() const {
  return fd_;
}

inline int PutLogHandler::Send(void* data, int size) {
  io_.set(ev::READ | ev::WRITE);
  return write_buffer_.Write(data, size);
}

inline int PutLogHandler::SendPackage(void* data) {
  int size = *(static_cast<int*>(data)) + sizeof(size);
  return Send(data, size);
}

inline Buffer* PutLogHandler::GetReadBuffer() {
  return &read_buffer_;
}

inline Buffer* PutLogHandler::GetWriteBuffer() {
  return &write_buffer_;
}

inline char* PutLogHandler::GetServerName() {
  return server_name_;
}

inline char* PutLogHandler::GetServerAddress() {
  return server_address_;
}

#endif // PUT_LOG_HANDLER_H_
