package com.yp.common.enums;

/**
 * 错误编码
 * @author zhiya.chai
 * 2016年9月3日 下午9:39:55
 */
public enum ErrorCode {

	SUCCESS(0,"成功"),
	//用户
	USER_REGISTER_FAIL(101001,"用户注册失败。"),
	LOGINNAME_ALREADY_EXIST(101002,"登录名已存在。"),
	PHONENO_ALREADY_EXIST(101003,"登录名已存在。"),
	USER_NOT_EXIST(101004,"用户不存在。"),
	USER_LOGIN_FAIL(101005,"用户名或密码不正确"),
	
	SYS_EMPTY_PARAMETER(999001,"系统参数为空");
	
	private int code;
	private String message;
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	private ErrorCode(int code, String message) {
		this.code = code;
		this.message = message;
	}
}
