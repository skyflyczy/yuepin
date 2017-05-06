package com.yp.publicweb.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * 基础 Controller
 * @author zhiya.chai
 * 2016年9月8日 下午1:37:23
 */
@Controller
public class BaseController {

	private ThreadLocal<HttpServletRequest> currentRequest = new ThreadLocal<HttpServletRequest>();
	private ThreadLocal<HttpServletResponse> currentResponse = new ThreadLocal<HttpServletResponse>();
	
	@ModelAttribute
	public void setHttpServletRequestAndResponse(HttpServletRequest request,HttpServletResponse response){
		currentRequest.set(request);
		currentResponse.set(response);
	}
	
	/**
	 * 获取request
	 * @return
	 * @return HttpServletRequest
	 * @author zhiya.chai
	 * 2016年9月6日 下午2:41:53
	 */
	public HttpServletRequest request() {
	    return currentRequest.get();
	}
	/**
	 * 获取response
	 * @return
	 * @return HttpServletResponse
	 * @author zhiya.chai
	 * 2016年9月6日 下午2:42:10
	 */
	public HttpServletResponse response(){
		return currentResponse.get();
	}
}
