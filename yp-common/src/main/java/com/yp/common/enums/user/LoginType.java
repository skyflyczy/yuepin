package com.yp.common.enums.user;

public enum LoginType {

	手机号验证码登录(1),手机号密码登录(2),微信(3),QQ(4),微博(5);
	
	private final int type;
	
	private LoginType(int type) {
		this.type = type;
	}
	
	public int getType(){
		return this.type;
	}
}
