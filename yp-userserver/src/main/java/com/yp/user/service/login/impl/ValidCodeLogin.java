package com.yp.user.service.login.impl;

import org.apache.commons.lang.StringUtils;

import com.yp.common.enums.ErrorCode;
import com.yp.common.enums.user.LoginType;
import com.yp.common.exception.ErrorCodeException;
import com.yp.common.vo.user.LoginUser;
import com.yp.common.vo.user.UserLoginVo;
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
		//验证验证码
		validCode(loginUser.getValidCode());
		//获取用户信息
		UserVo userVo = super.getUser(loginUser.getPhoneNo());
		//验证用户登录信息
		UserLoginVo userLoginVo = super.validLoginUser(userVo.getId(), loginUser.getPhoneNo(), LoginType.手机号验证码登录);
		//获取验证码
		String validCode = "";
		if(!validCode.equals(loginUser.getValidCode())) {
			loginFail("验证码不正确", userLoginVo);
			throw new ErrorCodeException(ErrorCode.USER_LOGIN_FAIL,"手机验证码不正确。");
		}
		//放入缓存
		createLoginCache(loginUser.getSessionId(),userVo);
		//登录成功
		loginSuccess(userLoginVo);
		return userVo;
	}
	
	private void validCode(String validCode) {
		if(StringUtils.isBlank(validCode)) {
			throw new ErrorCodeException(ErrorCode.SYS_EMPTY_PARAMETER,"手机验证码不能为空。");
		}
	}

	@Override
	public boolean isLoginType(LoginType loginType) {
		return loginType == LoginType.手机号验证码登录;
	}

}
