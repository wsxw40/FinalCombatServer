#include <getopt.h>
#include <sys/signal.h>
#include "log_server.h"

bool run_as_daemon()
{
  signal(SIGTTOU, SIG_IGN);
  signal(SIGTTIN, SIG_IGN);
  signal(SIGTSTP, SIG_IGN); 
  signal(SIGHUP, SIG_IGN);

  return daemon(1, 0) == 0;
}

enum flags
{
  flag_daemon = 0x100,
  flag_config,
};

static struct option long_options[] = 
{
  {"help",				0,	0,	'h'},
  {"config",				1,	0,	flag_config},
  {"daemon",				0,	0,	flag_daemon},
  {0, 0, 0, 0},
};

static void show_usage()
{
  fprintf(stderr,
      "Usage: logserver [options]\n"
      "  --help, -h                   Use this message\n"
      "  --config                     Set config file\n"
      "  --daemon                     Run as daemon\n"
      );
}

int main(int argc, char** argv)
{
  bool as_daemon = false;
  char config_path[PATH_MAX_TYPE] = "logserver.json";
  if (NULL == setlocale(LC_CTYPE, "zh_CN.GBK"))
  {
    printf("can not set local to zh_CN.GBK.\n");
    return 1;
  }

  for (;;)
  {
    int option_index = 0;
    int c = getopt_long(argc, argv, "h:", long_options, &option_index);
    if (c == -1)
      break;
    switch (c)
    {
      case 'h':
        show_usage();
        return 0;
      case flag_config:
        strncpy(config_path, optarg, PATH_MAX_TYPE);
        break;
      case flag_daemon:
        as_daemon = true;
        break;
      default:
        show_usage();
        return 1;
    }
  }

  if (as_daemon)
    if (!run_as_daemon())
      return 1;

  LogServer* log_server = LogServer::Static();
  if (log_server->Init(config_path, CONNECTION_MAX) < 0)
    return -1;

  if (log_server->Run() < 0)
    return -1;

  return 0;
}

