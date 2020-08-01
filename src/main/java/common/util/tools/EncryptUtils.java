package common.util.tools;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * 加密工具类(MD5, AES, RSA)
 * 
 * @author jieli
 *
 */
public class EncryptUtils {
	/**
	 * MD5加密字符串(Hex)
	 * 
	 * @param content 待加密的字符串
	 * @return
	 * @throws CommonException
	 */
	public static String md5Hex(String content) {
		return DigestUtils.md5Hex(content);
	}
}
