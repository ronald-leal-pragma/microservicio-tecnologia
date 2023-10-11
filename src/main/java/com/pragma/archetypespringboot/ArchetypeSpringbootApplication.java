package com.pragma.archetypespringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class ArchetypeSpringbootApplication {

	public static void main(String[] args) {SpringApplication.run(ArchetypeSpringbootApplication.class, args);}
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/*").allowedOrigins("");
			}
		};
	}
	public static final String USE_CASE_ROUTE = "com.pragma.archetypespringboot.*.domain.usecases";
	public static final String SERVICE_ROUTE = "com.pragma.archetypespringboot.*.application.services.impl";
	public static final String ADAPTER_ROUTE = "com.pragma.archetypespringboot.*.infrastructure.adapters";

}
