package com.yp.user.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.yp.common.db.user.UserLoginDB;
import com.yp.common.vo.user.UserLoginVo;

/**
 * 
 * Userlogin
 * @author zhiya.chai
 * 2017-04-20 21:22:06
 */
@Repository
public interface UserLoginDao {
	public int insert(UserLoginDB o);
	
	public int update(UserLoginDB o);
	
	public void delete(Integer id);
	
	public UserLoginVo getByUserId(Integer id);
	
	public List<UserLoginVo> select(Map<String, Object> map);
}
