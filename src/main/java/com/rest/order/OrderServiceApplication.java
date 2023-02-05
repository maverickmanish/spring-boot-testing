package com.rest.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OrderServiceApplication {

	public static void main(String[] args) {
		System.setProperty("server.servlet.context-path", "/adminservices");
		SpringApplication.run(OrderServiceApplication.class, args);
	}

}
