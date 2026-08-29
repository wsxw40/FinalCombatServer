/**
 * 
 */
package com.pearl.o2o.pojo;

import com.google.common.primitives.Ints;


/**
 * @author lifengyang
 * 
 */
public class TeamBuffView implements Comparable<TeamBuffView>{
	private SysTeamBuff sysTeamBuff;
	private TeamBuff teamBuff;
	private SysItem sysItem;
	private int status;//0：不可用	1：待解锁	2:待激活		3：已激活 	4：贡献值不足
	private int expireMinutesLeft;
	public TeamBuffView() {
	}

	public TeamBuffView(SysTeamBuff sysTeamBuff, TeamBuff teamBuff, SysItem sysItem,int expireMinutesLeft, int status) {
		this.sysTeamBuff = sysTeamBuff;
		this.teamBuff = teamBuff;
		this.sysItem = sysItem;
		this.status = status;
		this.expireMinutesLeft = expireMinutesLeft;
	}

	public SysTeamBuff getSysTeamBuff() {
		return sysTeamBuff;
	}

	public void setSysTeamBuff(SysTeamBuff sysTeamBuff) {
		this.sysTeamBuff = sysTeamBuff;
	}

	public TeamBuff getTeamBuff() {
		return teamBuff;
	}

	public void setTeamBuff(TeamBuff teamBuff) {
		this.teamBuff = teamBuff;
	}
	
	public int getExpireMinutesLeft() {
		return expireMinutesLeft;
	}

	public void setExpireMinutesLeft(int expireMinutesLeft) {
		this.expireMinutesLeft = expireMinutesLeft;
	}

	public SysItem getSysItem() {
		return sysItem;
	}

	public void setSysItem(SysItem sysItem) {
		this.sysItem = sysItem;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public int compareTo(TeamBuffView o) {
		return Ints.compare(this.getSysItem().getIId(), o.getSysItem().getIId());
	}

}
