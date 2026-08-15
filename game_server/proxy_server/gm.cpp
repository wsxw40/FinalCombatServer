#include "pch.h"
#include <stdarg.h>

//---------------------------------------------------------------------
// Commands
//---------------------------------------------------------------------
struct Command
{
	const char * name;
	const char * berf;
	const char * help;
	int (*handler) (GmConnection * gm, int argc, char **argv);
};
extern Command cmdtab[];

static int makeargv(char * line, char ** argp, int argp_count)
{
	char *cp, *cp2, c;
	int margc = 0;

	cp = line;

	while ((c = *cp))
	{
		int inquote = 0;
		while (c == '\t' || c == ' ')
			c = *++cp;
		if (c == '\0')
			break;
		*argp++ = cp;
		margc += 1;
		if (margc >= argp_count)
			return -1;
		for (cp2 = cp; c != '\0'; c = *++cp)
		{
			if (inquote)
			{
				if (c == inquote)
				{
					inquote = 0;
					continue;
				}
			}
			else
			{
				if (c == '\\')
				{
					if ((c = *++cp) == '\0')
						break;
				}
				else if (c == '"')
				{
					inquote = '"';
					continue;
				}
				else if (c == '\'')
				{
					inquote = '\'';
					continue;
				}
				else if (c == ' ' || c == '\t')
					break;
			}
			*cp2++ = c;
		}
		*cp2 = '\0';
		if (c == '\0')
			break;
		cp++;
	}
	*argp++ = 0;
	return margc;
}

static Command * getcmd(const char * name)
{
	for (Command * cmd = cmdtab; cmd->name; cmd++)
	{
		if (strcmp(cmd->name, name) == 0)
			return cmd;
	}

	return NULL;
}

static char send_berf[] = "send chat message to a client.";
static char send_help[] =
"Usage: send receiver channel sender msg\n"
"  receiver     : name of the receive character.\n"
"  channel      : message channel, to simulate a private message, use recever as channel.\n"
"                 other channels are /p(team) /g(group) /sys(system message) /info(infomation).\n"
"  sender       : message sender.\n"
"  msg          : message body.\n"
;
static int send_cmd(GmConnection * gm, int argc, char *argv[])
{
	if (argc != 5)
	{
		gm->WriteStringf("%s\n", send_help);
		return 0;
	}

	ClientConnection * client = server.GetClientByName(argv[1]);
	if (client)
	{
		client->NotifyChat(argv[2], argv[3], argv[4]);
	}
	else
	{
		gm->WriteStringf("Client not online: %s\n", argv[1]);
	}

	return 0;
}

static char broadcast_berf[] = "boradcast chat message to all clients.";
static char broadcast_help[] =
"Usage: broadcast server_id channel_id channel sender msg\n"
"  server_id    : server id to broadcast the message, 0 for all servers.\n"
"  channel_id   : channel id to broadcast the message, 0 for all channels.\n"
"  channel      : message channel, to simulate a private message, use recever as channel.\n"
"                 other channels are /p(team) /g(group) /sys(system message) /info(infomation).\n"
"  sender       : message sender.\n"
"  msg          : message body.\n"
;
static int broadcast_cmd(GmConnection * gm, int argc, char *argv[])
{
	if (argc != 6)
	{
		gm->WriteStringf("%s\n", broadcast_help);
		return 0;
	}

	uint server_id = atoi(argv[1]);
	uint channel_id = atoi(argv[2]);

	if (server_id == 0)
	{
		// borading message to online users
		for (ClientConnection * c = server.client_pool.Begin(); c < server.client_pool.End(); c++)
		{
			if (c->IsOnline())
			{
				c->NotifyChat(argv[3], argv[4], argv[5]);
			}
		}
	}
	else
	{
		if (channel_id == 0)
		{
			// borading message to online users
			for (ClientConnection * c = server.client_pool.Begin(); c < server.client_pool.End(); c++)
			{
				if (c->IsOnline() && c->character_server_id == server_id)
				{
					c->NotifyChat(argv[3], argv[4], argv[5]);
				}
			}
		}
		else
		{
			ChannelConnection * channel = server.GetChannel(server_id, channel_id);
			if (channel && channel->IsReady())
			{
				channel->BroadcastChatMessage(argv[3], argv[4], argv[5]);

			}
			else
			{
				gm->WriteStringf("Invalid channel(%d-%d).", server_id, channel_id);
			}
		}
	}

	return 0;
}

static char status_berf[] = "print server status.";
static char status_help[] = "Usage: status\n";
static int status_cmd(GmConnection * gm, int argc, char *argv[])
{
	struct Writer : StatusWriter
	{
		Writer(GmConnection * gm) : gm(gm)
		{
		}

		void Write(const char * key, const char * format, ...)
		{
			char temp[512];
			va_list args;
			va_start(args, format);
			vsnprintf(temp, sizeof(temp), format, args);
			va_end(args);
			gm->WriteStringf("%-30s %s\n", key, temp);
		}

		GmConnection * gm;
	}
	writer(gm);

	if (argc != 1)
	{
		gm->WriteStringf("%s\n", status_help);
		return 0;
	}

	status.Write(writer);
}

static char list_berf[] = "list information.";
static char list_help[] = 
"Usage: list [servers, channel, character] args\n"
"  list server               : list server information.\n"
"  list channel server_id    : list channel information.\n"
"  list character server_id channel_id\n"
"                            : list channel characters.\n"
"  list characterall         : list all characters.\n"
;
static int list_cmd(GmConnection * gm, int argc, char *argv[])
{
	if (argc < 2)
		goto usage;

	argv++;
	argc--;

	if (strcmp(argv[0], "server") == 0)
	{
		if (argc != 1)
			goto usage;

		for (std::map<uint, ServerInfo>::const_iterator itr = server.servers.begin(); 
			itr != server.servers.end(); itr++)
		{
			const ServerInfo & s = itr->second;
			
			if (s.id)
				gm->WriteStringf("%d\t%s\t%d/%d\n", s.id, s.name, s.online_count, s.online_max);
		}
	}
	else if (strcmp(argv[0], "channel") == 0)
	{
		if (argc != 2)
			goto usage;

		ServerInfo * s = server.GetServerInfo(atoi(argv[1]));
		if (s)
		{
			for (std::map<uint, ChannelInfo>::const_iterator itr = s->channels.begin(); 
				itr != s->channels.end(); itr++)
			{
				const ChannelInfo & c = itr->second;
				
				if (c.id)
					gm->WriteStringf("%d\t%s\t%d/%d\n", c.id, c.name,
							c.connection ? c.connection->client_count : 0,
							c.online_max);
			}
		}
		else
		{
			gm->WriteStringf("Invalid server id : %s\n", argv[1]);
		}
	}
	else if (strcmp(argv[0], "character") == 0)
	{
		if (argc != 3)
			goto usage;

		uint server_id = atoi(argv[1]);
		uint channel_id = atoi(argv[2]);

		for (ClientConnection * client = server.client_pool.Begin(); client < server.client_pool.End(); ++client)
		{
			if (client->IsOnline())
			{
				if (client->character_server_id == server_id &&
					client->character_channel_id == channel_id)
				{
					gm->WriteStringf("%-10s %-8d %-10s LV.%-5d\n",
							client->user_name, 
							client->character_id, client->character_name, 
							client->character_level);
				}
			}
		}
	}
	else if (strcmp(argv[0], "characterall") == 0)
	{
		if (argc != 1)
			goto usage;
		
		gm->WriteStringf("characterlist start\n");
		
		for (ClientConnection * client = server.client_pool.Begin(); client < server.client_pool.End(); ++client)
		{
			if (client->IsOnline())
			{
				gm->WriteStringf("%-10s %-8d %-10s LV.%-5d\n",
						client->user_name, 
						client->character_id, client->character_name, 
						client->character_level);
			}
		}
		
		gm->WriteStringf("characterlist end\n");
	}
	else goto usage;

	return 0;

usage:
	gm->WriteStringf("%s\n", list_help);
	return 1;
}

static char kick_berf[] = "kick a character offline.";
static char kick_help[] = "Usage: kick character_name";
static int kick_cmd(GmConnection * gm, int argc, char *argv[])
{
	if (argc != 2)
	{
		gm->WriteStringf("%s\n", kick_help);
		return 1;
	}

	ClientConnection * client = server.GetClientByName(argv[1]);

	if (client)
	{
		client->ForceDisconnect("kicked");
	}
	else
	{
		gm->WriteStringf("Invalid client : %s\n", argv[1]);
		return 1;
	}

	return 0;
}

static char kickid_berf[] = "kick a user offline.";
static char kickid_help[] = "Usage: kickid userid";
static int kickid_cmd(GmConnection * gm, int argc, char *argv[])
{
	if (argc != 2)
	{
		gm->WriteStringf("%s\n", kick_help);
		return 1;
	}

	ClientConnection * client = server.GetClientById(atoi(argv[1]));

	if (client)
	{
		client->ForceDisconnect("kicked");
	}
	else
	{
		gm->WriteStringf("Invalid client : %s\n", argv[1]);
		return 1;
	}

	return 0;
}

static char debug_berf[] = "debug command.";
static char debug_help[] = 
"Usage: debug\n"
"  debug server [loglevel, reloadcfg] arg                       : send server debugcmd.\n"
"  debug channel server_id channel_id [loglevel, reloadcfg] arg : send channel debugcmd.\n"
;
static int debug_cmd(GmConnection * gm, int argc, char *argv[])
{
	if (argc < 3)
		goto usage;

	argv++;
	argc--;

	if (strcmp(argv[0], "server") == 0)
	{
		argv++;
		argc--;
		
		if (strcmp(argv[0], "loglevel") == 0)
		{
			if (argc != 2)
				goto usage;
			
			uint loglevel = atoi(argv[1]);
			log_set_output_level(loglevel);
		}
		else if (strcmp(argv[0], "reloadcfg") == 0)
		{
			if (argc != 1)
				goto usage;
			
			appcfg.ReloadConfigFile();
		}
		else
		{
			goto usage;
		}
	}
	else if (strcmp(argv[0], "channel") == 0)
	{
		argv++;
		argc--;
		
		if (argc < 3)
			goto usage;

		uint server_id = atoi(argv[0]);
		uint channel_id = atoi(argv[1]);

		ChannelConnection * c = server.GetChannel(server_id, channel_id);
		if (c)
		{
			std::vector<std::string> cmd_list;
			std::string cmd;
			
			for (int i = 2; i < argc; i++)
			{
				cmd = argv[i];
				
				cmd_list.push_back(cmd);
			}
			
			c->RequestDebugCmd(cmd_list);
		}
		else
			gm->WriteStringf("Invalid channel : %d %d\n", server_id, channel_id);
	}
	else goto usage;

	return 0;

usage:
	gm->WriteStringf("%s\n", debug_help);
	return 1;
}

static char help_berf[] = "print help information.";
static char help_help[] = "Usage: help command [command ...]";
static int help_cmd(GmConnection * gm, int argc, char *argv[])
{
	Command * c;
	if (argc == 1)
	{
		gm->WriteString("Usage: help command [command ...]\nCommands are:\n");
		for (c = cmdtab; c->name; c++)
		{
			if (c->help)
			{
				gm->WriteStringf("%-*s\t%s\n", 10, c->name, c->berf);
			}
		}
		return 0;
	}

	while (--argc > 0)
	{
		char *arg;
		arg = *++argv;
		c = getcmd(arg);
		if (c == (Command *) 0)
			gm->WriteStringf("Invalid help command %s\n", arg);
		else
		{
			gm->WriteStringf("%s\n", c->berf);
			gm->WriteStringf("%s\n", c->help);
		}
	}
	return 0;
}

static char quit_berf[] = "disconnect from server.";
static char quit_help[] = "Usage: quit";
static int quit_cmd(GmConnection * gm, int argc, char *argv[])
{
	gm->Disconnect();
}

static char echo_berf[] = "echo";
static char echo_help[] = "Usage: echo [args ...]";
static int echo_cmd(GmConnection * gm, int argc, char *argv[])
{
	for (int i = 1; i < argc; i++)
	{
		if (i != 1)
			gm->WriteStringf(" %s", argv[i]);
		else
			gm->WriteStringf("%s", argv[i]);
	}
	gm->WriteStringf("\n");
	return 0;
}

#define cmd(name)	{#name, name##_berf, name##_help, name##_cmd},
Command cmdtab[] = 
{
	cmd(status)
	cmd(list)
	cmd(send)
	cmd(broadcast)
	cmd(kick)
	cmd(kickid)
	cmd(debug)
	cmd(help)
	cmd(quit)
	cmd(echo)
	{0, 0, 0},
};
#undef cmd

//---------------------------------------------------------------------
// GmConnection
//---------------------------------------------------------------------

GmConnection::GmConnection()
{
	connection = this;
	stream = this;
}

GmConnection::~GmConnection()
{
}

// on client connected
void GmConnection::OnConnected()
{
	log_write(LOG_DEBUG1, "gm connected from %s.", inet_ntoa(client_ip));
}

void GmConnection::OnDisconnected()
{
	log_write(LOG_DEBUG1, "gm disconnected %s.", inet_ntoa(client_ip));
	server.gm_pool.Free(uid);
}

void GmConnection::OnMessage()
{
	for (;;)
	{
		char * cmd = ReadLine();

		if (cmd)
		{
			if (cmd[0])
			{
				char * margv[20];
				int margc = makeargv(cmd, margv, 20);

				if (margc > 0 && margv[0])
				{
					Command * cm = getcmd(margv[0]);

					if (cm)
					{
						BeginWrite();
						(*cm->handler)(this, margc, margv);
						EndWrite();
					}
					else
					{
						BeginWrite();
						WriteStringf("Unknown command: %s\n", margv[0]);
						EndWrite();
					}
				}
			}
		}
		else
		{
			break;
		}
	}
}
