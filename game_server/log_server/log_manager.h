#ifndef LOG_MANAGER_H_
#define LOG_MANAGER_H_

#include <time.h>
#include <sys/time.h>
#include <stdio.h>
#include <sys/types.h>
#include <string.h>

#include <string>
#include <tr1/unordered_map>

#include <json/json.h>
#include "define.h"

class GetLogHandler;
class PutLogHandler;

class LogManager {
public:
  enum LogLevel {
    kEmerg = 0,
    kAlert = 1,
    kCrit = 2,
    kError = 3,
    kWarn = 4,
    kNotice = 5,
    kInfo = 6,
    kDebug1 = 7,
    kDebug2 = 8,
    kDebug3 = 9,
    kDebug4 = 10,
    kDebug5 = 11,
  };

  enum LogItem {
    kServerInfo = 0x1,
    kDateTime = 0x2,
    kLevel = 0x4,
    kLog = 0x8,
  };

public:
  LogManager();
  ~LogManager();

public:
  int Init();
  int LoadConfig();
  void LogProcess(PutLogHandler* handler, void* package);
  void LogControl(GetLogHandler* handler, void* package);
  int GetAllLogLevel();
  int GetLogLevelByName(const std::string& name);

private:
  struct WebFilter {
    std::string server;
    int level;
  };

  struct FileFilter {
    std::string server;
    int level;
    std::string emerg_file;
    std::string alert_file;
    std::string crit_file;
    std::string error_file;
    std::string warn_file;
    std::string notice_file;
    std::string info_file;
    std::string debug1_file;
    std::string debug2_file;
    std::string debug3_file;
    std::string debug4_file;
    std::string debug5_file;
  };

  typedef std::tr1::unordered_map<std::string, WebFilter> WebFilterMap;
  typedef std::tr1::unordered_map<std::string, FileFilter> FileFilterMap;

private:
  void ConfigToFileFilterMap(const Json::Value& server);
  void SetClientLogLevelByConfig(); 
  void WebLogProcess(char* server, int level,
                     char* log_text, char* buffer, size_t buffer_size);
  void FileLogProcess(char* server, int level,
                      char* log_text, char* buffer, size_t buffer_size);
  void GetFileName(const FileFilter& file_filter,
                   int level, char* buffer, size_t buffer_size);
  int GetDateTime(char* buffer, size_t buffer_size);
  const char* GetLevelText(int log_level);
  const char* ToTextLog(char* server_info, char* log_text, int level,
      int log_item_set, char* buffer, size_t buffer_size);
  void ProcessCommand(GetLogHandler* handler, char* command_text);
  void ExecuteCommand(GetLogHandler* handler, char* command, char* args);
  void ResponseFilter(GetLogHandler* handler);
  void ParseStatement(char* statement);
  void ClauseProcess(char* clause);
  void SplitStatement(char* statement);
  void SplitClause(char* clause);
  void SplitItem(char* item);

private:
  int put_log_level_set_;
  int web_log_level_set_;
  int web_log_item_set_;
  int file_log_item_set_;

  char clause_buffer_[128][LINE_MAX_TYPE];
  char item_buffer_[16][LINE_MAX_TYPE];
  char key_value_[2][LINE_MAX_TYPE];

  int clause_index_;
  int item_index_;
  int clause_count_;
  int item_count_;

  WebFilterMap web_filter_map_;
  FileFilterMap file_filter_map_;
};

#endif // LOG_MANAGER_H_
