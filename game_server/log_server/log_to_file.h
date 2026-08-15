#ifndef PROCESS_THREAD_H_
#define PROCESS_THREAD_H_

#include <pthread.h>
#include <stdio.h>

#include <string>
#include <tr1/unordered_map>

#include "aqueue.h"
#include "define.h"

class LogToFile {
public:
  LogToFile();
  ~LogToFile();

public:
  int Init();
  int Ruin();
  int LoadConfig();
  void OpenLogFile(const std::string& file_path);
  void Push(char* item);
  void GetFILEPtr(const std::string& file_path, FILE*& file_ptr);
  void ClearPathFILEPtrMap();

private:
  typedef std::tr1::unordered_map<std::string, FILE*> PathFILEPtrMap;

private:
  int WriteLogProcess(Byte* item);
  int MonitorFileProcess(Byte* item);
  void WriteLogIssue();
  void MonitorFileIssue();
  void WriteLog(Byte* item);
  void MonitorFile();

private:
  static void* WriteFileThreadFunction(void* arg);
  static void* MonitorFileThreadFunction(void* arg);

private:
  bool monitor_log_file_;
  int monitor_log_interval_;

  PathFILEPtrMap path_file_ptr_map_;

  AQueue<Byte, 1024 * 100> aqueue_;

  pthread_t write_file_thread_;
  pthread_t monitor_file_thread_;
  pthread_mutex_t lock_;
};

#endif // PROCESS_THREAD_H_
