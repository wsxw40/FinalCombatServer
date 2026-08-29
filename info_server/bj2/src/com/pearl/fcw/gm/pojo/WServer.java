package com.pearl.fcw.gm.pojo;

import com.pearl.fcw.core.pojo.DmModel;
import com.pearl.fcw.core.pojo.GoSchema;
import com.pearl.fcw.core.pojo.Schema;

@GoSchema(type = Schema.SYS)
public class WServer extends DmModel {

    private static final long serialVersionUID = 4784517427144402641L;

    private Integer id;

    private String name;

    private Integer maxLevel;

    private Integer minLevel;

    private Integer maxOnline;

    private Integer isNew;

    private Integer minFightnum;

    private String gameType;

    private Integer maxTeamLevel;

    private Integer minTeamLevel;

    private Integer serverType;

    private Integer index;

    private String remark;

	private String picture;

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getMaxLevel() {
        return maxLevel;
    }

    public void setMaxLevel(Integer maxLevel) {
        this.maxLevel = maxLevel;
    }

    public Integer getMinLevel() {
        return minLevel;
    }

    public void setMinLevel(Integer minLevel) {
        this.minLevel = minLevel;
    }

    public Integer getMaxOnline() {
        return maxOnline;
    }

    public void setMaxOnline(Integer maxOnline) {
        this.maxOnline = maxOnline;
    }

    public Integer getIsNew() {
        return isNew;
    }

    public void setIsNew(Integer isNew) {
        this.isNew = isNew;
    }

    public Integer getMinFightnum() {
        return minFightnum;
    }

    public void setMinFightnum(Integer minFightnum) {
        this.minFightnum = minFightnum;
    }

    public String getGameType() {
        return gameType;
    }

    public void setGameType(String gameType) {
        this.gameType = gameType == null ? null : gameType.trim();
    }

    public Integer getMaxTeamLevel() {
        return maxTeamLevel;
    }

    public void setMaxTeamLevel(Integer maxTeamLevel) {
        this.maxTeamLevel = maxTeamLevel;
    }

    public Integer getMinTeamLevel() {
        return minTeamLevel;
    }

    public void setMinTeamLevel(Integer minTeamLevel) {
        this.minTeamLevel = minTeamLevel;
    }

    public Integer getServerType() {
        return serverType;
    }

    public void setServerType(Integer serverType) {
        this.serverType = serverType;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}
}