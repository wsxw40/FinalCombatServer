package com.pearl.fcw.gm.pojo;

import java.util.ArrayList;
import java.util.List;

import com.pearl.fcw.core.pojo.DmModel;
import com.pearl.fcw.core.pojo.GoSchema;
import com.pearl.fcw.core.pojo.Schema;

@GoSchema(type = Schema.SYS)
public class WSysAchievement extends DmModel{

    private static final long serialVersionUID = -4014461630925813293L;

    private Integer id;

    private String name;

    private String description;

    private String title;

    private Integer type;

    private Integer number;

    private Integer color;

    private Integer characterId;

    private Integer action;

    private Integer statType;

    private String gift;

    private String parent;

    private Integer group;

    private String backUp;

	private String isDeleted;

    private List<WPayment> giftList = new ArrayList<>();

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

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
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

    public String getGift() {
        return gift;
    }

    public void setGift(String gift) {
        this.gift = gift == null ? null : gift.trim();
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent == null ? null : parent.trim();
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

	public String getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}

	public void setBackUp(String backUp) {
        this.backUp = backUp == null ? null : backUp.trim();
    }

    public List<WPayment> getGiftList() {
        return giftList;
    }

    public void setGiftList(List<WPayment> giftList) {
        this.giftList = giftList;
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