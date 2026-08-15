package com.pearl.fcw.gm.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.pearl.fcw.core.pojo.DmModel;
import com.pearl.fcw.core.pojo.GoSchema;
import com.pearl.fcw.core.pojo.Schema;
import com.pearl.fcw.lobby.pojo.ParamObject;

@GoSchema(type = Schema.SYS)
public class WSysActivity extends DmModel {

    private static final long serialVersionUID = 7563713478715360992L;

    private Integer id;

    private String title;

    private String name;

    private Date startTime;

    private Date endTime;

    private Integer value;

    private Integer targetNum;

    private Integer action;

    private String items;

    private String theSwitch;

    private Integer withAward;

    private String isDeleted;

    private String path;

    private Integer characterId;

    private Integer achievementAction;

    private String activityName;

    private String description;

    private String backUp;

    private Integer unit;

    private Integer unitType;

    private List<Integer> itemsList = new ArrayList<>();

    private Map<String, ParamObject<Number>> numberParamMap = new HashMap<>();

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
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

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Integer getTargetNum() {
        return targetNum;
    }

    public void setTargetNum(Integer targetNum) {
        this.targetNum = targetNum;
    }

    public Integer getAction() {
        return action;
    }

    public void setAction(Integer action) {
        this.action = action;
    }

    public String getItems() {
        return items;
    }

    public void setItems(String items) {
        this.items = items == null ? null : items.trim();
    }

    public String getTheSwitch() {
        return theSwitch;
    }

    public void setTheSwitch(String theSwitch) {
        this.theSwitch = theSwitch == null ? null : theSwitch.trim();
    }

    public Integer getWithAward() {
        return withAward;
    }

    public void setWithAward(Integer withAward) {
        this.withAward = withAward;
    }

    public String getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted == null ? null : isDeleted.trim();
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path == null ? null : path.trim();
    }

    public Integer getCharacterId() {
        return characterId;
    }

    public void setCharacterId(Integer characterId) {
        this.characterId = characterId;
    }

    public Integer getAchievementAction() {
        return achievementAction;
    }

    public void setAchievementAction(Integer achievementAction) {
        this.achievementAction = achievementAction;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName == null ? null : activityName.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getBackUp() {
        return backUp;
    }

    public void setBackUp(String backUp) {
        this.backUp = backUp == null ? null : backUp.trim();
    }

    public Integer getUnit() {
        return unit;
    }

    public void setUnit(Integer unit) {
        this.unit = unit;
    }

    public Integer getUnitType() {
        return unitType;
    }

    public void setUnitType(Integer unitType) {
        this.unitType = unitType;
    }

    public List<Integer> getItemsList() {
        return itemsList;
    }

    public void setItemsList(List<Integer> itemsList) {
        this.itemsList = itemsList;
    }

    public Map<String, ParamObject<Number>> getNumberParamMap() {
        return numberParamMap;
    }

    public void setNumberParamMap(Map<String, ParamObject<Number>> numberParamMap) {
        this.numberParamMap = numberParamMap;
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