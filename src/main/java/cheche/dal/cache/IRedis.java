package cheche.dal.cache;

/**
 * Redis原子操作接口
 * 
 * @author jieli
 *
 */
public interface IRedis {
	// === Lock ===
	/**
	 * SET(K, V, "EX", seconds, "NX") = SETNX + EXPIRE
	 * 
	 * @param key
	 * @param seconds 生存时间(s)
	 * @param value
	 * @return
	 */
	Boolean tryLock(String key, long seconds, String value);

	/**
	 * Lua脚本：IF (V == GET(K)) { DEL(K); }
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	Boolean unLock(String key, String value);
}
