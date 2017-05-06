package com.yp.user.service.datasupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yp.common.db.user.UserLoginDB;
import com.yp.common.vo.user.UserLoginVo;
import com.yp.user.dao.UserLoginDao;

@Service
public class UserLoginDataSupport {

	@Autowired
	private UserLoginDao userloginDao;
	
	public UserLoginVo getUserLoginByUserId(Integer userId) {
		return userloginDao.getByUserId(userId);
	}
	
	public UserLoginVo insertUserLogin(UserLoginDB userLogin) {
		int id = userloginDao.update(userLogin);
		if( id > 0 ) {
			//return userLoginD
		}
		return null;
	}
	
}
