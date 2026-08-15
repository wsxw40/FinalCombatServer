#include "pch.h"
#include <getopt.h>

enum flags
{
	flag_log = 0x100,
	flag_config,
	flag_daemon,
};

static struct option long_options[] = 
{
	{"help",				0,	0,	'h'},
	{"version",				0,	0,	'v'},
	{"debug",				1,	0,	'd'},
	{"log",					1,	0,	flag_log},
	{"config",				1,	0,	flag_config},
	{0, 0, 0, 0},
};

static void show_usage()
{
	fprintf(stderr,
		"Usage: gameserver [options] server_id channel_id room_id channel_socket\n"
		"  --debug, -d                  Use debug mode\n"
		"  --help, -h                   Use this message\n"
		"  --version, -v                Print version info\n"
		"  --log                        Set log output, special outputs are stdout, stderr, and syslog\n"
		"  --config                     Set config file\n"
		);
}

int main(int argc, char** argv)
{
	const char * log_file = NULL;

	if (NULL == setlocale(LC_CTYPE, "zh_CN.GBK"))
	{
		fprintf(stderr, "can not set local to zh_CN.GBK.\n");
		return 1;
	}

	for (;;)
	{
		int option_index = 0;

		int c = getopt_long(argc, argv, "dhi:v", long_options, &option_index);

		if (c == -1)
			break;

		switch (c)
		{
		case 'd':
			if (optarg)
				server.debug_level = atoi(optarg);
			break;

		case 'h':
			show_usage();
			return 0;

		case 'v':
			{
				extern const char * build_version;
				fprintf(stderr, "gameserver version %s\n", build_version);
				return 0;
			}
			break;

		case flag_log:
			log_file = optarg;
			break;
			
		case flag_config:
			{
				appcfg.LoadConfigFile(optarg);
			}
			break;

		default:
			show_usage();
			return 1;
		}
	}

	if (argc - optind != 4)
	{
		show_usage();
		return 1;
	}
	
	if (!Event::Initialize())
		return 1;

	log_set_output_level(server.debug_level);
	
	if (log_file)
		log_set_output(log_file);
	
	server.server_id = atoi(argv[optind + 0]);
	server.channel_id = atoi(argv[optind + 1]);
	server.room_id = atoi(argv[optind + 2]);
	server.channel_socket = atoi(argv[optind + 3]);

	log_set_ident("game-%d-%d-%d", server.server_id, server.channel_id, server.room_id);
	
	appcfg.PrintAppCfg();
	
	return server.Run();
}
