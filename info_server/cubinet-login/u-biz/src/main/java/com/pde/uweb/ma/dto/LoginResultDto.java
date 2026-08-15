/**
 * 
 */
package com.pde.uweb.ma.dto;

import com.pde.uweb.database.ma.user.UserPojo;

/**
 *  登录结果
 */
public class LoginResultDto {

	/** 会员对象 */
	private UserPojo user;

	/** 登录成功与否 */
	private boolean isLoginSuccess;

	/** 登录失败原因 */
	private String loginError;

	
	public UserPojo getUser() {
		return user;
	}
	public void setUser(UserPojo user) {
		this.user = user;
	}
	public boolean isLoginSuccess() {
		return isLoginSuccess;
	}
	public void setLoginSuccess(boolean isLoginSuccess) {
		this.isLoginSuccess = isLoginSuccess;
	}
	public String getLoginError() {
		return loginError;
	}
	public void setLoginError(String loginError) {
		this.loginError = loginError;
	}

}
