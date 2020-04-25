package com.ghifar.microservices.composite.product.productcompositeservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;


@SpringBootApplication
@ComponentScan("com.ghifar")
public class ProductCompositeServiceApplication {

	@Value("$(api.common.version)")
	String apiVersion;
	@Value("$(api.common.title)")
	String apiTitle;
	@Value("$(api.common.description)")
	String apiDescription;
	@Value("$(api.common.termsOfServiceUrl)")
	String apiTermsOfServiceUrl;
	@Value("$(api.common.apiLicense)")
	String apiLicense;
	@Value("$(api.common.licenseUrl)")
	String apiLicenseUrl;
	@Value("$(api.common.contact.name)")
	String apiContactName;
	@Value("$(api.common.contact.url)")
	String apiContactUrl;
	@Value("$(api.common.contact.email)")
	String apiContactEmail;



	public static void main(String[] args) {
		SpringApplication.run(ProductCompositeServiceApplication.class, args);
	}

	@Bean
	RestTemplate restTemplate(){
		return new RestTemplate();
	}
}
