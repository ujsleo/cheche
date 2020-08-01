package common.util.executor;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicReference;

import common.util.threadpool.JThreadPool;
import common.util.threadpool.ThreadPoolConstants;

/**
 * 执行器
 * 
 * @author jieli
 * 
 */
public class JExecutor {
	private static volatile JExecutor _instance = null;
	private static ExecutorService es = JThreadPool.instance().getExecutorService();
	private static AtomicReference<ScheduledExecutorService> ses = new AtomicReference<>();

	public static JExecutor instance() {
		if (_instance == null) {
			synchronized (JExecutor.class) {
				if (_instance == null) {
					_instance = new JExecutor();
				}
			}
		}
		return _instance;
	}

	private JExecutor() {
		ses.set(Executors.newScheduledThreadPool(ThreadPoolConstants.N_THREADS_IO_INTENSIVE));
	}

	/** 同步执行 */
	public <U, V> V sync(final AbstractExecutorTask<U, V> task, final U request) {
		Future<V> future = async(task, request);
		return get(task, request, future);
	}

	/** 异步执行 */
	public <U, V> Future<V> async(final AbstractExecutorTask<U, V> task, final U request) {
		return es.submit(new Callable<V>() {
			@Override
			public V call() throws Exception {
				return task.execute(request);
			}
		});
	}

	/** 异步执行结果(+超时/失败重试) */
	public <U, V> V get(final AbstractExecutorTask<U, V> task, final U request, Future<V> future) {
		V ret = null;
		try {
			ret = future.get(task.getTimeoutSeconds(), TimeUnit.SECONDS);
		} catch (InterruptedException | ExecutionException e) {
			throw new RuntimeException(e);
		} catch (TimeoutException e) {
			// log.info("JExecutor", e);
			switch (task.getTimeoutPolicy()) {
			case AbstractExecutorTask.TERMINATED:
				task.setCompleted();
				break;
			case AbstractExecutorTask.RETRY:
				task.setFailed();
				break;
			default:
				break;
			}
		}

		if (task.getStatus() == AbstractExecutorTask.FAILED) {
			Long retryDelaySeconds = check4Retry(task);
			if (retryDelaySeconds > 0) {
				return sync(task, request);
			}
		}
		return ret;
	}

	/** 延迟异步执行 */
	public <U, V> void delay(final AbstractExecutorTask<U, V> task, final U request) {
		long delaySeconds = task.getDelaySeconds();
		if (delaySeconds <= 0)
			throw new IllegalArgumentException("task arg: delay_seconds illegal.");
		ses.get().schedule(new Runnable() {
			@Override
			public void run() {
				task.execute(request);
			}
		}, delaySeconds, TimeUnit.SECONDS);
	}

	/** 计算下次重试的时间 */
	private <U, V> Long check4Retry(AbstractExecutorTask<U, V> task) {
		long ret = 0;
		long retriedCount = task.getRetriedCount();
		if (retriedCount < task.getRetryCount()) {
			task.setRetriedCount(retriedCount + 1);
			long retryDelaySeconds = task.getRetryDelaySeconds();
			if (retryDelaySeconds > 0) {
				// Retry Logic
				// retry... - but not immediately - put a delay...
				switch (task.getRetryLogic()) {
				case AbstractExecutorTask.RETRY_FIXED:
					break;
				case AbstractExecutorTask.RETRY_BACKOFF:
					retryDelaySeconds = retryDelaySeconds * (1 + task.getRetriedCount());
					break;
				default:
					break;
				}
			}
			ret = retryDelaySeconds;
		}
		return ret;
	}
}
