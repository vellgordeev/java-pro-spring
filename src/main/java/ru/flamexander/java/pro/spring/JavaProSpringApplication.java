package ru.flamexander.java.pro.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// -Dspring.profiles.active=dev

@SpringBootApplication
public class JavaProSpringApplication {
	public static void main(String[] args) {
		SpringApplication.run(JavaProSpringApplication.class, args);
	}
}
