CC = g++
CFLAGS = -c -I../common
LFLAGS = -m64

ifdef RELEASE
	CFLAGS += -O2 -g -DNDEBUG
	LFLAGS += -O2 -g -rdynamic -lpthread
else
	CFLAGS += -g -DDEBUG
	LFLAGS += -g -rdynamic -lpthread
endif

TARGET = ../bin/channelserver
SOURCES = channelserver.cpp \
		  client.cpp \
		  proxyconnection.cpp \
		  room.cpp \
		  game.cpp \
		  main.cpp \
		  appcfg.cpp \


OBJS = $(SOURCES:%.cpp=objs/%.o) ../lib/libcommon.a objs/version.o
DEPS = $(SOURCES:%.cpp=objs/%.d)

default : $(TARGET)
rebuild : clean default

objs/version.o:
	@../version >objs/version.cpp
	$(CC) $(CFLAGS) -o $@ objs/version.cpp

objs/%.d: %.cpp
	@$(CC) -MM $(CFLAGS) $< | sed 's,\($*\)\.o[ :]*,objs/\1.o $@ : ,g' >$@

objs/%.o: %.cpp
	$(CC) $(CFLAGS) -o $@ $<

-include $(DEPS)

clean :
	rm -f $(TARGET) objs/*
	
$(TARGET) : $(OBJS)
	$(CC) $(LFLAGS) -o $@ $(OBJS)
