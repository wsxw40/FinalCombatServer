#ifndef CONFIG_H_
#define CONFIG_H_

#include <json/json.h>
#include <ev++.h>

#include "define.h"

class Config {
public:
  Config();
  ~Config();

public:
  int Init(const char* config_path);
  int LoadConfig();
  int ReLoadConfig();
  inline void SetReLoad();
  Json::Value GetConfigItem(const char* key, const char* sub_key = NULL);

private:
  void TimerCallback();

private:
  char config_path_[PATH_MAX_TYPE];
  Json::Value config_;

  ev::timer timer_;
  const static int RELOAD_TIME_ = 1;

  time_t old_mtime_; 
};

#endif // CONFIG_H_
