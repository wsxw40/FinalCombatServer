package com.pde.uweb.ma.dto;

import com.pde.uweb.database.ma.user.UserPojo;

public class RegisterResultDto {

	/** 注册成功与否 */
	private boolean isRegisterSuccess;

	/** 注册失败原因 */
	private String registerError;
	
	/** 会员对象 */
	private UserPojo user;

	
	public boolean isRegisterSuccess() {
		return isRegisterSuccess;
	}
	public void setRegisterSuccess(boolean isRegisterSuccess) {
		this.isRegisterSuccess = isRegisterSuccess;
	}
	public String getRegisterError() {
		return registerError;
	}
	public void setRegisterError(String registerError) {
		this.registerError = registerError;
	}
	public UserPojo getUser() {
		return user;
	}
	public void setUser(UserPojo user) {
		this.user = user;
	}

}
