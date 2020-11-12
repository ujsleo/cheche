package cheche.common.utils;

import java.util.List;

import com.alibaba.fastjson.JSON;

/**
 * JSON工具集
 * 
 * @author jieli
 *
 */
public class JsonUtils {
	public static String toJSONString(Object object) {
		return JSON.toJSONString(object);
	}

	public static <T> T parseObject(String json, Class<T> clazz) {
		return JSON.parseObject(json, clazz);
	}

	public static <T> List<T> parseArray(String json, Class<T> clazz) {
		return JSON.parseArray(json, clazz);
	}
}
