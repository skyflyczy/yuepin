package com.yp.user.service.support;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yp.common.db.user.UserLoginDB;
import com.yp.common.enums.ErrorCode;
import com.yp.common.enums.user.LoginType;
import com.yp.common.vo.user.UserLoginVo;
import com.yp.user.service.datasupport.UserLoginDataSupport;

@Service
public class UserLoginSupportService {

	@Autowired
	private UserLoginDataSupport userLoginDataSupport;
	
	/**
	 * 获取用户登录记录
	 * @param userId
	 * @param loginName
	 * @param loginType
	 * @return
	 * @author zhiya.chai
	 */
	public UserLoginVo getLoginUser(Integer userId,String loginName,LoginType loginType) {
		UserLoginVo userLogin = userLoginDataSupport.getUserLoginByUserId(userId);
		if(userLogin == null) {
			userLogin = insertUserLogin(userId, loginName, loginType);
		}
		return userLogin;
	}
	/**
	 * 更新登录用户信息
	 * @param userLogin
	 * @author zhiya.chai
	 */
	public void updateLoginUser(UserLoginVo userLogin) {
		userLoginDataSupport.updateUserLogin(userLogin);
	}
	/**
	 * 插入登录用户信息
	 * @param userId
	 * @param loginName
	 * @param loginType
	 * @return
	 * @author zhiya.chai
	 */
	private UserLoginVo insertUserLogin(Integer userId,String loginName,LoginType loginType) {
		UserLoginDB userLogin = new UserLoginDB();
		userLogin.setFailNum(0);
		userLogin.setFailNumLx(0);
		userLogin.setLastFailTime(null);
		userLogin.setLastSuccessTime(new Date());
		userLogin.setLoginName(loginName);
		userLogin.setLoginType(loginType.getType());
		userLogin.setPreLoginTime(new Date());
		userLogin.setRetCode(ErrorCode.SUCCESS.getCode());
		userLogin.setSuccessNum(1);
		userLogin.setSuccessNumLx(1);
		userLogin.setUserId(userId);
		userLogin.setUpdateTime(new Date());
		return userLoginDataSupport.insertUserLogin(userLogin);
	}
}
