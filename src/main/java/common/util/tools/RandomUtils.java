package common.util.tools;

import java.security.SecureRandom;
import java.util.Date;

/**
 * 随机数工具类
 * 
 * @author jieli
 *
 */
public class RandomUtils {
	private static SecureRandom random = new SecureRandom();

	/**
	 * 利用标准正态分布N(0, 1)生成满足正态分布N(miu, sigma^2)的随机数序列
	 * 
	 * @param miu
	 *            数学期望
	 * @param sigma
	 *            标准差
	 * @return
	 */
	public static double nextNormalDistribution(double miu, double sigma) {
		return sigma * random.nextGaussian() + miu;
	}

	/**
	 * 服从正态分布的随机数
	 * 
	 * @param miu
	 *            数学期望
	 * @param sigma
	 *            标准差
	 * @param min
	 *            最小值
	 * @param max
	 *            最大值
	 * @return
	 */
	public static double nextNormalDistribution(double miu, double sigma, double min, double max) {
		double ret;
		do {
			ret = nextNormalDistribution(miu, sigma);
		} while (ret < min || ret > max);
		return ret;
	}
	
	/** 生成基于当前时间的20位流水号 */
	public static String nextSN() {
		String prefix = TimeUtils.convert2String(new Date(), "yyyyMMddHHmmssSSS");
		int suffix = random.nextInt(1000);
		if (suffix < 10)
			return prefix + "00" + suffix;
		else if (suffix < 100)
			return prefix + "0" + suffix;
		else
			return prefix + suffix;
	}
}
