package com.pde.uweb.ma.dto;

import com.pde.infor.common.utils.ValidateParaAnnotation;

/** 绑定手机请求对象 */
public class BindEmailRequestDto {

	@ValidateParaAnnotation(errorKey = "error", errorMsg = "userId必传", isNull = false)
	private long userId;
	
	@ValidateParaAnnotation(errorKey = "emailError", errorMsg = "邮箱必填", isNull = false)
	private String email;

	
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

}
