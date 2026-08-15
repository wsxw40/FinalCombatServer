#include "log_manager.h"

#include <assert.h>

#include "log_server.h"
#include "package.h"
#include "get_log_handler.h"
#include "put_log_handler.h"

LogManager::LogManager()
: put_log_level_set_(0xFF)
, web_log_level_set_(0xFF)
, web_log_item_set_(0xF)
, file_log_item_set_(0x8) {

}

LogManager::~LogManager() {
}

int LogManager::Init() {
  LoadConfig();
  return 0;
}

int LogManager::LoadConfig() {
  file_filter_map_.clear();
  LogServer::Static()->GetLogToFile()->ClearPathFILEPtrMap();
  file_log_item_set_ = 0x8;
  Config* config = LogServer::Static()->GetConfig();
  Json::Value server_config = config->GetConfigItem("log_server");
  Json::Value value;
  value = server_config["server_show"];
  if (value.isBool() && value.asBool())
    file_log_item_set_ |= 0x1;
  value = server_config["time_show"];
  if (value.isBool() && value.asBool())
    file_log_item_set_ |= 0x2;
  value = server_config["level_show"];
  if (value.isBool() && value.asBool())
    file_log_item_set_ |= 0x4;
  Json::ValueIterator iter = server_config.begin();
  for (; iter != server_config.end(); ++iter) {
    const Json::Value& server = *iter;
    if (server.isObject() && server["server"] != Json::Value::null)
      ConfigToFileFilterMap(server);
  }
  SetClientLogLevelByConfig();
  return 0;
}

void LogManager::SetClientLogLevelByConfig() {
  std::string all = "all";
  FileFilterMap::iterator iter = file_filter_map_.find(all);
  if (iter != file_filter_map_.end()) {
    int all_level = iter->second.level;
    LogServer::Static()->SetAllClientLogLevel(all_level);
  }
  for (iter = file_filter_map_.begin(); iter != file_filter_map_.end(); ++iter) {
    if (iter->second.server != "all") {
      int level = iter->second.level;
      LogServer::Static()->SetClientLogLevelByName(iter->second.server, level);
    }
  }
}

int LogManager::GetAllLogLevel() {
  std::string all = "all";
  FileFilterMap::iterator iter = file_filter_map_.find(all);
  if (iter != file_filter_map_.end())
    return iter->second.level;
  return -1;
}

int LogManager::GetLogLevelByName(const std::string& name) {
  FileFilterMap::iterator iter = file_filter_map_.find(name);
  if (iter != file_filter_map_.end())
    return iter->second.level;
  return -1;
}

void LogManager::ConfigToFileFilterMap(const Json::Value& server) {
  LogToFile* log_to_file = LogServer::Static()->GetLogToFile();
  FileFilter file_filter;
  Json::Value value;
  if ((value = server["server"]) != Json::Value::null) {
    if (value.isString())
      file_filter.server = value.asString();
  }
  if ((value = server["level"]) != Json::Value::null) {
    if (value.isInt())
      file_filter.level = value.asInt();
    else
      file_filter.level = kDebug5;
  }
  std::string file = "log.txt";
  if ((value = server["emerg"]) != Json::Value::null) {
    file = "log.txt";
    if (value.isString()) 
      file = value.asString();
    file_filter.emerg_file = file;
    log_to_file->OpenLogFile(file);
  }
  if ((value = server["alert"]) != Json::Value::null) {
    file = "log.txt";
    if (value.isString()) 
      file = value.asString();
    file_filter.alert_file = file;
    log_to_file->OpenLogFile(file);
  }
  if ((value = server["crit"]) != Json::Value::null) {
    file = "log.txt";
    if (value.isString()) 
      file = value.asString();
    file_filter.crit_file = file;
    log_to_file->OpenLogFile(file);
  }
  if ((value = server["error"]) != Json::Value::null) {
    file = "log.txt";
    if (value.isString()) 
      file = value.asString();
    file_filter.error_file = file;
    log_to_file->OpenLogFile(file);
  }
  if ((value = server["warn"]) != Json::Value::null) {
    file = "log.txt";
    if (value.isString()) 
      file = value.asString();
    file_filter.warn_file = file;
    log_to_file->OpenLogFile(file);
  }
  if ((value = server["notice"]) != Json::Value::null) {
    file = "log.txt";
    if (value.isString()) 
      file = value.asString();
    file_filter.notice_file = file;
    log_to_file->OpenLogFile(file);
  }
  if ((value = server["info"]) != Json::Value::null) {
    file = "log.txt";
    if (value.isString()) 
      file = value.asString();
    file_filter.info_file = file;
    log_to_file->OpenLogFile(file);
  }
  if ((value = server["debug1"]) != Json::Value::null) {
    file = "log.txt";
    if (value.isString()) 
      file = value.asString();
    file_filter.debug1_file = file;
    log_to_file->OpenLogFile(file);
  }
  if ((value = server["debug2"]) != Json::Value::null) {
    file = "log.txt";
    if (value.isString()) 
      file = value.asString();
    file_filter.debug2_file = file;
    log_to_file->OpenLogFile(file);
  }
  if ((value = server["debug3"]) != Json::Value::null) {
    file = "log.txt";
    if (value.isString()) 
      file = value.asString();
    file_filter.debug3_file = file;
    log_to_file->OpenLogFile(file);
  }
  if ((value = server["debug4"]) != Json::Value::null) {
    file = "log.txt";
    if (value.isString()) 
      file = value.asString();
    file_filter.debug4_file = file;
    log_to_file->OpenLogFile(file);
  }
  if ((value = server["debug5"]) != Json::Value::null) {
    file = "log.txt";
    if (value.isString()) 
      file = value.asString();
    file_filter.debug5_file = file;
    log_to_file->OpenLogFile(file);
  }
  file_filter_map_[file_filter.server] = file_filter;
}

void LogManager::LogProcess(PutLogHandler* handler, void* package) {
  assert(package && handler);
  char* server = handler->GetServerName();
  Package _package(package);
  int message_type = _package.ReadInt32(); 
  int level = _package.ReadInt32();
  char log_text[LINE_MAX_TYPE];
  _package.ReadString(log_text, LINE_MAX_TYPE);
  free(package);
  size_t buffer_size = LINE_MAX_TYPE + PATH_MAX_TYPE;
  char* buffer = static_cast<char*>(malloc(buffer_size));
  //WebLogProcess(server, level, log_text, buffer, buffer_size);
  FileLogProcess(server, level, log_text, buffer, buffer_size);
}

void LogManager::WebLogProcess(char* server, int level,
                               char* log_text, char* buffer, size_t buffer_size) {
  if (level & web_log_level_set_) {
    ToTextLog(server, log_text, level, web_log_item_set_, buffer, buffer_size);
    LogServer::Static()->TransferLog(buffer, strlen(buffer));
  }
}

void LogManager::FileLogProcess(char* server, int level,
                                char* log_text, char* buffer, size_t buffer_size) {
  assert(server && log_text && buffer);
  std::string server_name = server;
  char file_path[PATH_MAX_TYPE];
  FileFilterMap::iterator iter = file_filter_map_.find(server_name);
  if (iter != file_filter_map_.end()) {
    if (level <= iter->second.level) {
      GetFileName(iter->second, level, file_path, PATH_MAX_TYPE);
      if (strcmp(file_path, "") != 0) {
        char* buffer = static_cast<char*>(malloc(buffer_size));
        strncpy(buffer, file_path, PATH_MAX_TYPE);
        ToTextLog(server, log_text, level, file_log_item_set_,
                  buffer + PATH_MAX_TYPE, buffer_size - PATH_MAX_TYPE);
        LogServer::Static()->GetLogToFile()->Push(static_cast<Byte*>(buffer));
      }
    } 
  }
  server_name = "all";
  iter = file_filter_map_.find(server_name);
  if (iter != file_filter_map_.end()) {
    if (level <= iter->second.level) {
      GetFileName(iter->second, level, file_path, PATH_MAX_TYPE);
      if (strcmp(file_path, "") != 0) {
        strncpy(buffer, file_path, PATH_MAX_TYPE);
        ToTextLog(server, log_text, level, file_log_item_set_,
            buffer + PATH_MAX_TYPE, buffer_size - PATH_MAX_TYPE);
        //printf("all--file:%s, log:%s\n", buffer, buffer + PATH_MAX_TYPE);
        LogServer::Static()->GetLogToFile()->Push(static_cast<Byte*>(buffer));
        return;
      }
    }
  }
  free(buffer);
  return;
}

void LogManager::GetFileName(const FileFilter& file_filter,
                             int level, char* buffer, size_t buffer_size) {
  switch(level) {
    case kEmerg:
      strncpy(buffer, file_filter.emerg_file.c_str(), buffer_size);
      break;
    case kAlert:
      strncpy(buffer, file_filter.alert_file.c_str(), buffer_size);
      break;
    case kCrit:
      strncpy(buffer, file_filter.crit_file.c_str(), buffer_size);
      break;
    case kError:
      strncpy(buffer, file_filter.error_file.c_str(), buffer_size);
      break;
    case kWarn:
      strncpy(buffer, file_filter.warn_file.c_str(), buffer_size);
      break;
    case kNotice:
      strncpy(buffer, file_filter.notice_file.c_str(), buffer_size);
      break;
    case kInfo:
      strncpy(buffer, file_filter.info_file.c_str(), buffer_size);
      break;
    case kDebug1:
      strncpy(buffer, file_filter.debug1_file.c_str(), buffer_size);
      break;
    case kDebug2:
      strncpy(buffer, file_filter.debug2_file.c_str(), buffer_size);
      break;
    case kDebug3:
      strncpy(buffer, file_filter.debug3_file.c_str(), buffer_size);
      break;
    case kDebug4:
      strncpy(buffer, file_filter.debug4_file.c_str(), buffer_size);
      break;
    case kDebug5:
      strncpy(buffer, file_filter.debug5_file.c_str(), buffer_size);
      break;
    default:
      strncpy(buffer, "", buffer_size);
      break;
  }
}

void LogManager::LogControl(GetLogHandler* handler, void* package) {
  assert(package && handler);
  Package _package(package);
  char command[LINE_MAX_TYPE];
  _package.ReadString(command, LINE_MAX_TYPE);
  ProcessCommand(handler, command);
}

int LogManager::GetDateTime(char* buffer, size_t buffer_size) {
  assert(buffer);
  struct timeval time_value;
  gettimeofday(&time_value, NULL);
  struct tm* tm_pointer = localtime(&time_value.tv_sec);
  char datetime[32];
  strftime(datetime, 32, "%Y-%m-%d %H:%M:%S", tm_pointer);
  return snprintf(buffer, buffer_size, "%s:%03ld", datetime, time_value.tv_usec / 1000);
  return 0;
}

const char* LogManager::GetLevelText(int level) {
  switch(level) {
    case kEmerg:
      return "emerg";
    case kAlert:
      return "alert";
    case kCrit:
      return "crit";
    case kError:
      return "error";
    case kWarn:
      return "warn";
    case kNotice:
      return "notice";
    case kInfo:
      return "info";
    case kDebug1:
      return "debug_lv1";
    case kDebug2:
      return "debug_lv2";
    case kDebug3:
      return "debug_lv3";
    case kDebug4:
      return "debug_lv4";
    case kDebug5:
      return "debug_lv5";
    default:
      return "";
  }
}

void LogManager::ProcessCommand(GetLogHandler* handler, char* command_text) {
  assert(command_text && handler);
  char* command_begin = command_text;
  while (true) {
    char* char_pointer = strstr(command_begin, "=");
    if (char_pointer != NULL) {
      char command[32];
      char args[32];
      *char_pointer = '\0';
      strncpy(command, command_begin, 32);
      command_begin = ++char_pointer;
      char_pointer = strstr(command_begin, "&");
      if (char_pointer != NULL) {
        *char_pointer = '\0';
        strncpy(args, command_begin, 32);
        command_begin = ++char_pointer;
        ExecuteCommand(handler, command, args);
      } else {
        strncpy(args, command_begin, 32);
        ExecuteCommand(handler, command, args);
        return;
      }
    } else {
      return;
    }
  }
}

const char* LogManager::ToTextLog(char* server_info, char* log_text, int log_level,
                                  int log_item_set, char* buffer, size_t buffer_size) {
  strcpy(buffer, "");
  const char* null_string = "";
  const char* level = NULL;
  const char* server = NULL;
  char datetime[32];

  if (log_item_set & kDateTime) {
    GetDateTime(datetime, 32);
  } else {
    datetime[0] = '\0';
  }

  if (log_item_set & kServerInfo) {
    server = server_info;
  } else {
    server = null_string;
  }

  if (log_item_set & kLevel) {
    level = GetLevelText(log_level);
  } else {
    level = null_string;
  }

  snprintf(buffer,
           buffer_size, "[%s] [%s] [%s] %s", datetime, server, level, log_text);
  return buffer;
}

void LogManager::ExecuteCommand(GetLogHandler* handler, char* command, char* args) {
  assert(command && args);
  if (strcmp(command, "") == 0)
    return;

  int args_value = atoi(args);
  printf("command = %s, args = %d\n", command, args_value);
  if (strcmp(command, "putloglevel") == 0) {
    if (args_value != 0)
      put_log_level_set_ = args_value;
  } else if (strcmp(command, "getloglevel") == 0) {
    if (args_value != 0)
      web_log_level_set_ = args_value;
  }  else if (strcmp(command, "getlogitem") == 0) {
    if (args_value != 0)
      web_log_item_set_ = args_value;
  } else if (strcmp(command, "logfileitem") == 0) {
    if (args_value != 0)
      file_log_item_set_ = args_value;
  } else if (strcmp(command, "getlogfilter") == 0) {
    ResponseFilter(handler);
  } else if (strcmp(command, "statementfilter") == 0) {
    ParseStatement(args);
  }
}

void LogManager::ResponseFilter(GetLogHandler* handler) {
  char filter[LINE_MAX_TYPE];
  snprintf(filter, LINE_MAX_TYPE,
           "getloglevel=%d&getlogitem=%d",
           web_log_level_set_,
           web_log_item_set_);
  int MessageType = kLogFilter;
  int string_length = strlen(filter);
  int package_size = string_length + sizeof(string_length) + sizeof(MessageType);
  handler->Send(&package_size, sizeof(package_size));
  handler->Send(&MessageType, sizeof(MessageType));
  handler->Send(&string_length, sizeof(string_length));
  handler->Send(filter, string_length);
}

void LogManager::ParseStatement(char* statement) {
  SplitStatement(statement);
  for (int i = 0; i < clause_count_; ++i) {
    printf("%d : [%s]\n", i, clause_buffer_[i]);
    ClauseProcess(clause_buffer_[i]);
  }
  clause_count_ = 0;
  clause_index_ = 0;
}

void LogManager::ClauseProcess(char* clause) {
  SplitClause(clause);
  for (int i = 0; i < item_count_; ++i) {
    SplitItem(item_buffer_[i]);
    WebFilter filter;
    if (strstr(key_value_[0], "server:") != NULL) {
      filter.server = key_value_[1];
    } else if (strstr(key_value_[0], "level:") != NULL) {
      filter.level = atoi(key_value_[1]);
    }
    web_filter_map_[filter.server] = filter;
    //printf("%s -- %s\n", key_value_[0], key_value_[1]);
  }
  item_count_ = 0;
  item_index_ = 0;
}

void LogManager::SplitStatement(char* statement) {
  char* clause = statement;
  char* ptr = NULL;
  while (true) {
    ptr = strchr(clause, ';');
    if (ptr != NULL) {
      int strlen = ptr - clause;
      memcpy(clause_buffer_[clause_index_], clause, strlen);
      clause_buffer_[clause_index_][strlen] = '\0';
      ++clause_index_;
      clause = ++ptr;
      ++clause_count_;
    } else {
      if (strlen(clause) != 0) {
        strcpy(clause_buffer_[clause_index_], clause);
        ++clause_index_;
        ++clause_count_;
      }
      break;
    }
  }
}

void LogManager::SplitClause(char* clause) {
  char* item = clause;
  char* ptr = NULL;
  while (true) {
    ptr = strchr(item, ',');
    if (ptr != NULL) {
      int strlen = ptr - item;
      memcpy(item_buffer_[item_index_], item, strlen);
      item_buffer_[item_index_][strlen] = '\0';
      ++item_index_;
      item = ++ptr;
      ++item_count_;
    } else {
      if (strlen(item) != 0) {
        strcpy(item_buffer_[item_index_], item);
        ++item_index_;
        ++item_count_;
      }
      break;
    }
  }
}

void LogManager::SplitItem(char* item) {
  char* ptr = NULL;
  ptr = strchr(item, ':');
  if (ptr != NULL) {
    int strlen = ptr - item;
    memcpy(key_value_[0], item, strlen);
    key_value_[0][strlen] = '\0';
    ++ptr;
    strcpy(key_value_[1], ptr);
  }
}
