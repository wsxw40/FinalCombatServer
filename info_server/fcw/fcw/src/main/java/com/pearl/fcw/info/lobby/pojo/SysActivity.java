package com.pearl.fcw.info.lobby.pojo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.pearl.fcw.info.core.persistence.config.annotation.Schema;
import com.pearl.fcw.info.lobby.utils.CommonUtil;

@Entity
@Schema(GoSchema.SYS)
public class SysActivity extends BasePojo {
	private static final long serialVersionUID = -268751617451958697L;
	
	@Id
	private int id;
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
	@Transient
	private SysItem sysItem;
	@Transient
	private List<SysItem> itemList;
	private String activityName;
	@Transient
	private String itemsStr;
	private String description;
	private int withAward;
	private int unit;
	private int unitType;
	private String backup; // 目前用来区分 该活动是否每日结算

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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

	public void initNeedAward() {
		if (action == 1 || action == 2 || action == 3 || action == 4
				|| action == 9 || action == 11 || action == 12 || action == 13
				|| action == 14 || action == 15 || action == 16 || action == 17
				|| action == 22 || action == 23 || action == 25 || action == 26) {
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

	public void initTimeStr() {
		this.startTimeStr = new SimpleDateFormat("yyyy-MM-dd HH:mm")
				.format(startTime);
		this.endTimeStr = new SimpleDateFormat("yyyy-MM-dd HH:mm")
				.format(endTime);
	}

	private String messageFormatI18N(String regx, String pref, int num,
			Object... args) {// code style |
		regx = pref + regx + "^" + num + "^";
		for (Object obj : args) {
			regx += "\\\"" + obj + "\\\"^";
		}
		return regx + ">";
	}

	public void initActivity() throws Exception {
		initTimeStr();
		Object[] endTimeValue = { CommonUtil.dateFormat.format(endTime), value };
		Object[] startEndTime = { CommonUtil.dateFormat.format(startTime),
				CommonUtil.dateFormat.format(endTime) };
		Object[] startEndValue = { CommonUtil.dateFormat.format(startTime),
				CommonUtil.dateFormat.format(endTime), value };
		Object[] startEndTargetNum = { CommonUtil.dateFormat.format(startTime),
				CommonUtil.dateFormat.format(endTime), targetNum };
		Object[] startEndValueTargetNum = {
				CommonUtil.dateFormat.format(startTime),
				CommonUtil.dateFormat.format(endTime), value, targetNum };

		switch (action) {
		case 1:
			name = messageFormatI18N("" + this.action, "<AC", 2, endTimeValue);
			break;
		case 2:
			name = messageFormatI18N("" + this.action, "<AC", 2, startEndTime);
			break;
		case 3:
			name = messageFormatI18N("" + this.action, "<AC", 2, startEndTime);
			break;
		case 4:
			name = messageFormatI18N("" + this.action, "<AC", 3, startEndValue);
			break;
		case 5:
			// name=CommonUtil.messageFormat(this.idcription, objects1);
			name = "" + this.value;
			break;
		// 2,2,2,3,2,3,3,3, ,3,4,4,2,2,3,4,4,3,3,3,2,2,2,3,4,
		case 6:
			name = messageFormatI18N("" + this.action, "<AC", 3,
					startEndTargetNum);
			break;
		case 7:
			name = messageFormatI18N("" + this.action, "<AC", 3,
					startEndTargetNum);
			break;
		case 8:
			name = messageFormatI18N("" + this.action, "<AC", 3, startEndValue);
			break;
		case 9:
			name = messageFormatI18N("" + this.action, "<AC", 2, startEndTime);
			break;
		case 10:
			name = messageFormatI18N("" + this.action, "<AC", 3,
					startEndTargetNum);
			break;
		case 11:
			if (value == 0 && targetNum == 24) {
				name = messageFormatI18N("" + this.action * 10, "<AC", 2,
						startEndTime);
			} else {
				Object[] objs = new Object[] {
						CommonUtil.dateFormatOnlyDate.format(startTime),
						CommonUtil.dateFormatOnlyDate.format(endTime), value,
						targetNum };
				name = messageFormatI18N("" + this.action, "<AC", 4, objs);
			}

			break;
		case 12:
			name = messageFormatI18N("" + this.action, "<AC", 4,
					startEndValueTargetNum);
			break;
		case 13:
			name = messageFormatI18N("" + this.action, "<AC", 2, startEndTime);
			break;
		case 14:
			name = messageFormatI18N("" + this.action, "<AC", 2, startEndTime);
			break;
		case 15:
			name = messageFormatI18N("" + this.action, "<AC", 3, startEndValue);
			break;
		case 16:
			if (value == 0 && targetNum == 24) {
				name = messageFormatI18N("" + this.action * 10, "<AC", 2,
						startEndTime);
			} else {
				Object[] objs = new Object[] {
						CommonUtil.dateFormatOnlyDate.format(startTime),
						CommonUtil.dateFormatOnlyDate.format(endTime), value,
						targetNum };
				name = messageFormatI18N("" + this.action, "<AC", 4, objs);
			}
			break;
		case 17:
			if (value == 0 && targetNum == 24) {
				name = messageFormatI18N("" + this.action * 10, "<AC", 2,
						startEndTime);
			} else {
				Object[] objs = new Object[] {
						CommonUtil.dateFormatOnlyDate.format(startTime),
						CommonUtil.dateFormatOnlyDate.format(endTime), value,
						targetNum };
				name = messageFormatI18N("" + this.action, "<AC", 4, objs);
			}
			break;
		case 18:
			// （配置开始时间-配置结束时间）每天第1次登陆游戏 至 当日23:59，充值“配置钱数”可获得以下奖励！
			if (this.getBackup().equals("Y")) {
				name = messageFormatI18N("" + this.action * 10, "<AC", 3,
						startEndTargetNum);
			} else {
				name = messageFormatI18N("" + this.action, "<AC", 3,
						startEndTargetNum);
			}

			break;
		case 19:
			name = messageFormatI18N("" + this.action, "<AC", 3,
					startEndTargetNum);
			break;
		case 20:
			name = messageFormatI18N("" + this.action, "<AC", 3,
					startEndTargetNum);
			break;
		case 21:
			name = messageFormatI18N("" + this.action, "<AC", 3,
					startEndTargetNum);
			break;
		case 22:
			name = messageFormatI18N("" + this.action, "<AC", 2, startEndTime);
			break;
		case 23:
			name = messageFormatI18N("" + this.action, "<AC", 3, startEndTime);
			break;
		case 24:
			name = messageFormatI18N("" + this.action, "<AC", 4,
					startEndTargetNum);
			break;
		case 25:
			if (value == 0 && targetNum == 24) {
				name = messageFormatI18N("" + this.action * 10, "<AC", 2,
						startEndTime);
			} else {
				Object[] objs = new Object[] {
						CommonUtil.dateFormatOnlyDate.format(startTime),
						CommonUtil.dateFormatOnlyDate.format(endTime), value,
						targetNum };
				name = messageFormatI18N("" + this.action, "<AC", 4, objs);
			}
			break;
		case 26:
			name = messageFormatI18N("" + this.action, "<AC", 3,
					startEndTargetNum);
			break;
		case 27: // 28 需要占用，任务内 每日与非每日需要有两段文字
			Object[] objs = new Object[] {
					CommonUtil.dateFormatOnlyDate.format(startTime),
					CommonUtil.dateFormatOnlyDate.format(endTime), value,
					"<SN" + this.items + "^0>" };
			name = messageFormatI18N(""
					+ (this.action + (this.backup != null
							&& this.backup.toLowerCase().equals("y") ? 1 : 0)),
					"<AC", 4, objs);
			break;

		default:
			break;
		}
	}

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

	public String getBackup() {
		return backup;
	}

	public void setBackup(String backup) {
		this.backup = backup;
	}

}
