/**
 * 
 */
package com.pde.uweb.ma.dto;

import com.pde.infor.common.utils.ValidateParaAnnotation;

/**
 * 登录参数
 */
public class LoginRequestDto {

	/** 登录名 */
	@ValidateParaAnnotation(errorKey = "uNameError", errorMsg = "用户名必填", isNull = false)
	private String userName;

	/** 密码 */
	@ValidateParaAnnotation(errorKey = "pwdError", errorMsg = "密码必填", isNull = false)
	private String password;

	/** 验证码 */
	private String captcha;

	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCaptcha() {
		return captcha;
	}
	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}

}
