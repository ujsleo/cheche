package common.util.tools;

import java.math.BigDecimal;

/**
 * Number工具类
 * 
 * @author jieli
 *
 */
public class NumberUtils {
	public static <T> Integer convert2Integer(T rhs) {
		if (rhs == null)
			return null;
		if (rhs instanceof Integer)
			return (Integer) rhs;
		if (rhs instanceof Number) {
			Number num = (Number) rhs;
			return num.intValue();
		} else if (rhs instanceof String) {
			return Integer.parseInt((String) rhs);
		} else {
			throw new IllegalArgumentException("type NOT support");
		}
	}

	public static <T> Long convert2Long(T rhs) {
		if (rhs == null)
			return null;
		if (rhs instanceof Long)
			return (Long) rhs;
		if (rhs instanceof Number) {
			Number num = (Number) rhs;
			return num.longValue();
		} else if (rhs instanceof String) {
			return Long.parseLong((String) rhs);
		} else {
			throw new IllegalArgumentException("type NOT support");
		}
	}

	public static <T> Short convert2Short(T rhs) {
		if (rhs == null)
			return null;
		if (rhs instanceof Short)
			return (Short) rhs;
		if (rhs instanceof Number) {
			Number num = (Number) rhs;
			return num.shortValue();
		} else if (rhs instanceof String) {
			return Short.parseShort((String) rhs);
		} else {
			throw new IllegalArgumentException("type NOT support");
		}
	}

	public static <T> Double convert2Double(T rhs) {
		if (rhs == null)
			return null;
		if (rhs instanceof Double)
			return (Double) rhs;
		if (rhs instanceof Number) {
			Number num = (Number) rhs;
			return num.doubleValue();
		} else if (rhs instanceof String) {
			return Double.parseDouble((String) rhs);
		} else {
			throw new IllegalArgumentException("type NOT support");
		}
	}

	public static <T> BigDecimal convert2BigDecimal(T rhs) {
		if (rhs == null)
			return null;
		if (rhs instanceof BigDecimal)
			return (BigDecimal) rhs;
		if (rhs instanceof Number) {
			Number num = (Number) rhs;
			return BigDecimal.valueOf(num.doubleValue());
		} else if (rhs instanceof String) {
			return BigDecimal.valueOf(convert2Double(rhs));
		} else {
			throw new IllegalArgumentException("type NOT support");
		}
	}
}
