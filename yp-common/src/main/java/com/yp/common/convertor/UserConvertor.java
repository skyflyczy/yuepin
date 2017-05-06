package com.yp.common.convertor;

import com.yp.common.db.user.UserDB;
import com.yp.common.vo.user.UserVo;
/**
 * 用户对象转换
 * @author zhiya.chai
 */
public class UserConvertor extends BaseConvertor{

	private final static String [] User_IgnoreProperties = new String [] {};
	
	/**
	 * UserDB -- > UserVo
	 * @param userDB
	 * @return
	 * @author zhiya.chai
	 */
	public final static UserVo Convert(UserDB userDB) {
		UserVo userVo = null;
		if(userDB != null) {
			userVo = new UserVo();
			copyProperties(userDB, userVo, User_IgnoreProperties);
		}
		return userVo;
	}
	
	/**
	 * UserVo -- > UserDB
	 * @param userDB
	 * @return
	 * @author zhiya.chai
	 */
	public final static UserDB Convert(UserVo userVo) {
		UserDB userDB = null;
		if(userVo != null) {
			userDB = new UserDB();
			copyProperties(userVo, userDB, User_IgnoreProperties);
		}
		return userDB;
	}
}
