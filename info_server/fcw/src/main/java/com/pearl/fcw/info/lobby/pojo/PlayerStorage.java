package com.pearl.fcw.info.lobby.pojo;

public interface PlayerStorage {

    long getId();

    long getPlayerId();

    void setPlayerId(long playerId);

    int getSeq();

    int getQuantity();

    int getItemType();

    boolean getIsStorage();

    // 不应该是仓库的属性

    int getUnitType();

    int getUnit();

    boolean getIsEquipped();

    void setIsStorage(boolean isStorage);

}
