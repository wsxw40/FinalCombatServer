#include "pch.h"
#include <getopt.h>

enum flags
{
	flag_log = 0x100,
	flag_config,
	flag_daemon,
	flag_client_listen,
	flag_channel_listen,
	flag_gm_listen,
	flag_apex_server,
	flag_match_server,
};

static struct option long_options[] = 
{
	{"help",				0,	0,	'h'},
	{"version",				0,	0,	'v'},
	{"debug",				1,	0,	'd'},
	{"info-server",			1,	0,	'i'},
	{"log",					1,	0,	flag_log},
	{"config",				1,	0,	flag_config},
	{"daemon",				0,	0,	flag_daemon},
	{"client-listen",		1,	0,	flag_client_listen},
	{"channel-listen",		1,	0,	flag_channel_listen},	
	{"gm-listen",			1,	0,	flag_gm_listen},
	{"apex-server",			1,	0,	flag_apex_server},
	{"match-server",		1,	0,	flag_match_server},
	{0, 0, 0, 0},
};

static void show_usage()
{
	fprintf(stderr,
		"Usage: proxyserver [options]\n"
		"  --debug, -d                  Use debug mode\n"
		"  --help, -h                   Use this message\n"
		"  --version, -v                Print version info\n"
		"  --log                        Set log output, special outputs are stdout, stderr, and syslog\n"
		"  --config                     Set config file\n"
		"  --daemon                     Run as daemon\n"
		"  --client-listen=addr         Client connect address, default is :9000\n"
		"  --channel-listen=addr        Channel server connect address, default is :9001\n"
		"  --gm-listen=addr             GM connect address, default is :9002\n"
		"  --apex-server=addr			Apex server address, default is 127.0.0.1:9003\n"
		"  --match-server=addr			match server address, default is 127.0.0.1:16000\n"
		"  -i addr, --info-server=addr  Add info server\n"
		);
}

static void parse_addr(sockaddr_in & address, const char * value)
{
	if (!parse_sockaddr(address, optarg))
	{
		fprintf(stderr, "proxyserver: invalid address -- %s\n", optarg);
		exit(1);
	}
}

int main(int argc, char** argv)
{
	bool as_daemon = false;
	const char * log_file = NULL;
	int debug_level = 0;

	if (NULL == setlocale(LC_CTYPE, "zh_CN.GBK"))
	{
		printf("can not set local to zh_CN.GBK.\n");
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
				debug_level = atoi(optarg);
			break;

		case 'h':
			show_usage();
			return 0;

		case 'v':
			{
				extern const char * build_version;
				fprintf(stderr, "proxyserver version %s\n", build_version);
				return 0;
			}
			break;

		case 'i':
			{
				sockaddr_in addr;
				parse_addr(addr, optarg);
				server.info_servers.push_back(addr);
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
			
		case flag_daemon:
			as_daemon = true;
			break;

		case flag_client_listen:
			parse_addr(server.client_listener.addr, optarg);
			break;

		case flag_channel_listen:
			parse_addr(server.channel_listener.addr, optarg);
			break;

		case flag_gm_listen:
			parse_addr(server.gm_listener.addr, optarg);
			break;
			
		case flag_apex_server:
			parse_addr(server.apex_connection.addr, optarg);
			break;

		case flag_match_server:
			parse_addr(server.matching_connection.addr, optarg);
			break;

		default:
			{
				fprintf(stderr, "%d\n", c);
				show_usage();
			}
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
	
	log_set_ident("proxy-%d", ntohs(server.client_listener.addr.sin_port));
	
	appcfg.PrintAppCfg();
	
	return server.Run();
}

