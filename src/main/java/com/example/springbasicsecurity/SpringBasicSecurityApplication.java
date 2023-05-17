package com.example.springbasicsecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity
//without this annotation spring security won't work.
public class SpringBasicSecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBasicSecurityApplication.class, args);
	}

}
