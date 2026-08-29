package com.pearl.o2o.pojo;


import java.io.Serializable;
import java.util.Date;

import com.pearl.o2o.utils.DBRouteUtil;


public class PlayerPack extends BaseMappingBean<PlayerPack> implements Serializable {
	private static final long serialVersionUID = -4802053219704456980L;
	
	private Integer		playerId;
	private Integer		playerItemId;
	private String		type;
	private Integer		packId;
	private	Integer		seq;
	private	Date		expireTime;
	private Integer 	sysCharacterId;
	
	/**
	 * @return the playerId
	 */
	public Integer getPlayerId() {
		return playerId;
	}
	/**
	 * @param playerId the playerId to set
	 */
	public void setPlayerId(Integer playerId) {
		this.playerId = playerId;
	}
	/**
	 * @return the playerItemId
	 */
	public Integer getPlayerItemId() {
		return playerItemId;
	}
	/**
	 * @param playerItemId the playerItemId to set
	 */
	public void setPlayerItemId(Integer playerItemId) {
		this.playerItemId = playerItemId;
	}
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @return the packId
	 */
	public Integer getPackId() {
		return packId;
	}
	/**
	 * @param packId the packId to set
	 */
	public void setPackId(Integer packId) {
		this.packId = packId;
	}
	/**
	 * @return the seq
	 */
	public Integer getSeq() {
		return seq;
	}
	/**
	 * @param seq the seq to set
	 */
	public void setSeq(Integer seq) {
		this.seq = seq;
	}
	/**
	 * @return the expireTime
	 */
	public Date getExpireTime() {
		return expireTime;
	}
	public Integer getSysCharacterId() {
		return sysCharacterId;
	}
	public void setSysCharacterId(Integer sysCharacterId) {
		this.sysCharacterId = sysCharacterId;
	}
	/**
	 * @param expireTime the expireTime to set
	 */
	public void setExpireTime(Date expireTime) {
		this.expireTime = expireTime;
	}
	@Override
    public String getTableSuffix() {
        return DBRouteUtil.getTableSuffix(PlayerMission.class, playerId);
    }
}
