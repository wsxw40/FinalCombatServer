#! /bin/sh
ulimit -HSn 50000
java -cp $(for i in ../lib/*.jar ; do echo -n $i: ; done)$(for i in ../app/*.jar ; do echo -n $i: ; done). -Xms512m   -Xmx1536m  com.pearl.o2o.MockPorxyServer