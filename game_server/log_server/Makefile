CC = g++
LFLAGS = -m64
CFLAGS += \
	-c -std=gnu++14 -I ./include -I ./include/libev \
	-DEV_MULTIPLICITY=1 \
	-DEV_USE_EPOLL=1 \
	-DEV_STANDALONE=1 \
	-DEV_USE_REALTIME=0 \
	-DEV_USE_CLOCK_SYSCALL=0 \
	-DEV_FEATURES=0 \
	-DEV_SIGNAL_ENABLE=1 \
	
ifdef RELEASE
	CFLAGS += -O2 -g -DNDEBUG 
	LFLAGS += -O2 -g -rdynamic -lpthread 
else
	CFLAGS += -g -DDEBUG 
	LFLAGS += -g -rdynamic -lpthread 
endif

TARGET = ../bin/logserver
SOURCES += \
	buffer.cc \
	config.cc \
	get_log_handler.cc \
	log_manager.cc \
	log_server.cc \
	log_to_file.cc \
	main.cc \
	package.cc \
	put_log_handler.cc \
	json_value.cc \
	json_reader.cc \
	json_writer.cc \
	ev.cc \
	event.cc \


OBJS = $(SOURCES:%.cc=objs/%.o) 
DEPS = $(SOURCES:%.cc=objs/%.d)

default : $(TARGET)
rebuild : clean default

objs/%.o: %.cc
	@echo 'Buliding file: $<'
	@$(CC) $(CFLAGS) -o $@ $<
	
-include $(DEPS)

clean :
	-rm -f $(TARGET) objs/*

$(TARGET) : $(OBJS)
	@echo ' '
	@echo 'Buliding target: $@'
	$(CC) $(LFLAGS) -o $@ $(OBJS)
	@echo 'Finished building target: $@'
	@echo ' '

