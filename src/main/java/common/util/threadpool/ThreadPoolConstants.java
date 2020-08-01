package common.util.threadpool;

/**
 * 线程池常量
 * 
 * @author jieli
 *
 */
public class ThreadPoolConstants {
	/** number of CPUs */
	private static final int N_CPUS = Runtime.getRuntime().availableProcessors();
	/** IO密集型任务的并发线程数 */
	public static final int N_THREADS_IO_INTENSIVE = N_CPUS * 2;
	/** 计算密集型任务的并发线程数 */
	public static final int N_THREADS_COMPUTE_INTENSIVE = N_CPUS;
}
