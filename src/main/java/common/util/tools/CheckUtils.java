package common.util.tools;

/**
 * 校验工具类
 * 
 * @author jieli
 *
 */
public class CheckUtils {
	/**
	 * checkNotNull
	 * 
	 * @param reference
	 * @param errorMessage
	 * @return
	 */
	public static <T> T checkNotNull(T reference, String errorMessage) {
		if (reference == null)
			throw new IllegalArgumentException(errorMessage);
		if (reference instanceof String) {
			String str = (String) reference;
			if (str == null || str.length() == 0)
				throw new IllegalArgumentException(errorMessage);
		}
		return reference;
	}
}
