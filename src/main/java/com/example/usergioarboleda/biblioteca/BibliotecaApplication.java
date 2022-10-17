package com.example.usergioarboleda.biblioteca;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication(exclude={SecurityAutoConfiguration.class})
public class BibliotecaApplication {


	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/book/**")
					.allowedOrigins("http://localhost:8080")
					.allowedMethods("GET", "POST", "PUT", "DELETE")
					.maxAge(3600);
				registry.addMapping("/editorial/**")
					.allowedOrigins("http://localhost:8080")
					.allowedMethods("GET", "POST", "PUT", "DELETE")
					.maxAge(3600);
				registry.addMapping("/author/**")
					.allowedOrigins("http://localhost:8080")
					.allowedMethods("GET", "POST", "PUT", "DELETE")
					.maxAge(3600);
			}
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(BibliotecaApplication.class, args);
	}
}
