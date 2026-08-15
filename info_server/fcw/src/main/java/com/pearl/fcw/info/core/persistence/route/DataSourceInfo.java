package com.pearl.fcw.info.core.persistence.route;

import java.util.HashSet;
import java.util.Set;

import javax.sql.DataSource;

public class DataSourceInfo {

    private final DataSource dataSource;
    private final Set<Integer> shardIdSet;

    public DataSourceInfo(DataSource dataSource) {
        this.dataSource = dataSource;
        this.shardIdSet = new HashSet<>();
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public Set<Integer> getShardIdSet() {
        return shardIdSet;
    }

    public void addShardId(int shardId) {
        this.shardIdSet.add(shardId);
    }

}
