package com.yp.publicweb.domain;

import java.io.Serializable;

/**
 * 返回值
 * @author zhiya.chai
 * 2016年9月8日 下午1:41:50
 */
public class Response implements Serializable {

	private static final long serialVersionUID = -6594089859765211822L;
	
	private int retCode;//返回码
	private String retMsg;//返回信息
	private Object data;//返回对象
	
	public int getRetCode() {
		return retCode;
	}
	public Response setRetCode(int retCode) {
		this.retCode = retCode;
		return this;
	}
	public String getRetMsg() {
		return retMsg;
	}
	public Response setRetMsg(String retMsg) {
		this.retMsg = retMsg;
		return this;
	}
	public Object getData() {
		return data;
	}
	public Response setData(Object data) {
		this.data = data;
		return this;
	}
	
	public static Response build(){
		return new Response();
	}
	
	
}
