package com.pearl.fcw.gm.pojo;

import com.pearl.fcw.core.pojo.DmModel;
import com.pearl.fcw.core.pojo.GoSchema;
import com.pearl.fcw.core.pojo.Schema;

@GoSchema(type=Schema.SYS)
public class WSysMission extends DmModel {

    private static final long serialVersionUID = -1845164087796873994L;

    private Integer id;

    private String name;

    private String missiontitle;

    private String description;

    private String title;

    private Integer type;

    private Integer simpleTarget;

    private Integer normalTarget;

    private Integer hardTarget;

    private Integer color;

    private Integer characterId;

    private Integer action;

    private Integer statType;

    private String normalItems;

    private String vipItems;

    private String backUp;

	private Integer weight;

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

    public String getMissiontitle() {
        return missiontitle;
    }

    public void setMissiontitle(String missiontitle) {
        this.missiontitle = missiontitle == null ? null : missiontitle.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getSimpleTarget() {
        return simpleTarget;
    }

    public void setSimpleTarget(Integer simpleTarget) {
        this.simpleTarget = simpleTarget;
    }

    public Integer getNormalTarget() {
        return normalTarget;
    }

    public void setNormalTarget(Integer normalTarget) {
        this.normalTarget = normalTarget;
    }

    public Integer getHardTarget() {
        return hardTarget;
    }

    public void setHardTarget(Integer hardTarget) {
        this.hardTarget = hardTarget;
    }

    public Integer getColor() {
        return color;
    }

    public void setColor(Integer color) {
        this.color = color;
    }

    public Integer getCharacterId() {
        return characterId;
    }

    public void setCharacterId(Integer characterId) {
        this.characterId = characterId;
    }

    public Integer getAction() {
        return action;
    }

    public void setAction(Integer action) {
        this.action = action;
    }

    public Integer getStatType() {
        return statType;
    }

    public void setStatType(Integer statType) {
        this.statType = statType;
    }

    public String getNormalItems() {
        return normalItems;
    }

    public void setNormalItems(String normalItems) {
        this.normalItems = normalItems == null ? null : normalItems.trim();
    }

    public String getVipItems() {
        return vipItems;
    }

    public void setVipItems(String vipItems) {
        this.vipItems = vipItems == null ? null : vipItems.trim();
    }

    public String getBackUp() {
        return backUp;
    }

    public void setBackUp(String backUp) {
        this.backUp = backUp == null ? null : backUp.trim();
    }

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}
}