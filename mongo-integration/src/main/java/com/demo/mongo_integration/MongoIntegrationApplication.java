package com.demo.mongo_integration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Student Management API",
				version = "1.0",
				description = "Documentation for MongoDB Student API"
		)
)
@EnableCaching
public class MongoIntegrationApplication {

	public static void main(String[] args) {
		SpringApplication.run(MongoIntegrationApplication.class, args);
	}

}
