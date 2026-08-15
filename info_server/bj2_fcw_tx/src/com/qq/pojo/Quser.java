package com.qq.pojo;

import java.util.ArrayList;
import java.util.Date;

public class Quser {
	private Integer id;
	private String openid;// 不会
	private String openkey;// 会变
	private String pfkey;// 会变
	private ArrayList<Token> tokens = null;

	public ArrayList<Token> getTokens() {
		if (this.tokens == null)
			this.tokens = new ArrayList<Token>();
		return this.tokens;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getOpenkey() {
		return openkey;
	}

	public void setOpenkey(String openkey) {
		this.openkey = openkey;
	}

	public String getPfkey() {
		return pfkey;
	}

	public void setPfkey(String pfkey) {
		this.pfkey = pfkey;
	}
	@Override
	public String toString() {
		return id+"|"+openid+"|"+openkey+"|"+pfkey;
	}

}
