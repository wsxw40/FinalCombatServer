package com.pearl.o2o.pojo;

import java.io.Serializable;

import com.pearl.o2o.utils.DBRouteUtil;


public class PlayerBuff extends BaseMappingBean<PlayerBuff> implements Serializable{
	
	private static final long serialVersionUID = 3407901187750811291L;

	private Integer		playerId;
	private Integer		playerItemId;
	private Integer		buffId;

	public Integer getPlayerId() {
		return playerId;
	}
	public void setPlayerId(Integer playerId) {
		this.playerId = playerId;
	}
	public Integer getPlayerItemId() {
		return playerItemId;
	}
	public void setPlayerItemId(Integer playerItemId) {
		this.playerItemId = playerItemId;
	}
	public Integer getBuffId() {
		return buffId;
	}
	public void setBuffId(Integer buffId) {
		this.buffId = buffId;
	}
	@Override
    public String getTableSuffix() {
        return DBRouteUtil.getTableSuffix(PlayerMission.class, playerId);
    }
}
