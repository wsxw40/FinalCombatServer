CC = g++
CFLAGS = -c -I../common
LFLAGS = -m64 

ifdef RELEASE
	CFLAGS += -O2 -g -DNDEBUG -Wno-deprecated
	LFLAGS += -O2 -g -rdynamic -ldl -lpthread
else
	CFLAGS += -g -DDEBUG -Wno-deprecated
	LFLAGS += -g -rdynamic -ldl -lpthread
endif

TARGET = ../bin/apexserver_test
SOURCES = ApexProxy.cpp \
		ApexReqPool.cpp \
		appcfg.cpp \
		apexserver.cpp \
		serverconnection.cpp \
		main.cpp \


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
