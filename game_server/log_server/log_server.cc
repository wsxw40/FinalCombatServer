#include "log_server.h"

#include <utility>
#include <iostream>
#include "get_log_handler.h"
#include "put_log_handler.h"

LogServer::LogServer()
: loop_(EVBACKEND_EPOLL)
, get_log_handlers_array_(NULL)
, put_log_handlers_array_(NULL)
, max_handler_count_(0) {

}

LogServer::~LogServer() {

}

int LogServer::Init(const char* ini_path, int max_handler_count) {
  if (config_.Init(ini_path) < 0)
    return -1;

  max_handler_count_ = max_handler_count;
  
  put_acceptor_.Init(max_handler_count);
  get_acceptor_.Init(max_handler_count);
  
//  get_log_handlers_array_ = static_cast<GetLogHandler*>(malloc(sizeof(GetLogHandler)
//    * max_handler_count));
  put_log_handlers_array_ = static_cast<PutLogHandler*> (malloc(sizeof(PutLogHandler)
      * max_handler_count));
  //if (get_log_handlers_array_ == NULL || put_log_handlers_array_ == NULL)
  if (put_log_handlers_array_ == NULL)
   return -1;

  if (log_to_file_.Init() < 0)
    return -1;

  if (log_manager_.Init() < 0)
    return -1;

  return 0;
}

int LogServer::Run() {
  Json::Value value = config_.GetConfigItem("log_server", "put_log_port");
  in_port_t put_log_port = 0; 
  if (value.isInt()) {
    put_log_port = value.asInt();
  } else {
    return -1;
  }
  if (put_acceptor_.Accept(put_log_port) < 0)
    return -1;
  //in_port_t get_log_port = config_.GetConfigItem("log_server", "get_log_port").asInt();
  //if (get_acceptor_.Accept(get_log_port) < 0)
  //  return -1;
  loop_.run();
  return 0;
}

GetLogHandler* LogServer::ConstructGetLogHandler(int fd) {
  if (fd > max_handler_count_)
    return NULL;
  GetLogHandler* handler = get_log_handlers_array_ + fd;
  return new (handler) GetLogHandler(fd);
}

PutLogHandler* LogServer::ConstructPutLogHandler(int fd) {
  if (fd > max_handler_count_)
    return NULL;
  PutLogHandler* handler = put_log_handlers_array_ + fd;
  return new (handler) PutLogHandler(fd);
}

GetLogHandler* LogServer::GetGetLogHandler(int fd) {
  if (fd > max_handler_count_)
    return NULL;
  return get_log_handlers_array_ + fd;
}

PutLogHandler* LogServer::GetPutLogHandler(int fd) {
  if (fd > max_handler_count_)
    return NULL;
  return put_log_handlers_array_ + fd;
}

int LogServer::RegisterGetLog(GetLogHandler* handler) {
  std::pair<GetLogHandlerPtrSet::iterator, bool> result
    = get_log_handler_set_.insert(handler);
  if (!(result.second))
    return -1;
  return 0;
}

int LogServer::CanceledGetLog(GetLogHandler* handler) {
  size_t result = get_log_handler_set_.erase(handler);
  if (result == 0)
    return -1;
  return 0;
}

int LogServer::TransferLog(void* log_text, int32_t log_length) {
  if (get_log_handler_set_.empty())
    return 0;
  int transfer_handler_count_ = 0;
  int message_type = kLogText;
  int32_t package_size = log_length + sizeof(int32_t) + sizeof(message_type);
  for (GetLogHandlerPtrSet::iterator iter = get_log_handler_set_.begin();
      iter != get_log_handler_set_.end(); ++iter) {
    (*iter)->Send(&package_size, sizeof(package_size));
    (*iter)->Send(&message_type, sizeof(message_type));
    (*iter)->Send(&log_length, sizeof(log_length));
    (*iter)->Send(log_text, log_length);
    ++transfer_handler_count_;
  }
  return transfer_handler_count_;
}

int LogServer::RegisterPutLog(PutLogHandler* handler) {
  std::pair<PutLogHandlerPtrMap::iterator, bool> result = put_log_handler_map_.insert(
      std::make_pair(std::string(handler->GetServerAddress()), handler));
  if (!(result.second))
    return -1;
  return 0;
}

int LogServer::CanceledPutLog(PutLogHandler* handler) {
  PutLogHandlerPtrMap::iterator iter
    = put_log_handler_map_.find(handler->GetServerAddress());
  if (iter != put_log_handler_map_.end())
    put_log_handler_map_.erase(iter);
  return 0;
}

PutLogHandler* LogServer::GetPutLogHandlerByAddress(const std::string& address) {
  PutLogHandlerPtrMap::iterator iter = put_log_handler_map_.find(address);
  if (iter != put_log_handler_map_.end())
    return iter->second;
  return NULL;
}

void LogServer::SetAllClientLogLevel(int level) {
	for (PutLogHandlerPtrMap::iterator iter = put_log_handler_map_.begin();
			iter != put_log_handler_map_.end(); ++iter) {
		iter->second->SetClientLogLevel(level);
	}
}

void LogServer::SetClientLogLevelByName(const std::string& name, int level) {
	for (PutLogHandlerPtrMap::iterator iter = put_log_handler_map_.begin();
			iter != put_log_handler_map_.end(); ++iter) {
		if (iter->second->GetServerName() == name)
			iter->second->SetClientLogLevel(level);
	}
}
