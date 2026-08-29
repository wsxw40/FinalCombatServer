package com.pearl.fcw.info.core.persistence.route;

public interface RouteRule {

    int determineLookupKey(long key);

    String getTableSuffix(int shardId, long key, int tableCount);

    String getTableSuffix(int shardId, int tableNum);

    long getAutoIncrement(int shardId, int tableNum);

}
