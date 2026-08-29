package com.pearl.fcw.info.lobby.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.pearl.fcw.info.core.persistence.config.annotation.Schema;

@Entity
@Schema(GoSchema.SYS)
public class SysItem extends Weapon {

    private static final long serialVersionUID = -8265988504413865294L;

    @Id
    private long id;
    private String resource = null;
    private String displayName = "";
    private String description = "";
    private int type;
    private int subType;
    private int rank;  // 默认阶级
    private long decomposeId; // 被分解之后的系统物品ID
    private String icon = "";// 图标
    private int maxQuantity = 1;
    private int level; // 物品默认等级
    private int levelLimit; // 最大等级
    private String buff; // 附带被动BUFF ID
    private String sysBulletId; // 弹幕ID

    @Override
    public long getId() {
        return id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getSubType() {
        return subType;
    }

    public void setSubType(int subType) {
        this.subType = subType;
    }

    public long getDecomposeId() {
        return decomposeId;
    }

    public void setDecomposeId(long decomposeId) {
        this.decomposeId = decomposeId;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getMaxQuantity() {
        return maxQuantity;
    }

    public void setMaxQuantity(int maxQuantity) {
        this.maxQuantity = maxQuantity;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getLevelLimit() {
        return levelLimit;
    }

    public void setLevelLimit(int levelLimit) {
        this.levelLimit = levelLimit;
    }

    public String getBuff() {
        return buff;
    }

    public void setBuff(String buff) {
        this.buff = buff;
    }

    public String getSysBulletId() {
        return sysBulletId;
    }

    public void setSysBulletId(String sysBulletId) {
        this.sysBulletId = sysBulletId;
    }
}
