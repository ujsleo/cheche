package cheche;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ImportResource;

import common.util.tools.context.ContextUtils;

/**
 * cheche业务审批编排框架
 * 
 * @author jieli
 *
 */
@ImportResource("classpath:beanRefContext.xml")
@SpringBootApplication
public class ChecheApplication {
	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(ChecheApplication.class, args);
		// 持有Spring应用上下文
		ContextUtils.setContext(ctx);
	}
}
