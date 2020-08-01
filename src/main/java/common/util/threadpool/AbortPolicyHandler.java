package common.util.threadpool;

import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor.AbortPolicy;

/**
 * 线程池饱和策略：直接抛出异常
 * 
 * @author jieli
 *
 */
public class AbortPolicyHandler extends AbortPolicy {
	private final String threadName;

	public AbortPolicyHandler(String threadName) {
		this.threadName = threadName;
	}

	@Override
	public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
		String msg = String.format(
				"Thread pool is EXHAUSTED! Thread Name: %s, Pool Size: %d (active: %d, core: %d, max: %d, largest: %d), Task: %d (completed: %d), Executor status:(isShutdown:%s, isTerminated:%s, isTerminating:%s)",
				threadName, executor.getPoolSize(), executor.getActiveCount(), executor.getCorePoolSize(),
				executor.getMaximumPoolSize(), executor.getLargestPoolSize(), executor.getTaskCount(),
				executor.getCompletedTaskCount(), executor.isShutdown(), executor.isTerminated(),
				executor.isTerminating());
		throw new RejectedExecutionException(msg);
	}
}
