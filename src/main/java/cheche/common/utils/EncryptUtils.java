package cheche.common.utils;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * 加密工具类
 * 
 * @author jieli
 *
 */
public class EncryptUtils {
	/** MD5 */
	public static String md5Hex(String data) {
		return DigestUtils.md5Hex(data);
	}
}
