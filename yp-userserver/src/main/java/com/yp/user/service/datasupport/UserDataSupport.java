package com.yp.user.service.datasupport;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.yp.common.db.user.UserDB;
import com.yp.common.encrypt.SymmetricEncrypt;
import com.yp.common.vo.user.UserVo;
import com.yp.user.dao.UserDao;

/**
 * 用户数据支持服务
 * @author zhiya.chai
 * 2016年9月3日 下午6:34:38
 */
@Service
public class UserDataSupport {

	@Autowired
	private UserDao userDao;
	/*
	 * 解密
	 */
	private void decryptFiled(UserVo vo) {
		vo.setPhoneNo(StringUtils.isNotBlank(vo.getPhoneNo()) ? SymmetricEncrypt.decryptStr(vo.getPhoneNo()):vo.getPhoneNo());
	}
	/*
	 * 解密
	 */
	private void decryptFiled(UserDB db) {
		db.setPhoneNo(StringUtils.isNotBlank(db.getPhoneNo()) ? SymmetricEncrypt.decryptStr(db.getPhoneNo()):db.getPhoneNo());
	}
	/*
	 * 加密
	 */
	private void encryptFiled(UserDB db) {
		db.setPhoneNo(StringUtils.isNotBlank(db.getPhoneNo()) ? SymmetricEncrypt.encryptStr(db.getPhoneNo()):db.getPhoneNo());
	}
	/*
	 * 加密
	 */
	private void encryptFiled(Map<String,Object> map) {
		if(map.containsKey("phoneNo")){
			map.put("phoneNo", SymmetricEncrypt.encryptStr(map.get("phoneNo").toString()));
		}
	}
	/**
	 * 根据条件查询用户集合
	 * @param map
	 * @return List<UserVo>
	 * @author zhiya.chai
	 * 2016年9月3日 下午6:41:47
	 */
	public List<UserVo> getUserList(Map<String,Object> map) {
		encryptFiled(map);
		List<UserVo> list = userDao.select(map);
		if(CollectionUtils.isNotEmpty(list)) {
			for(UserVo vo : list) {
				decryptFiled(vo);
			}
		}
 		return list;
	}
	/**
	 * 插入用户信息
	 * @param db
	 * @return Integer
	 * @author zhiya.chai
	 * 2016年11月19日 下午4:02:21
	 */
	public Integer insertUser(UserDB db) {
		//加密
		encryptFiled(db);
		this.userDao.insert(db);
		decryptFiled(db);
		return db.getId();
	}
	/**
	 * 根据Id查找
	 * @param id
	 * @return UserVo
	 * @author zhiya.chai
	 * 2016年11月19日 下午4:25:01
	 */
	public UserVo getUserById(int id) {
		Map<String,Object> map = new HashMap<String, Object>(1);
		map.put("id", id);
		UserVo vo = this.userDao.getById(map);
		decryptFiled(vo);
		return vo;
	}
}

