#include "matchingserver.h"
#include "log.h"
#include "pch.h"

#include <locale.h>
#include <cstdio>
#include <getopt.h>

enum flags
{
	flag_log = 0x100,
	flag_config,
	flag_daemon,
	flag_proxy_listen,
};

static struct option long_options[] =
{
	{"help",			0,	0,	'h'},
	{"version",			0,	0,	'v'},
	{"debug",			1,	0,	'd'},
	{"log",				1,	0,	flag_log},
	{"config",			1,	0,	flag_config},
	{"daemon",			0,	0,	flag_daemon},
	{"proxy_listen",			1,	0,	flag_proxy_listen},
	//{"proxy-addr",			1,	0,	flag_proxy_addr},
	{0, 0, 0, 0},
};

static void show_usage()
{
	fprintf(stderr,
			"Usage: social server [option]\n"
			"  --help, -h             Show this message\n"
			"  --debug, -d            Use debug mode\n"
			"  --version, -d          Print version info\n"
			"  --log,                 Set log output, special outputs are stdout, stderr, and syslog\n"
			"  --daemon,              Run as daemon mode\n"
			"  --proxy-listen=addr,   Set proxy address, default is 127.0.0.1\n"
			"  --version, -v          Print version info\n"
		   );
}

static void parse_addr(sockaddr_in & address, const char * value)
{
	if (!parse_sockaddr(address, value))
	{
		fprintf(stderr, "socialserver: invalid address -- %s\n", value);
		exit(1);
	}
}

int main(int argc, char** argv)
{
	bool as_daemon = false;
	const char * log_file = NULL;
	int debug_level = 0;

	if (NULL == setlocale(LC_CTYPE, "en_US.UTF8"))
	{
		printf("can not set local to en_US.UTF8.\n");
		return 1;
	}

	for (;;)
	{
		int option_index = 0;

		int c = getopt_long(argc, argv, "dvh", long_options, &option_index);

		if (c == -1)
			break;

		switch (c)
		{
			case 'd':
				debug_level = atoi(optarg);
				break;

			case 'h':
				show_usage();
				return 0;

			case 'v':
				{
					extern const char * build_version;
					fprintf(stderr, "socialserver version %s\n", build_version);
					exit(0);
				}
			case flag_log:
				log_file = optarg;
				break;
			case flag_config:
				{
					appcfg.LoadConfigFile(optarg);
				}
				break;

			case flag_daemon:
				as_daemon = true;
				break;

			case flag_proxy_listen:
				parse_addr(server.proxy_listener.addr, optarg);
				break;

			default:
				show_usage();
				return 1;
		}
	}
	if (as_daemon)
		if (!run_as_daemon())
			return 1;

	if (!Event::Initialize())
		return 1;

	log_set_output_level(debug_level);
	if (log_file)
		log_set_output(log_file);

	appcfg.PrintAppCfg();

	log_set_ident("match-%d", ntohs(server.proxy_listener.addr.sin_port));

	return server.Run();
}
