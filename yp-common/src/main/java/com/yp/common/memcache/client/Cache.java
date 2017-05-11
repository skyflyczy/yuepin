package com.yp.common.memcache.client;

import java.util.Date;

/**
 * Cache统一接口 
 * @param <V> 缓存的值
 * @param <K> 缓存的Key
 */
public interface Cache<K,V>
{
	/**
	 * 保存数据
	 * @param key	主键
	 * @param value	值
	 * @return 缓存的对象泛型
	 */
	public V put(K key,V value);
	
	/**
	 * 保存有有效期的数据
	 * @param key	主键
	 * @param value	值
	 * @param expiry 有效期
	 * @return	缓存的对象泛型
	 */
	public V put(K key,V value, Date expiry);
	
	/**
	 * 保存有有效期的数据
	 * @param key	主键
	 * @param value	值
	 * @param TTL	数据超时的秒数
	 * @return	缓存的对象泛型
	 */
	public V put(K key,V value, int TTL);
	
	/**
	 * 获取缓存数据
	 * @param key	主键
	 * @return	缓存的对象泛型
	 */
	public V get(K key);
	
	/**
	 * 移出缓存数据
	 * @param key	主键
	 * @return	缓存的对象泛型
	 */
	public V remove(K key);
	
	/**
	 * 删除所有缓存内的数据
	 * @return	删除是否成功
	 */
	public boolean clear();
}