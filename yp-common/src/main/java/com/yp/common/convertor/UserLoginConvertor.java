package com.yp.common.convertor;

import com.yp.common.db.user.UserLoginDB;
import com.yp.common.vo.user.UserLoginVo;

public class UserLoginConvertor extends BaseConvertor{
	
	private final static String [] UserLoign_IgnoreProperties = new String [] {};
	
	/**
	 * userLoginDB -- > UserLoginVo
	 * @param userLoginDB
	 * @return
	 * @author zhiya.chai
	 */
	public static UserLoginVo convert(UserLoginDB userLoginDB) {
		UserLoginVo userLoginVo = null;
		if(userLoginDB != null) {
			userLoginVo = new UserLoginVo();
			copyProperties(userLoginDB, userLoginVo, UserLoign_IgnoreProperties);
		}
		return userLoginVo;
	}
	
	/**
	 * userLoginVo -- > UserLoginDB
	 * @param userLoginDB
	 * @return
	 * @author zhiya.chai
	 */
	public static UserLoginDB convert(UserLoginVo userLoginVo) {
		UserLoginDB userLoginDB = null;
		if(userLoginVo != null) {
			userLoginDB = new UserLoginDB();
			copyProperties(userLoginVo, userLoginDB, UserLoign_IgnoreProperties);
		}
		return userLoginDB;
	}
}
