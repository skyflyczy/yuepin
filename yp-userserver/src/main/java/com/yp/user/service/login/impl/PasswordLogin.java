package com.yp.user.service.login.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.yp.common.encrypt.IrreversibEncrypt;
import com.yp.common.enums.ErrorCode;
import com.yp.common.enums.user.LoginType;
import com.yp.common.exception.ErrorCodeException;
import com.yp.common.vo.user.LoginUser;
import com.yp.common.vo.user.UserLoginVo;
import com.yp.common.vo.user.UserVo;
import com.yp.user.service.login.LoginService;
import com.yp.user.service.support.UserSupportService;

/**
 * 密码登录
 * @author zhiya.chai
 *
 */
@Service
public class PasswordLogin extends LoginService {
	@Autowired
	private UserSupportService userSupportService;

	@Override
	public UserVo loginUser(LoginUser loginUser) {
		//验证密码
		validPassword(loginUser.getPassword());
		//获取用户信息
		UserVo userVo = super.getUser(loginUser.getPhoneNo());
		//验证用户登录信息
		UserLoginVo userLoginVo = super.validLoginUser(userVo.getId(), loginUser.getPhoneNo(), LoginType.手机号密码登录);
		//获取登录密码
		String loginPassword = IrreversibEncrypt.MD5Encrypt(userVo.getSalt() + loginUser.getPassword());
		if(!loginPassword.equals(userVo.getPassword())) {
			loginFail("密码不正确", userLoginVo);
			throw new ErrorCodeException(ErrorCode.USER_LOGIN_FAIL,"手机号或者密码不正确。");
		}
		//放入缓存
		createLoginCache(loginUser.getSessionId(),userVo);
		//登录成功
		loginSuccess(userLoginVo);
		return userVo;
	}
	
	private void validPassword(String password) {
		if(StringUtils.isBlank(password)) {
			throw new ErrorCodeException(ErrorCode.SYS_EMPTY_PARAMETER,"密码不能为空。");
		}
	}

	@Override
	public boolean isLoginType(LoginType loginType) {
		return loginType == LoginType.手机号密码登录;
	}

}
