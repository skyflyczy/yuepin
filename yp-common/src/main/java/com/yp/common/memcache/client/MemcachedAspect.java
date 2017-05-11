package com.yp.common.memcache.client;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.zip.CRC32;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;

import com.yp.common.util.DateTimeUtil;
import com.yp.common.util.MethodUtil;


public class MemcachedAspect extends MemcachedSupport {
	
	/**
	 * 声明对所有公共方法进行拦截
	 */
	@Pointcut("execution(public * *(..))")
	public void publicMethods(){
	}
	
	// FIXME: 目前不支持在类上注解,加了之后变成publicMethods() && (classWithMemcached() || methodWithMemcached())这样子会导致pointcut计算有问题?
//	@Pointcut("@target(Memcached)")
//	public void classWithMemcached(){
//	}
	
	/**
	 * 声明对Memcached注解进行拦截
	 */
	@Pointcut("@annotation(Memcached)")
	public void methodWithMemcached(){
	}

	/**
	 * 拦截后使用执行的方法
	 * @param joinpoint	切入点
	 * @return	切面切入后返回给外层的返回数据
	 * @throws Throwable	可能的异常
	 */
	@Around("publicMethods() && methodWithMemcached()")
	public Object optimizeMethodByMemcached(ProceedingJoinPoint joinpoint) throws Throwable {
		if(log.isDebugEnabled()){
			String method = joinpoint.getSignature().toLongString();
			log.debug("optimize method call [" + method + "] by memcached.");
		}
		
		CacheEntry value = null;
		String cachekey = resolveCachekey(joinpoint);
		value = (CacheEntry) memcachedCache.get(cachekey);
		if(value == null){
			// not found in cache, invoke 'real' method and put result into cache.
			if(log.isDebugEnabled()){
				log.debug("invoking actual method.");
			}
			Object result = joinpoint.proceed();
			value = new CacheEntry(result, System.currentTimeMillis());
			int timeout = getTimeout(joinpoint);
			
			// 保存到memcached
			if(timeout > 0)
				memcachedCache.add(cachekey, value, DateTimeUtil.add(new Date(), Calendar.SECOND, timeout));
			else
				memcachedCache.add(cachekey, value);
		}
		return value.getResult();
	}

	/**
	 * 获得设置的超时时间
	 * @param joinpoint
	 * @return
	 */
	private int getTimeout(ProceedingJoinPoint joinpoint) {
		Memcached m = MethodUtil.getAnnotation(joinpoint, Memcached.class);
		return m != null ? m.timeout() : -1;
	}

	/**
	 * 根据连接点信息生成缓存key
	 * @param joinpoint
	 * @return
	 */
	private String resolveCachekey(ProceedingJoinPoint joinpoint) {
		String key = generatekey(joinpoint);
		if(log.isDebugEnabled()){
			log.debug("computing cache key is: " + key);
		}
		return key;
	}
	
	/**
	 * 根据方法签名和实参的字符值CRC32编码生成缓存key.这里是用<code>String.valueOf()</code>方法取实参的字符值.
	 * 即如果该方法返回的结果一样,则生成的key也一样
	 * @param joinpoint
	 * @return
	 */
	protected String generatekey(ProceedingJoinPoint joinpoint) {
		String methodName = joinpoint.getSignature().toLongString();
		Object[] args = joinpoint.getArgs();
		CRC32 crc32 = new CRC32();
		crc32.update(methodName.getBytes());
		for (Object arg : args) {
			String temp = String.valueOf(arg);
			crc32.update(temp.getBytes());
		}
		return String.valueOf(crc32.getValue());
	}
	
	@SuppressWarnings("serial")
	protected static final class CacheEntry implements Serializable {
		private Object result;
		private long timestamp;
		
		public CacheEntry(Object result, long timestamp) {
			this.result = result;
			this.timestamp = timestamp;
		}

		public Object getResult() {
			return result;
		}

		public long getTimestamp() {
			return timestamp;
		}
	}
}
