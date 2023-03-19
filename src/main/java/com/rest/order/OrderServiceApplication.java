package com.rest.order;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OrderServiceApplication {

	@Value("${adminuserprivilege.name.role_management_view}")
	private static String roleManagementView;

	public static void main(String[] args) {

		System.setProperty("server.servlet.context-path", "/adminservices");
		SpringApplication.run(OrderServiceApplication.class, args);
		System.out.println("Hiiiii ..."+roleManagementView);
	}

}
