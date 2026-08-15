#include "log_to_file.h"

#include <stdio.h>
#include <unistd.h>
#include <string.h>
#include <assert.h>

#include "log_server.h"
#include "config.h"

LogToFile::LogToFile() : monitor_log_file_(true), monitor_log_interval_(1000) {
}

LogToFile::~LogToFile() {
  Ruin();
  pthread_join(write_file_thread_, NULL);
  pthread_join(monitor_file_thread_, NULL);
}

int LogToFile::Init() {
  if (LoadConfig() < 0)
    return -1;

  if (aqueue_.Init() != 0)
    return -1;

  int ret = pthread_create(&write_file_thread_,
      NULL, &LogToFile::WriteFileThreadFunction, this);
  if (ret < 0)
    return -1;

  ret = pthread_create(&monitor_file_thread_,
      NULL, &LogToFile::MonitorFileThreadFunction, this);
  if (ret < 0)
    return -1;

  return pthread_mutex_init(&lock_, NULL);
}

int LogToFile::LoadConfig() {
  Config* config = LogServer::Static()->GetConfig();
  Json::Value server_config = config->GetConfigItem("log_server");
  Json::Value value = server_config["monitor_log_file"];
  if (value.isBool())
    monitor_log_file_ = value.asBool();
  value = server_config["monitor_log_interval"];
  if (value.isInt())
    monitor_log_interval_ = value.asInt();
  return 0;
}

int LogToFile::Ruin() {
  pthread_cancel(write_file_thread_);
  pthread_cancel(monitor_file_thread_);
  ClearPathFILEPtrMap();
  return 0;
}

void LogToFile::Push(Byte* item) {
  aqueue_.Push(item);
}

void LogToFile::OpenLogFile(const std::string& file_path) {
  pthread_mutex_lock(&lock_);
  if (file_path == "")
    return;
  if (path_file_ptr_map_.find(file_path) == path_file_ptr_map_.end()) {
    FILE* file_ptr = fopen(file_path.c_str(), "a+");
    if (file_ptr != NULL) {
      path_file_ptr_map_[file_path] = file_ptr;
    }
  }
  pthread_mutex_unlock(&lock_);
}

void LogToFile::GetFILEPtr(const std::string& file_path, FILE*& file_ptr) {
  pthread_mutex_lock(&lock_);
  PathFILEPtrMap::iterator iter = path_file_ptr_map_.find(file_path);
  if (iter != path_file_ptr_map_.end()) {
    file_ptr = iter->second;
  } else {
    file_ptr = NULL;
  }
  pthread_mutex_unlock(&lock_);
}

void LogToFile::ClearPathFILEPtrMap() {
  pthread_mutex_lock(&lock_);
  for (PathFILEPtrMap::iterator iter = path_file_ptr_map_.begin();
      iter != path_file_ptr_map_.end(); ++iter) {
    fclose(iter->second);
  }
  path_file_ptr_map_.clear();
  pthread_mutex_unlock(&lock_);
}

void LogToFile::WriteLog(Byte* item) {
  std::string file_path = static_cast<char*>(item);
  FILE* file_ptr = NULL;
  GetFILEPtr(file_path, file_ptr);
  if (file_ptr != NULL) {
    char* log_text = item + PATH_MAX_TYPE;
    fputs(log_text, file_ptr);
    fputs("\n", file_ptr);
  }
}

void LogToFile::WriteLogIssue() {
  while (true) {
    Byte* item = aqueue_.Pop();
    WriteLog(item);
    free(item);
  }
}

void LogToFile::MonitorFileIssue() {
  while (true) {
    MonitorFile();
    usleep(monitor_log_interval_ * 1000);
  }
}

void LogToFile::MonitorFile() {
  if (!monitor_log_file_)
    return;

  for (PathFILEPtrMap::iterator iter = path_file_ptr_map_.begin();
      iter != path_file_ptr_map_.end(); ++iter) {
    const char* file_path = iter->first.c_str();
    if (access(file_path, F_OK) < 0) {
      pthread_mutex_lock(&lock_);
      FILE* file_ptr = iter->second;
      iter->second = fopen(file_path, "a+");
      fclose(file_ptr);
      pthread_mutex_unlock(&lock_);
    }
  }
}

void* LogToFile::WriteFileThreadFunction(void* arg) {
  LogToFile* process_thread = static_cast<LogToFile*>(arg);
  process_thread->WriteLogIssue();
  return NULL;
}

void* LogToFile::MonitorFileThreadFunction(void* arg) {
  LogToFile* process_thread = static_cast<LogToFile*>(arg);
  process_thread->MonitorFileIssue();
  return NULL;
}
