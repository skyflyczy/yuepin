package com.yp.common.service.mix;

/**
 * 验证码接口
 * @author zhiya.chai
 * 2016年11月19日 下午5:12:39
 */
public interface VerifyCodeService {

	/**
	 * 验证手机验证码
	 * @param phoneNo
	 * @param verifyCode
	 * @return
	 */
	boolean validateVerifyCode(String phoneNo,String verifyCode);
	
	/**
	 * 发送验证码
	 * @param phoneNo
	 * @return
	 */
	boolean sendVerifyCode(String phoneNo); 
}
