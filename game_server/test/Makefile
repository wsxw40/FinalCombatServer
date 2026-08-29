CC = g++
CFLAGS = -I../common

ifdef RELEASE
	CFLAGS += -O2 -DNDEBUG
	LFLAGS += -O2
else
	CFLAGS += -g -DDEBUG
	LFLAGS += -g
endif

default : testserver testclient

testserver : server.cpp ../lib/libcommon.a
	$(CC) $(CFLAGS) -o testserver server.cpp ../lib/libcommon.a

testclient : client.cpp ../lib/libcommon.a
	$(CC) $(CFLAGS) -o testclient client.cpp ../lib/libcommon.a

clean :
	rm -f testserver testclient
	
rebuild : clean default

