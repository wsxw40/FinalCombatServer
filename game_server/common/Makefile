CC = g++
AR = ar
CFLAGS = -c

ifdef RELEASE
	CFLAGS += -O2 -g -DNDEBUG -Wno-narrowing
else
	CFLAGS += -g -DDEBUG -Wno-narrowing
endif

TARGET = ../lib/libcommon.a
SOURCES = common.cpp \
		  event.cpp \
		  tcplistener.cpp \
		  udplistener.cpp \
		  tcpconnection.cpp \
		  udpconnection.cpp \
		  httpconnection.cpp \
		  localconnection.cpp \
		  networkstream.cpp \
		  characterinfo.cpp \
		  debuglog.cpp \
		  log.cpp \
		  dictmatch.cpp \
		  levelinfo.cpp \
		  roomoption.cpp \
		  gag.cpp \
		  compress.cpp \
		  encoder.cpp \
		  desencoder.cpp \
			des.cpp \
			dhkey.cpp \
		  base64.cpp \
		  aes.cpp \
		  inifile.cpp \
		  mutex.cpp \


OBJS = $(SOURCES:%.cpp=objs/%.o)
DEPS = $(SOURCES:%.cpp=objs/%.d)

default : $(TARGET)
rebuild : clean default

objs/%.d: %.cpp
	@$(CC) -MM $(CFLAGS) $< | sed 's,\($*\)\.o[ :]*,objs/\1.o $@ : ,g' >$@

objs/%.o: %.cpp
	$(CC) $(CFLAGS) -o $@ $<

-include $(DEPS)

clean :
	rm -f $(TARGET) objs/*
	
$(TARGET) : $(OBJS)
	$(AR) rcs $@ $(OBJS)
