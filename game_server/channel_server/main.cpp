#include "pch.h"
#include <getopt.h>

enum flags
{
	flag_log = 0x100,
	flag_config,
	flag_daemon,
	flag_channel_addr,
	flag_channel_domain_name,
	flag_server_id,
	flag_channel_id,
	flag_proxy_addr,
	flag_gameserver_path,
};

static struct option long_options[] = 
{
	{"help",				0,	0,	'h'},
	{"version",				0,	0,	'v'},
	{"debug",				1,	0,	'd'},
	{"log",					1,	0,	flag_log},
	{"config",				1,	0,	flag_config},
	{"daemon",				0,	0,	flag_daemon},
	{"server-id",			1,	0,	flag_server_id},
	{"channel-id",			1,	0,	flag_channel_id},	
	{"channel-addr",		1,	0,	flag_channel_addr},
	{"channel-domain-name",	1,	0,	flag_channel_domain_name},
	{"proxy-addr",			1,	0,	flag_proxy_addr},
	{"gameserver-path",		1,	0,	flag_gameserver_path},
	{0, 0, 0, 0},
};

static void show_usage()
{
	fprintf(stderr,
		"Usage: channelserver [options]\n"
		"  --debug, -d                  Use debug mode\n"
		"  --help, -h                   Use this message\n"
		"  --version, -v                Print version info\n"
		"  --log                        Set log output, special outputs are stdout, stderr, and syslog\n"
		"  --config                     Set config file\n"
		"  --daemon                     Run as daemon\n"
		"  --server-id=id               Set server id, default is 1\n"
		"  --channel-id=id              Set channel id, default is 1\n"
		"  --channel-addr=addr          Channel address, for client connection. default is 192.168.1.16:9011\n"
		"  --channel-domain-name=domain_name          Channel domain_name, for client connection. default is 192.168.1.16\n"
		"  --proxy-addr=addr            Proxy server address, default is 127.0.0.1:9001\n"
		"  --gameserver-path=path       Game server path, default is ./gameserver\n"
		);
}

static void parse_addr(sockaddr_in & address, const char * value)
{
	if (!parse_sockaddr(address, optarg))
	{
		fprintf(stderr, "channelserver: invalid address -- %s\n", optarg);
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
				fprintf(stderr, "channel version %s\n", build_version);
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
			
		case flag_daemon:
			as_daemon = true;
			break;

		case flag_channel_addr:
			{
				parse_addr(server.client_listener.addr, optarg);
				server.client_listener.addr.sin_addr.s_addr = 0;
			}
			break;
			
		case flag_channel_domain_name:
			server.m_strDomainName = optarg;
			break;

		case flag_server_id:
			server.server_id = atoi(optarg);
			break;

		case flag_channel_id:
			server.channel_id = atoi(optarg);
			break;

		case flag_proxy_addr:
			parse_addr(server.proxy_addr, optarg);
			break;

		case flag_gameserver_path:
			strncpy(server.game_server_path, optarg, sizeof(server.game_server_path));
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
	
	log_set_ident("channel-%d", ntohs(server.client_listener.addr.sin_port));
	
	appcfg.PrintAppCfg();
	
	return server.Run();
}

