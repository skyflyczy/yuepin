package com.yp.common.service.user;

import java.util.List;
import java.util.Map;

import com.yp.common.vo.user.LoginUser;
import com.yp.common.vo.user.UserVo;

/**
 * 用户服务
 * @author zhiya.chai
 * 2016年9月3日 下午4:39:42
 */
public interface UserService {
	
	/**
	 * 根据条件查询用户信息
	 * @param map
	 * @return
	 * @return List<UserVo>
	 * @author zhiya.chai
	 * 2016年9月3日 下午6:40:05
	 */
	List<UserVo> getUserList(Map<String,Object> map);
	
	/**
	 * 登录
	 * @param loginUser
	 * @return UserVo
	 * @author zhiya.chai
	 */
	UserVo login(LoginUser loginUser);
	
	/**
	 * 用户注册
	 * @param user
	 * @return UserVo
	 * @author zhiya.chai
	 */
	UserVo register(UserVo user);
	
}
