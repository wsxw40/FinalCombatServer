package com.pde.uweb.ma.dto;

import com.pde.infor.common.utils.ValidateParaAnnotation;

/** 快速注册请求参数对象 */
public class QuickRegisterRequestDto {
	
	@ValidateParaAnnotation(errorKey = "uNameError", errorMsg = "用户名必填", isNull = false)
	private String userName;
	
	@ValidateParaAnnotation(errorKey = "pwdError", errorMsg = "密码必填", isNull = false)
	private String password;
	
	
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

}
