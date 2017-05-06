package com.yp.user.service.support;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yp.common.db.user.UserDB;
import com.yp.common.db.user.UserLoginDB;
import com.yp.common.enums.ErrorCode;
import com.yp.common.enums.user.LoginType;
import com.yp.common.vo.user.UserLoginVo;
import com.yp.common.vo.user.UserVo;
import com.yp.user.service.datasupport.UserLoginDataSupport;

@Service
public class UserLoginSupportService {

	@Autowired
	private UserLoginDataSupport userLoginDataSupport;
	
	
	public void getLoginUser(UserVo user,String loginName,LoginType loginType) {
		UserLoginVo userLogin = userLoginDataSupport.getUserLoginByUserId(user.getId());
		if(userLogin == null) {
			insertUserLogin(user.getId(), loginName, loginType);
		}
		
	}
	
	private UserLoginDB insertUserLogin(Integer userId,String loginName,LoginType loginType) {
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
		return userLogin;
	}
}
