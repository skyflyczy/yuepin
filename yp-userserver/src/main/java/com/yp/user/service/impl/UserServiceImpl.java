package com.yp.user.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yp.common.enums.ErrorCode;
import com.yp.common.exception.ErrorCodeException;
import com.yp.common.service.user.UserService;
import com.yp.common.vo.user.LoginUser;
import com.yp.common.vo.user.UserVo;
import com.yp.user.service.datasupport.UserDataSupport;
import com.yp.user.service.login.LoginService;
import com.yp.user.service.login.LoginUserFactory;

/**
 * 用户服务
 * @author zhiya.chai
 * 2016年9月3日 下午6:33:44
 */
@Service("userService")
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDataSupport userDataSupport;

	/**
	 * 根据条件查询用户
	 */
	@Override
	public List<UserVo> getUserList(Map<String, Object> map) {
		return userDataSupport.getUserList(map);
	}

	@Override
	public UserVo login(LoginUser loginUser) {
		LoginService loginService = LoginUserFactory.getInstance().getMessageMqService(loginUser.getLoginType());
		if(loginService == null) {
			throw new ErrorCodeException(ErrorCode.USER_LOGIN_FAIL,"请换其他方式登录。");
		}
		return loginService.loginUser(loginUser);
	}

	@Override
	public UserVo register(UserVo user) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
