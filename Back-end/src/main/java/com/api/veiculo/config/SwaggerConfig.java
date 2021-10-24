package com.api.veiculo.config;


import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.api.veiculo.controller"))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(apiInfo())
;
	}

	private ApiInfo apiInfo() {
		return new ApiInfo(
				"Veiculo com Spring Boot v2.4.12.BUILD-SNAPSHOT", 
				"Descrição da api.",
				"v1",
				"Terms Of Service Url",
				new Contact("Veículo", "", "daniel.si@outlook.com.br"),
				"License of API", "License of URL", Collections.emptyList());
	}
}