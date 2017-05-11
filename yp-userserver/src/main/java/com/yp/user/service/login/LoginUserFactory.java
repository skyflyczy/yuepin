package com.yp.user.service.login;

import com.yp.common.enums.user.LoginType;
import com.yp.user.context.UserContext;
import com.yp.user.service.login.impl.PasswordLogin;
import com.yp.user.service.login.impl.ValidCodeLogin;


public class LoginUserFactory {

	private static final LoginUserFactory factory = new LoginUserFactory();
	
	private LoginUserFactory(){};
	public static final LoginUserFactory getInstance(){
		return factory;
	}
	/**
	 * 获取登录对象
	 * @param loginType
	 * @return
	 * @author zhiya.chai
	 * @date 2017年5月11日 下午4:10:25
	 */
	public LoginService getMessageMqService(LoginType loginType){
		if(loginType == LoginType.手机号密码登录) {
			return UserContext.getInstance().getSpringContext().getBean(PasswordLogin.class);
		}else if(loginType == LoginType.手机号验证码登录) {
			return UserContext.getInstance().getSpringContext().getBean(ValidCodeLogin.class);
		}
		return null;
	}
}
