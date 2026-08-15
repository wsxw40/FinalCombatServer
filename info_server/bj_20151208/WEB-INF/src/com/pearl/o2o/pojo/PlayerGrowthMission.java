package com.pearl.o2o.pojo;

import com.pearl.o2o.utils.DBRouteUtil;

/**
 * @author lifengyang
 * 
 */
public class PlayerGrowthMission extends BaseMappingBean<PlayerGrowthMission> {

	private static final long serialVersionUID = 3800222050737143012L;
	private int number;
	private byte status;
	private int playerId;
	private byte received;
	private transient SysGrowthMission sysGrowthMission;
	public PlayerGrowthMission() {
	}

	public PlayerGrowthMission(int playerId, int sysGrowthMissionId) {
		id = sysGrowthMissionId;
		this.playerId = playerId;
		this.number = 0;
		this.status = 0;
		this.received = 0;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public byte getStatus() {
		return status;
	}

	public void setStatus(byte status) {
		this.status = status;
	}

	public int getPlayerId() {
		return playerId;
	}

	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}

	public byte getReceived() {
		return received;
	}

	public void setReceived(byte received) {
		this.received = received;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public SysGrowthMission getSysGrowthMission() {
		return sysGrowthMission;
	}

	public void setSysGrowthMission(SysGrowthMission sysGrowthMission) {
		this.sysGrowthMission = sysGrowthMission;
	}

	@Override
    public String getTableSuffix() {
        return DBRouteUtil.getTableSuffix(PlayerGrowthMission.class, playerId);
    }
}
