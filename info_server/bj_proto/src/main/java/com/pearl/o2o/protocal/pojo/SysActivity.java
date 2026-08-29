/**
 * 
 */
package com.pearl.o2o.protocal.pojo;

/**
 * @author lifengyang
 *
 */
public class SysActivity {
	private int id;
	private int StartTime;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getStartTime() {
		return StartTime;
	}
	public void setStartTime(int startTime) {
		StartTime = startTime;
	}
	@Override
	public String toString() {
		return "SysActivity [id=" + id + ", StartTime=" + StartTime + "]";
	}
	
}
