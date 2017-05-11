package com.yp.common.memcache.client;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 标明为当前方法或者这个类的所有方法执行配置基于memcached的缓存优化
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({
	/* ElementType.TYPE, */ // FIXME: 目前还有问题? @see MemcachedAspect#classWithMemcached
	ElementType.METHOD}
)
public @interface Memcached {
	/**
	 * 设置缓存超时时间,单位为秒.如果设置为-1则不设置超时.
	 */
	int timeout() default -1;
}
