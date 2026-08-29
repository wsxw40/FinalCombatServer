package com.pearl.fcw.lobby.pojo;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.pearl.fcw.core.pojo.DmExtModel;
import com.pearl.fcw.core.pojo.GoSchema;
import com.pearl.fcw.core.pojo.Schema;

@GoSchema(type = Schema.EXT)
public class WPlayerAchievement extends DmExtModel implements Cloneable {

    private static final long serialVersionUID = 5034732458372660902L;
	private final Pattern SYS_CHARACTER_ID_PATTERN = Pattern.compile("[\\d]+$");

    private Integer id;

    private String sysAchievementIds;

    private Integer level;

    private Integer number;

    private Integer status;

    private Integer playerId;

    private Integer totalLevel;

    private Integer group;

    private String backUp;

    private List<ParamObject<Integer>> sysAchievementIdsList = new ArrayList<>();

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public String getSysAchievementIds() {
        return sysAchievementIds;
    }

    public void setSysAchievementIds(String sysAchievementIds) {
        this.sysAchievementIds = sysAchievementIds == null ? null : sysAchievementIds.trim();
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Integer playerId) {
        this.playerId = playerId;
    }

    public Integer getTotalLevel() {
        return totalLevel;
    }

    public void setTotalLevel(Integer totalLevel) {
        this.totalLevel = totalLevel;
    }

    public Integer getGroup() {
        return group;
    }

    public void setGroup(Integer group) {
        this.group = group;
    }

    public String getBackUp() {
        return backUp;
    }

    public void setBackUp(String backUp) {
        this.backUp = backUp == null ? null : backUp.trim();
    }

    public List<ParamObject<Integer>> getSysAchievementIdsList() {
        return sysAchievementIdsList;
    }

    public void setSysAchievementIdsList(List<ParamObject<Integer>> sysAchievementIdsList) {
        this.sysAchievementIdsList = sysAchievementIdsList;
    }

    public Integer getSysCharacterId() {
		Matcher m = SYS_CHARACTER_ID_PATTERN.matcher(getClass().getSimpleName());
        if (m.find()) {
            return Integer.parseInt(m.group());
        }
        return 0;
    }

    @Override
    public Integer getShareId() {
        return playerId;
    }

    @Override
    public void setShareId(Integer shareId) {
        playerId = shareId;
    }

    @Override
    public String getTableSuffix() {
        int result = getShareId() / getShareAmount() + (getShareId() % getShareAmount() > 0 ? 1 : 0);
        result = result > getMaxShare() ? getMaxShare() : result;
        return "_" + getSysCharacterId() + "_" + result;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}