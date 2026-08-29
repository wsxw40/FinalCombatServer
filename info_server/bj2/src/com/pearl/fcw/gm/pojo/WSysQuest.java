package com.pearl.fcw.gm.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.pearl.fcw.core.pojo.DmModel;
import com.pearl.fcw.core.pojo.GoSchema;
import com.pearl.fcw.core.pojo.Schema;
import com.pearl.fcw.lobby.pojo.json.JsonQuestTrigger;
import com.pearl.fcw.proto.enums.EItemColor;
import com.pearl.fcw.proto.enums.EQuestUiAction;
import com.pearl.fcw.proto.enums.EQuestUiType;
import com.pearl.fcw.proto.enums.ERepeatType;

@GoSchema(type = Schema.SYS)
public class WSysQuest extends DmModel {

	private static final long serialVersionUID = 8158104279077468880L;

	private Integer id;

    private String name;

    private String nameI18n;

    private String title;

    private String titleI18n;

    private String desc;

    private String descI18n;

    private String icon;

    private String resource;

	private Integer uiType = EQuestUiType.QUEST_UI_NONE.getNumber();

	private Integer uiAction = EQuestUiAction.QA_NONE.getNumber();

	private Integer uiGroup = 0;

	private Integer uiIndex = 0;

	private Long number = 0L;

	private Integer completeCount = 1;

	private Integer repeatType = ERepeatType.ONCE.getNumber();

	private Integer repeatParam = 0;

    private Date startTime;

    private Date endTime;

	private Integer color = EItemColor.TRANSPARENT.getNumber();

	private Integer sysCharacterId = 0;

	private Integer level = 0;

	private Integer vipLevel = 0;

	private Integer parentId = 0;

	private Integer difficulty = 0;

    private String triggerInterface;

	private Integer weightRate = 0;

	private Float probability = 1F;

	private String isDeleted = "N";

	private Integer isAutoAward = 0;

	private String triggerNumber;

	private String triggerEvent;

	private String items;

	private String vipItems;

	private List<WSysItemPrice> itemList = new ArrayList<>();
	private List<WSysItemPrice> vipItemList = new ArrayList<>();
	private JsonQuestTrigger trigger;

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

    public String getNameI18n() {
        return nameI18n;
    }

    public void setNameI18n(String nameI18n) {
        this.nameI18n = nameI18n == null ? null : nameI18n.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getTitleI18n() {
        return titleI18n;
    }

    public void setTitleI18n(String titleI18n) {
        this.titleI18n = titleI18n == null ? null : titleI18n.trim();
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc == null ? null : desc.trim();
    }

    public String getDescI18n() {
        return descI18n;
    }

    public void setDescI18n(String descI18n) {
        this.descI18n = descI18n == null ? null : descI18n.trim();
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource == null ? null : resource.trim();
    }

	public Integer getUiAction() {
		return uiAction;
	}

	public void setUiAction(Integer uiAction) {
		this.uiAction = uiAction;
	}

	public Integer getUiType() {
        return uiType;
    }

    public void setUiType(Integer uiType) {
        this.uiType = uiType;
    }

    public Integer getUiGroup() {
        return uiGroup;
    }

    public void setUiGroup(Integer uiGroup) {
        this.uiGroup = uiGroup;
    }

    public Integer getUiIndex() {
        return uiIndex;
    }

    public void setUiIndex(Integer uiIndex) {
        this.uiIndex = uiIndex;
    }

    public Integer getRepeatType() {
        return repeatType;
    }

    public void setRepeatType(Integer repeatType) {
        this.repeatType = repeatType;
    }

    public Integer getRepeatParam() {
        return repeatParam;
    }

    public void setRepeatParam(Integer repeatParam) {
        this.repeatParam = repeatParam;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getColor() {
        return color;
    }

    public void setColor(Integer color) {
        this.color = color;
    }

    public Integer getSysCharacterId() {
        return sysCharacterId;
    }

    public void setSysCharacterId(Integer sysCharacterId) {
        this.sysCharacterId = sysCharacterId;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getVipLevel() {
        return vipLevel;
    }

    public void setVipLevel(Integer vipLevel) {
        this.vipLevel = vipLevel;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Integer difficulty) {
        this.difficulty = difficulty;
    }

	public String getTriggerNumber() {
		return triggerNumber;
	}

	public void setTriggerNumber(String triggerNumber) {
		this.triggerNumber = triggerNumber;
	}

	public String getTriggerInterface() {
        return triggerInterface;
    }

    public void setTriggerInterface(String triggerInterface) {
        this.triggerInterface = triggerInterface == null ? null : triggerInterface.trim();
    }

    public Integer getWeightRate() {
        return weightRate;
    }

    public void setWeightRate(Integer weightRate) {
        this.weightRate = weightRate;
    }

    public Float getProbability() {
        return probability;
    }

    public void setProbability(Float probability) {
        this.probability = probability;
    }

    public String getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted == null ? null : isDeleted.trim();
    }

	public String getTriggerEvent() {
		return triggerEvent;
	}

	public void setTriggerEvent(String triggerEvent) {
		this.triggerEvent = triggerEvent;
	}

	public Integer getIsAutoAward() {
		return isAutoAward;
	}

	public void setIsAutoAward(Integer isAutoAward) {
		this.isAutoAward = isAutoAward;
	}

	public String getItems() {
		return items;
	}

	public void setItems(String items) {
		this.items = items;
	}

	public String getVipItems() {
		return vipItems;
	}

	public void setVipItems(String vipItems) {
		this.vipItems = vipItems;
	}

	public Long getNumber() {
		return number;
	}

	public void setNumber(Long number) {
		this.number = number;
	}

	public Integer getCompleteCount() {
		return completeCount;
	}

	public void setCompleteCount(Integer completeCount) {
		this.completeCount = completeCount;
	}

	public List<WSysItemPrice> getItemList() {
		return itemList;
	}

	public void setItemList(List<WSysItemPrice> itemsList) {
		this.itemList = itemsList;
	}

	public List<WSysItemPrice> getVipItemList() {
		return vipItemList;
	}

	public void setVipItemList(List<WSysItemPrice> vipItemsList) {
		this.vipItemList = vipItemsList;
	}

	public JsonQuestTrigger getTrigger() {
		return trigger;
	}

	public void setTrigger(JsonQuestTrigger trigger) {
		this.trigger = trigger;
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