CC = g++
CFLAGS = -c -I../common
LFLAGS = -m64

ifdef RELEASE
	CFLAGS += -O2 -g -DNDEBUG
	LFLAGS += -O2 -g
else
	CFLAGS += -O2 -g -DDEBUG
	LFLAGS += -O2 -g
endif

TARGET = ../bin/gmconsole
SOURCES = gmconsole.cpp \


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
