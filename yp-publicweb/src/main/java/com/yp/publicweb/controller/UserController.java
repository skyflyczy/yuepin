package com.yp.publicweb.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.yp.common.enums.ErrorCode;
import com.yp.common.service.user.UserService;
import com.yp.common.vo.user.UserVo;
import com.yp.publicweb.domain.Response;

/**
 * 用户Controller
 * @author zhiya.chai
 * 2016年9月8日 下午1:39:12
 */
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {
	
	@Reference(timeout=10000)
	private UserService userService;
	
	@RequestMapping(value="/test", method = RequestMethod.GET)
	public Response test(){
		Map<String,Object> map = new HashMap<String,Object>();
		List<UserVo> list = userService.getUserList(map);
		return Response.build().setRetCode(ErrorCode.SUCCESS.getCode()).setData(list);
	}

}
