#include "common.h"
#include "event.h"
#include "tcpconnection.h"
#include "log.h"
#include <iconv.h>
#include <locale.h>
#include <langinfo.h>
#include <getopt.h>

enum EClientMessage
{
	CM_SendChat,
	CM_BroadcastServerChat,
	CM_BroadcastChannelChat,
	CM_FCMNotify,
	CM_RequestStatus,
};

enum EServerMessage
{
	SM_ResponseStatus,
};


class GmConnection : public TcpConnection, public NetworkStream
{
public:
	// on connected
	void OnConnected()
	{
		linger l;
		l.l_onoff = 1;
		l.l_linger = 10;

		setsockopt(connected_socket, SOL_SOCKET, SO_LINGER, &l, sizeof(l));
	}

	// on close
	void OnDisconnected()
	{
		Event::Quit();
	}

	// on message
	void OnParseMessage()
	{
		recv_buffer[recv_offset] = 0;
		printf("%s", recv_buffer);
		recv_offset = 0;
	}

	// on command
	void OnCommand()
	{
		Connect(address);

		for (int i = 0; i < argc; i++)
		{
			WriteEscaped(to_utf8(argv[i]));
			Write(" ");
		}
		Write("\nquit\n");
	}

public:
	GmConnection()
		: NetworkStream(64 * 1024)
	{
		connection = this;
		stream = this;
		convertor = iconv_open("utf-8", nl_langinfo(CODESET));
	}

	~GmConnection()
	{
		iconv_close(convertor);
	}

	void Write(const char * data)
	{
		int len = strlen(data);
		if (send_offset + len < send_buffer_size)
		{
			memcpy(send_buffer + send_offset, data, len);
			send_offset += len;
		}
	}

	void WriteEscaped(const char * data)
	{
		int len = strlen(data);
		bool escape = len > 0 ? false : true;

		for (const char * s = data; s < data + len; s++)
		{
			switch (*s)
			{
			case ' ': case '"': case '\t': case '\n': case '\r':
				escape = true;
				s = data + len;
				break;
			}
		}

		if (escape)
		{
			char * pos = send_buffer + send_offset;
			char * end = send_buffer + send_buffer_size;

			*pos++ = '"'; if (pos >= end) return;

			for (const char * s = data; s < data + len; s++)
			{
				switch (*s)
				{
				case '"': 
					*pos++ = '\\'; if (pos >= end) return;
					*pos++ = '"'; if (pos >= end) return;
					break;

				case '\n':
					*pos++ = '\\'; if (pos >= end) return;
					*pos++ = '\n'; if (pos >= end) return;
					break;

				case '\r':
					*pos++ = '\\'; if (pos >= end) return;
					*pos++ = '\r'; if (pos >= end) return;
					break;

				default:
					*pos++ = *s; if (pos >= end) return;
				}
			}

			*pos++ = '"'; if (pos >= end) return;

			send_offset = pos - send_buffer;
		}
		else
		{
			if (send_offset + len < send_buffer_size)
			{
				memcpy(send_buffer + send_offset, data, len);
				send_offset += len;
			}
		}
	}

	const char * to_utf8(char * src)
	{
		static char buffer[10240];
		size_t src_len = strlen(src);
		size_t dst_len = sizeof(buffer);

		char * dst = buffer;

		if (-1 == iconv(convertor, &src, &src_len, &dst, &dst_len))
		{
			return src;
		}
		else
		{
			dst[0] = 0;
			return buffer;
		}
	}

public:
	sockaddr_in address;
	iconv_t convertor;
	char ** argv;
	int argc;
};

static struct option long_options[] = 
{
	{"help",				0,	0,	'h'},
	{"version",				0,	0,	'v'},
	{"proxy-addr",			1,	0,	'p'},
	{0, 0, 0, 0},
};

static void show_usage()
{
	fprintf(stderr,
		"Usage: gmconsole [options] command\n"
		"  -h, --help                   Use this message\n"
		"  -v, --version                Print version info\n"
		"  -p, --proxy-addr=addr        Proxy server address, default is 127.0.0.1:9002\n"
		//"  -t, --keep-alive             Keep connection with proxy server.\n"
		);
}

int main(int argc, char** argv)
{
	setlocale(LC_CTYPE, "");
	GmConnection gm;
	set_sockaddr(gm.address, "127.0.0.1", 9002);

	for (;;)
	{
		int option_index = 0;

		int c = getopt_long(argc, argv, "hp:vt", long_options, &option_index);

		if (c == -1)
			break;

		switch (c)
		{
		case 'p':
			if (!parse_sockaddr(gm.address, optarg))
			{
				fprintf(stderr, "gmconsole: invalid address -- %s\n", optarg);
				return 1;
			}
			break;

		case 'h':
			show_usage();
			return 0;

		case 'v':
			{
				extern const char * build_version;
				fprintf(stderr, "gmconsole version %s\n", build_version);
				return 0;
			}
			break;

		default:
			show_usage();
			return 1;
		}
	}

	if (!Event::Initialize())
		return false;

	// info gm
	gm.argv = argv + optind;
	gm.argc = argc - optind;

	if (gm.argc == 0)
	{
		show_usage();
		return 1;
	}

	gm.OnCommand();
	Event::Dispatch();
	Event::Terminate();
	
	return 0;
}
