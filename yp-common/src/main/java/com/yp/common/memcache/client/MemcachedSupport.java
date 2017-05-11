package com.yp.common.memcache.client;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;

/**
 * 提供memcached的基础支持.
 */
public class MemcachedSupport implements InitializingBean {
	protected Log log = LogFactory.getLog(getClass());
	protected MemcachedCache memcachedCache;
	
	public void setMemcachedCache(MemcachedCache memcachedCache) {
		this.memcachedCache = memcachedCache;
	}

	public MemcachedCache getMemcachedCache() {
		return memcachedCache;
	}
	
	/**
	 * spring中当属性注入完毕后检查memcacheCache是否未注入
	 */
	public void afterPropertiesSet() {
		if (memcachedCache == null) {
			throw new IllegalArgumentException("Property 'memcachedCache' is required");
		}
	}
}
