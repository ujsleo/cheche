package cheche.demo.cfg;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import cheche.dal.cache.IRedis;
import cheche.demo.dal.cache.DemoRedis;

/**
 * 配置属性cheche.demo=true之后才生效
 * 
 * @author jieli
 *
 */
@Configuration
@ConditionalOnProperty(name = "cheche.demo", havingValue = "true", matchIfMissing = false)
public class DemoCfg {
	@Bean
	public IRedis redis() {
		return new DemoRedis();
	}
}
