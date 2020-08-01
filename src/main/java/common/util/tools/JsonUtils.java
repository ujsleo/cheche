package common.util.tools;

import java.util.List;

import com.alibaba.fastjson.JSON;

/**
 * JSON工具集
 * 
 * @author jieli
 *
 */
public class JsonUtils {
	/**
	 * 对象转JSON字符串
	 * 
	 * @param obj
	 * @return
	 * @throws CommonException
	 */
	public static <T> String convert2Json(T obj) {
		return JSON.toJSONString(obj);
	}

	/**
	 * JSON字符串转对象
	 * 
	 * @param json
	 * @param clazz
	 * @return
	 */
	public static <T> T convert2T(String json, Class<T> clazz) {
		CheckUtils.checkNotNull(json, "json can NOT be empty or null");
		return JSON.parseObject(json, clazz);
	}

	/**
	 * JSON字符串转列表
	 * 
	 * @param json
	 * @param clazz
	 * @return
	 */
	public static <T> List<T> convert2List(String json, Class<T> clazz) {
		CheckUtils.checkNotNull(json, "json can NOT be empty or null");
		return JSON.parseArray(json, clazz);
	}
}
