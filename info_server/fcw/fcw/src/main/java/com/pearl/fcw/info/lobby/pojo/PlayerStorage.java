package com.pearl.fcw.info.lobby.pojo;

public interface PlayerStorage {

	int getId();

	int getPlayerId();

	void setPlayerId(int playerId);

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
