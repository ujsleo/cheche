package common.util.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 线程池
 * 
 * @author jieli
 * 
 */
public class JThreadPool {
	private static volatile JThreadPool _instance = null;
	private static AtomicReference<ExecutorService> es = new AtomicReference<>();

	private static final String THREADNAME = "JThreadPool";
	private static final String POOL_SIZE = "poolSize";
	private static final String KEEP_ALIVE_TIME = "keepAliveTime";
	private static final String WORK_QUEUE_CAPACITY = "workQueueCapacity";

	public static JThreadPool instance() {
		if (_instance == null) {
			synchronized (JThreadPool.class) {
				if (_instance == null)
					_instance = new JThreadPool();
			}
		}
		return _instance;
	}

	private JThreadPool() {
		es.set(ThreadPoolBuilder.newThreadPool(THREADNAME, //
				getConfigParam(POOL_SIZE, 500), //
				getConfigParam(KEEP_ALIVE_TIME, 60), //
				getConfigParam(WORK_QUEUE_CAPACITY, Integer.MAX_VALUE)));
	}

	private int getConfigParam(String key, int defaultValue) {
		if (key == null || key.length() == 0)
			throw new IllegalArgumentException("key can NOT be empty or null");
		if (defaultValue <= 0)
			throw new IllegalArgumentException("defaultValue can NOT <= 0");
		// 配置参数
		return defaultValue;
	}

	public ExecutorService getExecutorService() {
		return es.get();
	}
}
