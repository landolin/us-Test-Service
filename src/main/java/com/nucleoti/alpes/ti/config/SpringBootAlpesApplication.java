package com.nucleoti.alpes.ti.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class SpringBootAlpesApplication implements WebMvcConfigurer {
	//Clase que inicia el microservicio.
	public static void main(String[] args) {
		SpringApplication.run(SpringBootAlpesApplication.class, args);
	}

}
