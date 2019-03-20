package org.blackboa.swaggerconfig;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import springfox.documentation.annotations.ApiIgnore;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig  extends WebMvcConfigurerAdapter{
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("swagger-ui.html")
				.addResourceLocations("classpath:/META-INF/resources/");
		registry.addResourceHandler("/webjars*")
				.addResourceLocations("classpath:/META-INF/resources/webjars/");
	}
	
	@Bean
	public Docket createRestApi(){
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo())
				.select()
				.build()
				.ignoredParameterTypes(ApiIgnore.class)
				.enableUrlTemplating(false)
				.securitySchemes(Arrays.asList(apiKey()));
	}
	
	
	@Bean
	public SecurityScheme apiKey(){
		return new ApiKey("blackboa", "blackboa", "blackboa");
	}
	
	private ApiInfo apiInfo(){
		return new ApiInfoBuilder()
				.title("BlackBoa")
				.description("表信息生成工具")
				.version("1.1.0")
				.termsOfServiceUrl("no terms of service")
				.contact(new Contact("", "", ""))
				.license("The Apache License,Version 2.0")
				.licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
				.build();
	}
}
