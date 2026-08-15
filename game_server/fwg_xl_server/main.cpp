#include "pch.h"
#include <getopt.h>
#include <locale.h>

enum flags
{
	flag_log = 0x100,
	flag_daemon,
	flag_config,
	flag_address,
};

static struct option long_options[] = 
{
	{"debug",				1,	0,	'd'},
	{"version",				0,	0,	'v'},
	{"help",				0,	0,	'h'},
	{"log",					1,	0,	flag_log},
	{"daemon",				0,	0,	flag_daemon},
	{"config",				1,	0,	flag_config},
	{"address",				1,	0,	flag_address},
	{0, 0, 0, 0},
};

static void show_usage()
{
	fprintf(stderr,
		"Usage: apexserver [options]\n"
		"  --debug, -d                  Use debug mode\n"
		"  --help, -h                   Use this message\n"
		"  --version, -v                Print version info\n"
		"  --log                        Set log output, special outputs are stdout, stderr, and syslog\n"
		"  --daemon                     Run as daemon\n"
		"  --config=file                Set apex config file path\n"
		"  --address=address            Set bind address, default is :9003\n"
		);
}

int main(int argc, char** argv)
{
	bool as_daemon = false;
	const char * log_file = NULL;
	int debug_level = 0;

	if (NULL == setlocale(LC_CTYPE, "zh_CN.GBK"))
	{
		printf("can not set local to zh_CN.GBK.\n");
		return 10;
	}

	for (;;)
	{
		int option_index = 0;

		int c = getopt_long(argc, argv, "dhv", long_options, &option_index);

		if (c == -1)
			break;

		switch (c)
		{
		case 'p':
			server.listener.addr.sin_port = htons(atoi(optarg));
			break;

		case 'd':
			if (optarg)
				debug_level = atoi(optarg);
			break;

		case 'h':
			show_usage();
			return 0;

		case 'v':
			{
				extern const char * build_version;
				fprintf(stderr, "apexserver version %s\n", build_version);
				return 0;
			}
			break;

		case flag_log:
			log_file = optarg;
			break;

		case flag_daemon:
			as_daemon = true;
			break;

		case flag_config:
			{
				appcfg.LoadConfigFile(optarg);
			}
			break;

		case flag_address:
			if (!parse_sockaddr(server.listener.addr, optarg))
			{
				fprintf(stderr, "apexserver: invalid address -- %s\n", optarg);
				return 100;
			}
			break;

		default:
			show_usage();
			return 2;
		}
	}

	if (as_daemon)
		if (!run_as_daemon())
			return 3;

	if (!Event::Initialize())
		return 4;
			
	log_set_output_level(debug_level);

	if (log_file)
		log_set_output(log_file);
	
	log_set_ident("XLapexserver-%d", ntohs(server.listener.addr.sin_port));
	
	appcfg.PrintAppCfg();

	return server.Run();

}

