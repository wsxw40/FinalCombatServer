package com.pearl.o2o.pojo;

import java.util.Date;

import com.pearl.o2o.utils.DBRouteUtil;

/**
 * @author wuxiaofei
 *
 */
public class PlayerChest extends BaseMappingBean<PlayerChest> {

	private static final long serialVersionUID = 8848390461217867526L;

	private int playerId;
	private int level;
	private int isOpen;
	private int howGet;
	private int sysItemId;
	private Date createTime;
	public int getPlayerId() {
		return playerId;
	}
	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int getIsOpen() {
		return isOpen;
	}
	public void setIsOpen(int isOpen) {
		this.isOpen = isOpen;
	}
	public int getHowGet() {
		return howGet;
	}
	public void setHowGet(int howGet) {
		this.howGet = howGet;
	}
	public int getSysItemId() {
		return sysItemId;
	}
	public void setSysItemId(int sysItemId) {
		this.sysItemId = sysItemId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	@Override
    public String getTableSuffix() {
        return DBRouteUtil.getTableSuffix(PlayerChest.class, playerId);
    }
}
