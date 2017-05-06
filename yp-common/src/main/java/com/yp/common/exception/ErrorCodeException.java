package com.yp.common.exception;

import com.yp.common.enums.ErrorCode;

/**
 * 返回码异常
 * @author zhiya.chai
 * 2016年9月3日 下午9:42:26
 */
public class ErrorCodeException extends RuntimeException{

	/**
	 * 2016年9月3日 下午9:43:14
	 */
	private static final long serialVersionUID = 4437952667604307795L;

	private ErrorCode errorCode;

	public ErrorCode getErrorCode() {
		return errorCode;
	}

	public ErrorCodeException(ErrorCode errorCode) {
		this.errorCode = errorCode;
	}
	
	public ErrorCodeException(ErrorCode errorCode, Throwable cause) {
		super(cause);
		this.errorCode = errorCode;
	}

	public ErrorCodeException(ErrorCode errorCode, String message) {
		super(message);
		this.errorCode = errorCode;
	}

	public String toString(){
		return this.errorCode.getCode()+" : "+this.errorCode.getMessage();
	}
}
