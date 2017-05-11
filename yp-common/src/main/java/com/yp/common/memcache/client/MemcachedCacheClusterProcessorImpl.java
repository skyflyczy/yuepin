package com.yp.common.memcache.client;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 为了提高性能，对于Cluster内的数据作异步同步
 * 
 */
public class MemcachedCacheClusterProcessorImpl extends Thread implements MemcachedCacheClusterProcessor {
	private final Log log = LogFactory.getLog(MemcachedCacheClusterProcessorImpl.class);
	
	private MemcachedCacheClusterHelper helper;

	boolean isRunning = true;
	
	/**
	 * 数据队列
	 */
	private LinkedBlockingQueue<ClusterCommand> dataQueue;

	/**
	 * 执行异步更新Cluster的线程池
	 */
	private ExecutorService clusterProcessorPool;

	/**
	 * 无参数构造方法
	 */
	public MemcachedCacheClusterProcessorImpl() {
		clusterProcessorPool = Executors.newFixedThreadPool(10);
		dataQueue = new LinkedBlockingQueue<ClusterCommand>();
	}

	/**
	 * 异步执行方法
	 */
	public void run() {
		while (isRunning) {
			process();
		}
	}
	
	/**
	 * 线程开始执行
	 */
	public void startProcess() {
		this.setDaemon(true);
		this.start();
	}

	/**
	 * 线程停止
	 */
	public void stopProcess() {
		isRunning = false;

		try {
			if (clusterProcessorPool != null)
				clusterProcessorPool.shutdown();

			clusterProcessorPool = null;
		} catch (Exception ex) {
			log.error(ex);
		}
	}

	private void process() {
		ClusterCommand commands = null;

		try {
			commands = dataQueue.take();

			if (commands != null)
				clusterProcessorPool.execute(new ClusterUpdateJob(commands));
		} catch (Exception ex) {
			log.error("process cluster input error!", ex);
		}
	}

	private class ClusterUpdateJob implements java.lang.Runnable {
		ClusterCommand command;

		public ClusterUpdateJob(ClusterCommand commands) {
			this.command = commands;
		}

		public void run() {
			if (command != null) {
				List<MemcachedCache> caches = helper.getClusterCache();

				for (MemcachedCache cache : caches) {
					try {
						command.execute(cache);
					} catch (Exception ex) {
						log.error("memcached cluster process error", ex);
					}
				}

			}
		}

	}

	/**
	 * 将命令加入队列等待执行
	 * @param command 命令
	 */
	public void executeCommand(ClusterCommand command) {
		dataQueue.add(command);
	}

	public void setHelper(MemcachedCacheClusterHelper helper) {
		this.helper = helper;
	}
}
