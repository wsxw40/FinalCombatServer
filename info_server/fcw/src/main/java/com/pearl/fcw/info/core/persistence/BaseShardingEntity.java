package com.pearl.fcw.info.core.persistence;

public interface BaseShardingEntity extends BaseEntity {

    long getShardingKey();

}
