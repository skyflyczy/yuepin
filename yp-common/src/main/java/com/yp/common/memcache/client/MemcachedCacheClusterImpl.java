package com.yp.common.memcache.client;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.yp.common.memcache.client.MemcachedCacheClusterProcessor.ClusterCommand;

/**
 * 解决memcached的单点问题.通过在client之间的互相复制保存冗余数据,来完成数据的容灾.
 * 建议做replication时将互相备份的memcached节点部署在不同服务器上.
 * 
 */
public class MemcachedCacheClusterImpl implements MemcachedCache {
	private Log log = LogFactory.getLog(MemcachedCacheClusterImpl.class);

	private MemcachedCacheClusterProcessor processor;
	private MemcachedCacheClusterHelper helper;

	/**
	 * 构造方法
	 * @param memcachedCaches	多个memcached服务器对象
	 */
	public MemcachedCacheClusterImpl(MemcachedCache[] memcachedCaches) {
		this(memcachedCaches, new MemcachedCacheClusterProcessorImpl());
	}
	
	/**
	 * 构造方法
	 * @param memcachedCaches	多个memcached服务器对象
	 */	
	public MemcachedCacheClusterImpl(MemcachedCache[] memcachedCaches, MemcachedCacheClusterProcessor processor) {
		this.helper = new MemcachedCacheClusterHelper(memcachedCaches);
		this.processor = processor;
		processor.setHelper(helper);
		processor.startProcess();
	}

	/**
	 * 注销方法
	 */
	public void destroy() {
		try {
			if (processor != null)
				processor.stopProcess();
		} catch (Exception ex) {
			log.error(ex);
		}
	}

	@Override
	public boolean add(final String key, final Object value) {
		boolean result = helper.getCacheClient(key).add(key, value);

		processor.executeCommand(new ClusterCommand(){
			@Override
			public void execute(MemcachedCache cache) {
				if(!cache.equals(helper.getCacheClient(key))) cache.add(key, value);
			}
		});

		return result;
	}

	@Override
	public boolean add(final String key, final Object value, final Integer hashCode) {
		boolean result = helper.getCacheClient(key).add(key, value, hashCode);

		processor.executeCommand(new ClusterCommand(){
			@Override
			public void execute(MemcachedCache cache) {
				if(!cache.equals(helper.getCacheClient(key))) cache.add(key, value, hashCode);
			}
		});
		
		return result;
	}

	@Override
	public boolean add(final String key, final Object value, final Date expiry) {
		boolean result = helper.getCacheClient(key).add(key, value, expiry);

		processor.executeCommand(new ClusterCommand(){
			@Override
			public void execute(MemcachedCache cache) {
				if(!cache.equals(helper.getCacheClient(key))) cache.add(key, value, expiry);
			}
		});

		return result;
	}

	@Override
	public boolean add(final String key, final Object value, final Date expiry, final Integer hashCode) {
		boolean result = helper.getCacheClient(key).add(key, value, expiry, hashCode);

		processor.executeCommand(new ClusterCommand(){
			@Override
			public void execute(MemcachedCache cache) {
				if(!cache.equals(helper.getCacheClient(key))) cache.add(key, value, expiry, hashCode);
			}
		});
		
		return result;
	}

	@Override
	public long addOrDecr(final String key) {
		long result = helper.getCacheClient(key).addOrDecr(key);

		processor.executeCommand(new ClusterCommand(){
			@Override
			public void execute(MemcachedCache cache) {
				if(!cache.equals(helper.getCacheClient(key))) cache.addOrDecr(key);
			}
		});

		return result;
	}

	@Override
	public long addOrDecr(final String key, final long dec) {
		long result = helper.getCacheClient(key).addOrDecr(key, dec);

		processor.executeCommand(new ClusterCommand(){
			@Override
			public void execute(MemcachedCache cache) {
				if(!cache.equals(helper.getCacheClient(key))) cache.addOrDecr(key, dec);
			}
		});

		return result;
	}

	@Override
	public long addOrDecr(final String key, final long dec, final Integer hashCode) {
		long result = helper.getCacheClient(key).addOrDecr(key, dec, hashCode);

		processor.executeCommand(new ClusterCommand(){
			@Override
			public void execute(MemcachedCache cache) {
				if(!cache.equals(helper.getCacheClient(key))) cache.addOrDecr(key, dec, hashCode);
			}
		});

		return result;
	}

	@Override
	public long addOrIncr(final String key) {
		long result = helper.getCacheClient(key).addOrIncr(key);

		processor.executeCommand(new ClusterCommand(){
			@Override
			public void execute(MemcachedCache cache) {
				if(!cache.equals(helper.getCacheClient(key))) cache.addOrIncr(key);
			}
		});

		return result;
	}

	@Override
	public long addOrIncr(final String key, final long inc) {
		long result = helper.getCacheClient(key).addOrIncr(key, inc);

		processor.executeCommand(new ClusterCommand(){
			@Override
			public void execute(MemcachedCache cache) {
				if(!cache.equals(helper.getCacheClient(key))) cache.addOrIncr(key, inc);
			}
		});

		return result;
	}

	@Override
	public long addOrIncr(final String key, final long inc, final Integer hashCode) {
		long result = helper.getCacheClient(key).addOrIncr(key, inc, hashCode);

		processor.executeCommand(new ClusterCommand(){
			@Override
			public void execute(MemcachedCache cache) {
				if(!cache.equals(helper.getCacheClient(key))) cache.addOrIncr(key, inc, hashCode);
			}
		});

		return result;
	}

	@Override
	public long decr(final String key) {
		long result = helper.getCacheClient(key).decr(key);

		processor.executeCommand(new ClusterCommand(){
			@Override
			public void execute(MemcachedCache cache) {
				if(!cache.equals(helper.getCacheClient(key))) cache.decr(key);
			}
		});

		return result;
	}

	@Override
	public long decr(final String key, final long dec) {
		long result = helper.getCacheClient(key).decr(key, dec);

		processor.executeCommand(new ClusterCommand(){
			@Override
			public void execute(MemcachedCache cache) {
				if(!cache.equals(helper.getCacheClient(key))) cache.decr(key, dec);
			}
		});

		return result;
	}

	@Override
	public long decr(final String key, final long dec, final Integer hashCode) {
		long result = helper.getCacheClient(key).decr(key, dec, hashCode);

		processor.executeCommand(new ClusterCommand(){
			@Override
			public void execute(MemcachedCache cache) {
				if(!cache.equals(helper.getCacheClient(key))) cache.decr(key, dec, hashCode);
			}
		});

		return result;
	}

	@Override
	public boolean delete(String key) {
		boolean result = helper.getCacheClient(key).delete(key);

		List<MemcachedCache> caches = helper.getClusterCache();
		for (MemcachedCache cache : caches) {
			if (helper.getCacheClient(key).equals(cache))
				continue;

			try {
				cache.delete(key);
			} catch (Exception ex) {
				logerr(cache, "delete", ex);
			}
		}
		return result;
	}

	@Override
	public boolean delete(String key, Date expiry) {
		boolean result = helper.getCacheClient(key).delete(key, expiry);

		List<MemcachedCache> caches = helper.getClusterCache();
		for (MemcachedCache cache : caches) {
			if (helper.getCacheClient(key).equals(cache))
				continue;

			try {
				cache.delete(key, expiry);
			} catch (Exception ex) {
				logerr(cache, "delete", ex);
			}
		}
		return result;
	}

	@Override
	public boolean delete(String key, Integer hashCode, Date expiry) {
		boolean result = helper.getCacheClient(key).delete(key, hashCode, expiry);

		List<MemcachedCache> caches = helper.getClusterCache();
		for (MemcachedCache cache : caches) {
			if (helper.getCacheClient(key).equals(cache))
				continue;

			try {
				cache.delete(key, hashCode, expiry);
			} catch (Exception ex) {
				logerr(cache, "delete", ex);
			}
		}
		return result;
	}

	@Override
	public boolean flushAll() {
		boolean result = true;
		
		List<MemcachedCache> caches = helper.getClusterCache();
		for (MemcachedCache cache : caches) {
			try {
				result = result && cache.flushAll();
			} catch (Exception ex) {
				logerr(cache, "flushAll", ex);
			}
		}
		return result;
	}

	@Override
	public boolean flushAll(String[] servers) {
		boolean result = true;
		
		List<MemcachedCache> caches = helper.getClusterCache();
		for (MemcachedCache cache : caches) {
			try {
				result = result && cache.flushAll(servers);
			} catch (Exception ex) {
				logerr(cache, "flushAll", ex);
			}
		}
		return result;
	}

	@Override
	public Object get(String key) {
		// 先根据算法去指定的cache中取
		Object result = helper.getCacheClient(key).get(key);
		if (result != null)
			return result;

		// 从cluster中尝试
		List<MemcachedCache> caches = helper.getClusterCache();
		for (MemcachedCache cache : caches) {
			if (helper.getCacheClient(key).equals(cache))
				continue;
			try {
				result = cache.get(key);
			} catch (Exception ex) {
				logerr(cache, "get", ex);
			}

			if (result != null) {
				helper.getCacheClient(key).set(key, result);
				break;
			}
		}
		return result;
	}

	@Override
	public Object get(String key, Integer hashCode) {
		Object result = helper.getCacheClient(key).get(key, hashCode);
		if (result != null)
			return result;

		List<MemcachedCache> caches = helper.getClusterCache();
		for (MemcachedCache cache : caches) {
			if (helper.getCacheClient(key).equals(cache))
				continue;
			try {
				result = cache.get(key, hashCode);
			} catch (Exception ex) {
				logerr(cache, "get", ex);
			}

			if (result != null) {
				helper.getCacheClient(key).set(key, result, hashCode);
				break;
			}
		}
		return result;
	}

	@Override
	public Object get(String key, Integer hashCode, boolean asString) {
		Object result = helper.getCacheClient(key).get(key, hashCode, asString);
		if (result != null)
			return result;

		List<MemcachedCache> caches = helper.getClusterCache();
		for (MemcachedCache cache : caches) {
			if (helper.getCacheClient(key).equals(cache))
				continue;
			try {
				result = cache.get(key, hashCode, asString);
			} catch (Exception ex) {
				logerr(cache, "get", ex);
			}

			if (result != null) {
				helper.getCacheClient(key).set(key, result, hashCode);
				break;
			}
		}
		return result;
	}

	@Override
	public long getCounter(String key) {
		long result = helper.getCacheClient(key).getCounter(key);

		if (result == -1) {
			List<MemcachedCache> caches = helper.getClusterCache();

			for (MemcachedCache cache : caches) {
				if (helper.getCacheClient(key).equals(cache))
					continue;

				try {
					result = cache.getCounter(key);
					if (result != -1) {
						helper.getCacheClient(key).storeCounter(key, result);
						return result;
					}
				} catch (Exception ex) {
					logerr(cache, "getCounter", ex);
				}
			}
		}
		return result;
	}

	@Override
	public long getCounter(String key, Integer hashCode) {
		long result = helper.getCacheClient(key).getCounter(key, hashCode);

		if (result == -1) {
			List<MemcachedCache> caches = helper.getClusterCache();

			for (MemcachedCache cache : caches) {
				if (helper.getCacheClient(key).equals(cache))
					continue;

				try {
					result = cache.getCounter(key, hashCode);
					if (result != -1) {
						helper.getCacheClient(key).storeCounter(key, result, hashCode);
						return result;
					}
				} catch (Exception ex) {
					logerr(cache, "getCounter", ex);
				}
			}
		}
		return result;
	}

	@Override
	public Map<String, Object> getMulti(String[] keys) {
		if (keys == null || keys.length <= 0)
			return null;

		Map<String, Object> result = helper.getCacheClient(keys[0]).getMulti(keys);
		if (result != null)
			return result;

		List<MemcachedCache> caches = helper.getClusterCache();
		for (MemcachedCache cache : caches) {
			if (helper.getCacheClient(keys[0]).equals(cache))
				continue;
			try {
				result = cache.getMulti(keys);
			} catch (Exception ex) {
				logerr(cache, "getMulti", ex);
			}
			if (result != null)
				break;
		}
		return result;
	}

	@Override
	public Map<String, Object> getMulti(String[] keys, Integer[] hashCodes) {
		if (keys == null || keys.length <= 0)
			return null;

		Map<String, Object> result = helper.getCacheClient(keys[0]).getMulti(keys, hashCodes);
		if (result != null)
			return result;

		List<MemcachedCache> caches = helper.getClusterCache();
		for (MemcachedCache cache : caches) {
			if (helper.getCacheClient(keys[0]).equals(cache))
				continue;
			try {
				result = cache.getMulti(keys, hashCodes);
			} catch (Exception ex) {
				logerr(cache, "getMulti", ex);
			}
			if (result != null)
				break;
		}
		return result;
	}

	@Override
	public Map<String, Object> getMulti(String[] keys, Integer[] hashCodes, boolean asString) {
		if (keys == null || keys.length <= 0)
			return null;

		Map<String, Object> result = helper.getCacheClient(keys[0]).getMulti(keys, hashCodes, asString);
		if (result != null)
			return result;

		List<MemcachedCache> caches = helper.getClusterCache();
		for (MemcachedCache cache : caches) {
			if (helper.getCacheClient(keys[0]).equals(cache))
				continue;
			try {
				result = cache.getMulti(keys, hashCodes, asString);
			} catch (Exception ex) {
				logerr(cache, "getMulti", ex);
			}
			if (result != null)
				break;
		}
		return result;
	}

	@Override
	public Object[] getMultiArray(String[] keys) {
		if (keys == null || keys.length <= 0)
			return null;

		Object[] result = helper.getCacheClient(keys[0]).getMultiArray(keys);
		if (result != null)
			return result;

		List<MemcachedCache> caches = helper.getClusterCache();
		for (MemcachedCache cache : caches) {
			if (helper.getCacheClient(keys[0]).equals(cache))
				continue;
			try {
				result = cache.getMultiArray(keys);
			} catch (Exception ex) {
				logerr(cache, "getMultiArray", ex);
			}
			if (result != null)
				break;
		}
		return result;
	}

	@Override
	public Object[] getMultiArray(String[] keys, Integer[] hashCodes) {
		if (keys == null || keys.length <= 0)
			return null;

		Object[] result = helper.getCacheClient(keys[0]).getMultiArray(keys, hashCodes);
		if (result != null)
			return result;

		List<MemcachedCache> caches = helper.getClusterCache();
		for (MemcachedCache cache : caches) {
			if (helper.getCacheClient(keys[0]).equals(cache))
				continue;
			try {
				result = cache.getMultiArray(keys, hashCodes);
			} catch (Exception ex) {
				logerr(cache, "getMultiArray", ex);
			}
			if (result != null)
				break;
		}
		return result;
	}

	@Override
	public Object[] getMultiArray(String[] keys, Integer[] hashCodes, boolean asString) {
		if (keys == null || keys.length <= 0)
			return null;

		Object[] result = helper.getCacheClient(keys[0]).getMultiArray(keys, hashCodes, asString);
		if (result != null)
			return result;

		List<MemcachedCache> caches = helper.getClusterCache();
		for (MemcachedCache cache : caches) {
			if (helper.getCacheClient(keys[0]).equals(cache))
				continue;
			try {
				result = cache.getMultiArray(keys, hashCodes, asString);
			} catch (Exception ex) {
				logerr(cache, "getMultiArray", ex);
			}
			if (result != null)
				break;
		}
		return result;
	}

	@Override
	public long incr(final String key) {
		long result = helper.getCacheClient(key).incr(key);

		processor.executeCommand(new ClusterCommand(){
			@Override
			public void execute(MemcachedCache cache) {
				if(!cache.equals(helper.getCacheClient(key))) cache.incr(key);
			}
		});

		return result;
	}

	@Override
	public long incr(final String key, final long inc) {
		long result = helper.getCacheClient(key).incr(key, inc);

		processor.executeCommand(new ClusterCommand(){
			@Override
			public void execute(MemcachedCache cache) {
				if(!cache.equals(helper.getCacheClient(key))) cache.incr(key, inc);
			}
		});

		return result;
	}

	@Override
	public long incr(final String key, final long inc, final Integer hashCode) {
		long result = helper.getCacheClient(key).incr(key, inc, hashCode);

		processor.executeCommand(new ClusterCommand(){
			@Override
			public void execute(MemcachedCache cache) {
				if(!cache.equals(helper.getCacheClient(key))) cache.incr(key, inc, hashCode);
			}
		});

		return result;
	}

	@Override
	public boolean keyExists(String key) {
		boolean result = false;

		List<MemcachedCache> caches = helper.getClusterCache();
		for (MemcachedCache cache : caches) {
			try {
				result = cache.keyExists(key);
			} catch (Exception ex) {
				logerr(cache, "get", ex);
			}

			if (result == true) {
				break;
			}
		}
		return result;
	}

	@Override
	public boolean replace(final String key, final Object value) {
		boolean result = helper.getCacheClient(key).replace(key, value);

		processor.executeCommand(new ClusterCommand(){
			@Override
			public void execute(MemcachedCache cache) {
				if(!cache.equals(helper.getCacheClient(key))) cache.replace(key, value);
			}
		});
		
		return result;
	}

	@Override
	public boolean replace(final String key, final Object value, final Integer hashCode) {
		boolean result = helper.getCacheClient(key).replace(key, value, hashCode);

		processor.executeCommand(new ClusterCommand(){
			@Override
			public void execute(MemcachedCache cache) {
				if(!cache.equals(helper.getCacheClient(key))) cache.replace(key, value, hashCode);
			}
		});

		return result;
	}

	@Override
	public boolean replace(final String key, final Object value, final Date expiry) {
		boolean result = helper.getCacheClient(key).replace(key, value, expiry);

		processor.executeCommand(new ClusterCommand(){
			@Override
			public void execute(MemcachedCache cache) {
				if(!cache.equals(helper.getCacheClient(key))) cache.replace(key, value, expiry);
			}
		});

		return result;
	}

	@Override
	public boolean replace(final String key, final Object value, final Date expiry, final Integer hashCode) {
		boolean result = helper.getCacheClient(key).replace(key, value, expiry, hashCode);

		processor.executeCommand(new ClusterCommand(){
			@Override
			public void execute(MemcachedCache cache) {
				if(!cache.equals(helper.getCacheClient(key))) cache.replace(key, value, expiry, hashCode);
			}
		});

		return result;
	}

	@Override
	public boolean set(final String key, final Object value) {
		boolean result = helper.getCacheClient(key).set(key, value);

		processor.executeCommand(new ClusterCommand(){
			@Override
			public void execute(MemcachedCache cache) {
				if(!cache.equals(helper.getCacheClient(key))) cache.set(key, value);
			}
		});

		return result;
	}

	@Override
	public boolean set(final String key, final Object value, final Integer hashCode) {
		boolean result = helper.getCacheClient(key).set(key, value, hashCode);

		processor.executeCommand(new ClusterCommand(){
			@Override
			public void execute(MemcachedCache cache) {
				if(!cache.equals(helper.getCacheClient(key))) cache.set(key, value, hashCode);
			}
		});

		return result;
	}

	@Override
	public boolean set(final String key, final Object value, final Date expiry) {
		boolean result = helper.getCacheClient(key).set(key, value, expiry);

		processor.executeCommand(new ClusterCommand(){
			@Override
			public void execute(MemcachedCache cache) {
				if(!cache.equals(helper.getCacheClient(key))) cache.set(key, value, expiry);
			}
		});

		return result;
	}

	@Override
	public boolean set(final String key, final Object value, final Date expiry, final Integer hashCode) {
		boolean result = helper.getCacheClient(key).set(key, value, expiry, hashCode);

		processor.executeCommand(new ClusterCommand(){
			@Override
			public void execute(MemcachedCache cache) {
				if(!cache.equals(helper.getCacheClient(key))) cache.set(key, value, expiry, hashCode);
			}
		});

		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map stats() {
		Map result = new HashMap();
		
		List<MemcachedCache> caches = helper.getClusterCache();
		for (MemcachedCache cache : caches) {
			try {
				result.putAll(cache.stats());
			} catch (Exception ex) {
				logerr(cache, "stats", ex);
			}
		}
		
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map stats(String[] servers) {
		Map result = new HashMap();

		List<MemcachedCache> caches = helper.getClusterCache();
		for (MemcachedCache cache : caches) {
			try {
				result.putAll(cache.stats(servers));
			} catch (Exception ex) {
				logerr(cache, "stats", ex);
			}
		}

		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map statsCacheDump(int slabNumber, int limit) {
		Map result = new HashMap();

		List<MemcachedCache> caches = helper.getClusterCache();
		for (MemcachedCache cache : caches) {
			try {
				result.putAll(cache.statsCacheDump(slabNumber, limit));
			} catch (Exception ex) {
				logerr(cache, "statsCacheDump", ex);
			}
		}

		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map statsCacheDump(String[] servers, int slabNumber, int limit) {
		Map result = new HashMap();

		List<MemcachedCache> caches = helper.getClusterCache();
		for (MemcachedCache cache : caches) {
			try {
				result.putAll(cache.statsCacheDump(servers, slabNumber, limit));
			} catch (Exception ex) {
				logerr(cache, "statsCacheDump", ex);
			}
		}

		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map statsItems() {
		Map result = new HashMap();

		List<MemcachedCache> caches = helper.getClusterCache();
		for (MemcachedCache cache : caches) {
			try {
				result.putAll(cache.statsItems());
			} catch (Exception ex) {
				logerr(cache, "statsItems", ex);
			}
		}

		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map statsItems(String[] servers) {
		Map result = new HashMap();

		List<MemcachedCache> caches = helper.getClusterCache();
		for (MemcachedCache cache : caches) {
			try {
				result.putAll(cache.statsItems(servers));
			} catch (Exception ex) {
				logerr(cache, "statsItems", ex);
			}
		}

		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map statsSlabs() {
		Map result = new HashMap();

		List<MemcachedCache> caches = helper.getClusterCache();
		for (MemcachedCache cache : caches) {
			try {
				result.putAll(cache.statsSlabs());
			} catch (Exception ex) {
				logerr(cache, "statsSlabs", ex);
			}
		}

		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map statsSlabs(String[] servers) {
		Map result = new HashMap();

		List<MemcachedCache> caches = helper.getClusterCache();
		for (MemcachedCache cache : caches) {
			try {
				result.putAll(cache.statsSlabs(servers));
			} catch (Exception ex) {
				logerr(cache, "statsSlabs", ex);
			}
		}

		return result;
	}

	@Override
	public boolean storeCounter(final String key, final long counter) {
		boolean result = helper.getCacheClient(key).storeCounter(key, counter);

		processor.executeCommand(new ClusterCommand(){
			@Override
			public void execute(MemcachedCache cache) {
				if(!cache.equals(helper.getCacheClient(key))) cache.storeCounter(key, counter);
			}
		});

		return result;
	}

	@Override
	public boolean storeCounter(final String key, final Long counter) {
		boolean result = helper.getCacheClient(key).storeCounter(key, counter);

		processor.executeCommand(new ClusterCommand(){
			@Override
			public void execute(MemcachedCache cache) {
				if(!cache.equals(helper.getCacheClient(key))) cache.storeCounter(key, counter);
			}
		});

		return result;
	}

	@Override
	public boolean storeCounter(final String key, final Long counter, final Integer hashCode) {
		boolean result = helper.getCacheClient(key).storeCounter(key, counter, hashCode);

		processor.executeCommand(new ClusterCommand(){
			@Override
			public void execute(MemcachedCache cache) {
				if(!cache.equals(helper.getCacheClient(key))) cache.storeCounter(key, counter, hashCode);
			}
		});

		return result;
	}

	@Override
	public boolean clear() {
		return flushAll();
	}

	@Override
	public Object put(String key, Object value) {
		set(key, value);
		return value;
	}

	@Override
	public Object put(String key, Object value, Date expiry) {
		set(key, value, expiry);
		return value;
	}

	@Override
	public Object put(String key, Object value, int TTL) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.SECOND, TTL);

		set(key, value, calendar.getTime());
		return value;
	}

	@Override
	public Object remove(String key) {
		Object result = helper.getCacheClient(key).get(key);
		delete(key);
		return result;
	}
	
	@Override
	public long addOrIncr(final String key, final long inc, final Date expire, final Integer hashCode) {
		long result = helper.getCacheClient(key).addOrIncr(key, inc, expire, hashCode);

		processor.executeCommand(new ClusterCommand(){
			@Override
			public void execute(MemcachedCache cache) {
				if(!cache.equals(helper.getCacheClient(key))) cache.addOrIncr(key, inc, expire, hashCode);
			}
		});

		return result;
	}

	@Override
	public long addOrDecr(final String key, final long inc, final Date expire, final Integer hashCode) {
		long result = helper.getCacheClient(key).addOrDecr(key, inc, expire, hashCode);

		processor.executeCommand(new ClusterCommand(){
			@Override
			public void execute(MemcachedCache cache) {
				if(!cache.equals(helper.getCacheClient(key))) cache.addOrDecr(key, inc, expire, hashCode);
			}
		});

		return result;
	}

	@Override
	public String getCacheName() {
		StringBuffer sb = new StringBuffer("clustered: { ");
		for (MemcachedCache cache : helper.getClusterCache()) {
			sb.append(cache.getCacheName()).append(" ");
		}
		sb.append("}");
		return sb.toString();
	}

	private void logerr(MemcachedCache cache, String method, Exception ex) {
		log.error("cluster error while executing " + method + " on cache " + cache.getCacheName(), ex);
	}
}
