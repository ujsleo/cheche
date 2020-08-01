package common.util.tools;

/**
 * StopWatch工具类(适用于接口，其它情况建议new StopWatch)
 * 
 * @author jieli
 *
 */
public class StopWatchUtils {
	private static final ThreadLocal<Long> StopWatch = new ThreadLocal<Long>() {
		protected Long initialValue() {
			return System.currentTimeMillis();
		}
	};

	public static final void start() {
		StopWatch.set(System.currentTimeMillis());
	}

	public static final long stop() {
		return System.currentTimeMillis() - StopWatch.get();
	}
}
