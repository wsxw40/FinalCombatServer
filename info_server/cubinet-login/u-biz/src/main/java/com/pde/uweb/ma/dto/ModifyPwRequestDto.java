package com.pde.uweb.ma.dto;

import com.pde.infor.common.utils.ValidateParaAnnotation;

/** 修改密码请求对象 */
public class ModifyPwRequestDto {

	@ValidateParaAnnotation(errorKey = "error", errorMsg = "userId必传", isNull = false)
	private long userId;
	
	@ValidateParaAnnotation(errorKey = "oldPwError", errorMsg = "原始密码必填", isNull = false)
	private String oldPw;
	
	@ValidateParaAnnotation(errorKey = "newPwError", errorMsg = "新密码必填", isNull = false)
	private String newPw;
	
	@ValidateParaAnnotation(errorKey = "reNewPwError", errorMsg = "确认新密码必填", isNull = false)
	private String reNewPw;

	
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getOldPw() {
		return oldPw;
	}
	public void setOldPw(String oldPw) {
		this.oldPw = oldPw;
	}
	public String getNewPw() {
		return newPw;
	}
	public void setNewPw(String newPw) {
		this.newPw = newPw;
	}
	public String getReNewPw() {
		return reNewPw;
	}
	public void setReNewPw(String reNewPw) {
		this.reNewPw = reNewPw;
	}
	
}
