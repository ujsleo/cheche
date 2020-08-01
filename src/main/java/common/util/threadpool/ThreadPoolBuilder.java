package common.util.threadpool;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 线程池工厂类
 * 
 * @author jieli
 *
 */
public class ThreadPoolBuilder {
	/**
	 * new线程池+线程池饱和策略：直接抛出异常
	 * 
	 * @param threadName
	 *            线程池名
	 * @param poolSize
	 *            线程池大小
	 * @param keepAliveTime
	 *            空闲线程存活时间(s)
	 * @param workQueueCapacity
	 *            工作队列容量
	 * @return
	 */
	public static ThreadPoolExecutor newThreadPool(final String threadName, int poolSize, long keepAliveTime,
			int workQueueCapacity) {
		ThreadPoolExecutor ret = new ThreadPoolExecutor(poolSize, poolSize, keepAliveTime, TimeUnit.SECONDS,
				new LinkedBlockingQueue<Runnable>(workQueueCapacity), new ThreadFactory() {
					AtomicInteger ai = new AtomicInteger();

					@Override
					public Thread newThread(Runnable r) {
						Thread ret = new Thread(r, threadName + "-" + ai.getAndIncrement());
						return ret;
					}
				}, new AbortPolicyHandler(threadName));
		ret.allowCoreThreadTimeOut(true);
		return ret;
	}
}
