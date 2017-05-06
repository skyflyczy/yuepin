package com.yp.mix.service.impl;

import org.springframework.stereotype.Service;

import com.yp.common.service.mix.VerifyCodeService;

/**
 * 验证码服务
 * @author zhiya.chai
 * 2016年11月19日 下午5:14:13
 */
@Service
public class VerifyCodeServiceImpl implements VerifyCodeService {

	@Override
	public boolean validateVerifyCode(String phoneNo, String verifyCode) {
		return false;
	}

	@Override
	public boolean sendVerifyCode(String phoneNo) {
		return false;
	}

}
