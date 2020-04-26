package avinash.springframework.spring5mvcrest.configuration;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicates;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class SwaggerConfig {
	
	@Bean
	public Docket api() {
		
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(metaData())
				.select() 
				.apis(Predicates.not(RequestHandlerSelectors.basePackage("org.springframework.boot")))
				.build();
	}
	
	 private ApiInfo metaData(){

	        Contact contact = new Contact("Avinash", "",
	                "akumart11@gmail.com");

	        return new ApiInfo(
	                "Spring Framework MVC",
	                "Spring Framework 5: MVC RestFull WebServices",
	                "1.0",
	                "Terms of Service: blah",
	                contact,
	                "Apache License Version 2.0",
	                "https://www.apache.org/licenses/LICENSE-2.0",
	                new ArrayList<>());
	    }
}
