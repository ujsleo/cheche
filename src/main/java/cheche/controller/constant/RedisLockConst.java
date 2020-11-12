package cheche.controller.constant;

/**
 * Constants for Redis Lock
 * 
 * @author jieli
 *
 */
public class RedisLockConst {
	/** 默认过期时间(60s) */
	public final static int DEFAULT_EXPIRES = 60;
    /** 导入操作过期时间(600s) */
    public final static int IMPORT_EXPIRES = 600;

	/** try lock specific process by {process_code} */
	public final static String LOCK_PROCESS_BY_CODE = "CHECHE:LOCK:PROCESS:CODE:%s";
	/** try lock specific process by {process_id} */
	public final static String LOCK_PROCESS_BY_ID = "CHECHE:LOCK:PROCESS:ID:%d";

	/** try lock specific task by {task_id} */
	public final static String LOCK_TASK_BY_ID = "CHECHE:LOCK:TASK:ID:%d";

	/** try lock save template opt */
	public final static String LOCK_TEMPLATE_SAVE = "CHECHE:LOCK:TEMPLATE:SAVE";
	/** try lock specific template by {template_code} */
	public final static String LOCK_TEMPLATE_BY_CODE = "CHECHE:LOCK:TEMPLATE:CODE:%s";
}
