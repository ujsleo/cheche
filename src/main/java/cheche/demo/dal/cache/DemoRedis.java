package cheche.demo.dal.cache;

import java.util.concurrent.TimeUnit;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import cheche.controller.constant.RedisLockConst;
import cheche.dal.cache.IRedis;

/**
 * 
 * @author jieli
 *
 */
public class DemoRedis implements IRedis {
	/** 内存缓存 */
	private Cache<String, String> cache = CacheBuilder.newBuilder() //
			.maximumSize(1000) // 缓存池大小
			.expireAfterWrite(RedisLockConst.DEFAULT_EXPIRES, TimeUnit.SECONDS) // 过期时间
			.build();

	@Override
	public Boolean tryLock(String key, long seconds, String value) {
		if (cache.getIfPresent(key) != null)
			return false;

		cache.put(key, value);
		return true;
	}

	@Override
	public Boolean unLock(String key, String value) {
		if (value.equals(cache.getIfPresent(key))) {
			cache.invalidate(key);
			return false;
		}
		return true;
	}
}
