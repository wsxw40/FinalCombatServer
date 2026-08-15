package com.qq.pojo;

import java.util.Date;

public class Token {
	private String id;//token ID
	private String time;//linux时间戳
	private String payitem;//"50005*4*1"
	
	public Token() {
		super();
	}
	public Token(String id, String time, String payitem) {
		super();
		this.id = id;
		this.time = time;
		this.payitem = payitem;
	}
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getPayitem() {
		return payitem;
	}

	public void setPayitem(String payitem) {
		this.payitem = payitem;
	}
	
}