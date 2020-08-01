package common.util.tools;

/**
 * Log工具类(log pid, 耗时)
 * 
 * @author jieli
 * 
 */
public class LogUtils {
	private static final ThreadLocal<String> PID = new ThreadLocal<String>() {
		protected String initialValue() {
			return RandomUtils.nextSN();
		}
	};

	/**
	 * 
	 * @return log pid
	 */
	public static final String pid() {
		return PID.get();
	}

	/**
	 * default pattern
	 * 
	 * @return [pid:] [title] - msg
	 */
	public static final String pattern() {
		return String.format("[pid: %s] [{}] - {}", pid());
	}

	/**
	 * Controller request pattern
	 * 
	 * @return [pid:] [title] - request:
	 */
	public static final String requestPattern() {
		StopWatchUtils.start();
		return String.format("[pid: %s] [{}] - request: {}", pid());
	}

	/**
	 * Controller response pattern
	 * 
	 * @return [pid:] [title] - elapsed (ms): , response:
	 */
	public static final String responsePattern() {
		long elapsed = StopWatchUtils.stop(); // 执行耗时
		return String.format("[pid: %s] [{}] - elapsed (ms): %d, response: {}",
				pid(), elapsed);
	}

	/**
	 * Controller error pattern
	 * 
	 * @return [pid:] [title] - error:
	 */
	public static final String errorPattern(String title) {
		return String.format("[pid: %s] [%s] - error: ", pid(), title);
	}
}
