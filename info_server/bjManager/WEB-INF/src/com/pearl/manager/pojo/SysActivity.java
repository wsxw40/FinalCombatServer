package com.pearl.manager.pojo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class SysActivity extends BaseMappingBean<SysActivity> {
	private static final long serialVersionUID = -268751617451958697L;
	private String name;
	private String title;
	private Date startTime;
	private Date endTime;
	private int value;
	private int targetNum;
	private int action;
	private String theSwitch;
	private String items;
	private String isDeleted;
	private String path;
	private String startTimeStr;
	private String endTimeStr;
	private int chracter_id;
	private int achievement_action;
	private int needAward = 1;
	private SysItem sysItem;
	private List<SysItem> itemList;
	private String activityName;
	private String itemsStr;
	private String description;
	private int withAward;
	private int unit;
	private int unitType;
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<SysItem> getItemList() {
		return itemList;
	}
	public void setItemList(List<SysItem> itemList) {
		this.itemList = itemList;
	}
	public int getNeedAward() {
		return needAward;
	}
	public void setNeedAward(int needAward) {
		this.needAward = needAward;
	}
	public void initNeedAward(){
		if(action == 1 || action == 2 || action == 3 || action==4  || action == 9 || action == 11|| action == 16|| action == 17 || action == 12 || action == 13 || action == 14 || action == 15){
			needAward = 0;
		}
	}
	public int getChracter_id() {
		return chracter_id;
	}
	public void setChracter_id(int chracter_id) {
		this.chracter_id = chracter_id;
	}
	public int getAchievement_action() {
		return achievement_action;
	}
	public void setAchievement_action(int achievement_action) {
		this.achievement_action = achievement_action;
	}
	public String getStartTimeStr() {
		return startTimeStr;
	}
	public void setStartTimeStr(String startTimeStr) {
		this.startTimeStr = startTimeStr;
	}
	public String getEndTimeStr() {
		return endTimeStr;
	}
	public void setEndTimeStr(String endTimeStr) {
		this.endTimeStr = endTimeStr;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public void initTimeStr(){
		this.startTimeStr = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(startTime);
		this.endTimeStr = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(endTime);
	}
//	public void initActivity() throws Exception{
//		initTimeStr();
//		Object[] endTimeValue = { CommonUtil.dateFormat.format(endTime), value };
//		Object[] startEndTime = { CommonUtil.dateFormat.format(startTime), CommonUtil.dateFormat.format(endTime) };
//		Object[] startEndValue = { CommonUtil.dateFormat.format(startTime), CommonUtil.dateFormat.format(endTime), value };
//		Object[] startEndTargetNum = { CommonUtil.dateFormat.format(startTime), CommonUtil.dateFormat.format(endTime), targetNum };
//		Object[] startEndValueTargetNum = { CommonUtil.dateFormat.format(startTime), CommonUtil.dateFormat.format(endTime), value, targetNum };
//		switch (action) {
//		case 1:
//			name=CommonUtil.messageFormat(description, endTimeValue);
//			break;
//		case 2:
//			name=CommonUtil.messageFormat(description, startEndTime);
//			break;
//		case 3:
//			name=CommonUtil.messageFormat(description, startEndTime);
//			break;
//		case 4:
//			name=CommonUtil.messageFormat(description, startEndValue);
//			break;
//		case 5:
//			//name=CommonUtil.messageFormat(description, objects1);
////			name = ServiceLocator.getService.getSysAchievementById(this.value).getDescription();
//			break;
//		case 6:
//			name=CommonUtil.messageFormat(description, startEndTargetNum);
//			break;
//		case 7:
//			name=CommonUtil.messageFormat(description, startEndTargetNum);
//			break;
//		case 8:
//			name=CommonUtil.messageFormat(description, startEndValue);
//			break;
//		case 9:
//			name=CommonUtil.messageFormat(description, startEndTime);
//			break;
//		case 10:
//			name=CommonUtil.messageFormat(description, startEndTargetNum);
//			break;
//		case 11:
//			Object[] objs = new Object[]{CommonUtil.dateFormatOnlyDate.format(startTime), CommonUtil.dateFormatOnlyDate.format(endTime), value, targetNum};
//			name=CommonUtil.messageFormat(description, objs);
//			break;
//		case 12:
//			name=CommonUtil.messageFormat(description, startEndValueTargetNum);
//			break;
//		case 13:
//			name=CommonUtil.messageFormat(description, startEndTime);
//			break;
//		case 14:
//			name=CommonUtil.messageFormat(description, startEndTime);
//			break;
//		case 15:
//			name=CommonUtil.messageFormat(description, startEndValue);
//			break;
//		case 16:
//			 objs = new Object[]{CommonUtil.dateFormatOnlyDate.format(startTime), CommonUtil.dateFormatOnlyDate.format(endTime), value, targetNum};
//			name=CommonUtil.messageFormat(description, objs);
//			break;
//		case 17:
//			objs = new Object[]{CommonUtil.dateFormatOnlyDate.format(startTime), CommonUtil.dateFormatOnlyDate.format(endTime), value, targetNum};
//			name=CommonUtil.messageFormat(description, objs);
//			break;
//		case 18:
//			name=CommonUtil.messageFormat(description, startEndTargetNum);
//			break;
//		case 19:
//			name=CommonUtil.messageFormat(description, startEndTargetNum);
//			break;
//		default:
//			break;
//		}
//	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public int getTargetNum() {
		return targetNum;
	}
	public void setTargetNum(int targetNum) {
		this.targetNum = targetNum;
	}
	public int getAction() {
		return action;
	}
	public void setAction(int action) {
		this.action = action;
	}
	public String getTheSwitch() {
		return theSwitch;
	}
	public void setTheSwitch(String theSwitch) {
		this.theSwitch = theSwitch;
	}
	public String getItems() {
		return items;
	}
	public void setItems(String items) {
		this.items = items;
	}
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public SysItem getSysItem() {
		return sysItem;
	}
	public void setSysItem(SysItem sysItem) {
		this.sysItem = sysItem;
		this.itemsStr = sysItem.getDisplayName();
	}
	public String getActivityName() {
		return activityName;
	}
	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}
	public String getItemsStr() {
		return itemsStr;
	}
	public void setItemsStr(String itemsStr) {
		this.itemsStr = itemsStr;
	}
	public int getWithAward() {
		return withAward;
	}
	public void setWithAward(int withAward) {
		this.withAward = withAward;
	}
	public int getUnit() {
		return unit;
	}
	public void setUnit(int unit) {
		this.unit = unit;
	}
	public int getUnitType() {
		return unitType;
	}
	public void setUnitType(int unitType) {
		this.unitType = unitType;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
}
