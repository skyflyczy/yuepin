package com.yp.common.memcache.client;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import com.yp.common.memcache.MemCachedClient;



/**
 * 使用Adaptor模式实现MemcachedCache.简单的代理MemCachedClient进行操作
 */
public class MemcachedCacheImpl implements MemcachedCache {
	
	private MemCachedClient memCachedClient;
	private String cacheName;
	
	/**
	 * 默认构造方法
	 */
	public MemcachedCacheImpl() {
	}
	
	/**
	 * 构造方法
	 * @param name	缓存名字，与IoPool的name相同
	 */
	public MemcachedCacheImpl(String name) {
		cacheName = name;
		memCachedClient = new MemCachedClient(cacheName);
	}
	
	/**
	 * 构造方法
	 * @param name		IoPool中定义的名字
	 * @param compressEnable	是否压缩
	 * @param compressThreshold	压缩阀值
	 */
	public MemcachedCacheImpl(String name, boolean compressEnable, long compressThreshold) {
		cacheName = name;
		memCachedClient = new MemCachedClient(cacheName);
		memCachedClient.setCompressEnable(compressEnable);
		memCachedClient.setCompressThreshold(compressThreshold);
	}

	/**
	 * 构造方法
	 * @param memCachedClient	已有的MemCachedClient对象
	 */
	public MemcachedCacheImpl(MemCachedClient memCachedClient) {
		this.memCachedClient = memCachedClient;
	}

	@Override
	public boolean add(String key, Object value) {
		return memCachedClient.add(generateKey(key), value);
	}

	@Override
	public boolean add(String key, Object value, Integer hashCode) {
		return memCachedClient.add(generateKey(key), value, hashCode);
	}

	@Override
	public boolean add(String key, Object value, Date expiry) {
		return memCachedClient.add(generateKey(key), value, expiry);
	}

	@Override
	public boolean add(String key, Object value, Date expiry, Integer hashCode) {
		return memCachedClient.add(generateKey(key), value, expiry, hashCode);
	}

	@Override
	public long addOrDecr(String key) {
		return memCachedClient.addOrDecr(generateKey(key));
	}

	@Override
	public long addOrDecr(String key, long inc) {
		return memCachedClient.addOrDecr(generateKey(key), inc);
	}

	@Override
	public long addOrDecr(String key, long inc, Integer hashCode) {
		return memCachedClient.addOrDecr(generateKey(key), inc, hashCode);
	}

	@Override
	public long addOrIncr(String key) {
		return memCachedClient.addOrIncr(generateKey(key));
	}

	@Override
	public long addOrIncr(String key, long inc) {
		return memCachedClient.addOrIncr(generateKey(key), inc);
	}

	@Override
	public long addOrIncr(String key, long inc, Integer hashCode) {
		return memCachedClient.addOrIncr(generateKey(key), inc, hashCode);
	}

	@Override
	public long decr(String key) {
		return memCachedClient.decr(generateKey(key));
	}

	@Override
	public long decr(String key, long inc) {
		return memCachedClient.decr(generateKey(key), inc);
	}

	@Override
	public long decr(String key, long inc, Integer hashCode) {
		return memCachedClient.decr(generateKey(key), inc, hashCode);
	}

	@Override
	public boolean delete(String key) {
		return memCachedClient.delete(generateKey(key));
	}

	@Override
	public boolean delete(String key, Date expiry) {
		return memCachedClient.delete(generateKey(key), expiry);
	}

	@Override
	public boolean delete(String key, Integer hashCode, Date expiry) {
		return memCachedClient.delete(generateKey(key), hashCode, expiry);
	}

	@Override
	public boolean flushAll() {
		return memCachedClient.flushAll();
	}

	@Override
	public boolean flushAll(String[] servers) {
		return memCachedClient.flushAll(servers);
	}

	@Override
	public Object get(String key) {
		return memCachedClient.get(generateKey(key));
	}

	@Override
	public Object get(String key, Integer hashCode) {
		return memCachedClient.get(generateKey(key), hashCode);
	}

	@Override
	public Object get(String key, Integer hashCode, boolean asString) {
		return memCachedClient.get(generateKey(key), hashCode, asString);
	}

	@Override
	public long getCounter(String key) {
		return memCachedClient.getCounter(generateKey(key));
	}

	@Override
	public long getCounter(String key, Integer hashCode) {
		return memCachedClient.getCounter(generateKey(key), hashCode);
	}

	@Override
	public Map<String, Object> getMulti(String[] keys) {
		return memCachedClient.getMulti(generateKeys(keys));
	}

	@Override
	public Map<String, Object> getMulti(String[] keys, Integer[] hashCodes) {
		return memCachedClient.getMulti(generateKeys(keys), hashCodes);
	}

	@Override
	public Map<String, Object> getMulti(String[] keys, Integer[] hashCodes, boolean asString) {
		return memCachedClient.getMulti(generateKeys(keys), hashCodes, asString);
	}

	@Override
	public Object[] getMultiArray(String[] keys) {
		return memCachedClient.getMultiArray(generateKeys(keys));
	}

	@Override
	public Object[] getMultiArray(String[] keys, Integer[] hashCodes) {
		return memCachedClient.getMultiArray(generateKeys(keys), hashCodes);
	}

	@Override
	public Object[] getMultiArray(String[] keys, Integer[] hashCodes, boolean asString) {
		return memCachedClient.getMultiArray(generateKeys(keys), hashCodes, asString);
	}

	@Override
	public long incr(String key) {
		return memCachedClient.incr(generateKey(key));
	}

	@Override
	public long incr(String key, long inc) {
		return memCachedClient.incr(generateKey(key), inc);
	}

	@Override
	public long incr(String key, long inc, Integer hashCode) {
		return memCachedClient.incr(generateKey(key), inc, hashCode);
	}

	@Override
	public boolean keyExists(String key) {
		return memCachedClient.keyExists(generateKey(key));
	}

	@Override
	public boolean replace(String key, Object value) {
		return memCachedClient.replace(generateKey(key), value);
	}

	@Override
	public boolean replace(String key, Object value, Integer hashCode) {
		return memCachedClient.replace(generateKey(key), value, hashCode);
	}

	@Override
	public boolean replace(String key, Object value, Date expiry) {
		return memCachedClient.replace(generateKey(key), value, expiry);
	}

	@Override
	public boolean replace(String key, Object value, Date expiry, Integer hashCode) {
		return memCachedClient.replace(generateKey(key), value, expiry, hashCode);
	}

	@Override
	public boolean set(String key, Object value) {
		return memCachedClient.set(generateKey(key), value);
	}

	@Override
	public boolean set(String key, Object value, Integer hashCode) {
		return memCachedClient.set(generateKey(key), value, hashCode);
	}

	@Override
	public boolean set(String key, Object value, Date expiry) {
		return memCachedClient.set(generateKey(key), value, expiry);
	}

	@Override
	public boolean set(String key, Object value, Date expiry, Integer hashCode) {
		return memCachedClient.set(generateKey(key), value, expiry, hashCode);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map stats() {
		return memCachedClient.stats();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map stats(String[] servers) {
		return memCachedClient.stats(servers);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map statsCacheDump(int slabNumber, int limit) {
		return memCachedClient.statsCacheDump(slabNumber, limit);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map statsCacheDump(String[] servers, int slabNumber, int limit) {
		return memCachedClient.statsCacheDump(servers, slabNumber, limit);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map statsItems() {
		return memCachedClient.statsItems();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map statsItems(String[] servers) {
		return memCachedClient.statsItems(servers);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map statsSlabs() {
		return memCachedClient.statsSlabs();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map statsSlabs(String[] servers) {
		return memCachedClient.statsSlabs(servers);
	}

	@Override
	public boolean storeCounter(String key, long counter) {
		return memCachedClient.storeCounter(generateKey(key), counter);
	}

	@Override
	public boolean storeCounter(String key, Long counter) {
		return memCachedClient.storeCounter(generateKey(key), counter);
	}

	@Override
	public boolean storeCounter(String key, Long counter, Integer hashCode) {
		return memCachedClient.storeCounter(generateKey(key), counter, hashCode);
	}

	@Override
	public boolean clear() {
		return memCachedClient.flushAll();
	}

	@Override
	public Object put(String key, Object value) {
		return set(key, value);
	}

	@Override
	public Object put(String key, Object value, Date expiry) {
		return set(key, value, expiry);
	}

	@Override
	public Object put(String key, Object value, int TTL) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.SECOND, TTL);
		return set(key, value, calendar.getTime());
	}

	@Override
	public Object remove(String key) {
		Object object = get(key);
		delete(key);
		return object;
	}
	
	@Override
	public long addOrIncr(String key, long inc, Date expire, Integer hashCode) {
		return memCachedClient.addOrIncr(generateKey(key), inc, expire, hashCode);
	}

	@Override
	public long addOrDecr(String key, long inc, Date expire, Integer hashCode) {
		return memCachedClient.addOrDecr(generateKey(key), inc, expire, hashCode);
	}

	public void setMemCachedClient(MemCachedClient memCachedClient) {
		this.memCachedClient = memCachedClient;
	}

	@Override
	public String getCacheName() {
		return this.cacheName;
	}
	
	public void setCacheName(String cacheName) {
		this.cacheName = cacheName;
	}
	
	/**
	 * key生成器，子类可重载实现
	 * @param key
	 * @return
	 */
	protected String generateKey(String key){
		return key;
	}
	
	/**
	 * key[]生成器，子类可重载实现
	 * @param keys
	 * @return
	 */
	private String [] generateKeys(String [] keys){
		for (int i=0;i<keys.length;i++){
			keys[i]=generateKey(keys[i]);
		}
		return keys;
	}
}
