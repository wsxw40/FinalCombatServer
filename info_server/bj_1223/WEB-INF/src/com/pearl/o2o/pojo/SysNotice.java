package com.pearl.o2o.pojo;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class SysNotice extends BaseMappingBean<SysNotice> {
	private static final long serialVersionUID = -3969298110541354027L;
	private Integer type;
	private String content;
	private Integer startTime;
	private Integer endTime;
	private Integer noticeTime;
	
	private String startTimeStr;
	private String endTimeStr;

	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getNoticeTime() {
		return noticeTime;
	}
	public void setNoticeTime(Integer noticeTime) {
		this.noticeTime = noticeTime;
	}
	public Integer getStartTime() {
		return startTime;
	}
	public void setStartTime(Integer startTime) {
		this.startTime = startTime;
		long startLong = startTime;
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis((long)(1000L*startLong));
		this.startTimeStr = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(c.getTime());
	}
	public Integer getEndTime() {
		return endTime;
	}
	public void setEndTime(Integer endTime) {
		this.endTime = endTime;
		long endLong = endTime;
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis((long)(1000L*endLong));
		this.endTimeStr = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(c.getTime());
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

}
