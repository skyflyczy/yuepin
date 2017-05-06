package com.yp.user.service.login;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.yp.common.enums.ErrorCode;
import com.yp.common.enums.user.LoginType;
import com.yp.common.exception.ErrorCodeException;
import com.yp.common.vo.user.LoginUser;
import com.yp.common.vo.user.UserLoginVo;
import com.yp.common.vo.user.UserVo;
import com.yp.user.service.support.UserLoginSupportService;
import com.yp.user.service.support.UserSupportService;

@Service
public abstract class LoginService {
	@Autowired
	protected UserSupportService userSupportService;
	@Autowired
	protected UserLoginSupportService userLoginSupportService;

	/**
	 * 用户登录
	 * @param loginUser
	 * @return UserVo 
	 * @author zhiya.chai
	 */
	protected abstract UserVo loginUser(LoginUser loginUser);
	
	/**
	 * 是否登录类型
	 * @param loginType
	 * @return
	 * @author zhiya.chai
	 */
	protected abstract boolean isLoginType(LoginType loginType);
	
	protected UserVo getUser(String phoneNo) {
		UserVo userVo = userSupportService.getUserByPhoneNo(phoneNo);
		if(userVo == null) {
			throw new ErrorCodeException(ErrorCode.USER_NOT_EXIST,"用户不存在。");
		}
		return userVo;
	}
	
	protected void validLoginUser(Integer userId,String loginName,LoginType loginType) {
		UserLoginVo userLoginVo = userLoginSupportService.getLoginUser(userId, loginName, loginType);
	}
}
