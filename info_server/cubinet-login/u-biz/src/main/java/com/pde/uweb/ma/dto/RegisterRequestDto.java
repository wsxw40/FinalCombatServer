package com.pde.uweb.ma.dto;

import com.pde.infor.common.utils.ValidateParaAnnotation;
import com.pde.infor.common.utils.ValidateReg;

/** 注册请求参数对象 */
public class RegisterRequestDto {
	
	@ValidateParaAnnotation(errorKey = "uNameError", errorMsg = "用户名必填", isNull = false)
	private String userName;
	
	@ValidateParaAnnotation(errorKey = "pwdError", errorMsg = "密码必填", isNull = false)
	private String password;
	
	@ValidateParaAnnotation(errorKey = "repwdError", errorMsg = "确认密码必填", isNull = false)
	private String repassword;

	@ValidateParaAnnotation(errorKey = "idNameError", errorMsg = "请填写正确的姓名", isNull = false)
	private String idName;

	@ValidateParaAnnotation(errorKey = "idNoError", errorMsg = "请填写正确的身份证号", isNull = false, regex = ValidateReg.ID_CARD_REGEX)
	private String idNumber;
	
	private String em;
	
	private String mobile;
	
	
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
	public String getIdName() {
		return idName;
	}
	public void setIdName(String idName) {
		this.idName = idName;
	}	
	public String getIdNumber() {
		return idNumber;
	}
	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}
	public String getEm() {
		return em;
	}
	public void setEm(String em) {
		this.em = em;
	}
	public String getRepassword() {
		return repassword;
	}
	public void setRepassword(String repassword) {
		this.repassword = repassword;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

}
