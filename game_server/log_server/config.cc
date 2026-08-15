#include "config.h"

#include <sys/mman.h>
#include <fcntl.h>
#include <string.h>
#include <stdio.h>
#include <sys/stat.h>

#include "log_server.h"

Config::Config() : old_mtime_(0) {

}

Config::~Config() {
  timer_.stop();
}

int Config::Init(const char* config_path) {
  strncpy(config_path_, config_path, sizeof(config_path_));
  if (LoadConfig() < 0)
    return -1;

  timer_.set<Config, &Config::TimerCallback> (this);
  timer_.set(0., RELOAD_TIME_);
  timer_.again();

  return 0;
}

void Config::TimerCallback() {
  struct stat ini_stat;
  stat(config_path_, &ini_stat);
  time_t new_mtime = ini_stat.st_mtime;
  if (new_mtime != old_mtime_)
    ReLoadConfig();
  old_mtime_ = new_mtime;
}

int Config::LoadConfig() {
  struct stat sbuf;
  if (stat(config_path_, &sbuf) < 0)
    return -1;
  int fd = open(config_path_, O_RDONLY, 0);
  if (fd < 0)
    return -1;
  const size_t max_file_size = sbuf.st_size;
  char* file_mmap
    = static_cast<char*>(mmap(0, max_file_size, PROT_READ, MAP_PRIVATE, fd, 0));
  if (!Json::Reader().parse(file_mmap, config_)) {
    return -1;
  }
  close(fd);
  if (munmap(file_mmap, max_file_size) < 0)
    return -1;
  return 0;
}

int Config::ReLoadConfig() {
  if (LoadConfig() < 0)
    return -1;
  return 0;
}

Json::Value Config::GetConfigItem(const char* key, const char* sub_key) {
  if (sub_key == NULL || strcmp(sub_key, "") == 0)
    return config_[key];
  else
    return config_[key][sub_key];
}
