package ph.com.metrobank.fin.inst.config;

import java.sql.Timestamp;
import java.util.Collections;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@Profile("dev")
public class SwaggerConfig {
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("ph.com.metrobank.fin.inst.controller")).paths(PathSelectors.any()).build()
				.apiInfo(apiInfo()).directModelSubstitute(Timestamp.class, String.class);
	}
	
	public ApiInfo apiInfo() {
		ApiInfo apiInfo = null;
		apiInfo = new ApiInfo(
				"WSD FINANCIAL INSTITUTION API",
				"Financial Institution API For Payment Gateway",
				"1.0",
				"Terms of Service: ...",
				new Contact("WEBSERVICES", "DDD-Webservices Department", ""),
				"License Version 1.0",
				"", Collections.emptyList());
		return apiInfo;
	}
}