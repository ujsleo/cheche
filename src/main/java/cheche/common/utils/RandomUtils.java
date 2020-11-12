package cheche.common.utils;

import java.security.SecureRandom;
import java.util.Date;

import org.apache.commons.lang3.time.DateFormatUtils;

/**
 * 随机数工具类
 * 
 * @author jieli
 *
 */
public class RandomUtils {
	private static SecureRandom random = new SecureRandom();

	/**
	 * 利用标准正态分布N(0, 1)来生成满足正态分布N(μ, σ^2)的随机数
	 * 
	 * @param miu   数学期望μ决定其位置
	 * @param sigma 标准差σ决定分布的幅度
	 * @return
	 */
	public static double nextNormalDistribution(double miu, double sigma) {
		return sigma * random.nextGaussian() + miu;
	}

	/**
	 * 生成满足正态分布N(μ, σ^2)的随机数，且其在指定区间范围内
	 * 
	 * @param miu   数学期望μ决定其位置
	 * @param sigma 标准差σ决定分布的幅度
	 * @param min   最小值
	 * @param max   最大值
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
		String prefix = DateFormatUtils.format(new Date(), "yyyyMMddHHmmssSSS");
		int suffix = random.nextInt(1000);
		if (suffix < 10)
			return prefix + "00" + suffix;
		else if (suffix < 100)
			return prefix + "0" + suffix;
		else
			return prefix + suffix;
	}
}
