/**
 * 
 */
package com.yp.common.memcache.client;

import org.apache.commons.codec.digest.DigestUtils;

import com.yp.common.memcache.MemCachedClient;




/**
 * memcached云客户端，需要标识客户端名称（key前缀）
 *
 */
public class MemcachedCacheCloudImpl extends MemcachedCacheImpl implements
		MemcachedCache {
	private static final int MEMCACHED_KEY_MAX_LENGTH = 512;
	private String prefix;
	
	
	
	/**
	 * 构造方法
	 * @param appName	应用名
	 */
	public MemcachedCacheCloudImpl(String appName) {
		super();
		this.prefix=generatePrefix(appName);
	}

	/**
	 * 构造方法
	 * @param appName	应用名
	 * @param memCachedClient	memcached缓存client
	 */
	public MemcachedCacheCloudImpl(String appName,MemCachedClient memCachedClient) {
		super(memCachedClient);
		this.prefix=generatePrefix(appName);
	}


	/**
	 * 构造方法
	 * @param appName	应用名字
	 * @param name		IoPool中定义的名字
	 * @param compressEnable	是否压缩
	 * @param compressThreshold	压缩阀值
	 */

	public MemcachedCacheCloudImpl(String appName,String name, boolean compressEnable,
			long compressThreshold) {
		super(name, compressEnable, compressThreshold);
		this.prefix=generatePrefix(appName);
	}


	/**
	 * 构造方法	
	 * @param appName	应用名字
	 * @param name		IoPool中的名字
	 */
	public MemcachedCacheCloudImpl(String appName,String name) {
		super(name);
		this.prefix=generatePrefix(appName);
	}

	/**
	 * 生成前缀
	 * @param appName
	 * @return
	 */
	private String generatePrefix(String appName){
		return appName.concat("_");
	}
	/**
	 * memcached云内禁止flushAll操作
	 * @return 无返回，禁止flushAll操作
	 */
	@Override
	public boolean flushAll() {
		throw new RuntimeException("Disabled \"flushall\" opertion in memcached cloud");
	}

	/**
	 * memcached云内禁止flushAll操作
	 * @param servers 服务器列表
	 * @return 无返回，禁止flushAll操作
	 */
	@Override
	public boolean flushAll(String[] servers) {
		throw new RuntimeException("Disabled \"flushall\" opertion in memcached cloud");
	}
	
	
	/**
	 * key生成器，1、加前缀；2、当大于512字节时使用MD5
	 * @param key
	 * @return
	 */
	protected String generateKey(String key){
		String k=this.prefix.concat(key);
		if (k.getBytes().length>MEMCACHED_KEY_MAX_LENGTH){
			k=this.prefix.concat(DigestUtils.md5Hex(key));
		}
		return k;
	}
	
	
	
}
