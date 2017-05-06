package com.yp.user.service.login.impl;

import com.yp.common.enums.user.LoginType;
import com.yp.common.vo.user.LoginUser;
import com.yp.common.vo.user.UserVo;
import com.yp.user.service.login.LoginService;

/**
 * 验证码登录
 * @author zhiya.chai
 *
 */
public class ValidCodeLogin extends LoginService {

	@Override
	public UserVo loginUser(LoginUser loginUser) {
		return null;
	}

	@Override
	public boolean isLoginType(LoginType loginType) {
		return loginType == LoginType.手机号验证码;
	}

}
