package com.pearl.fcw.lobby.pojo;

/**
 * 权重和概率接口，用于随机获取list内的成员.
 * 概率非正数，权重为正数，按照权重计算;
 * 概率正数，权重非正数，按照概率计算;
 * 两者均为正数，按照权重计算;
 * 两者均为非正数，该实例不出现;
 * 权重非正数，概率>=1，该实例必出现.
 */
public interface RandomWeight {
    default Integer getWeightRate() {//权重
        return 1;
    }

    default void setWeightRate(Integer weightRate) {
    }

    default Float getProbability() {//概率
        return 0f;
    }

    default void setProbability(Float probability) {
    }
}
