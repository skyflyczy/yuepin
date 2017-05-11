package com.yp.user.service.login;

import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.yp.common.consts.MemcacheConstants;
import com.yp.common.enums.ErrorCode;
import com.yp.common.enums.user.LoginType;
import com.yp.common.exception.ErrorCodeException;
import com.yp.common.memcache.client.MemcachedCache;
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
	@Autowired
	protected MemcachedCache memcachedCache;

	/**
	 * 用户登录
	 * @param loginUser
	 * @return UserVo 
	 * @author zhiya.chai
	 */
	public abstract UserVo loginUser(LoginUser loginUser);
	
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
	/**
	 * 验证登录
	 * @param userId
	 * @param loginName
	 * @param loginType
	 * @author zhiya.chai
	 * @date 2017年5月11日 下午2:14:54
	 */
	protected UserLoginVo validLoginUser(Integer userId,String loginName,LoginType loginType) {
		UserLoginVo userLoginVo = userLoginSupportService.getLoginUser(userId, loginName, loginType);
		int lxLoginFailNum = 5;//连续失败次数
		int loginFrozenMinute = 10;//登陆失败冻结分钟
		if(userLoginVo.getFailNumLx() > lxLoginFailNum  
				&& userLoginVo.getLastFailTime().compareTo(DateUtils.addMinutes(new Date(), -loginFrozenMinute)) > 0) {
			throw new ErrorCodeException(ErrorCode.USER_NOT_EXIST,"账号已冻结，请稍后再试。"); 
		}
		return userLoginVo;
	}
	
	/**
	 * 登录失败
	 * @param retMsg
	 * @param userLoginVo
	 * @author zhiya.chai
	 * @date 2017年5月11日 下午2:49:55
	 */
	protected void loginFail(String retMsg,UserLoginVo userLoginVo){
		userLoginVo.setFailNumLx(userLoginVo.getFailNumLx()+1);
		userLoginVo.setFailNum(userLoginVo.getFailNum()+1);
		userLoginVo.setSuccessNumLx(0);
		userLoginVo.setRetMsg(retMsg);
		userLoginVo.setLastFailTime(new Date());
		userLoginSupportService.updateLoginUser(userLoginVo);
	}
	/**
	 * 登录成功
	 * @param userLoginVo
	 * @author zhiya.chai
	 * @date 2017年5月11日 下午2:52:59
	 */
	protected void loginSuccess(UserLoginVo userLoginVo) {
		userLoginVo.setFailNumLx(0);
		userLoginVo.setSuccessNumLx(userLoginVo.getSuccessNumLx()+1);
		userLoginVo.setSuccessNum(userLoginVo.getSuccessNum()+1);
		userLoginVo.setLastSuccessTime(new Date());
		userLoginVo.setRetMsg(null);
		userLoginSupportService.updateLoginUser(userLoginVo);
	}
	/**
	 * 存放登录缓存
	 * @param sessionId
	 * @param user
	 * @author zhiya.chai
	 * @date 2017年5月11日 下午3:49:28
	 */
	protected void createLoginCache(String sessionId,UserVo user) {
		this.memcachedCache.set(MemcacheConstants.LOGIN_USER_MEMCACHE + sessionId + "_" + user.getId(), user);
	}
}
