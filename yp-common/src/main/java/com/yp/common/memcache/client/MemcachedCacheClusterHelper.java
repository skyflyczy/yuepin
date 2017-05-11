/**
 * 
 */
package com.yp.common.memcache.client;

import java.util.Arrays;
import java.util.List;

/**
 * 为封装的MemCache提供实际处理的帮助类
 *
 */
public class MemcachedCacheClusterHelper
{
	private MemcachedCache[] memcachedCaches;
	/**
	 * 构造方法
	 * @param memcachedCache	多个memcached服务器实例
	 */
	public MemcachedCacheClusterHelper(MemcachedCache[] memcachedCache) {
		this.memcachedCaches = memcachedCache;
	}

	public List<MemcachedCache> getClusterCache() {
		return Arrays.asList(memcachedCaches);
	}
	
	/**
	 * 根据算法获取集群中的某一台节点服务器
	 *	@param key 主键
	 *	@return 主键对应的memcachedCache对象
	 */
	public MemcachedCache getCacheClient(String key) {
		
		List<MemcachedCache> clusters = getClusterCache();
		long keyhash = key.hashCode();
		int index = (int) keyhash % clusters.size();
		if (index < 0) index *= -1;

		return clusters.get(index);
	}
}
