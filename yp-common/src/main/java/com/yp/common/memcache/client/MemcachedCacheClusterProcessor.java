package com.yp.common.memcache.client;


/**
 * 提供给Cluster实现进行复制处理
 */
public interface MemcachedCacheClusterProcessor extends Runnable {
	
	/**
	 * 注入MemcachedCacheClusterHelper对象
	 * @param helper
	 */
	public void setHelper(MemcachedCacheClusterHelper helper);
	
	/**
	 * 处理命令
	 * @param command	命令
	 */
	public void executeCommand(ClusterCommand command);
	
	/**
	 * 开始处理
	 */
	public void startProcess();
	
	/**
	 * 停止处理
	 */
	public void stopProcess();
	
	/**
	 * 集群命令接口
	 * @author 冷水
	 *
	 */
	interface ClusterCommand {
		public void execute(MemcachedCache cache);
	}
}
