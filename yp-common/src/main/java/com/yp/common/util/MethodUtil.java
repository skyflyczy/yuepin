package com.yp.common.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.MethodSignature;

public class MethodUtil {
	private static Log log = LogFactory.getLog(MethodUtil.class);
	
	/**
	 * Get a single {@link Annotation} of <code>annotationType</code> from the
	 * supplied {@link JoinPoint}.
	 * @param joinPoint the joinPoint to look for annotations on
	 * @param annotationType the annotation class to look for
	 * @return the annotations found
	 */
	public static <A extends Annotation> A getAnnotation(JoinPoint joinPoint, Class<A> annotationType) {
		Method method=getMethod(joinPoint);
		try {
			return method.getAnnotation(annotationType);
		} catch (Exception e) {
			log.error(e);
			return null;
		}
	}
	
	/**
	 * 获得JoinPoint方法的返回对象类型，只能对方法
	 * @param joinPoint
	 * @return
	 */
	public static Class<?> getReturnClassOfMethod(JoinPoint joinPoint){
		Method method=getMethod(joinPoint);
	  	if (method!=null){
	  		return method.getReturnType();
	  	}else{
	  		return null;
	  	}
	}
	
	/**
	 * 获得Method,从joinPoint
	 * @param joinPoint
	 * @return
	 */
	public static Method getMethod(JoinPoint joinPoint){
		Signature sig = joinPoint.getStaticPart().getSignature();
		Method method =null;
	  	if (sig instanceof MethodSignature) {
	  	  // this must be a call or execution join point
	  	  method = ((MethodSignature)sig).getMethod();
	  	}else{
	  		throw new RuntimeException("joinPoint is not Method,Can't use this Process.");
	  	}
	  	return method;
	}
}
