package common.util.tools.context;

import org.springframework.context.ApplicationContext;

/**
 * Spring应用上下文
 * 
 * @author jieli
 *
 */
public class ContextUtils {
	/** 持有Spring应用上下文 */
	private static ApplicationContext context;

	public static ApplicationContext getContext() {
		return context;
	}

	public static void setContext(ApplicationContext applicationContext) {
		context = applicationContext;
	}
}
