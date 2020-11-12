package cheche.common.utils;

/**
 * Log工具类(pid, 耗时)
 * 
 * @author jieli
 * 
 */
public class LogUtils {
	/** pid 流水号 */
	private static final ThreadLocal<String> PID = new ThreadLocal<String>() {
		protected String initialValue() {
			return RandomUtils.nextSN();
		}
	};
	/** 执行耗时 */
	private static final ThreadLocal<Long> StopWatch = new ThreadLocal<Long>() {
		protected Long initialValue() {
			return System.currentTimeMillis();
		}
	};

	/**
	 * default pattern
	 * 
	 * @return [pid] [title] - {}
	 */
	public static final String pattern(String title) {
		return String.format("[pid: %s] [%s] - {}", pid(), title);
	}

	/**
	 * Controller request pattern
	 * 
	 * @return [pid] [title] - request: {}
	 */
	public static final String requestPattern(String title) {
		start();
		return String.format("[pid: %s] [%s] - request: {}", pid(), title);
	}

	/**
	 * Controller response pattern
	 * 
	 * @return [pid] [title] - elapsed (ms), response: {}
	 */
	public static final String responsePattern(String title) {
		long elapsed = stop();
		return String.format("[pid: %s] [%s] - elapsed (ms): %d, response: {}", pid(), title, elapsed);
	}

	/**
	 * Controller error pattern
	 * 
	 * @return [pid] [title] - elapsed (ms), error: {}
	 */
	public static final String errorPattern(String title) {
		long elapsed = stop();
		return String.format("[pid: %s] [%s] - elapsed (ms): %d, error: ", pid(), title, elapsed);
	}

	/** pid 流水号 */
	public static final String pid() {
		return PID.get();
	}

	private static final void start() {
		StopWatch.set(System.currentTimeMillis());
	}

	private static final long stop() {
		return System.currentTimeMillis() - StopWatch.get();
	}
}
