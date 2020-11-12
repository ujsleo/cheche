package cheche.common.executor;

import java.util.concurrent.Future;

/**
 * 抽象JExecutor任务
 * 
 * @author jieli
 * 
 * @param <U> request
 * @param <V> response
 */
public abstract class AbstractExecutorTask<U, V> {
	/** 任务名 */
	protected String name = "AbstractExecutorTask";
	/** 状态 */
	private int status = IN_PROGRESS;
	// === Timeout Policy ===
	/** 超时阈值(s) */
	protected long timeoutSeconds = 10;
	/** 超时策略 */
	protected int timeoutPolicy = TERMINATED;
	// === Retry Logic ===
	/** 当任务被标记为FAILED时，尝试重试的次数 */
	protected long retryCount = 0;
	/** 重试的延时阈值(s) */
	protected long retryDelaySeconds = 0;
	/** 重试逻辑 */
	protected int retryLogic = RETRY_NAN;
	/** 已重试的次数 */
	private long retriedCount;
	// === Delay ===
	/** 延时(s) */
	protected long delaySeconds = 1;

	// === 状态 ===
	/** 失败 */
	public static final int FAILED = -1;
	/** 执行中 */
	public static final int IN_PROGRESS = 0;
	/** 成功 */
	public static final int COMPLETED = 1;
	// === 超时策略 ===
	/** 超时终止 */
	public static final int TERMINATED = 1;
	/** 超时重试 */
	public static final int RETRY = 2;
	// === 重试逻辑 ===
	/** 无需重试 */
	public static final int RETRY_NAN = 0;
	/** 定周期重试：retryDelaySeconds后重试 */
	public static final int RETRY_FIXED = 1;
	/** 定周期重试：(retryDelaySeconds * 剩余重试次数)后重试 */
	public static final int RETRY_BACKOFF = 2;

	public AbstractExecutorTask() {
		init();
	}

	/** 自定义初始化默认任务参数 */
	protected abstract void init();

	/** 同步执行 */
	public V sync(U request) {
		return JExecutor.instance().sync(this, request);
	}

	/** 异步执行 */
	public Future<V> async(U request) {
		return JExecutor.instance().async(this, request);
	}

	/** 异步执行结果(+超时/失败重试) */
	public V get(U request, Future<V> future) {
		return JExecutor.instance().get(this, request, future);
	}

	/** 延迟异步执行 */
	public void delay(U request) {
		JExecutor.instance().delay(this, request);
	}

	/** 直接执行 */
	public V execute(U request) {
		status = IN_PROGRESS;
		V ret = doExecute(request);
		if (status == IN_PROGRESS)
			setCompleted();

		// 重试次数==阈值时，回调处理
		if (retryCount != 0 && retriedCount == retryCount)
			recovery(request);
		return ret;
	}

	/** 自定义执行体 */
	protected abstract V doExecute(U request);

	/** 自定义回调处理 */
	protected abstract V recovery(U request);

	// === 策略组枚举 ===
	/** RETRY策略组 */
	public static final int RETRY_POLICY = 4;

	/** 加载策略 */
	protected void loadPolicy(int policy) {
		switch (policy) {
		case RETRY_POLICY:
			// === Timeout Policy ===
			timeoutSeconds = 10;
			timeoutPolicy = RETRY;
			// === Retry Logic ===
			retryCount = 3;
			retryDelaySeconds = 3;
			retryLogic = RETRY_FIXED;
			break;
		default:
			break;
		}
	}

	/** 失败 */
	public void setFailed() {
		status = FAILED;
	}

	/** 成功 */
	public void setCompleted() {
		status = COMPLETED;
	}

	@Override
	public String toString() {
		return String.format(
				"Task Name: %s, Status: %d, Timeout Policy:(TimeoutSeconds: %d, TimeoutPolicy: %d), Retry Policy:(RetryCount: %d, RetryDelaySeconds: %d, RetryLogic: %d), RetriedCount: %d",
				name, status, timeoutSeconds, timeoutPolicy, retryCount, retryDelaySeconds, retryLogic, retriedCount);
	}

	public String getName() {
		return name;
	}

	public int getStatus() {
		return status;
	}

	public long getTimeoutSeconds() {
		return timeoutSeconds;
	}

	public int getTimeoutPolicy() {
		return timeoutPolicy;
	}

	public long getRetryCount() {
		return retryCount;
	}

	public long getRetryDelaySeconds() {
		return retryDelaySeconds;
	}

	public int getRetryLogic() {
		return retryLogic;
	}

	public long getRetriedCount() {
		return retriedCount;
	}

	public void setRetriedCount(long retriedCount) {
		this.retriedCount = retriedCount;
	}

	public long getDelaySeconds() {
		return delaySeconds;
	}
}
