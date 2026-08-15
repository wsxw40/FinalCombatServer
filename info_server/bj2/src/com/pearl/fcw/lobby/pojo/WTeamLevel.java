package com.pearl.fcw.lobby.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.pearl.fcw.core.pojo.DmExtModel;
import com.pearl.fcw.core.pojo.GoSchema;
import com.pearl.fcw.core.pojo.Schema;

@GoSchema(type = Schema.EXT)
public class WTeamLevel extends DmExtModel {

    private static final long serialVersionUID = 6725877960585878749L;

    private Integer id;

    private Integer teamId;

    private Integer levelType;

    private Integer orgLevelId;

    private String name;

    private String displayName;

    private String configPoints;

    private Date createTime;

    private Integer lastUpdateId;

    private Date lastUpdateTime;

    private Date lastBeginUpdateTime;

    private String isEditable;

    private String isDeleted;

    private List<TeamLevelConfigPoints> configPointsList = new ArrayList<>();

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public Integer getLevelType() {
        return levelType;
    }

    public void setLevelType(Integer levelType) {
        this.levelType = levelType;
    }

    public Integer getOrgLevelId() {
        return orgLevelId;
    }

    public void setOrgLevelId(Integer orgLevelId) {
        this.orgLevelId = orgLevelId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName == null ? null : displayName.trim();
    }

    public String getConfigPoints() {
        return configPoints;
    }

    public void setConfigPoints(String configPoints) {
        this.configPoints = configPoints == null ? null : configPoints.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getLastUpdateId() {
        return lastUpdateId;
    }

    public void setLastUpdateId(Integer lastUpdateId) {
        this.lastUpdateId = lastUpdateId;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public Date getLastBeginUpdateTime() {
        return lastBeginUpdateTime;
    }

    public void setLastBeginUpdateTime(Date lastBeginUpdateTime) {
        this.lastBeginUpdateTime = lastBeginUpdateTime;
    }

    public String getIsEditable() {
        return isEditable;
    }

    public void setIsEditable(String isEditable) {
        this.isEditable = isEditable == null ? null : isEditable.trim();
    }

    public String getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted == null ? null : isDeleted.trim();
    }

    public List<TeamLevelConfigPoints> getConfigPointsList() {
        return configPointsList;
    }

    public void setConfigPointsList(List<TeamLevelConfigPoints> configPointsList) {
        this.configPointsList = configPointsList;
    }

    @Override
    public Integer getShareId() {
        return teamId;
    }

    @Override
    public void setShareId(Integer shareId) {
        teamId = shareId;
    }

    @Override
    public Integer getShareAmount() {
        return null;
    }

    @Override
    public boolean getIsRemoved() {
        return "Y".equals(isDeleted);
    }

    @Override
    public void setIsRemoved(boolean isRemoved) {
        isDeleted = isRemoved ? "Y" : "N";
    }
}