package com.yp.user.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yp.common.service.user.UserService;
import com.yp.common.vo.user.LoginUser;
import com.yp.common.vo.user.UserVo;
import com.yp.user.service.datasupport.UserDataSupport;
import com.yp.user.service.support.UserSupportService;

/**
 * 用户服务
 * @author zhiya.chai
 * 2016年9月3日 下午6:33:44
 */
@Service("userService")
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDataSupport userDataSupport;
	@Autowired
	private UserSupportService userSupportService;

	/**
	 * 根据条件查询用户
	 */
	@Override
	public List<UserVo> getUserList(Map<String, Object> map) {
		return userDataSupport.getUserList(map);
	}

	@Override
	public UserVo login(LoginUser loginUser) {
		
		return null;
	}

	@Override
	public UserVo register(UserVo user) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
