#include "pch.h"
#include <getopt.h>
#include <locale.h>

enum flags
{
	flag_log = 0x100,
	flag_daemon,
	flag_address,
};

static struct option long_options[] = 
{
	{"debug",				1,	0,	'd'},
	{"version",				0,	0,	'v'},
	{"help",				0,	0,	'h'},
	{"log",					1,	0,	flag_log},
	{"daemon",				0,	0,	flag_daemon},
	{"address",				1,	0,	flag_address},
	{0, 0, 0, 0},
};

static void show_usage()
{
	fprintf(stderr,
		"Usage: apexclient [options]\n"
		"  --debug, -d                  Use debug mode\n"
		"  --help, -h                   Use this message\n"
		"  --version, -v                Print version info\n"
		"  --log                        Set log output, special outputs are stdout, stderr, and syslog\n"
		"  --daemon                     Run as daemon\n"
		"  --address=address            Set bind address, default is :9003\n"
		);
}

int main(int argc, char** argv)
{
	bool as_daemon = false;
	int debug_level = 0;

	if (NULL == setlocale(LC_CTYPE, "zh_CN.GBK"))
	{
		printf("can not set local to zh_CN.GBK.\n");
		return 1;
	}

	for (;;)
	{
		int option_index = 0;

		int c = getopt_long(argc, argv, "dhv", long_options, &option_index);

		if (c == -1)
			break;

		switch (c)
		{
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
				fprintf(stderr, "apexclient version %s\n", build_version);
				return 0;
			}
			break;

		case flag_log:
			{
				char filename[256] = {0};
				
				make_output_name(optarg, filename, sizeof(filename));
				log_set_output(filename);
			}
			break;

		case flag_daemon:
			as_daemon = true;
			break;

		case flag_address:
			if (!parse_sockaddr(server.apex_connection.addr, optarg))
			{
				fprintf(stderr, "apexclient: invalid address -- %s\n", optarg);
				return 1;
			}
			break;

		default:
			show_usage();
			return 1;
		}
	}

	if (as_daemon)
		if (!run_as_daemon())
			return 1;
			
	log_set_output_level(debug_level);

	log_set_ident("apexclient");
	
	atexit(&log_flush);

	return server.Run();

}

