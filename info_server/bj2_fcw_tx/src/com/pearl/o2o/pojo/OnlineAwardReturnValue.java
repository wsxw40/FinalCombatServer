package com.pearl.o2o.pojo;

import java.io.Serializable;
import java.util.List;

public class OnlineAwardReturnValue implements Serializable {
	private static final long serialVersionUID = 8662488518269965849L;
	
	private int left_seconds;
	private String next_time;
	private List<OnlineAward> gifts;
	private String errormsg;
	private int cExp;				//当前在线经验奖励
	public int getLeftSeconds() {
		return left_seconds;
	}
	public void setLeftSeconds(int left_seconds) {
		this.left_seconds = left_seconds;
	}
	public String getNextTime() {
		return next_time;
	}
	public void setNextTime(String next_time) {
		this.next_time = next_time;
	}
	public List<OnlineAward> getGifts() {
		return gifts;
	}
	public void setGifts(List<OnlineAward> gifts) {
		this.gifts = gifts;
	}
	public String getErrormsg() {
		return errormsg;
	}
	public void setErrormsg(String errormsg) {
		this.errormsg = errormsg;
	}
	public int getCExp() {
		return cExp;
	}
	public void setCExp(int exp) {
		cExp = exp;
	}
}
