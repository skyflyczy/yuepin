package com.yp.user.service.datasupport;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yp.common.convertor.UserLoginConvertor;
import com.yp.common.db.user.UserLoginDB;
import com.yp.common.enums.ErrorCode;
import com.yp.common.exception.ErrorCodeException;
import com.yp.common.vo.user.UserLoginVo;
import com.yp.user.dao.UserLoginDao;

@Service
public class UserLoginDataSupport {

	@Autowired
	private UserLoginDao userloginDao;
	
	public UserLoginVo getUserLoginByUserId(Integer userId) {
		return userloginDao.getByUserId(userId);
	}
	/**
	 * 插入新用户记录
	 * @param userLogin
	 * @return
	 */
	public UserLoginVo insertUserLogin(UserLoginDB userLogin) {
		int id = userloginDao.insert(userLogin);
		if( id > 0 ) {
			return UserLoginConvertor.convert(userLogin);
		}
		return null;
	}
	
	/**
	 * 更新用户登录信息
	 * @param userLogin
	 */
	public void updateUserLogin(UserLoginVo userLogin) {
		userLogin.setUpdateTime(new Date());
		UserLoginDB userLoginDB = UserLoginConvertor.convert(userLogin);
		int n = userloginDao.update(userLoginDB);
		if(n <= 0) {
			throw new ErrorCodeException(ErrorCode.USER_LOGIN_FAIL,"用户登录信息更新失败。");
		}
	}
	
}
