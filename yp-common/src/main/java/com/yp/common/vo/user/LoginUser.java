package com.yp.common.vo.user;

import java.io.Serializable;

import com.yp.common.enums.user.LoginType;

public class LoginUser implements Serializable{

	private static final long serialVersionUID = -8213544835127765141L;
	
	private String phoneNo;
	private String password;//密码
	private String validCode;//手机验证码
	private LoginType loginType;
	
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getValidCode() {
		return validCode;
	}
	public void setValidCode(String validCode) {
		this.validCode = validCode;
	}
	public LoginType getLoginType() {
		return loginType;
	}
	public void setLoginType(LoginType loginType) {
		this.loginType = loginType;
	}
}
