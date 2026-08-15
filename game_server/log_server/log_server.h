#ifndef LOG_SERVER_H_
#define LOG_SERVER_H_

#include <tr1/unordered_set>
#include <tr1/unordered_map>
#include <ev++.h>

#include "log_manager.h"
#include "log_to_file.h"
#include "acceptor.h"
#include "config.h"

class PutLogHandler;
class GetLogHandler;

class LogServer {
 public:
  ~LogServer();

 public:
  inline static LogServer* Static();
  inline LogToFile* GetLogToFile();
  inline LogManager* GetLogManager();
  inline Config* GetConfig();

  int Init(const char* ini_path, int max_handler_count);
  int Run();

  GetLogHandler* ConstructGetLogHandler(int fd);
  PutLogHandler* ConstructPutLogHandler(int fd);
  GetLogHandler* GetGetLogHandler(int fd);
  PutLogHandler* GetPutLogHandler(int fd);

  int RegisterGetLog(GetLogHandler* handler);
  int CanceledGetLog(GetLogHandler* handler);

  int TransferLog(void* package, int32_t log_length);

  int RegisterPutLog(PutLogHandler* handler);
  int CanceledPutLog(PutLogHandler* handler);
  PutLogHandler* GetPutLogHandlerByAddress(const std::string& address);

  void SetAllClientLogLevel(int level);
  void SetClientLogLevelByName(const std::string& name, int level);

 private:
  typedef std::tr1::unordered_set<GetLogHandler*> GetLogHandlerPtrSet;
  typedef std::tr1::unordered_map<std::string, PutLogHandler*> PutLogHandlerPtrMap;

 private:
  LogServer();
  LogServer(const LogServer& other);
  LogServer& operator=(const LogServer& other);

 private:
  Acceptor<PutLogHandler> put_acceptor_;
  Acceptor<GetLogHandler> get_acceptor_;

  ev::default_loop loop_;

  GetLogHandler* get_log_handlers_array_;
  PutLogHandler* put_log_handlers_array_;
  int max_handler_count_;

  LogToFile log_to_file_;

  LogManager log_manager_;

  GetLogHandlerPtrSet get_log_handler_set_;
  PutLogHandlerPtrMap put_log_handler_map_;

  Config config_;
};

inline LogServer* LogServer::Static() {
  static LogServer log_server;
  return &log_server;
}

inline LogToFile* LogServer::GetLogToFile() {
  return &log_to_file_;
}

inline LogManager* LogServer::GetLogManager() {
  return &log_manager_;
}

inline Config* LogServer::GetConfig() {
  return &config_;
}

#endif // LOG_SERVER_H_
