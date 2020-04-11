package com.ghifar.microservices.core.product.productservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.ghifar")
//@ComponentScan({"com.ghifar.util.util", "com.ghifar"})
//@ComponentScan("com.ghifar.util.util.exceptions")
@SpringBootApplication
public class ProductServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductServiceApplication.class, args);
	}
}

