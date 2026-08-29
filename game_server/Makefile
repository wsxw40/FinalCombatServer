BIN_FILES = apexserver channelserver gameserver gmconsole loginserver logserver proxyserver matchingserver

release: 
	$(MAKE) RELEASE=1 -C common
	$(MAKE) RELEASE=1 -C log_server
	$(MAKE) RELEASE=1 -C login_server
	$(MAKE) RELEASE=1 -C proxy_server
	$(MAKE) RELEASE=1 -C channel_server
	$(MAKE) RELEASE=1 -C game_server
	$(MAKE) RELEASE=1 -C apex_server
	$(MAKE) RELEASE=1 -C matching_server
	$(MAKE) RELEASE=1 -C gm_tools
	strip $(BIN_FILES:%=bin/%)

debug: 
	$(MAKE) -C common
	$(MAKE) -C log_server
	$(MAKE) -C login_server
	$(MAKE) -C proxy_server
	$(MAKE) -C channel_server
	$(MAKE) -C game_server
	$(MAKE) -C apex_server
	$(MAKE) -C matching_server
	$(MAKE) -C gm_tools

clean: 
	$(MAKE) clean -C common
	$(MAKE) clean -C log_server
	$(MAKE) clean -C login_server
	$(MAKE) clean -C proxy_server
	$(MAKE) clean -C channel_server
	$(MAKE) clean -C game_server
	$(MAKE) clean -C apex_server
	$(MAKE) clean -C matching_server
	$(MAKE) clean -C gm_tools

	rm -f mo_server.tar.bz2

cleanlog:
	rm -fr bin/*.log
	rm -fr bin/*.out
