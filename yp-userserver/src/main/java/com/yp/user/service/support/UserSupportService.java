package com.yp.user.service.support;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.yp.common.db.user.UserDB;
import com.yp.common.encrypt.IrreversibEncrypt;
import com.yp.common.enums.ErrorCode;
import com.yp.common.enums.user.Degree;
import com.yp.common.enums.user.SexType;
import com.yp.common.exception.ErrorCodeException;
import com.yp.common.util.GUIDGenerator;
import com.yp.common.vo.user.UserVo;
import com.yp.user.service.datasupport.UserDataSupport;

/**
 * 用户验证
 * @author zhiya.chai
 * 2016年9月3日 下午6:33:44
 */
@Service
public class UserSupportService {
	
	@Autowired
	private UserDataSupport userDataSupport;

	/**
	 * 注册用户验证
	 * @param regUser
	 * @return
	 * @author zhiya.chai
	 */
	public void validateRegisterUser(UserVo regUser) {
		if(StringUtils.isBlank(regUser.getPhoneNo())) {
			throw new ErrorCodeException(ErrorCode.SYS_EMPTY_PARAMETER, "请填写手机号。");
		}
		if(StringUtils.isNotBlank(regUser.getPhoneNo())) {
			validatePhoneNo(regUser.getPhoneNo());
		}
	}
	
	/**
	 * 验证手机号
	 * @param loginName
	 * @author zhiya.chai
	 */
	public void validatePhoneNo(String phoneNo) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("phoneNo", phoneNo);
		List<UserVo> list = userDataSupport.getUserList(map);
		if(CollectionUtils.isNotEmpty(list)) {
			throw new ErrorCodeException(ErrorCode.PHONENO_ALREADY_EXIST);
		}
	}
	
	/**
	 * 生成注册用户
	 * @param regUser
	 * @author zhiya.chai
	 */
	public UserDB genRegisterUser(UserVo regUser) {
		UserDB userDB = new UserDB();
		userDB.setBornMonth(regUser.getBornMonth());
		userDB.setBornYear(regUser.getBornYear());
		userDB.setCityId(regUser.getCityId());
		userDB.setDegree(Degree.高中或中专.getValue());
		userDB.setDistrictId(regUser.getDistrictId());
		userDB.setHeadPicId(1);
		userDB.setLevelId(0);
		/**
		 * 生成密码
		 */
		String salt = GUIDGenerator.getGUID();
		String password = salt + regUser.getPassword();
		userDB.setSalt(salt);
		userDB.setPassword(IrreversibEncrypt.MD5Encrypt(password));
		
		userDB.setPhoneNo(StringUtils.isBlank(regUser.getPhoneNo()) ? null : regUser.getPhoneNo());
		userDB.setProvinceId(regUser.getProvinceId());
		userDB.setScore(0);
		userDB.setSex(SexType.男.getType());
		userDB.setSignature(regUser.getSignature());
		userDB.setUserGuid(GUIDGenerator.getGUID());
		userDB.setUserName(GUIDGenerator.getGUID());
		return userDB;
	}
	/**
	 * 通过电话号码查找用户
	 * @param phoneNo
	 * @return UserVo
	 * @author zhiya.chai
	 */
	public UserVo getUserByPhoneNo(String phoneNo){
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("phoneNo", phoneNo);
		List<UserVo> list = this.userDataSupport.getUserList(map);
		if(CollectionUtils.isEmpty(list)) {
			return null;
		}
		return list.get(0);
	}
	
}
