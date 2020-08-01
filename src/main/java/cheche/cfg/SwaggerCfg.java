package cheche.cfg;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger配置 /swagger-ui.html
 * 
 * @author jieli
 * 
 */
@Configuration
@EnableSwagger2
public class SwaggerCfg {
	@Bean
	public Docket docket() {
		return new Docket(DocumentationType.SWAGGER_2) //
				.apiInfo(apiInfo()) //
				.select() //
				.apis(RequestHandlerSelectors.basePackage("cheche.controller")) // 扫描接口basePackage
				.build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder() //
				.title("cheche RESTful APIs") //
				.version("1.0.0") //
				.description("cheche业务审批编排框架") //
				.build();
	}
}
