package com.pearl.o2o.pojo;

import java.util.Date;

public class GmUpdateLog {
	private int id;
	private GmUser gm;
	private Object older;
	private Object newer;
	private Date time;
	
	
	public GmUpdateLog() {
		super();
	}
	public GmUpdateLog(GmUser gm, Object older, Object newer,
			Date time) {
		super();
		this.gm = gm;
		this.older = older;
		this.newer = newer;
		this.time = time;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public GmUser getGm() {
		return gm;
	}
	public void setGm(GmUser gm) {
		this.gm = gm;
	}
	public Object getOlder() {
		return older;
	}
	public void setOlder(Object older) {
		this.older = older;
	}
	public Object getNewer() {
		return newer;
	}
	public void setNewer(Object newer) {
		this.newer = newer;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	
	
	
}
